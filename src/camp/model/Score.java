package camp.model;

import camp.view.CampManagementApplication;

import java.util.List;


public class Score {
    //점수 ID
    private String scoreId;//
    //학생 ID
    private String studentId;
    //과목 ID
    private String subjectId;
    //시험 회차
    private int round;
    //점수
    private int score;




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

}