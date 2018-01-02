import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.StringTokenizer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnimText extends Applet implements Runnable
{
    public static final int TYPE_BLINK = 1;
    public static final int TYPE_WAVE = 2;
    public static final int TYPE_RANDOM = 3;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_RIGHT = 3;
    char[] textChars;
    Thread thread;
    int type;
    int style;
    int defaultMin;
    int defaultMax;
    int max;
    int min;
    int defaultStep;
    int step;
    int align;
    String rgbDelimiter;
    StringTokenizer st;
    Color fgColor;
    Color bgColor;
    boolean threadSuspended;
    static final String defaultString = "Welcome to Java!";
    String fontString;
    Font[] fonts;
    int[] current;
    int[] direction;
    int[] charWidth;
    int charHeight;
    boolean resized;
    boolean readyToPaint;
    int naptime;
    int defaultNaptime;
    int Width;
    int Height;
    int defaultWidth;
    int defaultHeight;
    int maxWidth;
    int maxHeight;
    int n;
    Image offI;
    Graphics offG;
    int totalWidth;
    int leader;
    
    public void init() {
        String parameter = this.getParameter("text");
        if (parameter == null) {
            parameter = "Welcome to Java!";
        }
        this.textChars = new char[parameter.length()];
        parameter.getChars(0, parameter.length(), this.textChars, 0);
        final String parameter2 = this.getParameter("font");
        if (parameter2 == null) {
            this.fontString = "TimesRoman";
        }
        else if (parameter2.equalsIgnoreCase("TimesRoman")) {
            this.fontString = "TimesRoman";
        }
        else if (parameter2.equalsIgnoreCase("Courier")) {
            this.fontString = "Courier";
        }
        else if (parameter2.equalsIgnoreCase("Helvetica")) {
            this.fontString = "Helvetica";
        }
        else if (parameter2.equalsIgnoreCase("Dialog")) {
            this.fontString = "Dialog";
        }
        else {
            this.fontString = "TimesRoman";
        }
        final String parameter3 = this.getParameter("style");
        if (parameter3 == null) {
            this.style = 0;
        }
        else if (parameter3.equalsIgnoreCase("PLAIN")) {
            this.style = 0;
        }
        else if (parameter3.equalsIgnoreCase("BOLD")) {
            this.style = 1;
        }
        else if (parameter3.equalsIgnoreCase("ITALIC")) {
            this.style = 2;
        }
        else {
            this.style = 0;
        }
        final String parameter4 = this.getParameter("type");
        if (parameter4 == null) {
            this.type = 2;
        }
        else if (parameter4.equalsIgnoreCase("blink")) {
            this.type = 1;
        }
        else if (parameter4.equalsIgnoreCase("wave")) {
            this.type = 2;
        }
        else if (parameter4.equalsIgnoreCase("random")) {
            this.type = 3;
        }
        else {
            this.type = 2;
        }
        final String parameter5 = this.getParameter("align");
        if (parameter5 == null) {
            this.align = 2;
        }
        else if (parameter5.equalsIgnoreCase("left")) {
            this.align = 1;
        }
        else if (parameter5.equalsIgnoreCase("center")) {
            this.align = 2;
        }
        else if (parameter5.equalsIgnoreCase("right")) {
            this.align = 3;
        }
        else {
            this.align = 2;
        }
        try {
            this.Width = new Integer(this.getParameter("width"));
        }
        catch (Exception ex) {
            this.Width = this.defaultWidth;
        }
        try {
            this.Height = new Integer(this.getParameter("height"));
        }
        catch (Exception ex2) {
            this.Height = this.defaultHeight;
        }
        try {
            this.min = new Integer(this.getParameter("min"));
        }
        catch (Exception ex3) {
            this.min = this.defaultMin;
        }
        try {
            this.max = new Integer(this.getParameter("max"));
        }
        catch (Exception ex4) {
            this.max = this.defaultMax;
        }
        if (this.min >= this.max || this.min <= 0) {
            this.min = this.defaultMin;
            this.max = this.defaultMax;
        }
        try {
            this.step = new Integer(this.getParameter("step"));
        }
        catch (Exception ex5) {
            this.step = this.defaultStep;
        }
        if (this.step > (this.max - this.min) / 2) {
            this.step = this.defaultStep;
        }
        try {
            this.naptime = new Integer(this.getParameter("naptime"));
        }
        catch (Exception ex6) {
            this.naptime = this.defaultNaptime;
        }
        if (this.naptime <= 0) {
            this.naptime = this.defaultNaptime;
        }
        final String parameter6 = this.getParameter("fgColor");
        if (parameter6 != null) {
            this.st = new StringTokenizer(parameter6, this.rgbDelimiter);
        }
        if (parameter6 == null) {
            this.fgColor = Color.black;
        }
        else if (parameter6.equalsIgnoreCase("red")) {
            this.fgColor = Color.red;
        }
        else if (parameter6.equalsIgnoreCase("blue")) {
            this.fgColor = Color.blue;
        }
        else if (parameter6.equalsIgnoreCase("green")) {
            this.fgColor = Color.green;
        }
        else if (parameter6.equalsIgnoreCase("yellow")) {
            this.fgColor = Color.yellow;
        }
        else if (parameter6.equalsIgnoreCase("white")) {
            this.fgColor = Color.white;
        }
        else if (parameter6.equalsIgnoreCase("orange")) {
            this.fgColor = Color.orange;
        }
        else if (parameter6.equalsIgnoreCase("cyan")) {
            this.fgColor = Color.cyan;
        }
        else if (parameter6.equalsIgnoreCase("magenta")) {
            this.fgColor = Color.magenta;
        }
        else if (this.st.countTokens() == 3) {
            this.fgColor = new Color(new Integer(this.st.nextToken()), new Integer(this.st.nextToken()), new Integer(this.st.nextToken()));
        }
        else {
            this.fgColor = Color.black;
        }
        final String parameter7 = this.getParameter("bgColor");
        if (parameter7 != null) {
            this.st = new StringTokenizer(parameter7, this.rgbDelimiter);
        }
        if (parameter7 == null) {
            this.bgColor = Color.lightGray;
        }
        else if (parameter7.equalsIgnoreCase("red")) {
            this.bgColor = Color.red;
        }
        else if (parameter7.equalsIgnoreCase("blue")) {
            this.bgColor = Color.blue;
        }
        else if (parameter7.equalsIgnoreCase("green")) {
            this.bgColor = Color.green;
        }
        else if (parameter7.equalsIgnoreCase("yellow")) {
            this.bgColor = Color.yellow;
        }
        else if (parameter7.equalsIgnoreCase("white")) {
            this.bgColor = Color.white;
        }
        else if (parameter7.equalsIgnoreCase("orange")) {
            this.bgColor = Color.orange;
        }
        else if (parameter7.equalsIgnoreCase("cyan")) {
            this.bgColor = Color.cyan;
        }
        else if (parameter7.equalsIgnoreCase("magenta")) {
            this.bgColor = Color.magenta;
        }
        else if (this.st.countTokens() == 3) {
            this.bgColor = new Color(new Integer(this.st.nextToken()), new Integer(this.st.nextToken()), new Integer(this.st.nextToken()));
        }
        else {
            this.bgColor = Color.lightGray;
        }
        this.n = this.max - this.min;
        if (this.n > 0) {
            this.fonts = new Font[this.n];
            this.current = new int[this.textChars.length];
            this.direction = new int[this.textChars.length];
            this.charWidth = new int[this.textChars.length];
        }
        for (int i = 0; i < this.n; ++i) {
            this.fonts[i] = new Font(this.fontString, this.style, this.min + i);
        }
        for (int j = 0; j < this.textChars.length; ++j) {
            switch (this.type) {
                case 1: {
                    this.current[j] = 0;
                    this.direction[j] = 1;
                    break;
                }
                case 2: {
                    this.current[j] = (int)(Math.sin(j / this.textChars.length * 3.141592653589793) * (this.n - 1));
                    this.direction[j] = 1;
                    break;
                }
                case 3: {
                    this.current[j] = (int)(Math.random() * this.n);
                    this.direction[j] = 1;
                    break;
                }
            }
            if (this.current[j] >= this.n - 1) {
                this.direction[j] = -1;
            }
        }
        try {
            this.offI = this.createImage(this.maxWidth, this.maxHeight);
            this.offG = this.offI.getGraphics();
        }
        catch (Exception ex7) {
            this.offG = null;
        }
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.n > 0 && this.thread != null) {
            this.repaint();
            try {
                Thread.sleep(this.naptime);
            }
            catch (Exception ex) {}
            this.readyToPaint = false;
            this.next();
            this.readyToPaint = true;
        }
    }
    
    public void next() {
        for (int i = 0; i < this.textChars.length; ++i) {
            final int[] current = this.current;
            final int n = i;
            current[n] += this.step * this.direction[i];
            if (this.current[i] >= this.n - 1) {
                this.current[i] = 2 * this.n - 2 - this.current[i];
                this.direction[i] = -1;
            }
            if (this.current[i] <= 0) {
                this.current[i] = Math.abs(this.current[i]);
                this.direction[i] = 1;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.readyToPaint) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.offG != null) {
            this.paintApplet(this.offG);
            graphics.drawImage(this.offI, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        if (!this.resized) {
            this.totalWidth = 0;
            graphics.setFont(this.fonts[this.n - 1]);
            for (int i = 0; i < this.textChars.length; ++i) {
                this.charWidth[i] = graphics.getFontMetrics().charWidth(this.textChars[i]);
                this.totalWidth += this.charWidth[i];
            }
            if (this.totalWidth > this.maxWidth) {
                this.totalWidth = this.maxWidth;
            }
            this.charHeight = graphics.getFontMetrics().getHeight();
            if (this.charHeight > this.maxHeight) {
                this.charHeight = this.maxHeight;
            }
            this.resize(this.Width, this.Height);
            this.resized = true;
        }
        int leader = 0;
        switch (this.align) {
            case 1: {
                leader = this.leader;
                break;
            }
            case 2: {
                leader = (this.size().width - this.totalWidth) / 2;
                break;
            }
            case 3: {
                leader = this.size().width - this.totalWidth - this.leader;
                break;
            }
        }
        graphics.setColor(this.bgColor);
        graphics.fillRect(0, 0, this.size().width - 1, this.size().height - 1);
        graphics.setColor(this.fgColor);
        for (int j = 0; j < this.textChars.length; ++j) {
            graphics.setFont(this.fonts[this.current[j]]);
            graphics.drawChars(this.textChars, j, 1, leader, (this.size().height + this.charHeight) / 2);
            leader += this.charWidth[j];
        }
    }
    
    public void stop() {
        this.thread = null;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.threadSuspended) {
            this.thread.resume();
        }
        else {
            this.thread.suspend();
        }
        this.threadSuspended = !this.threadSuspended;
        return true;
    }
    
    public AnimText() {
        this.defaultMin = 8;
        this.defaultMax = 28;
        this.defaultStep = 2;
        this.rgbDelimiter = ":,.";
        this.threadSuspended = false;
        this.resized = false;
        this.readyToPaint = true;
        this.defaultNaptime = 100;
        this.defaultWidth = 300;
        this.defaultHeight = 100;
        this.maxWidth = 600;
        this.maxHeight = 400;
        this.leader = 10;
    }
}
