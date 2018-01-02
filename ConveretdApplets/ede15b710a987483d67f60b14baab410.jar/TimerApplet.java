import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TimerApplet extends Applet implements Runnable
{
    private String msg1;
    private String msg2;
    private Font bigFont;
    private int secs;
    private int mins;
    private int hrs;
    private Thread clock;
    
    public void stop() {
        this.clock.suspend();
    }
    
    public void paint(final Graphics gr) {
        ++this.secs;
        if (this.secs == 60) {
            ++this.mins;
            this.secs = 0;
        }
        if (this.mins == 60) {
            ++this.hrs;
            this.secs = 0;
            this.mins = 0;
        }
        gr.setFont(this.bigFont);
        gr.setColor(Color.red);
        gr.drawString(this.msg1, 10, 100);
        gr.drawString("    " + this.hrs + " Hours " + this.mins + " Minutes " + this.secs + " Seconds", 10, 130);
        gr.drawString(this.msg2, 10, 150);
        this.setBackground(Color.black);
    }
    
    public TimerApplet() {
        this.msg1 = new String("Your Visit To My Page Has Lasted");
        this.msg2 = new String("");
        this.bigFont = new Font("Arial", 1, 15);
    }
    
    public void destroy() {
        this.clock.stop();
    }
    
    public void start() {
        this.clock.resume();
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        String parameter = this.getParameter("msg2");
        if (parameter == null) {
            this.msg2 = "";
        }
        else {
            this.msg2 = parameter;
        }
        parameter = this.getParameter("msg1");
        if (parameter == null) {
            this.msg1 = "Your Visit To My Page Has Lasted";
        }
        else {
            this.msg1 = parameter;
        }
        if (this.clock == null) {
            (this.clock = new Thread(this)).start();
        }
    }
}
