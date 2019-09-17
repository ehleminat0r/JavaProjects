/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRS;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author lhassler
 */
public class Job {
    private GregorianCalendar mDate;
    private Dog mDog;
    private String mRoom;

    /**
     * @return the mDate
     */
    public GregorianCalendar getDate() {
        return mDate;
    }

    /**
     * @param mDate the mDate to set
     */
    public void setDate(GregorianCalendar mDate) {
        this.mDate = mDate;
    }

    /**
     * @return the mDog
     */
    public Dog getDog() {
        return mDog;
    }

    /**
     * @param mDog the mDog to set
     */
    public void setDog(Dog mDog) {
        this.mDog = mDog;
    }

    /**
     * @return the mRoom
     */
    public String getRoom() {
        return mRoom;
    }

    /**
     * @param mRoom the mRoom to set
     */
    public void setRoom(String mRoom) {
        this.mRoom = mRoom;
    }

    // Constructor
    public Job(GregorianCalendar mDate, Dog mDog, String mRoom) {
        this.mDate = mDate;
        this.mDog = mDog;
        this.mRoom = mRoom;
    }

    

    
}
