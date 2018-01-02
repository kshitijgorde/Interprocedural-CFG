import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.util.Random;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Contour extends Applet implements Runnable
{
    int fontsize;
    int width;
    int height;
    int wait;
    int but_height;
    String[] str;
    String[] urls;
    URL[] links;
    String win_name;
    String button;
    String color;
    String fontcolor;
    String contcolor;
    String triangle;
    int im_width;
    int im_height;
    Thread anime;
    int x1;
    int x2;
    int y1;
    int y2;
    int xf;
    int yf;
    Random rand;
    Color gold;
    boolean flag;
    boolean up;
    boolean img;
    boolean loaded;
    boolean mousemoved;
    int Ymoved;
    Image mem_img;
    Font Helvetica;
    Font Courier;
    Color back;
    Color font;
    Color cont;
    Color trian;
    
    public Contour() {
        this.str = new String[20];
        this.urls = new String[20];
        this.links = new URL[20];
        this.rand = new Random();
        this.gold = new Color(255, 230, 0);
        this.flag = true;
        this.Courier = new Font("Courier", 0, 8);
    }
    
    public void paint(final Graphics g) {
        g.clearRect(0, 0, this.width, this.height);
        g.setFont(this.Helvetica);
        g.setColor(this.font);
        if (this.urls[1] != "" & this.img) {
            g.drawImage(this.mem_img, 0, 0, this);
        }
        if (this.str[1] != "") {
            g.drawString(this.str[1], this.leftPos(this.str[1]), this.topPos(this.str[1], 1));
        }
        for (int k = 2; this.urls[k] != "" & k < 20; ++k) {
            if (this.img) {
                g.drawImage(this.mem_img, 0, (k - 1) * this.im_height + (k - 1) * 10, this);
            }
            g.drawString(this.str[k], this.leftPos(this.str[k]), this.topPos(this.str[k], k));
        }
        g.setColor(this.trian);
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
        g.setColor(this.trian.darker().darker());
        Color gold_tmp = this.trian;
        int i = 1;
        do {
            gold_tmp = gold_tmp.darker();
            g.setColor(gold_tmp);
            g.drawLine(this.x1, this.y1 - i, Math.abs(this.x2 - this.x1) / 2, this.y2 - i);
        } while (++i < 4);
        g.drawLine(this.x1, this.y1 - 1, this.x2, this.y2 - 1);
        g.drawLine(this.x1, this.y1 + 1, this.x2, this.y2 + 1);
        gold_tmp = this.trian;
        i = 1;
        do {
            gold_tmp = gold_tmp.darker();
            g.setColor(gold_tmp);
            g.drawLine(Math.abs(this.x2 - this.x1) / 2, this.y2 - i, this.x2, this.y2 + i);
        } while (++i < 4);
        g.setColor(this.cont);
        if (this.mousemoved) {
            g.draw3DRect(0, this.Ymoved, this.im_width, this.im_height, true);
            this.mousemoved = false;
        }
        g.setFont(this.Courier);
        g.setColor(Color.white);
        g.drawString("Lunya", 1, 5);
    }
    
    public int topPos(final String s, final int i) {
        final int res = this.im_height / 2 + this.fontsize / 2 + this.im_height * (i - 1) + (i - 1) * 10;
        if (res > 0) {
            return res;
        }
        return 5;
    }
    
    public boolean imageUpdate(final Image img, final int flags, final int x, final int y, final int w, final int h) {
        if (img == this.mem_img) {
            if ((flags & 0x1) != 0x0) {
                this.im_width = w;
            }
            if ((flags & 0x2) != 0x0) {
                this.im_height = h;
            }
        }
        return super.imageUpdate(img, flags, x, y, w, h);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void start() {
        if (this.anime == null) {
            (this.anime = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        final int k = y / this.im_height;
        if (this.urls[k + 1] != "") {
            if (this.win_name != null) {
                this.getAppletContext().showDocument(this.links[k + 1], this.win_name);
            }
            else {
                this.getAppletContext().showDocument(this.links[k + 1]);
            }
        }
        return true;
    }
    
    public void run() {
        while (this.anime != null) {
            if (this.x2 != this.xf) {
                if (this.flag) {
                    if (this.x2 > 0) {
                        --this.x2;
                    }
                    else {
                        this.flag = false;
                    }
                }
                else if (this.x2 < this.width) {
                    ++this.x2;
                }
                else {
                    this.flag = true;
                }
            }
            else {
                if (this.yf < this.y1) {
                    this.up = false;
                }
                else {
                    this.up = true;
                }
                while (this.y2 != this.y1 && this.y2 != this.yf && this.y2 > 0 && this.y2 < this.height) {
                    if (this.up) {
                        ++this.y2;
                    }
                    else {
                        --this.y2;
                    }
                    this.repaint();
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
                this.x2 = this.x1;
                this.y2 = this.y1;
                this.x1 = this.xf;
                this.y1 = this.yf;
                this.xf = Math.abs(this.rand.nextInt() % this.width);
                this.yf = Math.abs(this.rand.nextInt() % this.height);
            }
            this.repaint();
            try {
                Thread.sleep(this.wait);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void init() {
        int i = 1;
        do {
            this.str[i] = "";
            this.urls[i] = "";
        } while (++i < 20);
        this.mem_img = this.getImage(this.getCodeBase(), this.getParameter("button"));
        if (this.mem_img == null) {
            this.img = false;
        }
        else {
            this.img = true;
        }
        int k = 1;
        do {
            final String sn = "name" + String.valueOf(k);
            final String sl = "link" + String.valueOf(k);
            final String sn_parm = this.getParameter(sn);
            final String sl_parm = this.getParameter(sl);
            if (sn_parm != null) {
                this.str[k] = sn_parm.toUpperCase();
            }
            if (sl_parm != null) {
                this.urls[k] = sl_parm;
            }
        } while (++k < 19);
        final String fontsizestr = this.getParameter("fontsize");
        if (fontsizestr == null) {
            this.fontsize = 18;
        }
        else {
            this.fontsize = Integer.parseInt(fontsizestr);
        }
        this.Helvetica = new Font("Helvetica", 2, this.fontsize);
        final String waitstr = this.getParameter("wait");
        if (waitstr == null) {
            this.wait = 100;
        }
        else {
            this.wait = Integer.parseInt(waitstr);
        }
        final String widthstr = this.getParameter("width");
        if (widthstr == null) {
            this.width = 150;
        }
        else {
            this.width = Integer.parseInt(widthstr);
        }
        final String heightstr = this.getParameter("height");
        if (heightstr == null) {
            this.height = 170;
        }
        else {
            this.height = Integer.parseInt(heightstr);
        }
        final String but_heightstr = this.getParameter("but_height");
        if (but_heightstr == null) {
            this.but_height = 50;
        }
        else {
            this.but_height = Integer.parseInt(but_heightstr);
        }
        this.win_name = this.getParameter("win_name");
        this.color = new String(this.getParameter("backcolor"));
        if (this.color.equals("white")) {
            this.back = new Color(255, 255, 255);
        }
        else if (this.color.equals("green")) {
            this.back = new Color(70, 90, 15);
        }
        else if (this.color.equals("yellow")) {
            this.back = new Color(255, 255, 0);
        }
        else if (this.color.equals("blue")) {
            this.back = new Color(46, 82, 180);
        }
        else if (this.color.equals("navy")) {
            this.back = new Color(0, 0, 80);
        }
        else if (this.color.equals("lime")) {
            this.back = new Color(0, 255, 0);
        }
        else if (this.color.equals("olive")) {
            this.back = new Color(80, 80, 0);
        }
        else if (this.color.equals("magenta")) {
            this.back = new Color(255, 0, 255);
        }
        else if (this.color.equals("orange")) {
            this.back = new Color(255, 165, 0);
        }
        else if (this.color.equals("red")) {
            this.back = new Color(255, 0, 0);
        }
        else if (this.color.equals("cyan")) {
            this.back = new Color(0, 255, 255);
        }
        else if (this.color.equals("gray")) {
            this.back = new Color(80, 80, 80);
        }
        else if (this.color.equals("pink")) {
            this.back = new Color(255, 175, 175);
        }
        else {
            this.back = new Color(0, 0, 0);
        }
        this.fontcolor = this.getParameter("fontcolor");
        if (this.fontcolor.equals("white")) {
            this.font = new Color(255, 255, 255);
        }
        else if (this.fontcolor.equals("green")) {
            this.font = new Color(70, 90, 15);
        }
        else if (this.fontcolor.equals("yellow")) {
            this.font = new Color(255, 255, 0);
        }
        else if (this.fontcolor.equals("blue")) {
            this.font = new Color(46, 82, 180);
        }
        else if (this.fontcolor.equals("navy")) {
            this.font = new Color(0, 0, 80);
        }
        else if (this.fontcolor.equals("lime")) {
            this.font = new Color(0, 255, 0);
        }
        else if (this.fontcolor.equals("olive")) {
            this.font = new Color(80, 80, 0);
        }
        else if (this.fontcolor.equals("magenta")) {
            this.font = new Color(255, 0, 255);
        }
        else if (this.fontcolor.equals("orange")) {
            this.font = new Color(255, 165, 0);
        }
        else if (this.fontcolor.equals("red")) {
            this.font = new Color(255, 0, 0);
        }
        else if (this.fontcolor.equals("cyan")) {
            this.font = new Color(0, 255, 255);
        }
        else if (this.fontcolor.equals("gray")) {
            this.font = new Color(80, 80, 80);
        }
        else if (this.fontcolor.equals("black")) {
            this.font = new Color(0, 0, 0);
        }
        else {
            this.font = Color.pink;
        }
        this.contcolor = this.getParameter("contourcolor");
        if (this.contcolor.equals("white")) {
            this.cont = new Color(255, 255, 255);
        }
        else if (this.contcolor.equals("green")) {
            this.cont = new Color(70, 90, 15);
        }
        else if (this.contcolor.equals("yellow")) {
            this.cont = new Color(255, 255, 0);
        }
        else if (this.contcolor.equals("navy")) {
            this.cont = new Color(0, 0, 80);
        }
        else if (this.contcolor.equals("lime")) {
            this.cont = new Color(0, 255, 0);
        }
        else if (this.contcolor.equals("olive")) {
            this.cont = new Color(80, 80, 0);
        }
        else if (this.contcolor.equals("magenta")) {
            this.cont = new Color(255, 0, 255);
        }
        else if (this.contcolor.equals("orange")) {
            this.cont = new Color(255, 165, 0);
        }
        else if (this.contcolor.equals("red")) {
            this.cont = new Color(255, 0, 0);
        }
        else if (this.contcolor.equals("cyan")) {
            this.cont = new Color(0, 255, 255);
        }
        else if (this.contcolor.equals("gray")) {
            this.cont = new Color(80, 80, 80);
        }
        else if (this.contcolor.equals("pink")) {
            this.cont = new Color(255, 175, 175);
        }
        else if (this.contcolor.equals("black")) {
            this.cont = new Color(0, 0, 0);
        }
        else {
            this.cont = Color.blue;
        }
        this.triangle = this.getParameter("triangle");
        if (this.triangle.equals("white")) {
            this.trian = new Color(255, 255, 255);
        }
        else if (this.triangle.equals("green")) {
            this.trian = new Color(70, 90, 15);
        }
        else if (this.triangle.equals("yellow")) {
            this.trian = new Color(255, 255, 0);
        }
        else if (this.triangle.equals("navy")) {
            this.trian = new Color(0, 0, 80);
        }
        else if (this.triangle.equals("lime")) {
            this.trian = new Color(0, 255, 0);
        }
        else if (this.triangle.equals("olive")) {
            this.trian = new Color(80, 80, 0);
        }
        else if (this.triangle.equals("magenta")) {
            this.trian = new Color(255, 0, 255);
        }
        else if (this.triangle.equals("orange")) {
            this.trian = new Color(255, 165, 0);
        }
        else if (this.triangle.equals("red")) {
            this.trian = new Color(255, 0, 0);
        }
        else if (this.triangle.equals("cyan")) {
            this.trian = new Color(0, 255, 255);
        }
        else if (this.triangle.equals("gray")) {
            this.trian = new Color(80, 80, 80);
        }
        else if (this.triangle.equals("pink")) {
            this.trian = new Color(255, 175, 175);
        }
        else if (this.triangle.equals("black")) {
            this.trian = new Color(0, 0, 0);
        }
        else {
            this.trian = this.gold;
        }
        this.resize(this.width, this.height);
        if (this.img) {
            this.im_width = this.mem_img.getWidth(this);
        }
        else {
            this.im_width = this.width;
        }
        if (this.img) {
            this.im_height = this.mem_img.getHeight(this);
        }
        else {
            this.im_height = this.but_height;
        }
        this.x1 = Math.abs(this.rand.nextInt() % this.width);
        this.x2 = Math.abs(this.rand.nextInt() % this.width);
        this.y1 = Math.abs(this.rand.nextInt() % this.height);
        this.y2 = Math.abs(this.rand.nextInt() % this.height);
        this.xf = Math.abs(this.rand.nextInt() % this.width);
        this.yf = Math.abs(this.rand.nextInt() % this.height);
        this.setBackground(this.back);
        for (int j = 1; this.urls[j] != "" & j < 20; ++j) {
            try {
                this.links[j] = new URL(this.urls[j]);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public int leftPos(final String s) {
        final int len = s.length();
        final int res = this.im_width / 2 - len * 3 * this.fontsize / 5 + this.fontsize;
        if (res > 0) {
            return res;
        }
        return 5;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        final int k = y / this.im_height;
        this.Ymoved = this.im_height * k;
        if (this.Ymoved != 0) {
            this.Ymoved += 10 * k;
        }
        if (this.urls[k + 1] != "") {
            this.mousemoved = true;
        }
        this.repaint();
        return true;
    }
}
