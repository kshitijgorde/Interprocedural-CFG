import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.net.URLConnection;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.awt.Container;
import java.awt.Frame;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Font;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class headline extends Applet implements Runnable
{
    Thread runner;
    int w;
    int h;
    int headline_width;
    int headline_font_size;
    int headline_font_style;
    int message_font_size;
    int message_font_style;
    int headline_margin;
    int message_margin;
    int border_thickness;
    int pausetime;
    int scroll_delay;
    int type_delay;
    int yoffset;
    int i;
    int j;
    int num;
    int htxth;
    int mtxth;
    int hth;
    int hyp;
    int mth;
    int myp;
    int stus;
    int curt;
    int sdly;
    int chr;
    int scount;
    int linew;
    boolean rg;
    boolean icdis;
    boolean bfile;
    String regcode;
    String headline_font_type;
    String message_font_type;
    String headline_underline;
    String message_underline;
    String headline_align;
    String message_align;
    String bs;
    String infostr;
    String input_text;
    String text_file;
    Color headline_color;
    Color message_color;
    Color headline_highlight_color;
    Color message_highlight_color;
    Color headline_bgcolor;
    Color message_bgcolor;
    Color border_color;
    Color highlight_border_color;
    Color hdhc;
    Color hdmc;
    Color hdbc;
    Graphics offg;
    Graphics offg2;
    Image offimg;
    Image offimg2;
    String[] hline;
    String[] ntext;
    String[] links;
    String[] target_frame;
    String[] stb;
    int[] dur;
    int[] xposh;
    int[] xposm;
    int[] metrik;
    char[][] harfler;
    URL u;
    Font hnf;
    Font mnf;
    FontMetrics hfmetrics;
    FontMetrics mfmetrics;
    Vector vect;
    Frame frm;
    Container container;
    
    public void init() {
        this.stus = 1;
        this.curt = 1;
        this.vect = new Vector();
        this.infostr = this.getParameter("copyright");
        if (this.infostr == null) {
            this.infostr = "";
        }
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null && (this.regcode.equals("hedne582") || this.regcode.equals("cohnrw"))) {
            this.rg = true;
        }
        this.headline_width = this.fi("headline_width", 10, 0);
        this.headline_color = new Color(this.fi("headline_color", 16, 0));
        this.message_color = new Color(this.fi("message_color", 16, 0));
        this.headline_highlight_color = new Color(this.fi("headline_highlight_color", 16, 16711680));
        this.message_highlight_color = new Color(this.fi("message_highlight_color", 16, 16711680));
        this.headline_bgcolor = new Color(this.fi("headline_bgcolor", 16, 16777215));
        this.message_bgcolor = new Color(this.fi("message_bgcolor", 16, 16777215));
        this.headline_font_type = this.stp("headline_font_type", "Helvetica");
        this.headline_font_size = this.fi("headline_font_size", 10, 12);
        this.headline_font_style = this.fi("headline_font_style", 10, 1);
        this.message_font_type = this.stp("message_font_type", "Helvetica");
        this.message_font_size = this.fi("message_font_size", 10, 12);
        this.message_font_style = this.fi("message_font_style", 10, 1);
        this.headline_underline = this.stp("headline_underline", "no");
        this.message_underline = this.stp("message_underline", "no");
        this.headline_align = this.stp("headline_align", "center");
        this.message_align = this.stp("message_align", "left");
        this.headline_margin = this.fi("headline_margin", 10, 0);
        this.message_margin = this.fi("message_margin", 10, 10);
        this.border_thickness = this.fi("border_thickness", 10, 0);
        this.border_color = new Color(this.fi("border_color", 16, 0));
        this.highlight_border_color = new Color(this.fi("highlight_border_color", 16, 16711680));
        this.pausetime = this.fi("pausetime", 10, 3000);
        this.scroll_delay = this.fi("scroll_delay", 10, 30);
        this.type_delay = this.fi("type_delay", 10, 30);
        this.yoffset = this.fi("yoffset", 10, 0);
        this.input_text = this.stp("read_messages", "read_from_parameters");
        this.text_file = this.stp("text_file", "headline.txt");
        if (this.input_text.equals("read_from_file")) {
            this.bfile = true;
        }
        else {
            this.bfile = false;
        }
        if (this.bfile) {
            try {
                final URLConnection openConnection = new URL(this.getCodeBase(), "" + this.text_file).openConnection();
                openConnection.setDoInput(true);
                openConnection.setUseCaches(false);
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                this.i = 1;
                while (true) {
                    this.bs = dataInputStream.readLine();
                    if (this.bs == null) {
                        --this.i;
                        if (this.i < 0) {
                            this.i = 0;
                            break;
                        }
                        break;
                    }
                    else if (this.bs.trim().equals("") || this.bs.length() <= 3) {
                        --this.i;
                        if (this.i < 0) {
                            this.i = 0;
                            break;
                        }
                        break;
                    }
                    else {
                        this.vect.addElement("" + this.bs);
                        ++this.i;
                    }
                }
                this.num = this.i;
            }
            catch (Exception ex) {}
        }
        else {
            this.i = 1;
            while (true) {
                this.bs = this.getParameter("message" + this.i);
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
        }
        this.hline = new String[this.num + 1];
        this.ntext = new String[this.num + 1];
        this.links = new String[this.num + 1];
        this.target_frame = new String[this.num + 1];
        this.stb = new String[this.num + 1];
        this.dur = new int[this.num + 1];
        this.xposh = new int[this.num + 1];
        this.xposm = new int[this.num + 1];
        this.metrik = new int[this.num + 1];
        this.harfler = new char[this.num + 1][500];
        this.j = 1;
        if (this.bfile) {
            final Enumeration<String> elements = (Enumeration<String>)this.vect.elements();
            while (elements.hasMoreElements()) {
                final StringTokenizer stringTokenizer = new StringTokenizer(elements.nextElement(), "|");
                this.hline[this.j] = stringTokenizer.nextToken();
                this.ntext[this.j] = stringTokenizer.nextToken();
                this.links[this.j] = stringTokenizer.nextToken();
                this.target_frame[this.j] = stringTokenizer.nextToken();
                ++this.j;
            }
        }
        this.i = 1;
        while (this.i <= this.num) {
            if (!this.bfile) {
                this.hline[this.i] = this.getParameter("headline_text" + this.i);
                this.ntext[this.i] = this.getParameter("message" + this.i);
                this.links[this.i] = this.getParameter("link" + this.i);
                this.target_frame[this.i] = this.getParameter("target_frame" + this.i);
            }
            if (!this.infostr.equals("Copyright (c) 2001 BruceM Anderson,http://appletlib.tripod.com")) {
                this.ntext[this.i] = "info parameter error!";
            }
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
            if (!this.rg) {
                this.stb[this.i] = "Unregistered version of Headline Java applet,(appletlib.tripod.com)";
            }
            this.harfler[this.i] = this.ntext[this.i].toCharArray();
            ++this.i;
        }
        this.hnf = new Font(this.headline_font_type, this.headline_font_style, this.headline_font_size);
        this.hfmetrics = this.getFontMetrics(this.hnf);
        this.mnf = new Font(this.message_font_type, this.message_font_style, this.message_font_size);
        this.mfmetrics = this.getFontMetrics(this.mnf);
        this.htxth = this.hfmetrics.getDescent() + this.hfmetrics.getAscent();
        this.mtxth = this.mfmetrics.getDescent() + this.mfmetrics.getAscent();
        this.show();
        int n = 1;
        final String parameter = this.getParameter("applet_width");
        if (parameter == null || parameter.trim().equals("")) {
            n = 0;
        }
        final String parameter2 = this.getParameter("applet_height");
        if (parameter2 == null || parameter2.trim().equals("")) {
            n = 0;
        }
        try {
            if (n == 1) {
                this.w = Integer.parseInt(parameter, 10);
                this.h = Integer.parseInt(parameter2, 10);
            }
            else {
                this.w = this.size().width;
                this.h = this.size().height;
            }
        }
        catch (Exception ex2) {
            this.w = this.size().width;
            this.h = this.size().height;
        }
        this.container = this.getParent();
        while (!(this.container instanceof Frame)) {
            this.container = this.container.getParent();
        }
        this.frm = (Frame)this.container;
        this.hth = (this.h - this.htxth) / 2;
        this.hyp = this.hth + this.hfmetrics.getAscent() + this.yoffset;
        this.mth = (this.h - this.mtxth) / 2;
        this.myp = this.mth + this.mfmetrics.getAscent() + this.yoffset;
        this.i = 1;
        while (this.i <= this.num) {
            this.metrik[this.i] = this.hfmetrics.stringWidth(this.hline[this.i]);
            if (this.headline_align.equals("center")) {
                this.xposh[this.i] = (this.headline_width - this.metrik[this.i]) / 2;
            }
            else {
                this.xposh[this.i] = this.headline_margin;
            }
            this.metrik[this.i] = this.mfmetrics.stringWidth(this.ntext[this.i]);
            if (this.message_align.equals("center")) {
                this.xposm[this.i] = (this.w - this.headline_width - this.metrik[this.i]) / 2;
            }
            else {
                this.xposm[this.i] = this.message_margin;
            }
            ++this.i;
        }
        this.offimg = this.createImage(this.w, this.h);
        (this.offg = this.offimg.getGraphics()).clipRect(0, 0, this.w, this.h);
        this.offimg2 = this.createImage(this.w, 2 * this.h);
        this.offg2 = this.offimg2.getGraphics();
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
    
    public String stp(String parameter, final String s) {
        parameter = this.getParameter("" + parameter);
        if (parameter == null) {
            return s;
        }
        return parameter;
    }
    
    public void update(final Graphics graphics) {
        this.offg.drawImage(this.offimg2, 0, 0, null);
        this.bord();
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimg, 0, 0, this);
    }
    
    public void bord() {
        this.offg.setColor(this.hdbc);
        if (this.border_thickness > 0) {
            this.j = 1;
            while (this.j <= this.border_thickness) {
                this.offg.drawRect(this.j - 1, this.j - 1, this.w - 2 * this.j + 1, this.h - 2 * this.j + 1);
                ++this.j;
            }
        }
    }
    
    public void drawstr() {
        this.offg2.setColor(this.headline_bgcolor);
        this.offg2.fillRect(0, 0, this.headline_width, 2 * this.h);
        this.offg2.setColor(this.hdhc);
        this.offg2.setFont(this.hnf);
        this.offg2.drawString(this.hline[this.curt], this.xposh[this.curt], this.hyp);
        this.offg2.drawString(this.hline[this.curt % this.num + 1], this.xposh[this.curt % this.num + 1], this.hyp + this.h);
        if (this.headline_underline.equals("yes")) {
            this.linew = this.hfmetrics.stringWidth(this.hline[this.curt]);
            this.offg2.drawLine(this.xposh[this.curt], this.hyp + 1, this.xposh[this.curt] + this.linew, this.hyp + 1);
            this.linew = this.hfmetrics.stringWidth(this.hline[this.curt % this.num + 1]);
            this.offg2.drawLine(this.xposh[this.curt % this.num + 1], this.hyp + this.h + 1, this.xposh[this.curt % this.num + 1] + this.linew, this.hyp + this.h + 1);
        }
        this.offg2.setColor(this.message_bgcolor);
        this.offg2.fillRect(this.headline_width, 0, this.w - this.headline_width, 2 * this.h);
        this.offg2.setColor(this.hdmc);
        this.offg2.setFont(this.mnf);
        this.offg2.drawChars(this.harfler[this.curt], 0, this.chr, this.headline_width + this.xposm[this.curt], this.myp);
        if (this.message_underline.equals("yes")) {
            this.linew = this.mfmetrics.stringWidth(this.ntext[this.curt].substring(0, this.chr));
            this.offg2.drawLine(this.headline_width + this.xposm[this.curt], this.myp + 1, this.headline_width + this.xposm[this.curt] + this.linew, this.myp + 1);
        }
    }
    
    public void stuc() {
        if (this.stus == 3) {
            if (this.hline[this.curt].equals("" + this.hline[this.curt % this.num + 1])) {
                this.offg2.copyArea(this.headline_width, 0, this.w - this.headline_width, 2 * this.h, 0, -1 * this.scount);
                return;
            }
            this.offg2.copyArea(0, 0, this.w, 2 * this.h, 0, -1 * this.scount);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.dur[this.curt] == 0) {
            return true;
        }
        this.icdis = true;
        this.hdhc = this.headline_highlight_color;
        this.hdmc = this.message_highlight_color;
        this.hdbc = this.highlight_border_color;
        this.frm.setCursor(12);
        this.showStatus("" + this.stb[this.curt]);
        this.drawstr();
        this.stuc();
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.icdis = false;
        this.hdhc = this.headline_color;
        this.hdmc = this.message_color;
        this.hdbc = this.border_color;
        this.frm.setCursor(0);
        this.showStatus("");
        this.drawstr();
        this.stuc();
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.dur[this.curt] == 0) {
            return true;
        }
        try {
            if (this.links[this.curt].startsWith("http://")) {
                this.u = new URL("" + this.links[this.curt]);
            }
            else {
                this.u = new URL(this.getCodeBase(), "" + this.links[this.curt]);
            }
        }
        catch (Exception ex) {
            return true;
        }
        this.getAppletContext().showDocument(this.u, this.target_frame[this.curt]);
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
        this.icdis = false;
        this.sdly = this.type_delay;
        this.stus = 1;
        this.chr = 0;
        this.scount = 0;
        this.curt = 1;
        this.hdhc = this.headline_color;
        this.hdmc = this.message_color;
        this.hdbc = this.border_color;
        while (true) {
            switch (this.stus) {
                case 1: {
                    ++this.chr;
                    if (this.chr >= this.ntext[this.curt].length()) {
                        this.chr = this.ntext[this.curt].length();
                        this.sdly = this.pausetime;
                        this.stus = 2;
                    }
                    this.drawstr();
                    break;
                }
                case 2: {
                    this.stus = 3;
                    this.sdly = this.scroll_delay;
                    this.scount = 0;
                    break;
                }
                case 3: {
                    if (this.hline[this.curt].equals("" + this.hline[this.curt % this.num + 1])) {
                        this.offg2.copyArea(this.headline_width, 0, this.w - this.headline_width, 2 * this.h, 0, -1);
                    }
                    else {
                        this.offg2.copyArea(0, 0, this.w, 2 * this.h, 0, -1);
                    }
                    ++this.scount;
                    if (this.scount != this.h) {
                        break;
                    }
                    this.sdly = this.type_delay;
                    this.stus = 4;
                    this.chr = 0;
                    ++this.curt;
                    if (this.curt > this.num) {
                        this.curt = 1;
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.icdis && this.dur[this.curt] != 0) {
                        this.frm.setCursor(12);
                        this.showStatus("" + this.stb[this.curt]);
                    }
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ex) {}
                    this.stus = 1;
                    break;
                }
            }
            this.repaint();
            try {
                Thread.sleep(this.sdly);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public headline() {
        this.rg = false;
        this.icdis = false;
        this.bfile = false;
        this.regcode = "";
        this.headline_font_type = "";
        this.message_font_type = "";
        this.headline_underline = "";
        this.message_underline = "";
        this.headline_align = "";
        this.message_align = "";
        this.bs = "";
        this.infostr = "";
        this.input_text = "";
        this.text_file = "";
    }
}
