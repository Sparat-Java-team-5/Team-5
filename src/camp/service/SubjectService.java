package camp.service;

import camp.database.SubjectStore;
import camp.database.SubjectTakenStore;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SubjectService {
    private static final Scanner sc = new Scanner(System.in);
    private final SubjectStore subjectStore;
    private final SubjectTakenStore subjectTakenStore;

    public SubjectService(SubjectStore subjectStore, SubjectTakenStore subjectTakenStore) {
        this.subjectStore = subjectStore;
        this.subjectTakenStore = subjectTakenStore;
    }

    public void registerSubjects(Student student) {
        ArrayList<String> mandatorySubjectTaken = new ArrayList<>();

        System.out.println("============[ 필수과목 수강신청 ]============");
        System.out.println("===[수강하실 필수과목 3개 이상 입력해 주세요!]===");
        System.out.println("[과목 코드] [과목 명]");
        // 필수과목 코드와 과목 명 출력
        for (Subject subject : subjectStore.getSubjectStore()) {
            if (subject.getSubjectType().equals("MANDATORY")) {
                System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName());
            }
        }
        // 필수과목 수강 종료 입력 받기 위한 변수
        boolean mandatory = true;
        // 필수 과목 수강 신청
        while (mandatory && mandatorySubjectTaken.size() < 5) {
            System.out.print("[과목 코드 입력] : ");
            String subjectId = sc.next();

            // 입력한 과목코드가 존재하는지 확인하는 변수
            boolean isContain = false;

            for (Subject subject : subjectStore.getSubjectStore()) {
                // 과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals("MANDATORY")) {
                    if (!mandatorySubjectTaken.contains(subjectId)) { // 중복 등록 방지
                        mandatorySubjectTaken.add(subjectId); // 필수 과목 리스트에 추가
                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
                        System.out.println(mandatorySubjectTaken);
                        isContain = true;
                    } else {
                        System.out.println("[" + subject.getSubjectName() + " 과목은 이미 등록되었습니다.]");
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
                        System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + mandatorySubjectTaken.size() + "과목이 등록되었습니다]===");
                        System.out.println("====[선택 과목으로 이동합니다.]====");
                        mandatory = false;
                        break;
                    } else if (exit.equals("no")) {
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
        if (mandatorySubjectTaken.size() == 5) {
            System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + mandatorySubjectTaken.size() + "과목이 등록되었습니다]===");
            System.out.println("필수과목은 최대 5개까지 수강 가능합니다.\n" +
                    "이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.\n");
        }
        System.out.println("필수 과목 수강 신청을 종료 합니다.");

        System.out.println("============[ 선택과목 수강신청 ]============");
        System.out.println("==[수강하실 선택과목 2개 이상 입력해 주세요!]==");
        System.out.println("[과목 코드] 과목 명");
        // 선택과목 코드와 과목 명 출력
        for (Subject subject : subjectStore.getSubjectStore()) {
            if (subject.getSubjectType().equals("CHOICE")) {
                System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName());
            }
        }

        ArrayList<String> choiceSubjectTaken = new ArrayList<>();
        // 선택과목 수강 종료 입력 받기 위한 변수
        boolean choice = true;
        // 선택 과목 수강 신청
        while (choice && choiceSubjectTaken.size() < 4) {
            System.out.print("[과목 코드 입력] : ");
            String subjectId = sc.next();

            // 입력한 과목코드가 존재하는지 확인하는 변수
            boolean isContain = false;

            for (Subject subject : subjectStore.getSubjectStore()) {
                // 과목 저장소에 있는 과목코드 이고, 수강한 과목과 중복되지 않는 과목코드만 수강 가능
                if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals("CHOICE")) {
                    if (!choiceSubjectTaken.contains(subjectId)) { // 중복 등록 방지
                        choiceSubjectTaken.add(subjectId); // 선택 과목 리스트에 추가
                        System.out.println(subject.getSubjectName() + " 등록이 완료되었습니다.");
                        System.out.println(choiceSubjectTaken);
                        isContain = true;
                    } else {
                        System.out.println("[" + subject.getSubjectName() + " 과목은 이미 등록되었습니다.]");
                    }
                }
            }
            if (!isContain) {
                System.out.println("[잘못된 입력입니다.]");
            }

            if (2 <= choiceSubjectTaken.size() && choiceSubjectTaken.size() < 4) {
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
        if (choiceSubjectTaken.size() == 4) {
            System.out.println("===[현재 등록된 선택 과목 리스트입니다. " + choiceSubjectTaken.size() + "과목이 등록되었습니다]===");
            System.out.println("선택과목은 최대 4개까지 수강 가능합니다.\n" +
                    "이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.\n");
        }
        System.out.println("선택 과목 수강 신청을 종료 합니다.\n");

        mandatorySubjectTaken.addAll(choiceSubjectTaken);
        // (Key : 학생 고유 ID, Value : 학생이 수강한 과목 ArrayList) 형식으로 Map 자료형에 추가
        subjectTakenStore.setSubjectTakenStore(student.getStudentId(), mandatorySubjectTaken);

        // 확인용 출력
        System.out.println("===== 수강생이 신청한 과목 확인 출력 =====");
        for (Map.Entry<String, ArrayList<String>> entrySet : subjectTakenStore.getSubjectTakenStore().entrySet()) {
            System.out.println("[ " + entrySet.getKey() + " : " + entrySet.getValue() + " ]");
        }

        // 성공
        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");
    }
}


//package camp.service;
//
//import camp.database.SubjectStore;
//import camp.database.SubjectTakenStore;
//import camp.model.Student;
//import camp.model.Subject;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class SubjectService {
//    private static final Scanner sc = new Scanner(System.in);
//    private final SubjectStore subjectStore;
//    private final SubjectTakenStore subjectTakenStore;
//
//    public SubjectService(SubjectStore subjectStore, SubjectTakenStore subjectTakenStore) {
//        this.subjectStore = subjectStore;
//        this.subjectTakenStore = subjectTakenStore;
//    }
//
//    public void registerSubjects(Student student) {
//        System.out.println("\n수강 과목을 등록합니다...");
//
//        // 필수 과목 선택
//        System.out.println("[필수 수강 과목 목록]");
//        List<Subject> mandatorySubjects = subjectStore.getSubjectStore().stream()
//                .filter(subject -> subject.getSubjectType().equals("MANDATORY"))
//                .toList();
//        for (int i = 0; i < mandatorySubjects.size(); i++) {
//            System.out.println((i + 1) + ". " + mandatorySubjects.get(i).getSubjectName());
//        }
//        System.out.println("필수 과목을 선택하세요 (최대 " + mandatorySubjects.size() + "개): ");
//        List<String> selectedMandatorySubjects = new ArrayList<>();
//        for (int i = 0; i < mandatorySubjects.size(); i++) {
//            System.out.print((i + 1) + "번째 필수 과목 번호: ");
//            String subjectIndex = sc.next();
//            if (isValidNumber(subjectIndex, 1, mandatorySubjects.size())) {
//                selectedMandatorySubjects.add(mandatorySubjects.get(Integer.parseInt(subjectIndex) - 1).getSubjectId());
//            } else {
//                System.out.println("올바른 과목 번호를 입력하세요.");
//                i--; // 다시 입력받기 위해 인덱스를 감소
//            }
//        }
//        subjectTakenStore.setSubjectTakenStore(student.getStudentId(), new ArrayList<>(selectedMandatorySubjects));
//        System.out.println("필수 과목이 등록되었습니다.\n");
//
//        // 선택 과목 선택
//        System.out.println("[선택 과목 목록]");
//        List<Subject> choiceSubjects = subjectStore.getSubjectStore().stream()
//                .filter(subject -> subject.getSubjectType().equals("CHOICE"))
//                .toList();
//        for (int i = 0; i < choiceSubjects.size(); i++) {
//            System.out.println((i + 1) + ". " + choiceSubjects.get(i).getSubjectName());
//        }
//        System.out.println("수강할 선택 과목명을 입력하세요 (최대 2개): ");
//        int choiceCount = 0;
//        while (choiceCount < 2) {
//            System.out.print((choiceCount + 1) + "번째 선택 과목 번호: ");
//            String subjectIndex = sc.next();
//            if (isValidNumber(subjectIndex, 1, choiceSubjects.size())) {
//                subjectTakenStore.getSubjectsTaken(student.getStudentId()).add(choiceSubjects.get(Integer.parseInt(subjectIndex) - 1).getSubjectId());
//                choiceCount++;
//            } else {
//                System.out.println("올바른 과목 번호를 입력하세요.");
//            }
//        }
//
//        System.out.println("선택 과목 등록이 완료되었습니다.");
//    }
//
//    private boolean isValidNumber(String input, int min, int max) {
//        try {
//            int number = Integer.parseInt(input);
//            return number >= min && number <= max;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    private Subject findSubjectByName(String subjectName) {
//        for (Subject subject : subjectStore.getSubjectStore()) {
//            if (subject.getSubjectName().equals(subjectName)) {
//                return subject;
//            }
//        }
//        return null;
//    }
//}