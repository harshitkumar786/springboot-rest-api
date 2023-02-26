package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students/")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Ramesh",
                "Fadatare"
        );
//        return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header","ramesh")
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();

        students.add(new Student(1,"Ramesh", "Fadatare"));
        students.add(new Student(2, "Umesh", "Fadatare"));
        students.add(new Student(3, "Ram", "Jadhav"));
        students.add(new Student(4, "Sanjay", "Pawar"));

        return ResponseEntity.ok(students);
    }

    // Spring Boot REST API with Path Vairable
    // http://localhost:8080/1/ramesh/fadatare
    // {id} - URI template Variable, @PathVariable used to bind URI template Variable into method argument
    @GetMapping("{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        Student student = new Student(studentId, firstName, lastName);

        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Ramesh&lastName=Fadatare
    // Request Param is used to extract query parameters into request URL
    @GetMapping("query")
    public Student studentWithRequestVariable(@RequestParam("id") int id,
                                              @RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName){
        return new Student(id, firstName, lastName);
    }

    // Spring Boot rest api that handles HTTP Post request
    // POST Mapping and Request Body
    @PostMapping("student/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }

    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int id){
        student.setId(id);
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }

    // Spring boot rest api that handles HTTP DELETE request - deleting the existing resource

    @DeleteMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        return "Student with id: " + studentId + " deleted successfully!!";
    }
}
