package com.galvix.assignment.service;

import com.galvix.assignment.dto.LogResponse;
import com.galvix.assignment.pagination.Pagination;
import com.galvix.assignment.repository.LogFilterRepository;
import com.galvix.assignment.entity.Log;
import com.galvix.assignment.filter.LogFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private LogFilterRepository logFilterRepository;

    @Override
    public LogResponse getLogsWithFilter(Map<String,String> queryParams) {
        List<LogFilter> filters = new ArrayList<>();
        AtomicInteger pageNumber = new AtomicInteger(0);
        AtomicInteger pageSize = new AtomicInteger(10);
        queryParams.forEach((key,value)-> {
            String operator = getOperatorFomKey(key);

            if(key.toLowerCase().startsWith("servicenames")) {
                filters.add(new LogFilter("serviceNames",operator,value));
            } else if(key.toLowerCase().equals("statuscode")) {
                filters.add(new LogFilter("statusCode",operator,value));
            } else if (key.toLowerCase().startsWith("startdate")) {
                filters.add(new LogFilter("startDate",operator,value));
            } else if (key.toLowerCase().startsWith("enddate")) {
                filters.add(new LogFilter("endDate",operator,value));
            } else if (key.toLowerCase().startsWith("pagenumber")) {
                pageNumber.set(Integer.parseInt(value));
            } else if (key.toLowerCase().startsWith("pagesize")) {
                pageSize.set(Integer.parseInt(value));
            }
        });
        List<Log> logs =  logFilterRepository.filterLogs(filters,pageNumber.intValue(),pageSize.intValue()).getContent();
        return getLogResponse(logs,pageNumber.intValue(),pageSize.intValue());
    }

    private String getOperatorFomKey(String key) {
        if(key.endsWith("[is]")) {
            return "IS";
        } else if(key.endsWith("[isNot]")) {
            return "IS_NOT";
        } else if (key.endsWith("[isAnyOf]")) {
            return "IS_ANY_OF";
        } else if (key.endsWith("[isAfter]")) {
            return "IS_AFTER";
        } else if (key.endsWith("[onOrAfter]")) {
            return "ON_OR_AFTER";
        } else if (key.endsWith("[isBefore]")) {
            return "IS_BEFORE";
        } else if (key.endsWith("[onOrBefore]")) {
            return "ON_OR_BEFORE";
        }
        return null;
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
