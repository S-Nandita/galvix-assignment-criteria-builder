package com.galvix.assignment.controller;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.filter.LogFilter;
import com.galvix.assignment.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogRestController {
    private LogService logService;

    @GetMapping
    public Page<Log> getLogs(
            @RequestParam(value = "serviceName [is]", required = false) String serviceNameIs,
            @RequestParam(value = "serviceName[isNot]", required = false) String serviceNameIsNot,
            @RequestParam(value = "serviceName[isAnyOf]", required = false) String serviceNameIsAnyOf,
            @RequestParam(required = false) Integer statusCode,
            @RequestParam(value = "startDate[is]",required = false)LocalDate startDateIs,
            @RequestParam(value = "startDate[isNot]",required = false)LocalDate startDateIsNot,
            @RequestParam(value = "startDate[isAfter]",required = false)LocalDate startDateIsAfter,
            @RequestParam(value = "startDate[onOrAfter]",required = false)LocalDate startDateOnOrAfter,
            @RequestParam(value = "startDate[isBefore]",required = false)LocalDate startDateIsBefore,
            @RequestParam(value = "startDate[onOrBefore]",required = false)LocalDate startDateOnOrBefore,
            @RequestParam(value = "endDate[is]",required = false)LocalDate endDateIs,
            @RequestParam(value = "endDate[isNot]",required = false)LocalDate endDateIsNot,
            @RequestParam(value = "endDate[isAfter]",required = false)LocalDate endDateIsAfter,
            @RequestParam(value = "endDate[onOrAfter]",required = false)LocalDate endDateOnOrAfter,
            @RequestParam(value = "endDate[isBefore]",required = false)LocalDate endDateIsBefore,
            @RequestParam(value = "endDate[onOrBefore]",required = false)LocalDate endDateOnOrBefore,
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize
            ) {
        LogFilter logFilter = getLogFilter(serviceNameIs,serviceNameIsNot,serviceNameIsAnyOf,statusCode,startDateIs,startDateIsNot,startDateIsAfter,startDateOnOrAfter,startDateIsBefore,startDateOnOrBefore,endDateIs,endDateIsNot,endDateIsAfter,endDateOnOrAfter,endDateIsBefore,endDateOnOrBefore);
        return logService.getLogsWithFilter(logFilter,pageNumber,pageSize);
    }
    private LogFilter getLogFilter(String serviceNameIs, String serviceNameIsNot, String serviceNameIsAnyOf, Integer statusCode, LocalDate startDateIs,LocalDate startDateIsNot, LocalDate startDateIsAfter, LocalDate startDateOnOrAfter, LocalDate startDateIsBefore, LocalDate startDateOnOrBefore,
                                   LocalDate endDateIs, LocalDate endDateIsNot, LocalDate endDateIsAfter, LocalDate endDateOnOrAfter, LocalDate endDateIsBefore, LocalDate endDateOnOrBefore) {
        LogFilter logFilter = new LogFilter();

        if(serviceNameIs != null) {
            logFilter.setServiceNameIs(serviceNameIs);
        }
        if(serviceNameIsNot != null) {
            logFilter.setServiceNameIsNot(serviceNameIsNot);
        }
        if(serviceNameIsAnyOf != null) {
            logFilter.setServiceNameIsAnyOf(Arrays.asList(serviceNameIsAnyOf.split(",")));
        }
        if(statusCode != null) {
            logFilter.setStatusCode(statusCode);
        }
        if(startDateIs != null) {
            logFilter.setStartDateIs(startDateIs);
        }
        if(startDateIsNot != null) {
            logFilter.setStartDateIsNot(startDateIsNot);
        }
        if(startDateIsAfter != null) {
            logFilter.setStartDateIsAfter(startDateIsAfter);
        }
        if(startDateOnOrAfter != null) {
            logFilter.setStartDateOnOrAfter(startDateOnOrAfter);
        }
        if(startDateIsBefore != null) {
            logFilter.setStartDateIsBefore(startDateIsBefore);
        }
        if(startDateOnOrBefore != null) {
            logFilter.setStartDateOnOrBefore(startDateOnOrBefore);
        }
        if(endDateIs != null) {
            logFilter.setEndDateIs(endDateIs);
        }
        if(endDateIsNot != null) {
            logFilter.setEndDateIsNot(endDateIsNot);
        }
        if(endDateIsAfter != null) {
            logFilter.setEndDateIsAfter(endDateIsAfter);
        }
        if(endDateOnOrAfter != null) {
            logFilter.setEndDateOnOrAfter(endDateOnOrAfter);
        }
        if(endDateIsBefore != null) {
            logFilter.setEndDateIsBefore(endDateIsBefore);
        }
        if(endDateOnOrBefore != null) {
            logFilter.setEndDateOnOrBefore(endDateOnOrBefore);
        }
        return logFilter;
    }
}
