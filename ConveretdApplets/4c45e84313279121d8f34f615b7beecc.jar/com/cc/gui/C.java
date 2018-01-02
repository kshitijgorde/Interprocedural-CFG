// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import com.cc.B.F;
import java.util.Observable;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import com.cc.B.A;
import java.util.Observer;
import java.awt.event.MouseListener;

public class C implements MouseListener, Observer
{
    private static final int B = 2;
    private A D;
    private D A;
    private Component C;
    
    public C(final A d, final Component c, final D a) {
        this.D = d;
        this.C = c;
        this.A = a;
        if (d.h) {
            d.addObserver(this);
        }
    }
    
    public void A(final Graphics graphics) {
        graphics.setFont(this.C.getFont());
        final com.cc.B.C c = new com.cc.B.C(this.D);
        int n = 0;
        for (int i = 0; i < this.D.Y.length(); ++i) {
            final char char1 = this.D.Y.charAt(i);
            graphics.setColor(c.A(char1) ? Color.gray : this.C.getForeground());
            graphics.drawString(String.valueOf(char1), n, graphics.getFontMetrics().getAscent());
            n += graphics.getFontMetrics().charWidth(char1) + 2;
        }
    }
    
    public Dimension A() {
        final FontMetrics fontMetrics = this.C.getFontMetrics(this.C.getFont());
        return new Dimension(fontMetrics.stringWidth(this.D.Y) + (this.D.Y.length() - 1) * 2, fontMetrics.getHeight());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final FontMetrics fontMetrics = this.C.getFontMetrics(this.C.getFont());
        final int x = mouseEvent.getX();
        char c = this.D.Y.charAt(0);
        int charWidth = fontMetrics.charWidth(c);
        for (int i = 1; i < this.D.Y.length(); ++i) {
            if (x < charWidth) {
                this.A.keyTyped(new KeyEvent(this.C, 1, System.currentTimeMillis(), 0, c, c));
                return;
            }
            c = this.D.Y.charAt(i);
            charWidth += fontMetrics.charWidth(c) + 2;
        }
        this.A.keyTyped(new KeyEvent(this.C, 1, System.currentTimeMillis(), 0, c, c));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void update(final Observable observable, final Object o) {
        if (((F)o).E == 0) {
            this.C.repaint();
        }
    }
}
