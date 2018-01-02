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

public class mimbounce extends Applet implements Runnable
{
    int delay;
    Thread animator;
    Dimension d;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    int total;
    int fps;
    int motion;
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
    String[] caption;
    String[] url;
    String[] frame;
    int[] w;
    int[] h;
    int width;
    int height;
    int maxstep;
    int id;
    int mousex;
    int mousey;
    int R;
    int G;
    int B;
    int highR;
    int highG;
    int highB;
    int loadR;
    int loadG;
    int loadB;
    int[] xx;
    int[] yy;
    int[] dx;
    int[] dy;
    boolean loaded;
    boolean registered;
    
    public mimbounce() {
        this.total = 4;
        this.fps = 15;
        this.motion = 1;
        this.copyright = false;
        this.border = 3;
        this.bgfile = "none";
        this.bgflag = false;
        this.bgwidth = -1;
        this.bgheight = -1;
        this.maxstep = 4;
        this.id = -1;
        this.mousex = 0;
        this.mousey = 0;
        this.loaded = false;
        this.registered = false;
    }
    
    public void init() {
        super.init();
        final int intValue = new Integer(this.getParameter("regcode"));
        final int n = intValue / 100000;
        final int n2 = intValue / 10 - n * 10000;
        final int n3 = intValue % 10;
        if (n == 439 && n3 == n * n2 % 10) {
            this.registered = true;
        }
        this.d = this.size();
        this.width = this.d.width;
        this.height = this.d.height;
        this.resize(this.width, this.height);
        this.fps = new Integer(this.getParameter("fps"));
        this.delay = ((this.fps > 0) ? (1000 / this.fps) : 100);
        this.maxstep = new Integer(this.getParameter("maxstep"));
        this.motion = new Integer(this.getParameter("motion"));
        this.bgfile = this.getParameter("bgimage");
        if (this.bgfile.equalsIgnoreCase("none")) {
            this.bgflag = false;
        }
        else {
            this.bgflag = true;
        }
        final int[] int1 = this.parseInt(this.getParameter("bgcolor"));
        this.R = int1[0];
        this.G = int1[1];
        this.B = int1[2];
        final int[] int2 = this.parseInt(this.getParameter("highcolor"));
        this.highR = int2[0];
        this.highG = int2[1];
        this.highB = int2[2];
        final int[] int3 = this.parseInt(this.getParameter("loadcolor"));
        this.loadR = int3[0];
        this.loadG = int3[1];
        this.loadB = int3[2];
        this.border = new Integer(this.getParameter("border"));
        this.total = new Integer(this.getParameter("total"));
        this.file = new String[this.total];
        this.caption = new String[this.total];
        this.url = new String[this.total];
        this.frame = new String[this.total];
        this.p = new Image[this.total];
        this.q = new Image[this.total];
        this.w = new int[this.total];
        this.h = new int[this.total];
        this.xx = new int[this.total];
        this.yy = new int[this.total];
        this.dx = new int[this.total];
        this.dy = new int[this.total];
        for (int i = 0; i < this.total; ++i) {
            this.w[i] = (this.h[i] = -1);
            this.dx[i] = (int)(Math.random() * this.maxstep + 1.0);
            if (Math.random() > 0.5) {
                this.dx[i] = -this.dx[i];
            }
            this.dy[i] = (int)(Math.random() * this.maxstep + 1.0);
            if (Math.random() > 0.5) {
                this.dy[i] = -this.dy[i];
            }
            this.xx[i] = this.width / 2;
            this.yy[i] = this.height / 2;
            final String[] parse = this.parse(this.getParameter("menu" + i));
            this.caption[i] = parse[0];
            this.file[i] = parse[1];
            this.url[i] = parse[2];
            this.frame[i] = parse[3];
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
                this.showStatus(" mimbounce (C) 2001 The J Maker");
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
        }
        this.mousex = mousex;
        this.mousey = mousey;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.registered) {
            this.showStatus(" mimbounce (C) 2001 The J Maker");
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
        for (int k = 0; k < this.total; ++k) {
            if (this.motion == 2) {
                this.dx[k] = (int)(Math.random() * (2 * this.maxstep + 1)) - this.maxstep;
                this.dy[k] = (int)(Math.random() * (2 * this.maxstep + 1)) - this.maxstep;
                if (this.xx[k] + this.dx[k] < 0 || this.xx[k] + this.dx[k] + this.w[k] > this.width - 1) {
                    this.dx[k] = -this.dx[k];
                }
                if (this.yy[k] + this.dy[k] < 0 || this.yy[k] + this.dy[k] + this.h[k] > this.height - 1) {
                    this.dy[k] = -this.dy[k];
                }
            }
            else {
                if (this.dx[k] < 0 && this.xx[k] + this.dx[k] < 0) {
                    this.dx[k] = (int)(Math.random() * (this.maxstep + 1) + 1.0);
                }
                else if (this.dx[k] >= 0 && this.xx[k] + this.w[k] + this.dx[k] >= this.width - 1) {
                    this.dx[k] = -(int)(Math.random() * (this.maxstep + 1) + 1.0);
                }
                if (this.dy[k] < 0 && this.yy[k] + this.dy[k] < 0) {
                    this.dy[k] = (int)(Math.random() * (this.maxstep + 1) + 1.0);
                }
                else if (this.dy[k] >= 0 && this.yy[k] + this.h[k] + this.dy[k] >= this.height - 1) {
                    this.dy[k] = -(int)(Math.random() * (this.maxstep + 1) + 1.0);
                }
            }
            if (this.copyright) {
                if (this.dx[k] >= 0) {
                    final int[] xx = this.xx;
                    final int n = k;
                    xx[n] += (int)(this.dx[k] * 0.5f + 0.5f);
                }
                else {
                    final int[] xx2 = this.xx;
                    final int n2 = k;
                    xx2[n2] += (int)(this.dx[k] * 0.5f - 0.5f);
                }
                if (this.dy[k] >= 0) {
                    final int[] yy = this.yy;
                    final int n3 = k;
                    yy[n3] += (int)(this.dy[k] * 0.5f + 0.5f);
                }
                else {
                    final int[] yy2 = this.yy;
                    final int n4 = k;
                    yy2[n4] += (int)(this.dy[k] * 0.5f - 0.5f);
                }
            }
            else {
                final int[] xx3 = this.xx;
                final int n5 = k;
                xx3[n5] += this.dx[k];
                final int[] yy3 = this.yy;
                final int n6 = k;
                yy3[n6] += this.dy[k];
            }
            if (this.id != k) {
                final int n7 = 70 + 130 * (this.total - k - 1) / (this.total - 1);
                graphics.setColor(new Color(n7, n7, n7));
                graphics.setFont(new Font("Helvetica", 1, 12));
                graphics.drawLine(0, this.yy[k] + this.h[k] / 2, this.width - 1, this.yy[k] + this.h[k] / 2);
                graphics.drawLine(this.xx[k] + this.w[k] / 2, 0, this.xx[k] + this.w[k] / 2, this.height - 1);
                graphics.setColor(new Color(20, 20, 20));
                graphics.fillRect(this.xx[k] + 5, this.yy[k] + 5, this.w[k], this.h[k]);
                if (this.copyright) {
                    graphics.drawString(this.caption[k], this.xx[k] + this.w[k] + 6, this.yy[k] + this.h[k] / 2 - 6);
                    graphics.drawString(this.caption[k], this.xx[k] + this.w[k] + 8, this.yy[k] + this.h[k] / 2 - 4);
                    graphics.drawString(this.caption[k], this.xx[k] + this.w[k] + 6, this.yy[k] + this.h[k] / 2 - 4);
                    graphics.drawString(this.caption[k], this.xx[k] + this.w[k] + 8, this.yy[k] + this.h[k] / 2 - 6);
                    graphics.setColor(new Color(this.R, this.G, this.B));
                    graphics.drawString(this.caption[k], this.xx[k] + this.w[k] + 7, this.yy[k] + this.h[k] / 2 - 5);
                }
                if (this.q[k] != null) {
                    graphics.drawImage(this.q[k], this.xx[k], this.yy[k], this);
                }
            }
        }
        if (this.id >= 0) {
            graphics.setColor(new Color(this.highR, this.highG, this.highB));
            graphics.setFont(new Font("Helvetica", 1, 12));
            graphics.drawLine(0, this.yy[this.id] + this.h[this.id] / 2, this.width - 1, this.yy[this.id] + this.h[this.id] / 2);
            graphics.drawLine(this.xx[this.id] + this.w[this.id] / 2, 0, this.xx[this.id] + this.w[this.id] / 2, this.height - 1);
            graphics.fillRect(this.xx[this.id] + 5, this.yy[this.id] + 5, this.w[this.id], this.h[this.id]);
            graphics.drawString(this.caption[this.id], this.xx[this.id] + this.w[this.id] + 6, this.yy[this.id] + this.h[this.id] / 2 - 6);
            graphics.drawString(this.caption[this.id], this.xx[this.id] + this.w[this.id] + 8, this.yy[this.id] + this.h[this.id] / 2 - 4);
            graphics.drawString(this.caption[this.id], this.xx[this.id] + this.w[this.id] + 6, this.yy[this.id] + this.h[this.id] / 2 - 4);
            graphics.drawString(this.caption[this.id], this.xx[this.id] + this.w[this.id] + 8, this.yy[this.id] + this.h[this.id] / 2 - 6);
            graphics.setColor(new Color(this.R, this.G, this.B));
            graphics.drawString(this.caption[this.id], this.xx[this.id] + this.w[this.id] + 7, this.yy[this.id] + this.h[this.id] / 2 - 5);
            if (this.p[this.id] != null) {
                graphics.drawImage(this.p[this.id], this.xx[this.id], this.yy[this.id], this);
            }
        }
        if (this.border != 0) {
            graphics.setColor(new Color(128, 128, 128));
            for (int l = 0; l < this.border; ++l) {
                if (l <= (this.border + 1) / 2) {
                    graphics.draw3DRect(l, l, this.width - 1 - l * 2, this.height - 1 - l * 2, true);
                }
                else {
                    graphics.draw3DRect(l, l, this.width - 1 - l * 2, this.height - 1 - l * 2, false);
                }
            }
        }
        if (!this.registered && this.copyright) {
            graphics.setFont(new Font("TimesRoman", 1, 12));
            final String s = "(C) The J Maker";
            final int n8 = this.width - graphics.getFontMetrics().stringWidth(s) - 4 - this.border;
            final int n9 = 16 + this.border;
            graphics.setColor(Color.black);
            graphics.drawString(s, n8 - 1, n9 - 1);
            graphics.drawString(s, n8 - 1, n9 + 1);
            graphics.drawString(s, n8 + 1, n9 - 1);
            graphics.drawString(s, n8 + 1, n9 + 1);
            graphics.setColor(Color.yellow);
            graphics.drawString(s, n8, n9);
        }
    }
    
    String[] parse(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
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
            graphics.setFont(new Font("Helvetica", 1, 12));
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
                this.q[i] = new mimbounce2(this.p[i], this.w[i], this.h[i]).imageNew;
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
                this.showStatus(" mimbounce (C) 2001 The J Maker");
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
