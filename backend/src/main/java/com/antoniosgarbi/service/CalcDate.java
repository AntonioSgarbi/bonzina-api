package com.antoniosgarbi.service;

import java.time.LocalDate;
import java.time.Year;

public class CalcDate {

    private final LocalDate date;

    private final int dayOfWeek;
    private final int dayOfMonth;
    private int month;
    private int year;

    private int daysPassedSinceWeekStarted;
    private int daysLeftToEndWeek;

    private final int daysLeftToTheEndOfMonth;
    private final int lastDayOfMonth;

    private final int dayWeekStarts;
    private final int dayWeekEnds;

    private int monthWeekStarts;
    private int monthWeekEnds;

    private int yearWeekStarts;
    private int yearWeekEnds;

    private final LocalDate dateWeekStarts;
    private final LocalDate dateWeekEnds;

    public CalcDate(LocalDate date) {

        this.date = date;

        month = date.getMonth().getValue();
        year = date.getYear();
        dayOfWeek = date.getDayOfWeek().getValue();
        dayOfMonth = date.getDayOfMonth();
        lastDayOfMonth = returnLastDayOfMonth(month, year);

        daysLeftToTheEndOfMonth = lastDayOfMonth - dayOfMonth;

        setDaysPassedFromMondayAndLeftToSaturday(dayOfWeek);

        monthWeekStarts = month;
        monthWeekEnds = month;
        yearWeekStarts = year;
        yearWeekEnds = year;

        if(daysLeftToTheEndOfMonth == 0) {
            dayWeekStarts = dayOfMonth - daysPassedSinceWeekStarted;
            dayWeekEnds = daysLeftToEndWeek;
            monthWeekEnds = month++;
            if(month == 12) {
                yearWeekEnds = year++;
            }
        }
        else if(dayOfMonth < daysPassedSinceWeekStarted) {
            if(month != 1) {
                monthWeekStarts = month - 1;
            } else{
                monthWeekStarts = 12;
                yearWeekStarts = year--;
            }
            dayWeekStarts = returnLastDayOfMonth(monthWeekStarts, year) - (daysPassedSinceWeekStarted - dayOfMonth);
            dayWeekEnds = dayOfMonth + daysLeftToEndWeek;
        }
        else if(daysLeftToTheEndOfMonth < daysLeftToEndWeek) {
            if(month == 12) {
                monthWeekEnds = 1;
                yearWeekEnds = year + 1;
            } else {
                monthWeekEnds = month + 1;
            }
            dayWeekStarts = dayOfMonth - daysPassedSinceWeekStarted;
            dayWeekEnds = daysLeftToEndWeek - (returnLastDayOfMonth(month, year) - dayOfMonth);
        }
        else { /*dayOfMonth >= daysPassedSinceWeekStarted && daysLeftToTheEndOfMonth >= daysLeftToEndWeek*/
            dayWeekStarts = dayOfMonth - daysPassedSinceWeekStarted;
            dayWeekEnds = dayOfMonth + daysLeftToEndWeek;
        }

        dateWeekStarts = LocalDate.of(yearWeekStarts, monthWeekStarts, dayWeekStarts);
        dateWeekEnds = LocalDate.of(yearWeekEnds, monthWeekEnds, dayWeekEnds);
    }

    private int returnLastDayOfMonth(int month, int year) {
        int lastDayOfMonth;
        if(month == 2) {
            if(!Year.isLeap(year)) {
                lastDayOfMonth = 28;
            } else lastDayOfMonth = 29;
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            lastDayOfMonth = 30;
        } else lastDayOfMonth = 31;
        return lastDayOfMonth;
    }

    private void setDaysPassedFromMondayAndLeftToSaturday(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1 : //DayOfWeek.ENUM starts at monday with 1
                daysPassedSinceWeekStarted = 1;
                daysLeftToEndWeek = 5;
                break;
            case 2 :
                daysPassedSinceWeekStarted = 2;
                daysLeftToEndWeek = 4;
                break;
            case 3 :
                daysPassedSinceWeekStarted = 3;
                daysLeftToEndWeek = 3;
                break;
            case 4 :
                daysPassedSinceWeekStarted = 4;
                daysLeftToEndWeek = 2;
                break;
            case 5 :
                daysPassedSinceWeekStarted = 5;
                daysLeftToEndWeek = 1;
                break;
            case 6 :
                daysPassedSinceWeekStarted = 6;
                daysLeftToEndWeek = 0;
                break;
            case 7 :
                daysPassedSinceWeekStarted = 0;
                daysLeftToEndWeek = 6;
                break;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDateWeekStarts() {
        return dateWeekStarts;
    }

    public LocalDate getDateWeekEnds() {
        return dateWeekEnds;
    }

    public int getLastDayOfMonth() {
        return lastDayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
