import java.awt.image.ImageObserver;
import java.util.GregorianCalendar;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class stardate extends Applet implements Runnable
{
    Image buffer;
    Graphics doublebuffer;
    int radix;
    String imagename;
    String imagetype;
    int height;
    int width;
    String beforePoint;
    String afterPoint;
    boolean preWarp;
    Thread mythread;
    Image[] glyphs;
    Image point;
    Image Background;
    long yearFractionMultiplyer;
    long yearFractionPadder;
    long dayFractionMultiplyer;
    long dayFractionPadder;
    int precision;
    int yearPrecision;
    int i;
    static final int RRATE = 1000;
    GregorianCalendar warpStart;
    
    public void init() {
        try {
            this.radix = Integer.valueOf(this.getParameter("radix"));
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Unable to get radix - fallback to 10");
            this.radix = 10;
        }
        if (this.radix == 0) {
            this.radix = 10;
        }
        try {
            this.precision = Integer.valueOf(this.getParameter("precision"));
        }
        catch (Exception ex2) {
            System.out.println(ex2);
            System.out.println("Unable to get precision - fallback to 5");
            this.precision = 5;
        }
        if (this.precision <= 0) {
            this.precision = 5;
        }
        try {
            this.yearPrecision = Integer.valueOf(this.getParameter("yearPrecision"));
        }
        catch (Exception ex3) {
            System.out.println(ex3);
            System.out.println("Unable to get year fraction precision - fallback to 3");
            this.yearPrecision = 3;
        }
        if (this.precision <= 0) {
            this.yearPrecision = 3;
        }
        try {
            this.imagename = this.getParameter("imagename");
            if (this.imagename == null) {
                this.imagename = new String("stardate");
            }
        }
        catch (Exception ex4) {
            System.out.println(ex4);
            System.out.println("fallback to 'stardate'");
            this.imagename = new String("stardate");
        }
        if (this.imagename == null) {
            this.imagename = new String("stardate");
        }
        try {
            this.imagetype = this.getParameter("imageType");
            if (this.imagetype == null) {
                this.imagetype = new String(".gif");
            }
        }
        catch (Exception ex5) {
            System.out.println(ex5);
            System.out.println("fallback to '.gif'");
            this.imagetype = new String(".gif");
        }
        if (this.imagetype == null) {
            this.imagetype = new String(".gif");
        }
        this.yearFractionMultiplyer = 1L;
        this.i = 0;
        while (this.i < this.yearPrecision) {
            this.yearFractionMultiplyer *= this.radix;
            ++this.i;
        }
        this.yearFractionPadder = this.radix * this.yearFractionMultiplyer;
        this.dayFractionMultiplyer = 1L;
        this.i = 0;
        while (this.i < this.precision) {
            this.dayFractionMultiplyer *= this.radix;
            ++this.i;
        }
        this.dayFractionPadder = this.dayFractionMultiplyer * this.radix;
        this.showStatus("loading glyphs...");
        this.point = this.getImage(this.getCodeBase(), String.valueOf(this.imagename) + "P" + this.imagetype);
        System.out.println(String.valueOf(this.imagename) + "P" + this.imagetype);
        this.Background = this.getImage(this.getCodeBase(), String.valueOf(this.imagename) + "BG" + this.imagetype);
        this.glyphs = new Image[this.radix];
        this.i = 0;
        while (this.i < this.radix) {
            this.glyphs[this.i] = this.getImage(this.getCodeBase(), String.valueOf(this.imagename) + Integer.toString(this.i) + this.imagetype);
            ++this.i;
        }
        this.width = this.bounds().width;
        this.height = this.bounds().height;
        this.buffer = this.createImage(this.width, this.height);
        this.doublebuffer = this.buffer.getGraphics();
        this.showStatus("done...");
    }
    
    public String getAppletInfo() {
        return new String("Copyright by Konstantin Priblouda 1999, all rights reserved\nThis applet display current stardate in various formats\nThis applet is licensed to  you under GNU GPL ( www.gnu.org )");
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "radix", "int", " radix to be used in stardate display,defaults to 10" }, { "precision", "int", "how many digits use after point defaults to 5" }, { "yearPrecision", "int", "how many digits to represent year fraction - 3 is default" }, { "imagename", "String", "base name of a glyph image, defaults to 'stardate'" }, { "imageType", "String", "name extension to be used with image ( i.e '.gif' , '.jpg' - defaults to '.gif'" } };
    }
    
    public void paint(final Graphics graphics) {
        this.computeStardate();
        this.doublebuffer.drawImage(this.Background, 0, 0, this);
        int n = 0;
        for (int i = 0; i < this.beforePoint.length(); ++i) {
            final int digit = Character.digit(this.beforePoint.charAt(i), this.radix);
            this.doublebuffer.drawImage(this.glyphs[digit], n, 0, this);
            n += this.glyphs[digit].getWidth(this);
        }
        this.doublebuffer.drawImage(this.point, n, 0, this);
        int n2 = n + this.point.getWidth(this);
        for (int j = 0; j < this.afterPoint.length(); ++j) {
            final int digit2 = Character.digit(this.afterPoint.charAt(j), this.radix);
            this.doublebuffer.drawImage(this.glyphs[digit2], n2, 0, this);
            n2 += this.glyphs[digit2].getWidth(this);
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void computeStardate() {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int abs = gregorianCalendar.get(1) - this.warpStart.get(1);
        if (gregorianCalendar.before(this.warpStart)) {
            this.preWarp = true;
            abs = Math.abs(abs);
        }
        double n;
        if (gregorianCalendar.isLeapYear(gregorianCalendar.get(1))) {
            n = (gregorianCalendar.get(6) - 1) / 366.0;
        }
        else {
            n = (gregorianCalendar.get(6) - 1) / 365.0;
        }
        final double n2 = (gregorianCalendar.get(11) * 3600.0 + gregorianCalendar.get(12) * 60.0 + gregorianCalendar.get(13)) / 86400.0;
        this.beforePoint = String.valueOf(Integer.toString(abs, this.radix)) + Integer.toString((int)(Object)new Double(this.yearFractionMultiplyer * n + this.yearFractionPadder), this.radix).substring(2);
        this.afterPoint = Integer.toString((int)(Object)new Double(this.dayFractionMultiplyer * n2 + this.dayFractionPadder), this.radix).substring(2);
        if (this.afterPoint.length() > this.precision) {
            this.afterPoint = this.afterPoint.substring(0, this.precision);
        }
    }
    
    public void start() {
        this.showStatus("Stardate syncronized...");
        this.mythread = new Thread(this);
        if (this.mythread != null) {
            this.mythread.start();
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void stop() {
        this.showStatus("Quitting applet");
        if (this.mythread != null) {
            this.mythread.stop();
            this.mythread = null;
        }
    }
    
    public void destroy() {
        this.showStatus("Freeing resources");
    }
    
    public stardate() {
        this.preWarp = false;
        this.warpStart = new GregorianCalendar(2323, 0, 1);
    }
}
