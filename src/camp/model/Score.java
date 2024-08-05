package camp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;

    //과목 회차
    //과목 점수
    //MAP (key : 회차, value : 점수)
    //ArrayList -> 회차 Arraylist, 점수Arraylist
    Map<Integer, Integer> roundScore;

    public Score(String seq, String studentId, String subjectId) {
        this.scoreId = seq;
        this.studentId = studentId;
        this.subjectId = subjectId;
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

    public Map getRoundScore(){
        return roundScore;
    }

    //Setter
    public void setRoundScore(Map roundScore){
        this.roundScore = roundScore;
    }

}
