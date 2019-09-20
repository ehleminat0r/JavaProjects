/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRS;

/**
 *
 * @author lhassler
 */
public class Room {
    private String mName;
    private int mSize;

    /**
     * @return the mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the mSize
     */
    public int getmSize() {
        return mSize;
    }

    /**
     * @param mSize the mSize to set
     */
    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public Room(String mName, int mSize) {
        this.mName = mName;
        this.mSize = mSize;
    }
    
    
    
}
