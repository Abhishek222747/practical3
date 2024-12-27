package com.abhishek.practical.controller;

import com.abhishek.practical.exception.GlobalExceptionHandler;
import com.abhishek.practical.logging.LogExecution;
import com.abhishek.practical.model.Employee;
import com.abhishek.practical.repository.EmployeeRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;

import java.util.Optional;

@Controller("/employees")
@Secured("isAuthenticated()")
public class EmployeeController {

    @Inject
    private EmployeeRepository employeeRepository;

    @LogExecution
    @Get
    public Iterable<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @LogExecution
    @Post
    public HttpResponse<Employee> createEmployee(@Body Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return HttpResponse.created(savedEmployee); // Return 201 Created
    }

    @LogExecution
    @Delete("/{id}")
    public HttpResponse<?> deleteEmployee(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return HttpResponse.noContent(); // 204 No Content
        } else {
            return HttpResponse.notFound(); // 404 Not Found
        }
    }

}
