package com.eduhub.eduhub_backed.controler;

import com.eduhub.eduhub_backed.components.Course;
import com.eduhub.eduhub_backed.components.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    List<Course> courseList=new ArrayList<>();

    public  CourseController(){
        courseList.add(new Course(1,"java",4));
        courseList.add(new Course(2,"python",4));
        courseList.add(new Course(3,"cpp",4));
        courseList.add(new Course(4,"adc",4));
        courseList.add(new Course(5,"mpes",4));

    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public  ResponseEntity<Course> getCourseByCode(@PathVariable("id") int id){
        for (int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseCode()==id){
                return new ResponseEntity<>(courseList.get(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(courseList.get(0),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/course/{id}/query")
    public  ResponseEntity<Course> getCourseByPathAndQuery(@PathVariable("id") int id, @RequestParam String name){
        for (int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseCode()==id && courseList.get(i).getCourseName().equals(name)){
                return new ResponseEntity<>(courseList.get(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(courseList.get(0),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addcourse")
    public ResponseEntity addCourse(@RequestBody
                                        Course course){

        courseList.add(course);
        return ResponseEntity.accepted().body("Added");
    }


    @PutMapping("{id}/{updatedName}")
    public ResponseEntity updateCourse(@PathVariable("id") int id,
                                       @PathVariable("updatedName") String name ){

        for (int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseCode()==id){
                courseList.get(i).setCourseName(name);
            }
        }
        return ResponseEntity.accepted().body("updated");
    }

    @DeleteMapping("{id}")
    public  ResponseEntity deleteCourse(@PathVariable("id") int id){
        for (int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseCode()==id){
               courseList.remove(i);
            }
        }
        return ResponseEntity.accepted().body("deleted");
    }

}
