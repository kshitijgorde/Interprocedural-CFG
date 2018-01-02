// 
// Decompiled by Procyon v0.5.30
// 

package com.quotemedia.awt;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public class Line extends Component
{
    int len;
    boolean top;
    boolean vertical;
    
    public Line(final boolean top, final boolean vertical) {
        this.top = false;
        this.vertical = false;
        this.top = top;
        this.vertical = vertical;
    }
    
    public void paint(final Graphics g) {
        if (this.vertical) {
            g.setColor(Color.decode("#000000"));
            g.drawLine(0, 0, 0, this.getSize().height);
            g.setColor(Color.decode("#FFFFFF"));
            g.drawLine(1, 0, 1, this.getSize().height);
        }
        else {
            g.setColor(Color.decode("#000000"));
            g.drawLine(0, 0, this.getSize().width - 2, 0);
            g.setColor(Color.decode("#FFFFFF"));
            g.drawLine(0, 1, this.getSize().width, 1);
            if (this.top) {
                g.setColor(Color.decode("#000000"));
                g.drawLine(0, 0, 0, 1);
                g.drawLine(this.getSize().width - 2, 0, this.getSize().width - 2, 1);
            }
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(2, 2);
    }
}
