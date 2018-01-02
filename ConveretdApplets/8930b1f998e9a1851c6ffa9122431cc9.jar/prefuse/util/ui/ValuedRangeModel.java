// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import javax.swing.BoundedRangeModel;

public interface ValuedRangeModel extends BoundedRangeModel
{
    Object getMinValue();
    
    Object getMaxValue();
    
    Object getLowValue();
    
    Object getHighValue();
}
