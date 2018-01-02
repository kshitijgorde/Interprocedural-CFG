// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.Enumeration;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;

public class GraphBanded extends GraphType
{
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
        GraphDataSeries graphDataSeries = null;
        float[] values = null;
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
            final GraphDataSeries graphDataSeries2 = dataSeries.nextElement();
            boolean b;
            if (dataSeries.hasMoreElements()) {
                graphDataSeries = dataSeries.nextElement();
                b = true;
            }
            else {
                b = false;
            }
            graphics.setColor(graphDataSeries2.getColor());
            final float[] values2 = graphDataSeries2.getValues();
            if (b) {
                values = graphDataSeries.getValues();
            }
            int n8 = 0;
            int n9 = -9191;
            int n10 = -9191;
            int n11 = -9191;
            final int[] array = new int[5];
            final int[] array2 = new int[5];
            int n12 = super._iValueCount;
            if (n12 > values2.length) {
                n12 = values2.length;
            }
            for (int i = 0; i < n12; ++i) {
                final int n13 = n + super._axisX.getCatagoryLeft(i) + spaceWidth + n7 * dataWidth;
                final int n14 = n2 + this.getYDraw(values2[i] * n5, n4, valuePerPixel, zeroIntersects, maxValue, minValue, n5);
                if (b) {
                    n8 = n2 + this.getYDraw(values[i] * n5, n4, valuePerPixel, zeroIntersects, maxValue, minValue, n5);
                }
                if (n10 != -9191) {
                    if (b) {
                        graphics.setColor(graphDataSeries2.getColor());
                        array[1] = (array[0] = n9);
                        array[3] = (array[2] = n13);
                        array[4] = n9;
                        array2[0] = n11;
                        array2[1] = n10;
                        array2[2] = n14;
                        array2[3] = n8;
                        array2[4] = n11;
                        graphics.fillPolygon(new Polygon(array, array2, 5));
                        graphics.setColor(graphDataSeries2.getColor().darker());
                        graphics.drawLine(n13, n8, n9, n11);
                        graphics.drawLine(n13, n14, n9, n10);
                    }
                    else {
                        graphics.setColor(Color.black);
                        graphics.drawLine(n13, n14, n9, n10);
                        graphics.setColor(graphDataSeries2.getColor());
                        graphics.drawLine(n13, n14 - 1, n9, n10 - 1);
                        graphics.drawLine(n13, n14 - 2, n9, n10 - 2);
                    }
                }
                n9 = n13;
                n10 = n14;
                n11 = n8;
            }
            ++n7;
        }
    }
    
    public void sync(final Graph graph) {
        super._legend = graph._legend;
        super._grid = graph._grid;
        super._axisY = graph._axisY;
        super._axisX = graph._axisX;
        super._axisX._fDataGapPercent = 100.0f;
        super._titleGraph = graph._titleGraph;
        super._titleXAxis = graph._titleXAxis;
        super._titleYAxis = graph._titleYAxis;
        super._legend._legendType = 0;
        super._axisY.setDescending(true);
        super._axisX.setOrientation(4);
        super._axisY.setOrientation(0);
        super._titleXAxis._iOrientation = 0;
        super._titleYAxis._iOrientation = 1;
    }
}
