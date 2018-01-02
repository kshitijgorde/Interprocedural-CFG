// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class WebWindowFurn extends Webfurn
{
    public WebWindowFurn(final int n, final int n2, final int n3, final int n4, final char c, final char c2, final String s, final Color color, final String s2, final double n5, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) {
        super(n, n2, n3, n4, c, c2, s, color, s2, n5, s3, s4, s5, s6, s7, s8, s9, s10);
    }
    
    public WebWindowFurn() {
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("TimesRoman", 0, 12));
        if (super.grabbed) {
            if (!super.c.equals(Color.black)) {
                graphics.setColor(super.c.darker());
            }
            else {
                graphics.setColor(Color.darkGray.darker());
            }
        }
        else {
            graphics.setColor(super.c);
        }
        this.drawWindow(graphics);
        if (super.n.length() > 0) {
            if (super.horizFont.getSize() >= super.vertFont.getSize()) {
                graphics.setFont(super.horizFont);
                this.drawNameHoriz(graphics);
            }
            else {
                graphics.setFont(super.vertFont);
                this.drawNameVert(graphics);
            }
        }
    }
    
    public void unPaint(final Graphics graphics) {
        if (super.t < Webfurn.apRmHeight) {
            graphics.setColor(Color.white);
        }
        else {
            graphics.setColor(Color.darkGray);
        }
        graphics.fillRect(super.l, super.t, super.w, super.h);
    }
    
    public boolean boxFits(final int n, final int n2) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = super.l + super.w / 2 - n / 2;
        array2[0] = super.t + super.h / 2 - n2 / 2;
        array[1] = array[0] + n;
        array2[1] = array2[0];
        array[2] = array[1];
        array2[2] = array2[1] + n2;
        array[3] = array[0];
        array2[3] = array2[2];
        for (int i = 0; i < array.length; ++i) {
            if (!super.actualFurn.inside(array[i], array2[i])) {
                return false;
            }
        }
        return true;
    }
    
    void drawWindow(final Graphics graphics) {
        final int n = super.h - 1;
        final int n2 = super.w - 1;
        graphics.drawRect(super.l, super.t, n2, n);
        graphics.drawLine(super.l, super.t, super.l + n2, super.t + n);
        graphics.drawLine(super.l, super.t + n, super.l + n2, super.t);
    }
}
