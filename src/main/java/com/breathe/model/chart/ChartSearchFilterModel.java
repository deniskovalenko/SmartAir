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

    public Date getStartDate(Date date) {
      //  endDate - offset
        DateTime endDate = new DateTime(this.getEndDate(date));
        DateTime startDate;
        switch (this.getMode()) {
            case 0 :
                startDate = endDate.minusHours(this.getCount());
                break;
            case 1 :
                startDate = endDate.minusDays(this.getCount());
                break;
            case 2 :
                startDate = endDate.minusWeeks(this.getCount());
                break;
            default:
                startDate = endDate;
        }
        return startDate.toDate();
    }

    public Date getEndDate(Date date) {
       //  date - skip*offset;
        DateTime currentDate = new DateTime(date);
        DateTime endDate;
        switch (this.getMode()) {
            case 0 :
                endDate = currentDate.minusHours(this.getCount() * this.getSkip());
                break;
            case 1 :
                endDate = currentDate.minusDays(this.getCount() * this.getSkip());
                break;
            case 2 :
                endDate = currentDate.minusWeeks(this.getCount() * this.getSkip());
                break;
            default:
                endDate = currentDate;
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
