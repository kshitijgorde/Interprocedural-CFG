import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ascroll2 extends Applet implements Runnable
{
    Thread runner;
    int w;
    int h;
    int i;
    int num;
    int inout;
    int X;
    int crr;
    int bi;
    int cb;
    int cx;
    int cy;
    int ssp;
    int downtut;
    int tutX;
    int ce;
    int tutce;
    int scroll_delay;
    int sdly;
    int xspace;
    int mdd;
    String bs;
    String mouse_over;
    String target_frame;
    String infostr;
    String border;
    String rgmsg;
    int[] dur;
    int[] ypos;
    String[] tx;
    String[] links;
    String[] stb;
    Color[] nc;
    Color[] hlc;
    Color bgc;
    Color borderc;
    Font[] nf;
    URL u;
    StringTokenizer st1;
    FontMetrics[] fmetrics;
    Graphics offg;
    Graphics offg2;
    Image offimg;
    Image offimg2;
    boolean crs;
    boolean csb;
    boolean rg;
    boolean whb;
    Frame frm;
    Container container;
    
    public void init() {
        this.w = -1;
        this.h = -1;
        this.whb = false;
        this.mdd = 0;
        this.border = this.getParameter("border");
        if (this.border == null) {
            this.border = "no";
        }
        this.target_frame = this.getParameter("target_frame");
        this.infostr = this.getParameter("info");
        if (this.infostr == null) {
            this.infostr = "";
        }
        if (this.target_frame == null) {
            this.target_frame = "_self";
        }
        this.mouse_over = this.getParameter("mouse_over");
        if (this.mouse_over == null) {
            this.mouse_over = "normal";
        }
        this.bs = this.getParameter("xspace");
        if (this.bs == null) {
            this.bs = "0";
        }
        this.xspace = this.fi(this.bs, 10, 0);
        this.scroll_delay = this.fi(this.getParameter("scroll_delay"), 10, 16);
        this.sdly = this.scroll_delay;
        this.bgc = new Color(this.fi(this.getParameter("bgcolor"), 16, 0));
        this.borderc = new Color(this.fi(this.getParameter("border_color"), 16, 0));
        final String parameter = this.getParameter("regcode");
        if (parameter != null && (parameter.equals("ahs4457t8") || parameter.equals("txta4g65r"))) {
            this.rg = true;
        }
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
            else if (this.bs.equals("")) {
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
        this.tx = new String[this.num + 1];
        this.links = new String[this.num + 1];
        this.stb = new String[this.num + 1];
        this.nc = new Color[this.num + 1];
        this.hlc = new Color[this.num + 1];
        this.dur = new int[this.num + 1];
        this.ypos = new int[this.num + 1];
        this.nf = new Font[this.num + 1];
        this.fmetrics = new FontMetrics[this.num + 1];
        this.show();
        int n = 1;
        final String parameter2 = this.getParameter("applet_width");
        if (parameter2 == null || parameter2.trim().equals("")) {
            n = 0;
        }
        final String parameter3 = this.getParameter("applet_height");
        if (parameter3 == null || parameter3.trim().equals("")) {
            n = 0;
        }
        try {
            if (n == 1) {
                this.w = Integer.parseInt(parameter2, 10);
                this.h = Integer.parseInt(parameter3, 10);
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
        this.container = this.getParent();
        while (!(this.container instanceof Frame)) {
            this.container = this.container.getParent();
        }
        this.frm = (Frame)this.container;
        this.i = 1;
        while (this.i <= this.num) {
            if (this.infostr.equals("Applet by Gokhan Dagli,www.appletcollection.com")) {
                this.tx[this.i] = this.getParameter("text" + this.i);
            }
            else {
                this.tx[this.i] = "info parameter error!";
            }
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
            this.bs = this.getParameter("font" + this.i);
            this.st1 = new StringTokenizer(this.bs.trim(), ",");
            this.nf[this.i] = new Font(this.st1.nextToken(), Integer.valueOf(this.st1.nextToken()), Integer.valueOf(this.st1.nextToken()));
            this.fmetrics[this.i] = this.getFontMetrics(this.nf[this.i]);
            this.bs = this.getParameter("color" + this.i);
            this.st1 = new StringTokenizer(this.bs.trim(), ",");
            this.nc[this.i] = new Color(this.fi(this.st1.nextToken(), 16, 16777215));
            this.hlc[this.i] = new Color(this.fi(this.st1.nextToken(), 16, 16777215));
            this.stb[this.i] = this.getParameter("status_bar_msg" + this.i);
            this.bs = this.getParameter("yposition" + this.i);
            this.ypos[this.i] = this.fi(this.bs, 10, this.h);
            ++this.i;
        }
        this.offimg = this.createImage(this.w, this.h);
        (this.offg = this.offimg.getGraphics()).clipRect(0, 0, this.w, this.h);
        this.offimg2 = this.createImage(this.w, this.h);
        (this.offg2 = this.offimg2.getGraphics()).clipRect(0, 0, this.w, this.h);
        System.gc();
        this.whb = true;
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
    
    public void update(final Graphics graphics) {
        if (this.whb) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.whb) {
            graphics.drawImage(this.offimg, 0, 0, this);
        }
    }
    
    public boolean mouseDrag(final Event event, final int downtut, final int cy) {
        if (this.whb) {
            this.cx = downtut;
            this.cy = cy;
            this.mdd = 2;
            this.X = this.tutX + downtut - this.downtut;
            if (this.X + this.fmetrics[this.crr].stringWidth(this.tx[this.crr]) <= 0) {
                this.X = this.X + this.fmetrics[this.crr].stringWidth(this.tx[this.crr]) + this.xspace;
                this.tutX = this.X;
                this.downtut = downtut;
                ++this.crr;
                if (this.crr > this.num) {
                    this.crr = 1;
                    this.X = this.w;
                    this.tutX = this.X;
                    this.downtut = downtut;
                }
            }
            if (this.X - this.xspace > 0) {
                --this.crr;
                if (this.crr == 0) {
                    this.crr = 1;
                    if (this.X > this.w) {
                        this.crr = this.num;
                        this.X = -1 * this.fmetrics[this.crr].stringWidth(this.tx[this.crr]);
                        this.tutX = this.X;
                        this.downtut = downtut;
                    }
                }
                else {
                    this.X = this.X - this.xspace - this.fmetrics[this.crr].stringWidth(this.tx[this.crr]);
                    this.tutX = this.X;
                    this.downtut = downtut;
                }
            }
            this.npos();
        }
        return true;
    }
    
    public void npos() {
        this.offg2.setColor(this.bgc);
        this.offg2.fillRect(0, 0, this.w, this.h);
        this.bi = this.X;
        this.cb = this.crr;
        this.crs = false;
        do {
            this.offg2.setFont(this.nf[this.cb]);
            if (this.inout == 1 && this.cx >= this.bi && this.cx <= this.bi + this.fmetrics[this.cb].stringWidth(this.tx[this.cb]) && this.dur[this.cb] == 1) {
                this.offg2.setColor(this.hlc[this.cb]);
                this.crs = true;
                this.ce = this.cb;
            }
            else {
                this.offg2.setColor(this.nc[this.cb]);
            }
            this.offg2.drawString(this.tx[this.cb], this.bi, this.ypos[this.cb]);
            this.bi = this.bi + this.fmetrics[this.cb].stringWidth(this.tx[this.cb]) + this.xspace;
            if (this.bi >= this.w) {
                break;
            }
            ++this.cb;
        } while (this.cb <= this.num);
        if (this.crs && (!this.csb || this.tutce != this.ce)) {
            this.frm.setCursor(12);
            this.showStatus("" + this.stb[this.ce]);
            this.csb = true;
        }
        if (!this.crs && (this.csb || this.tutce != this.ce)) {
            this.frm.setCursor(0);
            this.showStatus("");
            this.csb = false;
        }
        this.tutce = this.ce;
        if (this.border.equals("yes")) {
            this.offg2.setColor(this.borderc);
            this.offg2.drawRect(0, 0, this.w - 1, this.h - 1);
        }
        this.offg.drawImage(this.offimg2, 0, 0, null);
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int downtut, final int n) {
        if (this.whb) {
            if (!this.mouse_over.equals("stop")) {
                this.ssp = 1;
            }
            this.mdd = 1;
            this.downtut = downtut;
            this.tutX = this.X;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (!this.mouse_over.equals("stop")) {
                this.ssp = 0;
            }
            if (this.dur[this.tutce] == 0 || !this.crs) {
                this.mdd = 0;
            }
            else {
                if (this.mdd == 1 || Math.abs(n - this.downtut) < 20) {
                    this.mdd = 0;
                    try {
                        if (this.links[this.tutce].startsWith("http://") || this.links[this.tutce].startsWith("mailto:")) {
                            this.u = new URL("" + this.links[this.tutce]);
                        }
                        else {
                            this.u = new URL(this.getCodeBase(), "" + this.links[this.tutce]);
                        }
                    }
                    catch (Exception ex) {
                        return true;
                    }
                    this.getAppletContext().showDocument(this.u, this.target_frame);
                }
                this.mdd = 0;
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int cx, final int cy) {
        if (this.whb) {
            this.inout = 1;
            this.cx = cx;
            this.cy = cy;
            if (this.mouse_over.equals("stop")) {
                this.npos();
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (this.mouse_over.equals("slow")) {
                this.sdly = this.scroll_delay * 2;
            }
            if (this.mouse_over.equals("fast")) {
                this.sdly = this.scroll_delay / 2;
            }
            if (this.mouse_over.equals("stop")) {
                this.ssp = 1;
            }
            this.inout = 1;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.whb) {
            this.sdly = this.scroll_delay;
            if (this.mouse_over.equals("stop")) {
                this.ssp = 0;
            }
            this.inout = 0;
            this.cx = -1;
            this.cy = -1;
            if (!this.rg) {
                this.showStatus("" + this.rgmsg);
            }
            else {
                this.showStatus("");
            }
            this.frm.setCursor(0);
            this.repaint();
        }
        return true;
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
        this.X = this.w;
        this.crr = 1;
        while (true) {
            if (this.ssp != 1) {
                --this.X;
                if (this.X + this.fmetrics[this.crr].stringWidth(this.tx[this.crr]) <= 0) {
                    this.X = this.X + this.fmetrics[this.crr].stringWidth(this.tx[this.crr]) + this.xspace;
                    ++this.crr;
                    if (this.crr > this.num) {
                        this.crr = 1;
                        this.X = this.w;
                    }
                }
                this.offg2.setColor(this.bgc);
                this.offg2.fillRect(0, 0, this.w, this.h);
                this.bi = this.X;
                this.cb = this.crr;
                this.crs = false;
                do {
                    this.offg2.setFont(this.nf[this.cb]);
                    if (this.inout == 1 && this.cx >= this.bi && this.cx <= this.bi + this.fmetrics[this.cb].stringWidth(this.tx[this.cb]) && this.dur[this.cb] == 1) {
                        this.offg2.setColor(this.hlc[this.cb]);
                        this.crs = true;
                        this.ce = this.cb;
                    }
                    else {
                        this.offg2.setColor(this.nc[this.cb]);
                    }
                    this.offg2.drawString(this.tx[this.cb], this.bi, this.ypos[this.cb]);
                    this.bi = this.bi + this.fmetrics[this.cb].stringWidth(this.tx[this.cb]) + this.xspace;
                    if (this.bi >= this.w) {
                        break;
                    }
                    ++this.cb;
                } while (this.cb <= this.num);
                if (this.crs && (!this.csb || this.tutce != this.ce)) {
                    this.frm.setCursor(12);
                    this.showStatus("" + this.stb[this.ce]);
                    this.csb = true;
                }
                if (!this.crs && (this.csb || this.tutce != this.ce)) {
                    this.frm.setCursor(0);
                    if (!this.rg) {
                        this.showStatus("" + this.rgmsg);
                    }
                    else {
                        this.showStatus("");
                    }
                    this.csb = false;
                }
                this.tutce = this.ce;
                if (this.border.equals("yes")) {
                    this.offg2.setColor(this.borderc);
                    this.offg2.drawRect(0, 0, this.w - 1, this.h - 1);
                }
                this.offg.drawImage(this.offimg2, 0, 0, null);
                this.repaint();
                try {
                    Thread.sleep(this.sdly);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public ascroll2() {
        this.cx = -1;
        this.cy = -1;
        this.ce = 1;
        this.bs = "";
        this.mouse_over = "stop";
        this.target_frame = "";
        this.infostr = "";
        this.border = "";
        this.rgmsg = " Unregistered A. Horizontal Scrolling Text Java applet. http://www.appletcollection.com/";
        this.crs = false;
        this.csb = false;
        this.rg = false;
        this.whb = false;
    }
}
