package MiscProjects;

import java.io.*;

/**
 *
 * @author lhassler
 */
public class XMLParser {
    // Variablen
    static String inputDateiName = "C:\\Users\\lhassler\\Desktop\\req.xml";
    static String outputDateiName = "C:\\Users\\lhassler\\Desktop\\output.txt";
    
    // Methoden
    public static void main(String[] args) {
        ladeDatei(inputDateiName);
    }   
    
    private static void ladeDatei(String datName) {
        File file = new File(datName);
        if (!file.canRead() || !file.isFile())
            System.exit(0);
            BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(datName));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                // Einzelne Zeilenen werden in Variable "zeile" gespeichert
                //System.out.println("Gelesene Zeile: " + zeile);
                // Zeile wird durch Methode parse umgewandelt und an Methode schreibeDatei weitergegeben
                schreibeDatei(parse(zeile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }

    private static void schreibeDatei(String zeile) {
    PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputDateiName,true)),true);
            // Zeile wird in Datei geschrieben
            //System.out.println("Export: "+zeile);
            pWriter.println(zeile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null){
                pWriter.close();
            }
        } 
    }
    
    private static String parse(String zeile) {
        // Deutsche Umlaute
        zeile = zeile.replaceAll("&auml;", "ä");
        zeile = zeile.replaceAll("&uuml;", "ü");
        zeile = zeile.replaceAll("&ouml;", "ö");
        zeile = zeile.replaceAll("&Auml;", "Ä");
        zeile = zeile.replaceAll("&Uuml;", "Ü");
        zeile = zeile.replaceAll("&Ouml;", "Ö");
        
        // Sonderzeichen
        zeile = zeile.replaceAll("&deg;", "°");
        zeile = zeile.replaceAll("&sect;", "§");
        zeile = zeile.replaceAll("&acute;", "´");
        zeile = zeile.replaceAll("&sup2;", "²");
        zeile = zeile.replaceAll("&sup3;", "³");
        zeile = zeile.replaceAll("&micro;", "µ");
        zeile = zeile.replaceAll("&szlig;", "ß");
        zeile = zeile.replaceAll("&ldquo;", "“");
        zeile = zeile.replaceAll("&rdquo;", "”");
        zeile = zeile.replaceAll("&lsquo;", "‘");        
        zeile = zeile.replaceAll("&rsquo;", "’");        
        zeile = zeile.replaceAll("&ndash;", "–");        
        zeile = zeile.replaceAll("&iacute;", "í");    
        
        // zeile = zeile.replaceAll("&;", "");        
        return zeile;
    }

}
