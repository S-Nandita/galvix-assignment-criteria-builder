package com.galvix.assignment.repository;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.filter.LogFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class LogFilterRepository {
    private EntityManager entityManager;

    public Page<Log> filterLogs(LogFilter logFilter, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> criteriaQuery = criteriaBuilder.createQuery(Log.class);
        Root<Log> root = criteriaQuery.from(Log.class);
        List<Predicate> predicates = new ArrayList<>();

        if (logFilter.getServiceNameIs() != null || logFilter.getServiceNameIsNot() != null || logFilter.getServiceNameIsAnyOf() != null) {
            predicates.add(getServiceNamePredicate(criteriaBuilder, root, logFilter));
        }
        if (logFilter.getStatusCode() != null) {
            predicates.add(getStatusCodePredicate(criteriaBuilder, root, logFilter));
        }
        if (hasStartDateFilter(logFilter)) {
            predicates.add(getStartDatePredicate(criteriaBuilder, root, logFilter));
        }
        if (hasEndDateFilter(logFilter)) {
            predicates.add(getEndDatePredicate(criteriaBuilder, root, logFilter));
        }
        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);
        TypedQuery<Log> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageNumber * pageSize);
        typedQuery.setMaxResults(pageSize);
        return new PageImpl<>(typedQuery.getResultList());
    }

    private Predicate getServiceNamePredicate(CriteriaBuilder criteriaBuilder, Root root, LogFilter logFilter) {
        List<Predicate> predicates = new ArrayList<>();

        if (logFilter.getServiceNameIs() != null) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("serviceName")),
                    logFilter.getServiceNameIs().toLowerCase()
            ));
        }

        if (logFilter.getServiceNameIsNot() != null) {
            predicates.add(criteriaBuilder.notEqual(
                    criteriaBuilder.lower(root.get("serviceName")),
                    logFilter.getServiceNameIsNot().toLowerCase()
            ));
        }

        if (logFilter.getServiceNameIsAnyOf() != null && !logFilter.getServiceNameIsAnyOf().isEmpty()) {
            List<String> lowerCaseServiceNames = logFilter.getServiceNameIsAnyOf().stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            List<Predicate> inPredicates = lowerCaseServiceNames.stream()
                    .map(serviceName -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("serviceName")), serviceName))
                    .collect(Collectors.toList());
            Predicate[] inPredicateArray = new Predicate[inPredicates.size()];
            predicates.add(criteriaBuilder.or(inPredicates.toArray(inPredicateArray)));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getStatusCodePredicate(CriteriaBuilder criteriaBuilder, Root root, LogFilter logFilter) {
        return criteriaBuilder.equal(root.get("statusCode"), logFilter.getStatusCode());
    }

    private Predicate getStartDatePredicate(CriteriaBuilder criteriaBuilder, Root root, LogFilter logFilter) {
        if (logFilter.getStartDateIs() != null) {
            return criteriaBuilder.equal(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getStartDateIs()
            );
        }
        if (logFilter.getStartDateIsNot() != null) {
            return criteriaBuilder.notEqual(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getStartDateIsNot()
            );
        }
        if (logFilter.getStartDateIsAfter() != null) {
            return criteriaBuilder.greaterThan(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getStartDateIsAfter()
            );
        }
        if (logFilter.getStartDateOnOrAfter() != null) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getStartDateOnOrAfter()
            );
        }
        if (logFilter.getStartDateIsBefore() != null) {
            return criteriaBuilder.lessThan(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getStartDateIsBefore()
            );
        }
        if (logFilter.getStartDateOnOrBefore() != null) {
            return criteriaBuilder.lessThanOrEqualTo(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getStartDateOnOrBefore()
            );
        }
        return null;
    }

    private Predicate getEndDatePredicate(CriteriaBuilder criteriaBuilder, Root root, LogFilter logFilter) {
        if (logFilter.getEndDateIs() != null) {
            return criteriaBuilder.equal(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getEndDateIs()
            );
        }
        if (logFilter.getEndDateIsNot() != null) {
            return criteriaBuilder.notEqual(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getEndDateIsNot()
            );
        }
        if (logFilter.getEndDateIsAfter() != null) {
            return criteriaBuilder.greaterThan(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getEndDateIsAfter()
            );
        }
        if (logFilter.getEndDateOnOrAfter() != null) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getEndDateOnOrAfter()
            );
        }
        if (logFilter.getEndDateIsBefore() != null) {
            return criteriaBuilder.lessThan(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getEndDateIsBefore()
            );
        }
        if (logFilter.getEndDateOnOrBefore() != null) {
            return criteriaBuilder.lessThanOrEqualTo(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    logFilter.getEndDateOnOrBefore()
            );
        }
        return null;
    }

    private boolean hasStartDateFilter(LogFilter logFilter) {
        return logFilter.getStartDateIs() != null ||
                logFilter.getStartDateIsNot() != null ||
                logFilter.getStartDateIsAfter() != null ||
                logFilter.getStartDateOnOrAfter() != null ||
                logFilter.getStartDateIsBefore() != null ||
                logFilter.getStartDateOnOrBefore() != null;
    }

    private boolean hasEndDateFilter(LogFilter logFilter) {
        return logFilter.getEndDateIs() != null ||
                logFilter.getEndDateIsNot() != null ||
                logFilter.getEndDateIsAfter() != null ||
                logFilter.getEndDateOnOrAfter() != null ||
                logFilter.getEndDateIsBefore() != null ||
                logFilter.getEndDateOnOrBefore() != null;
    }

}
