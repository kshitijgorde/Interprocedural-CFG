import java.util.StringTokenizer;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class jp1
{
    private Applet app;
    private Graphics gc;
    private int threadSleep;
    public int mRC;
    private int mtSize;
    private int mtI;
    private short[] mtForward;
    private short[] mtBackward;
    private short[] mtSceneLink;
    private byte[] mtType;
    private byte[] mtSubType;
    private short[] mtGroupLink;
    private short[] mtLeft;
    private short[] mtTop;
    private short[] mtWidth;
    private short[] mtHeight;
    private short[] mtImagesFileNum;
    private int[] mtMaskColor;
    private short[] mtTimeWait;
    private byte[] mtZ;
    private byte[] mtOp1;
    private byte[] mtOp2;
    private byte[] mtOp3;
    private byte[] mtOpA;
    private byte[] mtOpB;
    private byte[] mtOpC;
    private byte[] mtOpD;
    private short[] mtBptr;
    private short[] mtPriority;
    private int mwWidth;
    private int mwHeight;
    private Image bwWindow;
    private int mwOffset;
    private int bwTop;
    private int bwLeft;
    private int bwWidth;
    private int bwHeight;
    private int[] bwbWindow;
    private short[] bwbO1;
    private short[] bwbO2;
    private short[] bwbO3;
    private short[] bwbO4;
    private int bwOcolor;
    private int bwIcolor;
    private int[][] biImages;
    private int biCnt;
    private int[] biMptr;
    private int[] biFSfileNum;
    private int[] biEffectColor1;
    private int[] biEffectColor2;
    private int[] biEffectColor3;
    private int[] bwbImage;
    private int bwX;
    private int bwY;
    private int bwW;
    private int bwH;
    private int xSkip;
    private int xEnd;
    private int ySkip;
    private int yEnd;
    private char nvDirection;
    private boolean nvTakeSideLink;
    private int nvRC;
    final int wpArray = 200;
    private int[] wpProps;
    private int[] wpPropsCnt;
    private int[] wpPropsScene;
    final int dtArray = 100;
    private int[] dtMtI;
    private char[] dtDirection;
    private boolean[] dtStartup;
    private boolean[] dtEnabled;
    private int[] dtStartMtI;
    private int[] dtLoopCount;
    private boolean[] dtLoop1Active;
    private int[] dtWait;
    private int[] dtLastBWprop;
    private int[] dtParentDtI;
    private int dtCnt;
    private int dtI;
    private int dtSceneLastProp;
    private boolean dtExit;
    private int currentAct;
    private int frActivateCnt;
    private int frFrameCnt;
    private long frActivateTime;
    private int mouseGone;
    private int mouseOver;
    private boolean showEmsg;
    private boolean showCheckDigit;
    private long imageLoadTime;
    private long checkDigitCalcTime;
    private int checkDigit;
    private int mCheckDigit;
    private int fsChainStart;
    private int jarSize;
    private int urlNum;
    private int movieBGcolor;
    private boolean actHTMLimage;
    private int dGrey;
    private int lGrey;
    private int[] ssDtI;
    private int ssCnt;
    private int ssLockDtI;
    
    jp1() {
        this.mtSize = 0;
        this.biCnt = 0;
    }
    
    void moviePlay() {
        int n = 1;
        this.mRC = 0;
        ++this.frActivateCnt;
        while (true) {
            this.dtExit = true;
            this.dtI = 0;
            while (this.dtI < this.dtCnt) {
                if (this.dtWait[this.dtI] > 0 && n != 0) {
                    this.dtWait[this.dtI] -= this.threadSleep;
                }
                Label_1144: {
                    if (this.dtEnabled[this.dtI] && this.dtWait[this.dtI] < 1) {
                        this.dtWait[this.dtI] = 0;
                        if ((this.mtOpA[this.dtMtI[this.dtI]] & 0x10) == 0x10) {
                            this.eventManager(6, this.dtMtI[this.dtI]);
                            if (!this.dtEnabled[this.dtI] || this.dtStartMtI[this.dtI] == -1) {
                                this.dtExit = false;
                                break Label_1144;
                            }
                        }
                        this.nvDirection = this.dtDirection[this.dtI];
                        this.mtI = this.dtMtI[this.dtI];
                        this.nvTakeSideLink = this.dtStartup[this.dtI];
                        this.navigate();
                        if (this.mRC > 0) {
                            return;
                        }
                        this.dtMtI[this.dtI] = this.mtI;
                        this.dtStartup[this.dtI] = false;
                        if (this.nvRC > 0) {
                            if (this.nvRC == 1) {
                                this.sceneEnd(this.dtI);
                                break Label_1144;
                            }
                            if (this.nvRC == 2) {
                                this.dtMtI[this.dtI] = this.dtStartMtI[this.dtI];
                                if ((this.mtOp1[this.dtMtI[this.dtI]] & 0x8) == 0x8) {
                                    this.dtExit = false;
                                    this.dtStartup[this.dtI] = true;
                                    break Label_1144;
                                }
                                if (this.dtLoopCount[this.dtI] > 0) {
                                    final int[] dtLoopCount = this.dtLoopCount;
                                    final int dtI = this.dtI;
                                    --dtLoopCount[dtI];
                                    if (this.dtLoopCount[this.dtI] > 0) {
                                        this.dtExit = false;
                                        this.dtStartup[this.dtI] = true;
                                        break Label_1144;
                                    }
                                }
                                this.sceneEnd(this.dtI);
                                break Label_1144;
                            }
                            else if (this.nvRC == 3 && this.mtTimeWait[this.dtMtI[this.dtI]] != -1) {
                                this.sceneEnd(this.dtI);
                                break Label_1144;
                            }
                        }
                        if (this.mtType[this.dtMtI[this.dtI]] != 4 || this.mtSubType[this.dtMtI[this.dtI]] <= 0) {
                            if ((this.mtOp1[this.dtMtI[this.dtI]] & 0x80) != 0x80) {
                                if (this.dtLastBWprop[this.dtI] > 0 && (this.mtOp1[this.dtLastBWprop[this.dtI]] & 0x20) == 0x20 && this.dtDirection[this.dtI] == 'F') {
                                    this.imageMove(this.dtLastBWprop[this.dtI], this.dtMtI[this.dtI], this.dtStartMtI[this.dtI]);
                                }
                                else if (this.dtLastBWprop[this.dtI] > 0 && this.dtDirection[this.dtI] == 'B' && (this.mtOp1[this.dtLastBWprop[this.dtI]] & 0x10) != 0x10) {
                                    this.imageMove(this.dtLastBWprop[this.dtI], this.dtMtI[this.dtI], this.dtStartMtI[this.dtI]);
                                }
                                else {
                                    this.mtI = this.dtMtI[this.dtI];
                                    this.imageManager('A', this.dtStartMtI[this.dtI]);
                                }
                                this.dtLastBWprop[this.dtI] = this.dtMtI[this.dtI];
                            }
                            else if ((this.mtOp1[this.dtMtI[this.dtI]] & 0x1) == 0x1) {
                                this.imageManager('B', 0);
                            }
                            this.dtWait[this.dtI] = this.mtTimeWait[this.dtMtI[this.dtI]];
                            if (this.dtWait[this.dtI] == 0) {
                                this.dtExit = false;
                            }
                            else if (this.dtWait[this.dtI] == -1) {
                                this.ssDtI[this.ssCnt] = this.dtI;
                                this.ssLockDtI = -1;
                                ++this.ssCnt;
                                this.dtEnabled[this.dtI] = false;
                            }
                        }
                        else if (this.mtType[this.dtMtI[this.dtI]] == 4 && this.mtSubType[this.dtMtI[this.dtI]] == 3) {
                            if ((this.mtOp1[this.dtMtI[this.dtI]] & 0x80) != 0x80) {
                                if ((this.mtOpA[this.dtMtI[this.dtI]] & 0x8) == 0x8) {
                                    this.sceneStart(this.dtMtI[this.dtI], this.dtMtI[this.dtI], this.dtI);
                                    this.dtEnabled[this.dtI] = false;
                                }
                                else {
                                    this.sceneStart(this.dtMtI[this.dtI], this.dtMtI[this.dtI], -1);
                                }
                                this.dtExit = false;
                            }
                        }
                        else {
                            if (this.mtType[this.dtMtI[this.dtI]] != 4 || this.mtSubType[this.dtMtI[this.dtI]] != 2) {
                                if (this.showEmsg) {
                                    this.app.showStatus("e20:  mtI=" + this.dtMtI[this.dtI] + " tp=" + this.mtType[this.dtMtI[this.dtI]] + " stp=" + this.mtSubType[this.dtMtI[this.dtI]]);
                                }
                                this.mRC = 20;
                                return;
                            }
                            this.actNew(this.dtMtI[this.dtI]);
                            this.dtExit = false;
                        }
                    }
                }
                ++this.dtI;
            }
            if (this.dtExit) {
                return;
            }
            n = 0;
        }
    }
    
    void showClickURL(final int n, final boolean b) {
        for (short n2 = this.mtForward[n]; this.mtType[n2] == 4 && this.mtSubType[n2] > 5; n2 = this.mtForward[n2]) {
            if (this.mtSubType[n2] == 8 && (this.mtOpD[n2] & 0x4) == 0x4) {
                if (this.app.getParameter("u" + this.mtSceneLink[n2]) != null) {
                    this.urlNum = this.mtSceneLink[n2];
                    if (b) {
                        this.app.showStatus(this.app.getParameter("u" + this.mtSceneLink[n2]));
                    }
                }
                return;
            }
        }
    }
    
    void eventManager(final int n, final int n2) {
    Label_1327:
        for (short n3 = this.mtForward[n2]; this.mtType[n3] == 4 && this.mtSubType[n3] > 5; n3 = this.mtForward[n3]) {
            if (this.mtSubType[n3] == n) {
                if ((this.mtOpB[n3] & 0x10) == 0x10) {
                    this.sceneStart(this.mtSceneLink[n3], this.mtSceneLink[n3], -1);
                }
                else if ((this.mtOpB[n3] & 0x8) == 0x8) {
                    for (int i = 0; i < this.dtCnt; ++i) {
                        if (this.dtStartMtI[i] == this.mtSceneLink[n3]) {
                            this.sceneEnd(i);
                            break;
                        }
                    }
                }
                else if ((this.mtOpB[n3] & 0x4) == 0x4) {
                    this.actNew(this.mtSceneLink[n3]);
                }
                else if ((this.mtOpB[n3] & 0x2) == 0x2) {
                    short n4;
                    for (n4 = this.mtForward[this.currentAct]; this.mtType[n4] != 4 || this.mtSubType[n4] != 2; n4 = this.mtForward[n4]) {
                        if (this.mtType[n4] == 4 && this.mtSubType[n4] == 5) {
                            continue Label_1327;
                        }
                    }
                    this.actNew(n4);
                }
                else if ((this.mtOpB[n3] & 0x1) == 0x1) {
                    if (this.currentAct != 4) {
                        short n5;
                        for (n5 = this.mtBackward[this.currentAct]; this.mtType[n5] != 4 || this.mtSubType[n5] != 2; n5 = this.mtBackward[n5]) {
                            if (this.mtType[n5] == 4 && this.mtSubType[n5] == 4) {
                                n5 = this.mtSceneLink[n5];
                            }
                        }
                        this.actNew(n5);
                    }
                }
                else if ((this.mtOpC[n3] & 0x80) == 0x80) {
                    if (this.ssCnt > 0 && this.ssLockDtI == -1) {
                        final int n6 = this.ssCnt - 1;
                        this.ssLockDtI = this.ssDtI[n6];
                        this.dtEnabled[this.ssDtI[n6]] = true;
                        this.dtDirection[this.ssDtI[n6]] = 'F';
                        --this.ssCnt;
                    }
                }
                else if ((this.mtOpC[n3] & 0x40) == 0x40) {
                    if (this.ssCnt > 0 && this.ssLockDtI == -1) {
                        final int n7 = this.ssCnt - 1;
                        this.ssLockDtI = this.ssDtI[n7];
                        this.dtEnabled[this.ssDtI[n7]] = true;
                        this.dtDirection[this.ssDtI[n7]] = 'B';
                        --this.ssCnt;
                    }
                }
                else if ((this.mtOpC[n3] & 0x20) == 0x20) {
                    this.mRC = 9999;
                }
                else if ((this.mtOpC[n3] & 0x10) == 0x10) {
                    for (short mtI = this.mtSceneLink[n3]; mtI != 0; mtI = this.mtGroupLink[mtI]) {
                        if (this.mtType[mtI] == 4 && this.mtSubType[mtI] == 3) {
                            for (int j = 0; j < this.dtCnt; ++j) {
                                if (this.dtStartMtI[j] == mtI) {
                                    this.sceneEnd(j);
                                    break;
                                }
                            }
                        }
                        else {
                            this.mtI = mtI;
                            this.imageManager('D', 0);
                        }
                    }
                }
                else if ((this.mtOpC[n3] & 0x8) == 0x8) {
                    for (short mtI2 = this.mtSceneLink[n3]; mtI2 != 0; mtI2 = this.mtGroupLink[mtI2]) {
                        if (this.mtType[mtI2] == 4 && this.mtSubType[mtI2] == 3) {
                            this.sceneStart(mtI2, mtI2, -1);
                        }
                        else {
                            this.mtI = mtI2;
                            this.imageManager('A', this.mtSceneLink[mtI2]);
                        }
                    }
                }
                else if ((this.mtOpC[n3] & 0x4) == 0x4) {
                    this.mtI = this.mtSceneLink[n3];
                    this.imageManager('D', 0);
                }
                else if ((this.mtOpC[n3] & 0x2) == 0x2) {
                    this.mtI = n2;
                    this.imageManager('D', 0);
                }
                else if ((this.mtOpC[n3] & 0x1) == 0x1) {
                    this.nvDirection = 'F';
                    this.mtI = n2;
                    this.nvTakeSideLink = false;
                    this.navigate();
                    if (this.mRC > 0) {
                        return;
                    }
                    if (this.nvRC == 0) {
                        this.imageManager('D', 0);
                    }
                }
                else if ((this.mtOpD[n3] & 0x80) == 0x80) {
                    this.nvDirection = 'B';
                    this.mtI = n2;
                    this.nvTakeSideLink = false;
                    this.navigate();
                    if (this.mRC > 0) {
                        return;
                    }
                    if (this.nvRC == 0) {
                        this.imageManager('D', 0);
                    }
                }
                else if ((this.mtOpD[n3] & 0x40) == 0x40) {
                    this.mtI = this.mtSceneLink[n3];
                    this.imageManager('A', this.mtSceneLink[n2]);
                }
                else if ((this.mtOpD[n3] & 0x20) == 0x20) {
                    this.mtI = n2;
                    this.imageManager('A', this.mtSceneLink[n2]);
                }
                else if ((this.mtOpD[n3] & 0x10) == 0x10) {
                    this.nvDirection = 'F';
                    this.mtI = n2;
                    this.nvTakeSideLink = false;
                    this.navigate();
                    if (this.mRC > 0) {
                        return;
                    }
                    if (this.nvRC == 0) {
                        this.imageManager('A', this.mtSceneLink[this.mtI]);
                    }
                }
                else if ((this.mtOpD[n3] & 0x8) == 0x8) {
                    this.nvDirection = 'B';
                    this.mtI = n2;
                    this.nvTakeSideLink = false;
                    this.navigate();
                    if (this.mRC > 0) {
                        return;
                    }
                    if (this.nvRC == 0) {
                        this.imageManager('A', this.mtSceneLink[this.mtI]);
                    }
                }
                else if ((this.mtOpD[n3] & 0x4) == 0x4 && this.mtSceneLink[n3] > 0 && this.app.getParameter("u" + this.mtSceneLink[n3]) != null) {
                    String s = this.app.getParameter("u" + this.mtSceneLink[n3]);
                    boolean b = false;
                    if (s.charAt(0) == '+') {
                        s = s.substring(1);
                        b = true;
                    }
                    URL url = null;
                    try {
                        url = new URL(s);
                    }
                    catch (MalformedURLException ex) {
                        this.app.showStatus("e21 - Invalid URL:   " + s);
                    }
                    if (url != null) {
                        if (b) {
                            this.app.getAppletContext().showDocument(url, "New Window");
                        }
                        else {
                            this.app.getAppletContext().showDocument(url);
                        }
                    }
                }
            }
        }
    }
    
    void mouseManager(final int n, final int n2, final int n3) {
        final String s = "http://www.VisualCowboys.com";
        final int n4 = this.bwWidth * n2 + n;
        short mtI = this.bwbO1[n4];
        if (n3 == 4) {
            String string = "";
            this.urlNum = 0;
            if ((this.mtOpA[mtI] & 0x10) == 0x10) {
                this.showClickURL(mtI, false);
                if (this.urlNum > 0) {
                    string = "  u" + this.urlNum;
                }
            }
            final String string2 = "  " + this.mtWidth[mtI] + " X " + this.mtHeight[mtI];
            int currentAct;
            if (mtI == 0) {
                currentAct = this.currentAct;
            }
            else {
                currentAct = this.biMptr[this.mtBptr[mtI]];
            }
            final int n5 = this.bwbWindow[n4];
            final String string3 = "Image:  " + currentAct + string + string2 + "  RGB " + ((n5 & 0xFF0000) >> 16) + "," + ((n5 & 0xFF00) >> 8) + "," + (n5 & 0xFF) + "  #" + Integer.toHexString(n5).toUpperCase().substring(2);
            final int n6 = this.jarSize / 10;
            final String string4 = "Movie:  " + n6 + "." + (this.jarSize - n6 * 10) + " KB  " + this.bwWidth + " X " + this.bwHeight;
            String s2;
            if (this.checkDigit == this.mCheckDigit) {
                s2 = "";
            }
            else {
                s2 = "  (c) Copyright 2002 by VisualCowboys";
            }
            if (this.showEmsg) {
                this.app.showStatus(string4 + "   " + string3 + s2);
            }
            else {
                this.app.showStatus(string4 + s2);
            }
            return;
        }
        if (n3 == 2) {
            if (mtI == 0) {
                return;
            }
            if ((this.mtOpA[this.mouseOver] & 0x80) == 0x80) {
                this.mtI = mtI;
                this.imageManager('R', 0);
                this.imageManager('M', 0);
            }
            if (mtI == this.mtForward[5] && (this.mtOp3[this.mtForward[5]] & 0x8) == 0x8) {
                try {
                    this.app.getAppletContext().showDocument(new URL(s), "New Window");
                }
                catch (MalformedURLException ex) {}
            }
            else if ((this.mtOpA[mtI] & 0x10) == 0x10) {
                this.eventManager(8, mtI);
            }
        }
        else {
            if (n3 != 3) {
                if (n3 == 1) {
                    if (mtI == this.mouseOver) {
                        return;
                    }
                    if (this.mouseOver > 0) {
                        this.app.setCursor(Cursor.getPredefinedCursor(0));
                        if ((this.mtOpA[this.mouseOver] & 0x80) == 0x80) {
                            this.mtI = this.mouseOver;
                            if ((this.mtOp2[this.mtI] & 0x1) != 0x1) {
                                this.imageManager('R', 0);
                            }
                        }
                        if ((this.mtOpA[this.mouseOver] & 0x10) == 0x10 || (this.mouseOver == this.mtForward[5] && (this.mtOp3[this.mtForward[5]] & 0x8) == 0x8)) {
                            this.app.showStatus(" ");
                        }
                        if ((this.mtOpA[this.mouseOver] & 0x10) == 0x10) {
                            this.eventManager(9, this.mouseOver);
                            mtI = this.bwbO1[n4];
                            this.mouseOver = mtI;
                        }
                    }
                    if (mtI > 0) {
                        if ((this.mtOpA[mtI] & 0x20) == 0x20) {
                            this.app.setCursor(Cursor.getPredefinedCursor(12));
                        }
                        if ((this.mtOpA[mtI] & 0x80) == 0x80) {
                            this.mtI = mtI;
                            this.imageManager('M', 0);
                        }
                        if (mtI == this.mtForward[5] && (this.mtOp3[this.mtForward[5]] & 0x8) == 0x8) {
                            this.app.showStatus(s);
                        }
                        else if ((this.mtOpA[mtI] & 0x10) == 0x10) {
                            this.showClickURL(mtI, true);
                            this.eventManager(7, mtI);
                            mtI = this.bwbO1[n4];
                        }
                    }
                    if (mtI == 0 && this.mtTimeWait[0] != 0) {
                        for (short mtI2 = this.mtTimeWait[0]; mtI2 != 0; mtI2 = this.mtGroupLink[mtI2]) {
                            if (this.mtType[mtI2] == 4 && this.mtSubType[mtI2] == 3) {
                                for (int i = 0; i < this.dtCnt; ++i) {
                                    if (this.dtStartMtI[i] == mtI2) {
                                        this.sceneEnd(i);
                                        break;
                                    }
                                }
                            }
                            else {
                                this.mtI = mtI2;
                                this.imageManager('D', 0);
                            }
                        }
                    }
                    this.mouseOver = mtI;
                }
                return;
            }
            if (mtI == 0) {
                return;
            }
            if ((this.mtOpA[this.mouseOver] & 0x80) == 0x80) {
                this.mtI = mtI;
                this.imageManager('R', 0);
                this.imageManager('C', 0);
            }
        }
    }
    
    void movieInit(final Graphics gc, final Applet app, final int threadSleep) {
        this.gc = gc;
        this.app = app;
        this.threadSleep = threadSleep;
        if (this.app.getParameter("em") != null && this.app.getParameter("em").equals("y")) {
            this.showEmsg = true;
        }
        if (this.app.getParameter("cd") != null && this.app.getParameter("cd").equals("y")) {
            this.showCheckDigit = true;
        }
        this.wpProps = new int[200];
        this.wpPropsCnt = new int[200];
        this.wpPropsScene = new int[200];
        this.dtMtI = new int[100];
        this.dtDirection = new char[100];
        this.dtStartup = new boolean[100];
        this.dtEnabled = new boolean[100];
        this.dtStartMtI = new int[100];
        this.dtLoopCount = new int[100];
        this.dtLoop1Active = new boolean[100];
        this.dtWait = new int[100];
        this.dtLastBWprop = new int[100];
        this.dtParentDtI = new int[100];
        this.dGrey = -8421505;
        this.lGrey = -4014398;
        this.ssDtI = new int[100];
        this.ssLockDtI = -1;
        this.dtMtI[this.dtCnt] = 3;
        this.dtStartMtI[this.dtCnt] = 3;
        this.dtStartup[this.dtCnt] = false;
        this.dtDirection[this.dtCnt] = 'F';
        this.dtEnabled[this.dtCnt] = true;
        this.dtParentDtI[this.dtCnt] = -1;
        ++this.dtCnt;
    }
    
    void navigate() {
        if (this.mtType[this.mtI] == 4 && this.mtSubType[this.mtI] > 3) {
            if (this.showEmsg) {
                this.app.showStatus("e16 nav:  mtI=" + this.mtI + " tp=" + this.mtType[this.mtI] + " stp=" + this.mtSubType[this.mtI]);
            }
            this.mRC = 16;
            return;
        }
        this.nvRC = 0;
        final int mtI = this.mtI;
        do {
            if (this.nvDirection == 'F') {
                if (this.nvTakeSideLink) {
                    this.mtI = this.mtSceneLink[this.mtI];
                    this.nvTakeSideLink = false;
                }
                else {
                    this.mtI = this.mtForward[this.mtI];
                }
            }
            else if (this.nvDirection == 'B') {
                this.mtI = this.mtBackward[this.mtI];
            }
            else if (this.nvDirection != 'C') {
                if (this.showEmsg) {
                    this.app.showStatus("e17 nav:  mtI=" + this.mtI + " tp=" + this.mtType[this.mtI] + " stp=" + this.mtSubType[this.mtI] + " dir=" + this.nvDirection);
                }
                this.mRC = 17;
                return;
            }
            if (this.mtType[this.mtI] == 4 && this.mtSubType[this.mtI] > 3) {
                if (this.mtSubType[this.mtI] != 4 || this.nvDirection != 'B') {
                    continue;
                }
                final int mtI2 = this.mtI;
                this.mtI = this.mtSceneLink[this.mtI];
                this.nvRC = 1;
            }
            else if (this.mtType[this.mtI] != 2 && this.mtType[this.mtI] != 3 && this.mtType[this.mtI] != 4 && this.mtType[this.mtI] != 6) {
                if (this.mtType[this.mtI] != 7) {
                    if (this.showEmsg) {
                        this.app.showStatus("e19 nav:  mtI=" + this.mtI + " tp=" + this.mtType[this.mtI] + " stp=" + this.mtSubType[this.mtI] + " dir=" + this.nvDirection);
                    }
                    this.mRC = 19;
                }
            }
            return;
        } while (this.mtSubType[this.mtI] != 5 || this.nvDirection != 'F');
        if (this.mtType[this.mtBackward[this.mtI]] == 4 && this.mtSubType[this.mtBackward[this.mtI]] == 4) {
            this.mtI = this.mtSceneLink[this.mtI];
            this.nvRC = 2;
            return;
        }
        final int mtI3 = this.mtI;
        this.mtI = mtI;
        if (mtI3 == 2) {
            this.nvRC = 3;
            return;
        }
        this.nvRC = 2;
    }
    
    void sceneStart(final int n, final int n2, final int n3) {
        int n4;
        for (n4 = 0; n4 < this.dtCnt && this.dtStartMtI[n4] != n; ++n4) {}
        if (n4 < this.dtCnt) {
            if (this.dtStartMtI[n4] != n2) {
                this.dtMtI[n4] = n2;
                this.dtEnabled[n4] = true;
                return;
            }
            this.sceneEnd(n4);
        }
        int n5;
        for (n5 = 0; n5 < this.dtCnt && this.dtStartMtI[n5] != -1; ++n5) {}
        int dtCnt;
        if (n5 < this.dtCnt) {
            dtCnt = n5;
        }
        else {
            dtCnt = this.dtCnt;
            ++this.dtCnt;
        }
        this.dtStartMtI[dtCnt] = n;
        this.dtMtI[dtCnt] = n2;
        if (n == 3 || n != n2) {
            this.dtStartup[dtCnt] = false;
        }
        else {
            this.dtStartup[dtCnt] = true;
        }
        this.dtDirection[dtCnt] = 'F';
        this.dtEnabled[dtCnt] = true;
        this.dtLoopCount[dtCnt] = this.mtImagesFileNum[n];
        this.dtLoop1Active[dtCnt] = true;
        this.dtWait[dtCnt] = 0;
        this.dtLastBWprop[dtCnt] = 0;
        if (this.dtSceneLastProp > 0) {
            this.dtLastBWprop[dtCnt] = this.dtSceneLastProp;
            this.dtSceneLastProp = 0;
        }
        this.dtParentDtI[dtCnt] = n3;
    }
    
    void sceneEnd(final int n) {
        final int n2 = this.dtStartMtI[n];
        if (this.dtLastBWprop[n] > 0) {
            this.mtI = this.dtLastBWprop[n];
            if (this.dtDirection[n] == 'B') {
                this.imageManager('D', 0);
            }
            else if ((this.mtOpA[n] & 0x40) == 0x40) {
                this.dtSceneLastProp = this.mtI;
            }
            else if ((this.mtOp1[this.dtLastBWprop[n]] & 0x20) == 0x20) {
                this.imageManager('D', 0);
            }
        }
        if ((this.mtOp1[n2] & 0x20) == 0x20) {
            this.scenePropsClear(n2);
        }
        if (n == this.ssLockDtI) {
            this.ssLockDtI = -1;
        }
        int n3;
        for (n3 = 0; n3 < this.ssCnt && this.ssDtI[n3] != n; ++n3) {}
        if (n3 < this.ssCnt) {
            for (int i = n3; i < this.ssCnt; ++i) {
                this.ssDtI[i] = this.ssDtI[i + 1];
            }
            --this.ssCnt;
        }
        for (int j = 0; j < this.dtCnt; ++j) {
            if (n == this.dtParentDtI[j]) {
                this.dtParentDtI[j] = -1;
                break;
            }
        }
        if (this.dtParentDtI[n] >= 0) {
            this.dtEnabled[this.dtParentDtI[n]] = true;
            this.dtExit = false;
        }
        this.dtEnabled[n] = false;
        this.dtStartMtI[n] = -1;
    }
    
    void scenePropsClear(final int n) {
        for (int i = 0; i <= 200; ++i) {
            if (this.wpProps[i] != -1) {
                if (this.wpProps[i] == 0) {
                    break;
                }
                if ((this.mtOp1[this.wpProps[i]] & 0x10) != 0x10 && this.wpPropsScene[i] == n) {
                    this.mtI = this.wpProps[i];
                    this.imageManager('D', 0);
                }
            }
        }
    }
    
    void actPropsClear() {
        for (int i = 0; i <= 200; ++i) {
            if (this.wpProps[i] != -1) {
                if (this.wpProps[i] == 0) {
                    break;
                }
                if ((this.mtOp1[this.wpPropsScene[i]] & 0x10) != 0x10 && (this.mtOp1[this.wpProps[i]] & 0x10) != 0x10) {
                    this.mtI = this.wpProps[i];
                    this.imageManager('X', 0);
                }
            }
        }
    }
    
    void actNew(final int currentAct) {
        this.currentAct = currentAct;
        this.ssCnt = 0;
        int n = -1;
        for (int i = 0; i < this.dtCnt; ++i) {
            if (this.dtStartMtI[i] > 0) {
                if (this.dtStartMtI[i] == this.mtSceneLink[currentAct]) {
                    n = i;
                }
                if ((this.mtOp1[this.dtStartMtI[i]] & 0x10) != 0x10) {
                    if (this.dtStartMtI[i] != this.mtSceneLink[currentAct]) {
                        this.sceneEnd(i);
                    }
                }
            }
        }
        if (n >= 0) {
            this.dtDirection[n] = 'F';
        }
        if (this.movieBGcolor != 0) {
            this.mtMaskColor[this.mtForward[currentAct]] = this.movieBGcolor;
        }
        final int parmRGB = this.parmRGB(currentAct + "bgcolor");
        if (parmRGB != 0) {
            this.mtMaskColor[this.mtForward[currentAct]] = parmRGB;
        }
        final int n2 = this.mtMaskColor[this.mtForward[currentAct]];
        for (int j = 1; j < this.biCnt; ++j) {
            if (this.biMptr[j] != -1) {
                final int n3 = this.biImages[j][0];
                if ((this.mtOp3[this.biMptr[j]] & 0x10) == 0x10 && n3 != n2) {
                    for (int k = 0; k < this.biImages[j].length; ++k) {
                        if (this.biImages[j][k] == n3) {
                            this.biImages[j][k] = n2;
                        }
                    }
                    if ((this.mtOp1[this.biMptr[j]] & 0x10) == 0x10) {
                        this.mtI = this.biMptr[j];
                        for (int l = 0; l <= 200; ++l) {
                            if (this.wpProps[l] == 0) {
                                break;
                            }
                            if (this.wpProps[l] == this.mtI) {
                                this.imageManager('I', this.mtSceneLink[this.mtI]);
                                break;
                            }
                        }
                    }
                }
            }
        }
        final int n4 = this.mtMaskColor[this.mtForward[currentAct]];
        final int length = this.biImages[0].length;
        for (int n5 = 0; n5 < length; ++n5) {
            this.biImages[0][n5] = n4;
        }
        for (int n6 = 0; n6 < length; ++n6) {
            if (this.bwbO1[n6] == 0) {
                this.bwbWindow[n6] = n4;
            }
        }
        this.actPropsClear();
        this.actHTMLimage = false;
        this.imageHTML(0, currentAct);
        this.mRC = 0;
        if (this.actHTMLimage && this.checkDigit == this.mCheckDigit) {
            this.imageManager('I', this.mtI = 0);
        }
        else if (this.mtBptr[this.mtForward[currentAct]] > 0) {
            this.mtI = this.mtForward[currentAct];
            this.imageManager('K', 0);
        }
        this.bwWindow = this.app.createImage(new MemoryImageSource(this.bwWidth, this.bwHeight, this.bwbWindow, 0, this.bwWidth));
        this.gc.drawImage(this.bwWindow, 0, 0, this.bwWidth, this.bwHeight, this.app);
        if (this.showCheckDigit) {
            this.app.showStatus(this.imageLoadTime + " " + this.checkDigit);
        }
        this.app.repaint();
        this.sceneStart(this.mtSceneLink[currentAct], currentAct, -1);
    }
    
    void imageMove(final int mtI, final int mtI2, final int n) {
        this.mtI = mtI;
        this.imageManager('X', 0);
        final int bwX = this.bwX;
        final int bwY = this.bwY;
        final int bwW = this.bwW;
        final int bwH = this.bwH;
        this.mtI = mtI2;
        this.imageManager('I', n);
        final int bwX2 = this.bwX;
        final int bwY2 = this.bwY;
        final int bwW2 = this.bwW;
        final int bwH2 = this.bwH;
        if (bwX != -1) {
            if (bwX2 == -1) {
                this.bwX = bwX;
                this.bwY = bwY;
                this.bwW = bwW;
                this.bwH = bwH;
            }
            else {
                if (bwX < bwX2) {
                    this.bwX = bwX;
                }
                if (bwY < bwY2) {
                    this.bwY = bwY;
                }
                if (bwX + bwW > bwX2 + bwW2) {
                    this.bwW = bwX + bwW - this.bwX;
                }
                else {
                    this.bwW = bwX2 + bwW2 - this.bwX;
                }
                if (bwY + bwH > bwY2 + bwH2) {
                    this.bwH = bwY + bwH - this.bwY;
                }
                else {
                    this.bwH = bwY2 + bwH2 - this.bwY;
                }
            }
        }
        if (bwX != -1 || bwX2 != -1) {
            int n2 = 0;
            for (int i = this.bwY; i < this.bwY + this.bwH; ++i) {
                for (int j = this.bwX; j < this.bwX + this.bwW; ++j) {
                    final int n3 = i * this.bwWidth + j;
                    this.bwbImage[n2] = this.bwbWindow[i * this.bwWidth + j];
                    ++n2;
                }
            }
            this.bwWindow = this.app.createImage(new MemoryImageSource(this.bwW, this.bwH, this.bwbImage, 0, this.bwW));
            this.gc.drawImage(this.bwWindow, this.bwX, this.bwY, this.bwW, this.bwH, this.app);
            this.app.repaint();
        }
    }
    
    void imageManager(final char c, final int n) {
        final short n2 = this.mtLeft[this.mtI];
        final short n3 = (short)(this.mtLeft[this.mtI] + this.mtWidth[this.mtI] - 1);
        final short n4 = this.mtTop[this.mtI];
        final short n5 = (short)(this.mtTop[this.mtI] + this.mtHeight[this.mtI] - 1);
        final int bwLeft = this.bwLeft;
        final int n6 = this.bwLeft + this.bwWidth - 1;
        final int bwTop = this.bwTop;
        final int n7 = this.bwTop + this.bwHeight - 1;
        this.xSkip = 0;
        this.xEnd = n3 - n2;
        this.ySkip = 0;
        this.yEnd = n5 - n4;
        if (n3 < bwLeft || n2 > n6 || n5 < bwTop || n4 > n7) {
            this.bwX = -1;
            return;
        }
        if (n2 <= bwLeft) {
            this.xSkip = bwLeft - n2;
        }
        if (n3 >= n6) {
            this.xEnd = n6 - n2;
        }
        if (n4 <= bwTop) {
            this.ySkip = bwTop - n4;
        }
        if (n5 >= n7) {
            this.yEnd = n7 - n4;
        }
        final short n8 = this.mtBptr[this.mtI];
        final short n9 = this.mtWidth[this.mtI];
        final short n10 = this.mtTop[this.mtI];
        final short n11 = this.mtLeft[this.mtI];
        final int mwOffset = this.mwOffset;
        final int n12 = this.mtMaskColor[this.mtI];
        final short n13 = this.mtPriority[this.mtI];
        final short shortValue = (short)(Object)new Integer(this.mtI);
        final byte b = this.mtSubType[this.mtI];
        int n14;
        if ((this.mtOp1[this.mtI] & 0x40) != 0x40) {
            n14 = 0;
        }
        else {
            n14 = this.biImages[n8][0];
        }
        this.bwX = n11 - mwOffset + this.xSkip;
        this.bwY = n10 - mwOffset + this.ySkip;
        this.bwW = this.xEnd - this.xSkip + 1;
        this.bwH = this.yEnd - this.ySkip + 1;
        if ((this.mtOp2[this.mtI] & 0x80) == 0x80) {
            return;
        }
        if (c == 'A' || c == 'I') {
            for (int i = 0; i <= 200; ++i) {
                if (this.wpProps[i] == 0) {
                    for (int j = 0; j <= 200; ++j) {
                        if (this.wpProps[j] < 1) {
                            this.wpProps[j] = this.mtI;
                            this.wpPropsScene[j] = n;
                            this.wpPropsCnt[j] = 1;
                            break;
                        }
                    }
                    break;
                }
                if (this.wpProps[i] == this.mtI) {
                    final int[] wpPropsCnt = this.wpPropsCnt;
                    final int n15 = i;
                    ++wpPropsCnt[n15];
                    break;
                }
            }
            for (int k = 0 + this.ySkip; k <= this.yEnd; ++k) {
                for (int l = 0 + this.xSkip; l <= this.xEnd; ++l) {
                    final int n16 = this.biImages[n8][k * n9 + l];
                    if (n16 != n14) {
                        final int n17 = (n10 - mwOffset + k) * this.bwWidth + n11 - mwOffset + l;
                        if (n13 >= this.mtPriority[this.bwbO1[n17]]) {
                            this.bwbO4[n17] = this.bwbO3[n17];
                            this.bwbO3[n17] = this.bwbO2[n17];
                            this.bwbO2[n17] = this.bwbO1[n17];
                            this.bwbO1[n17] = shortValue;
                            this.bwbWindow[n17] = n16;
                        }
                        else if (n13 >= this.mtPriority[this.bwbO2[n17]]) {
                            this.bwbO4[n17] = this.bwbO3[n17];
                            this.bwbO3[n17] = this.bwbO2[n17];
                            this.bwbO2[n17] = shortValue;
                        }
                        else if (n13 >= this.mtPriority[this.bwbO3[n17]]) {
                            this.bwbO4[n17] = this.bwbO3[n17];
                            this.bwbO3[n17] = shortValue;
                        }
                        else if (n13 >= this.mtPriority[this.bwbO4[n17]]) {
                            this.bwbO4[n17] = shortValue;
                        }
                    }
                }
            }
        }
        else if (c == 'D' || c == 'X') {
            int n18 = 0;
            for (int n19 = 0; n19 <= 200; ++n19) {
                if (this.wpProps[n19] == 0) {
                    return;
                }
                if (this.wpProps[n19] == this.mtI) {
                    n18 = this.wpPropsCnt[n19];
                    if (n18 > 4) {
                        n18 = 4;
                    }
                    this.wpPropsCnt[n19] = 0;
                    this.wpProps[n19] = -1;
                    break;
                }
            }
            for (int n20 = 1; n20 <= n18; ++n20) {
                for (int n21 = 0 + this.ySkip; n21 <= this.yEnd; ++n21) {
                    for (int n22 = 0 + this.xSkip; n22 <= this.xEnd; ++n22) {
                        if (this.biImages[n8][n21 * n9 + n22] != n14) {
                            final int n23 = (n10 - mwOffset + n21) * this.bwWidth + n11 - mwOffset + n22;
                            if (shortValue == this.bwbO1[n23]) {
                                this.bwbO1[n23] = this.bwbO2[n23];
                                this.bwbO2[n23] = this.bwbO3[n23];
                                this.bwbO3[n23] = this.bwbO4[n23];
                                this.bwbO4[n23] = 0;
                                final short n24 = this.bwbO1[n23];
                                if (n24 == 0) {
                                    this.bwbWindow[n23] = this.biImages[0][n23];
                                }
                                else {
                                    this.bwbWindow[n23] = this.biImages[this.mtBptr[n24]][(mwOffset + n23 / this.bwWidth - this.mtTop[n24]) * this.mtWidth[n24] + (mwOffset + n23 % this.bwWidth - this.mtLeft[n24])];
                                }
                            }
                            else if (shortValue == this.bwbO2[n23]) {
                                this.bwbO2[n23] = this.bwbO3[n23];
                                this.bwbO3[n23] = this.bwbO4[n23];
                                this.bwbO4[n23] = 0;
                            }
                            else if (shortValue == this.bwbO3[n23]) {
                                this.bwbO3[n23] = this.bwbO4[n23];
                                this.bwbO4[n23] = 0;
                            }
                            else if (shortValue == this.bwbO4[n23]) {
                                this.bwbO4[n23] = 0;
                            }
                        }
                    }
                }
            }
        }
        else if (c == 'M' || c == 'C') {
            for (int n25 = 0 + this.ySkip; n25 <= this.yEnd; ++n25) {
                for (int n26 = 0 + this.xSkip; n26 <= this.xEnd; ++n26) {
                    final int n27 = this.biImages[n8][n25 * n9 + n26];
                    final int n28 = (n10 - mwOffset + n25) * this.bwWidth + n11 - mwOffset + n26;
                    if (n27 != n14) {
                        if (this.bwbO1[n28] == shortValue) {
                            if (c == 'C') {
                                if ((this.mtOp2[this.mtI] & 0x40) != 0x40 && (this.mtOp2[this.mtI] & 0x20) != 0x20 && (n25 == 0 || n25 == this.yEnd || n26 == 0 || n26 == this.xEnd)) {
                                    this.bwbWindow[n28] = this.biEffectColor3[n8];
                                }
                            }
                            else if ((this.mtOp2[this.mtI] & 0x40) == 0x40) {
                                if (n25 == 0 || n25 == this.yEnd || n26 == 0 || n26 == this.xEnd) {
                                    this.bwbWindow[n28] = this.biEffectColor1[n8];
                                }
                            }
                            else if ((this.mtOp2[this.mtI] & 0x20) == 0x20) {
                                if (n25 > 0 && n25 < this.yEnd) {
                                    if (n25 / 2 * 2 == n25 && (n26 == 0 || n26 == this.xEnd)) {
                                        this.bwbWindow[n28] = this.biEffectColor1[n8];
                                    }
                                }
                                else if (n26 / 2 * 2 == n26) {
                                    this.bwbWindow[n28] = this.biEffectColor1[n8];
                                }
                            }
                            else if ((this.mtOp2[this.mtI] & 0x10) == 0x10) {
                                if (this.bwbWindow[n28] == this.biEffectColor1[n8]) {
                                    this.bwbWindow[n28] = this.biEffectColor2[n8];
                                }
                            }
                            else if ((this.mtOp2[this.mtI] & 0x8) == 0x8) {
                                if (n27 != this.biImages[n8][0]) {
                                    this.bwbWindow[n28] = this.biEffectColor1[n8];
                                }
                            }
                            else if ((this.mtOp2[this.mtI] & 0x2) == 0x2 || (this.mtOp2[this.mtI] & 0x1) == 0x1) {
                                if ((n25 == 0 && n26 >= 0) || (n26 == 0 & n25 >= 0)) {
                                    this.bwbWindow[n28] = this.biEffectColor2[n8];
                                }
                                if ((n25 > this.yEnd - 3 && n26 > this.yEnd - n25) || (n26 > this.xEnd - 3 && n25 > this.xEnd - n26)) {
                                    if ((this.yEnd - n25 == 2 && this.xEnd - n26 > 1) || (this.xEnd - n26 == 2 && this.yEnd - n25 > 1)) {
                                        this.bwbWindow[n28] = this.biEffectColor2[n8];
                                    }
                                    else {
                                        this.bwbWindow[n28] = this.biEffectColor1[n8];
                                    }
                                }
                            }
                        }
                    }
                    else if (c == 'C') {
                        if (n25 == 0 || n25 == this.yEnd || n26 == 0 || n26 == this.xEnd) {
                            this.bwbWindow[n28] = this.biEffectColor3[n8];
                        }
                    }
                    else if ((this.mtOp2[this.mtI] & 0x4) == 0x4 && this.bwbO1[n28] == 0) {
                        this.bwbWindow[n28] = this.biEffectColor1[n8];
                    }
                }
            }
        }
        else if (c == 'R') {
            for (int n29 = 0 + this.ySkip; n29 <= this.yEnd; ++n29) {
                for (int n30 = 0 + this.xSkip; n30 <= this.xEnd; ++n30) {
                    final int n31 = this.biImages[n8][n29 * n9 + n30];
                    final int n32 = (n10 - mwOffset + n29) * this.bwWidth + n11 - mwOffset + n30;
                    if (n31 != n14) {
                        if (this.bwbO1[n32] == shortValue) {
                            this.bwbWindow[n32] = n31;
                        }
                    }
                    else if (((this.mtOp2[this.mtI] & 0x4) == 0x4 || c == 'C') && this.bwbO1[n32] == 0) {
                        this.bwbWindow[n32] = this.biImages[0][n29 * n9 + n30];
                    }
                }
            }
        }
        else if (c == 'B' || c == 'K') {
            for (int n33 = 0 + this.ySkip; n33 <= this.yEnd; ++n33) {
                for (int n34 = 0 + this.xSkip; n34 <= this.xEnd; ++n34) {
                    final int n35 = this.biImages[n8][n33 * n9 + n34];
                    if (b == 10 || n35 != n14) {
                        final int n36 = (n10 - mwOffset + n33) * this.bwWidth + n11 - mwOffset + n34;
                        this.biImages[0][n36] = n35;
                        if (this.bwbO1[n36] == 0) {
                            this.bwbWindow[n36] = n35;
                        }
                    }
                }
            }
        }
        if ((c == 'A' || c == 'I') && (this.mtOp2[this.mtI] & 0x1) == 0x1) {
            this.imageManager('M', 0);
        }
        if (c == 'A' || c == 'D' || c == 'B' || c == 'M' || c == 'R' || c == 'C') {
            int n37 = 0;
            for (int bwY = this.bwY; bwY < this.bwY + this.bwH; ++bwY) {
                for (int bwX = this.bwX; bwX < this.bwX + this.bwW; ++bwX) {
                    final int n38 = bwY * this.bwWidth + bwX;
                    this.bwbImage[n37] = this.bwbWindow[bwY * this.bwWidth + bwX];
                    ++n37;
                }
            }
            this.bwWindow = this.app.createImage(new MemoryImageSource(this.bwW, this.bwH, this.bwbImage, 0, this.bwW));
            this.gc.drawImage(this.bwWindow, this.bwX, this.bwY, this.bwW, this.bwH, this.app);
            this.app.repaint();
        }
    }
    
    void loadFile() {
        this.mRC = 0;
        int n = 0;
        final int[] array = new int[100];
        int n2 = 0;
        try {
            final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("MX0.m11");
            int read;
            while ((read = resourceAsStream.read()) != -1) {
                ++n2;
                array[n2] = read;
                if (n2 == 8) {
                    n = array[8] * 256 + array[7];
                }
                if (n2 == 12) {
                    this.mtSize = array[12] * 256 + array[11];
                }
                if (n2 == 27) {
                    this.fsChainStart = array[27] * 256 + array[26];
                }
                if (n2 == 30) {
                    this.jarSize = array[30] * 256 + array[29];
                    break;
                }
            }
            resourceAsStream.close();
        }
        catch (IOException ex) {
            if (this.showEmsg) {
                this.app.showStatus("e1 I/O Error:  MX0.m11");
            }
            this.mRC = 1;
            return;
        }
        this.mtForward = new short[this.mtSize];
        this.mtBackward = new short[this.mtSize];
        this.mtSceneLink = new short[this.mtSize];
        this.mtType = new byte[this.mtSize];
        this.mtSubType = new byte[this.mtSize];
        this.mtGroupLink = new short[this.mtSize];
        this.mtLeft = new short[this.mtSize];
        this.mtTop = new short[this.mtSize];
        this.mtWidth = new short[this.mtSize];
        this.mtHeight = new short[this.mtSize];
        this.mtImagesFileNum = new short[this.mtSize];
        this.mtMaskColor = new int[this.mtSize];
        this.mtTimeWait = new short[this.mtSize];
        this.mtZ = new byte[this.mtSize];
        this.mtOp1 = new byte[this.mtSize];
        this.mtOp2 = new byte[this.mtSize];
        this.mtOp3 = new byte[this.mtSize];
        this.mtOpA = new byte[this.mtSize];
        this.mtOpB = new byte[this.mtSize];
        this.mtOpC = new byte[this.mtSize];
        this.mtOpD = new byte[this.mtSize];
        this.mtBptr = new short[this.mtSize];
        this.mtPriority = new short[this.mtSize];
        try {
            int n3 = 0;
            this.mtI = 0;
            final InputStream resourceAsStream2 = this.getClass().getClassLoader().getResourceAsStream("MX0.m11");
            int read2;
            while ((read2 = resourceAsStream2.read()) != -1) {
                ++n3;
                array[n3] = read2;
                if (n3 == 2) {
                    this.mtI = (short)(Object)new Integer(array[2] * 256 + array[1]);
                }
                if (this.mtI < 0 && n3 == 5) {
                    this.mtI *= -1;
                    this.mtForward[this.mtI] = (short)(Object)new Integer(this.mtForward[this.mtI - 1] + 1);
                    this.mtBackward[this.mtI] = (short)(Object)new Integer(this.mtBackward[this.mtI - 1] + 1);
                    this.mtSceneLink[this.mtI] = this.mtSceneLink[this.mtI - 1];
                    this.mtType[this.mtI] = this.mtType[this.mtI - 1];
                    this.mtSubType[this.mtI] = this.mtSubType[this.mtI - 1];
                    this.mtGroupLink[this.mtI] = this.mtGroupLink[this.mtI - 1];
                    this.mtLeft[this.mtI] = this.mtLeft[this.mtI - 1];
                    this.mtTop[this.mtI] = this.mtTop[this.mtI - 1];
                    this.mtWidth[this.mtI] = this.mtWidth[this.mtI - 1];
                    this.mtHeight[this.mtI] = this.mtHeight[this.mtI - 1];
                    this.mtImagesFileNum[this.mtI] = this.mtImagesFileNum[this.mtI - 1];
                    this.mtTimeWait[this.mtI] = this.mtTimeWait[this.mtI - 1];
                    this.mtZ[this.mtI] = this.mtZ[this.mtI - 1];
                    this.mtOp1[this.mtI] = this.mtOp1[this.mtI - 1];
                    this.mtOp2[this.mtI] = this.mtOp2[this.mtI - 1];
                    this.mtOp3[this.mtI] = this.mtOp3[this.mtI - 1];
                    this.mtOpA[this.mtI] = this.mtOpA[this.mtI - 1];
                    this.mtOpB[this.mtI] = this.mtOpB[this.mtI - 1];
                    this.mtOpC[this.mtI] = this.mtOpC[this.mtI - 1];
                    this.mtOpD[this.mtI] = this.mtOpD[this.mtI - 1];
                    short n4;
                    if ((array[3] & 0x80) == 0x80) {
                        array[3] &= 0x7F;
                        n4 = (short)(this.mtLeft[this.mtI] - (short)(Object)new Integer(array[3]));
                    }
                    else {
                        n4 = (short)(this.mtLeft[this.mtI] + (short)(Object)new Integer(array[3]));
                    }
                    this.mtLeft[this.mtI] = (short)(Object)new Integer(n4);
                    short n5;
                    if ((array[4] & 0x80) == 0x80) {
                        array[4] &= 0x7F;
                        n5 = (short)(this.mtTop[this.mtI] - (short)(Object)new Integer(array[4]));
                    }
                    else {
                        n5 = (short)(this.mtTop[this.mtI] + (short)(Object)new Integer(array[4]));
                    }
                    this.mtTop[this.mtI] = (short)(Object)new Integer(n5);
                    short n6;
                    if ((array[5] & 0x80) == 0x80) {
                        array[5] &= 0x7F;
                        n6 = (short)(this.mtImagesFileNum[this.mtI] - (short)(Object)new Integer(array[5]));
                    }
                    else {
                        n6 = (short)(this.mtImagesFileNum[this.mtI] + (short)(Object)new Integer(array[5]));
                    }
                    this.mtImagesFileNum[this.mtI] = (short)(Object)new Integer(n6);
                    n3 = 0;
                }
                else {
                    if (n3 != n) {
                        continue;
                    }
                    this.mtForward[this.mtI] = (short)(Object)new Integer(array[4] * 256 + array[3]);
                    this.mtBackward[this.mtI] = (short)(Object)new Integer(array[6] * 256 + array[5]);
                    this.mtSceneLink[this.mtI] = (short)(Object)new Integer(array[8] * 256 + array[7]);
                    this.mtType[this.mtI] = (byte)(Object)new Integer(array[9]);
                    this.mtSubType[this.mtI] = (byte)(Object)new Integer(array[10]);
                    this.mtGroupLink[this.mtI] = (short)(Object)new Integer(array[12] * 256 + array[11]);
                    this.mtLeft[this.mtI] = (short)(Object)new Integer(array[14] * 256 + array[13]);
                    this.mtTop[this.mtI] = (short)(Object)new Integer(array[16] * 256 + array[15]);
                    this.mtWidth[this.mtI] = (short)(Object)new Integer(array[18] * 256 + array[17]);
                    this.mtHeight[this.mtI] = (short)(Object)new Integer(array[20] * 256 + array[19]);
                    this.mtImagesFileNum[this.mtI] = (short)(Object)new Integer(array[22] * 256 + array[21]);
                    if (this.mtType[this.mtI] == 4) {
                        if (this.mtI == 1) {
                            this.bwOcolor = array[28] * 256 * 256 * 256 + array[25] * 256 * 256 + array[26] * 256 + array[27];
                            this.bwOcolor |= 0xFF000000;
                            this.bwIcolor = array[32] * 256 * 256 * 256 + array[29] * 256 * 256 + array[30] * 256 + array[31];
                            this.bwIcolor |= 0xFF000000;
                        }
                        else if (this.mtSubType[this.mtI] == 10) {
                            this.mtMaskColor[this.mtI] = array[28] * 256 * 256 * 256 + array[25] * 256 * 256 + array[26] * 256 + array[27];
                            this.mtMaskColor[this.mtI] |= 0xFF000000;
                            array[26] = (array[25] = 0);
                            array[28] = (array[27] = 0);
                        }
                    }
                    this.mtTimeWait[this.mtI] = (short)(Object)new Integer(array[24] * 256 + array[23]);
                    this.mtZ[this.mtI] = (byte)(Object)new Integer(array[25]);
                    this.mtOp1[this.mtI] = (byte)(Object)new Integer(array[26]);
                    this.mtOp2[this.mtI] = (byte)(Object)new Integer(array[27]);
                    this.mtOp3[this.mtI] = (byte)(Object)new Integer(array[28]);
                    this.mtOpA[this.mtI] = (byte)(Object)new Integer(array[29]);
                    this.mtOpB[this.mtI] = (byte)(Object)new Integer(array[30]);
                    this.mtOpC[this.mtI] = (byte)(Object)new Integer(array[31]);
                    this.mtOpD[this.mtI] = (byte)(Object)new Integer(array[32]);
                    n3 = 0;
                }
            }
            resourceAsStream2.close();
            this.mtZ[0] = 0;
            this.mtOp1[0] = 0;
            this.mtOp2[0] = 0;
            this.mtOp3[0] = 0;
            this.mtOpA[0] = 0;
            this.mtOpB[0] = 0;
            this.mtOpC[0] = 0;
            this.mtOpD[0] = 0;
        }
        catch (IOException ex2) {
            if (this.showEmsg) {
                this.app.showStatus("e2 I/O Error:  MX0.m11");
            }
            this.mRC = 2;
        }
    }
    
    void loadImages() {
        this.mRC = 0;
        int n = 0;
        this.mwWidth = this.mtWidth[0];
        this.mwHeight = this.mtHeight[0];
        this.bwTop = this.mtLeft[0];
        this.bwLeft = this.mtLeft[0];
        this.bwWidth = this.mtWidth[0] - this.mtLeft[0] * 2;
        this.bwHeight = this.mtHeight[0] - this.mtLeft[0] * 2;
        this.mwOffset = this.mtLeft[0];
        this.mCheckDigit = this.mtSize * 3 + this.mtTop[0] * 13;
        try {
            if (this.app.getParameter("l") != null) {
                this.mCheckDigit = Integer.parseInt(this.app.getParameter("l"));
            }
        }
        catch (Exception ex) {}
        this.mtTop[0] = this.mtLeft[0];
        this.mtWidth[0] = (short)(Object)new Integer(this.bwWidth);
        this.mtHeight[0] = (short)(Object)new Integer(this.bwHeight);
        this.bwbWindow = new int[this.bwWidth * this.bwHeight];
        this.bwbImage = new int[this.bwWidth * this.bwHeight];
        this.bwbO1 = new short[this.bwWidth * this.bwHeight];
        this.bwbO2 = new short[this.bwWidth * this.bwHeight];
        this.bwbO3 = new short[this.bwWidth * this.bwHeight];
        this.bwbO4 = new short[this.bwWidth * this.bwHeight];
        if (this.bwWidth != this.app.getSize().width || this.bwHeight != this.app.getSize().height) {
            if (this.showEmsg) {
                this.app.showStatus("e10 bWin: html=" + this.app.getSize().width + " X " + this.app.getSize().height + "  mv= " + this.bwWidth + " X " + this.bwHeight);
            }
            this.mRC = 10;
            return;
        }
        this.biCnt = this.mtImagesFileNum[0] + 1;
        for (int i = this.fsChainStart; i != 0; i = this.mtSceneLink[i]) {
            this.biCnt += this.mtZ[i];
            if ((this.mtOp3[i] & 0x80) == 0x80) {
                this.biCnt += this.mtZ[i];
            }
        }
        (this.biImages = new int[this.biCnt][])[0] = new int[this.bwWidth * this.bwHeight];
        this.biMptr = new int[this.biCnt];
        this.biEffectColor1 = new int[this.biCnt];
        this.biEffectColor2 = new int[this.biCnt];
        this.biEffectColor3 = new int[this.biCnt];
        this.biEffectColor1[0] = this.bwOcolor;
        this.biEffectColor2[0] = this.bwIcolor;
        this.biFSfileNum = new int[this.biCnt];
        int n2 = 1;
        for (int j = this.fsChainStart; j != 0; j = this.mtSceneLink[j]) {
            this.biFSfileNum[n2] = this.mtImagesFileNum[j];
            int n3 = this.mtZ[j];
            if ((this.mtOp3[j] & 0x80) == 0x80) {
                n3 *= 2;
            }
            for (short n4 = 0; n4 < n3 + 1; ++n4) {
                this.biFSfileNum[n2] = this.mtImagesFileNum[j] + n4;
                this.biMptr[n2] = -1;
                ++n2;
            }
        }
        this.mtI = 3;
        while (this.mtForward[this.mtI] != -1) {
            short n5 = 0;
            if (this.mtType[this.mtI] == 4 && this.mtSubType[this.mtI] == 10) {
                if (this.mtImagesFileNum[this.mtI] > 0) {
                    n5 = this.mtImagesFileNum[this.mtI];
                }
            }
            else if ((this.mtType[this.mtI] != 4 || this.mtSubType[this.mtI] <= 0) && (this.mtOp2[this.mtI] & 0x80) != 0x80) {
                n5 = this.mtImagesFileNum[this.mtI];
                if ((this.mtOp1[this.mtI] & 0x1) != 0x1) {
                    if ((this.mtOp3[this.mtI] & 0x8) == 0x8) {
                        this.mtPriority[this.mtI] = 99;
                    }
                    else if ((this.mtOpB[this.mtI] & 0x80) == 0x80) {
                        this.mtPriority[this.mtI] = (short)(Object)new Integer(30 - this.mtZ[this.mtI]);
                    }
                    else if ((this.mtOpB[this.mtI] & 0x40) == 0x40) {
                        this.mtPriority[this.mtI] = (short)(Object)new Integer(20 - this.mtZ[this.mtI]);
                    }
                }
            }
            if (n5 > 0) {
                int k;
                for (k = 1; k < n2; ++k) {
                    if (this.biMptr[k] == -1) {
                        if (n5 == this.biFSfileNum[k]) {
                            this.biMptr[k] = this.mtI;
                            break;
                        }
                    }
                    else if (n5 == this.mtImagesFileNum[this.biMptr[k]]) {
                        break;
                    }
                }
                if (k == n2) {
                    this.biMptr[n2] = this.mtI;
                    if (++n2 > this.biCnt) {
                        if (this.showEmsg) {
                            this.app.showStatus("e11 total images exceeded");
                        }
                        this.mRC = 11;
                        return;
                    }
                }
                this.mtBptr[this.mtI] = (short)(Object)new Integer(k);
            }
            this.mtI = this.mtForward[this.mtI];
        }
        if (n2 != this.biCnt) {
            if (this.showEmsg) {
                this.app.showStatus("e12 images missing: Expected=" + (this.biCnt - 1) + " Found=" + (n2 - 1));
            }
            this.mRC = 12;
            return;
        }
        this.imageLoadTime = System.currentTimeMillis();
        final MediaTracker mediaTracker = new MediaTracker(this.app);
        for (byte b = 1; b < this.biCnt; ++b) {
            if (this.biMptr[b] != -1 && (this.mtOp1[this.biMptr[b]] & 0x2) != 0x2) {
                this.biImages[b] = new int[this.mtWidth[this.biMptr[b]] * this.mtHeight[this.biMptr[b]]];
                final StringBuffer sb = new StringBuffer(4);
                if ((this.mtOpA[this.biMptr[b]] & 0x4) == 0x4) {
                    sb.append(".bmp");
                }
                else if ((this.mtOpA[this.biMptr[b]] & 0x2) == 0x2) {
                    sb.append(".gif");
                }
                else if ((this.mtOpA[this.biMptr[b]] & 0x1) == 0x1) {
                    sb.append(".jpg");
                }
                final Image image = this.app.getImage(this.app.getDocumentBase(), "MX" + this.mtImagesFileNum[this.biMptr[b]] + (Object)sb);
                try {
                    mediaTracker.addImage(image, 1);
                    mediaTracker.waitForID(1);
                }
                catch (InterruptedException ex2) {
                    if (this.showEmsg) {
                        this.app.showStatus("e24 MediaTracker problem");
                    }
                    this.mRC = 24;
                    return;
                }
                if (image.getWidth(this.app) != this.mtWidth[this.biMptr[b]] || image.getHeight(this.app) != this.mtHeight[this.biMptr[b]]) {
                    if (this.showEmsg) {
                        this.app.showStatus("e13 mtI=" + this.biMptr[b] + " img=" + image.getWidth(this.app) + " X " + image.getHeight(this.app) + " mv= " + this.mtWidth[this.biMptr[b]] + " X " + this.mtHeight[this.biMptr[b]]);
                    }
                    this.mRC = 13;
                    return;
                }
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, image.getWidth(this.app), image.getHeight(this.app), this.biImages[b], 0, image.getWidth(this.app));
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex3) {}
                if ((this.mtOp1[this.biMptr[b]] & 0x4) == 0x4) {
                    final byte b2 = this.mtZ[this.biMptr[b]];
                    final short n6 = (short)(this.mtWidth[this.biMptr[b]] / b2);
                    final short n7 = this.mtWidth[this.biMptr[b]];
                    final short n8 = this.mtHeight[this.biMptr[b]];
                    short n9 = 0;
                    byte b3;
                    for (b3 = (byte)(b + 1); b3 <= b + b2; ++b3) {
                        this.biImages[b3] = new int[n6 * n8];
                        for (short n10 = 0; n10 < n8; ++n10) {
                            for (short n11 = 0; n11 < n6; ++n11) {
                                this.biImages[b3][n10 * n6 + n11] = this.biImages[b][n10 * n7 + n11 + n9];
                            }
                        }
                        n9 += n6;
                    }
                    if ((this.mtOp3[this.biMptr[b]] & 0x80) == 0x80) {
                        short n12 = 0;
                        for (byte b4 = b3; b4 <= b + b2 * 2; ++b4) {
                            this.biImages[b4] = new int[n6 * n8];
                            for (short n13 = 0; n13 < n8; ++n13) {
                                for (short n14 = 0; n14 < n6; ++n14) {
                                    this.biImages[b4][n13 * n6 + (n6 - n14 - 1)] = this.biImages[b][n13 * n7 + n14 + n12];
                                }
                            }
                            n12 += n6;
                        }
                    }
                }
            }
            if (n == 0 && System.currentTimeMillis() - this.imageLoadTime > 1000L) {
                this.gc.drawString("Loading....", 5, 15);
                this.app.repaint();
                n = 1;
            }
        }
        this.imageLoadTime = System.currentTimeMillis() - this.imageLoadTime;
        this.checkDigitCalcTime = System.currentTimeMillis();
        int n15 = 0;
        for (int l = 1; l < this.biCnt; ++l) {
            int n16 = 0;
            for (int n17 = 0; n17 < this.biImages[l].length; n17 += 11) {
                n16 += (this.biImages[l][n17] & 0xFF);
            }
            n15 += n16;
        }
        this.checkDigit = (n15 & 0xFF);
        this.checkDigit = this.mtSize * 3 + this.checkDigit * 13;
        this.checkDigitCalcTime = System.currentTimeMillis() - this.checkDigitCalcTime;
        if (this.checkDigit == this.mCheckDigit && (this.mtOp3[this.mtForward[5]] & 0x8) == 0x8) {
            this.mtOp1[this.mtForward[5]] = (byte)(Object)new Integer(this.mtOp1[this.mtForward[5]] | 0x80);
        }
        final int parmRGB = this.parmRGB("1e1color");
        final int parmRGB2 = this.parmRGB("1e2color");
        final int parmRGB3 = this.parmRGB("1e3color");
        for (int n18 = 1; n18 < this.biCnt; ++n18) {
            if (this.biMptr[n18] != -1) {
                if ((this.mtOpA[this.biMptr[n18]] & 0x80) == 0x80) {
                    this.biEffectColor3[n18] = this.dGrey;
                    if (parmRGB3 != 0) {
                        this.biEffectColor3[n18] = parmRGB3;
                    }
                    if ((this.mtOp1[this.biMptr[n18]] & 0x2) == 0x2) {
                        this.biEffectColor1[n18] = this.dGrey;
                        this.biEffectColor2[n18] = this.lGrey;
                    }
                    else {
                        this.biEffectColor1[n18] = this.biImages[n18][1];
                        this.biEffectColor2[n18] = this.biImages[n18][2];
                        this.biImages[n18][1] = this.biImages[n18][0];
                        this.biImages[n18][2] = this.biImages[n18][0];
                    }
                    if ((this.mtOp2[this.biMptr[n18]] & 0x2) == 0x2 || (this.mtOp2[this.biMptr[n18]] & 0x1) == 0x1) {
                        if (parmRGB != 0) {
                            this.biEffectColor1[n18] = parmRGB;
                        }
                        if (parmRGB2 != 0) {
                            this.biEffectColor2[n18] = parmRGB2;
                        }
                    }
                    final int parmRGB4 = this.parmRGB(this.biMptr[n18] + "e1color");
                    if (parmRGB4 != 0) {
                        this.biEffectColor1[n18] = parmRGB4;
                    }
                    final int parmRGB5 = this.parmRGB(this.biMptr[n18] + "e2color");
                    if (parmRGB5 != 0) {
                        this.biEffectColor2[n18] = parmRGB5;
                    }
                    final int parmRGB6 = this.parmRGB(this.biMptr[n18] + "e3color");
                    if (parmRGB6 != 0) {
                        this.biEffectColor3[n18] = parmRGB6;
                    }
                }
                if ((this.mtOp3[this.biMptr[n18]] & 0x8) != 0x8 && this.checkDigit == this.mCheckDigit) {
                    this.imageHTML(n18, this.biMptr[n18]);
                    if (this.mRC > 0) {
                        return;
                    }
                }
                final int parmRGB7 = this.parmRGB(this.biMptr[n18] + "bgcolor");
                if (parmRGB7 != 0) {
                    final int n19 = this.biImages[n18][0];
                    this.mtOp3[this.biMptr[n18]] = (byte)(Object)new Integer(this.mtOp3[this.biMptr[n18]] & 0xEF);
                    if (n19 != parmRGB7) {
                        for (int n20 = 0; n20 < this.biImages[n18].length; ++n20) {
                            if (this.biImages[n18][n20] == n19) {
                                this.biImages[n18][n20] = parmRGB7;
                            }
                        }
                    }
                }
                if ((this.mtOp3[this.biMptr[n18]] & 0x8) != 0x8 && this.app.getParameter(this.biMptr[n18] + "remove") != null) {
                    this.mtOp1[this.biMptr[n18]] = (byte)(Object)new Integer(this.mtOp1[this.biMptr[n18]] | 0x80);
                }
            }
        }
        final int parmRGB8 = this.parmRGB("0e1color");
        if (parmRGB8 != 0) {
            this.biEffectColor1[0] = parmRGB8;
        }
        final int parmRGB9 = this.parmRGB("0e2color");
        if (parmRGB9 != 0) {
            this.biEffectColor2[0] = parmRGB9;
        }
        if ((this.mtOp3[3] & 0x40) == 0x40) {
            this.mtPriority[3] = 99;
            for (int n21 = 0; n21 < this.bwWidth; ++n21) {
                final int n22 = 0 * this.bwWidth + n21;
                this.bwbO1[n22] = 3;
                this.bwbWindow[n22] = this.biEffectColor1[0];
                final int n23 = (this.bwHeight - 1) * this.bwWidth + n21;
                this.bwbO1[n23] = 3;
                this.bwbWindow[n23] = this.biEffectColor1[0];
                if ((this.mtOp3[3] & 0x20) == 0x20) {
                    this.bwbO1[n22 + this.bwWidth] = 3;
                    this.bwbWindow[n22 + this.bwWidth] = this.biEffectColor2[0];
                    this.bwbO1[n23 - this.bwWidth] = 3;
                    this.bwbWindow[n23 - this.bwWidth] = this.biEffectColor2[0];
                }
            }
            for (int n24 = 1; n24 < this.bwHeight - 1; ++n24) {
                final int n25 = n24 * this.bwWidth;
                this.bwbO1[n25] = 3;
                this.bwbWindow[n25] = this.biEffectColor1[0];
                final int n26 = n24 * this.bwWidth + this.bwWidth - 1;
                this.bwbO1[n26] = 3;
                this.bwbWindow[n26] = this.biEffectColor1[0];
                if ((this.mtOp3[3] & 0x20) == 0x20) {
                    this.bwbO1[n25 + 1] = 3;
                    this.bwbWindow[n25 + 1] = this.biEffectColor2[0];
                    this.bwbO1[n26 - 1] = 3;
                    this.bwbWindow[n26 - 1] = this.biEffectColor2[0];
                }
            }
        }
        this.movieBGcolor = this.parmRGB("bgcolor");
    }
    
    int parmRGB(final String s) {
        int int1 = 0;
        if (this.app.getParameter(s) != null) {
            final String parameter = this.app.getParameter(s);
            if (parameter.charAt(0) == '#') {
                try {
                    int1 = Integer.parseInt(parameter.substring(1), 16);
                    int1 |= 0xFF000000;
                }
                catch (Exception ex) {}
            }
            else {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                try {
                    int1 = Integer.parseInt(stringTokenizer.nextToken()) * 256 * 256 + Integer.parseInt(stringTokenizer.nextToken()) * 256 + Integer.parseInt(stringTokenizer.nextToken());
                    int1 |= 0xFF000000;
                }
                catch (Exception ex2) {}
            }
        }
        return int1;
    }
    
    void imageHTML(final int n, final int n2) {
        final MediaTracker mediaTracker = new MediaTracker(this.app);
        if (this.app.getParameter(n2 + "image") != null) {
            final Image image = this.app.getImage(this.app.getDocumentBase(), this.app.getParameter(n2 + "image"));
            try {
                mediaTracker.addImage(image, 1);
                mediaTracker.waitForID(1);
            }
            catch (InterruptedException ex) {
                if (this.showEmsg) {
                    this.app.showStatus("e25 MediaTracker problem");
                }
                this.mRC = 25;
                return;
            }
            try {
                if (image.getWidth(this.app) != this.mtWidth[this.biMptr[n]] || image.getHeight(this.app) != this.mtHeight[this.biMptr[n]]) {
                    this.app.showStatus("e26 Image #" + n2 + " error");
                    this.mRC = 26;
                    return;
                }
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, image.getWidth(this.app), image.getHeight(this.app), this.biImages[n], 0, image.getWidth(this.app));
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex2) {}
            }
            catch (NullPointerException ex3) {
                this.app.showStatus("e27 Image #" + n2 + " error");
                this.mRC = 27;
                return;
            }
            if (n == 0) {
                this.actHTMLimage = true;
            }
        }
    }
}
