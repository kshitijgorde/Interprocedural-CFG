import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TF_Claud extends Applet implements Runnable
{
    private int i;
    private int i1;
    private int x;
    private int y;
    private int color;
    private int cnt;
    private double tstval;
    private int cnt1;
    private int[] cloud;
    private int[] cloud1;
    private int[] cloud2;
    private int[] PixelIndex;
    private int[] PixelIndexNew;
    private int[] blue;
    private int[] red;
    private int[] green;
    private double[] Stab;
    private Image image;
    private Image BGimage;
    private int frameNumber;
    private MemoryImageSource source;
    private Toolkit t;
    private Thread Tarek;
    private PixelGrabber grab;
    private Dimension d2;
    private Image im;
    private Graphics os;
    private int delay;
    private int picxsize;
    private int picysize;
    private int rough;
    private int fero;
    private int dirx;
    private int diry;
    private int scnt;
    private boolean pos;
    private String PICNAME;
    private MediaTracker tracker;
    private Color c;
    String Text;
    String[] text;
    String currText;
    String fontName;
    Color fontColor;
    Color bg;
    int x2;
    int y2;
    int numItems;
    int index2;
    int fontSize;
    int pause;
    int Speed;
    boolean wait;
    boolean Horizontal;
    boolean ShowText;
    
    public void stop() {
        this.Tarek = null;
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
        return true;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
        return true;
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    public void ST(final String Text) {
        final StringTokenizer st = new StringTokenizer(Text, "|");
        this.numItems = st.countTokens();
        this.text = new String[this.numItems];
        this.i = 0;
        while (this.i < this.numItems) {
            this.currText = "text" + String.valueOf(this.i + 1);
            this.text[this.i] = st.nextToken();
            ++this.i;
        }
        this.pause = 1500;
        this.fontName = "Arial";
        this.fontSize = 30;
        this.setFont(new Font(this.fontName, 1, this.fontSize));
        this.fontColor = new Color(8421504);
        this.wait = true;
        this.Horizontal = false;
    }
    
    public TF_Claud() {
        this.index2 = 0;
        this.pause = 0;
        this.Speed = 20;
    }
    
    public void MakeSky() {
        final int imageWidth = this.size().width;
        final int imageHeight = this.size().height;
        final int[] pixels = new int[imageWidth * imageHeight];
        int index = 0;
        for (int y = 0; y < imageHeight; ++y) {
            final int red = y * 255 / (imageHeight - 1);
            final int green = y * 255 / (imageHeight - 1);
            for (int x = 0; x < imageWidth; ++x) {
                final int alpha = y * 255 / (imageHeight - 1);
                final int blue = y * 255 / (imageHeight - 1);
                pixels[index++] = (alpha << 24 | red << 24 | green << 24 | blue);
            }
        }
        this.BGimage = this.createImage(new MemoryImageSource(imageWidth, imageHeight, pixels, 0, imageWidth));
    }
    
    public int Rand1(final int j, final int k) {
        int l = j + (int)(Math.random() * k) - k / 2;
        if (l < 0) {
            l = 0;
        }
        if (l > 255) {
            l = 255;
        }
        return l;
    }
    
    public void update(final Graphics g) {
        final Dimension d = this.size();
        if (this.os == null || d.width != this.d2.width || d.height != this.d2.height) {
            this.d2 = d;
            this.im = this.createImage(d.width, d.height);
            this.os = this.im.getGraphics();
        }
        this.os.setColor(this.c);
        this.os.fillRect(0, 0, d.width, d.height);
        this.os.setColor(this.c);
        this.MakeClaudImage();
        this.image.flush();
        this.image = this.createImage(this.source);
        this.cnt1 += this.diry;
        this.cnt += this.dirx;
        if (this.cnt1 > 256) {
            this.cnt1 -= 256;
        }
        if (this.cnt > 256) {
            this.cnt -= 256;
        }
        if (this.cnt < 0) {
            this.cnt += 256;
        }
        if (this.cnt1 < 0) {
            this.cnt1 += 256;
        }
        this.os.drawImage(this.image, 0, 0, this);
        if (this.ShowText) {
            this.os.setColor(this.fontColor.darker());
            this.os.drawString(this.text[this.index2], this.x2 + 1, this.y2 + 1);
            this.os.setColor(this.fontColor.brighter());
            this.os.drawString(this.text[this.index2], this.x2 - 1, this.y2 - 1);
            this.os.setColor(this.fontColor);
            this.os.drawString(this.text[this.index2], this.x2, this.y2);
        }
        g.drawImage(this.im, 0, 0, this);
    }
    
    public void start() {
        if (this.Tarek == null) {
            (this.Tarek = new Thread(this)).start();
        }
    }
    
    public void MakeClaudImage() {
        for (int j = 0; j < this.picysize; ++j) {
            final int k = j * this.picxsize;
            for (int l = 0; l < this.picxsize; ++l) {
                final int j2 = (this.cloud[l + j * 256] + this.cloud1[(l + this.cnt) % 256 + (j + this.cnt1) % 256 * 256] + this.cloud2[(l + this.cnt1) % 256 + j * 256]) / 3;
                double d = (255 - j2 * 2) / 255.0;
                if (d < 0.0) {
                    d = 0.0;
                }
                int k2 = j2 + (int)(this.red[l + k] * d);
                int l2 = j2 + (int)(this.green[l + k] * d);
                int i2 = j2 + (int)(this.blue[l + k] * d);
                if (k2 > 255) {
                    k2 = 255;
                }
                if (l2 > 255) {
                    l2 = 255;
                }
                if (i2 > 255) {
                    i2 = 255;
                }
                this.PixelIndex[l + k] = -16777216 + (k2 << 16) + (l2 << 8) + i2;
            }
        }
    }
    
    public void run() {
        this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
        final FontMetrics fm = this.getFontMetrics(this.getFont());
        final int ascent = fm.getAscent();
        long l = System.currentTimeMillis();
        while (this.Tarek != null) {
            if (this.ShowText) {
                final int fontLength = fm.stringWidth(this.text[this.index2]);
                if (this.Horizontal) {
                    this.y2 = (this.size().height + ascent) / 2;
                    this.x2 = this.size().width;
                    while (this.x2 > -fontLength) {
                        if (this.wait && this.x2 == (this.size().width - fontLength) / 2) {
                            try {
                                Thread.sleep(this.pause);
                            }
                            catch (InterruptedException ex) {}
                        }
                        try {
                            Thread.sleep(this.Speed);
                        }
                        catch (InterruptedException ex2) {}
                        --this.x2;
                    }
                }
                else {
                    this.x2 = (this.size().width - fm.stringWidth(this.text[this.index2])) / 2;
                    this.y2 = this.size().height + ascent;
                    while (this.y2 > 0) {
                        if (this.wait && this.y2 == (this.size().height + fm.getAscent()) / 2) {
                            try {
                                Thread.sleep(this.pause);
                            }
                            catch (InterruptedException ex3) {}
                        }
                        try {
                            Thread.sleep(this.Speed);
                        }
                        catch (InterruptedException ex4) {}
                        --this.y2;
                    }
                }
                this.index2 = (this.index2 + 1) % this.numItems;
            }
            ++this.frameNumber;
            this.repaint();
            try {
                l += this.delay;
                Thread.sleep(Math.max(0L, l - System.currentTimeMillis()));
            }
            catch (InterruptedException _ex) {}
        }
    }
    
    public void init() {
        this.ShowText = true;
        this.cnt = 255;
        this.cnt1 = 1;
        this.cloud = new int[65536];
        this.cloud1 = new int[65536];
        this.cloud2 = new int[65536];
        this.Stab = new double[361];
        this.frameNumber = -1;
        this.t = this.getToolkit();
        this.pos = false;
        this.tracker = new MediaTracker(this);
        this.setBackground(this.c = new Color(0));
        final byte byte0 = 10;
        this.os = null;
        this.i = 0;
        while (this.i <= 360) {
            this.Stab[this.i] = Math.sin(3.141592653589793 * this.i / 180.0);
            ++this.i;
        }
        this.delay = ((byte0 <= 0) ? 100 : (1000 / byte0));
        this.PICNAME = this.getParameter("image");
        this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
        this.image = this.getImage(this.getCodeBase(), this.PICNAME);
        if (this.image == null) {
            this.rough = 3;
            this.fero = 2;
            this.dirx = 3;
            this.diry = 1;
            this.MakeSky();
            this.image = this.BGimage;
        }
        else {
            this.rough = 4;
            this.fero = 3;
            this.dirx = 3;
            this.diry = 1;
            this.tracker.addImage(this.image, 0);
            try {
                this.tracker.waitForAll();
            }
            catch (InterruptedException ex) {}
        }
        this.cloud = this.CalcMarble(this.fero, this.rough);
        this.cloud1 = this.CalcMarble(this.fero, this.rough + 2);
        this.cloud2 = this.CalcMarble(this.fero + 1, this.rough);
        this.picxsize = this.image.getWidth(null);
        this.picysize = this.image.getHeight(null);
        this.PixelIndex = new int[this.picxsize * this.picysize];
        this.PixelIndexNew = new int[this.picxsize * this.picysize];
        this.red = new int[this.picxsize * this.picysize];
        this.green = new int[this.picxsize * this.picysize];
        this.blue = new int[this.picxsize * this.picysize];
        this.grab = new PixelGrabber(this.image, 0, 0, this.picxsize, this.picysize, this.PixelIndexNew, 0, this.picxsize);
        try {
            this.grab.grabPixels();
        }
        catch (InterruptedException ex2) {}
        for (int j = 0; j < this.picysize; ++j) {
            for (int k = 0; k < this.picxsize; ++k) {
                this.red[k + j * this.picxsize] = (this.PixelIndexNew[k + j * this.picxsize] >> 16 & 0xFF);
                this.green[k + j * this.picxsize] = (this.PixelIndexNew[k + j * this.picxsize] >> 8 & 0xFF);
                this.blue[k + j * this.picxsize] = (this.PixelIndexNew[k + j * this.picxsize] & 0xFF);
            }
        }
        this.source = new MemoryImageSource(this.picxsize, this.picysize, this.PixelIndex, 0, this.picxsize);
        this.image = this.createImage(this.source);
        if (this.ShowText) {
            this.Text = this.getParameter("Text");
            if (this.Text == null) {
                this.ShowText = false;
            }
            else {
                this.ST(this.Text);
            }
        }
    }
    
    public int[] CalcMarble(final int j, final int k) {
        final int[] ai = new int[65536];
        int l2;
        for (int k2 = 256; k2 > 1; k2 = l2) {
            l2 = k2 / 2;
            final int i3 = k2 * j + k;
            for (int m = 0; m < 256; m += k2) {
                final int k3 = m + k2 & 0xFF;
                for (int j2 = 0; j2 < 256; j2 += k2) {
                    final int l3 = j2 + k2 & 0xFF;
                    final int i4 = m + l2;
                    final int j3 = j2 + l2;
                    final int j4 = ai[(m << 8) + j2];
                    final int k4 = ai[(k3 << 8) + j2];
                    final int l4 = ai[(m << 8) + l3];
                    final int i5 = ai[(k3 << 8) + l3];
                    ai[(i4 << 8) + j3] = this.Rand1((j4 + k4 + l4 + i5) / 4, i3);
                    if (m == 0) {
                        ai[(i4 << 8) + j2] = this.Rand1((j4 + k4) / 2, i3);
                    }
                    if (j2 == 0) {
                        ai[(m << 8) + j3] = this.Rand1((j4 + l4) / 2, i3);
                    }
                    ai[(k3 << 8) + j3] = this.Rand1((k4 + i5) / 2, i3);
                    ai[(i4 << 8) + l3] = this.Rand1((l4 + i5) / 2, i3);
                }
            }
        }
        return ai;
    }
}
