import java.awt.Graphics;
import java.awt.Color;
import java.util.GregorianCalendar;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Timeline extends Canvas implements Runnable
{
    private Iqapplet iq;
    static Thread messageThread;
    Screen sc;
    Iqquiz frame;
    GregorianCalendar calendar;
    boolean running;
    Color fontkleur;
    Color timekleur;
    int red;
    int green;
    int blue;
    int uurend;
    int minend;
    int secend;
    int uurstarted;
    int minstarted;
    int secstarted;
    int uur1;
    int min1;
    int sec1;
    String stopstring;
    
    public Timeline(final Screen sc, final Iqapplet iq, final Iqquiz frame) {
        this.running = true;
        this.uur1 = 0;
        this.min1 = 0;
        this.sec1 = 0;
        this.stopstring = "Ready to start";
        this.sc = sc;
        this.iq = iq;
        this.frame = frame;
        this.calendar = new GregorianCalendar();
        this.red = ((iq.getParameter("fontred") == null) ? 255 : Integer.parseInt(iq.getParameter("fontred")));
        this.green = ((iq.getParameter("fontgreen") == null) ? 255 : Integer.parseInt(iq.getParameter("fontgreen")));
        this.blue = ((iq.getParameter("fontblue") == null) ? 0 : Integer.parseInt(iq.getParameter("fontblue")));
        this.fontkleur = new Color(this.red, this.green, this.blue);
        this.red = ((iq.getParameter("timered") == null) ? 255 : Integer.parseInt(iq.getParameter("timered")));
        this.green = ((iq.getParameter("timegreen") == null) ? 0 : Integer.parseInt(iq.getParameter("timegreen")));
        this.blue = ((iq.getParameter("timeblue") == null) ? 0 : Integer.parseInt(iq.getParameter("timeblue")));
        this.timekleur = new Color(this.red, this.green, this.blue);
        if (Timeline.messageThread == null) {
            Timeline.messageThread = new Thread(this);
        }
    }
    
    public void run() {
        while (true) {
            this.stopstring = "Running";
            ++this.sec1;
            if (this.sec1 > 59) {
                ++this.min1;
                this.sec1 = 0;
            }
            if (this.min1 == 10) {
                this.stopstring = "Time Up";
                this.frame.timeup = true;
                this.repaint();
                this.frame.stoppressed = true;
                this.frame.checkanswer();
                this.sc.repaint();
                Timeline.messageThread.suspend();
            }
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void volgende() {
        if (this.sc.succes) {
            this.sc.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString("MINUTE: " + (9 - this.min1), 20, 20);
        graphics.drawString("SECOND: " + (60 - this.sec1), 20, 40);
        graphics.drawString(this.stopstring, 20, 60);
    }
    
    public void Clear() {
        this.running = true;
        Timeline.messageThread.resume();
        this.sc.succes = false;
    }
    
    public void stopff() {
    }
    
    public void opnieuw() {
        this.frame.next.setLabel("Next");
        this.running = true;
    }
}
