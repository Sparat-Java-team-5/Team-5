package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;
    private static Map<String, ArrayList> subjectTakenStore;
    //private static Map<String, Map<String, List<Integer>>> scoreMap = new HashMap<>();


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
        subjectTakenStore = new HashMap<>(); //수강한 과목 저장 데이터베이스 자료형 생성
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

        //수강신청된 과목 저장 배열
        ArrayList<String> mandatorySubjectTaken = new ArrayList<>();

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
        while (mandatory && mandatorySubjectTaken.size() < 5) {
            System.out.println("[과목 코드 입력] : ");
            String subjectId = sc.next();

            //입력한 과목코드가 존재하는지 확인하는 변수
            boolean isContain = false;

            for (Subject subject : subjectStore) {
                //과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                    if (!mandatorySubjectTaken.contains(subjectId)) { //중복 등록 방지
                        mandatorySubjectTaken.add(subjectId); //필수 과목 리스트에 추가
                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
                        System.out.println(mandatorySubjectTaken);
                        isContain = true;
                    } else {
                        System.out.println("["+subject.getSubjectName() + " 과목은 이미 등록되었습니다.]");
                    }
                }
            }
            if (!isContain) {
                System.out.println("[잘못된 입력입니다.]");
            }



            if (3 <= mandatorySubjectTaken.size() && mandatorySubjectTaken.size() < 5) {

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
                        System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + mandatorySubjectTaken.size() + "과목이 등록되었습니다]===");
                        System.out.println("====[신청할 필수 과목 번호를 입력해주세요.]====");
                        System.out.println(mandatorySubjectTaken);
                        break;
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

        ArrayList<String> choiceSubjectTaken = new ArrayList<>();
        //선택과목 수강 종료 입력 받기위한 변수
        boolean choice = true;
        //선택 과목 수강 신청
        while (choice && choiceSubjectTaken.size() < 4) {
            System.out.println("[과목 코드 입력] : ");
            String subjectId = sc.next();

            //입력한 과목코드가 존재하는지 확인하는 변수
            boolean isContain = false;

            for (Subject subject : subjectStore) {
                //과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                    if (!choiceSubjectTaken.contains(subjectId)) { //중복 등록 방지
                        choiceSubjectTaken.add(subjectId); //필수 과목 리스트에 추가
                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
                        System.out.println(choiceSubjectTaken);
                        isContain = true;
                    } else {
                        System.out.println("["+subject.getSubjectName() + " 과목은 이미 등록되었습니다.]");
                    }
                    if (isContain == false) {
                        System.out.println("[잘못된 입력입니다.]");
                    }
                }
            }

            if (2<=choiceSubjectTaken.size() && choiceSubjectTaken.size() < 4) {

                while (true) {
                    System.out.println("==================================");
                    System.out.println("===[선택 과목은 2개 이상만 수강하시면 됩니다.]===\n" +
                            "===[선택 과목 수강을 종료하시겠습니까? (yes / no )]===");
                    String exit = sc.next();
                    if (exit.equals("yes")) {
                        System.out.println("===[현재 등록된 선택 과목 리스트입니다. " + choiceSubjectTaken.size() + "과목이 등록되었습니다]===");
                        choice = false;
                        break;
                    } else if (exit.equals("no")) {
                        System.out.println("===[선택 과목으로 이동합니다.]===");
                        System.out.println(choiceSubjectTaken);
                        break;
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

        mandatorySubjectTaken.addAll(choiceSubjectTaken);
        //(Key : 학생 고유 ID, Value : 학생이 수강한 과목 ArrayList) 형식으로 Map자료형에 추가
        subjectTakenStore.put(student.getStudentId(), mandatorySubjectTaken);

        //확인용 출력
        System.out.println("===== 수강생이 신청한 과목 확인 출력 =====");
        for (Map.Entry<String, ArrayList> entrySet : subjectTakenStore.entrySet()) {
            System.out.println("[ " + entrySet.getKey() + " : " + entrySet.getValue() + " ]");
        }

        //성공
        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");

    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        if (studentStore.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            for (Student student : studentStore) {
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

    private static void createScore()
    {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        if (studentId == null) {
            //점수 생성 메서드 호출 (재귀 함수)
            createScore();
        }

        System.out.println("===[시험 점수를 등록합니다]===");
        // 수강생이 수강한 과목 정보를 가져옴
        ArrayList<String> subjects = subjectTakenStore.get(studentId);
        System.out.println("확인용 출력 : "+subjects);

        //수강한 과목수 만큼 돌면서 모든 과목 점수 입력받도록
        boolean againSc = true;
        while (againSc) {
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println("[" + (i + 1) + "]" + " : " + subjects.get(i));
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
                scoreStore.add(scoreObject);
            }

            System.out.println("\n===[점수 등록 성공!]===");

            for(Score score : scoreStore){
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

        //--------------------------------------------------------------------
//        String studentId = getStudentId(); // 관리할 수강생 고유 번호
//        if (studentId == null) {
//            return; // 수강생이 없거나 잘못된 ID인 경우 메서드 종료
//        }
//        System.out.println("======== [[" + studentId +
//                "]] 시험 점수를 등록합니다 =========");
//        // 수강생의 과목 정보를 가져옴 & 점수 등록할 과목 ID 가져옴
//        String subjectId = getSubjectId(studentId);
//        System.out.println(subjectId);
//        Score scoreObject = new Score(sequence(INDEX_TYPE_SCORE), studentId, subjectId);
//        for (int j=0;j<1;j++) {
//            System.out.println(scoreObject.getScoreId());
//            System.out.println("=========점수를 등록합니다=========");
//            Map<Integer, Integer> roundScore = new HashMap<>();
//            for (int i = 0; i < 10; i++) {
//                System.out.println(i + 1 + " 회차 점수를 입력해 주세요 : ");
//                int scoreInput = sc.nextInt();
//                roundScore.put(i + 1, scoreInput);
//            }
//            scoreObject.setRoundScore(roundScore);
//            scoreStore.add(scoreObject);
//            System.out.println(scoreObject.getRoundScore().keySet());
//            System.out.println(scoreObject.getRoundScore().values());
//            for(Score score : scoreStore){
//                System.out.println(score.getScoreId());
//                System.out.println(score.getStudentId());
//                System.out.println(score.getSubjectId());
//                System.out.println(score.getRoundScore());
//            }
            //--------------------------------------------------------------------

//        boolean againSc = true;
//        while (againSc) {
//            for (int i = 0; i < subjects.size(); i++) {
//                System.out.println("[" + (i + 1) + "]" + " : " + subjects.get(i));
//            }
//            System.out.println("===[점수를 등록할 과목 번호를 입력해주세요]===");
//            String subjectId = sc.next();
//
//            String selectedSubject = subjectId;
//            System.out.println("[선택한 과목입니다] : " + selectedSubject);
//
//            Map<String, List<Integer>> studentScores = scoreMap.getOrDefault(subjectId, new HashMap<>());
//
//            List<Integer> scores = studentScores.getOrDefault(selectedSubject, new ArrayList<>());
//
//            int round = scores.size() + 1;
//            System.out.println("["+round + "회차 점수를 입력해주세요.]");
//
//            int score = sc.nextInt();
//            scores.add(score); // 점수를 리스트에 추가
//
//            studentScores.put(selectedSubject, scores);
//            scoreMap.put(subjectId, studentScores);
//
//            System.out.println("\n===[점수 등록 성공!]===");
//            System.out.println("===[다른 과목 점수를 등록하시겠습니까? (yes / no)]===");
//            String againScore = sc.next();
//            if (!againScore.equals("yes")) {
//                againSc = false;
//            }
//        }

    }

//    //1. 생성자로 LIST값을 외부에서 받기
//    public CampManagementApplication(List<Student> studentStore, List<Subject> subjectStore, List<Score> scoreStore){
//        this.studentStore = studentStore;
//        this.subjectStore = subjectStore;
//        this.scoreStore = scoreStore;
//    }


    //3. 헬퍼메서드 -> 기능구현에 활용할 함수
    private static Student findStudentById(String studentId){ //ID로학생찾는매서드
        return studentStore.stream() //stream : 컬렉션의 데이터를 하나씩 처리하는 도구
                .filter(student -> student.getStudentId().equals(studentId))//getstudentId==studentId인지 비교
                .findFirst() //걸러진 스트림에서 first요소를 find하는 것. (따라서 조건에 맞는 첫 번째 학생 객체를 반환)
                .orElse(null); //조건에 맞는 학생 객체 없으면 null
    }

    private static Subject findSubjectByName(String subjectName){//과목명으로 과목 찾는 메서드
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectName().equals(subjectName))
                .findFirst()
                .orElse(null);

    }


    private static Score findScore(Student student, Subject subject, int round) {//수강생,과목,회차로 점수 찾는 매소드

        return scoreStore.stream()
                .filter(score -> score.getStudent().getStudentId().equals(student.getStudentId()) &&
                        score.getSubject().getSubjectId().equals(subject.getSubjectId()) &&
                        score.getRound() == round)
                .findFirst()
                .orElse(null);
    }


//    private static List<Score> findScoresByStudentAndSubject(Student student, Subject subject) {//수강생이랑 과목으로 점수 찾는 메소드
//        return scoreStore.stream()
//                .filter(score -> score.getStudent().getStudentId().equals(student.getStudentId()) &&
//                        score.getSubject().getSubjectId().equals(subject.getSubjectId()))//특정 수강생, 특정 과목만 남긴다.
//                .toList();
//    }


    // 수강생의 과목별 회차 점수 수정 -> 기능구현시작
    private static void updateRoundScoreBySubject() {
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
        List<Score> scores = findScoresByStudentAndSubject(studentId, subject.getSubjectId());
        if (scores.isEmpty()) {
            System.out.println("해당 과목에 대한 점수가 존재하지 않습니다.");
            return;
        }
        System.out.println("회차별 등급을 조회합니다...");
        for (Score score : scores) {
            System.out.println("회차: " + score.getRound() + ", 점수: " + score.getScore()+", 등급 : "+score.getGrade());

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
