import java.awt.Event;
import java.awt.Container;
import java.net.URLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class HighscorePanel extends Panel
{
    int textfieldbreite;
    int textfieldhoehe;
    int textfieldx;
    int textfieldy;
    int welldoney;
    int eingabezeit;
    String lesedatei;
    String schreibdatei;
    String[] eingabetext;
    int eingabezeile;
    int punktezeile;
    int rangzeile;
    String[] silben;
    int index;
    boolean scoresok;
    TextField textfield;
    double_ applet;
    
    HighscorePanel(final double_ applet) {
        this.eingabezeit = 60000;
        this.lesedatei = "http://www.informatik.uni-muenchen.de/~michael.kraus/double/highscores";
        this.schreibdatei = "http://cgi.cip.informatik.uni-muenchen.de/cgi-bin/user/krausm/double.cgi";
        this.eingabetext = new String[] { "Well done, hero!", "", "Please enter your name:", "", "", "", "Score: ", "Rank: " };
        this.eingabezeile = 4;
        this.punktezeile = 6;
        this.rangzeile = 7;
        this.silben = new String[] { "MAR", "TIN", "KAR", "STEN", "NOR", "KURT", "UR", "BERT", "DAM", "MAN", "FRED", "HEL", "MUT", "TON", "DOLF", "WOLF", "GANG", "SEF", "SA", "BI", "NE", "OLLI", "AL", "AN", "EDE", "ANNE", "OL", "DON", "ORG", "SU", "LA", "OT", "TO", "RU", "MICH", "AEL", "SUF", "FI", "SIEG", "MUND", "FRIED", "FONS", "DRE", "AS", "HI", "AR", "NOLD", "TUR", "EL", "AU", "KO", "GUST", "WUF", "LIN", "DE", "RO", "LO" };
        this.applet = applet;
        this.textfield = new TextField(applet.highscorenamelaenge);
        try {
            this.HighscoresLesen();
            this.scoresok = true;
        }
        catch (Exception ex) {
            System.out.println("Error while loading highscores: " + ex.toString());
            this.NeueHighscores();
            this.scoresok = false;
        }
        this.welldoney = applet.texty + (applet.texthoehe - this.eingabetext.length * applet.fonthoehe) / 2;
        this.setLayout(null);
        this.setFont(applet.pearlfont);
        this.setForeground(applet.gelb);
        this.setBackground(applet.schwarz);
        this.add(this.textfield);
    }
    
    public void paint(final Graphics graphics) {
        this.applet.DoublebildZeichnen(graphics, this.applet.doublebildminy);
        this.EingabeSchreiben(graphics);
        this.applet.AutorenbildZeichnen(graphics);
    }
    
    void EingabeSchreiben(final Graphics graphics) {
        int welldoney = this.welldoney;
        for (int i = 0; i < this.eingabetext.length; ++i) {
            if (i == this.punktezeile) {
                this.applet.MittigSchreiben(graphics, this.eingabetext[i] + String.valueOf(this.applet.spielcanvas.punkte) + this.applet.punktestring, welldoney);
            }
            else if (i == this.rangzeile) {
                this.applet.MittigSchreiben(graphics, this.eingabetext[i] + String.valueOf(this.index + 1) + ".", welldoney);
            }
            else {
                this.applet.MittigSchreiben(graphics, this.eingabetext[i], welldoney);
            }
            welldoney += this.applet.fonthoehe;
        }
    }
    
    void HighscoresLesen() throws MalformedURLException, IOException {
        final DataInputStream dataInputStream = new DataInputStream(new URL(this.lesedatei).openConnection().getInputStream());
        for (int i = 0; i < this.applet.highscores; ++i) {
            this.applet.highscorename[i] = dataInputStream.readUTF();
            this.applet.highscorepunkte[i] = dataInputStream.readInt();
        }
        dataInputStream.close();
    }
    
    void HighscoresSchreiben() throws MalformedURLException, IOException {
        final URLConnection openConnection = new URL(this.schreibdatei).openConnection();
        openConnection.setDoOutput(true);
        final DataOutputStream dataOutputStream = new DataOutputStream(openConnection.getOutputStream());
        for (int i = 0; i < this.applet.highscores; ++i) {
            dataOutputStream.writeUTF(this.applet.highscorename[i]);
            dataOutputStream.writeInt(this.applet.highscorepunkte[i]);
        }
        dataOutputStream.close();
    }
    
    String MakeName() {
        String s = this.silben[this.applet.Zufall(this.silben.length)] + this.silben[this.applet.Zufall(this.silben.length)];
        if (this.applet.Zufall(2) == 1) {
            s += this.silben[this.applet.Zufall(this.silben.length)];
        }
        return s;
    }
    
    void NeueHighscores() {
        for (int i = 0; i < this.applet.highscores; ++i) {
            this.applet.highscorename[i] = this.MakeName();
            this.applet.highscorepunkte[i] = (this.applet.highscores - i) * (this.applet.highscores - i);
        }
    }
    
    String HighscoreString(final int n) {
        return this.applet.Auffuellen(String.valueOf(n + 1), String.valueOf(this.applet.highscores).length()) + "." + this.applet.zwischenraum + this.applet.Auffuellen(this.applet.highscorename[n], this.applet.highscorenamelaenge) + this.applet.zwischenraum + this.applet.Auffuellen(String.valueOf(this.applet.highscorepunkte[n]), String.valueOf(this.applet.maxpunkte).length()) + this.applet.punktestring;
    }
    
    void EnterScore() {
        try {
            this.HighscoresLesen();
            this.scoresok = true;
        }
        catch (Exception ex) {
            System.out.println("Error while loading highscores: " + ex.toString());
            this.scoresok = false;
        }
        this.index = 0;
        while (this.index < this.applet.highscores && this.applet.spielcanvas.punkte <= this.applet.highscorepunkte[this.index]) {
            ++this.index;
        }
        if (this.index < this.applet.highscores) {
            this.textfield.setText(this.MakeName());
            this.textfield.selectAll();
            this.applet.cardlayout.show(this.applet, "Score");
            this.textfield.requestFocus();
            try {
                synchronized (this.applet.object) {
                    this.applet.object.wait(this.eingabezeit);
                }
                // monitorexit(this.applet.object)
            }
            catch (InterruptedException ex3) {}
            for (int i = this.applet.highscores - 1; i > this.index; --i) {
                this.applet.highscorename[i] = this.applet.highscorename[i - 1];
                this.applet.highscorepunkte[i] = this.applet.highscorepunkte[i - 1];
            }
            final String[] highscorename = this.applet.highscorename;
            final int index = this.index;
            final String text = this.textfield.getText();
            highscorename[index] = text;
            if (text.length() > this.applet.highscorenamelaenge) {
                this.applet.highscorename[this.index] = this.applet.highscorename[this.index].substring(0, this.applet.highscorenamelaenge);
            }
            this.applet.highscorepunkte[this.index] = this.applet.spielcanvas.punkte;
            this.applet.titelcanvas.highscoreindex = this.index / this.applet.titelcanvas.highscoreseite * this.applet.titelcanvas.highscoreseite;
            try {
                if (this.scoresok) {
                    this.HighscoresSchreiben();
                }
                return;
            }
            catch (Exception ex2) {
                System.out.println("Error while saving highscores: " + ex2.toString());
                return;
            }
        }
        this.applet.titelcanvas.highscoreindex = (this.applet.titelcanvas.highscoreindex + this.applet.titelcanvas.highscoreseite) % this.applet.highscores;
    }
    
    public boolean action(final Event event, final Object o) {
        synchronized (this.applet.object) {
            this.applet.object.notifyAll();
        }
        // monitorexit(this.applet.object)
        return true;
    }
}
