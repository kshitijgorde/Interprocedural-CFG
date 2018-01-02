// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;

public class BorderPanel extends Panel
{
    private boolean hasframe;
    
    public BorderPanel() {
        this.hasframe = true;
    }
    
    public BorderPanel(final LayoutManager layoutManager) {
        super(layoutManager);
        this.hasframe = true;
    }
    
    public boolean getHasframe() {
        return this.hasframe;
    }
    
    public void invalidate() {
        super.invalidate();
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.hasframe) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        }
    }
    
    public void setHasframe(final boolean hasframe) {
        this.hasframe = hasframe;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
