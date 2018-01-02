import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Counter extends Applet implements Runnable
{
    Image backBuffer;
    Graphics offScreen;
    String bgColorString;
    Color bgColor;
    Color fontColor;
    String digitDirectory;
    Insets theInsets;
    boolean initialized;
    boolean laidOut;
    CountInfo counterInformation;
    long counterID;
    Thread counterThread;
    Digits theDigits;
    int speed;
    int xPos;
    int yPos;
    Font textFont;
    FontMetrics myFontMetrics;
    int counterStringWidth;
    boolean restarted;
    int numRuns;
    Image bannerImage;
    URL bannerURL;
    boolean drewAd;
    boolean adClicked;
    int counterSize;
    int bannerX;
    int bannerY;
    int bannerStopX;
    boolean advertising;
    int originalAdDelay;
    int adDelay;
    int originalFrameDelay;
    int adNumber;
    URL bannerTargetURL;
    int[] StarX;
    int[] StarY;
    int[] StarSpeed;
    int[] StarBrightness;
    Color[] StarColor;
    boolean CGIConfiguration;
    
    public void init() {
        this.initialized = false;
        this.StarX = new int[70];
        this.StarY = new int[70];
        this.StarSpeed = new int[70];
        this.StarBrightness = new int[70];
        this.StarColor = new Color[70];
        for (int i = 0; i < 70; ++i) {
            this.StarX[i] = (int)(Math.random() * this.size().width);
            this.StarY[i] = (int)(Math.random() * this.size().height);
            this.StarSpeed[i] = (int)(Math.random() * 10.0);
            this.StarBrightness[i] = (int)(Math.random() * 256.0);
            this.StarColor[i] = new Color(this.StarBrightness[i], this.StarBrightness[i], this.StarBrightness[i]);
        }
        this.backBuffer = this.createImage(this.size().width, this.size().height);
        this.offScreen = this.backBuffer.getGraphics();
        this.counterID = new Long(this.getParameter("counter_id"));
        final String parameter = this.getParameter("increment");
        final boolean b = parameter == null || parameter.compareTo("true") == 0;
        if (this.getParameter("CGI_configuration") != null) {
            if (this.getParameter("CGI_configuration").compareTo("true") == 0) {
                this.CGIConfiguration = true;
            }
            else {
                this.CGIConfiguration = false;
            }
        }
        this.counterInformation = new CountInfo(b, false, this.counterID, this);
        this.digitDirectory = this.getParameter("digittype");
        if (this.CGIConfiguration) {
            this.digitDirectory = this.counterInformation.getDigitDirectory();
        }
        this.theDigits = new Digits(this, this.digitDirectory);
        this.bgColorString = this.getParameter("bgcolor");
        if (this.bgColorString == null) {
            this.bgColorString = new String("#000000");
        }
        String parameter2 = this.getParameter("fontcolor");
        if (parameter2 == null) {
            parameter2 = new String("#64ff64");
        }
        this.bgColor = this.convertColor(this.bgColorString);
        this.fontColor = this.convertColor(parameter2);
        this.setBackground(this.bgColor);
        this.xPos = this.size().width;
        this.yPos = this.size().height / 2 - this.theDigits.getHeight() / 2;
        this.speed = 8;
        this.numRuns = 0;
        this.originalAdDelay = 100;
        this.advertising = false;
        this.drewAd = false;
        this.textFont = new Font("Helvetica", 0, 15);
    }
    
    public void start() {
        if (this.counterThread == null) {
            (this.counterThread = new Thread(this, "Counter")).start();
        }
    }
    
    public void stop() {
        this.counterThread = null;
        this.restarted = true;
    }
    
    public void dispose() {
        this.offScreen.dispose();
    }
    
    public void run() {
        while (Thread.currentThread() == this.counterThread) {
            this.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        if (!this.initialized) {
            this.myFontMetrics = graphics.getFontMetrics(this.textFont);
            if (!this.counterInformation.isSubscriber()) {
                this.initializeAds();
                graphics.drawImage(this.bannerImage, 1000, 1000, this);
            }
            this.initialized = true;
        }
        this.offScreen.setColor(this.bgColor);
        this.offScreen.fillRect(0, 0, this.size().width, this.size().height);
        for (int i = 0; i < 70; ++i) {
            this.offScreen.setColor(this.StarColor[i]);
            this.offScreen.drawRect(this.StarX[i], this.StarY[i], 1, 1);
            final int[] starX = this.StarX;
            final int n = i;
            starX[n] += this.StarSpeed[i];
            if (this.StarX[i] > this.size().width) {
                final int[] starX2 = this.StarX;
                final int n2 = i;
                starX2[n2] -= this.size().width;
            }
        }
        if (!this.advertising) {
            this.offScreen.setColor(this.fontColor);
            this.offScreen.setFont(this.textFont);
            if (!this.counterInformation.getExpired()) {
                this.counterSize = this.theDigits.drawDigits(this.counterInformation.getCount(), this.xPos, this.yPos, this.offScreen);
            }
            else {
                this.counterSize = 0;
            }
            final String substring = this.counterInformation.getStartDate().toString().substring(4, 7);
            int year = this.counterInformation.getStartDate().getYear();
            year += 1900;
            if (this.counterInformation.isSubscriber()) {
                if (this.counterInformation.getExpired()) {
                    final String s = "This counter has expired.  Please click here for payment information.";
                    this.offScreen.drawString(s, this.xPos + this.counterSize, this.yPos + this.theDigits.getHeight());
                    this.counterStringWidth = this.myFontMetrics.stringWidth(s);
                }
                else if (this.counterInformation.getCount() == 1L) {
                    final String string = " person has visited this page since " + this.counterInformation.getStartDate().getDate() + " " + substring + " " + year + ".";
                    this.offScreen.drawString(string, this.xPos + this.counterSize, this.yPos + this.theDigits.getHeight());
                    this.counterStringWidth = this.myFontMetrics.stringWidth(string);
                }
                else {
                    final String string2 = " people have visited this page since " + this.counterInformation.getStartDate().getDate() + " " + substring + " " + year + ".";
                    this.offScreen.drawString(string2, this.xPos + this.counterSize, this.yPos + this.theDigits.getHeight());
                    this.counterStringWidth = this.myFontMetrics.stringWidth(string2);
                }
            }
            else if (this.counterInformation.getCount() == 1L) {
                final String string3 = " person has visited this page since " + this.counterInformation.getStartDate().getDate() + " " + substring + " " + year + ".";
                this.offScreen.drawString(string3, this.xPos + this.counterSize, this.yPos + this.theDigits.getHeight());
                this.counterStringWidth = this.myFontMetrics.stringWidth(string3);
            }
            else {
                final String string4 = " people have visited this page since " + this.counterInformation.getStartDate().getDate() + " " + substring + " " + year + ".";
                this.offScreen.drawString(string4, this.xPos + this.counterSize, this.yPos + this.theDigits.getHeight());
                this.counterStringWidth = this.myFontMetrics.stringWidth(string4);
            }
            this.xPos -= this.speed;
            graphics.drawImage(this.backBuffer, 0, 0, this);
        }
        if (this.xPos < -(this.counterSize + this.counterStringWidth) && !this.advertising) {
            if (!this.counterInformation.isSubscriber()) {
                this.advertising = true;
                if (this.restarted) {
                    this.counterInformation = new CountInfo(false, true, this.counterID, this);
                    this.restarted = false;
                }
            }
            else {
                this.xPos = this.size().width;
                ++this.numRuns;
                if (this.restarted) {
                    this.counterInformation = new CountInfo(false, true, this.counterID, this);
                    this.restarted = false;
                }
                if (this.numRuns >= 6) {
                    this.numRuns = 0;
                    this.counterInformation = new CountInfo(false, true, this.counterID, this);
                }
            }
        }
        if (this.advertising) {
            if (this.bannerImage.getWidth(this) != -1) {
                this.bannerX = this.bannerImage.getWidth(this);
                this.bannerY = this.bannerImage.getHeight(this);
                graphics.setColor(new Color(0, 0, 0));
                this.offScreen.drawImage(this.bannerImage, (this.size().width - this.bannerX) / 2, (this.size().height - this.bannerY) / 2, null);
                graphics.drawImage(this.backBuffer, 0, 0, this);
                --this.adDelay;
                if (this.adDelay < 0) {
                    this.xPos = this.size().width;
                    this.advertising = false;
                    this.adDelay = this.originalAdDelay;
                    this.drewAd = false;
                }
            }
            else {
                this.xPos = this.size().width;
                this.advertising = false;
                this.adDelay = this.originalAdDelay;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.counterInformation.isSubscriber()) {
            try {
                if (this.counterInformation.getExpired()) {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase().toString()));
                }
                else {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase() + "php/counter_stats.php?counter_id=" + Long.toString(this.counterInformation.getCounterID())));
                }
            }
            catch (MalformedURLException ex) {}
            return true;
        }
        if (this.advertising) {
            this.getAppletContext().showDocument(this.bannerTargetURL);
            return true;
        }
        try {
            this.getAppletContext().showDocument(new URL(this.getCodeBase() + "php/counter_stats.php?counter_id=" + Long.toString(this.counterInformation.getCounterID())));
        }
        catch (MalformedURLException ex2) {}
        return false;
    }
    
    public void initializeAds() {
        this.numRuns = 0;
        this.adClicked = false;
        final double n = Math.random() * 1.0E7;
        try {
            this.bannerURL = new URL(this.counterInformation.getImageAddress());
            System.out.println(this.counterInformation.getImageAddress());
            System.out.println(this.bannerURL.toString());
            this.bannerTargetURL = this.counterInformation.getBannerTargetURL();
        }
        catch (MalformedURLException ex) {
            this.showStatus("JCount: incorrect banner image location.");
        }
        this.adDelay = this.originalAdDelay;
        this.adNumber = this.counterInformation.getAdNumber();
        this.theDigits.drawDigits(this.counterInformation.getCount(), 1200, 1200, this.offScreen);
        this.bannerImage = this.getImage(this.bannerURL);
        this.bannerX = this.bannerImage.getWidth(this);
        this.bannerY = this.bannerImage.getHeight(this);
        this.bannerStopX = 0;
    }
    
    public Color convertColor(final String s) {
        String substring = s;
        if (substring.charAt(0) == '#') {
            substring = substring.substring(1);
        }
        if (substring.length() != 6) {
            substring = new String("000000");
        }
        final String s2 = new String(substring.toUpperCase());
        return new Color(this.hexConvert(s2.substring(0, 2)), this.hexConvert(s2.substring(2, 4)), this.hexConvert(s2.substring(4, 6)));
    }
    
    int hexConvert(final String s) {
        int n = 0;
        switch (s.charAt(0)) {
            case '0': {
                n = 0;
                break;
            }
            case '1': {
                n = 16;
                break;
            }
            case '2': {
                n = 32;
                break;
            }
            case '3': {
                n = 48;
                break;
            }
            case '4': {
                n = 64;
                break;
            }
            case '5': {
                n = 80;
                break;
            }
            case '6': {
                n = 96;
                break;
            }
            case '7': {
                n = 112;
                break;
            }
            case '8': {
                n = 128;
                break;
            }
            case '9': {
                n = 144;
                break;
            }
            case 'A': {
                n = 160;
                break;
            }
            case 'B': {
                n = 176;
                break;
            }
            case 'C': {
                n = 192;
                break;
            }
            case 'D': {
                n = 208;
                break;
            }
            case 'E': {
                n = 224;
                break;
            }
            case 'F': {
                n = 240;
                break;
            }
            default: {
                n = 0;
                break;
            }
        }
        int n2 = 0;
        switch (s.charAt(1)) {
            case '0': {
                n2 = 0;
                break;
            }
            case '1': {
                n2 = 1;
                break;
            }
            case '2': {
                n2 = 2;
                break;
            }
            case '3': {
                n2 = 3;
                break;
            }
            case '4': {
                n2 = 4;
                break;
            }
            case '5': {
                n2 = 5;
                break;
            }
            case '6': {
                n2 = 6;
                break;
            }
            case '7': {
                n2 = 7;
                break;
            }
            case '8': {
                n2 = 8;
                break;
            }
            case '9': {
                n2 = 9;
                break;
            }
            case 'A': {
                n2 = 10;
                break;
            }
            case 'B': {
                n2 = 11;
                break;
            }
            case 'C': {
                n2 = 12;
                break;
            }
            case 'D': {
                n2 = 13;
                break;
            }
            case 'E': {
                n2 = 14;
                break;
            }
            case 'F': {
                n2 = 15;
                break;
            }
            default: {
                n2 = 0;
                break;
            }
        }
        return n + n2;
    }
    
    public Counter() {
        this.backBuffer = null;
        this.offScreen = null;
        this.bgColorString = null;
        this.fontColor = null;
        this.digitDirectory = null;
        this.theInsets = null;
        this.counterThread = null;
        this.theDigits = null;
        this.restarted = false;
    }
}
