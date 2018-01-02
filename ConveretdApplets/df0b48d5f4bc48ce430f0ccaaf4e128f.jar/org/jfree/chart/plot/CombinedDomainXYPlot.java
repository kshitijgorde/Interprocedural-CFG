// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.util.Collection;
import org.jfree.util.ObjectUtilities;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisState;
import java.awt.geom.Point2D;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.AxisSpace;
import java.awt.Graphics2D;
import java.util.Collections;
import org.jfree.ui.RectangleInsets;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.data.Range;
import java.util.Iterator;
import java.util.ArrayList;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.NumberAxis;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.jfree.chart.event.PlotChangeListener;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class CombinedDomainXYPlot extends XYPlot implements Cloneable, PublicCloneable, Serializable, PlotChangeListener
{
    private static final long serialVersionUID = -7765545541261907383L;
    private List subplots;
    private int totalWeight;
    private double gap;
    private transient Rectangle2D[] subplotAreas;
    
    public CombinedDomainXYPlot() {
        this(new NumberAxis());
    }
    
    public CombinedDomainXYPlot(final ValueAxis domainAxis) {
        super(null, domainAxis, null, null);
        this.totalWeight = 0;
        this.gap = 5.0;
        this.subplots = new ArrayList();
    }
    
    public String getPlotType() {
        return "Combined_Domain_XYPlot";
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
    
    public double getGap() {
        return this.gap;
    }
    
    public void setGap(final double gap) {
        this.gap = gap;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void add(final XYPlot subplot) {
        this.add(subplot, 1);
    }
    
    public void add(final XYPlot subplot, final int weight) {
        if (subplot == null) {
            throw new IllegalArgumentException("Null 'subplot' argument.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Require weight >= 1.");
        }
        subplot.setParent(this);
        subplot.setWeight(weight);
        subplot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0), false);
        subplot.setDomainAxis(null);
        subplot.addChangeListener(this);
        this.subplots.add(subplot);
        this.totalWeight += weight;
        final ValueAxis axis = this.getDomainAxis();
        if (axis != null) {
            axis.configure();
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void remove(final XYPlot subplot) {
        if (subplot == null) {
            throw new IllegalArgumentException(" Null 'subplot' argument.");
        }
        int position = -1;
        for (int size = this.subplots.size(), i = 0; position == -1 && i < size; ++i) {
            if (this.subplots.get(i) == subplot) {
                position = i;
            }
        }
        if (position != -1) {
            this.subplots.remove(position);
            subplot.setParent(null);
            subplot.removeChangeListener(this);
            this.totalWeight -= subplot.getWeight();
            final ValueAxis domain = this.getDomainAxis();
            if (domain != null) {
                domain.configure();
            }
            this.notifyListeners(new PlotChangeEvent(this));
        }
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
            final ValueAxis xAxis = this.getDomainAxis();
            final RectangleEdge xEdge = Plot.resolveDomainAxisLocation(this.getDomainAxisLocation(), orientation);
            if (xAxis != null) {
                space = xAxis.reserveSpace(g2, this, plotArea, xEdge, space);
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
            final XYPlot plot = this.subplots.get(i);
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
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final Point2D anchor, PlotState parentState, final PlotRenderingInfo info) {
        if (info != null) {
            info.setPlotArea(area);
        }
        final RectangleInsets insets = this.getInsets();
        insets.trim(area);
        final AxisSpace space = this.calculateAxisSpace(g2, area);
        final Rectangle2D dataArea = space.shrink(area, null);
        this.setFixedRangeAxisSpaceForSubplots(space);
        final ValueAxis axis = this.getDomainAxis();
        final RectangleEdge edge = this.getDomainAxisEdge();
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
        LegendItemCollection result = this.getFixedLegendItems();
        if (result == null) {
            result = new LegendItemCollection();
            if (this.subplots != null) {
                for (final XYPlot plot : this.subplots) {
                    final LegendItemCollection more = plot.getLegendItems();
                    result.addAll(more);
                }
            }
        }
        return result;
    }
    
    public void zoomRangeAxes(final double factor, final PlotRenderingInfo info, final Point2D source) {
        XYPlot subplot = this.findSubplot(info, source);
        if (subplot != null) {
            subplot.zoomRangeAxes(factor, info, source);
        }
        else {
            final Iterator iterator = this.getSubplots().iterator();
            while (iterator.hasNext()) {
                subplot = iterator.next();
                subplot.zoomRangeAxes(factor, info, source);
            }
        }
    }
    
    public void zoomRangeAxes(final double lowerPercent, final double upperPercent, final PlotRenderingInfo info, final Point2D source) {
        XYPlot subplot = this.findSubplot(info, source);
        if (subplot != null) {
            subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
        }
        else {
            final Iterator iterator = this.getSubplots().iterator();
            while (iterator.hasNext()) {
                subplot = iterator.next();
                subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
            }
        }
    }
    
    public XYPlot findSubplot(final PlotRenderingInfo info, final Point2D source) {
        if (info == null) {
            throw new IllegalArgumentException("Null 'info' argument.");
        }
        if (source == null) {
            throw new IllegalArgumentException("Null 'source' argument.");
        }
        XYPlot result = null;
        final int subplotIndex = info.getSubplotIndex(source);
        if (subplotIndex >= 0) {
            result = this.subplots.get(subplotIndex);
        }
        return result;
    }
    
    public void setRenderer(final XYItemRenderer renderer) {
        super.setRenderer(renderer);
        for (final XYPlot plot : this.subplots) {
            plot.setRenderer(renderer);
        }
    }
    
    public void setFixedRangeAxisSpace(final AxisSpace space) {
        super.setFixedRangeAxisSpace(space);
        this.setFixedRangeAxisSpaceForSubplots(space);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    protected void setFixedRangeAxisSpaceForSubplots(final AxisSpace space) {
        for (final XYPlot plot : this.subplots) {
            plot.setFixedRangeAxisSpace(space);
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
        if (!(obj instanceof CombinedDomainXYPlot)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final CombinedDomainXYPlot p = (CombinedDomainXYPlot)obj;
        return this.totalWeight == p.totalWeight && this.gap == p.gap && ObjectUtilities.equal(this.subplots, p.subplots);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final CombinedDomainXYPlot result = (CombinedDomainXYPlot)super.clone();
        result.subplots = (List)ObjectUtilities.deepClone(this.subplots);
        for (final Plot child : result.subplots) {
            child.setParent(result);
        }
        final ValueAxis domainAxis = result.getDomainAxis();
        if (domainAxis != null) {
            domainAxis.configure();
        }
        return result;
    }
}
