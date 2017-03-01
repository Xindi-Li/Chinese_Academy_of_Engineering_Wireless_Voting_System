package com.lixindi.gradproject.vo;

/**
 * Created by lixindi on 2017/2/24.
 */
public class CandidateInfo {
    private String candidateNum;
    private String name;
    private Integer age;
    private String major;
    private String company;
    private String department;
    private String group;

    public String getCandidateNum() {
        return candidateNum;
    }

    public void setCandidateNum(String candidateNum) {
        this.candidateNum = candidateNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
