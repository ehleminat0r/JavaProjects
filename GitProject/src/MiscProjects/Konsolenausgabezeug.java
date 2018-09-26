/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscProjects;


import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author lhassler
 */
public class Konsolenausgabezeug {
    // Variablen
    public static int[] zahlen = new int[20];
    
    // Main Methode
    public static void main(String[] args) {
        Scanner eingabe = new Scanner(System.in);
        Random zufall = new Random();
        
        
        for (int i=0;i<20;i++)
            zahlen[i] = zufall.nextInt(100);
               
        //ZahlenAusgeben();
        
        int save;
        int tauschAnzahl=0;
        for (int i=0;i<zahlen.length-1;i++)
            for (int j=i+1;j<zahlen.length;j++)
                if(zahlen[i]>zahlen[j]) {
                    tauschAnzahl++;
                    for (int ersteZahl = 0; ersteZahl <i; ersteZahl++)
                        System.out.print("   ");
                    System.out.print("↓");
                    for (int zweiteZahl = 0; zweiteZahl <j-i; zweiteZahl++)
                        System.out.print("¯¯¯");             
                    System.out.println("↓");
                    ZahlenAusgeben();
                    System.out.println("");
                    save = zahlen[i];
                    zahlen[i] = zahlen[j];
                    zahlen[j] = save;
                }
        
        System.out.println("Insgesamt "+tauschAnzahl+" mal wurden Zahlen getauscht");
        System.out.print("Sortierte Liste: ");
        ZahlenAusgeben();

        // Fakultät    
        System.out.println("Fakultaet von 5: "+Fakultaet(5));
        
        int zahlenArray[][] = { { 8,  2, 22, 97, 38, 15,  0, 40,  0, 75,  4,  5,  7, 78, 52, 12, 50, 77, 91,  8},
                                {49, 49, 99, 40, 17, 81, 18, 57, 60, 87, 17, 40, 98, 43, 69, 48, 04, 56, 62,  0},
                                {81, 49, 31, 73, 55, 79, 14, 29, 93, 71, 40, 67, 53, 88, 30,  3, 49, 13, 36, 65},
                                {52, 70, 95, 23,  4, 60, 11, 42, 69, 24, 68, 56,  1, 32, 56, 71, 37,  2, 36, 91},
                                {22, 31, 16, 71, 51, 67, 63, 89, 41, 92, 36, 54, 22, 40, 40, 28, 66, 33, 13, 80},
                                {24, 47, 32, 60, 99, 03, 45, 02, 44, 75, 33, 53, 78, 36, 84, 20, 35, 17, 12, 50},
                                {32, 98, 81, 28, 64, 23, 67, 10, 26, 38, 40, 67, 59, 54, 70, 66, 18, 38, 64, 70},
                                {67, 26, 20, 68,  2, 62, 12, 20, 95, 63, 94, 39, 63,  8, 40, 91, 66, 49, 94, 21},
                                {24, 55, 58,  5, 66, 73, 99, 26, 97, 17, 78, 78, 96, 83, 14, 88, 34, 89, 63, 72},
                                {21, 36, 23,  9, 75,  0, 76, 44, 20, 45, 35, 14,  0, 61, 33, 97, 34, 31, 33, 95},
                                {78, 17, 53, 28, 22, 75, 31, 67, 15, 94,  3, 80,  4, 62, 16, 14,  9, 53, 56, 92},
                                {16, 39,  5, 42, 96, 35, 31, 47, 55, 58, 88, 24,  0, 17, 54, 24, 36, 29, 85, 57},
                                {86, 56,  0, 48, 35, 71, 89,  7,  5, 44, 44, 37, 44, 60, 21, 58, 51, 54, 17, 58},
                                {19, 80, 81, 68,  5, 94, 47, 69, 28, 73, 92, 13, 86, 52, 17, 77,  4, 89, 55, 40},
                                { 4, 52,  8, 83, 97, 35, 99, 16,  7, 97, 57, 32, 16, 26, 26, 79, 33, 27, 98, 66},
                                {88, 36, 68, 87, 57, 62, 20, 72,  3, 46, 33, 67, 46, 55, 12, 32, 63, 93, 53, 69},
                                { 4, 42, 16, 73, 38, 25, 39, 11, 24, 94, 72, 18,  8, 46, 29, 32, 40, 62, 76, 36},
                                {20, 69, 36, 41, 72, 30, 23, 88, 34, 62, 99, 69, 82, 67, 59, 85, 74,  4, 36, 16},
                                {20, 73, 35, 29, 78, 31, 90,  1, 74, 31, 49, 71, 48, 86, 81, 16, 23, 57,  5, 54},
                                { 1, 70, 54, 71, 83, 51, 54, 69, 16, 92, 33, 48, 61, 43, 52,  1, 89, 19, 67, 48} };
        
        int max=0;
        
        for(int i=0; i<(zahlenArray.length); i++)
        {
            for (int j=0; j<(zahlenArray[0].length); j++ )
            {
                System.out.print(zahlenArray[i][j] + " ");
            }
            System.out.println("");
        }
        
        for(int i=0; i<(zahlenArray.length); i++)
        {
            for (int j=0; j<(zahlenArray[0].length-3); j++ )
            {
                if (max < (zahlenArray[i][j] * zahlenArray[i][j+1] * zahlenArray[i][j+2] *zahlenArray[i][j+3]))
                {
                    max = (zahlenArray[i][j] * zahlenArray[i][j+1] * zahlenArray[i][j+2] *zahlenArray[i][j+3]);
                }
            }
        }
        
        for(int i=0; i<(zahlenArray.length-3); i++)
        {
            for (int j=0; j<(zahlenArray[0].length); j++ )
            {
                if (max < (zahlenArray[i][j] * zahlenArray[i+1][j] * zahlenArray[i+2][j] *zahlenArray[i+3][j]))
                {
                    max = (zahlenArray[i][j] * zahlenArray[i+1][j] * zahlenArray[i+2][j] *zahlenArray[i+3][j]);
                }
            }
        }
        
        for(int i=0; i<(zahlenArray.length-3); i++)
        {
            for (int j=0; j<(zahlenArray[0].length-3); j++ )
            {
                if (max < (zahlenArray[i][j] * zahlenArray[i+1][j+1] * zahlenArray[i+2][j+2] *zahlenArray[i+3][j+3]))
                {
                    max = (zahlenArray[i][j] * zahlenArray[i+1][j+1] * zahlenArray[i+2][j+2] *zahlenArray[i+3][j+3]);
                }
            }
        }
        
        for(int i=0; i<(zahlenArray.length-3); i++)
        {
            for (int j=3; j<(zahlenArray[0].length); j++ )
            {
                if (max < (zahlenArray[i][j] * zahlenArray[i+1][j-1] * zahlenArray[i+2][j-2] *zahlenArray[i+3][j-3]))
                {
                    max = (zahlenArray[i][j] * zahlenArray[i+1][j-1] * zahlenArray[i+2][j-2] *zahlenArray[i+3][j-3]);
                }
            }
        }
                               
        System.out.println("Maxzahl =" + max);
        
        //
        
        int n=10;
        // Ausgabe erste Zeile
        test(n); 
        // Ausgabe Zeile 2 bis n-1
        test2(n); 
        // Ausgabe letzte Zeile
        test(n); 
        
        System.out.println(rechne(8));
        System.out.println("Binaer 15 ="+toBin(15));
        
        double leibnitz = 0.0;
        boolean negvz = true;
        for (int i=1; i<90000000; i+=2)
        {
            if (negvz)
            {
                leibnitz = leibnitz + (double)1/(double)i;
            }
            else
            {
                leibnitz = leibnitz - (double)1/(double)i;
            }
            negvz = !negvz;
        }
        System.out.println(leibnitz*4);
        
        String[] a1 = { "a", "b","c","d"};
        String[] a2 = { "d", "e","f"};
        String[] test = arrayAppend(a1,a2);
        for (String test1 : test) {
            System.out.println(test1);
        }
        String[] test2 = arrayDelete(a1, "b");
        System.out.println("new");
        for (String test21 : test2) {
            System.out.println(test21);
        }
        String name = "Hans Peter";
        System.out.println(vertauscheName(name));
    }
    
    static String[] arrayAppend(String[] a1, String[] a2)
    {
        String[] retval = new String[a1.length+a2.length];
        System.arraycopy(a1, 0, retval, 0, a1.length);
        System.arraycopy(a2, 0, retval, a1.length, a2.length);
        return retval;
    }
    
    static String[] arrayDelete(String[] a1, String del)
    {
        int count = 0;
        String[] retval = new String[a1.length-1];
        for (String a11 : a1) {
            if (a11 == null ? del != null : !a11.equals(del)) {
                retval[count] = a11;
                count++;
            }
        }
        return retval;
    }
    
    static String vertauscheName(String name)
    {
        int space = name.indexOf(" ");
        return name.substring(space+1,name.length())+ " " + name.substring(0,space);
    }

    public static String toBin(int zahl)
    {
        return Integer.toBinaryString(zahl);
    }
    
    public static int rechne(int x) 
    { 
      int erg = 1; 
      for (int i=1; i<=x; i++) 
        erg = 2 * erg; 
      return erg; 
    }   
    
    private static void test2(int n) {
        
        for(int zeile=2; zeile<=n-1;zeile++) {
            System.out.print("*");
            for(int spalte=1; spalte<=2*n-2;spalte++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
    }

    private static void test(int n) {
        
        for(int i=1; i<=2*n;i++) {
            System.out.print("*");
        }
        System.out.println("");
    }
    
    
    // Methoden
    static void ZahlenAusgeben() {
        for (int i=0;i<20;i++) {
            if (zahlen[i]>9)
                System.out.print(zahlen[i]);
            else
                System.out.print("0"+zahlen[i]);
            if (i != 19)
                System.out.print(",");
        }
        System.out.println("");
    }
    
    static int Fakultaet(int n) {
        
        if (n<=1)
            return 1;
        else
        {
            return n*Fakultaet(n-1);
            
        }
    }
    

    

}
