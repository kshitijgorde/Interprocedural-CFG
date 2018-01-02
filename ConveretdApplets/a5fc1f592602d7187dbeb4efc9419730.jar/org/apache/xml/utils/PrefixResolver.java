// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.Node;

public interface PrefixResolver
{
    String getNamespaceForPrefix(final String p0);
    
    String getNamespaceForPrefix(final String p0, final Node p1);
    
    String getBaseIdentifier();
    
    boolean handlesNullPrefixes();
}
