// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.io.Serializable;

public interface Configuration extends Serializable, Cloneable
{
    Object clone() throws CloneNotSupportedException;
    
    Iterator findPropertyKeys(final String p0);
    
    Enumeration getConfigProperties();
    
    String getConfigProperty(final String p0);
    
    String getConfigProperty(final String p0, final String p1);
}
