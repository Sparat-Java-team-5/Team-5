package camp.database;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//최초 데이터 생성 시 초기화
public class InitData {
//    public List<Student> studentStore = new ArrayList<>();
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;
    private static Map<String, ArrayList> subjectTakenStore;

    public static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    public static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    public static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    public static final String INDEX_TYPE_SCORE = "SC";

    //setInitData()
    //sequence()
    public static void initialize() {
        setInitData();
    }


    private static void setInitData() {
        studentStore = new ArrayList<>();
        //StudentStore studentStore = new StudentStore(studentStore);
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

    public static String sequence(String type) {
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
    public static List<Student> getStudentStore() {
        return studentStore;
    }
    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }
    public static List<Score> getScoreStore() {
        return scoreStore;
    }
    public static Map<String, ArrayList> getSubjectTakenStore() {
        return subjectTakenStore;
    }
}



