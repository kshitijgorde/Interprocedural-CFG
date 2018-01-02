// 
// Decompiled by Procyon v0.5.30
// 

package buildService;

import java.net.ServerSocket;

public class ClassId extends Cid implements ClassType
{
    ServerSocket sSocket;
    
    public ClassId(final Object md5, final String hash, final Object[] files) {
        super(md5, hash, files);
        this.sSocket = null;
    }
    
    public Object getKey() {
        return null;
    }
}
