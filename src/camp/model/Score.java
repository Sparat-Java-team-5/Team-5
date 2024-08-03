package camp.model;

public class Score {
    private String scoreId;
    //학생 아이디 studentId
    //과목 아이디 subjectId
    //과목 회차
    //과목 점수
    //MAP (key : 회차, value : 점수)
    //ArrayList -> 회차 Arraylist, 점수Arraylist

    public Score(String seq) {
        this.scoreId = seq;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

}
