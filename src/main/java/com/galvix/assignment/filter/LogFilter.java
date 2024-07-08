package com.galvix.assignment.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogFilter {
    String serviceNameIs;
    String serviceNameIsNot;
    List<String> serviceNameIsAnyOf;
}
