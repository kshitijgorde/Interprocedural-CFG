// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import java.awt.Color;
import java.awt.Dimension;
import ji.filter.uv.fc;
import ji.util.i;
import java.awt.Point;
import java.awt.Rectangle;
import ji.util.d;
import ji.res.ab;

public class p
{
    private q a;
    private static ab b;
    private static String c;
    private static boolean d;
    private String e;
    
    public p(final String e) {
        this.a = null;
        this.e = null;
        this.e = e;
    }
    
    public final void a() {
        try {
            if (this.a != null) {
                this.a = null;
            }
            if (p.b != null) {
                p.b.f();
                p.b = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void k(final Object o) {
        try {
            if (this.a == null) {
                this.a = q.a(o, this.e);
            }
            if (p.b == null) {
                p.b = new ab(this.e, "Prefs");
                this.b();
            }
        }
        catch (Exception ex) {}
    }
    
    public final String b() {
        try {
            p.b.d();
            p.c = this.c();
        }
        catch (Exception ex) {}
        return p.c;
    }
    
    private final String c() throws Exception {
        String a = null;
        if (p.d) {
            final String m = this.a.m();
            if (m != null) {
                a = p.b.a(null, m.getBytes(), null);
            }
        }
        return a;
    }
    
    private final void d() throws Exception {
        if (p.d && p.b != null && this.a != null) {
            this.a.a(p.b.c());
        }
    }
    
    public final void a(final String s, final String s2, final Object o) {
        if (p.d) {
            this.k(o);
            try {
                p.b.a(s, s2);
                this.d();
            }
            catch (Exception ex) {}
        }
    }
    
    public final String b(final String s, final String s2, final Object o) {
        String s3 = s2;
        if (p.d) {
            this.k(o);
            try {
                final String a = p.b.a(s);
                if (!ji.util.d.by(a)) {
                    s3 = a;
                }
            }
            catch (Exception ex) {}
        }
        return s3;
    }
    
    public final void a(final String s, final int n, final Object o) {
        if (p.d) {
            this.k(o);
            try {
                final String a = p.b.a(s);
                final String concat = "".concat(String.valueOf(String.valueOf(n)));
                if (a != null && a.toLowerCase().equals(concat.toLowerCase())) {
                    return;
                }
                p.b.a(s, concat);
                this.d();
            }
            catch (Exception ex) {}
        }
    }
    
    public final int b(final String s, final int n, final Object o) {
        int c = n;
        if (p.d) {
            this.k(o);
            try {
                final String a = p.b.a(s);
                if (!ji.util.d.by(a)) {
                    c = ji.util.d.c(a, n);
                }
            }
            catch (Exception ex) {}
        }
        return c;
    }
    
    public final double a(final String s, final double n, final Object o) {
        double a = n;
        if (p.d) {
            this.k(o);
            try {
                final String a2 = p.b.a(s);
                if (!ji.util.d.by(a2)) {
                    a = ji.util.d.a(a2, n);
                }
            }
            catch (Exception ex) {}
        }
        return a;
    }
    
    public final void b(final String s, final double n, final Object o) {
        if (p.d) {
            this.k(o);
            try {
                final String a = p.b.a(s);
                final String concat = "".concat(String.valueOf(String.valueOf(n)));
                if (a != null && a.toLowerCase().equals(concat.toLowerCase())) {
                    return;
                }
                p.b.a(s, concat);
                this.d();
            }
            catch (Exception ex) {}
        }
    }
    
    public final void a(final String s, final boolean b, final Object o) {
        if (b) {
            this.a(s, 1, o);
        }
        else {
            this.a(s, 0, o);
        }
    }
    
    public final boolean b(final String s, final boolean b, final Object o) {
        int n = 0;
        if (b) {
            n = 1;
        }
        return this.b(s, n, o) == 1;
    }
    
    public final void c(final String s, final boolean b, final Object o) {
        if (b) {
            this.a(s, "yes", o);
        }
        else {
            this.a(s, "no", o);
        }
    }
    
    public final boolean d(final String s, final boolean b, final Object o) {
        final String b2 = this.b(s, b ? "yes" : "no", o);
        return b2.equalsIgnoreCase("yes") || b2.equalsIgnoreCase("true");
    }
    
    private final void a(final String s, final Rectangle rectangle, final Object o) {
        if (p.d && rectangle != null) {
            this.k(o);
            try {
                rectangle.x = Math.max(rectangle.x, 0);
                rectangle.y = Math.max(rectangle.y, 0);
                if (rectangle.width > 50 && rectangle.height > 50) {
                    p.b.a(String.valueOf(String.valueOf(s)).concat("X"), "".concat(String.valueOf(String.valueOf(rectangle.x))));
                    p.b.a(String.valueOf(String.valueOf(s)).concat("Y"), "".concat(String.valueOf(String.valueOf(rectangle.y))));
                    p.b.a(String.valueOf(String.valueOf(s)).concat("W"), "".concat(String.valueOf(String.valueOf(rectangle.width))));
                    p.b.a(String.valueOf(String.valueOf(s)).concat("H"), "".concat(String.valueOf(String.valueOf(rectangle.height))));
                    this.d();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final Rectangle b(final String s, final Object o) {
        Rectangle rectangle = null;
        if (p.d) {
            this.k(o);
            try {
                final String a = p.b.a(String.valueOf(String.valueOf(s)).concat("X"));
                final String a2 = p.b.a(String.valueOf(String.valueOf(s)).concat("Y"));
                final String a3 = p.b.a(String.valueOf(String.valueOf(s)).concat("W"));
                final String a4 = p.b.a(String.valueOf(String.valueOf(s)).concat("H"));
                if (!ji.util.d.by(a) && !ji.util.d.by(a2) && !ji.util.d.by(a3) && !ji.util.d.by(a4)) {
                    rectangle = new Rectangle(ji.util.d.c(a, 0), ji.util.d.c(a2, 0), ji.util.d.c(a3, 0), ji.util.d.c(a4, 0));
                }
            }
            catch (Exception ex) {}
        }
        if (rectangle != null) {
            rectangle.x = Math.max(rectangle.x, 0);
            rectangle.y = Math.max(rectangle.y, 0);
        }
        return rectangle;
    }
    
    public final void a(final Rectangle rectangle, final Object o) {
        this.a("NewWindow", rectangle, o);
    }
    
    public final Rectangle a(final Object o) {
        return this.b("NewWindow", o);
    }
    
    public final void b(final Rectangle rectangle, final Object o) {
        this.a("MagWindow", rectangle, o);
    }
    
    public final Rectangle b(final Object o) {
        return this.b("MagWindow", o);
    }
    
    public final void c(final Rectangle rectangle, final Object o) {
        this.a("AppWindow", rectangle, o);
    }
    
    public final Rectangle c(final Object o) {
        return this.b("AppWindow", o);
    }
    
    public final void a(final int n, final Object o) {
        this.a("MagFactor", n, o);
    }
    
    public final int b(final int n, final Object o) {
        return this.b("MagFactor", n, o);
    }
    
    public final void c(final int n, final Object o) {
        this.a("MagInternalWidth", n, o);
    }
    
    public final int d(final int n, final Object o) {
        return this.b("MagInternalWidth", n, o);
    }
    
    public final void e(final int n, final Object o) {
        this.a("MagInternalHeight", n, o);
    }
    
    public final int f(final int n, final Object o) {
        return this.b("MagInternalHeight", n, o);
    }
    
    public final void a(final Point point, final Object o) {
        this.a("MagPointX", point.x, o);
        this.a("MagPointY", point.y, o);
    }
    
    public final Point b(final Point point, final Object o) {
        return new Point(this.b("MagPointX", point.x, o), this.b("MagPointY", point.y, o));
    }
    
    public final void a(final boolean b, final Object o) {
        this.a("AnnotTools", b, o);
    }
    
    public final boolean b(final boolean b, final Object o) {
        return i.c(11) || this.b("AnnotTools", b, o);
    }
    
    public final void g(final int n, final Object o) {
        this.a("ThumbsDisplay", n, o);
    }
    
    public final int h(final int n, final Object o) {
        return this.b("ThumbsDisplay", n, o);
    }
    
    public final void i(final int n, final Object o) {
        this.a("EnhanceState", n, o);
    }
    
    public final int j(final int n, final Object o) {
        return this.b("EnhanceState", n, o);
    }
    
    public final void c(final boolean b, final Object o) {
        this.a("Enhance", b, o);
    }
    
    public final boolean d(final boolean b, final Object o) {
        return this.b("Enhance", b, o);
    }
    
    public final void e(final boolean b, final Object o) {
        this.a("SearchCaseSensitive", b, o);
    }
    
    public final boolean f(final boolean b, final Object o) {
        return this.b("SearchCaseSensitive", b, o);
    }
    
    public final void g(final boolean b, final Object o) {
        this.a("SearchWholeWord", b, o);
    }
    
    public final boolean h(final boolean b, final Object o) {
        return this.b("SearchWholeWord", b, o);
    }
    
    public final void k(final int n, final Object o) {
        this.a("PDFDepth", n, o);
    }
    
    public final int l(final int n, final Object o) {
        return this.b("PDFDepth", n, o);
    }
    
    public final void i(final boolean b, final Object o) {
        this.a("LimitPDFMemory", b, o);
    }
    
    public final boolean j(final boolean b, final Object o) {
        return this.b("LimitPDFMemory", b, o);
    }
    
    public final void m(final int n, final Object o) {
        this.a("LimitPDFMemoryValue", n, o);
    }
    
    public final int n(final int n, final Object o) {
        return this.b("LimitPDFMemoryValue", n, o);
    }
    
    public final void o(final int n, final Object o) {
        this.a("PDFResolution", n, o);
    }
    
    public final int p(final int n, final Object o) {
        return this.b("PDFResolution", n, o);
    }
    
    public final boolean d(final Object o) {
        return this.d("UniversalViewingFileIDLoaded", false, o);
    }
    
    public final void k(final boolean b, final Object o) {
        if (p.b != null) {
            p.b.b("StellentLoaded");
        }
        this.c("UniversalViewingLoaded", b, o);
    }
    
    public final boolean e(final Object o) {
        return this.d("UniversalViewingLoaded", false, o) || this.d("StellentLoaded", false, o);
    }
    
    public final void l(final boolean b, final Object o) {
        if (p.b != null) {
            p.b.b("jiFilterStellentAutoLimitResolution");
        }
        this.a("jiFilterUVAutoLimitResolution", b, o);
    }
    
    public final boolean f(final Object o) {
        boolean b = this.b("jiFilterUVAutoLimitResolution", fc.f, o);
        if (b == fc.f) {
            b = this.b("jiFilterStellentAutoLimitResolution", fc.f, o);
        }
        return b;
    }
    
    public final int g(final Object o) {
        int n = this.b("jiFilterUVAutoLimitResolutionValue", fc.g, o);
        if (n == fc.g) {
            n = this.b("jiFilterStellentAutoLimitResolutionValue", fc.g, o);
        }
        return n;
    }
    
    public final void q(final int n, final Object o) {
        if (p.b != null) {
            p.b.b("jiFilterStellentResolution");
        }
        this.a("jiFilterUVResolution", n, o);
    }
    
    public final int h(final Object o) {
        int n = this.b("jiFilterUVResolution", fc.e, o);
        if (n == fc.e) {
            n = this.b("jiFilterStellentResolution", fc.e, o);
        }
        return n;
    }
    
    public final void a(final Dimension dimension, final Object o) {
        this.a("ThumbWidth", dimension.width, o);
        this.a("ThumbHeight", dimension.height, o);
    }
    
    public final Dimension b(final Dimension dimension, final Object o) {
        return new Dimension(this.b("ThumbWidth", dimension.width, o), this.b("ThumbHeight", dimension.height, o));
    }
    
    public final void c(final Dimension dimension, final Object o) {
        this.a("SplitWidth", dimension.width, o);
        this.a("SplitHeight", dimension.height, o);
    }
    
    public final Dimension d(final Dimension dimension, final Object o) {
        return new Dimension(this.b("SplitWidth", dimension.width, o), this.b("SplitHeight", dimension.height, o));
    }
    
    public final void a(final Color color, final Object o) {
        this.a("ImageBackR", color.getRed(), o);
        this.a("ImageBackG", color.getGreen(), o);
        this.a("ImageBackB", color.getBlue(), o);
    }
    
    public final Color b(final Color color, final Object o) {
        return new Color(0xFF000000 | this.b("ImageBackR", color.getRed(), o) << 16 | this.b("ImageBackG", color.getGreen(), o) << 8 | this.b("ImageBackB", color.getBlue(), o));
    }
    
    public final void c(final Color color, final Object o) {
        this.a("ImageForeR", color.getRed(), o);
        this.a("ImageForeG", color.getGreen(), o);
        this.a("ImageForeB", color.getBlue(), o);
    }
    
    public final Color d(final Color color, final Object o) {
        return new Color(0xFF000000 | this.b("ImageForeR", color.getRed(), o) << 16 | this.b("ImageForeG", color.getGreen(), o) << 8 | this.b("ImageForeB", color.getBlue(), o));
    }
    
    public final void r(final int n, final Object o) {
        this.a("ImageContrast", n, o);
    }
    
    public final int s(final int n, final Object o) {
        return this.b("ImageContrast", n, o);
    }
    
    public final void t(final int n, final Object o) {
        this.a("ImageBrightness", n, o);
    }
    
    public final int u(final int n, final Object o) {
        return this.b("ImageBrightness", n, o);
    }
    
    public final void v(final int n, final Object o) {
        this.a("ImageLuminance", n, o);
    }
    
    public final int w(final int n, final Object o) {
        return this.b("ImageLuminance", n, o);
    }
    
    public final void m(final boolean b, final Object o) {
        this.a("PrintMono", b, o);
    }
    
    public final boolean n(final boolean b, final Object o) {
        return this.b("PrintMono", b, o);
    }
    
    public final void o(final boolean b, final Object o) {
        this.a("pmVerified", b, o);
    }
    
    public final boolean p(final boolean b, final Object o) {
        return this.b("pmVerified", b, o);
    }
    
    public final void q(final boolean b, final Object o) {
        this.a("printOriginalSize", b, o);
    }
    
    public final boolean r(final boolean b, final Object o) {
        return this.b("printOriginalSize", b, o);
    }
    
    public final void s(final boolean b, final Object o) {
        this.a("Panel1Fold", b, o);
    }
    
    public final boolean t(final boolean b, final Object o) {
        return this.b("Panel1Fold", b, o);
    }
    
    public final void u(final boolean b, final Object o) {
        this.a("Panel2Fold", b, o);
    }
    
    public final boolean v(final boolean b, final Object o) {
        return this.b("Panel2Fold", b, o);
    }
    
    public final void w(final boolean b, final Object o) {
        this.a("Panel3Fold", b, o);
    }
    
    public final boolean x(final boolean b, final Object o) {
        return this.b("Panel3Fold", b, o);
    }
    
    public final void y(final boolean b, final Object o) {
        this.a("emptycliponclose", b, o);
    }
    
    public final boolean z(final boolean b, final Object o) {
        return this.b("emptycliponclose", b, o);
    }
    
    public final void aa(final boolean b, final Object o) {
        this.a("extendedcache", b, o);
    }
    
    public final boolean ab(final boolean b, final Object o) {
        return this.b("extendedcache", b, o);
    }
    
    public final void ac(final boolean b, final Object o) {
        this.a("displayprogcolor", b, o);
    }
    
    public final void ad(final boolean b, final Object o) {
        this.a("displayprogmono", b, o);
    }
    
    public final boolean ae(final boolean b, final Object o) {
        return this.b("displayprogcolor", b, o);
    }
    
    public final boolean af(final boolean b, final Object o) {
        return this.b("displayprogmono", b, o);
    }
    
    public final void ag(final boolean b, final Object o) {
        this.a("MagnifierPersistant", b, o);
    }
    
    public final boolean ah(final boolean b, final Object o) {
        return this.b("MagnifierPersistant", b, o);
    }
    
    public final void ai(final boolean b, final Object o) {
        this.a("ShowSecGroups", b, o);
    }
    
    public final boolean aj(final boolean b, final Object o) {
        return this.b("ShowSecGroups", b, o);
    }
    
    public final void ak(final boolean b, final Object o) {
        this.a("ShowSecUsers", b, o);
    }
    
    public final boolean al(final boolean b, final Object o) {
        return this.b("ShowSecUsers", b, o);
    }
    
    public final void a(final boolean b, final boolean b2, final Object o) {
        this.a("Magnifier", b, o);
        this.a("MagnifierExternal", b2, o);
    }
    
    public final boolean am(final boolean b, final Object o) {
        return this.b("Magnifier", b, o);
    }
    
    public final boolean an(final boolean b, final Object o) {
        return this.b("MagnifierExternal", b, o);
    }
    
    public final Point a(final String s, final Point point, final Object o) {
        return new Point(this.b(String.valueOf(String.valueOf(s)).concat("X"), point.x, o), this.b(String.valueOf(String.valueOf(s)).concat("Y"), point.y, o));
    }
    
    public final void b(final String s, final Point point, final Object o) {
        this.a(String.valueOf(String.valueOf(s)).concat("X"), point.x, o);
        this.a(String.valueOf(String.valueOf(s)).concat("Y"), point.y, o);
    }
    
    public final void ao(final boolean b, final Object o) {
        this.a("QuickStart", b, o);
    }
    
    public final boolean i(final Object o) {
        return !ji.util.d.by(p.b.a("QuickStart"));
    }
    
    public final boolean ap(final boolean b, final Object o) {
        return this.b("QuickStart", b, o);
    }
    
    public final String j(final Object o) {
        return this.b("filedialogdefaultdirectory", (String)null, o);
    }
    
    public final void a(final String s, final Object o) {
        this.a("filedialogdefaultdirectory", s, o);
    }
    
    public final boolean aq(final boolean b, final Object o) {
        return this.b("linkPage", b, o);
    }
    
    public final void ar(final boolean b, final Object o) {
        this.a("linkPage", b, o);
    }
    
    public final boolean as(final boolean b, final Object o) {
        return this.b("linkViewMode", b, o);
    }
    
    public final void at(final boolean b, final Object o) {
        this.a("linkViewMode", b, o);
    }
    
    public final boolean au(final boolean b, final Object o) {
        return this.b("linkThumbs", b, o);
    }
    
    public final void av(final boolean b, final Object o) {
        this.a("linkThumbs", b, o);
    }
    
    public final boolean aw(final boolean b, final Object o) {
        return this.b("showallfonts", b, o);
    }
    
    public final void ax(final boolean b, final Object o) {
        this.a("showallfonts", b, o);
    }
    
    public final boolean ay(final boolean b, final Object o) {
        return this.b("linkZoom", b, o);
    }
    
    public final void az(final boolean b, final Object o) {
        this.a("linkZoom", b, o);
    }
    
    public final boolean a0(final boolean b, final Object o) {
        return this.b("linkScroll", b, o);
    }
    
    public final void a1(final boolean b, final Object o) {
        this.a("linkScroll", b, o);
    }
    
    public final boolean a2(final boolean b, final Object o) {
        return this.b("linkMagnifier", b, o);
    }
    
    public final void a3(final boolean b, final Object o) {
        this.a("linkMagnifier", b, o);
    }
    
    public final boolean a4(final boolean b, final Object o) {
        return this.b("linkInvert", b, o);
    }
    
    public final void a5(final boolean b, final Object o) {
        this.a("linkInvert", b, o);
    }
    
    public final boolean a6(final boolean b, final Object o) {
        return this.b("linkFlip", b, o);
    }
    
    public final void a7(final boolean b, final Object o) {
        this.a("linkFlip", b, o);
    }
    
    public final boolean a8(final boolean b, final Object o) {
        return this.b("linkRotate", b, o);
    }
    
    public final void a9(final boolean b, final Object o) {
        this.a("linkRotate", b, o);
    }
    
    public final boolean ba(final boolean b, final Object o) {
        return this.b("linkShowAnnotations", b, o);
    }
    
    public final void bb(final boolean b, final Object o) {
        this.a("linkShowAnnotations", b, o);
    }
    
    static {
        p.b = null;
        p.c = null;
        p.d = true;
    }
}
