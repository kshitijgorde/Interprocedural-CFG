// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.EventObject;

public class DatasetChangeEvent extends EventObject
{
    private Dataset data;
    
    public DatasetChangeEvent(final Object source, final Dataset data) {
        super(source);
        this.data = data;
    }
    
    public Dataset getDataset() {
        return this.data;
    }
}
