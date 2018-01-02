import java.util.StringTokenizer;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedCharacterIterator;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class s
{
    public static final int a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    public static final int f;
    public static final int g;
    public static final int h;
    public static final int i;
    public static final int j;
    public static final int k;
    public static final int l = 1;
    public static final int m = 3;
    public static final int n = 4;
    public static final int o = 5;
    public static final int p = 8;
    public static final int q = 24;
    public static final int r = 26;
    private int s;
    private int t;
    private String u;
    private String v;
    private float[] w;
    private float[] x;
    private Rectangle y;
    private Point z;
    private float[] A;
    private float[] B;
    private Dimension C;
    private float[] D;
    private float E;
    private n F;
    private n G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    private Point L;
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("HotSpot:");
        sb.append(" mType='" + this.s + "'");
        sb.append(" mFlags='" + this.t + "'");
        sb.append(" mURL='" + this.u + "'");
        sb.append(" mPopupText='" + this.v + "'");
        sb.append(" paintHotspot='" + this.H + "'");
        sb.append(" paintTarget='" + this.I + "'");
        sb.append(" paintPopupText='" + this.J + "'");
        sb.append(" activateHotspot='" + this.K + "'");
        return sb.toString();
    }
    
    private static int a(final String s) {
        final byte[] array = { 32, 32, 32, 32 };
        s.getBytes(0, 4, array, 0);
        return (array[0] << 24 & 0xFF000000) | (array[1] << 16 & 0xFF0000) | (array[2] << 8 & 0xFF00) | (array[3] & 0xFF);
    }
    
    s() {
        this.D = new float[] { 0.0f, 0.0f };
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = null;
        this.G = new n();
        this.F = new n();
    }
    
    public Color a() {
        final Color red = Color.red;
        Color color = null;
        switch ((this.t & s.j) >> s.k) {
            case 0: {
                color = new Color(255, 0, 0);
                break;
            }
            case 1: {
                color = new Color(128, 0, 128);
                break;
            }
            case 2: {
                color = new Color(128, 128, 0);
                break;
            }
            case 3: {
                color = new Color(255, 0, 255);
                break;
            }
            case 4: {
                color = new Color(0, 128, 0);
                break;
            }
            case 5: {
                color = new Color(0, 0, 255);
                break;
            }
            case 6: {
                color = new Color(0, 0, 0);
                break;
            }
            case 7: {
                color = new Color(255, 255, 255, 0);
                break;
            }
            default: {
                color = new Color(255, 0, 0);
                break;
            }
        }
        return color;
    }
    
    public int b() {
        return this.s;
    }
    
    public int c() {
        return this.t;
    }
    
    public String d() {
        return this.u;
    }
    
    public String e() {
        return this.v;
    }
    
    public void a(final float e) {
        this.E = e;
        this.G.a(e);
        this.F.a(e);
    }
    
    public float[] f() {
        return this.x;
    }
    
    public void a(final float[] array, final float[] array2, final Dimension c) {
        this.A = array.clone();
        this.B = array2.clone();
        this.C = c;
        this.D[0] = c.width / 2 - 0.5f;
        this.D[1] = c.height / 2 - 0.5f;
        final float n = 0.64f * (float)Math.sqrt(this.C.width * this.C.width + this.C.height * this.C.height) / this.E;
        final float[] a = this.A;
        final int n2 = 3;
        a[n2] *= n;
        this.F.b(this.B);
        this.F.a(this.A);
    }
    
    public boolean a(final Point point) {
        final float[] array2;
        final float[] array = array2 = new float[] { point.x, point.y };
        final int n = 0;
        array2[n] -= this.D[0];
        final float[] array3 = array;
        final int n2 = 1;
        array3[n2] -= this.D[1];
        final float[] array4 = { 0.0f, 0.0f };
        final float a = this.F.a(array, array4);
        final float[] array5 = { 0.0f, 0.0f };
        return this.G.a(a, array4, array5) && this.y.contains(new Point((int)(Object)new Float(array5[0]), (int)(Object)new Float(array5[1])));
    }
    
    public Rectangle g() {
        final float[][] array = new float[4][2];
        array[0][0] = new Integer(this.y.x);
        array[0][1] = new Integer(this.y.y);
        array[1][0] = new Integer(this.y.x + this.y.width);
        array[1][1] = new Integer(this.y.y);
        array[2][0] = new Integer(this.y.x + this.y.width);
        array[2][1] = new Integer(this.y.y + this.y.height);
        array[3][0] = new Integer(this.y.x);
        array[3][1] = new Integer(this.y.y + this.y.height);
        final float[] array2 = { 10000.0f, 10000.0f, 10000.0f, 10000.0f };
        final float[] array3 = { 0.0f, 0.0f };
        final float[] array4 = { 0.0f, 0.0f };
        for (int i = 0; i < 4; ++i) {
            if (this.a(array[i], array[(i + 1) % 4], array3)) {
                if (this.a(array[(i + 1) % 4], array[i], array4)) {
                    array2[0] = Math.min(array4[0], Math.min(array3[0], array2[0]));
                    array2[1] = Math.min(array4[1], Math.min(array3[1], array2[1]));
                    array2[2] = Math.max(array4[0], Math.max(array3[0], array2[2]));
                    array2[3] = Math.max(array4[1], Math.max(array3[1], array2[3]));
                }
            }
        }
        final float[] array5 = array2;
        final int n = 0;
        array5[n] += this.D[0] - 2.0f;
        final float[] array6 = array2;
        final int n2 = 1;
        array6[n2] += this.D[1] - 2.0f;
        final float[] array7 = array2;
        final int n3 = 2;
        array7[n3] += this.D[0] + 2.0f;
        final float[] array8 = array2;
        final int n4 = 3;
        array8[n4] += this.D[1] + 2.0f;
        final Rectangle rectangle = new Rectangle();
        rectangle.x = Math.round(array2[0]);
        rectangle.y = Math.round(array2[1]);
        rectangle.width = Math.round(array2[2]) - rectangle.x;
        rectangle.height = Math.round(array2[3]) - rectangle.y;
        return rectangle;
    }
    
    public Point[] h() {
        final float[][] array = new float[4][2];
        array[0][0] = new Integer(this.y.x);
        array[0][1] = new Integer(this.y.y);
        array[1][0] = new Integer(this.y.x + this.y.width);
        array[1][1] = new Integer(this.y.y);
        array[2][0] = new Integer(this.y.x + this.y.width);
        array[2][1] = new Integer(this.y.y + this.y.height);
        array[3][0] = new Integer(this.y.x);
        array[3][1] = new Integer(this.y.y + this.y.height);
        final Point[] array2 = new Point[8];
        for (int i = 0; i < 8; ++i) {
            array2[i] = new Point();
            array2[i].x = -1;
            array2[i].y = -1;
        }
        final float[] array3 = { 0.0f, 0.0f };
        final float[] array4 = { 0.0f, 0.0f };
        for (int j = 0; j < 4; ++j) {
            if (this.a(array[j], array[(j + 1) % 4], array3)) {
                if (this.a(array[(j + 1) % 4], array[j], array4)) {
                    array2[j * 2].x = Math.round(array3[0] + this.D[0]);
                    array2[j * 2].y = Math.round(array3[1] + this.D[1]);
                    array2[j * 2 + 1].x = Math.round(array4[0] + this.D[0]);
                    array2[j * 2 + 1].y = Math.round(array4[1] + this.D[1]);
                }
            }
        }
        return array2;
    }
    
    public Point i() {
        final Point point = new Point(-1, -1);
        final float[] array = { 0.0f, 0.0f };
        final float[] array2 = { 0.0f, 0.0f };
        array2[0] = new Integer(this.z.x);
        array2[1] = new Integer(this.z.y);
        if (!this.F.a(this.G.a(array2, array), array, array2)) {
            return point;
        }
        point.x = Math.round(array2[0] + this.D[0]);
        point.y = Math.round(array2[1] + this.D[1]);
        return point;
    }
    
    private boolean a(final float[] array, final float[] array2, final float[] array3) {
        final float n = array2[0] - array[0];
        final float n2 = array2[1] - array[1];
        float n3 = Math.max(Math.abs(n), Math.abs(n2)) / 20.0f + 0.5f;
        if (n3 == 0.0f) {
            n3 = 1.0f;
        }
        final float n4 = n / n3;
        final float n5 = n2 / n3;
        final float[] array4 = { array[0], array[1] };
        final float[] array5 = new float[2];
        for (int n6 = 0; n6 < n3; ++n6) {
            if (this.F.a(this.G.a(array4, array5), array5, array3)) {
                return true;
            }
            final float[] array6 = array4;
            final int n7 = 0;
            array6[n7] += n4;
            final float[] array7 = array4;
            final int n8 = 1;
            array7[n8] += n5;
        }
        return false;
    }
    
    public void a(final x x) {
        final x b = x.b(s.a);
        if (b != null) {
            this.s = b.g();
        }
        final x b2 = x.b(s.b);
        if (b2 != null) {
            this.t = b2.g();
        }
        final x b3 = x.b(s.c);
        if (b3 != null) {
            this.u = b3.k();
        }
        final x b4 = x.b(s.d);
        if (b4 != null) {
            this.v = b4.k();
        }
        final x b5 = x.b(s.e);
        if (b5 != null) {
            this.w = new float[4];
            final byte[] l = b5.l();
            this.w[0] = g.e(this.a(l, 0, 4));
            this.w[1] = -g.e(this.a(l, 4, 4));
            this.w[2] = g.e(this.a(l, 8, 4));
            this.w[3] = g.e(this.a(l, 12, 4));
        }
        final x b6 = x.b(s.f);
        if (b6 != null) {
            this.x = new float[4];
            final byte[] i = b6.l();
            this.x[0] = g.e(this.a(i, 0, 4));
            this.x[1] = -g.e(this.a(i, 4, 4));
            this.x[2] = g.e(this.a(i, 8, 4));
            this.x[3] = g.e(this.a(i, 12, 4));
        }
        final x b7 = x.b(s.g);
        if (b7 != null) {
            this.y = new Rectangle();
            final byte[] j = b7.l();
            this.y.x = g.c(this.a(j, 0, 4));
            this.y.y = g.c(this.a(j, 4, 4));
            this.y.width = g.c(this.a(j, 8, 4)) - this.y.x;
            this.y.height = g.c(this.a(j, 12, 4)) - this.y.y;
        }
        final x b8 = x.b(s.h);
        if (b8 != null) {
            this.z = new Point();
            final byte[] k = b8.l();
            this.z.x = g.c(this.a(k, 0, 4));
            this.z.y = g.c(this.a(k, 4, 4));
        }
        this.G.b(this.x);
        this.G.a(this.w);
    }
    
    private byte[] a(final byte[] array, final int n, final int n2) {
        if (n + n2 > array.length) {
            return null;
        }
        final byte[] array2 = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = array[n + i];
        }
        return array2;
    }
    
    boolean a(final Graphics graphics, final bk bk) {
        if (!bk.b().G) {
            return false;
        }
        try {
            if (this.H) {
                graphics.setColor(bk.b().K);
                final Point[] h = this.h();
                if (h[0].x != -1 && h[0].y != -1 && h[1].x != -1 && h[1].y != -1) {
                    graphics.drawLine(h[0].x, h[0].y, h[1].x, h[1].y);
                }
                if (h[2].x != -1 && h[2].y != -1 && h[3].x != -1 && h[3].y != -1) {
                    graphics.drawLine(h[2].x, h[2].y, h[3].x, h[3].y);
                }
                if (h[4].x != -1 && h[4].y != -1 && h[5].x != -1 && h[5].y != -1) {
                    graphics.drawLine(h[4].x, h[4].y, h[5].x, h[5].y);
                }
                if (h[6].x != -1 && h[6].y != -1 && h[7].x != -1 && h[7].y != -1) {
                    graphics.drawLine(h[6].x, h[6].y, h[7].x, h[7].y);
                }
            }
            if (bk.b().H) {
                final Color a = this.a();
                final Point i = this.i();
                if (i.x != -1 && i.y != -1 && a.getAlpha() != 0) {
                    graphics.setColor(a);
                    graphics.fillOval(i.x - 8, i.y - 8, 16, 16);
                    graphics.setColor(Color.white);
                    graphics.fillOval(i.x + 2 - 8, i.y + 2 - 8, 12, 12);
                    graphics.setColor(a);
                    graphics.fillOval(i.x + 5 - 8, i.y + 5 - 8, 6, 6);
                }
            }
            if (bk.b().I & this.J) {
                final int intValue = new Integer(bk.b().getParameter("width"));
                final int intValue2 = new Integer(bk.b().getParameter("height"));
                final String trim = this.e().trim();
                if (trim.length() > 0) {
                    final Graphics2D graphics2D = (Graphics2D)graphics;
                    final FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
                    final int n = intValue - this.L.x;
                    final int x = this.L.x;
                    final int n2 = intValue2 - this.L.y;
                    final int y = this.L.y;
                    final Rectangle rectangle = new Rectangle();
                    if (x > n) {
                        rectangle.x = 3;
                        rectangle.width = x - 16;
                    }
                    else {
                        rectangle.x = this.L.x + 13;
                        rectangle.width = n - 16;
                    }
                    if (y > n2) {
                        rectangle.y = 3;
                        rectangle.height = y - 6;
                    }
                    else {
                        rectangle.y = this.L.y + 3;
                        rectangle.height = n2 - 6;
                    }
                    final int width = rectangle.width;
                    final AttributedString attributedString = new AttributedString(trim);
                    attributedString.addAttribute(TextAttribute.FONT, graphics.getFont());
                    final AttributedCharacterIterator iterator = attributedString.getIterator();
                    final LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(iterator, fontRenderContext);
                    final Point point = new Point(0, 0);
                    while (lineBreakMeasurer.getPosition() < trim.length()) {
                        final TextLayout nextLayout = lineBreakMeasurer.nextLayout(width);
                        point.x = Math.max(point.x, (int)nextLayout.getAdvance());
                        final int n3 = (int)(nextLayout.getAscent() + nextLayout.getDescent() + nextLayout.getLeading());
                        if (point.y + n3 >= rectangle.height) {
                            break;
                        }
                        final Point point2 = point;
                        point2.y += n3;
                    }
                    if (x > n) {
                        final Rectangle rectangle2 = rectangle;
                        rectangle2.x += rectangle.width - point.x;
                    }
                    if (y > n2) {
                        final Rectangle rectangle3 = rectangle;
                        rectangle3.y += rectangle.height - point.y;
                    }
                    rectangle.width = point.x;
                    rectangle.height = point.y + 1;
                    final Rectangle rectangle4 = new Rectangle(rectangle.x - 3, rectangle.y - 3, rectangle.width + 6, rectangle.height + 6);
                    final Rectangle rectangle5 = new Rectangle(rectangle.x - 2, rectangle.y - 2, rectangle.width + 4, rectangle.height + 4);
                    graphics.setColor(new Color(253, 249, 206));
                    graphics.fillRect(rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height);
                    graphics.setColor(Color.black);
                    graphics.drawRect(rectangle4.x, rectangle4.y, rectangle4.width, rectangle4.height);
                    final Point point3 = new Point(rectangle.x, rectangle.y);
                    final LineBreakMeasurer lineBreakMeasurer2 = new LineBreakMeasurer(iterator, fontRenderContext);
                    while (lineBreakMeasurer2.getPosition() < trim.length()) {
                        final TextLayout nextLayout2 = lineBreakMeasurer2.nextLayout(width);
                        if (point3.y + (int)(nextLayout2.getAscent() + nextLayout2.getDescent() + nextLayout2.getLeading()) - rectangle.y >= rectangle.height) {
                            break;
                        }
                        final Point point4 = point3;
                        point4.y += (int)nextLayout2.getAscent();
                        nextLayout2.draw(graphics2D, point3.x + (nextLayout2.isLeftToRight() ? 0.0f : (rectangle.width - nextLayout2.getAdvance())), point3.y);
                        final Point point5 = point3;
                        point5.y += (int)(nextLayout2.getDescent() + nextLayout2.getLeading());
                    }
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    void b(final Point point) {
        if (!this.H) {
            this.L = new Point(point.x, point.y);
        }
    }
    
    boolean a(final bk bk) {
        if (bk.b().G && this.K) {
            String trim = null;
            String trim2 = null;
            final StringTokenizer stringTokenizer = new StringTokenizer(this.d(), ";");
            if (stringTokenizer.hasMoreTokens()) {
                trim = stringTokenizer.nextToken().trim();
            }
            if (stringTokenizer.hasMoreTokens()) {
                trim2 = stringTokenizer.nextToken().trim();
            }
            switch (this.s) {
                case 1: {
                    bk.b().b(trim, true);
                    break;
                }
                case 3: {
                    bk.b().c(trim, true);
                    break;
                }
                case 4: {
                    bk.b().c(trim, true);
                    break;
                }
                case 5: {
                    break;
                }
                case 8: {
                    bk.b().a(trim, trim2, true);
                    break;
                }
                case 24: {
                    break;
                }
                case 26: {
                    if (trim.endsWith(".wav") || trim.endsWith(".mid")) {
                        bk.b().c(trim, true);
                        break;
                    }
                    bk.b().b(trim, trim2, true);
                    break;
                }
                default: {
                    bk.b().b(trim, trim2, true);
                    break;
                }
            }
            this.K = false;
            return true;
        }
        return false;
    }
    
    static {
        a = a("type");
        b = a("flgs");
        c = a("hurl");
        d = a("text");
        e = a("vwpt");
        f = a("rfvp");
        g = a("dbnd");
        h = a("dtgt");
        i = a("hspm");
        j = Integer.decode("0x000380");
        k = Integer.decode("0x000007");
    }
}
