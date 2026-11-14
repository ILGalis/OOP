package ru.nsu.fit.oop.ilg.recordbook.core;

import java.util.ArrayList;
import java.util.List;

public class RecordBook{
    private final List<Semester> semesters;
    private boolean isPaidEducation;
    private Grade diplomWorkGrade;

    public RecordBook(boolean isPaidEducation){
        this.semesters = new ArrayList<>();
        this.isPaidEducation = isPaidEducation;
        this.diplomWorkGrade = null;
    }

    public boolean isPaidEducation(){
        return isPaidEducation;
    }

    public void addSemester(Semester semester){
        semesters.add(semester);
    }

    public void setDiplomWorkGrade(Grade grade){
        this.diplomWorkGrade = grade;
    }

    public Semester getSemester(int number){
        for (Semester s : semesters) {
            if (s.getSemesterNumber()==number){
                return s;
            }
        }
        return null;
    }

    public double getAverageGrade(){
        double sum = 0;
        int count = 0;
        for (Semester s : semesters){
            for(Grade g : s.getGrades().values()){
                if(g.isCountsOnAverage()) {
                    sum += g.getValue();
                    count++;
                }
            }
        }
        return count == 0 ? 0.0 : sum/count;
    }

    public boolean canTransferToBudget(){
        if (!isPaidEducation){
            return false;
        }
        int size=semesters.size();
        if (size==0) return true;
        List<Semester> lastTwo=semesters.subList(Math.max(0,size-2),size);
        for (Semester s : lastTwo){
            for (var entry : s.getGrades().entrySet()) {
                if (entry.getKey().isExam() && entry.getValue()==Grade.ENOUGH) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canGetRedDiploma(){
        int totalGrades=0;
        int excellentCount=0;
        for (Semester s : semesters){
            for (var entry : s.getGrades().entrySet()){
                Grade g=entry.getValue();
                if (g!=Grade.DIFF_PASS && g!=Grade.DIFF_FAIL){
                    totalGrades++;
                    if (g==Grade.EXCELLENT){
                        excellentCount++;
                    }
                    if (g==Grade.ENOUGH){
                        return false;
                    }
                }
            }
        }
        if (totalGrades==0) return false;
        double excellentRatio=(double) excellentCount/totalGrades;
        if (excellentRatio<0.75){
            return false;
        }
        if (diplomWorkGrade!=null){
            return diplomWorkGrade==Grade.EXCELLENT;
        }
        return true;
    }

    public boolean canGetIncreasedScholarship(){
        if (semesters.isEmpty()) return false;
        Semester currentSemester=semesters.get(semesters.size()-1);
        for (Grade g : currentSemester.getGrades().values()){
            if (g==Grade.ENOUGH || g==Grade.BAD || g==Grade.DIFF_FAIL){
                return false;
            }
        }
        return true;
    }
}
