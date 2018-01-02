import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Event;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class starfield extends Applet implements Runnable
{
    Thread tr;
    URL u;
    String[] txt;
    String stat;
    int strCount;
    Color bgc;
    Color stc;
    Color ftc;
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
    int[] starField;
    int SD;
    int stSp;
    int mxSz;
    int maxZ;
    int doSize;
    int doCols;
    int dx;
    int dy;
    int fade;
    int delay;
    Font txFnt;
    String str;
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
    
    public void init() {
        this.loadTxt();
        this.SIZEX = this.size().width;
        this.SIZEY = this.size().height;
        this.offImg = this.createImage(this.SIZEX, this.SIZEY * 2);
        this.offGfx = this.offImg.getGraphics();
        this.SD = Integer.valueOf(this.getParameter("scrDist"));
        this.stSp = Integer.valueOf(this.getParameter("stSpeed"));
        this.mxSz = Integer.valueOf(this.getParameter("maxSize"));
        this.stars = Integer.valueOf(this.getParameter("stars"));
        this.TRWAIT = Integer.valueOf(this.getParameter("thread"));
        this.doSize = Integer.valueOf(this.getParameter("doSize"));
        this.doCols = Integer.valueOf(this.getParameter("doCols"));
        this.stc = this.convertToColor(this.getParameter("starCol"));
        this.bgc = this.convertToColor(this.getParameter("backCol"));
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
        this.lines = new String[this.txLines + 1];
        starfield.txtPt = 0;
        this.offGfx.setFont(this.txFnt);
        starfield.segy = this.SIZEY / this.txLines;
        starfield.fy = this.offGfx.getFontMetrics().getAscent();
        this.dy = (starfield.segy + starfield.fy) / 2;
        this.fade = Integer.valueOf(this.getParameter("fade"));
        this.delay = Integer.valueOf(this.getParameter("delay"));
        this.starField = new int[this.stars * 3];
        this.resize(this.SIZEX, this.SIZEY);
        for (int i = 0; i < this.stars; ++i) {
            this.starField[i * 3] = (int)(Math.random() * (2 * this.SIZEX) - this.SIZEX);
        }
        for (int j = 0; j < this.stars; ++j) {
            this.starField[j * 3 + 1] = (int)(Math.random() * (2 * this.SIZEY) - this.SIZEY);
        }
        for (int k = 0; k < this.stars; ++k) {
            this.starField[k * 3 + 2] = (int)(Math.random() * this.maxZ);
        }
        for (int l = 0; l <= this.txLines; ++l) {
            this.lines[l] = " ";
        }
        if (Integer.valueOf(this.getParameter("copyright by stefan mateescu")) == 1 && Integer.valueOf(this.getParameter("mateescu@headlight.de")) == 1) {
            this.MODE = 1;
        }
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        this.showStatus(this.stat);
        return true;
    }
    
    public boolean mouseMove(final Event e, final int x, final int y) {
        this.showStatus(this.stat);
        return true;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.showStatus("");
        return true;
    }
    
    public void loadTxt() {
        final byte[] b = new byte[400];
        try {
            this.u = new URL(this.getDocumentBase(), this.getParameter("txtfile"));
            final InputStream f = this.u.openStream();
            int c;
            while ((c = f.read()) > -1) {
                if (c == 10) {
                    ++this.strCount;
                }
            }
            if (f == null) {
                f.close();
            }
        }
        catch (IOException ex) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        int n = 0;
        int ln = 0;
        this.txt = new String[this.strCount];
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
                    final String s = new String(b, 0, 0, n);
                    this.txt[ln++] = s;
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
            c = Color.black;
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
            if (this.stars != 0) {
                this.starfield();
            }
            if (this.txLines != 0) {
                this.texter();
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
        this.offGfx.setFont(this.txFnt);
        for (int i = 0; i <= this.txLines; ++i) {
            int t = i + starfield.txtPt;
            if (t >= this.maxLn) {
                t -= this.maxLn;
            }
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
                        this.offGfx.setColor(this.bgc);
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
                        this.offGfx.setColor(this.bgc);
                    }
                }
                else {
                    this.offGfx.setColor(this.ftc);
                }
            }
            else {
                this.offGfx.setColor(this.ftc);
            }
            this.offGfx.drawString(this.txt[t], this.dx, this.dy + i * starfield.segy - starfield.acty);
        }
        if (starfield.startpt == this.delay) {
            if (++starfield.acty == starfield.segy) {
                starfield.acty = 0;
                if (++starfield.txtPt > this.maxLn - 1) {
                    starfield.txtPt = 0;
                }
            }
            starfield.startpt = 0;
            return;
        }
        ++starfield.startpt;
    }
    
    public void starfield() {
        int sz = 1;
        this.offGfx.setColor(this.stc);
        for (int i = 0; i < this.stars; ++i) {
            if (this.starField[i * 3 + 2] != 0) {
                final int scrX = this.SIZEX / 2 + this.SD * this.starField[i * 3] / this.starField[i * 3 + 2];
                final int scrY = this.SIZEY / 2 + this.SD * this.starField[i * 3 + 1] / this.starField[i * 3 + 2];
                if (scrX > 0 && scrX < this.SIZEX && scrY > 0 && scrY < this.SIZEY) {
                    if (this.doCols != 0) {
                        final int n = (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ;
                        final int cc1 = (this.stc.getRed() - this.bgc.getRed()) * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ;
                        final int cc2 = (this.stc.getGreen() - this.bgc.getGreen()) * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ;
                        final int n2 = (this.stc.getBlue() - this.bgc.getBlue()) * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ;
                        final Color tempcol = new Color(this.bgc.getRed() + cc1, this.bgc.getGreen() + cc2, this.bgc.getBlue() + cc2);
                        this.offGfx.setColor(tempcol);
                    }
                    if (this.doSize != 0) {
                        sz = this.mxSz * (this.maxZ - this.starField[i * 3 + 2]) / this.maxZ;
                    }
                    this.offGfx.fillRect(scrX, scrY, sz, sz);
                }
            }
            this.starField[i * 3 + 2] -= this.stSp;
            if (this.starField[i * 3 + 2] < 0) {
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
        this.doCols = 1;
        this.txLines = 3;
    }
}
