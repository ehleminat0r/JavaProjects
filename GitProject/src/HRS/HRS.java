/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRS;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lhassler
 */
public class HRS {
    private List<Job> mJobs = new ArrayList<Job>();
    private List<Dog> mDogs = new ArrayList<Dog>();

    /**
     * @return the mJobs
     */
    public List<Job> getJobs() {
        return mJobs;
    }

    /**
     * @param mJobs the mJobs to set
     */
    public void setJobs(List<Job> mJobs) {
        this.mJobs = mJobs;
    }

    /**
     * @return the mDogs
     */
    public List<Dog> getmDogs() {
        return mDogs;
    }

    /**
     * @param mDogs the mDogs to set
     */
    public void setmDogs(List<Dog> mDogs) {
        this.mDogs = mDogs;
    }
    
}
