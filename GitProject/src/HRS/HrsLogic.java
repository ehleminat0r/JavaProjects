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
public class HrsLogic {
    private List<Job> mJobs = new ArrayList<Job>();
    private List<Dog> mDogs = new ArrayList<Dog>();
    private List<Owner> mOwner = new ArrayList<Owner>();
    private List<Room> mRoom = new ArrayList<Room>();

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

    /**
     * @return the mOwner
     */
    public List<Owner> getmOwner() {
        return mOwner;
    }

    /**
     * @param mOwner the mOwner to set
     */
    public void setmOwner(List<Owner> mOwner) {
        this.mOwner = mOwner;
    }

    /**
     * @return the mRoom
     */
    public List<Room> getmRoom() {
        return mRoom;
    }

    /**
     * @param mRoom the mRoom to set
     */
    public void setmRoom(List<Room> mRoom) {
        this.mRoom = mRoom;
    }
    
}
