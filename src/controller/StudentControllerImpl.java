package controller;

import model.Grade;
import model.Student;
import repository.StudentRepository;
import util.ViewUtil;

import java.util.HashMap;
import java.util.Map;

public class StudentControllerImpl implements StudentController {
    private StudentRepository repository = new StudentRepository();

    @Override
    public void addStudent(String id, String name){
        try {
            if(!repository.exists(id)){
                repository.save(new Student(id,name));
            }else {
                throw new RuntimeException("Student With This Id Is Already Exist!");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public void addGrade(String id, String subject, Double score){
        Student student = repository.findById(id);
        if(student!=null){
            student.addOrUpdateGrade(subject,score);
            repository.save(student);
        }else {
            throw new RuntimeException("Student With This Id Isn't Exist!");
        }
    }


    @Override
    public Map<String,Student> findAll(){
        try {
           return repository.findAll();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    };
    @Override
    public Student findStudentById(String id){
        try {
            return repository.findById(id);
        }catch (RuntimeException e){
            throw new RuntimeException("Student With This Id Isn't Exist!");
        }
    }
    @Override
    public Student findTopPerformer(String subject) {
        Student top = null;
        double max = -1;

        for (Student s : repository.findAll().values()) {
            Grade g = s.getGrades().get(subject);
            if (g != null && g.getScore() > max && g.getSubject().equals(subject)) {
                max = g.getScore();
                top = s;
            }
        }
        return top;
    }
    @Override
    public void showAllStudentStatistics(){
            if (repository.findAll().isEmpty()) {
                System.out.println("No students available.");
                return;
            }
            double highest = Double.MIN_VALUE;
            double lowest = Double.MAX_VALUE;
            for (Student s : repository.findAll().values()) {
                double avg = s.getAverage();
                highest = Math.max(highest, avg);
                lowest = Math.min(lowest, avg);
            }
            System.out.println("Total Students: " + repository.findAll().size());
            System.out.println("Highest Average: " + highest);
            System.out.println("Lowest Average: " + lowest);
    }

    @Override
    public void showStudentStatistics(String id) {
        Student student = findStudentById(id);
        if (student ==  null) {
            return;
        }
        ViewUtil.printHeader("Average : " + student.getAverage());
    }

    @Override
    public Map<String,Student> listBelowAverage(double pass) {
        Map<String,Student> studentMap = new HashMap<>();
        for (Student s : repository.findAll().values()) {
            if (s.getAverage() < pass) {
                studentMap.put(s.getId(), s);
            }
        }
        return studentMap;
    }


}
