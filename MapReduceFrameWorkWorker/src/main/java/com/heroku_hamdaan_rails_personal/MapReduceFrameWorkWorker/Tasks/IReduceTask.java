package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.util.List;

/**
 * All reduce tasks will need to follow this interace
 *  reduce (k2,list(v2)) → list(v2)
 */
public interface IReduceTask {
    List<KeyValuePair> reduce(String content);
}
