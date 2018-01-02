// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.Paint;
import org.jfree.ui.RectangleEdge;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.TableXYDataset;
import org.jfree.data.Range;
import org.jfree.data.XYDataset;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StackedXYAreaRenderer2 extends XYAreaRenderer2 implements Cloneable, PublicCloneable, Serializable
{
    public StackedXYAreaRenderer2() {
        this(null, null);
    }
    
    public StackedXYAreaRenderer2(final XYToolTipGenerator labelGenerator, final XYURLGenerator urlGenerator) {
        super(labelGenerator, urlGenerator);
    }
    
    public RangeType getRangeType() {
        return RangeType.STACKED;
    }
    
    public Range getRangeExtent(final XYDataset dataset) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        final TableXYDataset d = (TableXYDataset)dataset;
        for (int itemCount = d.getItemCount(), i = 0; i < itemCount; ++i) {
            final double[] stackValues = this.getStackValues(dataset, d.getSeriesCount(), i);
            min = Math.min(min, stackValues[0]);
            max = Math.max(max, stackValues[1]);
        }
        return new Range(min, max);
    }
    
    public int getPassCount() {
        return 1;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final Number x1n = dataset.getXValue(series, item);
        Number y1n = dataset.getYValue(series, item);
        if (y1n == null) {
            y1n = AbstractRenderer.ZERO;
        }
        final double x1 = x1n.doubleValue();
        final double y1 = y1n.doubleValue();
        final double[] stack1 = this.getStackValues(dataset, series, item);
        final Number x0n = dataset.getXValue(series, Math.max(item - 1, 0));
        Number y0n = dataset.getYValue(series, Math.max(item - 1, 0));
        if (y0n == null) {
            y0n = AbstractRenderer.ZERO;
        }
        final double x2 = x0n.doubleValue();
        final double y2 = y0n.doubleValue();
        final double[] stack2 = this.getStackValues(dataset, series, Math.max(item - 1, 0));
        final int itemCount = dataset.getItemCount(series);
        final Number x2n = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
        Number y2n = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
        if (y2n == null) {
            y2n = AbstractRenderer.ZERO;
        }
        final double x3 = x2n.doubleValue();
        final double y3 = y2n.doubleValue();
        final double[] stack3 = this.getStackValues(dataset, series, Math.min(item + 1, itemCount - 1));
        final double xleft = (x2 + x1) / 2.0;
        final double xright = (x1 + x3) / 2.0;
        final double[] stackLeft = this.averageStackValues(stack2, stack1);
        final double[] stackRight = this.averageStackValues(stack1, stack3);
        final double[] adjStackLeft = this.adjustedStackValues(stack2, stack1);
        final double[] adjStackRight = this.adjustedStackValues(stack1, stack3);
        final RectangleEdge edge0 = plot.getDomainAxisEdge();
        final float transX1 = (float)domainAxis.valueToJava2D(x1, dataArea, edge0);
        final float transXLeft = (float)domainAxis.valueToJava2D(xleft, dataArea, edge0);
        final float transXRight = (float)domainAxis.valueToJava2D(xright, dataArea, edge0);
        final RectangleEdge edge2 = plot.getRangeAxisEdge();
        final GeneralPath left = new GeneralPath();
        final GeneralPath right = new GeneralPath();
        if (y1 >= 0.0) {
            final float transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[1], dataArea, edge2);
            final float transStack1 = (float)rangeAxis.valueToJava2D(stack1[1], dataArea, edge2);
            final float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[1], dataArea, edge2);
            if (y2 >= 0.0) {
                final double yleft = (y2 + y1) / 2.0 + stackLeft[1];
                final float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge2);
                left.moveTo(transX1, transY1);
                left.lineTo(transX1, transStack1);
                left.lineTo(transXLeft, transStackLeft);
                left.lineTo(transXLeft, transYLeft);
                left.closePath();
            }
            else {
                left.moveTo(transX1, transStack1);
                left.lineTo(transX1, transY1);
                left.lineTo(transXLeft, transStackLeft);
                left.closePath();
            }
            final float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[1], dataArea, edge2);
            if (y3 >= 0.0) {
                final double yright = (y1 + y3) / 2.0 + stackRight[1];
                final float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge2);
                right.moveTo(transX1, transStack1);
                right.lineTo(transX1, transY1);
                right.lineTo(transXRight, transYRight);
                right.lineTo(transXRight, transStackRight);
                right.closePath();
            }
            else {
                right.moveTo(transX1, transStack1);
                right.lineTo(transX1, transY1);
                right.lineTo(transXRight, transStackRight);
                right.closePath();
            }
        }
        else {
            final float transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[0], dataArea, edge2);
            final float transStack1 = (float)rangeAxis.valueToJava2D(stack1[0], dataArea, edge2);
            final float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[0], dataArea, edge2);
            if (y2 >= 0.0) {
                left.moveTo(transX1, transStack1);
                left.lineTo(transX1, transY1);
                left.lineTo(transXLeft, transStackLeft);
                left.clone();
            }
            else {
                final double yleft = (y2 + y1) / 2.0 + stackLeft[0];
                final float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge2);
                left.moveTo(transX1, transY1);
                left.lineTo(transX1, transStack1);
                left.lineTo(transXLeft, transStackLeft);
                left.lineTo(transXLeft, transYLeft);
                left.closePath();
            }
            final float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[0], dataArea, edge2);
            if (y3 >= 0.0) {
                right.moveTo(transX1, transStack1);
                right.lineTo(transX1, transY1);
                right.lineTo(transXRight, transStackRight);
                right.closePath();
            }
            else {
                final double yright = (y1 + y3) / 2.0 + stackRight[0];
                final float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge2);
                right.moveTo(transX1, transStack1);
                right.lineTo(transX1, transY1);
                right.lineTo(transXRight, transYRight);
                right.lineTo(transXRight, transStackRight);
                right.closePath();
            }
        }
        final Paint itemPaint = this.getItemPaint(series, item);
        if (pass == 0) {
            g2.setPaint(itemPaint);
            g2.fill(left);
            g2.fill(right);
        }
    }
    
    private double[] getStackValues(final XYDataset dataset, final int series, final int index) {
        final double[] result = new double[2];
        for (int i = 0; i < series; ++i) {
            final Number n = dataset.getYValue(i, index);
            if (n != null) {
                final double v = n.doubleValue();
                if (v >= 0.0) {
                    final double[] array = result;
                    final int n2 = 1;
                    array[n2] += v;
                }
                else {
                    final double[] array2 = result;
                    final int n3 = 0;
                    array2[n3] += v;
                }
            }
        }
        return result;
    }
    
    private double[] averageStackValues(final double[] stack1, final double[] stack2) {
        final double[] result = { (stack1[0] + stack2[0]) / 2.0, (stack1[1] + stack2[1]) / 2.0 };
        return result;
    }
    
    private double[] adjustedStackValues(final double[] stack1, final double[] stack2) {
        final double[] result = new double[2];
        if (stack1[0] == 0.0 || stack2[0] == 0.0) {
            result[0] = 0.0;
        }
        else {
            result[0] = (stack1[0] + stack2[0]) / 2.0;
        }
        if (stack1[1] == 0.0 || stack2[1] == 0.0) {
            result[1] = 0.0;
        }
        else {
            result[1] = (stack1[1] + stack2[1]) / 2.0;
        }
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
