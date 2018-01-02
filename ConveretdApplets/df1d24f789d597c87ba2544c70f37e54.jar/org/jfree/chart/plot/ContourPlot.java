// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import org.jfree.util.ObjectUtils;
import org.jfree.chart.event.AxisChangeEvent;
import java.beans.PropertyChangeEvent;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.Range;
import java.awt.geom.Line2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Ellipse2D;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.ContourEntity;
import org.jfree.chart.axis.NumberAxis;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.Iterator;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Shape;
import org.jfree.chart.axis.AxisSpace;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.annotations.XYAnnotation;
import java.util.ArrayList;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetChangeEvent;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.labels.StandardContourToolTipGenerator;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.data.DatasetChangeListener;
import java.util.ResourceBundle;
import org.jfree.chart.ClipPath;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.ContourToolTipGenerator;
import java.util.List;
import java.awt.Paint;
import java.awt.Stroke;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.ColorBar;
import org.jfree.data.ContourDataset;
import org.jfree.chart.axis.ValueAxis;
import java.awt.Insets;
import java.io.Serializable;
import java.beans.PropertyChangeListener;

public class ContourPlot extends Plot implements ContourValuePlot, ValueAxisPlot, PropertyChangeListener, Serializable, Cloneable
{
    protected static final Insets DEFAULT_INSETS;
    private ValueAxis domainAxis;
    private ValueAxis rangeAxis;
    private ContourDataset dataset;
    private ColorBar colorBar;
    private RectangleEdge colorBarLocation;
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
    private List domainMarkers;
    private List rangeMarkers;
    private List annotations;
    private ContourToolTipGenerator toolTipGenerator;
    private XYURLGenerator urlGenerator;
    private boolean renderAsPoints;
    private double ptSizePct;
    private transient ClipPath clipPath;
    private transient Paint missingPaint;
    protected static ResourceBundle localizationResources;
    
    public ContourPlot(final ContourDataset dataset, final ValueAxis domainAxis, final ValueAxis rangeAxis, final ColorBar colorBar) {
        this.colorBar = null;
        this.domainCrosshairLockedOnData = true;
        this.rangeCrosshairLockedOnData = true;
        this.renderAsPoints = false;
        this.ptSizePct = 0.05;
        this.clipPath = null;
        this.missingPaint = null;
        this.dataset = dataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }
        if ((this.domainAxis = domainAxis) != null) {
            domainAxis.setPlot(this);
            domainAxis.addChangeListener(this);
        }
        if ((this.rangeAxis = rangeAxis) != null) {
            rangeAxis.setPlot(this);
            rangeAxis.addChangeListener(this);
        }
        if ((this.colorBar = colorBar) != null) {
            colorBar.getAxis().setPlot(this);
            colorBar.getAxis().addChangeListener(this);
            colorBar.configure(this);
        }
        this.colorBarLocation = RectangleEdge.LEFT;
        this.toolTipGenerator = new StandardContourToolTipGenerator();
    }
    
    public RectangleEdge getColorBarLocation() {
        return this.colorBarLocation;
    }
    
    public void setColorBarLocation(final RectangleEdge edge) {
        this.colorBarLocation = edge;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public ContourDataset getDataset() {
        return this.dataset;
    }
    
    public void setDataset(final ContourDataset dataset) {
        final ContourDataset existing = this.dataset;
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        if ((this.dataset = dataset) != null) {
            this.setDatasetGroup(dataset.getGroup());
            dataset.addChangeListener(this);
        }
        final DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        this.datasetChanged(event);
    }
    
    public ContourDataset getContourDataset() {
        return this.getDataset();
    }
    
    public ValueAxis getDomainAxis() {
        final ValueAxis result = this.domainAxis;
        return result;
    }
    
    public void setDomainAxis(final ValueAxis axis) {
        if (this.isCompatibleDomainAxis(axis)) {
            if (axis != null) {
                axis.setPlot(this);
                axis.addChangeListener(this);
            }
            if (this.domainAxis != null) {
                this.domainAxis.removeChangeListener(this);
            }
            this.domainAxis = axis;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public ValueAxis getRangeAxis() {
        final ValueAxis result = this.rangeAxis;
        return result;
    }
    
    public void setRangeAxis(final ValueAxis axis) {
        if (axis != null) {
            axis.setPlot(this);
            axis.addChangeListener(this);
        }
        if (this.rangeAxis != null) {
            this.rangeAxis.removeChangeListener(this);
        }
        this.rangeAxis = axis;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void setColorBarAxis(final ColorBar axis) {
        this.colorBar = axis;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void addDomainMarker(final Marker marker) {
        if (this.domainMarkers == null) {
            this.domainMarkers = new ArrayList();
        }
        this.domainMarkers.add(marker);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void clearDomainMarkers() {
        if (this.domainMarkers != null) {
            this.domainMarkers.clear();
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public void addRangeMarker(final Marker marker) {
        if (this.rangeMarkers == null) {
            this.rangeMarkers = new ArrayList();
        }
        this.rangeMarkers.add(marker);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void clearRangeMarkers() {
        if (this.rangeMarkers != null) {
            this.rangeMarkers.clear();
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public void addAnnotation(final XYAnnotation annotation) {
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
    
    public boolean isCompatibleDomainAxis(final ValueAxis axis) {
        return true;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D plotArea, final PlotState parentState, final PlotRenderingInfo info) {
        final boolean b1 = plotArea.getWidth() <= 10.0;
        final boolean b2 = plotArea.getHeight() <= 10.0;
        if (b1 || b2) {
            return;
        }
        if (info != null) {
            info.setPlotArea(plotArea);
        }
        final Insets insets = this.getInsets();
        if (insets != null) {
            plotArea.setRect(plotArea.getX() + insets.left, plotArea.getY() + insets.top, plotArea.getWidth() - insets.left - insets.right, plotArea.getHeight() - insets.top - insets.bottom);
        }
        AxisSpace space = new AxisSpace();
        space = this.domainAxis.reserveSpace(g2, this, plotArea, RectangleEdge.BOTTOM, space);
        space = this.rangeAxis.reserveSpace(g2, this, plotArea, RectangleEdge.LEFT, space);
        final Rectangle2D estimatedDataArea = space.shrink(plotArea, null);
        AxisSpace space2 = new AxisSpace();
        space2 = this.colorBar.reserveSpace(g2, this, plotArea, estimatedDataArea, this.colorBarLocation, space2);
        final Rectangle2D adjustedPlotArea = space2.shrink(plotArea, null);
        final Rectangle2D dataArea = space.shrink(adjustedPlotArea, null);
        final Rectangle2D colorBarArea = space2.reserved(plotArea, this.colorBarLocation);
        if (this.getDataAreaRatio() != 0.0) {
            double ratio = this.getDataAreaRatio();
            final Rectangle2D tmpDataArea = (Rectangle2D)dataArea.clone();
            double h = tmpDataArea.getHeight();
            double w = tmpDataArea.getWidth();
            if (ratio > 0.0) {
                if (w * ratio <= h) {
                    h = ratio * w;
                }
                else {
                    w = h / ratio;
                }
            }
            else {
                ratio *= -1.0;
                final double xLength = this.getDomainAxis().getRange().getLength();
                final double yLength = this.getRangeAxis().getRange().getLength();
                final double unitRatio = yLength / xLength;
                ratio *= unitRatio;
                if (w * ratio <= h) {
                    h = ratio * w;
                }
                else {
                    w = h / ratio;
                }
            }
            dataArea.setRect(tmpDataArea.getX() + tmpDataArea.getWidth() / 2.0 - w / 2.0, tmpDataArea.getY(), w, h);
        }
        if (info != null) {
            info.setDataArea(dataArea);
        }
        final CrosshairState crosshairState = new CrosshairState();
        crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
        this.drawBackground(g2, dataArea);
        double cursor = dataArea.getMaxY();
        if (this.domainAxis != null) {
            this.domainAxis.draw(g2, cursor, adjustedPlotArea, dataArea, RectangleEdge.BOTTOM, info);
        }
        if (this.rangeAxis != null) {
            cursor = dataArea.getMinX();
            this.rangeAxis.draw(g2, cursor, adjustedPlotArea, dataArea, RectangleEdge.LEFT, info);
        }
        if (this.colorBar != null) {
            cursor = 0.0;
            cursor = this.colorBar.draw(g2, cursor, adjustedPlotArea, dataArea, colorBarArea, this.colorBarLocation);
        }
        final Shape originalClip = g2.getClip();
        final Composite originalComposite = g2.getComposite();
        g2.clip(dataArea);
        g2.setComposite(AlphaComposite.getInstance(3, this.getForegroundAlpha()));
        this.render(g2, dataArea, info, crosshairState);
        if (this.domainMarkers != null) {
            for (final Marker marker : this.domainMarkers) {
                this.drawDomainMarker(g2, this, this.getDomainAxis(), marker, dataArea);
            }
        }
        if (this.rangeMarkers != null) {
            for (final Marker marker : this.rangeMarkers) {
                this.drawRangeMarker(g2, this, this.getRangeAxis(), marker, dataArea);
            }
        }
        g2.setClip(originalClip);
        g2.setComposite(originalComposite);
        this.drawOutline(g2, dataArea);
    }
    
    public void render(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info, final CrosshairState crosshairState) {
        final ContourDataset data = this.getContourDataset();
        if (data != null) {
            final ColorBar zAxis = this.getColorBar();
            if (this.clipPath != null) {
                final GeneralPath clipper = this.getClipPath().draw(g2, dataArea, this.domainAxis, this.rangeAxis);
                if (this.clipPath.isClip()) {
                    g2.clip(clipper);
                }
            }
            if (this.renderAsPoints) {
                this.pointRenderer(g2, dataArea, info, this, this.domainAxis, this.rangeAxis, zAxis, data, crosshairState);
            }
            else {
                this.contourRenderer(g2, dataArea, info, this, this.domainAxis, this.rangeAxis, zAxis, data, crosshairState);
            }
            this.setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
            if (this.isDomainCrosshairVisible()) {
                this.drawVerticalLine(g2, dataArea, this.getDomainCrosshairValue(), this.getDomainCrosshairStroke(), this.getDomainCrosshairPaint());
            }
            this.setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
            if (this.isRangeCrosshairVisible()) {
                this.drawHorizontalLine(g2, dataArea, this.getRangeCrosshairValue(), this.getRangeCrosshairStroke(), this.getRangeCrosshairPaint());
            }
        }
        else if (this.clipPath != null) {
            this.getClipPath().draw(g2, dataArea, this.domainAxis, this.rangeAxis);
        }
    }
    
    public void contourRenderer(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info, final ContourPlot plot, final ValueAxis horizontalAxis, final ValueAxis verticalAxis, final ColorBar colorBar, final ContourDataset data, final CrosshairState crosshairState) {
        Rectangle2D.Double entityArea = null;
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }
        Rectangle2D.Double rect = null;
        rect = new Rectangle2D.Double();
        final Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        final Number[] xNumber = data.getXValues();
        final Number[] yNumber = data.getYValues();
        final Number[] zNumber = data.getZValues();
        final double[] x = new double[xNumber.length];
        final double[] y = new double[yNumber.length];
        for (int i = 0; i < x.length; ++i) {
            x[i] = xNumber[i].doubleValue();
            y[i] = yNumber[i].doubleValue();
        }
        final int[] xIndex = data.indexX();
        final int[] indexX = data.getXIndices();
        final boolean vertInverted = ((NumberAxis)verticalAxis).isInverted();
        boolean horizInverted = false;
        if (horizontalAxis instanceof NumberAxis) {
            horizInverted = ((NumberAxis)horizontalAxis).isInverted();
        }
        double transX = 0.0;
        double transXm1 = 0.0;
        double transXp1 = 0.0;
        double transDXm1 = 0.0;
        double transDXp1 = 0.0;
        double transDX = 0.0;
        double transY = 0.0;
        double transYm1 = 0.0;
        double transYp1 = 0.0;
        double transDYm1 = 0.0;
        double transDYp1 = 0.0;
        double transDY = 0.0;
        final int iMax = xIndex[xIndex.length - 1];
        for (int k = 0; k < x.length; ++k) {
            final int j = xIndex[k];
            if (indexX[j] == k) {
                if (j == 0) {
                    transX = (transXm1 = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM));
                    transXp1 = horizontalAxis.valueToJava2D(x[indexX[j + 1]], dataArea, RectangleEdge.BOTTOM);
                    transDXm1 = Math.abs(0.5 * (transX - transXm1));
                    transDXp1 = Math.abs(0.5 * (transX - transXp1));
                }
                else if (j == iMax) {
                    transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
                    transXm1 = horizontalAxis.valueToJava2D(x[indexX[j - 1]], dataArea, RectangleEdge.BOTTOM);
                    transXp1 = transX;
                    transDXm1 = Math.abs(0.5 * (transX - transXm1));
                    transDXp1 = Math.abs(0.5 * (transX - transXp1));
                }
                else {
                    transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
                    transXp1 = horizontalAxis.valueToJava2D(x[indexX[j + 1]], dataArea, RectangleEdge.BOTTOM);
                    transDXm1 = transDXp1;
                    transDXp1 = Math.abs(0.5 * (transX - transXp1));
                }
                if (horizInverted) {
                    transX -= transDXp1;
                }
                else {
                    transX -= transDXm1;
                }
                transDX = transDXm1 + transDXp1;
                transY = (transYm1 = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT));
                if (k + 1 == y.length) {
                    continue;
                }
                transYp1 = verticalAxis.valueToJava2D(y[k + 1], dataArea, RectangleEdge.LEFT);
                transDYm1 = Math.abs(0.5 * (transY - transYm1));
                transDYp1 = Math.abs(0.5 * (transY - transYp1));
            }
            else if ((j < indexX.length - 1 && indexX[j + 1] - 1 == k) || k == x.length - 1) {
                transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
                transYm1 = verticalAxis.valueToJava2D(y[k - 1], dataArea, RectangleEdge.LEFT);
                transYp1 = transY;
                transDYm1 = Math.abs(0.5 * (transY - transYm1));
                transDYp1 = Math.abs(0.5 * (transY - transYp1));
            }
            else {
                transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
                transYp1 = verticalAxis.valueToJava2D(y[k + 1], dataArea, RectangleEdge.LEFT);
                transDYm1 = transDYp1;
                transDYp1 = Math.abs(0.5 * (transY - transYp1));
            }
            if (vertInverted) {
                transY -= transDYm1;
            }
            else {
                transY -= transDYp1;
            }
            transDY = transDYm1 + transDYp1;
            rect.setRect(transX, transY, transDX, transDY);
            if (zNumber[k] != null) {
                g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
                g2.fill(rect);
            }
            else if (this.missingPaint != null) {
                g2.setPaint(this.missingPaint);
                g2.fill(rect);
            }
            entityArea = rect;
            if (entities != null) {
                String tip = "";
                if (this.getToolTipGenerator() != null) {
                    tip = this.toolTipGenerator.generateToolTip(data, k);
                }
                final String url = null;
                final ContourEntity entity = new ContourEntity((Shape)entityArea.clone(), tip, url);
                entity.setIndex(k);
                entities.addEntity(entity);
            }
            if (plot.isDomainCrosshairLockedOnData()) {
                if (plot.isRangeCrosshairLockedOnData()) {
                    crosshairState.updateCrosshairPoint(x[k], y[k], transX, transY, PlotOrientation.VERTICAL);
                }
                else {
                    crosshairState.updateCrosshairX(transX);
                }
            }
            else if (plot.isRangeCrosshairLockedOnData()) {
                crosshairState.updateCrosshairY(transY);
            }
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
    }
    
    public void pointRenderer(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info, final ContourPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final ColorBar colorBar, final ContourDataset data, final CrosshairState crosshairState) {
        RectangularShape entityArea = null;
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }
        final RectangularShape rect = new Ellipse2D.Double();
        final Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        final Number[] xNumber = data.getXValues();
        final Number[] yNumber = data.getYValues();
        final Number[] zNumber = data.getZValues();
        final double[] x = new double[xNumber.length];
        final double[] y = new double[yNumber.length];
        for (int i = 0; i < x.length; ++i) {
            x[i] = xNumber[i].doubleValue();
            y[i] = yNumber[i].doubleValue();
        }
        double transX = 0.0;
        double transDX = 0.0;
        double transY = 0.0;
        double transDY = 0.0;
        final double size = dataArea.getWidth() * this.ptSizePct;
        for (int k = 0; k < x.length; ++k) {
            transX = domainAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM) - 0.5 * size;
            transY = rangeAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT) - 0.5 * size;
            transDX = size;
            transDY = size;
            rect.setFrame(transX, transY, transDX, transDY);
            if (zNumber[k] != null) {
                g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
                g2.fill(rect);
            }
            else if (this.missingPaint != null) {
                g2.setPaint(this.missingPaint);
                g2.fill(rect);
            }
            entityArea = rect;
            if (entities != null) {
                String tip = null;
                if (this.getToolTipGenerator() != null) {
                    tip = this.toolTipGenerator.generateToolTip(data, k);
                }
                final String url = null;
                final ContourEntity entity = new ContourEntity((Shape)entityArea.clone(), tip, url);
                entity.setIndex(k);
                entities.addEntity(entity);
            }
            if (plot.isDomainCrosshairLockedOnData()) {
                if (plot.isRangeCrosshairLockedOnData()) {
                    crosshairState.updateCrosshairPoint(x[k], y[k], transX, transY, PlotOrientation.VERTICAL);
                }
                else {
                    crosshairState.updateCrosshairX(transX);
                }
            }
            else if (plot.isRangeCrosshairLockedOnData()) {
                crosshairState.updateCrosshairY(transY);
            }
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
    }
    
    protected void drawVerticalLine(final Graphics2D g2, final Rectangle2D dataArea, final double value, final Stroke stroke, final Paint paint) {
        final double xx = this.getDomainAxis().valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
        final Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
        g2.setStroke(stroke);
        g2.setPaint(paint);
        g2.draw(line);
    }
    
    protected void drawHorizontalLine(final Graphics2D g2, final Rectangle2D dataArea, final double value, final Stroke stroke, final Paint paint) {
        final double yy = this.getRangeAxis().valueToJava2D(value, dataArea, RectangleEdge.LEFT);
        final Line2D line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
        g2.setStroke(stroke);
        g2.setPaint(paint);
        g2.draw(line);
    }
    
    public void handleClick(final int x, final int y, final PlotRenderingInfo info) {
    }
    
    public void zoom(final double percent) {
        if (percent <= 0.0) {
            this.getRangeAxis().setAutoRange(true);
            this.getDomainAxis().setAutoRange(true);
        }
    }
    
    public String getPlotType() {
        return ContourPlot.localizationResources.getString("Contour_Plot");
    }
    
    public Range getDataRange(final ValueAxis axis) {
        if (this.dataset == null) {
            return null;
        }
        Range result = null;
        if (axis == this.getDomainAxis()) {
            result = DatasetUtilities.getDomainExtent(this.dataset);
        }
        else if (axis == this.getRangeAxis()) {
            result = DatasetUtilities.getRangeExtent(this.dataset);
        }
        return result;
    }
    
    public Range getContourDataRange() {
        Range result = null;
        final ContourDataset data = this.getDataset();
        if (data != null) {
            final Range h = this.getDomainAxis().getRange();
            final Range v = this.getRangeAxis().getRange();
            result = this.visibleRange(data, h, v);
        }
        return result;
    }
    
    public void propertyChange(final PropertyChangeEvent event) {
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void datasetChanged(final DatasetChangeEvent event) {
        if (this.domainAxis != null) {
            this.domainAxis.configure();
        }
        if (this.rangeAxis != null) {
            this.rangeAxis.configure();
        }
        if (this.colorBar != null) {
            this.colorBar.configure(this);
        }
        final PlotChangeEvent e = new PlotChangeEvent(this);
        this.notifyListeners(e);
    }
    
    public ColorBar getColorBar() {
        return this.colorBar;
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
    
    public ContourToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }
    
    public void setToolTipGenerator(final ContourToolTipGenerator generator) {
        this.toolTipGenerator = generator;
    }
    
    public XYURLGenerator getURLGenerator() {
        return this.urlGenerator;
    }
    
    public void setURLGenerator(final XYURLGenerator urlGenerator) {
        this.urlGenerator = urlGenerator;
    }
    
    public void drawDomainMarker(final Graphics2D g2, final ContourPlot plot, final ValueAxis domainAxis, final Marker marker, final Rectangle2D dataArea) {
        if (marker instanceof ValueMarker) {
            final ValueMarker vm = (ValueMarker)marker;
            final double value = vm.getValue();
            final Range range = domainAxis.getRange();
            if (!range.contains(value)) {
                return;
            }
            final double x = domainAxis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
            final Line2D line = new Line2D.Double(x, dataArea.getMinY(), x, dataArea.getMaxY());
            final Paint paint = marker.getOutlinePaint();
            final Stroke stroke = marker.getOutlineStroke();
            g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
            g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
            g2.draw(line);
        }
    }
    
    public void drawRangeMarker(final Graphics2D g2, final ContourPlot plot, final ValueAxis rangeAxis, final Marker marker, final Rectangle2D dataArea) {
        if (marker instanceof ValueMarker) {
            final ValueMarker vm = (ValueMarker)marker;
            final double value = vm.getValue();
            final Range range = rangeAxis.getRange();
            if (!range.contains(value)) {
                return;
            }
            final double y = rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
            final Line2D line = new Line2D.Double(dataArea.getMinX(), y, dataArea.getMaxX(), y);
            final Paint paint = marker.getOutlinePaint();
            final Stroke stroke = marker.getOutlineStroke();
            g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
            g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
            g2.draw(line);
        }
    }
    
    public ClipPath getClipPath() {
        return this.clipPath;
    }
    
    public void setClipPath(final ClipPath clipPath) {
        this.clipPath = clipPath;
    }
    
    public double getPtSizePct() {
        return this.ptSizePct;
    }
    
    public boolean isRenderAsPoints() {
        return this.renderAsPoints;
    }
    
    public void setPtSizePct(final double ptSizePct) {
        this.ptSizePct = ptSizePct;
    }
    
    public void setRenderAsPoints(final boolean renderAsPoints) {
        this.renderAsPoints = renderAsPoints;
    }
    
    public void axisChanged(final AxisChangeEvent event) {
        final Object source = event.getSource();
        if (source.equals(this.rangeAxis) || source.equals(this.domainAxis)) {
            final ColorBar cba = this.colorBar;
            if (this.colorBar.getAxis().isAutoRange()) {
                cba.getAxis().configure();
            }
        }
        super.axisChanged(event);
    }
    
    public Range visibleRange(final ContourDataset data, final Range x, final Range y) {
        Range range = null;
        range = data.getZValueRange(x, y);
        return range;
    }
    
    public Paint getMissingPaint() {
        return this.missingPaint;
    }
    
    public void setMissingPaint(final Paint missingPaint) {
        this.missingPaint = missingPaint;
    }
    
    public void zoomHorizontalAxes(final double factor) {
    }
    
    public void zoomHorizontalAxes(final double lowerPercent, final double upperPercent) {
    }
    
    public void zoomVerticalAxes(final double factor) {
    }
    
    public void zoomVerticalAxes(final double lowerPercent, final double upperPercent) {
    }
    
    public Object clone() throws CloneNotSupportedException {
        final ContourPlot clone = (ContourPlot)super.clone();
        if (this.domainAxis != null) {
            (clone.domainAxis = (ValueAxis)this.domainAxis.clone()).setPlot(clone);
            clone.domainAxis.addChangeListener(clone);
        }
        if (this.rangeAxis != null) {
            (clone.rangeAxis = (ValueAxis)this.rangeAxis.clone()).setPlot(clone);
            clone.rangeAxis.addChangeListener(clone);
        }
        if (clone.dataset != null) {
            clone.dataset.addChangeListener(clone);
        }
        if (this.colorBar != null) {
            clone.colorBar = (ColorBar)this.colorBar.clone();
        }
        clone.domainMarkers = ObjectUtils.clone(this.domainMarkers);
        clone.rangeMarkers = ObjectUtils.clone(this.rangeMarkers);
        clone.annotations = ObjectUtils.clone(this.annotations);
        if (this.clipPath != null) {
            clone.clipPath = (ClipPath)this.clipPath.clone();
        }
        return clone;
    }
    
    static {
        DEFAULT_INSETS = new Insets(2, 2, 100, 10);
        ContourPlot.localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
    }
}
