package com.sda.kadry3.controller;

import com.sda.kadry3.model.Employee;
import com.sda.kadry3.model.EmployeeDTO;
import com.sda.kadry3.repository.EmployeeRepository;
import com.sda.kadry3.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {

    EmployeeService service;
    EmployeeRepository repository;

    public EmployeeController(EmployeeService service, EmployeeRepository repository) {
        this.service = service;
        this.repository = repository;
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
    public String addEmployee(@ModelAttribute("form")
                                  @Valid EmployeeDTO form, BindingResult results) {
        if (results.hasErrors()) {
            return "/employee-add";
        } else {
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
    }



//@ResponseBody  - gdy dodamy tę adnotację to zamiast formularza employee-added.html
// wyświetli się po prostu napis employee-added czyli to samo co zrobiłby RestController
    @GetMapping("/employee-added")
    public String employeeAdded() {
        return "employee-added";
    }

    @ResponseBody
    @GetMapping("/employees")
    public List<Employee> getEmployees(@RequestParam(defaultValue = "brak") String imie,
                                       @RequestParam(defaultValue = "brak") String nazwisko) {

       if (imie.equals("brak") && nazwisko.equals("brak"))
               return service.findAll();
       else {
           if (!imie.equals("brak")) {
               return repository.findByFirstName(imie);
           }  else
           return service.findByFirstNameAndLastName(imie,nazwisko);
       }
    }

//    @GetMapping(/)
//    public List<Employee> findByFirstNameAndLastName(@PathVariable String imie,
//                                                     @PathVariable String nazwisko) {
//        return service.findByFirstNameAndLastName(imie, nazwisko);
//    }
    //@PostConstruct
    public void addEmployeeOnStartup(){
        Employee employee = new Employee("Piotr", "Kowalczyk", 31);
        service.addEmployee(employee);
    }
}
