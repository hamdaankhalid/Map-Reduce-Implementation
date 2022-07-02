package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.Helper;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This implements an interface so that later on we can swap this implementation for an implementation
 * that will be reading writing to a distributed file system instead of our current local file system
 */
public class InMemoryFileInteraction implements  IFileInteractor {
    private String dataDirectory;
    private int reduceCount;

    public InMemoryFileInteraction(String dataDirectory, int reduceCount) {
        this.dataDirectory = dataDirectory;
        this.reduceCount = reduceCount;
    }


    public void serializeMapToDisk(List<KeyValuePair> kvPairs, String mapTaskId) throws IOException {

        for(KeyValuePair keyValuePair: kvPairs) {
            // write to a different file each of the results
            int reduceBucket = Helper.MOD_FILE_WRITE_DECIDE(keyValuePair.getKey(), reduceCount);
            String intermediateFileName = "reduce_" + mapTaskId + "_"+ reduceBucket+ "_intermediate";
            File intermediateFile = new File(intermediateFileName);
            if (!intermediateFile.exists()) {
                intermediateFile.createNewFile();
            }
            FileWriter filewriter = new FileWriter(intermediateFile);
            filewriter.append(keyValuePair.getKey()+" : "+ keyValuePair.getValue());
        }

    }

    public void serializeReduceOutputToDisk(List<KeyValuePair> reduceOutput, String reducedForFile) {
        // find the reduce bucket this file was run for

        // create / or write to the appropriate output_i


    }

    public String readFromFile(String filename) throws IOException {
        String content = Files.readString(Paths.get(dataDirectory + "/" + filename));
        return content;
    }
}
