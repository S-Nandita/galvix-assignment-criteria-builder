package com.galvix.assignment.controller;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogRestController {
    private LogService logService;

    @PostMapping
    public void saveLog(@RequestBody Log log) {
        logService.saveLog(log);
    }

    @GetMapping
    public List<Log> getLogs(
            @RequestParam(value = "serviceName",required = false) String serviceName
    ) {
        return logService.getLogsWithFilter(serviceName);
    }
}
