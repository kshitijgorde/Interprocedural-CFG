import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mimdesc extends Applet implements Runnable
{
    int delay;
    Thread animator;
    Dimension d;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    int total;
    int fps;
    boolean copyright;
    int border;
    Image[] p;
    Image[] q;
    Image bg;
    String bgfile;
    boolean bgflag;
    int bgwidth;
    int bgheight;
    String[] file;
    String[] head;
    String[] url;
    String[] frame;
    String[] desc;
    int[] w;
    int[] h;
    int width;
    int height;
    int id;
    int mousex;
    int mousey;
    int R;
    int G;
    int B;
    int highR;
    int highG;
    int highB;
    int fgR;
    int fgG;
    int fgB;
    int loadR;
    int loadG;
    int loadB;
    int[] xx;
    int[] yy;
    int descx;
    int descy;
    int descwidth;
    int descheight;
    String descmessage;
    String fontface;
    int fontsize;
    boolean loaded;
    boolean registered;
    
    public mimdesc() {
        this.total = 4;
        this.fps = 15;
        this.copyright = false;
        this.border = 3;
        this.bgfile = "none";
        this.bgflag = false;
        this.bgwidth = -1;
        this.bgheight = -1;
        this.id = -1;
        this.mousex = 0;
        this.mousey = 0;
        this.descmessage = "Check images for details";
        this.fontface = "TimesRoman";
        this.fontsize = 10;
        this.loaded = false;
        this.registered = false;
    }
    
    public void init() {
        super.init();
        final int intValue = new Integer(this.getParameter("regcode"));
        final int n = intValue / 100000;
        final int n2 = intValue / 10 - n * 10000;
        final int n3 = intValue % 10;
        if (n == 512 && n3 == n * n2 % 10) {
            this.registered = true;
        }
        this.d = this.size();
        this.width = this.d.width;
        this.height = this.d.height;
        this.resize(this.width, this.height);
        this.delay = ((this.fps > 0) ? (1000 / this.fps) : 100);
        this.bgfile = this.getParameter("bgimage");
        if (this.bgfile.equalsIgnoreCase("none")) {
            this.bgflag = false;
        }
        else {
            this.bgflag = true;
        }
        this.fontface = this.getParameter("fontface");
        this.fontsize = new Integer(this.getParameter("fontsize"));
        final int[] int1 = this.parseInt(this.getParameter("fgcolor"));
        this.fgR = int1[0];
        this.fgG = int1[1];
        this.fgB = int1[2];
        final int[] int2 = this.parseInt(this.getParameter("bgcolor"));
        this.R = int2[0];
        this.G = int2[1];
        this.B = int2[2];
        final int[] int3 = this.parseInt(this.getParameter("highcolor"));
        this.highR = int3[0];
        this.highG = int3[1];
        this.highB = int3[2];
        final int[] int4 = this.parseInt(this.getParameter("loadcolor"));
        this.loadR = int4[0];
        this.loadG = int4[1];
        this.loadB = int4[2];
        this.border = new Integer(this.getParameter("border"));
        final String[] parse = this.parse(this.getParameter("descpanel"), "|");
        final int[] int5 = this.parseInt(parse[0]);
        this.descx = int5[0];
        this.descy = int5[1];
        final int[] int6 = this.parseInt(parse[1]);
        this.descwidth = int6[0];
        this.descheight = int6[1];
        this.descmessage = this.getParameter("descmessage");
        this.total = new Integer(this.getParameter("total"));
        this.file = new String[this.total];
        this.head = new String[this.total];
        this.url = new String[this.total];
        this.frame = new String[this.total];
        this.desc = new String[this.total];
        this.p = new Image[this.total];
        this.q = new Image[this.total];
        this.w = new int[this.total];
        this.h = new int[this.total];
        this.xx = new int[this.total];
        this.yy = new int[this.total];
        for (int i = 0; i < this.total; ++i) {
            this.w[i] = (this.h[i] = -1);
            final String[] parse2 = this.parse(this.getParameter("menu" + i), "|");
            this.file[i] = parse2[0];
            final int[] int7 = this.parseInt(parse2[1]);
            this.xx[i] = int7[0];
            this.yy[i] = int7[1];
            this.url[i] = parse2[2];
            this.frame[i] = parse2[3];
            this.head[i] = parse2[4];
            this.desc[i] = " " + parse2[5];
        }
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int mousex, final int mousey) {
        this.mousex = mousex;
        this.mousey = mousey;
        this.id = -1;
        for (int i = 0; i < this.total; ++i) {
            if (mousex >= this.xx[i] && mousex <= this.xx[i] + this.w[i] - 1 && mousey >= this.yy[i] && mousey <= this.yy[i] + this.h[i] - 1) {
                this.id = i;
                this.showStatus(this.url[this.id]);
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.url[this.id]), this.frame[this.id]);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
        if (this.id < 0) {
            if (!this.registered) {
                this.showStatus(" mimdesc (C) 2000 The J Maker");
            }
            else {
                this.showStatus("");
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int mousex, final int mousey) {
        if (this.id >= 0) {
            final int[] xx = this.xx;
            final int id = this.id;
            xx[id] += mousex - this.mousex;
            final int[] yy = this.yy;
            final int id2 = this.id;
            yy[id2] += mousey - this.mousey;
            this.showStatus("x: " + this.xx[this.id] + ", y: " + this.yy[this.id]);
        }
        this.mousex = mousex;
        this.mousey = mousey;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.registered) {
            this.showStatus(" mimdesc (C) 2000 The J Maker");
        }
        else {
            this.showStatus("");
        }
        this.id = -1;
        this.copyright = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mousex, final int mousey) {
        this.mousex = mousex;
        this.mousey = mousey;
        for (int i = 0; i < this.total; ++i) {
            if (mousex >= this.xx[i] && mousex <= this.xx[i] + this.w[i] - 1 && mousey >= this.yy[i] && mousey <= this.yy[i] + this.h[i] - 1) {
                this.id = i;
                this.showStatus(this.url[this.id]);
                break;
            }
        }
        return this.copyright = true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void paintFrame(final Graphics graphics) {
        if (this.bgflag && this.bg != null) {
            for (int i = 0; i < this.height - 1; i += this.bgheight) {
                for (int j = 0; j < this.width - 1; j += this.bgwidth) {
                    graphics.drawImage(this.bg, j, i, this);
                }
            }
        }
        if (this.R != -1 && this.G != -1 && this.B != -1) {
            graphics.setColor(new Color(this.R, this.G, this.B));
            graphics.fillRect(this.descx, this.descy, this.descwidth, this.descheight);
        }
        graphics.setColor(new Color(this.fgR, this.fgG, this.fgB));
        graphics.drawRect(this.descx, this.descy, this.descwidth - 1, this.descheight - 1);
        for (int k = 0; k < this.total; ++k) {
            if (this.id < 0 || this.id == k) {
                if (this.p[k] != null) {
                    graphics.drawImage(this.p[k], this.xx[k], this.yy[k], this);
                }
            }
            else if (this.q[k] != null) {
                graphics.drawImage(this.q[k], this.xx[k], this.yy[k], this);
            }
        }
        if (this.id >= 0) {
            graphics.setColor(new Color(this.highR, this.highG, this.highB));
            graphics.drawRect(this.xx[this.id] - 2, this.yy[this.id] - 2, this.w[this.id] + 3, this.h[this.id] + 3);
            graphics.setColor(new Color(this.fgR, this.fgG, this.fgB));
            graphics.setFont(new Font(this.fontface, 1, this.fontsize + 2));
            final int height = graphics.getFontMetrics().getHeight();
            graphics.getFontMetrics().getDescent();
            final int stringWidth = graphics.getFontMetrics().stringWidth(this.head[this.id]);
            int n = this.descy + height + 10;
            int n2 = 2;
            graphics.drawString(this.head[this.id], this.descx + (this.descwidth - stringWidth) / 2, n);
            graphics.setFont(new Font(this.fontface, 0, this.fontsize));
            final int height2 = graphics.getFontMetrics().getHeight();
            graphics.getFontMetrics().getDescent();
            n += 15;
            final String[] parse = this.parse(this.desc[this.id], " ");
            for (int l = 0; l < parse.length; ++l) {
                final int stringWidth2 = graphics.getFontMetrics().stringWidth(parse[l]);
                if (n2 + 4 + stringWidth2 < this.descwidth) {
                    n2 += 2;
                }
                else {
                    n2 = 4;
                    n += height2;
                }
                graphics.setColor(new Color(this.fgR, this.fgG, this.fgB));
                graphics.drawString(parse[l], this.descx + n2, n);
                n2 += stringWidth2;
            }
        }
        else {
            graphics.setFont(new Font(this.fontface, 0, this.fontsize));
            graphics.setColor(new Color(this.fgR, this.fgG, this.fgB));
            if (!this.descmessage.equalsIgnoreCase("none")) {
                graphics.drawString(this.descmessage, this.descx + 4, this.descy + this.descheight - this.fontsize);
            }
        }
        if (this.border != 0) {
            graphics.setColor(new Color(128, 128, 128));
            for (int n3 = 0; n3 < this.border; ++n3) {
                if (n3 <= (this.border + 1) / 2) {
                    graphics.draw3DRect(n3, n3, this.width - 1 - n3 * 2, this.height - 1 - n3 * 2, true);
                }
                else {
                    graphics.draw3DRect(n3, n3, this.width - 1 - n3 * 2, this.height - 1 - n3 * 2, false);
                }
            }
        }
        if (!this.registered && this.copyright) {
            graphics.setFont(new Font("Helvetica", 1, 12));
            final String s = "(C) The J Maker";
            final int n4 = this.width - graphics.getFontMetrics().stringWidth(s) - 4 - this.border;
            final int n5 = this.height - 4 - this.border;
            graphics.setColor(Color.black);
            graphics.drawString(s, n4 - 1, n5 - 1);
            graphics.drawString(s, n4 - 1, n5 + 1);
            graphics.drawString(s, n4 + 1, n5 - 1);
            graphics.drawString(s, n4 + 1, n5 + 1);
            graphics.setColor(Color.yellow);
            graphics.drawString(s, n4, n5);
        }
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void run() {
        if (!this.loaded) {
            final Graphics graphics = this.getGraphics();
            for (int i = 0; i < this.total; ++i) {
                graphics.setColor(new Color(this.loadR, this.loadG, this.loadB));
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(Color.black);
                graphics.drawString("Loading \"" + this.file[i] + "\"...", 2, 28);
                graphics.drawString("Loading \"" + this.file[i] + "\"...", 3, 27);
                graphics.setColor(Color.white);
                graphics.drawString("Loading \"" + this.file[i] + "\"...", 2, 27);
                this.p[i] = this.getImage(this.getCodeBase(), this.file[i]);
                while (this.w[i] < 0 || this.h[i] < 0) {
                    this.w[i] = this.p[i].getWidth(this);
                    this.h[i] = this.p[i].getHeight(this);
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex) {}
                }
                this.showStatus("Image '" + this.file[i] + "' (" + this.w[i] + "x" + this.h[i] + ") ready");
                this.q[i] = new mimdesc2(this.p[i], this.w[i], this.h[i]).imageNew;
                this.showStatus("Grey image '" + this.file[i] + "' (" + this.w[i] + "x" + this.h[i] + ") ready");
            }
            if (this.bgflag) {
                graphics.setColor(new Color(this.loadR, this.loadG, this.loadB));
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(Color.black);
                graphics.drawString("Loading background \"" + this.bgfile + "\"...", 2, 28);
                graphics.drawString("Loading background \"" + this.bgfile + "\"...", 3, 27);
                graphics.setColor(Color.white);
                graphics.drawString("Loading background \"" + this.bgfile + "\"...", 2, 27);
                this.bg = this.getImage(this.getCodeBase(), this.bgfile);
                while (this.bgwidth < 0 || this.bgheight < 0) {
                    this.bgwidth = this.bg.getWidth(this);
                    this.bgheight = this.bg.getHeight(this);
                    try {
                        Thread.sleep(50L);
                    }
                    catch (Exception ex2) {}
                }
            }
            if (!this.registered) {
                this.showStatus(" mimdesc (C) 2000 The J Maker");
            }
            else {
                this.showStatus("");
            }
            this.loaded = true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.animator) {
            this.repaint();
            try {
                currentTimeMillis += this.delay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex3) {
                break;
            }
        }
    }
    
    public void start() {
        (this.animator = new Thread(this)).start();
    }
    
    public void stop() {
        this.animator = null;
        this.offImage = null;
        this.offGraphics = null;
    }
    
    public void update(final Graphics graphics) {
        if (!this.loaded) {
            return;
        }
        if (this.offGraphics == null || this.width != this.offDimension.width || this.height != this.offDimension.height) {
            this.offDimension = this.d;
            this.offImage = this.createImage(this.width, this.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        if (!this.bgflag) {
            this.offGraphics.setColor(new Color(this.R, this.G, this.B));
            this.offGraphics.fillRect(0, 0, this.width, this.height);
        }
        this.paintFrame(this.offGraphics);
        graphics.drawImage(this.offImage, 0, 0, null);
    }
}
