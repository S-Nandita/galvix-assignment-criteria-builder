package com.galvix.assignment.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogFilter {
    String field;
    String operator;
    Object value;

    public LocalDate getLocalDateValue() {
        return LocalDate.parse((String) value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
