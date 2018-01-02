// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

import java.awt.Graphics;
import javax.swing.JFrame;

final class a extends JFrame
{
    private final DisplayUtilities a;
    
    a(final DisplayUtilities a) {
        this.a = a;
    }
    
    public final void paint(final Graphics graphics) {
        DisplayUtilities.a(this.a).paint(graphics);
    }
}
