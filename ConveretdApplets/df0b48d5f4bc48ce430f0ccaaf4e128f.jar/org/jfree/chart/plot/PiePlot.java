// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.ui.RectangleAnchor;
import org.jfree.chart.LegendItem;
import java.awt.geom.Line2D;
import org.jfree.chart.LegendItemCollection;
import org.jfree.text.TextBlock;
import org.jfree.text.TextBox;
import org.jfree.text.TextMeasurer;
import org.jfree.text.TextUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.data.KeyedValues;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.util.ShapeUtilities;
import java.awt.geom.Arc2D;
import java.util.List;
import org.jfree.ui.RectangleInsets;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.geom.Point2D;
import org.jfree.data.general.DatasetUtilities;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.util.Iterator;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import java.util.TreeMap;
import org.jfree.data.general.DatasetChangeListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ResourceBundle;
import java.awt.Shape;
import org.jfree.chart.urls.PieURLGenerator;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import java.util.Map;
import org.jfree.chart.StrokeMap;
import org.jfree.chart.PaintMap;
import org.jfree.util.Rotation;
import org.jfree.data.general.PieDataset;
import java.awt.Stroke;
import java.awt.Paint;
import java.awt.Font;
import java.io.Serializable;

public class PiePlot extends Plot implements Cloneable, Serializable
{
    private static final long serialVersionUID = -795612466005590431L;
    public static final double DEFAULT_INTERIOR_GAP = 0.25;
    public static final double MAX_INTERIOR_GAP = 0.4;
    public static final double DEFAULT_START_ANGLE = 90.0;
    public static final Font DEFAULT_LABEL_FONT;
    public static final Paint DEFAULT_LABEL_PAINT;
    public static final Paint DEFAULT_LABEL_BACKGROUND_PAINT;
    public static final Paint DEFAULT_LABEL_OUTLINE_PAINT;
    public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE;
    public static final Paint DEFAULT_LABEL_SHADOW_PAINT;
    public static final double DEFAULT_MINIMUM_ARC_ANGLE_TO_DRAW = 1.0E-5;
    private PieDataset dataset;
    private int pieIndex;
    private double interiorGap;
    private boolean circular;
    private double startAngle;
    private Rotation direction;
    private transient Paint sectionPaint;
    private PaintMap sectionPaintMap;
    private transient Paint baseSectionPaint;
    private boolean sectionOutlinesVisible;
    private transient Paint sectionOutlinePaint;
    private PaintMap sectionOutlinePaintMap;
    private transient Paint baseSectionOutlinePaint;
    private transient Stroke sectionOutlineStroke;
    private StrokeMap sectionOutlineStrokeMap;
    private transient Stroke baseSectionOutlineStroke;
    private transient Paint shadowPaint;
    private double shadowXOffset;
    private double shadowYOffset;
    private Map explodePercentages;
    private PieSectionLabelGenerator labelGenerator;
    private Font labelFont;
    private transient Paint labelPaint;
    private transient Paint labelBackgroundPaint;
    private transient Paint labelOutlinePaint;
    private transient Stroke labelOutlineStroke;
    private transient Paint labelShadowPaint;
    private double maximumLabelWidth;
    private double labelGap;
    private boolean labelLinksVisible;
    private double labelLinkMargin;
    private transient Paint labelLinkPaint;
    private transient Stroke labelLinkStroke;
    private AbstractPieLabelDistributor labelDistributor;
    private PieToolTipGenerator toolTipGenerator;
    private PieURLGenerator urlGenerator;
    private PieSectionLabelGenerator legendLabelGenerator;
    private PieSectionLabelGenerator legendLabelToolTipGenerator;
    private PieURLGenerator legendLabelURLGenerator;
    private boolean ignoreNullValues;
    private boolean ignoreZeroValues;
    private transient Shape legendItemShape;
    private double minimumArcAngleToDraw;
    protected static ResourceBundle localizationResources;
    
    public PiePlot() {
        this(null);
    }
    
    public PiePlot(final PieDataset dataset) {
        this.shadowPaint = Color.gray;
        this.shadowXOffset = 4.0;
        this.shadowYOffset = 4.0;
        this.maximumLabelWidth = 0.2;
        this.labelGap = 0.05;
        this.labelLinkMargin = 0.05;
        this.labelLinkPaint = Color.black;
        this.labelLinkStroke = new BasicStroke(0.5f);
        this.dataset = dataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }
        this.pieIndex = 0;
        this.interiorGap = 0.25;
        this.circular = true;
        this.startAngle = 90.0;
        this.direction = Rotation.CLOCKWISE;
        this.minimumArcAngleToDraw = 1.0E-5;
        this.sectionPaint = null;
        this.sectionPaintMap = new PaintMap();
        this.baseSectionPaint = Color.gray;
        this.sectionOutlinesVisible = true;
        this.sectionOutlinePaint = null;
        this.sectionOutlinePaintMap = new PaintMap();
        this.baseSectionOutlinePaint = PiePlot.DEFAULT_OUTLINE_PAINT;
        this.sectionOutlineStroke = null;
        this.sectionOutlineStrokeMap = new StrokeMap();
        this.baseSectionOutlineStroke = PiePlot.DEFAULT_OUTLINE_STROKE;
        this.explodePercentages = new TreeMap();
        this.labelGenerator = new StandardPieSectionLabelGenerator();
        this.labelFont = PiePlot.DEFAULT_LABEL_FONT;
        this.labelPaint = PiePlot.DEFAULT_LABEL_PAINT;
        this.labelBackgroundPaint = PiePlot.DEFAULT_LABEL_BACKGROUND_PAINT;
        this.labelOutlinePaint = PiePlot.DEFAULT_LABEL_OUTLINE_PAINT;
        this.labelOutlineStroke = PiePlot.DEFAULT_LABEL_OUTLINE_STROKE;
        this.labelShadowPaint = PiePlot.DEFAULT_LABEL_SHADOW_PAINT;
        this.labelLinksVisible = true;
        this.labelDistributor = new PieLabelDistributor(0);
        this.toolTipGenerator = null;
        this.urlGenerator = null;
        this.legendLabelGenerator = new StandardPieSectionLabelGenerator();
        this.legendLabelToolTipGenerator = null;
        this.legendLabelURLGenerator = null;
        this.legendItemShape = Plot.DEFAULT_LEGEND_ITEM_CIRCLE;
        this.ignoreNullValues = false;
        this.ignoreZeroValues = false;
    }
    
    public PieDataset getDataset() {
        return this.dataset;
    }
    
    public void setDataset(final PieDataset dataset) {
        final PieDataset existing = this.dataset;
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
    
    public int getPieIndex() {
        return this.pieIndex;
    }
    
    public void setPieIndex(final int index) {
        this.pieIndex = index;
    }
    
    public double getStartAngle() {
        return this.startAngle;
    }
    
    public void setStartAngle(final double angle) {
        this.startAngle = angle;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Rotation getDirection() {
        return this.direction;
    }
    
    public void setDirection(final Rotation direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Null 'direction' argument.");
        }
        this.direction = direction;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getInteriorGap() {
        return this.interiorGap;
    }
    
    public void setInteriorGap(final double percent) {
        if (percent < 0.0 || percent > 0.4) {
            throw new IllegalArgumentException("Invalid 'percent' (" + percent + ") argument.");
        }
        if (this.interiorGap != percent) {
            this.interiorGap = percent;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public boolean isCircular() {
        return this.circular;
    }
    
    public void setCircular(final boolean flag) {
        this.setCircular(flag, true);
    }
    
    public void setCircular(final boolean circular, final boolean notify) {
        this.circular = circular;
        if (notify) {
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public boolean getIgnoreNullValues() {
        return this.ignoreNullValues;
    }
    
    public void setIgnoreNullValues(final boolean flag) {
        this.ignoreNullValues = flag;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean getIgnoreZeroValues() {
        return this.ignoreZeroValues;
    }
    
    public void setIgnoreZeroValues(final boolean flag) {
        this.ignoreZeroValues = flag;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    protected Paint lookupSectionPaint(final Comparable key) {
        return this.lookupSectionPaint(key, false);
    }
    
    protected Paint lookupSectionPaint(final Comparable key, final boolean autoPopulate) {
        Paint result = this.getSectionPaint();
        if (result != null) {
            return result;
        }
        result = this.sectionPaintMap.getPaint(key);
        if (result != null) {
            return result;
        }
        if (autoPopulate) {
            final DrawingSupplier ds = this.getDrawingSupplier();
            if (ds != null) {
                result = ds.getNextPaint();
                this.sectionPaintMap.put(key, result);
            }
            else {
                result = this.baseSectionPaint;
            }
        }
        else {
            result = this.baseSectionPaint;
        }
        return result;
    }
    
    public Paint getSectionPaint() {
        return this.sectionPaint;
    }
    
    public void setSectionPaint(final Paint paint) {
        this.sectionPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    protected Comparable getSectionKey(final int section) {
        Comparable key = null;
        if (this.dataset != null && section >= 0 && section < this.dataset.getItemCount()) {
            key = this.dataset.getKey(section);
        }
        if (key == null) {
            key = new Integer(section);
        }
        return key;
    }
    
    public Paint getSectionPaint(final Comparable key) {
        return this.sectionPaintMap.getPaint(key);
    }
    
    public void setSectionPaint(final Comparable key, final Paint paint) {
        this.sectionPaintMap.put(key, paint);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getBaseSectionPaint() {
        return this.baseSectionPaint;
    }
    
    public void setBaseSectionPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.baseSectionPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean getSectionOutlinesVisible() {
        return this.sectionOutlinesVisible;
    }
    
    public void setSectionOutlinesVisible(final boolean visible) {
        this.sectionOutlinesVisible = visible;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    protected Paint lookupSectionOutlinePaint(final Comparable key) {
        return this.lookupSectionOutlinePaint(key, false);
    }
    
    protected Paint lookupSectionOutlinePaint(final Comparable key, final boolean autoPopulate) {
        Paint result = this.getSectionOutlinePaint();
        if (result != null) {
            return result;
        }
        result = this.sectionOutlinePaintMap.getPaint(key);
        if (result != null) {
            return result;
        }
        if (autoPopulate) {
            final DrawingSupplier ds = this.getDrawingSupplier();
            if (ds != null) {
                result = ds.getNextOutlinePaint();
                this.sectionOutlinePaintMap.put(key, result);
            }
            else {
                result = this.baseSectionOutlinePaint;
            }
        }
        else {
            result = this.baseSectionOutlinePaint;
        }
        return result;
    }
    
    public Paint getSectionOutlinePaint() {
        return this.sectionOutlinePaint;
    }
    
    public void setSectionOutlinePaint(final Paint paint) {
        this.sectionOutlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getSectionOutlinePaint(final Comparable key) {
        return this.sectionOutlinePaintMap.getPaint(key);
    }
    
    public void setSectionOutlinePaint(final Comparable key, final Paint paint) {
        this.sectionOutlinePaintMap.put(key, paint);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getBaseSectionOutlinePaint() {
        return this.baseSectionOutlinePaint;
    }
    
    public void setBaseSectionOutlinePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.baseSectionOutlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    protected Stroke lookupSectionOutlineStroke(final Comparable key) {
        return this.lookupSectionOutlineStroke(key, false);
    }
    
    protected Stroke lookupSectionOutlineStroke(final Comparable key, final boolean autoPopulate) {
        Stroke result = this.getSectionOutlineStroke();
        if (result != null) {
            return result;
        }
        result = this.sectionOutlineStrokeMap.getStroke(key);
        if (result != null) {
            return result;
        }
        if (autoPopulate) {
            final DrawingSupplier ds = this.getDrawingSupplier();
            if (ds != null) {
                result = ds.getNextOutlineStroke();
                this.sectionOutlineStrokeMap.put(key, result);
            }
            else {
                result = this.baseSectionOutlineStroke;
            }
        }
        else {
            result = this.baseSectionOutlineStroke;
        }
        return result;
    }
    
    public Stroke getSectionOutlineStroke() {
        return this.sectionOutlineStroke;
    }
    
    public void setSectionOutlineStroke(final Stroke stroke) {
        this.sectionOutlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Stroke getSectionOutlineStroke(final Comparable key) {
        return this.sectionOutlineStrokeMap.getStroke(key);
    }
    
    public void setSectionOutlineStroke(final Comparable key, final Stroke stroke) {
        this.sectionOutlineStrokeMap.put(key, stroke);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Stroke getBaseSectionOutlineStroke() {
        return this.baseSectionOutlineStroke;
    }
    
    public void setBaseSectionOutlineStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.baseSectionOutlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getShadowPaint() {
        return this.shadowPaint;
    }
    
    public void setShadowPaint(final Paint paint) {
        this.shadowPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getShadowXOffset() {
        return this.shadowXOffset;
    }
    
    public void setShadowXOffset(final double offset) {
        this.shadowXOffset = offset;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getShadowYOffset() {
        return this.shadowYOffset;
    }
    
    public void setShadowYOffset(final double offset) {
        this.shadowYOffset = offset;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getExplodePercent(final Comparable key) {
        double result = 0.0;
        if (this.explodePercentages != null) {
            final Number percent = this.explodePercentages.get(key);
            if (percent != null) {
                result = percent.doubleValue();
            }
        }
        return result;
    }
    
    public void setExplodePercent(final Comparable key, final double percent) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        if (this.explodePercentages == null) {
            this.explodePercentages = new TreeMap();
        }
        this.explodePercentages.put(key, new Double(percent));
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getMaximumExplodePercent() {
        double result = 0.0;
        for (final Comparable key : this.dataset.getKeys()) {
            final Number explode = this.explodePercentages.get(key);
            if (explode != null) {
                result = Math.max(result, explode.doubleValue());
            }
        }
        return result;
    }
    
    public PieSectionLabelGenerator getLabelGenerator() {
        return this.labelGenerator;
    }
    
    public void setLabelGenerator(final PieSectionLabelGenerator generator) {
        this.labelGenerator = generator;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getLabelGap() {
        return this.labelGap;
    }
    
    public void setLabelGap(final double gap) {
        this.labelGap = gap;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getMaximumLabelWidth() {
        return this.maximumLabelWidth;
    }
    
    public void setMaximumLabelWidth(final double width) {
        this.maximumLabelWidth = width;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean getLabelLinksVisible() {
        return this.labelLinksVisible;
    }
    
    public void setLabelLinksVisible(final boolean visible) {
        this.labelLinksVisible = visible;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getLabelLinkMargin() {
        return this.labelLinkMargin;
    }
    
    public void setLabelLinkMargin(final double margin) {
        this.labelLinkMargin = margin;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getLabelLinkPaint() {
        return this.labelLinkPaint;
    }
    
    public void setLabelLinkPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.labelLinkPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Stroke getLabelLinkStroke() {
        return this.labelLinkStroke;
    }
    
    public void setLabelLinkStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.labelLinkStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Font getLabelFont() {
        return this.labelFont;
    }
    
    public void setLabelFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.labelFont = font;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getLabelPaint() {
        return this.labelPaint;
    }
    
    public void setLabelPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.labelPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getLabelBackgroundPaint() {
        return this.labelBackgroundPaint;
    }
    
    public void setLabelBackgroundPaint(final Paint paint) {
        this.labelBackgroundPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getLabelOutlinePaint() {
        return this.labelOutlinePaint;
    }
    
    public void setLabelOutlinePaint(final Paint paint) {
        this.labelOutlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Stroke getLabelOutlineStroke() {
        return this.labelOutlineStroke;
    }
    
    public void setLabelOutlineStroke(final Stroke stroke) {
        this.labelOutlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getLabelShadowPaint() {
        return this.labelShadowPaint;
    }
    
    public void setLabelShadowPaint(final Paint paint) {
        this.labelShadowPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public AbstractPieLabelDistributor getLabelDistributor() {
        return this.labelDistributor;
    }
    
    public void setLabelDistributor(final AbstractPieLabelDistributor distributor) {
        if (distributor == null) {
            throw new IllegalArgumentException("Null 'distributor' argument.");
        }
        this.labelDistributor = distributor;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public PieToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }
    
    public void setToolTipGenerator(final PieToolTipGenerator generator) {
        this.toolTipGenerator = generator;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public PieURLGenerator getURLGenerator() {
        return this.urlGenerator;
    }
    
    public void setURLGenerator(final PieURLGenerator generator) {
        this.urlGenerator = generator;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getMinimumArcAngleToDraw() {
        return this.minimumArcAngleToDraw;
    }
    
    public void setMinimumArcAngleToDraw(final double angle) {
        this.minimumArcAngleToDraw = angle;
    }
    
    public Shape getLegendItemShape() {
        return this.legendItemShape;
    }
    
    public void setLegendItemShape(final Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        this.legendItemShape = shape;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public PieSectionLabelGenerator getLegendLabelGenerator() {
        return this.legendLabelGenerator;
    }
    
    public void setLegendLabelGenerator(final PieSectionLabelGenerator generator) {
        if (generator == null) {
            throw new IllegalArgumentException("Null 'generator' argument.");
        }
        this.legendLabelGenerator = generator;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public PieSectionLabelGenerator getLegendLabelToolTipGenerator() {
        return this.legendLabelToolTipGenerator;
    }
    
    public void setLegendLabelToolTipGenerator(final PieSectionLabelGenerator generator) {
        this.legendLabelToolTipGenerator = generator;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public PieURLGenerator getLegendLabelURLGenerator() {
        return this.legendLabelURLGenerator;
    }
    
    public void setLegendLabelURLGenerator(final PieURLGenerator generator) {
        this.legendLabelURLGenerator = generator;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public PiePlotState initialise(final Graphics2D g2, final Rectangle2D plotArea, final PiePlot plot, final Integer index, final PlotRenderingInfo info) {
        final PiePlotState state = new PiePlotState(info);
        state.setPassesRequired(2);
        state.setTotal(DatasetUtilities.calculatePieDatasetTotal(plot.getDataset()));
        state.setLatestAngle(plot.getStartAngle());
        return state;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final Point2D anchor, final PlotState parentState, final PlotRenderingInfo info) {
        final RectangleInsets insets = this.getInsets();
        insets.trim(area);
        if (info != null) {
            info.setPlotArea(area);
            info.setDataArea(area);
        }
        this.drawBackground(g2, area);
        this.drawOutline(g2, area);
        final Shape savedClip = g2.getClip();
        g2.clip(area);
        final Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(3, this.getForegroundAlpha()));
        if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
            this.drawPie(g2, area, info);
        }
        else {
            this.drawNoDataMessage(g2, area);
        }
        g2.setClip(savedClip);
        g2.setComposite(originalComposite);
        this.drawOutline(g2, area);
    }
    
    protected void drawPie(final Graphics2D g2, final Rectangle2D plotArea, final PlotRenderingInfo info) {
        final PiePlotState state = this.initialise(g2, plotArea, this, null, info);
        double labelWidth = 0.0;
        if (this.labelGenerator != null) {
            labelWidth = this.labelGap + this.maximumLabelWidth + this.labelLinkMargin;
        }
        final double gapHorizontal = plotArea.getWidth() * (this.interiorGap + labelWidth);
        final double gapVertical = plotArea.getHeight() * this.interiorGap;
        double linkX = plotArea.getX() + gapHorizontal / 2.0;
        double linkY = plotArea.getY() + gapVertical / 2.0;
        double linkW = plotArea.getWidth() - gapHorizontal;
        double linkH = plotArea.getHeight() - gapVertical;
        if (this.circular) {
            final double min = Math.min(linkW, linkH) / 2.0;
            linkX = (linkX + linkX + linkW) / 2.0 - min;
            linkY = (linkY + linkY + linkH) / 2.0 - min;
            linkW = 2.0 * min;
            linkH = 2.0 * min;
        }
        final Rectangle2D linkArea = new Rectangle2D.Double(linkX, linkY, linkW, linkH);
        state.setLinkArea(linkArea);
        final double hh = linkArea.getWidth() * this.labelLinkMargin;
        final double vv = linkArea.getHeight() * this.labelLinkMargin;
        final Rectangle2D explodeArea = new Rectangle2D.Double(linkX + hh / 2.0, linkY + vv / 2.0, linkW - hh, linkH - vv);
        state.setExplodedPieArea(explodeArea);
        final double maximumExplodePercent = this.getMaximumExplodePercent();
        final double percent = maximumExplodePercent / (1.0 + maximumExplodePercent);
        final double h1 = explodeArea.getWidth() * percent;
        final double v1 = explodeArea.getHeight() * percent;
        final Rectangle2D pieArea = new Rectangle2D.Double(explodeArea.getX() + h1 / 2.0, explodeArea.getY() + v1 / 2.0, explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);
        state.setPieArea(pieArea);
        state.setPieCenterX(pieArea.getCenterX());
        state.setPieCenterY(pieArea.getCenterY());
        state.setPieWRadius(pieArea.getWidth() / 2.0);
        state.setPieHRadius(pieArea.getHeight() / 2.0);
        if (this.dataset != null && this.dataset.getKeys().size() > 0) {
            final List keys = this.dataset.getKeys();
            final double totalValue = DatasetUtilities.calculatePieDatasetTotal(this.dataset);
            for (int passesRequired = state.getPassesRequired(), pass = 0; pass < passesRequired; ++pass) {
                double runningTotal = 0.0;
                for (int section = 0; section < keys.size(); ++section) {
                    final Number n = this.dataset.getValue(section);
                    if (n != null) {
                        final double value = n.doubleValue();
                        if (value > 0.0) {
                            runningTotal += value;
                            this.drawItem(g2, section, explodeArea, state, pass);
                        }
                    }
                }
            }
            this.drawLabels(g2, keys, totalValue, plotArea, linkArea, state);
        }
        else {
            this.drawNoDataMessage(g2, plotArea);
        }
    }
    
    protected void drawItem(final Graphics2D g2, final int section, final Rectangle2D dataArea, final PiePlotState state, final int currentPass) {
        final Number n = this.dataset.getValue(section);
        if (n == null) {
            return;
        }
        final double value = n.doubleValue();
        double angle1 = 0.0;
        double angle2 = 0.0;
        if (this.direction == Rotation.CLOCKWISE) {
            angle1 = state.getLatestAngle();
            angle2 = angle1 - value / state.getTotal() * 360.0;
        }
        else {
            if (this.direction != Rotation.ANTICLOCKWISE) {
                throw new IllegalStateException("Rotation type not recognised.");
            }
            angle1 = state.getLatestAngle();
            angle2 = angle1 + value / state.getTotal() * 360.0;
        }
        final double angle3 = angle2 - angle1;
        if (Math.abs(angle3) > this.getMinimumArcAngleToDraw()) {
            double ep = 0.0;
            final double mep = this.getMaximumExplodePercent();
            if (mep > 0.0) {
                ep = this.getExplodePercent(section) / mep;
            }
            final Rectangle2D arcBounds = this.getArcBounds(state.getPieArea(), state.getExplodedPieArea(), angle1, angle3, ep);
            final Arc2D.Double arc = new Arc2D.Double(arcBounds, angle1, angle3, 2);
            if (currentPass == 0) {
                if (this.shadowPaint != null) {
                    final Shape shadowArc = ShapeUtilities.createTranslatedShape(arc, (float)this.shadowXOffset, (float)this.shadowYOffset);
                    g2.setPaint(this.shadowPaint);
                    g2.fill(shadowArc);
                }
            }
            else if (currentPass == 1) {
                final Comparable key = this.getSectionKey(section);
                final Paint paint = this.lookupSectionPaint(key, true);
                g2.setPaint(paint);
                g2.fill(arc);
                final Paint outlinePaint = this.lookupSectionOutlinePaint(key);
                final Stroke outlineStroke = this.lookupSectionOutlineStroke(key);
                if (this.sectionOutlinesVisible) {
                    g2.setPaint(outlinePaint);
                    g2.setStroke(outlineStroke);
                    g2.draw(arc);
                }
                if (state.getInfo() != null) {
                    final EntityCollection entities = state.getEntityCollection();
                    if (entities != null) {
                        String tip = null;
                        if (this.toolTipGenerator != null) {
                            tip = this.toolTipGenerator.generateToolTip(this.dataset, key);
                        }
                        String url = null;
                        if (this.urlGenerator != null) {
                            url = this.urlGenerator.generateURL(this.dataset, key, this.pieIndex);
                        }
                        final PieSectionEntity entity = new PieSectionEntity(arc, this.dataset, this.pieIndex, section, key, tip, url);
                        entities.add(entity);
                    }
                }
            }
        }
        state.setLatestAngle(angle2);
    }
    
    protected void drawLabels(final Graphics2D g2, final List keys, final double totalValue, final Rectangle2D plotArea, final Rectangle2D linkArea, final PiePlotState state) {
        final Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(3, 1.0f));
        final DefaultKeyedValues leftKeys = new DefaultKeyedValues();
        final DefaultKeyedValues rightKeys = new DefaultKeyedValues();
        double runningTotal1 = 0.0;
        for (final Comparable key : keys) {
            boolean include = true;
            double v = 0.0;
            final Number n = this.dataset.getValue(key);
            if (n == null) {
                include = !this.ignoreNullValues;
            }
            else {
                v = n.doubleValue();
                include = (this.ignoreZeroValues ? (v > 0.0) : (v >= 0.0));
            }
            if (include) {
                runningTotal1 += v;
                final double mid = this.startAngle + this.direction.getFactor() * ((runningTotal1 - v / 2.0) * 360.0) / totalValue;
                if (Math.cos(Math.toRadians(mid)) < 0.0) {
                    leftKeys.addValue(key, new Double(mid));
                }
                else {
                    rightKeys.addValue(key, new Double(mid));
                }
            }
        }
        g2.setFont(this.getLabelFont());
        final float maxLabelWidth = (float)(this.getMaximumLabelWidth() * plotArea.getWidth());
        if (this.labelGenerator != null) {
            this.drawLeftLabels(leftKeys, g2, plotArea, linkArea, maxLabelWidth, state);
            this.drawRightLabels(rightKeys, g2, plotArea, linkArea, maxLabelWidth, state);
        }
        g2.setComposite(originalComposite);
    }
    
    protected void drawLeftLabels(final KeyedValues leftKeys, final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D linkArea, final float maxLabelWidth, final PiePlotState state) {
        this.labelDistributor.clear();
        final double lGap = plotArea.getWidth() * this.labelGap;
        final double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0;
        for (int i = 0; i < leftKeys.getItemCount(); ++i) {
            final String label = this.labelGenerator.generateSectionLabel(this.dataset, leftKeys.getKey(i));
            if (label != null) {
                final TextBlock block = TextUtilities.createTextBlock(label, this.labelFont, this.labelPaint, maxLabelWidth, new G2TextMeasurer(g2));
                final TextBox labelBox = new TextBox(block);
                labelBox.setBackgroundPaint(this.labelBackgroundPaint);
                labelBox.setOutlinePaint(this.labelOutlinePaint);
                labelBox.setOutlineStroke(this.labelOutlineStroke);
                labelBox.setShadowPaint(this.labelShadowPaint);
                final double theta = Math.toRadians(leftKeys.getValue(i).doubleValue());
                final double baseY = state.getPieCenterY() - Math.sin(theta) * verticalLinkRadius;
                final double hh = labelBox.getHeight(g2);
                this.labelDistributor.addPieLabelRecord(new PieLabelRecord(leftKeys.getKey(i), theta, baseY, labelBox, hh, lGap / 2.0 + lGap / 2.0 * -Math.cos(theta), 0.9 + this.getExplodePercent(leftKeys.getKey(i))));
            }
        }
        this.labelDistributor.distributeLabels(plotArea.getMinY(), plotArea.getHeight());
        for (int i = 0; i < this.labelDistributor.getItemCount(); ++i) {
            this.drawLeftLabel(g2, state, this.labelDistributor.getPieLabelRecord(i));
        }
    }
    
    protected void drawRightLabels(final KeyedValues keys, final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D linkArea, final float maxLabelWidth, final PiePlotState state) {
        this.labelDistributor.clear();
        final double lGap = plotArea.getWidth() * this.labelGap;
        final double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0;
        for (int i = 0; i < keys.getItemCount(); ++i) {
            final String label = this.labelGenerator.generateSectionLabel(this.dataset, keys.getKey(i));
            if (label != null) {
                final TextBlock block = TextUtilities.createTextBlock(label, this.labelFont, this.labelPaint, maxLabelWidth, new G2TextMeasurer(g2));
                final TextBox labelBox = new TextBox(block);
                labelBox.setBackgroundPaint(this.labelBackgroundPaint);
                labelBox.setOutlinePaint(this.labelOutlinePaint);
                labelBox.setOutlineStroke(this.labelOutlineStroke);
                labelBox.setShadowPaint(this.labelShadowPaint);
                final double theta = Math.toRadians(keys.getValue(i).doubleValue());
                final double baseY = state.getPieCenterY() - Math.sin(theta) * verticalLinkRadius;
                final double hh = labelBox.getHeight(g2);
                this.labelDistributor.addPieLabelRecord(new PieLabelRecord(keys.getKey(i), theta, baseY, labelBox, hh, lGap / 2.0 + lGap / 2.0 * Math.cos(theta), 0.9 + this.getExplodePercent(keys.getKey(i))));
            }
        }
        this.labelDistributor.distributeLabels(plotArea.getMinY(), plotArea.getHeight());
        for (int i = 0; i < this.labelDistributor.getItemCount(); ++i) {
            this.drawRightLabel(g2, state, this.labelDistributor.getPieLabelRecord(i));
        }
    }
    
    public LegendItemCollection getLegendItems() {
        final LegendItemCollection result = new LegendItemCollection();
        if (this.dataset == null) {
            return result;
        }
        final List keys = this.dataset.getKeys();
        int section = 0;
        final Shape shape = this.getLegendItemShape();
        for (final Comparable key : keys) {
            final Number n = this.dataset.getValue(key);
            boolean include = true;
            if (n == null) {
                include = !this.ignoreNullValues;
            }
            else {
                final double v = n.doubleValue();
                if (v == 0.0) {
                    include = !this.ignoreZeroValues;
                }
                else {
                    include = (v > 0.0);
                }
            }
            if (include) {
                final String label = this.legendLabelGenerator.generateSectionLabel(this.dataset, key);
                if (label != null) {
                    final String description = label;
                    String toolTipText = null;
                    if (this.legendLabelToolTipGenerator != null) {
                        toolTipText = this.legendLabelToolTipGenerator.generateSectionLabel(this.dataset, key);
                    }
                    String urlText = null;
                    if (this.legendLabelURLGenerator != null) {
                        urlText = this.legendLabelURLGenerator.generateURL(this.dataset, key, this.pieIndex);
                    }
                    final Paint paint = this.lookupSectionPaint(key, true);
                    final Paint outlinePaint = this.lookupSectionOutlinePaint(key);
                    final Stroke outlineStroke = this.lookupSectionOutlineStroke(key);
                    final LegendItem item = new LegendItem(label, description, toolTipText, urlText, true, shape, true, paint, true, outlinePaint, outlineStroke, false, new Line2D.Float(), new BasicStroke(), Color.black);
                    item.setDataset(this.getDataset());
                    result.add(item);
                }
                ++section;
            }
            else {
                ++section;
            }
        }
        return result;
    }
    
    public String getPlotType() {
        return PiePlot.localizationResources.getString("Pie_Plot");
    }
    
    protected Rectangle2D getArcBounds(final Rectangle2D unexploded, final Rectangle2D exploded, final double angle, final double extent, final double explodePercent) {
        if (explodePercent == 0.0) {
            return unexploded;
        }
        final Arc2D arc1 = new Arc2D.Double(unexploded, angle, extent / 2.0, 0);
        final Point2D point1 = arc1.getEndPoint();
        final Arc2D.Double arc2 = new Arc2D.Double(exploded, angle, extent / 2.0, 0);
        final Point2D point2 = arc2.getEndPoint();
        final double deltaX = (point1.getX() - point2.getX()) * explodePercent;
        final double deltaY = (point1.getY() - point2.getY()) * explodePercent;
        return new Rectangle2D.Double(unexploded.getX() - deltaX, unexploded.getY() - deltaY, unexploded.getWidth(), unexploded.getHeight());
    }
    
    protected void drawLeftLabel(final Graphics2D g2, final PiePlotState state, final PieLabelRecord record) {
        final double anchorX = state.getLinkArea().getMinX();
        final double targetX = anchorX - record.getGap();
        final double targetY = record.getAllocatedY();
        if (this.labelLinksVisible) {
            final double theta = record.getAngle();
            final double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius() * record.getLinkPercent();
            final double linkY = state.getPieCenterY() - Math.sin(theta) * state.getPieHRadius() * record.getLinkPercent();
            final double elbowX = state.getPieCenterX() + Math.cos(theta) * state.getLinkArea().getWidth() / 2.0;
            final double anchorY;
            final double elbowY = anchorY = state.getPieCenterY() - Math.sin(theta) * state.getLinkArea().getHeight() / 2.0;
            g2.setPaint(this.labelLinkPaint);
            g2.setStroke(this.labelLinkStroke);
            g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
            g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
            g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
        }
        final TextBox tb = record.getLabel();
        tb.draw(g2, (float)targetX, (float)targetY, RectangleAnchor.RIGHT);
    }
    
    protected void drawRightLabel(final Graphics2D g2, final PiePlotState state, final PieLabelRecord record) {
        final double anchorX = state.getLinkArea().getMaxX();
        final double targetX = anchorX + record.getGap();
        final double targetY = record.getAllocatedY();
        if (this.labelLinksVisible) {
            final double theta = record.getAngle();
            final double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius() * record.getLinkPercent();
            final double linkY = state.getPieCenterY() - Math.sin(theta) * state.getPieHRadius() * record.getLinkPercent();
            final double elbowX = state.getPieCenterX() + Math.cos(theta) * state.getLinkArea().getWidth() / 2.0;
            final double anchorY;
            final double elbowY = anchorY = state.getPieCenterY() - Math.sin(theta) * state.getLinkArea().getHeight() / 2.0;
            g2.setPaint(this.labelLinkPaint);
            g2.setStroke(this.labelLinkStroke);
            g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
            g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
            g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
        }
        final TextBox tb = record.getLabel();
        tb.draw(g2, (float)targetX, (float)targetY, RectangleAnchor.LEFT);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PiePlot)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final PiePlot that = (PiePlot)obj;
        return this.pieIndex == that.pieIndex && this.interiorGap == that.interiorGap && this.circular == that.circular && this.startAngle == that.startAngle && this.direction == that.direction && this.ignoreZeroValues == that.ignoreZeroValues && this.ignoreNullValues == that.ignoreNullValues && PaintUtilities.equal(this.sectionPaint, that.sectionPaint) && ObjectUtilities.equal(this.sectionPaintMap, that.sectionPaintMap) && PaintUtilities.equal(this.baseSectionPaint, that.baseSectionPaint) && this.sectionOutlinesVisible == that.sectionOutlinesVisible && PaintUtilities.equal(this.sectionOutlinePaint, that.sectionOutlinePaint) && ObjectUtilities.equal(this.sectionOutlinePaintMap, that.sectionOutlinePaintMap) && PaintUtilities.equal(this.baseSectionOutlinePaint, that.baseSectionOutlinePaint) && ObjectUtilities.equal(this.sectionOutlineStroke, that.sectionOutlineStroke) && ObjectUtilities.equal(this.sectionOutlineStrokeMap, that.sectionOutlineStrokeMap) && ObjectUtilities.equal(this.baseSectionOutlineStroke, that.baseSectionOutlineStroke) && PaintUtilities.equal(this.shadowPaint, that.shadowPaint) && this.shadowXOffset == that.shadowXOffset && this.shadowYOffset == that.shadowYOffset && ObjectUtilities.equal(this.explodePercentages, that.explodePercentages) && ObjectUtilities.equal(this.labelGenerator, that.labelGenerator) && ObjectUtilities.equal(this.labelFont, that.labelFont) && PaintUtilities.equal(this.labelPaint, that.labelPaint) && PaintUtilities.equal(this.labelBackgroundPaint, that.labelBackgroundPaint) && PaintUtilities.equal(this.labelOutlinePaint, that.labelOutlinePaint) && ObjectUtilities.equal(this.labelOutlineStroke, that.labelOutlineStroke) && PaintUtilities.equal(this.labelShadowPaint, that.labelShadowPaint) && this.maximumLabelWidth == that.maximumLabelWidth && this.labelGap == that.labelGap && this.labelLinkMargin == that.labelLinkMargin && this.labelLinksVisible == that.labelLinksVisible && PaintUtilities.equal(this.labelLinkPaint, that.labelLinkPaint) && ObjectUtilities.equal(this.labelLinkStroke, that.labelLinkStroke) && ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator) && ObjectUtilities.equal(this.urlGenerator, that.urlGenerator) && this.minimumArcAngleToDraw == that.minimumArcAngleToDraw && ShapeUtilities.equal(this.legendItemShape, that.legendItemShape) && ObjectUtilities.equal(this.legendLabelGenerator, that.legendLabelGenerator) && ObjectUtilities.equal(this.legendLabelToolTipGenerator, that.legendLabelToolTipGenerator) && ObjectUtilities.equal(this.legendLabelURLGenerator, that.legendLabelURLGenerator);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final PiePlot clone = (PiePlot)super.clone();
        if (clone.dataset != null) {
            clone.dataset.addChangeListener(clone);
        }
        if (this.urlGenerator instanceof PublicCloneable) {
            clone.urlGenerator = (PieURLGenerator)ObjectUtilities.clone(this.urlGenerator);
        }
        clone.legendItemShape = ShapeUtilities.clone(this.legendItemShape);
        if (this.legendLabelGenerator != null) {
            clone.legendLabelGenerator = (PieSectionLabelGenerator)ObjectUtilities.clone(this.legendLabelGenerator);
        }
        if (this.legendLabelToolTipGenerator != null) {
            clone.legendLabelToolTipGenerator = (PieSectionLabelGenerator)ObjectUtilities.clone(this.legendLabelToolTipGenerator);
        }
        if (this.legendLabelURLGenerator instanceof PublicCloneable) {
            clone.legendLabelURLGenerator = (PieURLGenerator)ObjectUtilities.clone(this.legendLabelURLGenerator);
        }
        return clone;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.sectionPaint, stream);
        SerialUtilities.writePaint(this.baseSectionPaint, stream);
        SerialUtilities.writePaint(this.sectionOutlinePaint, stream);
        SerialUtilities.writePaint(this.baseSectionOutlinePaint, stream);
        SerialUtilities.writeStroke(this.sectionOutlineStroke, stream);
        SerialUtilities.writeStroke(this.baseSectionOutlineStroke, stream);
        SerialUtilities.writePaint(this.shadowPaint, stream);
        SerialUtilities.writePaint(this.labelPaint, stream);
        SerialUtilities.writePaint(this.labelBackgroundPaint, stream);
        SerialUtilities.writePaint(this.labelOutlinePaint, stream);
        SerialUtilities.writeStroke(this.labelOutlineStroke, stream);
        SerialUtilities.writePaint(this.labelShadowPaint, stream);
        SerialUtilities.writePaint(this.labelLinkPaint, stream);
        SerialUtilities.writeStroke(this.labelLinkStroke, stream);
        SerialUtilities.writeShape(this.legendItemShape, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.sectionPaint = SerialUtilities.readPaint(stream);
        this.baseSectionPaint = SerialUtilities.readPaint(stream);
        this.sectionOutlinePaint = SerialUtilities.readPaint(stream);
        this.baseSectionOutlinePaint = SerialUtilities.readPaint(stream);
        this.sectionOutlineStroke = SerialUtilities.readStroke(stream);
        this.baseSectionOutlineStroke = SerialUtilities.readStroke(stream);
        this.shadowPaint = SerialUtilities.readPaint(stream);
        this.labelPaint = SerialUtilities.readPaint(stream);
        this.labelBackgroundPaint = SerialUtilities.readPaint(stream);
        this.labelOutlinePaint = SerialUtilities.readPaint(stream);
        this.labelOutlineStroke = SerialUtilities.readStroke(stream);
        this.labelShadowPaint = SerialUtilities.readPaint(stream);
        this.labelLinkPaint = SerialUtilities.readPaint(stream);
        this.labelLinkStroke = SerialUtilities.readStroke(stream);
        this.legendItemShape = SerialUtilities.readShape(stream);
    }
    
    public Paint getSectionPaint(final int section) {
        final Comparable key = this.getSectionKey(section);
        return this.getSectionPaint(key);
    }
    
    public void setSectionPaint(final int section, final Paint paint) {
        final Comparable key = this.getSectionKey(section);
        this.setSectionPaint(key, paint);
    }
    
    public Paint getSectionOutlinePaint(final int section) {
        final Comparable key = this.getSectionKey(section);
        return this.getSectionOutlinePaint(key);
    }
    
    public void setSectionOutlinePaint(final int section, final Paint paint) {
        final Comparable key = this.getSectionKey(section);
        this.setSectionOutlinePaint(key, paint);
    }
    
    public Stroke getSectionOutlineStroke(final int section) {
        final Comparable key = this.getSectionKey(section);
        return this.getSectionOutlineStroke(key);
    }
    
    public void setSectionOutlineStroke(final int section, final Stroke stroke) {
        final Comparable key = this.getSectionKey(section);
        this.setSectionOutlineStroke(key, stroke);
    }
    
    public double getExplodePercent(final int section) {
        final Comparable key = this.getSectionKey(section);
        return this.getExplodePercent(key);
    }
    
    public void setExplodePercent(final int section, final double percent) {
        final Comparable key = this.getSectionKey(section);
        this.setExplodePercent(key, percent);
    }
    
    static {
        DEFAULT_LABEL_FONT = new Font("SansSerif", 0, 10);
        DEFAULT_LABEL_PAINT = Color.black;
        DEFAULT_LABEL_BACKGROUND_PAINT = new Color(255, 255, 192);
        DEFAULT_LABEL_OUTLINE_PAINT = Color.black;
        DEFAULT_LABEL_OUTLINE_STROKE = new BasicStroke(0.5f);
        DEFAULT_LABEL_SHADOW_PAINT = Color.lightGray;
        PiePlot.localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
    }
}
