// 
// Decompiled by Procyon v0.5.30
// 

package newstick.content;

import java.net.URL;
import java.io.IOException;

public interface IContentParser
{
    void reset();
    
    void beforeReading();
    
    void onIOException(final IOException p0);
    
    void afterReading();
    
    boolean onRead(final String p0);
    
    URL getUrl();
}
