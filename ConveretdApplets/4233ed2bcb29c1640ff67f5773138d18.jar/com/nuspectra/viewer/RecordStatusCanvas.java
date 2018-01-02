// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

class RecordStatusCanvas extends Canvas
{
    String curStatus;
    
    public RecordStatusCanvas(final int width, final int height) {
        this.curStatus = "Initializing...";
        this.resize(width, height);
    }
    
    protected void setStatus(final String i) {
        boolean needRepaint;
        if (i != null) {
            needRepaint = !this.curStatus.equals(i);
            this.curStatus = i;
        }
        else {
            needRepaint = !this.curStatus.equals(" - ");
            this.curStatus = " - ";
        }
        if (needRepaint) {
            this.repaint();
        }
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.white);
        g.fillRect(1, 1, this.size().width - 1, this.size().height - 1);
        g.setColor(Color.black);
        g.drawString(this.curStatus, 10, 12);
        g.setColor(Color.darkGray);
        g.drawRoundRect(0, 0, this.size().width - 1, this.size().height, 2, 2);
    }
}
