package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Response.RequestTaskResponse;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.IMapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.IReduceTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.MapRunner;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.ReduceRunner;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.examples.WordCountMap;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.examples.WordCountReduce;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.client.MasterClient;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.CompletedTaskBody;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor.IFileInteractor;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.Helper;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor.InMemoryFileInteraction;

public class Main {
    public static void main(String[] args) {
        IFileInteractor fileSystemInteraction = new InMemoryFileInteraction("/Users/hamdaankhalid/Desktop/MapReduce/data/test", 4);

        // CHANGE THESE TO WHATEVER MAP AND REDUCE YOU WANT TO RUN
        IMapTask mapFunc = new WordCountMap();
        IReduceTask reduceFunc = new WordCountReduce();

        MapRunner mapExecutor = new MapRunner(mapFunc , fileSystemInteraction);
        ReduceRunner reduceExecutor = new ReduceRunner(reduceFunc, fileSystemInteraction);
        MasterClient masterClient = new MasterClient();

        while (true) {
            try {
                RequestTaskResponse requestTaskResponse = masterClient.requestTask();

                System.out.println("Task sent by master " + requestTaskResponse);

                if (requestTaskResponse.isFinished()) {
                    System.out.println("All maps and reduces have been processed. Exiting worker process!");
                    break;
                } else if (requestTaskResponse.getMapTask() == null && requestTaskResponse.getReduceTask() == null) {
                    System.out.println("Master responded with wait... resting for 5 seconds");
                    Helper.SLEEP(5);
                } else if (requestTaskResponse.getMapTask() != null) {
                    System.out.println("Executing map task");
                    // run map task, and notify
                    MapTask mapTask = mapExecutor.map(requestTaskResponse.getMapTask());
                    System.out.println("Map Task Executed "+ mapTask);
                    masterClient.notifyTaskCompleted(
                            new CompletedTaskBody(true, mapTask, null)
                    );
                } else {
                    System.out.println("Executing reduce task");
                    // run reduce task, and notify
                    Integer reducedOn = reduceExecutor.reduce(requestTaskResponse.getReduceTask());
                    System.out.println("Reduce Task Executed for "+ reducedOn);
                    masterClient.notifyTaskCompleted(
                            new CompletedTaskBody(false, null, reducedOn)
                    );
                }
            } catch (Exception e) {
                System.out.println("Error in execution"+ e);
                System.out.println(e.getMessage());
                Helper.SLEEP(3);
            }
        }
    }
}
