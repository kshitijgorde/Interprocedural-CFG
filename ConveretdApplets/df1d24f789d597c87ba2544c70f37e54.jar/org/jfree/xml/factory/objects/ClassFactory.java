// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.util.Configuration;
import java.util.Iterator;
import java.io.Serializable;

public interface ClassFactory extends Serializable
{
    ObjectDescription getDescriptionForClass(final Class p0);
    
    ObjectDescription getSuperClassObjectDescription(final Class p0, final ObjectDescription p1);
    
    Iterator getRegisteredClasses();
    
    void configure(final Configuration p0);
    
    boolean equals(final Object p0);
    
    int hashCode();
}
