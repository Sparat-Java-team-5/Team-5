package camp.database;

import java.util.ArrayList;
import java.util.Map;

//학생이 수강한 과목을 저장하는 저장소
public class SubjectTakenStore {
    //key : 학생 고유 ID , Value : 학생이 수강한 과목 배열
    private static Map<String, ArrayList> subjectTakenStore;
}
