package camp.model;

public class Student {
    //학생ID
    private final String studentId;
    //학생 이름
    private final String studentName;

    //생성자 :학생 객체를 초기화
    public Student(String seq, String studentName) {
        this.studentId = seq; //학생 ID를 초기화
        this.studentName = studentName; // 학생 이름을 초기화
    }

    // Getter 메서드 : 각 필드 값을 반환
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

}
