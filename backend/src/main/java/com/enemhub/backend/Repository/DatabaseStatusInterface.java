package com.enemhub.backend.Repository;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DatabaseStatusInterface {
    int getActivedConnections();
    int getMaxConnection();
    Map<String, Object> getDatabaseStatus();
    Float getDatabaseVersion();
}
