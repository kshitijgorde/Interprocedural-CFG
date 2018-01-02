import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hlinetyper2 extends Applet implements Runnable
{
    Thread runner;
    String target_frame;
    String infostr;
    String regcode;
    String bs;
    String bosluk;
    String kelime;
    String[] ialign;
    String[] ivalign;
    Image offimg;
    Image gci2;
    Graphics offScreeng;
    Graphics offg2;
    int durum;
    int cri;
    int i;
    int j;
    int inout;
    int maxs;
    int maxsh;
    int a;
    int b;
    int c;
    int border_thickness;
    int m;
    int xx;
    int yy;
    int hstatus;
    int scrolldelay;
    int typedelay;
    int num;
    int w;
    int h;
    int bos;
    int scsay;
    int satircri;
    int hsatircri;
    int harfcri;
    int hharfcri;
    int tort;
    int id;
    int ij;
    boolean devam;
    boolean rg;
    boolean whb;
    URL u;
    int[] xoffset;
    int[] yoffset;
    int[] rightm;
    int[] satirsay;
    int[] satirsayh;
    String[] str;
    String[] links;
    String[] stb;
    String[] tit;
    int[][] yspace;
    int[][] maxas;
    int[][] maxdes;
    int[][][] xht;
    int[][][] yht;
    int[] vi;
    int[] ysize;
    int[] dur;
    int[] dly;
    StringTokenizer st1;
    StringTokenizer st2;
    StringTokenizer st3;
    FontMetrics[][] fmetrics;
    Color borderc;
    Color[] bcd;
    Color[] ftc;
    Color[] hfc;
    Color[] ttc;
    Color[] thc;
    Font[][] nf;
    String[][] satir;
    String[][] satirh;
    char[][][] harfler;
    char[][][] harflerh;
    Frame frm;
    Container container;
    
    public void init() {
        this.w = -1;
        this.h = -1;
        this.whb = false;
        this.xx = 0;
        this.yy = 0;
        this.i = 1;
        while (true) {
            this.bs = this.getParameter("text" + this.i);
            if (this.bs == null) {
                break;
            }
            ++this.i;
        }
        --this.i;
        if (this.i < 0) {
            this.i = 0;
        }
        this.num = this.i;
        this.bcd = new Color[this.num + 1];
        this.ftc = new Color[this.num + 1];
        this.hfc = new Color[this.num + 1];
        this.ttc = new Color[this.num + 1];
        this.thc = new Color[this.num + 1];
        this.satir = new String[this.num + 1][201];
        this.satirh = new String[this.num + 1][201];
        this.str = new String[this.num + 1];
        this.ialign = new String[this.num + 1];
        this.ivalign = new String[this.num + 1];
        this.links = new String[this.num + 1];
        this.stb = new String[this.num + 1];
        this.tit = new String[this.num + 1];
        this.yspace = new int[2][this.num + 1];
        this.xoffset = new int[this.num + 1];
        this.yoffset = new int[this.num + 1];
        this.rightm = new int[this.num + 1];
        this.dur = new int[this.num + 1];
        this.satirsay = new int[this.num + 1];
        this.satirsayh = new int[this.num + 1];
        this.nf = new Font[2][this.num + 1];
        this.ysize = new int[this.num + 1];
        this.vi = new int[this.num + 1];
        this.maxas = new int[2][this.num + 1];
        this.maxdes = new int[2][this.num + 1];
        this.fmetrics = new FontMetrics[2][this.num + 1];
        this.dly = new int[this.num + 1];
        this.infostr = this.getParameter("info");
        if (this.infostr != null) {
            if (!this.infostr.equals("Applet by Gokhan Dagli,www.appletcollection.com")) {
                this.durum = 1;
            }
        }
        else {
            this.durum = 1;
        }
        this.scrolldelay = this.fi(this.getParameter("scroll_delay"), 10, 5);
        this.typedelay = this.fi(this.getParameter("type_delay"), 10, 20);
        this.border_thickness = this.fi(this.getParameter("border_thickness"), 10, 0);
        this.borderc = new Color(this.fi(this.getParameter("border_color"), 16, 0));
        this.i = 1;
        while (this.i <= this.num) {
            this.str[this.i] = this.getParameter("text" + this.i);
            this.tit[this.i] = this.getParameter("title" + this.i);
            this.links[this.i] = this.getParameter("link" + this.i);
            this.stb[this.i] = this.getParameter("status_bar_msg" + this.i);
            this.st1 = new StringTokenizer(this.getParameter("font" + this.i), ",");
            this.nf[0][this.i] = new Font(this.st1.nextToken(), Integer.valueOf(this.st1.nextToken()), Integer.valueOf(this.st1.nextToken()));
            this.yspace[0][this.i] = Integer.valueOf(this.st1.nextToken());
            this.st1 = new StringTokenizer(this.getParameter("title_font" + this.i), ",");
            this.nf[1][this.i] = new Font(this.st1.nextToken(), Integer.valueOf(this.st1.nextToken()), Integer.valueOf(this.st1.nextToken()));
            this.yspace[1][this.i] = Integer.valueOf(this.st1.nextToken());
            this.fmetrics[0][this.i] = this.getFontMetrics(this.nf[0][this.i]);
            this.fmetrics[1][this.i] = this.getFontMetrics(this.nf[1][this.i]);
            this.st1 = new StringTokenizer(this.getParameter("color" + this.i), ",");
            this.bcd[this.i] = new Color(this.fi(this.st1.nextToken(), 16, 16777215));
            this.ttc[this.i] = new Color(this.fi(this.st1.nextToken(), 16, 0));
            this.ftc[this.i] = new Color(this.fi(this.st1.nextToken(), 16, 0));
            this.thc[this.i] = new Color(this.fi(this.st1.nextToken(), 16, 0));
            this.hfc[this.i] = new Color(this.fi(this.st1.nextToken(), 16, 0));
            String parameter = this.getParameter("text_align_valign" + this.i);
            if (parameter == null) {
                parameter = "center,center";
            }
            if (parameter.equals("")) {
                parameter = "center,center";
            }
            this.st1 = new StringTokenizer(parameter, ",");
            this.ialign[this.i] = this.st1.nextToken();
            this.ivalign[this.i] = this.st1.nextToken();
            String parameter2 = this.getParameter("offset" + this.i);
            if (parameter2 == null) {
                parameter2 = "0,0";
            }
            if (parameter2.equals("")) {
                parameter2 = "0,0";
            }
            this.st1 = new StringTokenizer(parameter2, ",");
            this.xoffset[this.i] = this.fi(this.st1.nextToken(), 10, 0);
            this.yoffset[this.i] = this.fi(this.st1.nextToken(), 10, 0);
            this.rightm[this.i] = this.fi(this.st1.nextToken(), 10, 0);
            this.dly[this.i] = this.fi(this.getParameter("delay" + this.i), 10, 2000);
            if (this.links[this.i] == null) {
                this.dur[this.i] = 0;
            }
            else {
                this.dur[this.i] = 1;
                if (this.links[this.i].equals("") || this.links[this.i].equals(" ")) {
                    this.dur[this.i] = 0;
                }
            }
            ++this.i;
        }
        this.i = 1;
        while (this.i <= this.num) {
            this.maxas[0][this.i] = this.fmetrics[0][this.i].getAscent();
            this.maxdes[0][this.i] = this.fmetrics[0][this.i].getDescent();
            this.maxas[1][this.i] = this.fmetrics[1][this.i].getAscent();
            this.maxdes[1][this.i] = this.fmetrics[1][this.i].getDescent();
            ++this.i;
        }
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null && (this.regcode.equals("hetw926r") || this.regcode.equals("txta4g65r"))) {
            this.rg = true;
        }
        this.target_frame = this.getParameter("target_frame");
        this.show();
        int n = 1;
        final String parameter3 = this.getParameter("applet_width");
        if (parameter3 == null || parameter3.trim().equals("")) {
            n = 0;
        }
        final String parameter4 = this.getParameter("applet_height");
        if (parameter4 == null || parameter4.trim().equals("")) {
            n = 0;
        }
        try {
            if (n == 1) {
                this.w = Integer.parseInt(parameter3, 10);
                this.h = Integer.parseInt(parameter4, 10);
            }
            else {
                this.w = this.size().width;
                this.h = this.size().height;
            }
        }
        catch (Exception ex) {
            this.w = this.size().width;
            this.h = this.size().height;
        }
        this.i = 1;
        while (this.i <= this.num) {
            this.a = 1;
            this.b = 0;
            this.c = this.w - this.xoffset[this.i] - this.rightm[this.i];
            this.st3 = new StringTokenizer(this.tit[this.i], "#");
            this.j = 1;
            this.bos = this.fmetrics[1][this.i].stringWidth(this.bosluk);
            this.satirh[this.i][1] = "";
            while (this.st3.hasMoreTokens()) {
                this.bs = this.st3.nextToken();
                this.st2 = new StringTokenizer(this.bs, " ");
                while (this.st2.hasMoreTokens()) {
                    this.kelime = this.st2.nextToken();
                    if (this.fmetrics[1][this.i].stringWidth(this.satirh[this.i][this.a]) + this.fmetrics[1][this.i].stringWidth(this.kelime) + this.bos <= this.c) {
                        this.satirh[this.i][this.a] = this.satirh[this.i][this.a] + this.kelime + " ";
                    }
                    else {
                        ++this.a;
                        this.satirh[this.i][this.a] = "";
                        this.satirh[this.i][this.a] = this.satirh[this.i][this.a] + this.kelime + " ";
                    }
                    ++this.j;
                }
                ++this.a;
                this.satirh[this.i][this.a] = "";
            }
            this.satirsayh[this.i] = this.a - 1;
            this.a = 1;
            this.b = 0;
            this.st3 = new StringTokenizer(this.str[this.i], "#");
            this.j = 1;
            this.bos = this.fmetrics[0][this.i].stringWidth(this.bosluk);
            this.satir[this.i][1] = "";
            while (this.st3.hasMoreTokens()) {
                this.bs = this.st3.nextToken();
                this.st2 = new StringTokenizer(this.bs, " ");
                while (this.st2.hasMoreTokens()) {
                    this.kelime = this.st2.nextToken();
                    if (this.fmetrics[0][this.i].stringWidth(this.satir[this.i][this.a]) + this.fmetrics[0][this.i].stringWidth(this.kelime) + this.bos <= this.c) {
                        this.satir[this.i][this.a] = this.satir[this.i][this.a] + this.kelime + " ";
                    }
                    else {
                        ++this.a;
                        this.satir[this.i][this.a] = "";
                        this.satir[this.i][this.a] = this.satir[this.i][this.a] + this.kelime + " ";
                    }
                    ++this.j;
                }
                ++this.a;
                this.satir[this.i][this.a] = "";
            }
            this.satirsay[this.i] = this.a - 1;
            this.ysize[this.i] = this.satirsay[this.i] * (this.maxas[0][this.i] + this.maxdes[0][this.i]) + (this.satirsay[this.i] - 1) * this.yspace[0][this.i] + this.satirsayh[this.i] * (this.maxas[1][this.i] + this.maxdes[1][this.i]) + this.satirsayh[this.i] * this.yspace[1][this.i];
            ++this.i;
        }
        this.xht = new int[2][this.num + 1][201];
        this.yht = new int[2][this.num + 1][201];
        this.gci2 = this.createImage(this.w, this.h);
        this.offg2 = this.gci2.getGraphics();
        this.i = 1;
        while (this.i <= this.num) {
            this.typer();
            this.maxs = Math.max(this.satirsay[this.i], this.maxs);
            this.maxsh = Math.max(this.satirsayh[this.i], this.maxsh);
            ++this.i;
        }
        this.harfler = new char[this.num + 1][this.maxs + 1][400];
        this.harflerh = new char[this.num + 1][this.maxsh + 1][400];
        this.i = 1;
        while (this.i <= this.num) {
            this.j = 1;
            while (this.j <= this.satirsay[this.i]) {
                this.harfler[this.i][this.j] = this.satir[this.i][this.j].toCharArray();
                ++this.j;
            }
            this.j = 1;
            while (this.j <= this.satirsayh[this.i]) {
                this.harflerh[this.i][this.j] = this.satirh[this.i][this.j].toCharArray();
                ++this.j;
            }
            ++this.i;
        }
        System.gc();
        this.offimg = this.createImage(this.w, this.h);
        (this.offScreeng = this.offimg.getGraphics()).clipRect(0, 0, this.w, this.h);
        this.offg2.clipRect(0, 0, this.w, this.h);
        this.cri = 1;
        this.container = this.getParent();
        while (!(this.container instanceof Frame)) {
            this.container = this.container.getParent();
        }
        this.frm = (Frame)this.container;
        this.whb = true;
    }
    
    public void typer() {
        this.vi[this.i] = this.yoffset[this.i];
        if (this.ivalign[this.i] != null && this.ysize[this.i] < this.h) {
            if (this.ivalign[this.i].equals("center")) {
                this.vi[this.i] = (this.h - this.yoffset[this.i] - this.ysize[this.i]) / 2 + this.yoffset[this.i];
            }
            if (this.ivalign[this.i].equals("bottom")) {
                this.vi[this.i] = this.h - this.ysize[this.i];
            }
        }
        this.j = 1;
        while (this.j <= this.satirsayh[this.i]) {
            int n = this.xoffset[this.i];
            final String s = this.satirh[this.i][this.j];
            this.vi[this.i] += this.maxas[1][this.i];
            if (this.ialign[this.i] != null) {
                final int stringWidth = this.fmetrics[1][this.i].stringWidth(s);
                if (this.ialign[this.i].equals("center")) {
                    n = (this.w - this.xoffset[this.i] - this.rightm[this.i] - stringWidth) / 2 + this.xoffset[this.i];
                }
                if (this.ialign[this.i].equals("right")) {
                    n = this.w - stringWidth;
                }
            }
            this.xht[1][this.i][this.j] = n;
            this.yht[1][this.i][this.j] = this.vi[this.i];
            this.vi[this.i] = this.vi[this.i] + this.maxdes[1][this.i] + this.yspace[1][this.i];
            ++this.j;
        }
        this.j = 1;
        while (this.j <= this.satirsay[this.i]) {
            int n2 = this.xoffset[this.i];
            final String s2 = this.satir[this.i][this.j];
            this.vi[this.i] += this.maxas[0][this.i];
            if (this.ialign[this.i] != null) {
                final int stringWidth2 = this.fmetrics[0][this.i].stringWidth(s2);
                if (this.ialign[this.i].equals("center")) {
                    n2 = (this.w - this.xoffset[this.i] - this.rightm[this.i] - stringWidth2) / 2 + this.xoffset[this.i];
                }
                if (this.ialign[this.i].equals("right")) {
                    n2 = this.w - stringWidth2;
                }
            }
            if (this.durum == 1) {
                this.satir[this.i][this.j] = "info parameter error!";
            }
            this.xht[0][this.i][this.j] = n2;
            this.yht[0][this.i][this.j] = this.vi[this.i];
            this.vi[this.i] = this.vi[this.i] + this.maxdes[0][this.i] + this.yspace[0][this.i];
            ++this.j;
        }
    }
    
    public int fi(final String s, final int n, final int n2) {
        int int1;
        try {
            int1 = Integer.parseInt(s, n);
        }
        catch (Exception ex) {
            int1 = n2;
        }
        return int1;
    }
    
    public void ssts() {
        if (this.whb && this.inout == 1) {
            if (!this.rg) {
                this.showStatus("  Unregistered Headline Typer Java Applet. http://www.appletcollection.com/");
            }
            else if (this.stb[this.cri] != null) {
                this.showStatus("" + this.stb[this.cri]);
            }
            if (this.dur[this.cri] == 0) {
                this.sfc(0);
                return;
            }
            this.sfc(12);
        }
    }
    
    public void sfc(final int cursor) {
        this.frm.setCursor(cursor);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.durum != 2) {
            return true;
        }
        this.inout = 1;
        this.ssts();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (this.durum != 2) {
                return true;
            }
            this.inout = 1;
            if (this.hstatus == 1) {
                this.drwh();
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.whb) {
            this.inout = 0;
            this.showStatus("");
            this.sfc(0);
            if (this.hstatus == 1) {
                this.drwh();
            }
            this.repaint();
        }
        return true;
    }
    
    public void drwh() {
        if (this.whb) {
            if (this.durum != 2) {
                return;
            }
            this.offScreeng.setColor(this.bcd[this.cri]);
            this.offScreeng.fillRect(0, 0, this.w, this.h);
            this.offScreeng.setFont(this.nf[1][this.cri]);
            if (this.inout == 1 && this.dur[this.cri] == 1) {
                this.offScreeng.setColor(this.thc[this.cri]);
            }
            else {
                this.offScreeng.setColor(this.ttc[this.cri]);
            }
            final int n = this.satirsayh[this.cri];
            this.id = 1;
            while (this.id <= n) {
                this.offScreeng.drawString(this.satirh[this.cri][this.id], this.xht[1][this.cri][this.id], this.yht[1][this.cri][this.id]);
                ++this.id;
            }
            this.offScreeng.setFont(this.nf[0][this.cri]);
            if (this.inout == 1 && this.dur[this.cri] == 1) {
                this.offScreeng.setColor(this.hfc[this.cri]);
            }
            else {
                this.offScreeng.setColor(this.ftc[this.cri]);
            }
            final int n2 = this.satirsay[this.cri];
            this.id = 1;
            while (this.id <= n2) {
                this.offScreeng.drawString(this.satir[this.cri][this.id], this.xht[0][this.cri][this.id], this.yht[0][this.cri][this.id]);
                ++this.id;
            }
            this.offg2.drawImage(this.offimg, 0, -1 * this.scsay, null);
            if (this.border_thickness > 0) {
                this.offg2.setColor(this.borderc);
                this.m = 1;
                while (this.m <= this.border_thickness) {
                    this.offg2.drawRect(this.m - 1, this.m - 1, this.w - 2 * this.m + 1, this.h - 2 * this.m + 1);
                    ++this.m;
                }
            }
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (this.dur[this.cri] == 0) {
                return true;
            }
            try {
                if (this.links[this.cri].startsWith("http://") || this.links[this.cri].startsWith("mailto:")) {
                    this.u = new URL("" + this.links[this.cri]);
                }
                else {
                    this.u = new URL(this.getCodeBase(), "" + this.links[this.cri]);
                }
            }
            catch (Exception ex) {
                return true;
            }
            this.getAppletContext().showDocument(this.u, this.target_frame);
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        if (this.whb) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.whb) {
            graphics.drawImage(this.gci2, 0, 0, this);
        }
    }
    
    public void start() {
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
        }
        this.runner.start();
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void destroy() {
        this.runner = null;
    }
    
    public void run() {
        this.xx = 0;
        this.yy = 0;
        this.cri = 1;
        this.durum = 2;
        this.xx = 0;
        this.yy = 0;
        this.satircri = 1;
        this.hsatircri = 1;
        this.harfcri = 0;
        this.hharfcri = 0;
        this.tort = 0;
        this.repaint();
        while (true) {
            this.offScreeng.setColor(this.bcd[this.cri]);
            this.offScreeng.fillRect(0, 0, this.w, this.h);
            if (this.tort == 1) {
                this.offScreeng.setFont(this.nf[1][this.cri]);
                if (this.inout == 1 && this.dur[this.cri] == 1) {
                    this.offScreeng.setColor(this.thc[this.cri]);
                }
                else {
                    this.offScreeng.setColor(this.ttc[this.cri]);
                }
                this.hsatircri = this.satirsayh[this.cri];
                this.id = 1;
                while (this.id <= this.hsatircri) {
                    this.offScreeng.drawString(this.satirh[this.cri][this.id], this.xht[1][this.cri][this.id], this.yht[1][this.cri][this.id]);
                    ++this.id;
                }
                this.offScreeng.setFont(this.nf[0][this.cri]);
                if (this.inout == 1 && this.dur[this.cri] == 1) {
                    this.offScreeng.setColor(this.hfc[this.cri]);
                }
                else {
                    this.offScreeng.setColor(this.ftc[this.cri]);
                }
                this.id = 1;
                while (this.id <= this.satircri - 1) {
                    this.offScreeng.drawString(this.satir[this.cri][this.id], this.xht[0][this.cri][this.id], this.yht[0][this.cri][this.id]);
                    ++this.id;
                }
                this.offScreeng.drawChars(this.harfler[this.cri][this.satircri], 0, this.harfcri, this.xht[0][this.cri][this.satircri], this.yht[0][this.cri][this.satircri]);
                ++this.harfcri;
                if (this.harfcri > this.satir[this.cri][this.satircri].length()) {
                    ++this.satircri;
                    this.harfcri = 1;
                    if (this.satircri > this.satirsay[this.cri]) {
                        this.tort = 0;
                        this.satircri = 1;
                    }
                }
                if (this.tort == 0) {
                    this.hsatircri = 1;
                    this.hharfcri = 0;
                    this.hstatus = 1;
                    this.scsay = 0;
                    try {
                        Thread.sleep(this.dly[this.cri]);
                    }
                    catch (InterruptedException ex) {}
                    this.scsay = 0;
                    do {
                        this.offg2.drawImage(this.offimg, 0, -1 * this.scsay, null);
                        if (this.border_thickness > 0) {
                            this.offg2.setColor(this.borderc);
                            this.m = 1;
                            while (this.m <= this.border_thickness) {
                                this.offg2.drawRect(this.m - 1, this.m - 1, this.w - 2 * this.m + 1, this.h - 2 * this.m + 1);
                                ++this.m;
                            }
                        }
                        this.repaint();
                        try {
                            Thread.sleep(this.scrolldelay);
                        }
                        catch (InterruptedException ex2) {}
                        ++this.scsay;
                    } while (this.scsay <= this.h);
                    this.hstatus = 0;
                    ++this.cri;
                    if (this.cri > this.num) {
                        this.cri = 1;
                    }
                    this.ssts();
                }
            }
            else {
                this.scsay = 0;
                this.offScreeng.setFont(this.nf[1][this.cri]);
                if (this.inout == 1 && this.dur[this.cri] == 1) {
                    this.offScreeng.setColor(this.thc[this.cri]);
                }
                else {
                    this.offScreeng.setColor(this.ttc[this.cri]);
                }
                ++this.hharfcri;
                if (this.hharfcri >= this.satirh[this.cri][this.hsatircri].length()) {
                    ++this.hsatircri;
                    this.hharfcri = 1;
                    if (this.hsatircri > this.satirsayh[this.cri]) {
                        --this.hsatircri;
                        this.hharfcri = this.satirh[this.cri][this.hsatircri].length();
                        this.tort = 1;
                    }
                }
                this.id = 1;
                while (this.id <= this.hsatircri - 1) {
                    this.offScreeng.drawString(this.satirh[this.cri][this.id], this.xht[1][this.cri][this.id], this.yht[1][this.cri][this.id]);
                    ++this.id;
                }
                this.offScreeng.drawChars(this.harflerh[this.cri][this.hsatircri], 0, this.hharfcri, this.xht[1][this.cri][this.hsatircri], this.yht[1][this.cri][this.hsatircri]);
            }
            this.offg2.drawImage(this.offimg, 0, -1 * this.scsay, null);
            if (this.border_thickness > 0) {
                this.offg2.setColor(this.borderc);
                this.m = 1;
                while (this.m <= this.border_thickness) {
                    this.offg2.drawRect(this.m - 1, this.m - 1, this.w - 2 * this.m + 1, this.h - 2 * this.m + 1);
                    ++this.m;
                }
            }
            this.repaint();
            try {
                Thread.sleep(this.typedelay);
            }
            catch (InterruptedException ex3) {}
        }
    }
    
    public hlinetyper2() {
        this.target_frame = "";
        this.infostr = "";
        this.regcode = "";
        this.bs = "";
        this.bosluk = " ";
        this.kelime = "";
        this.cri = 1;
        this.devam = false;
        this.rg = false;
        this.whb = false;
    }
}
