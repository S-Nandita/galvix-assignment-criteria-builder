package com.galvix.assignment.service;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.operator.EndDateOperator;
import com.galvix.assignment.operator.ServiceNameOperator;
import com.galvix.assignment.operator.StartDateOperator;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface LogService {
    Page<Log> getLogsWithFilter(ServiceNameOperator serviceNameOperator, String serviceNames,
                                Integer statusCode, StartDateOperator startDateOperator, LocalDate startDate,
                                EndDateOperator endDateOperator, LocalDate endDate, Integer pageNumber, Integer pageSize);
}
