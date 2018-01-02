import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SunRise extends Applet implements Runnable
{
    final int numMountainPoints = 32;
    final int numMountainRanges = 4;
    final double Jag0 = 25.0;
    final double Jag1 = 15.0;
    final double Jag2 = 7.0;
    final int numClouds = 5;
    final int frameThickness = 4;
    int stage;
    final int dawn = 0;
    final int goingAway = 1;
    final int stall = 2;
    final int reset = 3;
    Thread rowThread;
    private int Speed;
    private String URLstr;
    URL goURL;
    private int winWidth;
    private int winHeight;
    private int fullWidth;
    private int fullHeight;
    Dimension d;
    Dimension Od;
    Image Oi;
    Graphics Og;
    boolean buttonPressed;
    boolean overButton;
    private final String PARAM_Text = "Text";
    private final String PARAM_Speed = "Speed";
    private final String PARAM_URL = "URL";
    Font thisFont;
    Font tinyFont;
    FontMetrics fm;
    int fontWidth;
    int fontHeight;
    int fontOffset;
    int fontSize;
    int numSkyColors;
    int brightness;
    double brightDelta;
    Color textColor;
    Color[] skyColor;
    Color cloudColor;
    Color hill0Color;
    Color hill1Color;
    Color mountainColor;
    Color treeColor;
    double[] mountainJag;
    int[][] mountsX;
    int[][] mountsY;
    int counter;
    int textX;
    int textY;
    String textStr;
    
    public void stop() {
        if (this.rowThread != null) {
            this.rowThread.stop();
            this.rowThread = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.overButton = true;
        this.showStatus(this.URLstr);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.overButton = false;
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.Og == null || size.width != this.Od.width || size.height != this.Od.height) {
            this.Od = size;
            this.Oi = this.createImage(size.width, size.height);
            this.Og = this.Oi.getGraphics();
        }
        this.Og.setColor(Color.gray);
        this.Og.setClip(0, 0, this.fullWidth, this.fullHeight);
        this.Og.fill3DRect(0, 0, this.fullWidth, this.fullHeight, true);
        this.Og.setClip(4, 4, this.winWidth, this.winHeight);
        this.setSkyColor(this.brightness, this.brightDelta);
        for (int i = 0; i < this.numSkyColors; ++i) {
            this.Og.setColor(this.skyColor[i]);
            this.Og.drawLine(4, 4 + i, this.winWidth + 4, 4 + i);
        }
        this.Og.setColor(Color.white);
        this.thisFont = new Font("Serif", 1, this.fontSize);
        this.Og.setFont(this.thisFont);
        this.Og.drawString(this.textStr, this.textX, this.textY);
        this.Og.setColor(this.mountainColor);
        this.Og.fillPolygon(this.mountsX[0], this.mountsY[0], 32);
        this.Og.setColor(this.hill0Color);
        this.Og.fillPolygon(this.mountsX[1], this.mountsY[1], 32);
        this.Og.setColor(this.hill1Color);
        this.Og.fillPolygon(this.mountsX[2], this.mountsY[2], 32);
        this.Og.setFont(this.tinyFont);
        this.Og.setColor(Color.black);
        this.Og.drawString("Copyright (c) 1999 by Linda Carpenter", this.winWidth / 2, this.winHeight);
        graphics.drawImage(this.Oi, 0, 0, this);
    }
    
    public SunRise() {
        this.stage = 0;
        this.rowThread = null;
        this.Speed = 50;
        this.URLstr = "";
        this.winWidth = 0;
        this.winHeight = 0;
        this.fullWidth = 0;
        this.fullHeight = 0;
        this.buttonPressed = false;
        this.overButton = false;
        this.fontSize = 30;
        this.brightness = 0;
        this.brightDelta = 0.3;
        this.counter = 50;
        this.textX = 0;
        this.textY = 0;
        this.textStr = "";
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.buttonPressed = false;
        this.getAppletContext().showDocument(this.goURL);
        return true;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Text", "String", "Parameter description" }, { "Speed", "int", "Parameter description" }, { "URL", "String", "Parameter description" } };
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.rowThread == null) {
            (this.rowThread = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: SunRise\r\nAuthor: Linda Carpenter\r\n(c) Copyright 1999 by author";
    }
    
    public void buildMountains(final int n) {
        final int n2 = this.winWidth / 30 + 1;
        this.mountsX[n][0] = 0;
        this.mountsY[n][0] = this.winHeight + 4;
        int n3 = 1;
        do {
            this.mountsY[n][n3] = (int)(this.mountainJag[n] * Math.random() - 2.0 * this.mountainJag[n]) + this.winHeight;
            this.mountsX[n][n3] = this.mountsX[n][n3 - 1] + n2;
        } while (++n3 < 31);
        this.mountsY[n][n3 - 1] = this.mountsY[n][0];
        this.mountsY[n][n3] = this.mountsY[n][0];
        this.mountsX[n][n3] = this.mountsX[n][n3 - 1] + n2;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return this.buttonPressed = true;
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    switch (this.stage) {
                        case 0: {
                            if (this.brightness > 120) {
                                ++this.stage;
                            }
                            else {
                                ++this.brightness;
                            }
                            this.textY = this.winHeight - this.brightness + this.fontSize;
                            break;
                        }
                        case 1: {
                            if (this.fontSize == 1) {
                                ++this.stage;
                            }
                            else {
                                --this.fontSize;
                            }
                            this.textX += 2;
                            this.textY += 2;
                            break;
                        }
                        case 2: {
                            if (this.counter <= 0) {
                                ++this.stage;
                                break;
                            }
                            --this.counter;
                            break;
                        }
                        case 3: {
                            this.brightness = 0;
                            this.textX = (this.d.width - this.fm.stringWidth(this.textStr)) / 2;
                            this.textY = this.winHeight - this.brightness + this.fontSize;
                            this.fontSize = 30;
                            this.counter = 50;
                            this.stage = 0;
                            break;
                        }
                    }
                    this.repaint();
                    Thread.sleep(this.Speed);
                }
            }
            catch (InterruptedException ex) {
                System.out.println("Barf");
                this.stop();
                continue;
            }
            break;
        }
    }
    
    public void init() {
        final Graphics graphics = this.getGraphics();
        this.d = this.size();
        this.fullWidth = this.d.width;
        this.fullHeight = this.d.height;
        this.winWidth = this.fullWidth - 8;
        this.winHeight = this.fullHeight - 8;
        this.textStr = this.getParameter("Text");
        final String parameter = this.getParameter("Speed");
        if (parameter != null) {
            this.Speed = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("URL");
        if (parameter2 != null) {
            this.URLstr = parameter2;
        }
        try {
            this.goURL = new URL(this.URLstr);
        }
        catch (MalformedURLException ex) {
            System.out.println("Something is wrong with your URL, Check applet parameter.");
        }
        Toolkit.getDefaultToolkit().getFontList();
        this.thisFont = new Font("Serif", 1, 30);
        this.tinyFont = new Font("Serif", 0, 9);
        graphics.setFont(this.thisFont);
        this.fm = graphics.getFontMetrics();
        this.fontWidth = this.fm.getMaxAdvance();
        this.fontHeight = this.fm.getHeight();
        this.textX = (this.d.width - this.fm.stringWidth(this.textStr)) / 2;
        this.textY = this.winHeight - this.brightness + this.fontSize;
        this.textColor = Color.black;
        this.numSkyColors = this.winHeight;
        this.skyColor = new Color[this.numSkyColors];
        this.cloudColor = new Color(255, 255, 255);
        this.hill0Color = new Color(96, 128, 64);
        this.hill1Color = new Color(128, 192, 128);
        this.mountainColor = new Color(128, 128, 0);
        this.treeColor = new Color(0, 255, 0);
        (this.mountainJag = new double[4])[0] = 25.0;
        this.mountainJag[1] = 15.0;
        this.mountainJag[2] = 7.0;
        this.mountsX = new int[32][32];
        this.mountsY = new int[32][32];
        int n = 0;
        do {
            int n2 = 0;
            do {
                this.mountsX[n][n2] = 0;
                this.mountsY[n][n2] = 0;
            } while (++n2 < 32);
        } while (++n < 4);
        this.buildMountains(0);
        this.buildMountains(1);
        this.buildMountains(2);
    }
    
    public void setSkyColor(final int n, final double n2) {
        int n3 = n;
        final int n4 = 40;
        double n5 = n3;
        for (int i = 0; i < this.numSkyColors; ++i) {
            int n6 = i - n4;
            if (n6 < 0) {
                n6 = 0;
            }
            this.skyColor[i] = new Color(n3 + n6, n3, (int)(2.0 * n3));
            if (n3 < 120.0) {
                n5 += n2;
                n3 = (int)n5;
            }
            if (n6 > 0) {
                --n6;
            }
        }
    }
}
