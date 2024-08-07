package camp.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectTakenStore {
    private static Map<String, ArrayList<String>> subjectTakenStore;

    public SubjectTakenStore(Map<String, ArrayList<String>> subjectTakenStore) {
        this.subjectTakenStore = subjectTakenStore;
    }

    public void setSubjectTakenStore(String studentId, ArrayList<String> subjects) {
        this.subjectTakenStore.put(studentId, subjects);
    }

    public Map<String, ArrayList<String>> getSubjectTakenStore() {
        return this.subjectTakenStore;
    }

    public ArrayList<String> getSubjectsTaken(String studentId) {
        return subjectTakenStore.get(studentId);
    }
}
