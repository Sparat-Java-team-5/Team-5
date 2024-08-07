package camp.view;

import camp.database.*;
import camp.model.Student;
import camp.model.Subject;
import camp.service.ScoreService;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static camp.database.InitData.*;

public class CampManagementApplication {

    //생성자
    public static ScoreStore scoreStore;
    public static StudentStore studentStore;
    public static SubjectTakenStore subjectTakenStore;
    public static SubjectStore subjectStore;

    // 스캐너
    private static final Scanner sc = new Scanner(System.in);

    //service 객체 생성
    private static ScoreService scoreService = new ScoreService();

    public static void main(String[] args) {
        try {
            InitData.initialize();
//            subjectStore = InitData.getSubjectStore();
//            scoreStore = InitData.getScoreStore();

            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성


    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    public static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        Student student = new Student(InitData.sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        studentStore.setStudentStore(student);

        //성공
        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");

    }
    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        if (studentStore.getStudentStore().isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            for (Student students : studentStore.getStudentStore()) {
                System.out.println("ID: " + students.getStudentId() + ", 이름: " + students.getStudentName());
            }
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> scoreService.createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> scoreService.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> scoreService.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
