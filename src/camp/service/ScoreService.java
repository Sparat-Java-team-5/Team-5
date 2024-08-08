package camp.service;

import camp.database.*;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static camp.database.InitData.INDEX_TYPE_SCORE;

public class ScoreService {
    //Scanner : 입력 값을 받기 위함
    private static final Scanner sc = new Scanner(System.in);
    //점수 저장소
    private final ScoreStore scoreStore;
    //학생 저장소
    private final StudentStore studentStore;
    //수강한 과목 저장소
    private final SubjectTakenStore subjectTakenStore;
    // 과목 저장소
    private final SubjectStore subjectStore;
    // 생성자
    public ScoreService(ScoreStore scoreStore, StudentStore studentStore, SubjectTakenStore subjectTakenStore, SubjectStore subjectStore) {
        this.scoreStore = scoreStore; //점수 저장소
        this.studentStore = studentStore;//학생 저장소
        this.subjectTakenStore = subjectTakenStore;//수강한 과목
        this.subjectStore = subjectStore;//과목 저장소
    }
    public String getStudentId() {
        System.out.print("\n===[관리할 수강생의 번호를 입력하시오]===");
        String studentId = sc.next();
        if (studentStore.getStudentStore().isEmpty()) { //학생 저장소가 비었는지 확인
            System.out.println("===[등록된 수강생이 없습니다.]===");
            //수강생 등록 메서드 이동
            StudentService.createStudent(); //수강생 등록 메서드 호출
        }
        for (Student student : studentStore.getStudentStore()) { //모든 숙강생 순회
            if (student.getStudentId().equals(studentId)) { // 입력받은 ID와 일치하는 수강생 찾기
                return studentId; //수강생 ID 반환
            }
        }
        System.out.println("===[해당 ID를 가지고 있는 수강생이 없습니다.]===");
        return null;
    }

    public void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        if (studentId == null) {
            //점수 생성 메서드 호출 (재귀 함수)
            createScore();
        }
        //수강한 과목수 만큼 돌면서 모든 과목 점수 입력받도록
        boolean againSc = true;
        while (againSc) {
            // 수강생이 수강한 과목 정보를 가져옴
//            ArrayList<String> subjects = subjectTakenStore.getSubjectTakenStore().get(studentId);
            System.out.println("===[시험 점수를 등록합니다]===");

            for(String subjectId : subjectTakenStore.getSubjectTakenStore().get(studentId)){ // 모든 수강한 과목을 순회
                Subject subjectName = findSubjectNameById(subjectId); // 과목 ID로 과목 이름을 찾음
                System.out.println("[" + subjectId + "] : "+ subjectName.getSubjectName());// 과목 ID와 이름 출력
            }

            System.out.println("수정할 과목명을 입력해주세요 : ");
            String subjectName = sc.next();
            Subject subject = findSubjectByName(subjectName);
            if (subject == null) {
                System.out.println("과목을 찾을 수 없습니다.");
                return;
            }
            String selectedSubjectId = subject.getSubjectId(); // 선택한 과목의 ID를 가져옴
            System.out.println("[선택한 과목 고유코드 입니다] : " + selectedSubjectId); // 선택한 과목의 ID 출력

            System.out.println("=========점수를 등록합니다=========");

            int round = 0; // 시험 회차 초기화
            while (true) {  // 유효한 회차를 입력받을 때까지 반복
                System.out.println("점수 등록할 회차를 입력하세요 : ");
                round = sc.nextInt(); // 사용자로부터 회차 입력받기
                if (1 <= round && round <= 10) { // 회차가 1에서 10 사이인지 확인
                    for(Score score : scoreStore.getScoreStore()){ // 모든 점수를 순회
                        if(score.getRound() == round){ // 이미 해당 회차의 점수가 존재하는지 확인
                            System.out.println("이미 해당 회차의 점수가 존재합니다.");
                            createScore(); // 점수 생성 메서드를 재귀 호출
                        }
                    }
                    break;// 유효한 회차 입력 시 반복 종료
                } else {
                    System.out.println("회차는 1 ~ 10 사이의 숫자여야 합니다.");
                }
            }

            System.out.println(round + " 회차 점수를 입력해 주세요 : ");

            int scoreValue = 0; // 점수 초기화
            while (true) { // 유효한 점수를 입력받을 때까지 반복
                System.out.println("등록할 점수를 입력하세요 : ");
                scoreValue = sc.nextInt(); // 사용자로부터 점수 입력받기
                if (0 <= scoreValue && scoreValue <= 100) {  // 점수가 0에서 100 사이인지 확인
                    break; //유효한 점수 입력 시 반복 종료
                } else {
                    System.out.println("시험 점수는 0 ~ 100 사이의 숫자여야 합니다.");
                }
            }
            // 새로운 점수 객체 생성
            Score scoreObject = new Score(InitData.sequence(INDEX_TYPE_SCORE), studentId, selectedSubjectId, round, scoreValue);
            scoreStore.setScoreStore(scoreObject); // 점수 저장소에 점수 추가


            System.out.println("\n===[점수 등록 성공!]===");

            for(Score score : scoreStore.getScoreStore()){  // 모든 점수를 순회하며 출력
                System.out.println(score.getScoreId());// 점수 ID 출력
                System.out.println(score.getStudentId()); // 학생 ID 출력
                System.out.println(score.getSubjectId()); // 과목 ID 출력
                System.out.println(score.getRound()); // 시험 회차 출력
                System.out.println(score.getScore()); // 점수 출력
            }

            System.out.println("===[다른 과목 점수를 등록하시겠습니까? (yes / no)]===");
            String againScore = sc.next();
            if (!againScore.equals("yes")) {
                againSc = false;
            }
        }
    }

    public void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호(ID)입력받기
        Student student = findStudentById(studentId); // 수강생 ID로 학생 객체 찾기
        if (student == null) {
            System.out.println("===[수강생을 찾을 수 없습니다.]===");
            return;
        }

        System.out.println("[수정할 과목명을 입력해주세요] : ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);  // 입력받은 과목명으로 과목 객체 찾기
        if (subject == null) { // 과목을 찾지 못한 경우
            System.out.println("===[과목을 찾을 수 없습니다.]===");
            return;
        }

        int round = 0; // 시험 회차 초기화
        while (true) { // 유효한 회차를 입력받을 때까지 반복
            System.out.println("[수정할 회차를 입력하세요] : ");
            round = sc.nextInt();
            if (1 <= round && round <= 10) { // 회차가 1에서 10 사이인지 확인
                break;// 유효한 회차 입력 시 반복 종료
            } else {
                System.out.println("===[회차는 1 ~ 10 사이의 숫자여야 합니다.]===");
            }
        }

        int scoreValue = 0; // 점수 초기화
        while (true) { // 유효한 점수를 입력받을 때까지 반복
            System.out.println("[수정할 점수를 입력하세요] : ");
            scoreValue = sc.nextInt();
            if (0 <= scoreValue && scoreValue <= 100) { // 점수가 0에서 100 사이인지 확인
                break; // 유효한 점수 입력 시 반복 종료
            } else {
                System.out.println("===[시험 점수는 0 ~ 100 사이의 숫자여야 합니다.]===");
            }
        }

        Score score = findScore(student, subject, round); // 학생, 과목, 회차로 점수 객체 찾기
        if (score != null) { // 점수를 찾은 경우
            score.setScore(scoreValue); // 점수 수정
            System.out.println("===[시험 점수 수정 성공]===");
        } else {
            System.out.println("===[해당 회차의 점수를 찾을 수 없습니다.]===");
        }
    }
    // 과목별로 시험 점수를 조회하는 메서드
    public void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생의 ID를 가져옴
        System.out.print("[과목명을 입력하세요] : ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);// 입력받은 과목명으로 과목 객체 찾기
        if (subject == null) {
            System.out.println("===[과목을 찾을 수 없습니다. 과목명을 확인해주세요.]===");
            return;
        }
        List<Score> scores = findScoresByStudentAndSubject(studentId, subject.getSubjectId()); // 학생 ID와 과목 ID로 점수 리스트 찾기
        if (scores.isEmpty()) {
            System.out.println("===[해당 과목에 대한 점수가 존재하지 않습니다.]===");
            return;
        }
        System.out.println("===[회차별 등급을 조회합니다.]===");
        for (Score score : scores) {
            System.out.println("[회차] : " + score.getRound() + ", [점수] : " + score.getScore() + ", [등급] : " + getGrade(score));
        }
        System.out.println("\n===[등급 조회 성공!]=== ");
    }
    //수강생 ID로 학생 객체를 찾는 메서드
    public Student findStudentById(String studentId) {
        return studentStore.getStudentStore().stream() // 학생 저장소에서 스트림 생성
                .filter(student -> student.getStudentId().equals(studentId))  // 학생 ID와 일치하는 학생 필터링
                .findFirst() // 첫 번째 일치하는 학생 찾기
                .orElse(null);  // 일치하는 학생이 없으면 null 반환
    }
    // 과목명으로 과목 객체를 찾는 메서드
    public Subject findSubjectByName(String subjectName) {
        return subjectStore.getSubjectStore().stream()// 과목 저장소에서 스트림 생성
                .filter(subject -> subject.getSubjectName().equals(subjectName)) // 과목명과 일치하는 과목 필터링
                .findFirst() // 첫 번째 일치하는 과목 찾기
                .orElse(null); // 일치하는 과목이 없으면 null 반환
    }
    // 과목 ID로 과목 객체를 찾는 메서드
    public Subject findSubjectNameById(String subjectId) {
        return subjectStore.getSubjectStore().stream()  // 과목 저장소에서 스트림 생성
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .findFirst()
                .orElse(null);
    }
    // 학생, 과목, 회차로 점수 객체를 찾는 메서드
    public Score findScore(Student student, Subject subject, int round) {
        return scoreStore.getScoreStore().stream()// 점수 저장소에서 스트림 생성
                .filter(score -> score.getStudentId().equals(student.getStudentId()) &&
                        score.getSubjectId().equals(subject.getSubjectId()) &&
                        score.getRound() == round)
                .findFirst()
                .orElse(null);
    }
    // 학생 ID와 과목 ID로 점수 리스트를 찾는 메서드
    public List<Score> findScoresByStudentAndSubject(String studentId, String subjectId) {
        return scoreStore.getScoreStore().stream() // 점수 저장소에서 스트림 생성
                .filter(score -> score.getStudentId().equals(studentId) &&
                        score.getSubjectId().equals(subjectId))
                .toList();
    }
    // 점수를 기반으로 등급을 계산하는 메서드
    public String getGrade(Score score) {
        String subjectType = ""; //과목 유형 초기화
        for (Subject subject : subjectStore.getSubjectStore()) { // 모든 과목을 순회
            if (subject.getSubjectId().equals(score.getSubjectId())) {
                subjectType = subject.getSubjectType();
                break;
            }
        }
        if (subjectType.equals("MANDATORY")) { // 필수 과목 등급 기준
            if (score.getScore() >= 95) {
                return "A";
            } else if (score.getScore() >= 90) {
                return "B";
            } else if (score.getScore() >= 80) {
                return "C";
            } else if (score.getScore() >= 70) {
                return "D";
            } else if (score.getScore() >= 60) {
                return "E";
            } else {
                return "F";
            }
        } else if (subjectType.equals("CHOICE")) { // 선택 과목 등급 기준
            if (score.getScore() >= 90) {
                return "A";
            } else if (score.getScore() >= 80) {
                return "B";
            } else if (score.getScore() >= 70) {
                return "C";
            } else if (score.getScore() >= 60) {
                return "D";
            } else if (score.getScore() >= 50) {
                return "E";
            } else {
                return "F";
            }
        } else {
            return "F"; // 유효하지 않은 과목 유형
        }
    }
}
