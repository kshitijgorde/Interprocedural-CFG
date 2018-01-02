// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import org.jfree.ui.RectangleEdge;
import org.jfree.data.CategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.CategoryPlot;
import java.awt.Graphics2D;
import org.jfree.chart.axis.CategoryAnchor;
import java.io.Serializable;

public class CategoryTextAnnotation extends TextAnnotation implements CategoryAnnotation, Cloneable, Serializable
{
    private Comparable category;
    private CategoryAnchor categoryAnchor;
    private double value;
    
    public CategoryTextAnnotation(final String text, final Comparable category, final double value) {
        super(text);
        this.category = category;
        this.value = value;
        this.categoryAnchor = CategoryAnchor.MIDDLE;
    }
    
    public Comparable getCategory() {
        return this.category;
    }
    
    public void setCategory(final Comparable category) {
        this.category = category;
    }
    
    public CategoryAnchor getCategoryAnchor() {
        return this.categoryAnchor;
    }
    
    public void setCategoryAnchor(final CategoryAnchor anchor) {
        this.categoryAnchor = anchor;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public void draw(final Graphics2D g2, final CategoryPlot plot, final Rectangle2D dataArea, final CategoryAxis domainAxis, final ValueAxis rangeAxis) {
        final CategoryDataset dataset = plot.getDataset();
        final int catIndex = dataset.getColumnIndex(this.category);
        final int catCount = dataset.getColumnCount();
        float anchorX = 0.0f;
        float anchorY = 0.0f;
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
        if (orientation == PlotOrientation.HORIZONTAL) {
            anchorY = (float)domainAxis.getCategoryJava2DCoordinate(this.categoryAnchor, catIndex, catCount, dataArea, domainEdge);
            anchorX = (float)rangeAxis.valueToJava2D(this.value, dataArea, rangeEdge);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            anchorX = (float)domainAxis.getCategoryJava2DCoordinate(this.categoryAnchor, catIndex, catCount, dataArea, domainEdge);
            anchorY = (float)rangeAxis.valueToJava2D(this.value, dataArea, rangeEdge);
        }
        g2.setFont(this.getFont());
        g2.setPaint(this.getPaint());
        RefineryUtilities.drawRotatedString(this.getText(), g2, anchorX, anchorY, this.getTextAnchor(), this.getRotationAnchor(), this.getRotationAngle());
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof CategoryTextAnnotation) {
            final CategoryTextAnnotation cta = (CategoryTextAnnotation)object;
            if (super.equals(object)) {
                final boolean b0 = this.category.equals(cta.getCategory());
                final boolean b2 = this.categoryAnchor.equals(cta.getCategoryAnchor());
                final boolean b3 = this.value == cta.getValue();
                return b0 && b2 && b3;
            }
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}