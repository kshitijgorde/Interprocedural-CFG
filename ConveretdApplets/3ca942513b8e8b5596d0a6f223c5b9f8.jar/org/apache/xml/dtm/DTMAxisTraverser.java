// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm;

public abstract class DTMAxisTraverser
{
    public int first(final int context) {
        return this.next(context, context);
    }
    
    public int first(final int context, final int extendedTypeID) {
        return this.next(context, context, extendedTypeID);
    }
    
    public abstract int next(final int p0, final int p1);
    
    public abstract int next(final int p0, final int p1, final int p2);
}
