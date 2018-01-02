import java.awt.Font;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GrowText extends Applet implements Runnable
{
    String TextString;
    String ParameterString;
    Color BackgroundColor;
    Color ForegroundColor;
    int Delay;
    int[] FontSizes;
    int FontSize;
    int FontIncDec;
    Thread killme;
    int i;
    int x_coord;
    int y_coord;
    String num;
    boolean threadSuspended;
    
    public void stop() {
        this.killme = null;
    }
    
    public void paint(final Graphics g) {
        g.drawString(this.TextString, 10, 35);
        this.getAppletContext().showStatus("GrowText (c) 1998 by Hans Peer http://ourworld.compuserve.com/homepages/hpeer/growtext.htm");
    }
    
    public Color GetColor(final String ColorString) {
        if (ColorString.compareTo("black") == 0) {
            return Color.black;
        }
        if (ColorString.compareTo("blue") == 0) {
            return Color.blue;
        }
        if (ColorString.compareTo("cyan") == 0) {
            return Color.cyan;
        }
        if (ColorString.compareTo("darkgray") == 0) {
            return Color.darkGray;
        }
        if (ColorString.compareTo("gray") == 0) {
            return Color.gray;
        }
        if (ColorString.compareTo("green") == 0) {
            return Color.green;
        }
        if (ColorString.compareTo("lightgray") == 0) {
            return Color.lightGray;
        }
        if (ColorString.compareTo("magenta") == 0) {
            return Color.magenta;
        }
        if (ColorString.compareTo("orange") == 0) {
            return Color.orange;
        }
        if (ColorString.compareTo("pink") == 0) {
            return Color.pink;
        }
        if (ColorString.compareTo("red") == 0) {
            return Color.red;
        }
        if (ColorString.compareTo("white") == 0) {
            return Color.white;
        }
        if (ColorString.compareTo("yellow") == 0) {
            return Color.yellow;
        }
        return Color.yellow;
    }
    
    public void start() {
        if (this.killme == null) {
            (this.killme = new Thread(this)).start();
        }
    }
    
    public GrowText() {
        this.FontSizes = new int[] { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48 };
        this.FontIncDec = 1;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (this.threadSuspended) {
            this.killme.resume();
        }
        else {
            this.killme.suspend();
        }
        this.threadSuspended = !this.threadSuspended;
        return true;
    }
    
    public void run() {
        while (this.killme != null) {
            try {
                Thread.sleep(this.Delay);
            }
            catch (InterruptedException ex) {}
            this.setFont(new Font("TimesRoman", 1, this.FontSizes[this.FontSize]));
            if (this.FontIncDec == 1) {
                ++this.FontSize;
                if (this.FontSize >= 14) {
                    this.FontIncDec = 2;
                }
            }
            else {
                --this.FontSize;
                if (this.FontSize == 0) {
                    this.FontIncDec = 1;
                }
            }
            this.repaint();
        }
        this.killme = null;
    }
    
    public void init() {
        this.setFont(new Font("TimesRoman", 1, this.FontSizes[0]));
        this.TextString = this.getParameter("text");
        if (this.TextString == null) {
            this.TextString = "GrowText (c) 1998 by Hans Peer";
        }
        this.ParameterString = this.getParameter("backgroundcolor");
        if (this.ParameterString == null) {
            this.BackgroundColor = Color.black;
        }
        else {
            this.BackgroundColor = this.GetColor(this.ParameterString);
        }
        this.ParameterString = this.getParameter("foregroundcolor");
        if (this.ParameterString == null) {
            this.ForegroundColor = Color.yellow;
        }
        else {
            this.ForegroundColor = this.GetColor(this.ParameterString);
        }
        this.ParameterString = this.getParameter("delay");
        if (this.ParameterString == null) {
            this.Delay = 100;
        }
        else {
            this.Delay = Integer.parseInt(this.ParameterString);
        }
        this.setBackground(this.BackgroundColor);
        this.setForeground(this.ForegroundColor);
    }
}
