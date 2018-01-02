// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public abstract class DataUtilities
{
    public static KeyedValues getCumulativePercentages(final KeyedValues data) {
        final DefaultKeyedValues result = new DefaultKeyedValues();
        double total = 0.0;
        for (int i = 0; i < data.getItemCount(); ++i) {
            final Number v = data.getValue(i);
            if (v != null) {
                total += v.doubleValue();
            }
        }
        double runningTotal = 0.0;
        for (int j = 0; j < data.getItemCount(); ++j) {
            final Number v2 = data.getValue(j);
            if (v2 != null) {
                runningTotal += v2.doubleValue();
            }
            result.addValue(data.getKey(j), new Double(runningTotal / total));
        }
        return result;
    }
}
