package camp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {
    private String scoreId;//
    private String studentId;
    private String subjectId;
    private Student student;//
    private Subject subject;//
    private int round;
    private int score;



    public Score(String seq, String studentId, String subjectId, String scoreId, Student student, Subject subject, int round, int score) {
        this.scoreId = seq;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreId=scoreId;//
        this.student=student;//
        this.subject=subject;//
        this.round=round;//
        this.score=score;//
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }


    public Student getStudent() { return student; }
    public Subject getSubject() { return subject;}
    public int getRound() { return round;}
    public int getScore(){ return score;}


    //Setter

    public void setScore(int score){
        this.score = score;
    }


    //점수를 등급으로 환산하는 메서드 추가
    public String getGrade() {
        String subjectType = subject.getSubjectType();
        if (subjectType.equals("MANDATORY")) { // 필수 과목 등급 기준
            if (score >= 95) {
                return "A";
            } else if (score >= 90) {
                return "B";
            } else if (score >= 80) {
                return "C";
            } else if (score >= 70) {
                return "D";
            } else if (score >= 60) {
                return "E";
            } else {
                return "F";
            }
        } else if (subjectType.equals("CHOICE")) { // 선택 과목 등급 기준
            if (score >= 90) {
                return "A";
            } else if (score >= 80) {
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else if (score >= 50) {
                return "E";
            } else {
                return "F";
            }
        } else {
            return "F"; // 유효하지 않은 과목 유형
        }
    }

}