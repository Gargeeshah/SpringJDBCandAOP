package com.spring.test;


import com.spring.config.Config;
import com.spring.service.StudentService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class StudentTest {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        StudentService studentService = applicationContext.getBean(StudentService.class);

       studentService.setStudentData();

    }
}
