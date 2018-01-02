import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetUtils
{
    public static final void fill3DRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (b) {
            graphics.setColor(Color.black);
            graphics.drawRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
            graphics.drawRect(n + 2, n2 + 2, n3 - 3, n4 - 3);
            graphics.setColor(Color.lightGray.brighter());
            graphics.setColor(new Color(234, 234, 234));
            graphics.drawRect(n, n2, n3 - 2, n4 - 2);
            graphics.drawRect(n + 1, n2 + 1, n3 - 3, n4 - 3);
            graphics.setColor(Color.gray);
            graphics.drawRect(n + 2, n2 + 2, n3 - 4, n4 - 4);
            graphics.setColor(Color.lightGray);
            graphics.fillRect(n + 2, n2 + 2, n3 - 4, n4 - 4);
            return;
        }
        graphics.setColor(Color.lightGray);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(Color.gray);
        graphics.drawRect(n, n2, n3 - 1, n4 - 1);
    }
}
