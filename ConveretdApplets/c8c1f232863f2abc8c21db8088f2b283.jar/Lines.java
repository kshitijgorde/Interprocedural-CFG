import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lines extends Applet implements Runnable
{
    Thread timer;
    int sleepvalue;
    int thiscolour;
    int maxlines;
    boolean lighter;
    private Image offscreenImage;
    public String thisFilename;
    private SheikLine[] lineArray;
    private int[][] velocityArray;
    private Color[] colourArray;
    private int textWidth;
    private int textHeight;
    public FontMetrics metrics;
    private String fontName;
    private int fontSize;
    private int fontStyle;
    private Font thisFont;
    private int intThisWord;
    private int colourCounter;
    private Color colBackground;
    private int dx1;
    private int dy1;
    private int dx2;
    private int dy2;
    private static final int X1VEL = 0;
    private static final int Y1VEL = 1;
    private static final int X2VEL = 2;
    private static final int Y2VEL = 3;
    private static final String VERSION = "V1.47";
    private static final String PROGRAMNAME = "SheikLines";
    private static final String AUTHOR = "Adam Sheik";
    private static String ABOUT;
    private static final String homeURL = "http://www.celerity.demon.co.uk";
    private static final String defaultText = "(C) Celerity Design";
    private static int intExpiryCounter;
    private static int intFadeCounter;
    private static int intRandomCounter;
    private static int intFadeFrequency;
    private static int intRandomFrequency;
    private static Color colFadeColour;
    private static boolean bolFade;
    private static boolean bolBrighten;
    private static boolean bolGlowAtAll;
    private static final int MAXCOUNTER = 5000;
    
    private Color setColour(final String thisParam, final Color defaultColour) {
        final String param = this.getParameter(thisParam);
        if (param != null) {
            return this.lookupRGBColor(param, defaultColour);
        }
        return defaultColour;
    }
    
    public void stop() {
        this.timer = null;
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        this.getAppletContext().showStatus(Lines.ABOUT);
        Lines.intFadeCounter = Lines.intFadeFrequency + 10;
        return false;
    }
    
    public Lines() {
        this.sleepvalue = 50;
        this.maxlines = 50;
        this.lighter = true;
        this.colourCounter = 1;
        this.dx1 = 2;
        this.dy1 = -2;
        this.dx2 = 1;
        this.dy2 = 3;
    }
    
    public void GenerateRandomMovement() {
        final int intMax = 6;
        if (Math.random() > 0.5) {
            this.dx1 = (int)(Math.random() * intMax);
        }
        else {
            this.dx1 = -(int)(Math.random() * intMax);
        }
        if (Math.random() > 0.5) {
            this.dy1 = (int)(Math.random() * intMax);
        }
        else {
            this.dy1 = -(int)(Math.random() * intMax);
        }
        if (Math.random() > 0.5) {
            this.dx2 = (int)(Math.random() * intMax);
        }
        else {
            this.dx2 = -(int)(Math.random() * intMax);
        }
        if (Math.random() > 0.5) {
            this.dy2 = (int)(Math.random() * intMax);
        }
        else {
            this.dy2 = -(int)(Math.random() * intMax);
        }
    }
    
    public void paint(final Graphics g) {
        final Rectangle r = this.bounds();
        final Graphics offg = this.offscreenImage.getGraphics();
        offg.setColor(this.colBackground);
        offg.fillRect(0, 0, r.width, r.height);
        offg.setFont(this.thisFont);
        for (int i = 0; i < this.maxlines; ++i) {
            int x1 = this.lineArray[i].x1;
            int y1 = this.lineArray[i].y1;
            int x2 = this.lineArray[i].x2;
            int y2 = this.lineArray[i].y2;
            this.dx1 = this.velocityArray[0][i];
            this.dy1 = this.velocityArray[1][i];
            this.dx2 = this.velocityArray[2][i];
            this.dy2 = this.velocityArray[3][i];
            if (x1 < 0 || x1 > r.width) {
                this.dx1 = -this.dx1;
            }
            if (x2 < 0 || x2 > r.width) {
                this.dx2 = -this.dx2;
            }
            if (y1 < 0 || y1 > r.height) {
                this.dy1 = -this.dy1;
            }
            if (y2 < 0 || y2 > r.height) {
                this.dy2 = -this.dy2;
            }
            x1 = (x1 += this.dx1);
            x2 = (x2 += this.dx2);
            y1 = (y1 += this.dy1);
            y2 = (y2 += this.dy2);
            this.lineArray[i].x1 = x1;
            this.lineArray[i].y1 = y1;
            this.lineArray[i].x2 = x2;
            this.lineArray[i].y2 = y2;
            this.velocityArray[0][i] = this.dx1;
            this.velocityArray[1][i] = this.dy1;
            this.velocityArray[2][i] = this.dx2;
            this.velocityArray[3][i] = this.dy2;
            this.lineArray[i].changeColour();
            this.lineArray[i].draw(offg);
        }
        if (Lines.intFadeCounter > Lines.intFadeFrequency) {
            Lines.colFadeColour = ColorUtils.ChooseColour(16, 32);
            Lines.intFadeCounter = 0;
            Lines.bolFade = false;
            Lines.bolBrighten = true;
        }
        if (!Lines.bolFade && !Lines.bolBrighten) {
            if (Lines.bolGlowAtAll) {
                ++Lines.intFadeCounter;
                Lines.colFadeColour = this.colBackground;
            }
        }
        else {
            if (Lines.colFadeColour.getRed() > 240 || Lines.colFadeColour.getGreen() > 240 || Lines.colFadeColour.getBlue() > 240) {
                Lines.bolFade = true;
                Lines.bolBrighten = false;
            }
            if (Lines.bolFade) {
                Lines.colFadeColour = ColorUtils.darker(Lines.colFadeColour, 0.95);
            }
            else if (Lines.bolBrighten) {
                Lines.colFadeColour = ColorUtils.brighter(Lines.colFadeColour, 0.95);
            }
            if (Lines.colFadeColour.equals(Color.black)) {
                Lines.bolFade = false;
                Lines.bolBrighten = false;
                Lines.colFadeColour = this.colBackground;
            }
        }
        if (Lines.intRandomCounter > Lines.intRandomFrequency) {
            Lines.intRandomCounter = 0;
            this.GenerateRandomMovement();
            this.setupLines(r, false);
        }
        else {
            ++Lines.intRandomCounter;
        }
        offg.setColor(Lines.colFadeColour);
        this.intThisWord = 0;
        String thisWord = TextReader1.wordArray[this.intThisWord];
        if (thisWord == null) {
            thisWord = "(C) Celerity Design";
        }
        offg.setFont(this.thisFont = new Font(this.fontName, this.fontStyle, this.fontSize));
        this.GetFontDimensions(this.thisFont, thisWord);
        offg.drawString(thisWord, (r.width - this.textWidth) / 2, r.height / 2 + this.textHeight / 4);
        if (!Shareware.bolLicensed) {
            if (Lines.intExpiryCounter > 5000) {
                TextReader1.wordArray[this.intThisWord] = "(C) Celerity Design";
                Lines.intExpiryCounter = 0;
            }
            else {
                ++Lines.intExpiryCounter;
            }
        }
        g.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    private Color lookupRGBColor(final String description, final Color defaultColor) {
        try {
            final StringTokenizer tokens = new StringTokenizer(description, ",");
            final int red = Integer.parseInt(tokens.nextToken());
            final int green = Integer.parseInt(tokens.nextToken());
            final int blue = Integer.parseInt(tokens.nextToken());
            return new Color(red, green, blue);
        }
        catch (Exception e) {
            return defaultColor;
        }
    }
    
    public void LoadWords() {
        final int extraWords = 0;
        final int maxPages = 1024;
        TextReader1.GetFileName(this, "linewords.txt");
        TextReader1.ReadText(this, extraWords, maxPages);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private Font getFont(String name, int style, int pointSize, final String thisWord) {
        String param = this.getParameter("fontname");
        if (param != null) {
            name = param;
        }
        boolean styleSpecified = false;
        param = this.getParameter("fontweight");
        if (param != null) {
            styleSpecified = true;
            if (param.equals("bold") || param.equals("BOLD")) {
                style = 1;
            }
            else {
                style = 0;
            }
        }
        param = this.getParameter("fontslant");
        if (param != null) {
            if (param.equals("italic") || param.equals("ITALIC")) {
                if (styleSpecified) {
                    style |= 0x2;
                }
                else {
                    style = 2;
                }
            }
            else if (!styleSpecified) {
                style = 0;
            }
        }
        param = this.getParameter("fontsize");
        if (param != null) {
            pointSize = Integer.parseInt(param);
        }
        this.fontName = name;
        this.fontStyle = style;
        this.fontSize = pointSize;
        final Font newFont = new Font(name, style, pointSize);
        this.metrics = this.getFontMetrics(newFont);
        this.textWidth = this.metrics.stringWidth(thisWord);
        this.textHeight = this.metrics.stringWidth(thisWord);
        return newFont;
    }
    
    static {
        Lines.ABOUT = "SheikLines" + ": " + "V1.47" + " by " + "Adam Sheik";
        Lines.intFadeFrequency = 200;
        Lines.intRandomFrequency = 1000;
        Lines.colFadeColour = Color.black;
        Lines.bolGlowAtAll = true;
    }
    
    public void start() {
        if (this.timer == null) {
            (this.timer = new Thread(this)).start();
        }
    }
    
    public boolean mouseLeave(final Event evt, final int x, final int y) {
        return false;
    }
    
    public void GetFontDimensions(final Font thisFont, final String strString) {
        this.metrics = this.getFontMetrics(thisFont);
        this.textWidth = this.metrics.stringWidth(strString);
        this.textHeight = this.metrics.getHeight();
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (Shareware.bolLicensed) {
            return false;
        }
        try {
            final URL url = new URL(this.getDocumentBase(), "http://www.celerity.demon.co.uk");
            this.getAppletContext().showDocument(url);
            return true;
        }
        catch (MalformedURLException e) {
            this.getAppletContext().showStatus("'" + "http://www.celerity.demon.co.uk" + "'" + " is a Malformed URL");
            final URL url = null;
            return false;
        }
    }
    
    private void setupBuffer(final Rectangle r) {
        if (this.offscreenImage != null) {
            this.offscreenImage.flush();
        }
        this.offscreenImage = this.createImage(r.width, r.height);
    }
    
    private void setupLines(final Rectangle r, final boolean bolNewPos) {
        Color colTemp = Color.yellow;
        int x1 = (int)(Math.random() * r.width);
        int y1 = (int)(Math.random() * r.height);
        int x2 = (int)(Math.random() * r.width);
        int y2 = (int)(Math.random() * r.height);
        this.lineArray[0] = new SheikLine(x1, y1, x2, y2, new Color(70, 0, 0));
        if (!bolNewPos) {}
        this.velocityArray[0][0] = this.dx1;
        this.velocityArray[1][0] = this.dy1;
        this.velocityArray[2][0] = this.dx2;
        this.velocityArray[3][0] = this.dy2;
        for (int i = 0; i < this.maxlines; ++i) {
            x1 = this.lineArray[i].x1;
            y1 = this.lineArray[i].y1;
            x2 = this.lineArray[i].x2;
            y2 = this.lineArray[i].y2;
            this.dx1 = this.velocityArray[0][i];
            this.dy1 = this.velocityArray[1][i];
            this.dx2 = this.velocityArray[2][i];
            this.dy2 = this.velocityArray[3][i];
            if (x1 < 0 || x1 > r.width) {
                this.dx1 = -this.dx1;
            }
            if (x2 < 0 || x2 > r.width) {
                this.dx2 = -this.dx2;
            }
            if (y1 < 0 || y1 > r.height) {
                this.dy1 = -this.dy1;
            }
            if (y2 < 0 || y2 > r.height) {
                this.dy2 = -this.dy2;
            }
            x1 = (x1 += this.dx1);
            x2 = (x2 += this.dx2);
            y1 = (y1 += this.dy1);
            y2 = (y2 += this.dy2);
            if (i < this.maxlines - 1) {
                colTemp = this.lineArray[i].getColour();
                this.lineArray[i + 1] = new SheikLine(x1, y1, x2, y2, colTemp);
                this.velocityArray[0][i + 1] = this.dx1;
                this.velocityArray[1][i + 1] = this.dy1;
                this.velocityArray[2][i + 1] = this.dx2;
                this.velocityArray[3][i + 1] = this.dy2;
            }
            else {
                this.lineArray[i] = new SheikLine(x1, y1, x2, y2, this.lineArray[i - 1].getColour());
                this.velocityArray[0][i] = this.dx1;
                this.velocityArray[1][i] = this.dy1;
                this.velocityArray[2][i] = this.dx2;
                this.velocityArray[3][i] = this.dy2;
            }
        }
        for (int i = 0; i < this.maxlines; ++i) {
            for (int j = 0; j <= i; ++j) {
                this.lineArray[i].changeColour();
            }
        }
    }
    
    public void run() {
        while (this.timer != null) {
            try {
                Thread.sleep(this.sleepvalue);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
        this.timer = null;
    }
    
    public void init() {
        Shareware.CheckDomain(this);
        if (Shareware.bolLicensed) {
            Lines.ABOUT += " [licensed]";
        }
        else {
            Lines.ABOUT += " [unlicensed]";
        }
        final String psleepvalue = this.getParameter("SLEEPVALUE");
        final String pmaxlines = this.getParameter("MAXLINES");
        String strTemp = this.getParameter("FILENAME");
        if (strTemp != null) {
            this.thisFilename = strTemp;
        }
        else {
            this.thisFilename = "linewords.txt";
        }
        strTemp = this.getParameter("GLOW");
        if (strTemp != null) {
            if (strTemp.equalsIgnoreCase("true")) {
                strTemp = this.getParameter("GLOWFREQUENCY");
                Lines.intFadeFrequency = 200;
                if (strTemp != null) {
                    try {
                        Lines.intFadeFrequency = Integer.parseInt(strTemp);
                    }
                    catch (NumberFormatException n) {
                        this.getAppletContext().showStatus("Invalid FADEFREQUENCY Argument");
                        Lines.intFadeFrequency = 200;
                    }
                }
                Lines.bolGlowAtAll = true;
            }
            else {
                Lines.bolGlowAtAll = false;
            }
        }
        strTemp = this.getParameter("RANDOMFREQUENCY");
        if (strTemp != null) {
            try {
                Lines.intRandomFrequency = Integer.parseInt(strTemp);
            }
            catch (NumberFormatException n) {
                this.getAppletContext().showStatus("Invalid RANDOMFREQUENCY Argument");
                Lines.intRandomFrequency = 1000;
            }
        }
        strTemp = this.getParameter("dx1");
        if (strTemp != null) {
            try {
                this.dx1 = Integer.parseInt(strTemp);
            }
            catch (NumberFormatException n) {
                this.getAppletContext().showStatus("Invalid DX1 Argument");
                this.dx1 = 2;
            }
        }
        strTemp = this.getParameter("dy1");
        if (strTemp != null) {
            try {
                this.dy1 = Integer.parseInt(strTemp);
            }
            catch (NumberFormatException n) {
                this.getAppletContext().showStatus("Invalid DY1 Argument");
                this.dy1 = -2;
            }
        }
        strTemp = this.getParameter("dx2");
        if (strTemp != null) {
            try {
                this.dx2 = Integer.parseInt(strTemp);
            }
            catch (NumberFormatException n) {
                this.getAppletContext().showStatus("Invalid DX2 Argument");
                this.dx2 = 1;
            }
        }
        strTemp = this.getParameter("dy2");
        if (strTemp != null) {
            try {
                this.dy2 = Integer.parseInt(strTemp);
            }
            catch (NumberFormatException n) {
                this.getAppletContext().showStatus("Invalid DY2 Argument");
                this.dy2 = 3;
            }
        }
        strTemp = this.getParameter("RANDOMMOVEMENT");
        if (strTemp != null && strTemp.equalsIgnoreCase("true")) {
            this.GenerateRandomMovement();
        }
        if (psleepvalue != null) {
            try {
                this.sleepvalue = Integer.parseInt(psleepvalue);
            }
            catch (NumberFormatException n) {
                this.getAppletContext().showStatus("Invalid SLEEPVALUE Argument");
                this.sleepvalue = 50;
            }
        }
        if (pmaxlines != null) {
            try {
                this.maxlines = Integer.parseInt(pmaxlines);
            }
            catch (NumberFormatException n) {
                this.getAppletContext().showStatus("Invalid MAXLINES Argument");
                this.maxlines = 50;
            }
        }
        this.setBackground(this.colBackground = this.setColour("background-rgb", Color.black));
        Lines.colFadeColour = this.colBackground;
        this.LoadWords();
        final Rectangle r = this.bounds();
        this.setupBuffer(r);
        this.lineArray = new SheikLine[this.maxlines];
        this.velocityArray = new int[4][this.maxlines];
        this.colourArray = new Color[this.maxlines];
        this.setupLines(r, true);
        this.thisFont = this.getFont("Helvetica", 0, 36, "(C) Celerity Design");
    }
}
