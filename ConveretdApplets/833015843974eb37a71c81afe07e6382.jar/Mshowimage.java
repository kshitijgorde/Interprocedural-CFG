import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mshowimage extends Applet implements Runnable
{
    Image[] img;
    Thread t;
    int nPriority;
    int x;
    int y;
    int frames;
    int velocity;
    int k;
    String[] sfondoRGB;
    String[] effetto;
    int index;
    int m;
    int pause;
    int m_g;
    int showeffect;
    Image[][] frame;
    Graphics M_G;
    Image imgBuffer;
    boolean ZoomIn;
    boolean Pause;
    boolean ZoomOut;
    boolean isStandalone;
    URL[] L;
    String[] target;
    String autore;
    
    public Mshowimage() {
        this.isStandalone = false;
        this.ZoomIn = false;
        this.Pause = false;
        this.ZoomOut = true;
    }
    
    public int HexToInt(final String s) {
        int i = 0;
        if (s.substring(0, 1).equalsIgnoreCase("a")) {
            i = 160;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("b")) {
            i = 176;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("c")) {
            i = 192;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("d")) {
            i = 208;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("e")) {
            i = 224;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("f")) {
            i = 240;
        }
        else {
            i = Integer.valueOf(s.substring(0, 1)) * 16;
        }
        if (s.substring(1).equalsIgnoreCase("a")) {
            i += 10;
        }
        else if (s.substring(1).equalsIgnoreCase("b")) {
            i += 11;
        }
        else if (s.substring(1).equalsIgnoreCase("c")) {
            i += 12;
        }
        else if (s.substring(1).equalsIgnoreCase("d")) {
            i += 13;
        }
        else if (s.substring(1).equalsIgnoreCase("e")) {
            i += 14;
        }
        else if (s.substring(1).equalsIgnoreCase("f")) {
            i += 15;
        }
        else {
            i += Integer.valueOf(s.substring(1));
        }
        return i;
    }
    
    public String getAppletInfo() {
        String s = "";
        s = String.valueOf(s) + "Titolo: Mshowimage\r\n";
        s = String.valueOf(s) + "Autore: Massimo Giari\r\n";
        s = String.valueOf(s) + "E-mail: motore@iol.it\r\n";
        s = String.valueOf(s) + "Copyright Massimo Giari 21/09/2000";
        return s;
    }
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) == null) ? def : this.getParameter(key));
    }
    
    public void init() {
        try {
            this.autore = this.getParameter("autore", "");
        }
        catch (Exception ex) {}
        if (!this.autore.equalsIgnoreCase("Massimo Giari")) {
            System.exit(0);
        }
        final String at = this.getParameter("priority");
        this.nPriority = ((at == null) ? 1 : Integer.valueOf(at));
        this.x = this.size().width;
        this.y = this.size().height;
        this.imgBuffer = this.createImage(this.x, this.y);
        this.M_G = this.imgBuffer.getGraphics();
        String s1;
        do {
            ++this.index;
            s1 = this.getParameter("image" + this.index);
        } while (s1 != null);
        --this.index;
        final String s2 = this.getParameter("pause");
        if (s2 != null) {
            this.pause = Integer.parseInt(s2);
        }
        final String s3 = this.getParameter("show_effect");
        if (s3 != null) {
            this.showeffect = Integer.parseInt(s3);
        }
        final String s4 = this.getParameter("num_frames");
        if (s4 != null) {
            this.frames = Integer.parseInt(s4);
        }
        final String s5 = this.getParameter("speed");
        if (s5 != null) {
            this.velocity = Integer.parseInt(s5);
        }
        this.frame = new Image[this.index][this.frames];
        this.img = new Image[this.index];
        this.sfondoRGB = new String[this.index];
        this.effetto = new String[this.index];
        this.L = new URL[this.index];
        this.target = new String[this.index];
        final MediaTracker mediatracker = new MediaTracker(this);
        for (int i = 0; i < this.index; ++i) {
            this.sfondoRGB[i] = this.getParameter("background" + (i + 1));
            this.effetto[i] = this.getParameter("effect_type" + String.valueOf(i + 1));
            mediatracker.addImage(this.img[i] = this.getImage(this.getCodeBase(), this.getParameter("image" + (i + 1))), 1);
            try {
                this.L[i] = new URL(this.getParameter("link" + String.valueOf(i + 1)));
            }
            catch (MalformedURLException ex2) {}
            this.target[i] = this.getParameter("target" + String.valueOf(i + 1));
        }
        final int l = Math.round(this.x / 2);
        final int i2 = Math.round(this.y / 2);
        final Image[] mgimage = new Image[this.index];
        this.showStatus("Caricamento immagini in corso...");
        for (int j1 = 0; j1 < this.index; ++j1) {
            int k1 = 0;
            final int l2 = this.HexToInt(this.sfondoRGB[j1].substring(0, 2));
            final int i3 = this.HexToInt(this.sfondoRGB[j1].substring(2, 4));
            final int j2 = this.HexToInt(this.sfondoRGB[j1].substring(4));
            final int[] m_g = new int[this.x * this.y];
            final PixelGrabber pixelgrabber = new PixelGrabber(this.img[j1], 0, 0, this.x, this.y, m_g, 0, this.x);
            try {
                pixelgrabber.grabPixels();
            }
            catch (InterruptedException ex3) {}
            for (int k2 = 0; k2 < this.frames; ++k2) {
                final int[] mg1 = new int[this.x * this.y];
                mgimage[j1] = this.createImage(this.x, this.y);
                final Graphics g = mgimage[j1].getGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, this.x, this.y);
                g.setColor(Color.black);
                if (this.effetto[j1].equalsIgnoreCase("Mshow1")) {
                    g.fillRect(l - k1, i2 - k1, 2 * k1, 2 * k1);
                }
                else if (this.effetto[j1].equalsIgnoreCase("Mshow2")) {
                    g.fillOval(l - k1, i2 - k1, 2 * k1, 2 * k1);
                }
                else if (this.effetto[j1].equalsIgnoreCase("Mshow3")) {
                    final int[] mg2 = new int[11];
                    final int[] mg3 = new int[11];
                    final int j3 = 2 * k1;
                    final int k3 = 2 * k1;
                    mg2[0] = j3 / 2;
                    mg3[0] = 0;
                    mg2[1] = 2 * j3 / 3;
                    mg3[1] = 2 * k3 / 5;
                    mg2[2] = j3;
                    mg3[2] = 2 * k3 / 5;
                    mg2[3] = 2 * j3 / 3;
                    mg3[3] = 3 * k3 / 5;
                    mg2[4] = 5 * j3 / 6;
                    mg3[4] = k3;
                    mg2[5] = j3 / 2;
                    mg3[5] = 4 * k3 / 5;
                    mg2[6] = j3 / 6;
                    mg3[6] = k3;
                    mg2[7] = j3 / 3;
                    mg3[7] = 3 * k3 / 5;
                    mg2[8] = 0;
                    mg3[8] = 2 * k3 / 5;
                    mg2[9] = j3 / 3;
                    mg3[9] = 2 * k3 / 5;
                    mg2[10] = j3 / 2;
                    mg3[10] = 0;
                    for (int l3 = 0; l3 < 11; ++l3) {
                        mg2[l3] = mg2[l3] + l - k1;
                        mg3[l3] = mg3[l3] + i2 - k1;
                    }
                    g.fillPolygon(mg2, mg3, 11);
                }
                else if (this.effetto[j1].equalsIgnoreCase("Mshow4")) {
                    final int[] mg4 = new int[4];
                    final int[] mg5 = new int[4];
                    final int k4 = 2 * k1;
                    final int l4 = 2 * k1;
                    mg4[0] = l - k1 + k4 / 2;
                    mg5[0] = i2 - k1 + l4;
                    mg4[1] = l - k1;
                    mg5[1] = i2 - k1 + l4 / 2;
                    mg4[2] = l - k1 + k4 / 2;
                    mg5[2] = i2 - k1;
                    mg4[3] = l - k1 + k4;
                    mg5[3] = i2 - k1 + l4 / 2;
                    g.fillPolygon(mg4, mg5, 4);
                    g.fillOval((int)(l - k1 - 0.2071 * k1), (int)(i2 - k1 - 0.2071 * k1), (int)(1.4142 * k1), (int)(1.4142 * k1));
                    g.fillOval((int)(l - k1 + 0.7929 * k1) + 1, (int)(i2 - k1 - 0.2071 * k1), (int)(1.4142 * k1), (int)(1.4142 * k1));
                }
                else if (this.effetto[j1].equalsIgnoreCase("Mshow5")) {
                    final int[] mg6 = new int[4];
                    final int[] mg7 = new int[4];
                    final int l5 = 2 * k1;
                    final int i4 = 2 * k1;
                    mg6[0] = l - k1 + l5 / 2;
                    mg7[0] = i2 - k1 + i4;
                    mg6[1] = l - k1;
                    mg7[1] = i2 - k1 + i4 / 2;
                    mg6[2] = l - k1 + l5 / 2;
                    mg7[2] = i2 - k1;
                    mg6[3] = l - k1 + l5;
                    mg7[3] = i2 - k1 + i4 / 2;
                    g.fillPolygon(mg6, mg7, 4);
                }
                else if (this.effetto[j1].equalsIgnoreCase("Mshow6")) {
                    final int[] mg6 = new int[4];
                    final int[] mg7 = new int[4];
                    final int l5 = 2 * k1;
                    final int i4 = 2 * k1;
                    mg6[0] = l - k1 + l5 / 2;
                    mg7[0] = i2 - k1 + i4;
                    mg6[1] = l - k1;
                    mg7[1] = i2 - k1 + i4;
                    mg6[2] = l - k1 + l5 / 2;
                    mg7[2] = i2 - k1;
                    mg6[3] = l - k1 + l5;
                    mg7[3] = i2 - k1 + i4;
                    g.fillPolygon(mg6, mg7, 4);
                }
                else if (this.effetto[j1].equalsIgnoreCase("Mshow7")) {
                    final int[] mg8 = new int[6];
                    final int[] mg9 = new int[6];
                    final int i5 = 2 * k1;
                    final int j4 = (int)(1.732 * k1);
                    mg8[0] = l - k1 + i5 / 4;
                    mg9[0] = i2 - k1;
                    mg8[1] = l - k1 + 3 * i5 / 4;
                    mg9[1] = i2 - k1;
                    mg8[2] = l - k1 + i5;
                    mg9[2] = i2 - k1 + j4 / 2;
                    mg8[3] = l - k1 + 3 * i5 / 4;
                    mg9[3] = i2 - k1 + j4;
                    mg8[4] = l - k1 + i5 / 4;
                    mg9[4] = i2 - k1 + j4;
                    mg8[5] = l - k1;
                    mg9[5] = i2 - k1 + j4 / 2;
                    g.fillPolygon(mg8, mg9, 6);
                }
                else {
                    final int l6 = 2 * k1;
                    final int i6 = 2 * k1;
                    g.fillRect(l - k1, i2 - k1, l6, i6);
                }
                final PixelGrabber pixelgrabber2 = new PixelGrabber(mgimage[j1], 0, 0, this.x, this.y, mg1, 0, this.x);
                try {
                    pixelgrabber2.grabPixels();
                }
                catch (InterruptedException ex4) {}
                final int[] m_g2 = new int[this.x * this.y];
                for (int j5 = 0; j5 < this.x * this.y; ++j5) {
                    final int k5 = mg1[j5];
                    final int i7 = (k5 & 0xFF0000) >> 16;
                    final int j6 = (k5 & 0xFF00) >> 8;
                    final int k6 = k5 & 0xFF;
                    if (i7 == 0 && j6 == 0 && k6 == 0) {
                        m_g2[j5] = m_g[j5];
                    }
                    else {
                        m_g2[j5] = -16777216 + (l2 << 16) + (i3 << 8) + j2;
                    }
                }
                this.frame[j1][k2] = this.createImage(new MemoryImageSource(this.x, this.y, m_g2, 0, this.x));
                k1 += this.showeffect;
                mediatracker.addImage(this.frame[j1][k2], 1);
            }
        }
        try {
            mediatracker.waitForAll();
        }
        catch (InterruptedException ex5) {}
        if (mediatracker.isErrorAny()) {
            this.showStatus("Errore");
            return;
        }
        if (mediatracker.checkAll()) {
            this.showStatus("Immagini caricate con successo");
        }
    }
    
    public boolean mouseDown(final Event event, final int i, final int l) {
        if (this.t == null) {
            this.start();
        }
        else {
            this.stop();
        }
        if (this.target[this.m] != null) {
            super.getAppletContext().showDocument(this.L[this.m], this.target[this.m]);
        }
        else {
            super.getAppletContext().showDocument(this.L[this.m]);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event evt, final int i, final int l) {
        this.setCursor(new Cursor(12));
        this.showStatus("Applet Mshowimage by ( Massimo Giari )");
        return true;
    }
    
    public boolean mouseExit(final Event evt, final int i, final int l) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics g) {
        if (this.ZoomOut) {
            this.M_G.drawImage(this.frame[this.m][this.k], 0, 0, this);
            g.drawImage(this.imgBuffer, 0, 0, this);
            ++this.k;
            if (this.k == this.frames) {
                this.k = this.frames - 1;
                this.ZoomIn = false;
                this.ZoomOut = false;
                this.Pause = true;
            }
        }
        else if (this.Pause) {
            this.M_G.drawImage(this.img[this.m], 0, 0, this);
            g.drawImage(this.imgBuffer, 0, 0, this);
            ++this.m_g;
            if (this.m_g == this.pause) {
                this.m_g = 0;
                this.Pause = false;
                this.ZoomIn = true;
                this.ZoomOut = false;
            }
        }
        else if (this.ZoomIn) {
            this.M_G.drawImage(this.frame[this.m][this.k], 0, 0, this);
            g.drawImage(this.imgBuffer, 0, 0, this);
            --this.k;
            if (this.k == -1) {
                this.k = 0;
                this.ZoomIn = false;
                this.Pause = false;
                this.ZoomOut = true;
                ++this.m;
                if (this.m == this.index) {
                    this.m = 0;
                }
            }
        }
    }
    
    public void run() {
        while (this.t != null) {
            try {
                Thread.sleep(this.velocity);
                Thread.currentThread().setPriority(this.nPriority);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null) {
            this.t.stop();
            this.t = null;
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
