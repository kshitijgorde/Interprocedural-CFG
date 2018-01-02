import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class double_ extends Applet implements Runnable
{
    String appletinfo;
    String[][] parameterinfo;
    Thread thread;
    Object object;
    String[] bildname;
    Image[] bild;
    int doublebild;
    int autorenbild;
    Font pearlfont;
    FontMetrics fontmetrics;
    int fonthoehe;
    int fontaufstieg;
    Color schwarz;
    Color gelb;
    CardLayout cardlayout;
    SpielCanvas spielcanvas;
    TitelCanvas titelcanvas;
    HighscorePanel highscorepanel;
    int appletbreite;
    int applethoehe;
    int doublebildbreite;
    int doublebildhoehe;
    int doublebildvspace;
    int doublebildx;
    int doublebildminy;
    int doublebildmaxy;
    int autorenbildbreite;
    int autorenbildhoehe;
    int autorenbildvspace;
    int autorenbildx;
    int autorenbildy;
    int texty;
    int texthoehe;
    int ohnepunkte;
    int maxpunkte;
    String unloeschbarstring;
    String punktestring;
    String leername;
    String zwischenraum;
    int highscorenamelaenge;
    int highscores;
    String[] highscorename;
    int[] highscorepunkte;
    
    int Zufall(final int n) {
        return (int)(Math.random() * n);
    }
    
    String Auffuellen(final String s, final int n) {
        return this.leername.substring(s.length(), n) + s;
    }
    
    void MittigSchreiben(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.appletbreite - this.fontmetrics.stringWidth(s)) / 2, n);
    }
    
    void DoublebildZeichnen(final Graphics graphics, final int n) {
        graphics.drawImage(this.bild[this.doublebild], this.doublebildx, n, null);
    }
    
    void AutorenbildZeichnen(final Graphics graphics) {
        graphics.drawImage(this.bild[this.autorenbild], this.autorenbildx, this.autorenbildy, null);
    }
    
    void BilderLaden() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < this.bildname.length; ++i) {
            mediaTracker.addImage(this.bild[i] = this.getImage(this.getCodeBase(), this.bildname[i]), 0);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.doublebildbreite = this.bild[this.doublebild].getWidth(null);
        this.doublebildhoehe = this.bild[this.doublebild].getHeight(null);
        this.autorenbildbreite = this.bild[this.autorenbild].getWidth(null);
        this.autorenbildhoehe = this.bild[this.autorenbild].getHeight(null);
    }
    
    public void init() {
        this.BilderLaden();
        this.thread = null;
        this.fontmetrics = this.getFontMetrics(this.pearlfont);
        this.fonthoehe = 18;
        this.fontaufstieg = 13;
        this.setBackground(this.schwarz);
        this.setLayout(this.cardlayout);
        this.spielcanvas = new SpielCanvas(this);
        this.titelcanvas = new TitelCanvas(this);
        this.highscorepanel = new HighscorePanel(this);
        this.add("Titel", this.titelcanvas);
        this.add("Spiel", this.spielcanvas);
        this.add("Score", this.highscorepanel);
        this.highscorepanel.textfieldbreite = this.highscorepanel.textfield.preferredSize().width;
        this.highscorepanel.textfieldhoehe = this.highscorepanel.textfield.preferredSize().height;
        this.highscorepanel.textfieldx = (this.appletbreite - this.highscorepanel.textfieldbreite) / 2;
        this.highscorepanel.textfieldy = this.highscorepanel.welldoney + this.highscorepanel.eingabezeile * this.fonthoehe - this.fontaufstieg - (this.highscorepanel.textfieldhoehe - this.fonthoehe) / 2;
        this.highscorepanel.textfield.reshape(this.highscorepanel.textfieldx, this.highscorepanel.textfieldy, this.highscorepanel.textfieldbreite, this.highscorepanel.textfieldhoehe);
        this.parameterinfo[0][1] = String.valueOf(this.appletbreite);
        this.parameterinfo[1][1] = String.valueOf(this.applethoehe);
        this.resize(this.appletbreite, this.applethoehe);
    }
    
    public void run() {
        while (true) {
            this.titelcanvas.Titel();
            this.spielcanvas.Spiel();
            this.highscorepanel.EnterScore();
        }
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public String getAppletInfo() {
        return this.appletinfo;
    }
    
    public String[][] getParameterInfo() {
        return this.parameterinfo;
    }
    
    public double_() {
        this.appletinfo = "double\n\nWritten by Martin Koerwien & Michael Kraus\n1992-1998\nVersion JDK 1.0\n\nPlease send bug reports, comments and suggestions to:\nmichael.kraus@informatik.uni-muenchen.de\nOr visit my homepage:\nwww.informatik.uni-muenchen.de/~michael.kraus";
        this.parameterinfo = new String[][] { { "width", "", "don't change this parameter" }, { "height", "", "don't change this parameter" } };
        this.object = new Object();
        this.bildname = new String[] { "lr.gif", "lu0.gif", "lo0.gif", "ro0.gif", "ru0.gif", "wg0.gif", "sk0.gif", "lu1.gif", "lo1.gif", "ro1.gif", "ru1.gif", "wg1.gif", "sk1.gif", "double.gif", "autoren.gif" };
        this.bild = new Image[this.bildname.length];
        this.doublebild = 13;
        this.autorenbild = 14;
        this.pearlfont = new Font("Courier", 1, 16);
        this.schwarz = new Color(17, 17, 17);
        this.gelb = new Color(255, 238, 204);
        this.cardlayout = new CardLayout();
        this.doublebildvspace = 6;
        this.doublebildminy = this.doublebildvspace;
        this.autorenbildvspace = 6;
        this.ohnepunkte = -1;
        this.maxpunkte = 999999;
        this.unloeschbarstring = " pts. X";
        this.punktestring = " pts.";
        this.leername = "               ";
        this.zwischenraum = "  ";
        this.highscorenamelaenge = this.leername.length();
        this.highscores = 100;
        this.highscorename = new String[this.highscores];
        this.highscorepunkte = new int[this.highscores];
    }
}
