// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import java.awt.Toolkit;
import risco_table.graphics.StringFormatter;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

public class ColumnDefinition
{
    public static final int WIDTH_FIXED_N_PIXELS = 0;
    public static final int WIDTH_FIXED_N_CHARS = 1;
    public static final int WIDTH_AUTO_BY_TITLE = 2;
    public static final int WIDTH_AUTO = 3;
    private int widthInChars;
    private int widthInPixels;
    private int widthAdjustment;
    private Color titleBackground;
    private Color titleForeground;
    private Color dataBackground;
    private Color dataBackgroundEven;
    private Color dataForeground;
    private Color dataForegroundEven;
    private String title;
    private Font titleFont;
    private Font dataFont;
    private FontMetrics titleFontMetrics;
    private FontMetrics dataFontMetrics;
    private boolean wrapDataCells;
    private StringFormatter titleFormatter;
    private StringFormatter dataFormatter;
    public static final int DATA_TYPE_STRING = 1;
    public static final int DATA_TYPE_INT = 2;
    public static final int DATA_TYPE_DATE = 3;
    public static final int DATA_TYPE_DECIMAL = 4;
    public static final int DATA_TYPE_MONEY = 5;
    public static final int DATA_TYPE_ADDRESS = 6;
    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
    private int m_iColumnType;
    private String m_sDateFormat;
    
    public int getColumnType() {
        return this.m_iColumnType;
    }
    
    public void setColumnType(final int iType) {
        if (iType == 1 || iType == 2 || iType == 3 || iType == 4 || iType == 5 || iType == 6) {
            this.m_iColumnType = iType;
        }
    }
    
    public String getDateFormat() {
        return this.m_sDateFormat;
    }
    
    public void setDateFormat(final String sDateFormat) {
        this.m_sDateFormat = sDateFormat;
    }
    
    public ColumnDefinition(final String title) {
        this(title, 2, null, null);
    }
    
    public ColumnDefinition(final String title, final int widthAdjustment, final StringFormatter titleFormatter, final StringFormatter dataFormatter) {
        this.widthInChars = 10;
        this.widthInPixels = 60;
        this.widthAdjustment = 2;
        this.dataBackgroundEven = null;
        this.dataForegroundEven = null;
        this.m_iColumnType = 1;
        this.m_sDateFormat = "MM/dd/yyyy";
        this.setTitleFormatter(titleFormatter);
        this.setDataFormatter(dataFormatter);
        this.title = title;
        this.widthAdjustment = widthAdjustment;
    }
    
    public ColumnDefinition(final String title, final StringFormatter titleFormatter, final StringFormatter dataFormatter) {
        this(title, 2, titleFormatter, dataFormatter);
    }
    
    public Color getDataBackground() {
        return this.dataBackground;
    }
    
    public Color getDataBackgroundEven() {
        return this.dataBackgroundEven;
    }
    
    public Font getDataFont() {
        return this.dataFont;
    }
    
    public FontMetrics getDataFontMetrics() {
        return this.dataFontMetrics;
    }
    
    public Color getDataForeground() {
        return this.dataForeground;
    }
    
    public Color getDataForegroundEven() {
        return this.dataForegroundEven;
    }
    
    public StringFormatter getDataFormatter() {
        return this.dataFormatter;
    }
    
    public int getPreferredDataLineHeight(final FontMetrics defaultFontMetrics) {
        final FontMetrics m = (this.dataFontMetrics == null) ? defaultFontMetrics : this.dataFontMetrics;
        return m.getHeight();
    }
    
    public int getPreferredTitleHeight(final FontMetrics defaultFontMetrics, final StringFormatter defaultFormatter) {
        return this.getPreferredTitleHeight(defaultFontMetrics, defaultFormatter, -1);
    }
    
    public int getPreferredTitleHeight(final FontMetrics defaultFontMetrics, final StringFormatter defaultFormatter, final int wrapTo) {
        final StringFormatter f = (this.titleFormatter == null) ? defaultFormatter : this.titleFormatter;
        final FontMetrics m = (this.titleFontMetrics == null) ? defaultFontMetrics : this.titleFontMetrics;
        final int width = (wrapTo > 0) ? wrapTo : 32767;
        f.measureString(m, this.title, 0, 0, width, 32767);
        return f.getPreferredHeight();
    }
    
    public int getPreferredWidth(final FontMetrics defaultFontMetrics, final StringFormatter defaultFormatter) {
        switch (this.widthAdjustment) {
            case 0: {
                return this.widthInPixels;
            }
            case 1: {
                final FontMetrics m = (this.dataFontMetrics == null) ? defaultFontMetrics : this.dataFontMetrics;
                return this.widthInChars * m.charWidth('0');
            }
            default: {
                final FontMetrics m = (this.titleFontMetrics == null) ? defaultFontMetrics : this.titleFontMetrics;
                final StringFormatter f = (this.titleFormatter == null) ? defaultFormatter : this.titleFormatter;
                f.measureString(m, this.title, 0, 0, 32767, 32767);
                return f.getPreferredWidth();
            }
        }
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Color getTitleBackground() {
        return this.titleBackground;
    }
    
    public Font getTitleFont() {
        return this.titleFont;
    }
    
    public FontMetrics getTitleFontMetrics() {
        return this.titleFontMetrics;
    }
    
    public Color getTitleForeground() {
        return this.titleForeground;
    }
    
    public StringFormatter getTitleFormatter() {
        return this.titleFormatter;
    }
    
    public int getWidthAdjustment() {
        return this.widthAdjustment;
    }
    
    public int getWidthInChars() throws IllegalArgumentException {
        if (this.widthAdjustment != 1) {
            throw new IllegalArgumentException();
        }
        return this.widthInChars;
    }
    
    public int getWidthInPixels() throws IllegalArgumentException {
        if (this.widthAdjustment != 0) {
            throw new IllegalArgumentException();
        }
        return this.widthInPixels;
    }
    
    boolean getWrapDataCells() {
        return this.wrapDataCells;
    }
    
    public void setDataBackground(final Color background) {
        this.dataBackground = background;
    }
    
    public void setDataBackground(final Color background, final Color backgroundEven) {
        this.dataBackground = background;
        this.dataBackgroundEven = backgroundEven;
    }
    
    public void setDataFont(final Font f) {
        this.dataFont = f;
        this.dataFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(f);
    }
    
    public void setDataForeground(final Color foreground) {
        this.dataForeground = foreground;
    }
    
    public void setDataForeground(final Color foreground, final Color foregroundEven) {
        this.dataForeground = foreground;
        this.dataForegroundEven = foregroundEven;
    }
    
    public void setDataFormatter(final StringFormatter formatter) {
        this.dataFormatter = formatter;
        if (this.dataFormatter != null && this.dataFormatter.isWrappingFormatter()) {
            this.wrapDataCells = true;
        }
        else {
            this.wrapDataCells = false;
        }
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setTitleBackground(final Color background) {
        this.titleBackground = background;
    }
    
    public void setTitleFont(final Font f) {
        this.titleFont = f;
        this.titleFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(f);
    }
    
    public void setTitleForeground(final Color foreground) {
        this.titleForeground = foreground;
    }
    
    public void setTitleFormatter(final StringFormatter formatter) {
        this.titleFormatter = formatter;
    }
    
    public void setWidthAdjustment(final int adjustment) {
        this.widthAdjustment = adjustment;
    }
    
    public void setWidthInChars(final int nZeroes) {
        this.widthAdjustment = 1;
        this.widthInChars = nZeroes;
    }
    
    public void setWidthInPixels(final int nPixels) {
        this.widthAdjustment = 0;
        this.widthInPixels = nPixels;
    }
}
