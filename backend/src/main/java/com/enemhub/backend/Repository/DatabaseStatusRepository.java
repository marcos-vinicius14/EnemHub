package com.enemhub.backend.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/**
 * Repository for accessing database status information.
 * This class interacts with the database to retrieve details about the number of active connections,
 * the maximum allowed connections, and other status metrics.
 *
 * It utilizes JPA's EntityManager to execute native SQL queries against the underlying database.
 */
@Repository
public class DatabaseStatusRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public int getActivedConnections() {
        Query query = entityManager.createNativeQuery(
                "SELECT count(*) FROM pg_stat_activity WHERE datname = current_database() AND state = 'active';");

        return ((Number) query.getSingleResult()).intValue();
    }

    public int getMaxConnections() {
        Query query = entityManager.createNativeQuery("SHOW max_connections");
        Object result = query.getSingleResult();
        return Integer.parseInt((String) result);
    }

    public Map<String, Object> getDatabaseStatus() {
        Map<String, Object> status = null;
        try {
            Query query = entityManager.createNativeQuery(
                    "SELECT datname, numbackends, xact_commit, xact_rollback " +
                            "FROM pg_stat_database WHERE datname = current_database();"
            );

            Object[] resultArray = (Object[]) query.getSingleResult();

            status = new HashMap<>();
            status.put("datname", resultArray[0]);
            status.put("transactions with commit", resultArray[2]);
            status.put("transactions with rollback", resultArray[3]);
            status.put("state: ", "active");
        } catch (Exception e) {
            status.put("state:", "inactive");
            status.put("error: ", "internal server error");
        }

        return status;

    }

}
