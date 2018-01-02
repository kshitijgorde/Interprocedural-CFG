// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.Graphics;
import javax.swing.JPanel;

final class X extends JPanel
{
    X(final WordleApplet wordleApplet) {
    }
    
    protected final void paintComponent(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
