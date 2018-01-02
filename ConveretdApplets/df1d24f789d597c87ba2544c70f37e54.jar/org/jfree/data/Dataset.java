// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface Dataset
{
    void addChangeListener(final DatasetChangeListener p0);
    
    void removeChangeListener(final DatasetChangeListener p0);
    
    DatasetGroup getGroup();
    
    void setGroup(final DatasetGroup p0);
}
