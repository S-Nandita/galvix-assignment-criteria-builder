package com.galvix.assignment.dto;

import com.galvix.assignment.Pagination;
import com.galvix.assignment.entity.Log;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogResponse {
    private List<Log> logs;
    private Pagination pagination;
}
