package com.antoniosgarbi.dto;

public class SchedulingCounter {

    private Long fromDay;
    private Long fromWeek;
    private Long fromMonth;

    public SchedulingCounter(){
    }

    public SchedulingCounter(Long fromDay, Long fromWeek, Long fromMonth){
        this.fromDay = fromDay;
        this.fromWeek = fromWeek;
        this.fromMonth = fromMonth;
    }

    public Long getFromDay() {
        return fromDay;
    }

    public Long getFromWeek() {
        return fromWeek;
    }

    public Long getFromMonth() {
        return fromMonth;
    }
}
