// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.icon;

import java.awt.Shape;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Rectangle;
import java.awt.Paint;
import java.awt.Color;

public class GradientIcon extends ResizingIcon
{
    private static final long F = 6997945159902070664L;
    public static final int MODE_VERTICAL = 0;
    public static final int MODE_HORIZONTAL = 1;
    protected int L;
    protected int I;
    protected int H;
    protected Color G;
    protected int E;
    protected Paint K;
    protected Rectangle J;
    
    public GradientIcon() {
        this.L = 0;
        this.I = 11976140;
        this.H = 5989495;
        this.G = new Color(3883342);
        this.E = 1;
        this.A();
    }
    
    public void setHeight(final int height) {
        super.setHeight(height);
        this.A();
    }
    
    public void setWidth(final int width) {
        super.setWidth(width);
        this.A();
    }
    
    public int getBeginColor() {
        return this.I;
    }
    
    public void setBeginColor(final int i) {
        this.I = i;
        this.A();
    }
    
    public int getEndColor() {
        return this.H;
    }
    
    public void setEndColor(final int h) {
        this.H = h;
        this.A();
    }
    
    public int getMode() {
        return this.L;
    }
    
    public void setMode(final int l) {
        this.L = l;
        this.A();
    }
    
    public Rectangle getClipRect() {
        return this.J;
    }
    
    public void setClipRect(final Rectangle j) {
        this.J = j;
    }
    
    public int getBorderColor() {
        return this.G.getRGB();
    }
    
    public void setBorderColor(final int n) {
        this.G = new Color(n);
    }
    
    public int getBorderWidth() {
        return this.E;
    }
    
    public void setBorderWidth(final int e) {
        this.E = e;
    }
    
    protected void A() {
        switch (this.L) {
            case 0: {
                this.K = new GradientPaint(0.0f, 0.0f, new Color(this.I), 0.0f, this.A, new Color(this.H));
                break;
            }
            case 1: {
                this.K = new GradientPaint(0.0f, 0.0f, new Color(this.I), this.B, 0.0f, new Color(this.H));
                break;
            }
        }
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final Shape clip = graphics2D.getClip();
        if (this.J != null) {
            graphics2D.setClip(this.J.x, this.J.y, this.J.width, this.J.height);
        }
        switch (this.L) {
            case 0: {
                this.K = new GradientPaint(0.0f, 0.0f, new Color(this.I), 0.0f, this.A, new Color(this.H));
                break;
            }
            case 1: {
                this.K = new GradientPaint(0.0f, 0.0f, new Color(this.I), this.B, 0.0f, new Color(this.H));
                break;
            }
        }
        graphics2D.setPaint(this.K);
        graphics2D.fillRect(n, n2, this.B, this.A);
        if (this.E > 0) {
            graphics2D.setColor(this.G);
            for (int i = 0; i < this.E; ++i) {
                graphics2D.drawRect(n + i, n2 + i, this.B - 2 * i - 1, this.A - 2 * i - 1);
            }
        }
        graphics2D.setClip(clip);
    }
}
