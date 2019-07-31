/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 *
 * @author lhassler
 */
public class Calculator {
    // Private Members
    private JFrame mFrame = new JFrame("Maze");
    private int [][] mMaze;
    
    // Constructor

    public Calculator()
    {
        mFrame.setSize(500, 550);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setResizable(false);
        mFrame.setLocationByPlatform(true);
        mFrame.setVisible(true);
        mFrame.setLayout(null); 
    }

    /**
     * @return the mMaze
     */
    public int[][] getmMaze() {
        return mMaze;
    }

    /**
     * @param mMaze the mMaze to set
     */
    public void setmMaze(int[][] mMaze) {
        this.mMaze = mMaze;
    }
    
    // Main method
    public static void main(String[] args) throws IOException
    {
        Calculator maze = new Calculator();
        maze.setmMaze(maze.loadMaze());
        System.out.println(Arrays.deepToString(maze.getmMaze()));
    }
    
    public int [][] loadMaze() throws FileNotFoundException, IOException
    {
        String st = "maze.txt"; 
        int count = 0;
        File file = new File(st);
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        int [][] maze = new int[(int)Files.lines(Paths.get(st)).count()][];
        while ((st = br.readLine()) != null)
        {
            maze[count] = new int[st.length()];
            for (int i=0; i<st.length();i++)
            {
               maze[count][i] = Integer.parseInt(st.substring(i,i+1));
            }
            count++;
        }
        return maze;
    }
     
}
