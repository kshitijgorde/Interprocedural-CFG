// 
// Decompiled by Procyon v0.5.30
// 

package com.roguewave.blend.border.v2_0;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Color;

public class ThreeDBorder extends RoundedBorder
{
    private Color m_clrShadow;
    private Color m_clrReflect;
    private boolean m_bRaised;
    
    public ThreeDBorder() {
        this.m_clrShadow = SystemColor.controlShadow;
        this.m_clrReflect = SystemColor.controlLtHighlight;
        this.m_bRaised = true;
    }
    
    public ThreeDBorder(final boolean raised) {
        this.m_clrShadow = SystemColor.controlShadow;
        this.m_clrReflect = SystemColor.controlLtHighlight;
        this.m_bRaised = true;
        this.setRaised(raised);
    }
    
    public void setShadowColor(final Color clrShadow) {
        this.m_clrShadow = clrShadow;
    }
    
    public void setReflectionColor(final Color clrReflect) {
        this.m_clrReflect = clrReflect;
    }
    
    public Color getShadowColor() {
        return this.m_clrShadow;
    }
    
    public Color getReflectionColor() {
        return this.m_clrReflect;
    }
    
    public void setRaised(final boolean bRaised) {
        this.m_bRaised = bRaised;
    }
    
    public boolean isRaised() {
        return this.m_bRaised;
    }
    
    public Insets getInsets(final Rectangle rectangle) {
        final int n = this.getArcLength(rectangle.width - this.getThickness() - rectangle.x) / 2;
        final int n2 = this.getArcLength(rectangle.height - this.getThickness() - rectangle.y) / 2;
        final int n3 = (int)(Math.min(n, n2) / Math.sqrt(2.0));
        int n4;
        int n5;
        int n6;
        int n7;
        if (this.m_bRaised) {
            n4 = n2 - n3 + 1;
            n5 = n4 - 1 + this.getThickness();
            n6 = n - n3 + 1;
            n7 = n6 - 1 + this.getThickness();
        }
        else {
            n5 = n2 - n3 + 1;
            n4 = n5 - 1 + this.getThickness();
            n7 = n - n3 + 1;
            n6 = n7 - 1 + this.getThickness();
        }
        return new Insets(n4, n6, n5, n7);
    }
    
    public void paint(final Graphics graphics, final Rectangle rectangle) {
        final Color color = graphics.getColor();
        for (int i = 0; i < this.getThickness(); ++i) {
            final int n = rectangle.height - (this.getThickness() - i);
            final int n2 = rectangle.width - (this.getThickness() - i);
            final int n3 = rectangle.x + i;
            final int n4 = rectangle.y + i;
            final int n5 = rectangle.x + n2;
            final int n6 = rectangle.y + n;
            if (this.m_bRaised) {
                if (i == this.getThickness() - 1) {
                    graphics.setColor(this.m_clrReflect);
                    this.drawFromLightSource(graphics, rectangle.x, rectangle.y, n3 + rectangle.width - this.getThickness() * 2, n4 + rectangle.height - this.getThickness() * 2);
                }
            }
            else {
                graphics.setColor(this.m_clrShadow);
                this.drawFromLightSource(graphics, n3, n4, rectangle.x + rectangle.width - 2, rectangle.y + rectangle.height - 2);
            }
            if (this.m_bRaised) {
                graphics.setColor(this.m_clrShadow);
                this.drawShadowedEdges(graphics, n3, n4, n5, n6);
            }
            else if (i == this.getThickness() - 1) {
                graphics.setColor(this.m_clrReflect);
                this.drawShadowedEdges(graphics, rectangle.x, rectangle.y, n5, n6);
            }
        }
        int n7;
        int n8;
        int n9;
        int n10;
        if (this.m_bRaised) {
            n7 = rectangle.x + 1;
            n8 = rectangle.y + 1;
            n9 = rectangle.width - this.getThickness() - 1;
            n10 = rectangle.height - this.getThickness() - 1;
        }
        else {
            n7 = rectangle.x + this.getThickness();
            n8 = rectangle.y + this.getThickness();
            n9 = rectangle.width - this.getThickness() - 1;
            n10 = rectangle.height - this.getThickness() - 1;
        }
        this.fillInterior(graphics, n7, n8, n9, n10);
        graphics.setColor(color);
    }
    
    protected void drawFromLightSource(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int arcLength = this.getArcLength(n3 - n);
        final int arcLength2 = this.getArcLength(n4 - n2);
        final int n5 = arcLength / 2;
        final int n6 = arcLength2 / 2;
        graphics.drawArc(n, n4 - arcLength2, arcLength, arcLength2, -180, 45);
        graphics.drawLine(n, n4 - n6, n, n2 + n6);
        graphics.drawArc(n, n2, arcLength, arcLength2, 180, -90);
        graphics.drawLine(n + n5, n2, n3 - n5, n2);
        graphics.drawArc(n3 - arcLength, n2, arcLength, arcLength2, 90, -45);
    }
    
    protected void drawShadowedEdges(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int arcLength = this.getArcLength(n3 - n);
        final int arcLength2 = this.getArcLength(n4 - n2);
        final int n5 = arcLength / 2;
        final int n6 = arcLength2 / 2;
        graphics.drawArc(n3 - arcLength, n2, arcLength, arcLength2, 45, -45);
        graphics.drawLine(n3, n2 + n6, n3, n4 - n6);
        graphics.drawArc(n3 - arcLength, n4 - arcLength2, arcLength, arcLength2, 0, -90);
        graphics.drawLine(n3 - n5, n4, n + n5, n4);
        graphics.drawArc(n, n4 - arcLength2, arcLength, arcLength2, -90, -45);
    }
    
    private void fillInterior(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int arcLength = this.getArcLength(n3);
        final int arcLength2 = this.getArcLength(n4);
        graphics.setColor(this.getBackground());
        graphics.fillRoundRect(n, n2, n3, n4, arcLength, arcLength2);
    }
    
    public void copyFrom(final Border border) {
        if (border instanceof ThreeDBorder) {
            this.m_clrShadow = ((ThreeDBorder)border).m_clrShadow;
            this.m_clrReflect = ((ThreeDBorder)border).m_clrReflect;
            this.m_bRaised = ((ThreeDBorder)border).m_bRaised;
        }
        super.copyFrom(border);
    }
}
