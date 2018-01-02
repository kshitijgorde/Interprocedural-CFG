import java.awt.Font;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vInferno extends Applet implements Runnable, Cloneable
{
    private Thread m_backg;
    vInferno ftxt;
    private Image buf;
    private Image buf2;
    private Graphics gbuf;
    private Graphics gbuf2;
    private int screen_W;
    private int screen_H;
    private int mouse_X;
    private int mouse_Y;
    int[] pixels;
    int[] pixDest;
    boolean isIdle;
    boolean busy;
    String msg;
    String[] msgParsed;
    int[] seq;
    int currentSeq;
    int waiter;
    int size;
    MemoryImageSource memIm;
    int fontsize;
    String fontname;
    private Graphics m_Graphics;
    private final String PARAM_msg = "message";
    private final String PARAM_size = "ferocity";
    private final String PARAM_fontname = "fontname";
    private final String PARAM_fontsize = "fontsize";
    
    public void stop() {
        if (this.m_backg != null) {
            this.m_backg.stop();
            this.m_backg = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int mouse_X, final int mouse_Y) {
        this.mouse_X = mouse_X;
        this.mouse_Y = mouse_Y;
        this.isIdle = false;
        return true;
    }
    
    void parseMsg() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.msg.length(); ++i) {
            if (this.msg.charAt(i) == '^') {
                ++n;
            }
        }
        this.seq = new int[n];
        this.msgParsed = new String[n + 1];
        int n3 = 0;
        for (int j = 0; j < this.msg.length(); ++j) {
            if (this.msg.charAt(j) == '^') {
                this.seq[n3] = Integer.parseInt(String.valueOf(this.msg.charAt(j + 1)));
                if (this.seq[n3] > 4) {
                    this.seq[n3] = 4;
                }
                if (n2 < j) {
                    this.msgParsed[n3] = this.msg.substring(n2, j);
                    n2 = j + 2;
                }
                ++n3;
                j += 2;
            }
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        return this.isIdle = true;
    }
    
    void GetParameters(final String[] array) {
        final String getParameter = this.GetParameter("message", array);
        if (getParameter != null) {
            this.msg = getParameter;
        }
        this.parseMsg();
        final String getParameter2 = this.GetParameter("ferocity", array);
        if (getParameter2 != null) {
            this.size = Integer.parseInt(getParameter2);
        }
        if (this.size < 0) {
            this.size = 0;
        }
        if (this.size > 100) {
            this.size = 100;
        }
        this.size = 255 - (100 - this.size);
        final String getParameter3 = this.GetParameter("fontname", array);
        if (getParameter3 != null) {
            this.fontname = getParameter3;
        }
        final String getParameter4 = this.GetParameter("fontsize", array);
        if (getParameter4 != null) {
            this.fontsize = Integer.parseInt(getParameter4);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.colorSpot();
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "message", "String", "Text to display, with embedded escape sequences" }, { "ferocity", "int", "smoke trail length" } };
    }
    
    public void destroy() {
    }
    
    void initFrame() {
        switch (this.seq[this.currentSeq]) {
            case 0:
            case 3: {
                this.mouse_Y = this.screen_H;
                this.mouse_X = 10;
            }
            case 1: {
                this.mouse_X = this.screen_W - 20;
                this.mouse_Y = this.screen_H - this.fontsize;
            }
            case 2: {
                this.mouse_X = 0;
                this.mouse_Y = -this.fontsize;
            }
            case 4: {
                this.mouse_X = this.screen_W - 20;
                this.mouse_Y = this.screen_H - this.fontsize * 2;
            }
            default: {}
        }
    }
    
    String GetParameter(final String s, final String[] array) {
        return this.getParameter(s);
    }
    
    void nextFrame() {
        switch (this.seq[this.currentSeq]) {
            case 0: {
                if (this.mouse_Y > this.fontsize) {
                    this.mouse_Y -= 4;
                    return;
                }
                if (this.waiter-- == 0) {
                    this.nextSeq();
                    return;
                }
                break;
            }
            case 1: {
                final int n = (int)(this.fontsize / 2 * Math.cos(this.mouse_X / this.fontsize * 2.0));
                if (this.mouse_X > 6) {
                    this.mouse_X -= 6;
                    this.mouse_Y = this.screen_H - this.fontsize * 2 + n;
                    return;
                }
                if (this.waiter-- == 0) {
                    this.nextSeq();
                    return;
                }
                break;
            }
            case 2: {
                if (this.mouse_Y < 60) {
                    this.mouse_Y += 4;
                    return;
                }
                if (this.waiter-- == 0) {
                    this.nextSeq();
                    return;
                }
                break;
            }
            case 3: {
                if (this.mouse_Y > -this.fontsize) {
                    this.mouse_Y -= 6;
                    return;
                }
                this.nextSeq();
            }
            case 4: {
                final int n2 = (int)(this.fontsize * Math.cos(this.mouse_X / 15.0));
                if (this.mouse_X > 6) {
                    if (n2 < 0) {
                        this.mouse_Y = this.screen_H - this.fontsize - this.fontsize / 2 + n2;
                    }
                    this.mouse_X -= 6;
                    return;
                }
                if (this.waiter-- == 0) {
                    this.nextSeq();
                    return;
                }
                break;
            }
        }
    }
    
    public vInferno() {
        this.isIdle = true;
        this.msg = "Xavier^2Potier^1";
        this.waiter = 10;
        this.fontsize = 40;
        this.fontname = "Arial";
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void getPixels(final Image image, final int n, final int n2, final int n3, final int n4) {
        this.pixels = new int[n3 * n4];
        if (this.pixDest == null) {
            this.pixDest = new int[n3 * n4];
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, this.pixels, 0, n3);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted  waiting  for  pixels!");
            return;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image  fetch  aborted  or  errored");
        }
    }
    
    public void start() {
        if (this.m_backg == null) {
            (this.m_backg = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: vInferno\r\n" + "Author: Xavier Potier\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    public void colorSpot() {
        if (this.busy) {
            return;
        }
        if (this.isIdle) {
            this.nextFrame();
        }
        for (int i = 0; i < this.screen_H - 1; ++i) {
            int screen_H = i - this.mouse_Y;
            if (screen_H > this.screen_H) {
                screen_H = this.screen_H;
            }
            final int n = screen_H % this.screen_H * this.screen_W;
            final int n2 = i * this.screen_W;
            for (int j = 0; j < this.screen_W - 1; ++j) {
                final int n3 = (int)(2.0 * Math.random());
                final int n4 = j - this.mouse_X;
                final int n5 = j + n2;
                final int n6 = this.pixDest[n5 + this.screen_W] & 0xFF0000;
                final int n7 = this.pixDest[n5 + this.screen_W] & 0xFF00;
                if (n4 > 0 && screen_H > 0 && (this.pixels[n4 + n] & 0xFF0000) != 0x0 && this.waiter > 8) {
                    this.pixDest[n5] = -8960;
                }
                else if (n6 != 0) {
                    this.pixDest[n3 + n5] = ((n6 * this.size >> 8 & 0xFF0000) | 0xFF000000 | (n7 * this.size * 3 / 4 >> 8 & 0xFF00));
                }
                else {
                    this.pixDest[n3 + n5] = -16777216;
                }
            }
        }
        final Graphics gbuf = this.gbuf;
        final MemoryImageSource memIm = new MemoryImageSource(this.screen_W, this.screen_H, this.pixDest, 0, this.screen_W);
        this.memIm = memIm;
        gbuf.drawImage(this.createImage(memIm), 0, 0, this.screen_W * 2, this.screen_H * 2, this);
        try {
            Thread.sleep(30L);
        }
        catch (InterruptedException ex) {
            this.stop();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.v-era.com"));
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public void run() {
        this.drawText(true);
    Label_0005_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.repaint();
                        Thread.sleep(50L);
                    }
                }
                catch (InterruptedException ex) {
                    this.stop();
                    continue Label_0005_Outer;
                }
                continue;
            }
        }
    }
    
    public void init() {
        this.GetParameters(null);
        this.screen_W = this.size().width;
        this.screen_H = this.size().height + 2;
        this.buf = this.createImage(this.screen_W, this.screen_H);
        this.buf2 = this.createImage(this.screen_W, this.screen_H);
        this.gbuf = this.buf.getGraphics();
        this.gbuf2 = this.buf2.getGraphics();
        final int n = this.screen_W / 2;
        this.screen_W = n;
        this.mouse_X = n;
        final int n2 = this.screen_H / 2;
        this.screen_H = n2;
        this.mouse_Y = n2;
    }
    
    void nextSeq() {
        this.waiter = 10;
        ++this.currentSeq;
        if (this.currentSeq == this.seq.length) {
            this.currentSeq %= this.seq.length;
        }
        this.drawText(false);
        this.initFrame();
    }
    
    void drawText(final boolean b) {
        this.gbuf2.setColor(new Color(0, 0, 0));
        this.gbuf2.fillRect(0, 0, this.screen_W, this.screen_H);
        this.gbuf2.setColor(Color.white);
        this.gbuf2.setFont(new Font(this.fontname, 1, this.fontsize));
        this.gbuf2.drawString(this.msgParsed[this.currentSeq], 0, 40);
        this.getPixels(this.buf2, 0, 0, this.screen_W, this.screen_H);
        this.initFrame();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.mouse_X = n / 2 - this.gbuf.getFontMetrics().stringWidth(this.msgParsed[this.currentSeq]);
        this.mouse_Y = n2 / 2 - this.gbuf.getFontMetrics().getHeight();
        return true;
    }
}
