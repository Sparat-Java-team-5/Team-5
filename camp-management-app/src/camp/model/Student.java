package camp.model;

public class Student {
    private final String studentId;
    private final String studentName;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getName() {
        return studentName;
    }

    public String getId() {
    return studentId;}
}
