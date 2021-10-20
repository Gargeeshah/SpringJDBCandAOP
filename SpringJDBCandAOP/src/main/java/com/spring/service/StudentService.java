package com.spring.service;

import com.spring.dao.IStudentDao;
import com.spring.entity.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class StudentService {

    @Autowired
    private IStudentDao iStudentDao;


    public void setStudentData(){

        List<Student> studentList = new ArrayList<>();

        Student student = new Student();
        student.setRollNo(1);
        student.setStudentName("Neha");
        student.setStudentAddress("Aanand");

        Student student1 = new Student();
        student1.setRollNo(2);
        student1.setStudentName("Riya");
        student1.setStudentAddress("Suart");

        Student student2 = new Student();
        student2.setRollNo(3);
        student2.setStudentName("Rahul");
        student2.setStudentAddress("Aanand");

        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);


      //  iStudentDao.saveData(studentList);

        //iStudentDao.deleteDataByRollno(2);

      //  iStudentDao.deleteRecordByStudentNameOrAddress("Riya","USA");

     //   iStudentDao.cleanUp();
        List<Student> listOfStudents = iStudentDao.findAllStudentsUsingRowMapper();
        getStudents(listOfStudents);
        List<Student> listOfStudents1 = iStudentDao.findAllStudentsUsingResultSetExtractor();
        getStudents(listOfStudents1);

    }

    public static void getStudents(List<Student> students){
        for(Student s : students){
            System.out.println(s);
        }
    }
}
