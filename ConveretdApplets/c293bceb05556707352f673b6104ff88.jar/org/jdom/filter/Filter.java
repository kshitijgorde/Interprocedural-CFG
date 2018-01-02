// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.filter;

public interface Filter
{
    boolean canAdd(final Object p0);
    
    boolean canRemove(final Object p0);
    
    boolean matches(final Object p0);
}
