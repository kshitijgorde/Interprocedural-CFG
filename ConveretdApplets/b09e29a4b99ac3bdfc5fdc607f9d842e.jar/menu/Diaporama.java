// 
// Decompiled by Procyon v0.5.30
// 

package menu;

import java.awt.MediaTracker;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.applet.Applet;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.awt.Component;

class Diaporama extends Component implements Runnable
{
    Image image;
    Image image1;
    Image image2;
    Image fond;
    int[] buffer;
    int[] buffer1;
    int[] buffer2;
    int[] bufferf;
    PixelGrabber pg;
    boolean anim;
    int ianim;
    int contraste;
    int courante;
    int w;
    int h;
    int w1;
    int h1;
    int w2;
    int h2;
    double o1;
    double o2;
    private Thread _$1007;
    private String[] _$1021;
    private Applet _$840;
    private String[] _$1031;
    private int _$1038;
    private int _$1045;
    private int _$1049;
    
    public Diaporama() {
        this.ianim = 0;
        this.courante = 0;
        try {
            this._$1054();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void _$1054() throws Exception {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                Diaporama.this.this_mouseClicked(e);
            }
            
            public void mouseEntered(final MouseEvent e) {
                Diaporama.this.this_mouseEntered(e);
            }
            
            public void mouseExited(final MouseEvent e) {
                Diaporama.this.this_mouseExited(e);
            }
        });
        this.anim = false;
    }
    
    void demarre() {
        (this._$1007 = new Thread(this)).start();
    }
    
    public void stop() {
        this._$1007.interrupt();
        this._$1007 = null;
    }
    
    public void run() {
        try {
            while (Thread.currentThread() == this._$1007 & !Thread.interrupted()) {
                for (int i = 0; i < this.buffer.length; ++i) {
                    this.buffer[i] = this.majRvbPixel(this.buffer2[i], 100 - this.contraste, this.buffer1[i]);
                }
                this.image = this.createImage(new MemoryImageSource(this.w, this.h, this.buffer, 0, this.w));
                this.repaint();
                Thread.sleep(this._$1045);
                if (this.contraste >= 100) {
                    this.contraste = 0;
                    ++this.courante;
                    final long t1 = System.currentTimeMillis();
                    for (int j = 0; j < this.buffer1.length; ++j) {
                        this.buffer1[j] = this.buffer2[j];
                    }
                    this.image2 = this.GetImageBase(this._$1031[(this.courante + 1) % this._$1038], true);
                    this.w1 = this.image2.getWidth(null);
                    this.h1 = this.image2.getHeight(null);
                    this.o1 = (this.w1 - this.w) / 2;
                    this.o2 = (this.h1 - this.h) / 2;
                    for (int j = 0; j < this.bufferf.length; ++j) {
                        this.buffer2[j] = this.bufferf[j];
                    }
                    this.pg = new PixelGrabber(this.image2, (int)this.o1, (int)this.o2, this.w, this.h, this.buffer2, 0, this.w);
                    try {
                        this.pg.grabPixels();
                    }
                    catch (InterruptedException ex) {}
                    final long t2 = System.currentTimeMillis();
                    final long d = this._$1049 - (t2 - t1);
                    if (d <= 0) {
                        continue;
                    }
                    Thread.sleep(d);
                }
                else {
                    ++this.contraste;
                }
            }
        }
        catch (InterruptedException ex2) {}
    }
    
    public synchronized void paint(final Graphics g) {
        g.drawImage(this.image, 0, 0, null);
    }
    
    public int majRvbPixel(final int pixel, int pourcen, final int pixelf) {
        if (pixel == pixelf) {
            return pixel;
        }
        if (pourcen > 100) {
            pourcen = 100;
        }
        final int alpha = pixel >> 24 & 0xFF;
        int red = pixel >> 16 & 0xFF;
        int green = pixel >> 8 & 0xFF;
        int blue = pixel & 0xFF;
        red += (255 - red) * pourcen / 100;
        green += (255 - green) * pourcen / 100;
        blue += (255 - blue) * pourcen / 100;
        final int pourcen2 = 100 - pourcen;
        final int alpha2 = pixel >> 24 & 0xFF;
        int red2 = pixelf >> 16 & 0xFF;
        int green2 = pixelf >> 8 & 0xFF;
        int blue2 = pixelf & 0xFF;
        red2 += (255 - red2) * pourcen2 / 100;
        green2 += (255 - green2) * pourcen2 / 100;
        blue2 += (255 - blue2) * pourcen2 / 100;
        return (alpha << 24) + (((red < red2) ? red : red2) << 16) + (((green < green2) ? green : green2) << 8) + ((blue < blue2) ? blue : blue2);
    }
    
    void this_mouseClicked(final MouseEvent e) {
        try {
            String s = this._$840.getDocumentBase().toString();
            if (s.indexOf("jar:") >= 0) {
                s = s.substring(4, s.lastIndexOf("/") + 1);
            }
            else {
                s = s.substring(0, s.lastIndexOf("/") + 1);
            }
            String target = "_self";
            String lien = this._$1021[this.courante % this._$1038];
            if (lien.indexOf("&") >= 0) {
                target = lien.substring(lien.lastIndexOf("&") + 1, lien.length());
                lien = lien.substring(0, lien.lastIndexOf("&"));
            }
            if (lien.length() > 1) {
                if (lien.indexOf("http") >= 0 ^ lien.indexOf("mailto") >= 0) {
                    this._$840.getAppletContext().showDocument(new URL(lien), target);
                }
                else {
                    this._$840.getAppletContext().showDocument(new URL(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(lien)))), target);
                }
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    void this_mouseEntered(final MouseEvent e) {
        this.setCursor(new Cursor(12));
        final String s = this._$840.getDocumentBase().toString();
        this._$840.getAppletContext().showStatus(String.valueOf(String.valueOf(s)).concat(" Version 20/03"));
    }
    
    void this_mouseExited(final MouseEvent e) {
        this.setCursor(new Cursor(0));
    }
    
    public Image GetImageBase(final String fichier, final boolean wait) {
        String s = this._$840.getDocumentBase().toString();
        if (s.indexOf("jar:") >= 0) {
            s = s.substring(4, s.lastIndexOf("/") + 1);
        }
        else {
            s = s.substring(0, s.lastIndexOf("/") + 1);
        }
        try {
            this.image1 = this._$840.getImage(new URL(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(fichier)))));
            final MediaTracker t = new MediaTracker(this);
            t.addImage(this.image1, 0);
            if (wait) {
                t.waitForID(0);
            }
        }
        catch (MalformedURLException ex) {}
        catch (InterruptedException ex2) {}
        return this.image1;
    }
    
    public void setApplet(final Applet newApplet) {
        this._$840 = newApplet;
        this.image = this.GetImageBase("fond.jpg", true);
        this.w = this.image.getWidth(null);
        this.h = this.image.getHeight(null);
        this.setBounds(this.getLocation().x, this.getLocation().y, this.w, this.h);
        this.bufferf = new int[this.w * this.h];
        this.buffer = new int[this.w * this.h];
        this.buffer1 = new int[this.w * this.h];
        this.buffer2 = new int[this.w * this.h];
        this.pg = new PixelGrabber(this.image, 0, 0, this.w, this.h, this.bufferf, 0, this.w);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this.bufferf.length; ++i) {
            this.buffer1[i] = this.bufferf[i];
        }
        this.image2 = this.GetImageBase(this._$1031[this.courante % this._$1038], true);
        this.w1 = this.image2.getWidth(null);
        this.h1 = this.image2.getHeight(null);
        this.o1 = (this.w1 - this.w) / 2;
        this.o2 = (this.h1 - this.h) / 2;
        for (int i = 0; i < this.bufferf.length; ++i) {
            this.buffer2[i] = this.bufferf[i];
        }
        this.pg = new PixelGrabber(this.image2, (int)this.o1, (int)this.o2, this.w, this.h, this.buffer2, 0, this.w);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException ex2) {}
    }
    
    public Applet getApplet() {
        return this._$840;
    }
    
    public void setFichier(final String[] newFichier) {
        this._$1031 = newFichier;
    }
    
    public void setSync(final int newSync) {
        this._$1045 = newSync;
    }
    
    public String[] getFichier() {
        return this._$1031;
    }
    
    public void setNbImage(final int newNbImage) {
        this._$1038 = newNbImage;
    }
    
    public void setHttp(final String[] newHttp) {
        this._$1021 = newHttp;
    }
    
    public String[] getHttp() {
        return this._$1021;
    }
    
    public void setDelai(final int newDelai) {
        this._$1049 = newDelai;
    }
}
