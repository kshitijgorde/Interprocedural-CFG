// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JTextField;

public class ar extends JTextField
{
    public ar() {
    }
    
    public ar(final String s) {
        super(s);
    }
    
    public void paintComponent(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(graphics);
    }
}
