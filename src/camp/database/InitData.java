package camp.database;

import camp.model.*;

import java.util.*;

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

    private static StudentStore studentStore;
    private static SubjectStore subjectStore;
    private static ScoreStore scoreStore;
    private static SubjectTakenStore subjectTakenStore;

    public static void initialize() {
        setInitData();
    }

    private static void setInitData() {
        studentStore = new StudentStore(new ArrayList<>());
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
        scoreStore = new ScoreStore(new ArrayList<>());
        subjectTakenStore = new SubjectTakenStore(new HashMap<>());
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

    public static StudentStore getStudentStore() {
        return studentStore;
    }

    public static SubjectStore getSubjectStore() {
        return subjectStore;
    }

    public static ScoreStore getScoreStore() {
        return scoreStore;
    }

    public static SubjectTakenStore getSubjectTakenStore() {
        return subjectTakenStore;
    }
}
