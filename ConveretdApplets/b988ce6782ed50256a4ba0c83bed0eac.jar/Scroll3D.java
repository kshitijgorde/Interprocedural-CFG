import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.io.DataInputStream;
import java.util.Vector;
import java.awt.Point;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Scroll3D extends Applet implements Runnable
{
    private final int PLAIN = 0;
    private final int OUTLINE = 7;
    private final int OUTLINEFONT = 1;
    private final int SMOOTH = 8;
    private final int THREED = 16;
    private final int TRANSPARENT = 256;
    private final int TRANSPARENT_TRIM = 768;
    private final int PREIMG_NORMAL = 0;
    private boolean pswdGood;
    private String strLock;
    private String infoL1;
    private String infoL2;
    private String frozenL;
    private long tol;
    private boolean myPause;
    private long lastUT;
    private long initUT;
    private long runCalled;
    private int runReady;
    private boolean restAwhile;
    private long pauseAwhile;
    private int picPosition;
    private long totalFrames;
    private long pauseLimit;
    private long picRequest;
    private Applet applets;
    private boolean firstStart;
    private final int VERTICAL = 1;
    private boolean verticalText;
    private int preimgStatus;
    private URL userUrl;
    private String target;
    private boolean ratDown;
    private Point ptTmp;
    private Thread clockThread;
    private int cntstr;
    private String sssb;
    private int nText;
    private Vector sTextBuf;
    private int[] tbufInfo;
    private String vTextFile;
    private DataInputStream vTextBr;
    private int textLineN;
    private Image[] offImage;
    private Image bkImg;
    private String bkFile;
    private int bkPicDraw;
    private int bkImgX;
    private int bkImgY;
    private int[] picInfo;
    private Color dark;
    private Color lite;
    private Color sDark;
    private int[] liteColor;
    private int[] darkColor;
    private int leadint;
    private int delay;
    int advance;
    private int pause;
    private int pAtl;
    private int loopCnt;
    private int pauseAt;
    private int aw;
    private int ah;
    private int realAw;
    private int realAh;
    private int iw;
    private int ih;
    private Font mFont;
    private int topMargin;
    private int shadow;
    private int shadowOffset;
    private int fontGap;
    private char[] workChar;
    private Color shadowColor;
    private int frontShadow;
    private int fShadowOffset;
    private Color fShadowColor;
    private boolean specialFont;
    private int f3D1;
    private int f3D2;
    private int fontEffect;
    private int feHeight;
    private int fMaxD;
    private int preCall;
    private int lm;
    private int rm;
    private int tm;
    private int bm;
    private int bian;
    private int rollGap;
    private Color[] frameColor;
    private int frameN;
    private int backPic;
    private int offID;
    private int ti;
    private boolean first;
    private boolean firstRun;
    private int maxc;
    private MediaTracker tracker;
    private boolean firstDraw;
    private int[] bmpInfo;
    private int[] bitMap;
    private int[] bitMap1;
    private int frontPic;
    private Image frontImg;
    private String fgFile;
    private DataInputStream bmpIsF;
    private DataInputStream bmpIs;
    private DataInputStream vtIs;
    private byte[] bitMapb;
    private boolean badThing;
    private int leftmost;
    private int lfLast;
    private int leftEdge;
    private int lastHead;
    private int flagPos;
    private int startAt;
    private boolean nextPause;
    private int overI;
    private int animator;
    private boolean frozen;
    
    public void init() {
        this.aw = this.size().width;
        this.ah = this.size().height;
        this.applets = this;
        this.ptTmp = new Point(0, 0);
        final String trimParam = this.getTrimParam("scrollDirection");
        this.animator = 0;
        if (this.emptyString(trimParam) > 0 && trimParam.equalsIgnoreCase("vertical")) {
            this.animator = 1;
        }
        this.textLineN = 50;
        final String trimParam2 = this.getTrimParam("backImage");
        if (this.emptyString(trimParam2) <= 0) {
            this.backPic = 0;
        }
        else {
            this.backPic = (this.isBMP(trimParam2) ? 2 : 1);
            this.bkFile = trimParam2;
            final String trimParam3 = this.getTrimParam("drawBkImage");
            if (this.emptyString(trimParam3) > 0) {
                if (trimParam3.toLowerCase().equals("as is") || trimParam3.startsWith("as is ")) {
                    this.bkPicDraw = 0;
                }
                int n = (this.bkPicDraw == 2) ? 1 : 3;
                final String paramString = this.paramString(trimParam3, n);
                final Point s2Int = this.s2Int(paramString);
                if (s2Int.y >= 0) {
                    this.bkImgX = s2Int.x;
                    ++n;
                    final Point s2Int2 = this.s2Int(this.paramString(trimParam3, n));
                    if (s2Int2.y >= 0) {
                        this.bkImgY = s2Int2.x;
                    }
                    else {
                        this.badThing = true;
                    }
                }
                else if (paramString != null) {
                    this.badThing = true;
                }
            }
        }
        final String trimParam4 = this.getTrimParam("frontImage");
        if (this.emptyString(trimParam4) > 0) {
            this.frontPic = (this.isBMP(trimParam4) ? 3 : 1);
            this.fgFile = trimParam4;
            final String trimParam5 = this.getTrimParam("frontImageIs");
            if (this.emptyString(trimParam5) > 0 && trimParam5.equalsIgnoreCase("text")) {
                ++this.frontPic;
            }
            if (this.backPic > 0 && !this.fgFile.equals(this.bkFile)) {
                this.frontPic = -this.frontPic;
            }
        }
        if (this.frontPic == 0) {
            this.vTextFile = this.getTrimParam("textFile");
        }
        if (this.backPic > 0) {
            this.bkImg = null;
            if (this.backPic == 1) {
                this.bkImg = this.getImage(this.getDocumentBase(), this.bkFile);
                (this.tracker = new MediaTracker(this)).addImage(this.bkImg, 0);
            }
            else {
                if ((this.bmpIs = this.getBytesFile(this.bkFile)) == null) {
                    System.err.println("Open backImg(bmp) error");
                    this.badThing = true;
                }
                this.bmpIsF = this.bmpIs;
            }
        }
        if (this.frontPic != 0) {
            int equals = 0;
            if (this.backPic > 0) {
                equals = (this.fgFile.equals(this.bkFile) ? 1 : 0);
            }
            if (equals == 0) {
                if (this.frontPic > -3 && this.frontPic < 3) {
                    this.frontImg = this.getImage(this.getDocumentBase(), this.fgFile);
                    if (this.backPic != 1) {
                        this.tracker = new MediaTracker(this);
                    }
                    this.tracker.addImage(this.frontImg, 0);
                }
                else {
                    if ((this.bmpIsF = this.getBytesFile(this.fgFile)) == null) {
                        System.err.println("Open front image(bmp) error");
                        this.badThing = true;
                    }
                    if (this.backPic == 0) {
                        this.bmpIs = this.bmpIsF;
                    }
                }
            }
        }
        if (this.frontPic == 0 && this.vTextFile != null && (this.vTextBr = this.getBytesFile(this.vTextFile)) == null) {
            System.err.println("Open text file error");
            this.badThing = true;
        }
        final String s = (this.vTextFile == null) ? this.getParameter("scrollText") : null;
        if (s != null) {
            final String[] array = new String[this.textLineN];
            if (s.length() == 0) {
                array[0] = s;
            }
            else {
                array[0] = ((s.charAt(0) == 's') ? s.substring(1) : s);
            }
            int i;
            for (i = 1; i < this.textLineN; ++i) {
                final String parameter = this.getParameter("scrollText" + i);
                if (parameter == null) {
                    break;
                }
                if (parameter.length() == 0) {
                    array[i] = parameter;
                }
                else {
                    array[i] = ((parameter.charAt(0) == 's') ? parameter.substring(1) : parameter);
                }
            }
            this.nText = i;
            int j;
            if (this.animator == 1) {
                this.sTextBuf = new Vector(this.nText);
                for (j = 0; j < this.nText; ++j) {
                    this.sTextBuf.addElement(array[j]);
                }
            }
            else {
                this.sssb = array[0];
                this.sTextBuf = new Vector(1);
                for (j = 1; j < this.nText; ++j) {
                    this.sssb = String.valueOf(this.sssb) + " " + array[j];
                }
                this.nText = 1;
                this.sTextBuf.addElement(this.sssb);
            }
            this.sssb = "3D Scroll: loading...";
            for (int k = 0; k < array.length; ++k) {
                array[j] = null;
            }
        }
        else if (this.vTextFile != null) {
            this.sssb = "3D Scroll: loading...";
        }
        this.leadint = -1;
        this.preCall = ((this.delay / 2 < 10) ? (this.delay / 2) : 10);
        this.liteColor = new int[3];
        final String trimParam6 = this.getTrimParam("textColor");
        for (int l = 0; l < 3; ++l) {
            this.liteColor[l] = 0;
        }
        if (this.getHexColor(trimParam6, this.liteColor, 0) < 0) {
            for (int n2 = 0; n2 < 3; ++n2) {
                this.liteColor[n2] = 0;
            }
        }
        this.lite = new Color(this.liteColor[0], this.liteColor[1], this.liteColor[2]);
        this.darkColor = new int[3];
        final String parameter2 = this.getParameter("shadow");
        if (this.emptyString(parameter2) > 0) {
            final Point s2Int3 = this.s2Int(parameter2);
            if (s2Int3.y >= 0 && s2Int3.x >= 0) {
                this.shadow = 1;
                this.shadowOffset = Math.min(10, s2Int3.x);
            }
            else {
                this.badThing = true;
            }
        }
        final String trimParam7 = this.getTrimParam("shadowColor");
        if (this.getHexColor(trimParam7, this.darkColor, 0) >= 0) {
            this.shadowColor = new Color(this.darkColor[0], this.darkColor[1], this.darkColor[2]);
        }
        else if (trimParam7 != null) {
            this.badThing = true;
        }
        final String parameter3 = this.getParameter("frontShadow");
        if (parameter3 != null) {
            final Point s2Int4 = this.s2Int(parameter3);
            if (s2Int4.y >= 0 && s2Int4.x >= 0) {
                this.frontShadow = 1;
                this.fShadowOffset = Math.min(10, s2Int4.x);
            }
            else {
                this.badThing = true;
            }
        }
        final String trimParam8 = this.getTrimParam("frontShadowColor");
        if (this.emptyString(trimParam8) > 0) {
            if (this.getHexColor(trimParam8, this.darkColor, 0) >= 0) {
                this.fShadowColor = new Color(this.darkColor[0], this.darkColor[1], this.darkColor[2]);
            }
            else {
                this.badThing = true;
            }
        }
        if (this.shadowColor == null) {
            this.shadowColor = this.fShadowColor;
        }
        if (this.fShadowColor == null) {
            this.fShadowColor = this.shadowColor;
        }
        final String parameter4 = this.getParameter("fontGap");
        if (parameter4 != null) {
            final Point s2Int5 = this.s2Int(parameter4);
            if (s2Int5.y >= 0 && s2Int5.x >= 0) {
                this.fontGap = s2Int5.x;
            }
            else {
                this.badThing = true;
            }
        }
        if (this.getHexColor(this.getTrimParam("fontBackColor"), this.darkColor, 255) >= 0) {
            this.sDark = new Color(this.darkColor[0], this.darkColor[1], this.darkColor[2]);
        }
        final String trimParam9 = this.getTrimParam("backColor");
        for (int n3 = 0; n3 < 3; ++n3) {
            this.darkColor[n3] = 255;
        }
        if (this.getHexColor(trimParam9, this.darkColor, 255) < 0) {
            for (int n4 = 0; n4 < 3; ++n4) {
                this.darkColor[n4] = 255;
            }
        }
        this.dark = new Color(this.darkColor[0], this.darkColor[1], this.darkColor[2]);
        if (this.sDark == null) {
            this.sDark = this.dark;
        }
        this.setFont(this.mFont);
        final String trimParam10 = this.getTrimParam("Frame");
        if (this.emptyString(trimParam10) > 0) {
            int frameN = 0;
            int hexColor = 0;
            final int length = trimParam10.length();
            int wordIndex = this.wordIndex(trimParam10, length + 1, 0);
            if (wordIndex != -1) {
                this.frameColor = new Color[hexColor = -wordIndex - 1];
                wordIndex = 0;
            }
            final int[] array2 = new int[3];
            while (wordIndex >= 0 && wordIndex < length && hexColor > 0) {
                final int wordIndex2 = this.wordIndex(trimParam10, 2, wordIndex);
                final int n5 = (wordIndex2 < 0) ? length : wordIndex2;
                hexColor = this.getHexColor(trimParam10.substring(wordIndex, n5), array2, 0);
                if (hexColor > 0) {
                    this.frameColor[frameN++] = new Color(array2[0], array2[1], array2[2]);
                    wordIndex = n5;
                }
                else {
                    this.badThing = true;
                    wordIndex = -1;
                }
            }
            this.frameN = frameN;
        }
        this.lm = this.frameN;
        this.rm = this.frameN;
        this.tm = this.frameN;
        this.bm = this.frameN;
        final Point s2Int6 = this.s2Int(this.getParameter("leftMargin"));
        if (s2Int6.y >= 0 && s2Int6.x >= 0) {
            this.lm = Math.min(s2Int6.x, this.aw);
        }
        final Point s2Int7 = this.s2Int(this.getParameter("rightMargin"));
        if (s2Int7.y >= 0 && s2Int7.x >= 0) {
            this.rm = Math.min(this.aw, s2Int7.x);
        }
        final Point s2Int8 = this.s2Int(this.getParameter("topMargin"));
        if (s2Int8.y >= 0 && s2Int8.x >= 0) {
            this.tm = Math.min(this.ah, s2Int8.x);
        }
        final Point s2Int9 = this.s2Int(this.getParameter("bottomMargin"));
        if (s2Int9.y >= 0 && s2Int9.x >= 0) {
            this.bm = Math.min(this.ah, s2Int9.x);
        }
        this.rm = Math.min(this.aw - this.lm, this.rm);
        this.bm = Math.min(this.ah - this.tm, this.bm);
        this.realAw = this.aw;
        this.realAh = this.ah;
        if (this.animator == 1) {
            this.leadint = this.lm;
            this.lm = this.tm;
            this.tm = this.rm;
            this.rm = this.bm;
            this.bm = this.leadint;
            this.leadint = this.aw;
            this.aw = this.ah;
            this.ah = this.leadint;
        }
        this.leadint = this.aw - this.rm - 1;
        this.bian = this.aw - this.rm;
        this.mFont = this.getFont();
        this.mFont = new Font("Helvetica", 0, 14);
        final String trimParam11 = this.getTrimParam("fontName");
        String s2;
        if (trimParam11 == null) {
            s2 = new String("Helvetica");
        }
        else {
            s2 = trimParam11;
        }
        int n6 = 0;
        final String trimParam12 = this.getTrimParam("fontStyle");
        if (this.emptyString(trimParam12) > 0) {
            if (trimParam12.equalsIgnoreCase("bold")) {
                n6 = 1;
            }
            else if (trimParam12.equalsIgnoreCase("italic")) {
                n6 = 2;
            }
            else if (trimParam12.equalsIgnoreCase("bold italic")) {
                n6 = 3;
            }
        }
        final Point s2Int10 = this.s2Int(this.getParameter("fontSize"));
        int x;
        if (s2Int10.y < 0 || s2Int10.x <= 0 || s2Int10.x > 72) {
            x = 14;
        }
        else {
            x = s2Int10.x;
        }
        this.mFont = new Font(s2, n6, x);
        final Point s2Int11 = this.s2Int(this.getParameter("delay"));
        if (s2Int11.y >= 0 && s2Int11.x > 0) {
            this.delay = s2Int11.x;
        }
        final Point s2Int12 = this.s2Int(this.getParameter("advance"));
        if (s2Int12.y >= 0) {
            this.advance = s2Int12.x;
        }
        this.overI = ((this.advance < 0) ? -10 : (this.aw + 10));
        final Point s2Int13 = this.s2Int(this.getParameter("pauseTime"));
        if (s2Int13.y >= 0 && s2Int13.x > 0) {
            this.pause = 100 * s2Int13.x;
        }
        if (this.pause > 0) {
            final Point s2Int14 = this.s2Int(this.getParameter("pauseLoop"));
            if (s2Int14.y >= 0) {
                this.pAtl = s2Int14.x;
            }
            final String trimParam13 = this.getTrimParam("pauseAt");
            boolean b = false;
            if (this.emptyString(trimParam13) > 0) {
                if (trimParam13.equalsIgnoreCase("middle")) {
                    this.pauseAt = this.overI;
                }
                else {
                    final Point s2Int15 = this.s2Int(trimParam13);
                    if (s2Int15.y >= 0) {
                        this.pauseAt = ((this.advance < 0) ? Math.max(this.lm - 1, s2Int15.x + this.lm) : Math.min(this.bian, s2Int15.x + this.lm));
                    }
                    else {
                        b = true;
                    }
                }
            }
            else {
                b = true;
            }
            if (b) {
                this.pauseAt = ((this.advance < 0) ? (this.lm - 1) : this.bian);
            }
        }
        final Point s2Int16 = this.s2Int(this.getParameter("Gap"));
        if (s2Int16.y >= 0 && s2Int16.x >= 0) {
            this.rollGap = Math.min(this.bian - this.lm, s2Int16.x);
        }
        final String trimParam14 = this.getTrimParam("startAt");
        boolean b2 = false;
        if (this.emptyString(trimParam14) > 0) {
            if (trimParam14.equalsIgnoreCase("middle")) {
                this.leftEdge = this.overI;
            }
            else {
                final Point s2Int17 = this.s2Int(trimParam14);
                if (s2Int17.y >= 0) {
                    this.leftEdge = ((this.advance < 0) ? Math.max(this.lm - 1, s2Int17.x + this.lm) : Math.min(this.bian, s2Int17.x + this.lm));
                }
                else {
                    b2 = true;
                }
            }
        }
        else {
            b2 = true;
        }
        if (b2) {
            this.leftEdge = ((this.advance < 0) ? (this.lm - 1) : this.bian);
        }
        if (Math.abs(this.frontPic) % 2 == 0) {
            final String trimParam15 = this.getTrimParam("fontEffect");
            if (this.emptyString(trimParam15) > 0) {
                final String lowerCase = trimParam15.toLowerCase();
                if (this.startEqual(lowerCase, "outline")) {
                    this.fontEffect = 7;
                }
                else if (this.startEqual(lowerCase, "outlinefont")) {
                    this.fontEffect = 1;
                }
                else if (this.startEqual(lowerCase, "transparent notrim")) {
                    if (this.frontPic != 0) {
                        this.fontEffect = 256;
                    }
                }
                else if (this.startEqual(lowerCase, "transparent")) {
                    if (this.frontPic != 0) {
                        this.fontEffect = 768;
                    }
                }
                else if (this.startEqual(lowerCase, "3d nosmooth")) {
                    this.f3D2 = 1;
                    this.fontEffect = 16;
                }
                else if (lowerCase.startsWith("3d smoothfont ")) {
                    this.f3D2 = 1;
                    this.f3D1 = -1;
                }
                else if (lowerCase.startsWith("3d ")) {
                    this.f3D2 = 1;
                    this.f3D1 = 1;
                }
                else if (lowerCase.startsWith("smooth smoothfont ")) {
                    this.f3D2 = -1;
                    this.f3D1 = -1;
                }
                else if (lowerCase.startsWith("smooth ")) {
                    this.f3D2 = -1;
                    this.f3D1 = 1;
                }
                if ((this.f3D2 > 0 || (this.fontEffect & 0x7) != 0x0) && (this.shadowColor == null || this.fShadowColor == null)) {
                    this.badThing = true;
                    this.f3D2 = 0;
                    this.fontEffect = 0;
                }
                if (this.f3D2 != 0 && this.f3D1 != 0) {
                    this.fontEffect = ((this.f3D2 > 0) ? 16 : 8);
                    final double[] paramDouble = this.paramDouble(lowerCase, (this.f3D1 > 0) ? 2 : 3);
                    if (paramDouble[0] == 0.0 && paramDouble[1] >= 0.0 && paramDouble[1] < 0.9999) {
                        this.f3D2 = ((this.f3D2 > 0) ? 64 : -64);
                        paramDouble[1] = Math.min(paramDouble[1], 0.999);
                        this.f3D1 = (int)((this.f3D1 > 0) ? (paramDouble[1] * 64.0) : (-(paramDouble[1] * 64.0)));
                    }
                    else {
                        this.badThing = true;
                        this.f3D2 = 0;
                    }
                }
            }
        }
        final String trimParam16 = this.getTrimParam("URL");
        if (trimParam16 != null) {
            try {
                this.userUrl = new URL(trimParam16);
            }
            catch (MalformedURLException ex) {
                this.userUrl = null;
                this.badThing = true;
            }
            if (this.userUrl != null) {
                this.target = this.getTrimParam("urlTarget");
            }
        }
        this.offImage = new Image[2];
        int n7 = (this.backPic == 0) ? 1 : 0;
        if (this.backPic == 1 && this.bkPicDraw == 1) {
            n7 = 1;
        }
        for (int n8 = n7; n8 < 2; ++n8) {
            this.offImage[n8] = this.createImage(this.realAw, this.realAh);
        }
        if (this.pAtl == 0) {
            this.pause = 0;
        }
        if (this.frontPic == 0 && this.sssb == null) {
            this.badThing = true;
        }
        if (this.badThing) {
            System.err.println("Input parameter(s) error!");
            this.sssb = "Input parameter(s) error!";
        }
        else {
            this.sssb = "3D Scroll: loading...";
            if (this.shadowColor == null) {
                this.shadowColor = new Color(0, 0, 0);
                this.fShadowColor = this.shadowColor;
            }
        }
        this.strLock = new String("12");
        this.infoL1 = new String("12");
        this.infoL2 = new String("12");
        this.frozenL = new String("12");
        this.tol = this.delay / 10L;
        if (this.pause <= this.delay) {
            this.pause = 0;
        }
        this.lastUT = System.currentTimeMillis() - 10 * this.delay;
    }
    
    public void start() {
        if (!this.firstStart && (this.badThing || this.frozen)) {
            return;
        }
        if (this.clockThread == null) {
            (this.clockThread = new Thread(this, "Clock")).start();
            return;
        }
        this.firstStart = false;
    }
    
    private void runError() {
        this.badThing = true;
        this.sweeping();
        this.clockThread = null;
        this.cleanM(1);
    }
    
    private int naps(final long n) {
        int n2 = 0;
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {
                n2 = -1;
            }
        }
        return n2;
    }
    
    public void run() {
        synchronized (this.strLock) {
            final Thread currentThread = Thread.currentThread();
            if (this.clockThread == currentThread && this.firstRun) {
                if (this.badThing) {
                    this.clockThread = null;
                    // monitorexit(this.strLock)
                    return;
                }
                if (!this.pswdGood) {
                    System.err.println("Please register this software");
                }
                if (this.backPic == 1 || (this.frontPic != 0 && Math.abs(this.frontPic) < 3)) {
                    final int waitImg = this.waitImg(0);
                    if (waitImg != 0) {
                        if (waitImg < 0) {
                            while (this.tracker.statusID(0, true) != 8) {
                                if (this.clockThread == null || this.clockThread != currentThread) {
                                    // monitorexit(this.strLock)
                                    return;
                                }
                                this.naps(3000L);
                                this.repaint();
                            }
                        }
                        System.err.println("wait image interrupted or error");
                        // monitorexit(this.strLock)
                        return;
                    }
                }
                if (!this.makeImgs()) {
                    System.err.println("Error in file(s) processing");
                    this.badThing = true;
                    this.sweeping();
                    this.clockThread = null;
                    this.cleanM(1);
                    // monitorexit(this.strLock)
                    return;
                }
                this.swapAsNeeded();
                this.imgWHDefined();
                this.sweeping();
                this.firstRun = false;
            }
            if (this.clockThread == currentThread && (this.advance == 0 || this.bian <= this.lm || this.ah <= this.tm + this.bm || this.iw <= 0 || this.ih <= 0)) {
                final String infoL2 = this.infoL2;
                // monitorenter(infoL2)
                try {
                    this.runCalled = System.currentTimeMillis();
                    this.runReady = 1;
                }
                // monitorexit(infoL2)
                finally {}
                this.repaint();
                System.err.println("run: no animation.");
                this.clockThread = null;
                // monitorexit(this.strLock)
                return;
            }
            while (this.clockThread != null && this.clockThread == currentThread) {
                if (!this.pswdGood) {
                    this.showStatus("Unregistered 3D Scroll");
                }
                if (this.myPause) {
                    this.naps(this.picRequest - System.currentTimeMillis());
                    this.myPause = false;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                final String infoL3 = this.infoL2;
                // monitorenter(infoL3)
                try {
                    this.runCalled = currentTimeMillis;
                    this.runReady = 1;
                }
                // monitorexit(infoL3)
                finally {}
                this.repaint();
                final String infoL4 = this.infoL1;
                // monitorenter(infoL4)
                try {
                    this.picRequest = this.pauseAwhile;
                    this.myPause = this.restAwhile;
                }
                // monitorexit(infoL4)
                finally {}
                if (this.clockThread == currentThread) {
                    this.naps(currentTimeMillis + this.delay - System.currentTimeMillis());
                }
            }
        }
        // monitorexit(this.strLock)
    }
    
    private void errorExit() {
        this.cleanM(1);
    }
    
    private int strPaintLen(final String s, final int n, final FontMetrics fontMetrics) {
        final int length = s.length();
        int stringWidth = 0;
        if (length > 0) {
            if (n > 0) {
                for (int i = 0; i < length; ++i) {
                    stringWidth += fontMetrics.charWidth(s.charAt(i));
                }
                stringWidth += n * (length - 1);
            }
            else {
                stringWidth = fontMetrics.stringWidth(s);
            }
        }
        return stringWidth;
    }
    
    private void swapAsNeeded() {
        if (this.frontPic == 0 && this.fontEffect == 0) {
            final Graphics graphics = this.offImage[1].getGraphics();
            final FontMetrics fontMetrics = graphics.getFontMetrics(this.mFont);
            int max = 0;
            for (int i = 0; i < this.nText; ++i) {
                max = Math.max(max, this.strPaintLen((i >= this.nText || i < 0) ? null : ((String)this.sTextBuf.elementAt(i)), this.fontGap, fontMetrics));
            }
            this.iw = max + this.shadowOffset + this.fShadowOffset;
            this.ih = fontMetrics.getHeight() + this.shadowOffset + this.fShadowOffset;
            this.ih *= this.nText;
            this.fMaxD = fontMetrics.getMaxDecent();
            graphics.dispose();
        }
        if (this.fontEffect != 0 && this.frontPic == 0) {
            this.frontPic = 5;
        }
        if (this.animator == 1) {
            final int iw = this.iw;
            this.iw = this.ih;
            this.ih = iw;
            if (this.frontPic == 0) {
                this.verticalText = true;
            }
        }
    }
    
    public void initLoadM(final Graphics graphics, final String s) {
        final Graphics graphics2 = (graphics == null) ? this.offImage[1].getGraphics() : graphics;
        graphics2.getFont();
        final FontMetrics fontMetrics = graphics2.getFontMetrics(graphics2.getFont());
        final int n = fontMetrics.getAscent() + fontMetrics.getDescent();
        graphics2.setColor(this.dark);
        graphics2.fillRect(0, 0, this.realAw, this.realAh);
        final int max = Math.max(n, (this.realAh + n) / 2);
        graphics2.setColor(this.lite);
        graphics2.drawString(s, 10, max);
    }
    
    public void update(final Graphics graphics) {
        final Graphics graphics2 = this.offImage[this.offID].getGraphics();
        boolean b = this.tracker != null;
        if (this.tracker != null && (this.tracker.statusID(0, true) & 0x4) == 0x0 && this.tracker.statusID(0, true) == 8) {
            b = false;
        }
        final long runCalled;
        final int runReady;
        synchronized (this.infoL2) {
            runCalled = this.runCalled;
            runReady = this.runReady;
        }
        // monitorexit(this.infoL2)
        if (this.badThing) {
            this.initLoadM(graphics2, "Error in parameters or files");
        }
        else if (runReady < 0 || b) {
            this.initLoadM(graphics2, "Loading...");
        }
        else if (this.incOne(runCalled)) {
            if (!this.nextPause) {
                this.picPosition = this.calcFrame(0);
            }
            else {
                this.nextPause = false;
            }
            if (!this.nextPause) {
                this.prepareImg(this.picPosition, this.offID, 0);
            }
            else {
                this.pauseLimit = this.lastUT + this.pause;
            }
        }
        synchronized (this.infoL1) {
            this.pauseAwhile = this.pauseLimit;
            this.restAwhile = this.nextPause;
        }
        // monitorexit(this.infoL1)
        graphics.drawImage(this.offImage[this.offID], 0, 0, this);
        graphics2.dispose();
    }
    
    private boolean incOne(final long n) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long n2 = currentTimeMillis - this.lastUT;
        boolean b = false;
        if (this.nextPause) {
            b = (currentTimeMillis >= this.pauseLimit);
        }
        else if (n2 >= this.delay) {
            b = true;
            if (n2 >= 2 * this.delay) {
                this.initUT = currentTimeMillis - this.delay;
                this.totalFrames = 1L;
            }
        }
        else if (n > this.lastUT) {
            b = (currentTimeMillis - this.initUT + this.tol < (this.totalFrames + 1L) * this.delay);
        }
        if (b) {
            if (!this.nextPause) {
                ++this.totalFrames;
            }
            this.lastUT = currentTimeMillis;
        }
        return b;
    }
    
    private void imgWHDefined() {
        this.maxc = Math.max(3, this.leadint + 640 - this.lm);
        this.topMargin = ((this.animator == 1) ? Math.max(this.bm, Math.min(this.ah - 1, (this.ah - this.tm - this.bm - this.ih) / 2 + this.bm)) : Math.max(this.tm, Math.min(this.ah - 1, (this.ah - this.tm - this.bm - this.ih) / 2 + this.tm)));
        if (this.leftEdge == this.overI) {
            this.leftEdge = ((this.advance >= 0) ? Math.max(this.lm + (this.bian - this.lm - this.iw + 1) / 2, this.lm) : Math.min(this.bian - 1 - (this.bian - this.lm - this.iw) / 2, this.bian - 1));
        }
        else {
            this.leftEdge = ((this.advance >= 0) ? Math.max(this.lm - this.iw + 1, this.leftEdge) : Math.min(this.bian - 2 + this.iw, this.leftEdge));
        }
        if (this.advance == 0 || this.iw <= 0 || this.ih <= 0 || this.bian - this.lm <= 0 || this.ah - this.bm - this.tm <= 0) {
            this.pause = 0;
            this.rollGap = -1;
        }
        if (this.pause > 0) {
            if (this.pauseAt == this.overI) {
                this.pauseAt = ((this.advance >= 0) ? Math.max(this.lm + (this.bian - this.lm - this.iw + 1) / 2, this.lm) : Math.min(this.bian - 1 - (this.bian - this.lm - this.iw) / 2, this.bian - 1));
            }
            else {
                this.pauseAt = ((this.advance >= 0) ? Math.max(this.lm - this.iw, this.pauseAt) : Math.min(this.bian - 1 + this.iw, this.pauseAt));
            }
            if (this.rollGap < 0) {
                this.pauseAt = ((this.advance < 0) ? Math.min(this.bian - 2 + this.iw, this.pauseAt) : Math.max(this.lm - this.iw + 1, this.pauseAt));
            }
        }
        this.startAt = this.leftEdge;
        this.flagPos = this.leftEdge;
        if (this.pause > 0) {
            if (this.pAtl < 0) {
                this.pAtl = -this.pAtl;
                this.loopCnt = this.pAtl - 1;
            }
            else if (this.pAtl > 0) {
                this.loopCnt = -1;
            }
            else {
                this.pAtl = -2;
            }
            if (this.startAt < this.pauseAt) {
                if (this.advance > 0) {
                    if (this.rollGap >= 0) {
                        while (this.flagPos < this.pauseAt) {
                            this.flagPos += this.iw + this.rollGap;
                        }
                    }
                    else {
                        final int n = this.flagPos - this.lm + this.iw + (this.verticalText ? this.fMaxD : 0);
                        this.flagPos = ((n % this.advance != 0) ? 1 : 0);
                        this.flagPos += n / this.advance;
                        this.flagPos = this.bian - 1 + this.flagPos * this.advance;
                        if (this.pauseAt == this.bian) {
                            ++this.flagPos;
                        }
                    }
                }
            }
            else if (this.startAt > this.pauseAt && this.advance < 0) {
                if (this.rollGap >= 0) {
                    while (this.flagPos > this.pauseAt) {
                        this.flagPos -= this.iw + this.rollGap;
                    }
                }
                else {
                    final int n2 = this.bian - 1 - this.flagPos + this.iw;
                    final int n3 = -this.advance;
                    this.flagPos = ((n2 % n3 != 0) ? 1 : 0);
                    this.flagPos += n2 / n3;
                    this.flagPos = this.lm + this.flagPos * this.advance;
                    if (this.pauseAt == this.lm - 1) {
                        --this.flagPos;
                    }
                }
            }
        }
        this.leftEdge += this.advance;
        this.lastHead = this.leftEdge;
        this.flagPos += this.advance;
        if (this.pause > 0 && this.rollGap < 0 && this.advance != 0) {
            if (this.advance > 0) {
                final int n4 = this.pauseAt + this.iw + (this.verticalText ? this.fMaxD : 0) - this.lm;
                int n5 = n4 / this.advance;
                if (n4 % this.advance != 0) {
                    ++n5;
                }
                this.rollGap = this.bian - 1 + n5 * this.advance;
                if (this.pauseAt == this.bian) {
                    ++this.rollGap;
                }
                this.rollGap = ((this.rollGap > 0) ? (-this.rollGap) : -1);
            }
            else {
                final int n6 = this.bian - 1 + this.iw - this.pauseAt;
                final int n7 = ((n6 % -this.advance > 0) ? 1 : 0) + n6 / -this.advance;
                this.rollGap = ((n7 < 0) ? -10 : (-(n7 + 1)));
            }
        }
        this.picInfo = new int[2];
        if (this.pause <= 0) {
            this.pAtl = 0;
            this.loopCnt = 1;
            this.pauseAt = 0;
            this.flagPos = 1;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void stop() {
        this.clockThread = null;
    }
    
    private void cleanM(final int n) {
        for (int i = 0; i < 2; ++i) {
            if (n == 0 || i != this.offID) {
                this.offImage[i] = null;
            }
        }
        for (int j = 0; j < this.frameN; ++j) {
            this.frameColor[j] = null;
        }
        this.frameColor = null;
        this.frontImg = null;
        this.bkImg = null;
        this.ptTmp = null;
        this.workChar = null;
        this.picInfo = null;
    }
    
    protected void finalize() throws Throwable {
        this.clockThread = null;
        this.cleanM(0);
        if (this.clockThread != null) {
            this.clockThread.stop();
        }
        this.tbufInfo = null;
        this.sTextBuf.removeAllElements();
        this.sTextBuf = null;
        if (this.vTextBr != null) {
            this.vTextBr.close();
        }
        this.vTextBr = null;
        super.finalize();
    }
    
    private int calcFrame(final int n) {
        final int n2 = this.verticalText ? this.fMaxD : 0;
        int leftEdge;
        if (n == 0) {
            leftEdge = ((this.advance >= 0) ? this.posLeft(n2) : this.posRight());
        }
        else {
            leftEdge = this.leftEdge;
        }
        return this.leftEdge = leftEdge;
    }
    
    public void paintFrames(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < this.frameN; ++i) {
            graphics.setColor(this.frameColor[i]);
            graphics.drawLine(i + n3, i + n4, n - i - ((i >= (this.frameN + 1) / 2) ? 1 : 2) + n3, i + n4);
            graphics.drawLine(i + n3, i + n4, i + n3, n2 - i - 1 + n4);
            graphics.drawLine(this.frameN - i + n3, n2 - this.frameN + i + n4, n - this.frameN + i + n3, n2 - this.frameN + i + n4);
            graphics.drawLine(n - this.frameN + i + n3, this.frameN - i - ((i >= this.frameN / 2) ? 1 : 0) + n4, n - this.frameN + i + n3, n2 - this.frameN + i + n4);
        }
    }
    
    private int prepareImg(final int p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        Scroll3D.offImage:[Ljava/awt/Image;
        //     4: iload_2        
        //     5: aaload         
        //     6: invokevirtual   java/awt/Image.getGraphics:()Ljava/awt/Graphics;
        //     9: astore          4
        //    11: aload_0        
        //    12: getfield        Scroll3D.backPic:I
        //    15: ifle            36
        //    18: aload           4
        //    20: aload_0        
        //    21: getfield        Scroll3D.offImage:[Ljava/awt/Image;
        //    24: iconst_0       
        //    25: aaload         
        //    26: iconst_0       
        //    27: iconst_0       
        //    28: aload_0        
        //    29: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //    32: pop            
        //    33: goto            60
        //    36: aload           4
        //    38: aload_0        
        //    39: getfield        Scroll3D.dark:Ljava/awt/Color;
        //    42: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    45: aload           4
        //    47: iconst_0       
        //    48: iconst_0       
        //    49: aload_0        
        //    50: getfield        Scroll3D.realAw:I
        //    53: aload_0        
        //    54: getfield        Scroll3D.realAh:I
        //    57: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //    60: aload_0        
        //    61: getfield        Scroll3D.iw:I
        //    64: ifle            103
        //    67: aload_0        
        //    68: getfield        Scroll3D.ih:I
        //    71: ifle            103
        //    74: aload_0        
        //    75: getfield        Scroll3D.ah:I
        //    78: aload_0        
        //    79: getfield        Scroll3D.tm:I
        //    82: isub           
        //    83: aload_0        
        //    84: getfield        Scroll3D.bm:I
        //    87: isub           
        //    88: ifle            103
        //    91: aload_0        
        //    92: getfield        Scroll3D.bian:I
        //    95: aload_0        
        //    96: getfield        Scroll3D.lm:I
        //    99: isub           
        //   100: ifgt            105
        //   103: iconst_0       
        //   104: ireturn        
        //   105: aload_0        
        //   106: getfield        Scroll3D.animator:I
        //   109: iconst_1       
        //   110: if_icmpeq       157
        //   113: aload           4
        //   115: aload_0        
        //   116: getfield        Scroll3D.lm:I
        //   119: aload_0        
        //   120: getfield        Scroll3D.tm:I
        //   123: aload_0        
        //   124: getfield        Scroll3D.realAw:I
        //   127: aload_0        
        //   128: getfield        Scroll3D.lm:I
        //   131: isub           
        //   132: aload_0        
        //   133: getfield        Scroll3D.rm:I
        //   136: isub           
        //   137: aload_0        
        //   138: getfield        Scroll3D.realAh:I
        //   141: aload_0        
        //   142: getfield        Scroll3D.tm:I
        //   145: isub           
        //   146: aload_0        
        //   147: getfield        Scroll3D.bm:I
        //   150: isub           
        //   151: invokevirtual   java/awt/Graphics.clipRect:(IIII)V
        //   154: goto            198
        //   157: aload           4
        //   159: aload_0        
        //   160: getfield        Scroll3D.bm:I
        //   163: aload_0        
        //   164: getfield        Scroll3D.lm:I
        //   167: aload_0        
        //   168: getfield        Scroll3D.realAw:I
        //   171: aload_0        
        //   172: getfield        Scroll3D.bm:I
        //   175: isub           
        //   176: aload_0        
        //   177: getfield        Scroll3D.tm:I
        //   180: isub           
        //   181: aload_0        
        //   182: getfield        Scroll3D.realAh:I
        //   185: aload_0        
        //   186: getfield        Scroll3D.lm:I
        //   189: isub           
        //   190: aload_0        
        //   191: getfield        Scroll3D.rm:I
        //   194: isub           
        //   195: invokevirtual   java/awt/Graphics.clipRect:(IIII)V
        //   198: aload           4
        //   200: aload_0        
        //   201: getfield        Scroll3D.mFont:Ljava/awt/Font;
        //   204: invokevirtual   java/awt/Graphics.getFontMetrics:(Ljava/awt/Font;)Ljava/awt/FontMetrics;
        //   207: astore          7
        //   209: aload_0        
        //   210: getfield        Scroll3D.fShadowOffset:I
        //   213: aload_0        
        //   214: getfield        Scroll3D.shadowOffset:I
        //   217: iadd           
        //   218: istore          8
        //   220: aload           7
        //   222: invokevirtual   java/awt/FontMetrics.getHeight:()I
        //   225: iload           8
        //   227: iadd           
        //   228: istore          9
        //   230: aload           7
        //   232: invokevirtual   java/awt/FontMetrics.getMaxDecent:()I
        //   235: istore          10
        //   237: aload           7
        //   239: invokevirtual   java/awt/FontMetrics.getLeading:()I
        //   242: aload           7
        //   244: invokevirtual   java/awt/FontMetrics.getDescent:()I
        //   247: iadd           
        //   248: istore          11
        //   250: aload_0        
        //   251: getfield        Scroll3D.verticalText:Z
        //   254: ifeq            262
        //   257: iload           10
        //   259: goto            263
        //   262: iconst_0       
        //   263: istore          12
        //   265: aload_0        
        //   266: getfield        Scroll3D.advance:I
        //   269: ifge            288
        //   272: aload_0        
        //   273: getfield        Scroll3D.verticalText:Z
        //   276: ifne            288
        //   279: iload_1        
        //   280: aload_0        
        //   281: getfield        Scroll3D.iw:I
        //   284: iconst_1       
        //   285: isub           
        //   286: isub           
        //   287: istore_1       
        //   288: aload_0        
        //   289: getfield        Scroll3D.iw:I
        //   292: istore          12
        //   294: iload           12
        //   296: aload_0        
        //   297: getfield        Scroll3D.rollGap:I
        //   300: ifge            314
        //   303: iconst_4       
        //   304: aload_0        
        //   305: getfield        Scroll3D.aw:I
        //   308: imul           
        //   309: iconst_1       
        //   310: iadd           
        //   311: goto            318
        //   314: aload_0        
        //   315: getfield        Scroll3D.rollGap:I
        //   318: iadd           
        //   319: istore          12
        //   321: aload_0        
        //   322: getfield        Scroll3D.advance:I
        //   325: ifge            333
        //   328: iload           12
        //   330: ineg           
        //   331: istore          12
        //   333: iload_1        
        //   334: istore          14
        //   336: aload_0        
        //   337: getfield        Scroll3D.verticalText:Z
        //   340: ifeq            348
        //   343: iload           10
        //   345: goto            354
        //   348: aload_0        
        //   349: getfield        Scroll3D.iw:I
        //   352: iconst_1       
        //   353: isub           
        //   354: istore          15
        //   356: aload_0        
        //   357: getfield        Scroll3D.verticalText:Z
        //   360: ifeq            377
        //   363: aload_0        
        //   364: iload_1        
        //   365: iload           9
        //   367: iload           10
        //   369: iload           11
        //   371: invokespecial   Scroll3D.getTextPos:(IIII)V
        //   374: goto            385
        //   377: aload_0        
        //   378: getfield        Scroll3D.ptTmp:Ljava/awt/Point;
        //   381: iconst_0       
        //   382: putfield        java/awt/Point.x:I
        //   385: aload_0        
        //   386: getfield        Scroll3D.verticalText:Z
        //   389: ifeq            402
        //   392: aload_0        
        //   393: getfield        Scroll3D.ptTmp:Ljava/awt/Point;
        //   396: getfield        java/awt/Point.y:I
        //   399: goto            419
        //   402: aload_0        
        //   403: getfield        Scroll3D.topMargin:I
        //   406: aload_0        
        //   407: getfield        Scroll3D.ih:I
        //   410: iadd           
        //   411: iload           10
        //   413: isub           
        //   414: iload           8
        //   416: isub           
        //   417: iconst_1       
        //   418: isub           
        //   419: istore          5
        //   421: aload_0        
        //   422: getfield        Scroll3D.topMargin:I
        //   425: istore          6
        //   427: aload_0        
        //   428: getfield        Scroll3D.verticalText:Z
        //   431: ifeq            439
        //   434: iload           10
        //   436: goto            445
        //   439: aload_0        
        //   440: getfield        Scroll3D.iw:I
        //   443: iconst_1       
        //   444: isub           
        //   445: istore          15
        //   447: goto            881
        //   450: aload_0        
        //   451: getfield        Scroll3D.frontPic:I
        //   454: ifne            464
        //   457: aload_0        
        //   458: getfield        Scroll3D.fontEffect:I
        //   461: ifeq            512
        //   464: aload_0        
        //   465: getfield        Scroll3D.animator:I
        //   468: iconst_1       
        //   469: if_icmpne       492
        //   472: aload           4
        //   474: aload_0        
        //   475: getfield        Scroll3D.frontImg:Ljava/awt/Image;
        //   478: aload_0        
        //   479: getfield        Scroll3D.topMargin:I
        //   482: iload           14
        //   484: aload_0        
        //   485: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   488: pop            
        //   489: goto            834
        //   492: aload           4
        //   494: aload_0        
        //   495: getfield        Scroll3D.frontImg:Ljava/awt/Image;
        //   498: iload           14
        //   500: aload_0        
        //   501: getfield        Scroll3D.topMargin:I
        //   504: aload_0        
        //   505: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   508: pop            
        //   509: goto            834
        //   512: aload           4
        //   514: aload_0        
        //   515: getfield        Scroll3D.mFont:Ljava/awt/Font;
        //   518: invokevirtual   java/awt/Graphics.setFont:(Ljava/awt/Font;)V
        //   521: aload_0        
        //   522: getfield        Scroll3D.verticalText:Z
        //   525: ifne            532
        //   528: iload           14
        //   530: istore          6
        //   532: aload_0        
        //   533: getfield        Scroll3D.ptTmp:Ljava/awt/Point;
        //   536: getfield        java/awt/Point.x:I
        //   539: istore          16
        //   541: goto            825
        //   544: aload_0        
        //   545: iload           16
        //   547: aload_0        
        //   548: getfield        Scroll3D.nText:I
        //   551: if_icmpge       559
        //   554: iload           16
        //   556: ifge            563
        //   559: aconst_null    
        //   560: goto            575
        //   563: aload_0        
        //   564: getfield        Scroll3D.sTextBuf:Ljava/util/Vector;
        //   567: iload           16
        //   569: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //   572: checkcast       Ljava/lang/String;
        //   575: putfield        Scroll3D.sssb:Ljava/lang/String;
        //   578: aload_0        
        //   579: getfield        Scroll3D.frontShadow:I
        //   582: ifeq            627
        //   585: aload           4
        //   587: aload_0        
        //   588: getfield        Scroll3D.fShadowColor:Ljava/awt/Color;
        //   591: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   594: aload_0        
        //   595: aload           4
        //   597: aload_0        
        //   598: getfield        Scroll3D.sssb:Ljava/lang/String;
        //   601: aload_0        
        //   602: getfield        Scroll3D.workChar:[C
        //   605: aload           7
        //   607: iload           6
        //   609: iload           5
        //   611: aload_0        
        //   612: getfield        Scroll3D.fontGap:I
        //   615: invokespecial   Scroll3D.paintString:(Ljava/awt/Graphics;Ljava/lang/String;[CLjava/awt/FontMetrics;III)V
        //   618: iload           6
        //   620: aload_0        
        //   621: getfield        Scroll3D.fShadowOffset:I
        //   624: iadd           
        //   625: istore          6
        //   627: iload           5
        //   629: iload           8
        //   631: iadd           
        //   632: istore          5
        //   634: aload_0        
        //   635: getfield        Scroll3D.shadow:I
        //   638: ifeq            679
        //   641: aload           4
        //   643: aload_0        
        //   644: getfield        Scroll3D.shadowColor:Ljava/awt/Color;
        //   647: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   650: aload_0        
        //   651: aload           4
        //   653: aload_0        
        //   654: getfield        Scroll3D.sssb:Ljava/lang/String;
        //   657: aload_0        
        //   658: getfield        Scroll3D.workChar:[C
        //   661: aload           7
        //   663: iload           6
        //   665: aload_0        
        //   666: getfield        Scroll3D.shadowOffset:I
        //   669: iadd           
        //   670: iload           5
        //   672: aload_0        
        //   673: getfield        Scroll3D.fontGap:I
        //   676: invokespecial   Scroll3D.paintString:(Ljava/awt/Graphics;Ljava/lang/String;[CLjava/awt/FontMetrics;III)V
        //   679: aload           4
        //   681: aload_0        
        //   682: getfield        Scroll3D.lite:Ljava/awt/Color;
        //   685: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   688: iload           5
        //   690: aload_0        
        //   691: getfield        Scroll3D.shadowOffset:I
        //   694: isub           
        //   695: istore          5
        //   697: aload_0        
        //   698: aload           4
        //   700: aload_0        
        //   701: getfield        Scroll3D.sssb:Ljava/lang/String;
        //   704: aload_0        
        //   705: getfield        Scroll3D.workChar:[C
        //   708: aload           7
        //   710: iload           6
        //   712: iload           5
        //   714: aload_0        
        //   715: getfield        Scroll3D.fontGap:I
        //   718: invokespecial   Scroll3D.paintString:(Ljava/awt/Graphics;Ljava/lang/String;[CLjava/awt/FontMetrics;III)V
        //   721: iload           5
        //   723: aload_0        
        //   724: getfield        Scroll3D.fShadowOffset:I
        //   727: isub           
        //   728: istore          5
        //   730: aload_0        
        //   731: getfield        Scroll3D.verticalText:Z
        //   734: ifeq            822
        //   737: iload           5
        //   739: aload_0        
        //   740: getfield        Scroll3D.advance:I
        //   743: ifge            752
        //   746: iload           9
        //   748: ineg           
        //   749: goto            754
        //   752: iload           9
        //   754: iadd           
        //   755: istore          5
        //   757: iload           6
        //   759: aload_0        
        //   760: getfield        Scroll3D.fShadowOffset:I
        //   763: isub           
        //   764: istore          6
        //   766: aload_0        
        //   767: getfield        Scroll3D.advance:I
        //   770: ifge            796
        //   773: iload           5
        //   775: aload_0        
        //   776: getfield        Scroll3D.lm:I
        //   779: iload           8
        //   781: isub           
        //   782: iload           10
        //   784: isub           
        //   785: if_icmplt       792
        //   788: iconst_0       
        //   789: goto            816
        //   792: iconst_1       
        //   793: goto            816
        //   796: iload           5
        //   798: aload_0        
        //   799: getfield        Scroll3D.bian:I
        //   802: iload           9
        //   804: iadd           
        //   805: iload           8
        //   807: isub           
        //   808: if_icmpge       815
        //   811: iconst_0       
        //   812: goto            816
        //   815: iconst_1       
        //   816: dup            
        //   817: istore          17
        //   819: ifne            834
        //   822: iinc            16, 1
        //   825: iload           16
        //   827: aload_0        
        //   828: getfield        Scroll3D.nText:I
        //   831: if_icmplt       544
        //   834: iload           14
        //   836: iload           12
        //   838: iadd           
        //   839: istore          14
        //   841: aload_0        
        //   842: getfield        Scroll3D.verticalText:Z
        //   845: ifeq            881
        //   848: aload_0        
        //   849: getfield        Scroll3D.advance:I
        //   852: ifge            863
        //   855: iload           14
        //   857: iload           8
        //   859: isub           
        //   860: goto            871
        //   863: iload           14
        //   865: iload           9
        //   867: iadd           
        //   868: iload           8
        //   870: isub           
        //   871: istore          5
        //   873: aload_0        
        //   874: getfield        Scroll3D.ptTmp:Ljava/awt/Point;
        //   877: iconst_0       
        //   878: putfield        java/awt/Point.x:I
        //   881: aload_0        
        //   882: getfield        Scroll3D.advance:I
        //   885: ifge            908
        //   888: iload           14
        //   890: aload_0        
        //   891: getfield        Scroll3D.lm:I
        //   894: iload           15
        //   896: isub           
        //   897: if_icmpge       904
        //   900: iconst_0       
        //   901: goto            922
        //   904: iconst_1       
        //   905: goto            922
        //   908: iload           14
        //   910: aload_0        
        //   911: getfield        Scroll3D.bian:I
        //   914: if_icmplt       921
        //   917: iconst_0       
        //   918: goto            922
        //   921: iconst_1       
        //   922: dup            
        //   923: istore          13
        //   925: ifne            450
        //   928: aload           4
        //   930: invokevirtual   java/awt/Graphics.dispose:()V
        //   933: iconst_0       
        //   934: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void paintString(final Graphics graphics, final String s, final char[] array, final FontMetrics fontMetrics, final int n, final int n2, final int n3) {
        final int length = s.length();
        if (length > 0) {
            if (n3 == 0) {
                graphics.drawString(s, n, n2);
                return;
            }
            int n4 = n;
            for (int i = 0; i < length; ++i) {
                array[0] = s.charAt(i);
                graphics.drawChars(array, 0, 1, n4, n2);
                n4 += fontMetrics.charWidth(array[0]) + n3;
            }
        }
    }
    
    private void getTextPos(final int n, final int n2, final int n3, final int n4) {
        this.ptTmp.x = 0;
        this.ptTmp.y = ((this.advance < 0) ? n : (n + n2 - 1));
        final int n5 = (this.advance < 0) ? (-n2) : n2;
        boolean b;
        while ((this.advance < 0) ? ((this.ptTmp.y < this.bian + n2 - n4) ? (b = false) : (b = true)) : ((this.ptTmp.y >= this.lm - n3) ? (b = false) : (b = true))) {
            final Point ptTmp = this.ptTmp;
            ptTmp.y += n5;
            final Point ptTmp2 = this.ptTmp;
            ++ptTmp2.x;
        }
        final int n6 = this.fShadowOffset + this.shadowOffset;
        final Point ptTmp3 = this.ptTmp;
        ptTmp3.y -= n6;
    }
    
    private int posLeft(final int n) {
        int n2 = 0;
        if (this.advance != 0 && this.pause > 0) {
            if (this.loopCnt == this.pAtl && this.flagPos == this.pauseAt) {
                this.nextPause = true;
                if (this.rollGap < 0) {
                    this.flagPos = -this.rollGap;
                    this.loopCnt = 0;
                }
                else {
                    this.loopCnt = -1;
                }
            }
            this.flagPos -= this.advance;
            while (this.flagPos <= this.pauseAt) {
                ++this.loopCnt;
                if (this.loopCnt == this.pAtl) {
                    n2 = this.pauseAt - this.flagPos;
                    this.flagPos = this.pauseAt;
                    break;
                }
                if (this.rollGap >= 0) {
                    this.flagPos += this.iw + this.rollGap;
                }
                else {
                    final int n3 = this.flagPos - this.lm + this.iw + n;
                    this.flagPos = ((n3 % this.advance != 0) ? 1 : 0);
                    this.flagPos += n3 / this.advance;
                    this.flagPos = this.bian - 1 + this.flagPos * this.advance;
                    if (this.pauseAt != this.bian) {
                        continue;
                    }
                    ++this.flagPos;
                }
            }
        }
        int n4 = this.leftEdge - this.advance + n2;
        if (this.rollGap < 0) {
            if (this.loopCnt == this.pAtl && this.flagPos == this.pauseAt) {
                if (n4 + this.iw + n <= this.lm) {
                    n4 = ((this.pauseAt == this.bian) ? this.pauseAt : (this.bian - 1));
                }
            }
            else if (n4 + this.iw + n <= this.lm) {
                n4 = this.bian - 1;
            }
        }
        else {
            while (n4 + this.iw + n <= this.lm) {
                n4 += this.iw + this.rollGap;
            }
        }
        return n4;
    }
    
    private int posRight() {
        int n = 0;
        if (this.advance != 0 && this.pause > 0) {
            if (this.loopCnt == this.pAtl && this.flagPos == this.pauseAt) {
                this.nextPause = true;
                if (this.rollGap < 0) {
                    this.flagPos = this.lm - (this.rollGap + 1) * this.advance;
                    this.loopCnt = 0;
                }
                else {
                    this.loopCnt = -1;
                }
            }
            this.flagPos -= this.advance;
            while (this.flagPos >= this.pauseAt) {
                ++this.loopCnt;
                if (this.loopCnt == this.pAtl) {
                    n = this.pauseAt - this.flagPos;
                    this.flagPos = this.pauseAt;
                    break;
                }
                if (this.rollGap >= 0) {
                    this.flagPos -= this.iw + this.rollGap;
                }
                else {
                    final int n2 = this.bian - 1 - this.flagPos + this.iw;
                    this.flagPos = ((n2 % -this.advance != 0) ? 1 : 0);
                    this.flagPos += n2 / -this.advance;
                    this.flagPos = this.lm + this.flagPos * this.advance;
                    if (this.pauseAt != this.lm - 1) {
                        continue;
                    }
                    --this.flagPos;
                }
            }
        }
        int lm = this.leftEdge - this.advance + n;
        if (this.rollGap < 0) {
            if (this.loopCnt == this.pAtl && this.flagPos == this.pauseAt) {
                if (lm - this.iw + 1 >= this.bian) {
                    lm = ((this.pauseAt == this.lm - 1) ? this.pauseAt : this.lm);
                }
            }
            else if (lm - this.iw + 1 >= this.bian) {
                lm = this.lm;
            }
        }
        else {
            while (lm + 1 - this.iw >= this.bian) {
                lm -= this.iw + this.rollGap;
            }
        }
        return lm;
    }
    
    private String getOneLine(final int n) {
        if (n >= this.nText || n < 0) {
            return null;
        }
        return this.sTextBuf.elementAt(n);
    }
    
    public boolean vTextRead(final DataInputStream dataInputStream) {
        this.sTextBuf = new Vector((this.animator == 1) ? 50 : 1, 10);
        (this.tbufInfo = new int[6])[0] = 0;
        this.tbufInfo[1] = 0;
        this.tbufInfo[4] = 0;
        int n = -1;
        String string = null;
        int n2 = 0;
        while (true) {
            ++n;
            String line;
            try {
                line = dataInputStream.readLine();
            }
            catch (IOException ex) {
                System.err.println("vTextRead: io exception " + ex.toString());
                try {
                    dataInputStream.close();
                }
                catch (IOException ex2) {}
                this.sTextBuf.removeAllElements();
                this.sTextBuf = null;
                return false;
            }
            if (line == null) {
                break;
            }
            n2 += line.length();
            if (this.animator == 1) {
                this.sTextBuf.addElement(line);
            }
            else if (n > 0) {
                string = String.valueOf(string) + " " + line;
            }
            else {
                string = line;
            }
        }
        if (this.animator != 1) {
            this.sTextBuf.addElement(string);
        }
        this.sTextBuf.trimToSize();
        try {
            dataInputStream.close();
        }
        catch (IOException ex3) {}
        final int n3 = this.sTextBuf.size() - 1;
        this.tbufInfo[2] = n3;
        this.tbufInfo[3] = n3;
        this.tbufInfo[5] = n3;
        this.nText = n3 + 1;
        boolean stringFontImg = true;
        if (n2 == 0) {
            System.err.println("textRead: empty string(s)");
            this.fontEffect = 0;
        }
        if (this.fontEffect != 0) {
            stringFontImg = this.stringFontImg();
        }
        return stringFontImg;
    }
    
    private String getTrimParam(final String s) {
        String s2 = this.getParameter(s);
        if (s2 != null) {
            s2 = s2.trim();
        }
        return s2;
    }
    
    private int hex2Int(final String s) {
        if (s == null) {
            return -3;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s.trim(), 16);
        }
        catch (NumberFormatException ex) {
            return -1;
        }
        return int1;
    }
    
    private int getHexColor(final String s, final int[] array, final int n) {
        String trim = null;
        if (s != null) {
            trim = s.trim();
        }
        if (trim == null) {
            for (int i = 0; i < 3; ++i) {
                array[i] = n;
            }
            return -1;
        }
        if (trim.length() > 6) {
            return -1;
        }
        final int hex2Int = this.hex2Int(trim);
        if (hex2Int < 0) {
            return -2;
        }
        array[0] = (hex2Int & 0xFF0000) >> 16;
        array[1] = (hex2Int & 0xFF00) >> 8;
        array[2] = (hex2Int & 0xFF);
        return 1;
    }
    
    private Point s2Int(final String s) {
        final Point point = new Point(0, 0);
        if (s == null) {
            point.y = -1;
            return point;
        }
        try {
            point.x = Integer.parseInt(s.trim());
        }
        catch (NumberFormatException ex) {
            point.x = 0;
            point.y = -2;
        }
        return point;
    }
    
    private void pr(final String s) {
        System.err.println(s);
    }
    
    private boolean backImg(final Image image) {
        int bkPicDraw = this.bkPicDraw;
        Image image2;
        if (image == null && this.frameN > 0) {
            this.offImage[0] = this.createImage(this.realAw, this.realAh);
            image2 = this.offImage[0];
            bkPicDraw = -1;
        }
        else {
            image2 = image;
        }
        final int width = image2.getWidth(null);
        final int height = image2.getHeight(null);
        if (width <= 0 || height <= 0) {
            System.err.println("backImg: bad dimension " + width + " " + height);
            return false;
        }
        final Graphics graphics = this.offImage[0].getGraphics();
        graphics.setColor(this.dark);
        graphics.fillRect(0, 0, this.realAw, this.realAh);
        final int bkImgX = this.bkImgX;
        final int bkImgY = this.bkImgY;
        if (bkPicDraw == 0) {
            graphics.drawImage(image2, -bkImgX, -bkImgY, null);
        }
        else if (bkPicDraw == 2) {
            for (int i = -bkImgY; i < this.realAh; i += height) {
                for (int j = -bkImgX; j < this.realAw; j += width) {
                    graphics.drawImage(image2, j, i, null);
                }
            }
        }
        if (this.frameN > 0) {
            this.paintFrames(graphics, this.realAw, this.realAh, 0, 0);
        }
        this.prepareImage(this.offImage[0], null);
        graphics.dispose();
        return true;
    }
    
    private int waitImg(final int n) {
        final boolean b = false;
        try {
            this.tracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            System.err.println("tracker wait interrupted");
            return 1;
        }
        if (!b) {}
        if ((this.tracker.statusID(0, false) & 0x4) != 0x0) {
            System.err.println("waitImg " + n + " loding error");
            return -1;
        }
        if ((this.tracker.statusID(0, false) & 0x1) != 0x0) {
            return 0;
        }
        if ((this.tracker.statusID(0, false) & 0x2) != 0x0) {
            System.err.println("waitImg " + n + " loading aborted.");
            return -3;
        }
        this.tracker.statusID(0, false);
        return 0;
    }
    
    private boolean stringFontImg() {
        final boolean b = this.fontEffect != 8;
        Font mFont = this.mFont;
        final int size = mFont.getSize();
        int n = 0;
        if (size < 16 && this.fontEffect == 16) {
            mFont = new Font(this.mFont.getName(), this.mFont.getStyle(), this.fontXTimes(this.applets, mFont, 2));
            n = 1;
        }
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(mFont);
        int n2 = b ? (2 + this.fontGap) : this.fontGap;
        if (n > 0) {
            n2 *= 2;
        }
        int max = 0;
        for (int i = 0; i < this.nText; ++i) {
            max = Math.max(max, this.strPaintLen((i >= this.nText || i < 0) ? null : ((String)this.sTextBuf.elementAt(i)), n2, fontMetrics));
        }
        if (max == 0) {
            this.iw = 0;
            this.ih = 0;
            this.fontEffect = 0;
            return true;
        }
        int max2 = max + 40 + 2 * fontMetrics.getMaxAdvance() + this.shadowOffset + this.fShadowOffset;
        int max3 = this.nText * fontMetrics.getHeight() + 40 + this.shadowOffset + this.fShadowOffset + fontMetrics.getMaxDecent();
        if (this.animator == 1) {
            max2 = Math.max(5, Math.min((n + 1) * this.realAw + 2, max2));
        }
        else {
            max3 = Math.max(5, Math.min((n + 1) * this.realAh + 2, max3));
        }
        final Image image = this.createImage(max2, max3);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, 0, max2, max3);
        graphics.setColor(new Color(255, 0, 0));
        graphics.setFont(mFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(mFont);
        final int height = fontMetrics2.getHeight();
        int n3 = (max3 - this.nText * height) / 2 + height;
        for (int n4 = (this.advance < 0) ? -1 : this.nText, j = (this.advance < 0) ? (this.nText - 1) : 0, n5 = (this.advance < 0) ? -1 : 1; j != n4; j += n5) {
            this.paintString(graphics, (j >= this.nText || j < 0) ? null : ((String)this.sTextBuf.elementAt(j)), this.workChar, fontMetrics2, 20, n3, n2);
            n3 += height;
        }
        if (!this.conv2Bmp(image, n + 1)) {
            return false;
        }
        if (this.fontEffect == 16) {
            this.font3DImg(n);
        }
        else if (this.fontEffect == 8) {
            this.smoothOnly();
        }
        else if ((this.fontEffect & 0x7) != 0x0) {
            this.bmpFontImg();
        }
        return true;
    }
    
    private int color2Int(final Color color) {
        return 0xFF000000 | (color.getRed() & 0xFF) << 16 | (color.getGreen() & 0xFF) << 8 | (color.getBlue() & 0xFF);
    }
    
    private int[] pixels3D(final int n, final int n2) {
        final int n3 = this.bmpInfo[0];
        final int n4 = this.bmpInfo[1];
        final int[] array = new int[n3 * n4];
        final int n5 = (this.liteColor[2] & 0xFF) | (this.liteColor[1] & 0xFF) << 8 | (this.liteColor[0] & 0xFF) << 16 | 0xFF000000;
        final int iAvColor = this.iAvColor(n, this.sDark);
        final int iAvColor2 = this.iAvColor(n2, this.sDark);
        for (int i = 0; i < n3 * n4; ++i) {
            array[i] = 0;
        }
        int n6 = 0;
        for (int j = 0; j < n4; ++j) {
            for (int k = 0; k < n3; ++k) {
                if (this.bitMap[n6] == n5 && this.bitMap[n6 - n3] != n5 && this.bitMap[n6 - n3 - 1] != n5 && this.bitMap[n6 - 1] != n5) {
                    array[n6 - n3 - 1] = iAvColor;
                }
                ++n6;
            }
        }
        int n7 = 0;
        for (int l = 0; l < n4; ++l) {
            for (int n8 = 0; n8 < n3; ++n8) {
                if (this.bitMap[n7] == n5) {
                    array[n7 - 1] = (array[n7 - n3] = n);
                }
                ++n7;
            }
        }
        int n9 = 0;
        for (int n10 = 0; n10 < n4; ++n10) {
            for (int n11 = 0; n11 < n3; ++n11) {
                if (this.bitMap[n9] == n5 && this.bitMap[n9 + n3] != n5 && this.bitMap[n9 + n3 + 1] != n5 && this.bitMap[n9 + 1] != n5) {
                    array[n9 + n3 + 1] = iAvColor2;
                }
                ++n9;
            }
        }
        int n12 = 0;
        int n13 = 1;
        for (int n14 = 0; n14 < n4; ++n14) {
            for (int n15 = 0; n15 < n3; ++n15) {
                if (this.bitMap[n12] == n5 && this.bitMap[n12 + 1] != n5) {
                    if (this.bitMap[n12 - n3 + 1] == n5) {
                        array[n13] = n2;
                    }
                    else if (this.bitMap[n12 + n3 + 1] != n5) {
                        if (this.bitMap[n12 - n3] == n5) {
                            array[n13] = n2;
                        }
                        else if (this.bitMap[n12 - n3 - 1] == n5) {
                            array[n13] = n2;
                        }
                        else {
                            array[n13] = iAvColor2;
                        }
                    }
                    else if (this.bitMap[n12 + 2] == n5) {
                        array[n13] = iAvColor;
                    }
                    else if (this.bitMap[n12 - n3] == n5) {
                        if (this.bitMap[n12 + n3 + 2] != n5) {
                            if (this.bitMap[n12 - 2 * n3] == n5) {
                                array[n13] = n2;
                            }
                            else if (this.bitMap[n12 - 2 * n3 + 1] == n5) {
                                array[n13] = n2;
                            }
                            else if (this.bitMap[n12 + 2 * n3 + 2] != n5) {
                                array[n13] = n2;
                            }
                            else if (this.bitMap[n12 + n3 + 3] == n5) {
                                array[n13] = n2;
                            }
                            else if (this.bitMap[n12 - 2 * n3 - 1] == n5) {
                                array[n13] = n2;
                            }
                            else if (this.bitMap[n12 + 3 * n3 + 3] != n5) {
                                array[n13] = n2;
                            }
                            else if (this.bitMap[n12 + 2 * n3 + 4] == n5) {
                                array[n13] = n2;
                            }
                            else if (n12 - 3 * n3 - 1 >= 0 && this.bitMap[n12 - 3 * n3 - 1] == n5) {
                                array[n13] = iAvColor2;
                            }
                        }
                    }
                    else if (this.bitMap[n12 + n3 + 2] != n5) {
                        if (this.bitMap[n12 + 2 * n3 + 1] == n5 && this.bitMap[n12 - n3 - 1] == n5 && this.bitMap[n12 - 2 * n3 - 1] == n5) {
                            array[n13] = n2;
                        }
                        else if (this.bitMap[n12 - 2 * n3 - 2] == n5) {
                            if (this.bitMap[n12 - 3 * n3 - 2] == n5) {
                                array[n13] = iAvColor2;
                            }
                        }
                        else if (this.bitMap[n12 + 2 * n3 + 2] != n5 && this.bitMap[n12 + 2 * n3 + 1] == n5 && this.bitMap[n12 - n3 - 1] != n5) {
                            array[n13] = iAvColor2;
                        }
                    }
                }
                ++n12;
                ++n13;
            }
        }
        int n16 = 0;
        int n17 = n3;
        for (int n18 = 0; n18 < n4; ++n18) {
            for (int n19 = 0; n19 < n3; ++n19) {
                if (this.bitMap[n16] == n5 && this.bitMap[n16 + n3] != n5) {
                    if (this.bitMap[n16 + n3 + 1] != n5) {
                        if (this.bitMap[n16 + 2 * n3] == n5) {
                            array[n17] = iAvColor2;
                        }
                        else {
                            array[n17] = n2;
                        }
                    }
                    else if (this.bitMap[n16 + 2 * n3 + 1] != n5) {
                        if (this.bitMap[n16 - 1] == n5 || this.bitMap[n16 + n3 - 1] == n5) {
                            array[n17] = n2;
                        }
                        else if (this.bitMap[n16 - n3 - 1] == n5) {
                            if (this.bitMap[n16 - 2 * n3 - 1] != n5 || this.bitMap[n16 + 2 * n3 + 2] != n5 || this.bitMap[n16 + 3 * n3 + 2] != n5) {
                                array[n17] = n2;
                            }
                        }
                        else if (this.bitMap[n16 + 2 * n3 + 2] == n5) {
                            if ((this.bitMap[n16 - n3] != n5 || this.bitMap[n16 + 3 * n3 + 2] != n5) && this.bitMap[n16 + 3 * n3 + 1] != n5) {
                                int n20 = n16;
                                while (n20 < n4 * n3) {
                                    if (this.bitMap[n20] != n5) {
                                        if (this.bitMap[n20] == n) {
                                            array[n17] = n2;
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        ++n20;
                                    }
                                }
                            }
                        }
                        else if (this.bitMap[n16 - n3] != n5 && this.bitMap[n16 + 2 * n3] != n5) {
                            array[n17] = iAvColor2;
                        }
                    }
                }
                ++n16;
                ++n17;
            }
        }
        for (int n21 = 0; n21 < n3 * n4; ++n21) {
            if (array[n21] == iAvColor2 && (array[n21 - n3 - 1] == n2 || array[n21 - n3 - 1] == iAvColor2) && (array[n21 + n3 + 1] == n2 || array[n21 + n3 + 1] == iAvColor2) && (this.bitMap[n21 - 1] == n5 || this.bitMap[n21 - n3] == n5)) {
                array[n21] = n2;
            }
        }
        final int n22 = n3 * n4;
        for (int n23 = 0; n23 < n22; ++n23) {
            if (this.bitMap[n23] == n5 && this.bitMap[n23 - n3 - 1] == n5 && this.bitMap[n23 + n3 + 1] == n5 && this.bitMap[n23 - 1] != n5 && this.bitMap[n23 - n3 - 2] != n5 && this.bitMap[n23 + n3] != n5 && this.bitMap[n23 + n3 - 1] != n5 && (array[n23 - 1] == n2 || array[n23 - 1] == iAvColor2)) {
                for (int n24 = n23; n24 < n22; ++n24) {
                    if (this.bitMap[n24] != n5 && (array[n24] == n2 || array[n24] == iAvColor2)) {
                        array[n23 - 1] = n;
                        break;
                    }
                }
            }
        }
        for (int n25 = 0; n25 < n22; ++n25) {
            if (this.bitMap[n25] == n5 && this.bitMap[n25 - n3 - 1] == n5 && this.bitMap[n25 + n3 + 1] == n5 && this.bitMap[n25 + 1] != n5 && this.bitMap[n25 + n3 + 2] != n5 && this.bitMap[n25 - n3 + 1] != n5 && (array[n25 + 1] == n || array[n25 + 1] == iAvColor)) {
                for (int n26 = n25; n26 > 0; --n26) {
                    if (this.bitMap[n26] != n5 && (array[n26] == n || array[n26] == iAvColor)) {
                        array[n25 + 1] = n2;
                        break;
                    }
                }
            }
        }
        for (int n27 = 0; n27 < n3 * n4; ++n27) {
            if (array[n27] == iAvColor2 && (array[n27 - n3 - 1] == n2 || array[n27 - n3 - 1] == iAvColor2) && (array[n27 + n3 + 1] == n2 || array[n27 + n3 + 1] == iAvColor2) && (this.bitMap[n27 - 1] == n5 || this.bitMap[n27 - n3] == n5)) {
                array[n27] = n2;
            }
        }
        for (int n28 = 0; n28 < n3 * n4; ++n28) {
            if (this.bitMap[n28] == n5) {
                array[n28] = n5;
            }
        }
        return array;
    }
    
    private void smoothing(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (n8 < 1) {
            return;
        }
        if (n5 > 0 && n3 != n4) {
            for (int i = 0; i < n * n2; ++i) {
                if (array[i] == n3) {
                    array[i] = n4;
                }
            }
        }
        int n9 = 0;
        final int n10 = n7 - n6;
        int n11 = n8 * (n + 1);
        for (int j = n8; j < n2 - n8; ++j) {
            for (int k = n8; k < n - n8; ++k) {
                final int n12 = n11 - n;
                final int n13 = n11 + n;
                boolean b = n5 == 0 || (n5 > 0 && array[n11] != n4);
                if (n5 == 1 && array[n11] == n4 && (array[n11 - 1] != n4 || array[n11 + 1] != n4 || array[n12] != n4 || array[n12 - 1] != n4 || array[n12 + 1] != n4 || array[n13] != n4 || array[n13 + 1] != n4 || array[n13 - 1] != n4)) {
                    b = true;
                }
                if (b) {
                    array2[n9] = (0xFF000000 | ((n6 * (array[n11] & 0xFF0000) >> 6) + n10 * ((array[n11 + 1] & 0xFF0000) + (array[n11 - 1] & 0xFF0000) + (array[n12 + 1] & 0xFF0000) + (array[n12] & 0xFF0000) + (array[n12 - 1] & 0xFF0000) + (array[n13 + 1] & 0xFF0000) + (array[n13] & 0xFF0000) + (array[n13 - 1] & 0xFF0000) >> 9) & 0xFF0000) | ((n6 * (array[n11] & 0xFF00) >> 6) + (n10 * ((array[n11 + 1] & 0xFF00) + (array[n11 - 1] & 0xFF00) + (array[n12 - 1] & 0xFF00) + (array[n12] & 0xFF00) + (array[n12 + 1] & 0xFF00) + (array[n13 - 1] & 0xFF00) + (array[n13] & 0xFF00) + (array[n13 + 1] & 0xFF00)) >> 9) & 0xFF00) | ((n6 * (array[j * n + k] & 0xFF) >> 6) + (n10 * ((array[n11 - 1] & 0xFF) + (array[n11 + 1] & 0xFF) + (array[n12 + 1] & 0xFF) + (array[n12] & 0xFF) + (array[n12 - 1] & 0xFF) + (array[n13 + 1] & 0xFF) + (array[n13] & 0xFF) + (array[n13 - 1] & 0xFF)) >> 9) & 0xFF));
                }
                ++n9;
                ++n11;
            }
            n11 += 2 * n8;
        }
    }
    
    public void reduce4(final int[] array, final int n, final int n2, final int[] array2, final int[] array3, final int n3, final int n4) {
        for (int i = 0; i < n * n2; ++i) {
            if (array[i] == 0) {
                array[i] = n4;
            }
        }
        final int n5 = (n3 == 0) ? (n2 >> 1) : (n2 - 2 * n3 + 1 >> 1);
        final int n6 = (n3 == 0) ? (n >> 1) : (n - 2 * n3 + 1 >> 1);
        array3[0] = n6;
        array3[1] = n5;
        int n7 = n3 * (n + 1);
        int n8 = 0;
        for (int j = 0; j < n5; ++j) {
            for (int k = 0; k < n6; ++k) {
                final int n9 = n7 + n;
                if (array[n7] != n4 || array[n7 + 1] != n4 || array[n9] != n4 || array[n9 + 1] != n4) {
                    array2[n8++] = (0xFF000000 | ((array[n7] & 0xFF0000) + (array[n7 + 1] & 0xFF0000) + (array[n9] & 0xFF0000) + (array[n9 + 1] & 0xFF0000) >> 2 & 0xFF0000) | ((array[n7] & 0xFF00) + (array[n7 + 1] & 0xFF00) + (array[n9] & 0xFF00) + (array[n9 + 1] & 0xFF00) >> 2 & 0xFF00) | (array[n7] & 0xFF) + (array[n7 + 1] & 0xFF) + (array[n9] & 0xFF) + (array[n9 + 1] & 0xFF) >> 2);
                }
                else {
                    array2[n8++] = 0;
                }
                n7 += 2;
            }
            n7 += n - n6 << 1;
        }
    }
    
    private int iAvColor(final int n, final Color color) {
        if (color == null) {
            return n;
        }
        return 0xFF000000 | ((((n & 0xFF0000) >> 16) + color.getRed()) / 2 & 0xFF) << 16 | ((((n & 0xFF00) >> 8) + color.getGreen()) / 2 & 0xFF) << 8 | (((n & 0xFF) + color.getBlue()) / 2 & 0xFF);
    }
    
    private boolean font3DImg(final int n) {
        final int n2 = this.bmpInfo[0];
        final int n3 = this.bmpInfo[1];
        if (n2 <= 0 || n3 <= 0) {
            return false;
        }
        final int[] imgTrimInfo = this.imgTrimInfo(2, (this.liteColor[2] & 0xFF) | (this.liteColor[1] & 0xFF) << 8 | (this.liteColor[0] & 0xFF) << 16 | 0xFF000000);
        if (imgTrimInfo[0] == n2) {
            final int min = Math.min(n2, 10);
            final int min2 = Math.min(n3, 10);
            this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(min, min2, this.bitMap, 0, min)), null);
            this.iw = min;
            this.ih = min2;
            return true;
        }
        final int[] bitMap = this.bitMap;
        final int n4 = n2 - imgTrimInfo[0] - imgTrimInfo[1] + 6;
        final int n5 = n3 - imgTrimInfo[2] - imgTrimInfo[3] + 6;
        this.bitMap1 = new int[n4 * n5];
        for (int i = 0; i < n4 * n5; ++i) {
            this.bitMap1[i] = 0;
        }
        this.copyIArray(this.bitMap, n2, n3 - imgTrimInfo[3], imgTrimInfo[0], n2 - imgTrimInfo[1], imgTrimInfo[2], this.bitMap1, n4, 3, 3);
        this.bitMap = this.bitMap1;
        this.bmpInfo[0] = n4;
        this.bmpInfo[1] = n5;
        this.bitMap1 = this.pixels3D(0xFF000000 | (this.fShadowColor.getRed() & 0xFF) << 16 | (this.fShadowColor.getGreen() & 0xFF) << 8 | (this.fShadowColor.getBlue() & 0xFF), 0xFF000000 | (this.shadowColor.getRed() & 0xFF) << 16 | (this.shadowColor.getGreen() & 0xFF) << 8 | (this.shadowColor.getBlue() & 0xFF));
        final int n6 = (this.f3D1 > 0) ? 2 : 1;
        final int n7 = n2 * n3;
        int iw = n2 - imgTrimInfo[0] - imgTrimInfo[1] + 2 * n6;
        int ih = n3 - imgTrimInfo[2] - imgTrimInfo[3] + 2 * n6;
        this.bitMap = bitMap;
        if (n7 < n4 * n5) {
            this.bitMap = new int[iw * ih];
        }
        for (int n8 = iw * ih, j = 0; j < n8; ++j) {
            this.bitMap[j] = 0;
        }
        final int n9 = 3 - n6;
        if ((this.f3D1 != 0 || n > 0) && Math.abs(this.f3D1) < Math.abs(this.f3D2)) {
            final int n10 = 0xFF000000 | (this.sDark.getRed() & 0xFF) << 16 | (this.sDark.getGreen() & 0xFF) << 8 | (this.sDark.getBlue() & 0xFF);
            final int n11 = (this.f3D1 > 0) ? 1 : 2;
            if (n == 0) {
                this.smoothing(this.bitMap1, this.bitMap, n4, n5, 0, n10, n11, Math.abs(this.f3D1), this.f3D2, n9);
            }
            else {
                this.reduce4(this.bitMap1, n4, n5, this.bitMap, this.bmpInfo, 2, n10);
                iw = this.bmpInfo[0];
                ih = this.bmpInfo[1];
            }
        }
        else {
            final int n12 = n9;
            this.copyIArray(this.bitMap1, n4, n5 - n12, n12, n4 - n12, n12, this.bitMap, iw, 0, 0);
        }
        this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(iw, ih, this.bitMap, 0, iw)), null);
        this.iw = iw;
        this.ih = ih;
        this.bmpInfo[0] = iw;
        this.bmpInfo[1] = ih;
        return true;
    }
    
    private void copyIArray(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int[] array2, final int n6, final int n7, final int n8) {
        final int n9 = n8 - n5;
        for (int i = n5; i < n2; ++i) {
            final int n10 = i * n;
            int n11 = (i + n9) * n6 + n7;
            for (int j = n3; j < n4; ++j) {
                array2[n11++] = array[n10 + j];
            }
        }
    }
    
    private int[] imgTrimInfo(final int n, final int n2) {
        final int n3 = this.bmpInfo[0];
        final int n4 = this.bmpInfo[1];
        final int[] array = new int[4];
        final int n5 = this.bitMap[0];
        array[1] = (array[0] = n3);
        array[2] = 0;
        int n6 = 0;
        for (int i = 0; i < n4; ++i) {
            final int n7 = i * n3;
            int n8 = n3;
            int min = n3;
            for (int j = 0; j < n3; ++j) {
                if (this.bitMap[n7 + j] != n5) {
                    n6 = i + 1;
                    if (n8 == n3) {
                        n8 = j;
                    }
                    min = n3;
                    if (n == 2) {
                        this.bitMap[n7 + j] = n2;
                    }
                }
                else {
                    min = Math.min(min, j);
                    if (n > 0) {
                        this.bitMap[n7 + j] = 0;
                    }
                }
            }
            if (n6 == 0) {
                final int[] array2 = array;
                final int n9 = 2;
                ++array2[n9];
            }
            array[0] = Math.min(n8, array[0]);
            array[1] = Math.min(n3 - min, array[1]);
        }
        array[3] = ((n6 == 0) ? 0 : (n4 - n6));
        return array;
    }
    
    private boolean bmpFontImg() {
        int iw = this.bmpInfo[0];
        int ih = this.bmpInfo[1];
        int[] array = { 0 };
        if (iw <= 0 || ih <= 0) {
            return false;
        }
        final int n = (this.liteColor[2] & 0xFF) | (this.liteColor[1] & 0xFF) << 8 | (this.liteColor[0] & 0xFF) << 16 | 0xFF000000;
        final int n2 = this.bitMap[0];
        int n3 = 0;
        int iw2 = 0;
        int ih2 = 0;
        final int[] imgTrimInfo = this.imgTrimInfo(((this.fontEffect & 0x300) != 0x0) ? 1 : 2, n);
        if (imgTrimInfo[0] == iw || this.fontEffect == 256) {
            final int min = Math.min(iw, 10);
            final int min2 = Math.min(ih, 10);
            this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(min, min2, this.bitMap, 0, min)), null);
            this.iw = min;
            this.ih = min2;
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (imgTrimInfo[i] > 0) {
                n3 = 1;
            }
        }
        if (n3 > 0) {
            final int n4 = ih - imgTrimInfo[3];
            this.bmpInfo[1] = n4;
            iw2 = iw - imgTrimInfo[0] - imgTrimInfo[1];
            ih2 = n4 - imgTrimInfo[2];
            this.bitMap1 = new int[iw2 * ih2];
            this.copyIArray(this.bitMap, iw, n4, imgTrimInfo[0], iw - imgTrimInfo[1], imgTrimInfo[2], this.bitMap1, iw2, 0, 0);
            this.bitMap = this.bitMap1;
            this.bmpInfo[0] = iw2;
            this.bmpInfo[1] = ih2;
            iw = iw2;
            ih = ih2;
        }
        final int n5 = 0xFF000000 | (this.fShadowColor.getRed() & 0xFF) << 16 | (this.fShadowColor.getGreen() & 0xFF) << 8 | (this.fShadowColor.getBlue() & 0xFF);
        if ((this.fontEffect & 0x7) != 0x0) {
            final int iw3 = iw + 2;
            final int ih3 = ih + 2;
            final int[] array2 = new int[iw3 * ih3];
            for (int j = 0; j < iw3 * ih3; ++j) {
                array2[j] = 0;
            }
            int n6 = iw3;
            int n7 = 0;
            for (int k = 0; k < ih; ++k) {
                for (int l = 0; l < iw; ++l) {
                    if (this.bitMap[n7++] != 0) {
                        array2[n6 + l] = (array2[n6 + l + 2] = n5);
                        array2[n6 + l - iw3 + 1] = (array2[n6 + l - iw3] = n5);
                        array2[n6 + l + iw3] = (array2[n6 + l - iw3 + 2] = n5);
                        array2[n6 + l + iw3 + 2] = (array2[n6 + l + iw3 + 1] = n5);
                    }
                }
                n6 += iw3;
            }
            int n8 = iw3 + 1;
            int n9 = 0;
            for (int n10 = 0; n10 < ih; ++n10) {
                for (int n11 = 0; n11 < iw; ++n11) {
                    if (this.bitMap[n9] != 0) {
                        array2[n8 + n11] = ((this.fontEffect == 7) ? 0 : this.bitMap[n9]);
                    }
                    ++n9;
                }
                n8 += iw3;
            }
            this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(iw3, ih3, array2, 0, iw3)), null);
            this.iw = iw3;
            this.ih = ih3;
            return true;
        }
        if ((this.shadowOffset > 0 || this.fShadowOffset > 0) && this.fontEffect == 0) {
            iw2 = iw + this.shadowOffset + this.fShadowOffset;
            ih2 = ih + this.shadowOffset + this.fShadowOffset;
            array = new int[iw2 * ih2];
            for (int n12 = 0; n12 < iw2 * ih2; ++n12) {
                array[n12] = 0;
            }
        }
        int n13 = 0;
        if (this.fShadowOffset > 0 && this.fontEffect == 0) {
            for (int n14 = 0; n14 < ih; ++n14) {
                final int n15 = n14 * iw2;
                for (int n16 = 0; n16 < iw; ++n16) {
                    if (this.bitMap[n13++] == n) {
                        array[n15 + n16] = n5;
                    }
                }
            }
        }
        if (this.shadowOffset > 0 && this.fontEffect == 0) {
            int n17 = 0;
            final int n18 = 0xFF000000 | (this.shadowColor.getRed() & 0xFF) << 16 | (this.shadowColor.getGreen() & 0xFF) << 8 | (this.shadowColor.getBlue() & 0xFF);
            for (int n19 = this.shadowOffset + this.fShadowOffset; n19 < ih2; ++n19) {
                final int n20 = n19 * iw2;
                for (int n21 = this.shadowOffset + this.fShadowOffset; n21 < iw2; ++n21) {
                    if (this.bitMap[n17++] != 0) {
                        array[n20 + n21] = n18;
                    }
                }
            }
        }
        if ((this.fShadowOffset > 0 || this.shadowOffset > 0) && this.fontEffect == 0) {
            int n22 = 0;
            for (int fShadowOffset = this.fShadowOffset; fShadowOffset < ih + this.fShadowOffset; ++fShadowOffset) {
                final int n23 = fShadowOffset * iw2;
                for (int fShadowOffset2 = this.fShadowOffset; fShadowOffset2 < iw + this.fShadowOffset; ++fShadowOffset2) {
                    if (this.bitMap[n22++] != 0) {
                        array[n23 + fShadowOffset2] = n;
                    }
                }
            }
        }
        if ((this.shadowOffset > 0 || this.fShadowOffset > 0) && this.fontEffect == 0) {
            this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(iw2, ih2, array, 0, iw2)), null);
            this.iw = iw2;
            this.ih = ih2;
            return true;
        }
        this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(iw, ih, this.bitMap, 0, iw)), null);
        this.iw = iw;
        this.ih = ih;
        return true;
    }
    
    public int fontXTimes(final Applet applet, final Font font, final int n) {
        final Graphics graphics = applet.getGraphics();
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int n2 = fontMetrics.getAscent() + fontMetrics.getDescent();
        final int size = font.getSize();
        if (n <= 1) {
            return size;
        }
        int n3 = n2;
        Font font2 = font;
        for (int i = size; i <= 72; i += 2) {
            font2 = new Font(font.getName(), font.getStyle(), i);
            final FontMetrics fontMetrics2 = graphics.getFontMetrics(font2);
            final int n4 = fontMetrics2.getAscent() + fontMetrics2.getDescent();
            if (n4 >= n * n2 && n3 <= n * n2) {
                return font2.getSize();
            }
            n3 = n4;
        }
        return font2.getSize();
    }
    
    public int wordIndex(final String s, final int n, final int n2) {
        if (n <= 0) {
            return -1;
        }
        if (s == null) {
            return -1;
        }
        if (n2 < 0) {
            return -1;
        }
        int n3 = n2 - 1;
        int i = 0;
        while (i < n) {
            char char1;
            do {
                ++n3;
                try {
                    char1 = s.charAt(n3);
                }
                catch (StringIndexOutOfBoundsException ex) {
                    return -(i + 1);
                }
            } while (char1 == ' ');
            if (++i < n) {
                char char2;
                do {
                    ++n3;
                    try {
                        char2 = s.charAt(n3);
                    }
                    catch (StringIndexOutOfBoundsException ex2) {
                        return -(i + 1);
                    }
                } while (char2 != ' ');
            }
        }
        return n3;
    }
    
    public boolean startEqual(final String s, final String s2) {
        return s.startsWith(String.valueOf(s2) + " ") || s.equals(s2);
    }
    
    public double[] paramDouble(final String s, final int n) {
        final double[] array = { 0.0, 0.0 };
        if (s == null) {
            array[0] = 10.0;
            return array;
        }
        new Point(0, 0);
        final int wordIndex = this.wordIndex(s, n, 0);
        if (wordIndex < 0) {
            return array;
        }
        final int wordIndex2 = this.wordIndex(s, 2, wordIndex);
        if (wordIndex2 < wordIndex + 2 && wordIndex2 != -2) {
            return array;
        }
        final int n2 = (wordIndex2 < 0) ? s.length() : wordIndex2;
        try {
            array[1] = Double.valueOf(s.substring(wordIndex, n2).trim());
        }
        catch (NumberFormatException ex) {
            array[0] = 10.0;
        }
        return array;
    }
    
    public Point paramInt(final String s, final int n, final int n2) {
        final Point point = new Point(0, -1);
        if (s == null) {
            return point;
        }
        final int wordIndex = this.wordIndex(s, n, 0);
        if (wordIndex < 0) {
            return point;
        }
        final int wordIndex2 = this.wordIndex(s, 2, wordIndex);
        if (wordIndex2 < wordIndex + 2 && wordIndex2 != -2) {
            return point;
        }
        final Point s2Int = this.s2Int(this.aSubString(s, wordIndex, ((wordIndex2 < 0) ? 0 : wordIndex2) - wordIndex));
        if ((n2 == 1 && s2Int.x < 0) || (n2 == 2 && s2Int.x <= 0)) {
            s2Int.y = -1;
        }
        return s2Int;
    }
    
    public int emptyString(final String s) {
        if (s == null) {
            return -1;
        }
        return s.length();
    }
    
    public String paramString(final String s, final int n) {
        if (s == null) {
            return null;
        }
        final int n2 = (n == 1) ? 0 : this.wordIndex(s, n, 0);
        if (n2 < 0) {
            return null;
        }
        final int wordIndex = this.wordIndex(s, n + 1, 0);
        if (wordIndex <= n2) {
            return this.aSubString(s, n2, -1);
        }
        return this.aSubString(s, n2, wordIndex - n2);
    }
    
    public String aSubString(final String s, final int n, final int n2) {
        if (s == null) {
            return null;
        }
        if (n2 <= 0) {
            try {
                return s.substring(n);
            }
            catch (StringIndexOutOfBoundsException ex) {
                return null;
            }
        }
        String s2;
        try {
            s2 = s.substring(n, n + n2);
        }
        catch (StringIndexOutOfBoundsException ex2) {
            return null;
        }
        return s2;
    }
    
    public int bytes2Int(final byte[] array, final int n, final int n2) {
        int n3 = 0;
        for (int i = n + n2 - 1; i >= n; --i) {
            n3 = n3 * 256 + ((array[i] & 0xF) + (array[i] >> 4 & 0xF) * 16);
        }
        return n3;
    }
    
    private Point readBytes(final DataInputStream dataInputStream, final int x, final byte[] array) {
        final Point point = new Point(0, 1);
        if (x <= 0) {
            point.x = x;
            point.y = -2;
            return point;
        }
        int i = 0;
        int x2 = 0;
        while (i >= 0) {
            try {
                i = dataInputStream.read(array, x2, x - x2);
            }
            catch (IOException ex) {
                System.err.println("IO error in readBytes " + x2 + " " + x);
                point.x = x2;
                point.y = -1;
                return point;
            }
            if (i < 0) {
                point.y = 0;
                point.x = x2;
                return point;
            }
            x2 += i;
            if (x2 >= x) {
                point.y = ((x2 > x) ? 2 : 1);
                point.x = x2;
                return point;
            }
        }
        return point;
    }
    
    private int decodeDIB(final DataInputStream dataInputStream, final int n) {
        final byte[] array = new byte[14];
        final Point bytes = this.readBytes(dataInputStream, 14, array);
        if (bytes.y != 1) {
            System.err.println("missing data h1: " + bytes.x + " " + bytes.y);
            return -1;
        }
        if (array[0] != 66 || array[1] != 77) {
            System.err.println("decodeDib: wrong file:  " + array[0] + " " + array[1]);
            return -2;
        }
        final int bytes2Int = this.bytes2Int(array, 10, 4);
        if (bytes2Int < 26) {
            System.err.println("decodeDib: wrong offset: " + bytes2Int);
            return -2;
        }
        final byte[] array2 = new byte[bytes2Int - 14];
        final Point bytes2 = this.readBytes(dataInputStream, 12, array2);
        if (bytes2.y != 1) {
            System.err.println("missing data h2: " + bytes2.x + " " + bytes2.y);
            return -1;
        }
        final int bytes2Int2 = this.bytes2Int(array2, 0, 4);
        if (bytes2Int2 < 12 || bytes2Int2 + 14 > bytes2Int) {
            System.err.println("corrupted bmp file: " + bytes2Int2 + " " + bytes2Int);
            return -1;
        }
        final int bytes2Int3 = this.bytes2Int(array2, 4, 4);
        final int bytes2Int4 = this.bytes2Int(array2, 8, 4);
        if (bytes2Int3 <= 0 || bytes2Int4 <= 0) {
            System.err.println("decodeDIB: Wrong dimension: " + bytes2Int3 + " " + bytes2Int4);
            return -2;
        }
        final int n2 = bytes2Int2 - 12;
        if (n2 > 0) {
            final Point bytes3 = this.readBytes(dataInputStream, n2, array2);
            if (bytes3.y != 1) {
                System.err.println("missing data hinfo: " + bytes3.x + " " + bytes3.y);
                return -1;
            }
        }
        final int n3;
        if ((n3 = ((n2 > 7) ? this.bytes2Int(array2, 4, 4) : 0)) != 0) {
            System.err.println("Compressed file not supported.");
            return -2;
        }
        int n4 = (n2 > 3) ? this.bytes2Int(array2, 2, 2) : 0;
        final int n5 = bytes2Int - 14 - bytes2Int2;
        final int n6 = (bytes2Int2 != 12) ? (n5 / 4) : (n5 / 3);
        if (bytes2Int2 == 12) {
            switch (n6) {
                case 0: {
                    n4 = 24;
                    break;
                }
                case 2: {
                    n4 = 1;
                    break;
                }
                case 16: {
                    n4 = 4;
                    break;
                }
                case 256: {
                    n4 = 8;
                    break;
                }
                default: {
                    n4 = ((n6 > 256) ? 8 : -1);
                    break;
                }
            }
        }
        if (n4 != 1 && n4 != 4 && n4 != 8 && n4 != 24) {
            System.err.println("Wrong bitsPerPixel: " + n4);
            return -2;
        }
        if ((n6 == 0 && n4 != 24) || n6 < 0) {
            System.err.println("missing color table: " + bytes2Int + " " + bytes2Int2 + " " + n6);
            return -4;
        }
        if (n5 > 0) {
            final Point bytes4 = this.readBytes(dataInputStream, n5, array2);
            if (bytes4.y != 1) {
                System.err.println("missing data at color: " + bytes4.x + " " + bytes4.y);
                return -1;
            }
        }
        (this.bmpInfo = new int[4 + 3 * n6])[0] = bytes2Int3;
        this.bmpInfo[1] = bytes2Int4;
        this.bmpInfo[2] = n4;
        this.bmpInfo[3] = n6;
        int n7 = 4;
        for (int i = 0; i < ((bytes2Int2 == 12) ? (n6 * 3) : (n6 * 4)); ++i) {
            this.bmpInfo[n7++] = this.bytes2Int(array2, i + 2, 1);
            this.bmpInfo[n7++] = this.bytes2Int(array2, i + 1, 1);
            this.bmpInfo[n7++] = this.bytes2Int(array2, i, 1);
            i += 3;
            if (bytes2Int2 != 12) {}
        }
        int n8;
        if (n4 < 8) {
            n8 = bytes2Int3 * n4 / 8;
            if (bytes2Int3 % (8 / n4) != 0) {
                ++n8;
            }
        }
        else {
            n8 = bytes2Int3 * n4 / 8;
        }
        int n9 = n8;
        if ((n8 & 0x3) > 0) {
            n9 += 4 - (n8 & 0x3);
        }
        if ((n & 0x1) == 0x0) {
            this.bitMapb = new byte[n9 * bytes2Int4];
            final Point bytes5 = this.readBytes(dataInputStream, n9 * bytes2Int4, this.bitMapb);
            if (bytes5.y != 1) {
                System.err.println("Wrong file size: " + bytes5.x + " " + bytes5.y);
                return -5;
            }
            final Point bytes6 = this.readBytes(dataInputStream, 1, new byte[2]);
            if (bytes6.y != 0) {
                System.err.println("too many data: " + bytes6.y + " " + bytes6.x);
                return -6;
            }
            return 0;
        }
        else {
            this.bitMap = new int[bytes2Int3 * bytes2Int4];
            final byte[] array3 = new byte[n9];
            for (int j = bytes2Int4; j > 0; --j) {
                final Point bytes7 = this.readBytes(dataInputStream, n9, array3);
                if (bytes7.y != 1) {
                    System.err.println("missing data last: " + bytes7.x + " " + bytes7.y + " " + j);
                    return -1;
                }
                int k = (j - 1) * bytes2Int3;
                if (n4 < 8) {
                    int n10 = 0;
                    final int n11 = (n4 == 1) ? 7 : 4;
                    final int n12 = (n4 == 1) ? 1 : 15;
                    int n13 = n11;
                    while (k < j * bytes2Int3) {
                        final int n14 = array3[n10] >> n13 & n12;
                        if (n14 >= n6) {
                            System.err.println("Color index off: " + n14 + " " + n6 + " " + n10 + " " + j);
                            return -2;
                        }
                        final int n15 = 3 * n14 + 4;
                        n13 -= n4;
                        if (n13 < 0) {
                            ++n10;
                            n13 = n11;
                        }
                        this.bitMap[k++] = (0xFF000000 | (this.bmpInfo[n15] & 0xFF) << 16 | (this.bmpInfo[n15 + 1] & 0xFF) << 8 | (this.bmpInfo[n15 + 2] & 0xFF));
                    }
                }
                else if (n4 == 8) {
                    for (int l = 0; l < bytes2Int3; ++l) {
                        final int bytes2Int5 = this.bytes2Int(array3, l, 1);
                        if (bytes2Int5 >= n6) {
                            System.err.println("Color index off: " + bytes2Int5 + " " + n6 + " " + l + " " + j);
                            return -2;
                        }
                        final int n16 = bytes2Int5 * 3 + 4;
                        this.bitMap[k++] = (0xFF000000 | (this.bmpInfo[n16] & 0xFF) << 16 | (this.bmpInfo[n16 + 1] & 0xFF) << 8 | (this.bmpInfo[n16 + 2] & 0xFF));
                    }
                }
                else {
                    for (int n17 = 0; n17 < 3 * bytes2Int3; n17 += 3) {
                        this.bitMap[k++] = (0xFF000000 | (this.bytes2Int(array3, n17 + 2, 1) & 0xFF) << 16 | (this.bytes2Int(array3, n17 + 1, 1) & 0xFF) << 8 | (this.bytes2Int(array3, n17, 1) & 0xFF));
                    }
                }
            }
            final Point bytes8 = this.readBytes(dataInputStream, 1, array3);
            if (bytes8.y != 0) {
                System.err.println("too many data: " + bytes8.y + " " + bytes8.x);
                return -6;
            }
            return 0;
        }
    }
    
    private DataInputStream getBytesFile(final String s) {
        URL url;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.err.println("getBytesFile: malformed URL " + s);
            return null;
        }
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(url.openStream());
        }
        catch (IOException ex2) {
            System.err.println("getBytesFile: IO Exception");
            return null;
        }
        return dataInputStream;
    }
    
    private boolean conv2Bmp(final Image image, final int n) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (width <= 0 || height <= 0) {
            System.err.println("conv2Bmp, src dimension error: " + width + " " + height);
            return false;
        }
        (this.bmpInfo = new int[4])[0] = width;
        this.bmpInfo[1] = height;
        this.bitMap = new int[width * height];
        final int n2 = (this.animator == 1) ? Math.min(n * this.realAw + 1, 2000) : 2000;
        int n3 = Math.min(250, 500000 / n2);
        if (this.animator != 1) {
            n3 = Math.min(n * this.realAh + 1, n3);
        }
        final int n4 = (n3 + 10) * (n2 + 10);
        int n5 = width;
        int n6 = height;
        int[] bitMap = this.bitMap;
        if (width * height > n4) {
            if (width > n2 + 10) {
                bitMap = new int[n4];
            }
            if (height > n3 + 10) {
                n6 = n3;
            }
        }
        int n7 = 0;
        for (int i = 0; i < height; i += n6) {
            if (width > n2 + 10 && width * height > n4) {
                n5 = n2;
            }
            else {
                n7 = i * width;
            }
            if (height <= n3 + 10 + i) {
                n6 = height - i;
            }
            for (int j = 0; j < width; j += n5) {
                if (width <= n2 + 10 + j) {
                    n5 = width - j;
                }
                final PixelGrabber pixelGrabber = new PixelGrabber(image, j, i, n5, n6, bitMap, n7, n5);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex) {
                    System.err.println("conv2Bmp: interrupted!");
                    return false;
                }
                if ((pixelGrabber.status() & 0x80) != 0x0) {
                    System.err.println("conv2Bmp: aborted or errored");
                    return false;
                }
                if (width > n2 + 10 && width * height > n4) {
                    this.copyIArray(bitMap, n5, n6, 0, n5, 0, this.bitMap, width, j, i);
                }
            }
        }
        return true;
    }
    
    private boolean isBMP(final String s) {
        if (s == null) {
            return false;
        }
        final int length = s.length();
        String substring;
        try {
            substring = s.substring(length - 3, length);
        }
        catch (StringIndexOutOfBoundsException ex) {
            return false;
        }
        return substring.equalsIgnoreCase("bmp");
    }
    
    private boolean makeImgs() {
        if (this.fontEffect != 0 && this.frontPic == 0 && this.vTextFile == null && !this.stringFontImg()) {
            return false;
        }
        if (this.backPic == 0 && this.frontPic == 0 && this.vTextFile == null) {
            if (this.frameN > 0) {
                this.backImg(null);
                this.backPic = 1;
            }
            return true;
        }
        if (this.backPic == 2) {
            final int decodeDIB = this.decodeDIB(this.bmpIs, 1);
            if (decodeDIB < 0) {
                System.err.println("run: decodeDIB file error: " + decodeDIB);
                return false;
            }
            this.prepareImage(this.bkImg = this.createImage(new MemoryImageSource(this.bmpInfo[0], this.bmpInfo[1], this.bitMap, 0, this.bmpInfo[0])), null);
            if (!this.backImg(this.bkImg)) {
                System.err.println("run: bad backImg");
                return false;
            }
            if (this.frontPic == 3) {
                this.frontImg = this.bkImg;
                this.iw = this.bmpInfo[0];
                this.ih = this.bmpInfo[1];
            }
            if (this.frontPic == 4) {
                if (this.f3D2 > 0) {
                    if (!this.font3DImg(0)) {
                        return false;
                    }
                }
                else if (this.f3D2 < 0) {
                    this.smoothOnly();
                }
                else if (!this.bmpFontImg()) {
                    System.err.println("run: bmp file error");
                    return false;
                }
            }
            if (this.frontPic != 4) {
                this.bmpInfo = null;
                this.bitMap = null;
            }
        }
        if (this.frontPic < -2 || (this.frontPic > 2 && this.backPic == 0)) {
            if (this.frontPic > 0) {
                this.bmpIsF = this.bmpIs;
            }
            final int decodeDIB2 = this.decodeDIB(this.bmpIsF, 1);
            if (decodeDIB2 < 0) {
                System.err.println("run: decodeDIB file (front) error: " + decodeDIB2);
                return false;
            }
            if (Math.abs(this.frontPic) == 3) {
                this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(this.bmpInfo[0], this.bmpInfo[1], this.bitMap, 0, this.bmpInfo[0])), null);
                this.iw = this.bmpInfo[0];
                this.ih = this.bmpInfo[1];
            }
            if (Math.abs(this.frontPic) == 4) {
                if (this.f3D2 == 0) {
                    if (!this.bmpFontImg()) {
                        return false;
                    }
                }
                else if (this.f3D2 > 0) {
                    if (!this.font3DImg(0)) {
                        return false;
                    }
                }
                else {
                    this.smoothOnly();
                }
            }
        }
        if (this.backPic == 1 || (this.frontPic != 0 && Math.abs(this.frontPic) < 3)) {
            if (this.backPic == 1 && this.frontPic > 0) {
                this.frontImg = this.bkImg;
            }
            if (Math.abs(this.frontPic) == 1) {
                this.iw = this.frontImg.getWidth(null);
                this.ih = this.frontImg.getHeight(null);
            }
            if (Math.abs(this.frontPic) == 2) {
                if (!this.conv2Bmp(this.frontImg, 1)) {
                    System.err.println("run: bad frontImg/font");
                    return false;
                }
                if (this.f3D2 > 0) {
                    if (!this.font3DImg(0)) {
                        return false;
                    }
                }
                else if (this.f3D2 < 0) {
                    this.smoothOnly();
                }
                else if (!this.bmpFontImg()) {
                    System.err.println("run: bmp file error");
                    return false;
                }
            }
            if (this.backPic == 1 && !this.backImg(this.bkImg)) {
                System.err.println("run: bad backImg");
                return false;
            }
        }
        if (this.vTextBr != null && !this.vTextRead(this.vTextBr)) {
            return false;
        }
        if (this.backPic == 0 && this.frameN > 0) {
            this.backImg(null);
            this.backPic = 1;
        }
        return true;
    }
    
    private boolean sweeping() {
        if (this.bmpIs != null && this.bmpIs != this.bmpIsF) {
            try {
                this.bmpIs.close();
            }
            catch (IOException ex) {}
        }
        if (this.bmpIsF != null) {
            try {
                this.bmpIsF.close();
            }
            catch (IOException ex2) {}
        }
        this.bitMap = null;
        this.bmpInfo = null;
        this.bitMap1 = null;
        this.bitMapb = null;
        this.bkImg = null;
        System.gc();
        return true;
    }
    
    private boolean smoothOnly() {
        final int n = this.bmpInfo[0];
        final int n2 = this.bmpInfo[1];
        if (n <= 0 || n2 <= 0) {
            return false;
        }
        final int n3 = (this.liteColor[2] & 0xFF) | (this.liteColor[1] & 0xFF) << 8 | (this.liteColor[0] & 0xFF) << 16 | 0xFF000000;
        final int n4 = (this.f3D1 > 0) ? 2 : 1;
        final int[] imgTrimInfo = this.imgTrimInfo(2, n3);
        if (imgTrimInfo[0] == n) {
            final int min = Math.min(n, 10);
            final int min2 = Math.min(n2, 10);
            this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(min, min2, this.bitMap, 0, min)), null);
            this.iw = min;
            this.ih = min2;
            return true;
        }
        final int n5 = n - imgTrimInfo[0] - imgTrimInfo[1] + 2 * n4;
        final int n6 = n2 - imgTrimInfo[2] - imgTrimInfo[3] + 2 * n4;
        final int[] array = new int[n5 * n6];
        int n7 = 0;
        final int n8 = (this.sDark.getBlue() & 0xFF) | (this.sDark.getGreen() & 0xFF) << 8 | (this.sDark.getRed() & 0xFF) << 16 | 0xFF000000;
        final int n9 = this.bitMap[0];
        final int n10 = imgTrimInfo[0] - n4;
        for (int i = 0; i < n6; ++i) {
            final int n11 = (i + imgTrimInfo[2] - n4) * n;
            for (int j = 0; j < n5; ++j) {
                if (i < n4 || i >= n6 - n4 || j < n4 || j >= n5 - n4) {
                    array[n7] = n8;
                }
                else if (this.bitMap[n11 + j + n10] != n9) {
                    array[n7] = n3;
                }
                else {
                    array[n7] = n8;
                }
                ++n7;
            }
        }
        final int n12 = n * n2;
        final int iw = n5 - 2;
        final int ih = n6 - 2;
        if (n12 - n5 * n6 < 0) {
            this.bitMap = new int[iw * ih];
        }
        for (int k = 0; k < iw * ih; ++k) {
            this.bitMap[k] = 0;
        }
        this.smoothing(array, this.bitMap, n5, n6, n8, n8, (this.f3D1 > 0) ? 1 : 2, Math.abs(this.f3D1), Math.abs(this.f3D2), 1);
        this.bmpInfo[0] = n5;
        this.bmpInfo[1] = n6;
        this.prepareImage(this.frontImg = this.createImage(new MemoryImageSource(iw, ih, this.bitMap, 0, iw)), null);
        this.iw = iw;
        this.ih = ih;
        this.bmpInfo[0] = iw;
        this.bmpInfo[1] = ih;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.userUrl != null) {
            if (this.target != null) {
                this.getAppletContext().showDocument(this.userUrl, this.target);
            }
            else {
                this.getAppletContext().showDocument(this.userUrl);
            }
        }
        this.ratDown = false;
        return true;
    }
    
    public Scroll3D() {
        this.pswdGood = false;
        this.myPause = false;
        this.runReady = -1;
        this.restAwhile = false;
        this.firstStart = true;
        this.verticalText = false;
        this.preimgStatus = 0;
        this.ratDown = false;
        this.textLineN = 1;
        this.bkPicDraw = 2;
        this.delay = 100;
        this.advance = 3;
        this.pauseAt = -1;
        this.workChar = new char[2];
        this.specialFont = false;
        this.fontEffect = 0;
        this.lm = -15;
        this.rm = -10;
        this.tm = -10;
        this.bm = -10;
        this.rollGap = -1;
        this.offID = 1;
        this.first = true;
        this.firstRun = true;
        this.firstDraw = true;
        this.badThing = false;
        this.leftEdge = -1;
        this.startAt = -1;
        this.nextPause = false;
        this.animator = 1;
        this.frozen = false;
    }
}
