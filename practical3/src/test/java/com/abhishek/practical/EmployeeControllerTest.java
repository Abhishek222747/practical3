package com.abhishek.practical;

import com.abhishek.practical.model.Employee;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
@Property(name = "micronaut.security.enabled", value = "true")
@Property(name = "micronaut.security.authentication", value = "basic")
public class EmployeeControllerTest {

    @Inject
    @Client("/employees")
    HttpClient client;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    private Employee newEmployee;

    @BeforeEach
    void setUp() {
        newEmployee = new Employee();
        newEmployee.setName("John Doe");
        newEmployee.setDepartment("Engineering");
        newEmployee.setSalary(75000.00);
    }

    //post
    @Test
    void testCreateEmployee() {
        HttpResponse<Employee> response = client.toBlocking().exchange(
                HttpRequest.POST("/", newEmployee).basicAuth(USERNAME, PASSWORD),
                Employee.class
        );

        assertEquals(201, response.getStatus().getCode());

        assertNotNull(response.body());
        assertEquals("John Doe", response.body().getName());
        assertEquals("Engineering", response.body().getDepartment());
        assertEquals(75000.00, response.body().getSalary());
    }

    @Test
    void testGetEmployees() {

        HttpResponse<Employee> createResponse = client.toBlocking().exchange(
                HttpRequest.POST("/", newEmployee).basicAuth(USERNAME, PASSWORD),
                Employee.class
        );

        assertEquals(201, createResponse.getStatus().getCode());
        assertNotNull(createResponse.body());
        assertEquals("John Doe", createResponse.body().getName());
        assertEquals("Engineering", createResponse.body().getDepartment());
        assertEquals(75000.00, createResponse.body().getSalary());

        HttpRequest<?> request = HttpRequest.GET("/").basicAuth(USERNAME, PASSWORD);
        HttpResponse<String> response = client.toBlocking().exchange(request, String.class);

        assertEquals(200, response.getStatus().getCode());

        assertNotNull(response.body());
        assertTrue(response.body().contains("John Doe"));
        assertTrue(response.body().contains("Engineering"));
        assertTrue(response.body().contains("75000.0")); // Salary representation in the response
    }

    @Test
    public void testDeleteEmployee() {

        HttpResponse<Employee> createResponse = client.toBlocking().exchange(
                HttpRequest.POST("/", newEmployee).basicAuth(USERNAME, PASSWORD),
                Employee.class
        );

        assertEquals(201, createResponse.getStatus().getCode());
        Long employeeId = createResponse.body().getId();
        assertNotNull(employeeId);

        HttpResponse<?> deleteResponse = client.toBlocking().exchange(
                HttpRequest.DELETE("/" + employeeId).basicAuth(USERNAME, PASSWORD)
        );

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatus());

        HttpResponse<String> getResponse = client.toBlocking().exchange(
                HttpRequest.GET("/").basicAuth(USERNAME, PASSWORD),
                String.class
        );

        assertEquals(HttpStatus.OK, getResponse.getStatus());
        assertNotNull(getResponse.body());
        assertFalse(getResponse.body().contains("John Doe"));
    }

}