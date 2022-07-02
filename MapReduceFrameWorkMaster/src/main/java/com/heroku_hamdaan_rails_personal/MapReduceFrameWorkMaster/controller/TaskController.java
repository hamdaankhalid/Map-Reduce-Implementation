package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.controller;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto.TaskCompletedRequest;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto.TaskResponse;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    MasterService masterService;

    @GetMapping("/task")
    public ResponseEntity<TaskResponse> getTask() {
        return ResponseEntity.status(HttpStatus.OK).body(masterService.getTaskToExecute());
    }

    @PostMapping("/task")
    public ResponseEntity<String> markTaskComplete(@RequestBody TaskCompletedRequest taskCompletedRequest) {
        masterService.markTaskComplete(taskCompletedRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body("created!");
    }
}
