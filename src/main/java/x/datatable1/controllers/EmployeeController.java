package x.datatable1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import x.datatable1.dto.EmployeeDTO;
import x.datatable1.models.Employee;
import x.datatable1.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee-crud")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Object employeeCRUD(@RequestBody EmployeeDTO employeeDTO) {
        return switch (employeeDTO.getOperation().toUpperCase()) {
            case "CREATE" -> employeeService.save(employeeDTO.getEmployee());
            case "BULK_CREATE" -> employeeService.saveAll((List<Employee>) employeeDTO.getEmployees());
            case "READ" -> employeeDTO.getEmployeeId() != null ? employeeService.findById(employeeDTO.getEmployeeId()) : employeeService.findAll();
            case "UPDATE" -> employeeService.update(employeeDTO.getEmployee(), employeeDTO.getEmployeeId());
            case "BULK_UPDATE" -> employeeService.updateAll((List<Employee>) employeeDTO.getEmployees());
            case "DELETE" -> {
                employeeService.delete(employeeDTO.getEmployeeId());
                yield "Employee deleted successfully";
            }
            default -> throw new IllegalArgumentException("Invalid operation");
            case "BULK_DELETE" -> {
                employeeService.deleteAllById(employeeDTO.getEmployeeIds());
                yield "Employees deleted successfully";
            }
        };
    }
}