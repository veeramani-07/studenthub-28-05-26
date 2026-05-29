package com.eduhub.eduhub_backed.controler;

import com.eduhub.eduhub_backed.components.Course;
import com.eduhub.eduhub_backed.components.User;
import com.eduhub.eduhub_backed.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    static ArrayList<User> userList=new ArrayList<>();
    static {
        userList.add(new User(1,"jeeva","jsikhi"));
        userList.add(new User(2,"sanjay","hii"));
        userList.add(new User(3,"raj","hello8"));
        userList.add(new User(4,"gopal","65the"));
        userList.add(new User(5,"prabhu","jai10"));


    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public  ResponseEntity<User> getCourseByCode(@PathVariable("id") int id){
        return userList.stream().filter(c->c.getUserId()==id).findFirst().map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException("User","UserId",id));
    }

    @GetMapping("/users/{id}/query")
    public  ResponseEntity<User> getCourseByPathAndQuery(@PathVariable("id") int id, @RequestParam String name){
        return userList.stream().filter(c->c.getUserId()==id && c.getUserName().equalsIgnoreCase(name)).findFirst().map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException("User","UserId & UserName",id));
    }

    @PostMapping("/adduser")
    public ResponseEntity addCourse(@RequestBody
                                    User user){

        userList.add(user);
        return ResponseEntity.accepted().body("Added");
    }


    @PutMapping("users/{id}/{updatedName}")
    public ResponseEntity updateUser(@PathVariable("id") int id,
                                       @PathVariable("updatedName") String name ){

        User user= userList.stream().filter(c->c.getUserId()==id).findFirst()
                .orElseThrow(()-> new ResourceNotFoundException("Course","CourseCode",id));
        user.setUserName(name);
        return  ResponseEntity.ok(user);
    }


    @DeleteMapping("users/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        User user=userList.stream().filter(c->c.getUserId()==id).findFirst()
                .orElseThrow(()-> new RuntimeException("can't delete"));
        userList.remove(user);
        return  ResponseEntity.ok("deleted");

    }



}
