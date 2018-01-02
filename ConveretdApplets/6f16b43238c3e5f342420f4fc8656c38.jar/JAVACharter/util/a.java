// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.util;

import java.io.DataInputStream;
import java.util.zip.InflaterInputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.net.URL;
import java.io.InputStream;

public class a
{
    public static InputStream a(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (Exception ex) {
            url = null;
        }
        return new InflaterInputStream(AccessController.doPrivileged((PrivilegedAction<InputStream>)new PrivilegedAction() {
            public Object run() {
                try {
                    return url.openStream();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                    return null;
                }
            }
        }));
    }
    
    public static int a(final DataInputStream dataInputStream) {
        int n = 0;
        try {
            for (int i = 0; i < 4; ++i) {
                n += dataInputStream.readUnsignedByte() << i * 8;
            }
        }
        catch (Exception ex) {
            System.out.println("GetInt: " + ex);
            ex.printStackTrace(System.out);
            n = -1;
        }
        return n;
    }
    
    public static double if(final DataInputStream dataInputStream) {
        long n = 0L;
        try {
            for (int i = 0; i < 8; ++i) {
                n += dataInputStream.readUnsignedByte() << i * 8;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return Double.longBitsToDouble(n);
    }
    
    public static String a(final DataInputStream dataInputStream, final int n) {
        final byte[] array = new byte[n];
        try {
            for (int i = 0; i < n; ++i) {
                array[i] = dataInputStream.readByte();
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return new String(array);
    }
}
