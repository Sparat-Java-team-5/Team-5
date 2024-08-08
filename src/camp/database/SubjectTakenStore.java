package camp.database;

import java.util.ArrayList;

import java.util.Map;

public class SubjectTakenStore {
    //학생이 수강한 과목을 저장하는 맵 키 : 학생 번호, value : 수강한 과목 ID 리스트
    private static Map<String, ArrayList<String>> subjectTakenStore;
    //생성자 : 수강한 과목  저장소를 초기화
    public SubjectTakenStore(Map<String, ArrayList<String>> subjectTakenStore) {
        //초기화된 수강 과목 맵을 클래스 변수에 할당
        this.subjectTakenStore = subjectTakenStore;
    }
    //학생이 수강한 과목을 저장소에 추가하는 메서드
    public void setSubjectTakenStore(String studentId, ArrayList<String> subjects) {
        //전달받은 학생 ID와 과목 리스트를 맵에 추가
        this.subjectTakenStore.put(studentId, subjects);
    }
    //수강한 과목 저장소를 반환하는 getter 메서드
    public Map<String, ArrayList<String>> getSubjectTakenStore() {
        return this.subjectTakenStore;
    }
    //특정 학생이 수강한 과목 리스트를 반환하는 메서드
    public ArrayList<String> getSubjectsTaken(String studentId) {
        //학생 ID에 해당하는 과목 리스트를 반환
        return subjectTakenStore.get(studentId);
    }
}
