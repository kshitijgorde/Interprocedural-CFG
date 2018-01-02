// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.util.Configuration;
import java.util.Iterator;
import java.io.Serializable;

public interface ObjectDescription extends Serializable
{
    Class getParameterDefinition(final String p0);
    
    void setParameter(final String p0, final Object p1);
    
    Object getParameter(final String p0);
    
    Iterator getParameterNames();
    
    Class getObjectClass();
    
    Object createObject();
    
    ObjectDescription getUnconfiguredInstance();
    
    ObjectDescription getInstance();
    
    void setParameterFromObject(final Object p0) throws ObjectFactoryException;
    
    void configure(final Configuration p0);
    
    boolean equals(final Object p0);
    
    int hashCode();
}
