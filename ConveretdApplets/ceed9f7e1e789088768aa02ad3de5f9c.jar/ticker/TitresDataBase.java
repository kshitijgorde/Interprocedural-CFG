// 
// Decompiled by Procyon v0.5.30
// 

package ticker;

import java.util.StringTokenizer;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;

public class TitresDataBase implements Runnable
{
    String urlcgi;
    int frequence;
    Thread thread;
    boolean on_tourne;
    URL urlBase;
    URL urlDocument;
    StringTriableVector vector;
    Hashtable hashtable;
    Hashtable hashtableBis;
    StringTriableVector vectorBis;
    
    public TitresDataBase(final URL urlDocument, final URL urlBase, final String urlcgi, final int frequence) {
        this.on_tourne = true;
        this.vector = new StringTriableVector();
        this.hashtable = new Hashtable();
        this.hashtableBis = new Hashtable();
        this.vectorBis = new StringTriableVector();
        this.urlcgi = urlcgi;
        this.frequence = frequence;
        this.urlBase = urlBase;
        this.urlDocument = urlDocument;
        (this.thread = new Thread(this)).start();
    }
    
    public Vector getTitres() {
        return this.vector;
    }
    
    public Vector getTitresForExtern() {
        return this.vectorBis;
    }
    
    public void start() {
        this.on_tourne = true;
        if (this.thread != null) {
            this.thread.stop();
        }
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.on_tourne = false;
            this.thread.stop();
            this.thread = null;
        }
        else {
            this.on_tourne = false;
        }
    }
    
    public void run() {
        while (this.on_tourne) {
            try {
                final URLConnection openConnection = new URL(this.urlBase, this.urlcgi).openConnection();
                openConnection.setUseCaches(false);
                openConnection.setRequestProperty("Pragma", "no-cache");
                openConnection.setRequestProperty("Cache-Control", " no-cache");
                openConnection.setRequestProperty("Referer", this.urlDocument.toExternalForm());
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
                this.traiteRequete(bufferedReader);
                bufferedReader.close();
            }
            catch (Exception ex) {
                System.out.println("TitresDatabase.run(), Exception :" + ex);
                this.stop();
            }
            pause(this.frequence);
        }
    }
    
    public void suspend() {
        if (this.thread != null) {
            this.thread.suspend();
        }
    }
    
    public void resume() {
        if (this.thread != null) {
            this.thread.resume();
        }
    }
    
    void traiteRequete(final BufferedReader bufferedReader) throws Exception {
        String nextToken = "";
        int n = 0;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() > 1 && line.charAt(0) != '#') {
                ++n;
                final StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
                try {
                    final String nextToken2 = stringTokenizer.nextToken();
                    float floatValue;
                    try {
                        nextToken = stringTokenizer.nextToken();
                        if (this.isDigit(nextToken)) {
                            floatValue = new Float(nextToken);
                        }
                        else {
                            floatValue = n;
                        }
                    }
                    catch (Exception ex) {
                        System.err.println("TitresDataBase.traiteRequete(), Exception 1 : " + ex);
                        ex.printStackTrace(System.err);
                        floatValue = n;
                    }
                    float floatValue2;
                    try {
                        floatValue2 = new Float("0.0");
                    }
                    catch (Exception ex2) {
                        floatValue2 = n;
                        System.err.println("TitresDataBase.traiteRequete(), Exception 2 : " + ex2);
                    }
                    Titre titre = this.hashtable.get(nextToken2);
                    if (titre == null) {
                        try {
                            titre = new Titre(nextToken2, floatValue, floatValue2);
                            titre.setValeurStr(nextToken);
                        }
                        catch (Exception ex3) {
                            System.err.println("TitresDataBase.traiteRequete(), Exception C : " + ex3 + " =>" + nextToken2 + "<=");
                        }
                        this.vector.ajoute(titre);
                        this.hashtable.put(nextToken2, titre);
                    }
                    else {
                        titre.setValeur(floatValue);
                        titre.setValeurStr(nextToken);
                        titre.setExValeur(floatValue2);
                    }
                }
                catch (Exception ex4) {
                    System.err.println("TitresDataBase.traiteRequete(), Exception 3 : " + ex4);
                }
            }
        }
    }
    
    public static void pause(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {
            System.err.println("TitresDataBase.pause(), Exception  : " + ex);
        }
    }
    
    public boolean isDigit(final String s) {
        if (s == null) {
            return false;
        }
        final char[] charArray = s.toCharArray();
        if (charArray == null) {
            return false;
        }
        int n;
        for (n = 0; n < charArray.length && (Character.isDigit(charArray[n]) || Character.getType(charArray[n]) == 22); ++n) {}
        return n == charArray.length;
    }
}
