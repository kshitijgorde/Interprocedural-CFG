import java.awt.Graphics;
import java.util.Enumeration;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Vector;
import java.awt.Frame;
import java.awt.Cursor;
import java.awt.font.TextLayout;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.text.AttributedString;
import java.awt.font.TextAttribute;
import java.util.Hashtable;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_et extends rp_eF
{
    private int e;
    private int f;
    rp_bf a;
    private static String[] a;
    
    public rp_et(final int n, final int n2, final int n3, final int n4, final int n5, final double n6) {
        super(n, n2, n3, n6);
        this.a = new rp_bf();
        final rp_bf a;
        (a = this.a).a = rp_au.a.a().a(0, "tx2");
        a.a = Color.black;
        final rp_bf rp_bf = a;
        final rp_bf rp_bf2 = a;
        final rp_bf rp_bf3 = a;
        final boolean a2 = false;
        rp_bf3.c = a2;
        rp_bf2.b = a2;
        rp_bf.a = a2;
        a.b = "Dialog";
        a.a = 12;
        a.b = 0;
        this.e = n4 / 2;
        this.f = n5 / 2;
        this.a(1, true);
        this.a(1048576, true);
        this.a(16, true);
    }
    
    private rp_et(final int n, final int n2, final int n3, final int n4, final int n5, final double n6, final rp_bf a) {
        this(n, n2, n3, n4, n5, n6);
        this.a = a;
    }
    
    public final int d() {
        return 66;
    }
    
    public final boolean a(final int n, final int n2) {
        this.e = n / 2;
        this.f = n2 / 2;
        return true;
    }
    
    public final rp_bf a() {
        return this.a;
    }
    
    public final void a(final rp_bf a) {
        this.a = a;
    }
    
    public final int a() {
        return 10;
    }
    
    public final int b() {
        return 2 * this.e;
    }
    
    public final int c() {
        return 2 * this.f;
    }
    
    public final void a(rp_eS rp_eS, rp_eP rp_eP) {
        if (rp_eS.a()) {
            this.a(rp_eS);
            return;
        }
        this.a(rp_eS);
        rp_eS.a(this.a.a);
        final AffineTransform transform = ((Graphics2D)(rp_eP = (rp_eP)rp_eS.a())).getTransform();
        int a = -this.e;
        int b = -this.f;
        int n = this.e;
        int n2 = this.f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        if (rp_eS instanceof rp_eV) {
            final rp_eV rp_eV;
            a = (rp_eV = (rp_eV)rp_eS).a(this.a - this.e);
            b = rp_eV.b(this.b - this.f);
            n = rp_eV.a(this.a + this.e);
            n2 = rp_eV.b(this.b + this.f);
            n3 = rp_eV.a(this.a);
            n4 = rp_eV.b(this.b);
        }
        ((Graphics2D)rp_eP).rotate((super.a + this.d) * 0.017453292519943295, n3, n4);
        ((Graphics2D)rp_eP).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final Shape clip = ((Graphics)rp_eP).getClip();
        ((Graphics)rp_eP).clipRect(a, b, n - a, n2 - b);
        final rp_et rp_et = this;
        final rp_eS rp_eS2 = rp_eS;
        final int n5 = a;
        final int n6 = b;
        final int n7 = n;
        final int n8 = n6;
        final int n9 = n5;
        rp_eS = rp_eS2;
        this = rp_et;
        try {
            if (this.a != null) {
                final Graphics2D graphics2D = (Graphics2D)rp_eS.a();
                final float n10 = n7 - n9;
                float n11 = 0.0f;
                final Hashtable<TextAttribute, Object> hashtable = new Hashtable<TextAttribute, Object>();
                final rp_et rp_et2 = this;
                final Hashtable<TextAttribute, Object> hashtable2 = hashtable;
                final rp_eS rp_eS3 = rp_eS;
                final Hashtable<TextAttribute, Object> hashtable3 = hashtable2;
                final rp_et rp_et3 = rp_et2;
                hashtable3.put(TextAttribute.FAMILY, rp_et3.a.b);
                if (rp_eS3 instanceof rp_eV) {
                    hashtable3.put(TextAttribute.SIZE, new Float(rp_et3.a.a * 1028.0f / ((rp_eV)rp_eS3).a));
                }
                if (rp_et3.a.a) {
                    hashtable3.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
                }
                if (rp_et3.a.b) {
                    hashtable3.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                }
                if (rp_et3.a.c) {
                    hashtable3.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                }
                final String[] split = this.a.a.split("\\n");
                for (int i = 0; i < split.length; ++i) {
                    if (split[i].length() == 0) {
                        split[i] = " ";
                    }
                    final AttributedCharacterIterator iterator;
                    final int beginIndex = (iterator = new AttributedString(split[i], hashtable).getIterator()).getBeginIndex();
                    final int endIndex = iterator.getEndIndex();
                    final LineBreakMeasurer lineBreakMeasurer;
                    (lineBreakMeasurer = new LineBreakMeasurer(iterator, graphics2D.getFontRenderContext())).setPosition(beginIndex);
                    while (lineBreakMeasurer.getPosition() < endIndex) {
                        final TextLayout nextLayout = lineBreakMeasurer.nextLayout(n10);
                        final float n12 = n11 + nextLayout.getAscent();
                        float n13 = 0.0f;
                        if (this.a.b == 2) {
                            n13 = n10 - nextLayout.getAdvance();
                        }
                        if (this.a.b == 1) {
                            n13 = (n10 - nextLayout.getAdvance()) / 2.0f;
                        }
                        nextLayout.draw(graphics2D, n9 + n13, n8 + n12);
                        n11 = n12 + (nextLayout.getDescent() + nextLayout.getLeading());
                    }
                }
            }
        }
        catch (Throwable t) {
            rp_C.a(1, "Error drawing label text: " + this.a.a);
            t.printStackTrace();
        }
        ((Graphics)rp_eP).setClip(clip);
        ((Graphics2D)rp_eP).setTransform(transform);
    }
    
    private void a(final rp_eS rp_eS, int n, int n2, final int n3, final int n4) {
        this.d(n, n2);
        n = this.a + this.c;
        n2 = this.b + this.d;
        this.d(n3, n4);
        rp_eS.a(n, n2, this.a + this.c, this.b + this.d);
    }
    
    private void a(final rp_eS rp_eS) {
        rp_eS.a(Color.gray);
        this.a(rp_eS, -this.e, -this.f, -this.e, this.f);
        this.a(rp_eS, this.e, this.f, -this.e, this.f);
        this.a(rp_eS, -this.e, -this.f, this.e, -this.f);
        this.a(rp_eS, this.e, this.f, this.e, -this.f);
    }
    
    public final int a(int n, final int n2) {
        final int n3 = (int)Math.round(this.c * (n - this.a) + this.b * (n2 - this.b));
        n = (int)Math.round(this.c * (n2 - this.b) - this.b * (n - this.a));
        if (Math.abs(n3) < this.e && Math.abs(n) < this.f) {
            return 7;
        }
        return 0;
    }
    
    public final Cursor a(final int n, final int n2) {
        return Cursor.getPredefinedCursor(13);
    }
    
    public final void a(final StringBuffer sb, final rp_eP rp_eP) {
        sb.append("TYPE=LABEL\n");
        sb.append("TEXT=");
        sb.append(this.a.a);
        sb.append("\n");
    }
    
    public final String a() {
        return "";
    }
    
    public final void a(final String s) {
    }
    
    public final String b() {
        return null;
    }
    
    public final void b(final String s) {
    }
    
    public final boolean a(final Frame frame) {
        final rp_aZ rp_aZ;
        (rp_aZ = new rp_aZ(this)).setVisible(true);
        return rp_aZ.d();
    }
    
    public final void a(final rp_eg rp_eg) {
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(rp_eg.a("id", Integer.toString(this.h)));
        rp_eg.a("label", vector);
        vector.removeAllElements();
        vector.addElement(rp_eg.a("x", Integer.toString(this.a)));
        vector.addElement(rp_eg.a("y", Integer.toString(this.b)));
        vector.addElement(rp_eg.a("angle", Integer.toString((int)(1000.0 * this.a))));
        rp_eg.a("position", vector, "");
        vector.removeAllElements();
        vector.addElement(rp_eg.a("x", Integer.toString(2 * this.e)));
        vector.addElement(rp_eg.a("y", Integer.toString(2 * this.f)));
        rp_eg.a("size", vector, "");
        vector.removeAllElements();
        final int b;
        vector.addElement(rp_eg.a("align", ((b = this.a.b) < 0 || b >= rp_et.a.length) ? "" : rp_et.a[b]));
        rp_eg.a("format", vector);
        vector.removeAllElements();
        vector.addElement(rp_eg.a("name", this.a.b));
        vector.addElement(rp_eg.a("size", Integer.toString(this.a.a)));
        vector.addElement(rp_eg.a("bold", rp_C.a(this.a.b)));
        vector.addElement(rp_eg.a("italic", rp_C.a(this.a.a)));
        vector.addElement(rp_eg.a("underline", rp_C.a(this.a.c)));
        rp_eg.a("font", vector);
        rp_eg.a("color", this.a.a);
        rp_eg.a();
        rp_eg.a();
        vector.removeAllElements();
        vector.addElement(rp_eg.a("type", "plain"));
        rp_eg.a("text", vector, URLEncoder.encode(this.a.a, "UTF-8"));
        rp_eg.a();
    }
    
    public static rp_et a(rp_eA elements) {
        final int a = elements.a("id", -1);
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        int a5 = 0;
        double n = 0.0;
        final rp_bf rp_bf = new rp_bf();
        elements = (rp_eA)elements.a.elements();
        while (((Enumeration)elements).hasMoreElements()) {
            final rp_eA rp_eA;
            if ((rp_eA = ((Enumeration<rp_eA>)elements).nextElement()).a.equals("position")) {
                a2 = rp_eA.a("x", 0);
                a3 = rp_eA.a("y", 0);
                n = rp_eA.a("angle", 0.0) / 1000.0;
            }
            else if (rp_eA.a.equals("size")) {
                a4 = rp_eA.a("x", 0);
                a5 = rp_eA.a("y", 0);
            }
            else if (rp_eA.a.equals("format")) {
                final rp_bf rp_bf2 = rp_bf;
                int b = 0;
                Label_0227: {
                    final String a6;
                    if ((a6 = rp_eA.a("align", (String)null)) != null) {
                        for (int i = 0; i < rp_et.a.length; ++i) {
                            if (a6.equals(rp_et.a[i])) {
                                b = i;
                                break Label_0227;
                            }
                        }
                    }
                    b = 0;
                }
                rp_bf2.b = b;
                final Enumeration elements2 = rp_eA.a.elements();
                while (elements2.hasMoreElements()) {
                    final rp_eA rp_eA2;
                    if ((rp_eA2 = elements2.nextElement()).a.equals("font")) {
                        rp_bf.b = rp_eA2.a("name", "Dialog");
                        rp_bf.a = rp_eA2.a("size", 12);
                        rp_bf.b = (0 != rp_eA2.a("bold", 0));
                        rp_bf.a = (0 != rp_eA2.a("italic", 0));
                        rp_bf.c = (0 != rp_eA2.a("underline", 0));
                        final Enumeration elements3 = rp_eA2.a.elements();
                        while (elements3.hasMoreElements()) {
                            final rp_eA rp_eA3;
                            if ((rp_eA3 = elements3.nextElement()).a.equals("color")) {
                                rp_bf.a = rp_C.a(rp_eA3);
                            }
                        }
                    }
                }
            }
            else {
                if (!rp_eA.a.equals("text")) {
                    continue;
                }
                try {
                    rp_bf.a = URLDecoder.decode(rp_eA.b, "UTF-8");
                }
                catch (UnsupportedEncodingException ex) {
                    rp_bf.a = rp_eA.b;
                }
            }
        }
        return new rp_et(a, a2, a3, a4, a5, n, rp_bf);
    }
    
    static {
        rp_et.a = new String[] { "left", "center", "right" };
    }
}
