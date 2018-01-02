// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFCompare;

class NFPieSliceSorter implements NFCompare
{
    public int compare(final Object o, final Object o2) {
        final NFPieSlice nfPieSlice = (NFPieSlice)o;
        final NFPieSlice nfPieSlice2 = (NFPieSlice)o2;
        final double n = nfPieSlice.g + nfPieSlice.h / 2.0;
        final double n2 = nfPieSlice2.g + nfPieSlice2.h / 2.0;
        double abs;
        double abs2;
        if (n > 90.0 && n <= 270.0) {
            abs = Math.abs(n - 180.0);
            abs2 = Math.abs(n2 - 180.0);
        }
        else {
            if (n < 90.0) {
                abs = n;
            }
            else {
                abs = 360.0 - n;
            }
            if (n2 < 90.0) {
                abs2 = n2;
            }
            else {
                abs2 = 360.0 - n2;
            }
        }
        if (abs > abs2) {
            return 1;
        }
        if (abs < abs2) {
            return -1;
        }
        return 0;
    }
}
