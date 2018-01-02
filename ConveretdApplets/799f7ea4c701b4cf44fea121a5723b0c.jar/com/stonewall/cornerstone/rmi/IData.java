// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import org.xmodel.IModelObject;

public interface IData
{
    IModelObject getRoot();
    
    Object getValue(final IDataAdaptor p0);
}
