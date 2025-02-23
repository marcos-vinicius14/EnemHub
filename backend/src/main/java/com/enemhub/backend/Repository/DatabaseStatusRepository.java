package com.enemhub.backend.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class DatabaseStatusRepository implements DatabaseStatusInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getActivedConnections() {
        Query query = entityManager.createNativeQuery(
                "SELECT count(*) FROM pg_stat_activity WHERE datname = current_database() AND state = 'active';");

        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public int getMaxConnection() {
        Query query = entityManager.createNativeQuery("SHOW max_connections");
        Object result = query.getSingleResult();
        return Integer.parseInt((String) result);
    }

    @Override
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

    @Override
    public Float getDatabaseVersion() {
        Query query = entityManager.createNativeQuery("SHOW server_version;");
        Object result = query.getSingleResult();

        if (!(result instanceof String)) {
            throw new RuntimeException("Resultado inesperado ao consultar a versão do banco de dados: " + result);
        }

        String versionString = (String) result;
        String[] parts = versionString.split("\\s|\\.");
        String version = parts[0] + "." + parts[1];

        return Float.parseFloat(version);
    }

}
