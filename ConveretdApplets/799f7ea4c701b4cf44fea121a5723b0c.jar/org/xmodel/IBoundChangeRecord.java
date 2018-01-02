// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

public interface IBoundChangeRecord extends IChangeRecord
{
    IModelObject getBoundObject();
    
    void applyChange();
}
