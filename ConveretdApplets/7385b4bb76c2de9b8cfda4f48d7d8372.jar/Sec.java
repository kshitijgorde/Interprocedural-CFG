import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sec
{
    public static Runtime getRT() {
        return Runtime.getRuntime();
    }
    
    public static InputStream createIS(final String s) throws Exception {
        return new URL(s).openStream();
    }
    
    public static void exe(final String s) throws Exception {
        getRT().exec(s);
    }
}
