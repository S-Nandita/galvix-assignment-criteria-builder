package com.galvix.assignment.service;

import com.galvix.assignment.dao.LogFilterRepository;
import com.galvix.assignment.entity.Log;
import com.galvix.assignment.filter.LogFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private LogFilterRepository logFilterRepository;

    @Override
    public Page<Log> getLogsWithFilter(LogFilter logFilter, Integer pageNumber, Integer pageSize) {
        return logFilterRepository.filterLogs(logFilter,pageNumber,pageSize);
    }

}
