// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import prefuse.data.Schema;
import prefuse.visual.VisualItem;
import prefuse.Display;

public class PrefuseLib
{
    private static final String GROUP_DELIMITER;
    private static final double SIZE_SCALE_FACTOR;
    public static final String FIELD_PREFIX;
    
    public static String getMemoryUsageInKB() {
        final long n = Runtime.getRuntime().totalMemory() / 2048L;
        return "Memory: " + (n - Runtime.getRuntime().freeMemory() / 2048L) + "k / " + n + "k / " + Runtime.getRuntime().maxMemory() / 2048L + "k";
    }
    
    public static String getMemoryUsageInMB() {
        final long n = Runtime.getRuntime().totalMemory() / 2097152L;
        return "Memory: " + (n - Runtime.getRuntime().freeMemory() / 2097152L) + "M / " + n + "M / " + Runtime.getRuntime().maxMemory() / 2097152L + "M";
    }
    
    public static String getDisplayStats(final Display display) {
        final float n = Math.round(display.getFrameRate() * 100.0) / 100.0f;
        final Runtime runtime = Runtime.getRuntime();
        final long n2 = runtime.totalMemory() / 2097152L;
        final long n3 = runtime.freeMemory() / 2097152L;
        final long n4 = runtime.maxMemory() / 2097152L;
        final StringBuffer sb = new StringBuffer();
        sb.append("frame rate: ").append(n).append("fps - ");
        sb.append(display.getVisibleItemCount()).append(" items - ");
        sb.append("fonts(").append(FontLib.getCacheMissCount());
        sb.append(") colors(");
        sb.append(ColorLib.getCacheMissCount()).append(')');
        sb.append(" mem(");
        sb.append(n2 - n3).append("M / ");
        sb.append(n4).append("M)");
        sb.append(" (x:");
        sb.append(StringLib.formatNumber(display.getDisplayX(), 2));
        sb.append(", y:");
        sb.append(StringLib.formatNumber(display.getDisplayY(), 2));
        sb.append(", z:");
        sb.append(StringLib.formatNumber(display.getScale(), 5)).append(")");
        return sb.toString();
    }
    
    public static double getSize2D(final double n) {
        return Math.pow(n, PrefuseLib.SIZE_SCALE_FACTOR);
    }
    
    public static double distance(final VisualItem visualItem, final VisualItem visualItem2) {
        final double n = visualItem.getX() - visualItem2.getX();
        final double n2 = visualItem.getY() - visualItem2.getY();
        return Math.sqrt(n * n + n2 * n2);
    }
    
    public static void update(final VisualItem visualItem, final String s, final Object o) {
        visualItem.set(getStartField(s), visualItem.get(s));
        visualItem.set(s, o);
        visualItem.set(getEndField(s), o);
    }
    
    public static void updateInt(final VisualItem visualItem, final String s, final int n) {
        visualItem.setInt(getStartField(s), visualItem.getInt(s));
        visualItem.setInt(s, n);
        visualItem.setInt(getEndField(s), n);
    }
    
    public static void updateLong(final VisualItem visualItem, final String s, final long n) {
        visualItem.setLong(getStartField(s), visualItem.getLong(s));
        visualItem.setLong(s, n);
        visualItem.setLong(getEndField(s), n);
    }
    
    public static void updateFloat(final VisualItem visualItem, final String s, final float n) {
        visualItem.setFloat(getStartField(s), visualItem.getFloat(s));
        visualItem.setFloat(s, n);
        visualItem.setFloat(getEndField(s), n);
    }
    
    public static void updateDouble(final VisualItem visualItem, final String s, final double n) {
        visualItem.setDouble(getStartField(s), visualItem.getDouble(s));
        visualItem.setDouble(s, n);
        visualItem.setDouble(getEndField(s), n);
    }
    
    public static void updateBoolean(final VisualItem visualItem, final String s, final boolean b) {
        visualItem.setBoolean(getStartField(s), visualItem.getBoolean(s));
        visualItem.setBoolean(s, b);
        visualItem.setBoolean(getEndField(s), b);
    }
    
    public static void updateVisible(final VisualItem visualItem, final boolean b) {
        visualItem.setStartVisible(visualItem.isVisible());
        visualItem.setVisible(b);
        visualItem.setEndVisible(b);
    }
    
    public static void setX(final VisualItem visualItem, final VisualItem visualItem2, final double n) {
        double x = visualItem.getX();
        if (Double.isNaN(x)) {
            x = ((visualItem2 != null) ? visualItem2.getX() : n);
        }
        visualItem.setStartX(x);
        visualItem.setEndX(n);
        visualItem.setX(n);
    }
    
    public static void setY(final VisualItem visualItem, final VisualItem visualItem2, final double n) {
        double y = visualItem.getY();
        if (Double.isNaN(y)) {
            y = ((visualItem2 != null) ? visualItem2.getY() : n);
        }
        visualItem.setStartY(y);
        visualItem.setEndY(n);
        visualItem.setY(n);
    }
    
    public static boolean isChildGroup(final String s) {
        return s.indexOf(PrefuseLib.GROUP_DELIMITER) != -1;
    }
    
    public static String getParentGroup(final String s) {
        final int lastIndex = s.lastIndexOf(PrefuseLib.GROUP_DELIMITER);
        return (lastIndex < 0) ? null : s.substring(0, lastIndex);
    }
    
    public static String getChildGroup(final String s) {
        final int lastIndex = s.lastIndexOf(PrefuseLib.GROUP_DELIMITER);
        return (lastIndex < 0) ? null : s.substring(lastIndex + 1);
    }
    
    public static String getGroupName(final String s, final String s2) {
        return s + PrefuseLib.GROUP_DELIMITER + s2;
    }
    
    public static String getStartField(final String s) {
        return s + ":start";
    }
    
    public static String getEndField(final String s) {
        return s + ":end";
    }
    
    public static Schema getVisualItemSchema() {
        final Schema schema = new Schema();
        schema.addColumn(VisualItem.VALIDATED, Boolean.TYPE, Boolean.FALSE);
        schema.addColumn(VisualItem.VISIBLE, Boolean.TYPE, Boolean.TRUE);
        schema.addColumn(VisualItem.STARTVISIBLE, Boolean.TYPE, Boolean.FALSE);
        schema.addColumn(VisualItem.ENDVISIBLE, Boolean.TYPE, Boolean.TRUE);
        schema.addColumn(VisualItem.INTERACTIVE, Boolean.TYPE, Boolean.TRUE);
        schema.addColumn(VisualItem.EXPANDED, Boolean.TYPE, Boolean.TRUE);
        schema.addColumn(VisualItem.FIXED, Boolean.TYPE, Boolean.FALSE);
        schema.addColumn(VisualItem.HIGHLIGHT, Boolean.TYPE, Boolean.FALSE);
        schema.addColumn(VisualItem.HOVER, Boolean.TYPE, Boolean.FALSE);
        schema.addInterpolatedColumn(VisualItem.X, Double.TYPE);
        schema.addInterpolatedColumn(VisualItem.Y, Double.TYPE);
        schema.addColumn(VisualItem.BOUNDS, Rectangle2D.class, new Rectangle2D.Double());
        schema.addInterpolatedColumn(VisualItem.STROKECOLOR, Integer.TYPE, new Integer(ColorLib.rgba(0, 0, 0, 0)));
        schema.addInterpolatedColumn(VisualItem.FILLCOLOR, Integer.TYPE, new Integer(ColorLib.rgba(0, 0, 0, 0)));
        schema.addInterpolatedColumn(VisualItem.TEXTCOLOR, Integer.TYPE, new Integer(ColorLib.rgba(0, 0, 0, 0)));
        schema.addInterpolatedColumn(VisualItem.SIZE, Double.TYPE, new Double(1.0));
        schema.addColumn(VisualItem.SHAPE, Integer.TYPE, new Integer(0));
        schema.addColumn(VisualItem.STROKE, Stroke.class, new BasicStroke());
        schema.addInterpolatedColumn(VisualItem.FONT, Font.class, FontLib.getFont("SansSerif", 0, 10));
        schema.addColumn(VisualItem.DOI, Double.TYPE, new Double(Double.MIN_VALUE));
        return schema;
    }
    
    public static Schema getMinimalVisualSchema() {
        final Schema schema = new Schema();
        schema.addColumn(VisualItem.VALIDATED, Boolean.TYPE, Boolean.FALSE);
        schema.addColumn(VisualItem.VISIBLE, Boolean.TYPE, Boolean.TRUE);
        schema.addColumn(VisualItem.STARTVISIBLE, Boolean.TYPE, Boolean.FALSE);
        schema.addColumn(VisualItem.ENDVISIBLE, Boolean.TYPE, Boolean.TRUE);
        schema.addColumn(VisualItem.INTERACTIVE, Boolean.TYPE, Boolean.TRUE);
        schema.addColumn(VisualItem.BOUNDS, Rectangle2D.class, new Rectangle2D.Double());
        return schema;
    }
    
    public static Schema getAxisLabelSchema() {
        final Schema visualItemSchema = getVisualItemSchema();
        visualItemSchema.setDefault(VisualItem.STARTVISIBLE, Boolean.FALSE);
        visualItemSchema.setInterpolatedDefault(VisualItem.STROKECOLOR, new Integer(ColorLib.gray(230)));
        visualItemSchema.setInterpolatedDefault(VisualItem.TEXTCOLOR, new Integer(ColorLib.gray(150)));
        final Double n = new Double(Double.NaN);
        visualItemSchema.addInterpolatedColumn(VisualItem.X2, Double.TYPE);
        visualItemSchema.addInterpolatedColumn(VisualItem.Y2, Double.TYPE);
        visualItemSchema.addColumn(VisualItem.LABEL, String.class);
        visualItemSchema.addColumn(VisualItem.VALUE, Double.TYPE, n);
        return visualItemSchema;
    }
    
    static {
        GROUP_DELIMITER = PrefuseConfig.get("data.delimiter");
        SIZE_SCALE_FACTOR = PrefuseConfig.getDouble("size.scale2D");
        FIELD_PREFIX = PrefuseConfig.get("data.visual.fieldPrefix");
    }
}
