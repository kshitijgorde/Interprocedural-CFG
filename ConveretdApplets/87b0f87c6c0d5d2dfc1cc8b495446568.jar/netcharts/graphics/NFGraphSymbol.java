// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Vector;
import netcharts.util.NFParam;
import java.awt.Rectangle;
import netcharts.util.NFColor;
import netcharts.util.NFUtil;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public final class NFGraphSymbol
{
    public static final int NONE = 0;
    public static final int CIRCLE = 1;
    public static final int SQUARE = 2;
    public static final int DIAMOND = 3;
    public static final int TRIANGLEUP = 4;
    public static final int TRIANGLEDOWN = 5;
    public static final int CROSS = 6;
    public static final int VRECTANGLE = 7;
    public static final int HRECTANGLE = 8;
    public static final int BAR = 9;
    public static final int TRIANGLEBAR = 10;
    public static final int DIAMONDBAR = 11;
    public static final int CYLINDER = 12;
    public static final int PIEVERTICAL = 13;
    public static final int PIEHORIZONTAL = 14;
    public static final int IMAGE = 15;
    public static final int FILLED = 1;
    public static final int OUTLINED = 2;
    public static final int BOTH = 3;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    public int type;
    public int size;
    public int style;
    private Color a;
    private Color b;
    private int c;
    private Image d;
    private double e;
    private Graphics f;
    private NFLine g;
    private Polygon h;
    private Polygon i;
    private Polygon j;
    private Polygon k;
    private Polygon l;
    
    public NFGraphSymbol() {
        this.type = 1;
        this.size = 4;
        this.style = 1;
        this.a = null;
        this.b = Color.black;
        this.c = 1;
        this.d = null;
        this.e = 0.0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }
    
    public Color getColor() {
        return this.a;
    }
    
    public void setColor(final Color a) {
        this.a = a;
    }
    
    public void setOutlineColor(final Color b) {
        this.b = b;
    }
    
    public void setOutlineWidth(final int c) {
        this.c = c;
        if (this.c < 1) {
            this.c = 1;
        }
    }
    
    public void setImage(final Image d) {
        this.d = d;
    }
    
    public void setScale(final double e) {
        this.e = e;
    }
    
    private void a(final Graphics graphics, final int n, final int n2) {
        if (this.d == null) {
            return;
        }
        final int width = this.d.getWidth(null);
        final int width2 = this.d.getWidth(null);
        if (width <= 0 || width2 <= 0) {
            return;
        }
        if (this.e <= 0.0) {
            graphics.drawImage(this.d, n - width / 2, n2 - width2 / 2, null);
        }
        else {
            int n3 = (int)(width * this.e);
            if (n3 < 1) {
                n3 = 1;
            }
            int n4 = (int)(width2 * this.e);
            if (n4 < 1) {
                n4 = 1;
            }
            graphics.drawImage(this.d, n - n3 / 2, n2 - n4 / 2, n3, n4, null);
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.a(n, n2, n3, n4, n5, null);
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, int n5, final NFPatternFill nfPatternFill) {
        final int n6 = n3 + n / 2;
        final int n7 = n4 + n / 2;
        final int n8 = n / 2;
        double n9 = 360.0;
        final double n10 = 720.0;
        final double n11 = 8.0;
        (this.l = new Polygon()).addPoint(n6, n7);
        do {
            final double n12 = 3.141592653589793 * (n9 / 180.0);
            this.l.addPoint(n6 + (int)NFUtil.rint(Math.cos(n12) * n8), n7 - (int)NFUtil.rint(Math.sin(n12) * n8));
            n9 += n11;
        } while (n9 < n10);
        final double n13 = 3.141592653589793 * (n10 / 180.0);
        this.l.addPoint(n6 + (int)NFUtil.rint(Math.cos(n13) * n8), n7 - (int)NFUtil.rint(Math.sin(n13) * n8));
        this.l.addPoint(n6, n7);
        if ((n2 & 0x1) == 0x0) {
            for (int i = 0; i < n5; ++i) {
                this.f.drawOval(n3 - i, n4 - i, n + i + i, n + i + i);
            }
            return;
        }
        final Color color = this.f.getColor();
        final boolean b = this.b != null && (n2 & 0x2) != 0x0;
        this.f.setColor(NFColor.darker(b ? this.b : color));
        this.f.fillOval(n3 - (b ? (n5 - 2) : -1), n4 - (b ? (n5 - 2) : -1), ((n == 1 && !b) ? (n + 1) : n) + (b ? (n5 + n5 - 2) : 0), ((n == 1 && !b) ? (n + 1) : n) + (b ? (n5 + n5 - 2) : 0));
        this.f.setColor(color);
        if (!b) {
            final int n14 = (n == 1) ? 2 : n;
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillOval(this.f, n3, n4, n14, n14, this.b, nfPatternFill);
            }
            else {
                this.f.fillOval(n3, n4, n14, n14);
            }
            return;
        }
        if (n5 <= 1) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillOval(this.f, n3, n4, n, n, this.b, nfPatternFill);
            }
            else {
                this.f.fillOval(n3, n4, n, n);
            }
            this.f.setColor(this.b);
            this.f.drawOval(n3, n4, n, n);
            this.f.setColor(color);
        }
        else {
            --n5;
            this.f.setColor(this.b);
            this.f.fillOval(n3 - n5, n4 - n5, n + n5 + n5, n + n5 + n5);
            this.f.setColor(color);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillOval(this.f, n3 + 1, n4 + 1, n - 2, n - 2, this.b, nfPatternFill);
            }
            else {
                this.f.fillOval(n3 + 1, n4 + 1, n - 2, n - 2);
            }
        }
    }
    
    private void b(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.b(n, n2, n3, n4, n5, null);
    }
    
    private void b(final int n, final int n2, final int n3, final int n4, int n5, final NFPatternFill nfPatternFill) {
        if ((n2 & 0x1) == 0x0) {
            for (int i = 0; i < n5; ++i) {
                this.f.drawRect(n3 - i, n4 - i, n + i + i, n + i + i);
            }
            return;
        }
        final Color color = this.f.getColor();
        final boolean b = this.b != null && (n2 & 0x2) != 0x0;
        this.f.setColor(NFColor.darker(b ? this.b : color));
        this.f.fillRect(n3 - (b ? (n5 - 2) : -1), n4 - (b ? (n5 - 2) : -1), n + (b ? (n5 + n5 - 2) : 0), n + (b ? (n5 + n5 - 2) : 0));
        this.f.setColor(color);
        if (!b) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(this.f, n3, n4, n, n, this.b, nfPatternFill);
            }
            else {
                this.f.fillRect(n3, n4, n, n);
            }
            return;
        }
        if (n5 <= 1) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(this.f, n3, n4, n, n, this.b, nfPatternFill);
            }
            else {
                this.f.fillRect(n3, n4, n, n);
            }
            this.f.setColor(this.b);
            this.f.drawRect(n3, n4, n, n);
            this.f.setColor(color);
        }
        else {
            --n5;
            this.f.setColor(this.b);
            this.f.fillRect(n3 - n5, n4 - n5, n + n5 + n5, n + n5 + n5);
            this.f.setColor(color);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(this.f, n3 + 1, n4 + 1, n - 2, n - 2, this.b, nfPatternFill);
            }
            else {
                this.f.fillRect(n3 + 1, n4 + 1, n - 2, n - 2);
            }
        }
    }
    
    private void a(final int n, int n2, final int n3, final int n4) {
        final int n5 = n2 / 2;
        switch (n) {
            case 4: {
                this.k.xpoints[0] = n3;
                this.k.ypoints[0] = n4 + n2;
                this.k.xpoints[1] = n3 + n5;
                this.k.ypoints[1] = n4;
                this.k.xpoints[2] = n3 + n2;
                this.k.ypoints[2] = n4 + n2;
                this.k.xpoints[3] = n3;
                this.k.ypoints[3] = n4 + n2;
                this.k.npoints = 4;
                break;
            }
            case 5: {
                this.k.xpoints[0] = n3;
                this.k.ypoints[0] = n4;
                this.k.xpoints[1] = n3 + n2;
                this.k.ypoints[1] = n4;
                this.k.xpoints[2] = n3 + n5;
                this.k.ypoints[2] = n4 + n2;
                this.k.xpoints[3] = n3;
                this.k.ypoints[3] = n4;
                this.k.npoints = 4;
                break;
            }
            case 3: {
                if (n2 > 1 && n2 % 2 == 1) {
                    --n2;
                }
                this.k.xpoints[0] = n3;
                this.k.ypoints[0] = n4 + n5;
                this.k.xpoints[1] = n3 + n5;
                this.k.ypoints[1] = n4;
                this.k.xpoints[2] = n3 + n2;
                this.k.ypoints[2] = n4 + n5;
                this.k.xpoints[3] = n3 + n5;
                this.k.ypoints[3] = n4 + n2;
                this.k.xpoints[4] = n3;
                this.k.ypoints[4] = n4 + n5;
                this.k.npoints = 5;
                break;
            }
            case 6: {
                final int n6 = (n2 == 1 || n2 == 2) ? 1 : (n2 / 3);
                final int n7 = 2 * n6;
                final int n8 = 3 * n6;
                this.k.xpoints[0] = n3 + n6;
                this.k.ypoints[0] = n4;
                this.k.xpoints[1] = n3 + n7;
                this.k.ypoints[1] = n4;
                this.k.xpoints[2] = n3 + n7;
                this.k.ypoints[2] = n4 + n6;
                this.k.xpoints[3] = n3 + n8;
                this.k.ypoints[3] = n4 + n6;
                this.k.xpoints[4] = n3 + n8;
                this.k.ypoints[4] = n4 + n7;
                this.k.xpoints[5] = n3 + n7;
                this.k.ypoints[5] = n4 + n7;
                this.k.xpoints[6] = n3 + n7;
                this.k.ypoints[6] = n4 + n8;
                this.k.xpoints[7] = n3 + n6;
                this.k.ypoints[7] = n4 + n8;
                this.k.xpoints[8] = n3 + n6;
                this.k.ypoints[8] = n4 + n7;
                this.k.xpoints[9] = n3;
                this.k.ypoints[9] = n4 + n7;
                this.k.xpoints[10] = n3;
                this.k.ypoints[10] = n4 + n6;
                this.k.xpoints[11] = n3 + n6;
                this.k.ypoints[11] = n4 + n6;
                this.k.xpoints[12] = n3 + n6;
                this.k.ypoints[12] = n4;
                this.k.npoints = 13;
                break;
            }
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.a(n, n2, n3, n4, n5, n6, null);
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final NFPatternFill nfPatternFill) {
        this.a(n, n2, n4, n5);
        if ((n3 & 0x1) == 0x0) {
            this.f.drawPolygon(this.k);
            for (int i = 1; i < n6; ++i) {
                this.a(n, n2 + i + i, n4 - i, n5 - i);
                this.f.drawPolygon(this.k);
            }
            return;
        }
        final Color color = this.f.getColor();
        final boolean b = this.b != null && (n3 & 0x2) != 0x0;
        this.f.setColor(NFColor.darker(b ? this.b : color));
        final Polygon k = new Polygon(this.k.xpoints, this.k.ypoints, this.k.npoints);
        final int n7 = (b && n == 6 && n6 % 3 == 0) ? -2 : -1;
        this.a(n, n2 + (b ? (n6 + n6 - 2) : 0), n4 - (b ? (n6 - 1 + n7) : n7), n5 - (b ? (n6 - 1 + n7) : n7));
        this.f.fillPolygon(this.k);
        if (b) {
            this.f.drawPolygon(this.k);
        }
        this.k = k;
        this.f.setColor(color);
        if (!b) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(this.f, this.k, this.b, nfPatternFill);
            }
            else {
                this.f.fillPolygon(this.k);
            }
            return;
        }
        if (n6 <= 1) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(this.f, this.k, this.b, nfPatternFill);
            }
            else {
                this.f.fillPolygon(this.k);
            }
            this.f.setColor(this.b);
            this.f.drawPolygon(this.k);
            this.f.setColor(color);
        }
        else {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(this.f, this.k, this.b, nfPatternFill);
            }
            else {
                this.f.fillPolygon(this.k);
            }
            this.f.setColor(this.b);
            for (int j = 0; j < n6; ++j) {
                this.a(n, n2 + j + j, n4 - j, n5 - j);
                this.f.drawPolygon(this.k);
            }
            this.f.setColor(color);
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.a(graphics, n, n2, n3, n4, n5, n6, null);
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, int n6, final NFPatternFill nfPatternFill) {
        if ((n2 & 0x1) == 0x0) {
            for (int i = 0; i < n6; ++i) {
                graphics.drawRect(n3 - n / 2 - i, n4 - i, n + i + i, n5 + i + i);
            }
            return;
        }
        if (this.b == null || (n2 & 0x2) == 0x0) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n3 - n / 2, n4, n, n5, this.b, nfPatternFill);
            }
            else {
                graphics.fillRect(n3 - n / 2, n4, n, n5);
            }
            return;
        }
        final Color color = graphics.getColor();
        if (n6 <= 1) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n3 - n / 2, n4, n, n5, this.b, nfPatternFill);
            }
            else {
                graphics.fillRect(n3 - n / 2, n4, n, n5);
            }
            graphics.setColor(this.b);
            graphics.drawRect(n3 - n / 2, n4, n, n5);
            graphics.setColor(color);
        }
        else {
            --n6;
            graphics.setColor(this.b);
            graphics.fillRect(n3 - n / 2 - n6, n4 - n6, n + n6 + n6, n5 + n6 + n6);
            graphics.setColor(color);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n3 - n / 2 + 1, n4 + 1, n - 2, n - 2, this.b, nfPatternFill);
            }
            else {
                graphics.fillRect(n3 - n / 2 + 1, n4 + 1, n - 2, n5 - 2);
            }
        }
    }
    
    private void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.b(graphics, n, n2, n3, n4, n5, n6, null);
    }
    
    private void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, int n6, final NFPatternFill nfPatternFill) {
        if ((n2 & 0x1) == 0x0) {
            for (int i = 0; i < n6; ++i) {
                graphics.drawRect(n3 - i, n4 - n / 2 - i, n5 + i + i, n + i + i);
            }
            return;
        }
        if (this.b == null || (n2 & 0x2) == 0x0) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n3, n4 - n / 2, n5, n, this.b, nfPatternFill);
            }
            else {
                graphics.fillRect(n3, n4 - n / 2, n5, n);
            }
            return;
        }
        final Color color = graphics.getColor();
        if (n6 <= 1) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n3, n4 - n / 2, n5, n, this.b, nfPatternFill);
            }
            else {
                graphics.fillRect(n3, n4 - n / 2, n5, n);
            }
            graphics.setColor(this.b);
            graphics.drawRect(n3, n4 - n / 2, n5, n);
            graphics.setColor(color);
        }
        else {
            --n6;
            graphics.setColor(this.b);
            graphics.fillRect(n3 - n6, n4 - n / 2 - n6, n5 + n6 + n6, n + n6 + n6);
            graphics.setColor(color);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n3 + 1, n4 - n / 2 + 1, n5 - 2, n - 2, this.b, nfPatternFill);
            }
            else {
                graphics.fillRect(n3 + 1, n4 - n / 2 + 1, n5 - 2, n - 2);
            }
        }
    }
    
    private void a() {
        if (this.i == null) {
            this.i = new Polygon();
            this.i.xpoints = new int[5];
            this.i.ypoints = new int[5];
            this.i.npoints = 5;
        }
        if (this.j == null) {
            this.j = new Polygon();
            this.j.xpoints = new int[5];
            this.j.ypoints = new int[5];
            this.j.npoints = 5;
        }
        if (this.h == null) {
            this.h = new Polygon();
            this.h.xpoints = new int[11];
            this.h.ypoints = new int[11];
            this.h.npoints = 11;
        }
        if (this.k == null) {
            this.k = new Polygon();
            this.k.xpoints = new int[20];
            this.k.ypoints = new int[20];
            this.k.npoints = 20;
        }
        if (this.l == null) {
            this.l = new Polygon();
            this.l.xpoints = new int[20];
            this.l.ypoints = new int[20];
        }
        this.l.npoints = 0;
    }
    
    public Polygon getOutline() {
        if (this.l == null || this.l.npoints == 0) {
            return null;
        }
        final Polygon polygon = new Polygon();
        polygon.xpoints = new int[this.l.npoints];
        polygon.ypoints = new int[this.l.npoints];
        polygon.npoints = this.l.npoints;
        System.arraycopy(this.l.xpoints, 0, polygon.xpoints, 0, polygon.npoints);
        System.arraycopy(this.l.ypoints, 0, polygon.ypoints, 0, polygon.npoints);
        return polygon;
    }
    
    public NFLine getBorderLine() {
        return this.g;
    }
    
    public void setBorderLine(final NFLine g) {
        this.g = g;
        if (g == null) {
            this.c = 0;
        }
        else {
            this.c = g.getThickness();
            this.b = g.getColor();
            if (this.b == null) {
                this.b = Color.black;
            }
        }
    }
    
    private void a(final Graphics graphics, final Polygon polygon) {
        if (this.c < 1) {
            return;
        }
        graphics.setColor(this.b);
        if (this.g == null) {
            graphics.drawPolygon(polygon);
            return;
        }
        this.g.drawPoly(graphics, polygon);
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.c < 1) {
            return;
        }
        graphics.setColor(this.b);
        if (this.g == null) {
            graphics.drawRect(n, n2, n3, n4);
            return;
        }
        this.g.setJoinLines(true);
        this.g.draw(graphics, n, n2, n + n3, n2);
        this.g.draw(graphics, n + n3, n2, n + n3, n2 + n4);
        this.g.draw(graphics, n + n3, n2 + n4, n, n2 + n4);
        this.g.draw(graphics, n, n2 + n4, n, n2);
        this.g.draw(graphics, n, n2, n + n3, n2);
        this.g.setJoinLines(false);
    }
    
    public void drawBar(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2) {
        this.drawBar(graphics, n, n2, n3, n4, n5, b, b2, null);
    }
    
    public void drawBar(final Graphics graphics, int n, int n2, int n3, int n4, final int n5, final boolean b, final boolean b2, final NFPatternFill nfPatternFill) {
        if (n4 < 0) {
            n2 += n4;
            n4 = -n4;
        }
        if (n3 < 0) {
            n += n3;
            n3 = -n3;
        }
        final Color color = graphics.getColor();
        if (n5 < 1) {
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n, n2, n3, n4, this.b, nfPatternFill);
            }
            else {
                graphics.fillRect(n, n2, n3, n4);
            }
        }
        else if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
            NF12GraphicsUtil.patternFillRect(graphics, n, n2, n3 + 1, n4 + 1, this.b, nfPatternFill);
        }
        else {
            graphics.fillRect(n, n2, n3 + 1, n4 + 1);
        }
        this.l.xpoints[0] = n;
        this.l.ypoints[0] = n2;
        this.l.xpoints[1] = n;
        this.l.ypoints[1] = n2 + n4;
        this.l.xpoints[2] = n + n3;
        this.l.ypoints[2] = n2 + n4;
        if (n5 < 1) {
            if (this.c > 0) {
                this.a(graphics, n, n2, n3, n4);
            }
            this.l.xpoints[3] = n + n3;
            this.l.ypoints[3] = n2;
            this.l.xpoints[4] = n;
            this.l.ypoints[4] = n2;
            this.l.npoints = 5;
            return;
        }
        if (b) {
            this.j.xpoints[0] = n;
            this.j.ypoints[0] = n2;
            this.j.xpoints[1] = n + n5;
            this.j.ypoints[1] = n2 - n5;
            this.j.xpoints[2] = n + n3 + n5;
            this.j.ypoints[2] = n2 - n5;
            this.j.xpoints[3] = n + n3;
            this.j.ypoints[3] = n2;
            this.j.xpoints[4] = n;
            this.j.ypoints[4] = n2;
        }
        if (b2) {
            this.i.xpoints[0] = n + n3;
            this.i.ypoints[0] = n2;
            this.i.xpoints[1] = n + n3 + n5;
            this.i.ypoints[1] = n2 - n5;
            this.i.xpoints[2] = n + n3 + n5;
            this.i.ypoints[2] = n2 + (n4 - n5);
            this.i.xpoints[3] = n + n3;
            this.i.ypoints[3] = n2 + n4;
            this.i.xpoints[4] = n + n3;
            this.i.ypoints[4] = n2;
        }
        if (b2) {
            graphics.setColor(NFColor.darker(color));
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.i, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(this.i);
            }
        }
        if (b) {
            graphics.setColor(NFColor.brighter(color));
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.j, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(this.j);
            }
        }
        if (b2 && b) {
            if (this.c > 0) {
                this.h.xpoints[0] = n + n3 + n5;
                this.h.ypoints[0] = n2 - n5;
                this.h.xpoints[1] = n + n5;
                this.h.ypoints[1] = n2 - n5;
                this.h.xpoints[2] = n;
                this.h.ypoints[2] = n2;
                this.h.xpoints[3] = n + n3;
                this.h.ypoints[3] = n2;
                this.h.xpoints[4] = n + n3;
                this.h.ypoints[4] = n2 + n4;
                this.h.xpoints[5] = n + n3 + n5;
                this.h.ypoints[5] = n2 + n4 - n5;
                this.h.xpoints[6] = n + n3 + n5;
                this.h.ypoints[6] = n2 - n5;
                this.h.xpoints[7] = n + n3;
                this.h.ypoints[7] = n2;
                this.h.xpoints[8] = n + n3;
                this.h.ypoints[8] = n2 + n4;
                this.h.xpoints[9] = n;
                this.h.ypoints[9] = n2 + n4;
                this.h.xpoints[10] = n;
                this.h.ypoints[10] = n2;
                this.a(graphics, this.h);
            }
            this.l.xpoints[3] = n + n3 + n5;
            this.l.ypoints[3] = n2 + n4 - n5;
            this.l.xpoints[4] = n + n3 + n5;
            this.l.ypoints[4] = n2 - n5;
            this.l.xpoints[5] = n + n5;
            this.l.ypoints[5] = n2 - n5;
            this.l.xpoints[6] = n;
            this.l.ypoints[6] = n2;
            this.l.npoints = 7;
        }
        else {
            if (b && this.c > 0) {
                this.a(graphics, this.j);
            }
            if (b2 && this.c > 0) {
                this.a(graphics, this.i);
            }
            this.l.xpoints[3] = n + n3;
            this.l.ypoints[3] = n2;
            this.l.xpoints[4] = n;
            this.l.ypoints[4] = n2;
            this.l.npoints = 5;
        }
    }
    
    public void drawAngledBar(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2, final int n6) {
        this.drawAngledBar(graphics, n, n2, n3, n4, n5, b, b2, n6, null);
    }
    
    public void drawAngledBar(final Graphics graphics, int n, int n2, int n3, int n4, int n5, final boolean b, final boolean b2, final int n6, final NFPatternFill nfPatternFill) {
        if (n5 < 1) {
            this.drawBar(graphics, n, n2, n3, n4, n5, b, b2, nfPatternFill);
            return;
        }
        if (n4 < 0) {
            n2 += n4;
            n4 = -n4;
        }
        if (n3 < 0) {
            n += n3;
            n3 = -n3;
        }
        if (this.type == 11) {
            n5 /= 2;
        }
        if (n6 == 1) {
            final int n7 = n;
            final int n8 = n7 + n3 / 2;
            final int n9 = n7 + n3;
            final int n10 = n2;
            final int n11 = n10 - n5;
            final int n12 = n10 + n4;
            final int n13 = n12 - n5;
            this.k.xpoints[0] = n7;
            this.k.ypoints[0] = n13;
            this.k.xpoints[1] = n8;
            this.k.ypoints[1] = n12;
            this.k.xpoints[2] = n8;
            this.k.ypoints[2] = n10;
            this.k.xpoints[3] = n7;
            this.k.ypoints[3] = n11;
            this.k.xpoints[4] = n7;
            this.k.ypoints[4] = n13;
            this.k.npoints = 5;
            if (b) {
                this.j.xpoints[0] = n7;
                this.j.ypoints[0] = n11;
                this.j.xpoints[1] = n8;
                this.j.ypoints[1] = n10;
                this.j.xpoints[2] = n9;
                this.j.ypoints[2] = n11;
                if (this.type == 10) {
                    this.j.xpoints[3] = n7;
                    this.j.ypoints[3] = n11;
                    this.j.npoints = 4;
                }
                else {
                    this.j.xpoints[3] = n8;
                    this.j.ypoints[3] = n11 - n5;
                    this.j.xpoints[4] = n7;
                    this.j.ypoints[4] = n11;
                    this.j.npoints = 5;
                }
            }
            if (b2) {
                this.i.xpoints[0] = n8;
                this.i.ypoints[0] = n12;
                this.i.xpoints[1] = n9;
                this.i.ypoints[1] = n13;
                this.i.xpoints[2] = n9;
                this.i.ypoints[2] = n11;
                this.i.xpoints[3] = n8;
                this.i.ypoints[3] = n10;
                this.i.xpoints[4] = n8;
                this.i.ypoints[4] = n12;
            }
            this.l.xpoints[0] = n7;
            this.l.ypoints[0] = n13;
            this.l.xpoints[1] = n8;
            this.l.ypoints[1] = n12;
            this.l.xpoints[2] = n9;
            this.l.ypoints[2] = n13;
            this.l.xpoints[3] = n9;
            this.l.ypoints[3] = n11;
            if (this.type == 10) {
                this.l.xpoints[4] = n7;
                this.l.ypoints[4] = n11;
                this.l.xpoints[5] = n7;
                this.l.ypoints[5] = n13;
                this.l.npoints = 6;
            }
            else {
                this.l.xpoints[4] = n8;
                this.l.ypoints[4] = n11 - n5;
                this.l.xpoints[5] = n7;
                this.l.ypoints[5] = n11;
                this.l.xpoints[6] = n7;
                this.l.ypoints[6] = n13;
                this.l.npoints = 7;
            }
        }
        else {
            final int n14 = n;
            final int n15 = n14 + n5;
            final int n16 = n14 + n3;
            final int n17 = n16 + n5;
            final int n18 = n2;
            final int n19 = n18 + n4 / 2;
            final int n20 = n18 + n4;
            this.k.xpoints[0] = n15;
            this.k.ypoints[0] = n18;
            this.k.xpoints[1] = n17;
            this.k.ypoints[1] = n18;
            this.k.xpoints[2] = n16;
            this.k.ypoints[2] = n19;
            this.k.xpoints[3] = n14;
            this.k.ypoints[3] = n19;
            this.k.xpoints[4] = n15;
            this.k.ypoints[4] = n18;
            this.k.npoints = 5;
            this.i.xpoints[0] = n17;
            this.i.ypoints[0] = n18;
            this.i.xpoints[1] = n16;
            this.i.ypoints[1] = n19;
            this.i.xpoints[2] = n17;
            this.i.ypoints[2] = n20;
            if (this.type == 10) {
                this.i.xpoints[3] = n17;
                this.i.ypoints[3] = n18;
                this.i.npoints = 4;
            }
            else {
                this.i.xpoints[3] = n17 + n5;
                this.i.ypoints[3] = n19;
                this.i.xpoints[4] = n17;
                this.i.ypoints[4] = n18;
                this.i.npoints = 5;
            }
            this.j.xpoints[0] = n14;
            this.j.ypoints[0] = n19;
            this.j.xpoints[1] = n16;
            this.j.ypoints[1] = n19;
            this.j.xpoints[2] = n17;
            this.j.ypoints[2] = n20;
            this.j.xpoints[3] = n15;
            this.j.ypoints[3] = n20;
            this.j.xpoints[4] = n14;
            this.j.ypoints[4] = n19;
            this.l.xpoints[0] = n17;
            this.l.ypoints[0] = n18;
            this.l.xpoints[1] = n15;
            this.l.ypoints[1] = n18;
            this.l.xpoints[2] = n14;
            this.l.ypoints[2] = n19;
            this.l.xpoints[3] = n15;
            this.l.ypoints[3] = n20;
            this.l.xpoints[4] = n17;
            this.l.ypoints[4] = n20;
            if (this.type == 10) {
                this.l.xpoints[5] = n17;
                this.l.ypoints[5] = n18;
                this.l.npoints = 6;
            }
            else {
                this.l.xpoints[5] = n17 + n5;
                this.l.ypoints[5] = n19;
                this.l.xpoints[6] = n17;
                this.l.ypoints[6] = n18;
                this.l.npoints = 7;
            }
        }
        final Color color = graphics.getColor();
        graphics.setColor(color);
        if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
            NF12GraphicsUtil.patternFillPolygon(graphics, this.k, this.b, nfPatternFill);
        }
        else {
            graphics.fillPolygon(this.k);
        }
        if (this.c > 0) {
            this.k.xpoints[this.k.npoints] = this.k.xpoints[1];
            this.k.ypoints[this.k.npoints] = this.k.ypoints[1];
            final Polygon k = this.k;
            ++k.npoints;
            this.a(graphics, this.k);
            final Polygon i = this.k;
            --i.npoints;
        }
        if (b2) {
            graphics.setColor(NFColor.darker(color));
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.i, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(this.i);
            }
        }
        if (b) {
            graphics.setColor(NFColor.brighter(color));
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.j, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(this.j);
            }
        }
        if (b && this.c > 0) {
            this.a(graphics, this.j);
        }
        if (b2 && this.c > 0) {
            this.a(graphics, this.i);
        }
    }
    
    public void drawCylinder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2, final int n6) {
        this.drawCylinder(graphics, n, n2, n3, n4, n5, b, b2, n6, null);
    }
    
    public void drawCylinder(final Graphics graphics, int n, int n2, int n3, int n4, final int n5, final boolean b, final boolean b2, final int n6, final NFPatternFill nfPatternFill) {
        if (n5 < 1) {
            this.drawBar(graphics, n, n2, n3, n4, n5, b, b2, nfPatternFill);
            return;
        }
        if (n4 < 0) {
            n2 += n4;
            n4 = -n4;
        }
        if (n3 < 0) {
            n += n3;
            n3 = -n3;
        }
        final Color color = graphics.getColor();
        if (n6 == 1) {
            final int n7 = n + n3;
            n2 -= (int)(n5 * 1.0);
            final int n8 = n2 + n5 / 2;
            final int n9 = n8 + n4;
            this.k.xpoints[0] = n;
            this.k.ypoints[0] = n8;
            this.k.xpoints[1] = n7;
            this.k.ypoints[1] = n8;
            this.k.xpoints[2] = n7;
            this.k.ypoints[2] = n9;
            this.k.xpoints[3] = n;
            this.k.ypoints[3] = n9;
            this.k.xpoints[4] = n;
            this.k.ypoints[4] = n8;
            this.k.npoints = 5;
            graphics.setColor(color);
            final Polygon polygon = new Polygon();
            generateArc(polygon, new Rectangle(n, n9 - n5 / 2, n3, n5), 0.0, 360.0, 0);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, polygon, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(polygon);
            }
            graphics.setColor(Color.black);
            graphics.drawPolygon(polygon);
            graphics.setColor(color);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.k, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(this.k);
            }
            graphics.setColor(this.b);
            graphics.drawLine(n, n8, n, n9);
            graphics.drawLine(n7, n8, n7, n9);
            if (b) {
                graphics.setColor(NFColor.darker(color));
                final Polygon polygon2 = new Polygon();
                generateArc(polygon2, new Rectangle(n, n2, n3, n5), 0.0, 360.0, 0);
                if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                    NF12GraphicsUtil.patternFillPolygon(graphics, polygon2, this.b, nfPatternFill);
                }
                else {
                    graphics.fillPolygon(polygon2);
                }
                graphics.setColor(this.b);
                graphics.drawPolygon(polygon2);
            }
            this.l.xpoints[0] = n;
            this.l.ypoints[0] = n2;
            this.l.xpoints[1] = n7;
            this.l.ypoints[1] = n2;
            this.l.xpoints[2] = n7;
            this.l.ypoints[2] = n9 + n5 / 2;
            this.l.xpoints[3] = n;
            this.l.ypoints[3] = n9 + n5 / 2;
            this.l.xpoints[4] = n;
            this.l.ypoints[4] = n2;
            this.l.npoints = 5;
        }
        else {
            final int n10 = n + n5 / 2;
            final int n11 = n2 + n4;
            final int n12 = n + n3;
            final int n13 = n10 + n3;
            this.k.xpoints[0] = n10;
            this.k.ypoints[0] = n2;
            this.k.xpoints[1] = n13;
            this.k.ypoints[1] = n2;
            this.k.xpoints[2] = n13;
            this.k.ypoints[2] = n11;
            this.k.xpoints[3] = n10;
            this.k.ypoints[3] = n11;
            this.k.xpoints[4] = n10;
            this.k.ypoints[4] = n2;
            this.k.npoints = 5;
            graphics.setColor(color);
            final Polygon polygon3 = new Polygon();
            generateArc(polygon3, new Rectangle(n, n2, n5, n4), 0.0, 360.0, 0);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, polygon3, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(polygon3);
            }
            graphics.setColor(this.b);
            graphics.drawPolygon(polygon3);
            graphics.setColor(color);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.k, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(this.k);
            }
            graphics.setColor(this.b);
            graphics.drawLine(n10, n2, n13, n2);
            graphics.drawLine(n10, n11, n13, n11);
            if (b2) {
                graphics.setColor(NFColor.darker(color));
                final Polygon polygon4 = new Polygon();
                generateArc(polygon4, new Rectangle(n12, n2, n5, n4), 0.0, 360.0, 0);
                if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                    NF12GraphicsUtil.patternFillPolygon(graphics, polygon4, this.b, nfPatternFill);
                }
                else {
                    graphics.fillPolygon(polygon4);
                }
                graphics.setColor(this.b);
                graphics.drawPolygon(polygon4);
            }
            this.l.xpoints[0] = n;
            this.l.ypoints[0] = n2;
            this.l.xpoints[1] = n13 + n5 / 2;
            this.l.ypoints[1] = n2;
            this.l.xpoints[2] = n13 + n5 / 2;
            this.l.ypoints[2] = n11;
            this.l.xpoints[3] = n;
            this.l.ypoints[3] = n11;
            this.l.xpoints[4] = n;
            this.l.ypoints[4] = n2;
            this.l.npoints = 5;
        }
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        this.draw(graphics, n, n2, null);
    }
    
    public void draw(final Graphics f, final int n, final int n2, final NFPatternFill nfPatternFill) {
        this.a();
        this.f = f;
        int size = this.size;
        int c = this.c;
        if (this.e > 0.0) {
            if (size > 0) {
                size *= (int)this.e;
                if (size < 1) {
                    size = 1;
                }
            }
            if (c > 0) {
                c *= (int)this.e;
                if (c < 1) {
                    c = 1;
                }
            }
        }
        final int n3 = n - size / 2;
        final int n4 = n2 - size / 2;
        switch (this.type) {
            case 0: {
                break;
            }
            case 15: {
                this.a(f, n, n2);
                break;
            }
            case 1: {
                this.a(size, this.style, n3, n4, c, nfPatternFill);
                break;
            }
            case 2: {
                this.b(size, this.style, n3, n4, c, nfPatternFill);
                break;
            }
            case 3:
            case 4:
            case 5:
            case 6: {
                this.a(this.type, size, this.style, n3, n4, c, nfPatternFill);
                break;
            }
            default: {
                this.draw(f, n, n2, 20, nfPatternFill);
                break;
            }
        }
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3) {
        this.draw(graphics, n, n2, n3, null);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final NFPatternFill nfPatternFill) {
        this.a();
        int size = this.size;
        int c = this.c;
        int n4 = n3;
        if (this.e > 0.0) {
            if (size > 0) {
                size *= (int)this.e;
                if (size < 1) {
                    size = 1;
                }
            }
            if (c > 0) {
                c *= (int)this.e;
                if (c < 1) {
                    c = 1;
                }
            }
            if (n4 > 0) {
                n4 *= (int)this.e;
                if (n4 < 1) {
                    n4 = 1;
                }
            }
        }
        switch (this.type) {
            case 0: {
                break;
            }
            case 15: {
                this.a(graphics, n, n2);
                break;
            }
            case 8: {
                this.b(graphics, size, this.style, n, n2, n4, c, nfPatternFill);
                break;
            }
            case 7: {
                this.a(graphics, size, this.style, n, n2, n4, c, nfPatternFill);
                break;
            }
            default: {
                this.draw(graphics, n - 5, n2 - 5, 10, 10, 5, 1, nfPatternFill);
                break;
            }
        }
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.draw(graphics, n, n2, n3, n4, n5, n6, null);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final NFPatternFill nfPatternFill) {
        this.draw(graphics, n, n2, n3, n4, n5, n6, true, true, nfPatternFill);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2) {
        this.draw(graphics, n, n2, n3, n4, n5, n6, b, b2, null);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final NFPatternFill nfPatternFill) {
        this.a();
        switch (this.type) {
            case 0: {
                break;
            }
            case 15: {
                this.a(graphics, n, n2);
                break;
            }
            case 9: {
                this.drawBar(graphics, n, n2, n3, n4, n5, b, b2, nfPatternFill);
                break;
            }
            case 10:
            case 11: {
                this.drawAngledBar(graphics, n, n2, n3, n4, n5, b, b2, n6, nfPatternFill);
                break;
            }
            case 12: {
                this.drawCylinder(graphics, n, n2, n3, n4, n5, b, b2, n6, nfPatternFill);
                break;
            }
            case 13:
            case 14: {
                this.draw(graphics, n, n2, n3, n3, new Color[] { graphics.getColor() }, new int[] { 25 }, 1, 3, nfPatternFill);
                break;
            }
            default: {
                this.draw(graphics, n, n2, nfPatternFill);
                break;
            }
        }
    }
    
    public static void generateArc(final Polygon polygon, final Rectangle rectangle, double n, final double n2, final int n3) {
        final int n4 = rectangle.width / 2;
        final int n5 = rectangle.height / 2;
        final int n6 = rectangle.x + n4;
        final int n7 = rectangle.y + n5;
        final double n8 = n + n2;
        final double n9 = 8.0;
        do {
            final double n10 = 3.141592653589793 * (n / 180.0);
            polygon.addPoint(n6 + (int)NFUtil.rint(Math.cos(n10) * n4), n7 - (int)NFUtil.rint(Math.sin(n10) * n5) + n3);
            n += n9;
        } while (n < n8);
        final double n11 = 3.141592653589793 * (n8 / 180.0);
        polygon.addPoint(n6 + (int)NFUtil.rint(Math.cos(n11) * n4), n7 - (int)NFUtil.rint(Math.sin(n11) * n5) + n3);
    }
    
    public static Polygon createArcShadow(final Rectangle rectangle, final double n, final double n2, final int n3, final boolean b) {
        final Polygon polygon = new Polygon();
        generateArc(polygon, rectangle, n, n2, 0);
        for (int i = polygon.npoints - 1; i >= 0; --i) {
            if (b) {
                polygon.addPoint(polygon.xpoints[i], polygon.ypoints[i] + n3);
            }
            else {
                polygon.addPoint(polygon.xpoints[i] + n3, polygon.ypoints[i]);
            }
        }
        polygon.addPoint(polygon.xpoints[0], polygon.ypoints[0]);
        return polygon;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color[] array, final int[] array2, final int n5, final int n6) {
        this.draw(graphics, n, n2, n3, n4, array, array2, n5, n6, null);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color[] array, final int[] array2, final int n5, final int n6, final NFPatternFill nfPatternFill) {
        if (this.type != 14 && this.type != 13) {
            this.draw(graphics, n, n2, nfPatternFill);
            return;
        }
        final int length = array.length;
        if (length == 0) {
            return;
        }
        graphics.setColor(array[0]);
        final Polygon polygon = new Polygon();
        generateArc(polygon, new Rectangle(n, n2, n3, n4), 0.0, 360.0, 0);
        if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
            NF12GraphicsUtil.patternFillPolygon(graphics, polygon, this.b, nfPatternFill);
        }
        else {
            graphics.fillPolygon(polygon);
        }
        graphics.drawPolygon(polygon);
        int n7;
        if (this.type == 13) {
            n7 = 270;
        }
        else {
            n7 = 180;
        }
        graphics.setColor(NFColor.darker(array[0]));
        final Polygon arcShadow = createArcShadow(new Rectangle(n, n2, n3, n4), n7, 180.0, n6, this.type == 14);
        if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
            NF12GraphicsUtil.patternFillPolygon(graphics, arcShadow, this.b, nfPatternFill);
        }
        else {
            graphics.fillPolygon(arcShadow);
        }
        graphics.drawPolygon(arcShadow);
        for (int i = 0; i < n5; ++i) {
            final int n8 = (i + 1 < length) ? (i + 1) : (length - 1);
            final int n9 = 360 * array2[i] / 100;
            int n10;
            if (this.type == 13) {
                n10 = 360 - n9 / 2;
            }
            else {
                n10 = 270 - n9 / 2;
            }
            graphics.setColor(NFColor.darker(array[n8]));
            final Polygon arcShadow2 = createArcShadow(new Rectangle(n, n2, n3, n4), n10, n9, n6, this.type == 14);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, arcShadow2, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(arcShadow2);
            }
            graphics.drawPolygon(arcShadow2);
            graphics.setColor(array[n8]);
            final Polygon polygon2 = new Polygon();
            polygon2.addPoint(n + n3 / 2, n2 + n4 / 2);
            generateArc(polygon2, new Rectangle(n, n2, n3, n4), n10, n9, 0);
            polygon2.addPoint(n + n3 / 2, n2 + n4 / 2);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, polygon2, this.b, nfPatternFill);
            }
            else {
                graphics.fillPolygon(polygon2);
            }
            graphics.drawPolygon(polygon2);
        }
    }
    
    public static NFGraphSymbol loadParams(final NFParam nfParam, final Object o) {
        return loadParams(nfParam, o, 0);
    }
    
    public static NFGraphSymbol loadParams(final NFParam nfParam, final Object o, final int n) {
        final Vector vector = (Vector)o;
        if (vector == null || vector.size() <= n) {
            return null;
        }
        final int intValue = vector.elementAt(n + 0).intValue();
        if (intValue == 0) {
            return null;
        }
        final NFGraphSymbol nfGraphSymbol = new NFGraphSymbol();
        nfGraphSymbol.type = intValue;
        nfGraphSymbol.size = vector.elementAt(n + 1).intValue();
        nfGraphSymbol.style = vector.elementAt(n + 2).intValue();
        return nfGraphSymbol;
    }
    
    public static NFGraphSymbol createCopy(final NFGraphSymbol nfGraphSymbol) {
        if (nfGraphSymbol == null) {
            return null;
        }
        final NFGraphSymbol nfGraphSymbol2 = new NFGraphSymbol();
        nfGraphSymbol2.type = nfGraphSymbol.type;
        nfGraphSymbol2.size = nfGraphSymbol.size;
        nfGraphSymbol2.style = nfGraphSymbol.style;
        nfGraphSymbol2.a = nfGraphSymbol.a;
        nfGraphSymbol2.b = nfGraphSymbol.b;
        nfGraphSymbol2.c = nfGraphSymbol.c;
        nfGraphSymbol2.d = nfGraphSymbol.d;
        nfGraphSymbol2.e = nfGraphSymbol.e;
        nfGraphSymbol2.f = nfGraphSymbol.f;
        nfGraphSymbol2.g = NFLine.createCopy(nfGraphSymbol.g);
        return nfGraphSymbol2;
    }
}
