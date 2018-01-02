import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Canvas;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollDisp2 extends Applet implements Runnable
{
    ScrollCanvas2 myScrollCanvas;
    int iScrollCount;
    Color colorAppletBackground;
    Color colorScrollBackground;
    Color colorScroll1;
    Color colorScroll2;
    Color colorScroll3;
    Color colorScroll4;
    Color colorScroll5;
    int iMaxCharsInLine;
    int iFontSize;
    int iNumberOfLinesInList;
    int iXFaster;
    int iYFaster;
    int iXSlower;
    int iYSlower;
    int iXUp;
    int iYUp;
    int iXDown;
    int iYDown;
    int iXStop;
    int iYStop;
    int iXStart;
    int iYStart;
    int iScrollX;
    int iScrollY;
    int iScrollW;
    int iScrollH;
    int iDelta;
    Thread checkNumbers;
    int MAX_LIST;
    String[] sNameList;
    int iScrollSpeed;
    int iScrollDirection;
    String sCenter;
    int iFine;
    String sInput;
    Image skinImage;
    boolean bStop;
    int iScrollPlace;
    int[] iInput;
    
    public ScrollDisp2() {
        this.iScrollCount = 9;
        this.iMaxCharsInLine = 18;
        this.iFontSize = 30;
        this.iNumberOfLinesInList = 15;
        this.iDelta = 8;
        this.checkNumbers = null;
        this.MAX_LIST = 2000;
        this.sNameList = new String[this.MAX_LIST];
        this.iScrollSpeed = 100;
        this.iScrollDirection = 1;
        this.iFine = 2;
        this.skinImage = null;
        this.bStop = false;
        this.iInput = new int[4];
    }
    
    public void init() {
        final int iBreak = 0;
        final int iStart = 0;
        final int iEnd = 0;
        int iPlace = 0;
        System.out.println("Version 2.22.2001");
        String sBGImage = this.getParameter("BackgroundImage");
        if (sBGImage == null) {
            sBGImage = "Honor.gif";
        }
        this.setLayout(null);
        final URL codebase = this.getCodeBase();
        (this.skinImage = this.getImage(codebase, sBGImage)).getWidth(this);
        this.sCenter = this.getParameter("centerText");
        if (this.sCenter == null) {
            this.sCenter = "NO";
        }
        this.convertS2I(this.sInput = this.getParameter("colorAppletBackgroundColor"), "255,255,255", this.iInput);
        this.setBackground(this.colorAppletBackground = new Color(this.iInput[0], this.iInput[1], this.iInput[2]));
        this.convertS2I(this.sInput = this.getParameter("colorScrollBackground"), "255,255,255", this.iInput);
        this.colorScrollBackground = new Color(this.iInput[0], this.iInput[1], this.iInput[2]);
        this.convertS2I(this.sInput = this.getParameter("colorScroll1"), "0,0,0", this.iInput);
        this.colorScroll1 = new Color(this.iInput[0], this.iInput[1], this.iInput[2]);
        this.convertS2I(this.sInput = this.getParameter("colorScroll2"), "0,200,0", this.iInput);
        this.colorScroll2 = new Color(this.iInput[0], this.iInput[1], this.iInput[2]);
        this.convertS2I(this.sInput = this.getParameter("colorScroll3"), "200,0,0", this.iInput);
        this.colorScroll3 = new Color(this.iInput[0], this.iInput[1], this.iInput[2]);
        this.convertS2I(this.sInput = this.getParameter("colorScroll4"), "0,0,0", this.iInput);
        this.colorScroll4 = new Color(this.iInput[0], this.iInput[1], this.iInput[2]);
        this.convertS2I(this.sInput = this.getParameter("colorScroll5"), "0,0,0", this.iInput);
        this.colorScroll5 = new Color(this.iInput[0], this.iInput[1], this.iInput[2]);
        this.convertS2I(this.sInput = this.getParameter("iMaxCharsInLine"), "18", this.iInput);
        this.iMaxCharsInLine = this.iInput[0];
        System.out.println(this.iMaxCharsInLine);
        this.convertS2I(this.sInput = this.getParameter("iFontSize"), "12", this.iInput);
        this.iFontSize = this.iInput[0];
        this.convertS2I(this.sInput = this.getParameter("iNumberOfLinesInList"), "18", this.iInput);
        this.iNumberOfLinesInList = this.iInput[0];
        this.convertS2I(this.sInput = this.getParameter("iSlower"), "15,257", this.iInput);
        this.iXSlower = this.iInput[0];
        this.iYSlower = this.iInput[1];
        this.convertS2I(this.sInput = this.getParameter("iFaster"), "31,257", this.iInput);
        this.iXFaster = this.iInput[0];
        this.iYFaster = this.iInput[1];
        this.convertS2I(this.sInput = this.getParameter("iUp"), "60,257", this.iInput);
        this.iXUp = this.iInput[0];
        this.iYUp = this.iInput[1];
        this.convertS2I(this.sInput = this.getParameter("iDown"), "75,257", this.iInput);
        this.iXDown = this.iInput[0];
        this.iYDown = this.iInput[1];
        this.convertS2I(this.sInput = this.getParameter("iStart"), "106,257", this.iInput);
        this.iXStart = this.iInput[0];
        this.iYStart = this.iInput[1];
        this.convertS2I(this.sInput = this.getParameter("iStop"), "120,257", this.iInput);
        this.iXStop = this.iInput[0];
        this.iYStop = this.iInput[1];
        this.convertS2I(this.sInput = this.getParameter("iScrollBounds"), "10,35,120,195", this.iInput);
        this.iScrollX = this.iInput[0];
        this.iScrollY = this.iInput[1];
        this.iScrollW = this.iInput[2];
        this.iScrollH = this.iInput[3];
        String sNames = this.getParameter("NameList");
        final int iLength = sNames.length() - 1;
        if (iLength <= 0) {
            sNames = "Your Name Here!|";
        }
        final byte[] bBuffer = sNames.getBytes();
        byte bColor = 1;
        int iStartOfLine = 0;
        int iLastBlank = -1;
        iPlace = 0;
        for (int i = 0; i < sNames.length(); ++i) {
            final byte cTest = bBuffer[i];
            if (cTest == 126) {
                if (iStartOfLine != i) {
                    this.sNameList[iPlace++] = String.valueOf(String.valueOf(bColor)).concat(String.valueOf(String.valueOf(new String(bBuffer, iStartOfLine, i - iStartOfLine))));
                }
                ++bColor;
                iLastBlank = -1;
                iStartOfLine = i + 1;
            }
            else if (cTest == 124) {
                if (iStartOfLine != i) {
                    this.sNameList[iPlace++] = String.valueOf(String.valueOf(bColor)).concat(String.valueOf(String.valueOf(new String(bBuffer, iStartOfLine, i - iStartOfLine))));
                }
                this.sNameList[iPlace++] = String.valueOf(String.valueOf(bColor)).concat(" ");
                bColor = 1;
                iLastBlank = -1;
                iStartOfLine = i + 1;
            }
            else if (i - iStartOfLine > this.iMaxCharsInLine) {
                if (iLastBlank == -1) {
                    this.sNameList[iPlace++] = String.valueOf(String.valueOf(bColor)).concat(String.valueOf(String.valueOf(new String(bBuffer, iStartOfLine, i - iStartOfLine))));
                    iStartOfLine = i + 1;
                    iLastBlank = -1;
                }
                else {
                    this.sNameList[iPlace++] = String.valueOf(String.valueOf(bColor)).concat(String.valueOf(String.valueOf(new String(bBuffer, iStartOfLine, iLastBlank - iStartOfLine))));
                    iStartOfLine = iLastBlank + 1;
                    iLastBlank = -1;
                }
            }
            else if (cTest == 32) {
                iLastBlank = i;
            }
        }
        this.iScrollCount = iPlace;
        this.add(this.myScrollCanvas = new ScrollCanvas2(null, this));
        this.convertS2I(this.sInput = this.getParameter("iAppletSize"), "200,400", this.iInput);
        this.myScrollCanvas.setBounds(0, 0, this.iInput[0], this.iInput[1]);
    }
    
    void convertS2I(String sInput, final String sDefault, final int[] iInput) {
        if (sInput == null) {
            sInput = sDefault;
        }
        int iStart = 0;
        int iEnd = 0;
        int iPlace = 0;
        while (true) {
            iEnd = sInput.indexOf(",", iStart);
            if (iEnd == -1) {
                break;
            }
            iInput[iPlace++] = Integer.parseInt(sInput.substring(iStart, iEnd));
            iStart = iEnd + 1;
        }
        iEnd = sInput.length();
        iInput[iPlace++] = Integer.parseInt(sInput.substring(iStart, iEnd));
    }
    
    public boolean imageUpdate(final Image im, final int flags, final int x, final int y) {
        if (im == this.skinImage) {
            if ((flags & 0x20) != 0x0) {
                this.myScrollCanvas.repaint();
            }
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 501) {
            final int x = e.x;
            final int y = e.y;
            if (x < this.iDelta + this.iXSlower && x > this.iXSlower - this.iDelta && y < this.iDelta + this.iYSlower && y > this.iYSlower - this.iDelta) {
                this.iScrollSpeed += 20;
                if (this.iScrollSpeed > 1000) {
                    this.iScrollSpeed = 1000;
                }
                return true;
            }
            if (x < this.iDelta + this.iXFaster && x > this.iXFaster - this.iDelta && y < this.iDelta + this.iYFaster && y > this.iYFaster - this.iDelta) {
                this.iScrollSpeed -= 20;
                if (this.iScrollSpeed < 10) {
                    this.iScrollSpeed = 10;
                }
                return true;
            }
            if (x < this.iDelta + this.iXUp && x > this.iXUp - this.iDelta && y < this.iDelta + this.iYUp && y > this.iYUp - this.iDelta) {
                this.iScrollDirection = 1;
            }
            else if (x < this.iDelta + this.iXDown && x > this.iXDown - this.iDelta && y < this.iDelta + this.iYDown && y > this.iYDown - this.iDelta) {
                this.iScrollDirection = -1;
            }
            else if (x < this.iDelta + this.iXStart && x > this.iXStart - this.iDelta && y < this.iDelta + this.iYStart && y > this.iYStart - this.iDelta) {
                this.bStop = false;
            }
            else if (x < this.iDelta + this.iXStop && x > this.iXStop - this.iDelta && y < this.iDelta + this.iYStop && y > this.iYStop - this.iDelta) {
                this.bStop = true;
            }
        }
        return true;
    }
    
    public void start() {
        this.iScrollPlace = 0;
        if (this.checkNumbers == null) {
            this.checkNumbers = new Thread(this);
        }
        this.checkNumbers.start();
    }
    
    public void stop() {
        this.checkNumbers.stop();
        this.checkNumbers = null;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.iScrollSpeed);
            }
            catch (InterruptedException ex) {}
            if (this.bStop) {
                continue;
            }
            this.iFine += 4;
            if (this.iFine >= this.iFontSize) {
                this.iFine = 0;
                this.iScrollPlace += this.iScrollDirection;
                if (this.iScrollPlace < 0) {
                    this.iScrollPlace = this.iScrollCount - 1;
                }
                if (this.iScrollPlace >= this.iScrollCount) {
                    this.iScrollPlace = 0;
                }
            }
            this.myScrollCanvas.paintIt();
        }
    }
    
    class ScrollCanvas2 extends Canvas
    {
        Color oStatusColor;
        Image imageBG;
        boolean bDrawBG;
        int xWidth;
        int yHeight;
        Color textColor;
        ScrollDisp2 myScrollDisp;
        FontMetrics fm;
        Font boldFont;
        int CHARHEIGHT;
        
        public ScrollCanvas2(final Frame frame, final ScrollDisp2 oScrollDisp) {
            this.oStatusColor = Color.black;
            this.imageBG = null;
            this.bDrawBG = true;
            this.xWidth = 50;
            this.yHeight = 50;
            this.textColor = Color.white;
            this.boldFont = null;
            this.CHARHEIGHT = 0;
            this.myScrollDisp = oScrollDisp;
            this.boldFont = new Font("Verdana", 1, ScrollDisp2.this.iFontSize);
            this.fm = this.getFontMetrics(this.boldFont);
            this.CHARHEIGHT = this.fm.getAscent() - 4;
        }
        
        public void repaint() {
            final Graphics g = this.getGraphics();
            if (g == null) {
                return;
            }
            g.drawImage(ScrollDisp2.this.skinImage, 0, 0, this);
            this.paint(g);
            g.dispose();
        }
        
        public void paintIt() {
            final Graphics g = this.getGraphics();
            this.bDrawBG = false;
            this.paint(g);
            g.dispose();
        }
        
        public void paint(final Graphics gMain) {
            if (this.bDrawBG) {
                gMain.drawImage(ScrollDisp2.this.skinImage, 0, 0, this);
            }
            this.bDrawBG = true;
            if (this.imageBG == null) {
                this.imageBG = this.createImage(ScrollDisp2.this.iScrollW, ScrollDisp2.this.iScrollH);
                return;
            }
            final Graphics g = this.imageBG.getGraphics();
            g.setFont(this.boldFont);
            g.setColor(this.myScrollDisp.colorScrollBackground);
            g.fillRect(0, 0, this.xWidth, this.yHeight);
            final FontMetrics fm = g.getFontMetrics();
            int iNext = this.myScrollDisp.iScrollPlace;
            for (int i = 0; i < this.myScrollDisp.iNumberOfLinesInList; ++i) {
                final String sOut = ScrollDisp2.this.sNameList[iNext];
                final char cTest = sOut.charAt(0);
                if (cTest == '1') {
                    g.setColor(this.myScrollDisp.colorScroll1);
                }
                else if (cTest == '2') {
                    g.setColor(this.myScrollDisp.colorScroll2);
                }
                else if (cTest == '3') {
                    g.setColor(this.myScrollDisp.colorScroll3);
                }
                else if (cTest == '4') {
                    g.setColor(this.myScrollDisp.colorScroll4);
                }
                else {
                    g.setColor(this.myScrollDisp.colorScroll5);
                }
                int iOffset = 0;
                if (ScrollDisp2.this.sCenter.startsWith("Y") || ScrollDisp2.this.sCenter.startsWith("y")) {
                    iOffset = ScrollDisp2.this.iScrollW / 2 - fm.stringWidth(sOut.substring(1)) / 2;
                }
                if (this.myScrollDisp.iScrollDirection == 1) {
                    g.drawString(sOut.substring(1), iOffset, i * this.myScrollDisp.iFontSize - this.myScrollDisp.iFine);
                }
                else {
                    g.drawString(sOut.substring(1), iOffset, i * this.myScrollDisp.iFontSize + this.myScrollDisp.iFine);
                }
                if (++iNext < 0) {
                    iNext = this.myScrollDisp.iScrollCount - 1;
                }
                if (iNext >= this.myScrollDisp.iScrollCount) {
                    iNext = 0;
                }
            }
            gMain.drawImage(this.imageBG, ScrollDisp2.this.iScrollX, ScrollDisp2.this.iScrollY, this);
            g.dispose();
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(150, 24);
        }
        
        public synchronized void setBounds(final int x, final int y, final int w, final int h) {
            super.setBounds(x, y, w, h);
            this.xWidth = w;
            this.yHeight = h;
            this.repaint();
        }
    }
}
