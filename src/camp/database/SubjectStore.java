package camp.database;

import camp.model.Subject;

import java.util.List;

public class SubjectStore {
    private static List<Subject> subjectStore;

    public SubjectStore(List<Subject> subjectStore) {
        this.subjectStore = subjectStore;
    }

    public List<Subject> getSubjectStore() {
        return this.subjectStore;
    }
}
