package com.enemhub.backend.Service;


import com.enemhub.backend.Repository.DatabaseStatusRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for retrieving the current status of the database.
 * This class interacts with {@link DatabaseStatusRepository} to gather information
 * such as the number of active connections, the maximum allowed connections,
 * and detailed database status metrics.
 *
 * Responsibilities of this service include:
 * - Fetching the number of active connections to the database.
 * - Retrieving the maximum allowed connections to the database.
 * - Obtaining additional status details about the database.
 *
 * The collected status information is aggregated and returned as a map.
 */
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

        return status;
    }

}
