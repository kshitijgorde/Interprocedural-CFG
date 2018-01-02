// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.io.Serializable;

public abstract class AbstractSeriesDataset extends AbstractDataset implements SeriesDataset, SeriesChangeListener, Serializable
{
    public abstract int getSeriesCount();
    
    public abstract String getSeriesName(final int p0);
    
    public void seriesChanged(final SeriesChangeEvent event) {
        this.fireDatasetChanged();
    }
}
