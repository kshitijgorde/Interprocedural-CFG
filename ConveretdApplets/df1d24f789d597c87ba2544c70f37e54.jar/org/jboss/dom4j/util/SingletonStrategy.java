// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.util;

public interface SingletonStrategy
{
    Object instance();
    
    void reset();
    
    void setSingletonClassName(final String p0);
}
