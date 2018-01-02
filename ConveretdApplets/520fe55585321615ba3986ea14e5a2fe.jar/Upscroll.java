import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Upscroll extends Applet implements Runnable
{
    Thread upscroll;
    int linjer;
    float[] HSB;
    float B;
    float spring;
    boolean farveSkift;
    String[] text;
    int[] textX;
    long nextTime;
    Image bagved;
    Graphics offscreen;
    Color baggrund;
    Color darkBlue;
    Color original;
    float[] hsbvals;
    Font current;
    int t2;
    int textY;
    int midten;
    int farve;
    boolean low;
    int taller;
    
    public Upscroll() {
        this.farveSkift = true;
        this.baggrund = new Color(0, 0, 0);
        this.darkBlue = new Color(20, 25, 165);
        this.hsbvals = new float[3];
        this.current = new Font("Helvetica", 1, 12);
        this.t2 = 14;
        this.textY = 220;
        this.farve = 15;
        this.low = true;
        this.taller = 0;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                this.musInd();
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("fontStorrelse");
        if (parameter != null) {
            this.t2 = Integer.parseInt(parameter);
            this.current = new Font("Helvetica", 1, this.t2);
        }
        final String parameter2 = this.getParameter("antalLinjer");
        if (parameter2 != null) {
            this.linjer = Integer.parseInt(parameter2);
        }
        this.text = new String[this.linjer];
        this.textX = new int[this.linjer];
        int n = 0;
        for (int i = 0; i < this.linjer; ++i) {
            ++n;
            final String parameter3 = this.getParameter("Text" + n);
            if (parameter3 != null) {
                this.text[n - 1] = parameter3;
            }
            this.textX[n - 1] = this.size().width / 2 - this.getFontMetrics(this.current).stringWidth(this.text[n - 1]) / 2;
        }
        this.midten = this.size().height / 2;
        final String parameter4 = this.getParameter("farveSkift");
        if (parameter4 != null && Integer.parseInt(parameter4) == 0) {
            this.farveSkift = false;
        }
        final String parameter5 = this.getParameter("ScrollFarveRGB");
        if (parameter5 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter5, ",");
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            this.darkBlue = new Color(int1, int2, int3);
            this.HSB = Color.RGBtoHSB(int1, int2, int3, new float[3]);
            this.original = Color.getHSBColor(this.HSB[0], this.HSB[1], this.HSB[2]);
            this.B = this.HSB[2];
        }
        final String parameter6 = this.getParameter("BaggrundsFarveRGB");
        if (parameter6 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter6, ",");
            this.baggrund = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
        }
        this.setBackground(this.baggrund);
        this.bagved = this.createImage(400, 200);
        this.offscreen = this.bagved.getGraphics();
    }
    
    final void musInd() {
        this.showStatus("Upscroll Applet - Wizax.cd@get2net.dk");
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setFont(this.current);
        this.offscreen.setColor(this.baggrund);
        this.offscreen.fillRect(0, 0, 400, 200);
        this.offscreen.setColor(this.original);
        this.offscreen.drawString(this.text[this.taller], this.textX[this.taller], this.textY);
        graphics.drawImage(this.bagved, 0, 0, this);
    }
    
    public void run() {
        if (this.farveSkift) {
            this.spring = this.B / 120.0f;
            this.HSB[2] = this.B - 120.0f * this.spring;
        }
        while (true) {
            if (this.textY < -10) {
                this.textY = 220;
                ++this.taller;
                if (this.farveSkift) {
                    this.HSB[2] = this.B - 120.0f * this.spring;
                }
            }
            if (this.taller == this.linjer) {
                this.taller = 0;
            }
            if (this.farveSkift) {
                if (this.textY < 100) {
                    final float[] hsb = this.HSB;
                    final int n = 2;
                    hsb[n] -= this.spring;
                }
                if (this.textY > 100) {
                    final float[] hsb2 = this.HSB;
                    final int n2 = 2;
                    hsb2[n2] += this.spring;
                }
                this.original = Color.getHSBColor(this.HSB[0], this.HSB[1], this.HSB[2]);
            }
            --this.textY;
            this.repaint();
            if (this.textY == this.midten) {
                this.vent(2500);
            }
            this.vent(9);
        }
    }
    
    public void start() {
        if (this.upscroll == null) {
            (this.upscroll = new Thread(this)).start();
        }
    }
    
    public final void stop() {
        if (this.upscroll != null) {
            this.upscroll.stop();
            this.upscroll = null;
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    final void vent(final int n) {
        try {
            Thread.sleep(Math.max(0L, this.nextTime - System.currentTimeMillis()));
        }
        catch (InterruptedException ex) {}
        this.nextTime = System.currentTimeMillis() + n;
    }
}
