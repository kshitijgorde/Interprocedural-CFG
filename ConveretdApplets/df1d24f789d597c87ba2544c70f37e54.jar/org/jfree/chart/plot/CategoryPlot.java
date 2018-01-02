// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.data.Range;
import java.awt.geom.Line2D;
import org.jfree.chart.axis.ValueTick;
import org.jfree.chart.renderer.CategoryItemRendererState;
import org.jfree.data.DatasetUtilities;
import java.util.Iterator;
import org.jfree.chart.axis.AxisCollection;
import java.awt.Insets;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Shape;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.Axis;
import java.awt.Graphics2D;
import org.jfree.chart.annotations.CategoryAnnotation;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import org.jfree.chart.event.RendererChangeEvent;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.LegendItem;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetChangeEvent;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.ui.Layer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.DatasetChangeListener;
import org.jfree.chart.renderer.CategoryItemRenderer;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisSpace;
import java.util.List;
import java.util.Map;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.util.SortOrder;
import org.jfree.util.ObjectList;
import org.jfree.ui.Spacer;
import java.util.ResourceBundle;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;
import org.jfree.chart.event.RendererChangeListener;

public class CategoryPlot extends Plot implements ValueAxisPlot, RendererChangeListener, Cloneable, PublicCloneable, Serializable
{
    public static final boolean DEFAULT_DOMAIN_GRIDLINES_VISIBLE = false;
    public static final boolean DEFAULT_RANGE_GRIDLINES_VISIBLE = true;
    public static final Stroke DEFAULT_GRIDLINE_STROKE;
    public static final Paint DEFAULT_GRIDLINE_PAINT;
    public static final Font DEFAULT_VALUE_LABEL_FONT;
    protected static ResourceBundle localizationResources;
    private PlotOrientation orientation;
    private Spacer axisOffset;
    private ObjectList domainAxes;
    private ObjectList domainAxisLocations;
    private boolean drawSharedDomainAxis;
    private ObjectList rangeAxes;
    private ObjectList rangeAxisLocations;
    private ObjectList datasets;
    private ObjectList datasetToDomainAxisMap;
    private ObjectList datasetToRangeAxisMap;
    private ObjectList renderers;
    private DatasetRenderingOrder renderingOrder;
    private SortOrder columnRenderingOrder;
    private SortOrder rowRenderingOrder;
    private boolean domainGridlinesVisible;
    private CategoryAnchor domainGridlinePosition;
    private transient Stroke domainGridlineStroke;
    private transient Paint domainGridlinePaint;
    private boolean rangeGridlinesVisible;
    private transient Stroke rangeGridlineStroke;
    private transient Paint rangeGridlinePaint;
    private double anchorValue;
    private boolean rangeCrosshairVisible;
    private double rangeCrosshairValue;
    private transient Stroke rangeCrosshairStroke;
    private transient Paint rangeCrosshairPaint;
    private boolean rangeCrosshairLockedOnData;
    private transient Map foregroundRangeMarkers;
    private transient Map backgroundRangeMarkers;
    private transient List annotations;
    private int weight;
    private AxisSpace fixedDomainAxisSpace;
    private AxisSpace fixedRangeAxisSpace;
    private LegendItemCollection fixedLegendItems;
    
    public CategoryPlot() {
        this(null, null, null, null);
    }
    
    public CategoryPlot(final CategoryDataset dataset, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryItemRenderer renderer) {
        this.renderingOrder = DatasetRenderingOrder.REVERSE;
        this.columnRenderingOrder = SortOrder.ASCENDING;
        this.rowRenderingOrder = SortOrder.ASCENDING;
        this.rangeCrosshairLockedOnData = true;
        this.orientation = PlotOrientation.VERTICAL;
        this.domainAxes = new ObjectList();
        this.domainAxisLocations = new ObjectList();
        this.rangeAxes = new ObjectList();
        this.rangeAxisLocations = new ObjectList();
        this.datasetToDomainAxisMap = new ObjectList();
        this.datasetToRangeAxisMap = new ObjectList();
        this.renderers = new ObjectList();
        (this.datasets = new ObjectList()).set(0, dataset);
        if (dataset != null) {
            dataset.addChangeListener(this);
        }
        this.axisOffset = new Spacer(1, 0.0, 0.0, 0.0, 0.0);
        this.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT, false);
        this.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT, false);
        this.renderers.set(0, renderer);
        if (renderer != null) {
            renderer.setPlot(this);
            renderer.addChangeListener(this);
        }
        this.domainAxes.set(0, domainAxis);
        this.mapDatasetToDomainAxis(0, 0);
        if (domainAxis != null) {
            domainAxis.setPlot(this);
            domainAxis.addChangeListener(this);
        }
        this.drawSharedDomainAxis = false;
        this.rangeAxes.set(0, rangeAxis);
        this.mapDatasetToRangeAxis(0, 0);
        if (rangeAxis != null) {
            rangeAxis.setPlot(this);
            rangeAxis.addChangeListener(this);
        }
        this.configureDomainAxes();
        this.configureRangeAxes();
        this.domainGridlinesVisible = false;
        this.domainGridlinePosition = CategoryAnchor.MIDDLE;
        this.domainGridlineStroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
        this.domainGridlinePaint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
        this.rangeGridlinesVisible = true;
        this.rangeGridlineStroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
        this.rangeGridlinePaint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
        this.foregroundRangeMarkers = new HashMap();
        this.backgroundRangeMarkers = new HashMap();
        final Marker baseline = new ValueMarker(0.0, new Color(0.8f, 0.8f, 0.8f, 0.5f), new BasicStroke(1.0f), new Color(0.85f, 0.85f, 0.95f, 0.5f), new BasicStroke(1.0f), 0.6f);
        this.addRangeMarker(baseline, Layer.BACKGROUND);
        this.anchorValue = 0.0;
    }
    
    public String getPlotType() {
        return CategoryPlot.localizationResources.getString("Category_Plot");
    }
    
    public PlotOrientation getOrientation() {
        return this.orientation;
    }
    
    public void setOrientation(final PlotOrientation orientation) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        this.orientation = orientation;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Spacer getAxisOffset() {
        return this.axisOffset;
    }
    
    public void setAxisOffset(final Spacer offset) {
        this.axisOffset = offset;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public CategoryAxis getDomainAxis() {
        return this.getDomainAxis(0);
    }
    
    public CategoryAxis getDomainAxis(final int index) {
        CategoryAxis result = null;
        if (index < this.domainAxes.size()) {
            result = (CategoryAxis)this.domainAxes.get(index);
        }
        if (result == null) {
            final Plot parent = this.getParent();
            if (parent instanceof CategoryPlot) {
                final CategoryPlot cp = (CategoryPlot)parent;
                result = cp.getDomainAxis(index);
            }
        }
        return result;
    }
    
    public void setDomainAxis(final CategoryAxis axis) {
        this.setDomainAxis(0, axis);
    }
    
    public void setDomainAxis(final int index, final CategoryAxis axis) {
        final CategoryAxis existing = (CategoryAxis)this.domainAxes.get(index);
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        if (axis != null) {
            axis.setPlot(this);
        }
        this.domainAxes.set(index, axis);
        if (axis != null) {
            axis.configure();
            axis.addChangeListener(this);
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public AxisLocation getDomainAxisLocation() {
        return this.getDomainAxisLocation(0);
    }
    
    public AxisLocation getDomainAxisLocation(final int index) {
        AxisLocation result = null;
        if (index < this.domainAxisLocations.size()) {
            result = (AxisLocation)this.domainAxisLocations.get(index);
        }
        if (result == null) {
            result = AxisLocation.getOpposite(this.getDomainAxisLocation(0));
        }
        return result;
    }
    
    public void setDomainAxisLocation(final AxisLocation location) {
        this.setDomainAxisLocation(location, true);
    }
    
    public void setDomainAxisLocation(final AxisLocation location, final boolean notify) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        this.setDomainAxisLocation(0, location);
    }
    
    public void setDomainAxisLocation(final int index, final AxisLocation location) {
        this.domainAxisLocations.set(index, location);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public RectangleEdge getDomainAxisEdge() {
        return this.getDomainAxisEdge(0);
    }
    
    public RectangleEdge getDomainAxisEdge(final int index) {
        RectangleEdge result = null;
        final AxisLocation location = this.getDomainAxisLocation(index);
        if (location != null) {
            result = Plot.resolveDomainAxisLocation(location, this.orientation);
        }
        else {
            result = RectangleEdge.opposite(this.getDomainAxisEdge(0));
        }
        return result;
    }
    
    public void clearDomainAxes() {
        for (int i = 0; i < this.domainAxes.size(); ++i) {
            final CategoryAxis axis = (CategoryAxis)this.domainAxes.get(i);
            if (axis != null) {
                axis.removeChangeListener(this);
            }
        }
        this.domainAxes.clear();
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void configureDomainAxes() {
        for (int i = 0; i < this.domainAxes.size(); ++i) {
            final CategoryAxis axis = (CategoryAxis)this.domainAxes.get(i);
            if (axis != null) {
                axis.configure();
            }
        }
    }
    
    public ValueAxis getRangeAxis() {
        return this.getRangeAxis(0);
    }
    
    public ValueAxis getRangeAxis(final int index) {
        ValueAxis result = null;
        if (index < this.rangeAxes.size()) {
            result = (ValueAxis)this.rangeAxes.get(index);
        }
        if (result == null) {
            final Plot parent = this.getParent();
            if (parent instanceof CategoryPlot) {
                final CategoryPlot cp = (CategoryPlot)parent;
                result = cp.getRangeAxis(index);
            }
        }
        return result;
    }
    
    public void setRangeAxis(final ValueAxis axis) {
        this.setRangeAxis(0, axis);
    }
    
    public void setRangeAxis(final int index, final ValueAxis axis) {
        final ValueAxis existing = (ValueAxis)this.rangeAxes.get(index);
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        if (axis != null) {
            axis.setPlot(this);
        }
        this.rangeAxes.set(index, axis);
        if (axis != null) {
            axis.configure();
            axis.addChangeListener(this);
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public AxisLocation getRangeAxisLocation() {
        return this.getRangeAxisLocation(0);
    }
    
    public AxisLocation getRangeAxisLocation(final int index) {
        AxisLocation result = null;
        if (index < this.rangeAxisLocations.size()) {
            result = (AxisLocation)this.rangeAxisLocations.get(index);
        }
        if (result == null) {
            result = AxisLocation.getOpposite(this.getRangeAxisLocation(0));
        }
        return result;
    }
    
    public void setRangeAxisLocation(final AxisLocation location) {
        this.setRangeAxisLocation(location, true);
    }
    
    public void setRangeAxisLocation(final AxisLocation location, final boolean notify) {
        this.setRangeAxisLocation(0, location, notify);
    }
    
    public void setRangeAxisLocation(final int index, final AxisLocation location) {
        this.setRangeAxisLocation(index, location, true);
    }
    
    public void setRangeAxisLocation(final int index, final AxisLocation location, final boolean notify) {
        this.rangeAxisLocations.set(index, location);
        if (notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public RectangleEdge getRangeAxisEdge() {
        return this.getRangeAxisEdge(0);
    }
    
    public RectangleEdge getRangeAxisEdge(final int index) {
        final AxisLocation location = this.getRangeAxisLocation(index);
        RectangleEdge result = Plot.resolveRangeAxisLocation(location, this.orientation);
        if (result == null) {
            result = RectangleEdge.opposite(this.getRangeAxisEdge(0));
        }
        return result;
    }
    
    public void clearRangeAxes() {
        for (int i = 0; i < this.rangeAxes.size(); ++i) {
            final ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
            if (axis != null) {
                axis.removeChangeListener(this);
            }
        }
        this.rangeAxes.clear();
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void configureRangeAxes() {
        for (int i = 0; i < this.rangeAxes.size(); ++i) {
            final ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
            if (axis != null) {
                axis.configure();
            }
        }
    }
    
    public CategoryDataset getDataset() {
        return this.getDataset(0);
    }
    
    public CategoryDataset getDataset(final int index) {
        CategoryDataset result = null;
        if (this.datasets.size() > index) {
            result = (CategoryDataset)this.datasets.get(index);
        }
        return result;
    }
    
    public void setDataset(final CategoryDataset dataset) {
        this.setDataset(0, dataset);
    }
    
    public void setDataset(final int index, final CategoryDataset dataset) {
        final CategoryDataset existing = (CategoryDataset)this.datasets.get(index);
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        this.datasets.set(index, dataset);
        if (dataset != null) {
            dataset.addChangeListener(this);
        }
        final DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        this.datasetChanged(event);
    }
    
    public void mapDatasetToDomainAxis(final int index, final int axisIndex) {
        this.datasetToDomainAxisMap.set(index, new Integer(axisIndex));
        this.datasetChanged(new DatasetChangeEvent(this, this.getDataset(index)));
    }
    
    public CategoryAxis getDomainAxisForDataset(final int index) {
        CategoryAxis result = this.getDomainAxis();
        final Integer axisIndex = (Integer)this.datasetToDomainAxisMap.get(index);
        if (axisIndex != null) {
            result = this.getDomainAxis(axisIndex);
        }
        return result;
    }
    
    public void mapDatasetToRangeAxis(final int index, final int axisIndex) {
        this.datasetToRangeAxisMap.set(index, new Integer(axisIndex));
        this.datasetChanged(new DatasetChangeEvent(this, this.getDataset(index)));
    }
    
    public ValueAxis getRangeAxisForDataset(final int index) {
        ValueAxis result = this.getRangeAxis();
        final Integer axisIndex = (Integer)this.datasetToRangeAxisMap.get(index);
        if (axisIndex != null) {
            result = this.getRangeAxis(axisIndex);
        }
        return result;
    }
    
    public CategoryItemRenderer getRenderer() {
        return this.getRenderer(0);
    }
    
    public CategoryItemRenderer getRenderer(final int index) {
        CategoryItemRenderer result = null;
        if (this.renderers.size() > index) {
            result = (CategoryItemRenderer)this.renderers.get(index);
        }
        return result;
    }
    
    public void setRenderer(final CategoryItemRenderer renderer) {
        this.setRenderer(0, renderer, true);
    }
    
    public void setRenderer(final CategoryItemRenderer renderer, final boolean notify) {
        this.setRenderer(0, renderer, notify);
    }
    
    public void setRenderer(final int index, final CategoryItemRenderer renderer) {
        this.setRenderer(index, renderer, true);
    }
    
    public void setRenderer(final int index, final CategoryItemRenderer renderer, final boolean notify) {
        final CategoryItemRenderer existing = (CategoryItemRenderer)this.renderers.get(index);
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        this.renderers.set(index, renderer);
        if (renderer != null) {
            renderer.setPlot(this);
            renderer.addChangeListener(this);
        }
        this.configureDomainAxes();
        this.configureRangeAxes();
        if (notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public CategoryItemRenderer getRendererForDataset(final CategoryDataset d) {
        CategoryItemRenderer result = null;
        for (int i = 0; i < this.datasets.size(); ++i) {
            if (this.datasets.get(i) == d) {
                result = (CategoryItemRenderer)this.renderers.get(i);
                break;
            }
        }
        return result;
    }
    
    public DatasetRenderingOrder getDatasetRenderingOrder() {
        return this.renderingOrder;
    }
    
    public void setDatasetRenderingOrder(final DatasetRenderingOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Null 'order' argument.");
        }
        this.renderingOrder = order;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public SortOrder getColumnRenderingOrder() {
        return this.columnRenderingOrder;
    }
    
    public void setColumnRenderingOrder(final SortOrder order) {
        this.columnRenderingOrder = order;
    }
    
    public SortOrder getRowRenderingOrder() {
        return this.rowRenderingOrder;
    }
    
    public void setRowRenderingOrder(final SortOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Null 'order' argument.");
        }
        this.rowRenderingOrder = order;
    }
    
    public boolean isDomainGridlinesVisible() {
        return this.domainGridlinesVisible;
    }
    
    public void setDomainGridlinesVisible(final boolean visible) {
        if (this.domainGridlinesVisible != visible) {
            this.domainGridlinesVisible = visible;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public CategoryAnchor getDomainGridlinePosition() {
        return this.domainGridlinePosition;
    }
    
    public void setDomainGridlinePosition(final CategoryAnchor position) {
        this.domainGridlinePosition = position;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Stroke getDomainGridlineStroke() {
        return this.domainGridlineStroke;
    }
    
    public void setDomainGridlineStroke(final Stroke stroke) {
        this.domainGridlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getDomainGridlinePaint() {
        return this.domainGridlinePaint;
    }
    
    public void setDomainGridlinePaint(final Paint paint) {
        this.domainGridlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean isRangeGridlinesVisible() {
        return this.rangeGridlinesVisible;
    }
    
    public void setRangeGridlinesVisible(final boolean visible) {
        if (this.rangeGridlinesVisible != visible) {
            this.rangeGridlinesVisible = visible;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Stroke getRangeGridlineStroke() {
        return this.rangeGridlineStroke;
    }
    
    public void setRangeGridlineStroke(final Stroke stroke) {
        this.rangeGridlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getRangeGridlinePaint() {
        return this.rangeGridlinePaint;
    }
    
    public void setRangeGridlinePaint(final Paint paint) {
        this.rangeGridlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public LegendItemCollection getFixedLegendItems() {
        return this.fixedLegendItems;
    }
    
    public void setFixedLegendItems(final LegendItemCollection items) {
        this.fixedLegendItems = items;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public LegendItemCollection getLegendItems() {
        LegendItemCollection result = this.fixedLegendItems;
        if (result == null) {
            result = new LegendItemCollection();
            for (int count = this.datasets.size(), datasetIndex = 0; datasetIndex < count; ++datasetIndex) {
                final CategoryDataset dataset = this.getDataset(datasetIndex);
                if (dataset != null) {
                    final CategoryItemRenderer renderer = this.getRenderer(datasetIndex);
                    if (renderer != null) {
                        for (int seriesCount = dataset.getRowCount(), i = 0; i < seriesCount; ++i) {
                            final LegendItem item = renderer.getLegendItem(datasetIndex, i);
                            if (item != null) {
                                result.add(item);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public void handleClick(final int x, final int y, final PlotRenderingInfo info) {
        final Rectangle2D dataArea = info.getDataArea();
        if (dataArea.contains(x, y)) {
            double java2D = 0.0;
            if (this.orientation == PlotOrientation.HORIZONTAL) {
                java2D = x;
            }
            else if (this.orientation == PlotOrientation.VERTICAL) {
                java2D = y;
            }
            final RectangleEdge edge = Plot.resolveRangeAxisLocation(this.getRangeAxisLocation(), this.orientation);
            final double value = this.getRangeAxis().java2DToValue(java2D, info.getDataArea(), edge);
            this.setAnchorValue(value);
            this.setRangeCrosshairValue(value);
        }
    }
    
    public void zoom(final double percent) {
        if (percent > 0.0) {
            final double range = this.getRangeAxis().getRange().getLength();
            final double scaledRange = range * percent;
            this.getRangeAxis().setRange(this.anchorValue - scaledRange / 2.0, this.anchorValue + scaledRange / 2.0);
        }
        else {
            this.getRangeAxis().setAutoRange(true);
        }
    }
    
    public void datasetChanged(final DatasetChangeEvent event) {
        for (int count = this.rangeAxes.size(), axisIndex = 0; axisIndex < count; ++axisIndex) {
            final ValueAxis yAxis = this.getRangeAxis(axisIndex);
            if (yAxis != null) {
                yAxis.configure();
            }
        }
        if (this.getParent() != null) {
            this.getParent().datasetChanged(event);
        }
        else {
            final PlotChangeEvent e = new PlotChangeEvent(this);
            this.notifyListeners(e);
        }
    }
    
    public void rendererChanged(final RendererChangeEvent event) {
        final Plot parent = this.getParent();
        if (parent != null) {
            if (!(parent instanceof RendererChangeListener)) {
                throw new RuntimeException("The renderer has changed and I don't know what to do!");
            }
            final RendererChangeListener rcl = (RendererChangeListener)parent;
            rcl.rendererChanged(event);
        }
        else {
            final PlotChangeEvent e = new PlotChangeEvent(this);
            this.notifyListeners(e);
        }
    }
    
    public void addRangeMarker(final Marker marker) {
        this.addRangeMarker(marker, Layer.FOREGROUND);
    }
    
    public void addRangeMarker(final Marker marker, final Layer layer) {
        this.addRangeMarker(0, marker, layer);
    }
    
    public void addRangeMarker(final int index, final Marker marker, final Layer layer) {
        if (layer == Layer.FOREGROUND) {
            Collection markers = this.foregroundRangeMarkers.get(new Integer(index));
            if (markers == null) {
                markers = new ArrayList();
                this.foregroundRangeMarkers.put(new Integer(index), markers);
            }
            markers.add(marker);
        }
        else if (layer == Layer.BACKGROUND) {
            Collection markers = this.backgroundRangeMarkers.get(new Integer(index));
            if (markers == null) {
                markers = new ArrayList();
                this.backgroundRangeMarkers.put(new Integer(index), markers);
            }
            markers.add(marker);
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void clearRangeMarkers() {
        if (this.backgroundRangeMarkers != null) {
            this.backgroundRangeMarkers.clear();
        }
        if (this.foregroundRangeMarkers != null) {
            this.foregroundRangeMarkers.clear();
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Collection getRangeMarkers(final Layer layer) {
        return this.getRangeMarkers(0, layer);
    }
    
    public Collection getRangeMarkers(final int index, final Layer layer) {
        Collection result = null;
        final Integer key = new Integer(index);
        if (layer == Layer.FOREGROUND) {
            result = this.foregroundRangeMarkers.get(key);
        }
        else if (layer == Layer.BACKGROUND) {
            result = this.backgroundRangeMarkers.get(key);
        }
        if (result != null) {
            result = Collections.unmodifiableCollection((Collection<?>)result);
        }
        return result;
    }
    
    public void clearRangeMarkers(final int index) {
        final Integer key = new Integer(index);
        if (this.backgroundRangeMarkers != null) {
            final Collection markers = this.backgroundRangeMarkers.get(key);
            if (markers != null) {
                markers.clear();
            }
        }
        if (this.foregroundRangeMarkers != null) {
            final Collection markers = this.foregroundRangeMarkers.get(key);
            if (markers != null) {
                markers.clear();
            }
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean isRangeCrosshairVisible() {
        return this.rangeCrosshairVisible;
    }
    
    public void setRangeCrosshairVisible(final boolean flag) {
        if (this.rangeCrosshairVisible != flag) {
            this.rangeCrosshairVisible = flag;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public boolean isRangeCrosshairLockedOnData() {
        return this.rangeCrosshairLockedOnData;
    }
    
    public void setRangeCrosshairLockedOnData(final boolean flag) {
        if (this.rangeCrosshairLockedOnData != flag) {
            this.rangeCrosshairLockedOnData = flag;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public double getRangeCrosshairValue() {
        return this.rangeCrosshairValue;
    }
    
    public void setRangeCrosshairValue(final double value) {
        this.setRangeCrosshairValue(value, true);
    }
    
    public void setRangeCrosshairValue(final double value, final boolean notify) {
        this.rangeCrosshairValue = value;
        if (this.isRangeCrosshairVisible() && notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Stroke getRangeCrosshairStroke() {
        return this.rangeCrosshairStroke;
    }
    
    public void setRangeCrosshairStroke(final Stroke stroke) {
        this.rangeCrosshairStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getRangeCrosshairPaint() {
        return this.rangeCrosshairPaint;
    }
    
    public void setRangeCrosshairPaint(final Paint paint) {
        this.rangeCrosshairPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public List getAnnotations() {
        return this.annotations;
    }
    
    public void addAnnotation(final CategoryAnnotation annotation) {
        if (this.annotations == null) {
            this.annotations = new ArrayList();
        }
        this.annotations.add(annotation);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    protected AxisSpace calculateDomainAxisSpace(final Graphics2D g2, final Rectangle2D plotArea, AxisSpace space) {
        if (space == null) {
            space = new AxisSpace();
        }
        if (this.fixedDomainAxisSpace != null) {
            if (this.orientation == PlotOrientation.HORIZONTAL) {
                space.ensureAtLeast(this.fixedDomainAxisSpace.getLeft(), RectangleEdge.LEFT);
                space.ensureAtLeast(this.fixedDomainAxisSpace.getRight(), RectangleEdge.RIGHT);
            }
            else if (this.orientation == PlotOrientation.VERTICAL) {
                space.ensureAtLeast(this.fixedDomainAxisSpace.getTop(), RectangleEdge.TOP);
                space.ensureAtLeast(this.fixedDomainAxisSpace.getBottom(), RectangleEdge.BOTTOM);
            }
        }
        else {
            final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(this.getDomainAxisLocation(), this.orientation);
            if (this.drawSharedDomainAxis) {
                space = this.getDomainAxis().reserveSpace(g2, this, plotArea, domainEdge, space);
            }
            for (int i = 0; i < this.domainAxes.size(); ++i) {
                final Axis xAxis = (Axis)this.domainAxes.get(i);
                if (xAxis != null) {
                    final RectangleEdge edge = this.getDomainAxisEdge(i);
                    space = xAxis.reserveSpace(g2, this, plotArea, edge, space);
                }
            }
        }
        return space;
    }
    
    protected AxisSpace calculateRangeAxisSpace(final Graphics2D g2, final Rectangle2D plotArea, AxisSpace space) {
        if (space == null) {
            space = new AxisSpace();
        }
        if (this.fixedRangeAxisSpace != null) {
            if (this.orientation == PlotOrientation.HORIZONTAL) {
                space.ensureAtLeast(this.fixedRangeAxisSpace.getTop(), RectangleEdge.TOP);
                space.ensureAtLeast(this.fixedRangeAxisSpace.getBottom(), RectangleEdge.BOTTOM);
            }
            else if (this.orientation == PlotOrientation.VERTICAL) {
                space.ensureAtLeast(this.fixedRangeAxisSpace.getLeft(), RectangleEdge.LEFT);
                space.ensureAtLeast(this.fixedRangeAxisSpace.getRight(), RectangleEdge.RIGHT);
            }
        }
        else {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final Axis yAxis = (Axis)this.rangeAxes.get(i);
                if (yAxis != null) {
                    final RectangleEdge edge = this.getRangeAxisEdge(i);
                    space = yAxis.reserveSpace(g2, this, plotArea, edge, space);
                }
            }
        }
        return space;
    }
    
    protected AxisSpace calculateAxisSpace(final Graphics2D g2, final Rectangle2D plotArea) {
        AxisSpace space = new AxisSpace();
        space = this.calculateRangeAxisSpace(g2, plotArea, space);
        space = this.calculateDomainAxisSpace(g2, plotArea, space);
        return space;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D plotArea, final PlotState parentState, final PlotRenderingInfo state) {
        final boolean b1 = plotArea.getWidth() <= 10.0;
        final boolean b2 = plotArea.getHeight() <= 10.0;
        if (b1 || b2) {
            return;
        }
        if (state != null) {
            state.setPlotArea(plotArea);
        }
        final Insets insets = this.getInsets();
        if (insets != null) {
            plotArea.setRect(plotArea.getX() + insets.left, plotArea.getY() + insets.top, plotArea.getWidth() - insets.left - insets.right, plotArea.getHeight() - insets.top - insets.bottom);
        }
        final AxisSpace space = this.calculateAxisSpace(g2, plotArea);
        final Rectangle2D dataArea = space.shrink(plotArea, null);
        this.axisOffset.trim(dataArea);
        if (state != null) {
            state.setDataArea(dataArea);
        }
        if (this.getRenderer() != null) {
            this.getRenderer().drawBackground(g2, this, dataArea);
        }
        else {
            this.drawBackground(g2, dataArea);
        }
        final Map axisStateMap = this.drawAxes(g2, plotArea, dataArea, state);
        this.drawDomainGridlines(g2, dataArea);
        AxisState rangeAxisState = axisStateMap.get(this.getRangeAxis());
        if (rangeAxisState == null && parentState != null) {
            rangeAxisState = parentState.getSharedAxisStates().get(this.getRangeAxis());
        }
        if (rangeAxisState != null) {
            this.drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
        }
        for (int i = 0; i < this.renderers.size(); ++i) {
            this.drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND);
        }
        this.drawRangeMarkers(g2, dataArea, Layer.BACKGROUND);
        boolean foundData = false;
        final Shape savedClip = g2.getClip();
        g2.clip(dataArea);
        final Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(3, this.getForegroundAlpha()));
        final DatasetRenderingOrder order = this.getDatasetRenderingOrder();
        if (order == DatasetRenderingOrder.FORWARD) {
            for (int j = 0; j < this.datasets.size(); ++j) {
                foundData = (this.render(g2, dataArea, j, state) || foundData);
            }
        }
        else {
            for (int j = this.datasets.size() - 1; j >= 0; --j) {
                foundData = (this.render(g2, dataArea, j, state) || foundData);
            }
        }
        g2.setClip(savedClip);
        g2.setComposite(originalComposite);
        if (!foundData) {
            this.drawNoDataMessage(g2, dataArea);
        }
        if (this.isRangeCrosshairVisible()) {
            this.drawRangeLine(g2, dataArea, this.getRangeCrosshairValue(), this.getRangeCrosshairStroke(), this.getRangeCrosshairPaint());
        }
        for (int j = 0; j < this.renderers.size(); ++j) {
            this.drawRangeMarkers(g2, dataArea, j, Layer.FOREGROUND);
        }
        this.drawRangeMarkers(g2, dataArea, Layer.FOREGROUND);
        this.drawAnnotations(g2, dataArea);
        if (this.getRenderer() != null) {
            this.getRenderer().drawOutline(g2, this, dataArea);
        }
        else {
            this.drawOutline(g2, dataArea);
        }
    }
    
    protected Map drawAxes(final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D dataArea, final PlotRenderingInfo plotState) {
        final AxisCollection axisCollection = new AxisCollection();
        for (int index = 0; index < this.domainAxes.size(); ++index) {
            final CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(index);
            if (xAxis != null) {
                axisCollection.add(xAxis, this.getDomainAxisEdge(index));
            }
        }
        for (int index = 0; index < this.rangeAxes.size(); ++index) {
            final ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(index);
            if (yAxis != null) {
                axisCollection.add(yAxis, this.getRangeAxisEdge(index));
            }
        }
        final Map axisStateMap = new HashMap();
        double cursor = dataArea.getMinY() - this.axisOffset.getTopSpace(dataArea.getHeight());
        for (final Axis axis : axisCollection.getAxesAtTop()) {
            if (axis != null) {
                final AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.TOP, plotState);
                cursor = axisState.getCursor();
                axisStateMap.put(axis, axisState);
            }
        }
        cursor = dataArea.getMaxY() + this.axisOffset.getBottomSpace(dataArea.getHeight());
        for (final Axis axis : axisCollection.getAxesAtBottom()) {
            if (axis != null) {
                final AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.BOTTOM, plotState);
                cursor = axisState.getCursor();
                axisStateMap.put(axis, axisState);
            }
        }
        cursor = dataArea.getMinX() - this.axisOffset.getLeftSpace(dataArea.getWidth());
        for (final Axis axis : axisCollection.getAxesAtLeft()) {
            if (axis != null) {
                final AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.LEFT, plotState);
                cursor = axisState.getCursor();
                axisStateMap.put(axis, axisState);
            }
        }
        cursor = dataArea.getMaxX() + this.axisOffset.getRightSpace(dataArea.getWidth());
        for (final Axis axis : axisCollection.getAxesAtRight()) {
            if (axis != null) {
                final AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.RIGHT, plotState);
                cursor = axisState.getCursor();
                axisStateMap.put(axis, axisState);
            }
        }
        return axisStateMap;
    }
    
    public boolean render(final Graphics2D g2, final Rectangle2D dataArea, final int index, final PlotRenderingInfo info) {
        boolean foundData = false;
        final CategoryDataset currentDataset = this.getDataset(index);
        final CategoryItemRenderer renderer = this.getRenderer(index);
        final CategoryAxis domainAxis = this.getDomainAxisForDataset(index);
        final ValueAxis rangeAxis = this.getRangeAxisForDataset(index);
        if (!DatasetUtilities.isEmptyOrNull(currentDataset) && renderer != null) {
            foundData = true;
            final CategoryItemRendererState state = renderer.initialise(g2, dataArea, this, index, info);
            final int columnCount = currentDataset.getColumnCount();
            final int rowCount = currentDataset.getRowCount();
            if (this.columnRenderingOrder == SortOrder.ASCENDING) {
                for (int column = 0; column < columnCount; ++column) {
                    if (this.rowRenderingOrder == SortOrder.ASCENDING) {
                        for (int row = 0; row < rowCount; ++row) {
                            renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column);
                        }
                    }
                    else {
                        for (int row = rowCount - 1; row >= 0; --row) {
                            renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column);
                        }
                    }
                }
            }
            else {
                for (int column = columnCount - 1; column >= 0; --column) {
                    if (this.rowRenderingOrder == SortOrder.ASCENDING) {
                        for (int row = 0; row < rowCount; ++row) {
                            renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column);
                        }
                    }
                    else {
                        for (int row = rowCount - 1; row >= 0; --row) {
                            renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column);
                        }
                    }
                }
            }
        }
        return foundData;
    }
    
    protected void drawDomainGridlines(final Graphics2D g2, final Rectangle2D dataArea) {
        if (this.isDomainGridlinesVisible()) {
            final CategoryAnchor anchor = this.getDomainGridlinePosition();
            final RectangleEdge domainAxisEdge = this.getDomainAxisEdge();
            final Stroke gridStroke = this.getDomainGridlineStroke();
            final Paint gridPaint = this.getDomainGridlinePaint();
            if (gridStroke != null && gridPaint != null) {
                final CategoryDataset data = this.getDataset();
                if (data != null) {
                    final CategoryAxis axis = this.getDomainAxis();
                    if (axis != null) {
                        for (int columnCount = data.getColumnCount(), c = 0; c < columnCount; ++c) {
                            final double xx = axis.getCategoryJava2DCoordinate(anchor, c, columnCount, dataArea, domainAxisEdge);
                            final CategoryItemRenderer renderer1 = this.getRenderer();
                            if (renderer1 != null) {
                                renderer1.drawDomainGridline(g2, this, dataArea, xx);
                            }
                        }
                    }
                }
            }
        }
    }
    
    protected void drawRangeGridlines(final Graphics2D g2, final Rectangle2D dataArea, final List ticks) {
        if (this.isRangeGridlinesVisible()) {
            final Stroke gridStroke = this.getRangeGridlineStroke();
            final Paint gridPaint = this.getRangeGridlinePaint();
            if (gridStroke != null && gridPaint != null) {
                final ValueAxis axis = this.getRangeAxis();
                if (axis != null) {
                    for (final ValueTick tick : ticks) {
                        final CategoryItemRenderer renderer1 = this.getRenderer();
                        if (renderer1 != null) {
                            renderer1.drawRangeGridline(g2, this, this.getRangeAxis(), dataArea, tick.getValue());
                        }
                    }
                }
            }
        }
    }
    
    protected void drawAnnotations(final Graphics2D g2, final Rectangle2D dataArea) {
        if (this.getAnnotations() != null) {
            for (final CategoryAnnotation annotation : this.getAnnotations()) {
                annotation.draw(g2, this, dataArea, this.getDomainAxis(), this.getRangeAxis());
            }
        }
    }
    
    protected void drawRangeMarkers(final Graphics2D g2, final Rectangle2D dataArea, final Layer layer) {
        final CategoryItemRenderer r = this.getRenderer();
        final Collection markers = this.getRangeMarkers(layer);
        if (markers != null && r != null) {
            for (final Marker marker : markers) {
                r.drawRangeMarker(g2, this, this.getRangeAxis(), marker, dataArea);
            }
        }
    }
    
    protected void drawRangeMarkers(final Graphics2D g2, final Rectangle2D dataArea, final int index, final Layer layer) {
        final CategoryItemRenderer r = this.getRenderer(index);
        if (r == null) {
            return;
        }
        final Collection markers = this.getRangeMarkers(index, layer);
        final ValueAxis axis = this.getRangeAxisForDataset(index);
        if (markers != null && axis != null) {
            for (final Marker marker : markers) {
                r.drawRangeMarker(g2, this, axis, marker, dataArea);
            }
        }
    }
    
    protected void drawRangeLine(final Graphics2D g2, final Rectangle2D dataArea, final double value, final Stroke stroke, final Paint paint) {
        final double java2D = this.getRangeAxis().valueToJava2D(value, dataArea, this.getRangeAxisEdge());
        Line2D line = null;
        if (this.orientation == PlotOrientation.HORIZONTAL) {
            line = new Line2D.Double(java2D, dataArea.getMinY(), java2D, dataArea.getMaxY());
        }
        else if (this.orientation == PlotOrientation.VERTICAL) {
            line = new Line2D.Double(dataArea.getMinX(), java2D, dataArea.getMaxX(), java2D);
        }
        g2.setStroke(stroke);
        g2.setPaint(paint);
        g2.draw(line);
    }
    
    public Range getDataRange(final ValueAxis axis) {
        Range result = null;
        final List mappedDatasets = new ArrayList();
        final int rangeIndex = this.rangeAxes.indexOf(axis);
        if (rangeIndex >= 0) {
            mappedDatasets.addAll(this.getDatasetsMappedToRangeAxis(rangeIndex));
        }
        else if (axis == this.getRangeAxis()) {
            mappedDatasets.addAll(this.getDatasetsMappedToRangeAxis(0));
        }
        for (final CategoryDataset d : mappedDatasets) {
            final CategoryItemRenderer r = this.getRendererForDataset(d);
            if (r != null) {
                result = Range.combine(result, r.getRangeExtent(d));
            }
        }
        return result;
    }
    
    private List getDatasetsMappedToRangeAxis(final int index) {
        final List result = new ArrayList();
        for (int i = 0; i < this.datasets.size(); ++i) {
            final Integer m = (Integer)this.datasetToRangeAxisMap.get(i);
            if (m == null) {
                if (index == 0) {
                    result.add(this.datasets.get(i));
                }
            }
            else if (m == index) {
                result.add(this.datasets.get(i));
            }
        }
        return result;
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public void setWeight(final int weight) {
        this.weight = weight;
    }
    
    public AxisSpace getFixedDomainAxisSpace() {
        return this.fixedDomainAxisSpace;
    }
    
    public void setFixedDomainAxisSpace(final AxisSpace space) {
        this.fixedDomainAxisSpace = space;
    }
    
    public AxisSpace getFixedRangeAxisSpace() {
        return this.fixedRangeAxisSpace;
    }
    
    public void setFixedRangeAxisSpace(final AxisSpace space) {
        this.fixedRangeAxisSpace = space;
    }
    
    public List getCategories() {
        List result = null;
        if (this.getDataset() != null) {
            result = Collections.unmodifiableList((List<?>)this.getDataset().getColumnKeys());
        }
        return result;
    }
    
    public boolean getDrawSharedDomainAxis() {
        return this.drawSharedDomainAxis;
    }
    
    public void setDrawSharedDomainAxis(final boolean draw) {
        this.drawSharedDomainAxis = draw;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void zoomHorizontalAxes(final double factor) {
        if (this.orientation == PlotOrientation.HORIZONTAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.resizeRange(factor);
                }
            }
        }
    }
    
    public void zoomHorizontalAxes(final double lowerPercent, final double upperPercent) {
        if (this.orientation == PlotOrientation.HORIZONTAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.zoomRange(lowerPercent, upperPercent);
                }
            }
        }
    }
    
    public void zoomVerticalAxes(final double factor) {
        if (this.orientation == PlotOrientation.VERTICAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.resizeRange(factor);
                }
            }
        }
    }
    
    public void zoomVerticalAxes(final double lowerPercent, final double upperPercent) {
        if (this.orientation == PlotOrientation.VERTICAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.zoomRange(lowerPercent, upperPercent);
                }
            }
        }
    }
    
    public double getAnchorValue() {
        return this.anchorValue;
    }
    
    public void setAnchorValue(final double value) {
        this.setAnchorValue(value, true);
    }
    
    public void setAnchorValue(final double value, final boolean notify) {
        this.anchorValue = value;
        if (notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof CategoryPlot && super.equals(object)) {
            final CategoryPlot p = (CategoryPlot)object;
            final boolean b0 = this.orientation == p.orientation;
            final boolean b2 = ObjectUtils.equal(this.axisOffset, p.axisOffset);
            final boolean b3 = this.domainAxes.equals(p.domainAxes);
            final boolean b4 = this.domainAxisLocations.equals(p.domainAxisLocations);
            final boolean b5 = this.drawSharedDomainAxis == p.drawSharedDomainAxis;
            final boolean b6 = this.rangeAxes.equals(p.rangeAxes);
            final boolean b7 = this.rangeAxisLocations.equals(p.rangeAxisLocations);
            final boolean b8 = ObjectUtils.equal(this.datasetToDomainAxisMap, p.datasetToDomainAxisMap);
            final boolean b9 = ObjectUtils.equal(this.datasetToRangeAxisMap, p.datasetToRangeAxisMap);
            final boolean b10 = ObjectUtils.equal(this.renderers, p.renderers);
            final boolean b11 = this.renderingOrder == p.renderingOrder;
            final boolean b12 = this.columnRenderingOrder == p.columnRenderingOrder;
            final boolean b13 = this.rowRenderingOrder == p.rowRenderingOrder;
            final boolean b14 = this.domainGridlinesVisible == p.domainGridlinesVisible;
            final boolean b15 = this.domainGridlinePosition == p.domainGridlinePosition;
            final boolean b16 = ObjectUtils.equal(this.domainGridlineStroke, p.domainGridlineStroke);
            final boolean b17 = ObjectUtils.equal(this.domainGridlinePaint, p.domainGridlinePaint);
            final boolean b18 = this.rangeGridlinesVisible == p.rangeGridlinesVisible;
            final boolean b19 = ObjectUtils.equal(this.rangeGridlineStroke, p.rangeGridlineStroke);
            final boolean b20 = ObjectUtils.equal(this.rangeGridlinePaint, p.rangeGridlinePaint);
            final boolean b21 = this.anchorValue == p.anchorValue;
            final boolean b22 = this.rangeCrosshairVisible == p.rangeCrosshairVisible;
            final boolean b23 = this.rangeCrosshairValue == p.rangeCrosshairValue;
            final boolean b24 = ObjectUtils.equal(this.rangeCrosshairStroke, p.rangeCrosshairStroke);
            final boolean b25 = ObjectUtils.equal(this.rangeCrosshairPaint, p.rangeCrosshairPaint);
            final boolean b26 = this.rangeCrosshairLockedOnData == p.rangeCrosshairLockedOnData;
            final boolean b27 = ObjectUtils.equal(this.foregroundRangeMarkers, p.foregroundRangeMarkers);
            final boolean b28 = ObjectUtils.equal(this.backgroundRangeMarkers, p.backgroundRangeMarkers);
            final boolean b29 = ObjectUtils.equal(this.annotations, p.annotations);
            final boolean b30 = this.weight == p.weight;
            final boolean b31 = ObjectUtils.equal(this.fixedDomainAxisSpace, p.fixedDomainAxisSpace);
            final boolean b32 = ObjectUtils.equal(this.fixedRangeAxisSpace, p.fixedRangeAxisSpace);
            return b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10 && b11 && b12 && b13 && b14 && b15 && b16 && b17 && b18 && b19 && b20 && b21 && b22 && b23 && b24 && b25 && b26 && b27 && b28 && b29 && b30 && b31 && b32;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final CategoryPlot clone = (CategoryPlot)super.clone();
        clone.domainAxes = new ObjectList();
        for (int i = 0; i < this.domainAxes.size(); ++i) {
            final CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(i);
            if (xAxis != null) {
                final CategoryAxis clonedAxis = (CategoryAxis)xAxis.clone();
                clone.setDomainAxis(i, clonedAxis);
            }
        }
        clone.domainAxisLocations = (ObjectList)this.domainAxisLocations.clone();
        clone.rangeAxes = new ObjectList();
        for (int i = 0; i < this.rangeAxes.size(); ++i) {
            final ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(i);
            if (yAxis != null) {
                final ValueAxis clonedAxis2 = (ValueAxis)yAxis.clone();
                clone.setRangeAxis(i, clonedAxis2);
            }
        }
        clone.rangeAxisLocations = (ObjectList)this.rangeAxisLocations.clone();
        clone.datasets = (ObjectList)this.datasets.clone();
        for (int i = 0; i < clone.datasets.size(); ++i) {
            final CategoryDataset dataset = clone.getDataset(i);
            if (dataset != null) {
                dataset.addChangeListener(clone);
            }
        }
        clone.datasetToDomainAxisMap = (ObjectList)this.datasetToDomainAxisMap.clone();
        clone.datasetToRangeAxisMap = (ObjectList)this.datasetToRangeAxisMap.clone();
        clone.renderers = (ObjectList)this.renderers.clone();
        clone.fixedDomainAxisSpace = (AxisSpace)ObjectUtils.clone(this.fixedDomainAxisSpace);
        clone.fixedRangeAxisSpace = (AxisSpace)ObjectUtils.clone(this.fixedRangeAxisSpace);
        return clone;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
        SerialUtilities.writePaint(this.domainGridlinePaint, stream);
        SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
        SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
        SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
        SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.domainGridlineStroke = SerialUtilities.readStroke(stream);
        this.domainGridlinePaint = SerialUtilities.readPaint(stream);
        this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
        this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
        this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
        this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
        for (int i = 0; i < this.domainAxes.size(); ++i) {
            final CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(i);
            if (xAxis != null) {
                xAxis.setPlot(this);
            }
        }
        for (int i = 0; i < this.rangeAxes.size(); ++i) {
            final ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(i);
            if (yAxis != null) {
                yAxis.setPlot(this);
            }
        }
        this.foregroundRangeMarkers = new HashMap();
        this.backgroundRangeMarkers = new HashMap();
        final Marker baseline = new ValueMarker(0.0, new Color(0.8f, 0.8f, 0.8f, 0.5f), new BasicStroke(1.0f), new Color(0.85f, 0.85f, 0.95f, 0.5f), new BasicStroke(1.0f), 0.6f);
        this.addRangeMarker(baseline, Layer.BACKGROUND);
    }
    
    static {
        DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5f, 0, 2, 0.0f, new float[] { 2.0f, 2.0f }, 0.0f);
        DEFAULT_GRIDLINE_PAINT = Color.lightGray;
        DEFAULT_VALUE_LABEL_FONT = new Font("SansSerif", 0, 10);
        CategoryPlot.localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
    }
}
