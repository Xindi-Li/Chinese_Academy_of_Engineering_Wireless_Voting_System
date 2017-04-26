package com.lixindi.gradproject.dto;

import com.lixindi.gradproject.vo.Academician;

import java.util.List;

/**
 * Created by JamesLee on 2017/4/24.
 */
public class AcademicianResponse {
    private List<Academician> academician;
    private int total;

    public List<Academician> getAcademician() {
        return academician;
    }

    public void setAcademician(List<Academician> academician) {
        this.academician = academician;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
