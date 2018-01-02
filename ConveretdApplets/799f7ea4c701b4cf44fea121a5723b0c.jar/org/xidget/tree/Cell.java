// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.tree;

import org.xidget.feature.tree.ColumnSourceListener;
import org.xidget.feature.tree.ColumnImageListener;
import org.xmodel.IModelObject;

public class Cell
{
    public Object icon;
    public String text;
    public IModelObject source;
    public ColumnImageListener imageListener;
    public ColumnSourceListener sourceListener;
}
