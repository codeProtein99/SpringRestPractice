package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

//For Rest Api we use this controller and it stands Representational State Transfer.
@RestController  
public class MyController {
    
    //As we need object of CourseServiceImpl but we also want loose coupling so we'll make a variable of type CourseService:
    @Autowired
    private CourseService courseService;

    @GetMapping("/home")
    public String home(){
        return "This is home Page";
    }

    // Get the courses:

    @GetMapping("/courses")
    public List<Course> getCourses(){
        
        //Controller will not not do this fethching from itself, it'll request the service for all the courses:

        //We will be needing the object of CourseServiceImpl right.
        return this.courseService.getCourses();
        //This method of the CourseService Interface will be called. Since theres no implementations of it, the child class of this
        //interface will be called here. Therefore this method of CourseServiceImpl will be called now.
    }

    //Get a course based on Id:

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId){
        
        //We convert the value coming from url to long here
        return this.courseService.getCourse( Long.parseLong(courseId));
    }

    //Adding courses to the data:
    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course){
        return this.courseService.addCourse(course);
    }

    //For updating an already existing Course:

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course){
        return this.courseService.updateCourse(course);
    }

    //This function is for Deleting a particular course:
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId){

        try {
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


/* When we talk about loose coupling we make an interface and its implementation class. So that we dont need to make the obejct of the
implementation class directly rather uske interface se call kre ham log.

In this courseService se we are referring to an object of its implementation which is upcasted to its parent interface type.
 */