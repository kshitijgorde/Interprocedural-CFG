// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.common.ui;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JLabel;

public class SmoothLabel extends JLabel
{
    private static final long serialVersionUID = 1L;
    
    public SmoothLabel() {
    }
    
    public SmoothLabel(final String s) {
        super(s);
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(graphics2D);
    }
}
