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

}