import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.HashMap;

// 
// Decompiled by Procyon v0.5.30
// 

public class new
{
    public static String b = "\u2273\u2272\u2270\u2265\u2272\u2272\u2268\u227a\u2276\u2265\u227c";
    public static String c = "\u227a\u227e\u2279\u2262\u2263\u2272\u2268\u227a\u2276\u2265\u227c";
    private boolean ya;
    private HashMap d;
    private ImageObserver xa;
    private int e;
    private int f;
    private int g;
    private static String T = "\u2206";
    private static String U = "\u2205";
    private static String V = "\u2204";
    private static String W = "\u2203";
    private static String ba = "\u2202";
    private static String ca = "\u2201";
    private static String da = "\u2200";
    private static String ea = "\u220f";
    private static String ta = "\u220e";
    private static String ua = "\u2207";
    private static String Sa = "\u2273\u2272\u2270\u2265\u2272\u2272\u2268\u227a\u2276\u2265\u227c";
    private static String Ta = "\u227a\u227e\u2279\u2262\u2263\u2272\u2268\u227a\u2276\u2265\u227c";
    private static String Ua = "\u2276";
    private static String Va = "\u2275";
    private static String Wa = "\u2274";
    private static String Xa = "\u2273";
    private static String Ya = "\u2272";
    private static String Za = "\u2271";
    private static String h = "\u2270";
    private static String i = "\u227f";
    private static String j = "\u227e";
    private static String k = "\u227d";
    private static String l = "\u227c";
    private static String m = "\u227b";
    private static String n = "\u227a";
    private static String o = "\u2279";
    private static String p = "\u2278";
    private static String q = "\u2267";
    private static String r = "\u2266";
    private static String s = "\u2265";
    private static String t = "\u2264";
    private static String u = "\u2263";
    private static String v = "\u2262";
    private static String w = "\u2261";
    private static String x = "\u2260";
    private static String y = "\u226f";
    private static String z = "\u226e";
    private static String A = "\u226d";
    private static String B = "\u2279\u2242\u225a\u2255\u2252\u2245\u2217\u2258\u2251\u2217\u2244\u2247\u2245\u225e\u2243\u2252\u2217\u225e\u225a\u2256\u2250\u2252\u2244\u220d\u2217";
    private static String C = "\u2272\u2245\u2245\u2258\u2245\u2217\u225b\u2258\u2256\u2253\u225e\u2259\u2250\u2217\u225e\u225a\u2256\u2250\u2252\u2244\u2216";
    private static String D = "\u2272\u2245\u2245\u2258\u2245\u2217\u2240\u225f\u225e\u225b\u2252\u2217\u225f\u2256\u2259\u2253\u225b\u225e\u2259\u2250\u2217\u225e\u225a\u2256\u2250\u2252\u2244\u220d\u2217";
    private static String E = "\u2217\u221a\u2217";
    
    public new(final boolean ya, final int e, final int f, final Image image, final ImageObserver xa) {
        this.ya = ya;
        this.xa = xa;
        this.e = e;
        this.g = image.getHeight(this.xa);
        this.f = f;
        this.d = new HashMap(38);
        this._(e, f, image);
    }
    
    public Image a(final String s) {
        return this.d.get(s.toUpperCase());
    }
    
    public int u() {
        return this.e;
    }
    
    public int f() {
        return this.g;
    }
    
    public int t() {
        return this.f;
    }
    
    private String b(final int n) {
        switch (n) {
            case 0: {
                return new.T;
            }
            case 1: {
                return new.U;
            }
            case 2: {
                return new.V;
            }
            case 3: {
                return new.W;
            }
            case 4: {
                return new.ba;
            }
            case 5: {
                return new.ca;
            }
            case 6: {
                return new.da;
            }
            case 7: {
                return new.ea;
            }
            case 8: {
                return new.ta;
            }
            case 9: {
                return new.ua;
            }
            case 10: {
                return new.Sa;
            }
            case 11: {
                return new.Ta;
            }
            case 12: {
                return new.Ua;
            }
            case 13: {
                return new.Va;
            }
            case 14: {
                return new.Wa;
            }
            case 15: {
                return new.Xa;
            }
            case 16: {
                return new.Ya;
            }
            case 17: {
                return new.Za;
            }
            case 18: {
                return new.h;
            }
            case 19: {
                return new.i;
            }
            case 20: {
                return new.j;
            }
            case 21: {
                return new.k;
            }
            case 22: {
                return new.l;
            }
            case 23: {
                return new.m;
            }
            case 24: {
                return new.n;
            }
            case 25: {
                return new.o;
            }
            case 26: {
                return new.p;
            }
            case 27: {
                return new.q;
            }
            case 28: {
                return new.r;
            }
            case 29: {
                return new.s;
            }
            case 30: {
                return new.t;
            }
            case 31: {
                return new.u;
            }
            case 32: {
                return new.v;
            }
            case 33: {
                return new.w;
            }
            case 34: {
                return new.x;
            }
            case 35: {
                return new.y;
            }
            case 36: {
                return new.z;
            }
            case 37: {
                return new.A;
            }
            default: {
                return null;
            }
        }
    }
    
    private void _(final int n, final int n2, final Image image) {
        final Component component = (Component)this.xa;
        final MediaTracker mediaTracker = new MediaTracker(component);
        final int width = image.getWidth(this.xa);
        int n3 = 0;
        int i = 0;
        final ImageProducer source = image.getSource();
        while (i <= width) {
            this.d.put(this.b(n3), component.createImage(new FilteredImageSource(source, new CropImageFilter(i, 0, n, this.g))));
            mediaTracker.addImage((Image)this.d.get(this.b(n3)), 0);
            i += n + n2;
            ++n3;
        }
        this.b(new.B.concat(String.valueOf(String.valueOf(this.d.size()))));
        try {
            mediaTracker.waitForID(0);
            if (mediaTracker.isErrorAny()) {
                this.b(new.C);
            }
        }
        catch (Exception ex) {
            this.b(new.D.concat(String.valueOf(String.valueOf(ex.toString()))));
        }
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(new.E).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    static {
        new.b = m(new.b);
        new.c = m(new.c);
        new.T = m(new.T);
        new.U = m(new.U);
        new.V = m(new.V);
        new.W = m(new.W);
        new.ba = m(new.ba);
        new.ca = m(new.ca);
        new.da = m(new.da);
        new.ea = m(new.ea);
        new.ta = m(new.ta);
        new.ua = m(new.ua);
        new.Sa = m(new.Sa);
        new.Ta = m(new.Ta);
        new.Ua = m(new.Ua);
        new.Va = m(new.Va);
        new.Wa = m(new.Wa);
        new.Xa = m(new.Xa);
        new.Ya = m(new.Ya);
        new.Za = m(new.Za);
        new.h = m(new.h);
        new.i = m(new.i);
        new.j = m(new.j);
        new.k = m(new.k);
        new.l = m(new.l);
        new.m = m(new.m);
        new.n = m(new.n);
        new.o = m(new.o);
        new.p = m(new.p);
        new.q = m(new.q);
        new.r = m(new.r);
        new.s = m(new.s);
        new.t = m(new.t);
        new.u = m(new.u);
        new.v = m(new.v);
        new.w = m(new.w);
        new.x = m(new.x);
        new.y = m(new.y);
        new.z = m(new.z);
        new.A = m(new.A);
        new.B = m(new.B);
        new.C = m(new.C);
        new.D = m(new.D);
        new.E = m(new.E);
        new.b = new.Sa;
        new.c = new.Ta;
    }
    
    private static String m(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF2237);
        }
        return new String(array);
    }
}
