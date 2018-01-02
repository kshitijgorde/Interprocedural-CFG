// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JTextArea;

public class P extends JTextArea
{
    public P() {
    }
    
    public P(final String s, final int n, final int n2) {
        super(s, n, n2);
    }
    
    public void paintComponent(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(graphics);
    }
}
