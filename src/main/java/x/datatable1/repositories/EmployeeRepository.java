package x.datatable1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import x.datatable1.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
