// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.statistics;

import org.jfree.util.ObjectUtils;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class BoxAndWhiskerItem implements Serializable
{
    private Number mean;
    private Number median;
    private Number q1;
    private Number q3;
    private Number minRegularValue;
    private Number maxRegularValue;
    private Number minOutlier;
    private Number maxOutlier;
    private List outliers;
    
    public BoxAndWhiskerItem(final Number mean, final Number median, final Number q1, final Number q3, final Number minRegularValue, final Number maxRegularValue, final Number minOutlier, final Number maxOutlier, final List outliers) {
        this.mean = mean;
        this.median = median;
        this.q1 = q1;
        this.q3 = q3;
        this.minRegularValue = minRegularValue;
        this.maxRegularValue = maxRegularValue;
        this.minOutlier = minOutlier;
        this.maxOutlier = maxOutlier;
        this.outliers = outliers;
    }
    
    public Number getMean() {
        return this.mean;
    }
    
    public Number getMedian() {
        return this.median;
    }
    
    public Number getQ1() {
        return this.q1;
    }
    
    public Number getQ3() {
        return this.q3;
    }
    
    public Number getMinRegularValue() {
        return this.minRegularValue;
    }
    
    public Number getMaxRegularValue() {
        return this.maxRegularValue;
    }
    
    public Number getMinOutlier() {
        return this.minOutlier;
    }
    
    public Number getMaxOutlier() {
        return this.maxOutlier;
    }
    
    public List getOutliers() {
        return Collections.unmodifiableList((List<?>)this.outliers);
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof BoxAndWhiskerItem) {
            final BoxAndWhiskerItem item = (BoxAndWhiskerItem)obj;
            final boolean b0 = ObjectUtils.equal(this.mean, item.mean);
            final boolean b2 = ObjectUtils.equal(this.median, item.median);
            final boolean b3 = ObjectUtils.equal(this.q1, item.q1);
            final boolean b4 = ObjectUtils.equal(this.q3, item.q3);
            final boolean b5 = ObjectUtils.equal(this.minRegularValue, item.minRegularValue);
            final boolean b6 = ObjectUtils.equal(this.maxRegularValue, item.maxRegularValue);
            final boolean b7 = ObjectUtils.equal(this.minOutlier, item.minOutlier);
            final boolean b8 = ObjectUtils.equal(this.maxOutlier, item.maxOutlier);
            final boolean b9 = ObjectUtils.equal(this.outliers, item.outliers);
            return b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9;
        }
        return false;
    }
}
