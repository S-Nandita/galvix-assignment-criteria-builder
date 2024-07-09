package com.galvix.assignment.service;

import com.galvix.assignment.dto.LogResponse;
import com.galvix.assignment.operator.EndDateOperator;
import com.galvix.assignment.operator.ServiceNameOperator;
import com.galvix.assignment.operator.StartDateOperator;

import java.time.LocalDate;

public interface LogService {
    LogResponse getLogsWithFilter(ServiceNameOperator serviceNameOperator, String serviceNames,
                                  Integer statusCode, StartDateOperator startDateOperator, LocalDate startDate,
                                  EndDateOperator endDateOperator, LocalDate endDate, Integer pageNumber, Integer pageSize);
}
