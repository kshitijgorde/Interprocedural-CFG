import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cD extends JPanel
{
    private static final int[] a;
    private static final int[] b;
    private static final int[] c;
    private static final int[] d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;
    private int[] i;
    private int[] j;
    static int a;
    private int b;
    private boolean a;
    private boolean b;
    
    rp_cD(int b, final boolean b2) {
        this.i = null;
        this.j = null;
        this.a = false;
        this.b = false;
        this.b = b2;
        this.setBackground(rp_aJ.g);
        final rp_cD rp_cD = this;
        b = b;
        this = rp_cD;
        switch (rp_cD.b = b) {
            case 0: {
                this.i = rp_cD.a;
                this.j = rp_cD.e;
            }
            case 1: {
                this.i = rp_cD.b;
                this.j = rp_cD.f;
            }
            case 2: {
                this.i = rp_cD.c;
                this.j = rp_cD.g;
            }
            case 3: {
                this.i = rp_cD.d;
                this.j = rp_cD.h;
                break;
            }
        }
    }
    
    final int a() {
        return this.b;
    }
    
    public final int getWidth() {
        return this.getBounds().width;
    }
    
    public final int getHeight() {
        return this.getBounds().height;
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(180, 130);
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.getWidth() == 0 || this.getHeight() == 0) {
            return;
        }
        if (this.a) {
            final Color color = graphics.getColor();
            graphics.setColor(rp_aJ.f);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.setColor(color);
        }
        graphics.setColor(rp_aJ.h);
        final double min = Math.min((this.getWidth() - 20 - 8) / 90.0, (this.getHeight() - 20 - 8) / 60.0);
        final int n = (this.getWidth() - (int)(min * 90.0) - 8) / 2 + 4;
        final int n2 = (this.getHeight() - (int)(min * 60.0) - 8) / 2 + 4;
        int i;
        for (i = 2; i < this.i.length; i += 2) {
            this.a(graphics, (int)(n + min * this.i[i - 2]), (int)(n2 + min * this.i[i - 1]), (int)(n + min * this.i[i]), (int)(n2 + min * this.i[i + 1]));
        }
        this.a(graphics, (int)(n + min * this.i[i - 2]), (int)(n2 + min * this.i[i - 1]), (int)(n + min * this.i[0]), (int)(n2 + min * this.i[1]));
        if (this.b) {
            int n3;
            int j;
            for (n3 = 0, j = 2; j < this.i.length; j += 2, ++n3) {
                if (n3 < this.j.length && this.j[n3] != 0) {
                    this.a(graphics, this.j[n3] - 1, n + (int)(min * (this.i[j - 2] + this.i[j]) / 2.0), n2 + (int)(min * (this.i[j - 1] + this.i[j + 1]) / 2.0));
                }
            }
            if (n3 < this.j.length && this.j[n3] != 0) {
                this.a(graphics, this.j[n3] - 1, n + (int)(min * (this.i[j - 2] + this.i[0]) / 2.0), n2 + (int)(min * (this.i[j - 1] + this.i[1]) / 2.0));
            }
        }
    }
    
    final int b() {
        int n = 0;
        for (int i = 0; i < this.j.length; ++i) {
            if (n < this.j[i]) {
                n = this.j[i];
            }
        }
        return n;
    }
    
    static String a(final int n) {
        return String.valueOf("ABCDEF".charAt(n));
    }
    
    private void a(final Graphics graphics, final int n, int n2, int n3) {
        if ((rp_cD.a & 0x1) != 0x0) {
            n2 = this.getWidth() - n2;
        }
        if ((rp_cD.a & 0x2) != 0x0) {
            n3 = this.getHeight() - n3;
        }
        final String a = a(n);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n4 = (int)(1.2 * Math.max(fontMetrics.stringWidth(a), fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()));
        graphics.setColor(Color.white);
        graphics.fillOval(n2 - n4 / 2, n3 - n4 / 2, n4, n4);
        graphics.setColor(rp_aJ.d);
        graphics.drawOval(n2 - n4 / 2, n3 - n4 / 2, n4, n4);
        graphics.drawString(a, n2 - fontMetrics.stringWidth(a) / 2, n3 + fontMetrics.getAscent() / 2 - 1);
    }
    
    private void a(final Graphics graphics, int n, int n2, int n3, int n4) {
        if ((rp_cD.a & 0x1) != 0x0) {
            n = this.getWidth() - n;
            n3 = this.getWidth() - n3;
        }
        if ((rp_cD.a & 0x2) != 0x0) {
            n2 = this.getHeight() - n2;
            n4 = this.getHeight() - n4;
        }
        if (n == n3) {
            final int n5 = n2;
            final int n6 = n4;
            final int n7 = n;
            int n8 = n6;
            int n9 = n5;
            if (n9 < n8) {
                n9 -= 4;
                n8 += 4;
            }
            else {
                n9 += 4;
                n8 -= 4;
            }
            graphics.fillRoundRect(n7 - 4, Math.min(n9, n8), 8, Math.abs(n8 - n9), 8, 8);
        }
        if (n2 == n4) {
            final int n10 = n;
            final int n11 = n3;
            final int n12 = n2;
            int n13 = n11;
            int n14 = n10;
            if (n14 < n13) {
                n14 -= 4;
                n13 += 4;
            }
            else {
                n14 += 4;
                n13 -= 4;
            }
            graphics.fillRoundRect(Math.min(n14, n13), n12 - 4, Math.abs(n13 - n14), 8, 8, 8);
        }
    }
    
    final void a(final boolean a) {
        this.a = a;
    }
    
    static {
        a = new int[] { 0, 0, 90, 0, 90, 60, 0, 60 };
        b = new int[] { 0, 0, 40, 0, 40, 20, 90, 20, 90, 60, 0, 60 };
        c = new int[] { 0, 0, 30, 0, 30, 20, 60, 20, 60, 10, 90, 10, 90, 60, 0, 60 };
        d = new int[] { 0, 0, 90, 0, 90, 30, 75, 30, 75, 60, 30, 60, 30, 40, 0, 40 };
        final int[] array = { 0, 0, 60, 0, 60, 10, 90, 10, 90, 60, 30, 60, 30, 40, 0, 40 };
        e = new int[] { 1, 0, 0, 2 };
        f = new int[] { 1, 0, 0, 4, 3, 2 };
        g = new int[] { 1, 6, 0, 0, 5, 4, 3, 2 };
        h = new int[] { 1, 6, 0, 5, 4, 0, 3, 2 };
        final int[] array2 = { 1, 1, 1 };
        rp_cD.a = 0;
    }
}
