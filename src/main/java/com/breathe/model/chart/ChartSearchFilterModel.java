package com.breathe.model.chart;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by denis on 17.04.15.
 */
public class ChartSearchFilterModel {
    //search for count*"mode" period. Mode = {hour, day, week} => {0, 1, 2}
    private int count;
    //search period length - hour, day, week (0, 1, 2 respectively)
    private int mode;
    //how many count*mode time periods to skip. 0 - to get data for latest count*mode period
    private int skip;

    /**
     * @return time offset from LastDate point.
     */
    private DateTime getOffset() {
        DateTime offset = new DateTime();
        switch (this.getMode()) {
            case 0 :
                offset.plusHours(this.getCount());
                break;
            case 1 :
                offset.plusDays(this.getCount());
                break;
            case 2 :
                offset.plusWeeks(this.getCount());
                break;
            default:

        }
        return offset;
    }

    public Date getStartDate(Date date) {
      //  endDate - getOffset()
        DateTime startDate = new DateTime(this.getEndDate(date));
        switch (this.getMode()) {
            case 0 :
                startDate.minusHours(this.getCount());
                break;
            case 1 :
                startDate.minusDays(this.getCount());
                break;
            case 2 :
                startDate.minusWeeks(this.getCount());
                break;
            default:
        }
        return startDate.toDate();
    }

    public Date getEndDate(Date date) {
       //  date - skip*offset;
        DateTime endDate = new DateTime(date);
        switch (this.getMode()) {
            case 0 :
                endDate.minusHours(this.getCount() * this.getSkip());
                break;
            case 1 :
                endDate.minusDays(this.getCount() * this.getSkip());
                break;
            case 2 :
                endDate.minusWeeks(this.getCount() * this.getSkip());
                break;
            default:
        }
        return endDate.toDate();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
}
