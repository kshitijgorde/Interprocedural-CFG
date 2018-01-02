import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class starfield extends Applet implements Runnable
{
    Thread tr;
    MediaTracker tracker;
    Image backGr;
    URL u;
    String[] txt;
    String[] lnk;
    Color[] colz;
    int[] aligns;
    int[] pauses;
    int[] speeds;
    String stat;
    int strCount;
    Color bgc;
    Color stc;
    Color ftc;
    Color lkc;
    int bgc1;
    int bgc2;
    int bgc3;
    int txc1;
    int txc2;
    int txc3;
    int tmp1;
    int tmp2;
    int tmp3;
    int dc1;
    int dc2;
    int dc3;
    Graphics offGfx;
    Image offImg;
    int bgImg;
    int FONTX;
    int FONTY;
    int SIZEX;
    int SIZEY;
    int IFBACK;
    int TRWAIT;
    int WAIT;
    int STAND;
    int MODE;
    int LINES;
    int stars;
    double[] starField;
    int SD;
    int stSp;
    int mxSz;
    int maxZ;
    int doSize;
    int doSine;
    int doCols;
    int speed;
    int albord;
    int siz1;
    int siz2;
    double Zan;
    int dx;
    int dy;
    int fade;
    int delay;
    Font txFnt;
    String str;
    String codeBase;
    int mousex;
    int mousey;
    int pressed;
    int going;
    int checkURL;
    String goWhere;
    URL uGo;
    int ptTemp;
    int ptTemp2;
    String[] lines;
    int txLines;
    int maxLn;
    static int align;
    static int startpt;
    static int segy;
    static int acty;
    static int txtPt;
    static int fy;
    static int pwt;
    Color oldcol;
    int localLook;
    int ptS1;
    int maxS1;
    int ptS2;
    int maxS2;
    
    public void init() {
        this.loadTxt();
        this.SIZEX = this.size().width;
        this.SIZEY = this.size().height;
        this.offImg = this.createImage(this.SIZEX, this.SIZEY);
        this.offGfx = this.offImg.getGraphics();
        this.SD = Integer.valueOf(this.getParameter("scrDist"));
        this.stSp = Integer.valueOf(this.getParameter("stSpeed"));
        this.mxSz = Integer.valueOf(this.getParameter("maxSize"));
        this.stars = Integer.valueOf(this.getParameter("stars"));
        this.TRWAIT = Integer.valueOf(this.getParameter("thread"));
        this.doSize = Integer.valueOf(this.getParameter("doSize"));
        this.doSine = Integer.valueOf(this.getParameter("doSine"));
        this.doCols = Integer.valueOf(this.getParameter("doCols"));
        this.speed = Integer.valueOf(this.getParameter("speed"));
        this.albord = Integer.valueOf(this.getParameter("alignBorder"));
        this.Zan = Double.valueOf(this.getParameter("Zangle"));
        if (this.getParameter("backPic") != "") {
            this.bgImg = 1;
            this.showStatus("Applet Starfied Upscroll is loading... Please wait!");
            this.tracker = new MediaTracker(this);
            this.backGr = this.getImage(this.getCodeBase(), this.getParameter("backPic"));
            this.tracker.addImage(this.backGr, 1);
            try {
                this.tracker.waitForAll();
            }
            catch (InterruptedException ex) {
                this.showStatus("Error loading image!");
            }
        }
        this.stc = this.convertToColor(this.getParameter("starCol"));
        this.bgc = this.convertToColor(this.getParameter("backCol"));
        this.lkc = this.convertToColor(this.getParameter("linkColor"));
        this.bgc1 = this.tmp1;
        this.bgc2 = this.tmp2;
        this.bgc3 = this.tmp3;
        this.ftc = this.convertToColor(this.getParameter("fontColor"));
        this.txc1 = this.tmp1;
        this.txc2 = this.tmp2;
        this.txc3 = this.tmp3;
        this.dc1 = this.txc1 - this.bgc1;
        this.dc2 = this.txc2 - this.bgc2;
        this.dc3 = this.txc3 - this.bgc3;
        switch (Integer.valueOf(this.getParameter("fontStyle"))) {
            case 0: {
                this.txFnt = new Font(this.getParameter("fontName"), 0, Integer.valueOf(this.getParameter("fontSize")));
                break;
            }
            case 1: {
                this.txFnt = new Font(this.getParameter("fontName"), 1, Integer.valueOf(this.getParameter("fontSize")));
                break;
            }
            case 2: {
                this.txFnt = new Font(this.getParameter("fontName"), 2, Integer.valueOf(this.getParameter("fontSize")));
                break;
            }
            case 3: {
                this.txFnt = new Font(this.getParameter("fontName"), 3, Integer.valueOf(this.getParameter("fontSize")));
                break;
            }
            default: {
                this.txFnt = new Font(this.getParameter("fontName"), 0, Integer.valueOf(this.getParameter("fontSize")));
                break;
            }
        }
        this.txLines = Integer.valueOf(this.getParameter("lines"));
        this.fade = Integer.valueOf(this.getParameter("fade"));
        this.delay = Integer.valueOf(this.getParameter("delay"));
        this.starField = new double[this.stars * 3];
        this.resize(this.SIZEX, this.SIZEY);
        if (this.Zan != 0.0) {
            if (this.SIZEX > this.SIZEY) {
                this.siz1 = this.SIZEX;
                this.siz2 = this.SIZEX;
            }
            else {
                this.siz1 = this.SIZEY;
                this.siz2 = this.SIZEY;
            }
        }
        else {
            this.siz1 = this.SIZEX;
            this.siz2 = this.SIZEY;
        }
        for (int i = 0; i < this.stars; ++i) {
            this.starField[i * 3] = (int)(Math.random() * (2 * this.siz1) - this.siz1);
        }
        for (int j = 0; j < this.stars; ++j) {
            this.starField[j * 3 + 1] = (int)(Math.random() * (2 * this.siz2) - this.siz2);
        }
        for (int k = 0; k < this.stars; ++k) {
            this.starField[k * 3 + 2] = (int)(Math.random() * this.maxZ);
        }
        if (this.txLines != 0) {
            this.lines = new String[this.txLines + 1];
            starfield.txtPt = 0;
            this.offGfx.setFont(this.txFnt);
            starfield.segy = this.SIZEY / this.txLines;
            starfield.fy = this.offGfx.getFontMetrics().getAscent();
            this.dy = (starfield.segy + starfield.fy) / 2;
            for (int l = 0; l <= this.txLines; ++l) {
                this.lines[l] = " ";
            }
        }
        this.codeBase = this.getCodeBase().toString();
        this.checkURL();
        if (Integer.valueOf(this.getParameter("copyright by stefan mateescu")) == 1 && Integer.valueOf(this.getParameter("mateescu@headlight.de")) == 1) {
            this.MODE = 1;
        }
    }
    
    public int checkURL() {
        int checked = 0;
        if (this.checkOne(0, 'f') == 1 && this.checkOne(1, 'i') == 1 && this.checkOne(2, 'l') == 1 && this.checkOne(3, 'e') == 1 && this.checkOne(4, ':') == 1) {
            checked = 1;
        }
        final byte[] barray1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final byte[] barray2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        barray1[0] = 115;
        barray1[1] = 116;
        barray1[2] = 111;
        barray1[3] = 114;
        barray1[4] = 109;
        barray1[5] = 46;
        barray2[0] = 109;
        barray2[1] = 46;
        barray2[2] = 104;
        barray2[3] = 101;
        barray2[4] = 97;
        barray2[5] = 100;
        barray2[6] = 108;
        barray2[7] = 105;
        barray2[8] = 103;
        barray2[9] = 104;
        if (this.checkMore(barray1, 5) == 1 && this.checkMore(barray2, 9) == 1) {
            checked = 1;
        }
        barray1[0] = 111;
        barray1[1] = 117;
        barray1[2] = 116;
        barray1[3] = 105;
        barray1[4] = 113;
        barray1[5] = 117;
        barray1[6] = 101;
        barray1[7] = 46;
        barray2[0] = 106;
        barray2[1] = 97;
        barray2[2] = 118;
        barray2[3] = 97;
        barray2[4] = 98;
        barray2[5] = 111;
        barray2[6] = 117;
        barray2[7] = 116;
        barray2[8] = 105;
        barray2[9] = 113;
        if (this.checkMore(barray1, 7) == 1 && this.checkMore(barray2, 9) == 1) {
            checked = 1;
        }
        this.checkURL = checked;
        checked = 1;
        return checked;
    }
    
    public int checkMore(final byte[] b, final int len) {
        int checked = 0;
        for (int i = 0; i <= this.codeBase.length() - 1; ++i) {
            if (b[0] == (byte)this.codeBase.charAt(i)) {
                int temp = 1;
                for (int j = 0; j <= len; ++j) {
                    if (this.codeBase.length() - 1 >= i + len) {
                        if (b[j] != (byte)this.codeBase.charAt(i + j)) {
                            temp = 0;
                        }
                    }
                    else {
                        temp = 0;
                    }
                }
                if (temp == 1) {
                    checked = 1;
                }
            }
        }
        return checked;
    }
    
    public int checkOne(final int loc, final char b) {
        int checked = 0;
        if (this.codeBase.charAt(loc) == b) {
            checked = 1;
        }
        return checked;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        this.pressed = 1;
        return true;
    }
    
    public boolean mouseUp(final Event e, final int x, final int y) {
        if (this.localLook == 1 && this.goWhere != null) {
            if (this.checkURL == 1) {
                try {
                    this.uGo = new URL(this.goWhere);
                }
                catch (MalformedURLException ex) {
                    System.out.println("Malformed URL!");
                }
                this.getAppletContext().showDocument(this.uGo);
                this.going = 1;
            }
            else {
                this.going = 2;
            }
        }
        return true;
    }
    
    public void showStat() {
        if (this.localLook != 1) {
            this.showStatus(this.stat);
            return;
        }
        this.showStatus(this.goWhere);
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        this.showStat();
        this.mousex = x;
        this.mousey = y;
        return true;
    }
    
    public boolean mouseMove(final Event e, final int x, final int y) {
        this.showStat();
        this.mousex = x;
        this.mousey = y;
        return true;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.showStatus("");
        this.mousex = -1;
        this.mousey = -1;
        return true;
    }
    
    public void loadTxt() {
        final byte[] b = new byte[400];
        int temp = 0;
        int first = 0;
        try {
            this.u = new URL(this.getDocumentBase(), this.getParameter("txtfile"));
            final InputStream f = this.u.openStream();
            int c;
            while ((c = f.read()) > -1) {
                if (c == 10) {
                    ++this.strCount;
                    first = 2;
                }
                else {
                    if (first <= 0) {
                        continue;
                    }
                    --first;
                    if (c != 91) {
                        continue;
                    }
                    ++temp;
                }
            }
            if (f == null) {
                f.close();
            }
        }
        catch (IOException ex) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        this.strCount -= temp;
        int n = 0;
        int ln = 0;
        int oldalign = 0;
        this.txt = new String[this.strCount];
        this.lnk = new String[this.strCount];
        this.colz = new Color[this.strCount];
        this.aligns = new int[this.strCount];
        this.pauses = new int[this.strCount];
        this.oldcol = this.ftc;
        for (int j = 0; j < this.strCount; ++j) {
            this.pauses[j] = 0;
        }
        int not = 0;
        try {
            final InputStream f = new URL(this.getDocumentBase(), this.getParameter("txtfile")).openStream();
            int c;
            while ((c = f.read()) > -1) {
                if (c == 10) {
                    if (n == 0) {
                        n = 2;
                        b[0] = 32;
                        b[2] = (b[1] = 32);
                    }
                    if (b[0] == 91 && b[1] == 67 && b[2] == 93) {
                        final byte[] ff = new byte[7];
                        for (int bc = 0; bc < 7; ++bc) {
                            ff[bc] = b[3 + bc];
                        }
                        final String st = new String(ff, 0, 0, 7);
                        this.oldcol = this.convertToColor(st);
                        ++not;
                    }
                    if (b[0] == 91 && b[1] == 80 && b[2] == 93) {
                        ++not;
                        if (b[3] == 49) {
                            this.pauses[ln] = 1;
                        }
                        if (b[3] == 50) {
                            this.pauses[ln] = 2;
                        }
                        if (b[3] == 51) {
                            this.pauses[ln] = 3;
                        }
                        if (b[3] == 52) {
                            this.pauses[ln] = 4;
                        }
                        if (b[3] == 53) {
                            this.pauses[ln] = 5;
                        }
                        if (b[3] == 54) {
                            this.pauses[ln] = 6;
                        }
                        if (b[3] == 55) {
                            this.pauses[ln] = 7;
                        }
                        if (b[3] == 56) {
                            this.pauses[ln] = 8;
                        }
                        if (b[3] == 57) {
                            this.pauses[ln] = 9;
                        }
                    }
                    if (b[0] == 91 && b[1] == 65 && b[2] == 93) {
                        ++not;
                        if (b[3] == 99 || b[3] == 67) {
                            oldalign = 0;
                        }
                        if (b[3] == 108 || b[3] == 76) {
                            oldalign = 1;
                        }
                        if (b[3] == 114 || b[3] == 82) {
                            oldalign = 2;
                        }
                    }
                    if (b[0] == 91 && b[1] == 76 && b[2] == 93) {
                        ++not;
                        this.lnk[ln - 1] = new String(b, 0, 3, n - 3);
                    }
                    this.aligns[ln] = oldalign;
                    this.colz[ln] = new Color(this.oldcol.getRed(), this.oldcol.getGreen(), this.oldcol.getBlue());
                    final String s = new String(b, 0, 0, n);
                    if (not == 0) {
                        this.txt[ln++] = s;
                    }
                    else {
                        not = 0;
                    }
                    n = 0;
                }
                else {
                    if (c == 13 || c == 10) {
                        continue;
                    }
                    b[n++] = (byte)c;
                }
            }
            if (f == null) {
                f.close();
            }
        }
        catch (IOException ex2) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        this.maxLn = ln;
    }
    
    public Color convertToColor(final String s) {
        final String h = "0123456789abcdef";
        final int[] hex = new int[6];
        Color c;
        if (s != null && s.length() == 7) {
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 16; ++j) {
                    if (Character.toLowerCase(s.charAt(i + 1)) == h.charAt(j)) {
                        hex[i] = j;
                    }
                }
            }
            this.tmp1 = hex[0] * 16 + hex[1];
            this.tmp2 = hex[2] * 16 + hex[3];
            this.tmp3 = hex[4] * 16 + hex[5];
            c = new Color(this.tmp1, this.tmp2, this.tmp3);
        }
        else {
            c = Color.red;
        }
        return c;
    }
    
    public void start() {
        if (this.tr == null) {
            (this.tr = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.tr != null) {
            this.tr.stop();
            this.tr = null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.TRWAIT);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public synchronized void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        if (this.MODE == 1) {
            this.offGfx.setColor(this.bgc);
            this.offGfx.fillRect(0, 0, this.SIZEX, this.SIZEY);
            if (this.bgImg == 1) {
                final int x = (this.SIZEX - this.backGr.getWidth(this)) / 2;
                final int y = (this.SIZEY - this.backGr.getHeight(this)) / 2;
                this.offGfx.drawImage(this.backGr, x, y, this);
            }
            if (this.stars != 0) {
                this.starfield();
            }
            if (this.going == 0) {
                if (this.txLines != 0) {
                    this.texter();
                }
            }
            else if (this.going == 1) {
                final String str = "going to: " + this.goWhere;
                this.offGfx.setColor(Color.white);
                this.offGfx.setFont(new Font("Arial", 1, 20));
                final int fx = this.offGfx.getFontMetrics().stringWidth(str);
                this.offGfx.drawString(str, (this.SIZEX - fx) / 2, (this.SIZEY + starfield.fy) / 2);
            }
            else {
                final String[] str2 = { "This function is", "not allowed with an", "unregistered version!", "Contact mateescu@headlight.de", "for more information!" };
                this.offGfx.setColor(Color.white);
                this.offGfx.setFont(new Font("Arial", 0, 12));
                for (int i = 0; i < 5; ++i) {
                    final int fx2 = this.offGfx.getFontMetrics().stringWidth(str2[i]);
                    this.offGfx.drawString(str2[i], (this.SIZEX - fx2) / 2, (this.SIZEY + starfield.fy) / 2 + i * 100 / 4 - 50);
                }
            }
        }
        else {
            final Font f = new Font("TimesRoman", 2, 14);
            this.offGfx.setColor(Color.red);
            this.offGfx.setFont(f);
            this.offGfx.drawString("Sorry! This java-class is not yours!", 10, this.SIZEY / 2 - 7);
            this.offGfx.drawString("mail to: mateescu@headlight.de for more infos!", 10, this.SIZEY / 2 + 10);
        }
        g.drawImage(this.offImg, 0, 0, this);
    }
    
    public void texter() {
        this.localLook = 0;
        this.offGfx.setFont(this.txFnt);
        int ad = 0;
        for (int i = 0; i <= this.txLines; ++i) {
            int t = i + starfield.txtPt;
            if (t >= this.maxLn) {
                t -= this.maxLn;
            }
            if (this.txt[t].charAt(0) == '[' && this.txt[t].charAt(2) == ']') {
                ++ad;
                if (t >= this.maxLn) {
                    t -= this.maxLn;
                }
            }
            int dont = 0;
            this.ftc = this.colz[t];
            this.dc1 = this.ftc.getRed() - this.bgc.getRed();
            this.dc2 = this.ftc.getGreen() - this.bgc.getGreen();
            this.dc3 = this.ftc.getBlue() - this.bgc.getBlue();
            final int fx = this.offGfx.getFontMetrics().stringWidth(this.txt[t]);
            this.dx = (this.SIZEX - fx) / 2;
            if (this.fade == 1) {
                final int sg2 = starfield.segy / 2;
                if (i == 0) {
                    if (starfield.acty < sg2) {
                        final int ct1 = this.bgc1 + this.dc1 * (sg2 - starfield.acty) / sg2;
                        final int ct2 = this.bgc2 + this.dc2 * (sg2 - starfield.acty) / sg2;
                        final int ct3 = this.bgc3 + this.dc3 * (sg2 - starfield.acty) / sg2;
                        final Color cTmp = new Color(ct1, ct2, ct3);
                        this.offGfx.setColor(cTmp);
                    }
                    else {
                        dont = 1;
                    }
                }
                else if (i == this.txLines) {
                    if (starfield.acty > sg2) {
                        final int ct1 = this.bgc1 + this.dc1 * (starfield.acty - sg2) / sg2;
                        final int ct2 = this.bgc2 + this.dc2 * (starfield.acty - sg2) / sg2;
                        final int ct3 = this.bgc3 + this.dc3 * (starfield.acty - sg2) / sg2;
                        final Color cTmp = new Color(ct1, ct2, ct3);
                        this.offGfx.setColor(cTmp);
                    }
                    else {
                        dont = 1;
                    }
                }
                else {
                    this.offGfx.setColor(this.ftc);
                }
            }
            else {
                this.offGfx.setColor(this.ftc);
            }
            if (dont == 0) {
                if (this.aligns[t] == 0) {
                    this.checkLoc(this.dx, this.dy + i * starfield.segy - starfield.acty, fx, starfield.fy, t);
                    this.offGfx.drawString(this.txt[t], this.dx, this.dy + i * starfield.segy - starfield.acty);
                }
                if (this.aligns[t] == 1) {
                    this.checkLoc(this.albord, this.dy + i * starfield.segy - starfield.acty, fx, starfield.fy, t);
                    this.offGfx.drawString(this.txt[t], this.albord, this.dy + i * starfield.segy - starfield.acty);
                }
                if (this.aligns[t] == 2) {
                    this.checkLoc(this.SIZEX - this.albord - fx, this.dy + i * starfield.segy - starfield.acty, fx, starfield.fy, t);
                    this.offGfx.drawString(this.txt[t], this.SIZEX - this.albord - fx, this.dy + i * starfield.segy - starfield.acty);
                }
            }
        }
        if (starfield.startpt == this.delay) {
            if (starfield.pwt == 0) {
                starfield.acty += this.speed;
            }
            else {
                --starfield.pwt;
            }
            if (starfield.acty >= starfield.segy) {
                starfield.acty -= starfield.segy;
                ++starfield.txtPt;
                if (starfield.txtPt > this.maxLn - 1) {
                    starfield.txtPt = 0;
                }
                int t2 = starfield.txtPt + this.txLines;
                if (t2 >= this.maxLn) {
                    t2 -= this.maxLn;
                }
                starfield.pwt = this.pauses[t2] * 30;
            }
            starfield.startpt = 0;
            return;
        }
        ++starfield.startpt;
    }
    
    public void checkLoc(int stx, int sty, int widex, int backy, final int tloc) {
        stx -= 2;
        sty += 2;
        widex += 4;
        backy += 4;
        if (this.mousex > stx && this.mousex < stx + widex && this.mousey > sty - backy && this.mousey < sty && this.lnk[tloc] != null) {
            this.offGfx.setColor(this.lkc);
            this.goWhere = this.lnk[tloc];
            this.localLook = 1;
        }
    }
    
    public void starfield() {
        double Zan = this.Zan;
        if (this.doSine == 1) {
            Zan = (Math.sin(this.ptS1 * 2 * 3.141592653589793 / this.maxS1) + Math.sin(this.ptS2 * 2 * 3.141592653589793 / this.maxS2)) * this.Zan / 2.0;
            if (this.ptS1++ >= this.maxS1) {
                this.ptS1 = 0;
            }
            if (this.ptS2++ >= this.maxS2) {
                this.ptS2 = 0;
            }
        }
        int sz = 1;
        this.offGfx.setColor(this.stc);
        for (int i = 0; i < this.stars; ++i) {
            if (Zan != 0.0) {
                final double px = this.starField[i * 3] * Math.cos(Zan) - this.starField[i * 3 + 1] * Math.sin(Zan);
                final double py = this.starField[i * 3] * Math.sin(Zan) + this.starField[i * 3 + 1] * Math.cos(Zan);
                this.starField[i * 3] = px;
                this.starField[i * 3 + 1] = py;
            }
            if (this.starField[i * 3 + 2] != 0.0) {
                final int scrX = (int)(this.SIZEX / 2 + this.SD * this.starField[i * 3] / this.starField[i * 3 + 2]);
                final int scrY = (int)(this.SIZEY / 2 + this.SD * this.starField[i * 3 + 1] / this.starField[i * 3 + 2]);
                if (scrX > 0 && scrX < this.SIZEX && scrY > 0 && scrY < this.SIZEY) {
                    if (this.doCols != 0) {
                        final int cc1 = (int)((this.stc.getRed() - this.bgc.getRed()) * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ);
                        final int cc2 = (int)((this.stc.getGreen() - this.bgc.getGreen()) * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ);
                        final int cc3 = (int)((this.stc.getBlue() - this.bgc.getBlue()) * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ);
                        final Color tempcol = new Color(this.bgc.getRed() + cc1, this.bgc.getGreen() + cc2, this.bgc.getBlue() + cc3);
                        this.offGfx.setColor(tempcol);
                    }
                    if (this.doSize != 0) {
                        sz = (int)(this.mxSz * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ);
                    }
                    this.offGfx.fillRect(scrX, scrY, sz, sz);
                }
            }
            this.starField[i * 3 + 2] -= this.stSp;
            if (this.starField[i * 3 + 2] < 0.0) {
                this.starField[i * 3] = (int)(Math.random() * (2 * this.siz1) - this.siz1);
                this.starField[i * 3 + 1] = (int)(Math.random() * (2 * this.siz2) - this.siz2);
                this.starField[i * 3 + 2] = this.maxZ;
            }
        }
    }
    
    public starfield() {
        this.stat = "Starfield Upscroller by Stefan Mateescu (mateescu@headlight.de)";
        this.FONTX = 20;
        this.FONTY = 20;
        this.SIZEX = 400;
        this.SIZEY = 200;
        this.IFBACK = 1;
        this.TRWAIT = 10;
        this.WAIT = 100;
        this.STAND = 5;
        this.LINES = 5;
        this.stars = 200;
        this.SD = 60;
        this.stSp = 5;
        this.mxSz = 5;
        this.maxZ = 600;
        this.doSize = 1;
        this.doSine = 1;
        this.doCols = 1;
        this.speed = 1;
        this.albord = 10;
        this.Zan = -0.02;
        this.mousex = -1;
        this.mousey = -1;
        this.txLines = 3;
        this.maxS1 = 80;
        this.maxS2 = 55;
    }
}
