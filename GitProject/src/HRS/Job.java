/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRS;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author lhassler
 */
public class Job {
    private Calendar mDate;
    private Dog mDog;
    private Room mRoom;

    /**
     * @return the mDate
     */
    public Calendar getDate() {
        return mDate;
    }

    /**
     * @param mDate the mDate to set
     */
    public void setDate(Calendar mDate) {
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
    public Room getRoom() {
        return mRoom;
    }

    /**
     * @param mRoom the mRoom to set
     */
    public void setRoom(Room mRoom) {
        this.mRoom = mRoom;
    }

    // Constructor
    public Job(Calendar mDate, Dog mDog, Room mRoom) {
        this.mDate = mDate;
        this.mDog = mDog;
        this.mRoom = mRoom;
    }

    

    
}
