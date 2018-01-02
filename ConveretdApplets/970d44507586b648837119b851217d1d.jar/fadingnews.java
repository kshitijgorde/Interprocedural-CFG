import java.awt.Frame;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fadingnews extends Applet implements Runnable
{
    Thread runner;
    int w;
    int h;
    int border_thickness;
    int i;
    int num;
    int left_margin;
    int top_margin;
    int right_margin;
    int a;
    int b;
    int c;
    int j;
    int bos;
    int tmaxas;
    int tmaxdes;
    int maxas;
    int maxdes;
    int yspace_title;
    int yspace_text;
    int durum;
    String kelime;
    String infostr;
    String regcode;
    Color bgc;
    Color titlec;
    Color textc;
    Color htitlec;
    Color htextc;
    Color borderc;
    Color hborderc;
    Color currborderc;
    Color tfadecolor;
    Color fadecolor;
    String align;
    String valign;
    String ft;
    String bs;
    String title_underline;
    String text_underline;
    String bosluk;
    Font tnf;
    Font nf;
    FontMetrics tfmetrics;
    FontMetrics fmetrics;
    String[] str;
    String[] links;
    String[] stb;
    String[] tit;
    int[] dur;
    int[] satirsayh;
    String[][] satirh;
    int[] satirsay;
    String[][] satir;
    StringTokenizer st1;
    StringTokenizer st2;
    StringTokenizer st3;
    String[] target_frame;
    int[] vi;
    int[][][] xht;
    int[][][] yht;
    int[] ysize;
    int rb;
    int gb;
    int bb;
    int rt;
    int gt;
    int bt;
    int rh;
    int gh;
    int bh;
    int rc;
    int gc;
    int bc;
    int colc;
    int re;
    int ge;
    int be;
    int rhtit;
    int ghtit;
    int bhtit;
    int retit;
    int getit;
    int betit;
    int rttit;
    int gttit;
    int bttit;
    Image offimg;
    Image offimg2;
    Graphics offg;
    Graphics offg2;
    int fade_delay;
    int sdly;
    int crr;
    int m;
    int coloc;
    int pause_time;
    int yon;
    int inout;
    int basla;
    int ilkmove;
    int linew;
    URL u;
    boolean rg;
    boolean whb;
    
    public void init() {
        this.basla = 0;
        this.durum = 0;
        this.whb = false;
        this.infostr = this.getParameter("copyright");
        if (this.infostr != null) {
            if (!this.infostr.equals("Copyright (c) 2001 BruceW Anderson,http://appletlib.tripod.com")) {
                this.durum = 1;
            }
        }
        else {
            this.durum = 1;
        }
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null && (this.regcode.equals("nfehues") || this.regcode.equals("cohnrw"))) {
            this.rg = true;
        }
        this.bgc = new Color(this.fi(this.getParameter("bgcolor"), 16, 16777215));
        this.titlec = new Color(this.fi(this.getParameter("title_color"), 16, 0));
        this.textc = new Color(this.fi(this.getParameter("text_color"), 16, 0));
        this.htitlec = new Color(this.fi(this.getParameter("highlight_title_color"), 16, 0));
        this.htextc = new Color(this.fi(this.getParameter("highlight_text_color"), 16, 0));
        this.align = this.getParameter("align");
        if (this.align == null) {
            this.align = "left";
        }
        this.valign = this.getParameter("valign");
        if (this.valign == null) {
            this.valign = "top";
        }
        this.align = this.getParameter("align");
        if (this.align == null) {
            this.align = "left";
        }
        this.ft = this.getParameter("title_font_type");
        if (this.ft == null) {
            this.ft = "Arial";
        }
        this.tnf = new Font(this.ft, this.fi(this.getParameter("title_font_style"), 10, 1), this.fi(this.getParameter("title_font_size"), 10, 12));
        this.tfmetrics = this.getFontMetrics(this.tnf);
        this.ft = this.getParameter("text_font_type");
        if (this.ft == null) {
            this.ft = "Arial";
        }
        this.nf = new Font(this.ft, this.fi(this.getParameter("text_font_style"), 10, 1), this.fi(this.getParameter("text_font_size"), 10, 12));
        this.fmetrics = this.getFontMetrics(this.nf);
        this.border_thickness = this.fi(this.getParameter("border_thickness"), 10, 0);
        this.borderc = new Color(this.fi(this.getParameter("border_color"), 16, 0));
        this.hborderc = new Color(this.fi(this.getParameter("highlight_border_color"), 16, 0));
        this.i = 1;
        while (true) {
            this.bs = this.getParameter("text" + this.i);
            if (this.bs == null) {
                --this.i;
                if (this.i < 0) {
                    this.i = 0;
                    break;
                }
                break;
            }
            else if (this.bs.trim().equals("")) {
                --this.i;
                if (this.i < 0) {
                    this.i = 0;
                    break;
                }
                break;
            }
            else {
                ++this.i;
            }
        }
        this.num = this.i;
        this.str = new String[this.num + 1];
        this.links = new String[this.num + 1];
        this.stb = new String[this.num + 1];
        this.tit = new String[this.num + 1];
        this.dur = new int[this.num + 1];
        this.satirsayh = new int[this.num + 1];
        this.satirh = new String[this.num + 1][101];
        this.satir = new String[this.num + 1][101];
        this.satirsay = new int[this.num + 1];
        this.vi = new int[this.num + 1];
        this.xht = new int[2][this.num + 1][101];
        this.yht = new int[2][this.num + 1][101];
        this.ysize = new int[this.num + 1];
        this.target_frame = new String[this.num + 1];
        this.i = 1;
        while (this.i <= this.num) {
            this.str[this.i] = this.getParameter("text" + this.i);
            this.tit[this.i] = this.getParameter("title" + this.i);
            this.links[this.i] = this.getParameter("link" + this.i);
            if (this.links[this.i] == null) {
                this.dur[this.i] = 0;
            }
            else {
                this.dur[this.i] = 1;
                if (this.links[this.i].trim().equals("")) {
                    this.dur[this.i] = 0;
                }
            }
            if (this.dur[this.i] == 0) {
                this.stb[this.i] = "";
            }
            else {
                this.target_frame[this.i] = this.getParameter("target_frame" + this.i);
                if (this.target_frame[this.i] == null) {
                    this.target_frame[this.i] = "_self";
                }
                else if (this.target_frame[this.i].trim().equals("")) {
                    this.target_frame[this.i] = "_self";
                }
                if (this.links[this.i].startsWith("http://")) {
                    this.stb[this.i] = this.links[this.i];
                }
                else {
                    this.stb[this.i] = "" + this.getCodeBase() + "" + this.links[this.i];
                }
            }
            ++this.i;
        }
        this.title_underline = this.getParameter("title_underline");
        this.text_underline = this.getParameter("text_underline");
        if (this.title_underline == null) {
            this.title_underline = "no";
        }
        if (this.text_underline == null) {
            this.text_underline = "no";
        }
        this.left_margin = this.fi(this.getParameter("left_margin"), 10, 0);
        this.top_margin = this.fi(this.getParameter("top_margin"), 10, 0);
        this.right_margin = this.fi(this.getParameter("right_margin"), 10, 0);
        this.tmaxas = this.tfmetrics.getAscent();
        this.tmaxdes = this.tfmetrics.getDescent();
        this.maxas = this.fmetrics.getAscent();
        this.maxdes = this.fmetrics.getDescent();
        this.yspace_title = this.fi(this.getParameter("line_space_fortitle"), 10, 0);
        this.yspace_text = this.fi(this.getParameter("line_space_fortext"), 10, 0);
    }
    
    public int fi(final String s, final int n, final int n2) {
        if (s == null) {
            return n2;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s, n);
        }
        catch (Exception ex) {
            int1 = n2;
        }
        return int1;
    }
    
    public void typer() {
        this.vi[this.i] = this.top_margin;
        if (this.valign != null && this.ysize[this.i] < this.h) {
            if (this.valign.equals("center")) {
                this.vi[this.i] = (this.h - this.top_margin - this.ysize[this.i]) / 2 + this.top_margin;
            }
            if (this.valign.equals("bottom")) {
                this.vi[this.i] = this.h - this.ysize[this.i];
            }
        }
        this.j = 1;
        while (this.j <= this.satirsayh[this.i]) {
            int left_margin = this.left_margin;
            final String s = this.satirh[this.i][this.j];
            this.vi[this.i] += this.tmaxas;
            if (this.align != null) {
                final int stringWidth = this.tfmetrics.stringWidth(s);
                if (this.align.equals("center")) {
                    left_margin = (this.w - this.left_margin - this.right_margin - stringWidth) / 2 + this.left_margin;
                }
                if (this.align.equals("right")) {
                    left_margin = this.w - stringWidth;
                }
            }
            this.xht[1][this.i][this.j] = left_margin;
            this.yht[1][this.i][this.j] = this.vi[this.i];
            this.vi[this.i] = this.vi[this.i] + this.tmaxdes + this.yspace_title;
            ++this.j;
        }
        this.j = 1;
        while (this.j <= this.satirsay[this.i]) {
            int left_margin2 = this.left_margin;
            final String s2 = this.satir[this.i][this.j];
            this.vi[this.i] += this.maxas;
            if (this.align != null) {
                final int stringWidth2 = this.fmetrics.stringWidth(s2);
                if (this.align.equals("center")) {
                    left_margin2 = (this.w - this.left_margin - this.right_margin - stringWidth2) / 2 + this.left_margin;
                }
                if (this.align.equals("right")) {
                    left_margin2 = this.w - stringWidth2;
                }
            }
            if (this.durum == 1) {
                this.satir[this.i][this.j] = "info parameter error!";
            }
            this.xht[0][this.i][this.j] = left_margin2;
            this.yht[0][this.i][this.j] = this.vi[this.i];
            this.vi[this.i] = this.vi[this.i] + this.maxdes + this.yspace_text;
            ++this.j;
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.whb) {
            this.offg2.setColor(this.bgc);
            this.offg2.fillRect(0, 0, this.w, this.h);
            this.offg2.setFont(this.tnf);
            this.rc = (this.colc * this.rttit + (20 - this.colc) * this.rb) / 20;
            this.gc = (this.colc * this.gttit + (20 - this.colc) * this.gb) / 20;
            this.bc = (this.colc * this.bttit + (20 - this.colc) * this.bb) / 20;
            this.tfadecolor = new Color(this.rc, this.gc, this.bc);
            this.offg2.setColor(this.tfadecolor);
            this.j = 1;
            while (this.j <= this.satirsayh[this.crr]) {
                this.offg2.drawString(this.satirh[this.crr][this.j], this.xht[1][this.crr][this.j], this.yht[1][this.crr][this.j]);
                if (this.title_underline.equals("yes")) {
                    this.linew = this.tfmetrics.stringWidth(this.satirh[this.crr][this.j]);
                    this.offg2.drawLine(this.xht[1][this.crr][this.j], this.yht[1][this.crr][this.j] + 1, this.xht[1][this.crr][this.j] + this.linew, this.yht[1][this.crr][this.j] + 1);
                }
                ++this.j;
            }
            this.offg2.setFont(this.nf);
            this.rc = (this.colc * this.rt + (20 - this.colc) * this.rb) / 20;
            this.gc = (this.colc * this.gt + (20 - this.colc) * this.gb) / 20;
            this.bc = (this.colc * this.bt + (20 - this.colc) * this.bb) / 20;
            this.tfadecolor = new Color(this.rc, this.gc, this.bc);
            this.offg2.setColor(this.tfadecolor);
            this.j = 1;
            while (this.j <= this.satirsay[this.crr]) {
                this.offg2.drawString(this.satir[this.crr][this.j], this.xht[0][this.crr][this.j], this.yht[0][this.crr][this.j]);
                if (this.text_underline.equals("yes")) {
                    this.linew = this.fmetrics.stringWidth(this.satir[this.crr][this.j]);
                    this.offg2.drawLine(this.xht[0][this.crr][this.j], this.yht[0][this.crr][this.j] + 1, this.xht[0][this.crr][this.j] + this.linew, this.yht[0][this.crr][this.j] + 1);
                }
                ++this.j;
            }
            if (this.border_thickness > 0) {
                this.offg2.setColor(this.currborderc);
                this.m = 1;
                while (this.m <= this.border_thickness) {
                    this.offg2.drawRect(this.m - 1, this.m - 1, this.w - 2 * this.m + 1, this.h - 2 * this.m + 1);
                    ++this.m;
                }
            }
            this.offg.drawImage(this.offimg2, 0, 0, null);
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.whb) {
            graphics.drawImage(this.offimg, 0, 0, this);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.whb) {
            this.inout = 1;
            if (this.dur[this.crr] == 0) {
                return true;
            }
            this.rt = this.rh;
            this.gt = this.gh;
            this.bt = this.bh;
            this.rttit = this.rhtit;
            this.gttit = this.ghtit;
            this.bttit = this.bhtit;
            this.currborderc = this.hborderc;
            this.ssts();
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.whb) {
            this.inout = 0;
            this.ilkmove = 0;
            if (this.dur[this.crr] == 0) {
                return true;
            }
            this.rt = this.re;
            this.gt = this.ge;
            this.bt = this.be;
            this.rttit = this.retit;
            this.gttit = this.getit;
            this.bttit = this.betit;
            this.currborderc = this.borderc;
            this.repaint();
            ((Frame)this.getParent()).setCursor(0);
            this.showStatus("");
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.whb) {
            this.inout = 1;
            if (this.basla == 0) {
                return true;
            }
            if (this.basla == 1 && this.ilkmove == 0) {
                if (this.dur[this.crr] == 0) {
                    return true;
                }
                this.rt = this.rh;
                this.gt = this.gh;
                this.bt = this.bh;
                this.rttit = this.rhtit;
                this.gttit = this.ghtit;
                this.bttit = this.bhtit;
                this.currborderc = this.hborderc;
                this.ssts();
                this.repaint();
            }
            this.ilkmove = 1;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (this.dur[this.crr] == 0) {
                return true;
            }
            try {
                if (this.links[this.crr].startsWith("http://")) {
                    this.u = new URL("" + this.links[this.crr]);
                }
                else {
                    this.u = new URL(this.getCodeBase(), "" + this.links[this.crr]);
                }
            }
            catch (Exception ex) {
                return true;
            }
            this.getAppletContext().showDocument(this.u, this.target_frame[this.crr]);
        }
        return true;
    }
    
    public void ssts() {
        if (this.inout == 1) {
            if (!this.rg) {
                this.showStatus("Unregistered version of FadingNews Java applet");
            }
            else if (this.stb[this.crr] != null) {
                this.showStatus("" + this.stb[this.crr]);
            }
            if (this.dur[this.crr] == 0) {
                this.sfc(0);
                return;
            }
            this.sfc(12);
        }
    }
    
    public void sfc(final int cursor) {
        ((Frame)this.getParent()).setCursor(cursor);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
            return;
        }
        this.runner.resume();
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
        this.show();
        this.w = this.size().width;
        this.h = this.size().height;
        if (!this.whb) {
            this.i = 1;
            while (this.i <= this.num) {
                this.a = 1;
                this.b = 0;
                this.c = this.w - this.left_margin - this.right_margin;
                this.st3 = new StringTokenizer(this.tit[this.i], "#");
                this.j = 1;
                this.bos = this.tfmetrics.stringWidth(this.bosluk);
                this.satirh[this.i][1] = "";
                while (this.st3.hasMoreTokens()) {
                    this.bs = this.st3.nextToken();
                    this.st2 = new StringTokenizer(this.bs, " ");
                    while (this.st2.hasMoreTokens()) {
                        this.kelime = this.st2.nextToken();
                        if (this.tfmetrics.stringWidth(this.satirh[this.i][this.a]) + this.tfmetrics.stringWidth(this.kelime) + this.bos <= this.c) {
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
                this.bos = this.fmetrics.stringWidth(this.bosluk);
                this.satir[this.i][1] = "";
                while (this.st3.hasMoreTokens()) {
                    this.bs = this.st3.nextToken();
                    this.st2 = new StringTokenizer(this.bs, " ");
                    while (this.st2.hasMoreTokens()) {
                        this.kelime = this.st2.nextToken();
                        if (this.fmetrics.stringWidth(this.satir[this.i][this.a]) + this.fmetrics.stringWidth(this.kelime) + this.bos <= this.c) {
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
                this.ysize[this.i] = this.satirsay[this.i] * (this.tmaxas + this.tmaxdes) + (this.satirsay[this.i] - 1) * this.yspace_text + this.satirsayh[this.i] * (this.maxas + this.maxdes) + this.satirsayh[this.i] * this.yspace_title;
                ++this.i;
            }
            this.rb = this.bgc.getRed();
            this.gb = this.bgc.getGreen();
            this.bb = this.bgc.getBlue();
            this.re = this.textc.getRed();
            this.ge = this.textc.getGreen();
            this.be = this.textc.getBlue();
            this.retit = this.titlec.getRed();
            this.getit = this.titlec.getGreen();
            this.betit = this.titlec.getBlue();
            this.rt = this.re;
            this.gt = this.ge;
            this.bt = this.be;
            this.rttit = this.retit;
            this.gttit = this.getit;
            this.bttit = this.betit;
            this.rh = this.htextc.getRed();
            this.gh = this.htextc.getGreen();
            this.bh = this.htextc.getBlue();
            this.rhtit = this.htitlec.getRed();
            this.ghtit = this.htitlec.getGreen();
            this.bhtit = this.htitlec.getBlue();
            this.i = 1;
            while (this.i <= this.num) {
                this.vi[this.i] = 0;
                this.typer();
                ++this.i;
            }
            this.offimg = this.createImage(this.w, this.h);
            (this.offg = this.offimg.getGraphics()).clipRect(0, 0, this.w, this.h);
            this.offimg2 = this.createImage(this.w, this.h);
            (this.offg2 = this.offimg2.getGraphics()).clipRect(0, 0, this.w, this.h);
            this.fade_delay = this.fi(this.getParameter("fade_delay"), 10, 50);
            this.pause_time = this.fi(this.getParameter("pause_time"), 10, 3000);
            System.gc();
            this.basla = 0;
        }
        this.whb = true;
        this.sdly = this.fade_delay;
        this.crr = 1;
        this.colc = 0;
        this.currborderc = this.borderc;
        this.inout = 0;
        this.ilkmove = 0;
        while (true) {
            this.basla = 1;
            this.sdly = this.fade_delay;
            if (this.yon == 0) {
                ++this.colc;
            }
            if (this.yon == 1) {
                --this.colc;
            }
            if (this.colc > 20) {
                this.colc = 20;
                this.sdly = this.pause_time;
            }
            if (this.colc <= 0) {
                this.colc = 0;
            }
            this.repaint();
            try {
                Thread.sleep(this.sdly);
            }
            catch (InterruptedException ex) {}
            if (this.sdly == this.pause_time) {
                this.yon = 1;
            }
            if (this.yon == 1 && this.colc == 0) {
                ++this.crr;
                this.yon = 0;
                if (this.crr > this.num) {
                    this.crr = 1;
                }
                this.ssts();
            }
        }
    }
    
    public fadingnews() {
        this.kelime = "";
        this.infostr = "";
        this.regcode = "";
        this.align = "";
        this.valign = "";
        this.ft = "";
        this.bs = "";
        this.title_underline = "";
        this.text_underline = "";
        this.bosluk = " ";
        this.pause_time = 2000;
        this.rg = false;
        this.whb = false;
    }
}
