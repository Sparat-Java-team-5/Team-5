package camp.database;

import camp.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentStore {
    // key: 학생 ID, value: Student 객체
    private static Map<String, Student> studentStore = new HashMap<>();

    // 학생 추가 메서드
    public static void addStudent(Student student) {
        studentStore.put(student.getStudentId(), student);
    }

    // 모든 학생 조회 메서드
    public static Map<String, Student> getAllStudents() {
        return studentStore;
    }
}
