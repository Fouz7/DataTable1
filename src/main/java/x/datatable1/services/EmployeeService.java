package x.datatable1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import x.datatable1.models.Employee;
import x.datatable1.repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> saveAll(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee update(Employee employee, Long id) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(employee.getName());
                    return employeeRepository.save(existingEmployee);
                }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> updateAll(List<Employee> employeesToUpdate) {
        List<Employee> updatedEmployees = new ArrayList<>();
        for (Employee employee : employeesToUpdate) {
            Employee updatedEmployee = employeeRepository.findById(employee.getId())
                    .map(existingEmployee -> {
                        existingEmployee.setName(employee.getName());
                        return employeeRepository.save(existingEmployee);
                    }).orElseThrow(() -> new RuntimeException("Employee not found with id: " + employee.getId()));
            updatedEmployees.add(updatedEmployee);
        }
        return updatedEmployees;
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public void deleteAllById(List<Long> ids) {
        employeeRepository.deleteAllById(ids);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
