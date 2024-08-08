package camp.model;

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

    // Getter 메서드 : 각 필드 값을 반환
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

    // Setter 메서드 : 점수를 설정
    public void setScore(int score) {
        this.score = score;
    }

}