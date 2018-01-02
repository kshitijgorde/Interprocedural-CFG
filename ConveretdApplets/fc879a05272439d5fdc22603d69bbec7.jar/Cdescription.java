import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Cdescription extends Canvas implements Runnable
{
    private Graphics gbuf;
    private Image buf;
    private Image bg;
    private GConsole console;
    private Game gm;
    private Color textcolor;
    private Font textfont;
    private boolean info;
    private Thread desThread;
    private int findex;
    private boolean stopped;
    private boolean run;
    private boolean ad;
    
    public Cdescription(final GConsole console, final int n, final int n2) {
        this.info = true;
        this.stopped = true;
        this.console = console;
        this.buf = console.createImage(n, n2);
        this.gbuf = this.buf.getGraphics();
        this.textcolor = new Color(255, 255, 255);
        this.textfont = new Font("Arial", 0, 12);
        this.info = true;
        (this.desThread = new Thread(this)).setName("Description Thread");
    }
    
    public void paint(final Graphics graphics) {
        this.gbuf.setColor(this.textcolor);
        this.gbuf.setFont(this.textfont);
        if (this.bg != null) {
            this.gbuf.drawImage(this.bg, 0, 0, this);
        }
        if (this.info) {
            this.gbuf.drawString("RealApplets Game Console", 10, 20);
            this.gbuf.drawString("Design: Carl Nollet", 10, 35);
            this.gbuf.drawString("Coding: Bavo Bruylandt", 10, 50);
            this.gbuf.drawString("Select a game with the", 10, 70);
            this.gbuf.drawString("Up and Down arrows.", 10, 85);
            this.gbuf.drawString(String.valueOf(this.console.numberOfGames()) + " games available!", 10, 105);
            this.gbuf.drawString("Demo version", 30, 150);
        }
        else if (this.ad) {
            this.gbuf.drawString("Vistit RealApplets for", 10, 20);
            this.gbuf.drawString("Free Java Applets", 10, 35);
            this.gbuf.drawString("Get your own console now!", 10, 50);
            this.gbuf.drawString("UNREGISTERED VERSION", 5, 100);
            this.gbuf.drawString("Register to remove these", 5, 115);
            this.gbuf.drawString("messages and extra games!", 5, 130);
            this.gbuf.drawString("Full version has 30 games.", 5, 145);
        }
        else {
            int n = 0;
            if (this.gm != null) {
                this.gm.resetIterator();
            }
            while (this.gm != null && this.gm.hasNextLine()) {
                n += 14;
                int n2 = this.findex;
                final String nextLine = this.gm.nextLine();
                if (n2 > nextLine.length()) {
                    n2 = nextLine.length();
                }
                this.gbuf.drawString(nextLine.substring(0, n2), 10, 20 + n);
            }
        }
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    public void quit() {
        this.stopped = false;
        this.run = false;
        this.desThread = null;
    }
    
    public void run() {
        this.stopped = false;
        this.findex = 0;
        while (this.run) {
            ++this.findex;
            try {
                Thread.sleep(20L);
                this.repaint();
            }
            catch (Exception ex) {
                this.run = false;
            }
            if (this.findex > 40) {
                this.run = false;
            }
        }
        this.stopped = true;
    }
    
    public void setImage(final Image bg) {
        this.bg = bg;
    }
    
    public void showAd() {
        this.info = false;
        this.ad = true;
        this.repaint();
    }
    
    public void showDescription() {
        this.ad = false;
        this.info = false;
        (this.gm = this.console.getSelectedGame()).resetIterator();
        this.run = true;
        if (this.stopped) {
            (this.desThread = new Thread(this)).setName("Description Thread");
            this.desThread.start();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
