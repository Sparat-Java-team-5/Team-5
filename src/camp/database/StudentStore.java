package camp.database;

import camp.model.Student;

import java.util.*;

public class StudentStore {
    //학생 데이터를 저장하는 리스트
    private static List<Student> studentStore;

    //생성자 : 학생 저장소를 초기화
    public StudentStore(List<Student> studentStore) {
        //초기화된 학생 리스트를 클래스 변수에 할당
        this.studentStore = studentStore;
    }
    //학생 저장소 리스트를 반환하는 getter 메서드
    public List<Student> getStudentStore() {
        return this.studentStore;
    }
    //새로운 학생을 학생 저장소에 추가하는 메서드
    public void setStudentStore(Student studentObject) {
        //전달받은 학생 객체를 리스트에 추가
        this.studentStore.add(studentObject);
    }
}
