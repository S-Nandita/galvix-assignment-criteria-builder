package com.galvix.assignment.service;

import com.galvix.assignment.pagination.Pagination;
import com.galvix.assignment.dto.LogResponse;
import com.galvix.assignment.repository.LogFilterRepository;
import com.galvix.assignment.entity.Log;
import com.galvix.assignment.operator.EndDateOperator;
import com.galvix.assignment.operator.ServiceNameOperator;
import com.galvix.assignment.operator.StartDateOperator;
import com.galvix.assignment.filter.LogFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private LogFilterRepository logFilterRepository;

    @Override
    public LogResponse getLogsWithFilter(ServiceNameOperator serviceNameOperator, String serviceNames,
                                       Integer statusCode, StartDateOperator startDateOperator, LocalDate startDate,
                                       EndDateOperator endDateOperator, LocalDate endDate, Integer pageNumber, Integer pageSize) {

        LogFilter logFilter = getLogFilter(serviceNameOperator, serviceNames, statusCode, startDateOperator, startDate, endDateOperator, endDate);
        List<Log> logs = logFilterRepository.filterLogs(logFilter,pageNumber,pageSize).getContent();
        return getLogResponse(logs,pageNumber,pageSize);
    }

    private LogFilter getLogFilter(ServiceNameOperator serviceNameOperator, String serviceNames,
                                   Integer statusCode, StartDateOperator startDateOperator, LocalDate startDate,
                                   EndDateOperator endDateOperator, LocalDate endDate) {

        LogFilter logFilter = new LogFilter();

        if (serviceNameOperator != null && serviceNames != null) {
            switch (serviceNameOperator) {
                case IS:
                    logFilter.setServiceNameIs(serviceNames);
                    break;
                case IS_NOT:
                    logFilter.setServiceNameIsNot(serviceNames);
                    break;
                case IS_ANY_OF:
                    logFilter.setServiceNameIsAnyOf(Arrays.asList(serviceNames.split(",")));
            }
        }
        if (statusCode != null) {
            logFilter.setStatusCode(statusCode);
        }
        if (startDateOperator != null && startDate != null) {
            switch (startDateOperator) {
                case IS:
                    logFilter.setStartDateIs(startDate);
                    break;
                case IS_NOT:
                    logFilter.setStartDateIsNot(startDate);
                    break;
                case IS_AFTER:
                    logFilter.setStartDateIsAfter(startDate);
                    break;
                case ON_OR_AFTER:
                    logFilter.setStartDateOnOrAfter(startDate);
                    break;
                case IS_BEFORE:
                    logFilter.setStartDateIsBefore(startDate);
                    break;
                case ON_OR_BEFORE:
                    logFilter.setStartDateOnOrBefore(startDate);
                    break;
            }
        }
        if (endDateOperator != null && endDate != null) {
            switch (endDateOperator) {
                case IS:
                    logFilter.setStartDateIs(endDate);
                    break;
                case IS_NOT:
                    logFilter.setStartDateIsNot(endDate);
                    break;
                case IS_AFTER:
                    logFilter.setStartDateIsAfter(endDate);
                    break;
                case ON_OR_AFTER:
                    logFilter.setStartDateOnOrAfter(endDate);
                    break;
                case IS_BEFORE:
                    logFilter.setStartDateIsBefore(endDate);
                    break;
                case ON_OR_BEFORE:
                    logFilter.setStartDateOnOrBefore(endDate);
                    break;
            }
        }
        return logFilter;
    }
    public LogResponse getLogResponse(List<Log> logs, int pageNumber,int pageSize) {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(pageNumber);
        pagination.setPageSize(pageSize);
        LogResponse logResponse = new LogResponse();
        logResponse.setLogs(logs);
        logResponse.setPagination(pagination);
        return logResponse;
    }
}
