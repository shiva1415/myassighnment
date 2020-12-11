package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Employee employee = new Employee();

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(Integer.valueOf(id));

        if(employeeEntity.isPresent())
        {
            employee.setId(employeeEntity.get().getId());
            employee.setFirstName(employeeEntity.get().getFirstName());
            employee.setLastName(employeeEntity.get().getLastName());
            employee.setDateOfBirth(employeeEntity.get().getDateOfBirth());
            Address address = new Address();
            address.setLine1(employeeEntity.get().getLine1());
            address.setLine2(employeeEntity.get().getLine2());
            address.setCity(employeeEntity.get().getCity());
            address.setState(employeeEntity.get().getState());
            address.setCountry(employeeEntity.get().getCountry());
            address.setZipCode(employeeEntity.get().getZipCode());
            employee.setAddress(address);
            return new ResponseEntity<>(employee, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @Override
    public ResponseEntity<Integer> addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setDateOfBirth(employee.getDateOfBirth());
        //employeeEntity.setDateOfBirth(new Date().toString());

        //set address
        if (employee.getAddress()!= null)
        {
            employeeEntity.setLine1(employee.getAddress().getLine1());
            employeeEntity.setLine2(employee.getAddress().getLine2());
            employeeEntity.setCity(employee.getAddress().getCity());
            employeeEntity.setState(employee.getAddress().getState());
            employeeEntity.setCountry(employee.getAddress().getCountry());
            employeeEntity.setZipCode(employee.getAddress().getZipCode());
        }


        employeeEntity = employeeRepository.save(employeeEntity);
        return new ResponseEntity<>(employeeEntity.getId(), HttpStatus.CREATED);
    }


}
