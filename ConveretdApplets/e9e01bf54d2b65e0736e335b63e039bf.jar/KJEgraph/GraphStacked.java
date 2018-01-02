// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.Graphics;

public class GraphStacked extends GraphType
{
    public String getGraphType() {
        return "GraphStacked";
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
        final float[] array = new float[graph.getValueCount()];
        super.rItems = new Rectangle[graph.getDataSeriesCount()][];
        super.sItems = new String[graph.getDataSeriesCount()][];
        if (!super._axisY.isDescending()) {
            n5 = -1;
        }
        while (true) {
            Enumeration dataSeries = null;
            Label_0179: {
                try {
                    dataSeries = graph.getDataSeries();
                    break Label_0179;
                }
                catch (Exception ex) {
                    return;
                }
                final float[] values = dataSeries.nextElement().getValues();
                for (int i = 0; i < values.length; ++i) {
                    final float[] array2 = array;
                    final int n6 = i;
                    array2[n6] += values[i];
                }
            }
            if (!dataSeries.hasMoreElements()) {
                Enumeration dataSeries2;
                try {
                    dataSeries2 = graph.getDataSeries();
                }
                catch (Exception ex2) {
                    return;
                }
                int n7 = 0;
                int n8 = 0;
                while (dataSeries2.hasMoreElements()) {
                    ++n8;
                    final GraphDataSeries graphDataSeries = dataSeries2.nextElement();
                    graphics.setColor(graphDataSeries.getColor());
                    final float[] values2 = graphDataSeries.getValues();
                    if (super._iValueCount > values2.length) {
                        super._iValueCount = values2.length;
                    }
                    super.rItems[n7] = new Rectangle[super._iValueCount];
                    super.sItems[n7] = new String[super._iValueCount];
                    for (int j = 0; j < super._iValueCount; ++j) {
                        final int n9 = n + super._axisX.getCatagoryLeft(j) + spaceWidth + n7 * dataWidth;
                        final float n10 = array[j] * n5;
                        final float[] array3 = array;
                        final int n11 = j;
                        array3[n11] -= values2[j];
                        int n12;
                        int n13;
                        if (n10 < 0.0f) {
                            if (maxValue < 0.0f && n5 != -1) {
                                n12 = n2;
                                n13 = -1 * Math.round((n10 + maxValue) * valuePerPixel);
                            }
                            else if (minValue > 0.0f && n5 == -1) {
                                n12 = n2;
                                n13 = -1 * Math.round((n10 + minValue) * valuePerPixel);
                            }
                            else {
                                n12 = n2 + zeroIntersects;
                                n13 = -1 * Math.round(n10 * valuePerPixel);
                            }
                        }
                        else if (minValue > 0.0f && n5 != -1) {
                            n13 = Math.round((n10 - minValue) * valuePerPixel);
                            n12 = n2 + n4 - n13;
                        }
                        else if (maxValue < 0.0f && n5 == -1) {
                            n13 = Math.round((n10 + maxValue) * valuePerPixel);
                            n12 = n2 + n4 - n13;
                        }
                        else {
                            n13 = Math.round(n10 * valuePerPixel);
                            n12 = n2 + zeroIntersects - n13;
                        }
                        graphics.setColor(graphDataSeries.getColor());
                        graphics.fillRect(n9, n12, barWidth, n13);
                        graphics.setColor(graph.getForeground());
                        graphics.drawRect(n9, n12, barWidth, n13);
                        super.rItems[n7][j] = new Rectangle(n9, n12, barWidth, n13);
                        super.sItems[n7][j] = graph.getFormat(values2[j], n7, j);
                    }
                    ++n7;
                }
                return;
            }
            continue;
        }
    }
    
    public void sync(final Graph graph) {
        super.sync(graph);
        super._axisX._fDataGapPercent = -1.0f;
    }
}
