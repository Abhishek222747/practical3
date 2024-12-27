package com.abhishek.practical.repository;

import com.abhishek.practical.model.Employee;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
