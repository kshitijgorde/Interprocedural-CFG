import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class menu_three extends Applet implements Runnable
{
    Thread runner;
    int i;
    int j;
    int num;
    int iw;
    int ih;
    int w;
    int h;
    int txth;
    int yp;
    int xp;
    int anim_speed;
    int text_yoffset;
    int left_margin;
    String bs;
    String text_align;
    String regcode;
    String infostr;
    Color bgcolor;
    Color text_color;
    Color mouseover_text_color;
    Color click_text_color;
    Font nf;
    FontMetrics fmetrics;
    Image offimg;
    Image offimg2;
    Image button_image;
    Image mouseover_button_image;
    Image click_button_image;
    Graphics offg;
    Graphics offg2;
    String[] str;
    String[] links;
    String[] stb;
    String[] target_frame;
    int[] dur;
    int[] xpos;
    int[] ypos;
    Image[] but;
    Image[] overbut;
    Image[] clickbut;
    boolean rg;
    button_three[] cb;
    Frame frm;
    Container container;
    
    public void init() {
        this.setLayout(null);
        this.infostr = this.getParameter("copyright");
        if (this.infostr == null) {
            this.infostr = "";
        }
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null && (this.regcode.equals("m3ktgbmn") || this.regcode.equals("apac628wkwc"))) {
            this.rg = true;
        }
        this.bgcolor = new Color(this.fi("bgcolor", 16, 16777215));
        this.anim_speed = this.fi("anim_speed", 10, 8);
        this.left_margin = this.fi("text_left_margin", 10, 5);
        this.text_yoffset = this.fi("text_yoffset", 10, 0);
        this.setBackground(this.bgcolor);
        this.text_align = this.stp("text_align", "center");
        this.text_color = new Color(this.fi("text_color", 16, 16777215));
        this.mouseover_text_color = new Color(this.fi("mouseover_text_color", 16, 16777215));
        this.click_text_color = new Color(this.fi("click_text_color", 16, 16777215));
        this.nf = new Font(this.stp("font_type", "Helvetica"), this.fi("font_style", 10, 1), this.fi("font_size", 10, 12));
        this.fmetrics = this.getFontMetrics(this.nf);
        this.i = 1;
        while (true) {
            this.bs = this.getParameter("button" + this.i);
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
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.button_image = this.getImage(this.getCodeBase(), "" + this.stp("button_image", ""));
        this.mouseover_button_image = this.getImage(this.getCodeBase(), "" + this.stp("mouseover_button_image", ""));
        this.click_button_image = this.getImage(this.getCodeBase(), "" + this.stp("click_button_image", ""));
        mediaTracker.addImage(this.button_image, 1);
        mediaTracker.addImage(this.mouseover_button_image, 2);
        mediaTracker.addImage(this.click_button_image, 3);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.str = new String[this.num + 1];
        this.links = new String[this.num + 1];
        this.stb = new String[this.num + 1];
        this.dur = new int[this.num + 1];
        this.xpos = new int[this.num + 1];
        this.ypos = new int[this.num + 1];
        this.target_frame = new String[this.num + 1];
        this.but = new Image[this.num + 1];
        this.overbut = new Image[this.num + 1];
        this.clickbut = new Image[this.num + 1];
        this.cb = new button_three[this.num + 1];
        this.i = 1;
        while (this.i <= this.num) {
            this.bs = this.stp("button_xy_position" + this.i, "0,0");
            final StringTokenizer stringTokenizer = new StringTokenizer(this.bs, ",");
            try {
                this.xpos[this.i] = Integer.parseInt(stringTokenizer.nextToken(), 10);
            }
            catch (Exception ex2) {
                this.xpos[this.i] = 0;
            }
            try {
                this.ypos[this.i] = Integer.parseInt(stringTokenizer.nextToken(), 10);
            }
            catch (Exception ex3) {
                this.ypos[this.i] = 0;
            }
            this.str[this.i] = this.stp("button" + this.i, "");
            this.links[this.i] = this.stp("link" + this.i, "");
            this.stb[this.i] = this.stp("status_bar_msg" + this.i, " ");
            this.target_frame[this.i] = this.stp("target_frame" + this.i, "_self");
            if (this.links[this.i].trim().equals("")) {
                this.dur[this.i] = 0;
            }
            else {
                this.dur[this.i] = 1;
            }
            ++this.i;
        }
        this.iw = this.button_image.getWidth(this);
        this.ih = this.button_image.getHeight(this);
        this.offimg2 = this.createImage(this.iw, this.ih);
        (this.offg2 = this.offimg2.getGraphics()).setFont(this.nf);
        this.txth = this.fmetrics.getDescent() + this.fmetrics.getAscent();
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
        catch (Exception ex4) {
            this.w = this.size().width;
            this.h = this.size().height;
        }
        this.container = this.getParent();
        while (!(this.container instanceof Frame)) {
            this.container = this.container.getParent();
        }
        this.frm = (Frame)this.container;
        this.yp = (this.ih - this.txth) / 2 + this.fmetrics.getAscent();
        this.i = 1;
        while (this.i <= this.num) {
            this.offg2.setColor(this.bgcolor);
            this.offg2.fillRect(0, 0, this.iw, this.ih);
            this.offg2.drawImage(this.button_image, 0, 0, this);
            this.offg2.setColor(this.text_color);
            if (this.text_align.equals("left")) {
                this.xp = this.left_margin;
            }
            else {
                this.xp = (this.iw - this.fmetrics.stringWidth("" + this.str[this.i])) / 2;
            }
            if (!this.infostr.equals("Copyright (c) 2002 Secret Plus Inc.,www.secretplus.com")) {
                this.offg2.drawString("copyright parameter error!", this.xp, this.yp + this.text_yoffset);
            }
            else {
                this.offg2.drawString("" + this.str[this.i], this.xp, this.yp + this.text_yoffset);
            }
            this.but[this.i] = this.createImage(this.offimg2.getSource());
            this.offg2.drawImage(this.but[this.i], 0, 0, this);
            this.offg2.setColor(this.bgcolor);
            this.offg2.fillRect(0, 0, this.iw, this.ih);
            this.offg2.drawImage(this.mouseover_button_image, 0, 0, this);
            this.offg2.setColor(this.mouseover_text_color);
            this.offg2.drawString("" + this.str[this.i], this.xp, this.yp + this.text_yoffset);
            this.overbut[this.i] = this.createImage(this.offimg2.getSource());
            this.offg2.drawImage(this.overbut[this.i], 0, 0, this);
            this.offg2.setColor(this.bgcolor);
            this.offg2.fillRect(0, 0, this.iw, this.ih);
            this.offg2.drawImage(this.click_button_image, 0, 0, this);
            this.offg2.setColor(this.click_text_color);
            this.offg2.drawString("" + this.str[this.i], this.xp, this.yp + this.text_yoffset);
            this.clickbut[this.i] = this.createImage(this.offimg2.getSource());
            this.offg2.drawImage(this.clickbut[this.i], 0, 0, this);
            ++this.i;
        }
        this.offg2.dispose();
        this.offimg = this.createImage(this.w, this.h);
        (this.offg = this.offimg.getGraphics()).clipRect(0, 0, this.w, this.h);
        this.offg.setColor(this.bgcolor);
        this.offg.fillRect(0, 0, this.w, this.h);
        this.i = 1;
        while (this.i <= this.num) {
            (this.cb[this.i] = new button_three(this, this.but[this.i], this.overbut[this.i], this.clickbut[this.i], this.iw, this.ih, this.i)).reshape(this.xpos[this.i], this.ypos[this.i], this.xpos[this.i] + this.iw, this.ypos[this.i] + this.ih);
            this.add(this.cb[this.i]);
            this.cb[this.i].show();
            this.cb[this.i].resize(this.iw, this.ih);
            ++this.i;
        }
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
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.rg) {
            this.showStatus("--- Unregistered version of Animated Menu Three Java applet,(www.secretplus.com) ---");
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
        this.i = 1;
        while (this.i <= this.num) {
            this.cb[this.i].repaint();
            ++this.i;
        }
        this.repaint();
        while (true) {
            this.i = 1;
            while (this.i <= this.num) {
                if (this.cb[this.i].d != 0) {
                    this.cb[this.i].prs();
                }
                ++this.i;
            }
            try {
                Thread.sleep(40L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public menu_three() {
        this.bs = "";
        this.text_align = "";
        this.regcode = "";
        this.infostr = "";
        this.rg = false;
    }
}
