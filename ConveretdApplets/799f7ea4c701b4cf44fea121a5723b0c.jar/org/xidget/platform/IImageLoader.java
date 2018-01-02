// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.platform;

import java.util.List;
import java.net.URL;

public interface IImageLoader
{
    List<URL> loadList(final URL p0);
    
    Object load(final URL p0);
}
