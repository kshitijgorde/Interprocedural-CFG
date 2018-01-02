// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.Component;
import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public class GraphColumn extends GraphType
{
    public static final int LINE_NONE = 0;
    public static final int LINE_ON_FIRST_ONLY = 2;
    public static final int LINE_ON_ALL = 3;
    
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
        graphics.setFont(graph.FONT_BOLD);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int descent = fontMetrics.getDescent();
        if (!graph._showItemLabelOnTop) {
            descent = -fontMetrics.getDescent() - fontMetrics.getHeight();
        }
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
            int n8 = -9191;
            int n9 = -9191;
            final GraphDataSeries graphDataSeries = dataSeries.nextElement();
            graphics.setColor(graphDataSeries.getColor());
            final float[] values = graphDataSeries.getValues();
            int n10 = super._iValueCount;
            if (n10 > values.length) {
                n10 = values.length;
            }
            super.rItems[n7] = new Rectangle[n10];
            super.sItems[n7] = new String[n10];
            for (int i = 0; i < n10; ++i) {
                final int n11 = n + super._axisX.getCatagoryLeft(i) + spaceWidth + n7 * dataWidth;
                final float n12 = values[i] * n5;
                int n13;
                int n14;
                if (n12 < 0.0f) {
                    if (maxValue < 0.0f && n5 != -1) {
                        n13 = n2;
                        n14 = -1 * Math.round((n12 + maxValue) * valuePerPixel);
                    }
                    else if (minValue > 0.0f && n5 == -1) {
                        n13 = n2;
                        n14 = -1 * Math.round((n12 + minValue) * valuePerPixel);
                    }
                    else {
                        n13 = n2 + zeroIntersects;
                        n14 = -1 * Math.round(n12 * valuePerPixel);
                    }
                }
                else if (minValue > 0.0f && n5 != -1) {
                    n14 = Math.round((n12 - minValue) * valuePerPixel);
                    n13 = n2 + n4 - n14;
                }
                else if (maxValue < 0.0f && n5 == -1) {
                    n14 = Math.round((n12 + maxValue) * valuePerPixel);
                    n13 = n2 + n4 - n14;
                }
                else {
                    n14 = Math.round(n12 * valuePerPixel);
                    n13 = n2 + zeroIntersects - n14;
                }
                if (super._legend._legendType == 2) {
                    graphics.setColor(graph.getColorList(i));
                }
                else {
                    graphics.setColor(graphDataSeries.getColor());
                }
                if (graph._iArea != 2 || n6 != 1) {
                    graphics.fillRect(n11, n13, barWidth, n14);
                    graphics.setColor(graph.getForeground());
                    super.rItems[n7][i] = new Rectangle(n11, n13, barWidth, n14);
                    graphics.drawRect(n11, n13, barWidth, n14);
                }
                if (graph._iArea == 3 || (graph._iArea == 2 && n6 == 1)) {
                    if (n9 != -9191) {
                        graphics.setColor(Color.black);
                        graphics.drawLine(n11 + barWidth / 2, n13, n8 + barWidth / 2, n9);
                        graphics.setColor(graphDataSeries.getColor());
                        graphics.drawLine(n11 + barWidth / 2, n13 - 1, n8 + barWidth / 2, n9 - 1);
                        graphics.drawLine(n11 + barWidth / 2, n13 - 2, n8 + barWidth / 2, n9 - 2);
                    }
                    else {
                        graphDataSeries.getColor();
                    }
                    n8 = n11;
                    n9 = n13;
                }
                super.sItems[n7][i] = graph.getFormat(values[i], n7, i);
                if (graph._showItemLabel) {
                    graphics.setColor(graph.getForeground());
                    graphics.drawString(super.sItems[n7][i], n11 + barWidth / 2 - fontMetrics.stringWidth(super.sItems[n7][i]) / 2, n13 - descent);
                }
            }
            ++n7;
        }
    }
    
    public void paintTitles(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        if (graph._showItemLabelOnTop) {
            super._titleGraph.paint(graphics, n, n2 - super._titleGraph._iHeight / 3, n3, super._titleGraph._iHeight, graph);
        }
        else {
            super._titleGraph.paint(graphics, n, n2, n3, super._titleGraph._iHeight, graph);
        }
    }
    
    public void sync(final Graph graph) {
        super.sync(graph);
    }
}
