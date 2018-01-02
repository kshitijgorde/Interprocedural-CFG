import java.util.Iterator;
import java.util.LinkedList;
import java.awt.geom.Rectangle2D;
import java.io.Reader;
import java.io.StringReader;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.util.Map;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aV
{
    private String e;
    private Map a;
    float a;
    private float b;
    public boolean a;
    public Color a;
    public Color b;
    String a;
    String b;
    String c;
    String d;
    boolean b;
    int a;
    boolean c;
    rp_bV[] a;
    
    public rp_aV(final String e) {
        this.a = new HashMap();
        this.a = 72.0f;
        this.b = 1.0f;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.b = false;
        this.a = 0;
        this.c = false;
        this.a = null;
        this.e = e;
        if (e.indexOf("http://www.inkscape.org/namespaces/inkscape") != -1) {
            this.a = 90.0f;
        }
        if (e.indexOf("Created with Inkscape") != -1) {
            this.a = 90.0f;
        }
        this.b = 1.0f / this.a;
        this.a = 1.0f;
        this.a();
        this.b();
    }
    
    private void a() {
        try {
            this.a = this.a(this.a("ANNOTATION"), "SKU");
            this.c = this.a(this.a("ANNOTATION"), "Layer");
            this.b = this.a(this.a("ANNOTATION"), "Style");
            final rp_n a = this.a("ANNOTATION");
            final ArrayList<Object> list = new ArrayList<Object>();
            this.a((rp_gg)a, list);
            final ArrayList<Object> list2 = list;
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list2.size(); ++i) {
                final String s;
                final String lowerCase;
                if (!(lowerCase = (s = list2.get(i)).toLowerCase()).startsWith("sku:") && !lowerCase.startsWith("style:") && !lowerCase.startsWith("size:") && !lowerCase.startsWith("layer:") && !lowerCase.startsWith("height:") && !lowerCase.startsWith("depth:") && !lowerCase.startsWith("view:")) {
                    sb.append(s);
                }
            }
            this.d = sb.toString();
            final String a2 = this.a(this.a("ANNOTATION"), "View");
            this.b = (a2 != null && a2.equalsIgnoreCase("FRONT"));
            final String a3 = this.a(this.a("ANNOTATION"), "Height");
            final String a4 = this.a(this.a("ANNOTATION"), "Depth");
            if (this.b && a4 != null && a4.length() > 0) {
                this.a = rp_cR.a(a4, 0);
            }
            if (!this.b && a3 != null && a3.length() > 0) {
                this.a = rp_cR.a(a3, 0);
            }
            this.c = false;
            final String a5;
            if ((a5 = this.a(this.a("SETTING"), "proportional")) != null && a5.equalsIgnoreCase("true")) {
                this.c = true;
            }
            final ArrayList list3 = new ArrayList();
            this.a(list3, "MAGN-", rp_dc.a);
            this.a(list3, "REPEL-", rp_dc.b);
            this.a = (rp_bV[])list3.toArray(new rp_bV[list3.size()]);
        }
        catch (Throwable t) {}
    }
    
    public synchronized rp_cX a() {
        final String string = ((this.a && this.b != null) ? String.valueOf(this.b.getRGB()) : "null") + ";" + ((this.a && this.a != null) ? String.valueOf(this.a.getRGB()) : "null");
        rp_cX b;
        if ((b = this.a.get(string)) == null) {
            b = this.b();
            this.a(b.a.a((Vector)null));
            this.a.put(string, b);
        }
        return b;
    }
    
    private rp_cX b() {
        try {
            final rp_dq rp_dq;
            (rp_dq = new rp_dq()).a = this.b;
            return rp_dq.a(rp_dq.a((Reader)new StringReader(this.e), "foo", false), true);
        }
        catch (Exception ex2) {
            final Exception ex = ex2;
            ex2.printStackTrace();
            throw new RuntimeException("Error loading doc due to: " + ex.getMessage(), ex);
        }
    }
    
    public final int a() {
        return Math.round(this.a().a());
    }
    
    public final int b() {
        return Math.round(this.a().b());
    }
    
    public final int c() {
        return Math.round(this.a() / (this = this).a * 2540.0f);
    }
    
    public final int d() {
        return Math.round(this.b() / (this = this).a * 2540.0f);
    }
    
    private void a(final List list, final String s, final rp_dc rp_dc) {
        final List a = this.a(s, true);
        for (int i = 0; i < a.size(); ++i) {
            final rp_gg rp_gg;
            final String b;
            if ((b = ((rp_n)(rp_gg = (rp_gg)a.get(i))).b()).length() >= 8 && b.charAt(b.length() - 2) == '-') {
                final char char1;
                String s2 = null;
                switch (char1 = b.charAt(b.length() - 1)) {
                    case 'D':
                    case 'M': {
                        s2 = "R";
                        break;
                    }
                    case 'W': {
                        s2 = "S";
                        break;
                    }
                    default: {
                        s2 = "F";
                        break;
                    }
                }
                final String substring = b.substring(5, b.length() - 2);
                final rp_eC rp_eC = new rp_eC("x1");
                final rp_eC rp_eC2 = new rp_eC("y1");
                final rp_eC rp_eC3 = new rp_eC("x2");
                final rp_eC rp_eC4 = new rp_eC("y2");
                try {
                    final rp_cu a2;
                    int n;
                    int n2;
                    int n3;
                    int n4;
                    if ((a2 = this.a(rp_gg)) != null) {
                        final rp_cu rp_cu;
                        ((rp_gg)(rp_cu = a2)).a(rp_eC);
                        ((rp_gg)rp_cu).a(rp_eC2);
                        ((rp_gg)rp_cu).a(rp_eC3);
                        ((rp_gg)rp_cu).a(rp_eC4);
                        n = (int)Math.round((rp_eC.a() - this.a() / 2.0) / this.a * 2540.0);
                        n2 = (int)Math.round((rp_eC2.a() - this.b() / 2.0) / this.a * 2540.0);
                        n3 = (int)Math.round((rp_eC3.a() - this.a() / 2.0) / this.a * 2540.0);
                        n4 = (int)Math.round((rp_eC4.a() - this.b() / 2.0) / this.a * 2540.0);
                    }
                    else {
                        final Rectangle2D bounds2D = this.a(rp_gg).a().getBounds2D();
                        n = (int)Math.round(this.a(bounds2D.getMinX()) - this.a(this.a()) / 2.0);
                        n2 = (int)Math.round(this.a(bounds2D.getMinY()) - this.a(this.b()) / 2.0);
                        n3 = (int)Math.round(this.a(bounds2D.getMaxX()) - this.a(this.a()) / 2.0);
                        n4 = (int)Math.round(this.a(bounds2D.getMaxY()) - this.a(this.b()) / 2.0);
                    }
                    final int b2 = rp_bV.b(s2);
                    final int a3 = rp_bV.a("" + char1);
                    if (b2 > 0 && a3 > 0) {
                        list.add(new rp_bV(substring, rp_dc, b2, a3, n, n4, n3, n2));
                    }
                }
                catch (rp_H rp_H) {
                    throw new RuntimeException("Failed to parse magnet due to: " + rp_H.getMessage());
                }
            }
        }
    }
    
    private double a(final double n) {
        return n / this.a * 2540.0;
    }
    
    private void a(final rp_gg rp_gg, final List list) {
        final Vector a = rp_gg.a((Vector)null);
        for (int i = 0; i < a.size(); ++i) {
            final rp_gg rp_gg2;
            if ((rp_gg2 = a.get(i)) instanceof rp_fI) {
                final LinkedList a2 = ((rp_fI)rp_gg2).a;
                for (int j = 0; j < a2.size(); ++j) {
                    final rp_gg rp_gg3;
                    String s;
                    if ((rp_gg3 = a2.get(j)) instanceof rp_cl) {
                        s = ((rp_cl)rp_gg3).a;
                    }
                    else {
                        s = rp_gg3.toString();
                    }
                    list.add(s);
                }
            }
            else {
                this.a(rp_gg2, list);
            }
        }
    }
    
    private rp_bs a(final rp_gg rp_gg) {
        final Vector a = rp_gg.a((Vector)null);
        for (int i = 0; i < a.size(); ++i) {
            final rp_gg rp_gg2;
            if ((rp_gg2 = a.get(i)) instanceof rp_bs) {
                return (rp_bs)rp_gg2;
            }
            final rp_bs a2;
            if ((a2 = this.a(rp_gg2)) != null) {
                return a2;
            }
        }
        return null;
    }
    
    private rp_cu a(final rp_gg rp_gg) {
        final Vector a = rp_gg.a((Vector)null);
        for (int i = 0; i < a.size(); ++i) {
            final rp_gg rp_gg2;
            if ((rp_gg2 = a.get(i)) instanceof rp_cu) {
                return (rp_cu)rp_gg2;
            }
            final rp_cu a2;
            if ((a2 = this.a(rp_gg2)) != null) {
                return a2;
            }
        }
        return null;
    }
    
    private String a(final rp_n rp_n, final String s) {
        if (rp_n == null) {
            return null;
        }
        String s2 = null;
        final Vector a = rp_n.a((Vector)null);
        for (int i = 0; i < a.size(); ++i) {
            final rp_gg rp_gg;
            if ((rp_gg = a.get(i)) instanceof rp_n) {
                final String a2;
                if ((a2 = this.a((rp_n)rp_gg, s)) != null) {
                    return a2;
                }
            }
            else if (rp_gg instanceof rp_fI) {
                final Iterator iterator = ((rp_fI)rp_gg).a.iterator();
                while (iterator.hasNext()) {
                    final String next;
                    if ((next = iterator.next()) instanceof String) {
                        final String s3 = next;
                        if (s != null && !s3.toLowerCase().startsWith(s.toLowerCase())) {
                            continue;
                        }
                        if (s2 == null) {
                            s2 = "";
                        }
                        s2 += s3.substring(s3.indexOf(":") + 1);
                    }
                    else {
                        if (!(next instanceof rp_cl)) {
                            continue;
                        }
                        final String a3 = ((rp_cl)next).a;
                        if (s != null && !a3.toLowerCase().startsWith(s.toLowerCase())) {
                            continue;
                        }
                        if (s2 == null) {
                            s2 = "";
                        }
                        s2 += a3.substring(a3.indexOf(":") + 1);
                    }
                }
            }
        }
        return s2;
    }
    
    private void b() {
        if (this.a("FRAME") != null) {
            final List a = this.a("", true);
            for (int i = 0; i < a.size(); ++i) {
                final rp_n rp_n = a.get(i);
                if (!"FRAME".equalsIgnoreCase(rp_n.b())) {
                    try {
                        rp_n.a().a((rp_gg)rp_n);
                    }
                    catch (rp_H rp_H) {
                        throw new RuntimeException("Error removing metadata group due to: " + rp_H.getMessage());
                    }
                }
            }
        }
    }
    
    private List a(final String s, final boolean b) {
        final ArrayList<rp_n> list = new ArrayList<rp_n>();
        final Vector a = this.a().a.a((Vector)null);
        final String lowerCase = s.toLowerCase();
        for (int i = 0; i < a.size(); ++i) {
            final rp_gg rp_gg;
            if ((rp_gg = a.get(i)) instanceof rp_n) {
                final rp_n rp_n = (rp_n)rp_gg;
                if (b && s != null && rp_n.b() != null && rp_n.b().toLowerCase().startsWith(lowerCase)) {
                    list.add(rp_n);
                }
                else if (s.equalsIgnoreCase(rp_n.b())) {
                    list.add(rp_n);
                }
            }
        }
        return list;
    }
    
    private rp_n a(final String s) {
        final List a;
        if ((a = this.a(s, false)).size() > 0) {
            return a.get(0);
        }
        return null;
    }
    
    private void a(final rp_gg rp_gg, final String s, Color color) {
        if (this.a && color != null) {
            final rp_eC rp_eC = new rp_eC(s);
            final boolean a;
            if (a = rp_gg.a(rp_eC, false)) {
                if (rp_eC.a() != null) {
                    final String hexString = Integer.toHexString((color = color).getRGB());
                    rp_eC.a("#" + hexString.substring(2, hexString.length()));
                }
                rp_gg.b(s, 2, rp_eC.b());
            }
        }
    }
    
    private void a(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            final rp_gg rp_gg;
            if ((rp_gg = vector.get(i)) instanceof rp_de) {
                final rp_de rp_de = (rp_de)rp_gg;
                if (this.a && this.a != null) {
                    final float[] a = rp_de.a();
                    final Color[] a2 = rp_de.a();
                    final Color white = Color.WHITE;
                    final Color a3 = this.a;
                    for (int j = 0; j < a.length; ++j) {
                        final float n = 1.0f - a[j];
                        a2[j] = new Color(Math.round(white.getRed() * n + a3.getRed() * (1.0f - n)), Math.round(white.getGreen() * n + a3.getGreen() * (1.0f - n)), Math.round(white.getBlue() * n + a3.getBlue() * (1.0f - n)));
                    }
                }
            }
            else {
                try {
                    this.a(rp_gg, "fill", this.a);
                    this.a(rp_gg, "stroke", this.b);
                }
                catch (Throwable t) {
                    System.out.println("Error crawling due to: " + t.getMessage());
                    t.printStackTrace();
                }
            }
            this.a(rp_gg.a((Vector)null));
        }
    }
}
