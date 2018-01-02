import java.awt.image.ImageObserver;
import java.util.Date;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class DigitalClock2ech extends Applet implements Runnable
{
    AppletUtil3 aut;
    boolean countdown;
    boolean twentyfour;
    FontMetrics fm;
    Graphics bufferG;
    Image bufferI;
    Image background;
    int tzoffset;
    int width;
    int height;
    int speed;
    int tx;
    int ty;
    int dp;
    long countdown_time;
    Thread woohoo;
    String align;
    String esc;
    String param;
    String before_message;
    String time_string;
    String time_string_start;
    String after_message;
    String wd_abrevs;
    String mn_abrevs;
    String[] weekdays;
    String[] months;
    
    public void init() {
        this.aut = new AppletUtil3(this);
        this.width = this.size().width;
        this.height = this.size().height;
        this.weekdays[0] = "Sun";
        this.weekdays[1] = "Mon";
        this.weekdays[2] = "Tue";
        this.weekdays[3] = "Wed";
        this.weekdays[4] = "Thu";
        this.weekdays[5] = "Fri";
        this.weekdays[6] = "Sat";
        this.months[0] = "Jan";
        this.months[1] = "Feb";
        this.months[2] = "Mar";
        this.months[3] = "Apr";
        this.months[4] = "May";
        this.months[5] = "Jun";
        this.months[6] = "Jul";
        this.months[7] = "Aug";
        this.months[8] = "Sep";
        this.months[9] = "Oct";
        this.months[10] = "Nov";
        this.months[11] = "Dec";
        this.setBackground(this.aut.makeColor(this.getParameter("BGCOLOR"), Color.lightGray));
        this.setForeground(this.aut.makeColor(this.getParameter("FGCOLOR"), Color.black));
        this.setFont(this.aut.getFont());
        this.background = this.aut.getImage(this.getParameter("BACKGROUND"));
        if (this.getParameter("ESCAPE.CHARACTER") != null) {
            this.esc = this.getParameter("ESCAPE.CHARACTER");
        }
        if (this.getParameter("ALIGN") != null) {
            this.align = this.getParameter("ALIGN").toLowerCase();
        }
        if (this.getParameter("WEEKDAYS") != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("WEEKDAYS"));
            for (int n = 0; n < 7 && stringTokenizer.hasMoreTokens(); ++n) {
                this.weekdays[n] = stringTokenizer.nextToken();
            }
        }
        if (this.getParameter("MONTHS") != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("MONTHS"));
            for (int n2 = 0; n2 < 11 && stringTokenizer2.hasMoreTokens(); ++n2) {
                this.months[n2] = stringTokenizer2.nextToken();
            }
        }
        this.param = this.getParameter("COUNTDOWN.TIME");
        if (this.param != null) {
            this.countdown = true;
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.param, ", \t");
            this.countdown_time = new Date(this.aut.getRandom(stringTokenizer3.nextToken()), this.aut.getRandom(stringTokenizer3.nextToken()), this.aut.getRandom(stringTokenizer3.nextToken()), this.aut.getRandom(stringTokenizer3.nextToken()), this.aut.getRandom(stringTokenizer3.nextToken()), this.aut.getRandom(stringTokenizer3.nextToken())).getTime();
            this.param = this.getParameter("COUNTDOWN.BEFORE");
            this.before_message = ((this.param == null) ? ("Countdown: " + this.esc("day") + ", " + this.esc("hour") + ":" + this.esc("min") + ":" + this.esc("sec")) : this.param);
            this.param = this.getParameter("COUNTDOWN.AFTER");
            this.after_message = ((this.param == null) ? ("Your time passed " + this.esc("day") + " days, " + this.esc("hour") + " hours, " + this.esc("min") + " minutes, and " + this.esc("sec") + " seconds ago.") : this.param);
        }
        if (this.getParameter("SPEED") != null) {
            this.speed = this.aut.getRandom(this.getParameter("SPEED"));
        }
        if (this.getParameter("DECIMAL.PLACES") != null) {
            this.dp = this.aut.getRandom(this.getParameter("DECIMAL.PLACES"));
        }
        this.param = this.getParameter("TIMEZONE.OFFSET");
        if (this.param != null) {
            this.tzoffset = Integer.parseInt(this.param);
        }
        this.param = this.getParameter("24HOUR");
        this.twentyfour = (this.param != null && new Boolean(this.param));
    }
    
    private String esc(final String s) {
        return this.esc + s + this.esc;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.woohoo == null) {
            this.woohoo = new Thread(this);
        }
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("DigitalClock applet, Copyright 1998, Eric Harshbarger")) {
            this.woohoo.start();
            return;
        }
        System.out.println("AUTHOR & COPYRIGHT parameters are incorrect.");
    }
    
    public void stop() {
        if (this.woohoo != null) {
            this.woohoo.stop();
            this.woohoo = null;
        }
    }
    
    public void destroy() {
        if (this.bufferG != null) {
            this.bufferG.dispose();
        }
    }
    
    public void run() {
        this.param = this.getParameter("TIME.DISPLAY");
        this.time_string_start = ((this.param == null) ? (this.esc("mday") + " " + this.esc("mon_name") + " " + this.esc("year") + " " + this.esc("hour") + ":" + this.esc("minute") + ":" + this.esc("sec")) : this.param);
        while (true) {
            this.time_string = this.time_string_start;
            final Date date = new Date();
            if (!this.countdown) {
                if (this.getParameter("TIMEZONE.OFFSET") != null) {
                    date.setTime(date.getTime() + (date.getTimezoneOffset() + this.tzoffset) * 60 * 1000);
                }
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("mday"), this.pad(date.getDate(), 2));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("wday"), this.weekdays[date.getDay()]);
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("year"), this.pad(date.getYear(), 2));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("mon_name"), this.months[date.getMonth()]);
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("mon"), this.pad(date.getMonth() + 1, 2));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("min"), this.pad(date.getMinutes(), 2));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("sec"), this.pad(date.getSeconds(), 2));
                if (!this.twentyfour) {
                    final int hours = date.getHours();
                    this.time_string = ((hours > 11) ? this.aut.replaceAll(this.time_string, this.esc("ampm"), "pm") : this.aut.replaceAll(this.time_string, this.esc("ampm"), "am"));
                    this.time_string = ((hours > 11) ? this.aut.replaceAll(this.time_string, this.esc("AMPM"), "PM") : this.aut.replaceAll(this.time_string, this.esc("AMPM"), "AM"));
                    this.time_string = this.aut.replaceAll(this.time_string, this.esc("hour"), String.valueOf((hours + 11) % 12 + 1));
                }
                else {
                    this.time_string = this.aut.replaceAll(this.time_string, this.esc("ampm"), "");
                    this.time_string = this.aut.replaceAll(this.time_string, this.esc("AMPM"), "");
                    this.time_string = this.aut.replaceAll(this.time_string, this.esc("hour"), this.pad(date.getHours(), 2));
                }
            }
            else {
                final long time = date.getTime();
                final long abs = Math.abs(this.countdown_time - time);
                final int n = (int)(abs / 86400000L);
                final long n2 = abs % 86400000L;
                final int n3 = (int)(n2 / 3600000L);
                final long n4 = n2 % 3600000L;
                final int n5 = (int)(n4 / 60000L);
                final long n6 = n4 % 60000L;
                final int n7 = (int)(n6 / 1000L);
                final long n8 = n6 % 1000L;
                this.time_string = ((this.countdown_time - time < 0L) ? this.after_message : this.before_message);
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("day"), String.valueOf(n));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("hour"), this.pad(n3, 2));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("min"), this.pad(n5, 2));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("sec"), this.pad(n7, 2));
                this.time_string = this.aut.replaceAll(this.time_string, this.esc("msec"), this.backPad((int)n8, this.dp));
            }
            this.repaint();
            try {
                Thread.sleep(this.speed);
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.bufferG == null) {
            this.bufferI = this.createImage(this.width, this.height);
            (this.bufferG = this.bufferI.getGraphics()).setFont(this.getFont());
            this.fm = this.bufferG.getFontMetrics();
            this.ty = this.fm.getHeight() - this.fm.getMaxDescent();
        }
        this.bufferG.setColor(this.getBackground());
        this.bufferG.fillRect(0, 0, this.width, this.height);
        if (this.background != null) {
            this.bufferG.drawImage(this.background, 0, 0, this);
        }
        this.bufferG.setColor(this.getForeground());
        if (this.align.startsWith("r")) {
            final int stringWidth = this.fm.stringWidth(this.time_string);
            this.width = stringWidth;
            this.tx = stringWidth;
        }
        else if (this.align.startsWith("l")) {
            this.tx = 0;
        }
        else {
            this.tx = (this.width - this.fm.stringWidth(this.time_string)) / 2;
        }
        this.bufferG.drawString(this.time_string, this.tx, this.ty);
        graphics.drawImage(this.bufferI, 0, 0, this);
    }
    
    private String pad(final int n) {
        return this.pad(n, 2);
    }
    
    private String pad(final int n, final int n2) {
        String string = "";
        for (int i = 0; i < n2; ++i) {
            string += "0";
        }
        final String string2 = string + n;
        return string2.substring(string2.length() - n2, string2.length());
    }
    
    private String backPad(final int n) {
        return this.backPad(n, 2);
    }
    
    private String backPad(final int n, final int n2) {
        String string = "";
        for (int i = 0; i < n2; ++i) {
            string += "0";
        }
        return (n + string).substring(0, n2);
    }
    
    public DigitalClock2ech() {
        this.countdown = false;
        this.twentyfour = false;
        this.speed = 900;
        this.dp = 2;
        this.align = "left";
        this.esc = "%";
        this.before_message = "";
        this.time_string = "";
        this.time_string_start = "";
        this.after_message = "";
        this.weekdays = new String[7];
        this.months = new String[12];
    }
}
