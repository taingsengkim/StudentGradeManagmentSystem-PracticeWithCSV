package util;



import model.Grade;
import model.Student;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Map;

public class ViewUtil {




    public static void mainMenu(){
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);

        table.setColumnWidth(0, 50, 200);
        table.addCell("Menu", cellStyle);
        table.addCell("1 ) Add New Student", cellStyle);
        table.addCell("2 ) Get All Student ( No Grades ) ", cellStyle);
        table.addCell("3 ) Add/Update Grade For Student", cellStyle);
        table.addCell("4 ) View Student Report ( All Grades + Avg ) ", cellStyle);
        table.addCell("5 ) Get Top Performer In Specific Subject", cellStyle);
        table.addCell("6 ) Display Class Statistics ( Highest/Lowest Average , Total Student )", cellStyle);
        table.addCell("7 ) List All Students With Averages Below Passing threshold ", cellStyle);
        table.addCell("0 ) Exit", cellStyle);
        print(table.render(), true);    }

    public static void print(String text, boolean isNewLine) {
        if (isNewLine)
            System.out.println(text);
        else
            System.out.print(text);
    }

    public static void printHeader(String text) {
        Table table = new Table(1,
                BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell(text);
        print(table.render(), true);
    }

    public static void printAllSubject(Map<String,Grade> gradeMap){
        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell("Name");
        for (Grade s : gradeMap.values()){
            table.addCell(s.getSubject());
        }
        print(table.render(), true);
    }
    public static void printAllStudentWithNoGrade(Map<String,Student> studentMap){
        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell("ID");
        table.addCell("Name");
        for (Student s : studentMap.values()){
            table.addCell(s.getId());
            table.addCell(s.getFullName());
        }
        print(table.render(), true);
    }

    public static void printStudentListBelowAvg(Map<String,Student> s) {
        Table table = new Table(3, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Average");

        for (Student student : s.values()){
            table.addCell(student.getId());
            table.addCell(student.getFullName());
            table.addCell(String.valueOf(student.getAverage()) );
        }
        print(table.render(), true);
    }




    public static void printStudentListDetail(Map<String,Student> s) {
        Table table = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Subject");
        table.addCell("Score");

        for (Student student : s.values()){
            if(student.getGrades().isEmpty()){
                table.addCell(student.getId());
                table.addCell(student.getFullName());
                table.addCell("-");
                table.addCell("No Grade");
            }else {
                for (Grade g : student.getGrades().values()) {
                    table.addCell(student.getId());
                    table.addCell(student.getFullName());
                    table.addCell(g.getSubject());
                    table.addCell(String.valueOf(g.getScore()));
                }
            }
        }
        print(table.render(), true);
    }


    public static void printStudentDetail(Student student) {
        Table table = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Subject");
        table.addCell("Score");

        if(student.getGrades().isEmpty()){
            table.addCell(student.getId());
            table.addCell(student.getFullName());
            table.addCell("-");
            table.addCell("No Grade");
        }else {
            for (Grade g : student.getGrades().values()) {
                table.addCell(student.getId());
                table.addCell(student.getFullName());
                table.addCell(g.getSubject());
                table.addCell(String.valueOf(g.getScore()));
            }
        }
        print(table.render(), true);
    }

    public static void printTopStudentSpecificSubject(Student student,String subject) {
        Table table = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Subject");
        table.addCell("Score");


            for (Grade g : student.getGrades().values()) {
               if(g.getSubject().equals(subject)){
                   table.addCell(student.getId());
                   table.addCell(student.getFullName());
                   table.addCell(g.getSubject());
                   table.addCell(String.valueOf(g.getScore()));
               }
            }

        print(table.render(), true);
    }


}
