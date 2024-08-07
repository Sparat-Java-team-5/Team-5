package camp.service;

import camp.database.ScoreStore;
import camp.model.Student;
import camp.model.Subject;
import camp.model.Score; //
import camp.database.InitData; //



import java.util.List;

public class ScoreService {//점수관련 서비스들 집합


    //createScore()

    //getGrade()
    public String getGrade(String subjectId) {
        List<Subject> subjectStore = campManagementApplication.getSubjectStore();
        String subjectType="";
        for (Subject subject : subjectStore){
            if(subject.getSubjectId().equals(subjectId)){
                subjectType = subject.getSubjectType();
                break;
            }
        }
        if (subjectType.equals("MANDATORY")) { // 필수 과목 등급 기준
            if (score >= 95) {
                return "A";
            } else if (score >= 90) {
                return "B";
            } else if (score >= 80) {
                return "C";
            } else if (score >= 70) {
                return "D";
            } else if (score >= 60) {
                return "E";
            } else {
                return "F";
            }
        } else if (subjectType.equals("CHOICE")) { // 선택 과목 등급 기준
            if (score >= 90) {
                return "A";
            } else if (score >= 80) {
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else if (score >= 50) {
                return "E";
            } else {
                return "F";
            }
        } else {
            return "F"; // 유효하지 않은 과목 유형
        }
    }

    //updateRoundScoreBySubject()

    //inquireRoundGradeBySubject()

    //getStudentId()

    //findStudentById()


    //findSubjectByName()

    //findSubjectNameById()

    //findScore()

    //findScoresByStudentAndSubject()


}
