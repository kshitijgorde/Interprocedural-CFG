import java.awt.Event;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HeadLiner extends Applet implements Runnable
{
    StringBuffer[] myStrings;
    StringBuffer sponsorString;
    Rectangle myRect;
    int nCharHeight;
    int nCharWidth;
    int nOffset;
    int nVisible;
    Font myFont;
    int nNumStrings;
    int nDelay;
    StringBuffer bgColorParam;
    StringBuffer fgColorParam;
    StringBuffer sleepParam;
    StringBuffer borderParam;
    StringBuffer fileParam;
    boolean fileError;
    boolean bBorder;
    Color bgColor;
    Color fgColor;
    Color flashColor;
    boolean bRunning;
    Thread scrollThread;
    Image offscreen;
    Graphics og;
    
    public void update(final Graphics graphics) {
        boolean b = false;
        if (this.nOffset-- <= -this.nCharHeight) {
            this.nOffset = 0;
            b = true;
        }
        if (b) {
            final StringBuffer sb = this.myStrings[0];
            for (int i = 0; i < this.nNumStrings; ++i) {
                this.myStrings[i] = this.myStrings[i + 1];
            }
            this.myStrings[this.nNumStrings] = sb;
        }
        if (this.offscreen == null) {
            this.offscreen = this.createImage(this.myRect.width, this.myRect.height);
            this.og = this.offscreen.getGraphics();
        }
        this.og.setColor(this.bgColor);
        this.og.fillRect(0, 0, this.myRect.width, this.myRect.height);
        this.og.setColor(this.fgColor);
        for (int n = 0; n < this.nVisible && n < this.nNumStrings; ++n) {
            this.og.drawString(this.myStrings[n].toString(), 2, 2 + this.nOffset + this.myRect.y + this.nCharHeight * (n + 1));
        }
        if (this.bBorder) {
            final Dimension dimension = new Dimension();
            dimension.width = this.myRect.width;
            dimension.height = this.myRect.height;
            this.og.drawLine(0, 0, dimension.width - 1, 0);
            this.og.drawLine(dimension.width - 1, 0, dimension.width - 1, dimension.height - 1);
            this.og.drawLine(dimension.width - 1, dimension.height - 1, 0, dimension.height - 1);
            this.og.drawLine(0, dimension.height - 1, 0, 0);
        }
        graphics.drawImage(this.offscreen, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void init() {
        for (int i = 0; i < 1000; ++i) {
            this.myStrings[i] = new StringBuffer(0);
        }
        this.myRect = new Rectangle(0, 0, this.size().width, this.size().height);
        this.myFont = this.getFont();
        this.nCharHeight = this.getToolkit().getFontMetrics(this.myFont).getAscent() + this.getToolkit().getFontMetrics(this.myFont).getDescent();
        this.nCharWidth = this.getToolkit().getFontMetrics(this.myFont).getMaxAdvance();
        this.nOffset = this.nCharHeight;
        this.sponsorString = new StringBuffer("bigfoot.com/~Java4Free");
        this.nVisible = this.myRect.height / this.nCharHeight;
        ++this.nVisible;
        this.nNumStrings = 0;
        this.fileError = false;
        try {
            this.fileParam = new StringBuffer(this.getParameter("FileName"));
        }
        catch (Exception ex) {
            this.fileParam.setLength(0);
            this.fileParam.append("headlines.txt");
        }
        this.readFile();
        try {
            (this.borderParam = new StringBuffer(0)).append(this.getParameter("Border"));
            if (this.borderParam.length() == 1) {
                this.bBorder = (Integer.valueOf(this.borderParam.toString(), 10) == 1);
            }
            else {
                this.bBorder = false;
            }
        }
        catch (Exception ex2) {
            this.bBorder = false;
        }
        try {
            (this.sleepParam = new StringBuffer(0)).append(this.getParameter("ScrollDelay"));
            if (this.sleepParam.length() == 6) {
                this.nDelay = Integer.valueOf(this.sleepParam.toString(), 10);
            }
            else {
                this.nDelay = 10;
            }
        }
        catch (Exception ex3) {
            this.nDelay = 10;
        }
        try {
            (this.bgColorParam = new StringBuffer(0)).append(this.getParameter("BGColor"));
            if (this.bgColorParam.length() == 6) {
                this.bgColor = new Color(Integer.valueOf(this.bgColorParam.toString(), 16));
            }
            else {
                this.bgColor = Color.white;
            }
            this.setBackground(this.bgColor);
        }
        catch (Exception ex4) {
            this.bgColor = Color.white;
        }
        try {
            (this.fgColorParam = new StringBuffer(0)).append(this.getParameter("FGColor"));
            if (this.fgColorParam.length() == 6) {
                this.fgColor = new Color(Integer.valueOf(this.fgColorParam.toString(), 16));
            }
            else {
                this.fgColor = Color.black;
            }
        }
        catch (Exception ex5) {
            this.fgColor = Color.black;
        }
        this.setForeground(this.fgColor);
        if (this.fgColor == Color.red) {
            this.flashColor = Color.blue;
        }
        else {
            this.flashColor = Color.red;
        }
        if (this.scrollThread == null) {
            this.scrollThread = new Thread(this);
            this.bRunning = true;
            this.scrollThread.start();
        }
    }
    
    public void setBorder(final String s) {
        try {
            this.borderParam.setLength(0);
            this.borderParam.append(s);
            if (this.borderParam.length() == 1) {
                this.bBorder = (Integer.valueOf(this.borderParam.toString(), 10) == 1);
                return;
            }
            this.bBorder = false;
        }
        catch (Exception ex) {
            this.bBorder = false;
        }
    }
    
    public void setFileName(final String s) {
        this.fileError = false;
        try {
            this.fileParam.setLength(0);
            this.fileParam.append(s);
        }
        catch (Exception ex) {
            this.fileParam.setLength(0);
            this.fileParam.append("headlines.txt");
            this.fileError = true;
        }
        this.readFile();
    }
    
    public void setScrollDelay(final String s) {
        try {
            this.sleepParam.setLength(0);
            this.sleepParam.append(s);
            if (this.sleepParam.length() == 6) {
                this.nDelay = Integer.valueOf(this.sleepParam.toString(), 10);
                return;
            }
            this.nDelay = 10;
        }
        catch (Exception ex) {
            this.nDelay = 10;
        }
    }
    
    public void setbgColor(final String s) {
        this.bgColorParam.setLength(0);
        this.bgColorParam.append(s);
        try {
            if (this.bgColorParam.length() == 6) {
                this.bgColor = new Color(Integer.valueOf(this.bgColorParam.toString(), 16));
            }
            else {
                this.bgColor = Color.white;
            }
            this.setBackground(this.bgColor);
        }
        catch (Exception ex) {
            this.bgColor = Color.white;
        }
    }
    
    public void setfgColor(final String s) {
        this.fgColorParam.setLength(0);
        this.fgColorParam.append(s);
        try {
            if (this.fgColorParam.length() == 6) {
                this.fgColor = new Color(Integer.valueOf(this.fgColorParam.toString(), 16));
            }
            else {
                this.fgColor = Color.black;
            }
            this.setBackground(this.fgColor);
        }
        catch (Exception ex) {
            this.fgColor = Color.black;
        }
    }
    
    void readFile() {
        for (int i = 0; i < this.nNumStrings; ++i) {
            this.myStrings[i].setLength(0);
        }
        this.fileError = false;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new URL(this.getCodeBase(), this.fileParam.toString()).openConnection().getInputStream()));
            final StringBuffer sb = new StringBuffer();
            this.nNumStrings = 0;
            while (!sb.append(dataInputStream.readLine()).toString().equals("null")) {
                if (this.nNumStrings >= 995) {
                    return;
                }
                this.myStrings[this.nNumStrings].append(sb.toString());
                ++this.nNumStrings;
                sb.setLength(0);
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {
            this.fileError = true;
        }
        catch (FileNotFoundException ex2) {
            this.fileError = true;
        }
        catch (IOException ex3) {
            this.fileError = true;
        }
        catch (SecurityException ex4) {
            this.fileError = true;
        }
    }
    
    public void start() {
        this.bRunning = true;
        (this.scrollThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.bRunning = false;
        this.scrollThread = null;
        this.og = null;
        this.offscreen = null;
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.scrollThread) {
            this.repaint();
            try {
                currentTimeMillis += this.nDelay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("HeadLiner is FREE!  Click Now!  bigfoot.com/~Java4Free");
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.bigfoot.com/~Java4Free"));
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public String getAppletInfo() {
        return "HeadLiner Applet (C) Copyright 1999, Mark Ganson.  bigfoot.com/~java4free";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "BGColor", "int", "background color (6 hex digits rrggbb)" }, { "FGColor", "int", "foreground color (6 hex digits rrggbb)" }, { "ScrollDelay", "int", "scroll delay factor (6 base ten digits)" }, { "Border", "boolean", "1=yes, 0=no" }, { "FileName", "URL", "file containing text to scroll (must be relative URL)" } };
    }
    
    public HeadLiner() {
        this.myStrings = new StringBuffer[1000];
        this.bRunning = true;
    }
}
