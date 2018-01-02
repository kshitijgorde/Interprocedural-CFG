// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart;

import java.awt.Graphics2D;

public class PointShapes
{
    public static final void drawShape(final Graphics2D graphics2D, final int[][] array, final double n, final double n2) {
        final int n3 = (int)n;
        final int n4 = (int)n2;
        int i = 0;
        while (i < array.length) {
            final int n5 = array[i][0] + n3;
            final int n6 = array[i][1] + n4;
            ++i;
            final int n7 = array[i][0] + n3;
            final int n8 = array[i][1] + n4;
            ++i;
            graphics2D.drawLine(n5, n6, n7, n8);
        }
    }
}
