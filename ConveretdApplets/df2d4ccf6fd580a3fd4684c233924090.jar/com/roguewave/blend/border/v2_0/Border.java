// 
// Decompiled by Procyon v0.5.30
// 

package com.roguewave.blend.border.v2_0;

import java.awt.SystemColor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.Serializable;

public abstract class Border implements Serializable
{
    Color m_clrBackground;
    Color m_clrBorder;
    int m_nThickness;
    
    public void setBackground(final Color clrBackground) {
        this.m_clrBackground = clrBackground;
    }
    
    public Color getBackground() {
        return this.m_clrBackground;
    }
    
    public void setThickness(final int nThickness) {
        this.m_nThickness = nThickness;
    }
    
    public int getThickness() {
        return this.m_nThickness;
    }
    
    public void setBorderColor(final Color clrBorder) {
        this.m_clrBorder = clrBorder;
    }
    
    public Color getBorderColor() {
        return this.m_clrBorder;
    }
    
    public abstract Insets getInsets(final Rectangle p0);
    
    public abstract void paint(final Graphics p0, final Rectangle p1);
    
    public void copyFrom(final Border border) {
        this.m_clrBackground = border.m_clrBackground;
        this.m_clrBorder = border.m_clrBorder;
        this.m_nThickness = border.m_nThickness;
    }
    
    public Border() {
        this.m_clrBackground = SystemColor.control;
        this.m_clrBorder = Color.black;
        this.m_nThickness = 1;
    }
}
