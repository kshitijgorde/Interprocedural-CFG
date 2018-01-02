// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class OutlierListCollection
{
    private List outlierLists;
    private boolean highFarOut;
    private boolean lowFarOut;
    
    public OutlierListCollection() {
        this.highFarOut = false;
        this.lowFarOut = false;
        this.outlierLists = new ArrayList();
    }
    
    protected boolean isHighFarOut() {
        return this.highFarOut;
    }
    
    protected void setHighFarOut(final boolean farOut) {
        this.highFarOut = farOut;
    }
    
    protected boolean isLowFarOut() {
        return this.lowFarOut;
    }
    
    protected void setLowFarOut(final boolean farOut) {
        this.lowFarOut = farOut;
    }
    
    public boolean add(final Outlier outlier) {
        if (this.outlierLists.isEmpty()) {
            return this.outlierLists.add(new OutlierList(outlier));
        }
        boolean updated = false;
        for (final OutlierList list : this.outlierLists) {
            if (list.isOverlapped(outlier)) {
                updated = this.updateOutlierList(list, outlier);
            }
        }
        if (!updated) {
            updated = this.outlierLists.add(new OutlierList(outlier));
        }
        return updated;
    }
    
    public Iterator iterator() {
        return this.outlierLists.iterator();
    }
    
    private boolean updateOutlierList(final OutlierList list, final Outlier outlier) {
        boolean result = false;
        result = list.add(outlier);
        list.updateAveragedOutlier();
        list.setMultiple(true);
        return result;
    }
}
