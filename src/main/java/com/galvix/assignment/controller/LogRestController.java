package com.galvix.assignment.controller;

import com.galvix.assignment.dto.LogResponse;
import com.galvix.assignment.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LogResponse> getLogs(
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

        return new ResponseEntity<>(logService.getLogsWithFilter(serviceNameOperator,serviceNames,statusCode,
                startDateOperator,startDate,endDateOperator,endDate,pageNumber,pageSize), HttpStatus.OK);
    }
}
