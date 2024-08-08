package camp.database;

import camp.model.Subject;

import java.util.List;

public class SubjectStore {
    //과목 데이터를 저장하는 리스트
    private static List<Subject> subjectStore;
    //생성자 : 과목 저장소를 초기화
    public SubjectStore(List<Subject> subjectStore) {
        //초기화된 과목 리스트를 클래스 변수에 할당
        this.subjectStore = subjectStore;
    }

    //과목 저장소 리스트를 반환하는 getter 메서드
    public List<Subject> getSubjectStore() {
        return this.subjectStore;
    }
}
