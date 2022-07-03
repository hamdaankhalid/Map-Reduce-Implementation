package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.io.IOException;
import java.util.List;

public interface IFileInteractor {
    public void serializeMapToDisk(List<KeyValuePair> kvPairs, String mapTaskNum) throws IOException;
    public void serializeReduceOutputToDisk(KeyValuePair reduceOutput, int reduceTask) throws IOException;
    public String readFromFile(String filename) throws IOException;

    public List<String> getIntermediateFiles(Integer reduceTask);

    public List<String> readLinesFromFile(String filename) throws IOException;
}
