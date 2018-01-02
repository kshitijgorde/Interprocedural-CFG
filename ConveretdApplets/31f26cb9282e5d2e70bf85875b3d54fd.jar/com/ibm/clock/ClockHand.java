// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

import java.awt.Graphics;
import java.awt.Color;

class ClockHand
{
    public static final int DEFAULT_LENGTH = 30;
    public static final int MINIMUM_WIDTH = 1;
    private boolean visible;
    private int length;
    private Color color;
    private int width;
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) throws IllegalArgumentException {
        if (!this.isProperWidth(width)) {
            throw new IllegalArgumentException(" width =" + width);
        }
        this.width = width;
    }
    
    protected boolean isProperWidth(final int n) {
        return n >= 1;
    }
    
    public Color getColor() {
        if (this.color == null) {
            this.color = Color.black;
        }
        return this.color;
    }
    
    public void setColor(final Color color) {
        if (color == null) {
            throw new NullPointerException();
        }
        this.color = color;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(final int length) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException(" bad length = " + length);
        }
        this.length = length;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    protected boolean isProperAngle(final double n) {
        return n >= 0.0 && n <= 6.283185307179586;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2, final double n3) {
        if (!this.isVisible()) {
            return;
        }
        final Color color = graphics.getColor();
        graphics.setColor(this.getColor());
        graphics.translate(n, n2);
        this.drawLine(graphics, n3, this.getWidth(), this.getLength());
        this.drawTop(graphics, n3, this.getWidth(), this.getLength(), this.getWidth() / 2);
        this.drawCentre(graphics, this.getWidth());
        graphics.translate(-n, -n2);
        graphics.setColor(color);
    }
    
    private void drawLine(final Graphics graphics, final double n, final int n2, final int n3) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        final int n4 = (int)Math.round(n3 * cos);
        final int n5 = (int)Math.round(n3 * sin);
        if (n2 != 1) {
            final int n6 = (int)Math.round(n2 * sin / 2.0);
            final int n7 = (int)Math.round(n2 * cos / 2.0);
            array[0] = n6;
            array2[0] = -n7;
            array[1] = -n6;
            array2[1] = n7;
            array[2] = n4 - n6;
            array2[2] = n5 + n7;
            array[3] = n4 + n6;
            array2[3] = n5 - n7;
            graphics.fillPolygon(array, array2, 4);
            return;
        }
        graphics.drawLine(0, 0, n4, n5);
    }
    
    private void drawTop(final Graphics graphics, final double n, final int n2, final int n3, final int n4) {
        if (n2 <= 2) {
            return;
        }
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        final int n5 = (int)Math.round(n3 * cos);
        final int n6 = (int)Math.round(n3 * sin);
        final int n7 = (int)Math.round(n2 * sin / 2.0);
        final int n8 = (int)Math.round(n2 * cos / 2.0);
        array[0] = n5 + n7;
        array2[0] = n6 - n8;
        array[1] = n5 - n7;
        array2[1] = n6 + n8;
        array[2] = (int)Math.round((n3 + n4) * cos);
        array2[2] = (int)Math.round((n3 + n4) * sin);
        graphics.fillPolygon(array, array2, 3);
    }
    
    private void drawCentre(final Graphics graphics, final int n) {
        graphics.fillOval(-n, -n, n * 2, n * 2);
    }
    
    ClockHand() {
        this.visible = true;
        this.length = 30;
        this.width = 1;
    }
}
