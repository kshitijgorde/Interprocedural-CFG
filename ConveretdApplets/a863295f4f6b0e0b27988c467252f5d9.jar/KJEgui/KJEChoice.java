// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Choice;

public class KJEChoice extends Choice
{
    public void printAll(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.setFont(this.getFont());
        graphics.drawString(this.getSelectedItem(), 0, graphics.getClipBounds().height / 2 + graphics.getFontMetrics().getDescent() + 1);
    }
}
