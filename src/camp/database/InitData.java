package camp.database;

import camp.model.*;
import camp.service.ScoreService;
import camp.service.StudentService;
import camp.service.SubjectService;
import camp.view.CampManagementApplication;

import java.util.*;

public class InitData {

    // 상수로 정의된 필수 과목과 선택 과목의 타입
    public static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    public static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    public static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    public static final String INDEX_TYPE_SCORE = "SC";

    //데이터 저장소
    private static ScoreService scoreService;
    private static StudentService studentService;
    private static SubjectService subjectService;
    // 데이터 저장소
    private static StudentStore studentStore;
    private static SubjectStore subjectStore;
    private static ScoreStore scoreStore;
    private static SubjectTakenStore subjectTakenStore;

    //초기화 메서드
    public static void initialize() {
        setInitData();
    }
   //초기 데이터를 설정하는 메서드
    private static void setInitData() {
        //학생 저장소 초기화
        studentStore = new StudentStore(new ArrayList<>());
        //과목 리스트 초기화 및 과목 저장소 설정
        List<Subject> subjects = List.of(
                new Subject(sequence(INDEX_TYPE_SUBJECT), "Java", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "객체지향", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "JPA", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "MySQL", SUBJECT_TYPE_MANDATORY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "디자인 패턴", SUBJECT_TYPE_CHOICE),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring Security", SUBJECT_TYPE_CHOICE),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "Redis", SUBJECT_TYPE_CHOICE),
                new Subject(sequence(INDEX_TYPE_SUBJECT), "MongoDB", SUBJECT_TYPE_CHOICE)
        );
        subjectStore = new SubjectStore(subjects);
        //점수 저장소 초기화
        scoreStore = new ScoreStore(new ArrayList<>());
        //수강한 과목 저장소 초기화
        subjectTakenStore = new SubjectTakenStore(new HashMap<>());

        //서비스 초기화
        subjectService = new SubjectService(subjectStore, subjectTakenStore);
        studentService = new StudentService(studentStore, subjectService);
        scoreService = new ScoreService(scoreStore, studentStore, subjectTakenStore, subjectStore);

        CampManagementApplication campManagementApplication = new CampManagementApplication(scoreService, studentService, subjectService);
    }

    //인덱스 값을 생성하는 메서드
    public static String sequence(String type) {
        //Index 타입에 따라 Index를 증가시키고 해당 Index 값을 반환
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

}
