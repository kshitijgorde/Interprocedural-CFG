// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.Enumeration;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;

public class GraphLine extends GraphType
{
    public static final int AREA_NONE = 0;
    public static final int AREA_ALL = 1;
    public static final int AREA_FIRST_ONLY = 2;
    public static final int AREA_SECOND_ONLY = 3;
    public static final int AREA_BANDED = 4;
    
    public String getGraphType() {
        return "GraphLine";
    }
    
    public void paintData(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        super._axisX.getBarWidth();
        final float valuePerPixel = super._grid.getValuePerPixel();
        final int spaceWidth = super._axisX.getSpaceWidth();
        final int dataWidth = super._axisX.getDataWidth();
        final float minValue = super._axisY.getMinValue();
        final float maxValue = super._axisY.getMaxValue();
        final int zeroIntersects = super._axisY.getZeroIntersects();
        int n5 = 1;
        if (!super._axisY.isDescending()) {
            n5 = -1;
        }
        Enumeration dataSeries;
        try {
            dataSeries = graph.getDataSeries();
        }
        catch (Exception ex) {
            return;
        }
        int n6 = 0;
        int n7 = 0;
        while (dataSeries.hasMoreElements()) {
            ++n6;
            final GraphDataSeries graphDataSeries = dataSeries.nextElement();
            graphics.setColor(graphDataSeries.getColor());
            final float[] values = graphDataSeries.getValues();
            int n8 = -9191;
            int n9 = -9191;
            final int[] array = new int[5];
            final int[] array2 = new int[5];
            int n10 = super._iValueCount;
            if (n10 > values.length) {
                n10 = values.length;
            }
            for (int i = 0; i < n10; ++i) {
                final int n11 = n + super._axisX.getCatagoryLeft(i) + spaceWidth + n7 * dataWidth;
                final int n12 = n2 + this.getYDraw(values[i] * n5, n4, valuePerPixel, zeroIntersects, maxValue, minValue, n5);
                if (n9 != -9191) {
                    if (graph._iArea == 1 || (graph._iArea == 2 && n6 == 1) || (graph._iArea == 3 && n6 == 2)) {
                        graphics.setColor(graphDataSeries.getColor());
                        array[1] = (array[0] = n8);
                        array[3] = (array[2] = n11);
                        array[4] = n8;
                        array2[0] = n2 + n4;
                        array2[1] = n9;
                        array2[2] = n12;
                        array2[4] = (array2[3] = n2 + n4);
                        graphics.fillPolygon(new Polygon(array, array2, 5));
                        graphics.setColor(Color.darkGray);
                        graphics.drawLine(n11 + 1, n12, n8 + 1, n9);
                        graphics.setColor(Color.black);
                        graphics.drawLine(n11, n12, n8, n9);
                    }
                    else {
                        graphics.setColor(Color.black);
                        graphics.drawLine(n11, n12, n8, n9);
                        graphics.setColor(graphDataSeries.getColor());
                        graphics.drawLine(n11, n12 - 1, n8, n9 - 1);
                        graphics.drawLine(n11, n12 - 2, n8, n9 - 2);
                    }
                }
                else {
                    graphDataSeries.getColor();
                }
                n8 = n11;
                n9 = n12;
            }
            ++n7;
        }
    }
    
    public void sync(final Graph graph) {
        super.sync(graph);
        super._axisX._fDataGapPercent = 100.0f;
    }
}
