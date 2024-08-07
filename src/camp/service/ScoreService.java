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
    private static final Scanner sc = new Scanner(System.in);
    private final ScoreStore scoreStore;
    private final StudentStore studentStore;
    private final SubjectTakenStore subjectTakenStore;
    private final SubjectStore subjectStore;

    public ScoreService(ScoreStore scoreStore, StudentStore studentStore, SubjectTakenStore subjectTakenStore, SubjectStore subjectStore) {
        this.scoreStore = scoreStore;
        this.studentStore = studentStore;
        this.subjectTakenStore = subjectTakenStore;
        this.subjectStore = subjectStore;
    }

    public String getStudentId() {
        System.out.print("\n===[관리할 수강생의 번호를 입력하시오]===");
        String studentId = sc.next();
        if (studentStore.getStudentStore().isEmpty()) {
            System.out.println("===[등록된 수강생이 없습니다.]===");
            //수강생 등록 메서드 이동
            StudentService.createStudent();
        }
        for (Student student : studentStore.getStudentStore()) {
            if (student.getStudentId().equals(studentId)) {
                return studentId;
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
            ArrayList<String> subjects = subjectTakenStore.getSubjectTakenStore().get(studentId);
            System.out.println("===[시험 점수를 등록합니다]===");

            for(String subjectId : subjects){
                Subject subjectName = findSubjectNameById(subjectId);
                System.out.println("[" + subjectId + "] : "+ subjectName.getSubjectName());
            }



            System.out.println("수정할 과목명을 입력해주세요 : ");
            String subjectName = sc.next();
            Subject subject = findSubjectByName(subjectName);
            if (subject == null) {
                System.out.println("과목을 찾을 수 없습니다.");
                return;
            }
            String selectedSubjectId = subject.getSubjectId();
            System.out.println("[선택한 과목 고유코드 입니다] : " + selectedSubjectId);

            System.out.println("=========점수를 등록합니다=========");

            int round = 0;
            while (true) {
                System.out.println("점수 등록할 회차를 입력하세요 : ");
                round = sc.nextInt();
                if (1 <= round && round <= 10) {
                    for(Score score : scoreStore.getScoreStore()){
                        if(score.getRound() == round){
                            System.out.println("이미 해당 회차의 점수가 존재합니다.");
                            createScore();
                        }
                    }
                    break;
                } else {
                    System.out.println("회차는 1 ~ 10 사이의 숫자여야 합니다.");
                }
            }

            System.out.println(round + " 회차 점수를 입력해 주세요 : ");

            int scoreValue = 0;
            while (true) {
                System.out.println("등록할 점수를 입력하세요 : ");
                scoreValue = sc.nextInt();
                if (0 <= scoreValue && scoreValue <= 100) {
                    break;
                } else {
                    System.out.println("시험 점수는 0 ~ 100 사이의 숫자여야 합니다.");
                }
            }

            Score scoreObject = new Score(InitData.sequence(INDEX_TYPE_SCORE), studentId, selectedSubjectId, round, scoreValue);
            scoreStore.setScoreStore(scoreObject);

            System.out.println("\n===[점수 등록 성공!]===");

            for(Score score : scoreStore.getScoreStore()){
                System.out.println(score.getScoreId());
                System.out.println(score.getStudentId());
                System.out.println(score.getSubjectId());
                System.out.println(score.getRound());
                System.out.println(score.getScore());
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
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("===[수강생을 찾을 수 없습니다.]===");
            return;
        }

        System.out.println("[수정할 과목명을 입력해주세요] : ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);
        if (subject == null) {
            System.out.println("===[과목을 찾을 수 없습니다.]===");
            return;
        }

        int round = 0;
        while (true) {
            System.out.println("[수정할 회차를 입력하세요] : ");
            round = sc.nextInt();
            if (1 <= round && round <= 10) {
                break;
            } else {
                System.out.println("===[회차는 1 ~ 10 사이의 숫자여야 합니다.]===");
            }
        }

        int scoreValue = 0;
        while (true) {
            System.out.println("[수정할 점수를 입력하세요] : ");
            scoreValue = sc.nextInt();
            if (0 <= scoreValue && scoreValue <= 100) {
                break;
            } else {
                System.out.println("===[시험 점수는 0 ~ 100 사이의 숫자여야 합니다.]===");
            }
        }

        Score score = findScore(student, subject, round);
        if (score != null) {
            score.setScore(scoreValue);
            System.out.println("===[시험 점수 수정 성공]===");
        } else {
            System.out.println("===[해당 회차의 점수를 찾을 수 없습니다.]===");
        }
    }

    public void inquireRoundGradeBySubject() {
        String studentId = getStudentId();
        System.out.print("[과목명을 입력하세요] : ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);
        if (subject == null) {
            System.out.println("===[과목을 찾을 수 없습니다. 과목명을 확인해주세요.]===");
            return;
        }
        List<Score> scores = findScoresByStudentAndSubject(studentId, subject.getSubjectId());
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

    public Student findStudentById(String studentId) {
        return studentStore.getStudentStore().stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public Subject findSubjectByName(String subjectName) {
        return subjectStore.getSubjectStore().stream()
                .filter(subject -> subject.getSubjectName().equals(subjectName))
                .findFirst()
                .orElse(null);
    }

    public Subject findSubjectNameById(String subjectId) {
        return subjectStore.getSubjectStore().stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .findFirst()
                .orElse(null);
    }

    public Score findScore(Student student, Subject subject, int round) {
        return scoreStore.getScoreStore().stream()
                .filter(score -> score.getStudentId().equals(student.getStudentId()) &&
                        score.getSubjectId().equals(subject.getSubjectId()) &&
                        score.getRound() == round)
                .findFirst()
                .orElse(null);
    }

    public List<Score> findScoresByStudentAndSubject(String studentId, String subjectId) {
        return scoreStore.getScoreStore().stream()
                .filter(score -> score.getStudentId().equals(studentId) &&
                        score.getSubjectId().equals(subjectId))
                .toList();
    }

    public String getGrade(Score score) {
        String subjectType = "";
        for (Subject subject : subjectStore.getSubjectStore()) {
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
