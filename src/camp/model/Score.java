package camp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    //학생 아이디 studentId
    //과목 아이디 subjectId
    //과목 회차
    //과목 점수
    //MAP (key : 회차, value : 점수)
    //ArrayList -> 회차 Arraylist, 점수Arraylist
    Map<Integer, List<Integer>> roundScore;

    public Score(String seq, Map<Integer, List<Integer>> scoreMap) {
        this.scoreId = seq;
    }


    // Getter
    public String getScoreId() {
        return scoreId;
    }

}
