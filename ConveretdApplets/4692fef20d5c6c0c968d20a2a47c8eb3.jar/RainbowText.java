import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RainbowText extends Applet implements Runnable
{
    private String textStr;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private Color bgColor;
    private int sleepTime;
    private String paramStr;
    private int strlen;
    private Thread runner;
    private char[] theChars;
    private int[] charOffsets;
    private Color[] colors;
    private int yOffset;
    private int phase;
    private Image offScreenImage;
    private Graphics offScreenG;
    private Font f;
    private FontMetrics fm;
    private boolean stopped;
    private String[][] pinfo;
    
    public void init() {
        int n = 20;
        this.paramStr = this.getParameter("bgcolor");
        if (this.paramStr == null) {
            this.bgColor = Color.black;
        }
        else {
            try {
                this.bgColor = new Color(Integer.parseInt(this.paramStr, 16));
            }
            catch (NumberFormatException ex) {
                this.bgColor = Color.black;
            }
        }
        this.setBackground(this.bgColor);
        this.textStr = this.getParameter("text");
        if (this.textStr == null) {
            this.textStr = "RainbowText";
        }
        this.fontName = this.getParameter("fontname");
        if (this.fontName == null) {
            this.fontName = "TimesRoman";
        }
        this.paramStr = this.getParameter("fontstyle");
        if (this.paramStr == null) {
            this.fontStyle = 0;
        }
        else if (this.paramStr.equals("B")) {
            this.fontStyle = 1;
        }
        else if (this.paramStr.equals("I")) {
            this.fontStyle = 2;
        }
        else if (this.paramStr.equals("BI")) {
            this.fontStyle = 3;
        }
        else {
            this.fontStyle = 0;
        }
        this.paramStr = this.getParameter("fontsize");
        if (this.paramStr == null) {
            this.fontSize = 36;
        }
        else {
            try {
                this.fontSize = Integer.parseInt(this.paramStr);
            }
            catch (Exception ex2) {
                this.fontSize = 36;
            }
        }
        this.paramStr = this.getParameter("sleeptime");
        if (this.paramStr == null) {
            this.sleepTime = 100;
        }
        else {
            try {
                this.sleepTime = Integer.parseInt(this.paramStr);
            }
            catch (Exception ex3) {
                this.sleepTime = 100;
            }
        }
        this.f = new Font(this.fontName, this.fontStyle, this.fontSize);
        this.fm = this.getFontMetrics(this.f);
        this.resize(40 + this.fm.stringWidth(this.textStr), 10 + this.fm.getHeight());
        this.yOffset = this.fm.getAscent() + 5;
        this.strlen = this.textStr.length();
        this.theChars = new char[this.strlen];
        this.charOffsets = new int[this.strlen];
        this.textStr.getChars(0, this.strlen, this.theChars, 0);
        this.colors = new Color[this.strlen];
        for (int i = 0; i < this.strlen; ++i) {
            this.colors[i] = new Color(Color.HSBtoRGB(i / this.strlen, 1.0f, 1.0f));
            this.charOffsets[i] = n;
            n += this.fm.charWidth(this.theChars[i]);
        }
        this.offScreenImage = this.createImage(this.size().width, this.size().height);
        (this.offScreenG = this.offScreenImage.getGraphics()).setColor(this.bgColor);
        this.offScreenG.fillRect(0, 0, this.size().width, this.size().height);
        this.offScreenG.setFont(this.f);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void run() {
        this.showStatus("Click to stop " + this.getClass().getName());
        while (this.runner != null) {
            this.repaint();
            try {
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        --this.phase;
        if (this.phase < 0) {
            this.phase = this.strlen - 1;
        }
        for (int i = 0; i < this.strlen; ++i) {
            final int n = this.charOffsets[i];
            this.offScreenG.setColor(this.colors[(this.phase + i) % this.strlen]);
            this.offScreenG.drawChars(this.theChars, i, 1, n, this.yOffset);
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501) {
            if (this.runner != null && this.runner.isAlive()) {
                if (this.stopped) {
                    this.showStatus("Click to stop " + this.getClass().getName());
                    this.runner.resume();
                }
                else {
                    this.showStatus("Click to restart " + this.getClass().getName());
                    this.runner.suspend();
                }
                this.stopped = !this.stopped;
            }
            else {
                this.stopped = false;
                (this.runner = new Thread(this)).start();
            }
        }
        else {
            if (event.id != 504) {
                return super.handleEvent(event);
            }
            if (this.stopped) {
                this.showStatus("Click to restart " + this.getClass().getName());
            }
            else {
                this.showStatus("Click to stop " + this.getClass().getName());
            }
        }
        return true;
    }
    
    public String[][] getParameterInfo() {
        return this.pinfo;
    }
    
    public String getAppletInfo() {
        return "Glenn A. Richard, CHiPR, SUNY at Stony Brook";
    }
    
    public RainbowText() {
        this.stopped = false;
        this.pinfo = new String[][] { { "text", "String", "Text to display (RainbowText)" }, { "bgcolor", "hex rrggbb color", "Background color in rrggbb format (000000)" }, { "fontname", "String", "Name of the font (TimesRoman)" }, { "fontstyle", "String", "B = bold, I = italic, BI = both ()" }, { "fontsize", "int", "Point size to be used (36)" }, { "sleeptime", "int", "Milliseconds for sleep time (100)" } };
    }
}
