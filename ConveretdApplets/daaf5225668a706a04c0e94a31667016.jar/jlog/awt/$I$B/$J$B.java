// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I$B;

import java.net.URL;
import java.io.InputStream;
import java.awt.Image;

public interface $J$B
{
    Image $PC(final String p0) throws Exception;
    
    String getProperty(final String p0, final String p1);
    
    InputStream getResourceAsStream(final String p0) throws Exception;
    
    void showDocument(final URL p0, final String p1) throws Exception;
}
