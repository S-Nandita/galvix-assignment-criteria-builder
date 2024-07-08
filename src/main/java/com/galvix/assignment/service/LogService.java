package com.galvix.assignment.service;

import com.galvix.assignment.entity.Log;

import java.util.List;

public interface LogService {
    void saveLog(Log log);
    List<Log> getLogsWithFilter(String serviceName);
}
