import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class StarWarsScroller extends Applet implements Runnable
{
    Thread thread;
    int SIZEX;
    int SIZEY;
    int TRWAIT;
    int MODE;
    String statusText;
    Color BACKCOL;
    Color TEXTCOL;
    URL uLink;
    String regCode;
    int regOK;
    int strCount;
    int strPt;
    String[] txt;
    int loaded;
    int pressed;
    int singleCol;
    int delay;
    int delayPt;
    Graphics offGfx;
    Image offImg;
    MemoryImageSource bufImg;
    Image showImg;
    byte[] red;
    byte[] green;
    byte[] blue;
    IndexColorModel colModel;
    byte[] buf;
    byte[] txtbuf;
    int pt;
    int txtbufMax;
    double[] sinTable;
    double[] cosTable;
    int[] edgeLeft;
    int[] edgeRite;
    double[] um_arr;
    double[] vm_arr;
    double[] u_arr;
    int[] s_arr;
    int[] e_arr;
    int[] vx_arr;
    int[] yx_arr;
    byte[] colRange;
    int offX1;
    int offY1;
    int offX2;
    int offY2;
    int[][] coords;
    int offX1center;
    int offX2center;
    int fontHeight;
    int fontWmax;
    int[] fontWidth;
    int[] fontData;
    byte[] smallBuf;
    
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
            final int tmp1 = hex[0] * 16 + hex[1];
            final int tmp2 = hex[2] * 16 + hex[3];
            final int tmp3 = hex[4] * 16 + hex[5];
            c = new Color(tmp1, tmp2, tmp3);
        }
        else {
            c = Color.red;
        }
        return c;
    }
    
    public void setStatusLine() {
        this.showStatus(this.statusText);
    }
    
    public String getAppletInfo() {
        return this.statusText;
    }
    
    public void loadFont() {
        final byte[] b = new byte[80];
        int loc = 0;
        int chr = 0;
        final int pt1 = 0;
        final int pt2 = 0;
        int line = 1;
        int ln2 = -1;
        int first = 0;
        try {
            final InputStream f = new URL(this.getDocumentBase(), this.getParameter("fontfile")).openStream();
            int c;
            while ((c = f.read()) > -1) {
                if (c == 10) {
                    ++line;
                    loc = 0;
                    first = 2;
                    if (ln2 == -1) {
                        continue;
                    }
                    if (ln2 == 0) {
                        final String st = new String(b, 0, 0, 2);
                        this.fontWidth[chr] = Integer.valueOf(st);
                    }
                    else {
                        for (int x = 0; x < this.fontWmax; ++x) {
                            int d = 0;
                            if (b[x] != 32) {
                                d = 1;
                            }
                            this.fontData[chr * this.fontWmax * this.fontHeight + (ln2 - 1) * this.fontWmax + x] = d;
                        }
                    }
                    if (++ln2 < this.fontHeight + 1) {
                        continue;
                    }
                    ln2 = 0;
                    ++chr;
                }
                else {
                    if (line == 2) {
                        b[loc++] = (byte)c;
                        if (loc == 5) {
                            final String st2 = new String(b, 0, 0, 2);
                            this.fontWmax = Integer.valueOf(st2);
                            final String st3 = new String(b, 0, 3, 2);
                            this.fontHeight = Integer.valueOf(st3);
                            this.fontData = new int[this.fontWmax * this.fontHeight * 110];
                        }
                    }
                    if (line == 4 && ln2 == -1) {
                        ln2 = 0;
                    }
                    if (ln2 == -1) {
                        continue;
                    }
                    b[loc++] = (byte)c;
                }
            }
            f.close();
        }
        catch (IOException ex) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        ++this.loaded;
    }
    
    public void loadText() {
        final byte[] b = new byte[160];
        try {
            final InputStream f = new URL(this.getDocumentBase(), this.getParameter("txtfile")).openStream();
            int c;
            while ((c = f.read()) > -1) {
                if (c == 10) {
                    ++this.strCount;
                }
            }
            f.close();
        }
        catch (IOException ex) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        final int i = 0;
        int n = 0;
        int ln = 0;
        if (this.regOK != 1) {
            this.strCount += 9;
        }
        this.txt = new String[this.strCount];
        try {
            final InputStream f = new URL(this.getCodeBase(), this.getParameter("txtfile")).openStream();
            int c;
            while ((c = f.read()) > -1) {
                if (c == 10) {
                    if (n == 0) {
                        n = 1;
                        b[1] = (b[0] = 32);
                    }
                    final String s = new String(b, 0, 0, n);
                    this.txt[ln++] = s;
                    n = 0;
                }
                else {
                    if (c == 10 || c == 13) {
                        c = 32;
                    }
                    b[n++] = (byte)c;
                }
            }
            f.close();
        }
        catch (IOException ex2) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        if (this.regOK != 1) {
            this.txt[ln++] = " ";
            this.txt[ln++] = "This applet was programmed";
            this.txt[ln++] = "by Stefan Mateescu.";
            this.txt[ln++] = "Visit the author by";
            this.txt[ln++] = "clicking on the applet!";
            this.txt[ln++] = " ";
            this.txt[ln++] = " ";
            this.txt[ln++] = " ";
            this.txt[ln++] = " ";
        }
    }
    
    public void convertor() {
        this.regCode = this.getParameter("regCode");
        if (this.regCode.length() >= 11) {
            final int[] adds = { 15, 22, 7, 12, 9, 11, 19, 21, 10, 6, 2, 11, 12, 4, 15, 22, 7, 12, 9, 11 };
            int l1 = 0;
            int l2 = 0;
            int r1 = 0;
            int r2 = 0;
            byte bb = 0;
            String tmp3 = "";
            String tmp4 = "";
            final String docBase = this.getDocumentBase().toString();
            final String codBase = this.getCodeBase().toString();
            if (this.regCode.charAt(0) == 's' && this.regCode.charAt(1) == 't' && this.regCode.charAt(2) == 'm') {
                final int i = this.regCode.length() - 7;
                final byte[] b = new byte[40];
                b[0] = (byte)this.regCode.charAt(3);
                b[1] = (byte)this.regCode.charAt(4);
                final String tmp5 = new String(b, 0, 0, 2);
                l1 = Integer.valueOf(tmp5) - 52;
                b[0] = (byte)this.regCode.charAt(5);
                b[1] = (byte)this.regCode.charAt(6);
                final String tmp6 = new String(b, 0, 0, 2);
                l2 = Integer.valueOf(tmp6) - 7;
                if (i == l1 + l2) {
                    for (int r3 = 0; r3 < l1; ++r3) {
                        int cc = 0;
                        bb = (byte)this.regCode.charAt(r3 + 7);
                        if (bb == 53) {
                            bb = 46;
                            cc = 1;
                        }
                        if (bb == 56) {
                            bb = 47;
                            cc = 1;
                        }
                        if (cc == 0) {
                            int low = 0;
                            if (bb >= 65 && bb <= 90) {
                                low = 1;
                            }
                            bb -= (byte)adds[r3];
                            if (low == 0) {
                                if (bb < 97) {
                                    bb += 26;
                                }
                            }
                            else if (bb < 65) {
                                bb += 26;
                            }
                        }
                        b[r3] = bb;
                    }
                    tmp3 = new String(b, 0, 0, l1);
                    for (int r4 = 0; r4 < l2; ++r4) {
                        int cc2 = 0;
                        bb = (byte)this.regCode.charAt(r4 + l1 + 7);
                        if (bb == 53) {
                            bb = 46;
                            cc2 = 1;
                        }
                        if (bb == 56) {
                            bb = 47;
                            cc2 = 1;
                        }
                        if (cc2 == 0) {
                            int low2 = 0;
                            if (bb >= 65 && bb <= 90) {
                                low2 = 1;
                            }
                            bb -= (byte)adds[r4];
                            if (low2 == 0) {
                                if (bb < 97) {
                                    bb += 26;
                                }
                            }
                            else if (bb < 65) {
                                bb += 26;
                            }
                        }
                        b[r4] = bb;
                    }
                    tmp4 = new String(b, 0, 0, l2);
                    if (l1 != 0) {
                        r1 = 1;
                    }
                    if (l2 != 0) {
                        r2 = 1;
                    }
                    int not = 0;
                    if (l1 != 0) {
                        for (int r5 = 0; r5 < docBase.length() - l1 + 1; ++r5) {
                            int cnt = 0;
                            for (int s = 0; s < l1; ++s) {
                                if (docBase.charAt(s + r5) == tmp3.charAt(s)) {
                                    ++cnt;
                                }
                            }
                            if (cnt == l1) {
                                not = 1;
                            }
                        }
                    }
                    if (not == 1) {
                        ++r1;
                    }
                    not = 0;
                    if (l2 != 0) {
                        for (int r5 = 0; r5 < docBase.length() - l2 + 1; ++r5) {
                            int cnt = 0;
                            for (int s = 0; s < l2; ++s) {
                                if (docBase.charAt(s + r5) == tmp4.charAt(s)) {
                                    ++cnt;
                                }
                            }
                            if (cnt == l2) {
                                not = 1;
                            }
                        }
                    }
                    if (not == 1) {
                        ++r2;
                    }
                    if (r1 == 0 && r2 == 2) {
                        this.regOK = 1;
                    }
                    if (r1 == 2 && r2 == 0) {
                        this.regOK = 1;
                    }
                    if (r1 == 2 && r2 == 2) {
                        this.regOK = 1;
                    }
                    if (this.regOK == 0 && (docBase.charAt(0) == 'f' || docBase.charAt(0) == 'F') && (docBase.charAt(1) == 'i' || docBase.charAt(1) == 'I') && (docBase.charAt(2) == 'l' || docBase.charAt(2) == 'L') && (docBase.charAt(3) == 'e' || docBase.charAt(3) == 'E') && docBase.charAt(4) == ':') {
                        this.regOK = 1;
                    }
                }
            }
        }
    }
    
    public void init() {
        super.init();
        this.SIZEX = this.size().width;
        this.SIZEY = this.size().height;
        this.TRWAIT = Integer.valueOf(this.getParameter("threadWait"));
        this.delay = Integer.valueOf(this.getParameter("delay"));
        this.singleCol = Integer.valueOf(this.getParameter("singleCol"));
        this.BACKCOL = this.convertToColor(this.getParameter("backCol"));
        this.TEXTCOL = this.convertToColor(this.getParameter("textCol"));
        this.showStatus("StarWarsUpscroller is loading... Please wait!");
        this.convertor();
        this.loadFont();
        this.loadText();
        this.offX1 = Integer.valueOf(this.getParameter("offX1"));
        this.offY1 = Integer.valueOf(this.getParameter("offY1"));
        this.offX2 = Integer.valueOf(this.getParameter("offX2"));
        this.offY2 = Integer.valueOf(this.getParameter("offY2"));
        this.offX1center = Integer.valueOf(this.getParameter("offX1center"));
        this.offX2center = Integer.valueOf(this.getParameter("offX2center"));
        if (this.regOK != 0) {
            try {
                this.uLink = new URL(this.getDocumentBase(), this.getParameter("clickLink"));
            }
            catch (MalformedURLException ex) {}
            this.statusText = this.getParameter("statusText");
        }
        else {
            try {
                this.uLink = new URL("http://storm.headlight.de");
            }
            catch (MalformedURLException ex2) {}
        }
        if (Integer.valueOf(this.getParameter("http://storm.headlight.de")) == 1 && Integer.valueOf(this.getParameter("copyright by stefan mateescu")) == 1 && Integer.valueOf(this.getParameter("mateescu@headlight.de")) == 1) {
            this.MODE = 1;
        }
        final int cols = 128;
        final int bpp = 7;
        this.red = new byte[cols];
        this.green = new byte[cols];
        this.blue = new byte[cols];
        final double r1 = this.BACKCOL.getRed();
        final double g1 = this.BACKCOL.getGreen();
        final double b1 = this.BACKCOL.getBlue();
        final double r2 = this.TEXTCOL.getRed();
        final double g2 = this.TEXTCOL.getGreen();
        final double b2 = this.TEXTCOL.getBlue();
        final double rm = (r2 - r1) / cols;
        final double gm = (g2 - g1) / cols;
        final double bm = (b2 - b1) / cols;
        for (int i = 0; i < cols; ++i) {
            this.red[i] = (byte)(rm * i + r1);
            this.green[i] = (byte)(gm * i + g1);
            this.blue[i] = (byte)(bm * i + b1);
        }
        this.red[9] = -1;
        this.colModel = new IndexColorModel(bpp, cols, this.red, this.green, this.blue);
        this.txtbuf = new byte[this.SIZEX * this.SIZEY];
        this.buf = new byte[this.SIZEX * this.SIZEY];
        (this.bufImg = new MemoryImageSource(this.SIZEX, this.SIZEY, this.colModel, this.buf, 0, this.SIZEX)).setAnimated(true);
        this.showImg = this.createImage(this.bufImg);
        this.starWarsInit();
        this.edgeLeft = new int[this.SIZEY];
        this.edgeRite = new int[this.SIZEY];
        this.colRange = new byte[this.SIZEY];
        this.vectorInit();
        ++this.loaded;
        this.showStatus(this.statusText);
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        this.setStatusLine();
        return true;
    }
    
    public boolean mouseMove(final Event e, final int x, final int y) {
        this.setStatusLine();
        return true;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.showStatus(" ");
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        this.pressed = 1;
        return true;
    }
    
    public boolean mouseUp(final Event e, final int x, final int y) {
        if (this.pressed == 1) {
            if (this.regOK == 0) {
                try {
                    final URL u = new URL(this.getDocumentBase(), "http://storm.headlight.de");
                    this.getAppletContext().showDocument(u);
                }
                catch (MalformedURLException ex) {}
            }
            else if (this.getParameter("clickLink").length() > 3) {
                this.getAppletContext().showDocument(this.uLink);
            }
        }
        return true;
    }
    
    public synchronized void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        if (this.MODE == 1) {
            if (this.loaded == 2) {
                int tmp = 0;
                if (this.delay != 0) {
                    if (this.delayPt++ == this.delay) {
                        this.delayPt = 0;
                        tmp = 1;
                    }
                }
                else {
                    tmp = 1;
                }
                if (tmp == 1) {
                    g.drawImage(this.showImg, 0, 0, this);
                }
            }
            else {
                g.setColor(Color.red);
                g.fillRect(0, 0, this.SIZEX, this.SIZEY);
                g.setColor(Color.white);
            }
        }
        else {
            g.setColor(Color.black);
            g.fillRect(0, 0, this.SIZEX, this.SIZEY);
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", 2, 14));
            g.drawString("Sorry! This java-class is not yours!", 10, this.SIZEY / 2 - 7);
            g.drawString("mail to: mateescu@headlight.de for more infos!", 10, this.SIZEY / 2 + 10);
        }
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.TRWAIT);
            }
            catch (InterruptedException ex) {}
            if (this.delayPt == 0) {
                this.starWarsRun();
                this.vectorRun();
                this.bufImg.newPixels();
            }
            this.repaint();
        }
    }
    
    public void starWarsInit() {
        for (int y = 0; y < this.SIZEY; ++y) {
            for (int x = 0; x < this.SIZEX; ++x) {
                this.txtbuf[this.SIZEX * y + x] = 0;
            }
        }
        this.smallBuf = new byte[this.SIZEX * (this.fontHeight + 1)];
        this.um_arr = new double[this.SIZEY];
        this.vm_arr = new double[this.SIZEY];
        this.u_arr = new double[this.SIZEY];
        this.s_arr = new int[this.SIZEY];
        this.e_arr = new int[this.SIZEY];
        this.vx_arr = new int[this.SIZEY];
        this.yx_arr = new int[this.SIZEY];
        for (int y2 = 0; y2 < this.SIZEY; ++y2) {
            this.um_arr[y2] = 0.0;
            this.vm_arr[y2] = 0.0;
            this.vx_arr[y2] = 0;
            this.yx_arr[y2] = 0;
            this.u_arr[y2] = -1.0;
            this.s_arr[y2] = -1;
            this.e_arr[y2] = 0;
        }
    }
    
    public void starWarsRun() {
        for (int y = 0; y < this.txtbufMax - 1; ++y) {
            for (int x = 0; x < this.SIZEX; ++x) {
                this.txtbuf[this.SIZEX * y + x] = this.txtbuf[this.SIZEX * (y + 1) + x];
            }
        }
        for (int x = 0; x < this.SIZEX; ++x) {
            this.txtbuf[this.SIZEX * (this.txtbufMax - 1) + x] = this.smallBuf[this.pt * this.SIZEX + x];
        }
        if (++this.pt > this.fontHeight) {
            this.pt = 0;
            for (int i = 0; i < this.SIZEX * this.fontHeight; ++i) {
                this.smallBuf[i] = 0;
            }
            int offx = 0;
            int midx = 0;
            for (int c = 0; c < this.txt[this.strPt].length(); ++c) {
                byte cc = (byte)((byte)this.txt[this.strPt].charAt(c) - 32);
                if (cc < 0 || cc > 99) {
                    cc = 0;
                }
                midx += this.fontWidth[cc];
            }
            offx = (this.SIZEX - midx) / 2;
            if (offx <= 0) {
                offx = 0;
                midx = 0;
                final String txt = "* line too long! *";
                for (int c2 = 0; c2 < txt.length(); ++c2) {
                    byte cc2 = (byte)((byte)txt.charAt(c2) - 32);
                    if (cc2 < 0 || cc2 > 99) {
                        cc2 = 0;
                    }
                    midx += this.fontWidth[cc2];
                }
                offx = (this.SIZEX - midx) / 2;
                for (int c3 = 0; c3 < txt.length(); ++c3) {
                    byte cc3 = (byte)((byte)txt.charAt(c3) - 32);
                    if (cc3 < 0 || cc3 > 99) {
                        cc3 = 0;
                    }
                    for (int y2 = 0; y2 < this.fontHeight; ++y2) {
                        for (int x2 = 0; x2 < this.fontWmax; ++x2) {
                            if (this.fontData[cc3 * this.fontWmax * this.fontHeight + y2 * this.fontWmax + x2] != 0) {
                                this.smallBuf[y2 * this.SIZEX + x2 + offx] = 9;
                            }
                            else {
                                this.smallBuf[y2 * this.SIZEX + x2 + offx] = 0;
                            }
                        }
                    }
                    offx += this.fontWidth[cc3];
                }
            }
            else {
                for (int c4 = 0; c4 < this.txt[this.strPt].length(); ++c4) {
                    byte cc4 = (byte)((byte)this.txt[this.strPt].charAt(c4) - 32);
                    if (cc4 < 0 || cc4 > 99) {
                        cc4 = 0;
                    }
                    for (int y3 = 0; y3 < this.fontHeight; ++y3) {
                        for (int x3 = 0; x3 < this.fontWmax; ++x3) {
                            if (this.fontData[cc4 * this.fontWmax * this.fontHeight + y3 * this.fontWmax + x3] != 0) {
                                this.smallBuf[y3 * this.SIZEX + x3 + offx] = 9;
                            }
                            else {
                                this.smallBuf[y3 * this.SIZEX + x3 + offx] = 0;
                            }
                        }
                    }
                    offx += this.fontWidth[cc4];
                }
            }
            if (++this.strPt >= this.strCount) {
                this.strPt = 0;
            }
        }
    }
    
    public void vectorInit() {
        this.coords = new int[4][2];
        this.coords[0][0] = this.SIZEX - this.offX1 + this.offX1center;
        this.coords[0][1] = this.offY1;
        this.coords[1][0] = this.SIZEX - this.offX2 + this.offX2center;
        this.coords[1][1] = this.SIZEY + this.offY2;
        this.coords[2][0] = this.offX2 + this.offX2center;
        this.coords[2][1] = this.SIZEY + this.offY2;
        this.coords[3][0] = this.offX1 + this.offX1center;
        this.coords[3][1] = this.offY1;
        final double m1 = (this.coords[1][0] - this.coords[0][0]) / (this.coords[1][1] - this.coords[0][1]);
        double x1 = this.coords[0][0];
        final double m2 = (this.coords[2][0] - this.coords[3][0]) / (this.coords[2][1] - this.coords[3][1]);
        double x2 = this.coords[3][0];
        for (int y = this.coords[0][1]; y < this.SIZEY; ++y) {
            if (y <= this.coords[1][1]) {
                x1 += m1;
                x2 += m2;
                this.edgeRite[y] = (int)x1;
                this.edgeLeft[y] = (int)x2;
                if (this.singleCol == 0) {
                    this.colRange[y] = (byte)((y - this.coords[0][1]) * 117 / (this.SIZEY - this.coords[0][1]) + 10);
                }
                else {
                    this.colRange[y] = 127;
                }
            }
        }
        int SIZ;
        if (this.SIZEX >= this.SIZEY) {
            SIZ = this.SIZEY;
        }
        else {
            SIZ = this.SIZEX;
        }
        if (this.SIZEX == this.SIZEY) {
            SIZ = SIZ * 4 / 5;
        }
        double s = 0.0;
        double e = 0.0;
        double v = 0.0;
        double vm = 0.0;
        for (int y = this.coords[0][1]; y < this.SIZEY; ++y) {
            if (y <= this.coords[1][1]) {
                s = this.edgeLeft[y];
                e = this.edgeRite[y];
                double u = 0.0;
                this.um_arr[y] = this.SIZEX / (e - s);
                this.vm_arr[y] = SIZ / (e - s);
                final double um = this.um_arr[y];
                vm = this.vm_arr[y];
                this.vx_arr[y] = (int)v * this.SIZEX;
                this.yx_arr[y] = y * this.SIZEX;
                for (int x3 = (int)s; x3 < (int)e; ++x3) {
                    if (x3 >= 0 && x3 < this.SIZEX) {
                        if (this.u_arr[y] == -1.0) {
                            this.u_arr[y] = u;
                        }
                        if (this.s_arr[y] == -1) {
                            this.s_arr[y] = x3;
                        }
                        this.e_arr[y] = x3;
                    }
                    u += um;
                }
            }
            v += vm;
        }
        if (v + 1.0 < this.SIZEY) {
            this.txtbufMax = (int)(v + 1.0);
        }
        else {
            this.txtbufMax = this.SIZEY;
        }
        for (int i = 0; i < 360; ++i) {
            this.sinTable[i] = Math.sin(i * 2 * 3.141592653589793 / 360.0);
            this.cosTable[i] = Math.cos(i * 2 * 3.141592653589793 / 360.0);
        }
    }
    
    public void vectorRun() {
        double v = 0.0;
        double vm = 0.0;
        for (int y = this.coords[0][1]; y < this.SIZEY; ++y) {
            if (y <= this.coords[1][1]) {
                final double s = this.edgeLeft[y];
                final double e = this.edgeRite[y];
                double u = this.u_arr[y];
                final double um = this.um_arr[y];
                vm = this.vm_arr[y];
                for (int x = this.s_arr[y]; x < this.e_arr[y]; ++x) {
                    final int c = this.txtbuf[this.vx_arr[y] + (int)u];
                    if (c == 9) {
                        this.buf[this.yx_arr[y] + x] = this.colRange[y];
                    }
                    else {
                        this.buf[this.yx_arr[y] + x] = 0;
                    }
                    u += um;
                }
            }
            v += vm;
        }
    }
    
    public StarWarsScroller() {
        this.TRWAIT = 30;
        this.statusText = "StarWarsUpscroller LITE (C) by Stefan Mateescu (mateescu@headlight.de)";
        this.sinTable = new double[360];
        this.cosTable = new double[360];
        this.offX1 = 60;
        this.offY1 = 30;
        this.offX2 = -100;
        this.offY2 = 100;
        this.fontHeight = 15;
        this.fontWmax = 15;
        this.fontWidth = new int[110];
    }
}
