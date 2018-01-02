// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.Color;
import java.util.Enumeration;
import java.awt.Graphics;

public class GraphFlatPie extends GraphPie
{
    public void paintData(final Graphics graphics, int n, int n2, final int n3, final int n4, final Graph graph) {
        float n5 = 0.0f;
        Enumeration dataSeries;
        try {
            dataSeries = graph.getDataSeries();
        }
        catch (Exception ex) {
            return;
        }
        int n6;
        if (n3 > n4) {
            n6 = n4;
            n += (n3 - n4) / 2;
        }
        else {
            n6 = n3;
            n2 += (n4 - n3) / 2;
        }
        n6 += 5;
        if (dataSeries.hasMoreElements()) {
            final GraphDataSeries graphDataSeries = dataSeries.nextElement();
            graphics.setColor(graphDataSeries.getColor());
            final float[] values = graphDataSeries.getValues();
            final Color[] colorList = graph.getColorList();
            for (int i = 0; i < values.length; ++i) {
                n5 += values[i];
            }
            int n7 = -45;
            for (int j = 0; j < values.length; ++j) {
                graphics.setColor(colorList[j]);
                final float n8 = Math.round(360.0f * (values[j] / n5));
                graphics.fillArc(n, n2, n6, n6, n7, (int)n8);
                n7 += (int)n8;
            }
        }
    }
}
