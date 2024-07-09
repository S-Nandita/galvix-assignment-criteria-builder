package com.galvix.assignment.controller;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.galvix.assignment.operator.StartDateOperator;
import com.galvix.assignment.operator.EndDateOperator;
import com.galvix.assignment.operator.ServiceNameOperator;

import java.time.LocalDate;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogRestController {
    private LogService logService;

    @GetMapping
    public Page<Log> getLogs(
            @RequestParam(value = "serviceName",required = false) ServiceNameOperator serviceNameOperator,
            @RequestParam(required = false) String serviceNames,
            @RequestParam(required = false) Integer statusCode,
            @RequestParam(value = "startDateOperator", required = false) StartDateOperator startDateOperator,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(value = "endDateOperator",required = false) EndDateOperator endDateOperator,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize
            ) {

        return logService.getLogsWithFilter(serviceNameOperator,serviceNames,statusCode,
                startDateOperator,startDate,endDateOperator,endDate,pageNumber,pageSize);
    }
}
