// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

public interface IChangeRecord
{
    int getType();
    
    boolean isType(final int p0);
    
    String getAttributeName();
    
    IModelObject getChild();
}
