import java.awt.Font;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class SG1_Status extends Canvas implements Runnable
{
    Thread thisThread;
    String[] message;
    String string;
    StringBuffer sb;
    Color c1;
    Color c2;
    boolean changed;
    int controler;
    int w;
    int h;
    int sl;
    AudioClip ts;
    
    public void paint(final Graphics g) {
        g.setColor(this.c1);
        g.fill3DRect(0, 0, this.w, this.h, false);
        g.setColor(this.c2);
        g.drawString(this.sb.toString(), 5, 12);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public SG1_Status(final Color clr1, final Color clr2, final int wdt, final int hgt, final AudioClip acp) {
        this.message = new String[10];
        this.changed = true;
        this.setFont(new Font("Arial", 1, 10));
        this.w = wdt;
        this.h = hgt;
        this.c1 = clr1;
        this.c2 = clr2;
        this.ts = acp;
        this.message[0] = "Ready, click \"New\" to begin";
        this.message[1] = "Type recipient email address and press enter";
        this.message[2] = "Type your e-mail address and press enter";
        this.message[3] = "Type your name and surname and prass enter";
        this.message[4] = "Type subject and press enter";
        this.message[5] = "Edit message and click Send button";
        this.message[6] = "To send message click \"Yes\"";
        this.message[7] = "Sending message,please wait...";
        this.message[8] = "Done";
        this.message[9] = "!!! Malformed e-mail address !!!";
        (this.thisThread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            if (this.changed) {
                this.string = this.message[this.controler];
                this.sb = new StringBuffer();
                this.sl = this.string.length();
                this.changed = false;
                for (int i = 0; i < this.sl; ++i) {
                    this.sb.append(this.string.charAt(i));
                    this.repaint();
                    this.ts.play();
                    try {
                        Thread.sleep(20L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex2) {}
        }
    }
}
