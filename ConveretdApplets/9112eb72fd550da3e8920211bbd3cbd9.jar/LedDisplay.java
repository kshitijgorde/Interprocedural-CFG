import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class LedDisplay extends Canvas implements Runnable
{
    Digit h1;
    Digit h2;
    Digit m1;
    Digit m2;
    Digit s1;
    Digit s2;
    LedWorldTime lwa;
    int hour1;
    int hour2;
    int minutes1;
    int minutes2;
    int seconds1;
    int seconds2;
    WorldTime time;
    Thread displayThread;
    Image leds;
    Graphics gbuff;
    boolean useFade;
    int fadedelay;
    
    public LedDisplay(final LedWorldTime lwa) {
        System.out.println("LedDisplay Created");
        this.lwa = lwa;
        this.h1 = new Digit(lwa);
        this.h2 = new Digit(lwa);
        this.m1 = new Digit(lwa);
        this.m2 = new Digit(lwa);
        this.s1 = new Digit(lwa);
        this.s2 = new Digit(lwa);
        this.time = new WorldTime(Integer.parseInt(lwa.getParameter("Default_timezone")));
        this.leds = lwa.createImage(135, 30);
        this.fadedelay = Integer.parseInt(lwa.getParameter("fade_delay"));
        this.gbuff = this.leds.getGraphics();
        if (lwa.getParameter("use_fade").toUpperCase().equals("YES")) {
            this.useFade = true;
        }
        else {
            this.useFade = false;
        }
        if (this.displayThread == null) {
            (this.displayThread = new Thread(this)).start();
        }
    }
    
    public void getCurrentTime() {
        this.hour1 = this.time.getHour(1);
        this.hour2 = this.time.getHour(2);
        this.minutes1 = this.time.getMinutes(1);
        this.minutes2 = this.time.getMinutes(2);
        this.seconds1 = this.time.getSeconds(1);
        this.seconds2 = this.time.getSeconds(2);
    }
    
    public void paint(final Graphics graphics) {
        this.gbuff.setColor(this.lwa.bgcolor);
        this.gbuff.fillRect(0, 0, 135, 30);
        this.gbuff.setColor(this.lwa.inactiveColor);
        if (this.h1.hasChanged(this.hour1)) {
            this.gbuff.drawImage(this.h1.getDigit(this.hour1), 0, 0, this);
        }
        else {
            this.gbuff.drawImage(this.h1.getOldDigit(), 0, 0, this);
        }
        if (this.h2.hasChanged(this.hour2)) {
            this.gbuff.drawImage(this.h2.getDigit(this.hour2), 20, 0, this);
        }
        else {
            this.gbuff.drawImage(this.h2.getOldDigit(), 20, 0, this);
        }
        if (this.m1.hasChanged(this.minutes1)) {
            this.gbuff.drawImage(this.m1.getDigit(this.minutes1), 45, 0, this);
        }
        else {
            this.gbuff.drawImage(this.m1.getOldDigit(), 45, 0, this);
        }
        if (this.m2.hasChanged(this.minutes2)) {
            this.gbuff.drawImage(this.m2.getDigit(this.minutes2), 65, 0, this);
        }
        else {
            this.gbuff.drawImage(this.m2.getOldDigit(), 65, 0, this);
        }
        if (this.s1.hasChanged(this.seconds1)) {
            this.gbuff.drawImage(this.s1.getDigit(this.seconds1), 90, 0, this);
        }
        else {
            this.gbuff.drawImage(this.s1.getOldDigit(), 90, 0, this);
        }
        if (this.useFade) {
            if (this.s2.hasSecChanged(this.seconds2)) {
                this.gbuff.drawImage(this.s2.getSecDigit(this.seconds2), 110, 0, this);
            }
            else {
                this.gbuff.drawImage(this.s2.getOldDigit(), 110, 0, this);
            }
        }
        else if (this.s2.hasChanged(this.seconds2)) {
            this.gbuff.drawImage(this.s2.getDigit(this.seconds2), 110, 0, this);
        }
        else {
            this.gbuff.drawImage(this.s2.getOldDigit(), 110, 0, this);
        }
        this.gbuff.fillArc(37, 4, 4, 4, 0, 360);
        this.gbuff.fillArc(37, 16, 4, 4, 0, 360);
        graphics.drawImage(this.leds, 1, 0, this);
    }
    
    public void run() {
        System.out.println("Run called for first time");
        while (true) {
            this.getCurrentTime();
            this.repaint();
            try {
                Thread.sleep(this.fadedelay);
            }
            catch (Exception ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
