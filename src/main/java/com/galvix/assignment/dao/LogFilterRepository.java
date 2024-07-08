package com.galvix.assignment.dao;

import com.galvix.assignment.entity.Log;
import com.galvix.assignment.filter.LogFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class LogFilterRepository {
    private EntityManager entityManager;

    public List<Log> filterLogs(LogFilter logFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> criteriaQuery = criteriaBuilder.createQuery(Log.class);
        Root<Log> root = criteriaQuery.from(Log.class);
        List<Predicate> predicates = new ArrayList<>();
        if(logFilter.getServiceNameIs() != null) {
            Predicate equalPredicate = criteriaBuilder.equal(root.get("serviceName"),logFilter.getServiceNameIs());
            predicates.add(equalPredicate);
        }

        if(logFilter.getServiceNameIsNot() != null) {
            Predicate notEqualPredicate = criteriaBuilder.notEqual(root.get("serviceName"),logFilter.getServiceNameIsNot());
            predicates.add(notEqualPredicate);
        }

        if(logFilter.getServiceNameIsAnyOf() != null && !logFilter.getServiceNameIsAnyOf().isEmpty()) {
            Predicate inPredicate = root.get("serviceName").in(logFilter.getServiceNameIsAnyOf());
            predicates.add(inPredicate);
        }
        if(logFilter.getStatusCode() != 0) {
            Predicate statusCodePredicate = criteriaBuilder.equal(root.get("statusCode"),logFilter.getStatusCode());
            predicates.add(statusCodePredicate);
        }
        Predicate finalPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);
        TypedQuery<Log> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
