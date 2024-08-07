package camp.database;

import camp.model.Student;

import java.util.*;

public class StudentStore {
    private static List<Student> studentStore;

    public StudentStore(List<Student> studentStore) {
        this.studentStore = studentStore;
    }

    public List<Student> getStudentStore() {
        return this.studentStore;
    }

    public void setStudentStore(Student studentObject) {
        this.studentStore.add(studentObject);
    }
}
