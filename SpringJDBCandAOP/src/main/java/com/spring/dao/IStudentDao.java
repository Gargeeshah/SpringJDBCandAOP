package com.spring.dao;

import com.spring.entity.Student;

import java.util.List;

public interface IStudentDao {

    void saveData(List<Student> student);
    void deleteDataByRollno(int rollNo);
    void deleteRecordByStudentNameOrAddress(String name, String address);
    void cleanUp();
    List<Student> findAllStudentsUsingRowMapper();
    List<Student> findAllStudentsUsingResultSetExtractor();

}
