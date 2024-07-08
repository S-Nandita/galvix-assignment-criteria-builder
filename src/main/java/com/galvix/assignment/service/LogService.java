package com.galvix.assignment.service;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.filter.LogFilter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface LogService {
    Page<Log> getLogsWithFilter(LogFilter logFilter, Integer pageNumber, Integer pageSize);
}
