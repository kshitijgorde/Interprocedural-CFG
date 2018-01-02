import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Container;
import java.awt.Frame;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class newsticker extends Applet implements Runnable
{
    int bgci;
    int fci;
    int fontsize;
    int fontstyle;
    int genislik;
    int yukseklik;
    int hlci;
    int tcount;
    int i;
    int delay_time;
    int scroll_delay;
    int scroll_jump;
    int txdes;
    int txth;
    int aralik;
    int uyu;
    int dur;
    int X;
    int curr;
    int mesafe;
    int cx;
    int cy;
    int downtut;
    int tutX;
    int left_margin;
    Color bgcc;
    Color fcc;
    Color hlcc;
    String fonttype;
    String text_alignment;
    String target_frame;
    String infostr;
    String regcode;
    Font nf;
    FontMetrics fmetrics;
    Graphics offScreeng;
    Graphics offbuff;
    Graphics offScreeng2;
    Image offscrimg;
    Image offscrimg2;
    String[] texts;
    String[] links;
    Image[] normi;
    Image[] hlighti;
    int[] metrik;
    int[] duryer;
    Thread runner;
    boolean icdis;
    boolean rg;
    boolean yan1;
    boolean yan2;
    boolean bd1;
    boolean bd2;
    boolean whb;
    URL u;
    Frame frm;
    Container container;
    
    public void init() {
        this.genislik = -1;
        this.yukseklik = -1;
        this.whb = false;
        this.X = 0;
        this.bgci = this.fi("bgcolor", 16, 0);
        this.fci = this.fi("fontcolor", 16, 16777215);
        this.hlci = this.fi("highlight_color", 16, 65280);
        this.infostr = this.getParameter("info");
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null && this.regcode.equals("nwt786")) {
            this.rg = true;
        }
        this.bgcc = new Color(this.bgci);
        this.fcc = new Color(this.fci);
        this.hlcc = new Color(this.hlci);
        this.fontsize = this.fi("font_size", 10, 11);
        this.fonttype = this.getParameter("font_type");
        this.fontstyle = this.fi("font_style", 10, 0);
        this.left_margin = this.fi("left_margin", 10, 10);
        this.nf = new Font(this.fonttype, this.fontstyle, this.fontsize);
        this.fmetrics = this.getFontMetrics(this.nf);
        this.target_frame = this.getParameter("target_frame");
        this.text_alignment = this.getParameter("text_alignment");
        final Vector<String> vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        this.tcount = 0;
        while (true) {
            ++this.tcount;
            final String parameter = this.getParameter("text" + this.tcount);
            String parameter2 = this.getParameter("link" + this.tcount);
            if (parameter == null) {
                --this.tcount;
                break;
            }
            if (parameter.equals("")) {
                --this.tcount;
                break;
            }
            if (parameter2 == null) {
                parameter2 = "";
            }
            vector2.addElement(parameter2);
            vector.addElement(parameter);
        }
        this.texts = new String[this.tcount + 1];
        this.links = new String[this.tcount + 1];
        this.normi = new Image[this.tcount + 1];
        this.hlighti = new Image[this.tcount + 1];
        this.metrik = new int[this.tcount + 1];
        this.duryer = new int[this.tcount + 1];
        final Enumeration<String> elements = vector.elements();
        this.i = 0;
        while (elements.hasMoreElements()) {
            ++this.i;
            this.texts[this.i] = elements.nextElement();
            if (!this.infostr.equals("Applet by Gokhan Dagli")) {
                this.texts[this.i] = "info parameter error!";
            }
        }
        final Enumeration<String> elements2 = vector2.elements();
        this.i = 0;
        while (elements2.hasMoreElements()) {
            ++this.i;
            this.links[this.i] = elements2.nextElement();
        }
        this.delay_time = this.fi("delay_time", 10, 4000);
        this.scroll_delay = this.fi("scroll_delay", 10, 8);
        this.scroll_jump = this.fi("scroll_jump", 10, 2);
        this.uyu = this.delay_time;
        this.txth = this.fmetrics.getDescent() + this.fmetrics.getAscent();
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
                this.genislik = Integer.parseInt(parameter3, 10);
                this.yukseklik = Integer.parseInt(parameter4, 10);
            }
            else {
                this.genislik = this.size().width;
                this.yukseklik = this.size().height;
            }
        }
        catch (Exception ex) {
            this.genislik = this.size().width;
            this.yukseklik = this.size().height;
        }
        this.aralik = (this.yukseklik - this.txth) / 2;
        this.i = 1;
        while (this.i <= this.tcount) {
            this.metrik[this.i] = this.fmetrics.stringWidth(this.texts[this.i]);
            this.normi[this.i] = this.createImage(this.metrik[this.i] + this.scroll_jump + 1, this.yukseklik);
            this.hlighti[this.i] = this.createImage(this.metrik[this.i] + this.scroll_jump + 1, this.yukseklik);
            if (this.text_alignment.equals("center")) {
                this.duryer[this.i] = (this.genislik - this.metrik[this.i]) / 2;
            }
            else {
                this.duryer[this.i] = this.left_margin;
            }
            (this.offbuff = this.normi[this.i].getGraphics()).setFont(this.nf);
            this.offbuff.setColor(this.bgcc);
            this.offbuff.fillRect(0, 0, this.metrik[this.i] + this.scroll_jump + 1, this.yukseklik);
            this.offbuff.setColor(this.fcc);
            this.offbuff.drawString(this.texts[this.i], 0, this.aralik + this.fmetrics.getAscent());
            this.offbuff = null;
            (this.offbuff = this.hlighti[this.i].getGraphics()).setFont(this.nf);
            this.offbuff.setColor(this.bgcc);
            this.offbuff.fillRect(0, 0, this.metrik[this.i] + this.scroll_jump + 1, this.yukseklik);
            this.offbuff.setColor(this.hlcc);
            this.offbuff.drawString(this.texts[this.i], 0, this.aralik + this.fmetrics.getAscent());
            ++this.i;
        }
        if (this.offbuff != null) {
            this.offbuff.dispose();
        }
        this.offscrimg = this.createImage(this.genislik, this.yukseklik);
        this.offScreeng = this.offscrimg.getGraphics();
        this.offscrimg2 = this.createImage(this.genislik, this.yukseklik);
        this.offScreeng2 = this.offscrimg2.getGraphics();
        this.offScreeng.setColor(this.bgcc);
        this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
        this.curr = 0;
        this.cx = -1;
        this.cy = -1;
        this.X = 10;
        if (this.tcount > 0) {
            this.X = this.duryer[1];
        }
        this.mesafe = 0;
        this.container = this.getParent();
        while (!(this.container instanceof Frame)) {
            this.container = this.container.getParent();
        }
        this.frm = (Frame)this.container;
        this.whb = true;
    }
    
    public int fi(String parameter, final int n, final int n2) {
        parameter = this.getParameter("" + parameter);
        if (parameter == null) {
            return n2;
        }
        int int1;
        try {
            int1 = Integer.parseInt(parameter, n);
        }
        catch (Exception ex) {
            int1 = n2;
        }
        return int1;
    }
    
    public void update(final Graphics graphics) {
        if (this.whb) {
            this.offScreeng2.drawImage(this.offscrimg, 0, 0, this);
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.whb) {
            graphics.drawImage(this.offscrimg2, 0, 0, this);
        }
    }
    
    public boolean mouseDown(final Event event, final int downtut, final int n) {
        if (this.whb) {
            if (this.tcount < 1) {
                return true;
            }
            this.stop();
            this.downtut = downtut;
            this.tutX = this.X;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (this.tcount < 1) {
                return true;
            }
            this.start();
            try {
                if (this.yan1) {
                    this.u = new URL(this.links[this.curr]);
                    this.getAppletContext().showDocument(this.u, this.target_frame);
                }
                if (this.yan2) {
                    this.u = new URL(this.links[this.curr % this.tcount + 1]);
                    this.getAppletContext().showDocument(this.u, this.target_frame);
                }
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int cx, final int cy) {
        if (this.whb) {
            if (this.tcount < 1) {
                return true;
            }
            this.cx = cx;
            this.cy = cy;
            if (this.whb) {
                this.bul(this.cx, this.cy);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int cx, final int cy) {
        if (this.whb) {
            if (this.tcount < 1) {
                return true;
            }
            this.cx = cx;
            this.cy = cy;
            this.X = this.tutX + cx - this.downtut;
            if (this.whb) {
                this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
                this.kur();
                this.bul(cx, cy);
                this.pfunc();
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.tcount < 1) {
            return true;
        }
        if (!this.rg) {
            this.showStatus("Unregistered version of newsticker Java applet");
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (this.tcount < 1) {
                return true;
            }
            this.yan1 = false;
            this.yan2 = false;
            this.cx = -1;
            this.cy = -1;
            if (this.whb) {
                this.bul(this.cx, this.cy);
                this.pfunc();
                this.repaint();
            }
            this.showStatus("");
            if (!this.rg) {
                this.showStatus("Unregistered version of newsticker Java applet");
            }
        }
        return true;
    }
    
    public void start() {
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
        }
        this.runner.start();
    }
    
    public void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
    }
    
    public void destroy() {
        this.runner = null;
    }
    
    public void bul(final int n, final int n2) {
        if (this.tcount < 1) {
            return;
        }
        if (n < this.X) {
            this.yan1 = false;
            this.yan2 = false;
        }
        if (n >= 0 && n >= this.X && n <= this.X + this.metrik[this.curr]) {
            if (this.links[this.curr] != null) {
                if (this.links[this.curr].startsWith("http://")) {
                    this.yan1 = true;
                }
                else {
                    this.yan1 = false;
                }
            }
            else {
                this.yan1 = false;
            }
            this.yan2 = false;
        }
        if (n > this.X + this.metrik[this.curr] && n < this.X + this.metrik[this.curr] + this.mesafe) {
            this.yan1 = false;
            this.yan2 = false;
        }
        if (n >= 0 && n >= this.X + this.metrik[this.curr] + this.mesafe && n <= this.X + this.metrik[this.curr] + this.mesafe + this.metrik[this.curr % this.tcount + 1]) {
            this.yan1 = false;
            if (this.links[this.curr % this.tcount + 1] != null) {
                if (this.links[this.curr % this.tcount + 1].startsWith("http://")) {
                    this.yan2 = true;
                }
                else {
                    this.yan2 = false;
                }
            }
            else {
                this.yan2 = false;
            }
        }
        if (n >= 0 && n > this.X + this.metrik[this.curr] + this.mesafe + this.metrik[this.curr % this.tcount + 1]) {
            this.yan1 = false;
            this.yan2 = false;
        }
        if (this.bd1 != this.yan1 || this.bd2 != this.yan2) {
            if (this.yan1 || this.yan2) {
                this.frm.setCursor(12);
                if (this.yan1) {
                    if (this.links[this.curr] != null) {
                        if (this.links[this.curr].startsWith("http://")) {
                            this.showStatus("" + this.links[this.curr]);
                        }
                        else {
                            this.showStatus("");
                        }
                    }
                    else {
                        this.showStatus("");
                    }
                }
                if (this.yan2) {
                    if (this.links[this.curr % this.tcount + 1] != null) {
                        if (this.links[this.curr % this.tcount + 1].startsWith("http://")) {
                            this.showStatus("" + this.links[this.curr % this.tcount + 1]);
                        }
                        else {
                            this.showStatus("");
                        }
                    }
                    else {
                        this.showStatus("");
                    }
                }
            }
            else {
                this.frm.setCursor(0);
                this.showStatus("");
            }
            this.pfunc();
        }
    }
    
    public void pfunc() {
        if (this.uyu == this.delay_time) {
            this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
        }
        if (this.links[this.curr] != null) {
            if (this.yan1 && this.links[this.curr].startsWith("http://")) {
                this.offScreeng.drawImage(this.hlighti[this.curr], this.X, 0, this);
                this.bd1 = true;
            }
            else {
                this.offScreeng.drawImage(this.normi[this.curr], this.X, 0, this);
                this.bd1 = false;
            }
        }
        else {
            this.offScreeng.drawImage(this.normi[this.curr], this.X, 0, this);
            this.bd1 = false;
        }
        if (this.links[this.curr % this.tcount + 1] != null) {
            if (this.yan2 && this.links[this.curr % this.tcount + 1].startsWith("http://")) {
                this.offScreeng.drawImage(this.hlighti[this.curr % this.tcount + 1], this.X + this.mesafe + this.metrik[this.curr], 0, this);
                this.bd2 = true;
            }
            else {
                this.offScreeng.drawImage(this.normi[this.curr % this.tcount + 1], this.X + this.mesafe + this.metrik[this.curr], 0, this);
                this.bd2 = false;
            }
        }
        else {
            this.offScreeng.drawImage(this.normi[this.curr % this.tcount + 1], this.X + this.mesafe + this.metrik[this.curr], 0, this);
            this.bd2 = false;
        }
        this.repaint();
    }
    
    public void isle() {
        this.uyu = this.scroll_delay;
        this.X -= this.scroll_jump;
    }
    
    public void kur() {
        if (this.X + this.metrik[this.curr] + this.mesafe <= this.duryer[this.curr % this.tcount + 1]) {
            ++this.curr;
            this.uyu = this.delay_time;
            if (this.curr > this.tcount || this.curr == 0) {
                this.curr = 1;
            }
            this.mesafe = Math.abs(this.duryer[this.curr % this.tcount + 1]) + Math.abs(this.duryer[this.curr]);
            if (!this.text_alignment.equals("center")) {
                this.mesafe = Math.abs(this.genislik - this.metrik[this.curr] - this.left_margin);
            }
            this.X = this.duryer[this.curr];
            this.tutX = this.X;
            this.downtut = this.cx;
            return;
        }
        if (this.X > this.duryer[this.curr]) {
            --this.curr;
            if (this.curr == 0) {
                this.curr = this.tcount;
            }
            this.mesafe = Math.abs(this.duryer[this.curr % this.tcount + 1]) + Math.abs(this.duryer[this.curr]);
            if (!this.text_alignment.equals("center")) {
                this.mesafe = Math.abs(this.genislik - this.metrik[this.curr] - this.left_margin);
            }
            this.X = this.X - this.mesafe - this.metrik[this.curr];
            this.tutX = this.X;
            this.downtut = this.cx;
        }
    }
    
    public void run() {
        if (!this.rg) {
            this.showStatus("Unregistered version of newsticker Java applet");
        }
        if (this.tcount < 1) {
            return;
        }
        while (true) {
            this.isle();
            this.kur();
            this.bul(this.cx, this.cy);
            this.pfunc();
            try {
                Thread.sleep(this.uyu);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public newsticker() {
        this.dur = 20;
        this.curr = 1;
        this.cx = -1;
        this.cy = -1;
        this.downtut = -1;
        this.left_margin = 10;
        this.fonttype = "Arial";
        this.text_alignment = "left";
        this.target_frame = "";
        this.infostr = "";
        this.regcode = "";
        this.icdis = false;
        this.rg = false;
        this.yan1 = false;
        this.yan2 = false;
        this.bd1 = false;
        this.bd2 = false;
        this.whb = false;
    }
}
