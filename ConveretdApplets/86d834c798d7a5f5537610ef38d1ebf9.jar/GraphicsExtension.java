import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class GraphicsExtension
{
    public static void drawBetter3DRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        Color color;
        Color color2;
        Color color3;
        Color color4;
        if (b) {
            color = new Color(127, 127, 127);
            color2 = Color.white;
            color3 = Color.black;
            color4 = new Color(223, 223, 223);
        }
        else {
            color2 = Color.black;
            color = new Color(223, 223, 223);
            color3 = Color.white;
            color4 = new Color(127, 127, 127);
        }
        final Color color5 = graphics.getColor();
        graphics.setColor(color4);
        graphics.drawLine(n, n2, n, n2 + n4 - 1);
        graphics.drawLine(n, n2, n + n3 - 1, n2);
        graphics.setColor(color3);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.setColor(color2);
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + n4 - 2);
        graphics.drawLine(n + 1, n2 + 1, n + n3 - 2, n2 + 1);
        graphics.setColor(color);
        graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
        graphics.setColor(color5);
    }
}
