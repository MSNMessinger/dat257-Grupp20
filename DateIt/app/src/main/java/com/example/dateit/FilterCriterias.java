package com.example.dateit;

public class FilterCriterias {
    private int isSummerJob;
    private int isEmployment;
    private int isMasterThesis;
    private int isIT;
    private int isE;
    private int isD;

    public FilterCriterias(){
        isSummerJob = 0;
        isEmployment = 0;
        isMasterThesis = 0;
        isIT = 0;
        isE = 0;
        isD = 0;
    }

    public int getIsSummerJob() {
        return isSummerJob;
    }

    public void setIsSummerJob(int isSummerJob) {
        this.isSummerJob = isSummerJob;
    }

    public int getIsEmployment() {
        return isEmployment;
    }

    public void setIsEmployment(int isEmployment) {
        this.isEmployment = isEmployment;
    }

    public int getIsMasterThesis() {
        return isMasterThesis;
    }

    public void setIsMasterThesis(int isMasterThesis) {
        this.isMasterThesis = isMasterThesis;
    }

    public int getIsIT() {
        return isIT;
    }

    public void setIsIT(int isIT) {
        this.isIT = isIT;
    }

    public int getIsE() {
        return isE;
    }

    public void setIsE(int isE) {
        this.isE = isE;
    }

    public int getIsD() {
        return isD;
    }

    public void setIsD(int isD) {
        this.isD = isD;
    }
}
