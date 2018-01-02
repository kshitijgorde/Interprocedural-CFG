// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import org.jfree.util.Log;
import java.awt.Color;
import java.awt.BasicStroke;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ObjectUtils;
import org.jfree.chart.LegendItem;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.data.Range;
import java.awt.geom.Line2D;
import java.util.Collections;
import org.jfree.chart.renderer.XYItemRendererState;
import org.jfree.data.DatasetUtilities;
import org.jfree.chart.axis.AxisCollection;
import java.util.Iterator;
import org.jfree.chart.axis.ValueTick;
import java.awt.Insets;
import org.jfree.chart.axis.AxisState;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Shape;
import org.jfree.chart.axis.Axis;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.annotations.XYAnnotation;
import java.util.ArrayList;
import java.util.Collection;
import org.jfree.ui.Layer;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetChangeEvent;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.data.DatasetChangeListener;
import java.util.HashMap;
import org.jfree.chart.renderer.XYItemRenderer;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.XYDataset;
import org.jfree.util.LogContext;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisSpace;
import java.util.List;
import java.util.Map;
import java.awt.geom.Point2D;
import org.jfree.util.ObjectList;
import org.jfree.ui.Spacer;
import java.util.ResourceBundle;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;
import org.jfree.chart.event.RendererChangeListener;

public class XYPlot extends Plot implements ValueAxisPlot, RendererChangeListener, Cloneable, Serializable
{
    public static final Stroke DEFAULT_GRIDLINE_STROKE;
    public static final Paint DEFAULT_GRIDLINE_PAINT;
    public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
    public static final Stroke DEFAULT_CROSSHAIR_STROKE;
    public static final Paint DEFAULT_CROSSHAIR_PAINT;
    protected static ResourceBundle localizationResources;
    private PlotOrientation orientation;
    private Spacer axisOffset;
    private ObjectList domainAxes;
    private ObjectList domainAxisLocations;
    private ObjectList rangeAxes;
    private ObjectList rangeAxisLocations;
    private ObjectList datasets;
    private ObjectList renderers;
    private ObjectList domainAxisMap;
    private ObjectList rangeAxisMap;
    private transient Point2D quadrantOrigin;
    private transient Paint[] quadrantPaint;
    private boolean domainGridlinesVisible;
    private transient Stroke domainGridlineStroke;
    private transient Paint domainGridlinePaint;
    private boolean rangeGridlinesVisible;
    private transient Stroke rangeGridlineStroke;
    private transient Paint rangeGridlinePaint;
    private boolean domainCrosshairVisible;
    private double domainCrosshairValue;
    private transient Stroke domainCrosshairStroke;
    private transient Paint domainCrosshairPaint;
    private boolean domainCrosshairLockedOnData;
    private boolean rangeCrosshairVisible;
    private double rangeCrosshairValue;
    private transient Stroke rangeCrosshairStroke;
    private transient Paint rangeCrosshairPaint;
    private boolean rangeCrosshairLockedOnData;
    private transient Map foregroundDomainMarkers;
    private transient Map backgroundDomainMarkers;
    private transient Map foregroundRangeMarkers;
    private transient Map backgroundRangeMarkers;
    private List annotations;
    private transient Paint domainTickBandPaint;
    private transient Paint rangeTickBandPaint;
    private AxisSpace fixedDomainAxisSpace;
    private AxisSpace fixedRangeAxisSpace;
    private DatasetRenderingOrder renderingOrder;
    private int weight;
    private LegendItemCollection fixedLegendItems;
    private static final LogContext LOGGER;
    static /* synthetic */ Class class$org$jfree$chart$plot$XYPlot;
    
    public XYPlot() {
        this(null, null, null, null);
    }
    
    public XYPlot(final XYDataset dataset, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYItemRenderer renderer) {
        this.quadrantOrigin = new Point2D.Double(0.0, 0.0);
        this.quadrantPaint = new Paint[] { null, null, null, null };
        this.domainCrosshairLockedOnData = true;
        this.rangeCrosshairLockedOnData = true;
        this.renderingOrder = DatasetRenderingOrder.REVERSE;
        this.orientation = PlotOrientation.VERTICAL;
        this.weight = 1;
        this.axisOffset = new Spacer(1, 0.0, 0.0, 0.0, 0.0);
        this.domainAxes = new ObjectList();
        this.domainAxisLocations = new ObjectList();
        this.foregroundDomainMarkers = new HashMap();
        this.backgroundDomainMarkers = new HashMap();
        this.rangeAxes = new ObjectList();
        this.rangeAxisLocations = new ObjectList();
        this.foregroundRangeMarkers = new HashMap();
        this.backgroundRangeMarkers = new HashMap();
        this.datasets = new ObjectList();
        this.renderers = new ObjectList();
        this.domainAxisMap = new ObjectList();
        this.rangeAxisMap = new ObjectList();
        this.datasets.set(0, dataset);
        if (dataset != null) {
            dataset.addChangeListener(this);
        }
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
        this.domainAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
        this.rangeAxes.set(0, rangeAxis);
        this.mapDatasetToRangeAxis(0, 0);
        if (rangeAxis != null) {
            rangeAxis.setPlot(this);
            rangeAxis.addChangeListener(this);
        }
        this.rangeAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
        this.configureDomainAxes();
        this.configureRangeAxes();
        this.domainGridlinesVisible = true;
        this.domainGridlineStroke = XYPlot.DEFAULT_GRIDLINE_STROKE;
        this.domainGridlinePaint = XYPlot.DEFAULT_GRIDLINE_PAINT;
        this.rangeGridlinesVisible = true;
        this.rangeGridlineStroke = XYPlot.DEFAULT_GRIDLINE_STROKE;
        this.rangeGridlinePaint = XYPlot.DEFAULT_GRIDLINE_PAINT;
        this.domainCrosshairVisible = false;
        this.domainCrosshairValue = 0.0;
        this.domainCrosshairStroke = XYPlot.DEFAULT_CROSSHAIR_STROKE;
        this.domainCrosshairPaint = XYPlot.DEFAULT_CROSSHAIR_PAINT;
        this.rangeCrosshairVisible = false;
        this.rangeCrosshairValue = 0.0;
        this.rangeCrosshairStroke = XYPlot.DEFAULT_CROSSHAIR_STROKE;
        this.rangeCrosshairPaint = XYPlot.DEFAULT_CROSSHAIR_PAINT;
    }
    
    public String getPlotType() {
        return XYPlot.localizationResources.getString("XY_Plot");
    }
    
    public PlotOrientation getOrientation() {
        return this.orientation;
    }
    
    public void setOrientation(final PlotOrientation orientation) {
        if (orientation == null) {
            throw new IllegalArgumentException("XYPlot.setOrientation(...): null not allowed.");
        }
        if (orientation != this.orientation) {
            this.orientation = orientation;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Spacer getAxisOffset() {
        return this.axisOffset;
    }
    
    public void setAxisOffset(final Spacer offset) {
        this.axisOffset = offset;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public ValueAxis getDomainAxis() {
        return this.getDomainAxis(0);
    }
    
    public ValueAxis getDomainAxis(final int index) {
        ValueAxis result = null;
        if (index < this.domainAxes.size()) {
            result = (ValueAxis)this.domainAxes.get(index);
        }
        if (result == null) {
            final Plot parent = this.getParent();
            if (parent instanceof XYPlot) {
                final XYPlot xy = (XYPlot)parent;
                result = xy.getDomainAxis(index);
            }
        }
        return result;
    }
    
    public void setDomainAxis(final ValueAxis axis) {
        this.setDomainAxis(0, axis);
    }
    
    public void setDomainAxis(final int index, final ValueAxis axis) {
        final ValueAxis existing = this.getDomainAxis(index);
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
        return (AxisLocation)this.domainAxisLocations.get(0);
    }
    
    public void setDomainAxisLocation(final AxisLocation location) {
        this.setDomainAxisLocation(location, true);
    }
    
    public void setDomainAxisLocation(final AxisLocation location, final boolean notify) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        this.domainAxisLocations.set(0, location);
        if (notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public RectangleEdge getDomainAxisEdge() {
        return Plot.resolveDomainAxisLocation(this.getDomainAxisLocation(), this.orientation);
    }
    
    public int getDomainAxisCount() {
        return this.domainAxes.size();
    }
    
    public void clearDomainAxes() {
        for (int i = 0; i < this.domainAxes.size(); ++i) {
            final ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
            if (axis != null) {
                axis.removeChangeListener(this);
            }
        }
        this.domainAxes.clear();
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void configureDomainAxes() {
        for (int i = 0; i < this.domainAxes.size(); ++i) {
            final ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
            if (axis != null) {
                axis.configure();
            }
        }
    }
    
    public AxisLocation getDomainAxisLocation(final int index) {
        AxisLocation result = null;
        if (index < this.domainAxisLocations.size()) {
            result = (AxisLocation)this.domainAxisLocations.get(index);
        }
        if (result == null) {
            result = AxisLocation.getOpposite(this.getDomainAxisLocation());
        }
        return result;
    }
    
    public void setDomainAxisLocation(final int index, final AxisLocation location) {
        this.domainAxisLocations.set(index, location);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public RectangleEdge getDomainAxisEdge(final int index) {
        final AxisLocation location = this.getDomainAxisLocation(index);
        RectangleEdge result = Plot.resolveDomainAxisLocation(location, this.orientation);
        if (result == null) {
            result = RectangleEdge.opposite(this.getDomainAxisEdge());
        }
        return result;
    }
    
    public ValueAxis getRangeAxis() {
        return this.getRangeAxis(0);
    }
    
    public void setRangeAxis(final ValueAxis axis) {
        if (axis != null) {
            axis.setPlot(this);
        }
        final ValueAxis existing = this.getRangeAxis();
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        this.rangeAxes.set(0, axis);
        if (axis != null) {
            axis.configure();
            axis.addChangeListener(this);
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public AxisLocation getRangeAxisLocation() {
        return (AxisLocation)this.rangeAxisLocations.get(0);
    }
    
    public void setRangeAxisLocation(final AxisLocation location) {
        this.setRangeAxisLocation(location, true);
    }
    
    public void setRangeAxisLocation(final AxisLocation location, final boolean notify) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        this.rangeAxisLocations.set(0, location);
        if (notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public RectangleEdge getRangeAxisEdge() {
        return Plot.resolveRangeAxisLocation(this.getRangeAxisLocation(), this.orientation);
    }
    
    public ValueAxis getRangeAxis(final int index) {
        ValueAxis result = null;
        if (index < this.rangeAxes.size()) {
            result = (ValueAxis)this.rangeAxes.get(index);
        }
        if (result == null) {
            final Plot parent = this.getParent();
            if (parent instanceof XYPlot) {
                final XYPlot xy = (XYPlot)parent;
                result = xy.getRangeAxis(index);
            }
        }
        return result;
    }
    
    public void setRangeAxis(final int index, final ValueAxis axis) {
        final ValueAxis existing = this.getRangeAxis(index);
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
    
    public int getRangeAxisCount() {
        return this.rangeAxes.size();
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
    
    public AxisLocation getRangeAxisLocation(final int index) {
        AxisLocation result = null;
        if (index < this.rangeAxisLocations.size()) {
            result = (AxisLocation)this.rangeAxisLocations.get(index);
        }
        if (result == null) {
            result = AxisLocation.getOpposite(this.getRangeAxisLocation());
        }
        return result;
    }
    
    public void setRangeAxisLocation(final int index, final AxisLocation location) {
        this.rangeAxisLocations.set(index, location);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public RectangleEdge getRangeAxisEdge(final int index) {
        final AxisLocation location = this.getRangeAxisLocation(index);
        RectangleEdge result = Plot.resolveRangeAxisLocation(location, this.orientation);
        if (result == null) {
            result = RectangleEdge.opposite(this.getRangeAxisEdge());
        }
        return result;
    }
    
    public XYDataset getDataset() {
        return this.getDataset(0);
    }
    
    public XYDataset getDataset(final int index) {
        XYDataset result = null;
        if (this.datasets.size() > index) {
            result = (XYDataset)this.datasets.get(index);
        }
        return result;
    }
    
    public void setDataset(final XYDataset dataset) {
        this.setDataset(0, dataset);
    }
    
    public void setDataset(final int index, final XYDataset dataset) {
        final XYDataset existing = this.getDataset(index);
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        this.datasets.set(index, dataset);
        if (dataset != null) {
            dataset.addChangeListener(this);
        }
        if (index >= this.domainAxisMap.size()) {
            this.domainAxisMap.set(index, new Integer(0));
        }
        if (index >= this.rangeAxisMap.size()) {
            this.rangeAxisMap.set(index, new Integer(0));
        }
        final DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        this.datasetChanged(event);
    }
    
    public int getDatasetCount() {
        return this.datasets.size();
    }
    
    public int indexOf(final XYDataset dataset) {
        int result = -1;
        for (int i = 0; i < this.datasets.size(); ++i) {
            if (dataset == this.datasets.get(i)) {
                result = i;
                break;
            }
        }
        return result;
    }
    
    public void mapDatasetToDomainAxis(final int index, final int axisIndex) {
        this.domainAxisMap.set(index, new Integer(axisIndex));
        this.datasetChanged(new DatasetChangeEvent(this, this.getDataset(index)));
    }
    
    public void mapDatasetToRangeAxis(final int index, final int axisIndex) {
        this.rangeAxisMap.set(index, new Integer(axisIndex));
        this.datasetChanged(new DatasetChangeEvent(this, this.getDataset(index)));
    }
    
    public XYItemRenderer getRenderer() {
        return this.getRenderer(0);
    }
    
    public XYItemRenderer getRenderer(final int index) {
        XYItemRenderer result = null;
        if (this.renderers.size() > index) {
            result = (XYItemRenderer)this.renderers.get(index);
        }
        return result;
    }
    
    public void setRenderer(final XYItemRenderer renderer) {
        this.setRenderer(0, renderer);
    }
    
    public void setRenderer(final int index, final XYItemRenderer renderer) {
        final XYItemRenderer existing = this.getRenderer(index);
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        this.renderers.set(index, renderer);
        if (renderer != null) {
            renderer.setPlot(this);
        }
        this.notifyListeners(new PlotChangeEvent(this));
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
    
    public XYItemRenderer getRendererForDataset(final XYDataset dataset) {
        XYItemRenderer result = null;
        for (int i = 0; i < this.datasets.size(); ++i) {
            if (this.datasets.get(i) == dataset) {
                result = (XYItemRenderer)this.renderers.get(i);
                break;
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
    
    public boolean isDomainGridlinesVisible() {
        return this.domainGridlinesVisible;
    }
    
    public void setDomainGridlinesVisible(final boolean visible) {
        if (this.domainGridlinesVisible != visible) {
            this.domainGridlinesVisible = visible;
            this.notifyListeners(new PlotChangeEvent(this));
        }
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
    
    public Paint getDomainTickBandPaint() {
        return this.domainTickBandPaint;
    }
    
    public void setDomainTickBandPaint(final Paint paint) {
        this.domainTickBandPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getRangeTickBandPaint() {
        return this.rangeTickBandPaint;
    }
    
    public void setRangeTickBandPaint(final Paint paint) {
        this.rangeTickBandPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Point2D getQuadrantOrigin() {
        return this.quadrantOrigin;
    }
    
    public void setQuadrantOrigin(final Point2D origin) {
        if (origin == null) {
            throw new IllegalArgumentException("Null 'origin' argument.");
        }
        this.quadrantOrigin = origin;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getQuadrantPaint(final int index) {
        if (index < 0 || index > 3) {
            throw new IllegalArgumentException("The index should be in the range 0 to 3.");
        }
        return this.quadrantPaint[index];
    }
    
    public void setQuadrantPaint(final int index, final Paint paint) {
        if (index < 0 || index > 3) {
            throw new IllegalArgumentException("The index should be in the range 0 to 3.");
        }
        this.quadrantPaint[index] = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void addDomainMarker(final Marker marker) {
        this.addDomainMarker(marker, Layer.FOREGROUND);
    }
    
    public void addDomainMarker(final Marker marker, final Layer layer) {
        this.addDomainMarker(0, marker, layer);
    }
    
    public void clearDomainMarkers() {
        if (this.foregroundDomainMarkers != null) {
            this.foregroundDomainMarkers.clear();
        }
        if (this.backgroundDomainMarkers != null) {
            this.backgroundDomainMarkers.clear();
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void addDomainMarker(final int index, final Marker marker, final Layer layer) {
        if (layer == Layer.FOREGROUND) {
            Collection markers = this.foregroundDomainMarkers.get(new Integer(index));
            if (markers == null) {
                markers = new ArrayList();
                this.foregroundDomainMarkers.put(new Integer(index), markers);
            }
            markers.add(marker);
        }
        else if (layer == Layer.BACKGROUND) {
            Collection markers = this.backgroundDomainMarkers.get(new Integer(index));
            if (markers == null) {
                markers = new ArrayList();
                this.backgroundDomainMarkers.put(new Integer(index), markers);
            }
            markers.add(marker);
        }
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void addRangeMarker(final Marker marker) {
        this.addRangeMarker(marker, Layer.FOREGROUND);
    }
    
    public void addRangeMarker(final Marker marker, final Layer layer) {
        this.addRangeMarker(0, marker, layer);
    }
    
    public void clearRangeMarkers() {
        if (this.foregroundRangeMarkers != null) {
            this.foregroundRangeMarkers.clear();
        }
        if (this.backgroundRangeMarkers != null) {
            this.backgroundRangeMarkers.clear();
        }
        this.notifyListeners(new PlotChangeEvent(this));
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
    
    public void addAnnotation(final XYAnnotation annotation) {
        if (annotation == null) {
            throw new IllegalArgumentException("Null 'annotation' argument.");
        }
        if (this.annotations == null) {
            this.annotations = new ArrayList();
        }
        this.annotations.add(annotation);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void clearAnnotations() {
        if (this.annotations != null) {
            this.annotations.clear();
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    protected AxisSpace calculateAxisSpace(final Graphics2D g2, final Rectangle2D plotArea) {
        AxisSpace space = new AxisSpace();
        space = this.calculateDomainAxisSpace(g2, plotArea, space);
        space = this.calculateRangeAxisSpace(g2, plotArea, space);
        return space;
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
            for (int i = 0; i < this.domainAxes.size(); ++i) {
                final Axis axis = (Axis)this.domainAxes.get(i);
                if (axis != null) {
                    final RectangleEdge edge = this.getDomainAxisEdge(i);
                    space = axis.reserveSpace(g2, this, plotArea, edge, space);
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
                final Axis axis = (Axis)this.rangeAxes.get(i);
                if (axis != null) {
                    final RectangleEdge edge = this.getRangeAxisEdge(i);
                    space = axis.reserveSpace(g2, this, plotArea, edge, space);
                }
            }
        }
        return space;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final PlotState parentState, final PlotRenderingInfo state) {
        this.draw(g2, area, null, parentState, state);
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area, Point2D anchor, final PlotState parentState, final PlotRenderingInfo info) {
        if (XYPlot.LOGGER.isDebugEnabled()) {
            XYPlot.LOGGER.debug("Entering draw() method, plot area = " + area.toString());
        }
        final boolean b1 = area.getWidth() <= 10.0;
        final boolean b2 = area.getHeight() <= 10.0;
        if (b1 || b2) {
            return;
        }
        if (info != null) {
            info.setPlotArea(area);
        }
        final Insets insets = this.getInsets();
        if (insets != null) {
            area.setRect(area.getX() + insets.left, area.getY() + insets.top, area.getWidth() - insets.left - insets.right, area.getHeight() - insets.top - insets.bottom);
        }
        final AxisSpace space = this.calculateAxisSpace(g2, area);
        if (XYPlot.LOGGER.isDebugEnabled()) {
            XYPlot.LOGGER.debug("Calculated axis space = " + space.toString());
        }
        final Rectangle2D dataArea = space.shrink(area, null);
        this.axisOffset.trim(dataArea);
        if (info != null) {
            info.setDataArea(dataArea);
        }
        if (XYPlot.LOGGER.isDebugEnabled()) {
            XYPlot.LOGGER.debug("Data area = " + dataArea.toString());
        }
        this.drawBackground(g2, dataArea);
        final Map axisStateMap = this.drawAxes(g2, area, dataArea, info);
        if (anchor != null && !dataArea.contains(anchor)) {
            anchor = null;
        }
        final CrosshairState crosshairState = new CrosshairState();
        crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
        crosshairState.setAnchor(anchor);
        crosshairState.setCrosshairX(this.getDomainCrosshairValue());
        crosshairState.setCrosshairY(this.getRangeCrosshairValue());
        final Shape originalClip = g2.getClip();
        final Composite originalComposite = g2.getComposite();
        g2.clip(dataArea);
        g2.setComposite(AlphaComposite.getInstance(3, this.getForegroundAlpha()));
        AxisState domainAxisState = axisStateMap.get(this.getDomainAxis());
        if (domainAxisState == null && parentState != null) {
            domainAxisState = parentState.getSharedAxisStates().get(this.getDomainAxis());
        }
        if (domainAxisState != null) {
            this.drawDomainTickBands(g2, dataArea, domainAxisState.getTicks());
            this.drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
        }
        AxisState rangeAxisState = axisStateMap.get(this.getRangeAxis());
        if (rangeAxisState == null && parentState != null) {
            rangeAxisState = parentState.getSharedAxisStates().get(this.getRangeAxis());
        }
        if (rangeAxisState != null) {
            this.drawRangeTickBands(g2, dataArea, rangeAxisState.getTicks());
            this.drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
        }
        for (int i = 0; i < this.renderers.size(); ++i) {
            this.drawDomainMarkers(g2, dataArea, i, Layer.BACKGROUND);
        }
        for (int i = 0; i < this.renderers.size(); ++i) {
            this.drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND);
        }
        this.drawDomainMarkers(g2, dataArea, Layer.BACKGROUND);
        this.drawRangeMarkers(g2, dataArea, Layer.BACKGROUND);
        boolean foundData = false;
        final DatasetRenderingOrder order = this.getDatasetRenderingOrder();
        if (order == DatasetRenderingOrder.FORWARD) {
            for (int j = 0; j < this.getDatasetCount(); ++j) {
                foundData = (this.render(g2, dataArea, j, info, crosshairState) || foundData);
            }
        }
        else {
            for (int j = this.getDatasetCount() - 1; j >= 0; --j) {
                foundData = (this.render(g2, dataArea, j, info, crosshairState) || foundData);
            }
        }
        final PlotOrientation orient = this.getOrientation();
        this.setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
        if (this.isDomainCrosshairVisible()) {
            final double x = this.getDomainCrosshairValue();
            final Paint paint = this.getDomainCrosshairPaint();
            final Stroke stroke = this.getDomainCrosshairStroke();
            if (orient == PlotOrientation.HORIZONTAL) {
                this.drawHorizontalLine(g2, dataArea, x, stroke, paint);
            }
            else if (orient == PlotOrientation.VERTICAL) {
                this.drawVerticalLine(g2, dataArea, x, stroke, paint);
            }
        }
        this.setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
        if (this.isRangeCrosshairVisible() && this.getRangeAxis().getRange().contains(this.getRangeCrosshairValue())) {
            final double y = this.getRangeCrosshairValue();
            final Paint paint = this.getRangeCrosshairPaint();
            final Stroke stroke = this.getRangeCrosshairStroke();
            if (orient == PlotOrientation.HORIZONTAL) {
                this.drawVerticalLine(g2, dataArea, y, stroke, paint);
            }
            else if (orient == PlotOrientation.VERTICAL) {
                this.drawHorizontalLine(g2, dataArea, y, stroke, paint);
            }
        }
        if (!foundData) {
            this.drawNoDataMessage(g2, dataArea);
        }
        for (int k = 0; k < this.renderers.size(); ++k) {
            this.drawDomainMarkers(g2, dataArea, k, Layer.FOREGROUND);
        }
        for (int k = 0; k < this.renderers.size(); ++k) {
            this.drawRangeMarkers(g2, dataArea, k, Layer.FOREGROUND);
        }
        this.drawDomainMarkers(g2, dataArea, Layer.FOREGROUND);
        this.drawRangeMarkers(g2, dataArea, Layer.FOREGROUND);
        this.drawAnnotations(g2, dataArea, info);
        g2.setClip(originalClip);
        g2.setComposite(originalComposite);
        this.drawOutline(g2, dataArea);
        if (XYPlot.LOGGER.isDebugEnabled()) {
            XYPlot.LOGGER.debug("Leaving draw() method.");
        }
    }
    
    public void drawBackground(final Graphics2D g2, final Rectangle2D area) {
        this.fillBackground(g2, area);
        this.drawQuadrants(g2, area);
        this.drawBackgroundImage(g2, area);
    }
    
    protected void drawQuadrants(final Graphics2D g2, final Rectangle2D area) {
        boolean somethingToDraw = false;
        final ValueAxis xAxis = this.getDomainAxis();
        final double x = this.quadrantOrigin.getX();
        final double xx = xAxis.valueToJava2D(x, area, this.getDomainAxisEdge());
        final ValueAxis yAxis = this.getRangeAxis();
        final double y = this.quadrantOrigin.getY();
        final double yy = yAxis.valueToJava2D(y, area, this.getRangeAxisEdge());
        final double xmin = xAxis.getLowerBound();
        final double xxmin = xAxis.valueToJava2D(xmin, area, this.getDomainAxisEdge());
        final double xmax = xAxis.getUpperBound();
        final double xxmax = xAxis.valueToJava2D(xmax, area, this.getDomainAxisEdge());
        final double ymin = yAxis.getLowerBound();
        final double yymin = yAxis.valueToJava2D(ymin, area, this.getRangeAxisEdge());
        final double ymax = yAxis.getUpperBound();
        final double yymax = yAxis.valueToJava2D(ymax, area, this.getRangeAxisEdge());
        final Rectangle2D[] r = { null, null, null, null };
        if (this.quadrantPaint[0] != null && x > xmin && y < ymax) {
            if (this.orientation == PlotOrientation.HORIZONTAL) {
                r[0] = new Rectangle2D.Double(Math.min(yymax, yy), Math.min(xxmin, xx), Math.abs(yy - yymax), Math.abs(xx - xxmin));
            }
            else {
                r[0] = new Rectangle2D.Double(Math.min(xxmin, xx), Math.min(yymax, yy), Math.abs(xx - xxmin), Math.abs(yy - yymax));
            }
            somethingToDraw = true;
        }
        if (this.quadrantPaint[1] != null && x < xmax && y < ymax) {
            if (this.orientation == PlotOrientation.HORIZONTAL) {
                r[1] = new Rectangle2D.Double(Math.min(yymax, yy), Math.min(xxmax, xx), Math.abs(yy - yymax), Math.abs(xx - xxmax));
            }
            else {
                r[1] = new Rectangle2D.Double(Math.min(xx, xxmax), Math.min(yymax, yy), Math.abs(xx - xxmax), Math.abs(yy - yymax));
            }
            somethingToDraw = true;
        }
        if (this.quadrantPaint[2] != null && x > xmin && y > ymin) {
            if (this.orientation == PlotOrientation.HORIZONTAL) {
                r[2] = new Rectangle2D.Double(Math.min(yymin, yy), Math.min(xxmin, xx), Math.abs(yy - yymin), Math.abs(xx - xxmin));
            }
            else {
                r[2] = new Rectangle2D.Double(Math.min(xxmin, xx), Math.min(yymin, yy), Math.abs(xx - xxmin), Math.abs(yy - yymin));
            }
            somethingToDraw = true;
        }
        if (this.quadrantPaint[3] != null && x < xmax && y > ymin) {
            if (this.orientation == PlotOrientation.HORIZONTAL) {
                r[3] = new Rectangle2D.Double(Math.min(yymin, yy), Math.min(xxmax, xx), Math.abs(yy - yymin), Math.abs(xx - xxmax));
            }
            else {
                r[3] = new Rectangle2D.Double(Math.min(xx, xxmax), Math.min(yymin, yy), Math.abs(xx - xxmax), Math.abs(yy - yymin));
            }
            somethingToDraw = true;
        }
        if (somethingToDraw) {
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, this.getBackgroundAlpha()));
            for (int i = 0; i < 4; ++i) {
                if (this.quadrantPaint[i] != null && r[i] != null) {
                    g2.setPaint(this.quadrantPaint[i]);
                    g2.fill(r[i]);
                }
            }
            g2.setComposite(originalComposite);
        }
    }
    
    public void drawDomainTickBands(final Graphics2D g2, final Rectangle2D dataArea, final List ticks) {
        final Paint bandPaint = this.getDomainTickBandPaint();
        if (bandPaint != null) {
            boolean fillBand = false;
            final ValueAxis xAxis = this.getDomainAxis();
            double previous = xAxis.getLowerBound();
            for (final ValueTick tick : ticks) {
                final double current = tick.getValue();
                if (fillBand) {
                    this.getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea, previous, current);
                }
                previous = current;
                fillBand = !fillBand;
            }
            final double end = xAxis.getUpperBound();
            if (fillBand) {
                this.getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea, previous, end);
            }
        }
    }
    
    public void drawRangeTickBands(final Graphics2D g2, final Rectangle2D dataArea, final List ticks) {
        final Paint bandPaint = this.getRangeTickBandPaint();
        if (bandPaint != null) {
            boolean fillBand = false;
            final ValueAxis axis = this.getRangeAxis();
            double previous = axis.getLowerBound();
            for (final ValueTick tick : ticks) {
                final double current = tick.getValue();
                if (fillBand) {
                    this.getRenderer().fillRangeGridBand(g2, this, axis, dataArea, previous, current);
                }
                previous = current;
                fillBand = !fillBand;
            }
            final double end = axis.getUpperBound();
            if (fillBand) {
                this.getRenderer().fillRangeGridBand(g2, this, axis, dataArea, previous, end);
            }
        }
    }
    
    protected Map drawAxes(final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D dataArea, final PlotRenderingInfo plotState) {
        final AxisCollection axisCollection = new AxisCollection();
        for (int index = 0; index < this.domainAxes.size(); ++index) {
            final ValueAxis axis = (ValueAxis)this.domainAxes.get(index);
            if (axis != null) {
                axisCollection.add(axis, this.getDomainAxisEdge(index));
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
        for (final ValueAxis axis2 : axisCollection.getAxesAtTop()) {
            final AxisState info = axis2.draw(g2, cursor, plotArea, dataArea, RectangleEdge.TOP, plotState);
            cursor = info.getCursor();
            axisStateMap.put(axis2, info);
        }
        cursor = dataArea.getMaxY() + this.axisOffset.getBottomSpace(dataArea.getHeight());
        for (final ValueAxis axis2 : axisCollection.getAxesAtBottom()) {
            final AxisState info = axis2.draw(g2, cursor, plotArea, dataArea, RectangleEdge.BOTTOM, plotState);
            cursor = info.getCursor();
            axisStateMap.put(axis2, info);
        }
        cursor = dataArea.getMinX() - this.axisOffset.getLeftSpace(dataArea.getWidth());
        for (final ValueAxis axis2 : axisCollection.getAxesAtLeft()) {
            final AxisState info = axis2.draw(g2, cursor, plotArea, dataArea, RectangleEdge.LEFT, plotState);
            cursor = info.getCursor();
            axisStateMap.put(axis2, info);
        }
        cursor = dataArea.getMaxX() + this.axisOffset.getRightSpace(dataArea.getWidth());
        for (final ValueAxis axis2 : axisCollection.getAxesAtRight()) {
            final AxisState info = axis2.draw(g2, cursor, plotArea, dataArea, RectangleEdge.RIGHT, plotState);
            cursor = info.getCursor();
            axisStateMap.put(axis2, info);
        }
        return axisStateMap;
    }
    
    public boolean render(final Graphics2D g2, final Rectangle2D dataArea, final int index, final PlotRenderingInfo info, final CrosshairState crosshairState) {
        boolean foundData = false;
        final XYDataset dataset = this.getDataset(index);
        if (!DatasetUtilities.isEmptyOrNull(dataset)) {
            foundData = true;
            ValueAxis xAxis = this.getDomainAxis();
            final Integer key = (Integer)this.domainAxisMap.get(index);
            if (key != null) {
                final ValueAxis axis = this.getDomainAxis(key);
                if (axis != null) {
                    xAxis = axis;
                }
            }
            ValueAxis yAxis = this.getRangeAxis();
            final Integer yAxisIndex = (Integer)this.rangeAxisMap.get(index);
            if (yAxisIndex != null) {
                final ValueAxis axis2 = this.getRangeAxis(yAxisIndex);
                if (axis2 != null) {
                    yAxis = axis2;
                }
            }
            XYItemRenderer renderer = this.getRenderer(index);
            if (renderer == null) {
                renderer = this.getRenderer();
            }
            final XYItemRendererState state = renderer.initialise(g2, dataArea, this, dataset, info);
            for (int passCount = renderer.getPassCount(), pass = 0; pass < passCount; ++pass) {
                for (int seriesCount = dataset.getSeriesCount(), series = 0; series < seriesCount; ++series) {
                    for (int itemCount = dataset.getItemCount(series), item = 0; item < itemCount; ++item) {
                        renderer.drawItem(g2, state, dataArea, info, this, xAxis, yAxis, dataset, series, item, crosshairState, pass);
                    }
                }
            }
        }
        return foundData;
    }
    
    public ValueAxis getDomainAxisForDataset(final int index) {
        if (index >= this.getDatasetCount()) {
            throw new IllegalArgumentException();
        }
        ValueAxis valueAxis = null;
        final Integer axisIndex = (Integer)this.domainAxisMap.get(index);
        valueAxis = this.getDomainAxis(axisIndex);
        return valueAxis;
    }
    
    public ValueAxis getRangeAxisForDataset(final int index) {
        if (index >= this.getDatasetCount()) {
            throw new IllegalArgumentException();
        }
        ValueAxis valueAxis = null;
        final Integer axisIndex = (Integer)this.rangeAxisMap.get(index);
        valueAxis = this.getRangeAxis(axisIndex);
        return valueAxis;
    }
    
    protected void drawDomainGridlines(final Graphics2D g2, final Rectangle2D dataArea, final List ticks) {
        if (this.getRenderer() == null) {
            return;
        }
        if (this.isDomainGridlinesVisible()) {
            final Stroke gridStroke = this.getDomainGridlineStroke();
            final Paint gridPaint = this.getDomainGridlinePaint();
            if (gridStroke != null && gridPaint != null) {
                for (final ValueTick tick : ticks) {
                    this.getRenderer().drawDomainGridLine(g2, this, this.getDomainAxis(), dataArea, tick.getValue());
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
                        this.getRenderer().drawRangeGridLine(g2, this, this.getRangeAxis(), dataArea, tick.getValue());
                    }
                }
            }
        }
    }
    
    public void drawAnnotations(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info) {
        if (this.annotations != null) {
            for (final XYAnnotation annotation : this.annotations) {
                annotation.draw(g2, this, dataArea, this.getDomainAxis(), this.getRangeAxis());
            }
        }
    }
    
    protected void drawDomainMarkers(final Graphics2D g2, final Rectangle2D dataArea, final Layer layer) {
        final XYItemRenderer r = this.getRenderer();
        final Collection markers = this.getDomainMarkers(layer);
        if (markers != null && r != null) {
            for (final Marker marker : markers) {
                r.drawDomainMarker(g2, this, this.getDomainAxis(), marker, dataArea);
            }
        }
    }
    
    protected void drawRangeMarkers(final Graphics2D g2, final Rectangle2D dataArea, final Layer layer) {
        final XYItemRenderer r = this.getRenderer();
        final Collection markers = this.getRangeMarkers(layer);
        if (markers != null && r != null) {
            for (final Marker marker : markers) {
                r.drawRangeMarker(g2, this, this.getRangeAxis(), marker, dataArea);
            }
        }
    }
    
    protected void drawDomainMarkers(final Graphics2D g2, final Rectangle2D dataArea, final int index, final Layer layer) {
        final XYItemRenderer r = this.getRenderer(index);
        if (r == null) {
            return;
        }
        final Collection markers = this.getDomainMarkers(index, layer);
        final ValueAxis axis = this.getDomainAxis(index);
        if (markers != null && axis != null) {
            for (final Marker marker : markers) {
                r.drawDomainMarker(g2, this, axis, marker, dataArea);
            }
        }
    }
    
    protected void drawRangeMarkers(final Graphics2D g2, final Rectangle2D dataArea, final int index, final Layer layer) {
        final XYItemRenderer r = this.getRenderer(index);
        if (r == null) {
            return;
        }
        final Collection markers = this.getRangeMarkers(index, layer);
        final ValueAxis axis = this.getRangeAxis(index);
        if (markers != null && axis != null) {
            for (final Marker marker : markers) {
                r.drawRangeMarker(g2, this, axis, marker, dataArea);
            }
        }
    }
    
    public Collection getDomainMarkers(final Layer layer) {
        return this.getDomainMarkers(0, layer);
    }
    
    public Collection getRangeMarkers(final Layer layer) {
        return this.getRangeMarkers(0, layer);
    }
    
    public Collection getDomainMarkers(final int index, final Layer layer) {
        Collection result = null;
        final Integer key = new Integer(index);
        if (layer == Layer.FOREGROUND) {
            result = this.foregroundDomainMarkers.get(key);
        }
        else if (layer == Layer.BACKGROUND) {
            result = this.backgroundDomainMarkers.get(key);
        }
        if (result != null) {
            result = Collections.unmodifiableCollection((Collection<?>)result);
        }
        return result;
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
    
    protected void drawHorizontalLine(final Graphics2D g2, final Rectangle2D dataArea, final double value, final Stroke stroke, final Paint paint) {
        ValueAxis axis = this.getRangeAxis();
        if (this.getOrientation() == PlotOrientation.HORIZONTAL) {
            axis = this.getDomainAxis();
        }
        if (axis.getRange().contains(value)) {
            final double yy = axis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
            final Line2D line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
            g2.setStroke(stroke);
            g2.setPaint(paint);
            g2.draw(line);
        }
    }
    
    protected void drawVerticalLine(final Graphics2D g2, final Rectangle2D dataArea, final double value, final Stroke stroke, final Paint paint) {
        ValueAxis axis = this.getDomainAxis();
        if (this.getOrientation() == PlotOrientation.HORIZONTAL) {
            axis = this.getRangeAxis();
        }
        if (axis.getRange().contains(value)) {
            final double xx = axis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
            final Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
            g2.setStroke(stroke);
            g2.setPaint(paint);
            g2.draw(line);
        }
    }
    
    public void handleClick(final int x, final int y, final PlotRenderingInfo info) {
        final Rectangle2D dataArea = info.getDataArea();
        if (dataArea.contains(x, y)) {
            final ValueAxis da = this.getDomainAxis();
            if (da != null) {
                final double hvalue = da.java2DToValue(x, info.getDataArea(), this.getDomainAxisEdge());
                this.setDomainCrosshairValue(hvalue);
            }
            final ValueAxis ra = this.getRangeAxis();
            if (ra != null) {
                final double vvalue = ra.java2DToValue(y, info.getDataArea(), this.getRangeAxisEdge());
                this.setRangeCrosshairValue(vvalue);
            }
        }
    }
    
    public void zoom(final double percent) {
        if (percent > 0.0) {
            final ValueAxis domainAxis1 = this.getDomainAxis();
            Range range = domainAxis1.getRange();
            double center = range.getCentralValue();
            double length = range.getLength();
            double scaledRange = length * percent;
            domainAxis1.setRange(center - scaledRange / 2.0, center + scaledRange / 2.0);
            final ValueAxis rangeAxis1 = this.getRangeAxis();
            range = rangeAxis1.getRange();
            center = range.getCentralValue();
            length = range.getLength();
            scaledRange = length * percent;
            rangeAxis1.setRange(center - scaledRange / 2.0, center + scaledRange / 2.0);
        }
        else {
            this.getRangeAxis().setAutoRange(true);
            this.getDomainAxis().setAutoRange(true);
        }
    }
    
    private List getDatasetsMappedToDomainAxis(final Integer axisIndex) {
        final List result = new ArrayList();
        for (int i = 0; i < this.datasets.size(); ++i) {
            final Integer m = (Integer)this.domainAxisMap.get(i);
            if (m == null) {
                if (axisIndex == null) {
                    result.add(this.datasets.get(i));
                }
            }
            else if (m.equals(axisIndex)) {
                result.add(this.datasets.get(i));
            }
        }
        return result;
    }
    
    private List getDatasetsMappedToRangeAxis(final Integer index) {
        final List result = new ArrayList();
        for (int i = 0; i < this.datasets.size(); ++i) {
            final Integer m = (Integer)this.rangeAxisMap.get(i);
            if (m == null) {
                if (index == null) {
                    result.add(this.datasets.get(i));
                }
            }
            else if (m.equals(index)) {
                result.add(this.datasets.get(i));
            }
        }
        return result;
    }
    
    public Range getDataRange(final ValueAxis axis) {
        Range result = null;
        final List mappedDatasets = new ArrayList();
        boolean isDomainAxis = true;
        final int domainIndex = this.domainAxes.indexOf(axis);
        if (domainIndex >= 0) {
            isDomainAxis = true;
            mappedDatasets.addAll(this.getDatasetsMappedToDomainAxis(new Integer(domainIndex)));
        }
        else if (axis == this.getDomainAxis()) {
            isDomainAxis = true;
            mappedDatasets.addAll(this.getDatasetsMappedToDomainAxis(new Integer(0)));
        }
        final int rangeIndex = this.rangeAxes.indexOf(axis);
        if (rangeIndex >= 0) {
            isDomainAxis = false;
            mappedDatasets.addAll(this.getDatasetsMappedToRangeAxis(new Integer(rangeIndex)));
        }
        else if (axis == this.getRangeAxis()) {
            isDomainAxis = false;
            mappedDatasets.addAll(this.getDatasetsMappedToRangeAxis(new Integer(0)));
        }
        for (final XYDataset d : mappedDatasets) {
            final XYItemRenderer r = this.getRendererForDataset(d);
            if (isDomainAxis) {
                result = Range.combine(result, DatasetUtilities.getDomainExtent(d));
            }
            else if (r != null) {
                result = Range.combine(result, r.getRangeExtent(d));
            }
            else {
                result = Range.combine(result, DatasetUtilities.getRangeExtent(d));
            }
        }
        return result;
    }
    
    public void datasetChanged(final DatasetChangeEvent event) {
        this.configureDomainAxes();
        this.configureRangeAxes();
        if (this.getParent() != null) {
            this.getParent().datasetChanged(event);
        }
        else {
            final PlotChangeEvent e = new PlotChangeEvent(this);
            this.notifyListeners(e);
        }
    }
    
    public void rendererChanged(final RendererChangeEvent event) {
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean isDomainCrosshairVisible() {
        return this.domainCrosshairVisible;
    }
    
    public void setDomainCrosshairVisible(final boolean flag) {
        if (this.domainCrosshairVisible != flag) {
            this.domainCrosshairVisible = flag;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public boolean isDomainCrosshairLockedOnData() {
        return this.domainCrosshairLockedOnData;
    }
    
    public void setDomainCrosshairLockedOnData(final boolean flag) {
        if (this.domainCrosshairLockedOnData != flag) {
            this.domainCrosshairLockedOnData = flag;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public double getDomainCrosshairValue() {
        return this.domainCrosshairValue;
    }
    
    public void setDomainCrosshairValue(final double value) {
        this.setDomainCrosshairValue(value, true);
    }
    
    public void setDomainCrosshairValue(final double value, final boolean notify) {
        this.domainCrosshairValue = value;
        if (this.isDomainCrosshairVisible() && notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Stroke getDomainCrosshairStroke() {
        return this.domainCrosshairStroke;
    }
    
    public void setDomainCrosshairStroke(final Stroke stroke) {
        this.domainCrosshairStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getDomainCrosshairPaint() {
        return this.domainCrosshairPaint;
    }
    
    public void setDomainCrosshairPaint(final Paint paint) {
        this.domainCrosshairPaint = paint;
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
    
    public void zoomHorizontalAxes(final double factor) {
        final PlotOrientation orient = this.getOrientation();
        if (orient == PlotOrientation.HORIZONTAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.resizeRange(factor);
                }
            }
        }
        else if (orient == PlotOrientation.VERTICAL) {
            for (int i = 0; i < this.domainAxes.size(); ++i) {
                final ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
                if (domainAxis != null) {
                    domainAxis.resizeRange(factor);
                }
            }
        }
    }
    
    public void zoomHorizontalAxes(final double lowerPercent, final double upperPercent) {
        final PlotOrientation orient = this.getOrientation();
        if (orient == PlotOrientation.HORIZONTAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.zoomRange(lowerPercent, upperPercent);
                }
            }
        }
        else if (orient == PlotOrientation.VERTICAL) {
            for (int i = 0; i < this.domainAxes.size(); ++i) {
                final ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
                if (domainAxis != null) {
                    domainAxis.zoomRange(lowerPercent, upperPercent);
                }
            }
        }
    }
    
    public void zoomVerticalAxes(final double factor) {
        final PlotOrientation orient = this.getOrientation();
        if (orient == PlotOrientation.HORIZONTAL) {
            for (int i = 0; i < this.domainAxes.size(); ++i) {
                final ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
                if (domainAxis != null) {
                    domainAxis.resizeRange(factor);
                }
            }
        }
        else if (orient == PlotOrientation.VERTICAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.resizeRange(factor);
                }
            }
        }
    }
    
    public void zoomVerticalAxes(final double lowerPercent, final double upperPercent) {
        final PlotOrientation orient = this.getOrientation();
        if (orient == PlotOrientation.VERTICAL) {
            for (int i = 0; i < this.rangeAxes.size(); ++i) {
                final ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
                if (rangeAxis != null) {
                    rangeAxis.zoomRange(lowerPercent, upperPercent);
                }
            }
        }
        else if (orient == PlotOrientation.HORIZONTAL) {
            for (int i = 0; i < this.domainAxes.size(); ++i) {
                final ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
                if (domainAxis != null) {
                    domainAxis.zoomRange(lowerPercent, upperPercent);
                }
            }
        }
    }
    
    public int getSeriesCount() {
        int result = 0;
        final XYDataset dataset = this.getDataset();
        if (dataset != null) {
            result = dataset.getSeriesCount();
        }
        return result;
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
                final XYDataset dataset = this.getDataset(datasetIndex);
                if (dataset != null) {
                    final XYItemRenderer renderer = this.getRenderer(datasetIndex);
                    if (renderer != null) {
                        for (int seriesCount = dataset.getSeriesCount(), i = 0; i < seriesCount; ++i) {
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
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYPlot)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final XYPlot p = (XYPlot)obj;
        return this.weight == p.weight && this.orientation == p.orientation && this.domainAxes.equals(p.domainAxes) && this.domainAxisLocations.equals(p.domainAxisLocations) && this.rangeCrosshairLockedOnData == p.rangeCrosshairLockedOnData && this.domainGridlinesVisible == p.domainGridlinesVisible && this.rangeGridlinesVisible == p.rangeGridlinesVisible && this.domainCrosshairVisible == p.domainCrosshairVisible && this.domainCrosshairValue == p.domainCrosshairValue && this.domainCrosshairLockedOnData == p.domainCrosshairLockedOnData && this.rangeCrosshairVisible == p.rangeCrosshairVisible && this.rangeCrosshairValue == p.rangeCrosshairValue && ObjectUtils.equal(this.axisOffset, p.axisOffset) && ObjectUtils.equal(this.renderers, p.renderers) && ObjectUtils.equal(this.rangeAxes, p.rangeAxes) && this.rangeAxisLocations.equals(p.rangeAxisLocations) && ObjectUtils.equal(this.domainAxisMap, p.domainAxisMap) && ObjectUtils.equal(this.rangeAxisMap, p.rangeAxisMap) && ObjectUtils.equal(this.domainGridlineStroke, p.domainGridlineStroke) && ObjectUtils.equal(this.domainGridlinePaint, p.domainGridlinePaint) && ObjectUtils.equal(this.rangeGridlineStroke, p.rangeGridlineStroke) && ObjectUtils.equal(this.rangeGridlinePaint, p.rangeGridlinePaint) && ObjectUtils.equal(this.domainCrosshairStroke, p.domainCrosshairStroke) && ObjectUtils.equal(this.domainCrosshairPaint, p.domainCrosshairPaint) && ObjectUtils.equal(this.rangeCrosshairStroke, p.rangeCrosshairStroke) && ObjectUtils.equal(this.rangeCrosshairPaint, p.rangeCrosshairPaint) && ObjectUtils.equal(this.foregroundDomainMarkers, p.foregroundDomainMarkers) && ObjectUtils.equal(this.backgroundDomainMarkers, p.backgroundDomainMarkers) && ObjectUtils.equal(this.foregroundRangeMarkers, p.foregroundRangeMarkers) && ObjectUtils.equal(this.backgroundRangeMarkers, p.backgroundRangeMarkers) && ObjectUtils.equal(this.foregroundDomainMarkers, p.foregroundDomainMarkers) && ObjectUtils.equal(this.backgroundDomainMarkers, p.backgroundDomainMarkers) && ObjectUtils.equal(this.foregroundRangeMarkers, p.foregroundRangeMarkers) && ObjectUtils.equal(this.backgroundRangeMarkers, p.backgroundRangeMarkers) && ObjectUtils.equal(this.annotations, p.annotations);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final XYPlot clone = (XYPlot)super.clone();
        clone.domainAxes = (ObjectList)ObjectUtils.clone(this.domainAxes);
        for (int i = 0; i < this.domainAxes.size(); ++i) {
            final ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
            if (axis != null) {
                final ValueAxis clonedAxis = (ValueAxis)axis.clone();
                clone.domainAxes.set(i, clonedAxis);
                clonedAxis.setPlot(clone);
                clonedAxis.addChangeListener(clone);
            }
        }
        clone.domainAxisLocations = (ObjectList)this.domainAxisLocations.clone();
        clone.rangeAxes = (ObjectList)ObjectUtils.clone(this.rangeAxes);
        for (int i = 0; i < this.rangeAxes.size(); ++i) {
            final ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
            if (axis != null) {
                final ValueAxis clonedAxis = (ValueAxis)axis.clone();
                clone.rangeAxes.set(i, clonedAxis);
                clonedAxis.setPlot(clone);
                clonedAxis.addChangeListener(clone);
            }
        }
        clone.rangeAxisLocations = (ObjectList)ObjectUtils.clone(this.rangeAxisLocations);
        clone.datasets = (ObjectList)ObjectUtils.clone(this.datasets);
        for (int i = 0; i < clone.datasets.size(); ++i) {
            final XYDataset d = this.getDataset(i);
            if (d != null) {
                d.addChangeListener(clone);
            }
        }
        clone.domainAxisMap = (ObjectList)ObjectUtils.clone(this.domainAxisMap);
        clone.rangeAxisMap = (ObjectList)ObjectUtils.clone(this.rangeAxisMap);
        clone.renderers = (ObjectList)ObjectUtils.clone(this.renderers);
        for (int i = 0; i < this.renderers.size(); ++i) {
            final XYItemRenderer renderer2 = (XYItemRenderer)this.renderers.get(i);
            if (renderer2 instanceof PublicCloneable) {
                final PublicCloneable pc = (PublicCloneable)renderer2;
                clone.renderers.set(i, pc.clone());
            }
        }
        clone.foregroundDomainMarkers = (Map)ObjectUtils.clone(this.foregroundDomainMarkers);
        clone.backgroundDomainMarkers = (Map)ObjectUtils.clone(this.backgroundDomainMarkers);
        clone.foregroundRangeMarkers = (Map)ObjectUtils.clone(this.foregroundRangeMarkers);
        clone.backgroundRangeMarkers = (Map)ObjectUtils.clone(this.backgroundRangeMarkers);
        clone.annotations = ObjectUtils.clone(this.annotations);
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
        SerialUtilities.writeStroke(this.domainCrosshairStroke, stream);
        SerialUtilities.writePaint(this.domainCrosshairPaint, stream);
        SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
        SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
        SerialUtilities.writePaint(this.domainTickBandPaint, stream);
        SerialUtilities.writePaint(this.rangeTickBandPaint, stream);
        SerialUtilities.writePoint2D(this.quadrantOrigin, stream);
        for (int i = 0; i < 4; ++i) {
            SerialUtilities.writePaint(this.quadrantPaint[i], stream);
        }
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.domainGridlineStroke = SerialUtilities.readStroke(stream);
        this.domainGridlinePaint = SerialUtilities.readPaint(stream);
        this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
        this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
        this.domainCrosshairStroke = SerialUtilities.readStroke(stream);
        this.domainCrosshairPaint = SerialUtilities.readPaint(stream);
        this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
        this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
        this.domainTickBandPaint = SerialUtilities.readPaint(stream);
        this.rangeTickBandPaint = SerialUtilities.readPaint(stream);
        this.quadrantOrigin = SerialUtilities.readPoint2D(stream);
        this.quadrantPaint = new Paint[4];
        for (int i = 0; i < 4; ++i) {
            this.quadrantPaint[i] = SerialUtilities.readPaint(stream);
        }
        this.foregroundDomainMarkers = new HashMap();
        this.backgroundDomainMarkers = new HashMap();
        this.foregroundRangeMarkers = new HashMap();
        this.backgroundRangeMarkers = new HashMap();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5f, 0, 2, 0.0f, new float[] { 2.0f, 2.0f }, 0.0f);
        DEFAULT_GRIDLINE_PAINT = Color.lightGray;
        DEFAULT_CROSSHAIR_STROKE = XYPlot.DEFAULT_GRIDLINE_STROKE;
        DEFAULT_CROSSHAIR_PAINT = Color.blue;
        XYPlot.localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
        LOGGER = Log.createContext((XYPlot.class$org$jfree$chart$plot$XYPlot == null) ? (XYPlot.class$org$jfree$chart$plot$XYPlot = class$("org.jfree.chart.plot.XYPlot")) : XYPlot.class$org$jfree$chart$plot$XYPlot);
    }
}
