package com.enemhub.backend.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class DatabaseStatusRepositoryTest {
    @Autowired
    private DatabaseStatusInterface databaseStatusInterface;

    @Test
    @DisplayName("Should return only one active connection")
    void getActiveConnections() {
        // When
        int activeConnections = databaseStatusInterface.getActivedConnections();
        assertEquals(1, activeConnections, "Expected exactly one active connection");
        assertNotNull(activeConnections, "activeConnection não deve ser nulo");
    }

    @Test
    void getMaxConnections() {
        //TODO: Esse teste esta focado na versão local do DB que aceita no máximo 100 conexões
        int maxConnection = databaseStatusInterface.getMaxConnection();
        assertEquals(100, maxConnection, "Expected exactly 100 max connection");
        assertNotNull(maxConnection, "maxConnection não deve ser nulo");
    }

    @Test
    void getDatabaseStatus_sucessCase() {
        Map<String, Object> databaseStatus = databaseStatusInterface.getDatabaseStatus();

        assertNotNull(databaseStatus, "databaseStatus não deve ser nulo");
        assertEquals("enemhub_test", databaseStatus.get("datname"), "o nome do banco deve ser 'enemhub_test'");
        assertEquals("active", databaseStatus.get("state: "), "O estado deve ser active");
        assertNull(databaseStatus.get("error: "), "Não deve haver erro em caso de sucesso");

    }

    //TODO: Futuramente, criar teste no cenário de falha do getDabaseStatus

    @Test
    void getDatabaseVersion() {
        Float databaseVersion = databaseStatusInterface.getDatabaseVersion();
        assertEquals(17.3F, databaseVersion);
        assertTrue(databaseVersion instanceof Float, "databaseVersion deve ser um FLOAT");
        assertNotNull(databaseVersion, "getDatabaseVersion não deve ser nulo");
    }
}