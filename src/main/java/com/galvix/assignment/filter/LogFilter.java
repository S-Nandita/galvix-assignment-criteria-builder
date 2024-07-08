package com.galvix.assignment.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogFilter {
    String serviceNameIs;
    String serviceNameIsNot;
    List<String> serviceNameIsAnyOf;

    Integer statusCode ;

    LocalDate startDateIs;
    LocalDate startDateIsNot;
    LocalDate startDateIsAfter;
    LocalDate startDateOnOrAfter;
    LocalDate startDateIsBefore;
    LocalDate startDateOnOrBefore;

    LocalDate endDateIs;
    LocalDate endDateIsNot;
    LocalDate endDateIsAfter;
    LocalDate endDateOnOrAfter;
    LocalDate endDateIsBefore;
    LocalDate endDateOnOrBefore;

}
