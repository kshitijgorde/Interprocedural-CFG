// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.data.XYZDataset;

public interface XYZToolTipGenerator extends XYToolTipGenerator
{
    String generateToolTip(final XYZDataset p0, final int p1, final int p2);
}
