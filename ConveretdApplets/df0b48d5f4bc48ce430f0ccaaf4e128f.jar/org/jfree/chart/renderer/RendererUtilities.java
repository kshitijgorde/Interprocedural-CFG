// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.data.DomainOrder;
import org.jfree.data.xy.XYDataset;

public class RendererUtilities
{
    public static int findLiveItemsLowerBound(final XYDataset dataset, final int series, final double xLow, final double xHigh) {
        final int itemCount = dataset.getItemCount(series);
        if (itemCount <= 1) {
            return 0;
        }
        if (dataset.getDomainOrder() == DomainOrder.ASCENDING) {
            int low = 0;
            int high = itemCount - 1;
            int mid = (low + high) / 2;
            final double lowValue = dataset.getXValue(series, low);
            if (lowValue >= xLow) {
                return low;
            }
            final double highValue = dataset.getXValue(series, high);
            if (highValue < xLow) {
                return high;
            }
            while (high - low > 1) {
                final double midV = dataset.getXValue(series, mid);
                if (midV >= xLow) {
                    high = mid;
                }
                else {
                    low = mid;
                }
                mid = (low + high) / 2;
            }
            return mid;
        }
        else {
            if (dataset.getDomainOrder() != DomainOrder.DESCENDING) {
                int index;
                for (index = 0; index < itemCount && dataset.getXValue(series, index) < xLow; ++index) {}
                return Math.max(0, index - 1);
            }
            int low = 0;
            int high = itemCount - 1;
            int mid = (low + high) / 2;
            final double lowValue = dataset.getXValue(series, low);
            if (lowValue <= xHigh) {
                return low;
            }
            final double highValue = dataset.getXValue(series, high);
            if (highValue > xHigh) {
                return high;
            }
            while (high - low > 1) {
                final double midV = dataset.getXValue(series, mid);
                if (midV > xHigh) {
                    low = mid;
                }
                else {
                    high = mid;
                }
                mid = (low + high) / 2;
            }
            return mid;
        }
    }
    
    public static int findLiveItemsUpperBound(final XYDataset dataset, final int series, final double xLow, final double xHigh) {
        final int itemCount = dataset.getItemCount(series);
        if (itemCount <= 1) {
            return 0;
        }
        if (dataset.getDomainOrder() == DomainOrder.ASCENDING) {
            int low = 0;
            int high = itemCount - 1;
            int mid = (low + high + 1) / 2;
            final double lowValue = dataset.getXValue(series, low);
            if (lowValue > xHigh) {
                return low;
            }
            final double highValue = dataset.getXValue(series, high);
            if (highValue <= xHigh) {
                return high;
            }
            while (high - low > 1) {
                final double midV = dataset.getXValue(series, mid);
                if (midV <= xHigh) {
                    low = mid;
                }
                else {
                    high = mid;
                }
                mid = (low + high + 1) / 2;
            }
            return mid;
        }
        else {
            if (dataset.getDomainOrder() != DomainOrder.DESCENDING) {
                int index;
                for (index = itemCount - 1; index >= 0 && dataset.getXValue(series, index) > xHigh; --index) {}
                return Math.min(itemCount - 1, index + 1);
            }
            int low = 0;
            int high = itemCount - 1;
            int mid = (low + high) / 2;
            final double lowValue = dataset.getXValue(series, low);
            if (lowValue < xLow) {
                return low;
            }
            final double highValue = dataset.getXValue(series, high);
            if (highValue >= xLow) {
                return high;
            }
            while (high - low > 1) {
                final double midV = dataset.getXValue(series, mid);
                if (midV >= xLow) {
                    low = mid;
                }
                else {
                    high = mid;
                }
                mid = (low + high) / 2;
            }
            return mid;
        }
    }
    
    public static int[] findLiveItems(final XYDataset dataset, final int series, final double xLow, final double xHigh) {
        final int i0 = findLiveItemsLowerBound(dataset, series, xLow, xHigh);
        final int i2 = findLiveItemsUpperBound(dataset, series, xLow, xHigh);
        return new int[] { i0, i2 };
    }
}
