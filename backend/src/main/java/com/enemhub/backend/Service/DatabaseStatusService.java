package com.enemhub.backend.Service;


import com.enemhub.backend.Repository.DatabaseStatusRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DatabaseStatusService {
    private final DatabaseStatusRepository databaseStatusRepository;

    public DatabaseStatusService(DatabaseStatusRepository databaseStatusRepository) {
        this.databaseStatusRepository = databaseStatusRepository;
    }

    public Map<String, Object> getDatabaseStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("activedConnections", databaseStatusRepository.getActivedConnections());
        status.put("maxConnections", databaseStatusRepository.getMaxConnections());
        status.put("databaseStatus", databaseStatusRepository.getDatabaseStatus());
        status.put("database version", databaseStatusRepository.getDatabaseVersion());

        return status;
    }

}
