import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class StarWords extends Applet implements Runnable
{
    Thread timer;
    int maxx;
    int maxy;
    int maxz;
    int dx;
    int dy;
    int dz;
    int angle;
    int sleepvalue;
    Color c1;
    int thiscolour;
    boolean lighter;
    int maxstars;
    int intCurrentNumStars;
    Vector stararray;
    Rectangle r;
    private Image offscreenImage;
    public Graphics offg;
    private Font thisFont;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private int thisFontSize;
    private double fontZVel;
    private double fontZAcc;
    private Color fontColour;
    private Color defaultColour;
    private Color colBackground;
    private String thisString;
    private int intThisString;
    private int textWidth;
    private int textHeight;
    public FontMetrics metrics;
    public int intMoveCounter;
    public int intMoveFrequency;
    public int intMoveSpeed;
    public int intMoveSpeedCounter;
    public int intRotateCounter;
    public int intRotateFrequency;
    public int intRotateSpeed;
    public int intRotateSpeedCounter;
    public int targetDx;
    public int targetDy;
    public int targetDz;
    public int targetAngle;
    public int intRotationDirection;
    public boolean bolRandomStartColour;
    public boolean bolAppletRunning;
    public boolean bolWordsLoaded;
    public int intStarDrawStyle;
    private static final int MAXFONTSIZE = 350;
    private static final int MAXXVEL = 5;
    private static final int MAXYVEL = 5;
    private static final int MAXZVEL = 10;
    private static final String VERSION = "V1.61";
    private static final String PROGRAMNAME = "Star Words";
    private static final String AUTHOR = "Adam Sheik";
    private static final String ABOUT;
    public final String HOMEURL = "www.celerity.demon.co.uk";
    private static final int CLOCKWISE = 1;
    private static final int ANTICLOCKWISE = -1;
    private static final int DOT = 0;
    private static final int CROSS = 1;
    private static final int BLOB = 2;
    private static final int STAR = 3;
    private static final int MOSTLYCROSSES = 10;
    private static final int MOSTLYBLOBS = 11;
    
    private Color setColour(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return this.lookupRGBColor(parameter, color);
        }
        return color;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (Shareware.bolLicensed) {
            this.getAppletContext().showStatus(StarWords.ABOUT);
        }
        else {
            this.getAppletContext().showStatus(StarWords.ABOUT + " [unlicensed]");
        }
        return false;
    }
    
    public void stop() {
        this.timer.stop();
        this.timer = null;
    }
    
    public void ChangeRotation() {
        if (this.intRotateCounter <= this.intRotateFrequency) {
            ++this.intRotateCounter;
            return;
        }
        this.intRotateCounter = 0;
        this.angle = (int)(Math.random() * 3.0);
        if (Math.random() > 0.5) {
            this.intRotationDirection = 1;
            return;
        }
        this.intRotationDirection = -1;
    }
    
    public void ChangeMovement() {
        if (this.intMoveSpeedCounter > this.intMoveSpeed) {
            if (this.dx > this.targetDx) {
                --this.dx;
            }
            else if (this.dx < this.targetDx) {
                ++this.dx;
            }
            if (this.dy > this.targetDy) {
                --this.dy;
            }
            else if (this.dy < this.targetDy) {
                ++this.dy;
            }
            if (this.dz > this.targetDz) {
                --this.dz;
            }
            else if (this.dz < this.targetDz) {
                ++this.dz;
            }
            this.intMoveSpeedCounter = 0;
        }
        else {
            ++this.intMoveSpeedCounter;
        }
        if (this.intMoveCounter <= this.intMoveFrequency) {
            ++this.intMoveCounter;
            return;
        }
        this.intMoveCounter = 0;
        if (Math.random() > 0.5) {
            this.targetDx = (int)(Math.random() * 5.0);
        }
        else {
            this.targetDx = -(int)(Math.random() * 5.0);
        }
        if (Math.random() > 0.5) {
            this.targetDy = (int)(Math.random() * 5.0);
            return;
        }
        this.targetDz = -(int)(Math.random() * 10.0);
    }
    
    public void GetFirstString() {
        this.intThisString = 0;
        this.thisString = TextReader1.wordArray[this.intThisString];
    }
    
    public void paint(final Graphics graphics) {
        if (!this.bolAppletRunning) {
            return;
        }
        if (!this.bolWordsLoaded) {
            graphics.setColor(Color.gray);
            graphics.setFont(this.thisFont);
            graphics.drawString(StarWords.ABOUT, 50, this.r.height - this.r.height / 2);
            graphics.drawString("Loading in messages...", 50, this.r.height - this.r.height / 4);
        }
        boolean b = false;
        final double n = this.maxz / 2;
        final double n2 = -this.maxz / 2;
        this.offg.setColor(this.colBackground);
        this.offg.fillRect(0, 0, this.r.width, this.r.height);
        this.ChangeMovement();
        this.ChangeRotation();
        if (this.intCurrentNumStars < this.maxstars) {
            ++this.intCurrentNumStars;
        }
        else {
            this.intCurrentNumStars = this.maxstars;
        }
        for (int i = 0; i < this.intCurrentNumStars; ++i) {
            final Star star = this.stararray.elementAt(i);
            star.TranslateStar(this.dx, this.dy, this.dz);
            star.RotateStarZAxis(this.angle, this.intRotationDirection);
            if (star.x > this.maxx) {
                star.x = -this.maxx;
            }
            else if (star.x < -this.maxx) {
                star.x = this.maxx;
            }
            if (star.y > this.maxy) {
                star.y = -this.maxy;
            }
            else if (star.y < -this.maxy) {
                star.y = this.maxy;
            }
            if (star.z > this.maxz) {
                star.z = -this.maxz;
            }
            else if (star.z < -this.maxz) {
                star.z = this.maxz;
            }
            this.stararray.setElementAt(star, i);
            star.MapMeToTwoD(this.r);
            this.c1 = star.colour;
            int n3;
            if (star.z > n) {
                this.offg.setColor(ColorUtils.darker(this.c1, 0.7));
                n3 = 1;
            }
            else if (star.z > 0.0) {
                this.offg.setColor(ColorUtils.darker(this.c1, 0.8));
                n3 = 2;
            }
            else if (star.z < n2) {
                this.offg.setColor(ColorUtils.brighter(this.c1, 0.6));
                n3 = 3;
            }
            else {
                this.offg.setColor(this.c1);
                n3 = 3;
            }
            star.draw(this.offg, n3);
        }
        if (this.intCurrentNumStars == this.maxstars) {
            if (this.fontZAcc >= 0.0) {
                b = (this.textHeight + this.r.height / 4 > this.r.height);
            }
            if (this.fontZAcc >= 0.0) {
                if (this.thisFontSize >= 350) {
                    this.thisFontSize = this.fontSize;
                    this.fontZVel = 1.0;
                    this.fontColour = ColorUtils.ChooseColour(16, 64);
                    this.GetNextString();
                    b = false;
                }
                else {
                    this.fontZVel += this.fontZAcc;
                    this.thisFontSize += (int)this.fontZVel;
                }
            }
            else if (this.thisFontSize <= this.fontSize) {
                this.thisFontSize = 350;
                this.fontZVel = -5.0;
                this.fontColour = ColorUtils.ChooseColour(200, 255);
                this.GetNextString();
                b = false;
            }
            else {
                this.fontZVel += this.fontZAcc;
                this.thisFontSize += (int)this.fontZVel;
            }
            this.thisFont = new Font(this.fontName, this.fontStyle, this.thisFontSize);
            this.offg.setFont(this.thisFont);
            this.GetFontDimensions(this.thisFont, this.thisString);
            if (b) {
                this.fontColour = ColorUtils.darker(this.fontColour, 0.9);
            }
            else if (this.fontZAcc >= 0.0) {
                this.fontColour = ColorUtils.brighter(this.fontColour, 0.965);
            }
            else {
                this.fontColour = ColorUtils.darker(this.fontColour, 0.985);
            }
            this.offg.setColor(this.fontColour);
            if (!b) {
                this.offg.drawString(this.thisString, (this.r.width - this.textWidth) / 2, this.r.height / 2 + this.textHeight / 4);
            }
            else if (this.fontColour.getRed() > 16 || this.fontColour.getGreen() > 16 || this.fontColour.getBlue() > 16) {
                this.offg.drawString(this.thisString, (this.r.width - this.textWidth) / 2, this.r.height / 2 + this.textHeight / 4);
            }
            else {
                this.thisFontSize = 350;
            }
        }
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public StarWords() {
        this.timer = null;
        this.maxx = 0;
        this.maxy = 0;
        this.maxz = 0;
        this.dx = 0;
        this.dy = 0;
        this.dz = 3;
        this.angle = (int)(Math.random() * 2.0);
        this.sleepvalue = 50;
        this.c1 = new Color(128, 128, 128);
        this.thiscolour = 0;
        this.lighter = true;
        this.maxstars = 50;
        this.intCurrentNumStars = 0;
        this.stararray = new Vector(0, 1);
        this.offscreenImage = null;
        this.offg = null;
        this.fontZVel = 1.0;
        this.defaultColour = new Color(16, 32, 48);
        this.intMoveCounter = 0;
        this.intMoveFrequency = 200;
        this.intMoveSpeed = 50;
        this.intMoveSpeedCounter = 0;
        this.intRotateCounter = 0;
        this.intRotateFrequency = 50;
        this.intRotateSpeed = 20;
        this.intRotateSpeedCounter = 0;
        this.intRotationDirection = 1;
        this.bolRandomStartColour = false;
        this.bolAppletRunning = false;
        this.bolWordsLoaded = false;
        this.intStarDrawStyle = 1;
    }
    
    private Color lookupRGBColor(final String s, final Color color) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    public void LoadWords() {
        if (Shareware.bolLicensed) {
            final int extraPages = 0;
            final int maxPages = 1024;
            TextReader1.GetFileName(this, "starwords.txt");
            TextReader1.ReadText(this, extraPages, maxPages);
        }
        else {
            final int extraPages2 = 4;
            final int maxPages2 = 10;
            TextReader1.GetFileName(this, "starwords.txt");
            TextReader1.ReadText(this, extraPages2, maxPages2);
            final int numPages = TextReader1.numPages;
            TextReader1.wordArray[numPages] = StarWords.ABOUT;
            TextReader1.wordArray[numPages + 1] = "To Register Visit:";
            TextReader1.wordArray[numPages + 2] = "www.celerity.demon.co.uk";
            TextReader1.wordArray[numPages + 3] = "(C) Celerity Design '98";
        }
        this.GetFirstString();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private Font getFont(String fontName, int fontStyle, int int1, final String s) {
        final String parameter = this.getParameter("fontname");
        if (parameter != null) {
            fontName = parameter;
        }
        boolean b = false;
        final String parameter2 = this.getParameter("fontweight");
        if (parameter2 != null) {
            b = true;
            if (parameter2.equals("bold") || parameter2.equals("BOLD")) {
                fontStyle = 1;
            }
            else {
                fontStyle = 0;
            }
        }
        final String parameter3 = this.getParameter("fontslant");
        if (parameter3 != null) {
            if (parameter3.equals("italic") || parameter3.equals("ITALIC")) {
                if (b) {
                    fontStyle |= 0x2;
                }
                else {
                    fontStyle = 2;
                }
            }
            else if (!b) {
                fontStyle = 0;
            }
        }
        final String parameter4 = this.getParameter("fontsize");
        if (parameter4 != null) {
            int1 = Integer.parseInt(parameter4);
        }
        final Font font = new Font(fontName, fontStyle, int1);
        this.fontName = fontName;
        this.fontSize = int1;
        if (this.fontZAcc >= 0.0) {
            this.thisFontSize = this.fontSize;
        }
        else {
            this.thisFontSize = 350;
        }
        this.fontZVel = 1.0;
        this.fontStyle = fontStyle;
        this.metrics = this.getFontMetrics(font);
        this.textWidth = this.metrics.stringWidth(s);
        this.textHeight = this.metrics.getHeight();
        if (this.fontZAcc >= 0.0) {
            this.fontColour = ColorUtils.ChooseColour(16, 64);
        }
        else {
            this.fontColour = ColorUtils.ChooseColour(200, 255);
        }
        return font;
    }
    
    public void GetNextString() {
        ++this.intThisString;
        if (this.intThisString >= TextReader1.wordArray.length) {
            this.intThisString = 0;
        }
        this.thisString = TextReader1.wordArray[this.intThisString];
    }
    
    static {
        ABOUT = "Star Words" + ": " + "V1.61" + " by " + "Adam Sheik";
    }
    
    public void start() {
        this.GetVectorParameters();
        this.RandomiseStars();
        if (this.timer == null) {
            (this.timer = new Thread(this)).start();
        }
        this.offg = this.offscreenImage.getGraphics();
        this.bolAppletRunning = true;
    }
    
    public void GetFontDimensions(final Font font, final String s) {
        this.metrics = this.getFontMetrics(font);
        this.textWidth = this.metrics.stringWidth(s);
        this.textHeight = this.metrics.getHeight();
    }
    
    private void setupBuffer(final Rectangle rectangle) {
        if (this.offscreenImage != null) {
            this.offscreenImage.flush();
        }
        this.offscreenImage = this.createImage(rectangle.width, rectangle.height);
    }
    
    public void RandomiseStars() {
        final int n = 48;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        if (Math.random() < 0.333) {
            n2 = n;
        }
        else if (Math.random() < 0.666) {
            n3 = n;
        }
        else {
            n4 = n;
        }
        if (this.bolRandomStartColour) {
            this.c1 = ColorUtils.ChooseColour(128, 150);
        }
        else {
            this.c1 = this.setColour("star-rgb", new Color(128, 128, 128));
        }
        this.dx = (int)(Math.random() * 5.0);
        this.dy = (int)(Math.random() * 5.0);
        this.dz = (int)(Math.random() * 10.0);
        this.targetDx = this.dx;
        this.targetDy = this.dy;
        this.targetDz = this.dz;
        this.targetAngle = 0;
        this.getAppletContext().showStatus("About to seed starfield");
        for (int i = 0; i < this.maxstars; ++i) {
            final Color chooseSimilarColour = ColorUtils.ChooseSimilarColour(this.c1.getRed(), this.c1.getRed() + n2, this.c1.getGreen(), this.c1.getGreen() + n3, this.c1.getBlue(), this.c1.getBlue() + n4);
            int intStarDrawStyle;
            if (this.intStarDrawStyle < 10) {
                intStarDrawStyle = this.intStarDrawStyle;
            }
            else if (this.intStarDrawStyle == 10) {
                if (Math.random() * 100.0 > 85.0) {
                    intStarDrawStyle = 2;
                }
                else {
                    intStarDrawStyle = 1;
                }
            }
            else if (Math.random() * 100.0 > 85.0) {
                intStarDrawStyle = 1;
            }
            else {
                intStarDrawStyle = 2;
            }
            final Star star = new Star(Math.random() * this.maxx * 2.0, Math.random() * this.maxy * 2.0, Math.random() * this.maxz * 2.0, intStarDrawStyle, chooseSimilarColour);
            star.TranslateStar(-this.maxx, -this.maxy, -this.maxz);
            this.stararray.addElement(star);
        }
        this.getAppletContext().showStatus("Seeded starfield");
    }
    
    public void run() {
        while (this.timer != null) {
            try {
                Thread.sleep(this.sleepvalue);
            }
            catch (InterruptedException ex) {}
            if (!this.bolWordsLoaded) {
                this.repaint();
                this.LoadWords();
                this.thisFont = this.getFont("Helvetica", 0, 36, TextReader1.wordArray[0]);
                this.bolWordsLoaded = true;
            }
            this.repaint();
        }
        this.timer = null;
    }
    
    public void init() {
        this.colBackground = this.setColour("background-rgb", Color.black);
        this.r = this.bounds();
        this.setBackground(this.colBackground);
        this.repaint();
        final String parameter = this.getParameter("SLEEPVALUE");
        final String parameter2 = this.getParameter("MAXSTARS");
        Shareware.CheckDomain(this);
        ColorUtils.MaxColours();
        if (parameter != null) {
            try {
                this.sleepvalue = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                this.getAppletContext().showStatus("Invalid SLEEPVALUE Argument");
                this.sleepvalue = 50;
            }
        }
        if (parameter2 != null) {
            try {
                this.maxstars = Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex2) {
                this.getAppletContext().showStatus("Invalid MAXSTARS Argument");
                this.maxstars = 50;
            }
        }
        final String parameter3 = this.getParameter("MOVEFREQUENCY");
        if (parameter3 != null) {
            try {
                this.intMoveFrequency = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex3) {
                this.getAppletContext().showStatus("Invalid MOVEFREQUENCY Argument");
                this.intMoveFrequency = 200;
            }
        }
        final String parameter4 = this.getParameter("STARTYPE");
        if (parameter4 != null) {
            try {
                this.intStarDrawStyle = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex4) {
                this.getAppletContext().showStatus("Invalid STARTYPE Argument");
                this.intStarDrawStyle = 11;
            }
        }
        final String parameter5 = this.getParameter("MOVESPEED");
        if (parameter5 != null) {
            try {
                this.intMoveSpeed = Integer.parseInt(parameter5);
            }
            catch (NumberFormatException ex5) {
                this.getAppletContext().showStatus("Invalid MOVESPEED Argument");
                this.intMoveSpeed = 20;
            }
        }
        final String parameter6 = this.getParameter("ROTATEFREQUENCY");
        if (parameter6 != null) {
            try {
                this.intRotateFrequency = Integer.parseInt(parameter6);
            }
            catch (NumberFormatException ex6) {
                this.getAppletContext().showStatus("Invalid ROTATEFREQUENCY Argument");
                this.intRotateFrequency = 50;
            }
        }
        final String parameter7 = this.getParameter("RANDOMSTARTCOLOUR");
        if (parameter7 != null && parameter7.equalsIgnoreCase("true")) {
            this.bolRandomStartColour = true;
        }
        final String parameter8 = this.getParameter("BUILDSTARS");
        if (parameter8 != null) {
            if (parameter8.equalsIgnoreCase("false")) {
                this.intCurrentNumStars = this.maxstars;
            }
            else {
                this.intCurrentNumStars = 0;
            }
        }
        final Rectangle bounds = this.bounds();
        this.maxx = bounds.width;
        this.maxy = bounds.height;
        this.maxz = (bounds.height + bounds.width) / 2 / 2;
        this.getAppletContext().showStatus("Creating lookup tables...");
        TrigFunctions.CreateTrigArrays();
        this.setupBuffer(bounds);
    }
    
    private void GetVectorParameters() {
        final String parameter = this.getParameter("TextZAcc");
        if (parameter != null) {
            try {
                this.fontZAcc = 0.01 * Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                this.getAppletContext().showStatus("Invalid TextZAcc Argument");
                this.fontZAcc = 0.02;
            }
        }
    }
}
