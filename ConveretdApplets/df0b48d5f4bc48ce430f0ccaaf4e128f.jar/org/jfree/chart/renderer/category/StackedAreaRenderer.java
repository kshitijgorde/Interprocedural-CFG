// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer.category;

import org.jfree.data.Values2D;
import org.jfree.data.DataUtilities;
import java.awt.Paint;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.entity.EntityCollection;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.event.RendererChangeEvent;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StackedAreaRenderer extends AreaRenderer implements Cloneable, PublicCloneable, Serializable
{
    private static final long serialVersionUID = -3595635038460823663L;
    private boolean renderAsPercentages;
    
    public StackedAreaRenderer() {
        this(false);
    }
    
    public StackedAreaRenderer(final boolean renderAsPercentages) {
        this.renderAsPercentages = renderAsPercentages;
    }
    
    public boolean getRenderAsPercentages() {
        return this.renderAsPercentages;
    }
    
    public void setRenderAsPercentages(final boolean asPercentages) {
        this.renderAsPercentages = asPercentages;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public int getPassCount() {
        return 2;
    }
    
    public Range findRangeBounds(final CategoryDataset dataset) {
        if (this.renderAsPercentages) {
            return new Range(0.0, 1.0);
        }
        return DatasetUtilities.findStackedRangeBounds(dataset);
    }
    
    public void drawItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset dataset, final int row, final int column, final int pass) {
        Shape entityArea = null;
        final EntityCollection entities = state.getEntityCollection();
        double y1 = 0.0;
        Number n = dataset.getValue(row, column);
        if (n != null) {
            y1 = n.doubleValue();
        }
        final double[] stack1 = this.getStackValues(dataset, row, column);
        final double xx1 = domainAxis.getCategoryMiddle(column, this.getColumnCount(), dataArea, plot.getDomainAxisEdge());
        double y2 = 0.0;
        n = dataset.getValue(row, Math.max(column - 1, 0));
        if (n != null) {
            y2 = n.doubleValue();
        }
        final double[] stack2 = this.getStackValues(dataset, row, Math.max(column - 1, 0));
        final double xx2 = domainAxis.getCategoryStart(column, this.getColumnCount(), dataArea, plot.getDomainAxisEdge());
        final int itemCount = dataset.getColumnCount();
        double y3 = 0.0;
        n = dataset.getValue(row, Math.min(column + 1, itemCount - 1));
        if (n != null) {
            y3 = n.doubleValue();
        }
        final double[] stack3 = this.getStackValues(dataset, row, Math.min(column + 1, itemCount - 1));
        final double xx3 = domainAxis.getCategoryEnd(column, this.getColumnCount(), dataArea, plot.getDomainAxisEdge());
        final double xxLeft = xx2;
        final double xxRight = xx3;
        final double[] stackLeft = this.averageStackValues(stack2, stack1);
        final double[] stackRight = this.averageStackValues(stack1, stack3);
        final double[] adjStackLeft = this.adjustedStackValues(stack2, stack1);
        final double[] adjStackRight = this.adjustedStackValues(stack1, stack3);
        final RectangleEdge edge1 = plot.getRangeAxisEdge();
        final GeneralPath left = new GeneralPath();
        final GeneralPath right = new GeneralPath();
        if (y1 >= 0.0) {
            final float transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[1], dataArea, edge1);
            final float transStack1 = (float)rangeAxis.valueToJava2D(stack1[1], dataArea, edge1);
            final float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[1], dataArea, edge1);
            if (y2 >= 0.0) {
                final double yleft = (y2 + y1) / 2.0 + stackLeft[1];
                final float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge1);
                left.moveTo((float)xx1, transY1);
                left.lineTo((float)xx1, transStack1);
                left.lineTo((float)xxLeft, transStackLeft);
                left.lineTo((float)xxLeft, transYLeft);
                left.closePath();
            }
            else {
                left.moveTo((float)xx1, transStack1);
                left.lineTo((float)xx1, transY1);
                left.lineTo((float)xxLeft, transStackLeft);
                left.closePath();
            }
            final float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[1], dataArea, edge1);
            if (y3 >= 0.0) {
                final double yright = (y1 + y3) / 2.0 + stackRight[1];
                final float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
                right.moveTo((float)xx1, transStack1);
                right.lineTo((float)xx1, transY1);
                right.lineTo((float)xxRight, transYRight);
                right.lineTo((float)xxRight, transStackRight);
                right.closePath();
            }
            else {
                right.moveTo((float)xx1, transStack1);
                right.lineTo((float)xx1, transY1);
                right.lineTo((float)xxRight, transStackRight);
                right.closePath();
            }
        }
        else {
            final float transY1 = (float)rangeAxis.valueToJava2D(y1 + stack1[0], dataArea, edge1);
            final float transStack1 = (float)rangeAxis.valueToJava2D(stack1[0], dataArea, edge1);
            final float transStackLeft = (float)rangeAxis.valueToJava2D(adjStackLeft[0], dataArea, edge1);
            if (y2 >= 0.0) {
                left.moveTo((float)xx1, transStack1);
                left.lineTo((float)xx1, transY1);
                left.lineTo((float)xxLeft, transStackLeft);
                left.clone();
            }
            else {
                final double yleft = (y2 + y1) / 2.0 + stackLeft[0];
                final float transYLeft = (float)rangeAxis.valueToJava2D(yleft, dataArea, edge1);
                left.moveTo((float)xx1, transY1);
                left.lineTo((float)xx1, transStack1);
                left.lineTo((float)xxLeft, transStackLeft);
                left.lineTo((float)xxLeft, transYLeft);
                left.closePath();
            }
            final float transStackRight = (float)rangeAxis.valueToJava2D(adjStackRight[0], dataArea, edge1);
            if (y3 >= 0.0) {
                right.moveTo((float)xx1, transStack1);
                right.lineTo((float)xx1, transY1);
                right.lineTo((float)xxRight, transStackRight);
                right.closePath();
            }
            else {
                final double yright = (y1 + y3) / 2.0 + stackRight[0];
                final float transYRight = (float)rangeAxis.valueToJava2D(yright, dataArea, edge1);
                right.moveTo((float)xx1, transStack1);
                right.lineTo((float)xx1, transY1);
                right.lineTo((float)xxRight, transYRight);
                right.lineTo((float)xxRight, transStackRight);
                right.closePath();
            }
        }
        g2.setPaint(this.getItemPaint(row, column));
        g2.setStroke(this.getItemStroke(row, column));
        final Paint itemPaint = this.getItemPaint(row, column);
        if (pass == 0) {
            g2.setPaint(itemPaint);
            g2.fill(left);
            g2.fill(right);
        }
        if (entities != null) {
            final GeneralPath gp = new GeneralPath(left);
            gp.append(right, false);
            entityArea = gp;
            this.addItemEntity(entities, dataset, row, column, entityArea);
        }
    }
    
    protected double getPreviousHeight(final CategoryDataset dataset, final int series, final int category) {
        double result = 0.0;
        double total = 0.0;
        if (this.renderAsPercentages) {
            total = DataUtilities.calculateColumnTotal(dataset, category);
        }
        for (int i = 0; i < series; ++i) {
            final Number n = dataset.getValue(i, category);
            if (n != null) {
                double v = n.doubleValue();
                if (this.renderAsPercentages) {
                    v /= total;
                }
                result += v;
            }
        }
        return result;
    }
    
    protected double[] getStackValues(final CategoryDataset dataset, final int series, final int index) {
        final double[] result = new double[2];
        for (int i = 0; i < series; ++i) {
            if (this.isSeriesVisible(i)) {
                double v = 0.0;
                final Number n = dataset.getValue(i, index);
                if (n != null) {
                    v = n.doubleValue();
                }
                if (!Double.isNaN(v)) {
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
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StackedAreaRenderer)) {
            return false;
        }
        final StackedAreaRenderer that = (StackedAreaRenderer)obj;
        return this.renderAsPercentages == that.renderAsPercentages && super.equals(obj);
    }
}
