// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Label;

public class QLabel extends Label implements IQLabel
{
    private String texto;
    private int X;
    private int Y;
    private Image img;
    private boolean temImagem;
    private int pos;
    private boolean sublinhado;
    private boolean comEspaco;
    private Color corSublinhado;
    private int distancia;
    
    public QLabel(final String texto) {
        this.texto = "";
        this.X = 12;
        this.pos = 0;
        this.distancia = -1;
        this.texto = texto;
    }
    
    public QLabel() {
        this("");
    }
    
    public synchronized void limpaTela() {
        final Color foreground = this.getForeground();
        this.setForeground(this.getBackground());
        this.repaint();
        this.setForeground(foreground);
    }
    
    public synchronized void setText(final String texto) {
        this.texto = texto;
        this.limpaTela();
    }
    
    public String getTexto() {
        return this.texto;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        int n = 10;
        int n2 = 10;
        FontMetrics fontMetrics;
        if (this.getFont() != null) {
            fontMetrics = this.getFontMetrics(this.getFont());
        }
        else {
            fontMetrics = this.getFontMetrics(new Font("dialog", 0, 11));
        }
        try {
            n = fontMetrics.stringWidth(this.texto) + 2;
            n2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getHeight();
            if (this.temImagem) {
                n += this.img.getWidth(this);
                n2 = ((this.img.getHeight(this) > n2) ? this.img.getHeight(this) : n2);
            }
        }
        catch (NullPointerException ex) {}
        return new Dimension(n, n2);
    }
    
    public void setImage(final Image img) {
        Util.waitForImage(this, img);
        this.img = img;
        this.temImagem = true;
        if (this.isShowing()) {
            this.repaint();
        }
    }
    
    public void setImagePosition(final int pos) {
        this.pos = pos;
    }
    
    public void setSublinhado(final boolean sublinhado, final boolean comEspaco) {
        this.sublinhado = sublinhado;
        this.comEspaco = comEspaco;
    }
    
    public void setCorSublinhado(final Color corSublinhado) {
        this.corSublinhado = corSublinhado;
    }
    
    public void setDistImgTxt(final int distancia) {
        this.distancia = distancia;
    }
    
    public void paint(final Graphics graphics) {
        try {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            final int n = this.getSize().height / 2 + fontMetrics.getAscent() / 2;
            if (this.temImagem) {
                final int n2 = this.getSize().width - this.img.getWidth(this);
                final int n3 = n - fontMetrics.getAscent();
                switch (this.getAlignment()) {
                    case 0: {
                        graphics.drawImage(this.img, 0, n3, this);
                        graphics.drawString(this.texto, this.img.getWidth(this) + 5, n);
                        break;
                    }
                    default: {
                        graphics.drawString(this.texto, n2 - fontMetrics.stringWidth(this.texto) - ((this.distancia == -1) ? (this.img.getWidth(this) / 2) : this.distancia), n);
                        graphics.drawImage(this.img, n2, n3, this);
                        break;
                    }
                }
            }
            else {
                switch (this.getAlignment()) {
                    case 0: {
                        graphics.drawString(this.texto, 3, n);
                        break;
                    }
                    case 2: {
                        graphics.drawString(this.texto, this.getSize().width - fontMetrics.stringWidth(this.texto), n);
                        break;
                    }
                    case 1: {
                        graphics.drawString(this.texto, this.getSize().width / 2 - fontMetrics.stringWidth(this.texto) / 2, n);
                        break;
                    }
                }
            }
            if (this.sublinhado) {
                final int n4 = this.getSize().width - (this.comEspaco ? fontMetrics.stringWidth("    ") : 0);
                if (this.corSublinhado != null) {
                    graphics.setColor(this.corSublinhado);
                }
                graphics.drawLine(0, this.getSize().height - 1, n4, this.getSize().height - 1);
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
