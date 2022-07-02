package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor.IFileInteractor;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ReduceRunner {
    private IReduceTask reduceFunc;
    private IFileInteractor fileSystemInteraction;

    public ReduceRunner(IReduceTask reduceFunc, IFileInteractor fileSystemInteraction) {
        this.reduceFunc = reduceFunc;
        this.fileSystemInteraction = fileSystemInteraction;
    }

    public String reduce(String runReduceOnFile) throws IOException {
        String contentToReduce = fileSystemInteraction.readFromFile(runReduceOnFile);
        List<KeyValuePair> reduceOutput = reduceFunc.reduce(contentToReduce);
        fileSystemInteraction.serializeReduceOutputToDisk(reduceOutput, runReduceOnFile);
        return runReduceOnFile;
    }
}
