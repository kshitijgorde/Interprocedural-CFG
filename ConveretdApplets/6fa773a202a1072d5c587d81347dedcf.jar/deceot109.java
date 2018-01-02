import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class deceot109 extends Applet implements Runnable
{
    String versStr;
    Date dat;
    compute comp;
    Thread myThread;
    
    public void init() {
        this.dat = new Date();
        this.setBackground(Color.white);
        this.comp = new compute(this.dat);
        this.repaint();
    }
    
    public void start() {
        (this.myThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.myThread.stop();
    }
    
    public void run() {
        while (true) {
            this.dat = new Date();
            this.comp = new compute(this.dat);
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 14));
        g.setColor(Color.red);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        g.drawString(this.versStr, 30, 30);
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.black);
        this.comp.update(g);
    }
    
    public deceot109() {
        this.versStr = "Sun Declin. & EoT - 1.09";
    }
}
