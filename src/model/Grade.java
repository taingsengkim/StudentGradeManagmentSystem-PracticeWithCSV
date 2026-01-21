package model;

public class Grade {
    private String subject;
    private Double score;

    public Grade(){}

    public Grade(String subject, Double score) {
        this.subject = subject;
        this.score = score;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return this.subject;
    }
    public void setScore(Double score){
        this.score = score;
    }
    public Double getScore(){
        return this.score;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "subject='" + subject + '\'' +
                ", score=" + score +
                '}';
    }
}
