// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import java.net.URL;
import ji.util.d;

public class cp
{
    public static final cq a() throws Exception {
        if (d.b()) {
            return (cq)d.a2("ji.server.util.jiServerV2");
        }
        return new tx();
    }
    
    static class tx implements cq
    {
        public byte[] getServerVersionBytes(final URL url, final ClassLoader classLoader, final String s) throws Exception {
            return aa.a(new URL(url, aa.f()), s);
        }
    }
}
