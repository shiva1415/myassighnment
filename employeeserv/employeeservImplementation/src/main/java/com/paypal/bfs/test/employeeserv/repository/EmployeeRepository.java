package com.paypal.bfs.test.employeeserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

 
@Repository
public interface EmployeeRepository
        extends CrudRepository<EmployeeEntity, Integer> {
 
}
