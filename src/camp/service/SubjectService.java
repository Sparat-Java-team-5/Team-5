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
    //과목 저장소
    private final SubjectStore subjectStore;
    //수강한 과목 저장소
    private final SubjectTakenStore subjectTakenStore;
    //생성자 : 과목 서비스 초기화
    public SubjectService(SubjectStore subjectStore, SubjectTakenStore subjectTakenStore) {
        this.subjectStore = subjectStore; //과목 저장소 초기화
        this.subjectTakenStore = subjectTakenStore; //수강한 과목 저장소 초기화
    }
    //학생에게 과목을 등록하는 메서드
    public void registerSubjects(Student student) {
        ArrayList<String> mandatorySubjectTaken = new ArrayList<>(); //필수 과목 리스트 초기화

        System.out.println("============[ 필수과목 수강신청 ]============");
        System.out.println("===[수강하실 필수과목 3개 이상 입력해 주세요!]===");
        System.out.println("[과목 코드] [과목 명]");
        // 필수과목 코드와 과목 명 출력
        for (Subject subject : subjectStore.getSubjectStore()) {
            if (subject.getSubjectType().equals("MANDATORY")) { //필수 과목인지 확인
                System.out.println("[" + subject.getSubjectId() + "] " + " [" + subject.getSubjectName()+"]");
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
                        System.out.println("===["+subject.getSubjectName() + " 등록이 완료되었습니다.]===");
                        System.out.println("["+mandatorySubjectTaken+"]");
                        isContain = true;
                    } else {
                        System.out.println("===[" + subject.getSubjectName() + " 과목은 이미 등록되었습니다.]===");
                    }
                }
            }
            if (!isContain) {
                System.out.println("===[잘못된 입력입니다.]===");
            }
            // 필수 과목이 3개 이상일 때
            if (3 <= mandatorySubjectTaken.size() && mandatorySubjectTaken.size() < 5) {
                while (true) { // 유효한 입력을 받을 때까지 반복
                    System.out.println("[==================================]");
                    System.out.println("===[필수 과목은 3개 이상만 수강하시면 됩니다]===.\n" +
                            "===[필수 과목 수강을 종료하시겠습니까? (yes / no )]===");
                    String exit = sc.next();
                    if (exit.equals("yes")) {
                        System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + mandatorySubjectTaken.size() + "과목이 등록되었습니다]===");
                        System.out.println("====[선택 과목으로 이동합니다.]====");
                        mandatory = false; //필수 과목 등록 종료
                        break; //반복 종료
                    } else if (exit.equals("no")) {
                        System.out.println("====[신청할 필수 과목 번호를 입력해주세요.]====");
                        System.out.println(mandatorySubjectTaken); //등록된 과목 리스트 출력
                        break;
                    } else {
                        System.out.println("===[잘못입력하였습니다.]===");
                    }
                }
            }
        }

        System.out.println("***********************************");
        if (mandatorySubjectTaken.size() == 5) { //필수 과목이 5개일때
            System.out.println("===[현재 등록된 필수 과목 리스트입니다. " + mandatorySubjectTaken.size() + "과목이 등록되었습니다]===");
            System.out.println("===[필수과목은 최대 5개까지 수강 가능합니다.]===\n" +
                    "===[이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.]===\n");
        }
        System.out.println("===[필수 과목 수강 신청을 종료 합니다.]===");

        System.out.println("============[ 선택과목 수강신청 ]============");
        System.out.println("===수강하실 선택과목 2개 이상 입력해 주세요!]===");
        System.out.println("[과목 코드] [과목 명]");
        // 선택과목 코드와 과목 명 출력
        for (Subject subject : subjectStore.getSubjectStore()) {
            if (subject.getSubjectType().equals("CHOICE")) {
                System.out.println("[" + subject.getSubjectId() + "] " + " [" + subject.getSubjectName()+"]");
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
                        System.out.println("===["+subject.getSubjectName() + " 등록이 완료되었습니다.]===");
                        System.out.println(choiceSubjectTaken); //등록된 선ㅌ택 과목 리스트 출력
                        isContain = true;
                    } else {
                        System.out.println("===[" + subject.getSubjectName() + " 과목은 이미 등록되었습니다.]===");
                    }
                }
            }
            if (!isContain) {
                System.out.println("===[잘못된 입력입니다.]===");
            }
            //선택 과목이 2개 이상일때
            if (2 <= choiceSubjectTaken.size() && choiceSubjectTaken.size() < 4) {
                while (true) { //유효한 입력을 받을 때까지
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
        if (choiceSubjectTaken.size() == 4) { //선택과목이 4개일때
            System.out.println("===[현재 등록된 선택 과목 리스트입니다. " + choiceSubjectTaken.size() + "과목이 등록되었습니다]===");
            System.out.println("===[선택과목은 최대 4개까지 수강 가능합니다.]===\n" +
                    "===[이미 최대 수강신청 과목 수만큼 수강이 완료되셨습니다.]===\n");
        }
        System.out.println("===[선택 과목 수강 신청을 종료 합니다.]===\n");

        mandatorySubjectTaken.addAll(choiceSubjectTaken); // 필수 과목과 선택 과목 리스트 합치기
        // (Key : 학생 고유 ID, Value : 학생이 수강한 과목 ArrayList) 형식으로 Map 자료형에 추가
        subjectTakenStore.setSubjectTakenStore(student.getStudentId(), mandatorySubjectTaken);

        // 확인용 출력
        System.out.println("===== 수강생이 신청한 과목 확인 출력 =====");
        for (Map.Entry<String, ArrayList<String>> entrySet : subjectTakenStore.getSubjectTakenStore().entrySet()) {
            System.out.println("===[ " + entrySet.getKey() + " : " + entrySet.getValue() + " ]===");
        }

        // 성공
        System.out.println("****** 수강생 등록 및 과목 등록 성공! *****\n");
    }
}
