package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.Test;
import com.proj2g17.proj2g17.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private DataSource dataSource;

    private TestService testService;

    @Autowired
    public TestController(TestService testService){
        this.testService=testService;
    }

    @GetMapping("/test")
    public List<Test> getAllTests(){

            Optional<List<Test>> tests = testService.getTests();
            if (tests.isPresent()) {
                return tests.get();
            } else {
                throw new RuntimeException("No tests found");
            }

    }

    @GetMapping("/testconnection")
    public String testconnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Connection successful: " + connection.getCatalog();
        } catch (SQLException e) {
            return "Connection failed: " + e.getMessage();
        }
    }
}
