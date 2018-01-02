// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.OutputStream;

public interface HttpOutputStreamFilter
{
    OutputStream pushStream(final OutputStream p0, final RoRequest p1);
}
