package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.*;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor.IFileInteractor;

import java.io.IOException;
import java.util.List;

public class MapRunner {
    private IMapTask mapFunc;
    private IFileInteractor fileSystemInteraction;

    public MapRunner(IMapTask mapFunc, IFileInteractor fileSystemInteraction) {
        this.mapFunc = mapFunc;
        this.fileSystemInteraction = fileSystemInteraction;
    }

    public MapTask map(MapTask mapTask) throws IOException {
        String filename = mapTask.getFileName();
        String content = fileSystemInteraction.readFromFile(filename);
        String mapTaskNum = filename.substring("input".length());
        List<KeyValuePair> output = mapFunc.map(content);
        if (output!=null) {
            fileSystemInteraction.serializeMapToDisk(output, mapTaskNum);
        }
        // send it as in progress / it will be marked as completed by server
        return new MapTask(filename, TaskStatus.IN_PROGRESS);
    }
}
