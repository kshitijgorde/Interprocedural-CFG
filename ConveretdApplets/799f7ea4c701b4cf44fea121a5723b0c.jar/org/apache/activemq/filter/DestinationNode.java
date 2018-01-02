// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import java.util.Collection;
import java.util.Set;

public interface DestinationNode
{
    void appendMatchingValues(final Set p0, final String[] p1, final int p2);
    
    void appendMatchingWildcards(final Set p0, final String[] p1, final int p2);
    
    void appendDescendantValues(final Set p0);
    
    Collection getDesendentValues();
    
    DestinationNode getChild(final String p0);
    
    Collection getValues();
    
    Collection getChildren();
    
    Collection removeDesendentValues();
    
    Collection removeValues();
}
