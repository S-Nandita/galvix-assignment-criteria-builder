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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LogFilterRepository {
    private EntityManager entityManager;

    public List<Log> filterLogs(LogFilter logFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> criteriaQuery = criteriaBuilder.createQuery(Log.class);
        Root<Log> root = criteriaQuery.from(Log.class);
        Predicate equalPredicate = null;
        if(logFilter.getServiceName() != null) {
            equalPredicate = criteriaBuilder.equal(root.get("serviceName"),logFilter.getServiceName());
        }
        if(equalPredicate != null) {
            criteriaQuery.where(equalPredicate);
            TypedQuery<Log> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        }
        TypedQuery<Log> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
