package camp.model;

import camp.CampManagementApplication;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
//<<<<<<< Test/Sumin
    private int round;
    private int score;

    // Constructor
    public Score(String scoreId) {
        this.scoreId = scoreId;
    }
//=======
    private Student student;
    private Subject subject;
    private int round;
    private int score;
    //과목 회차
    //과목 점수
    //MAP (key : 회차, value : 점수)
    //ArrayList -> 회차 Arraylist, 점수Arraylist
    Map<Integer, Integer> roundScore;
//>>>>>>> Feat/Sumin

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

//<<<<<<< Test/Sumin
    public CampManagementApplication getStudent() {
        CampManagementApplication student = null;
        return student;
//=======
    public Student getStudent() { return student; }
    public Subject getSubject() { return subject;}
    public int getRound() { return round;}
    public int getScore(){ return score;}


    //Setter
    public void setRoundScore(Map roundScore){
        this.roundScore = roundScore;
//>>>>>>> Feat/Sumin
    }
    public void setScore(int score){
        this.score = score;
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
