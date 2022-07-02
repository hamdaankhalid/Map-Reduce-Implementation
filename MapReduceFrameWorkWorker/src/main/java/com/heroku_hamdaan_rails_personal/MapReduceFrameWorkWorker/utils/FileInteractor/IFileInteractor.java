package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.FileInteractor;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFileInteractor {
    public void serializeMapToDisk(List<KeyValuePair> kvPairs, String mapTaskNum) throws IOException;
    public void serializeReduceOutputToDisk(List<KeyValuePair> reduceOutput, String reducedForFile) throws IOException;
    public String readFromFile(String filename) throws IOException;
}
