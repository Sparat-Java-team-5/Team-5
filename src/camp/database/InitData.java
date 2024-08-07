package camp.database;

import camp.model.*;
import java.util.*;

//최초 데이터 생성 시 초기화
public class InitData {

    public static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    public static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    public static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    public static final String INDEX_TYPE_SCORE = "SC";


    public static void initialize() {
        setInitData();
    }


    private static void setInitData() {
//<<<<<<< Feat/Sumin
        StudentStore studentStore = new StudentStore(new ArrayList<>());
        List<Subject> subjects = List.of(
//=======
        ArrayList<Subject> Subject = new ArrayList<>();
        StudentStore studentstore = new StudentStore();
        subjectStore = List.of(
//>>>>>>> release1-3
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
        SubjectStore subjectStore = new SubjectStore(subjects);
        ScoreStore scoreStore = new ScoreStore(new ArrayList<>());
        SubjectTakenStore subjectTakenStore = new SubjectTakenStore(new HashMap<>()); //수강한 과목 저장 데이터베이스 자료형 생성
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
}



