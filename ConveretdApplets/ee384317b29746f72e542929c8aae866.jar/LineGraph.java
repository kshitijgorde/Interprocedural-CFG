import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineGraph
{
    private Graphics g;
    private CoordinateSystem coordinateSystem;
    private Vector graphData;
    private int expectedSubElements;
    Color lineColor;
    int lastX;
    int lastY;
    
    LineGraph(final CoordinateSystem coordinateSystem, final Graphics g, final int expectedSubElements) {
        this.coordinateSystem = coordinateSystem;
        this.g = g;
        this.expectedSubElements = expectedSubElements;
    }
    
    public void drawGraph(final Vector graphData, final Color lineColor) {
        this.graphData = graphData;
        this.lineColor = lineColor;
        this.plotData();
    }
    
    private void drawPoint(final double x1, final double y1, final Color clr) {
        final int x2 = this.coordinateSystem.plotX(x1);
        final int y2 = this.coordinateSystem.plotY(y1);
        this.g.setColor(clr);
        if (this.lastY != -1000000) {
            this.g.drawLine(this.lastX, this.lastY, x2, y2);
        }
        this.lastX = x2;
        this.lastY = y2;
    }
    
    private void plotData() {
        final double gap = 1000 / this.graphData.size();
        double xLoc = 0.0;
        this.lastY = -1000000;
        for (int i = 0; i < this.graphData.size(); ++i) {
            final double[] dblArray = this.graphData.elementAt(i);
            int subElementCnt = this.expectedSubElements;
            if (subElementCnt < dblArray.length) {
                subElementCnt = dblArray.length;
            }
            final double gap2 = gap / subElementCnt;
            final double gap3 = gap2 * 0.9;
            double xLoc2 = xLoc;
            for (int j = 0; j < dblArray.length; ++j) {
                final double y01 = dblArray[j];
                this.drawPoint(xLoc2, y01, this.lineColor);
                xLoc2 += gap2;
            }
            xLoc += gap;
        }
    }
}
