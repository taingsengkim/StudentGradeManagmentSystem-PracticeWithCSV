package view;

import controller.StudentController;
import controller.StudentControllerImpl;
import model.Grade;
import model.Student;
import util.InputUtil;
import util.ViewUtil;

import java.util.Map;
import java.util.Scanner;

public class StudentView {
    private Scanner scanner = new Scanner(System.in);

    private StudentController studentControllerImpl = new StudentControllerImpl();
    public void menu(){
        do{
            ViewUtil.mainMenu();
            String opt = InputUtil.getText("Enter Option : ");
            switch (opt){
                case "1":{
                   try {
                       ViewUtil.printHeader("Add New Student");
                       String id = InputUtil.getText("Enter Student Id ");
                       String name = InputUtil.getText("Enter Student Name : ");
                       studentControllerImpl.addStudent(id,name);
                   }catch (RuntimeException e){
                       ViewUtil.printHeader(e.getMessage());
                   }

                    break;
                }
                case "2":{
                    ViewUtil.printHeader("All Student");
                    Map<String, Student> students = studentControllerImpl.findAll();
                    ViewUtil.printAllStudentWithNoGrade(students);
                    break;
                }
                case "3":{
                    try {
                        ViewUtil.printHeader("Add Grade For Student");
                        String id = InputUtil.getText("Enter Student Id : ");
                        Student student =studentControllerImpl.findStudentById(id);
                        if(student != null){
                            String subject = InputUtil.getText("Enter Subject : ");
                          try {

                              if(student.getGrades().isEmpty()){
                                  Double score = InputUtil.getDouble("Enter Score : ");
                                  studentControllerImpl.addGrade(id,subject,score);
                                  ViewUtil.printHeader("Score Added Successfully!");
                                  break;
                              }else {
                                  ViewUtil.printHeader("You already assign score to this Subject. Do you want to Update ? [Y/N]  ");
                                  for (Grade s : student.getGrades().values()) {
                                      ViewUtil.printHeader("Your Current Score : " + s.getScore());
                                  }
                                  String optU = InputUtil.getText("Enter Option : ");
                                  if(optU.equals("y")){
                                      Double score = InputUtil.getDouble("Enter Score : ");
                                      studentControllerImpl.addGrade(id,subject,score);
                                      ViewUtil.printHeader("Score Updated Successfully!");
                                  }else {
                                      return;
                                  }
                              }
                          }catch (RuntimeException e){
                              ViewUtil.printHeader(e.getMessage());
                          }
                        }else {
                            ViewUtil.printHeader("Student Not Exist!");
                        }
                    }catch (RuntimeException e){
                        ViewUtil.printHeader(e.getMessage());
                    }
                    break;
                }
                case "4":{
                    try {
                        ViewUtil.printHeader("View Student Report");
                        String id = InputUtil.getText("Enter Student Id : ");
                        Student student = studentControllerImpl.findStudentById(id);
                        if(student!= null){
                            ViewUtil.printStudentDetail(student);
                        }else {
                            ViewUtil.printHeader("Student Not Exist!");
                        }
                    }catch (RuntimeException e){
                        ViewUtil.printHeader(e.getMessage());
                    }
                    break;
                }
                case "5":{
                    ViewUtil.printHeader("Top Performer In Subject ");
                    String subject = InputUtil.getText("Enter Subjcet :  ");
                    Student student = studentControllerImpl.findTopPerformer(subject);
                    if(student!=null){
                        ViewUtil.printStudentDetail(student);
                    }else {
                        ViewUtil.printHeader("Subject Not Exist!");
                    }
                    break;
                }
                case "6":{
                    ViewUtil.printHeader("Display Class Statistics");
                    studentControllerImpl.showStudentStatistics();
                    ViewUtil.printStudentListDetail(studentControllerImpl.findAll());
                    break;
                }
                case "7":{
                    ViewUtil.printHeader("Student With Below Avg Score ( Below 50 )");
                    Map<String,Student> studentMap = studentControllerImpl.listBelowAverage(50);
                    ViewUtil.printStudentListDetail(studentMap);
                    break;
                }
                case "0" : return;
                default:{
                    ViewUtil.printHeader("Invalid Options!");
                }
            }
        }while (true);
    }
}
