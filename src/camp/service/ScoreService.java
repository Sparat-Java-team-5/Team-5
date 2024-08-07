package camp.service;

import camp.database.ScoreStore;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.database.InitData;
  
import java.util.*;

public class ScoreService {
    //스캐너
    private static final Scanner sc = new Scanner(System.in);

    //생성자
    public ScoreStore scoreStore;

    public String getStudentId() {
        System.out.print("\n==[관리할 수강생의 번호를 입력하시오]===");
        String studentId = sc.next();
        if (studentStore.isEmpty()) {
            System.out.println("===[등록된 수강생이 없습니다.]===");
            //수강생 등록 메서드 이동
            createStudent();
        }
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                return studentId;
            }
        }
        System.out.println("===[해당 ID를 가지고 있는 수강생이 없습니다.]===");
        return null;
    }

    public void createScore()
    {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        if (studentId == null) {
            //점수 생성 메서드 호출 (재귀 함수)
            createScore();
        }

        System.out.println("===[시험 점수를 등록합니다]===");
        // 수강생이 수강한 과목 정보를 가져옴
        ArrayList<String> subjects = subjectTakenStore.get(studentId);
        //System.out.println("확인용 출력 : "+subjects);

        //수강한 과목수 만큼 돌면서 모든 과목 점수 입력받도록
        boolean againSc = true;
        while (againSc) {
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
            for (int i = 0; i < 10; i++) {
                System.out.println(i + 1 + " 회차 점수를 입력해 주세요 : ");
                int scoreInput = sc.nextInt();
                Score scoreObject = new Score(sequence(INDEX_TYPE_SCORE), studentId, selectedSubjectId, i+1, scoreInput);
                scoreStore.setScoreStore(scoreObject);
            }

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


    //3. 헬퍼메서드 -> 기능구현에 활용할 함수
    public Student findStudentById(String studentId){ //ID로학생찾는매서드
        return studentStore.stream() //stream : 컬렉션의 데이터를 하나씩 처리하는 도구
                .filter(student -> student.getStudentId().equals(studentId))//getstudentId==studentId인지 비교
                .findFirst() //걸러진 스트림에서 first요소를 find하는 것. (따라서 조건에 맞는 첫 번째 학생 객체를 반환)
                .orElse(null); //조건에 맞는 학생 객체 없으면 null
    }


    public Subject findSubjectByName(String subjectName){//과목명으로 과목 찾는 메서드
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectName().equals(subjectName))
                .findFirst()
                .orElse(null);

    }

    //과목 고유 ID로 과목 명 찾기
    public Subject findSubjectNameById(String subjectId){
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .findFirst()
                .orElse(null);

    }


    public Score findScore(Student student, Subject subject, int round) {//수강생,과목,회차로 점수 찾는 매소드
        return scoreStore.getScoreStore().stream()
                .filter(score -> score.getStudentId().equals(student.getStudentId()) &&
                        score.getSubjectId().equals(subject.getSubjectId()) &&
                        score.getRound() == round)
                .findFirst()
                .orElse(null);
    }


    // 수강생의 과목별 회차 점수 수정 -> 기능구현시작
    public void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호(ID)입력받기
        // 3. ID 입력받고 ID헬퍼메서드 호출해서 ID추적. 없으면 오류(ID값 == null)
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("수강생을 찾을 수 없습니다.");
            return;
        }


        //  subject 입력받고 subject헬퍼 매서드 호출해 해당 과목 찾고 없으면 오류메시지
        System.out.println("수정할 과목명을 입력해주세요 : ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);
        if (subject == null) {
            System.out.println("과목을 찾을 수 없습니다.");
            return;
        }


        //수정할 회차와 점수 입력받고 score헬퍼 호출 해 특정과목회차점수 찾기
        //입력 받을 수 있는 회차는 1 ~ 10 사이 수
        int round =0;
        while(true){
            System.out.println("수정할 회차를 입력하세요 : ");
            round = sc.nextInt();
            if(1<=round && round <=10){
                break;
            }
            else{
                System.out.println("회차는 1 ~ 10 사이의 숫자여야 합니다.");
            }
        }

        // 점수 입력 범위 0 ~ 100으로 제한
        int scoreValue = 0;
        while(true){
            System.out.println("수정할 점수를 입력하세요 : ");
            scoreValue = sc.nextInt();
            if(0<=scoreValue && scoreValue <=100){
                break;
            }
            else{
                System.out.println("시험 점수는 0 ~ 100 사이의 숫자여야 합니다.");
            }
        }

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
    public void inquireRoundGradeBySubject() {
        String studentId = getStudentId();
        System.out.print("과목명을 입력하세요: ");
        String subjectName = sc.next();
        Subject subject = findSubjectByName(subjectName);
        if (subject == null) {
            System.out.println("과목을 찾을 수 없습니다. 과목명을 확인해주세요.");
            return;
        }
        List<Score> scores = findScoresByStudentAndSubject(studentId, subject.getSubjectId());
        if (scores.isEmpty()) {
            System.out.println("해당 과목에 대한 점수가 존재하지 않습니다.");
            return;
        }
        System.out.println("회차별 등급을 조회합니다...");
        for (Score score : scores) {
            System.out.println("회차: " + score.getRound() + ", 점수: " + score.getScore()+", 등급 : "+ getGrade(score);

        }
        System.out.println("\n등급 조회 성공!");
    }
    public List<Score> findScoresByStudentAndSubject(String studentId, String subjectId) {//수강생이랑 과목으로 점수 찾는 메소드
        return scoreStore.getScoreStore().stream()
                .filter(score -> score.getStudentId().equals(studentId) &&
                        score.getSubjectId().equals(subjectId))//특정 수강생, 특정 과목만 남긴다.
                .toList();
    }

    //getGrade()
    public String getGrade(Score score) {
        List<Subject> subjectStore = //과목 스토리지 들고와야함;
        String subjectType="";
        for (Subject subject : subjectStore){
            if(subject.getSubjectId().equals(score.getSubjectId())){
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
