// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.hash;

import org.apache.activemq.kaha.IndexMBean;

public interface HashIndexMBean extends IndexMBean
{
    int getKeySize();
    
    void setKeySize(final int p0);
    
    int getPageSize();
    
    int getNumberOfBins();
    
    boolean isEnablePageCaching();
    
    int getPageCacheSize();
    
    int getSize();
    
    int getActiveBins();
}
