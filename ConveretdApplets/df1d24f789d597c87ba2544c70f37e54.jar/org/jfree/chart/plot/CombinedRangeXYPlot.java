// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import org.jfree.util.ObjectUtils;
import org.jfree.data.Range;
import java.util.Iterator;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisState;
import java.awt.geom.Point2D;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.AxisSpace;
import java.awt.Graphics2D;
import java.util.Collections;
import org.jfree.chart.event.PlotChangeEvent;
import java.awt.Insets;
import java.util.ArrayList;
import org.jfree.chart.renderer.XYItemRenderer;
import org.jfree.data.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.NumberAxis;
import java.util.ResourceBundle;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.jfree.chart.event.PlotChangeListener;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class CombinedRangeXYPlot extends XYPlot implements Cloneable, PublicCloneable, Serializable, PlotChangeListener
{
    private List subplots;
    private int totalWeight;
    private double gap;
    private transient Rectangle2D[] subplotAreas;
    protected static ResourceBundle localizationResources;
    
    public CombinedRangeXYPlot() {
        this(new NumberAxis());
    }
    
    public CombinedRangeXYPlot(final ValueAxis rangeAxis) {
        super(null, null, rangeAxis, null);
        this.totalWeight = 0;
        this.gap = 5.0;
        this.subplots = new ArrayList();
    }
    
    public String getPlotType() {
        return CombinedRangeXYPlot.localizationResources.getString("Combined_Range_XYPlot");
    }
    
    public double getGap() {
        return this.gap;
    }
    
    public void setGap(final double gap) {
        this.gap = gap;
    }
    
    public void add(final XYPlot subplot) {
        this.add(subplot, 1);
    }
    
    public void add(final XYPlot subplot, final int weight) {
        if (weight <= 0) {
            final String msg = "CombinedRangeXYPlot.add(...): weight must be positive.";
            throw new IllegalArgumentException(msg);
        }
        subplot.setParent(this);
        subplot.setWeight(weight);
        subplot.setInsets(new Insets(0, 0, 0, 0));
        subplot.setRangeAxis(null);
        subplot.addChangeListener(this);
        this.subplots.add(subplot);
        this.totalWeight += weight;
        this.configureRangeAxes();
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void remove(final XYPlot subplot) {
        this.subplots.remove(subplot);
        subplot.setParent(null);
        subplot.removeChangeListener(this);
        this.totalWeight -= subplot.getWeight();
        this.configureRangeAxes();
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public List getSubplots() {
        return Collections.unmodifiableList((List<?>)this.subplots);
    }
    
    protected AxisSpace calculateAxisSpace(final Graphics2D g2, final Rectangle2D plotArea) {
        AxisSpace space = new AxisSpace();
        final PlotOrientation orientation = this.getOrientation();
        final AxisSpace fixed = this.getFixedRangeAxisSpace();
        if (fixed != null) {
            if (orientation == PlotOrientation.VERTICAL) {
                space.setLeft(fixed.getLeft());
                space.setRight(fixed.getRight());
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                space.setTop(fixed.getTop());
                space.setBottom(fixed.getBottom());
            }
        }
        else {
            final ValueAxis valueAxis = this.getRangeAxis();
            final RectangleEdge valueEdge = Plot.resolveRangeAxisLocation(this.getRangeAxisLocation(), orientation);
            if (valueAxis != null) {
                space = valueAxis.reserveSpace(g2, this, plotArea, valueEdge, space);
            }
        }
        final Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
        final int n = this.subplots.size();
        this.subplotAreas = new Rectangle2D[n];
        double x = adjustedPlotArea.getX();
        double y = adjustedPlotArea.getY();
        double usableSize = 0.0;
        if (orientation == PlotOrientation.VERTICAL) {
            usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
        }
        else if (orientation == PlotOrientation.HORIZONTAL) {
            usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
        }
        for (int i = 0; i < n; ++i) {
            final XYPlot plot = this.subplots.get(i);
            if (orientation == PlotOrientation.VERTICAL) {
                final double w = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
                x = x + w + this.gap;
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                final double h = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
                y = y + h + this.gap;
            }
            final AxisSpace subSpace = plot.calculateDomainAxisSpace(g2, this.subplotAreas[i], null);
            space.ensureAtLeast(subSpace);
        }
        return space;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final PlotState parentState, final PlotRenderingInfo info) {
        this.draw(g2, area, null, parentState, info);
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final Point2D anchor, PlotState parentState, final PlotRenderingInfo info) {
        if (info != null) {
            info.setPlotArea(area);
        }
        final Insets insets = this.getInsets();
        if (insets != null) {
            area.setRect(area.getX() + insets.left, area.getY() + insets.top, area.getWidth() - insets.left - insets.right, area.getHeight() - insets.top - insets.bottom);
        }
        final AxisSpace space = this.calculateAxisSpace(g2, area);
        final Rectangle2D dataArea = space.shrink(area, null);
        this.setFixedDomainAxisSpaceForSubplots(space);
        final ValueAxis axis = this.getRangeAxis();
        final RectangleEdge edge = this.getRangeAxisEdge();
        final double cursor = RectangleEdge.coordinate(dataArea, edge);
        final AxisState axisState = axis.draw(g2, cursor, area, dataArea, edge, info);
        if (parentState == null) {
            parentState = new PlotState();
        }
        parentState.getSharedAxisStates().put(axis, axisState);
        for (int i = 0; i < this.subplots.size(); ++i) {
            final XYPlot plot = this.subplots.get(i);
            PlotRenderingInfo subplotInfo = null;
            if (info != null) {
                subplotInfo = new PlotRenderingInfo(info.getOwner());
                info.addSubplotInfo(subplotInfo);
            }
            plot.draw(g2, this.subplotAreas[i], anchor, parentState, subplotInfo);
        }
        if (info != null) {
            info.setDataArea(dataArea);
        }
    }
    
    public LegendItemCollection getLegendItems() {
        final LegendItemCollection result = new LegendItemCollection();
        if (this.subplots != null) {
            for (final XYPlot plot : this.subplots) {
                final LegendItemCollection more = plot.getLegendItems();
                result.addAll(more);
            }
        }
        return result;
    }
    
    public void zoom(final double percent) {
    }
    
    public void setRenderer(final XYItemRenderer renderer) {
        super.setRenderer(renderer);
        for (final XYPlot plot : this.subplots) {
            plot.setRenderer(renderer);
        }
    }
    
    public void setOrientation(final PlotOrientation orientation) {
        super.setOrientation(orientation);
        for (final XYPlot plot : this.subplots) {
            plot.setOrientation(orientation);
        }
    }
    
    public Range getDataRange(final ValueAxis axis) {
        Range result = null;
        if (this.subplots != null) {
            for (final XYPlot subplot : this.subplots) {
                result = Range.combine(result, subplot.getDataRange(axis));
            }
        }
        return result;
    }
    
    protected void setFixedDomainAxisSpaceForSubplots(final AxisSpace space) {
        for (final XYPlot plot : this.subplots) {
            plot.setFixedDomainAxisSpace(space);
        }
    }
    
    public void handleClick(final int x, final int y, final PlotRenderingInfo info) {
        final Rectangle2D dataArea = info.getDataArea();
        if (dataArea.contains(x, y)) {
            for (int i = 0; i < this.subplots.size(); ++i) {
                final XYPlot subplot = this.subplots.get(i);
                final PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
                subplot.handleClick(x, y, subplotInfo);
            }
        }
    }
    
    public void plotChanged(final PlotChangeEvent event) {
        this.notifyListeners(event);
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof CombinedRangeXYPlot) {
            final CombinedRangeXYPlot p = (CombinedRangeXYPlot)obj;
            if (super.equals(obj)) {
                final boolean b0 = ObjectUtils.equal(this.subplots, p.subplots);
                final boolean b2 = this.totalWeight == p.totalWeight;
                final boolean b3 = this.gap == p.gap;
                return b0 && b2 && b3;
            }
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final CombinedRangeXYPlot result = (CombinedRangeXYPlot)super.clone();
        result.subplots = ObjectUtils.clone(this.subplots);
        for (final Plot child : result.subplots) {
            child.setParent(result);
        }
        final ValueAxis rangeAxis = result.getRangeAxis();
        if (rangeAxis != null) {
            rangeAxis.configure();
        }
        return result;
    }
    
    static {
        CombinedRangeXYPlot.localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
    }
}
