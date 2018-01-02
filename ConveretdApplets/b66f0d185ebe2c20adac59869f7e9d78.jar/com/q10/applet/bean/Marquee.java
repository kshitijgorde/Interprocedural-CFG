// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.util.Calendar;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.util.GregorianCalendar;
import java.awt.Canvas;

public class Marquee extends Canvas implements Runnable
{
    private String texto;
    private int x;
    private int y;
    private int espera;
    private static final GregorianCalendar gc;
    private static String copyright;
    private boolean segurando;
    private Thread marquee;
    private ICamposTicker[] campos;
    private boolean camposAtualizados;
    private Image vazia;
    private boolean primeiraVez;
    private Image imgTexto;
    private Graphics marqueeGraf;
    private Image offscreenImage;
    private boolean arrastando;
    
    public Marquee() {
        this.espera = 10;
        this.segurando = false;
        this.primeiraVez = true;
        this.arrastando = false;
    }
    
    public synchronized void setTexto(final String texto) {
        this.texto = texto;
        this.primeiraVez = true;
    }
    
    public void setEspera(final int espera) {
        this.espera = espera;
    }
    
    public void init(final int x, final int y, final String texto) {
        this.setX(x);
        this.y = y;
        this.texto = texto;
    }
    
    public void setCopyright(final String s) {
        Marquee.copyright = ((s != null) ? s : "");
    }
    
    public void init() {
        this.init(this.getSize().width, this.getSize().height - this.getFontMetrics(this.getFont()).getMaxDescent() - 2, Marquee.copyright);
    }
    
    public void start() {
        this.setSegurando(false);
        (this.marquee = new Thread(this)).start();
    }
    
    public void destroy() {
        this.setImgTexto(null);
        this.setMarqueeGraf(null);
        this.marquee = null;
    }
    
    private synchronized boolean isCamposAtualizados() {
        return this.camposAtualizados;
    }
    
    public synchronized void setCamposAtivo(final ICamposTicker[] array) {
        System.arraycopy(array, 0, this.campos = new ICamposTicker[array.length], 0, this.campos.length);
        this.camposAtualizados = true;
    }
    
    private synchronized void atualizaCamposAtivo() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final Image[] array = new Image[this.campos.length];
        int n = 0;
        final Color background = this.getBackground();
        final Color foreground = this.getForeground();
        final int height = this.getSize().height;
        final Font font = this.getFont();
        for (int i = 0; i < this.campos.length; ++i) {
            try {
                array[i] = this.campos[i].getImagemCotacao(font, height, background, foreground);
                n += array[i].getWidth(this);
            }
            catch (NullPointerException ex) {}
        }
        int stringWidth = 0;
        if (Marquee.copyright != null) {
            stringWidth = fontMetrics.stringWidth(Marquee.copyright);
            this.setImgTexto(this.createImage(n + stringWidth, this.getSize().height));
            this.setMarqueeGraf(this.getImgTexto().getGraphics());
            this.getMarqueeGraf().drawString(Marquee.copyright, 0, this.getSize().height - 2);
        }
        int n2 = stringWidth;
        for (int j = 0; j < this.campos.length; ++j) {
            try {
                this.getMarqueeGraf().drawImage(array[j], n2, -2, this);
                n2 += array[j].getWidth(this);
            }
            catch (NullPointerException ex2) {}
        }
        this.vazia = this.createImage(this.getSize().width, this.getSize().height);
        this.primeiraVez = false;
        this.camposAtualizados = false;
    }
    
    public void run() {
        this.getFontMetrics(this.getFont());
        try {
            while (true) {
                if (this.isCamposAtualizados()) {
                    this.atualizaCamposAtivo();
                }
                this.repaint();
                try {
                    Thread.sleep(this.espera);
                }
                catch (InterruptedException ex) {
                    throw ex;
                }
                this.decX(1);
                if (this.getImgTexto() == null) {
                    this.setX(this.getSize().width);
                }
                else {
                    if (this.getX() >= -this.getImgTexto().getWidth(this)) {
                        continue;
                    }
                    this.setX(this.getSize().width);
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.getImgTexto() == null) {
            return;
        }
        try {
            if (this.offscreenImage != null) {
                this.offscreenImage.flush();
            }
            this.offscreenImage = this.createImage(this.getBounds().width, this.getBounds().height);
            this.offscreenImage.getGraphics().drawImage(this.getImgTexto(), this.getX(), -2, this);
            graphics.drawImage(this.offscreenImage, this.getBounds().x, this.getBounds().y, this);
        }
        catch (NullPointerException ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized boolean isSegurando() {
        return this.segurando;
    }
    
    public synchronized void setSegurando(final boolean segurando) {
        this.segurando = segurando;
    }
    
    public synchronized int getX() {
        return this.x;
    }
    
    public synchronized void setX(final int x) {
        this.x = x;
    }
    
    public synchronized void incX(final int n) {
        this.x += n;
    }
    
    public synchronized void decX(final int n) {
        this.x -= n;
    }
    
    public synchronized boolean isArrastando() {
        return this.arrastando;
    }
    
    public synchronized void setArrastando(final boolean arrastando) {
        this.arrastando = arrastando;
    }
    
    public synchronized Image getImgTexto() {
        return this.imgTexto;
    }
    
    public synchronized void setImgTexto(final Image imgTexto) {
        this.imgTexto = imgTexto;
    }
    
    public synchronized Graphics getMarqueeGraf() {
        return this.marqueeGraf;
    }
    
    public synchronized void setMarqueeGraf(final Graphics marqueeGraf) {
        this.marqueeGraf = marqueeGraf;
    }
    
    static {
        gc = (GregorianCalendar)Calendar.getInstance();
        Marquee.copyright = ":: © " + Marquee.gc.get(1) + "Q10 Inform\u00e1tica :: ";
    }
}
