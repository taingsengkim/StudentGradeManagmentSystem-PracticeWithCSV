package controller;

import model.Grade;
import model.Student;
import repository.StudentRepository;

import java.util.HashMap;
import java.util.Map;

public interface StudentController {

    void addStudent(String id, String name);
    void addGrade(String id, String subject, Double score);
    Map<String,Student> findAll();
    Student findStudentById(String id);
    Student findTopPerformer(String subject);
    void showStudentStatistics();
    public Map<String,Student> listBelowAverage(double pass);
}
