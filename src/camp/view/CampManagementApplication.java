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







    //수강신청된 과목 저장 배열
//        ArrayList<String> mandatorySubjectTaken = new ArrayList<>();
//
//        System.out.println("============[ 필수과목 수강신청 ]============");
//        System.out.println("===[수강하실 필수과목 3개 이상 입력해 주세요!]===");
//        System.out.println("[과목 코드] [과목 명]");
//        //필수과목코드와 과목 명 출력
//        for (Subject subject : subjectStore) {
//            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
//                System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName());
//            }
//        }
//        //필수과목 수강 종료 입력 받기위한 변수
//        boolean mandatory = true;
//        //필수 과목 수강 신청
//        while (mandatory && mandatorySubjectTaken.size() < 5) {
//            System.out.println("[과목 코드 입력] : ");
//            String subjectId = sc.next();
//
//            //입력한 과목코드가 존재하는지 확인하는 변수
//            boolean isContain = false;
//
//            for (Subject subject : subjectStore) {
//                //과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
//                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
//                    if (!mandatorySubjectTaken.contains(subjectId)) { //중복 등록 방지
//                        mandatorySubjectTaken.add(subjectId); //필수 과목 리스트에 추가
//                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
//                        System.out.println(mandatorySubjectTaken);
//                        isContain = true;
//                    } else {
//                        System.out.println("["+subject.getSubjectName() + " 과목은 이미 등록되었습니다.]");
//                    }
//                }
//            }
//            if (!isContain) {
//                System.out.println("[잘못된 입력입니다.]");
//            }
//
//
//
//            if (3 <= mandatorySubjectTaken.size() && mandatorySubjectTaken.size() < 5) {
//
//                while (true) {
//                    System.out.println("[==================================]");
//                    System.out.println("===[필수 과목은 3개 이상만 수강하시면 됩니다]===.\n" +
//                            "[필수 과목 수강을 종료하시겠습니까? (yes / no )]");
//                    String exit = sc.next();
//                    if (exit.equals("yes")) {
//                        System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + mandatorySubjectTaken.size() + "과목이 등록되었습니다]===");
//                        System.out.println("====[선택 과목으로 이동합니다.]====");
//                        mandatory = false;
//                        break;
//                    } else if (exit.equals("no")) {
//                        System.out.println("====[신청할 필수 과목 번호를 입력해주세요.]====");
//                        System.out.println(mandatorySubjectTaken);
//                        break;
//                    } else {
//                        System.out.println("[잘못입력하였습니다.]");
//                    }
//                }
//            }
//        }
//
//        System.out.println("***********************************");
//        if(mandatorySubjectTaken.size() == 5){
//            System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + mandatorySubjectTaken.size() + "과목이 등록되었습니다]===");
//            System.out.println("필수과목은 최대 5개까지 수강 가능합니다.\n" +
//                    "이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.\n");
//        }
//        System.out.println("필수 과목 수강 신청을 종료 합니다.");
//        //======================================================================================================
//        System.out.println("============[ 선택과목 수강신청 ]============");
//        System.out.println("==[수강하실 선택과목 2개 이상 입력해 주세요!]==");
//        System.out.println("[과목 코드] 과목 명");
//        //필수과목코드와 과목 명 출력
//        for (Subject subject : subjectStore) {
//            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
//                System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName());
//            }
//        }
//
//        ArrayList<String> choiceSubjectTaken = new ArrayList<>();
//        //선택과목 수강 종료 입력 받기위한 변수
//        boolean choice = true;
//        //선택 과목 수강 신청
//        while (choice && choiceSubjectTaken.size() < 4) {
//            System.out.println("[과목 코드 입력] : ");
//            String subjectId = sc.next();
//
//            //입력한 과목코드가 존재하는지 확인하는 변수
//            boolean isContain = false;
//
//            for (Subject subject : subjectStore) {
//                //과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
//                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
//                    if (!choiceSubjectTaken.contains(subjectId)) { //중복 등록 방지
//                        choiceSubjectTaken.add(subjectId); //필수 과목 리스트에 추가
//                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
//                        System.out.println(choiceSubjectTaken);
//                        isContain = true;
//                    } else {
//                        System.out.println("["+subject.getSubjectName() + " 과목은 이미 등록되었습니다.]");
//                    }
//                    if (isContain == false) {
//                        System.out.println("[잘못된 입력입니다.]");
//                    }
//                }
//            }
//
//            if (2<=choiceSubjectTaken.size() && choiceSubjectTaken.size() < 4) {
//
//                while (true) {
//                    System.out.println("==================================");
//                    System.out.println("===[선택 과목은 2개 이상만 수강하시면 됩니다.]===\n" +
//                            "===[선택 과목 수강을 종료하시겠습니까? (yes / no )]===");
//                    String exit = sc.next();
//                    if (exit.equals("yes")) {
//                        System.out.println("===[현재 등록된 선택 과목 리스트입니다. " + choiceSubjectTaken.size() + "과목이 등록되었습니다]===");
//                        choice = false;
//                        break;
//                    } else if (exit.equals("no")) {
//                        System.out.println("===[선택 과목으로 이동합니다.]===");
//                        System.out.println(choiceSubjectTaken);
//                        break;
//                    } else {
//                        System.out.println("===[잘못입력하였습니다.]===");
//                    }
//                }
//            }
//        }
//        System.out.println("***********************************");
//        if(choiceSubjectTaken.size() == 4){
//            System.out.println("===[현재 등록된 선택 과목 리스트입니다. " + choiceSubjectTaken.size() + "과목이 등록되었습니다]===");
//            System.out.println("선택과목은 최대 4개까지 수강 가능합니다.\n" +
//                    "이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.\n");
//        }
//        System.out.println("선택 과목 수강 신청을 종료 합니다.\n");
//
//        mandatorySubjectTaken.addAll(choiceSubjectTaken);
//        SubjectTakenStore.addSubjectsTaken(student.getStudentId(), mandatorySubjectTaken);
//
//        //확인용 출력
//        System.out.println("===== 수강생이 신청한 과목 확인 출력 =====");
//        for (Map.Entry<String, ArrayList<String>> entrySet : getAllSubjectTakenStore().entrySet()) {
//            System.out.println("[ " + entrySet.getKey() + " : " + entrySet.getValue() + " ]");
//        }
//>>>>>>> release1-3

    //성공
        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");

        // 수강생 목록 조회
        private static void inquireStudent() {
            System.out.println("\n수강생 목록을 조회합니다...");
            if (studentStore.getStudentStore().isEmpty()) {
                System.out.println("등록된 수강생이 없습니다.");
            } else {
                for (Student student : studentStore.getStudentStore()) {
                    System.out.println("ID: " + student.getStudentId() + ", 이름: " + student.getStudentName());
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
}
