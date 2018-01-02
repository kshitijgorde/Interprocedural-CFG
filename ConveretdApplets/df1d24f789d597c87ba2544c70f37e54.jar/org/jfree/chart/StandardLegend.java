// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import org.jfree.util.Log;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import java.awt.font.LineMetrics;
import java.awt.FontMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.Graphics;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import java.util.Iterator;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.List;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.LegendChangeEvent;
import java.awt.Color;
import java.awt.BasicStroke;
import org.jfree.util.LogContext;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import org.jfree.ui.Spacer;
import java.io.Serializable;

public class StandardLegend extends Legend implements Serializable
{
    public static final Spacer DEFAULT_OUTER_GAP;
    public static final Spacer DEFAULT_INNER_GAP;
    public static final Stroke DEFAULT_OUTLINE_STROKE;
    public static final Paint DEFAULT_OUTLINE_PAINT;
    public static final Paint DEFAULT_BACKGROUND_PAINT;
    public static final Font DEFAULT_TITLE_FONT;
    public static final Font DEFAULT_ITEM_FONT;
    public static final double NO_PREFERRED_WIDTH = Double.MAX_VALUE;
    private static final String UNEXPECTED_LEGEND_ANCHOR = "Unexpected legend anchor";
    private Spacer outerGap;
    private transient Stroke outlineStroke;
    private transient Paint outlinePaint;
    private transient Paint backgroundPaint;
    private Spacer innerGap;
    private String title;
    private Font titleFont;
    private Font itemFont;
    private transient Paint itemPaint;
    private boolean outlineShapes;
    private transient Stroke shapeOutlineStroke;
    private transient Paint shapeOutlinePaint;
    private boolean displaySeriesShapes;
    private double shapeScaleX;
    private double shapeScaleY;
    private boolean displaySeriesLines;
    private LegendRenderingOrder renderingOrder;
    private int boundingBoxArcWidth;
    private int boundingBoxArcHeight;
    private double preferredWidth;
    private static final LogContext LOGGER;
    static /* synthetic */ Class class$org$jfree$chart$StandardLegend;
    
    public StandardLegend() {
        this.shapeOutlineStroke = new BasicStroke(0.5f);
        this.shapeOutlinePaint = Color.lightGray;
        this.shapeScaleX = 1.0;
        this.shapeScaleY = 1.0;
        this.renderingOrder = LegendRenderingOrder.STANDARD;
        this.boundingBoxArcWidth = 0;
        this.boundingBoxArcHeight = 0;
        this.preferredWidth = Double.MAX_VALUE;
        this.outerGap = StandardLegend.DEFAULT_OUTER_GAP;
        this.innerGap = StandardLegend.DEFAULT_INNER_GAP;
        this.backgroundPaint = StandardLegend.DEFAULT_BACKGROUND_PAINT;
        this.outlineStroke = StandardLegend.DEFAULT_OUTLINE_STROKE;
        this.outlinePaint = StandardLegend.DEFAULT_OUTLINE_PAINT;
        this.title = null;
        this.titleFont = StandardLegend.DEFAULT_TITLE_FONT;
        this.itemFont = StandardLegend.DEFAULT_ITEM_FONT;
        this.itemPaint = Color.black;
        this.displaySeriesShapes = false;
        this.displaySeriesLines = false;
    }
    
    public StandardLegend(final JFreeChart chart) {
        this();
    }
    
    public Spacer getOuterGap() {
        return this.outerGap;
    }
    
    public void setOuterGap(final Spacer outerGap) {
        if (outerGap == null) {
            throw new NullPointerException("Null 'outerGap' argument.");
        }
        this.outerGap = outerGap;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Spacer getInnerGap() {
        return this.innerGap;
    }
    
    public void setInnerGap(final Spacer innerGap) {
        if (innerGap == null) {
            throw new NullPointerException("Null 'innerGap' argument.");
        }
        this.innerGap = innerGap;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    public void setBackgroundPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.backgroundPaint = paint;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    public void setOutlineStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new NullPointerException("Null 'stroke' argument.");
        }
        this.outlineStroke = stroke;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    public void setOutlinePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.outlinePaint = paint;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Font getTitleFont() {
        return this.titleFont;
    }
    
    public void setTitleFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.titleFont = font;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Font getItemFont() {
        return this.itemFont;
    }
    
    public void setItemFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.itemFont = font;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Paint getItemPaint() {
        return this.itemPaint;
    }
    
    public void setItemPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.itemPaint = paint;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public boolean getOutlineShapes() {
        return this.outlineShapes;
    }
    
    public void setOutlineShapes(final boolean flag) {
        this.outlineShapes = flag;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Stroke getShapeOutlineStroke() {
        return this.shapeOutlineStroke;
    }
    
    public void setShapeOutlineStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new NullPointerException("Null 'stroke' argument");
        }
        this.shapeOutlineStroke = stroke;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Paint getShapeOutlinePaint() {
        return this.shapeOutlinePaint;
    }
    
    public void setShapeOutlinePaint(final Paint paint) {
        this.shapeOutlinePaint = paint;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public void setDisplaySeriesShapes(final boolean flag) {
        this.displaySeriesShapes = flag;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public boolean getDisplaySeriesShapes() {
        return this.displaySeriesShapes;
    }
    
    public double getShapeScaleX() {
        return this.shapeScaleX;
    }
    
    public void setShapeScaleX(final double factor) {
        this.shapeScaleX = factor;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public double getShapeScaleY() {
        return this.shapeScaleY;
    }
    
    public void setShapeScaleY(final double factor) {
        this.shapeScaleY = factor;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public void setDisplaySeriesLines(final boolean flag) {
        this.displaySeriesLines = flag;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public boolean getDisplaySeriesLines() {
        return this.displaySeriesLines;
    }
    
    public LegendRenderingOrder getRenderingOrder() {
        return this.renderingOrder;
    }
    
    public void setRenderingOrder(final LegendRenderingOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Null 'order' argument.");
        }
        this.renderingOrder = order;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public int getBoundingBoxArcWidth() {
        return this.boundingBoxArcWidth;
    }
    
    public void setBoundingBoxArcWidth(final int arcWidth) {
        this.boundingBoxArcWidth = arcWidth;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public int getBoundingBoxArcHeight() {
        return this.boundingBoxArcHeight;
    }
    
    public void setBoundingBoxArcHeight(final int arcHeight) {
        this.boundingBoxArcHeight = arcHeight;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public double getPreferredWidth() {
        return this.preferredWidth;
    }
    
    public void setPreferredWidth(final double width) {
        this.preferredWidth = width;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Rectangle2D draw(final Graphics2D g2, final Rectangle2D available, final ChartRenderingInfo info) {
        return this.draw(g2, available, (this.getAnchor() & 0x1) != 0x0, (this.getAnchor() & 0x2) != 0x0, info);
    }
    
    protected Rectangle2D draw(final Graphics2D g2, final Rectangle2D available, final boolean horizontal, final boolean inverted, final ChartRenderingInfo info) {
        final LegendItemCollection legendItems = this.getChart().getPlot().getLegendItems();
        if (legendItems == null || legendItems.getItemCount() == 0) {
            return available;
        }
        DrawableLegendItem legendTitle = null;
        LegendItem titleItem = null;
        if (this.title != null && !this.title.equals("")) {
            titleItem = new LegendItem(this.title, this.title, null, true, Color.black, new BasicStroke(1.0f), Color.black, new BasicStroke(1.0f));
        }
        final double availableWidth = available.getWidth();
        final List items = new ArrayList();
        RectangularShape legendArea;
        Point2D translation;
        if (horizontal) {
            final double xstart = available.getX() + this.getOuterGap().getLeftSpace(availableWidth);
            final double xlimit = available.getMaxX() - this.getOuterGap().getRightSpace(availableWidth);
            double maxRowWidth = 0.0;
            double xoffset = 0.0;
            double rowHeight = 0.0;
            double totalHeight = 0.0;
            boolean wrappingAllowed = true;
            if (titleItem != null) {
                g2.setFont(this.getTitleFont());
                legendTitle = this.createDrawableLegendItem(g2, titleItem, xoffset, totalHeight);
                rowHeight = Math.max(0.0, legendTitle.getHeight());
                xoffset += legendTitle.getWidth();
            }
            g2.setFont(this.itemFont);
            for (int i = 0; i < legendItems.getItemCount(); ++i) {
                DrawableLegendItem item;
                if (this.renderingOrder == LegendRenderingOrder.STANDARD) {
                    item = this.createDrawableLegendItem(g2, legendItems.get(i), xoffset, totalHeight);
                }
                else if (this.renderingOrder == LegendRenderingOrder.REVERSE) {
                    item = this.createDrawableLegendItem(g2, legendItems.get(legendItems.getItemCount() - i - 1), xoffset, totalHeight);
                }
                else {
                    item = null;
                }
                if (item.getMaxX() + xstart > xlimit && wrappingAllowed) {
                    maxRowWidth = Math.max(maxRowWidth, xoffset);
                    xoffset = 0.0;
                    totalHeight += rowHeight;
                    --i;
                    wrappingAllowed = false;
                }
                else {
                    rowHeight = Math.max(rowHeight, item.getHeight());
                    xoffset += item.getWidth();
                    wrappingAllowed = true;
                    items.add(item);
                }
            }
            maxRowWidth = Math.max(maxRowWidth, xoffset);
            totalHeight += rowHeight;
            legendArea = new RoundRectangle2D.Double(0.0, 0.0, maxRowWidth, totalHeight, this.boundingBoxArcWidth, this.boundingBoxArcHeight);
            translation = this.createTranslationPointForHorizontalDraw(available, inverted, maxRowWidth, totalHeight);
        }
        else {
            double totalHeight2 = 0.0;
            double maxWidth = (this.preferredWidth == Double.MAX_VALUE) ? 0.0 : this.preferredWidth;
            if (titleItem != null) {
                g2.setFont(this.getTitleFont());
                legendTitle = this.createDrawableLegendItem(g2, titleItem, 0.0, totalHeight2);
                totalHeight2 += legendTitle.getHeight();
                maxWidth = Math.max(maxWidth, legendTitle.getWidth());
            }
            g2.setFont(this.itemFont);
            for (int legendItemsLength = legendItems.getItemCount(), j = 0; j < legendItemsLength; ++j) {
                List drawableParts;
                if (this.renderingOrder == LegendRenderingOrder.STANDARD) {
                    drawableParts = this.createAllDrawableLinesForItem(g2, legendItems.get(j), 0.0, totalHeight2, maxWidth);
                }
                else if (this.renderingOrder == LegendRenderingOrder.REVERSE) {
                    drawableParts = this.createAllDrawableLinesForItem(g2, legendItems.get(legendItemsLength - j - 1), 0.0, totalHeight2, maxWidth);
                }
                else {
                    drawableParts = null;
                }
                for (final DrawableLegendItem item2 : drawableParts) {
                    totalHeight2 += item2.getHeight();
                    maxWidth = Math.max(maxWidth, item2.getWidth());
                    items.add(item2);
                }
            }
            legendArea = new RoundRectangle2D.Float(0.0f, 0.0f, (float)maxWidth, (float)totalHeight2, this.boundingBoxArcWidth, this.boundingBoxArcHeight);
            translation = this.createTranslationPointForVerticalDraw(available, inverted, totalHeight2, maxWidth);
        }
        g2.translate(translation.getX(), translation.getY());
        StandardLegend.LOGGER.debug("legendArea = " + legendArea.getWidth() + ", " + legendArea.getHeight());
        this.drawLegendBox(g2, legendArea);
        this.drawLegendTitle(g2, legendTitle);
        this.drawSeriesElements(g2, items, translation, info);
        g2.translate(-translation.getX(), -translation.getY());
        return this.calcRemainingDrawingArea(available, horizontal, inverted, legendArea);
    }
    
    private Point2D createTranslationPointForHorizontalDraw(final Rectangle2D available, final boolean inverted, final double maxRowWidth, final double totalHeight) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2         /* inverted */
        //     1: ifeq            26
        //     4: aload_1         /* available */
        //     5: invokevirtual   java/awt/geom/Rectangle2D.getMaxY:()D
        //     8: dload           totalHeight
        //    10: dsub           
        //    11: aload_0         /* this */
        //    12: invokevirtual   org/jfree/chart/StandardLegend.getOuterGap:()Lorg/jfree/ui/Spacer;
        //    15: aload_1         /* available */
        //    16: invokevirtual   java/awt/geom/Rectangle2D.getHeight:()D
        //    19: invokevirtual   org/jfree/ui/Spacer.getBottomSpace:(D)D
        //    22: dsub           
        //    23: goto            42
        //    26: aload_1         /* available */
        //    27: invokevirtual   java/awt/geom/Rectangle2D.getY:()D
        //    30: aload_0         /* this */
        //    31: invokevirtual   org/jfree/chart/StandardLegend.getOuterGap:()Lorg/jfree/ui/Spacer;
        //    34: aload_1         /* available */
        //    35: invokevirtual   java/awt/geom/Rectangle2D.getHeight:()D
        //    38: invokevirtual   org/jfree/ui/Spacer.getTopSpace:(D)D
        //    41: dadd           
        //    42: dstore          yloc
        //    44: aload_0         /* this */
        //    45: invokevirtual   org/jfree/chart/StandardLegend.isAnchoredToLeft:()Z
        //    48: ifeq            75
        //    51: aload_1         /* available */
        //    52: invokevirtual   java/awt/geom/Rectangle2D.getX:()D
        //    55: aload_0         /* this */
        //    56: invokevirtual   org/jfree/chart/StandardLegend.getChart:()Lorg/jfree/chart/JFreeChart;
        //    59: invokevirtual   org/jfree/chart/JFreeChart.getPlot:()Lorg/jfree/chart/plot/Plot;
        //    62: invokevirtual   org/jfree/chart/plot/Plot.getInsets:()Ljava/awt/Insets;
        //    65: getfield        java/awt/Insets.left:I
        //    68: i2d            
        //    69: dadd           
        //    70: dstore          xloc
        //    72: goto            154
        //    75: aload_0         /* this */
        //    76: invokevirtual   org/jfree/chart/StandardLegend.isAnchoredToCenter:()Z
        //    79: ifeq            106
        //    82: aload_1         /* available */
        //    83: invokevirtual   java/awt/geom/Rectangle2D.getX:()D
        //    86: aload_1         /* available */
        //    87: invokevirtual   java/awt/geom/Rectangle2D.getWidth:()D
        //    90: ldc2_w          2.0
        //    93: ddiv           
        //    94: dadd           
        //    95: dload_3         /* maxRowWidth */
        //    96: ldc2_w          2.0
        //    99: ddiv           
        //   100: dsub           
        //   101: dstore          xloc
        //   103: goto            154
        //   106: aload_0         /* this */
        //   107: invokevirtual   org/jfree/chart/StandardLegend.isAnchoredToRight:()Z
        //   110: ifeq            144
        //   113: aload_1         /* available */
        //   114: invokevirtual   java/awt/geom/Rectangle2D.getX:()D
        //   117: aload_1         /* available */
        //   118: invokevirtual   java/awt/geom/Rectangle2D.getWidth:()D
        //   121: dadd           
        //   122: dload_3         /* maxRowWidth */
        //   123: dsub           
        //   124: aload_0         /* this */
        //   125: invokevirtual   org/jfree/chart/StandardLegend.getChart:()Lorg/jfree/chart/JFreeChart;
        //   128: invokevirtual   org/jfree/chart/JFreeChart.getPlot:()Lorg/jfree/chart/plot/Plot;
        //   131: invokevirtual   org/jfree/chart/plot/Plot.getInsets:()Ljava/awt/Insets;
        //   134: getfield        java/awt/Insets.left:I
        //   137: i2d            
        //   138: dsub           
        //   139: dstore          xloc
        //   141: goto            154
        //   144: new             Ljava/lang/IllegalStateException;
        //   147: dup            
        //   148: ldc             "Unexpected legend anchor"
        //   150: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   153: athrow         
        //   154: new             Ljava/awt/geom/Point2D$Double;
        //   157: dup            
        //   158: dload           9
        //   160: dload           yloc
        //   162: invokespecial   java/awt/geom/Point2D$Double.<init>:(DD)V
        //   165: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  --------------------------------
        //  72     3       9     xloc         D
        //  103    3       9     xloc         D
        //  141    3       9     xloc         D
        //  0      166     0     this         Lorg/jfree/chart/StandardLegend;
        //  0      166     1     available    Ljava/awt/geom/Rectangle2D;
        //  0      166     2     inverted     Z
        //  0      166     3     maxRowWidth  D
        //  0      166     5     totalHeight  D
        //  44     122     7     yloc         D
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private Point2D createTranslationPointForVerticalDraw(final Rectangle2D available, final boolean inverted, final double totalHeight, final double maxWidth) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2         /* inverted */
        //     1: ifeq            26
        //     4: aload_1         /* available */
        //     5: invokevirtual   java/awt/geom/Rectangle2D.getMaxX:()D
        //     8: dload           maxWidth
        //    10: dsub           
        //    11: aload_0         /* this */
        //    12: invokevirtual   org/jfree/chart/StandardLegend.getOuterGap:()Lorg/jfree/ui/Spacer;
        //    15: aload_1         /* available */
        //    16: invokevirtual   java/awt/geom/Rectangle2D.getWidth:()D
        //    19: invokevirtual   org/jfree/ui/Spacer.getRightSpace:(D)D
        //    22: dsub           
        //    23: goto            42
        //    26: aload_1         /* available */
        //    27: invokevirtual   java/awt/geom/Rectangle2D.getX:()D
        //    30: aload_0         /* this */
        //    31: invokevirtual   org/jfree/chart/StandardLegend.getOuterGap:()Lorg/jfree/ui/Spacer;
        //    34: aload_1         /* available */
        //    35: invokevirtual   java/awt/geom/Rectangle2D.getWidth:()D
        //    38: invokevirtual   org/jfree/ui/Spacer.getLeftSpace:(D)D
        //    41: dadd           
        //    42: dstore          xloc
        //    44: aload_0         /* this */
        //    45: invokevirtual   org/jfree/chart/StandardLegend.isAnchoredToTop:()Z
        //    48: ifeq            75
        //    51: aload_1         /* available */
        //    52: invokevirtual   java/awt/geom/Rectangle2D.getY:()D
        //    55: aload_0         /* this */
        //    56: invokevirtual   org/jfree/chart/StandardLegend.getChart:()Lorg/jfree/chart/JFreeChart;
        //    59: invokevirtual   org/jfree/chart/JFreeChart.getPlot:()Lorg/jfree/chart/plot/Plot;
        //    62: invokevirtual   org/jfree/chart/plot/Plot.getInsets:()Ljava/awt/Insets;
        //    65: getfield        java/awt/Insets.top:I
        //    68: i2d            
        //    69: dadd           
        //    70: dstore          yloc
        //    72: goto            154
        //    75: aload_0         /* this */
        //    76: invokevirtual   org/jfree/chart/StandardLegend.isAnchoredToMiddle:()Z
        //    79: ifeq            106
        //    82: aload_1         /* available */
        //    83: invokevirtual   java/awt/geom/Rectangle2D.getY:()D
        //    86: aload_1         /* available */
        //    87: invokevirtual   java/awt/geom/Rectangle2D.getHeight:()D
        //    90: ldc2_w          2.0
        //    93: ddiv           
        //    94: dadd           
        //    95: dload_3         /* totalHeight */
        //    96: ldc2_w          2.0
        //    99: ddiv           
        //   100: dsub           
        //   101: dstore          yloc
        //   103: goto            154
        //   106: aload_0         /* this */
        //   107: invokevirtual   org/jfree/chart/StandardLegend.isAnchoredToBottom:()Z
        //   110: ifeq            144
        //   113: aload_1         /* available */
        //   114: invokevirtual   java/awt/geom/Rectangle2D.getY:()D
        //   117: aload_1         /* available */
        //   118: invokevirtual   java/awt/geom/Rectangle2D.getHeight:()D
        //   121: dadd           
        //   122: aload_0         /* this */
        //   123: invokevirtual   org/jfree/chart/StandardLegend.getChart:()Lorg/jfree/chart/JFreeChart;
        //   126: invokevirtual   org/jfree/chart/JFreeChart.getPlot:()Lorg/jfree/chart/plot/Plot;
        //   129: invokevirtual   org/jfree/chart/plot/Plot.getInsets:()Ljava/awt/Insets;
        //   132: getfield        java/awt/Insets.bottom:I
        //   135: i2d            
        //   136: dsub           
        //   137: dload_3         /* totalHeight */
        //   138: dsub           
        //   139: dstore          yloc
        //   141: goto            154
        //   144: new             Ljava/lang/IllegalStateException;
        //   147: dup            
        //   148: ldc             "Unexpected legend anchor"
        //   150: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   153: athrow         
        //   154: new             Ljava/awt/geom/Point2D$Double;
        //   157: dup            
        //   158: dload           xloc
        //   160: dload           9
        //   162: invokespecial   java/awt/geom/Point2D$Double.<init>:(DD)V
        //   165: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  --------------------------------
        //  72     3       9     yloc         D
        //  103    3       9     yloc         D
        //  141    3       9     yloc         D
        //  0      166     0     this         Lorg/jfree/chart/StandardLegend;
        //  0      166     1     available    Ljava/awt/geom/Rectangle2D;
        //  0      166     2     inverted     Z
        //  0      166     3     totalHeight  D
        //  0      166     5     maxWidth     D
        //  44     122     7     xloc         D
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void drawLegendTitle(final Graphics2D g2, final DrawableLegendItem legendTitle) {
        if (legendTitle != null) {
            g2.setPaint(legendTitle.getItem().getPaint());
            g2.setPaint(this.itemPaint);
            g2.setFont(this.getTitleFont());
            RefineryUtilities.drawAlignedString(legendTitle.getItem().getLabel(), g2, (float)legendTitle.getLabelPosition().getX(), (float)legendTitle.getLabelPosition().getY(), TextAnchor.CENTER_LEFT);
            StandardLegend.LOGGER.debug("Title x = " + legendTitle.getLabelPosition().getX());
            StandardLegend.LOGGER.debug("Title y = " + legendTitle.getLabelPosition().getY());
        }
    }
    
    private void drawLegendBox(final Graphics2D g2, final RectangularShape legendArea) {
        g2.setPaint(this.backgroundPaint);
        g2.fill(legendArea);
        g2.setPaint(this.outlinePaint);
        g2.setStroke(this.outlineStroke);
        g2.draw(legendArea);
    }
    
    private void drawSeriesElements(final Graphics2D g2, final List items, final Point2D translation, final ChartRenderingInfo info) {
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getEntityCollection();
        }
        for (int i = 0; i < items.size(); ++i) {
            final DrawableLegendItem item = items.get(i);
            g2.setPaint(item.getItem().getPaint());
            final Shape keyBox = item.getMarker();
            if (this.displaySeriesLines) {
                g2.setStroke(item.getLineStroke());
                g2.draw(item.getLine());
                if (this.displaySeriesShapes) {
                    if (item.isMarkerFilled()) {
                        g2.fill(keyBox);
                    }
                    else {
                        g2.draw(keyBox);
                    }
                }
            }
            else if (item.isMarkerFilled()) {
                g2.fill(keyBox);
            }
            else {
                g2.draw(keyBox);
            }
            if (this.getOutlineShapes()) {
                g2.setPaint(this.shapeOutlinePaint);
                g2.setStroke(this.shapeOutlineStroke);
                g2.draw(keyBox);
            }
            g2.setPaint(this.itemPaint);
            g2.setFont(this.itemFont);
            RefineryUtilities.drawAlignedString(item.getItem().getLabel(), g2, (float)item.getLabelPosition().getX(), (float)item.getLabelPosition().getY(), TextAnchor.CENTER_LEFT);
            StandardLegend.LOGGER.debug("Item x = " + item.getLabelPosition().getX());
            StandardLegend.LOGGER.debug("Item y = " + item.getLabelPosition().getY());
            if (entities != null) {
                final Rectangle2D area = new Rectangle2D.Double(translation.getX() + item.getX(), translation.getY() + item.getY(), item.getWidth(), item.getHeight());
                final LegendItemEntity entity = new LegendItemEntity(area);
                entity.setSeriesIndex(i);
                entities.addEntity(entity);
            }
        }
    }
    
    private Rectangle2D calcRemainingDrawingArea(final Rectangle2D available, final boolean horizontal, final boolean inverted, final RectangularShape legendArea) {
        if (horizontal) {
            final double yy = available.getY();
            final double yloc = inverted ? yy : (yy + legendArea.getHeight() + this.getOuterGap().getBottomSpace(available.getHeight()));
            return new Rectangle2D.Double(available.getX(), yloc, available.getWidth(), available.getHeight() - legendArea.getHeight() - this.getOuterGap().getTopSpace(available.getHeight()) - this.getOuterGap().getBottomSpace(available.getHeight()));
        }
        final double xloc = inverted ? available.getX() : (available.getX() + legendArea.getWidth() + this.getOuterGap().getLeftSpace(available.getWidth()) + this.getOuterGap().getRightSpace(available.getWidth()));
        return new Rectangle2D.Double(xloc, available.getY(), available.getWidth() - legendArea.getWidth() - this.getOuterGap().getLeftSpace(available.getWidth()) - this.getOuterGap().getRightSpace(available.getWidth()), available.getHeight());
    }
    
    private List createAllDrawableLinesForItem(final Graphics2D g2, final LegendItem legendItem, final double x, final double y, final double wordWrapWidth) {
        final List drawableParts = new ArrayList();
        DrawableLegendItem line = this.createDrawableLegendItem(g2, legendItem, x, y);
        if (line.getWidth() < wordWrapWidth) {
            drawableParts.add(line);
            return drawableParts;
        }
        boolean firstLine = true;
        double totalHeight = y;
        String prefix = "";
        String suffix = legendItem.getLabel().trim();
        LegendItem tmpItem = new LegendItem(prefix.trim(), legendItem.getLabel(), legendItem.getShape(), legendItem.isShapeFilled(), legendItem.getPaint(), legendItem.getStroke(), legendItem.getOutlinePaint(), legendItem.getOutlineStroke());
        line = this.createDrawableLegendItem(g2, tmpItem, x, totalHeight);
        DrawableLegendItem goodLine = null;
        do {
            final String prevSuffix = suffix;
            final int spacePos = suffix.indexOf(" ");
            if (spacePos < 0) {
                prefix += suffix;
                suffix = "";
            }
            else {
                prefix += suffix.substring(0, spacePos + 1);
                suffix = suffix.substring(spacePos + 1);
            }
            final Paint background = this.getBackgroundPaint();
            tmpItem = new LegendItem(prefix.trim(), legendItem.getLabel(), legendItem.getShape(), legendItem.isShapeFilled(), firstLine ? legendItem.getPaint() : background, legendItem.getStroke(), firstLine ? legendItem.getOutlinePaint() : background, legendItem.getOutlineStroke());
            line = this.createDrawableLegendItem(g2, tmpItem, x, totalHeight);
            if (line.getWidth() < wordWrapWidth) {
                goodLine = line;
            }
            else {
                if (goodLine == null) {
                    drawableParts.add(line);
                    totalHeight += line.getHeight();
                }
                else {
                    drawableParts.add(goodLine);
                    totalHeight += goodLine.getHeight();
                    suffix = prevSuffix;
                }
                firstLine = false;
                prefix = "";
                suffix = suffix.trim();
                line = null;
                goodLine = null;
            }
        } while (!suffix.equals(""));
        if (line != null) {
            drawableParts.add(line);
        }
        return drawableParts;
    }
    
    private DrawableLegendItem createDrawableLegendItem(final Graphics2D graphics, final LegendItem legendItem, final double x, final double y) {
        StandardLegend.LOGGER.debug("In createDrawableLegendItem(x = " + x + ", y = " + y);
        final int insideGap = 2;
        final FontMetrics fm = graphics.getFontMetrics();
        final LineMetrics lm = fm.getLineMetrics(legendItem.getLabel(), graphics);
        final float textAscent = lm.getAscent();
        final float lineHeight = textAscent + lm.getDescent() + lm.getLeading();
        final DrawableLegendItem item = new DrawableLegendItem(legendItem);
        final float xLabelLoc = (float)(x + insideGap + 1.15f * lineHeight);
        final float yLabelLoc = (float)(y + insideGap + 0.5f * lineHeight);
        item.setLabelPosition(new Point2D.Float(xLabelLoc, yLabelLoc));
        final float width = (float)(item.getLabelPosition().getX() - x + fm.stringWidth(legendItem.getLabel()) + 0.5 * textAscent);
        final float height = 2 * insideGap + lineHeight;
        item.setBounds(x, y, width, height);
        final float boxDim = lineHeight * 0.7f;
        final float xloc = (float)(x + insideGap + 0.15f * lineHeight);
        final float yloc = (float)(y + insideGap + 0.15f * lineHeight);
        if (this.displaySeriesLines) {
            final Line2D line = new Line2D.Float(xloc, yloc + boxDim / 2.0f, xloc + boxDim * 3.0f, yloc + boxDim / 2.0f);
            item.setLineStroke(legendItem.getStroke());
            item.setLine(line);
            item.setBounds(item.getX(), item.getY(), item.getWidth() + boxDim * 2.0f, item.getHeight());
            item.setLabelPosition(new Point2D.Float(xLabelLoc + boxDim * 2.0f, yLabelLoc));
            if (this.displaySeriesShapes) {
                final Shape marker = legendItem.getShape();
                final AffineTransform t1 = AffineTransform.getScaleInstance(this.shapeScaleX, this.shapeScaleY);
                final Shape s1 = t1.createTransformedShape(marker);
                final AffineTransform transformer = AffineTransform.getTranslateInstance(xloc + boxDim * 1.5, yloc + boxDim / 2.0f);
                final Shape s2 = transformer.createTransformedShape(s1);
                item.setMarker(s2);
            }
        }
        else if (this.displaySeriesShapes) {
            final Shape marker2 = legendItem.getShape();
            final AffineTransform t2 = AffineTransform.getScaleInstance(this.shapeScaleX, this.shapeScaleY);
            final Shape s3 = t2.createTransformedShape(marker2);
            final AffineTransform transformer2 = AffineTransform.getTranslateInstance(xloc + boxDim / 2.0f, yloc + boxDim / 2.0f);
            final Shape s4 = transformer2.createTransformedShape(s3);
            item.setMarker(s4);
        }
        else {
            item.setMarker(new Rectangle2D.Float(xloc, yloc, boxDim, boxDim));
        }
        item.setMarkerFilled(legendItem.isShapeFilled());
        return item;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof StandardLegend) {
            final StandardLegend l = (StandardLegend)obj;
            if (super.equals(obj)) {
                if (!ObjectUtils.equal(this.outerGap, l.outerGap)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.outlineStroke, l.outlineStroke)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.outlinePaint, l.outlinePaint)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.backgroundPaint, l.backgroundPaint)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.innerGap, l.innerGap)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.title, l.title)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.titleFont, l.titleFont)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.itemFont, l.itemFont)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.itemPaint, l.itemPaint)) {
                    return false;
                }
                if (this.outlineShapes != l.outlineShapes) {
                    return false;
                }
                if (!ObjectUtils.equal(this.shapeOutlineStroke, l.shapeOutlineStroke)) {
                    return false;
                }
                if (!ObjectUtils.equal(this.shapeOutlinePaint, l.shapeOutlinePaint)) {
                    return false;
                }
                if (this.displaySeriesShapes == l.displaySeriesShapes) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writeStroke(this.outlineStroke, stream);
        SerialUtilities.writePaint(this.outlinePaint, stream);
        SerialUtilities.writePaint(this.backgroundPaint, stream);
        SerialUtilities.writePaint(this.itemPaint, stream);
        SerialUtilities.writeStroke(this.shapeOutlineStroke, stream);
        SerialUtilities.writePaint(this.shapeOutlinePaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.outlineStroke = SerialUtilities.readStroke(stream);
        this.outlinePaint = SerialUtilities.readPaint(stream);
        this.backgroundPaint = SerialUtilities.readPaint(stream);
        this.itemPaint = SerialUtilities.readPaint(stream);
        this.shapeOutlineStroke = SerialUtilities.readStroke(stream);
        this.shapeOutlinePaint = SerialUtilities.readPaint(stream);
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
        DEFAULT_OUTER_GAP = new Spacer(1, 3.0, 3.0, 3.0, 3.0);
        DEFAULT_INNER_GAP = new Spacer(1, 2.0, 2.0, 2.0, 2.0);
        DEFAULT_OUTLINE_STROKE = new BasicStroke();
        DEFAULT_OUTLINE_PAINT = Color.gray;
        DEFAULT_BACKGROUND_PAINT = Color.white;
        DEFAULT_TITLE_FONT = new Font("SansSerif", 1, 11);
        DEFAULT_ITEM_FONT = new Font("SansSerif", 0, 10);
        LOGGER = Log.createContext((StandardLegend.class$org$jfree$chart$StandardLegend == null) ? (StandardLegend.class$org$jfree$chart$StandardLegend = class$("org.jfree.chart.StandardLegend")) : StandardLegend.class$org$jfree$chart$StandardLegend);
    }
}
