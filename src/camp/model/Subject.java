package camp.model;

public class Subject {
    //과목 ID
    private final String subjectId;
    //과목 이름
    private final String subjectName;
    //과목 유형
    private final String subjectType;
    //생성자 : 과목 객체를 초기화
    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq; //과목 ID 초기화
        this.subjectName = subjectName; //과목 이름 초기화
        this.subjectType = subjectType; //과목 타입 초기화
    }

    // Getter 메서드 : 각 필드 값을 반환
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

}
