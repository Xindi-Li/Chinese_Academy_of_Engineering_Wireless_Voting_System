package com.lixindi.gradproject.vo;

import java.util.List;

/**
 * Created by lixindi on 2017/3/14.
 */
public class GroupRequest {
    private List<String> names;
    private String department;
    private String group;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
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
