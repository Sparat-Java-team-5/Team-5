package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static final Scanner sc = new Scanner(System.in);

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
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
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
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
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

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        if (studentStore.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            for (Student student : studentStore) {
                System.out.println("ID: " + student.getId() + ", 이름: " + student.getName());
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
        return sc.next();
    }
    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.print("과목명을 입력하세요: ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);
        if (subject == null) {
            System.out.println("과목을 찾을 수 없습니다. 과목명을 확인해주세요.");
            return;
        }
        System.out.print("시험 회차를 입력하세요: ");
        int round = sc.nextInt();
        System.out.print("시험 점수를 입력하세요: ");
        int scoreValue = sc.nextInt();
        Score score = new Score(sequence(INDEX_TYPE_SCORE), studentId, subject.getId(), round, scoreValue);
        scoreStore.add(score);
        System.out.println("\n점수 등록 성공!");
    }

    //1. 생성자로 LIST값을 외부에서 받기
    public CampManagementApplication(List<Student> studentStore, List<Subject> subjectStore, List<Score> scoreStore){
        this.studentStore = studentStore;
        this.subjectStore = subjectStore;
        this.scoreStore = scoreStore;
    }

    //3. 헬퍼메서드 -> 컬렉션 stream이용
    private static Student findStudentById(String studentId){
        return studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))//getstudentId==studentId인지 비교
                .findFirst()
                .orElse(null);
    }
    private static Subject findSubjectByName(String subjectName){
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectName().equals(subjectName))
                .findFirst()
                .orElse(null);

    }
    private static Score findScore(Student student, Subject subject, int round) {
        return scoreStore.stream()
                .filter(score -> score.getStudent().getStudentId().equals(student.getStudentId()) &&
                        score.getSubject().getSubjectId().equals(subject.getSubjectId()) &&
                        score.getRound() == round)
                .findFirst()
                .orElse(null);
    }

    private List<Score> findScoresByStudentAndSubject(Student student, Subject subject) {
        return scoreStore.stream()
                .filter(score -> score.getStudent().getStudentId().equals(student.getStudentId()) &&
                        score.getSubject().getSubjectId().equals(subject.getSubjectId()))
                .toList();
    }


    // 수강생의 과목별 회차 점수 수정 -> 기능 구현 시작
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호(ID)입력받기
        // ID 입력받고 ID헬퍼메서드 호출해서 ID추적. 없으면 오류(ID값 == null)
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("수강생을 찾을 수 없습니다.");
            return;
        }
// subject 입력받고 subject헬퍼 매서드 호출해 해당 과목 찾고 없으면 오류메시지
        System.out.println("수정할 과목명을 입력해주세요 : ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);
        if (subject == null) {
            System.out.println("과목을 찾을 수 없습니다.");
            return;
        }
//수정할 회차와 점수 입력받고 score헬퍼 호출 해 특정과목회차점수 찾기
        System.out.println("수정할 회차를 입력하세요 : ");
        int round = sc.nextInt();
        System.out.println("수정할 점수를 입력하세요 : ");
        int scoreValue = sc.nextInt();
        Score score = findScore(student, subject, round);
// 점수있으면 수정하고 성공메시지 출력,없으면 오류메시지 출력
        if (score != null) {
            score.setScore(scoreValue);
            System.out.println("시험 점수 수정 성공");
        } else {
            System.out.println("해당 회차의 점수를 찾을 수 없습니다.");
        }

    }
    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId();
        System.out.print("과목명을 입력하세요: ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);
        if (subject == null) {
            System.out.println("과목을 찾을 수 없습니다. 과목명을 확인해주세요.");
            return;
        }
        List<Score> scores = findScoresByStudentAndSubject(studentId, subject.getId());
        if (scores.isEmpty()) {
            System.out.println("해당 과목에 대한 점수가 존재하지 않습니다.");
            return;
        }
        System.out.println("회차별 등급을 조회합니다...");
        for (Score score : scores) {
            System.out.println("회차: " + score.getRound() + ", 점수: " + score.getScore());
        }
        System.out.println("\n등급 조회 성공!");
    }

    private static List<Score> findScoresByStudentAndSubject(String studentId, String subjectId) {
        List<Score> scores = new ArrayList<>();
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                scores.add(score);
            }
        }
        return scores;
    }
}
