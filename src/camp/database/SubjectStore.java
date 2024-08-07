package camp.database;

import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

//과목 데이터 저장소
public class SubjectStore {
    //과목 정보를 저장하는 리스트 자료구조
    private static List<Subject> subjectStore;

    //생성자
    public SubjectStore(List<Subject> subjectStore) {
        this.subjectStore = subjectStore;
    }

    public List<Subject> getSubjectStore() {
        return this.subjectStore;
    }
}

