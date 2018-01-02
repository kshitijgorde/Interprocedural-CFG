import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.MediaTracker;
import java.util.Date;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class dac extends Applet implements Runnable
{
    Graphics og;
    Image oi;
    Image photo;
    Thread t;
    int width;
    int height;
    int delay;
    Date currTime;
    String infile;
    String fontstyle;
    String datefontstyle;
    String time;
    String datew;
    String date;
    String datem;
    MediaTracker tracker;
    int panelstyle;
    int fontR;
    int fontG;
    int fontB;
    int fontsize;
    int timeH;
    int timeM;
    int timeS;
    int dateYY;
    int dateMM;
    int dateDD;
    int dateWW;
    int datefontR;
    int datefontG;
    int datefontB;
    int datefontsize;
    int fontbgR;
    int fontbgG;
    int fontbgB;
    int datefontbgR;
    int datefontbgG;
    int datefontbgB;
    int clockX;
    int clockY;
    int dx;
    int dy;
    int mstep;
    int alarmcount;
    int nalarms;
    int dateX;
    int dateY;
    int datemstep;
    int timewidth;
    int timeheight;
    int framewidth;
    int frameheight;
    int datewidth;
    int dateheight;
    int framewidth2;
    int frameheight2;
    boolean firstRound;
    boolean am;
    boolean motion;
    boolean datemotion;
    boolean alarmup;
    Font font;
    Font datefont;
    boolean loaded;
    Color fontcolor;
    Color fontbgcolor;
    Color datefontcolor;
    Color datefontbgcolor;
    dacalarm d;
    AudioClip alarmaudio;
    AudioClip timeaudio;
    String alarmau;
    String timeau;
    String[] days;
    String[] months;
    boolean copyright;
    
    public dac() {
        this.t = null;
        this.width = this.size().width;
        this.height = this.size().height;
        this.delay = 100;
        this.panelstyle = 0;
        this.timeH = 10;
        this.timeM = 20;
        this.timeS = 40;
        this.mstep = 2;
        this.alarmcount = 10;
        this.nalarms = 10;
        this.datemstep = 2;
        this.firstRound = true;
        this.am = true;
        this.motion = false;
        this.datemotion = false;
        this.alarmup = false;
        this.loaded = false;
        this.d = new dacalarm();
        this.days = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        this.months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.copyright = false;
    }
    
    public void init() {
        super.init();
        this.infile = this.getParameter("file");
        this.alarmau = this.getParameter("alarmfile");
        this.timeau = this.getParameter("timefile");
        this.delay = new Integer(this.getParameter("delay"));
        this.panelstyle = new Integer(this.getParameter("panelstyle"));
        final int[] int1 = this.parseInt(this.getParameter("clocklocation"));
        this.clockX = int1[0];
        this.clockY = int1[1];
        this.mstep = new Integer(this.getParameter("clockmotionstep"));
        this.fontstyle = this.getParameter("clockfontstyle");
        final int[] int2 = this.parseInt(this.getParameter("clockfontcolor"));
        this.fontR = int2[0];
        this.fontG = int2[1];
        this.fontB = int2[2];
        final int[] int3 = this.parseInt(this.getParameter("clockfontbgcolor"));
        this.fontbgR = int3[0];
        this.fontbgG = int3[1];
        this.fontbgB = int3[2];
        this.fontsize = new Integer(this.getParameter("clockfontsize"));
        final int[] int4 = this.parseInt(this.getParameter("datelocation"));
        this.dateX = int4[0];
        this.dateY = int4[1];
        this.datemstep = new Integer(this.getParameter("datemotionstep"));
        this.datefontstyle = this.getParameter("datefontstyle");
        final int[] int5 = this.parseInt(this.getParameter("datefontcolor"));
        this.datefontR = int5[0];
        this.datefontG = int5[1];
        this.datefontB = int5[2];
        final int[] int6 = this.parseInt(this.getParameter("datefontbgcolor"));
        this.datefontbgR = int6[0];
        this.datefontbgG = int6[1];
        this.datefontbgB = int6[2];
        this.datefontsize = new Integer(this.getParameter("datefontsize"));
        this.nalarms = new Integer(this.getParameter("alarmduration"));
        this.tracker = new MediaTracker(this);
        this.photo = this.getImage(this.getDocumentBase(), this.infile);
        this.tracker.addImage(this.photo, 0);
        this.waitForImage();
        this.width = this.photo.getWidth(this);
        this.height = this.photo.getHeight(this);
        this.resize(this.width, this.height);
        this.oi = this.createImage(this.width, this.height);
        this.og = this.oi.getGraphics();
        this.repaint();
        if (this.clockX < 0 || this.clockY < 0) {
            this.clockX = 2;
            this.clockY = this.height / 2 - 30;
            this.motion = true;
        }
        if (this.dateX < 0 || this.dateY < 0) {
            this.dateX = 2;
            this.dateY = this.height / 2 + 30;
            this.datemotion = true;
        }
        this.alarmaudio = this.getAudioClip(this.getDocumentBase(), this.alarmau);
        this.timeaudio = this.getAudioClip(this.getDocumentBase(), this.timeau);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.d.show();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.copyright = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.copyright = true;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.photo != null) {
            this.og.drawImage(this.photo, 0, 0, this);
        }
        if (!this.loaded) {
            this.og.setColor(Color.white);
            this.og.fillRect(0, 0, this.width - 1, this.height - 1);
            this.og.setColor(Color.blue);
            this.og.drawString("Loading...", 1, 40);
            this.loaded = true;
        }
        if (this.firstRound) {
            this.fontcolor = new Color(this.fontR, this.fontG, this.fontB);
            this.fontbgcolor = new Color(this.fontbgR, this.fontbgG, this.fontbgB);
            this.font = new Font(this.fontstyle, 1, this.fontsize);
            this.datefontcolor = new Color(this.datefontR, this.datefontG, this.datefontB);
            this.datefontbgcolor = new Color(this.datefontbgR, this.datefontbgG, this.datefontbgB);
            this.datefont = new Font(this.datefontstyle, 1, this.datefontsize);
            this.firstRound = false;
        }
        this.currTime = new Date();
        this.timeH = this.currTime.getHours();
        this.timeM = this.currTime.getMinutes();
        this.timeS = this.currTime.getSeconds();
        this.dateYY = this.currTime.getYear();
        if (this.dateYY > 90) {
            this.dateYY += 1900;
        }
        else {
            this.dateYY += 2000;
        }
        this.dateMM = this.currTime.getMonth();
        this.dateDD = this.currTime.getDate();
        this.dateWW = this.currTime.getDay();
        this.datem = this.months[this.dateMM];
        this.datew = this.days[this.dateWW];
        this.date = String.valueOf(this.dateYY) + "-" + this.dateMM + "-" + this.dateDD + " " + this.datew;
        this.date = String.valueOf(this.datew) + ", " + this.datem + " " + this.dateDD + ", " + this.dateYY;
        this.currTime = null;
        this.am = true;
        if (this.timeH > 12) {
            this.timeH -= 12;
            this.am = false;
        }
        if (this.timeH == 12) {
            this.am = false;
        }
        this.time = null;
        if (this.timeH < 10) {
            this.time = "0" + this.timeH;
        }
        else {
            this.time = String.valueOf(this.timeH);
        }
        if (this.timeM < 10) {
            this.time = String.valueOf(this.time) + ":0" + this.timeM;
        }
        else {
            this.time = String.valueOf(this.time) + ":" + this.timeM;
        }
        if (this.timeS < 10) {
            this.time = String.valueOf(this.time) + ":0" + this.timeS;
        }
        else {
            this.time = String.valueOf(this.time) + ":" + this.timeS;
        }
        if (this.am) {
            this.time = String.valueOf(this.time) + " am";
        }
        else {
            this.time = String.valueOf(this.time) + " pm";
        }
        this.og.setFont(this.datefont);
        this.datewidth = this.og.getFontMetrics().stringWidth(this.date);
        this.dateheight = this.og.getFontMetrics().getHeight();
        this.framewidth2 = this.datewidth + 6;
        this.frameheight2 = this.dateheight + 2;
        if (this.datemotion) {
            this.dx = (int)(Math.random() * (2 * this.datemstep + 1)) - this.datemstep;
            this.dy = (int)(Math.random() * (2 * this.datemstep + 1)) - this.datemstep;
            if (this.dateX + this.dx < 0 || this.dateX + this.dx + this.framewidth2 > this.width - 1) {
                this.dx = -this.dx;
            }
            if (this.dateY + this.dy < 0 || this.dateY + this.dy + this.frameheight2 > this.height - 1) {
                this.dy = -this.dy;
            }
            this.dateX += this.dx;
            this.dateY += this.dy;
        }
        this.og.setColor(this.datefontbgcolor);
        if (this.panelstyle == 0) {
            this.og.fillRoundRect(this.dateX, this.dateY, this.framewidth2 - 1, this.frameheight2 - 1, 4, 4);
        }
        else {
            this.og.drawRoundRect(this.dateX - 1, this.dateY, this.framewidth2 - 1, this.frameheight2 - 1, 4, 4);
            this.og.drawRoundRect(this.dateX + 1, this.dateY, this.framewidth2 - 1, this.frameheight2 - 1, 4, 4);
            this.og.drawRoundRect(this.dateX, this.dateY - 1, this.framewidth2 - 1, this.frameheight2 - 1, 4, 4);
            this.og.drawRoundRect(this.dateX, this.dateY + 1, this.framewidth2 - 1, this.frameheight2 - 1, 4, 4);
            this.og.drawString(this.date, this.dateX + 2, this.dateY + this.frameheight2 - 2 - this.og.getFontMetrics().getDescent());
            this.og.drawString(this.date, this.dateX + 4, this.dateY + this.frameheight2 - 2 - this.og.getFontMetrics().getDescent());
            this.og.drawString(this.date, this.dateX + 3, this.dateY + this.frameheight2 - 3 - this.og.getFontMetrics().getDescent());
            this.og.drawString(this.date, this.dateX + 3, this.dateY + this.frameheight2 - 1 - this.og.getFontMetrics().getDescent());
        }
        this.og.setColor(this.datefontcolor);
        this.og.drawRoundRect(this.dateX, this.dateY, this.framewidth2 - 1, this.frameheight2 - 1, 4, 4);
        this.og.drawString(this.date, this.dateX + 3, this.dateY + this.frameheight2 - 2 - this.og.getFontMetrics().getDescent());
        this.og.setFont(this.font);
        this.timewidth = this.og.getFontMetrics().stringWidth(this.time);
        this.timeheight = this.og.getFontMetrics().getHeight();
        this.framewidth = this.timewidth + 6;
        this.frameheight = this.timeheight + 2;
        if (this.motion) {
            this.dx = (int)(Math.random() * (2 * this.mstep + 1)) - this.mstep;
            this.dy = (int)(Math.random() * (2 * this.mstep + 1)) - this.mstep;
            if (this.clockX + this.dx < 0 || this.clockX + this.dx + this.framewidth > this.width - 1) {
                this.dx = -this.dx;
            }
            if (this.clockY + this.dy < 0 || this.clockY + this.dy + this.frameheight > this.height - 1) {
                this.dy = -this.dy;
            }
            this.clockX += this.dx;
            this.clockY += this.dy;
        }
        this.og.setColor(this.fontbgcolor);
        if (this.panelstyle == 0) {
            this.og.fillRoundRect(this.clockX, this.clockY, this.framewidth - 1, this.frameheight - 1, 6, 6);
        }
        else {
            this.og.drawRoundRect(this.clockX - 1, this.clockY, this.framewidth - 1, this.frameheight - 1, 6, 6);
            this.og.drawRoundRect(this.clockX + 1, this.clockY, this.framewidth - 1, this.frameheight - 1, 6, 6);
            this.og.drawRoundRect(this.clockX, this.clockY - 1, this.framewidth - 1, this.frameheight - 1, 6, 6);
            this.og.drawRoundRect(this.clockX, this.clockY + 1, this.framewidth - 1, this.frameheight - 1, 6, 6);
            this.og.drawString(this.time, this.clockX + 2, this.clockY + this.frameheight - 2 - this.og.getFontMetrics().getDescent());
            this.og.drawString(this.time, this.clockX + 4, this.clockY + this.frameheight - 2 - this.og.getFontMetrics().getDescent());
            this.og.drawString(this.time, this.clockX + 3, this.clockY + this.frameheight - 1 - this.og.getFontMetrics().getDescent());
            this.og.drawString(this.time, this.clockX + 3, this.clockY + this.frameheight - 3 - this.og.getFontMetrics().getDescent());
        }
        this.og.setColor(this.fontcolor);
        this.og.drawRoundRect(this.clockX, this.clockY, this.framewidth - 1, this.frameheight - 1, 6, 6);
        this.og.drawString(this.time, this.clockX + 3, this.clockY + this.frameheight - 2 - this.og.getFontMetrics().getDescent());
        if (this.copyright) {
            this.og.setFont(new Font("Helvetica", 0, 10));
            final String s = "C. Liu";
            final int n = this.og.getFontMetrics().stringWidth(s) + 4;
            this.og.getFontMetrics().getHeight();
            this.og.setColor(Color.blue);
            this.og.drawString(s, this.width - n, this.height - 5);
            this.og.drawString(s, this.width - n + 2, this.height - 3);
            this.og.drawString(s, this.width - n, this.height - 3);
            this.og.drawString(s, this.width - n + 2, this.height - 5);
            this.og.setColor(Color.yellow);
            this.og.drawString(s, this.width - n + 1, this.height - 4);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void run() {
        Thread.currentThread().setPriority(4);
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            if (this.timeM % 30 == 0 && this.timeS == 0) {
                this.timeaudio.play();
            }
            if (this.d.alarmset && this.timeH == this.d.alarmH && this.timeM == this.d.alarmM && this.timeS == this.d.alarmS) {
                this.alarmup = true;
                this.alarmcount = this.nalarms;
            }
            if (this.alarmcount > 0 && this.alarmup) {
                this.alarmaudio.loop();
                --this.alarmcount;
                if (this.alarmcount >= 1) {
                    continue;
                }
                this.alarmup = false;
            }
            else {
                this.alarmaudio.stop();
            }
        }
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.t.stop();
        this.t = null;
        if (this.alarmaudio != null) {
            this.alarmaudio.stop();
        }
        if (this.timeaudio != null) {
            this.timeaudio.stop();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void waitForImage() {
        while (!this.tracker.checkID(0, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
}
