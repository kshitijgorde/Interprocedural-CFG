// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public final class bg extends Canvas
{
    private boolean a;
    private String a;
    private String b;
    private Font a;
    private Font b;
    private Color a;
    private Color b;
    
    public final Dimension preferredSize() {
        final Dimension preferredSize = super.preferredSize();
        final int height = this.getFontMetrics(this.a).getHeight();
        final int height2 = this.getFontMetrics(this.b).getHeight();
        if (this.a) {
            return new Dimension(preferredSize.width, Math.max(Math.max(height, height2) + 8, preferredSize.height));
        }
        return new Dimension(preferredSize.width, Math.max(height + height2 + 12, preferredSize.height));
    }
    
    public final void a(final String a) {
        this.a = a;
        this.b = null;
        this.repaint();
    }
    
    public final void a(final Font a, final Font b) {
        this.a = a;
        this.b = b;
        this.repaint();
    }
    
    public final void a(final Color a, final Color b) {
        this.a = a;
        this.b = b;
        this.repaint();
    }
    
    public final void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.a);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.b);
        final Dimension size = this.size();
        graphics.clipRect(3, 3, size.width - 7, size.height - 7);
        int n;
        int n2;
        int n3;
        if (this.a) {
            n = (size.height + fontMetrics.getAscent()) / 2 - 1;
            n2 = (size.height + fontMetrics2.getAscent()) / 2 - 1;
            n3 = 5 + fontMetrics.stringWidth(this.a) + 7;
        }
        else {
            final int n4 = (size.height - (fontMetrics.getHeight() + fontMetrics2.getHeight())) / 4;
            n = fontMetrics.getAscent() + n4;
            n2 = size.height - 2 * fontMetrics2.getDescent() - n4;
            n3 = 5;
        }
        graphics.setColor(this.a);
        graphics.setFont(this.a);
        graphics.drawString(this.a, 5, n);
        if (this.b != null) {
            graphics.setColor(this.b);
            graphics.setFont(this.b);
            graphics.drawString(this.b, n3, n2);
        }
    }
    
    public bg() {
        this.a = "";
        this.b = "";
        this.a = true;
        final Font c = ay.c;
        this.b = c;
        this.a = c;
        final Color black = Color.black;
        this.b = black;
        this.a = black;
    }
}
