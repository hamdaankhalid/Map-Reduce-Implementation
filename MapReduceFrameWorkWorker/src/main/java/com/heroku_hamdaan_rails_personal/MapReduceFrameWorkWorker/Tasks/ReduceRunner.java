package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor.IFileInteractor;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.io.IOException;
import java.util.*;


public class ReduceRunner {
    private IReduceTask reduceFunc;
    private IFileInteractor fileSystemInteraction;

    public ReduceRunner(IReduceTask reduceFunc, IFileInteractor fileSystemInteraction) {
        this.reduceFunc = reduceFunc;
        this.fileSystemInteraction = fileSystemInteraction;
    }

    public Integer reduce(Integer reduceTask) throws IOException {
        List<String> intermediateFiles = fileSystemInteraction.getIntermediateFiles(reduceTask);
        Map<String, List<String>> sortedIntermediate = new HashMap<>();
        for (String filename: intermediateFiles) {
            List<String> contentByLine = fileSystemInteraction.readLinesFromFile(filename);
            for (String line: contentByLine) {
                String[] keyVal = line.split(":");
                String key = keyVal[0];
                String val = keyVal[1];
                if (sortedIntermediate.containsKey(key)) {
                    sortedIntermediate.get(key).add(val);
                } else {
                    sortedIntermediate.put(key, new ArrayList<>(List.of(val)));
                }
            }
        }
        for(String key: sortedIntermediate.keySet()) {
            KeyValuePair reduced = reduceFunc.reduce(key, sortedIntermediate.get(key));
            fileSystemInteraction.serializeReduceOutputToDisk(reduced, reduceTask);
        }
        return reduceTask;
    }
}
