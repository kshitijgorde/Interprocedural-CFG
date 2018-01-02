import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SillyScrollTrial extends Applet implements Runnable
{
    boolean clicked;
    Color color;
    long downTime;
    int manualPause;
    int linkClick;
    int specialBackground;
    boolean special;
    long startTime;
    boolean link;
    int MyWidth;
    boolean paused;
    Font[] fontArray3;
    boolean mouseexit;
    int mousex;
    int mousey;
    int lastlined;
    boolean once;
    String sColor;
    int tempInt;
    boolean checkError;
    String error;
    int lines;
    String[] lineArray;
    int[] sizeArray;
    Color[] colorArray;
    String[] linkArray;
    int[] pauseLineArray;
    String temp;
    String[] fontArray;
    int[] styleArray;
    int[] lineOffsetArray;
    String[] effectArray;
    int lineDown;
    Thread thread;
    Image offscreen;
    Graphics offscreeng;
    int width;
    int height;
    Font f;
    FontMetrics fm;
    int fs;
    int ypos;
    int lineSpace;
    int diff;
    int startLines;
    int pauseValue;
    Color background;
    int totLines;
    int adjYpos;
    boolean run;
    int lineOffsetDefault;
    String[] SColorArray;
    Color[] ColorMap;
    String[] SStyleArray;
    int[] StyleMap;
    String[] fontArray2;
    int lineSelect;
    int lastLink;
    int lined;
    long diffTime;
    Graphics gb;
    Font fullFont;
    String[] Char;
    int[] startX;
    int[] startY;
    int[] endX;
    int[] endY;
    int[] diffX;
    int[] diffY;
    int appWidth;
    int appHeight;
    int myx;
    int myy;
    Image imageBuffer;
    FontMetrics fnm;
    int z;
    int effectIndex;
    Color borderColor;
    String unregistered;
    boolean allowed;
    String status;
    String slogan;
    long displayStatus;
    int nextDispl;
    
    public SillyScrollTrial() {
        this.clicked = false;
        this.special = false;
        this.link = false;
        this.paused = false;
        this.mouseexit = true;
        this.mousex = 0;
        this.mousey = 0;
        this.lastlined = 0;
        this.once = true;
        this.sColor = "";
        this.checkError = true;
        this.error = "";
        this.lineDown = 0;
        this.offscreen = null;
        this.ypos = 0;
        this.startLines = 0;
        this.run = true;
        this.SColorArray = new String[] { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "red", "white", "yellow" };
        this.ColorMap = new Color[] { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
        this.SStyleArray = new String[] { "PLAIN", "ITALIC", "BOLD" };
        this.StyleMap = new int[] { 0, 2, 1 };
        this.fontArray2 = new String[] { "Dialog", "Helvetica", "TimesRoman", "Courier" };
        this.lineSelect = -1;
        this.lastLink = -1;
        this.lined = 0;
        this.z = 0;
        this.effectIndex = -1;
        this.unregistered = "Unregistered: Contact support@consultcom.com";
        this.allowed = true;
        this.status = "";
        this.slogan = "FREE Java Applets at www.consultcom.com";
        this.displayStatus = 0L;
        this.nextDispl = 5000;
    }
    
    private void buildCharacters(final int n, final boolean b) {
        this.myy = this.lineSpace;
        this.myx = this.lineOffsetArray[n];
        this.Char = new String[this.lineArray[n].length()];
        this.startX = new int[this.lineArray[n].length()];
        this.startY = new int[this.lineArray[n].length()];
        this.endX = new int[this.lineArray[n].length()];
        this.endY = new int[this.lineArray[n].length()];
        this.diffX = new int[this.lineArray[n].length()];
        this.diffY = new int[this.lineArray[n].length()];
        int i = 0;
        int n2 = 1;
        int n3 = -1;
        int stringWidth = 0;
        this.fullFont = this.fontArray3[n];
        this.fnm = this.getFontMetrics(this.fullFont);
        while (i < this.lineArray[n].length()) {
            final String substring = this.lineArray[n].substring(i, n2);
            if (i > this.lineArray[n].length() / 2) {
                n3 = 1;
            }
            this.handleCharacter(i, substring, this.myx + stringWidth, this.myy, n3, b);
            stringWidth = this.fnm.stringWidth(this.lineArray[n].substring(0, n2));
            ++i;
            ++n2;
        }
    }
    
    void checkPause() {
        for (int i = 0; i < this.lines; ++i) {
            if (this.pauseLineArray[i] > 0 && this.ypos == -(this.startLines + this.lineSpace * i + this.sizeArray[i] / 3 - (this.sizeArray[i] - this.lineSpace) - 4)) {
                ++this.ypos;
                this.paused = true;
                this.pause(this.pauseLineArray[i]);
                this.paused = false;
                --this.ypos;
                return;
            }
        }
    }
    
    boolean checkSecurity() {
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            this.error = this.unregistered;
            this.showStatus(this.status = this.unregistered);
            return false;
        }
        if (parameter.compareTo("Silly Scroll v1.0 Copyright (c) 2000, consulting.com Inc.") == 0) {
            return true;
        }
        this.error = this.unregistered;
        this.showStatus(this.status = this.unregistered);
        return false;
    }
    
    void doEffect() {
        int n = this.ypos * -1 / this.lineSpace;
        if (this.ypos * -1 % this.lineSpace != 0) {
            return;
        }
        if (--n < 0 || n >= this.lineArray.length) {
            return;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.fontArray[n], 2, this.sizeArray[n]));
        final int compareTo = this.effectArray[n].compareTo("left");
        final int compareTo2 = this.effectArray[n].compareTo("right");
        final int compareTo3 = this.effectArray[n].compareTo("grow");
        final int compareTo4 = this.effectArray[n].compareTo("fly");
        if (compareTo == 0) {
            this.slideText(n, this.size().width, -1);
            return;
        }
        if (compareTo2 == 0) {
            this.slideText(n, -fontMetrics.stringWidth(this.lineArray[n]), 1);
            return;
        }
        if (compareTo3 == 0 || compareTo4 == 0) {
            boolean b = false;
            if (compareTo4 == 0) {
                b = true;
            }
            this.drawCharacters(n, b);
        }
    }
    
    void drawCharacters(final int effectIndex, final boolean b) {
        this.buildCharacters(this.effectIndex = effectIndex, b);
        this.fullFont = this.fontArray3[effectIndex];
        final Font font = new Font(this.fontArray[effectIndex], this.styleArray[effectIndex], this.sizeArray[effectIndex] - 9);
        this.gb.setFont(this.fullFont);
        this.gb.setColor(this.colorArray[effectIndex]);
        final Graphics graphics = this.getGraphics();
        graphics.clipRect(3, this.size().height - this.lineSpace * 2 - 3, this.size().width - 6, this.lineSpace * 2 - 3);
        final Graphics graphics2 = this.getGraphics();
        graphics2.clipRect(3, 3, this.size().width - 6, this.size().height - this.lineSpace * 2);
        for (int i = 0; i < this.lineArray[effectIndex].length(); ++i) {
            for (int j = 0; j < 9; ++j) {
                this.gb.setColor(this.background);
                this.gb.fillRect(0, 0, this.size().width, this.lineSpace * 2);
                if (effectIndex > 0) {
                    this.gb.setColor(this.colorArray[effectIndex - 1]);
                    this.gb.setFont(this.fontArray3[effectIndex - 1]);
                    this.gb.drawString(this.lineArray[effectIndex - 1], this.lineOffsetArray[effectIndex - 1], 0);
                }
                this.gb.setColor(this.colorArray[effectIndex]);
                this.gb.setFont(this.fullFont);
                this.gb.drawString(this.lineArray[effectIndex].substring(0, i), this.myx, this.myy);
                this.gb.setFont(new Font(font.getName(), 2, font.getSize() + j));
                this.gb.drawString(this.Char[i], this.startX[i], this.startY[i]);
                graphics.drawImage(this.imageBuffer, 0, this.size().height - this.lineSpace * 2, this);
                if (b) {
                    this.pause(15);
                }
                else {
                    this.pause(10);
                }
                final int[] startX = this.startX;
                final int n = i;
                startX[n] -= this.diffX[i];
                final int[] startY = this.startY;
                final int n2 = i;
                startY[n2] -= this.diffY[i];
                graphics2.drawImage(this.offscreen, 0, this.ypos, this);
            }
        }
        this.lineDown = this.lineSpace * effectIndex;
        this.lineDown += this.startLines;
        this.lined = this.lineDown + this.lineSpace;
        this.offscreeng.setFont(this.fontArray3[effectIndex]);
        this.offscreeng.setColor(this.colorArray[effectIndex]);
        this.offscreeng.drawString(this.lineArray[effectIndex], this.lineOffsetArray[effectIndex], this.lined);
        final Graphics graphics3 = this.getGraphics();
        graphics3.setColor(this.background);
        graphics3.fillRect(3, this.size().height - this.lineSpace, this.size().width - 3, this.lineSpace - 3);
        this.effectIndex = -1;
    }
    
    Color getColor(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final int index = this.temp.indexOf(",");
        if (index <= 0) {
            for (int i = 0; i < this.SColorArray.length; ++i) {
                if (this.temp.compareTo(this.SColorArray[i]) == 0) {
                    return this.ColorMap[i];
                }
            }
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final String substring = this.temp.substring(0, index);
        final int index2 = this.temp.indexOf(",", index + 1);
        if (index2 < 0) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final String substring2 = this.temp.substring(index + 1, index2);
        final String substring3 = this.temp.substring(index2 + 1, this.temp.length());
        int intValue;
        try {
            intValue = Integer.valueOf(substring);
        }
        catch (NumberFormatException ex) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        int intValue2;
        try {
            intValue2 = Integer.valueOf(substring2);
        }
        catch (NumberFormatException ex2) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        int intValue3;
        try {
            intValue3 = Integer.valueOf(substring3);
        }
        catch (NumberFormatException ex3) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        if (intValue > 255 || intValue2 > 255 || intValue3 > 255) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        return new Color(intValue, intValue2, intValue3);
    }
    
    int getFont(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        for (int i = 0; i < this.fontArray2.length; ++i) {
            if (this.temp.compareTo(this.fontArray2[i]) == 0) {
                return i;
            }
        }
        this.error = "error: " + s + " value=\"" + this.temp + "\"";
        return -2;
    }
    
    int getNumber(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        int intValue;
        try {
            intValue = Integer.valueOf(this.temp);
        }
        catch (NumberFormatException ex) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -2;
        }
        return intValue;
    }
    
    int getStyle(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        for (int i = 0; i < this.SStyleArray.length; ++i) {
            if (this.temp.compareTo(this.SStyleArray[i]) == 0) {
                return i;
            }
        }
        this.error = "error: " + s + " value=\"" + this.temp + "\"";
        return -2;
    }
    
    void handleCharacter(final int n, final String s, final int n2, final int n3, final int n4, final boolean b) {
        this.Char[n] = s;
        this.endX[n] = n2;
        this.endY[n] = n3;
        if (b) {
            this.diffX[n] = (int)Math.floor(Math.random() * 25.0);
            this.diffY[n] = (int)Math.floor(Math.random() * 25.0);
        }
        else {
            this.diffX[n] = 0;
            this.diffY[n] = 0;
        }
        this.diffX[n] *= n4;
        this.startX[n] = n2;
        this.startY[n] = n3;
        for (int i = 0; i < 8; ++i) {
            final int[] startX = this.startX;
            startX[n] += this.diffX[n];
            final int[] startY = this.startY;
            startY[n] += this.diffY[n];
        }
    }
    
    public void init() {
        super.init();
        if (!(this.allowed = this.checkSecurity())) {
            return;
        }
        this.showStatus(this.status = this.slogan);
        this.displayStatus = System.currentTimeMillis() + this.nextDispl;
        this.lines = this.getNumber("numberOflines");
        if (this.lines < 0) {
            return;
        }
        this.lineArray = new String[this.lines];
        this.sizeArray = new int[this.lines];
        this.colorArray = new Color[this.lines];
        this.fontArray = new String[this.lines];
        this.styleArray = new int[this.lines];
        this.linkArray = new String[this.lines];
        this.pauseLineArray = new int[this.lines];
        this.lineOffsetArray = new int[this.lines];
        this.fontArray3 = new Font[this.lines];
        this.effectArray = new String[this.lines];
        this.borderColor = this.getColor("borderColor");
        if (this.borderColor == null) {
            this.borderColor = Color.lightGray;
        }
        this.manualPause = this.getNumber("manualPause");
        if (this.manualPause < 0) {
            this.manualPause = 0;
        }
        this.linkClick = this.getNumber("linkClick");
        if (this.linkClick < 0) {
            this.linkClick = 1;
        }
        if (this.linkClick > 2) {
            this.linkClick = 2;
        }
        this.temp = this.getParameter("background");
        if (this.temp == null) {
            this.error = "error: background value=\"null\"";
            return;
        }
        this.color = this.getColor("background");
        if (this.color == null) {
            return;
        }
        this.setBackground(this.background = this.color);
        this.lineSpace = this.getNumber("lineSpace");
        if (this.lineSpace < 0) {
            return;
        }
        this.pauseValue = this.getNumber("scrollDelay");
        if (this.pauseValue < 0) {
            return;
        }
        this.startLines = this.size().height - this.lineSpace;
        this.totLines = this.lineSpace * this.lines;
        this.height = this.size().height * 2 + this.totLines;
        this.offscreen = this.createImage(this.size().width, this.height);
        (this.offscreeng = this.offscreen.getGraphics()).setColor(this.getBackground());
        this.offscreeng.fillRect(0, 0, this.size().width, this.height);
        for (int i = 0; i < this.lines; ++i) {
            this.effectArray[i] = this.getParameter("effectDefault");
            if (this.effectArray[i] == null) {
                this.lineArray[i] = "no";
            }
            this.pauseLineArray[i] = this.getNumber("pauseValueDefault");
            if (this.pauseLineArray[i] < 0) {
                return;
            }
            this.lineArray[i] = this.getParameter("lineDefault");
            if (this.lineArray[i] == null) {
                this.lineArray[i] = "";
            }
            this.sizeArray[i] = this.getNumber("sizeDefault");
            if (this.sizeArray[i] < 0) {
                return;
            }
            this.color = this.getColor("colorDefault");
            if (this.color == null) {
                return;
            }
            this.colorArray[i] = this.color;
            this.tempInt = this.getFont("fontDefault");
            if (this.tempInt < 0) {
                return;
            }
            this.fontArray[i] = this.fontArray2[this.tempInt];
            this.tempInt = this.getStyle("styleDefault");
            if (this.tempInt < 0) {
                return;
            }
            this.styleArray[i] = this.StyleMap[this.tempInt];
            this.linkArray[i] = this.getParameter("linkDefault");
            if (this.linkArray[i] == null) {
                this.linkArray[i] = "";
            }
            if (this.linkArray[i].compareTo("") != 0) {
                this.link = true;
            }
            this.lineOffsetArray[i] = this.getNumber("lineOffsetDefault");
            if (this.lineOffsetArray[i] < 0) {
                this.lineOffsetArray[i] = 0;
            }
        }
        for (int j = 0; j < this.lines; ++j) {
            this.temp = this.getParameter("effect" + Integer.toString(j + 1));
            if (this.temp != null) {
                this.effectArray[j] = this.temp;
            }
            this.tempInt = this.getNumber("lineOffset" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.lineOffsetArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.tempInt = this.getNumber("pauseValue" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.pauseLineArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.temp = this.getParameter("line" + Integer.toString(j + 1));
            if (this.temp != null) {
                this.lineArray[j] = this.temp;
            }
            this.tempInt = this.getNumber("size" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.sizeArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.color = this.getColor("color" + Integer.toString(j + 1));
            if (this.color != null) {
                this.colorArray[j] = this.color;
            }
            this.tempInt = this.getFont("font" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.fontArray[j] = this.fontArray2[this.tempInt];
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.tempInt = this.getStyle("style" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.styleArray[j] = this.StyleMap[this.tempInt];
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.temp = this.getParameter("link" + Integer.toString(j + 1));
            if (this.temp != null) {
                this.linkArray[j] = this.temp;
                this.link = true;
            }
            this.fontArray3[j] = new Font(this.fontArray[j], this.styleArray[j], this.sizeArray[j]);
            this.offscreeng.setFont(this.fontArray3[j]);
            this.fm = this.offscreeng.getFontMetrics();
            this.offscreeng.setColor(this.colorArray[j]);
            this.offscreeng.drawString(this.lineArray[j], this.lineOffsetArray[j], this.startLines + this.lineSpace * (j + 1));
        }
        this.checkError = false;
        this.MyWidth = this.size().width;
        this.appWidth = this.size().width;
        this.appHeight = this.size().height;
        this.imageBuffer = this.createImage(this.appWidth, this.lineSpace * 2);
        (this.gb = this.imageBuffer.getGraphics()).setColor(this.background);
        this.gb.fillRect(0, 0, this.size().width, this.lineSpace * 2);
        this.startTime = System.currentTimeMillis();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseexit = false;
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.checkError) {
            if (this.once) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.size().width, this.size().height);
                this.once = false;
            }
            graphics.setFont(this.f = new Font("Dialog", 0, 12));
            graphics.setColor(Color.black);
            graphics.drawString(this.error, 0, 20);
            if (!this.allowed) {
                this.status = "Unregistered: Contact support@consultcom.com";
            }
            this.showStatus(this.status);
        }
        else {
            final int n = this.ypos * -1 / this.lineSpace;
            boolean b = true;
            int compareTo = -1;
            int compareTo2 = -1;
            int compareTo3 = -1;
            int compareTo4 = -1;
            if (n >= 0 && n < this.lineArray.length) {
                compareTo = this.effectArray[n].compareTo("left");
                compareTo2 = this.effectArray[n].compareTo("right");
                compareTo3 = this.effectArray[n].compareTo("grow");
                compareTo4 = this.effectArray[n].compareTo("fly");
            }
            if (compareTo != 0 && compareTo2 != 0 && compareTo3 != 0 && compareTo4 != 0) {
                b = false;
            }
            if (b) {
                this.lineDown = this.lineSpace * n;
                this.lineDown += this.startLines;
                this.lined = this.lineDown + this.lineSpace;
                this.offscreeng.setFont(this.fontArray3[n]);
                this.offscreeng.setColor(this.background);
                this.offscreeng.drawString(this.lineArray[n], this.lineOffsetArray[n], this.lined);
            }
            graphics.setColor(this.borderColor);
            for (int i = 0; i < 3; ++i) {
                graphics.draw3DRect(i, i, this.size().width - i * 2 - 1, this.size().height - i * 2 - 1, true);
            }
            graphics.clipRect(3, 3, this.size().width - 6, this.size().height - this.lineSpace);
            graphics.drawImage(this.offscreen, 0, this.ypos, this);
        }
    }
    
    void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        while (this.thread.isAlive()) {
            this.startTime = System.currentTimeMillis();
            if (this.run) {
                if (this.ypos < -(this.startLines + this.lineSpace * this.lines)) {
                    this.ypos = -1;
                }
                else {
                    --this.ypos;
                }
            }
            this.checkPause();
            this.repaint();
            if (!this.checkError) {
                this.doEffect();
            }
            if (System.currentTimeMillis() > this.displayStatus && this.allowed) {
                this.showStatus(this.status = this.slogan);
                this.displayStatus = System.currentTimeMillis() + this.nextDispl;
            }
            this.diffTime = System.currentTimeMillis() - this.startTime;
            if (this.diffTime < this.pauseValue) {
                this.pause(this.pauseValue - (int)this.diffTime);
            }
        }
    }
    
    boolean setBoolean(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return false;
        }
        this.z = parameter.compareTo("yes");
        return this.z == 0;
    }
    
    void slideText(final int effectIndex, final int n, final int n2) {
        this.effectIndex = effectIndex;
        this.fullFont = new Font(this.fontArray[effectIndex], 2, this.sizeArray[effectIndex]);
        this.gb.setFont(this.fullFont);
        this.getFontMetrics(this.fullFont);
        this.gb.setColor(this.colorArray[effectIndex]);
        final Graphics graphics = this.getGraphics();
        graphics.clipRect(3, this.size().height - this.lineSpace * 2 - 3, this.size().width - 6, this.lineSpace * 2 - 3);
        final Graphics graphics2 = this.getGraphics();
        graphics2.clipRect(3, 6, this.size().width - 6, this.size().height - this.lineSpace * 2);
        int i = n;
        while (i != this.lineOffsetArray[effectIndex]) {
            this.gb.setColor(this.background);
            this.gb.fillRect(0, 0, this.size().width, this.lineSpace * 2);
            if (effectIndex > 0) {
                this.gb.setColor(this.colorArray[effectIndex - 1]);
                this.gb.setFont(this.fontArray3[effectIndex - 1]);
                this.gb.drawString(this.lineArray[effectIndex - 1], this.lineOffsetArray[effectIndex - 1], 0);
            }
            this.gb.setColor(this.colorArray[effectIndex]);
            this.gb.setFont(this.fullFont);
            this.gb.drawString(this.lineArray[effectIndex], i += n2, this.lineSpace + 1);
            graphics.drawImage(this.imageBuffer, 0, this.size().height - this.lineSpace * 2, this);
            this.pause(2);
            graphics2.drawImage(this.offscreen, 0, this.ypos, this);
        }
        this.lineDown = this.lineSpace * effectIndex;
        this.lineDown += this.startLines;
        this.lined = this.lineDown + this.lineSpace;
        this.offscreeng.setFont(this.fontArray3[effectIndex]);
        this.offscreeng.setColor(this.colorArray[effectIndex]);
        this.offscreeng.drawString(this.lineArray[effectIndex], this.lineOffsetArray[effectIndex], this.lined);
        final Graphics graphics3 = this.getGraphics();
        graphics3.setColor(this.background);
        graphics3.fillRect(3, this.size().height - this.lineSpace, this.size().width - 3, this.lineSpace - 3);
        this.effectIndex = -1;
    }
    
    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
        }
        this.thread = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
