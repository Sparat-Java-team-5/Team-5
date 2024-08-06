package camp.model;

import camp.CampManagementApplication;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;

    // Constructor
    public Score(String scoreId) {
        this.scoreId = scoreId;
    }

    public Score(String scoreId, String studentId, String subjectId, int round, int score) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
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

    public CampManagementApplication getStudent() {
        CampManagementApplication student = null;
        return student;
    }

    public Score getSubject() {
        Score subject = null;
        return subject;
    }
    // Setter
    public void setScore(int score) {
        this.score = score;
    }
}
