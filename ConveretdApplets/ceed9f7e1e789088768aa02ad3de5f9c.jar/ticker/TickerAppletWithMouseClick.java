// 
// Decompiled by Procyon v0.5.30
// 

package ticker;

import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Hashtable;
import java.applet.Applet;

public class TickerAppletWithMouseClick extends Applet
{
    DefileTest def;
    TitresDataBase database;
    int width;
    Hashtable hParam;
    boolean firsttime;
    
    public TickerAppletWithMouseClick() {
        this.firsttime = true;
    }
    
    public void init() {
        this.initParametres();
        this.database = new TitresDataBase(this.getDocumentBase(), this.getCodeBase(), Parametres.getParamStr("cgi"), Parametres.getParamInt("cgi_frequence_ms"));
        this.def = new DefileTest(this.database, this);
        this.setLayout(new BorderLayout());
        this.add("Center", this.def);
    }
    
    int getParamInt(final String s, final int n) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? n : Integer.parseInt(parameter);
    }
    
    Color getParamCol(final String s, final Color color) {
        if (this.getParameter(s) == null) {
            return color;
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter(s), ",");
            return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    public void stop() {
        this.def.stop();
        this.database.stop();
    }
    
    public void start() {
        this.firsttime = false;
        this.def.start();
        this.database.start();
    }
    
    public void initParametres() {
        this.hParam = this.loadINI();
        Parametres.addParamInt("vitesse", this.getParamInt("vitesse", 5));
        Parametres.addParamInt("vitesse_acceleree", this.getParamInt("vitesse_acceleree", 5));
        Parametres.addParamInt("periode", this.getParamInt("periode", 25));
        Parametres.addParamInt("periode_acceleree", this.getParamInt("periode_acceleree", 25));
        Parametres.addParamInt("espace", this.getParamInt("espace", 20));
        Parametres.addParamInt("TAILLE_CASE1", this.getParamInt("TAILLE_CASE1", 100));
        Parametres.addParamInt("TAILLE_CASE2", this.getParamInt("TAILLE_CASE2", 65));
        Parametres.addParamInt("TAILLE_CASE3", this.getParamInt("TAILLE_CASE3", 55));
        Parametres.addParam("cgi", this.getParameter("cgi"));
        Parametres.addParamInt("cgi_frequence_ms", this.getParamInt("cgi_frequence_ms", 10000));
        Parametres.addParamCol("COL_CASE1_TEXT", this.getParamCol("COL_CASE1_TEXT", new Color(240, 255, 240)));
        Parametres.addParamCol("COL_CASE2_TEXT", this.getParamCol("COL_CASE2_TEXT", new Color(255, 255, 11)));
        Parametres.addParamCol("COL_CASE3_TEXT_PLUS", this.getParamCol("COL_CASE3_TEXT_PLUS", new Color(0, 250, 0)));
        Parametres.addParamCol("COL_CASE3_TEXT_MINUS", this.getParamCol("COL_CASE3_TEXT_MINUS", new Color(255, 0, 0)));
        Parametres.addParamCol("COL_CASE3_TEXT_UNCH", this.getParamCol("COL_CASE3_TEXT_UNCH", new Color(0, 180, 0)));
        Parametres.addParamCol("COL_CASE4_TEXT", this.getParamCol("COL_CASE2_TEXT", new Color(255, 255, 11)));
        Parametres.addParamCol("COL_CASE1_FOND", this.getParamCol("COL_CASE1_FOND", new Color(11, 71, 26)));
        Parametres.addParamCol("COL_CASE2_FOND", this.getParamCol("COL_CASE2_FOND", new Color(79, 75, 11)));
        Parametres.addParamCol("COL_CASE3_FOND", this.getParamCol("COL_CASE3_FOND", new Color(78, 50, 8)));
        Parametres.addParamCol("COL_CASE4_FOND", this.getParamCol("COL_CASE4_FOND", new Color(78, 50, 8)));
        Parametres.addParamCol("COL_FOND", this.getParamCol("COL_FOND", new Color(0, 0, 0)));
        Parametres.addParamCol("COL_CASES_CADRE", this.getParamCol("COL_CASES_CADRE", new Color(0, 0, 180)));
    }
    
    public String getParameter(final String s) {
        final String parameter = super.getParameter(s);
        if (parameter == null) {
            return this.hParam.get(s.toLowerCase());
        }
        return parameter;
    }
    
    Hashtable loadINI() {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DataInputStream(new URL(this.getCodeBase(), "ticker/config.ini").openStream())));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0 && line.charAt(0) >= '0') {
                    try {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line, "=");
                        hashtable.put(stringTokenizer.nextToken().toLowerCase(), stringTokenizer.nextToken());
                    }
                    catch (Exception ex) {
                        System.err.println("TickerApplet.loadINI(), Erreur de syntaxe dans config.ini : " + ex);
                    }
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex2) {
            System.err.println("TickerApplet.loadINI(), Impossible de charger config.ini : " + ex2);
        }
        return hashtable;
    }
    
    public int getNombreTitres() {
        if (this.database != null) {
            return this.database.getTitresForExtern().size();
        }
        return 0;
    }
    
    private Titre getTitre(final int n) {
        try {
            if (this.database == null) {
                return null;
            }
            final Vector titresForExtern;
            if ((titresForExtern = this.database.getTitresForExtern()) == null) {
                return null;
            }
            return titresForExtern.elementAt(n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getTitreNom(final int n) {
        final Titre titre = this.getTitre(n);
        if (titre == null) {
            return "";
        }
        return titre.getNom();
    }
    
    public String getTitreID(final int n) {
        final Titre titre = this.getTitre(n);
        if (titre == null) {
            return "";
        }
        return titre.getID();
    }
    
    public float getTitreValeur(final int n) {
        final Titre titre = this.getTitre(n);
        if (titre == null) {
            return 0.0f;
        }
        return titre.getValeur();
    }
    
    public float getTitreExValeur(final int n) {
        final Titre titre = this.getTitre(n);
        if (titre == null) {
            return 0.0f;
        }
        return titre.getExValeur();
    }
}
