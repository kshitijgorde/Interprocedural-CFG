// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

public final class cx extends bZ
{
    public Image q;
    public String q;
    private static Image w;
    
    public cx(final int n, final String s) {
        super(n, s);
    }
    
    public cx(final cx cx) {
        super(cx.r, cx.getName());
        this.q = cx.q;
        this.q = cx.q;
    }
    
    public final Object q(final String s) {
        if ("image".equals(s)) {
            return this.q;
        }
        return super.q(s);
    }
    
    public static Image q(final Component component) {
        if (cx.w == null) {
            cx.w = dU.q(component, 23, 23, "s.cnsi");
        }
        final Graphics graphics;
        (graphics = cx.w.getGraphics()).setColor(new Color(238, 238, 238));
        graphics.fillRect(0, 0, 23, 23);
        final Font font = new Font("Serif", 1, 14);
        graphics.setColor(Color.black);
        graphics.setFont(font);
        graphics.drawString("No", 6, 17);
        graphics.dispose();
        return cx.w;
    }
    
    public final int q(final cx cx) {
        final int q;
        if ((q = super.q(cx)) != 0) {
            return q;
        }
        if (this.q != cx.q) {
            return 1;
        }
        if (this.q.compareTo(cx.q) != 0) {
            return this.q.compareTo(cx.q);
        }
        return 0;
    }
    
    static {
        cx.w = null;
    }
}
