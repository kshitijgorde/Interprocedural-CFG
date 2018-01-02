// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public abstract class AbstractPieLabelDistributor implements Serializable
{
    protected List labels;
    
    public AbstractPieLabelDistributor() {
        this.labels = new ArrayList();
    }
    
    public PieLabelRecord getPieLabelRecord(final int index) {
        return this.labels.get(index);
    }
    
    public void addPieLabelRecord(final PieLabelRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Null 'record' argument.");
        }
        this.labels.add(record);
    }
    
    public int getItemCount() {
        return this.labels.size();
    }
    
    public void clear() {
        this.labels.clear();
    }
    
    public abstract void distributeLabels(final double p0, final double p1);
}
