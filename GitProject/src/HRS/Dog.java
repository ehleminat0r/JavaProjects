/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRS;

import java.util.Date;

/**
 *
 * @author lhassler
 */
public class Dog {
    private String mName;
    private Date mAge;
    private String mRace;
    private Owner mOwner;

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the mAge
     */
    public Date getAge() {
        return mAge;
    }

    /**
     * @param mAge the mAge to set
     */
    public void setAge(Date mAge) {
        this.mAge = mAge;
    }

    /**
     * @return the mRace
     */
    public String getRace() {
        return mRace;
    }

    /**
     * @param mRace the mRace to set
     */
    public void setRace(String mRace) {
        this.mRace = mRace;
    }

    /**
     * @return the mOwner
     */
    public Owner getOwner() {
        return mOwner;
    }

    /**
     * @param mOwner the mOwner to set
     */
    public void setOwner(Owner mOwner) {
        this.mOwner = mOwner;
    }

    public Dog() {
    }

    public Dog(String mName, Date mAge, String mRace) {
        this.mName = mName;
        this.mAge = mAge;
        this.mRace = mRace;
    }
    
    
    
    
}
