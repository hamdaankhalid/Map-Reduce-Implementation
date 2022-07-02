package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.controller;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto.StatusResponse;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowStatusController {
    @Autowired
    MasterService masterService;

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> getStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(masterService.getStatus());
    }
}
