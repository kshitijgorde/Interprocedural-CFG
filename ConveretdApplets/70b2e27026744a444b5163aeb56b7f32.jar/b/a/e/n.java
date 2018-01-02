// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Dimension;
import b.a.d.b;
import java.awt.Graphics;
import javax.swing.plaf.ColorUIResource;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.SwingConstants;

public class n extends m implements SwingConstants
{
    protected int c;
    protected String d;
    public static final Color e;
    public static final Color f;
    protected static BufferedImage g;
    protected static int h;
    protected static int i;
    protected static int j;
    protected static int[] k;
    protected static int[] l;
    static /* synthetic */ Class m;
    
    public n() {
        this("", 2, null);
    }
    
    public n(final String s) {
        this(s, 2, null);
    }
    
    public n(final String d, final int c, final Color color) {
        this.d = d;
        this.c = c;
        this.setOpaque(false);
        this.setForeground((color != null) ? color : new ColorUIResource(n.e));
    }
    
    protected void a(final Graphics graphics) {
        int n = 0;
        if (this.c != 2) {
            n = this.getWidth() - a(this.d);
            if (this.c == 0) {
                n /= 2;
            }
        }
        if (!this.isEnabled()) {
            graphics.setColor(b.a.d.b.a(graphics.getColor(), b.a.e.n.f));
        }
        a(graphics, this.d, n, b.a.e.n.j);
    }
    
    public Dimension b() {
        return new Dimension(a(this.d), n.h);
    }
    
    public Dimension c() {
        return new Dimension(a(this.d), n.h);
    }
    
    public static void a(final Graphics graphics, final String s, final int n, final int n2) {
        if (s == null) {
            return;
        }
        int n3 = n;
        for (int i = 0; i < s.length(); ++i) {
            n3 += a(graphics, s.charAt(i), n3, n2);
        }
    }
    
    public static int a(final Graphics graphics, final char c, final int n, final int n2) {
        final int a = a(c);
        final int n3 = n2 - n.j;
        if (n.k[c] == 0) {
            graphics.drawRect(n, n3, a - 2, n.h - 2);
        }
        else {
            final BufferedImage bufferedImage = new BufferedImage(a, n.h, 2);
            final int[] rgb = n.g.getRGB(n.l[c], 0, a, n.h, null, 0, a);
            final int n4 = graphics.getColor().getRGB() & 0xFFFFFF;
            final int alpha = graphics.getColor().getAlpha();
            for (int i = 0; i < rgb.length; ++i) {
                rgb[i] = ((((rgb[i] & 0xFF000000) >>> 24) * alpha + 127) / 255 << 24 | n4);
            }
            bufferedImage.setRGB(0, 0, a, n.h, rgb, 0, a);
            graphics.drawImage(bufferedImage, n, n3, null);
        }
        return a;
    }
    
    public static int a(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += a(s.charAt(i));
        }
        return n;
    }
    
    public static int a(final char c) {
        if (c > '\u00ff' || n.k[c] == 0) {
            return n.k[78];
        }
        return n.k[c];
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        e = new Color(204);
        f = new Color(15658734);
        n.i = -1;
        n.j = -1;
        n.k = new int[256];
        n.l = new int[256];
        final Image a = b.a.e.a.a("$/MiniLabel_charset.gif", (n.m == null) ? (n.m = b("b.a.e.n")) : n.m);
        final int width = a.getWidth(null);
        final int height = a.getHeight(null);
        n.h = height - 1;
        n.g = new BufferedImage(width, height, 2);
        n.g.getGraphics().drawImage(a, 0, 0, null);
        for (int i = 0; i < height; ++i) {
            if (n.g.getRGB(0, i) == -16777216) {
                n.j = i;
                break;
            }
        }
        int n = 0;
        for (int n2 = 32, n3 = 1; n3 < width && n2 <= 255; ++n3) {
            if (b.a.e.n.g.getRGB(n3, height - 1) == -16777216) {
                final int n4 = n3 - n;
                b.a.e.n.k[n2] = n4;
                b.a.e.n.i = Math.max(b.a.e.n.i, n4);
                b.a.e.n.l[n2] = n + 1;
                n = n3;
                if (++n2 == 127) {
                    n2 = 160;
                }
            }
        }
        for (int j = 0; j < b.a.e.n.h; ++j) {
            for (int k = 0; k < width; ++k) {
                b.a.e.n.g.setRGB(k, j, 255 - (b.a.e.n.g.getRGB(k, j) & 0xFF) << 24);
            }
        }
    }
}
