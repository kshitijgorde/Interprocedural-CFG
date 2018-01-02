// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Event;

public class SignCheck extends CheckButton
{
    private boolean fChecked;
    
    public SignCheck() {
        this.fChecked = this.getChecked();
    }
    
    public void setChecked(final boolean b) {
        if (this.fChecked != b) {
            this.fChecked = b;
            this.repaint();
        }
        super.setChecked(b);
    }
    
    public boolean handleEvent(final Event event) {
        final boolean handleEvent = super.handleEvent(event);
        if (this.fChecked != this.getChecked()) {
            this.fChecked = this.getChecked();
            this.repaint();
        }
        return handleEvent;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final Dimension size = this.size();
        graphics.setColor(this.getForeground());
        final int n = size.width - 6 >> 1;
        final int n2 = size.height - 2 >> 1;
        graphics.drawLine(n, n2, n + 5, n2);
        graphics.drawLine(n, n2 + 1, n + 5, n2 + 1);
        if (!this.fChecked) {
            final int n3 = size.width - 2 >> 1;
            final int n4 = size.height - 6 >> 1;
            graphics.drawLine(n3, n4, n3, n4 + 5);
            graphics.drawLine(n3 + 1, n4, n3 + 1, n4 + 5);
        }
    }
}
