package com.galvix.assignment.controller;

import com.galvix.assignment.dto.LogResponse;
import com.galvix.assignment.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogRestController {
    private LogService logService;

    @GetMapping
    public ResponseEntity<LogResponse> getLogs(
            @RequestParam Map<String,String> queryParams
    ) {
        return new ResponseEntity<>(logService.getLogsWithFilter(queryParams), HttpStatus.OK);
    }
}
