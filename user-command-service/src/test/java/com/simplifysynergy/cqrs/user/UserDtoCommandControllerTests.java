package com.simplifysynergy.cqrs.user;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
   properties = {"spring.cloud.discovery.enabled=false"})
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDtoCommandControllerTests {

    static String id;

    @Container
    static MongoDBContainer mongodb = new MongoDBContainer("mongo:4.4");

    @DynamicPropertySource
    static void registerMongoProperties(DynamicPropertyRegistry registry) {
        String uri = mongodb.getConnectionString() + "/test";
        registry.add("spring.data.mongodb.uri", () -> uri);
    }

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void add() {
        User user = new User("Test1", "Test2", 10);
        user = restTemplate.postForObject("/", user, User.class);
        assertNotNull(user);
        assertNotNull(user.getId());
        id = user.getId();
    }

    @Test
    @Order(2)
    void findById() {
        User user = restTemplate.getForObject("/{id}", User.class, id);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(id, user.getId());
    }

    @Test
    @Order(2)
    void findAll() {
        User[] users = restTemplate.getForObject("/", User[].class);
        assertTrue(users.length > 0);
    }
}
