package com.spring.dao;

import com.spring.entity.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class StudentDaoImpl implements IStudentDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveData(List<Student> student) {

        ArrayList<Object[]> sqlArgs = new ArrayList<>();
        String sql = "insert into Student values (?,?,?)";
        for (Student student1 : student) {
            Object[] tempStudentData = {student1.getRollNo(), student1.getStudentName(), student1.getStudentAddress()};
            sqlArgs.add(tempStudentData);
        }

        jdbcTemplate.batchUpdate(sql, sqlArgs);

    }

    @Override
    public void deleteDataByRollno(int rollNo) {
        String sql = "delete from Student where rollNo = ?";

        jdbcTemplate.update(sql, rollNo);

    }

    @Override
    public void deleteRecordByStudentNameOrAddress(String name, String address) {

        String sql = "delete from Student where studentName = ? OR studentAddress = ?";

        jdbcTemplate.update(sql, name, address);

    }

    public void cleanUp() {
        jdbcTemplate.execute("truncate table Student");

    }

    @Override
    public List<Student> findAllStudentsUsingRowMapper() {


        String sql = "select * from Student";

        RowMapper<Student> rowMapper = (rs, rowNum) -> {
            Student student = new Student();
            student.setRollNo(rs.getInt("rollNo"));
            student.setStudentName(rs.getString("studentName"));
            student.setStudentAddress(rs.getString("studentAddress"));

            return student;
        };
        List<Student> studentList = jdbcTemplate.query(sql, rowMapper);

        return studentList;
    }

    @Override
    public List<Student> findAllStudentsUsingResultSetExtractor() {

        String sql = "select * from Student";

        ResultSetExtractor<List<Student>> resultSetExtractor = rs -> {

            ArrayList<Student> list = new ArrayList<>();
            while(rs.next()){

                Student student = new Student();
                student.setRollNo(rs.getInt("rollNo"));
                student.setStudentAddress(rs.getString("studentAddress"));
                student.setStudentName(rs.getString("studentName"));

                list.add(student);
            }

            return list;
        };

      List<Student> list = jdbcTemplate.query(sql, resultSetExtractor);
      return  list;
    }

}
