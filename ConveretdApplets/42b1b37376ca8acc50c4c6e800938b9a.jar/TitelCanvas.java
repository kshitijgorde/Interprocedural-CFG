import java.awt.Event;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class TitelCanvas extends Canvas
{
    int highscoreyohnepunkte;
    int highscoreymitpunkte;
    int highscoreseite;
    int titelzeit;
    String[][] titeltext;
    int spielstart;
    int automatik;
    int controlstitel;
    int instructionstitel;
    int highscoretitel;
    int highscoretext;
    int yourscoretext;
    int titel;
    int highscoreindex;
    double_ applet;
    int catched;
    
    TitelCanvas(final double_ applet) {
        this.highscoreseite = 10;
        this.titelzeit = 30000;
        this.titeltext = new String[][] { { "Controls", "", "Abort: timeout or 'a' key", "Move: mouse or cursor keys", "Erase: middle mouse button or 'e' key", "Rotate: right mouse button or return key", "Drop: left mouse button or space key or automatic", "", "Highscores screens: '1', '2', ... '0' keys", "Instructions screen: 'i' key", "Controls screen: 'c' key", "", "Press left mouse button or any other key to play" }, { "Instructions", "", "You have to drop the blue piece within the time", "indicated by the yellow bar, i.e. four seconds.", "After that time, the game tries to drop the piece", "automatically. If you build a closed loop it will", "dissolve into score, one point for each tile of", "the loop. If you clear the whole field, your points", "will be doubled. Erasing can only be done if the", "blue piece coincides with the underlying tiles and", "only if you scored at least 100 points since the", "last erasing was done, which is indicated by an 'X'." }, { "Highscores", "Your score: " } };
        this.spielstart = -2;
        this.automatik = -1;
        this.instructionstitel = 1;
        this.highscoretitel = 2;
        this.yourscoretext = 1;
        this.applet = applet;
        applet.autorenbildx = (applet.appletbreite - applet.autorenbildbreite) / 2;
        applet.autorenbildy = applet.applethoehe - applet.autorenbildvspace - applet.autorenbildhoehe;
        applet.texty = applet.doublebildminy + applet.doublebildhoehe + applet.doublebildvspace + applet.fontaufstieg;
        applet.texthoehe = applet.autorenbildy - applet.autorenbildvspace - applet.fontmetrics.getDescent() - applet.texty;
        this.highscoreymitpunkte = applet.texty + (applet.texthoehe - (4 + this.highscoreseite) * applet.fonthoehe) / 2;
        this.highscoreyohnepunkte = applet.texty + (applet.texthoehe - (2 + this.highscoreseite) * applet.fonthoehe) / 2;
        this.titel = this.controlstitel;
        this.highscoreindex = applet.highscores - this.highscoreseite;
        this.setFont(applet.pearlfont);
        this.setForeground(applet.gelb);
        this.setBackground(applet.schwarz);
    }
    
    public void paint(final Graphics graphics) {
        this.applet.DoublebildZeichnen(graphics, this.applet.doublebildminy);
        if (this.titel == this.highscoretitel) {
            this.HighscoreSchreiben(graphics);
        }
        else {
            this.TextSchreiben(graphics);
        }
        this.applet.AutorenbildZeichnen(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.TextUnschreiben(graphics);
        if (this.titel == this.highscoretitel) {
            this.highscoreindex = (this.highscoreindex + this.highscoreseite) % this.applet.highscores;
            this.HighscoreSchreiben(graphics);
            return;
        }
        this.TextSchreiben(graphics);
    }
    
    void HighscoreSchreiben(final Graphics graphics) {
        int n;
        if (this.applet.spielcanvas.punkte == this.applet.ohnepunkte) {
            n = this.highscoreyohnepunkte;
        }
        else {
            n = this.highscoreymitpunkte;
        }
        this.applet.MittigSchreiben(graphics, this.titeltext[this.highscoretitel][this.highscoretext], n);
        int n2 = n + 2 * this.applet.fonthoehe;
        for (int i = this.highscoreindex; i < this.highscoreindex + this.highscoreseite; ++i) {
            this.applet.MittigSchreiben(graphics, this.applet.highscorepanel.HighscoreString(i), n2);
            n2 += this.applet.fonthoehe;
        }
        if (this.applet.spielcanvas.punkte != this.applet.ohnepunkte) {
            this.applet.MittigSchreiben(graphics, this.titeltext[this.highscoretitel][this.yourscoretext] + this.applet.spielcanvas.punkte + this.applet.punktestring, n2 + this.applet.fonthoehe);
        }
    }
    
    void TextSchreiben(final Graphics graphics) {
        int n = this.applet.texty + (this.applet.texthoehe - this.titeltext[this.titel].length * this.applet.fonthoehe) / 2;
        for (int i = 0; i < this.titeltext[this.titel].length; ++i) {
            this.applet.MittigSchreiben(graphics, this.titeltext[this.titel][i], n);
            n += this.applet.fonthoehe;
        }
    }
    
    void TextUnschreiben(final Graphics graphics) {
        graphics.clearRect(0, this.applet.texty, this.applet.appletbreite, this.applet.texthoehe);
    }
    
    public void Titel() {
        this.applet.cardlayout.show(this.applet, "Titel");
        this.requestFocus();
        while (true) {
            this.catched = this.automatik;
            try {
                synchronized (this.applet.object) {
                    this.applet.object.wait(this.titelzeit);
                }
                // monitorexit(this.applet.object)
            }
            catch (InterruptedException ex) {}
            if (this.catched == this.spielstart) {
                break;
            }
            if (this.catched == this.automatik) {
                this.titel = ++this.titel % this.titeltext.length;
            }
            else {
                this.titel = this.catched;
            }
            this.repaint();
        }
        this.titel = this.highscoretitel;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 49) {
            this.catched = this.highscoretitel;
            this.highscoreindex = this.applet.highscores - this.highscoreseite;
        }
        else if (n == 50) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 0;
        }
        else if (n == 51) {
            this.catched = this.highscoretitel;
            this.highscoreindex = this.highscoreseite;
        }
        else if (n == 52) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 2 * this.highscoreseite;
        }
        else if (n == 53) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 3 * this.highscoreseite;
        }
        else if (n == 54) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 4 * this.highscoreseite;
        }
        else if (n == 55) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 5 * this.highscoreseite;
        }
        else if (n == 56) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 6 * this.highscoreseite;
        }
        else if (n == 57) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 7 * this.highscoreseite;
        }
        else if (n == 48) {
            this.catched = this.highscoretitel;
            this.highscoreindex = 8 * this.highscoreseite;
        }
        else if (n == 99) {
            this.catched = this.controlstitel;
        }
        else if (n == 105) {
            this.catched = this.instructionstitel;
        }
        else {
            this.catched = this.spielstart;
        }
        synchronized (this.applet.object) {
            this.applet.object.notifyAll();
        }
        // monitorexit(this.applet.object)
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.modifiers == 0) {
            this.catched = this.spielstart;
        }
        synchronized (this.applet.object) {
            this.applet.object.notifyAll();
        }
        // monitorexit(this.applet.object)
        return true;
    }
}
