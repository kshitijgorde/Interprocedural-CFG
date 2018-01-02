import java.util.Date;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Uhr extends Applet implements Runnable
{
    Thread thread;
    ParameterUtilities parameterUtilities;
    Polygon lastsecondpolygon;
    Polygon lastminutepolygon;
    Polygon lasthourpolygon;
    int center_x;
    int center_y;
    int timezoneoffset;
    private static final int LOCALTIMEZONEOFFSET;
    Color hourcolor;
    Color minutecolor;
    Color secondcolor;
    int hourhand;
    int minutehand;
    int secondhand;
    int none;
    int slide;
    int bound;
    int off;
    int on;
    String[] handvalues;
    String[] secondhandvalues;
    Color fifteenminutecolor;
    Color fiveminutecolor;
    Color oneminutecolor;
    int fifteenminute;
    int fiveminute;
    int oneminute;
    int diamonds;
    int lines;
    int numbers;
    String[] minutevalues;
    String[] oneminutevalues;
    Color dialcolor;
    Color dialedgecolor;
    int dial;
    int withedge;
    int withoutedge;
    String[] dialvalues;
    private static final int UNDEFINED = 65535;
    
    public void init() {
        this.thread = null;
        this.parameterUtilities = new ParameterUtilities(this);
        this.lastsecondpolygon = new Polygon();
        this.lastminutepolygon = new Polygon();
        this.lasthourpolygon = new Polygon();
        this.timezoneoffset = this.parameterUtilities.getIntegerParameter("timezone", 65535);
        this.secondhand = this.parameterUtilities.getStringArrayParameter("second hand", this.secondhandvalues, this.on);
        this.minutehand = this.parameterUtilities.getStringArrayParameter("minute hand", this.handvalues, this.bound);
        this.hourhand = this.parameterUtilities.getStringArrayParameter("hour hand", this.handvalues, this.slide);
        this.oneminute = this.parameterUtilities.getStringArrayParameter("1 minute", this.oneminutevalues, this.lines);
        this.fiveminute = this.parameterUtilities.getStringArrayParameter("5 minute", this.minutevalues, this.diamonds);
        this.fifteenminute = this.parameterUtilities.getStringArrayParameter("15 minute", this.minutevalues, this.diamonds);
        this.dial = this.parameterUtilities.getStringArrayParameter("dial", this.dialvalues, this.withedge);
        this.dialcolor = this.parameterUtilities.getColorParameter("dial color", Color.lightGray);
        this.dialedgecolor = this.parameterUtilities.getColorParameter("dial edge color", Color.darkGray);
        final Color colorParameter;
        if ((colorParameter = this.parameterUtilities.getColorParameter("background color", null)) != null) {
            this.setBackground(colorParameter);
        }
        this.secondcolor = this.parameterUtilities.getColorParameter("second hand color", Color.red);
        this.minutecolor = this.parameterUtilities.getColorParameter("minute hand color", this.hourcolor);
        this.hourcolor = this.parameterUtilities.getColorParameter("hour hand color", Color.black);
        this.oneminutecolor = this.parameterUtilities.getColorParameter("1 minute color", Color.darkGray);
        this.fiveminutecolor = this.parameterUtilities.getColorParameter("5 minute color", Color.white);
        this.fifteenminutecolor = this.parameterUtilities.getColorParameter("15 minute color", Color.black);
    }
    
    Polygon getDiamond(final int n, final double n2, final double n3, final double n4, final double n5) {
        final Polygon polygon = new Polygon();
        final double sin = Math.sin((n - n2) * 3.141592653589793 / 30.0);
        final double cos = Math.cos((n - n2) * 3.141592653589793 / 30.0);
        final double sin2 = Math.sin(n * 3.141592653589793 / 30.0);
        final double cos2 = Math.cos(n * 3.141592653589793 / 30.0);
        final double sin3 = Math.sin((n + n2) * 3.141592653589793 / 30.0);
        final double cos3 = Math.cos((n + n2) * 3.141592653589793 / 30.0);
        polygon.addPoint(this.center_x + (int)(this.center_x * n3 * sin2), this.center_y - (int)(this.center_y * n3 * cos2));
        polygon.addPoint(this.center_x + (int)(this.center_x * n5 * sin), this.center_y - (int)(this.center_y * n5 * cos));
        polygon.addPoint(this.center_x + (int)(this.center_x * n4 * sin2), this.center_y - (int)(this.center_y * n4 * cos2));
        polygon.addPoint(this.center_x + (int)(this.center_x * n5 * sin3), this.center_y - (int)(this.center_y * n5 * cos3));
        return polygon;
    }
    
    void drawNumber(final Graphics graphics, final int n, final int n2, final double n3, final double n4) {
        final Font font = new Font("TimesRoman", 0, (int)(Math.min(this.center_x, this.center_y) * n3));
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final String string = new Integer(n2).toString();
        final int n5 = this.center_x + (int)(this.center_x * n4 * Math.sin(n * 3.141592653589793 / 30.0));
        final int n6 = this.center_y - (int)(this.center_y * n4 * Math.cos(n * 3.141592653589793 / 30.0));
        final int n7 = n5 - fontMetrics.stringWidth(string) / 2;
        final int n8 = n6 + fontMetrics.getAscent() / 2;
        graphics.setFont(font);
        graphics.drawString(string, n7, n8);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        this.center_x = size.width / 2;
        this.center_y = size.height / 2;
        this.lastsecondpolygon = new Polygon();
        this.lastminutepolygon = new Polygon();
        this.lasthourpolygon = new Polygon();
        if (this.dial == this.withedge) {
            graphics.setColor(this.dialedgecolor);
            graphics.fillOval(0, 0, size.width, size.height);
            graphics.setColor(this.dialcolor);
            graphics.fillOval((int)(size.width * 0.02), (int)(size.height * 0.02), (int)(size.width * 0.96), (int)(size.height * 0.96));
        }
        else if (this.dial == this.withoutedge) {
            graphics.setColor(this.dialcolor);
            graphics.fillOval(0, 0, size.width, size.height);
        }
        for (int i = 0; i < 60; ++i) {
            if (i % 15 == 0 && this.fifteenminute == this.diamonds) {
                graphics.setColor(this.fifteenminutecolor);
                graphics.fillPolygon(this.getDiamond(i, 0.4, 1.0, 0.8, 0.9));
            }
            else if (i % 15 == 0 && this.fifteenminute == this.numbers) {
                graphics.setColor(this.fifteenminutecolor);
                this.drawNumber(graphics, i, (i == 0) ? 12 : (i / 5), 0.17, 0.9);
            }
            else if (i % 5 == 0 && this.fiveminute == this.diamonds) {
                graphics.setColor(this.fiveminutecolor);
                graphics.fillPolygon(this.getDiamond(i, 0.2, 0.95, 0.85, 0.9));
            }
            else if (i % 5 == 0 && this.fiveminute == this.numbers) {
                graphics.setColor(this.fiveminutecolor);
                this.drawNumber(graphics, i, (i == 0) ? 12 : (i / 5), 0.1, 0.9);
            }
            else if (this.oneminute == this.lines) {
                final double sin = Math.sin(i * 3.141592653589793 / 30.0);
                final double cos = Math.cos(i * 3.141592653589793 / 30.0);
                graphics.setColor(this.oneminutecolor);
                graphics.drawLine(this.center_x + (int)(this.center_x * 0.88 * sin), this.center_y - (int)(this.center_y * 0.88 * cos), this.center_x + (int)(this.center_x * 0.92 * sin), this.center_y - (int)(this.center_y * 0.92 * cos));
            }
            else if (this.oneminute == this.numbers) {
                graphics.setColor(this.oneminutecolor);
                this.drawNumber(graphics, i, i, 0.05, 0.9);
            }
        }
    }
    
    Polygon getHand(final double n, final double n2, final double n3, final double n4) {
        final Polygon polygon = new Polygon();
        final double sin = Math.sin(n * 3.141592653589793 / 30.0);
        final double cos = Math.cos(n * 3.141592653589793 / 30.0);
        polygon.addPoint(this.center_x + (int)(this.center_x * n2 * sin), this.center_y - (int)(this.center_y * n2 * cos));
        polygon.addPoint(this.center_x + (int)(this.center_x * n4 * cos), this.center_y + (int)(this.center_y * n4 * sin));
        polygon.addPoint(this.center_x - (int)(this.center_x * n3 * sin), this.center_y + (int)(this.center_y * n3 * cos));
        polygon.addPoint(this.center_x - (int)(this.center_x * n4 * cos), this.center_y - (int)(this.center_y * n4 * sin));
        return polygon;
    }
    
    void drawHand(final Graphics graphics, final Polygon polygon) {
        graphics.fillPolygon(polygon);
        graphics.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[2], polygon.ypoints[2]);
    }
    
    public void update(final Graphics graphics) {
        Polygon hand = new Polygon();
        Polygon hand2 = new Polygon();
        Polygon hand3 = new Polygon();
        double n = 0.0;
        final Date date = new Date();
        if (this.timezoneoffset != 65535) {
            date.setTime(date.getTime() + Uhr.LOCALTIMEZONEOFFSET * 60 * 1000 + this.timezoneoffset * 60 * 1000);
        }
        final double n2 = date.getSeconds();
        double n3;
        if (this.minutehand == this.slide) {
            n3 = date.getMinutes() + n2 / 60.0;
        }
        else {
            n3 = date.getMinutes();
        }
        if (this.hourhand == this.slide) {
            n = date.getHours() + n3 / 60.0;
        }
        if (this.hourhand == this.bound) {
            n = date.getHours();
        }
        if (this.hourhand != this.none) {
            hand = this.getHand(n * 5.0, 0.4, 0.1, 0.05);
        }
        if (this.minutehand != this.none) {
            hand2 = this.getHand(n3, 0.7, 0.2, 0.04);
        }
        if (this.secondhand == this.on) {
            hand3 = this.getHand(n2, 0.8, 0.3, 0.03);
        }
        if (this.dial != this.none) {
            graphics.setColor(this.dialcolor);
        }
        else {
            graphics.setColor(this.getBackground());
        }
        if (this.hourhand != this.none) {
            this.drawHand(graphics, this.lasthourpolygon);
        }
        if (this.minutehand != this.none) {
            this.drawHand(graphics, this.lastminutepolygon);
        }
        if (this.secondhand == this.on) {
            this.drawHand(graphics, this.lastsecondpolygon);
        }
        if (this.hourhand != this.none) {
            graphics.setColor(this.hourcolor);
            this.drawHand(graphics, hand);
        }
        if (this.minutehand != this.none) {
            graphics.setColor(this.minutecolor);
            this.drawHand(graphics, hand2);
        }
        if (this.secondhand == this.on) {
            graphics.setColor(this.secondcolor);
            this.drawHand(graphics, hand3);
        }
        this.lasthourpolygon = hand;
        this.lastminutepolygon = hand2;
        this.lastsecondpolygon = hand3;
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(1000L - System.currentTimeMillis() % 1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public Uhr() {
        this.slide = 1;
        this.bound = 2;
        this.on = 1;
        this.handvalues = new String[] { "none", "slide", "bound" };
        this.secondhandvalues = new String[] { "off", "on" };
        this.diamonds = 1;
        this.lines = 1;
        this.numbers = 2;
        this.minutevalues = new String[] { "none", "diamonds", "numbers" };
        this.oneminutevalues = new String[] { "none", "lines", "numbers" };
        this.withedge = 1;
        this.withoutedge = 2;
        this.dialvalues = new String[] { "none", "with edge", "without edge" };
    }
    
    static {
        LOCALTIMEZONEOFFSET = new Date().getTimezoneOffset();
    }
}
