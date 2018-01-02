import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class zDancingText extends Applet implements Runnable
{
    Thread mythread;
    Image offI;
    Graphics offG;
    String s;
    String bc;
    String fs;
    String tc;
    FontMetrics fm;
    int xpos;
    int i;
    int yj;
    int c;
    Font font;
    Color tcolor;
    Color bcolor;
    StringTokenizer st;
    StringTokenizer tt;
    
    public void init() {
        this.bc = this.getParameter("backgroundcolor");
        this.st = new StringTokenizer(this.bc, ",");
        this.setBackground(this.bcolor = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken())));
        this.tc = this.getParameter("textcolor");
        this.tt = new StringTokenizer(this.tc, ",");
        this.tcolor = new Color(Integer.parseInt(this.tt.nextToken()), Integer.parseInt(this.tt.nextToken()), Integer.parseInt(this.tt.nextToken()));
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.s = this.getParameter("text");
        this.xpos = 20;
        this.fs = this.getParameter("fontsize");
        this.font = new Font("TimesRoman", 1, Integer.parseInt(this.fs));
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        this.go();
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offI, 0, 0, this);
    }
    
    public void go() {
        while (true) {
            this.showStatus("Zmei Dancing Text Applet by Plamen Gelev-Unregistered");
            this.i = 0;
            this.xpos = 20;
            try {
                Thread.sleep(100L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                for (int j = 0; j < this.s.length(); ++j) {
                    this.yj = (int)(Math.random() * 5.0);
                    this.offG.setColor(this.tcolor);
                    this.offG.setFont(this.font);
                    this.fm = this.offG.getFontMetrics();
                    this.offG.drawString(this.s.substring(j, j + 1), 20 + this.i, this.size().height / 2 + 10 - this.yj);
                    this.i = this.fm.stringWidth(this.s.substring(0, j + 1));
                }
                this.repaint();
            }
            catch (Exception ex) {}
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
