package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private ArrayList<String> mandatorySubject;
    private ArrayList<String> choiceSubject;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // Setter
    public void setMandatorySubject(ArrayList<String> mandatorySubject) {
        this.mandatorySubject = mandatorySubject;
    }

    public void setChoiceSubject(ArrayList<String> choiceSubject) {
        this.choiceSubject = choiceSubject;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<String> getMandatorySubject() {
        return mandatorySubject;
    }
    public List<String> getChoiceSubject() {
        return choiceSubject;
    }
}
