package camp.service;
import camp.database.SubjectStore;
import camp.model.Subject;
import camp.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class SubjectService {
    private static final Scanner sc = new Scanner(System.in);
    private static Map<String, ArrayList<String>> subjectTakenStore;
    private static SubjectStore subjectStore;
    public void registerSubjects(Student student) {
        System.out.println("\n수강 과목을 등록합니다...");
        System.out.println("[필수 수강 과목 목록]");
        SubjectStore.getSubjectStore().stream()
                .filter(subject -> subject.getSubjectType().equals("MANDATORY"))
                .forEach(subject -> System.out.println(subject.getSubjectName()));
        System.out.println("필수 과목은 자동으로 등록됩니다.");
        List<String> mandatorySubjects = SubjectStore.getSubjectStore().stream()
                .filter(subject -> subject.getSubjectType().equals("MANDATORY"))
                .map(Subject::getSubjectId)
                .toList();
        subjectTakenStore.put(student.getStudentId(), new ArrayList<>(mandatorySubjects));
        System.out.println("필수 과목이 등록되었습니다.\n");

        System.out.println("[선택 과목 목록]");
        SubjectStore.getSubjectStore().stream()
                .filter(subject -> subject.getSubjectType().equals("CHOICE"))
                .forEach(subject -> System.out.println(subject.getSubjectName()));


        System.out.println("수강할 선택 과목명을 입력하세요 (최대 2개): ");
        int choiceCount = 0;
        while (choiceCount < 2) {
            System.out.print((choiceCount + 1) + "번째 선택 과목: ");
            String chosenSubjectName = sc.next();
            Subject chosenSubject = findSubjectByName(chosenSubjectName);

            if (chosenSubject != null && chosenSubject.getSubjectType().equals("CHOICE")) {
                subjectTakenStore.get(student.getStudentId()).add(chosenSubject.getSubjectId());
                choiceCount++;
            } else {
                System.out.println("올바른 선택 과목명을 입력하세요.");
            }
        }

        System.out.println("선택 과목 등록이 완료되었습니다.");
    }

    private Subject findSubjectByName(String subjectName) {
        for (Subject subject : SubjectStore.getSubjectStore()) {
            if (subject.getSubjectName().equals(subjectName)) {
                return subject;
            }
        }
        return null;
    }
}