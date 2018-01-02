import java.util.Date;
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

public class sprites extends Applet implements Runnable
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
    String[] file;
    int[] w;
    int[] h;
    int width;
    int height;
    int R;
    int G;
    int B;
    int[] xx;
    int[] yy;
    int[] dx;
    int[] dy;
    int[] wrap;
    boolean loaded;
    String urllink;
    boolean registered;
    
    public sprites() {
        this.total = 4;
        this.fps = 15;
        this.copyright = false;
        this.border = 3;
        this.loaded = false;
        this.urllink = "http://www.thejmaker.com/";
        this.registered = false;
    }
    
    public void init() {
        super.init();
        final int intValue = new Integer(this.getParameter("regcode"));
        final int n = intValue / 100000;
        final int n2 = intValue / 10 - n * 10000;
        final int n3 = intValue % 10;
        if (n == 747 && n3 == n * n2 % 10) {
            this.registered = true;
        }
        this.d = this.size();
        this.width = this.d.width;
        this.height = this.d.height;
        this.resize(this.width, this.height);
        this.fps = new Integer(this.getParameter("fps"));
        this.delay = ((this.fps > 0) ? (1000 / this.fps) : 50);
        final int[] int1 = this.parseInt(this.getParameter("bgcolor"));
        this.R = int1[0];
        this.G = int1[1];
        this.B = int1[2];
        this.border = new Integer(this.getParameter("border"));
        this.urllink = this.getParameter("url");
        this.total = new Integer(this.getParameter("total"));
        this.file = new String[this.total];
        this.p = new Image[this.total];
        this.w = new int[this.total];
        this.h = new int[this.total];
        this.xx = new int[this.total];
        this.yy = new int[this.total];
        this.dx = new int[this.total];
        this.dy = new int[this.total];
        this.wrap = new int[this.total];
        for (int i = 0; i < this.total; ++i) {
            this.w[i] = (this.h[i] = -1);
            final String[] parse = this.parse(this.getParameter("menu" + i));
            this.file[i] = parse[0];
            final int[] int2 = this.parseInt(parse[1]);
            this.xx[i] = int2[0];
            this.yy[i] = int2[1];
            final int[] int3 = this.parseInt(parse[2]);
            this.dx[i] = int3[0];
            this.dy[i] = int3[1];
            this.wrap[i] = this.parseInt(parse[3])[0];
        }
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.showStatus("Connecting " + this.urllink + "...");
        try {
            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.urllink));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.registered) {
            this.showStatus(" sprites (C) 2000 The J Maker");
        }
        else {
            this.showStatus("");
        }
        this.copyright = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return this.copyright = true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void paintFrame(final Graphics graphics) {
        for (int i = 0; i < this.total; ++i) {
            if (!this.copyright) {
                final int[] xx = this.xx;
                final int n = i;
                xx[n] += this.dx[i];
                final int[] yy = this.yy;
                final int n2 = i;
                yy[n2] += this.dy[i];
            }
            if (this.dx[i] < 0 && this.xx[i] < -this.w[i]) {
                this.xx[i] = this.width - 1 + (this.w[i] - this.width % this.w[i]);
            }
            else if (this.dx[i] > 0 && this.xx[i] > this.width - 1) {
                this.xx[i] = -(this.w[i] - this.width % this.w[i]);
            }
            if (this.yy[i] < -this.h[i]) {
                this.yy[i] = this.height - 1 + (this.h[i] - this.height % this.h[i]);
            }
            else if (this.yy[i] > this.height - 1) {
                this.yy[i] = -(this.h[i] - this.height % this.h[i]);
            }
            if (this.p[i] != null) {
                if (this.wrap[i] == 0) {
                    graphics.drawImage(this.p[i], this.xx[i], this.yy[i], this);
                }
                else {
                    graphics.drawImage(this.p[i], this.xx[i], this.yy[i], this);
                    if (this.dx[i] != 0) {
                        int j = this.xx[i];
                        while (j > 0) {
                            j -= this.w[i];
                            graphics.drawImage(this.p[i], j, this.yy[i], this);
                        }
                        for (int k = this.xx[i] + this.w[i]; k < this.width; k += this.w[i]) {
                            graphics.drawImage(this.p[i], k, this.yy[i], this);
                        }
                    }
                    if (this.dy[i] != 0) {
                        int l = this.yy[i];
                        while (l > 0) {
                            l -= this.h[i];
                            graphics.drawImage(this.p[i], this.xx[i], l, this);
                        }
                        for (int n3 = this.yy[i] + this.h[i]; n3 < this.height; n3 += this.h[i]) {
                            graphics.drawImage(this.p[i], this.xx[i], n3, this);
                        }
                    }
                }
            }
        }
        if (this.border != 0) {
            graphics.setColor(new Color(128, 128, 128));
            for (int n4 = 0; n4 < this.border; ++n4) {
                if (n4 < (this.border + 1) / 2) {
                    graphics.draw3DRect(n4, n4, this.width - 1 - n4 * 2, this.height - 1 - n4 * 2, true);
                }
                else {
                    graphics.draw3DRect(n4, n4, this.width - 1 - n4 * 2, this.height - 1 - n4 * 2, false);
                }
            }
        }
        if (!this.registered && this.copyright) {
            graphics.setFont(new Font("Helvetica", 1, 12));
            final String s = "(C) The J Maker";
            final int n5 = this.width - graphics.getFontMetrics().stringWidth(s) - 4 - this.border;
            final int n6 = this.border + 15;
            graphics.setColor(Color.black);
            graphics.drawString(s, n5 - 1, n6 - 1);
            graphics.drawString(s, n5 - 1, n6 + 1);
            graphics.drawString(s, n5 + 1, n6 - 1);
            graphics.drawString(s, n5 + 1, n6 + 1);
            graphics.setColor(Color.yellow);
            graphics.drawString(s, n5, n6);
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
            for (int i = 0; i < this.total; ++i) {
                graphics.setColor(Color.red);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(Color.black);
                graphics.drawString("Loading \"" + this.file[i] + "\"...", 4, 29);
                graphics.setColor(Color.white);
                graphics.drawString("Loading \"" + this.file[i] + "\"...", 2, 27);
                this.p[i] = this.getImage(this.getCodeBase(), this.file[i]);
                while (this.w[i] < 0 || this.h[i] < 0) {
                    this.w[i] = this.p[i].getWidth(this);
                    this.h[i] = this.p[i].getHeight(this);
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex2) {}
                }
                this.showStatus("Image '" + this.file[i] + "' (" + this.w[i] + "x" + this.h[i] + ") ready");
            }
            if (!this.registered) {
                this.showStatus(" sprites (C) 2000 The J Maker");
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
            if (!this.registered) {
                final Date date = new Date();
                if (date.getMinutes() % 20 != 0 || date.getSeconds() >= 2) {
                    continue;
                }
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/"));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
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
        this.offGraphics.setColor(new Color(this.R, this.G, this.B));
        this.offGraphics.fillRect(0, 0, this.width, this.height);
        this.paintFrame(this.offGraphics);
        graphics.drawImage(this.offImage, 0, 0, null);
    }
}
