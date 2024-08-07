package camp.service;

import camp.database.StudentStore;
import camp.model.Student;

import java.util.Scanner;

import static camp.database.InitData.INDEX_TYPE_STUDENT;

public class StudentService {
    static Scanner sc = new Scanner(System.in);
    static StudentStore studentStore;
    static SubjectService subjectService;

    public StudentService(StudentStore studentStore, SubjectService subjectService) {
        this.studentStore = studentStore;
        this.subjectService = subjectService;
    }

    //createStudent()
    public static void createStudent() {
        System.out.println("\n===[수강생을 등록합니다]===");
        System.out.print("[수강생 이름 입력] : ");
        String studentName = sc.next();
        Student student = new Student(INDEX_TYPE_STUDENT + studentStore.getStudentStore().size() + 1, studentName);
        studentStore.setStudentStore(student);

        subjectService.registerSubjects(student);
        //성공
        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");
    }

    public void inquireStudent() {
        System.out.println("\n===[수강생 목록을 조회합니다]===");
        if (studentStore.getStudentStore().isEmpty()) {
            System.out.println("===[등록된 수강생이 없습니다.]===");
        } else {
            for (Student students : studentStore.getStudentStore()) {
                System.out.println("[ID : " + students.getStudentId() + "]   [이름 : " + students.getStudentName()+"]");
            }
        }
        System.out.println("\n===[수강생 목록 조회 성공!]===");
    }
}
