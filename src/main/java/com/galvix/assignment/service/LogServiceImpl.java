package com.galvix.assignment.service;

import com.galvix.assignment.dao.LogFilterRepository;
import com.galvix.assignment.dao.LogRepository;
import com.galvix.assignment.entity.Log;
import com.galvix.assignment.filter.LogFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private LogRepository logRepository;
    private LogFilterRepository logFilterRepository;
    @Override
    public void saveLog(Log log) {
        logRepository.save(log);
    }

    @Override
    public List<Log> getLogsWithFilter(String serviceName) {
        LogFilter logFilter = new LogFilter();
        logFilter.setServiceName(serviceName);
        return logFilterRepository.filterLogs(logFilter);
    }

}
