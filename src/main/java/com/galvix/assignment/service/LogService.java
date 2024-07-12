package com.galvix.assignment.service;

import com.galvix.assignment.dto.LogResponse;
import java.util.Map;

public interface LogService {
    LogResponse getLogsWithFilter(Map<String,String> queryParams);
}
