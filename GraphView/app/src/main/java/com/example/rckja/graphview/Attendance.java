package com.example.rckja.graphview;

import java.util.Date;

/**
 * Created by rckja on 24-02-2018.
 */
//Class to store date and Student Count
public class Attendance implements Comparable<Attendance> {

    public Date mDate;
    public int mAttendanceCount;

    Attendance(Date date, int count){
        this.mDate =date;
        this.mAttendanceCount = count;
    }
    public Date getmDate() {
        return mDate;
    }
    @Override
    public int compareTo(Attendance objAttendance) {
        /* For Ascending order*/
        return getmDate().compareTo(objAttendance.getmDate());

    }
    public void setmAttendanceCount(int count){
        this.mAttendanceCount =count;
    }


}
