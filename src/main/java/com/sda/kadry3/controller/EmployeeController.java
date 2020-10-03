package com.sda.kadry3.controller;

import com.sda.kadry3.model.Employee;
import com.sda.kadry3.model.EmployeeDTO;
import com.sda.kadry3.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

@Controller
public class EmployeeController {

    EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping("/employee-add")
    // Obiekt Model służy do przechowywania danych i przekazywania ich pomiędzy metodami
    // w kontrolerze
    public String getForm(Model model) {
        EmployeeDTO form = new EmployeeDTO();
        model.addAttribute("form", form);

        return "employee-add";
    }

    @PostMapping("/employee-add")
    public String addEmployee(@ModelAttribute("form") EmployeeDTO form) {
        Employee employee = new Employee();
        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setAge(form.getAge());

        service.addEmployee(employee);
// Gdybyśmy zrobili return na "employee-added" to pozostalibyśmy na endpoincie
// employee-add i każde odświeżenie strony ponownie doda tego samego pracownika
// mysimy więc przekierować się na inny endpoint np. eployee-added i dodać metodę
// do wyświetlania formularze emmployee-added
        return "redirect:/employee-added";
    }

//@ResponseBody  - gdy dodamy tę adnotację to zamiast formularza employee-added.html
// wyświetli się po prostu napis employee-added czyli to samo co zrobiłby RestController
    @GetMapping("/employee-added")
    public String employeeAdded() {
        return "employee-added";
    }

    //@PostConstruct
    public void addEmployeeOnStartup(){
        Employee employee = new Employee("Piotr", "Kowalczyk", 31);
        service.addEmployee(employee);
    }
}
