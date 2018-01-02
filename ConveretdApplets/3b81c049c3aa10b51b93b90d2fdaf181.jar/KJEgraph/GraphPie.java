// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.Enumeration;
import java.awt.Graphics;

public class GraphPie extends GraphType
{
    public String getGraphType() {
        return "GraphPie";
    }
    
    public void paintAxis(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
    }
    
    public void paintAxisTitle(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
    }
    
    public void paintData(final Graphics graphics, final int n, final int n2, int n3, final int n4, final Graph graph) {
        float n5 = 0.0f;
        Enumeration dataSeries;
        try {
            dataSeries = graph.getDataSeries();
        }
        catch (Exception ex) {
            return;
        }
        double n6 = n3 / n4;
        int n7;
        if (n6 <= 1.0) {
            n6 = 1.0;
            n7 = 0;
        }
        else {
            n7 = n4 / 8;
            n3 -= (int)(Math.ceil(n7 / n6) + 1.0);
        }
        if (dataSeries.hasMoreElements()) {
            final GraphDataSeries graphDataSeries = dataSeries.nextElement();
            graphics.setColor(graphDataSeries.getColor());
            final float[] values = graphDataSeries.getValues();
            for (int i = 0; i < values.length; ++i) {
                n5 += values[i];
            }
            for (int j = n7; j >= 1; --j) {
                int n8 = 90;
                int n9 = 0;
                for (int k = values.length - 1; k >= 0; --k) {
                    graphics.setColor(graph.getColorList(k).darker());
                    final float n10 = Math.round(360.0f * (values[k] / n5));
                    n9 += (int)n10;
                    graphics.fillArc(n, j + n2 - 5, n3, (int)(n3 / n6), n8, (k == 0) ? (360 - n9 + (int)n10) : ((int)n10));
                    n8 += (int)n10;
                }
            }
            int n11 = 90;
            int n12 = 0;
            for (int l = values.length - 1; l >= 0; --l) {
                graphics.setColor(graph.getColorList(l));
                final float n13 = Math.round(360.0f * (values[l] / n5));
                n12 += (int)n13;
                graphics.fillArc(n, n2 - 5, n3, (int)(n3 / n6), n11, (l == 0) ? (360 - n12 + (int)n13) : ((int)n13));
                n11 += (int)n13;
            }
        }
    }
    
    public void paintGrid(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
    }
    
    public void setATB(final Graphics graphics, final int ixatb, final int iyatb, final int iwatb, final int ihatb, final Graph graph) {
        super._iXATB = ixatb;
        super._iYATB = iyatb;
        super._iWATB = iwatb;
        super._iHATB = ihatb;
    }
    
    public void setAxis(final Graphics graphics, final int ixAxis, final int iyAxis, final int iwAxis, final int ihAxis, final Graph graph) {
        super._iWAxis = iwAxis;
        super._iHAxis = ihAxis;
        super._iXAxis = ixAxis;
        super._iYAxis = iyAxis;
    }
    
    public void setData(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Graph graph) {
        super._iXData = n + super._legend.getXOffset();
        super._iYData = n2 + super._legend.getYOffset();
        super._iWData = n3 - super._legend.getWidthOffset();
        super._iHData = n4 - super._legend.getHeightOffset();
    }
    
    public void setGrid(final Graphics graphics, final int ixGrid, final int iyGrid, final int iwGrid, final int ihGrid, final Graph graph) {
        super._iXGrid = ixGrid;
        super._iYGrid = iyGrid;
        super._iWGrid = iwGrid;
        super._iHGrid = ihGrid;
    }
    
    public void sync(final Graph graph) {
        super._grid = graph._grid;
        super._axisY = graph._axisY;
        super._axisX = graph._axisX;
        super._titleXAxis = graph._titleXAxis;
        super._titleYAxis = graph._titleYAxis;
        super._titleGraph = graph._titleGraph;
        super._legend = graph._legend;
        super._legend._legendType = 2;
    }
}
