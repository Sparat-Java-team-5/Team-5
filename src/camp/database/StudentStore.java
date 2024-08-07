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
    public static List<Student> getStudentStore(){
        return studentStore;
    }

    //setter
    public static void setStudentStore(Student studentObject){
        studentStore.add(studentObject);
    }
}
