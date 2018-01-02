// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import java.awt.FontMetrics;
import org.jfree.text.TextUtilities;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.Insets;
import java.awt.geom.Ellipse2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.LegendItemCollection;
import java.util.List;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetChangeEvent;
import org.jfree.data.DatasetChangeListener;
import org.jfree.chart.event.PlotChangeEvent;
import java.util.ResourceBundle;
import java.text.NumberFormat;
import org.jfree.data.Range;
import org.jfree.data.ValueDataset;
import java.awt.Font;
import java.awt.Paint;
import java.io.Serializable;

public class MeterPlot extends Plot implements Serializable, Cloneable
{
    public static final int NORMAL_DATA_RANGE = 0;
    public static final int WARNING_DATA_RANGE = 1;
    public static final int CRITICAL_DATA_RANGE = 2;
    public static final int FULL_DATA_RANGE = 3;
    public static final String NORMAL_TEXT = "Normal";
    public static final String WARNING_TEXT = "Warning";
    public static final String CRITICAL_TEXT = "Critical";
    static final Paint DEFAULT_NORMAL_PAINT;
    static final Paint DEFAULT_WARNING_PAINT;
    static final Paint DEFAULT_CRITICAL_PAINT;
    static final Paint DEFAULT_DIAL_BACKGROUND_PAINT;
    static final Paint DEFAULT_NEEDLE_PAINT;
    static final Font DEFAULT_VALUE_FONT;
    static final Paint DEFAULT_VALUE_PAINT;
    public static final int DEFAULT_METER_ANGLE = 270;
    public static final float DEFAULT_BORDER_SIZE = 3.0f;
    public static final float DEFAULT_CIRCLE_SIZE = 10.0f;
    public static final Paint DEFAULT_BACKGROUND_PAINT;
    public static final Font DEFAULT_LABEL_FONT;
    public static final int NO_LABELS = 0;
    public static final int VALUE_LABELS = 1;
    private ValueDataset dataset;
    private String units;
    private Range range;
    private Range normalRange;
    private Range warningRange;
    private Range criticalRange;
    private transient Paint dialOutlinePaint;
    private transient Paint normalPaint;
    private transient Paint warningPaint;
    private transient Paint criticalPaint;
    private DialShape shape;
    private transient Paint dialBackgroundPaint;
    private transient Paint needlePaint;
    private Font valueFont;
    private transient Paint valuePaint;
    private int tickLabelType;
    private Font tickLabelFont;
    private NumberFormat tickLabelFormat;
    private boolean drawBorder;
    private int meterCalcAngle;
    private double meterRange;
    protected static ResourceBundle localizationResources;
    private int meterAngle;
    private double minMeterValue;
    public static final int DIALTYPE_PIE = 0;
    public static final int DIALTYPE_CIRCLE = 1;
    public static final int DIALTYPE_CHORD = 2;
    
    public MeterPlot() {
        this(null);
    }
    
    public MeterPlot(final ValueDataset dataset) {
        this.normalPaint = MeterPlot.DEFAULT_NORMAL_PAINT;
        this.warningPaint = MeterPlot.DEFAULT_WARNING_PAINT;
        this.criticalPaint = MeterPlot.DEFAULT_CRITICAL_PAINT;
        this.shape = DialShape.CIRCLE;
        this.meterCalcAngle = -1;
        this.meterRange = -1.0;
        this.meterAngle = 270;
        this.minMeterValue = 0.0;
        this.units = "Units";
        this.range = new Range(0.0, 100.0);
        this.normalRange = new Range(0.0, 60.0);
        this.warningRange = new Range(60.0, 90.0);
        this.criticalRange = new Range(90.0, 100.0);
        this.tickLabelType = 1;
        this.tickLabelFont = MeterPlot.DEFAULT_LABEL_FONT;
        this.tickLabelFormat = NumberFormat.getInstance();
        this.dialBackgroundPaint = MeterPlot.DEFAULT_DIAL_BACKGROUND_PAINT;
        this.needlePaint = MeterPlot.DEFAULT_NEEDLE_PAINT;
        this.valueFont = MeterPlot.DEFAULT_VALUE_FONT;
        this.valuePaint = MeterPlot.DEFAULT_VALUE_PAINT;
        this.setDataset(dataset);
    }
    
    public String getUnits() {
        return this.units;
    }
    
    public void setUnits(final String units) {
        this.units = units;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Range getRange() {
        return this.range;
    }
    
    public void setRange(final Range range) {
        this.range = range;
    }
    
    public Range getNormalRange() {
        return this.normalRange;
    }
    
    public void setNormalRange(final Range range) {
        this.normalRange = range;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Range getWarningRange() {
        return this.warningRange;
    }
    
    public void setWarningRange(final Range range) {
        this.warningRange = range;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Range getCriticalRange() {
        return this.criticalRange;
    }
    
    public void setCriticalRange(final Range range) {
        this.criticalRange = range;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public DialShape getDialShape() {
        return this.shape;
    }
    
    public void setDialShape(final DialShape shape) {
        this.shape = shape;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getDialBackgroundPaint() {
        return this.dialBackgroundPaint;
    }
    
    public void setDialBackgroundPaint(final Paint paint) {
        this.dialBackgroundPaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getNeedlePaint() {
        return this.needlePaint;
    }
    
    public void setNeedlePaint(final Paint paint) {
        this.needlePaint = ((paint == null) ? MeterPlot.DEFAULT_NEEDLE_PAINT : paint);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Font getValueFont() {
        return this.valueFont;
    }
    
    public void setValueFont(final Font font) {
        this.valueFont = ((font == null) ? MeterPlot.DEFAULT_VALUE_FONT : font);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getValuePaint() {
        return this.valuePaint;
    }
    
    public void setValuePaint(final Paint paint) {
        this.valuePaint = ((paint == null) ? MeterPlot.DEFAULT_VALUE_PAINT : paint);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getNormalPaint() {
        return this.normalPaint;
    }
    
    public void setNormalPaint(final Paint paint) {
        this.normalPaint = ((paint == null) ? MeterPlot.DEFAULT_NORMAL_PAINT : paint);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getWarningPaint() {
        return this.warningPaint;
    }
    
    public void setWarningPaint(final Paint paint) {
        this.warningPaint = ((paint == null) ? MeterPlot.DEFAULT_WARNING_PAINT : paint);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getCriticalPaint() {
        return this.criticalPaint;
    }
    
    public void setCriticalPaint(final Paint paint) {
        this.criticalPaint = ((paint == null) ? MeterPlot.DEFAULT_CRITICAL_PAINT : paint);
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public int getTickLabelType() {
        return this.tickLabelType;
    }
    
    public void setTickLabelType(final int type) {
        if (type != 0 && type != 1) {
            throw new IllegalArgumentException("MeterPlot.setLabelType(int): unrecognised type.");
        }
        if (this.tickLabelType != type) {
            this.tickLabelType = type;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }
    
    public void setTickLabelFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (!this.tickLabelFont.equals(font)) {
            this.tickLabelFont = font;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public NumberFormat getTickLabelFormat() {
        return this.tickLabelFormat;
    }
    
    public void setTickLabelFormat(final NumberFormat format) {
        if (format == null) {
            throw new IllegalArgumentException("Null 'format' argument.");
        }
        this.tickLabelFormat = format;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean getDrawBorder() {
        return this.drawBorder;
    }
    
    public void setDrawBorder(final boolean draw) {
        this.drawBorder = draw;
    }
    
    public int getMeterAngle() {
        return this.meterAngle;
    }
    
    public void setMeterAngle(final int angle) {
        this.meterAngle = angle;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getDialOutlinePaint() {
        return this.dialOutlinePaint;
    }
    
    public void setDialOutlinePaint(final Paint paint) {
        this.dialOutlinePaint = paint;
    }
    
    public ValueDataset getDataset() {
        return this.dataset;
    }
    
    public void setDataset(final ValueDataset dataset) {
        final ValueDataset existing = this.dataset;
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
    
    public List getLegendItemLabels() {
        return null;
    }
    
    public LegendItemCollection getLegendItems() {
        return null;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D plotArea, final PlotState parentState, final PlotRenderingInfo info) {
        if (info != null) {
            info.setPlotArea(plotArea);
        }
        final Insets insets = this.getInsets();
        if (insets != null) {
            plotArea.setRect(plotArea.getX() + insets.left, plotArea.getY() + insets.top, plotArea.getWidth() - insets.left - insets.right, plotArea.getHeight() - insets.top - insets.bottom);
        }
        plotArea.setRect(plotArea.getX() + 4.0, plotArea.getY() + 4.0, plotArea.getWidth() - 8.0, plotArea.getHeight() - 8.0);
        if (this.drawBorder) {
            this.drawBackground(g2, plotArea);
        }
        final double gapHorizontal = 6.0;
        final double gapVertical = 6.0;
        double meterX = plotArea.getX() + gapHorizontal / 2.0;
        double meterY = plotArea.getY() + gapVertical / 2.0;
        double meterW = plotArea.getWidth() - gapHorizontal;
        double meterH = plotArea.getHeight() - gapVertical + ((this.meterAngle <= 180 && this.shape != DialShape.CIRCLE) ? (plotArea.getHeight() / 1.25) : 0.0);
        final double min = Math.min(meterW, meterH) / 2.0;
        meterX = (meterX + meterX + meterW) / 2.0 - min;
        meterY = (meterY + meterY + meterH) / 2.0 - min;
        meterW = 2.0 * min;
        meterH = 2.0 * min;
        final Rectangle2D meterArea = new Rectangle2D.Double(meterX, meterY, meterW, meterH);
        final Rectangle2D.Double originalArea = new Rectangle2D.Double(meterArea.getX() - 4.0, meterArea.getY() - 4.0, meterArea.getWidth() + 8.0, meterArea.getHeight() + 8.0);
        final double meterMiddleX = meterArea.getCenterX();
        final double meterMiddleY = meterArea.getCenterY();
        final ValueDataset data = this.getDataset();
        if (data != null) {
            final double dataMin = this.range.getLowerBound();
            final double dataMax = this.range.getUpperBound();
            this.minMeterValue = dataMin;
            this.meterCalcAngle = 180 + (this.meterAngle - 180) / 2;
            this.meterRange = dataMax - dataMin;
            final Shape savedClip = g2.getClip();
            g2.clip(originalArea);
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, this.getForegroundAlpha()));
            if (this.dialBackgroundPaint != null) {
                this.drawArc(g2, originalArea, dataMin, dataMax, this.dialBackgroundPaint, 1);
            }
            this.drawTicks(g2, meterArea, dataMin, dataMax);
            this.drawArcFor(g2, meterArea, data, 3);
            if (this.normalRange != null) {
                this.drawArcFor(g2, meterArea, data, 0);
            }
            if (this.warningRange != null) {
                this.drawArcFor(g2, meterArea, data, 1);
            }
            if (this.criticalRange != null) {
                this.drawArcFor(g2, meterArea, data, 2);
            }
            if (data.getValue() != null) {
                final double dataVal = data.getValue().doubleValue();
                this.drawTick(g2, meterArea, dataVal, true, this.valuePaint, true, this.getUnits());
                g2.setPaint(this.needlePaint);
                g2.setStroke(new BasicStroke(2.0f));
                final double radius = meterArea.getWidth() / 2.0 + 3.0 + 15.0;
                final double valueAngle = this.calculateAngle(dataVal);
                final double valueP1 = meterMiddleX + radius * Math.cos(3.141592653589793 * (valueAngle / 180.0));
                final double valueP2 = meterMiddleY - radius * Math.sin(3.141592653589793 * (valueAngle / 180.0));
                final Polygon arrow = new Polygon();
                if ((valueAngle > 135.0 && valueAngle < 225.0) || (valueAngle < 45.0 && valueAngle > -45.0)) {
                    final double valueP3 = meterMiddleY - 2.5;
                    final double valueP4 = meterMiddleY + 2.5;
                    arrow.addPoint((int)meterMiddleX, (int)valueP3);
                    arrow.addPoint((int)meterMiddleX, (int)valueP4);
                }
                else {
                    arrow.addPoint((int)(meterMiddleX - 2.5), (int)meterMiddleY);
                    arrow.addPoint((int)(meterMiddleX + 2.5), (int)meterMiddleY);
                }
                arrow.addPoint((int)valueP1, (int)valueP2);
                final Ellipse2D circle = new Ellipse2D.Double(meterMiddleX - 5.0, meterMiddleY - 5.0, 10.0, 10.0);
                g2.fill(arrow);
                g2.fill(circle);
            }
            g2.clip(savedClip);
            g2.setComposite(originalComposite);
        }
        if (this.drawBorder) {
            this.drawOutline(g2, plotArea);
        }
    }
    
    protected void drawArcFor(final Graphics2D g2, final Rectangle2D meterArea, final ValueDataset data, final int type) {
        double minValue = 0.0;
        double maxValue = 0.0;
        Paint paint = null;
        switch (type) {
            case 0: {
                minValue = this.normalRange.getLowerBound();
                maxValue = this.normalRange.getUpperBound();
                paint = this.getNormalPaint();
                break;
            }
            case 1: {
                minValue = this.warningRange.getLowerBound();
                maxValue = this.warningRange.getUpperBound();
                paint = this.getWarningPaint();
                break;
            }
            case 2: {
                minValue = this.criticalRange.getLowerBound();
                maxValue = this.criticalRange.getUpperBound();
                paint = this.getCriticalPaint();
                break;
            }
            case 3: {
                minValue = this.range.getLowerBound();
                maxValue = this.range.getUpperBound();
                paint = MeterPlot.DEFAULT_BACKGROUND_PAINT;
                break;
            }
            default: {
                return;
            }
        }
        this.drawArc(g2, meterArea, minValue, maxValue, paint);
        this.drawTick(g2, meterArea, minValue, true, paint);
        this.drawTick(g2, meterArea, maxValue, true, paint);
    }
    
    protected void drawArc(final Graphics2D g2, final Rectangle2D area, final double minValue, final double maxValue, final Paint paint) {
        this.drawArc(g2, area, minValue, maxValue, paint, 0);
    }
    
    protected void drawArc(final Graphics2D g2, final Rectangle2D area, final double minValue, final double maxValue, final Paint paint, final int outlineType) {
        final double startAngle = this.calculateAngle(maxValue);
        final double endAngle = this.calculateAngle(minValue);
        double extent = endAngle - startAngle;
        final double x = area.getX();
        final double y = area.getY();
        final double w = area.getWidth();
        final double h = area.getHeight();
        g2.setPaint(paint);
        if (outlineType > 0) {
            g2.setStroke(new BasicStroke(10.0f));
        }
        else {
            g2.setStroke(new BasicStroke(3.0f));
        }
        int joinType = 0;
        if (outlineType > 0) {
            if (this.shape == DialShape.PIE) {
                joinType = 2;
            }
            else if (this.shape == DialShape.CHORD) {
                if (this.meterAngle > 180) {
                    joinType = 1;
                }
                else {
                    joinType = 2;
                }
            }
            else {
                if (this.shape != DialShape.CIRCLE) {
                    throw new IllegalStateException("MeterPlot.drawArc(...): dialType not recognised.");
                }
                joinType = 2;
                extent = 360.0;
            }
        }
        final Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, extent, joinType);
        if (outlineType > 0) {
            g2.fill(arc);
        }
        else {
            g2.draw(arc);
        }
    }
    
    double calculateAngle(double value) {
        value -= this.minMeterValue;
        final double ret = this.meterCalcAngle - value / this.meterRange * this.meterAngle;
        return ret;
    }
    
    protected void drawTicks(final Graphics2D g2, final Rectangle2D meterArea, final double minValue, final double maxValue) {
        final int numberOfTicks = 20;
        for (double diff = (maxValue - minValue) / numberOfTicks, i = minValue; i <= maxValue; i += diff) {
            this.drawTick(g2, meterArea, i);
        }
    }
    
    protected void drawTick(final Graphics2D g2, final Rectangle2D meterArea, final double value) {
        this.drawTick(g2, meterArea, value, false, null, false, null);
    }
    
    protected void drawTick(final Graphics2D g2, final Rectangle2D meterArea, final double value, final boolean label, final Paint color) {
        this.drawTick(g2, meterArea, value, label, color, false, null);
    }
    
    protected void drawTick(final Graphics2D g2, final Rectangle2D meterArea, final double value, final boolean label, Paint labelPaint, final boolean curValue, final String units) {
        double valueAngle = this.calculateAngle(value);
        final double meterMiddleX = meterArea.getCenterX();
        final double meterMiddleY = meterArea.getCenterY();
        if (labelPaint == null) {
            labelPaint = Color.white;
        }
        g2.setPaint(labelPaint);
        g2.setStroke(new BasicStroke(2.0f));
        double valueP2X = 0.0;
        double valueP2Y = 0.0;
        if (!curValue) {
            final double radius = meterArea.getWidth() / 2.0 + 3.0;
            final double radius2 = radius - 15.0;
            final double valueP1X = meterMiddleX + radius * Math.cos(3.141592653589793 * (valueAngle / 180.0));
            final double valueP1Y = meterMiddleY - radius * Math.sin(3.141592653589793 * (valueAngle / 180.0));
            valueP2X = meterMiddleX + radius2 * Math.cos(3.141592653589793 * (valueAngle / 180.0));
            valueP2Y = meterMiddleY - radius2 * Math.sin(3.141592653589793 * (valueAngle / 180.0));
            final Line2D.Double line = new Line2D.Double(valueP1X, valueP1Y, valueP2X, valueP2Y);
            g2.draw(line);
        }
        else {
            valueP2X = meterMiddleX;
            valueP2Y = meterMiddleY;
            valueAngle = 90.0;
        }
        if (this.tickLabelType == 1 && label) {
            String tickLabel = this.tickLabelFormat.format(value);
            if (curValue && units != null) {
                tickLabel = tickLabel + " " + units;
            }
            if (curValue) {
                g2.setFont(this.getValueFont());
            }
            else if (this.tickLabelFont != null) {
                g2.setFont(this.tickLabelFont);
            }
            final FontMetrics fm = g2.getFontMetrics();
            final Rectangle2D tickLabelBounds = TextUtilities.getTextBounds(tickLabel, g2, fm);
            double x = valueP2X;
            double y = valueP2Y;
            if (curValue) {
                y += 10.0;
            }
            if (valueAngle == 90.0 || valueAngle == 270.0) {
                x -= tickLabelBounds.getWidth() / 2.0;
            }
            else if (valueAngle < 90.0 || valueAngle > 270.0) {
                x -= tickLabelBounds.getWidth();
            }
            if ((valueAngle > 135.0 && valueAngle < 225.0) || valueAngle > 315.0 || valueAngle < 45.0) {
                y -= tickLabelBounds.getHeight() / 2.0;
            }
            else {
                y += tickLabelBounds.getHeight() / 2.0;
            }
            g2.drawString(tickLabel, (float)x, (float)y);
        }
    }
    
    public String getPlotType() {
        return MeterPlot.localizationResources.getString("Meter_Plot");
    }
    
    public void zoom(final double percent) {
    }
    
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof MeterPlot && super.equals(object)) {
            final MeterPlot p = (MeterPlot)object;
            return ObjectUtils.equal(this.units, p.units) && ObjectUtils.equal(this.range, p.range) && ObjectUtils.equal(this.normalRange, p.normalRange) && ObjectUtils.equal(this.warningRange, p.warningRange) && ObjectUtils.equal(this.criticalRange, p.criticalRange) && ObjectUtils.equal(this.dialOutlinePaint, p.dialOutlinePaint) && ObjectUtils.equal(this.normalPaint, p.normalPaint) && ObjectUtils.equal(this.warningPaint, p.warningPaint) && ObjectUtils.equal(this.criticalPaint, p.criticalPaint) && this.shape == p.shape && ObjectUtils.equal(this.dialBackgroundPaint, p.dialBackgroundPaint) && ObjectUtils.equal(this.needlePaint, p.needlePaint) && ObjectUtils.equal(this.valueFont, p.valueFont) && ObjectUtils.equal(this.valuePaint, p.valuePaint) && this.tickLabelType == p.tickLabelType && ObjectUtils.equal(this.tickLabelFont, p.tickLabelFont) && ObjectUtils.equal(this.tickLabelFormat, p.tickLabelFormat) && this.drawBorder == p.drawBorder && this.meterAngle == p.meterAngle;
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.criticalPaint, stream);
        SerialUtilities.writePaint(this.dialBackgroundPaint, stream);
        SerialUtilities.writePaint(this.needlePaint, stream);
        SerialUtilities.writePaint(this.normalPaint, stream);
        SerialUtilities.writePaint(this.valuePaint, stream);
        SerialUtilities.writePaint(this.warningPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.criticalPaint = SerialUtilities.readPaint(stream);
        this.dialBackgroundPaint = SerialUtilities.readPaint(stream);
        this.needlePaint = SerialUtilities.readPaint(stream);
        this.normalPaint = SerialUtilities.readPaint(stream);
        this.valuePaint = SerialUtilities.readPaint(stream);
        this.warningPaint = SerialUtilities.readPaint(stream);
        if (this.dataset != null) {
            this.dataset.addChangeListener(this);
        }
    }
    
    public int getDialType() {
        if (this.shape == DialShape.CIRCLE) {
            return 1;
        }
        if (this.shape == DialShape.CHORD) {
            return 2;
        }
        if (this.shape == DialShape.PIE) {
            return 0;
        }
        throw new IllegalStateException("MeterPlot.getDialType: unrecognised dial type.");
    }
    
    public void setDialType(final int type) {
        switch (type) {
            case 1: {
                this.setDialShape(DialShape.CIRCLE);
                break;
            }
            case 2: {
                this.setDialShape(DialShape.CHORD);
                break;
            }
            case 0: {
                this.setDialShape(DialShape.PIE);
                break;
            }
            default: {
                throw new IllegalArgumentException("MeterPlot.setDialType: unrecognised type.");
            }
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        final MeterPlot clone = (MeterPlot)super.clone();
        if (clone.dataset != null) {
            clone.dataset.addChangeListener(clone);
        }
        return clone;
    }
    
    static {
        DEFAULT_NORMAL_PAINT = Color.green;
        DEFAULT_WARNING_PAINT = Color.yellow;
        DEFAULT_CRITICAL_PAINT = Color.red;
        DEFAULT_DIAL_BACKGROUND_PAINT = Color.black;
        DEFAULT_NEEDLE_PAINT = Color.green;
        DEFAULT_VALUE_FONT = new Font("SansSerif", 1, 12);
        DEFAULT_VALUE_PAINT = Color.yellow;
        DEFAULT_BACKGROUND_PAINT = Color.lightGray;
        DEFAULT_LABEL_FONT = new Font("SansSerif", 1, 10);
        MeterPlot.localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
    }
}
