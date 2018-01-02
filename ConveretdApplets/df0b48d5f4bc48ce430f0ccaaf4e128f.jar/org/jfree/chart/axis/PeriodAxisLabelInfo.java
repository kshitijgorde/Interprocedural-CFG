// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import org.jfree.data.time.RegularTimePeriod;
import java.util.TimeZone;
import java.util.Date;
import java.text.DateFormat;
import java.awt.Stroke;
import java.awt.Paint;
import java.awt.Font;
import org.jfree.ui.RectangleInsets;
import java.io.Serializable;

public class PeriodAxisLabelInfo implements Cloneable, Serializable
{
    private static final long serialVersionUID = 5710451740920277357L;
    public static final RectangleInsets DEFAULT_INSETS;
    public static final Font DEFAULT_FONT;
    public static final Paint DEFAULT_LABEL_PAINT;
    public static final Stroke DEFAULT_DIVIDER_STROKE;
    public static final Paint DEFAULT_DIVIDER_PAINT;
    private Class periodClass;
    private RectangleInsets padding;
    private DateFormat dateFormat;
    private Font labelFont;
    private transient Paint labelPaint;
    private boolean drawDividers;
    private transient Stroke dividerStroke;
    private transient Paint dividerPaint;
    static /* synthetic */ Class class$java$util$Date;
    static /* synthetic */ Class class$java$util$TimeZone;
    
    public PeriodAxisLabelInfo(final Class periodClass, final DateFormat dateFormat) {
        this(periodClass, dateFormat, PeriodAxisLabelInfo.DEFAULT_INSETS, PeriodAxisLabelInfo.DEFAULT_FONT, PeriodAxisLabelInfo.DEFAULT_LABEL_PAINT, true, PeriodAxisLabelInfo.DEFAULT_DIVIDER_STROKE, PeriodAxisLabelInfo.DEFAULT_DIVIDER_PAINT);
    }
    
    public PeriodAxisLabelInfo(final Class periodClass, final DateFormat dateFormat, final RectangleInsets padding, final Font labelFont, final Paint labelPaint, final boolean drawDividers, final Stroke dividerStroke, final Paint dividerPaint) {
        if (periodClass == null) {
            throw new IllegalArgumentException("Null 'periodClass' argument.");
        }
        if (dateFormat == null) {
            throw new IllegalArgumentException("Null 'dateFormat' argument.");
        }
        if (padding == null) {
            throw new IllegalArgumentException("Null 'padding' argument.");
        }
        if (labelFont == null) {
            throw new IllegalArgumentException("Null 'labelFont' argument.");
        }
        if (labelPaint == null) {
            throw new IllegalArgumentException("Null 'labelPaint' argument.");
        }
        if (dividerStroke == null) {
            throw new IllegalArgumentException("Null 'dividerStroke' argument.");
        }
        if (dividerPaint == null) {
            throw new IllegalArgumentException("Null 'dividerPaint' argument.");
        }
        this.periodClass = periodClass;
        this.dateFormat = dateFormat;
        this.padding = padding;
        this.labelFont = labelFont;
        this.labelPaint = labelPaint;
        this.drawDividers = drawDividers;
        this.dividerStroke = dividerStroke;
        this.dividerPaint = dividerPaint;
    }
    
    public Class getPeriodClass() {
        return this.periodClass;
    }
    
    public DateFormat getDateFormat() {
        return this.dateFormat;
    }
    
    public RectangleInsets getPadding() {
        return this.padding;
    }
    
    public Font getLabelFont() {
        return this.labelFont;
    }
    
    public Paint getLabelPaint() {
        return this.labelPaint;
    }
    
    public boolean getDrawDividers() {
        return this.drawDividers;
    }
    
    public Stroke getDividerStroke() {
        return this.dividerStroke;
    }
    
    public Paint getDividerPaint() {
        return this.dividerPaint;
    }
    
    public RegularTimePeriod createInstance(final Date millisecond, final TimeZone zone) {
        RegularTimePeriod result = null;
        try {
            final Constructor c = this.periodClass.getDeclaredConstructor((PeriodAxisLabelInfo.class$java$util$Date == null) ? (PeriodAxisLabelInfo.class$java$util$Date = class$("java.util.Date")) : PeriodAxisLabelInfo.class$java$util$Date, (PeriodAxisLabelInfo.class$java$util$TimeZone == null) ? (PeriodAxisLabelInfo.class$java$util$TimeZone = class$("java.util.TimeZone")) : PeriodAxisLabelInfo.class$java$util$TimeZone);
            result = c.newInstance(millisecond, zone);
        }
        catch (Exception ex) {}
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PeriodAxisLabelInfo) {
            final PeriodAxisLabelInfo info = (PeriodAxisLabelInfo)obj;
            return info.periodClass.equals(this.periodClass) && info.dateFormat.equals(this.dateFormat) && info.padding.equals(this.padding) && info.labelFont.equals(this.labelFont) && info.labelPaint.equals(this.labelPaint) && info.drawDividers == this.drawDividers && info.dividerStroke.equals(this.dividerStroke) && info.dividerPaint.equals(this.dividerPaint);
        }
        return false;
    }
    
    public int hashCode() {
        int result = 41;
        result = 37 * this.periodClass.hashCode();
        result = 37 * this.dateFormat.hashCode();
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Object clone = super.clone();
        return clone;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.labelPaint, stream);
        SerialUtilities.writeStroke(this.dividerStroke, stream);
        SerialUtilities.writePaint(this.dividerPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.labelPaint = SerialUtilities.readPaint(stream);
        this.dividerStroke = SerialUtilities.readStroke(stream);
        this.dividerPaint = SerialUtilities.readPaint(stream);
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
        DEFAULT_INSETS = new RectangleInsets(2.0, 2.0, 2.0, 2.0);
        DEFAULT_FONT = new Font("SansSerif", 0, 10);
        DEFAULT_LABEL_PAINT = Color.black;
        DEFAULT_DIVIDER_STROKE = new BasicStroke(0.5f);
        DEFAULT_DIVIDER_PAINT = Color.gray;
    }
}
