// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.utils.SetupManager;
import jfig.gui.FontCache;
import java.util.StringTokenizer;
import jfig.gui.ColorCache;
import java.util.Properties;
import java.awt.Color;
import java.awt.Font;

public class FigAttribs implements Cloneable
{
    public static final int FIG_20 = 20;
    public static final int FIG_21 = 21;
    public static final int FIG_30 = 30;
    public static final int FIG_31 = 31;
    public static final int FIG_32 = 32;
    public static final int SIMPLE_ARROW = 2;
    public static final int TRIANGLE_ARROW = 4;
    public static final int TRIANGLE_FILL_ARROW = 5;
    public static final int BACK_ARROW = 6;
    public static final int BACK_FILL_ARROW = 7;
    public static final int ARC_ARROW = 8;
    public static final int ARC_FILL_ARROW = 9;
    public static final int SOLDERDOT_ARROW = 11;
    public static final int NO_ARROW = 0;
    public static final int FORWARD_ARROW = 1;
    public static final int BACKWARD_ARROW = 2;
    public static final int DOUBLE_ARROW = 3;
    public static final int SOLID_LINE = 0;
    public static final int DASHED_LINE = 1;
    public static final int DOTTED_LINE = 2;
    public static final int DASH_DOT_LINE = 3;
    public static final int DASH_DOT2_LINE = 4;
    public static final int DASH_DOT3_LINE = 5;
    public static final int LINE_WIDTH_0 = 0;
    public static final int LINE_WIDTH_1 = 1;
    public static final int LINE_WIDTH_2 = 2;
    public static final int LINE_WIDTH_3 = 3;
    public static final int LINE_WIDTH_4 = 4;
    public static final int LINE_WIDTH_5 = 5;
    public static final int LINE_WIDTH_6 = 6;
    public static final int LINE_WIDTH_7 = 7;
    public static final int LINE_WIDTH_8 = 8;
    public static final int LINE_WIDTH_9 = 9;
    public static final double defaultDashLength = 192.0;
    public static final int NO_FILL = 1;
    public static final int SOLID_FILL = 2;
    public static final int PATTERN_FILL = 3;
    public static final int PATTERN_BLACK = 0;
    public static final int PATTERN_PURE = 20;
    public static final int PATTERN_WHITE = 40;
    public static final int PATTERN_30_DEGREE_LEFT = 41;
    public static final int PATTERN_30_DEGREE_RIGHT = 42;
    public static final int PATTERN_30_DEGREE_CROSSHATCH = 43;
    public static final int PATTERN_45_DEGREE_LEFT = 44;
    public static final int PATTERN_45_DEGREE_RIGHT = 45;
    public static final int PATTERN_45_DEGREE_CROSSHATCH = 46;
    public static final int PATTERN_BRICKS = 47;
    public static final int PATTERN_CIRCLES = 48;
    public static final int PATTERN_HORIZONTAL_LINES = 49;
    public static final int PATTERN_VERTICAL_LINES = 50;
    public static final int PATTERN_CROSSHATCH = 51;
    public static final int PATTERN_FISH_SCALES = 52;
    public static final int PATTERN_SMALL_FISH_SCALES = 53;
    public static final int PATTERN_OCTOGONS = 54;
    public static final int PATTERN_HORIZONTAL_TIRE_TREADS = 55;
    public static final int PATTERN_VERTICAL_TIRE_TREADDS = 56;
    public static final int JOIN_MITER = 0;
    public static final int JOIN_BEVEL = 1;
    public static final int JOIN_ROUND = 2;
    public static final int CAP_BUTT = 0;
    public static final int CAP_ROUND = 1;
    public static final int CAP_PROJECTING = 2;
    public static final int NO_ALIGN = 0;
    public static final int LEFT_ALIGN = 1;
    public static final int CENTER_X_ALIGN = 2;
    public static final int RIGHT_ALIGN = 3;
    public static final int BOTTOM_ALIGN = 1;
    public static final int CENTER_Y_ALIGN = 2;
    public static final int TOP_ALIGN = 3;
    public static final int defaultFontSize = 12;
    public static final Font defaultFont;
    public static final int FONT_TIMES_ROMAN = 0;
    public static final int FONT_TIMES_ROMAN_ITALIC = 1;
    public static final int FONT_TIMES_ROMAN_BOLD = 2;
    public static final int FONT_TIMES_ROMAN_BOLD_ITALIC = 3;
    public static final int FONT_AVANTGARDE_BOOK = 4;
    public static final int FONT_AVANTGARDE_BOOK_OBLIQUE = 5;
    public static final int FONT_AVANTGARDE_BOOK_DEMI = 6;
    public static final int FONT_AVANTGARDE_BOOK_DEMI_OBLIQUE = 7;
    public static final int FONT_BOOKMAN_LIGHT = 8;
    public static final int FONT_BOOKMAN_LIGHT_ITALIC = 9;
    public static final int FONT_BOOKMAN_LIGHT_DEMI = 10;
    public static final int FONT_BOOKMAN_LIGHT_DEMI_ITALIC = 11;
    public static final int FONT_COURIER = 12;
    public static final int FONT_COURIER_OBLIQUE = 13;
    public static final int FONT_COURIER_BOLD = 14;
    public static final int FONT_COURIER_BOLD_OBLIQUE = 15;
    public static final int FONT_HELVETICA = 16;
    public static final int FONT_HELVETICA_OBLIQUE = 17;
    public static final int FONT_HELVETICA_BOLD = 18;
    public static final int FONT_HELVETICA_BOLD_OBLIQUE = 19;
    public static final int FONT_HELVETICA_NARROW = 20;
    public static final int FONT_HELVETICA_NARROW_OBLIQUE = 21;
    public static final int FONT_HELVETICA_NARROW_BOLD = 22;
    public static final int FONT_HELVETICA_NARROW_BOLD_OBLIQUE = 23;
    public static final int FONT_NEW_CENTURY_SCHLBK_ROMAN = 24;
    public static final int FONT_NEW_CENTURY_SCHLBK_ITALIC = 25;
    public static final int FONT_NEW_CENTURY_SCHLBK_BOLD = 26;
    public static final int FONT_NEW_CENTURY_SCHLBK_BOLD_ITALIC = 27;
    public static final int FONT_PALATINO_ROMAN = 28;
    public static final int FONT_PALATINO_ITALIC = 29;
    public static final int FONT_PALATINO_BOLD = 30;
    public static final int FONT_PALATINO_BOLD_ITALIC = 31;
    public static final int FONT_SYMBOL = 32;
    public static final int FONT_ZAPF_CHANCERY_MEDIUM_ITALIC = 33;
    public static final int FONT_ZAPF_DINGBATS = 34;
    public static final int FONT_CMR = 35;
    public static final int FONT_CMTI = 36;
    public static final int FONT_CMBX = 37;
    public static final int FONT_CMSS = 38;
    public static final int FONT_CMSL = 39;
    public static final int FONT_CMTT = 40;
    public static final int FONT_CMITT = 41;
    public static final int FONT_CMMI = 42;
    public static final int FONT_CMMIB = 43;
    public static final int FONT_CMSY = 44;
    public static final int FONT_CMBSY = 45;
    public static final int FONT_CMEX = 46;
    public static final int FONT_MSAM = 47;
    public static final int FONT_MSBM = 48;
    public static final String HEADER_UNITS_METRIC = "Metric";
    public static final String HEADER_UNITS_INCHES = "Inches";
    public static final String[] HEADER_PAPER_SIZE;
    public static int defaultFigVersion;
    public static String pageUnits;
    public static String pageJustification;
    public static String pageOrientation;
    public static String paperSize;
    public static String singleOrMultipleSheets;
    public static double exportMagnification;
    public static int transparentColorIndex;
    private static String[] objects;
    private static String[] colorNames;
    private static String[] lineStyles;
    private static String[] arrowModes;
    private static String[] patterns;
    public static final int Landscape = 0;
    public static final int Portrait = 1;
    public static final int CenterJustification = 0;
    public static final int FlushLeftJustification = 1;
    public static final int MetricUnits = 0;
    public static final int InchesUnits = 1;
    public static final int XMMUnits = 2;
    public static int fig_orientation;
    public static int fig_justification;
    public static int fig_units;
    public static int fig_resolution;
    public static int fig_origin;
    public static boolean enableJava2D;
    public static boolean updateLineColorMask;
    public static boolean updateLineStyleMask;
    public static boolean updateLineWidthMask;
    public static boolean updateArrowModeMask;
    public static boolean updateArrowStyleMask;
    public static boolean updateFillColorMask;
    public static boolean updateFillStyleMask;
    public static boolean updateFontMask;
    public static boolean updateFontSizeMask;
    public static boolean updateFontFlagMask;
    public static boolean updateTextAlignMask;
    public static boolean updateTextMask;
    public static boolean updateAngleMask;
    public static boolean updateLayerMask;
    public static boolean updateCornerRadiusMask;
    public int fig_line_color;
    public int fig_fill_color;
    public int fig_area_fill;
    public int fig_pen_style;
    public int fig_join_style;
    public int fig_cap_style;
    public double fig_style_val;
    public double fig_angle;
    public double fig_ellipse_angle;
    public int fig_ellipse_direction;
    public int fig_font;
    public int fig_font_flags;
    public double fig_text_height;
    public double fig_text_width;
    public int figVersion;
    public Color lineColor;
    public int lineStyle;
    public int arrowMode;
    public double lineWidth;
    public double dashLength;
    public int currentLayer;
    public int arrow_f_Style;
    public int arrow_b_Style;
    public double arrow_f_Width;
    public double arrow_f_Length;
    public double arrow_f_Thickness;
    public double arrow_b_Width;
    public double arrow_b_Length;
    public double arrow_b_Thickness;
    public int cornerRadius;
    public int objectAlign;
    public int textAlign;
    public Color fillColor;
    public int fillStyle;
    public int fontSize;
    
    public FigAttribs getClone() {
        try {
            return (FigAttribs)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void update(final FigAttribs figAttribs) {
        if (figAttribs.getUpdateLineStyleMask()) {
            this.lineStyle = figAttribs.lineStyle;
            this.fig_style_val = figAttribs.fig_style_val;
            this.dashLength = figAttribs.dashLength;
        }
        if (figAttribs.getUpdateLineWidthMask()) {
            this.lineWidth = figAttribs.lineWidth;
        }
        if (figAttribs.getUpdateLineColorMask()) {
            this.lineColor = figAttribs.lineColor;
            this.fig_line_color = figAttribs.fig_line_color;
        }
        if (figAttribs.getUpdateArrowModeMask()) {
            this.arrowMode = figAttribs.arrowMode;
        }
        if (figAttribs.getUpdateArrowStyleMask()) {
            this.arrow_f_Style = figAttribs.arrow_f_Style;
            this.arrow_b_Style = figAttribs.arrow_b_Style;
            this.arrow_f_Width = figAttribs.arrow_f_Width;
            this.arrow_f_Length = figAttribs.arrow_f_Length;
            this.arrow_f_Thickness = figAttribs.arrow_f_Thickness;
            this.arrow_b_Width = figAttribs.arrow_b_Width;
            this.arrow_b_Length = figAttribs.arrow_b_Length;
            this.arrow_b_Thickness = figAttribs.arrow_b_Thickness;
        }
        if (figAttribs.getUpdateFillStyleMask()) {
            this.fillStyle = figAttribs.fillStyle;
            this.fig_area_fill = figAttribs.fig_area_fill;
        }
        if (figAttribs.getUpdateFillColorMask()) {
            this.fillColor = figAttribs.fillColor;
            this.fig_fill_color = figAttribs.fig_fill_color;
        }
        if (figAttribs.getUpdateFontMask()) {
            this.fig_font = figAttribs.fig_font;
        }
        if (figAttribs.getUpdateFontSizeMask()) {
            this.fontSize = figAttribs.fontSize;
        }
        if (figAttribs.getUpdateFontFlagMask()) {
            this.fig_font_flags = figAttribs.fig_font_flags;
        }
        if (figAttribs.getUpdateTextAlignMask()) {
            this.textAlign = figAttribs.textAlign;
        }
        if (figAttribs.getUpdateLayerMask()) {
            this.currentLayer = figAttribs.currentLayer;
        }
        if (figAttribs.getUpdateCornerRadiusMask()) {
            this.cornerRadius = figAttribs.cornerRadius;
        }
        if (figAttribs.getUpdateAngleMask()) {
            this.fig_angle = figAttribs.fig_angle;
        }
    }
    
    public static void initializeFromProperties(final Properties properties) {
        FigAttribs.pageUnits = properties.getProperty("jfig.units", "Metric");
        FigAttribs.paperSize = properties.getProperty("jfig.paperSize", "A4");
        FigAttribs.pageJustification = properties.getProperty("jfig.pageJustification", "Center");
        FigAttribs.pageOrientation = properties.getProperty("jfig.pageOrientation", "Landscape");
    }
    
    public void setFigLineWidth(final int n) {
        if (n <= 0) {
            this.lineWidth = 0.0;
        }
        else {
            this.lineWidth = n * 30;
        }
    }
    
    public String getPageOrientation() {
        return FigAttribs.pageOrientation;
    }
    
    public String getPageJustification() {
        return FigAttribs.pageJustification;
    }
    
    public String getPageUnits() {
        return FigAttribs.pageUnits;
    }
    
    public String getPaperSize() {
        return FigAttribs.paperSize;
    }
    
    public double getExportMagnification() {
        return FigAttribs.exportMagnification;
    }
    
    public String getSingleOrMultipleSheets() {
        return FigAttribs.singleOrMultipleSheets;
    }
    
    public int getTransparentColorIndex() {
        return FigAttribs.transparentColorIndex;
    }
    
    public int getFigOrientation() {
        return FigAttribs.fig_orientation;
    }
    
    public int getFigJustification() {
        return FigAttribs.fig_justification;
    }
    
    public int getFigUnits() {
        return FigAttribs.fig_units;
    }
    
    public int getFigOrigin() {
        return FigAttribs.fig_origin;
    }
    
    public int getFigResolution() {
        return FigAttribs.fig_resolution;
    }
    
    public int getDefaultFigVersion() {
        return FigAttribs.defaultFigVersion;
    }
    
    public boolean getEnableJava2D() {
        return FigAttribs.enableJava2D;
    }
    
    public void setPageOrientation(final String pageOrientation) {
        FigAttribs.pageOrientation = pageOrientation;
    }
    
    public void setPageJustification(final String pageJustification) {
        FigAttribs.pageJustification = pageJustification;
    }
    
    public void setPageUnits(final String pageUnits) {
        FigAttribs.pageUnits = pageUnits;
    }
    
    public void setPaperSize(final String paperSize) {
        FigAttribs.paperSize = paperSize;
    }
    
    public void setExportMagnification(final double exportMagnification) {
        FigAttribs.exportMagnification = exportMagnification;
    }
    
    public void setSingleOrMultipleSheets(final String singleOrMultipleSheets) {
        FigAttribs.singleOrMultipleSheets = singleOrMultipleSheets;
    }
    
    public void setDefaultFigVersion(final int defaultFigVersion) {
        FigAttribs.defaultFigVersion = defaultFigVersion;
    }
    
    public void setFigVersion(final int figVersion) {
        this.figVersion = figVersion;
    }
    
    public void setFigOrientation(final int fig_orientation) {
        FigAttribs.fig_orientation = fig_orientation;
    }
    
    public void setFigJustification(final int fig_justification) {
        FigAttribs.fig_justification = fig_justification;
    }
    
    public void setFigUnits(final int fig_units) {
        FigAttribs.fig_units = fig_units;
    }
    
    public void setFigResolution(final int fig_resolution) {
        FigAttribs.fig_resolution = fig_resolution;
    }
    
    public void setFigOrigin(final int fig_origin) {
        FigAttribs.fig_origin = fig_origin;
    }
    
    public void setTransparentColorIndex(final int transparentColorIndex) {
        FigAttribs.transparentColorIndex = transparentColorIndex;
    }
    
    public boolean getUpdateLineStyleMask() {
        return FigAttribs.updateLineStyleMask;
    }
    
    public boolean getUpdateLineWidthMask() {
        return FigAttribs.updateLineWidthMask;
    }
    
    public boolean getUpdateLineColorMask() {
        return FigAttribs.updateLineColorMask;
    }
    
    public boolean getUpdateArrowModeMask() {
        return FigAttribs.updateArrowModeMask;
    }
    
    public boolean getUpdateArrowStyleMask() {
        return FigAttribs.updateArrowStyleMask;
    }
    
    public boolean getUpdateFillStyleMask() {
        return FigAttribs.updateFillStyleMask;
    }
    
    public boolean getUpdateFillColorMask() {
        return FigAttribs.updateFillColorMask;
    }
    
    public boolean getUpdateFontMask() {
        return FigAttribs.updateFontMask;
    }
    
    public boolean getUpdateFontSizeMask() {
        return FigAttribs.updateFontSizeMask;
    }
    
    public boolean getUpdateFontFlagMask() {
        return FigAttribs.updateFontFlagMask;
    }
    
    public boolean getUpdateTextAlignMask() {
        return FigAttribs.updateTextAlignMask;
    }
    
    public boolean getUpdateLayerMask() {
        return FigAttribs.updateLayerMask;
    }
    
    public boolean getUpdateCornerRadiusMask() {
        return FigAttribs.updateCornerRadiusMask;
    }
    
    public boolean getUpdateAngleMask() {
        return FigAttribs.updateAngleMask;
    }
    
    public void setUpdateLineStyleMask(final boolean updateLineStyleMask) {
        FigAttribs.updateLineStyleMask = updateLineStyleMask;
    }
    
    public void setUpdateLineWidthMask(final boolean updateLineWidthMask) {
        FigAttribs.updateLineWidthMask = updateLineWidthMask;
    }
    
    public void setUpdateLineColorMask(final boolean updateLineColorMask) {
        FigAttribs.updateLineColorMask = updateLineColorMask;
    }
    
    public void setUpdateArrowModeMask(final boolean updateArrowModeMask) {
        FigAttribs.updateArrowModeMask = updateArrowModeMask;
    }
    
    public void setUpdateArrowStyleMask(final boolean updateArrowStyleMask) {
        FigAttribs.updateArrowStyleMask = updateArrowStyleMask;
    }
    
    public void setUpdateFillStyleMask(final boolean updateFillStyleMask) {
        FigAttribs.updateFillStyleMask = updateFillStyleMask;
    }
    
    public void setUpdateFillColorMask(final boolean updateFillColorMask) {
        FigAttribs.updateFillColorMask = updateFillColorMask;
    }
    
    public void setUpdateFontMask(final boolean updateFontMask) {
        FigAttribs.updateFontMask = updateFontMask;
    }
    
    public void setUpdateFontSizeMask(final boolean updateFontSizeMask) {
        FigAttribs.updateFontSizeMask = updateFontSizeMask;
    }
    
    public void setUpdateFontFlagMask(final boolean updateFontFlagMask) {
        FigAttribs.updateFontFlagMask = updateFontFlagMask;
    }
    
    public void setUpdateTextAlignMask(final boolean updateTextAlignMask) {
        FigAttribs.updateTextAlignMask = updateTextAlignMask;
    }
    
    public void setUpdateLayerMask(final boolean updateLayerMask) {
        FigAttribs.updateLayerMask = updateLayerMask;
    }
    
    public void setUpdateCornerRadiusMask(final boolean updateCornerRadiusMask) {
        FigAttribs.updateCornerRadiusMask = updateCornerRadiusMask;
    }
    
    public void setUpdateAngleMask(final boolean updateAngleMask) {
        FigAttribs.updateAngleMask = updateAngleMask;
    }
    
    public void setLineColor(final Color lineColor) {
        final int registerUserColor = ColorCache.getColorCache().registerUserColor(lineColor);
        this.lineColor = lineColor;
        this.fig_line_color = registerUserColor;
    }
    
    public void setFillColor(final Color fillColor) {
        final int registerUserColor = ColorCache.getColorCache().registerUserColor(fillColor);
        this.fillColor = fillColor;
        this.fig_fill_color = registerUserColor;
    }
    
    public String toString() {
        return "FigAttribs.toString():\n layer " + this.currentLayer + " align " + this.textAlign + "\n" + " line color " + this.lineColor.toString() + "\n" + " fill color " + this.fillColor.toString() + " fill style " + this.fillStyle + "\n" + " line style " + this.lineStyle + " width " + this.lineWidth + " dash " + this.dashLength + "\n" + " arrow mode " + this.arrowMode + "f: " + this.arrow_f_Style + ", " + this.arrow_f_Length + ", " + this.arrow_f_Width + ", b: " + this.arrow_b_Style + ", " + this.arrow_b_Length + ", " + this.arrow_b_Width + "\n" + " font " + this.fig_font + " size " + this.fontSize + "\n";
    }
    
    public void restoreDefaults() {
        this.lineColor = Color.black;
        this.fig_line_color = 0;
        this.fillColor = Color.black;
        this.fillStyle = 1;
        this.fig_fill_color = -1;
        this.fig_area_fill = -1;
        this.dashLength = 192.0;
        this.fig_style_val = 4.0;
    }
    
    public void parse(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " \t\n");
            while (stringTokenizer.hasMoreTokens()) {
                this.parseSingleToken(stringTokenizer.nextToken());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void parseSingleToken(final String s) {
        final int index = s.indexOf(61);
        if (index > 0) {
            final String substring = s.substring(0, index);
            final String substring2 = s.substring(index + 1, s.length());
            if ("color".equals(substring)) {
                this.setLineColor(substring2);
            }
            else if ("linecolor".equals(substring)) {
                this.setLineColor(substring2);
            }
            else if ("linestyle".equals(substring)) {
                this.setLineStyle(substring2);
            }
            else if ("style".equals(substring)) {
                this.setLineStyle(substring2);
            }
            else if ("linewidth".equals(substring)) {
                this.setLineWidth(substring2);
            }
            else if ("width".equals(substring)) {
                this.setLineWidth(substring2);
            }
            else if ("arrowmode".equals(substring)) {
                this.setArrowMode(substring2);
            }
            else if ("arrowstyle".equals(substring)) {
                this.setArrowStyle(substring2);
            }
            else if ("arrowsize".equals(substring)) {
                this.setArrowSize(substring2);
            }
            else if ("fillcolor".equals(substring)) {
                this.setFillColor(substring2);
            }
            else if ("fillstyle".equals(substring)) {
                this.setFillStyle(substring2);
            }
            else if ("pattern".equals(substring)) {
                this.setFillStyle(substring2);
            }
            else if ("depth".equals(substring)) {
                this.setLayer(substring2);
            }
            else if ("layer".equals(substring)) {
                this.setLayer(substring2);
            }
            else if ("font".equals(substring)) {
                this.setFont(substring2);
            }
            else if ("fontname".equals(substring)) {
                this.setFont(substring2);
            }
            else if ("fontsize".equals(substring)) {
                this.setFontSize(substring2);
            }
            else if ("pt".equals(substring)) {
                this.setFontSize(substring2);
            }
            else if ("align".equals(substring)) {
                this.setTextAlign(substring2);
            }
            else if ("textalign".equals(substring)) {
                this.setTextAlign(substring2);
            }
            else if ("angle".equals(substring)) {
                this.setRotationAngle(substring2);
            }
            else if ("rotation".equals(substring)) {
                this.setRotationAngle(substring2);
            }
            else {
                this.err("parseSingleToken: Unknown key: '" + s + "'");
            }
        }
        else {
            for (int i = 0; i < FigAttribs.colorNames.length; ++i) {
                if (FigAttribs.colorNames[i].equals(s)) {
                    this.setLineColor(s);
                    return;
                }
            }
            for (int j = 0; j < FigAttribs.lineStyles.length; ++j) {
                if (FigAttribs.lineStyles[j].equals(s)) {
                    this.setLineStyle(s);
                    return;
                }
            }
            for (int k = 0; k < FigAttribs.arrowModes.length; ++k) {
                if (FigAttribs.arrowModes[k].equals(s)) {
                    this.setArrowMode(s);
                    return;
                }
            }
            for (int l = 0; l < FigAttribs.patterns.length; ++l) {
                if (FigAttribs.patterns[l].equals(s)) {
                    this.setFillStyle(s);
                    return;
                }
            }
            this.err("-E- Unsupported attribute token: '" + s + "'");
        }
    }
    
    public void setLineColor(final String s) {
        this.fig_line_color = this.decodeColor(s);
        this.lineColor = ColorCache.getColorCache().get(this.fig_line_color);
    }
    
    public void setLineStyle(final String s) {
        final String lowerCase = s.trim().toLowerCase();
        if ("solid".equals(lowerCase)) {
            this.lineStyle = 0;
        }
        else if ("dashed".equals(lowerCase)) {
            this.lineStyle = 1;
        }
        else if ("dotted".equals(lowerCase)) {
            this.lineStyle = 2;
        }
        else if ("dashdot".equals(lowerCase)) {
            this.lineStyle = 3;
        }
        else if ("dashdot2".equals(lowerCase)) {
            this.lineStyle = 4;
        }
        else if ("dashdot3".equals(lowerCase)) {
            this.lineStyle = 5;
        }
        else if ("-".equals(lowerCase)) {
            this.lineStyle = 0;
        }
        else if ("--".equals(lowerCase)) {
            this.lineStyle = 1;
        }
        else if (".".equals(lowerCase)) {
            this.lineStyle = 2;
        }
        else if ("-.".equals(lowerCase)) {
            this.lineStyle = 3;
        }
        else if ("-..".equals(lowerCase)) {
            this.lineStyle = 4;
        }
        else if ("-...".equals(lowerCase)) {
            this.lineStyle = 5;
        }
        else {
            this.err("setLineStyle: Unknown line style '" + s + "'");
        }
    }
    
    public void setLineWidth(final String s) {
        try {
            final int int1 = Integer.parseInt(s);
            if (int1 <= 0) {
                this.lineWidth = 0.0;
            }
            else {
                this.lineWidth = int1 * 30;
            }
        }
        catch (NumberFormatException ex) {
            this.err("setLineWidth: Illegal number format '" + s + "'");
        }
    }
    
    public void setArrowMode(final String s) {
        final String lowerCase = s.trim().toLowerCase();
        if ("none".equals(lowerCase)) {
            this.arrowMode = 0;
        }
        else if ("forward".equals(lowerCase)) {
            this.arrowMode = 1;
        }
        else if ("backward".equals(lowerCase)) {
            this.arrowMode = 2;
        }
        else if ("both".equals(lowerCase)) {
            this.arrowMode = 3;
        }
        else if ("-".equals(lowerCase)) {
            this.arrowMode = 0;
        }
        else if ("->".equals(lowerCase)) {
            this.arrowMode = 1;
        }
        else if ("<-".equals(lowerCase)) {
            this.arrowMode = 2;
        }
        else if ("<->".equals(lowerCase)) {
            this.arrowMode = 3;
        }
        else {
            this.err("setArrowMode: Unknown mode '" + s + "'");
        }
    }
    
    public void setArrowStyle(final String s) {
        final String lowerCase = s.trim().toLowerCase();
        int n = 0;
        if ("0".equals(lowerCase)) {
            n = 2;
        }
        else if ("1".equals(lowerCase)) {
            n = 4;
        }
        else if ("2".equals(lowerCase)) {
            n = 5;
        }
        else if ("3".equals(lowerCase)) {
            n = 6;
        }
        else if ("4".equals(lowerCase)) {
            n = 7;
        }
        else if ("5".equals(lowerCase)) {
            n = 8;
        }
        else if ("6".equals(lowerCase)) {
            n = 9;
        }
        else if ("7".equals(lowerCase)) {
            n = 11;
        }
        else {
            this.err("setArrowStyle: unknown value '" + s + "'");
        }
        this.arrow_f_Style = n;
        this.arrow_b_Style = n;
    }
    
    public void setArrowSize(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
            final double doubleValue2 = Double.valueOf(stringTokenizer.nextToken());
            final double doubleValue3 = Double.valueOf(stringTokenizer.nextToken());
            this.arrow_f_Width = 96.0 * doubleValue;
            this.arrow_f_Length = 96.0 * doubleValue2;
            this.arrow_f_Thickness = 30.0 * doubleValue3;
            this.arrow_b_Width = 96.0 * doubleValue;
            this.arrow_b_Length = 96.0 * doubleValue2;
            this.arrow_b_Thickness = 30.0 * doubleValue3;
        }
        catch (Exception ex) {
            this.err("setArrowSize: invalid input '" + s + "'");
        }
    }
    
    public void setFillColor(final String s) {
        this.fig_fill_color = this.decodeColor(s);
        this.fillColor = ColorCache.getColorCache().get(this.fig_fill_color);
    }
    
    public void setFillStyle(final String s) {
        try {
            final String lowerCase = s.trim().toLowerCase();
            if ("none".equals(lowerCase)) {
                this.fillStyle = 1;
                this.fig_area_fill = 0;
            }
            else if ("pure".equals(lowerCase)) {
                this.fillStyle = 2;
                this.fig_area_fill = 20;
            }
            else if (lowerCase.indexOf(37) > 0) {
                int fig_area_fill = Integer.parseInt(lowerCase.substring(0, lowerCase.indexOf(37))) / 5;
                if (fig_area_fill < 0) {
                    fig_area_fill = 0;
                }
                if (fig_area_fill > 40) {
                    fig_area_fill = 40;
                }
                this.fillStyle = 2;
                this.fig_area_fill = fig_area_fill;
            }
            else {
                final int int1 = Integer.parseInt(lowerCase);
                if (int1 < 0) {
                    this.fillStyle = 2;
                    this.fig_area_fill = 0;
                }
                else if (int1 <= 40) {
                    this.fillStyle = 2;
                    this.fig_area_fill = int1;
                }
                else if (int1 <= 56) {
                    this.fillStyle = 3;
                    this.fig_area_fill = int1;
                }
                else {
                    this.err("setFillStyle: value out of range '" + s + "'");
                }
            }
        }
        catch (Exception ex) {
            this.err("setFillStyle: internal error, input is '" + s + "'");
            this.fillStyle = 1;
            this.fig_area_fill = 0;
        }
    }
    
    public void setFont(final String s) {
        final String lowerCase = s.trim().toLowerCase();
        try {
            if (lowerCase.length() <= 2) {
                this.fig_font = Integer.parseInt(lowerCase);
            }
            else {
                this.fig_font = FontCache.getFontCache().getFontIndex(lowerCase);
            }
        }
        catch (Exception ex) {
            this.err("setFont: parsing error '" + s + "'");
        }
    }
    
    public void setFontSize(final String s) {
        try {
            this.fontSize = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            this.err("setFontSize: Invalid number format: '" + s + "'");
        }
    }
    
    public void setTextAlign(final String s) {
        final String lowerCase = s.trim().toLowerCase();
        if ("left".equals(lowerCase)) {
            this.textAlign = 1;
        }
        else if ("center".equals(lowerCase)) {
            this.textAlign = 2;
        }
        else if ("right".equals(lowerCase)) {
            this.textAlign = 3;
        }
        else {
            this.err("setTextAlign: unknown value: '" + s + "'");
        }
    }
    
    public void setLayer(final String s) {
        try {
            this.currentLayer = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            this.err("setLayer: Invalid number format: '" + s + "'");
        }
    }
    
    public void setDepth(final String layer) {
        this.setLayer(layer);
    }
    
    public void setRotationAngle(final String s) {
        try {
            this.fig_angle = Double.valueOf(s.trim()) * 3.141592653589793 / 180.0;
            this.fig_ellipse_angle = this.fig_angle;
        }
        catch (Exception ex) {
            this.err("setRotationAngle: Invalid input: '" + s + "'");
        }
    }
    
    private int decodeColor(final String s) {
        final String lowerCase = s.trim().toLowerCase();
        if (lowerCase.startsWith("0x")) {
            return ColorCache.getColorCache().registerUserColor(new Color(Integer.parseInt(lowerCase.substring(2), 16)));
        }
        if (lowerCase.startsWith("(")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(lowerCase, "() ,");
            return ColorCache.getColorCache().registerUserColor(new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())));
        }
        for (int i = 0; i < FigAttribs.colorNames.length; ++i) {
            if (FigAttribs.colorNames[i].equals(lowerCase)) {
                return i;
            }
        }
        this.err("decodeColor: unknown color '" + s + "'");
        return 0;
    }
    
    private void err(final String s) {
        System.err.println("-E- FigAttribs:" + s);
    }
    
    public void NOT_YET() {
        Thread.dumpStack();
    }
    
    public FigAttribs() {
        this.fig_style_val = 4.0;
        this.figVersion = FigAttribs.defaultFigVersion;
        this.lineColor = Color.black;
        this.lineStyle = 0;
        this.arrowMode = 0;
        this.lineWidth = 0.0;
        this.dashLength = 192.0;
        this.currentLayer = 5;
        this.arrow_f_Style = 2;
        this.arrow_b_Style = 2;
        this.cornerRadius = 192;
        this.objectAlign = 2;
        this.textAlign = 2;
        this.fillColor = Color.black;
        this.fillStyle = 1;
        this.fontSize = 12;
        this.figVersion = FigAttribs.defaultFigVersion;
        this.currentLayer = 100;
        this.lineColor = Color.black;
        this.lineStyle = 0;
        this.arrowMode = 0;
        this.arrow_f_Style = 2;
        this.arrow_b_Style = 2;
        this.arrow_f_Width = 288.0;
        this.arrow_f_Length = 480.0;
        this.arrow_f_Thickness = 30.0;
        this.arrow_b_Width = 288.0;
        this.arrow_b_Length = 480.0;
        this.arrow_b_Thickness = 30.0;
        this.lineWidth = 30.0;
        this.dashLength = 192.0;
        this.objectAlign = 2;
        this.textAlign = 1;
        this.fillColor = Color.black;
        this.fillStyle = 1;
        this.cornerRadius = 0;
        this.fontSize = 12;
        FigAttribs.fig_justification = 0;
        FigAttribs.fig_units = 1;
        FigAttribs.fig_resolution = 1200;
        FigAttribs.fig_origin = 2;
        this.fig_line_color = -1;
        this.fig_fill_color = -1;
        this.fig_area_fill = 20;
        this.fig_pen_style = 1;
        this.fig_join_style = 0;
        this.fig_cap_style = 0;
        this.fig_style_val = 4.0;
        this.fig_angle = 0.0;
        this.fig_ellipse_angle = 0.0;
        this.fig_ellipse_direction = 1;
        this.fig_font = 0;
        this.fig_font_flags = 4;
        this.fig_text_height = 0.0;
    }
    
    static {
        defaultFont = new Font("Courier", 0, 10);
        HEADER_PAPER_SIZE = new String[] { "A4", "A3", "Letter", "Legal" };
        FigAttribs.defaultFigVersion = 32;
        FigAttribs.pageUnits = "Metric";
        FigAttribs.pageJustification = "Center";
        FigAttribs.pageOrientation = "Portrait";
        FigAttribs.paperSize = "A4";
        FigAttribs.singleOrMultipleSheets = "Single";
        FigAttribs.exportMagnification = 100.0;
        FigAttribs.transparentColorIndex = -2;
        FigAttribs.objects = new String[] { "polyline", "line", "polygon", "patch", "spline", "bspline", "ispline", "xspline", "arc", "rect", "rectangle", "roundrect", "circle", "ellipse", "image", "text" };
        FigAttribs.colorNames = new String[] { "black", "blue", "green", "cyan", "red", "magenta", "yellow", "white", "blue4", "blue3", "blue2", "ltblue", "green4", "green3", "green2", "cyan4", "cyan3", "cyan2", "red4", "red3", "red", "magenta4", "magenta3", "magenta2", "brown4", "brown3", "brown2", "pink4", "pink3", "pink2", "pink", "gold" };
        FigAttribs.lineStyles = new String[] { "solid", "dashed", "dotted", "dashdot", "dashdot2", "dashdot3", "-", "--", ".", "-.", "-..", "-..." };
        FigAttribs.arrowModes = new String[] { "none", "forward", "backward", "both", "-", "->", "<-", "<->" };
        FigAttribs.patterns = new String[] { "none", "pure", "0%", "5%", "10%", "15%", "20%", "25%", "30%", "35%", "40%", "45%", "50%", "55%", "60%", "65%", "70%", "75%", "80%", "85%", "90%", "95%", "100%", "105%", "110%", "115%", "120%", "125%", "130%", "135%", "140%", "145%", "150%", "155%", "160%", "165%", "170%", "175%", "180%", "185%", "190%", "195%", "bricks", "crosshatch", "fishscales" };
        FigAttribs.fig_orientation = 0;
        FigAttribs.fig_justification = 0;
        FigAttribs.fig_units = 0;
        FigAttribs.fig_resolution = 1200;
        FigAttribs.fig_origin = 2;
        FigAttribs.enableJava2D = false;
        FigAttribs.updateLineColorMask = true;
        FigAttribs.updateLineStyleMask = true;
        FigAttribs.updateLineWidthMask = true;
        FigAttribs.updateArrowModeMask = true;
        FigAttribs.updateArrowStyleMask = true;
        FigAttribs.updateFillColorMask = true;
        FigAttribs.updateFillStyleMask = true;
        FigAttribs.updateFontMask = true;
        FigAttribs.updateFontSizeMask = true;
        FigAttribs.updateFontFlagMask = true;
        FigAttribs.updateTextAlignMask = true;
        FigAttribs.updateTextMask = true;
        FigAttribs.updateAngleMask = true;
        FigAttribs.updateLayerMask = true;
        FigAttribs.updateCornerRadiusMask = true;
        try {
            Class.forName("java.awt.geom.AffineTransform");
            Class.forName("java.awt.Graphics2D");
            if (System.getProperty("java.vendor").toLowerCase().indexOf("kaffe") >= 0) {
                FigAttribs.enableJava2D = false;
                System.out.println("-I- Java2D disabled due to Kaffe VM bugs.");
            }
            else if (SetupManager.getBoolean("jfig.allowJava2D", true)) {
                FigAttribs.enableJava2D = true;
                System.out.println("-I- Java2D found, using it...");
            }
            else {
                FigAttribs.enableJava2D = false;
                System.out.println("-I- Java2D available but disabled, using AWT...");
            }
        }
        catch (Throwable t) {
            FigAttribs.enableJava2D = false;
            System.out.println("-I- Java2D not found, using AWT...");
        }
    }
}
