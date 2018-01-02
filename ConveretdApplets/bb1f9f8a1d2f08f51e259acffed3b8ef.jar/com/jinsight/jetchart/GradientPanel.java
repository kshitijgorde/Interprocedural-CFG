// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

public class GradientPanel extends Panel
{
    public static final int TOP_TO_BOTTOM = 0;
    public static final int BOTTOM_TO_TOP = 1;
    public static final int LEFT_TO_RIGHT = 2;
    public static final int RIGHT_TO_LEFT = 3;
    Color a;
    Color b;
    int c;
    
    public void setGradientOrientation(final int c) {
        this.c = c;
    }
    
    public void setGradientColors(final Color color, final Color b) {
        this.a = color;
        this.b = b;
        if (color.equals(b)) {
            this.setBackground(color);
        }
    }
    
    public void setBackground(final Color b) {
        super.setBackground(b);
        this.a = b;
        this.b = b;
    }
    
    public void paint(final Graphics graphics) {
        final boolean g = GraphSerie.G;
        final int red = this.a.getRed();
        final int green = this.a.getGreen();
        final int blue = this.a.getBlue();
        final int red2 = this.b.getRed();
        final int green2 = this.b.getGreen();
        final int blue2 = this.b.getBlue();
        final int n = red2 - red;
        final int n2 = green2 - green;
        final int n3 = blue2 - blue;
        final Dimension size = this.getSize();
        int n4 = 0;
        Label_0135: {
            if (this.c == 0 || this.c == 1) {
                n4 = size.height;
                if (!g) {
                    break Label_0135;
                }
            }
            if (this.c == 2 || this.c == 3) {
                n4 = size.width;
            }
        }
        final float n5 = n / n4;
        final float n6 = n2 / n4;
        final float n7 = n3 / n4;
        int n8 = 0;
        int n9 = 0;
        Label_0203: {
            if (this.c == 1) {
                n9 = size.height;
                if (!g) {
                    break Label_0203;
                }
            }
            if (this.c == 3) {
                n9 = size.width;
            }
        }
        int n10 = 0;
        while (true) {
            Label_0402: {
                if (!g) {
                    break Label_0402;
                }
                graphics.setColor(new Color(red + Math.round(n5 * n8), green + Math.round(n6 * n8), blue + Math.round(n7 * n8)));
                Label_0374: {
                    switch (this.c) {
                        case 0: {
                            graphics.drawLine(0, n9, size.width, n9);
                            ++n9;
                            if (g) {
                                break Label_0374;
                            }
                            break;
                        }
                        case 1: {
                            graphics.drawLine(0, n9, size.width, n9);
                            --n9;
                            if (g) {
                                break Label_0374;
                            }
                            break;
                        }
                        case 2: {
                            graphics.drawLine(n9, 0, n9, size.height);
                            ++n9;
                            if (g) {
                                break Label_0374;
                            }
                            break;
                        }
                        case 3: {
                            graphics.drawLine(n9, 0, n9, size.height);
                            --n9;
                            if (g) {}
                            break;
                        }
                    }
                }
                ++n8;
                ++n10;
            }
            if (n10 > n4) {
                return;
            }
            continue;
        }
    }
    
    public GradientPanel() {
        this(Color.white, Color.white);
    }
    
    public GradientPanel(final Color color, final Color b) {
        this.a = color;
        this.b = b;
        if (color.equals(b)) {
            this.setBackground(color);
        }
    }
    
    public GradientPanel(final Color color, final Color color2, final int c) {
        this(color, color2);
        this.c = c;
    }
}
