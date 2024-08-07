package camp.database;

import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

//과목 데이터 저장소
public class SubjectStore {
    //과목 정보를 저장하는 리스트 자료구조
    private static List<Subject> subjectStore = new ArrayList<>();

    //getter
    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }

    //setter
    public static void setSubjectStore(List<Subject> subjectStore) {
        SubjectStore.subjectStore = subjectStore;
    }

    //과목 추가 메서드
    public static void addSubject(Subject subject) {
        subjectStore.add(subject);
    }

    //과목 삭제 메서드
    public static void removeSubject(Subject subject) {
        subjectStore.remove(subject);
    }
}

