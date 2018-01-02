// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class WebDoorFurn extends Webfurn
{
    String[] openingArray;
    int oInd;
    
    public WebDoorFurn(final int n, final int n2, final int n3, final int n4, final char c, final char c2, final String s, final Color color, final String s2, final double n5, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) {
        super(n, n2, n3, n4, c, c2, s, color, s2, n5, s3, s4, s5, s6, s7, s8, s9, s10);
        this.openingArray = new String[] { "NE", "SE", "SW", "NW" };
        this.oInd = 0;
    }
    
    public WebDoorFurn() {
        this.openingArray = new String[] { "NE", "SE", "SW", "NW" };
        this.oInd = 0;
    }
    
    public void shiftOpening(final String s) {
        if (s.equals("clock")) {
            ++this.oInd;
            if (this.oInd == 4) {
                this.oInd = 0;
            }
        }
        else {
            --this.oInd;
            if (this.oInd == -1) {
                this.oInd = 3;
            }
        }
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
        this.drawDoor(graphics);
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
        if (super.grabbed) {
            this.drawTabs(graphics);
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
    
    void drawDoor(final Graphics graphics) {
        final int n = super.h - 1;
        final int n2 = super.w - 1;
        if (this.openingArray[this.oInd].equals("NE")) {
            graphics.drawLine(super.l, super.t, super.l, super.t + n);
            graphics.drawLine(super.l, super.t + n, super.l + n2, super.t + n);
            graphics.setColor(Color.lightGray);
            int n3 = 0;
            int n4 = n;
            for (double n5 = 89.0; n5 > 0; --n5) {
                final double n6 = n5 * 2 * 3.141592653589793 / 360.0;
                final int n7 = (int)(super.actualH * Math.cos(n6));
                final int n8 = (int)(super.actualH * Math.sin(n6));
                if (Math.floor(n5 / 5) == n5 / 5) {
                    graphics.drawLine(super.l + n3, super.t + n - n4, super.l + n7, super.t + n - n8);
                }
                n3 = n7;
                n4 = n8;
            }
        }
        if (this.openingArray[this.oInd].equals("SE")) {
            graphics.drawLine(super.l, super.t, super.l, super.t + n);
            graphics.drawLine(super.l, super.t, super.l + n2, super.t);
            graphics.setColor(Color.lightGray);
            int w = super.w;
            int n9 = 0;
            for (double n10 = 89.0; n10 > 0; --n10) {
                final double n11 = n10 * 2 * 3.141592653589793 / 360.0;
                final int n12 = (int)(super.actualH * Math.cos(n11));
                final int n13 = (int)(super.actualH * Math.sin(n11));
                if (Math.floor(n10 / 5) == n10 / 5) {
                    graphics.drawLine(super.l + w, super.t + n9, super.l + n12, super.t + n13);
                }
                w = n12;
                n9 = n13;
            }
        }
        if (this.openingArray[this.oInd].equals("SW")) {
            graphics.drawLine(super.l + n2, super.t, super.l + n2, super.t + n);
            graphics.drawLine(super.l, super.t, super.l + n2, super.t);
            graphics.setColor(Color.lightGray);
            int n14 = 0;
            int h = super.h;
            for (double n15 = 89.0; n15 > 0; --n15) {
                final double n16 = n15 * 2 * 3.141592653589793 / 360.0;
                final int n17 = (int)(super.actualH * Math.cos(n16));
                final int n18 = (int)(super.actualH * Math.sin(n16));
                if (Math.floor(n15 / 5) == n15 / 5) {
                    graphics.drawLine(super.l + n2 - n14, super.t + h, super.l + n2 - n17, super.t + n18);
                }
                n14 = n17;
                h = n18;
            }
        }
        if (this.openingArray[this.oInd].equals("NW")) {
            graphics.drawLine(super.l + n2, super.t, super.l + n2, super.t + n);
            graphics.drawLine(super.l, super.t + n, super.l + n2, super.t + n);
            graphics.setColor(Color.lightGray);
            int w2 = super.w;
            int n19 = 0;
            for (double n20 = 89.0; n20 > 0; --n20) {
                final double n21 = n20 * 2 * 3.141592653589793 / 360.0;
                final int n22 = (int)(super.actualH * Math.cos(n21));
                final int n23 = (int)(super.actualH * Math.sin(n21));
                if (Math.floor(n20 / 5) == n20 / 5) {
                    graphics.drawLine(super.l + n2 - w2, super.t + n - n19, super.l + n2 - n22, super.t + n - n23);
                }
                w2 = n22;
                n19 = n23;
            }
        }
    }
}
