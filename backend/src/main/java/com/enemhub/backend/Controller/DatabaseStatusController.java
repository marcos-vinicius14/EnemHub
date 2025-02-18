package com.enemhub.backend.Controller;


import com.enemhub.backend.Service.DatabaseStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/status")
public class DatabaseStatusController {
   private final DatabaseStatusService databaseStatusService;

    public DatabaseStatusController(DatabaseStatusService databaseStatusService) {
        this.databaseStatusService = databaseStatusService;
    }

    @GetMapping
    public Map<String, Object> getDatabaseStatus() {
        return databaseStatusService.getDatabaseStatus();
    }
}
