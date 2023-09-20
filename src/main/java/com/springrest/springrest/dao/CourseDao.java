package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.springrest.entities.Course;

/*It required the functionalities that can help to delete, update etc in DB so we need to write manually functions into it.
 * but if you are using spring jpa to we will extend an interface and usse fir ye ban jayegi same type ki then any child object of 
 * this interface will already have the functions we want because spring data jpa itself creates a child implementation class
 * object of it!!
*/
public interface CourseDao extends JpaRepository<Course,Long>{
    //Tell it the type of ENtity you are working with and the primary key of it
}
