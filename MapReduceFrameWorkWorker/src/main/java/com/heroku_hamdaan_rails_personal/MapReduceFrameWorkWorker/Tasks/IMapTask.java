package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.util.List;

/**
 * All map tasks will need to follow this interace
 */
public interface IMapTask {
    List<KeyValuePair> map(String content);

}
