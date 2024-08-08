package camp.service;

import camp.database.InitData;
import camp.database.StudentStore;
import camp.model.Student;

import java.util.Scanner;

import static camp.database.InitData.INDEX_TYPE_STUDENT;

public class StudentService {
    static Scanner sc = new Scanner(System.in);
    static StudentStore studentStore; //학생 저장소
    static SubjectService subjectService; //과목 서비스

    public StudentService(StudentStore studentStore, SubjectService subjectService) {
        this.studentStore = studentStore; //학생 저장소 초기화
        this.subjectService = subjectService; // 과목 서비스 초기화
    }
    //createStudent()
    public static void createStudent() {
        System.out.println("\n===[수강생을 등록합니다]===");
        System.out.print("[수강생 이름 입력] : ");
        String studentName = sc.next();
        // 새로운 수강생 객체 생성
        Student student = new Student(InitData.sequence(INDEX_TYPE_STUDENT), studentName);
        studentStore.setStudentStore(student);// 학생 저장소에 수강생 추가

        //수강생에게 과목 등록
        subjectService.registerSubjects(student);
        //성공
        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");
    }
    //수강생 목록 조회 메서드
    public void inquireStudent() {
        System.out.println("\n===[수강생 목록을 조회합니다]===");
        //학생 저장소가 비었는지 확인
        if (studentStore.getStudentStore().isEmpty()) {
            System.out.println("===[등록된 수강생이 없습니다.]===");
        } else {
            //모든 수강생을 순회
            for (Student students : studentStore.getStudentStore()) {
                System.out.println("[ID : " + students.getStudentId() + "]   [이름 : " + students.getStudentName()+"]");
            }
        }
        System.out.println("\n===[수강생 목록 조회 성공!]===");
    }
}
