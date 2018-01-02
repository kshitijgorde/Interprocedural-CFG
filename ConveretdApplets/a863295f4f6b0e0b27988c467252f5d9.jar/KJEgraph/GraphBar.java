// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Graphics;

public class GraphBar extends GraphType
{
    public void paintAxis(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        super._axisY.paint(graphics, n + super._axisX.getXOffset(), n2, super._iWGrid, super._iHGrid);
        super._axisX.paint(graphics, n, n2 + super._axisY.getYOffset(), super._iWGrid, super._iHGrid);
    }
    
    public void paintAxisTitle(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        super._titleXAxis.paint(graphics, n, super._iYGrid, super._titleXAxis._iWidth, super._iHGrid, graph);
        super._titleYAxis.paint(graphics, super._iXGrid, n2 + n4 - super._titleYAxis._iHeight, super._iWGrid, super._titleYAxis._iHeight, graph);
    }
    
    public void paintData(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        final int barWidth = super._axisX.getBarWidth();
        final float valuePerPixel = super._grid.getValuePerPixel();
        final int spaceWidth = super._axisX.getSpaceWidth();
        final int dataWidth = super._axisX.getDataWidth();
        final float minValue = super._axisY.getMinValue();
        final float maxValue = super._axisY.getMaxValue();
        final int zeroIntersects = super._axisY.getZeroIntersects();
        int n5 = 1;
        super.rItems = new Rectangle[graph.getDataSeriesCount()][];
        super.sItems = new String[graph.getDataSeriesCount()][];
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
        while (dataSeries.hasMoreElements()) {
            final GraphDataSeries graphDataSeries = dataSeries.nextElement();
            graphics.setColor(graphDataSeries.getColor());
            final float[] values = graphDataSeries.getValues();
            int n7 = super._iValueCount;
            if (super._iValueCount > values.length) {
                n7 = values.length;
            }
            super.rItems[n6] = new Rectangle[n7];
            super.sItems[n6] = new String[n7];
            for (int i = 0; i < n7; ++i) {
                final int n8 = n2 + super._axisX.getCatagoryLeft(i) + spaceWidth + n6 * dataWidth;
                final float n9 = values[i] * n5;
                int n10;
                int n11;
                if (n9 < 0.0f) {
                    if (maxValue < 0.0f && n5 != -1) {
                        n10 = n;
                        n11 = -1 * Math.round((n9 + maxValue) * valuePerPixel);
                    }
                    else if (minValue > 0.0f && n5 == -1) {
                        n10 = n;
                        n11 = -1 * Math.round((n9 + minValue) * valuePerPixel);
                    }
                    else {
                        n10 = n + zeroIntersects;
                        n11 = -1 * Math.round(n9 * valuePerPixel);
                    }
                }
                else if (minValue > 0.0f && n5 != -1) {
                    n11 = Math.round((n9 - minValue) * valuePerPixel);
                    n10 = n + n4 - n11;
                }
                else if (maxValue < 0.0f && n5 == -1) {
                    n11 = Math.round((n9 + maxValue) * valuePerPixel);
                    n10 = n + n4 - n11;
                }
                else {
                    n11 = Math.round(n9 * valuePerPixel);
                    n10 = n + zeroIntersects - n11;
                }
                if (super._legend._legendType == 2) {
                    graph.getColorList(i);
                }
                else {
                    graphics.setColor(graphDataSeries.getColor());
                }
                graphics.fillRect(n10, n8, n11, barWidth);
                graphics.setColor(graph.getForeground());
                graphics.drawRect(n10, n8, n11, barWidth);
                super.rItems[n6][i] = new Rectangle(n10, n8, n11, barWidth);
                super.sItems[n6][i] = graph.getFormat(values[i], n6 * values.length, i);
            }
            ++n6;
        }
    }
    
    public void paintGrid(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        super._grid.paint(graphics, n, n2);
    }
    
    public void setATB(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        super._iXATB = n + super._legend.getXOffset();
        super._iYATB = n2 + super._legend.getYOffset();
        super._iWATB = n3 - super._legend.getWidthOffset();
        super._iHATB = n4 - super._legend.getHeightOffset();
    }
    
    public void setAxis(final Graphics graphics, final int n, final int iyAxis, final int n2, final int n3, final Graph graph) {
        super._iWAxis = n2 - super._titleXAxis._iWidth - 8;
        super._iHAxis = n3 - super._titleYAxis._iHeight - 8;
        super._axisY.setSize(graphics, super._iWAxis - super._axisX.getWidth());
        super._axisX.setSize(graphics, super._iHAxis - super._axisY.getHeight());
        super._axisY.setSize(graphics, super._iWAxis - super._axisX.getWidth());
        super._iXAxis = n + (super._axisY.getMaxLength() - super._axisY.getWidth()) + super._titleXAxis._iWidth;
        super._iYAxis = iyAxis;
    }
    
    public void setGrid(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        super._iXGrid = n + super._axisX.getXOffset();
        super._iYGrid = n2 + super._axisY.getYOffset();
        super._iWGrid = super._axisY.getDataLength();
        super._iHGrid = super._axisX.getDataLength();
        super._grid.setSize(graphics, super._axisY, super._axisX);
    }
    
    public void sync(final Graph graph) {
        super.sync(graph);
        super._axisY.setDescending(false);
        super._titleXAxis._iOrientation = 1;
        super._titleYAxis._iOrientation = 0;
        super._axisX.setOrientation(0);
        super._axisY.setOrientation(4);
    }
}
