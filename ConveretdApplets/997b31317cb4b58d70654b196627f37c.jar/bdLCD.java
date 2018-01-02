import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bdLCD extends Applet implements Runnable
{
    Thread thDisplay;
    boolean initDone;
    boolean running;
    Image offscreen;
    Graphics bufferGraphics;
    Dimension dim;
    int curX;
    int curY;
    Color colorOn;
    Color colorOff;
    Color colorBG;
    boolean displayFull;
    int inRows;
    int inCols;
    int scanSpeed;
    String inString;
    String copyright;
    int inStringPointer;
    int initTime;
    int currentColorStyle;
    char currentChar;
    int pixelWidth;
    int pixelHeight;
    int[][] charNull;
    int[][] charFull;
    int[][] charA;
    int[][] charB;
    int[][] charC;
    int[][] charD;
    int[][] charE;
    int[][] charF;
    int[][] charG;
    int[][] charH;
    int[][] charI;
    int[][] charJ;
    int[][] charK;
    int[][] charL;
    int[][] charM;
    int[][] charN;
    int[][] charO;
    int[][] charP;
    int[][] charQ;
    int[][] charR;
    int[][] charS;
    int[][] charT;
    int[][] charU;
    int[][] charV;
    int[][] charW;
    int[][] charX;
    int[][] charY;
    int[][] charZ;
    int[][] chara;
    int[][] charb;
    int[][] charc;
    int[][] chard;
    int[][] chare;
    int[][] charf;
    int[][] charg;
    int[][] charh;
    int[][] chari;
    int[][] charj;
    int[][] chark;
    int[][] charl;
    int[][] charm;
    int[][] charn;
    int[][] charo;
    int[][] charp;
    int[][] charq;
    int[][] charr;
    int[][] chars;
    int[][] chart;
    int[][] charu;
    int[][] charv;
    int[][] charw;
    int[][] charx;
    int[][] chary;
    int[][] charz;
    int[][] char0;
    int[][] char1;
    int[][] char2;
    int[][] char3;
    int[][] char4;
    int[][] char5;
    int[][] char6;
    int[][] char7;
    int[][] char8;
    int[][] char9;
    int[][] charEx;
    int[][] charAt;
    int[][] charPo;
    int[][] charDo;
    int[][] charPc;
    int[][] charCa;
    int[][] charAm;
    int[][] charAs;
    int[][] charLp;
    int[][] charRp;
    int[][] charDa;
    int[][] charEq;
    int[][] charUs;
    int[][] charPl;
    int[][] charLBr;
    int[][] charRBr;
    int[][] charLBk;
    int[][] charRBk;
    int[][] charPi;
    int[][] charSc;
    int[][] charSq;
    int[][] charCo;
    int[][] charDq;
    int[][] charLt;
    int[][] charGt;
    int[][] charCm;
    int[][] charPd;
    int[][] charFs;
    int[][] charQu;
    int[][] charTi;
    int[][] charTl;
    
    public void init() {
        if (this.getParameter("ColorOn") != null) {
            this.colorOn = new Color(Integer.parseInt(this.getParameter("ColorOn"), 16));
        }
        else {
            this.setElementColor(0, "435B15");
        }
        if (this.getParameter("ColorOff") != null) {
            this.colorOff = new Color(Integer.parseInt(this.getParameter("ColorOff"), 16));
        }
        else {
            this.setElementColor(1, "94C41C");
        }
        if (this.getParameter("ColorBack") != null) {
            this.colorBG = new Color(Integer.parseInt(this.getParameter("ColorBack"), 16));
        }
        else {
            this.setElementColor(2, "9BCA22");
        }
        if (this.getParameter("Text") != null) {
            this.inString = this.getParameter("Text");
        }
        else {
            this.inString = "bartdart.com (o)";
        }
        this.copyright = "bartdart.com";
        if (!this.copyright.equals(this.getParameter("Copyright"))) {
            this.showStatus("You must include Copyright notice in Parameters!");
            this.destroy();
        }
        if (this.getParameter("DrawDelay") != null) {
            this.scanSpeed = Integer.parseInt(this.getParameter("DrawDelay"), 10);
        }
        else {
            this.scanSpeed = 50;
        }
        if (this.getParameter("InitDelay") != null) {
            this.initTime = Integer.parseInt(this.getParameter("InitDelay"), 10);
        }
        else {
            this.initTime = 1000;
        }
        if (this.getParameter("PixelWidth") != null) {
            this.pixelWidth = Integer.parseInt(this.getParameter("PixelWidth"), 10);
        }
        else {
            this.pixelWidth = 2;
        }
        if (this.getParameter("PixelHeight") != null) {
            this.pixelHeight = Integer.parseInt(this.getParameter("PixelHeight"), 10);
        }
        else {
            this.pixelHeight = 2;
        }
        this.curX = 0;
        this.curY = 0;
        this.dim = this.getSize();
        this.inCols = this.dim.width / (5 * this.pixelWidth + 1);
        this.inRows = this.dim.height / (7 * this.pixelHeight + 1);
        this.setBackground(this.colorBG);
        this.inStringPointer = 0;
        this.currentColorStyle = 1;
        this.offscreen = this.createImage(this.dim.width, this.dim.height);
        this.bufferGraphics = this.offscreen.getGraphics();
        this.initDone = false;
    }
    
    public void setElementColor(final int n, final String s) {
        switch (n) {
            case 0: {
                this.colorOn = new Color(Integer.parseInt(s, 16));
            }
            case 1: {
                this.colorOff = new Color(Integer.parseInt(s, 16));
            }
            case 2: {
                this.colorBG = new Color(Integer.parseInt(s, 16));
            }
            default: {}
        }
    }
    
    public void cycleColor() {
        switch (this.currentColorStyle) {
            case 0: {
                this.setElementColor(0, "435B15");
                this.setElementColor(1, "94C41C");
                this.setElementColor(2, "9BCA22");
                break;
            }
            case 1: {
                this.setElementColor(0, "27E0BE");
                this.setElementColor(1, "101211");
                this.setElementColor(2, "000000");
                break;
            }
            case 2: {
                this.setElementColor(0, "2E4929");
                this.setElementColor(1, "72B86A");
                this.setElementColor(2, "7AC572");
                break;
            }
            case 3: {
                this.setElementColor(0, "B3C9EF");
                this.setElementColor(1, "5656D1");
                this.setElementColor(2, "5B7FF5");
                break;
            }
            case 4: {
                this.setElementColor(0, "EF1C39");
                this.setElementColor(1, "B60010");
                this.setElementColor(2, "9F0111");
                break;
            }
            case 5: {
                this.setElementColor(0, "9F2E42");
                this.setElementColor(1, "5C0205");
                this.setElementColor(2, "2F0A27");
                break;
            }
        }
        ++this.currentColorStyle;
        if (this.currentColorStyle > 5) {
            this.currentColorStyle = 0;
        }
        this.setBackground(this.colorBG);
        this.initDone = false;
        this.displayFull = false;
    }
    
    public void scrollDown() {
        if (this.inStringPointer < this.inString.length()) {
            this.displayFull = true;
            this.drawNulls();
            this.repaint();
            this.displayFull = false;
            this.curY = 0;
            this.curX = 0;
            return;
        }
        this.scrollUp();
    }
    
    public void scrollUp() {
        this.curY = 0;
        this.curX = 0;
        this.inStringPointer = 0;
        this.drawNulls();
        this.repaint();
        this.displayFull = false;
    }
    
    public void clearScreen() {
        this.curY = 0;
        this.curX = 0;
        this.displayFull = true;
        this.inStringPointer = 0;
        this.drawNulls();
        this.repaint();
    }
    
    public void turnOff() {
        this.curY = 0;
        this.curX = 0;
        this.displayFull = true;
        this.inStringPointer = 0;
        this.bufferGraphics.clearRect(0, 0, this.dim.width, this.dim.width);
    }
    
    public void turnOn() {
        this.curY = 0;
        this.curX = 0;
        this.displayFull = false;
        this.initDone = false;
        this.inStringPointer = 0;
    }
    
    public void setText(final String inString) {
        this.inString = inString;
        this.clearScreen();
        this.displayFull = false;
    }
    
    public String getText() {
        return this.inString;
    }
    
    public String getElementColor(final int n) {
        switch (n) {
            case 0: {
                return this.colorOn.toString();
            }
            case 1: {
                return this.colorOff.toString();
            }
            case 2: {
                return this.colorBG.toString();
            }
            default: {
                return "0";
            }
        }
    }
    
    public void setDrawDelay(final int scanSpeed) {
        if (scanSpeed > 0) {
            this.scanSpeed = scanSpeed;
        }
    }
    
    public void setInitDelay(final int initTime) {
        if (initTime > 0) {
            this.initTime = initTime;
        }
    }
    
    public void resetDisplay() {
        this.initDone = false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreen, 0, 0, this);
    }
    
    private void drawNextDigit(final char c) {
        if (this.curY >= this.inRows) {
            --this.inStringPointer;
            this.displayFull = true;
            return;
        }
        if (c == '\\') {
            this.curX = 0;
            ++this.curY;
            return;
        }
        if (this.curX < this.inCols && c != '\\') {
            this.drawDigit(c, this.colorOff, this.colorOn, this.curX * (5 * this.pixelWidth + 1), this.curY * (7 * this.pixelHeight + 1));
            ++this.curX;
            return;
        }
        this.curX = 0;
        ++this.curY;
        this.drawDigit(c, this.colorOff, this.colorOn, this.curX * (5 * this.pixelWidth + 1), this.curY * (7 * this.pixelHeight + 1));
        ++this.curX;
    }
    
    private char getNextChar() {
        char char1;
        if (this.inStringPointer < this.inString.length()) {
            char1 = this.inString.charAt(this.inStringPointer);
        }
        else {
            char1 = ' ';
        }
        ++this.inStringPointer;
        return char1;
    }
    
    private void drawInit() {
        this.bufferGraphics.clearRect(0, 0, this.dim.width, this.dim.width);
        this.curX = 0;
        this.curY = 0;
        this.inStringPointer = 0;
        for (int i = 0; i < this.inRows; ++i) {
            for (int j = 0; j < this.inCols; ++j) {
                this.drawDots(this.charFull, this.colorOff, this.colorOn, j * (5 * this.pixelWidth + 1), i * (7 * this.pixelHeight + 1));
            }
        }
        this.initDone = true;
    }
    
    private void drawNulls() {
        this.bufferGraphics.clearRect(0, 0, this.dim.width, this.dim.width);
        for (int i = 0; i < this.inRows; ++i) {
            for (int j = 0; j < this.inCols; ++j) {
                this.drawDots(this.charNull, this.colorOff, this.colorOn, j * (5 * this.pixelWidth + 1), i * (7 * this.pixelHeight + 1));
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        (this.thDisplay = new Thread(this)).start();
    }
    
    public void stop() {
        this.running = false;
    }
    
    public void destroy() {
        this.running = false;
        this.thDisplay = null;
    }
    
    public void run() {
        while (this.running) {
            try {
                if (!this.initDone) {
                    this.drawInit();
                    this.repaint();
                    Thread.currentThread();
                    Thread.sleep(this.initTime);
                    this.drawNulls();
                }
                else {
                    Thread.currentThread();
                    Thread.sleep(this.scanSpeed);
                }
            }
            catch (InterruptedException ex) {}
            if (this.initDone && !this.displayFull && this.inStringPointer < this.inString.length()) {
                this.drawNextDigit(this.getNextChar());
            }
            this.repaint();
        }
    }
    
    private void drawDigit(final char c, final Color color, final Color color2, final int n, final int n2) {
        switch (c) {
            case 'A': {
                this.drawDots(this.charA, color, color2, n, n2);
            }
            case 'a': {
                this.drawDots(this.chara, color, color2, n, n2);
            }
            case 'B': {
                this.drawDots(this.charB, color, color2, n, n2);
            }
            case 'b': {
                this.drawDots(this.charb, color, color2, n, n2);
            }
            case 'C': {
                this.drawDots(this.charC, color, color2, n, n2);
            }
            case 'c': {
                this.drawDots(this.charc, color, color2, n, n2);
            }
            case 'D': {
                this.drawDots(this.charD, color, color2, n, n2);
            }
            case 'd': {
                this.drawDots(this.chard, color, color2, n, n2);
            }
            case 'E': {
                this.drawDots(this.charE, color, color2, n, n2);
            }
            case 'e': {
                this.drawDots(this.chare, color, color2, n, n2);
            }
            case 'F': {
                this.drawDots(this.charF, color, color2, n, n2);
            }
            case 'f': {
                this.drawDots(this.charf, color, color2, n, n2);
            }
            case 'G': {
                this.drawDots(this.charG, color, color2, n, n2);
            }
            case 'g': {
                this.drawDots(this.charg, color, color2, n, n2);
            }
            case 'H': {
                this.drawDots(this.charH, color, color2, n, n2);
            }
            case 'h': {
                this.drawDots(this.charh, color, color2, n, n2);
            }
            case 'I': {
                this.drawDots(this.charI, color, color2, n, n2);
            }
            case 'i': {
                this.drawDots(this.chari, color, color2, n, n2);
            }
            case 'J': {
                this.drawDots(this.charJ, color, color2, n, n2);
            }
            case 'j': {
                this.drawDots(this.charj, color, color2, n, n2);
            }
            case 'K': {
                this.drawDots(this.charK, color, color2, n, n2);
            }
            case 'k': {
                this.drawDots(this.chark, color, color2, n, n2);
            }
            case 'L': {
                this.drawDots(this.charL, color, color2, n, n2);
            }
            case 'l': {
                this.drawDots(this.charl, color, color2, n, n2);
            }
            case 'M': {
                this.drawDots(this.charM, color, color2, n, n2);
            }
            case 'm': {
                this.drawDots(this.charm, color, color2, n, n2);
            }
            case 'N': {
                this.drawDots(this.charN, color, color2, n, n2);
            }
            case 'n': {
                this.drawDots(this.charn, color, color2, n, n2);
            }
            case 'O': {
                this.drawDots(this.charO, color, color2, n, n2);
            }
            case 'o': {
                this.drawDots(this.charo, color, color2, n, n2);
            }
            case 'P': {
                this.drawDots(this.charP, color, color2, n, n2);
            }
            case 'p': {
                this.drawDots(this.charp, color, color2, n, n2);
            }
            case 'Q': {
                this.drawDots(this.charQ, color, color2, n, n2);
            }
            case 'q': {
                this.drawDots(this.charq, color, color2, n, n2);
            }
            case 'R': {
                this.drawDots(this.charR, color, color2, n, n2);
            }
            case 'r': {
                this.drawDots(this.charr, color, color2, n, n2);
            }
            case 'S': {
                this.drawDots(this.charS, color, color2, n, n2);
            }
            case 's': {
                this.drawDots(this.chars, color, color2, n, n2);
            }
            case 'T': {
                this.drawDots(this.charT, color, color2, n, n2);
            }
            case 't': {
                this.drawDots(this.chart, color, color2, n, n2);
            }
            case 'U': {
                this.drawDots(this.charU, color, color2, n, n2);
            }
            case 'u': {
                this.drawDots(this.charu, color, color2, n, n2);
            }
            case 'V': {
                this.drawDots(this.charV, color, color2, n, n2);
            }
            case 'v': {
                this.drawDots(this.charv, color, color2, n, n2);
            }
            case 'W': {
                this.drawDots(this.charW, color, color2, n, n2);
            }
            case 'w': {
                this.drawDots(this.charw, color, color2, n, n2);
            }
            case 'X': {
                this.drawDots(this.charX, color, color2, n, n2);
            }
            case 'x': {
                this.drawDots(this.charx, color, color2, n, n2);
            }
            case 'Y': {
                this.drawDots(this.charY, color, color2, n, n2);
            }
            case 'y': {
                this.drawDots(this.chary, color, color2, n, n2);
            }
            case 'Z': {
                this.drawDots(this.charZ, color, color2, n, n2);
            }
            case 'z': {
                this.drawDots(this.charz, color, color2, n, n2);
            }
            case '0': {
                this.drawDots(this.char0, color, color2, n, n2);
            }
            case '1': {
                this.drawDots(this.char1, color, color2, n, n2);
            }
            case '2': {
                this.drawDots(this.char2, color, color2, n, n2);
            }
            case '3': {
                this.drawDots(this.char3, color, color2, n, n2);
            }
            case '4': {
                this.drawDots(this.char4, color, color2, n, n2);
            }
            case '5': {
                this.drawDots(this.char5, color, color2, n, n2);
            }
            case '6': {
                this.drawDots(this.char6, color, color2, n, n2);
            }
            case '7': {
                this.drawDots(this.char7, color, color2, n, n2);
            }
            case '8': {
                this.drawDots(this.char8, color, color2, n, n2);
            }
            case '9': {
                this.drawDots(this.char9, color, color2, n, n2);
            }
            case '!': {
                this.drawDots(this.charEx, color, color2, n, n2);
            }
            case '@': {
                this.drawDots(this.charAt, color, color2, n, n2);
            }
            case '#': {
                this.drawDots(this.charPo, color, color2, n, n2);
            }
            case '$': {
                this.drawDots(this.charDo, color, color2, n, n2);
            }
            case '%': {
                this.drawDots(this.charPc, color, color2, n, n2);
            }
            case '^': {
                this.drawDots(this.charCa, color, color2, n, n2);
            }
            case '&': {
                this.drawDots(this.charAm, color, color2, n, n2);
            }
            case '*': {
                this.drawDots(this.charAs, color, color2, n, n2);
            }
            case '(': {
                this.drawDots(this.charLp, color, color2, n, n2);
            }
            case ')': {
                this.drawDots(this.charRp, color, color2, n, n2);
            }
            case '-': {
                this.drawDots(this.charDa, color, color2, n, n2);
            }
            case '=': {
                this.drawDots(this.charEq, color, color2, n, n2);
            }
            case '_': {
                this.drawDots(this.charUs, color, color2, n, n2);
            }
            case '+': {
                this.drawDots(this.charPl, color, color2, n, n2);
            }
            case '{': {
                this.drawDots(this.charLBr, color, color2, n, n2);
            }
            case '}': {
                this.drawDots(this.charRBr, color, color2, n, n2);
            }
            case '[': {
                this.drawDots(this.charLBk, color, color2, n, n2);
            }
            case ']': {
                this.drawDots(this.charRBk, color, color2, n, n2);
            }
            case '|': {
                this.drawDots(this.charPi, color, color2, n, n2);
            }
            case ';': {
                this.drawDots(this.charSc, color, color2, n, n2);
            }
            case '\'': {
                this.drawDots(this.charQu, color, color2, n, n2);
            }
            case ':': {
                this.drawDots(this.charCo, color, color2, n, n2);
            }
            case '\"': {
                this.drawDots(this.charDq, color, color2, n, n2);
            }
            case ',': {
                this.drawDots(this.charCm, color, color2, n, n2);
            }
            case '.': {
                this.drawDots(this.charPd, color, color2, n, n2);
            }
            case '/': {
                this.drawDots(this.charFs, color, color2, n, n2);
            }
            case '<': {
                this.drawDots(this.charLt, color, color2, n, n2);
            }
            case '>': {
                this.drawDots(this.charGt, color, color2, n, n2);
            }
            case '?': {
                this.drawDots(this.charQu, color, color2, n, n2);
            }
            case '`': {
                this.drawDots(this.charTi, color, color2, n, n2);
            }
            case '~': {
                this.drawDots(this.charTl, color, color2, n, n2);
            }
            default: {
                this.drawDots(this.charNull, color, color2, n, n2);
            }
        }
    }
    
    private void drawDots(final int[][] array, final Color color, final Color color2, final int n, final int n2) {
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (array[i][j] == 1) {
                    this.bufferGraphics.setColor(color2);
                }
                else {
                    this.bufferGraphics.setColor(color);
                }
                this.bufferGraphics.fillRect(n + j * this.pixelWidth, n2 + i * this.pixelHeight, this.pixelWidth, this.pixelHeight);
            }
        }
    }
    
    public bdLCD() {
        this.running = true;
        this.charNull = new int[][] { new int[5], new int[5], new int[5], new int[5], new int[5], new int[5], new int[5] };
        this.charFull = new int[][] { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };
        this.charA = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 } };
        this.charB = new int[][] { { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0 } };
        final int[][] charC = new int[7][];
        charC[0] = new int[] { 0, 1, 1, 1, 0 };
        charC[1] = new int[] { 1, 0, 0, 0, 1 };
        final int n = 2;
        final int[] array = new int[5];
        array[0] = 1;
        charC[n] = array;
        final int n2 = 3;
        final int[] array2 = new int[5];
        array2[0] = 1;
        charC[n2] = array2;
        final int n3 = 4;
        final int[] array3 = new int[5];
        array3[0] = 1;
        charC[n3] = array3;
        charC[5] = new int[] { 1, 0, 0, 0, 1 };
        charC[6] = new int[] { 0, 1, 1, 1, 0 };
        this.charC = charC;
        this.charD = new int[][] { { 1, 1, 1, 0, 0 }, { 1, 0, 0, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 1, 0 }, { 1, 1, 1, 0, 0 } };
        final int[][] charE = new int[7][];
        charE[0] = new int[] { 1, 1, 1, 1, 1 };
        final int n4 = 1;
        final int[] array4 = new int[5];
        array4[0] = 1;
        charE[n4] = array4;
        final int n5 = 2;
        final int[] array5 = new int[5];
        array5[0] = 1;
        charE[n5] = array5;
        charE[3] = new int[] { 1, 1, 1, 1, 0 };
        final int n6 = 4;
        final int[] array6 = new int[5];
        array6[0] = 1;
        charE[n6] = array6;
        final int n7 = 5;
        final int[] array7 = new int[5];
        array7[0] = 1;
        charE[n7] = array7;
        charE[6] = new int[] { 1, 1, 1, 1, 1 };
        this.charE = charE;
        final int[][] charF = new int[7][];
        charF[0] = new int[] { 1, 1, 1, 1, 1 };
        final int n8 = 1;
        final int[] array8 = new int[5];
        array8[0] = 1;
        charF[n8] = array8;
        final int n9 = 2;
        final int[] array9 = new int[5];
        array9[0] = 1;
        charF[n9] = array9;
        charF[3] = new int[] { 1, 1, 1, 1, 0 };
        final int n10 = 4;
        final int[] array10 = new int[5];
        array10[0] = 1;
        charF[n10] = array10;
        final int n11 = 5;
        final int[] array11 = new int[5];
        array11[0] = 1;
        charF[n11] = array11;
        final int n12 = 6;
        final int[] array12 = new int[5];
        array12[0] = 1;
        charF[n12] = array12;
        this.charF = charF;
        final int[][] charG = new int[7][];
        charG[0] = new int[] { 0, 1, 1, 1, 0 };
        charG[1] = new int[] { 1, 0, 0, 0, 1 };
        final int n13 = 2;
        final int[] array13 = new int[5];
        array13[0] = 1;
        charG[n13] = array13;
        charG[3] = new int[] { 1, 0, 1, 1, 1 };
        charG[4] = new int[] { 1, 0, 0, 0, 1 };
        charG[5] = new int[] { 1, 0, 0, 0, 1 };
        charG[6] = new int[] { 0, 1, 1, 1, 0 };
        this.charG = charG;
        this.charH = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 } };
        this.charI = new int[][] { { 0, 1, 1, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 } };
        this.charJ = new int[][] { { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.charK = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 1, 0 }, { 1, 0, 1, 0, 0 }, { 1, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0 }, { 1, 0, 0, 1, 0 }, { 1, 0, 0, 0, 1 } };
        final int[][] charL = new int[7][];
        final int n14 = 0;
        final int[] array14 = new int[5];
        array14[0] = 1;
        charL[n14] = array14;
        final int n15 = 1;
        final int[] array15 = new int[5];
        array15[0] = 1;
        charL[n15] = array15;
        final int n16 = 2;
        final int[] array16 = new int[5];
        array16[0] = 1;
        charL[n16] = array16;
        final int n17 = 3;
        final int[] array17 = new int[5];
        array17[0] = 1;
        charL[n17] = array17;
        final int n18 = 4;
        final int[] array18 = new int[5];
        array18[0] = 1;
        charL[n18] = array18;
        final int n19 = 5;
        final int[] array19 = new int[5];
        array19[0] = 1;
        charL[n19] = array19;
        charL[6] = new int[] { 1, 1, 1, 1, 1 };
        this.charL = charL;
        this.charM = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 } };
        this.charN = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 0, 1, 1 }, { 1, 0, 0, 1, 1 }, { 1, 0, 0, 0, 1 } };
        this.charO = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        final int[][] charP = { { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0 }, null, null, null };
        final int n20 = 4;
        final int[] array20 = new int[5];
        array20[0] = 1;
        charP[n20] = array20;
        final int n21 = 5;
        final int[] array21 = new int[5];
        array21[0] = 1;
        charP[n21] = array21;
        final int n22 = 6;
        final int[] array22 = new int[5];
        array22[0] = 1;
        charP[n22] = array22;
        this.charP = charP;
        this.charQ = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 1, 1, 1, 1 } };
        this.charR = new int[][] { { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0 }, { 1, 0, 1, 0, 0 }, { 1, 0, 0, 1, 0 }, { 1, 0, 0, 0, 1 } };
        final int[][] charS = new int[7][];
        charS[0] = new int[] { 0, 1, 1, 1, 0 };
        charS[1] = new int[] { 1, 0, 0, 0, 1 };
        final int n23 = 2;
        final int[] array23 = new int[5];
        array23[0] = 1;
        charS[n23] = array23;
        charS[3] = new int[] { 0, 1, 1, 1, 0 };
        charS[4] = new int[] { 0, 0, 0, 0, 1 };
        charS[5] = new int[] { 1, 0, 0, 0, 1 };
        charS[6] = new int[] { 0, 1, 1, 1, 0 };
        this.charS = charS;
        this.charT = new int[][] { { 1, 1, 1, 1, 1 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 } };
        this.charU = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.charV = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 } };
        this.charW = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1 }, { 0, 1, 0, 1, 0 } };
        this.charX = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 } };
        this.charY = new int[][] { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 } };
        final int[][] charZ = { { 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, null, null };
        final int n24 = 5;
        final int[] array24 = new int[5];
        array24[0] = 1;
        charZ[n24] = array24;
        charZ[6] = new int[] { 1, 1, 1, 1, 1 };
        this.charZ = charZ;
        this.chara = new int[][] { new int[5], new int[5], { 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        final int[][] charb = new int[7][];
        final int n25 = 0;
        final int[] array25 = new int[5];
        array25[0] = 1;
        charb[n25] = array25;
        final int n26 = 1;
        final int[] array26 = new int[5];
        array26[0] = 1;
        charb[n26] = array26;
        charb[2] = new int[] { 1, 0, 1, 1, 0 };
        charb[3] = new int[] { 1, 1, 0, 0, 1 };
        charb[4] = new int[] { 1, 0, 0, 0, 1 };
        charb[5] = new int[] { 1, 0, 0, 0, 1 };
        charb[6] = new int[] { 0, 1, 1, 1, 0 };
        this.charb = charb;
        final int[][] charc = { new int[5], new int[5], { 0, 1, 1, 1, 0 }, null, null, null, null };
        final int n27 = 3;
        final int[] array27 = new int[5];
        array27[0] = 1;
        charc[n27] = array27;
        final int n28 = 4;
        final int[] array28 = new int[5];
        array28[0] = 1;
        charc[n28] = array28;
        charc[5] = new int[] { 1, 0, 0, 0, 1 };
        charc[6] = new int[] { 0, 1, 1, 1, 0 };
        this.charc = charc;
        this.chard = new int[][] { { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 1, 1, 0, 1 }, { 1, 0, 0, 1, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        final int[][] chare = { new int[5], new int[5], { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0 }, null, null };
        final int n29 = 5;
        final int[] array29 = new int[5];
        array29[0] = 1;
        chare[n29] = array29;
        chare[6] = new int[] { 0, 1, 1, 1, 0 };
        this.chare = chare;
        this.charf = new int[][] { { 0, 0, 1, 1, 0 }, { 0, 1, 0, 0, 1 }, { 0, 1, 0, 0, 0 }, { 1, 1, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 } };
        this.charg = new int[][] { new int[5], { 0, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        final int[][] charh = new int[7][];
        final int n30 = 0;
        final int[] array30 = new int[5];
        array30[0] = 1;
        charh[n30] = array30;
        final int n31 = 1;
        final int[] array31 = new int[5];
        array31[0] = 1;
        charh[n31] = array31;
        charh[2] = new int[] { 1, 0, 1, 1, 0 };
        charh[3] = new int[] { 1, 1, 0, 0, 1 };
        charh[4] = new int[] { 1, 0, 0, 0, 1 };
        charh[5] = new int[] { 1, 0, 0, 0, 1 };
        charh[6] = new int[] { 1, 0, 0, 0, 1 };
        this.charh = charh;
        this.chari = new int[][] { { 0, 0, 1, 0, 0 }, new int[5], { 0, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 } };
        this.charj = new int[][] { { 0, 0, 0, 1, 0 }, new int[5], { 0, 0, 1, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 1, 0, 0, 1, 0 }, { 0, 1, 1, 0, 0 } };
        final int[][] chark = new int[7][];
        final int n32 = 0;
        final int[] array32 = new int[5];
        array32[0] = 1;
        chark[n32] = array32;
        final int n33 = 1;
        final int[] array33 = new int[5];
        array33[0] = 1;
        chark[n33] = array33;
        chark[2] = new int[] { 1, 0, 0, 1, 0 };
        chark[3] = new int[] { 1, 0, 1, 0, 0 };
        chark[4] = new int[] { 1, 1, 0, 0, 0 };
        chark[5] = new int[] { 1, 0, 1, 0, 0 };
        chark[6] = new int[] { 1, 0, 0, 1, 0 };
        this.chark = chark;
        this.charl = new int[][] { { 0, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 } };
        this.charm = new int[][] { new int[5], new int[5], { 1, 1, 0, 1, 0 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 } };
        this.charn = new int[][] { new int[5], new int[5], { 1, 0, 1, 1, 0 }, { 1, 1, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 } };
        this.charo = new int[][] { new int[5], new int[5], { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        final int[][] charp = { new int[5], new int[5], { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0 }, null, null };
        final int n34 = 5;
        final int[] array34 = new int[5];
        array34[0] = 1;
        charp[n34] = array34;
        final int n35 = 6;
        final int[] array35 = new int[5];
        array35[0] = 1;
        charp[n35] = array35;
        this.charp = charp;
        this.charq = new int[][] { new int[5], new int[5], { 0, 1, 1, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 } };
        final int[][] charr = { new int[5], new int[5], { 1, 0, 1, 1, 0 }, { 1, 1, 0, 0, 1 }, null, null, null };
        final int n36 = 4;
        final int[] array36 = new int[5];
        array36[0] = 1;
        charr[n36] = array36;
        final int n37 = 5;
        final int[] array37 = new int[5];
        array37[0] = 1;
        charr[n37] = array37;
        final int n38 = 6;
        final int[] array38 = new int[5];
        array38[0] = 1;
        charr[n38] = array38;
        this.charr = charr;
        final int[][] chars = { new int[5], new int[5], { 0, 1, 1, 1, 0 }, null, null, null, null };
        final int n39 = 3;
        final int[] array39 = new int[5];
        array39[0] = 1;
        chars[n39] = array39;
        chars[4] = new int[] { 0, 1, 1, 1, 0 };
        chars[5] = new int[] { 0, 0, 0, 0, 1 };
        chars[6] = new int[] { 1, 1, 1, 1, 0 };
        this.chars = chars;
        this.chart = new int[][] { { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 1, 1, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 0, 0, 1, 1, 0 } };
        this.charu = new int[][] { new int[5], new int[5], { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 1, 1, 0, 1 } };
        this.charv = new int[][] { new int[5], new int[5], { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 } };
        this.charw = new int[][] { new int[5], new int[5], { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1 }, { 0, 1, 0, 1, 0 } };
        this.charx = new int[][] { new int[5], new int[5], { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1 } };
        this.chary = new int[][] { new int[5], new int[5], { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.charz = new int[][] { new int[5], new int[5], { 1, 1, 1, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, { 1, 1, 1, 1, 1 } };
        this.char0 = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 1, 1 }, { 1, 0, 0, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.char1 = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 } };
        final int[][] char2 = { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, null, null };
        final int n40 = 5;
        final int[] array40 = new int[5];
        array40[0] = 1;
        char2[n40] = array40;
        char2[6] = new int[] { 1, 1, 1, 1, 1 };
        this.char2 = char2;
        this.char3 = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.char4 = new int[][] { { 0, 0, 0, 1, 0 }, { 0, 0, 1, 1, 0 }, { 0, 1, 0, 1, 0 }, { 1, 1, 1, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 } };
        final int[][] char3 = new int[7][];
        char3[0] = new int[] { 1, 1, 1, 1, 1 };
        final int n41 = 1;
        final int[] array41 = new int[5];
        array41[0] = 1;
        char3[n41] = array41;
        final int n42 = 2;
        final int[] array42 = new int[5];
        array42[0] = 1;
        char3[n42] = array42;
        char3[3] = new int[] { 1, 1, 1, 1, 0 };
        char3[4] = new int[] { 0, 0, 0, 0, 1 };
        char3[5] = new int[] { 1, 0, 0, 0, 1 };
        char3[6] = new int[] { 0, 1, 1, 1, 0 };
        this.char5 = char3;
        final int[][] char4 = new int[7][];
        char4[0] = new int[] { 0, 1, 1, 1, 0 };
        char4[1] = new int[] { 1, 0, 0, 0, 1 };
        final int n43 = 2;
        final int[] array43 = new int[5];
        array43[0] = 1;
        char4[n43] = array43;
        char4[3] = new int[] { 1, 1, 1, 1, 0 };
        char4[4] = new int[] { 1, 0, 0, 0, 1 };
        char4[5] = new int[] { 1, 0, 0, 0, 1 };
        char4[6] = new int[] { 0, 1, 1, 1, 0 };
        this.char6 = char4;
        final int[][] char5 = { { 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, null, null };
        final int n44 = 5;
        final int[] array44 = new int[5];
        array44[0] = 1;
        char5[n44] = array44;
        final int n45 = 6;
        final int[] array45 = new int[5];
        array45[0] = 1;
        char5[n45] = array45;
        this.char7 = char5;
        this.char8 = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.char9 = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.charEx = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, new int[5], { 0, 0, 1, 0, 0 } };
        final int[][] charAt = { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 1, 0, 1, 1 }, { 1, 0, 1, 1, 0 }, null, null };
        final int n46 = 5;
        final int[] array46 = new int[5];
        array46[0] = 1;
        charAt[n46] = array46;
        charAt[6] = new int[] { 0, 1, 1, 1, 0 };
        this.charAt = charAt;
        this.charPo = new int[][] { { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 0, 1, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 } };
        this.charDo = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.charPc = new int[][] { { 1, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, { 1, 0, 0, 1, 1 }, { 1, 0, 0, 1, 1 } };
        this.charCa = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1 }, new int[5], new int[5], new int[5], new int[5] };
        this.charAm = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 1, 1, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1 }, { 0, 1, 1, 1, 0 } };
        this.charAs = new int[][] { new int[5], { 0, 0, 1, 0, 0 }, { 1, 0, 1, 0, 1 }, { 0, 1, 1, 1, 0 }, { 1, 0, 1, 0, 1 }, { 0, 0, 1, 0, 0 }, new int[5] };
        this.charLp = new int[][] { { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0 } };
        this.charRp = new int[][] { { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 } };
        this.charDa = new int[][] { new int[5], new int[5], new int[5], { 1, 1, 1, 1, 1 }, new int[5], new int[5], new int[5] };
        this.charEq = new int[][] { new int[5], new int[5], { 1, 1, 1, 1, 1 }, new int[5], { 1, 1, 1, 1, 1 }, new int[5], new int[5] };
        this.charUs = new int[][] { new int[5], new int[5], new int[5], new int[5], new int[5], new int[5], { 1, 1, 1, 1, 1 } };
        this.charPl = new int[][] { new int[5], { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 1, 1, 1, 1, 1 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, new int[5] };
        this.charLBr = new int[][] { { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0 } };
        this.charRBr = new int[][] { { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 } };
        this.charLBk = new int[][] { { 0, 1, 1, 1, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 1, 1, 0 } };
        this.charRBk = new int[][] { { 0, 1, 1, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 1, 1, 1, 0 } };
        this.charPi = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 } };
        this.charSc = new int[][] { new int[5], { 0, 1, 1, 0, 0 }, { 0, 1, 1, 0, 0 }, new int[5], { 0, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 } };
        this.charSq = new int[][] { { 0, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, new int[5], new int[5], new int[5], new int[5] };
        this.charCo = new int[][] { new int[5], { 0, 1, 1, 0, 0 }, { 0, 1, 1, 0, 0 }, new int[5], { 0, 1, 1, 0, 0 }, { 0, 1, 1, 0, 0 }, new int[5] };
        this.charDq = new int[][] { { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 }, new int[5], new int[5], new int[5], new int[5] };
        final int[][] charLt = { { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, null, null, null, null };
        final int n47 = 3;
        final int[] array47 = new int[5];
        array47[0] = 1;
        charLt[n47] = array47;
        charLt[4] = new int[] { 0, 1, 0, 0, 0 };
        charLt[5] = new int[] { 0, 0, 1, 0, 0 };
        charLt[6] = new int[] { 0, 0, 0, 1, 0 };
        this.charLt = charLt;
        this.charGt = new int[][] { { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 } };
        this.charCm = new int[][] { new int[5], new int[5], new int[5], new int[5], { 0, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 } };
        this.charPd = new int[][] { new int[5], new int[5], new int[5], new int[5], new int[5], { 0, 1, 1, 0, 0 }, { 0, 1, 1, 0, 0 } };
        final int[][] charFs = { { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, null };
        final int n48 = 6;
        final int[] array48 = new int[5];
        array48[0] = 1;
        charFs[n48] = array48;
        this.charFs = charFs;
        this.charQu = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, new int[5], { 0, 0, 1, 0, 0 } };
        this.charTi = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, new int[5], new int[5], new int[5], new int[5] };
        this.charTl = new int[][] { { 1, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, new int[5], new int[5], new int[5], new int[5], new int[5] };
    }
}
