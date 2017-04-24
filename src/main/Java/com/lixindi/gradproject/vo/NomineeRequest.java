package com.lixindi.gradproject.vo;

import java.util.List;

/**
 * Created by JamesLee on 2017/4/19.
 */
public class NomineeRequest {
    private String department;
    private List<String> groups;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
