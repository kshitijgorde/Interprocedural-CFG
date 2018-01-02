// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol;

import java.io.IOException;
import java.util.Collection;
import java.net.URL;

public interface URLLister
{
    Collection listMembers(final URL p0, final String p1, final boolean p2) throws IOException;
    
    Collection listMembers(final URL p0, final String p1) throws IOException;
    
    Collection listMembers(final URL p0, final URLFilter p1, final boolean p2) throws IOException;
    
    Collection listMembers(final URL p0, final URLFilter p1) throws IOException;
    
    public interface URLFilter
    {
        boolean accept(final URL p0, final String p1);
    }
}
