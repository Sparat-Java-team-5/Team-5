package camp.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectTakenStore {
    // key : 학생 고유 ID, value : 학생이 수강한 과목 배열
    private static Map<String, ArrayList<String>> subjectTakenStore = new HashMap<>();

    // 수강 과목 추가 메서드
    public static void addSubjectsTaken(String studentId, ArrayList<String> subjects) {
        subjectTakenStore.put(studentId, subjects);
    }

    // 수강 과목 조회 메서드
    public static ArrayList<String> getSubjectsTaken(String studentId) {
        return subjectTakenStore.get(studentId);
    }

    // 모든 수강 과목 조회 메서드
    public static Map<String, ArrayList<String>> getAllSubjectTakenStore() {
        return subjectTakenStore;
    }
}