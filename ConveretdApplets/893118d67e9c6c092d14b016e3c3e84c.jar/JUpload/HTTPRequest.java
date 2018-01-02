// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.io.File;
import java.net.URL;

public interface HTTPRequest
{
    void setActionURL(final URL p0);
    
    void addFile(final File p0);
    
    void setLastRequest(final boolean p0);
    
    boolean isFinished();
    
    boolean isRunning();
    
    void start();
}
