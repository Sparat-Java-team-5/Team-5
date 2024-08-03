package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;


/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {

    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;
    private static Map<String, ArrayList> subjectTakenStore;


    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY"; // 필수 과목 타입
    private static String SUBJECT_TYPE_CHOICE = "CHOICE"; // 선택 과목 타임

    // index 관리 필드
    private static int studentIndex;  //학생 index
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;  //과목 index
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;  // 점수 index??
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인_패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring_Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
        subjectTakenStore = new HashMap<>(); //수강한 과목 저장 데이터베이스 자료형 생성
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex; //학생 ID 생성
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex; // 과목 ID 생성
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;  // 점수 ID 생성??
            }
        }
    }

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

    // 수강생 등록 메서드
    private static void createStudent() {
        System.out.println("[===================[수강생을 등록합니다]=========================]");
        System.out.print("[수강생 이름 입력] : ");
        String studentName = sc.next();

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName);
        studentStore.add(student);
        // 수강생 인스턴스 생성

        //수강신청된 과목 저장 배열
        ArrayList<String> subjectTaken = new ArrayList<>();

        System.out.println("============[ 필수과목 수강신청 ]============");
        System.out.println("===[수강하실 필수과목 3개 이상 입력해 주세요!]===");
        System.out.println("[과목 코드] [과목 명]");
        //필수과목코드와 과목 명 출력
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName());
            }
        }
        //필수과목 수강 종료 입력 받기위한 변수
        boolean mandatory = true;
        //필수 과목 수강 신청
        while (mandatory && subjectTaken.size() < 5) {
            System.out.println("[과목 코드 입력] : ");
            String subjectId = sc.next();

            //입력한 과목코드가 존재하는지 확인하는 변수
            boolean isContain = false;

            for (Subject subject : subjectStore) {
                //과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                    if (!subjectTaken.contains(subjectId)) { //중복 등록 방지
                        subjectTaken.add(subjectId); //필수 과목 리스트에 추가
                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
                        System.out.println(subjectTaken);
                        isContain = true;
                    } else {
                        System.out.println(subject.getSubjectName() + " 과목은 이미 등록되었습니다.");
                    }
                }
            }
            if (!isContain) {
                System.out.println("[잘못된 입력입니다.]");
            }

            if (3 <= subjectTaken.size() && subjectTaken.size() < 5) {

                while (true) {
                    System.out.println("[==================================]");
                    System.out.println("===[필수 과목은 3개 이상만 수강하시면 됩니다]===.\n" +
                            "[필수 과목 수강을 종료하시겠습니까? (yes / no )]");
                    String exit = sc.next();
                    if (exit.equals("yes")) {
                        System.out.println("====[선택 과목으로 이동합니다.]====");
                        mandatory = false;
                        break;
                    } else if (exit.equals("no")) {
                        System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + subjectTaken.size() + "과목이 등록되었습니다]===");
                        System.out.println("====[신청할 필수 과목 번호를 입력해주세요.]====");
                        System.out.println(subjectTaken);
                    } else {
                        System.out.println("[잘못입력하였습니다.]");
                    }
                }
            }
        }
        System.out.println("***********************************");
        System.out.println("필수과목은 최대 5개까지 수강 가능합니다.\n" +
                "이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.\n" +
                "필수 과목 수강 신청을 종료 합니다.");
        //======================================================================================================
        System.out.println("============[ 선택과목 수강신청 ]============");
        System.out.println("==[수강하실 선택과목 2개 이상 입력해 주세요!]==");
        System.out.println("[과목 코드] 과목 명");
        //필수과목코드와 과목 명 출력
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName());
            }
        }

        //선택과목 수강 종료 입력 받기위한 변수
        boolean choice = true;
        //선택 과목 수강 신청
        while (choice && subjectTaken.size() < 9) {
            System.out.println("[과목 코드 입력] : ");
            String subjectId = sc.next();

            //입력한 과목코드가 존재하는지 확인하는 변수
            boolean isContain = false;

            for (Subject subject : subjectStore) {
                //과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                    if (!subjectTaken.contains(subjectId)) { //중복 등록 방지
                        subjectTaken.add(subjectId); //필수 과목 리스트에 추가
                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
                        System.out.println(subjectTaken);
                        isContain = true;
                    } else {
                        System.out.println(subject.getSubjectName() + " 과목은 이미 등록되었습니다.");
                    }
                    if (isContain == false) {
                        System.out.println("[잘못된 입력입니다.]");
                    }
                }
            }

            if (5 <= subjectTaken.size() && subjectTaken.size() < 9) {


                while (true) {
                    System.out.println("==================================");
                    System.out.println("===[선택 과목은 2개 이상만 수강하시면 됩니다.]===\n" +
                            "===[선택 과목 수강을 종료하시겠습니까? (yes / no )]===");
                    String exit = sc.next();
                    if (exit.equals("yes")) {
                        System.out.println("===[현재 등록된 선택 과목 리스트입니다. " + subjectTaken.size() + "과목이 등록되었습니다]===");
                        choice = false;
                        break;
                    } else if (exit.equals("no")) {
                        System.out.println("===[선택 과목으로 이동합니다.]===");
                        System.out.println(subjectTaken);
                    } else {
                        System.out.println("===[잘못입력하였습니다.]===");
                    }
                }
            }

        }
        System.out.println("***********************************");
        System.out.println("선택과목은 최대 4개까지 수강 가능합니다.\n" +
                "이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.\n" +
                "선택 과목 수강 신청을 종료 합니다.\n");

        //(Key : 학생 고유 ID, Value : 학생이 수강한 과목 ArrayList) 형식으로 Map자료형에 추가
        subjectTakenStore.put(student.getStudentId(), subjectTaken);

        //확인용 출력
        System.out.println("===== 수강생이 신청한 과목 확인 출력 =====");
        for (Map.Entry<String, ArrayList> entrySet : subjectTakenStore.entrySet()) {
            System.out.println("[ " + entrySet.getKey() + " : " + entrySet.getValue() + " ]");
        }

        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");

    }


    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        System.out.println("============================");
        if (studentStore.isEmpty()) { // 저장소가 비었을때
            System.out.println("등록된 수강생이 없습니다.");
            System.out.println("\n========================");
        } else {
            for (Student student : studentStore) { //for each 문으로 출력
                System.out.println("학생 이름 : " + student.getStudentName());
                System.out.println("학생 ID : " + student.getStudentId());
                System.out.println("================================================================");
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
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String studentId = sc.next();
        if (studentStore.isEmpty()) {
            return ("등록된 수강생이 없습니다.");
        }
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                return studentId;
            }
        }
        System.out.println("해당 ID를 가지고 있는 수강생이 없습니다.");
        return null;
    }

    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        if (studentId == null) {
            return; // 수강생이 없거나 잘못된 ID인 경우 메서드 종료
        }
        System.out.println("시험 점수를 등록합니다...");
        // 수강생의 과목 정보를 가져옴
        ArrayList<String> subjects = subjectTakenStore.get(studentId);

        if (subjects == null || subjects.isEmpty()) {
            System.out.println("해당 학생이 수강한 과목이 없습니다.");
            return;
        }

        // 학생의 점수 정보를 저장할 Map 생성
        Map<Integer, List<Integer>> scoreMap = new HashMap<>();

        for (String subject : subjects) {
            System.out.println("과목: " + subject); // 과목명 출력

            // 해당 과목의 점수 리스트를 가져오거나 새로 생성
            List<Integer> scores = scoreMap.getOrDefault(subject, new ArrayList<>());


            // 회차 자동 부여 (1부터 시작, 최대 10회차)
            int round = scores.size()+1;
            System.out.println(round+"회차");

            System.out.println("점수를 입력해주세요.");
            int score = sc.nextInt();
            scores.add(score); // 점수를 리스트에 추가

            // 과목별 점수 리스트를 scoreMap에 저장
            scoreMap.put(round, scores);
            System.out.println("등록된 점수: " + scores); // 등록된 점수 리스트 출력
        }

        // 해당 수강생의 점수 정보를 scoreStore에 저장
        scoreStore.add(new Score(studentId, scoreMap));

        System.out.println("===== 수강생의 과목별 점수 확인 출력 =====");
        for (Map.Entry<Integer, List<Integer>> entrySet : scoreMap.entrySet()) {
            System.out.println("[" + entrySet.getKey() + " : " + entrySet.getValue() + "]");
        }

        System.out.println("\n점수 등록 성공!");
    }








    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String studentId = sc.next();
        if (studentStore.isEmpty()) {

        }
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {

            }
        }
        System.out.println("해당 ID를 가지고 있는 수강생이 없습니다.");

        System.out.println("시험 점수를 수정합니다...");
        //
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");

        System.out.println("\n등급 조회 성공!");
    }

}
