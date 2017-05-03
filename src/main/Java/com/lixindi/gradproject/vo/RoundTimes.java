package com.lixindi.gradproject.vo;

import java.io.Serializable;

/**
 * Created by JamesLee on 2017/4/30.
 */
public class RoundTimes implements Serializable {
    private static final long serialVersionUID = -4745261534783940655L;

    private int round;

    private int times;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
