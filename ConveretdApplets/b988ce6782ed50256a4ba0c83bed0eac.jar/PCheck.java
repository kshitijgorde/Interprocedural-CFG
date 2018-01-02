import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.TextArea;
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

public class PCheck extends Applet
{
    private final int PLAIN = 0;
    private final int OUTLINE = 7;
    private final int OUTLINEFONT = 1;
    private final int SMOOTH = 8;
    private final int THREED = 16;
    private final int TRANSPARENT = 256;
    private final int TRANSPARENT_TRIM = 768;
    private final int PREIMG_NORMAL = 0;
    private int[] psword;
    private final int PSWD_LENGTH;
    private int[] pswarray;
    private final int RN;
    private boolean pswdGood;
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
    private int[] updateInfo;
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
    private ClockLock clk;
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
    private TextArea dspT;
    
    public void init() {
        this.aw = this.size().width;
        this.ah = this.size().height;
        this.applets = this;
        this.ptTmp = new Point(0, 0);
        this.dspT = new TextArea(10, 40);
        final String trimParam = this.getTrimParam("scrollDirection");
        this.animator = 0;
        if (this.emptyString(trimParam) > 0 && trimParam.equalsIgnoreCase("vertical")) {
            this.animator = 1;
        }
        if (this.animator == 1) {
            this.dspT.appendText("Scrooll direction: Vertical.\n");
        }
        else {
            this.dspT.appendText("Scrooll direction: Horizontal.\n");
        }
        this.textLineN = 50;
        this.dspT.appendText("\n");
        final String trimParam2 = this.getTrimParam("backImage");
        if (trimParam2 == null) {
            this.backPic = 0;
            this.dspT.appendText("No Background Image.\n");
        }
        else {
            this.backPic = (this.isBMP(trimParam2) ? 2 : 1);
            this.bkFile = trimParam2;
            final String trimParam3 = this.getTrimParam("drawBkImage");
            if (this.emptyString(trimParam3) > 0) {
                if (trimParam3.toLowerCase().equals("as is") || trimParam3.startsWith("as is ")) {
                    this.bkPicDraw = 0;
                    this.dspT.appendText("BackImage will be draw AS IS\n");
                }
                else {
                    this.dspT.appendText("BackImage will FILL the background.\n");
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
                        this.errorM("DrawBkIamge parameters.", trimParam3);
                        this.badThing = true;
                    }
                }
                else if (paramString != null) {
                    this.errorM("DrawBkIamge parameters.", trimParam3);
                    this.badThing = true;
                }
            }
            else {
                this.dspT.appendText("BackImage will FILL the background.\n");
            }
        }
        this.dspT.appendText("\n");
        final String trimParam4 = this.getTrimParam("frontImage");
        if (this.emptyString(trimParam4) > 0) {
            this.frontPic = (this.isBMP(trimParam4) ? 3 : 1);
            this.fgFile = trimParam4;
            final String trimParam5 = this.getTrimParam("frontImageIs");
            if (this.emptyString(trimParam5) > 0 && trimParam5.equalsIgnoreCase("text")) {
                ++this.frontPic;
            }
            if (this.frontPic % 2 == 0) {
                this.dspT.appendText("Front image is a Text Image.\n");
            }
            else {
                this.dspT.appendText("Front image is not a Text Image.\n");
            }
            if (this.backPic > 0 && !this.fgFile.equals(this.bkFile)) {
                this.frontPic = -this.frontPic;
            }
        }
        else {
            this.dspT.appendText("No front image, text will be used.\n");
        }
        this.dspT.appendText("\n");
        if (this.animator == 1 && this.frontPic == 0) {
            this.vTextFile = this.getTrimParam("textFile");
            if (this.vTextFile != null) {
                this.dspT.appendText(String.valueOf(new StringBuffer("Vertcal scrolling text will be taken from file: ").append(this.vTextFile).toString()) + "\n");
                this.dspT.appendText("\n");
            }
        }
        final String s = (this.vTextFile == null) ? this.getParameter("scrollText") : null;
        if (s != null) {
            final String[] array = new String[this.textLineN];
            array[0] = ((s.charAt(0) == 's') ? s.substring(1) : s);
            int i;
            for (i = 1; i < this.textLineN; ++i) {
                final String parameter = this.getParameter("scrollText" + i);
                if (parameter == null) {
                    break;
                }
                array[i] = ((parameter.charAt(0) == 's') ? parameter.substring(1) : parameter);
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
        this.preCall = ((this.delay / 2 < 5) ? (this.delay / 2) : 5);
        this.liteColor = new int[3];
        final String trimParam6 = this.getTrimParam("textColor");
        for (int l = 0; l < 3; ++l) {
            this.liteColor[l] = 0;
        }
        if (this.getHexColor(trimParam6, this.liteColor, 0) < 0) {
            for (int n2 = 0; n2 < 3; ++n2) {
                this.liteColor[n2] = 0;
            }
            this.dspT.appendText("TextColor will use BLACK(default).\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        this.lite = new Color(this.liteColor[0], this.liteColor[1], this.liteColor[2]);
        this.darkColor = new int[3];
        final String parameter2 = this.getParameter("shadow");
        if (this.emptyString(parameter2) > 0) {
            final Point s2Int3 = this.s2Int(parameter2);
            if (s2Int3.y >= 0 && s2Int3.x >= 0) {
                this.shadow = 1;
                this.shadowOffset = Math.min(10, s2Int3.x);
                this.dspT.appendText(String.valueOf(new StringBuffer("Input shadow value:  ").append(this.shadowOffset).toString()) + "\n");
            }
            else {
                this.badThing = true;
                this.errorM("Input value for Shadow.", parameter2);
            }
        }
        final String trimParam7 = this.getTrimParam("shadowColor");
        if (this.getHexColor(trimParam7, this.darkColor, 0) >= 0) {
            this.shadowColor = new Color(this.darkColor[0], this.darkColor[1], this.darkColor[2]);
        }
        else if (trimParam7 != null) {
            this.badThing = true;
            this.errorM("Input value for ShadowColor.", trimParam7);
        }
        this.dspT.appendText("\n");
        final String parameter3 = this.getParameter("frontShadow");
        if (parameter3 != null) {
            final Point s2Int4 = this.s2Int(parameter3);
            if (s2Int4.y >= 0 && s2Int4.x >= 0) {
                this.frontShadow = 1;
                this.fShadowOffset = Math.min(10, s2Int4.x);
                this.dspT.appendText(String.valueOf(new StringBuffer("Input FrontShadow value:  ").append(this.fShadowOffset).toString()) + "\n");
            }
            else {
                this.badThing = true;
                this.errorM("Input value for FrontShadow.", parameter3);
            }
        }
        final String trimParam8 = this.getTrimParam("frontShadowColor");
        if (this.emptyString(trimParam8) > 0) {
            if (this.getHexColor(trimParam8, this.darkColor, 0) >= 0) {
                this.fShadowColor = new Color(this.darkColor[0], this.darkColor[1], this.darkColor[2]);
            }
            else {
                this.badThing = true;
                this.errorM("Input value for FrontShadowColor.", trimParam8);
            }
        }
        this.dspT.appendText("\n");
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
                this.dspT.appendText(String.valueOf(new StringBuffer("FontGap coding error.  FontGap set to  ").append(this.fontGap).append(" (dafult)").toString()) + "\n");
                this.dspT.appendText(String.valueOf(new StringBuffer("   Input for FontGap:   ").append(parameter4).toString()) + "\n");
                this.dspT.appendText("\n");
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
            this.dspT.appendText("BackgroundColor will use WHITE(default).\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        this.dark = new Color(this.darkColor[0], this.darkColor[1], this.darkColor[2]);
        if (this.sDark == null) {
            this.sDark = this.dark;
            this.dspT.appendText("FontBackColor set to default value.\n");
            this.dspT.appendText("There maybe error if coded\n");
            this.dspT.appendText("\n");
        }
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
                    this.errorM("Frame input value.", trimParam10);
                    wordIndex = -1;
                }
            }
            this.frameN = frameN;
        }
        else {
            this.dspT.appendText("Frame was not coded.  No Frame will be draw.\n");
            this.dspT.appendText("\n");
        }
        this.lm = this.frameN;
        this.rm = this.frameN;
        this.tm = this.frameN;
        this.bm = this.frameN;
        final Point s2Int6 = this.s2Int(this.getParameter("leftMargin"));
        if (s2Int6.y < 0 || s2Int6.x < 0) {
            this.dspT.appendText(String.valueOf(new StringBuffer("LeftMargin set to:  ").append(this.frameN).append(" (default).").toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        else {
            this.lm = Math.min(s2Int6.x, this.aw);
        }
        final Point s2Int7 = this.s2Int(this.getParameter("rightMargin"));
        if (s2Int7.y < 0 || s2Int7.x < 0) {
            this.dspT.appendText(String.valueOf(new StringBuffer("RightMargin set to:  ").append(this.frameN).append(" (default).").toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        else {
            this.rm = Math.min(this.aw, s2Int7.x);
        }
        final Point s2Int8 = this.s2Int(this.getParameter("topMargin"));
        if (s2Int8.y < 0 || s2Int8.x < 0) {
            this.dspT.appendText(String.valueOf(new StringBuffer("TopMargin set to:  ").append(this.frameN).append(" (default).").toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        else {
            this.tm = Math.min(this.ah, s2Int8.x);
        }
        final Point s2Int9 = this.s2Int(this.getParameter("bottomMargin"));
        if (s2Int9.y < 0 || s2Int9.x < 0) {
            this.dspT.appendText(String.valueOf(new StringBuffer("BottomMargin set to:  ").append(this.frameN).append(" (default).").toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        else {
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
        String s3 = " plain ";
        final String trimParam12 = this.getTrimParam("fontStyle");
        if (this.emptyString(trimParam12) > 0) {
            if (trimParam12.equalsIgnoreCase("bold")) {
                n6 = 1;
                s3 = " bold ";
            }
            else if (trimParam12.equalsIgnoreCase("italic")) {
                n6 = 2;
                s3 = " italic ";
            }
            else if (trimParam12.equalsIgnoreCase("bold italic")) {
                n6 = 3;
                s3 = " bold italic ";
            }
        }
        final Point s2Int10 = this.s2Int(this.getParameter("fontSize"));
        int x;
        if (s2Int10.y < 0 || s2Int10.x <= 0 || s2Int10.x > 72) {
            x = 14;
            this.dspT.appendText(String.valueOf(new StringBuffer("FontSize set to:  ").append(x).append(" (default).").toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        else {
            x = s2Int10.x;
        }
        this.mFont = new Font(s2, n6, x);
        this.dspT.appendText(String.valueOf(new StringBuffer("Applet will use font:  ").append(s2).append(s3).append(x).toString()) + "\n");
        this.dspT.appendText("\n");
        final Point s2Int11 = this.s2Int(this.getParameter("delay"));
        if (s2Int11.y >= 0 && s2Int11.x > 0) {
            this.delay = s2Int11.x;
        }
        else {
            this.dspT.appendText(String.valueOf(new StringBuffer("Delay set to default value:  ").append(this.delay).toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        final Point s2Int12 = this.s2Int(this.getParameter("advance"));
        if (s2Int12.y >= 0) {
            this.advance = s2Int12.x;
        }
        else {
            this.dspT.appendText(String.valueOf(new StringBuffer("Advance set to default value: ").append(this.advance).toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        this.overI = ((this.advance < 0) ? -10 : (this.aw + 10));
        final Point s2Int13 = this.s2Int(this.getParameter("pauseTime"));
        if (s2Int13.y >= 0 && s2Int13.x > 0) {
            this.pause = 100 * s2Int13.x;
        }
        else {
            this.dspT.appendText(String.valueOf(new StringBuffer("PauseTime set to default value: ").append(this.pause).toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        if (this.pause > 0) {
            final Point s2Int14 = this.s2Int(this.getParameter("pauseLoop"));
            if (s2Int14.y >= 0) {
                this.pAtl = s2Int14.x;
            }
            else {
                this.dspT.appendText("There will be no PAUSE.\n");
                this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
                this.dspT.appendText("\n");
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
                this.dspT.appendText(String.valueOf(new StringBuffer("PauseAt set to default value: ").append(this.pauseAt).toString()) + "\n");
                this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
                this.dspT.appendText("\n");
            }
        }
        final Point s2Int16 = this.s2Int(this.getParameter("Gap"));
        if (s2Int16.y >= 0 && s2Int16.x >= 0) {
            this.rollGap = Math.min(this.bian - this.lm, s2Int16.x);
        }
        else {
            this.dspT.appendText("No GAP will be used.\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
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
            this.dspT.appendText(String.valueOf(new StringBuffer("StartAt set to default value:  ").append(this.leftEdge).toString()) + "\n");
            this.dspT.appendText("  There maybe error in input parameter value (if coded).\n");
            this.dspT.appendText("\n");
        }
        if (Math.abs(this.frontPic) % 2 == 0) {
            final String trimParam15 = this.getTrimParam("fontEffect");
            if (trimParam15 != null) {
                final String lowerCase = trimParam15.toLowerCase();
                if (lowerCase.startsWith("outline ")) {
                    if (this.fShadowColor != null) {
                        this.fontEffect = 7;
                    }
                    else {
                        this.dspT.appendText("(fontEffect)Outline without FrontShadowColor!\n");
                        this.dspT.appendText("  FontEffect set to no effect.\n");
                        this.dspT.appendText("\n");
                    }
                }
                else if (lowerCase.startsWith("outlinefont ")) {
                    if (this.fShadowColor != null) {
                        this.fontEffect = 1;
                    }
                    else {
                        this.errorM("OutlineFont (fontEffect) without FrontShadowColor!", null);
                        this.dspT.appendText("  Fonteffect set to no effect.\n");
                        this.dspT.appendText("\n");
                    }
                }
                else if (lowerCase.startsWith("transparent notrim")) {
                    if (this.frontPic != 0) {
                        this.fontEffect = 256;
                    }
                }
                else if (lowerCase.startsWith("transparent ")) {
                    if (this.frontPic != 0) {
                        this.fontEffect = 768;
                    }
                }
                else if (lowerCase.startsWith("3d nosmooth")) {
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
                    this.errorM("No shadow color coded for 3D Effects.", null);
                    this.f3D2 = 0;
                    this.fontEffect = 0;
                }
                if (this.f3D2 != 0 && this.f3D1 != 0) {
                    this.fontEffect = ((this.f3D2 > 0) ? 16 : 8);
                    final double[] paramDouble = this.paramDouble(lowerCase, (this.f3D1 > 0) ? 2 : 3);
                    if (paramDouble[0] == 0.0 && paramDouble[1] > 0.0 && paramDouble[1] < 0.9999) {
                        this.f3D2 = ((this.f3D2 > 0) ? 64 : -64);
                        paramDouble[1] = Math.min(paramDouble[1], 0.999);
                        this.f3D1 = (int)((this.f3D1 > 0) ? (paramDouble[1] * 64.0) : (-(paramDouble[1] * 64.0)));
                    }
                    else {
                        this.errorM("SmoothFactor.", lowerCase);
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
                this.errorM("Malformed URL", trimParam16);
            }
            if (this.userUrl != null) {
                this.target = this.getTrimParam("urlTarget");
            }
        }
        if (this.pAtl == 0) {
            this.pause = 0;
        }
        if (this.frontPic == 0 && this.sssb == null) {
            this.badThing = true;
            this.errorM("No front image and no scrolling text supplied.", null);
        }
        if (this.badThing) {
            System.err.println("Input parameter(s) error!");
            this.dspT.appendText("Input parameter(s) error!\n");
            this.sssb = "Input parameter(s) error!";
        }
        else {
            this.sssb = "3D Scroll: loading...";
        }
        this.dspT.setEditable(false);
        this.add("center", this.dspT);
        this.validate();
    }
    
    private void newt() {
        this.dspT.appendText("\n");
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
    
    private void errorM(final String s, final String s2) {
        this.dspT.appendText("\n");
        this.dspT.appendText("Error!!!   " + s + "\n");
        if (s2 != null) {
            this.dspT.appendText("  Input is: " + s2 + "\n");
        }
        this.dspT.appendText("\n");
    }
    
    private void tam(final String s) {
        this.dspT.appendText(String.valueOf(s) + "\n");
    }
    
    public int emptyString(final String s) {
        if (s == null) {
            return -1;
        }
        return s.length();
    }
    
    public PCheck() {
        this.psword = new int[] { 58, 45, 76 };
        this.PSWD_LENGTH = 2 * this.psword.length;
        this.pswarray = new int[] { 3, 12, 7, 8, 17, 0, 11 };
        this.RN = this.pswarray.length;
        this.pswdGood = false;
        this.firstStart = true;
        this.verticalText = false;
        this.preimgStatus = 0;
        this.ratDown = false;
        this.textLineN = 1;
        this.bkPicDraw = 2;
        this.delay = 100;
        this.advance = 3;
        this.pauseAt = -1;
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
    }
}
