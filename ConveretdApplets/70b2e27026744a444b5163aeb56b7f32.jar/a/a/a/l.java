// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import b.a.a.a;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import b.a.c.q;
import javax.swing.JPanel;

class l extends JPanel
{
    private q a;
    private final String b = "12°34'N · 123°45'E · UT-05:00 §";
    private final k c;
    
    public l(final k c, final q a) {
        this.c = c;
        this.a = a;
        this.setOpaque(true);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(250, k.a(this.c));
    }
    
    protected void paintComponent(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(j.e(k.b(this.c)));
        String s = this.a.g();
        final String a = this.a();
        int n = fontMetrics.stringWidth(s);
        final int stringWidth = fontMetrics.stringWidth("W...");
        final int stringWidth2 = fontMetrics.stringWidth("    ");
        final int stringWidth3 = fontMetrics2.stringWidth("12°34'N · 123°45'E · UT-05:00 §");
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(this.getForeground());
        final int width = k.c(this.c).getViewport().getExtentSize().width;
        final int n2 = width - stringWidth3 - stringWidth2;
        if (n > n2) {
            s += "...";
            while (s.length() > 5 && n > stringWidth) {
                s = s.substring(0, s.length() - 4) + "...";
                n = fontMetrics.stringWidth(s);
                if (n <= n2) {
                    break;
                }
            }
        }
        graphics.drawString(s, 0, k.d(this.c));
        graphics.setFont(j.e(k.b(this.c)));
        graphics.drawString(a, Math.max(width - stringWidth3, stringWidth + stringWidth2), k.d(this.c));
    }
    
    private String a() {
        a a = this.a.c();
        String s = "E";
        a a2 = this.a.d();
        String s2 = "N";
        if (a.a() <= 0.0) {
            a = a.a(-1.0, -3);
            s = "W";
        }
        if (a2.a() < 0.0) {
            a2 = a2.a(-1.0, -3);
            s2 = "S";
        }
        String s3 = a2.a("0#°0#'") + s2 + " · " + a.a("00#°0#'") + s;
        if (this.a.f() != 1) {
            s3 = s3 + " · " + b.a.c.k.a(this.a.e(), false, false);
            if (this.a.f() != 0) {
                s3 += " §";
            }
        }
        return s3;
    }
}
