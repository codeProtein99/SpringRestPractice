package com.springrest.springrest.services;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entities.Course;

@Service
public class CourseServiceImpl implements CourseService{

    /*Making the object of CourseDao so that we can use the methods of the jpaRepo Interface using an implementaion of this 
     * interface which is somehow */
    @Autowired
    private CourseDao courseDao;

    // List<Course> list;

    public CourseServiceImpl(){
        // list = new ArrayList<>();
        // list.add(new Course(123, "Java Course", "Beginner Friendly"));
        // list.add(new Course(124, "Java Advanced", "Professional Friendly"));
    }

    @Override
    public List<Course> getCourses() {
        //Itll return all the courses from database. It'll make a list of objects(Course here) put in list and return it to us. 
        return courseDao.findAll();
        // return list;
    }

    //Method to get a single course:

    public Course getCourse(long courseId){
        
       /*  Course c = null;
        for(Course course : list){
            if(course.getId()==courseId){
                c = course;
                break;
            }
        }

        return c;*/
        Optional<Course> optionalEntity =  courseDao.findById(courseId);
        Course entity = (Course) optionalEntity.get();
        return entity;
    }

    //Method to add a course:

    public Course addCourse(Course course){
        // list.add(course);
        courseDao.save(course);
        return course;
    }
    
    //This method is for updating the course:

    public Course updateCourse(Course course){
        
        //iterate to search for the same course in the list with courseId:
        //for (Course eachCourse : list) {
        //     if(eachCourse.getId() == course.getId()){
        //         eachCourse.setTitle(course.getTitle());
        //         eachCourse.setDescription(course.getDescription());
        //         return eachCourse;
        //     }
        // }
        
        courseDao.save(course); //In the updation too itll update the record using that id and save it on that poistion only
        /* It works like if there is no course itll add a new one else itll update that entry only */
        return course;
    }

    //Method for deleting a list item:

    public void deleteCourse(Long courseId){

        //iterate through the whole list:
        // for (Course i : list) {
        //     if(i.getId() == courseId){
        //         list.remove(i);
        //         return;
        //     }
        // }

       Optional<Course> optionalEntity =  courseDao.findById(courseId);
       Course entity = (Course) optionalEntity.get();
       courseDao.delete(entity);

    }
}
