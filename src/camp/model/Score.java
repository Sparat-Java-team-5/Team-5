package camp.model;

import camp.view.CampManagementApplication;

import java.util.List;


public class Score {
    private String scoreId;//
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    CampManagementApplication campManagementApplication = new CampManagementApplication();


    //Student student, Subject subject,
    public Score(String seq, String studentId, String subjectId, int round, int score) {
        this.scoreId = seq;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;//
        this.score = score;//
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }

    // Setter
    public void setScore(int score) {
        this.score = score;
    }


    //getGrade() 는 ScoreService로
    //점수를 등급으로 환산하는 메서드 추가
    public String getGrade(String subjectId) {
        List<Subject> subjectStore = campManagementApplication.getSubjectStore();
        String subjectType="";
        for (Subject subject : subjectStore){
            if(subject.getSubjectId().equals(subjectId)){
                subjectType = subject.getSubjectType();
                break;
            }
        }
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