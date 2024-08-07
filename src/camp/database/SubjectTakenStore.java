package camp.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectTakenStore {
    // key : 학생 고유 ID, value : 학생이 수강한 과목 배열
    private static Map<String, ArrayList<String>> subjectTakenStore;

    public SubjectTakenStore(Map<String, ArrayList<String>> subjectTakenStore){
        this.subjectTakenStore = subjectTakenStore;
    }

    // 수강 과목 추가 메서드
    public static void setSubjectTakenStore(String studentId, ArrayList<String> subjects) {
        subjectTakenStore.put(studentId, subjects);
    }

    // 모든 수강 과목 조회 메서드
    public static Map<String, ArrayList<String>> getSubjectTakenStore() {
        return subjectTakenStore;
    }


    // 수강 과목 조회 메서드
    public ArrayList<String> getSubjectsTaken(String studentId) {
        return subjectTakenStore.get(studentId);
    }
}