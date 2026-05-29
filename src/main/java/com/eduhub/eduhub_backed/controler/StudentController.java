package com.eduhub.eduhub_backed.controler;

import com.eduhub.eduhub_backed.components.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"jeeva","sanjay ");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents(){
        ArrayList<Student> stuList=new ArrayList<>();
        stuList.add(new Student(1,"jeeva","sanjay "));
        stuList.add(new Student(2,"akarson","S "));
        stuList.add(new Student(3,"sai","nithin "));

        return new ResponseEntity<>(stuList, HttpStatus.OK);

    }
    @GetMapping("{id}/{first}/{last}")
    public ResponseEntity<Student> getStudentDetails(@PathVariable("id") int id,
                                                     @PathVariable("first") String f,
                                                     @PathVariable("last") String l){
        Student student = new Student(id,f,l);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("query")
    public ResponseEntity<Student> getStudentDetailsByQuery(@RequestParam int id,
                                                     @RequestParam String f,
                                                     @RequestParam String l){
        Student student = new Student(id,f,l);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("create")
    public  ResponseEntity<String>  addStudent( @RequestBody Student student){
            System.out.println(student.getFirstName());
            return new ResponseEntity<>(student.getFirstName(),HttpStatus.OK);
    }




}
