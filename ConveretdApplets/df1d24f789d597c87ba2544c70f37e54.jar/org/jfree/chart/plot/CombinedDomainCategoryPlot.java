// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import org.jfree.util.ObjectUtils;
import org.jfree.chart.LegendItemCollection;
import java.util.Iterator;
import org.jfree.chart.axis.AxisState;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.AxisSpace;
import java.awt.Graphics2D;
import java.util.Collections;
import java.awt.Insets;
import org.jfree.chart.event.PlotChangeEvent;
import java.util.ArrayList;
import org.jfree.chart.renderer.CategoryItemRenderer;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.axis.CategoryAxis;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.jfree.chart.event.PlotChangeListener;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class CombinedDomainCategoryPlot extends CategoryPlot implements Cloneable, PublicCloneable, Serializable, PlotChangeListener
{
    private List subplots;
    private int totalWeight;
    private double gap;
    private transient Rectangle2D[] subplotAreas;
    
    public CombinedDomainCategoryPlot() {
        this(null);
    }
    
    public CombinedDomainCategoryPlot(final CategoryAxis domainAxis) {
        super(null, domainAxis, null, null);
        this.subplots = new ArrayList();
        this.totalWeight = 0;
        this.gap = 5.0;
    }
    
    public double getGap() {
        return this.gap;
    }
    
    public void setGap(final double gap) {
        this.gap = gap;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void add(final CategoryPlot subplot, final int weight) {
        subplot.setParent(this);
        subplot.setWeight(weight);
        subplot.setInsets(new Insets(0, 0, 0, 0));
        subplot.setDomainAxis(null);
        subplot.setOrientation(this.getOrientation());
        subplot.addChangeListener(this);
        this.subplots.add(subplot);
        this.totalWeight += weight;
        this.getDomainAxis().configure();
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void remove(final CategoryPlot subplot) {
        this.subplots.remove(subplot);
        subplot.setParent(null);
        subplot.removeChangeListener(this);
        this.totalWeight -= subplot.getWeight();
        final CategoryAxis domain = this.getDomainAxis();
        if (domain != null) {
            domain.configure();
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public List getSubplots() {
        return Collections.unmodifiableList((List<?>)this.subplots);
    }
    
    protected AxisSpace calculateAxisSpace(final Graphics2D g2, final Rectangle2D plotArea) {
        AxisSpace space = new AxisSpace();
        final PlotOrientation orientation = this.getOrientation();
        final AxisSpace fixed = this.getFixedDomainAxisSpace();
        if (fixed != null) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                space.setLeft(fixed.getLeft());
                space.setRight(fixed.getRight());
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                space.setTop(fixed.getTop());
                space.setBottom(fixed.getBottom());
            }
        }
        else {
            final CategoryAxis categoryAxis = this.getDomainAxis();
            final RectangleEdge categoryEdge = Plot.resolveDomainAxisLocation(this.getDomainAxisLocation(), orientation);
            if (categoryAxis != null) {
                space = categoryAxis.reserveSpace(g2, this, plotArea, categoryEdge, space);
            }
            else if (this.getDrawSharedDomainAxis()) {
                space = this.getDomainAxis().reserveSpace(g2, this, plotArea, categoryEdge, space);
            }
        }
        final Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
        final int n = this.subplots.size();
        this.subplotAreas = new Rectangle2D[n];
        double x = adjustedPlotArea.getX();
        double y = adjustedPlotArea.getY();
        double usableSize = 0.0;
        if (orientation == PlotOrientation.HORIZONTAL) {
            usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
        }
        for (int i = 0; i < n; ++i) {
            final CategoryPlot plot = this.subplots.get(i);
            if (orientation == PlotOrientation.HORIZONTAL) {
                final double w = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, adjustedPlotArea.getHeight());
                x = x + w + this.gap;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                final double h = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, adjustedPlotArea.getWidth(), h);
                y = y + h + this.gap;
            }
            final AxisSpace subSpace = plot.calculateRangeAxisSpace(g2, this.subplotAreas[i], null);
            space.ensureAtLeast(subSpace);
        }
        return space;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D plotArea, PlotState parentState, final PlotRenderingInfo info) {
        if (info != null) {
            info.setPlotArea(plotArea);
        }
        final Insets insets = this.getInsets();
        if (insets != null) {
            plotArea.setRect(plotArea.getX() + insets.left, plotArea.getY() + insets.top, plotArea.getWidth() - insets.left - insets.right, plotArea.getHeight() - insets.top - insets.bottom);
        }
        final AxisSpace space = this.calculateAxisSpace(g2, plotArea);
        final Rectangle2D dataArea = space.shrink(plotArea, null);
        this.setFixedRangeAxisSpaceForSubplots(space);
        final CategoryAxis axis = this.getDomainAxis();
        final RectangleEdge domainEdge = this.getDomainAxisEdge();
        final double cursor = RectangleEdge.coordinate(dataArea, domainEdge);
        final AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, domainEdge, info);
        if (parentState == null) {
            parentState = new PlotState();
        }
        parentState.getSharedAxisStates().put(axis, axisState);
        for (int i = 0; i < this.subplots.size(); ++i) {
            final CategoryPlot plot = this.subplots.get(i);
            PlotRenderingInfo subplotInfo = null;
            if (info != null) {
                subplotInfo = new PlotRenderingInfo(info.getOwner());
                info.addSubplotInfo(subplotInfo);
            }
            plot.draw(g2, this.subplotAreas[i], parentState, subplotInfo);
        }
        if (info != null) {
            info.setDataArea(dataArea);
        }
    }
    
    protected void setFixedRangeAxisSpaceForSubplots(final AxisSpace space) {
        for (final CategoryPlot plot : this.subplots) {
            plot.setFixedRangeAxisSpace(space);
        }
    }
    
    public void setOrientation(final PlotOrientation orientation) {
        super.setOrientation(orientation);
        for (final CategoryPlot plot : this.subplots) {
            plot.setOrientation(orientation);
        }
    }
    
    public LegendItemCollection getLegendItems() {
        final LegendItemCollection result = new LegendItemCollection();
        if (this.subplots != null) {
            for (final CategoryPlot plot : this.subplots) {
                final LegendItemCollection more = plot.getLegendItems();
                result.addAll(more);
            }
        }
        return result;
    }
    
    public List getCategories() {
        final List result = new ArrayList();
        if (this.subplots != null) {
            for (final CategoryPlot plot : this.subplots) {
                final List more = plot.getCategories();
                for (final Comparable category : more) {
                    if (!result.contains(category)) {
                        result.add(category);
                    }
                }
            }
        }
        return Collections.unmodifiableList((List<?>)result);
    }
    
    public void handleClick(final int x, final int y, final PlotRenderingInfo info) {
        final Rectangle2D dataArea = info.getDataArea();
        if (dataArea.contains(x, y)) {
            for (int i = 0; i < this.subplots.size(); ++i) {
                final CategoryPlot subplot = this.subplots.get(i);
                final PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
                subplot.handleClick(x, y, subplotInfo);
            }
        }
    }
    
    public void plotChanged(final PlotChangeEvent event) {
        this.notifyListeners(event);
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof CombinedDomainCategoryPlot) {
            final CombinedDomainCategoryPlot plot = (CombinedDomainCategoryPlot)object;
            if (super.equals(object)) {
                final boolean b0 = ObjectUtils.equal(this.subplots, plot.subplots);
                final boolean b2 = this.totalWeight == plot.totalWeight;
                final boolean b3 = this.gap == plot.gap;
                return b0 && b2 && b3;
            }
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final CombinedDomainCategoryPlot result = (CombinedDomainCategoryPlot)super.clone();
        result.subplots = ObjectUtils.clone(this.subplots);
        for (final Plot child : result.subplots) {
            child.setParent(result);
        }
        return result;
    }
}
