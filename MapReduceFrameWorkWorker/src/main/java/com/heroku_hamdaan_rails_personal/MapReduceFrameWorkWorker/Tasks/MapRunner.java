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
        String mapTaskNum = filename.split("_")[1];

        List<KeyValuePair> output = mapFunc.map(content);
        fileSystemInteraction.serializeMapToDisk(output, mapTaskNum);

        return new MapTask(mapTask.getFileName(), TaskStatus.COMPLETED, mapTask.getMapTaskId());
    }
}
