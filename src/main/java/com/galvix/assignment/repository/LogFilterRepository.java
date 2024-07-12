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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class LogFilterRepository {
    private EntityManager entityManager;

    public Page<Log> filterLogs(List<LogFilter> filters,int pageNumber, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> criteriaQuery = criteriaBuilder.createQuery(Log.class);
        Root<Log> root = criteriaQuery.from(Log.class);
        List<Predicate> predicates = new ArrayList<>();

        for (LogFilter logFilter : filters) {
            if(logFilter.getField().equals("serviceNames")) {
               predicates.add(getServiceNamePredicate(criteriaBuilder,root,logFilter.getOperator(),(String) logFilter.getValue()));
            }
            if (logFilter.getField().equals("statusCode")) {
                predicates.add(getStatusCodePredicate(criteriaBuilder,root,logFilter.getOperator(),(String) logFilter.getValue()));
            }
            if(logFilter.getField().equals("startDate")) {
                predicates.add(getDatePredicate(criteriaBuilder,root,logFilter.getOperator(),logFilter.getLocalDateValue()));
            }
            if(logFilter.getField().equals("endDate")) {
                predicates.add(getDatePredicate(criteriaBuilder,root,logFilter.getOperator(),logFilter.getLocalDateValue()));
            }
        }
        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);
        TypedQuery<Log> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageNumber * pageSize);
        typedQuery.setMaxResults(pageSize);
        return new PageImpl<>(typedQuery.getResultList());
    }

    private Predicate getServiceNamePredicate(CriteriaBuilder criteriaBuilder,Root root,String operator, String value) {
        List<Predicate> predicates = new ArrayList<>();
        if (operator.equals("IS")) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("serviceName")),value.toLowerCase()));
        }
        if(operator.equals("IS_NOT")) {
            predicates.add(criteriaBuilder.notEqual(criteriaBuilder.lower(root.get("serviceName")),value.toLowerCase()));
        }
        if(operator.equals("IS_ANY_OF")) {
            List<String> values = Arrays.asList(value.split(","));
            List<String> lowerCaseServiceNames = values.stream()
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

    private Predicate getStatusCodePredicate(CriteriaBuilder criteriaBuilder,Root root,String operator,String value) {
        return criteriaBuilder.equal(root.get("statusCode"),value);
    }

    private Predicate getDatePredicate(CriteriaBuilder criteriaBuilder, Root<Log> root, String operator, Object value) {
        if(operator.equals("IS")) {
            return criteriaBuilder.equal(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    value
            );
        }
        if(operator.equals("IS_NOT")) {
            return criteriaBuilder.notEqual(
                    criteriaBuilder.function("DATE",LocalDate.class,root.get("loggedAt")),
                    value
            );
        }
        if(operator.equals("IS_AFTER")) {
            return criteriaBuilder.greaterThan(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    (LocalDate) value
            );
        }
        if(operator.equals("ON_OR_AFTER")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    (LocalDate) value
            );
        }
        if(operator.equals("IS_BEFORE")) {
            return criteriaBuilder.lessThan(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    (LocalDate) value
            );
        }
        if(operator.equals("ON_OR_BEFORE")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    criteriaBuilder.function("DATE", LocalDate.class, root.get("loggedAt")),
                    (LocalDate) value
            );
        }
        return null;
    }
}
