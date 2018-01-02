// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.metapanel;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

class ColoredTabbedPaneUI extends MetalTabbedPaneUI
{
    public void setSelectedTabBackground(final Color color) {
        this.selectColor = color;
    }
    
    protected void paintFocusIndicator(final Graphics g, final int tabPlacement, final Rectangle[] rects, final int tabIndex, final Rectangle iconRect, final Rectangle textRect, final boolean isSelected) {
    }
}
