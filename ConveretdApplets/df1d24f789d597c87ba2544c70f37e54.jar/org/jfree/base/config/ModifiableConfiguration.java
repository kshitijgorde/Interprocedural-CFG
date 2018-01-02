// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.config;

import java.util.Iterator;
import java.util.Enumeration;
import org.jfree.util.Configuration;

public interface ModifiableConfiguration extends Configuration
{
    void setConfigProperty(final String p0, final String p1);
    
    Enumeration getConfigProperties();
    
    Iterator findPropertyKeys(final String p0);
}
