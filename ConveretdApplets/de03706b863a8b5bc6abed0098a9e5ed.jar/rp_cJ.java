import java.awt.Color;
import java.util.Enumeration;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Dimension;
import java.util.Vector;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_cJ implements rp_cI
{
    private int b;
    int a;
    
    rp_cJ() {
        this.a = 0;
        this.b = 0;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final boolean a(InputStream elements, final Vector vector, final Dimension dimension) {
        final rp_eA rp_eA = new rp_eA();
        if (elements == null) {
            return false;
        }
        try {
            rp_eA.a(new BufferedReader(new InputStreamReader(elements)));
            if (!rp_eA.a.equalsIgnoreCase("items")) {
                return false;
            }
            final int a;
            if ((a = rp_eA.a("total", -1)) != -1) {
                this.a = a;
            }
            elements = (InputStream)rp_eA.a.elements();
            while (((Enumeration)elements).hasMoreElements()) {
                final rp_eA rp_eA2;
                if ((rp_eA2 = ((Enumeration<rp_eA>)elements).nextElement()).a.equalsIgnoreCase("item")) {
                    try {
                        vector.addElement(a(rp_eA2));
                    }
                    catch (Exception ex) {
                        System.out.println("Error reading item from xml [" + ex + "]");
                    }
                }
            }
            dimension.width = 0;
            dimension.height = 0;
            this = this;
            for (int i = 0; i < vector.size(); ++i) {
                final rp_fn rp_fn;
                if ((rp_fn = vector.elementAt(i)) != null) {
                    dimension.width = Math.max(dimension.width, rp_fn.a());
                    dimension.height = Math.max(dimension.height, rp_fn.b());
                }
            }
            dimension.width *= (int)1.05;
            dimension.height *= (int)1.05;
            return true;
        }
        catch (Exception ex2) {
            rp_C.a(1, "DataLoaderXML: XML List load: " + ex2);
            return false;
        }
    }
    
    private static rp_fn a(final rp_eA rp_eA) {
        rp_fn rp_fn = null;
        final String a = rp_C.a(rp_eA.a("sku", ""));
        final String a2 = rp_eA.a("type", "sku");
        int a3;
        if ((a3 = rp_eA.a("layer", -1)) != -1 && (a3 < 1 || a3 > 10)) {
            rp_C.a(3, "Item layer out of range sku:" + a + " layer:" + a3);
            a3 = 5;
        }
        final boolean equals = a2.equals("sku");
        String a4 = null;
        String a5 = null;
        String b = null;
        String a6 = null;
        int a7 = 0;
        if (equals) {
            a4 = rp_eA.a("display-sku", "");
            a5 = rp_eA.a("cat", "");
            if ((b = rp_eA.a("brand", "")).length() == 0) {
                b = rp_eA.a("mfg", "");
            }
            a6 = rp_eA.a("coll", "");
            a7 = rp_eA.a("discontinued", 0);
        }
        final Enumeration<rp_eA> elements = rp_eA.a.elements();
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2;
            if ((rp_eA2 = elements.nextElement()).a.equalsIgnoreCase("sep")) {
                rp_fn = new rp_fn(rp_eA2.b.trim(), 1);
            }
        }
        if (rp_fn == null) {
            String a8 = null;
            int a9 = 0;
            int a10 = 0;
            int a11 = 0;
            final Enumeration<rp_eA> elements2 = rp_eA.a.elements();
            while (elements2.hasMoreElements()) {
                final rp_eA rp_eA3;
                if ((rp_eA3 = elements2.nextElement()).a.equalsIgnoreCase("template")) {
                    a8 = rp_eA3.a("name", (String)null);
                }
                if (rp_eA3.a.equalsIgnoreCase("size")) {
                    a9 = rp_eA3.a("width", 0);
                    a10 = rp_eA3.a("depth", 0);
                    a11 = rp_eA3.a("height", 0);
                }
            }
            if (a8 == null || a8.length() == 0) {
                rp_C.a(3, a + ": neither sep nor BasicTemplate specified.");
                return null;
            }
            if (a9 <= 0 || a10 <= 0) {
                rp_C.a(3, a + ": invalid size specified. [" + a9 + " ; " + a10 + "]");
                return null;
            }
            rp_fn = new rp_fn(equals ? 1 : 2, a8, rp_au.a().a().a(a8), a9, a10, a11);
        }
        if (rp_fn != null) {
            if (a3 != -1) {
                rp_fn.a = a3;
            }
            final rp_fT a12 = rp_fn.a;
            if (a.length() != 0) {
                a12.d = a;
            }
            if (equals) {
                if (!(a12 instanceof rp_eJ)) {
                    throw new Exception(a);
                }
                final rp_eJ rp_eJ = (rp_eJ)a12;
                if (a4.length() != 0) {
                    rp_eJ.c = a4;
                }
                if (a6.length() != 0) {
                    rp_eJ.f = a6;
                }
                if (b.length() != 0) {
                    rp_eJ.b = b;
                }
                if (a5.length() != 0) {
                    rp_eJ.a = a5;
                }
                rp_fn.b = (a7 == 1);
            }
        }
        if (rp_fn != null) {
            final Enumeration<rp_eA> elements3 = rp_eA.a.elements();
            while (elements3.hasMoreElements()) {
                final rp_eA rp_eA4;
                final int a13;
                if ((rp_eA4 = elements3.nextElement()).a.equalsIgnoreCase("stamp") && (a13 = rp_eA4.a("size", 0)) != 0) {
                    final Enumeration elements4 = rp_eA4.a.elements();
                    while (elements4.hasMoreElements()) {
                        final rp_eA rp_eA5;
                        if ((rp_eA5 = elements4.nextElement()).a.equalsIgnoreCase("url")) {
                            rp_fn.a.a(a13, rp_eA5.b);
                        }
                    }
                }
                final String a14;
                if (rp_eA4.a.equalsIgnoreCase("description") && (a14 = rp_C.a(rp_eA4.b)) != null && a14.length() > 0) {
                    rp_fn.a("desc", a14);
                }
                if (rp_eA4.a.equalsIgnoreCase("appearance")) {
                    final Color[] a15;
                    final Color color = (a15 = rp_C.a(rp_eA4))[0];
                    final Color color2 = a15[1];
                    if (color != null) {
                        rp_fn.a("c-border", color);
                    }
                    if (color2 == null) {
                        continue;
                    }
                    rp_fn.a("c-fill", color2);
                }
            }
        }
        return rp_fn;
    }
}
