package com.galvix.assignment.controller;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            @RequestParam(value = "serviceName[is]", required = false) String serviceNameIs,
            @RequestParam(value = "serviceName[isNot]", required = false) String serviceNameIsNot,
            @RequestParam(value = "serviceName[isAnyOf]", required = false) String serviceNameIsAnyOf,
            @RequestParam(required = false) int statusCode
    ) {
        return logService.getLogsWithFilter(serviceNameIs,serviceNameIsNot, serviceNameIsAnyOf,statusCode);
    }
}
