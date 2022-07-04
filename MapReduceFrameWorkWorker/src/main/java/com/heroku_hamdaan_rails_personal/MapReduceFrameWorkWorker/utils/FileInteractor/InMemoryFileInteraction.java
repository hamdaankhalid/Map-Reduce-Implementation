package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.Helper;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        System.out.println("Serializing Kv list from map of length " + kvPairs.size() + " to disk");

        for(KeyValuePair keyValuePair: kvPairs) {
            // write to a different file each of the results
            try {
                int reduceBucket = Helper.MOD_FILE_WRITE_DECIDE(keyValuePair.getKey(), reduceCount);
                String intermediateFileName = "reduce_" + mapTaskId + "_"+ reduceBucket+ "_intermediate";
                if (Files.notExists(Path.of(dataDirectory + "/" + intermediateFileName))) {
                    new File(dataDirectory+"/"+intermediateFileName).createNewFile();
                }
                FileWriter filewriter = new FileWriter(dataDirectory+"/"+intermediateFileName, true);
                BufferedWriter bw = new BufferedWriter(filewriter);
                bw.append(keyValuePair.getKey()+":"+ keyValuePair.getValue());
                bw.newLine();
                bw.close();
            } catch (Exception e) {
                System.out.println("ERROR While serializing kv pair to disk"+ keyValuePair.getKey() + e);
            }
        }

        System.out.println("Serialized map to disk successfully for map task for file " + mapTaskId);
    }

    public void serializeReduceOutputToDisk(KeyValuePair reduceOutput, int reduceTask) throws IOException {
        String outputFileName = "Output_"+reduceTask;
        if (Files.notExists(Path.of(dataDirectory + "/" + outputFileName))) {
            new File(dataDirectory+"/"+outputFileName).createNewFile();
        }
        FileWriter filewriter = new FileWriter(dataDirectory+"/"+outputFileName, true);
        BufferedWriter bw = new BufferedWriter(filewriter);
        bw.append(reduceOutput.getKey()+":"+reduceOutput.getValue());
        bw.newLine();
        bw.close();
        System.out.println(outputFileName);
    }

    public String readFromFile(String filename) throws IOException {
        String content = Files.readString(Paths.get(dataDirectory + "/" + filename));
        return content;
    }

    public List<String> readLinesFromFile(String filename) throws IOException {
        List<String> content = Files.readAllLines(Paths.get(dataDirectory + "/" + filename));
        return content;
    }

    public List<String> getIntermediateFiles(Integer reduceTask) {
        List<String> intermediateFiles = new ArrayList<String>();
        File[] allFiles = new File(this.dataDirectory).listFiles();
        for(File file: allFiles) {
            String filename = file.getName();
            if (!filename.substring(0, "reduce".length()).equals("reduce")) {
                continue;
            }

            String reduceTaskNum = filename.split("_")[2];

            if (reduceTaskNum.equals(String.valueOf(reduceTask))) {
                intermediateFiles.add(filename);
            }
        }
        return intermediateFiles;
    }
}
