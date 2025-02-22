package com.enemhub.backend.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.flyway.enabled=false")
class DatabaseStatusRepositoryTest {
    @Autowired
    private DatabaseStatusRepository databaseStatusRepository;

    @Test
    @DisplayName("Should return only 1 connection open")
    void getActivedConnections() {
        //TODO: Deve retornar somente 1 conexão ativa
        int openConnection = databaseStatusRepository.getActivedConnections();

        assertEquals(1, openConnection);

    }

    @Test
    void getMaxConnections() {
        //TODO: Deve retornar no máximo 100 conexões.
    }

    @Test
    void getDatabaseStatus() {
    }

    @Test
    void getDatabaseVersion() {

    }
}