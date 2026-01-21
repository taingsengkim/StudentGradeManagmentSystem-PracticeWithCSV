package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String id;
    private String fullName;
    private Map<String,Grade> grades;

    public Student(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.grades = new HashMap<>();

    }

    public void addOrUpdateGrade(String subject, Double score) {
        grades.put(subject, new Grade(subject,score));
    }

    public Student(String id, String fullName, Map<String, Grade> grades) {
        this.id = id;
        this.fullName = fullName;
        this.grades = grades;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getFullName(){
        return this.fullName;
    }
    public void setGrades(Map<String,Grade> grades){
        this.grades = grades;
    }
    public Map<String, Grade> getGrades(){
        return this.grades;
    }
    public double getAverage() {
        if (grades.isEmpty()) return 0;

        double sum = 0;
        for (Grade g : grades.values()) {
            sum += g.getScore();
        }
        return sum / grades.size();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", grades=" + grades +
                '}';
    }
}
