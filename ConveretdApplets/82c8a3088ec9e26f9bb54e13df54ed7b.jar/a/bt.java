// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

public final class bt extends aJ
{
    public Image q;
    private static Image w;
    
    public bt(final int n, final String s) {
        super(n, s);
    }
    
    public final Object q(final String s) {
        if ("image".equals(s)) {
            return this.q;
        }
        return super.q(s);
    }
    
    public static Image q(final Component component) {
        if (bt.w == null) {
            bt.w = cs.q(component, 23, 23, "s.cnsi");
        }
        final Graphics graphics;
        (graphics = bt.w.getGraphics()).setColor(new Color(238, 238, 238));
        graphics.fillRect(0, 0, 23, 23);
        final Font font = new Font("Serif", 1, 14);
        graphics.setColor(Color.black);
        graphics.setFont(font);
        graphics.drawString("No", 6, 17);
        graphics.dispose();
        return bt.w;
    }
    
    static {
        bt.w = null;
    }
}
