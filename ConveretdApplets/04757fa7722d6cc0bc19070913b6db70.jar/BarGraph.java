import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class BarGraph
{
    private Graphics g;
    private CoordinateSystem coordinateSystem;
    private Vector graphData;
    private int expectedSubElements;
    Color barBorderColor;
    Color barColor;
    
    BarGraph(final CoordinateSystem coordinateSystem, final Graphics g, final int expectedSubElements) {
        this.coordinateSystem = coordinateSystem;
        this.g = g;
        this.expectedSubElements = expectedSubElements;
    }
    
    public void drawGraph(final Vector graphData, final Color barBorderColor, final Color barColor) {
        this.graphData = graphData;
        this.barColor = barColor;
        this.barBorderColor = barBorderColor;
        this.plotData();
    }
    
    private void plotData() {
        final double n = 1000 / this.graphData.size();
        double n2 = 0.0;
        for (int i = 0; i < this.graphData.size(); ++i) {
            final double[] array = this.graphData.elementAt(i);
            int n3 = this.expectedSubElements;
            if (n3 < array.length) {
                n3 = array.length;
            }
            final double n4 = n / n3;
            final double width1 = n4 * 0.5;
            double x1 = n2;
            for (int j = 0; j < array.length; ++j) {
                double y1 = 0.0;
                double height1 = array[j];
                if (height1 < 0.0) {
                    y1 = height1;
                    height1 = -1.0 * y1;
                }
                this.coordinateSystem.drawBar(this.g, x1, y1, width1, height1, this.barBorderColor, this.barColor);
                x1 += n4;
            }
            n2 += n;
        }
    }
}
