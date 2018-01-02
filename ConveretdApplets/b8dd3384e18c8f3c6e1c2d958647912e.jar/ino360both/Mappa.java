// 
// Decompiled by Procyon v0.5.30
// 

package ino360both;

import java.io.InputStream;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

public final class Mappa extends Applet implements Runnable
{
    private Color coloreFreccia;
    private float direzione;
    private Image imgCerchio;
    private Image imgMappa;
    private int offsetAngolo;
    private Thread timerThread;
    private float ultimaDirezione;
    private int xCentro;
    private int yCentro;
    
    private void chiediDirezione() {
        try {
            Applet receiver = null;
            final String receiverName = "Panoramica";
            receiver = this.getAppletContext().getApplet(receiverName);
            if (receiver != null) {
                this.direzione = (float)((ptviewer)receiver).pan();
                this.direzione += 180.0f;
                if (this.direzione > 360.0f) {
                    this.direzione -= 360.0f;
                }
            }
        }
        catch (Exception e) {}
    }
    
    private void disegnaCerchio(final Graphics g) {
        final int x = this.xCentro - 8 - 1;
        final int y = this.yCentro - 8;
        g.drawImage(this.imgCerchio, x, y, this);
    }
    
    private void disegnaContorno(final Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.drawLine(0, 0, 200, 0);
        g.drawLine(200, 0, 200, 300);
        g.drawLine(200, 300, 0, 300);
        g.drawLine(0, 300, 0, 0);
    }
    
    private void disegnaFreccia(final Graphics g) {
        int x1 = this.xCentro;
        int y1 = this.yCentro;
        final double angolo = (this.direzione + this.offsetAngolo) * 3.141592653589793 / 180.0;
        final double seno = Math.sin(angolo);
        final double coseno = Math.cos(angolo);
        int r = 30;
        int x2 = (int)(x1 + r * seno);
        int y2 = (int)(y1 - r * coseno);
        r = 7;
        x1 += (int)(r * seno);
        y1 -= (int)(r * coseno);
        g.setColor(this.coloreFreccia);
        int deltaX1;
        int deltaX2;
        if (coseno > 0.38 || coseno < -0.92) {
            deltaX1 = -1;
            deltaX2 = 1;
        }
        else if (coseno > -0.38) {
            deltaX1 = 0;
            deltaX2 = 0;
        }
        else {
            deltaX1 = 1;
            deltaX2 = -1;
        }
        int deltaY1;
        int deltaY2;
        if (seno > 0.38 || seno < -0.92) {
            deltaY1 = -1;
            deltaY2 = 1;
        }
        else if (seno > -0.38) {
            deltaY1 = 0;
            deltaY2 = 0;
        }
        else {
            deltaY1 = 1;
            deltaY2 = -1;
        }
        r = 30;
        x2 = (int)(this.xCentro + r * seno);
        y2 = (int)(this.yCentro - r * coseno);
        final Polygon poli = new Polygon();
        poli.addPoint(x1 + deltaX1, y1 + deltaY1);
        poli.addPoint(x1 + deltaX2, y1 + deltaY2);
        poli.addPoint(x2 + deltaX2, y2 + deltaY2);
        poli.addPoint(x2 + deltaX1, y2 + deltaY1);
        g.fillPolygon(poli);
    }
    
    public void init() {
        this.imgMappa = this.getImage(this.getDocumentBase(), this.nomeImmagine("MAPPA"));
        try {
            final MediaTracker m = new MediaTracker(this);
            final InputStream is = this.getClass().getResourceAsStream("Cerchio.gif");
            final BufferedInputStream bis = new BufferedInputStream(is);
            final byte[] byBuf = new byte[10000];
            final int byteRead = bis.read(byBuf, 0, 10000);
            m.addImage(this.imgCerchio = Toolkit.getDefaultToolkit().createImage(byBuf), 0);
            m.waitForAll();
        }
        catch (Exception e) {
            this.imgCerchio = null;
        }
        String s = this.getParameter("BASEFRECCIA");
        if (s == null) {
            s = "100,100";
        }
        final int i = s.indexOf(44);
        if (i == -1) {
            this.xCentro = 100;
            this.yCentro = 100;
        }
        else {
            this.xCentro = Integer.parseInt(s.substring(0, i));
            this.yCentro = Integer.parseInt(s.substring(i + 1));
        }
        s = this.getParameter("OFFSET");
        if (s == null) {
            s = "0";
        }
        this.offsetAngolo = Integer.parseInt(s);
        this.coloreFreccia = new Color(255, 64, 64);
        this.ultimaDirezione = -1.0f;
    }
    
    private String nomeImmagine(final String nomeParametro) {
        String s = this.getParameter(nomeParametro);
        if (s == null) {
            s = "";
        }
        return s;
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        final Thread currentThread = Thread.currentThread();
        while (currentThread == this.timerThread) {
            if (this.direzione != this.ultimaDirezione) {
                this.repaint();
                this.ultimaDirezione = this.direzione;
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                break;
            }
            this.chiediDirezione();
        }
    }
    
    public void start() {
        if (this.timerThread == null) {
            this.timerThread = new Thread(this);
        }
        this.timerThread.start();
    }
    
    public void stop() {
        this.timerThread = null;
    }
    
    public void update(final Graphics g) {
        g.drawImage(this.imgMappa, 0, 0, this);
        this.disegnaCerchio(g);
        this.disegnaFreccia(g);
        this.disegnaContorno(g);
    }
}
