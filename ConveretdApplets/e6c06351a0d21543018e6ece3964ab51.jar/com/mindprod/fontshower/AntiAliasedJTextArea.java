// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.fontshower;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JTextArea;

public final class AntiAliasedJTextArea extends JTextArea
{
    private static final boolean DEBUGGING = false;
    private boolean antialias;
    
    public AntiAliasedJTextArea() {
        this.antialias = true;
    }
    
    public AntiAliasedJTextArea(final String text) {
        super(text);
        this.antialias = true;
    }
    
    public void setAntiAlias(final boolean antialias) {
        this.antialias = antialias;
        this.repaint();
    }
    
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        if (this.antialias) {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }
        super.paintComponent(g2d);
    }
}
