package x.datatable1.dto;

import x.datatable1.models.Employee;
import lombok.Data;
import java.util.List;

@Data
public class EmployeeDTO {
    private String operation;
    private Employee employee;
    private List<Employee> employees;
    private Long employeeId;
    private List<Long> employeeIds;
}