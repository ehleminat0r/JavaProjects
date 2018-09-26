package MiscProjects;

import java.util.Scanner;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Dezimal
{
  int bintodec( String eingabe )
  {
    int zahl=0;
    for (int i=0; i<eingabe.length(); i++)      // Binaere Zahl in Dez umwandeln
    {
        if (eingabe.charAt((eingabe.length()-i-1)) == '1')
            zahl += (int)Math.pow(2,i);
                    
    }
    return zahl ;
  }
}



class Binaer
{
    public int potZahl(int eingabe)
    {
        int quadrat = 0;
        while (((int)Math.pow(2,quadrat+1)-1)<eingabe)  // Minimalwert der Quadratischen Funktion ermitteln
            quadrat++;
        return quadrat;
    }
    
    public String dectobin(int eingabe)               // Sting erstellen der Binärcode der eingegebenen Zahl darstellt
    {
        String binnr = "";
        int zahl = eingabe;
        
        for(int i=potZahl(zahl);i>=0;i--)
        {
            if (zahl >= (int)Math.pow(2,i))
            {
                zahl -= (int)Math.pow(2,i);
                binnr += "1";
            }
            else
                binnr += "0";
        }    
        
        return binnr;
    }
    
    public void showRechenweg(int eingabe)
    {
        int zahl = eingabe;
        for(int i=potZahl(zahl);i>=0;i--)
        {
            if (zahl >= (int)Math.pow(2,i))
            {
                zahl -= (int)Math.pow(2,i);
                System.out.print("1*"+(int)Math.pow(2,i));
            }
            else
                System.out.print("0*"+(int)Math.pow(2,i));
            if (i>0)
                System.out.print(" + ");
        }
        System.out.println("");
    }
    
    public void showAll(int eingabe)
    {
        int zahl = eingabe;
        int laenge = Integer.toString((int)Math.pow(2,potZahl(zahl))).length();     // Wie viele Stellen hat die Dez zahl
        // System.out.println("Stellen der Dez Zahl: " + laenge);
        for (int i=potZahl(zahl); i >=0; i--)
        {
            if (Integer.toString((int)Math.pow(2, i)).length() < laenge)
                for (int j=0; j < (laenge - Integer.toString((int)Math.pow(2, i)).length()) ;j++)   //0er füllen
                    System.out.print("0");
        System.out.print((int)Math.pow(2, i) + " ");
        }
        System.out.println("dec");
       
        // Binaer mit Leerzeichen
        for(int i=potZahl(zahl);i>=0;i--)
        {
            for(int j=laenge-1; j>0; j--)
            System.out.print(" ");
            if (zahl >= (int)Math.pow(2,i))
            {
                zahl -= (int)Math.pow(2,i);
                System.out.print("1 ");
            }
            else
                System.out.print("0 ");
        }
        System.out.println("bin"); 
    }
    
}

class Chiffre
{
    // Klassenvariablen
    private String gBuchstaben = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private String kBuchstaben = new String("abcdefghijklmnopqrstuvwxyz");
    private String codeWort;
    private int verschiebung;
    private String save;
    
    // Konstruktor
    public Chiffre(String wort, int versch)
    {
        System.out.println("Code: " + verschl(wort,versch));
    }
    
    // Methoden
    public String verschl(String wort, int versch)
    {
        this.codeWort = wort;
        this.verschiebung = versch;
        save = "";
        int keineZahl = 0;
        for (int i=0; i < codeWort.length(); i++)
        {
            keineZahl = save.length();
            for (int j=0; j < 26; j++)          // ist code Klein- oder Großbuchstabe ?
                if (codeWort.charAt(i) == gBuchstaben.charAt(j))
                {
                    if ( (j+versch) >= 26 )     // wenn es über Z geht wieder bei A anfangen
                        save = save + gBuchstaben.charAt(j+versch-26);
                    else
                        save = save + gBuchstaben.charAt(j+versch);
                }
                else
                    if (codeWort.charAt(i) == kBuchstaben.charAt(j))
                    {
                    if ( (j+versch) >= 26 )         // wenn es über Z geht wieder bei A anfangen
                        save = save + kBuchstaben.charAt(j+versch-26);
                    else
                        save = save + kBuchstaben.charAt(j+versch); 
                    }
            if (save.length() == keineZahl )    // ist das verschobene codewort größer geworden(buchstabe hinzugefügt?)
                save = save + codeWort.charAt(i);
        }
        return save;
    }
    
}

class DecBinChiffre
{
  public static void main ( String[] args )
  {
    /*  
    Scanner scan = new Scanner(System.in);
    System.out.print("zahl :");
    int zahl = scan.nextInt();
    */
            
    // KlassenVariablen erstellen
    int dzahl = Integer.parseInt(JOptionPane.showInputDialog("Dezimal Zahl:"));
    String bzahl = JOptionPane.showInputDialog("Binäre Zahl:");
    
    String code = JOptionPane.showInputDialog("Bitte String eingeben:");
    int verschieb = Integer.parseInt(JOptionPane.showInputDialog("Um wieviel Stellen soll String verschoben werden:\n Zahl < 26"));
    
    Random rnd = new Random();
    
    Binaer bin = new Binaer();
    Dezimal dec = new Dezimal();
    
    
    // Binaer Methoden aufrufen    
    System.out.println("Dezimal Zahl: "+dzahl);
    System.out.println("Potenz min Zwei hoch "+bin.potZahl(dzahl)+" ("+(bin.potZahl(dzahl)+1)+" Bit)");
    System.out.println(bin.dectobin(dzahl)+" Bin");
    bin.showRechenweg(dzahl);
    bin.showAll(dzahl);
    
    // Dezimal Methoden aufrufen
    System.out.println("Binaere Zahl: "+bzahl);
    System.out.println(dec.bintodec(bzahl));
    
    // anderes
    for (int i=0; i<20; i++)
    {
        int test = rnd.nextInt(50);
        System.out.println("Dezimal: "+test);
        bin.showAll(test);
    }
    
    // Chiffre Codierung (im Alphabet verschieben)
    Chiffre test = new Chiffre(code,verschieb);
    
    
    /* // Fizz Buzz Zahlenreihe Peter Paul
    for (int i=0;i<=100;i++)
    {
        if((i%3) == 0 && (i%5) == 0)
            System.out.println("FizzBuzz");
        else
            if ((i%3) == 0)
                System.out.println("Fizz");
            else
                if ((i%5) == 0)
                    System.out.println("Buzz");
                else   
                    System.out.println(i);
    }
    */
    
  }
}