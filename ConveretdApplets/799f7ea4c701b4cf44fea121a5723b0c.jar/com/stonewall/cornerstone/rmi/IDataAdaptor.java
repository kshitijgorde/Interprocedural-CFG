// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

public interface IDataAdaptor
{
    String getClassName(final Object p0);
    
    String transform(final Object p0);
    
    Object transform(final String p0, final String p1);
}
