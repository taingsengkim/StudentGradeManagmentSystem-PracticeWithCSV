package repository;

import model.Grade;
import model.Student;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StudentRepository {
    private final String filePath = "students.csv";

    public Map<String, Student> students = new HashMap<>();
    public StudentRepository(){
        loadFromCSV();
    }
    public void save(Student student){
        this.students.put(student.getId(),student);
        saveToCSV();
    }
    public Student findById(String id){
        return students.get(id);
    }
    public Map<String,Student> findAll(){
        return students;
    }
    public boolean exists(String id) {
        return students.containsKey(id);
    }

    private void loadFromCSV(){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line=br.readLine()) !=null){
                String[] parts = line.split(",");
                Student student = new Student(parts[0],parts[1]);
                for(int i = 2; i<parts.length ;i+=2){
                    String subject = parts[i];
                    double score = Double.parseDouble(parts[i+1]);
                    student.addOrUpdateGrade(subject,score);
                }
                students.put(student.getId(), student);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private void saveToCSV(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(Student s : students.values()){
                StringBuilder sb = new StringBuilder();
                sb.append(s.getId()).append(",").append(s.getFullName());
                for (Grade g : s.getGrades().values()){
                    sb.append(",").append(g.getSubject()).append(",").append(g.getScore());
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
