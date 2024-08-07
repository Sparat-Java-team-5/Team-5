package camp.database;

import camp.model.Student;

import java.util.*;

public class StudentStore {
    //학생 저장 자료구조
    private static List<Student> studentStore;

    //생성자
    public StudentStore(List<Student> studentStore){
        this.studentStore = studentStore;
    }

    //getter
    public List<Student> getStudentStore(){
        return this.studentStore;
    }

    //setter
    public void setStudentStore(Student studentObject){
        this.studentStore.add(studentObject);
    }
}
