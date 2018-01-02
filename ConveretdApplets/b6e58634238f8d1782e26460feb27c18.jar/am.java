import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class am extends al
{
    public String a;
    public ah b;
    
    public am(final d d, final boolean b, String a, final Object o) {
        super(d, b);
        if (a == null) {
            a = "";
        }
        this.a = a;
        if (o instanceof ah) {
            this.b = (ah)o;
        }
        else {
            (this.b = new Object(super.a, this.a.length()) {
                public d a = a;
                public Vector b = new Vector(n);
                public Vector c = new Vector(n);
                
                public final void a(final Color color, final int y) {
                    if (this.b.size() == 0) {
                        if (y > 0) {
                            this.b.addElement(new Point(0, y));
                            this.c.addElement(color);
                        }
                    }
                    else {
                        final Point point = this.b.elementAt(this.b.size() - 1);
                        final Color color2 = this.c.elementAt(this.c.size() - 1);
                        if (y > point.y) {
                            if ((color2 == null && color == null) || (color2 != null && color2.equals(color))) {
                                point.y = y;
                            }
                            else {
                                this.b.addElement(new Point(point.y, y));
                                this.c.addElement(color);
                            }
                        }
                    }
                }
                
                public final int a() {
                    return this.b.size();
                }
                
                public final Point a(final int n) {
                    return this.b.elementAt(n);
                }
                
                public final Color b(final int n) {
                    return this.c.elementAt(n);
                }
            }).a(null, this.a.length());
        }
        if (o instanceof Font) {
            this.setFont((Font)o);
        }
    }
    
    public final ah a() {
        return this.b;
    }
    
    public final Dimension c() {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
        final Dimension dimension2;
        final Dimension dimension = dimension2 = new Dimension(6, 2);
        dimension2.width += fontMetrics.stringWidth(this.a);
        final Dimension dimension3 = dimension;
        dimension3.height += fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        return dimension;
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        final String a;
        if ((a = this.a) != null) {
            graphics.setFont(this.getFont());
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() <= size.height) {
                String a2 = super.a.d().a(a, size.width, fontMetrics, super.a.d().a());
                if (a2 == null) {
                    a2 = "";
                }
                final boolean endsWith = a2.endsWith("...");
                if (a2.length() > 0) {
                    final Object a3 = this.a();
                    int n = 3;
                    final int n2 = fontMetrics.getMaxAscent() + 1;
                    for (int i = 0; i < a3.a(); ++i) {
                        final Point a4 = a3.a(i);
                        Color color;
                        if ((color = a3.b(i)) == null) {
                            color = this.getForeground();
                        }
                        graphics.setColor(color);
                        final String substring = a2.substring(a4.x, endsWith ? Math.min(a4.y, a2.length() - "...".length()) : a4.y);
                        graphics.drawString(substring, n, n2);
                        n += fontMetrics.stringWidth(substring);
                        if (endsWith && a4.y > a2.length() - "...".length()) {
                            graphics.setColor(this.getForeground());
                            graphics.drawString("...", n, n2);
                            break;
                        }
                    }
                }
            }
        }
    }
}
