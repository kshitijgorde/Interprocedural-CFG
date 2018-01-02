// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.HighLowDataset;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.XYPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.RendererChangeEvent;
import java.awt.Color;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.labels.HighLowItemLabelGenerator;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class CandlestickRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    public static final int WIDTHMETHOD_AVERAGE = 0;
    public static final int WIDTHMETHOD_SMALLEST = 1;
    public static final int WIDTHMETHOD_INTERVALDATA = 2;
    private int autoWidthMethod;
    private double autoWidthFactor;
    private double autoWidthGap;
    private double candleWidth;
    private double maxCandleWidthInMilliseconds;
    private double maxCandleWidth;
    private transient Paint upPaint;
    private transient Paint downPaint;
    private boolean drawVolume;
    private transient double maxVolume;
    
    public CandlestickRenderer() {
        this(-1.0);
    }
    
    public CandlestickRenderer(final double candleWidth) {
        this(candleWidth, true, new HighLowItemLabelGenerator());
    }
    
    public CandlestickRenderer(final double candleWidth, final boolean drawVolume, final XYToolTipGenerator toolTipGenerator) {
        this.autoWidthMethod = 0;
        this.autoWidthFactor = 0.6428571428571429;
        this.autoWidthGap = 0.0;
        this.maxCandleWidthInMilliseconds = 7.2E7;
        this.setToolTipGenerator(toolTipGenerator);
        this.candleWidth = candleWidth;
        this.drawVolume = drawVolume;
        this.upPaint = Color.green;
        this.downPaint = Color.red;
    }
    
    public double getCandleWidth() {
        return this.candleWidth;
    }
    
    public void setCandleWidth(final double width) {
        if (width != this.candleWidth) {
            this.candleWidth = width;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public double getMaxCandleWidthInMilliseconds() {
        return this.maxCandleWidthInMilliseconds;
    }
    
    public void setMaxCandleWidthInMilliseconds(final double millis) {
        this.maxCandleWidthInMilliseconds = millis;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public int getAutoWidthMethod() {
        return this.autoWidthMethod;
    }
    
    public void setAutoWidthMethod(final int autoWidthMethod) {
        if (this.autoWidthMethod != autoWidthMethod) {
            this.autoWidthMethod = autoWidthMethod;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public double getAutoWidthFactor() {
        return this.autoWidthFactor;
    }
    
    public void setAutoWidthFactor(final double autoWidthFactor) {
        if (this.autoWidthFactor != autoWidthFactor) {
            this.autoWidthFactor = autoWidthFactor;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public double getAutoWidthGap() {
        return this.autoWidthGap;
    }
    
    public void setAutoWidthGap(final double autoWidthGap) {
        if (this.autoWidthGap != autoWidthGap) {
            this.autoWidthGap = autoWidthGap;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public Paint getUpPaint() {
        return this.upPaint;
    }
    
    public void setUpPaint(final Paint paint) {
        this.upPaint = paint;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public Paint getDownPaint() {
        return this.downPaint;
    }
    
    public void setDownPaint(final Paint paint) {
        this.downPaint = paint;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean drawVolume() {
        return this.drawVolume;
    }
    
    public void setDrawVolume(final boolean flag) {
        if (this.drawVolume != flag) {
            this.drawVolume = flag;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset dataset, final PlotRenderingInfo info) {
        final ValueAxis axis = plot.getDomainAxis();
        final double x1 = axis.getLowerBound();
        final double x2 = x1 + this.maxCandleWidthInMilliseconds;
        final RectangleEdge edge = plot.getDomainAxisEdge();
        final double xx1 = axis.valueToJava2D(x1, dataArea, edge);
        final double xx2 = axis.valueToJava2D(x2, dataArea, edge);
        this.maxCandleWidth = Math.abs(xx2 - xx1);
        if (this.drawVolume) {
            final HighLowDataset highLowDataset = (HighLowDataset)dataset;
            this.maxVolume = 0.0;
            for (int series = 0; series < highLowDataset.getSeriesCount(); ++series) {
                for (int item = 0; item < highLowDataset.getItemCount(series); ++item) {
                    final double volume = highLowDataset.getVolumeValue(series, item).doubleValue();
                    if (volume > this.maxVolume) {
                        this.maxVolume = volume;
                    }
                }
            }
        }
        return new XYItemRendererState(info);
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           plot
        //     2: invokevirtual   org/jfree/chart/plot/XYPlot.getOrientation:()Lorg/jfree/chart/plot/PlotOrientation;
        //     5: astore          orientation
        //     7: aload           orientation
        //     9: getstatic       org/jfree/chart/plot/PlotOrientation.HORIZONTAL:Lorg/jfree/chart/plot/PlotOrientation;
        //    12: if_acmpne       21
        //    15: iconst_1       
        //    16: istore          horiz
        //    18: goto            36
        //    21: aload           orientation
        //    23: getstatic       org/jfree/chart/plot/PlotOrientation.VERTICAL:Lorg/jfree/chart/plot/PlotOrientation;
        //    26: if_acmpne       35
        //    29: iconst_0       
        //    30: istore          horiz
        //    32: goto            36
        //    35: return         
        //    36: aconst_null    
        //    37: astore          entities
        //    39: aload           info
        //    41: ifnull          54
        //    44: aload           info
        //    46: invokevirtual   org/jfree/chart/plot/PlotRenderingInfo.getOwner:()Lorg/jfree/chart/ChartRenderingInfo;
        //    49: invokevirtual   org/jfree/chart/ChartRenderingInfo.getEntityCollection:()Lorg/jfree/chart/entity/EntityCollection;
        //    52: astore          entities
        //    54: aload           dataset
        //    56: checkcast       Lorg/jfree/data/HighLowDataset;
        //    59: astore          highLowData
        //    61: aload           highLowData
        //    63: iload           series
        //    65: iload           item
        //    67: invokeinterface org/jfree/data/HighLowDataset.getXValue:(II)Ljava/lang/Number;
        //    72: astore          x
        //    74: aload           highLowData
        //    76: iload           series
        //    78: iload           item
        //    80: invokeinterface org/jfree/data/HighLowDataset.getHighValue:(II)Ljava/lang/Number;
        //    85: astore          yHigh
        //    87: aload           highLowData
        //    89: iload           series
        //    91: iload           item
        //    93: invokeinterface org/jfree/data/HighLowDataset.getLowValue:(II)Ljava/lang/Number;
        //    98: astore          yLow
        //   100: aload           highLowData
        //   102: iload           series
        //   104: iload           item
        //   106: invokeinterface org/jfree/data/HighLowDataset.getOpenValue:(II)Ljava/lang/Number;
        //   111: astore          yOpen
        //   113: aload           highLowData
        //   115: iload           series
        //   117: iload           item
        //   119: invokeinterface org/jfree/data/HighLowDataset.getCloseValue:(II)Ljava/lang/Number;
        //   124: astore          yClose
        //   126: aload           plot
        //   128: invokevirtual   org/jfree/chart/plot/XYPlot.getDomainAxisEdge:()Lorg/jfree/ui/RectangleEdge;
        //   131: astore          domainEdge
        //   133: aload           domainAxis
        //   135: aload           x
        //   137: invokevirtual   java/lang/Number.doubleValue:()D
        //   140: aload_3         /* dataArea */
        //   141: aload           domainEdge
        //   143: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   146: dstore          xx
        //   148: aload           plot
        //   150: invokevirtual   org/jfree/chart/plot/XYPlot.getRangeAxisEdge:()Lorg/jfree/ui/RectangleEdge;
        //   153: astore          edge
        //   155: aload           rangeAxis
        //   157: aload           yHigh
        //   159: invokevirtual   java/lang/Number.doubleValue:()D
        //   162: aload_3         /* dataArea */
        //   163: aload           edge
        //   165: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   168: dstore          yyHigh
        //   170: aload           rangeAxis
        //   172: aload           yLow
        //   174: invokevirtual   java/lang/Number.doubleValue:()D
        //   177: aload_3         /* dataArea */
        //   178: aload           edge
        //   180: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   183: dstore          yyLow
        //   185: aload           rangeAxis
        //   187: aload           yOpen
        //   189: invokevirtual   java/lang/Number.doubleValue:()D
        //   192: aload_3         /* dataArea */
        //   193: aload           edge
        //   195: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   198: dstore          yyOpen
        //   200: aload           rangeAxis
        //   202: aload           yClose
        //   204: invokevirtual   java/lang/Number.doubleValue:()D
        //   207: aload_3         /* dataArea */
        //   208: aload           edge
        //   210: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   213: dstore          yyClose
        //   215: aload_0         /* this */
        //   216: getfield        org/jfree/chart/renderer/CandlestickRenderer.candleWidth:D
        //   219: dconst_0       
        //   220: dcmpl          
        //   221: ifle            239
        //   224: aload_0         /* this */
        //   225: getfield        org/jfree/chart/renderer/CandlestickRenderer.candleWidth:D
        //   228: dstore          volumeWidth
        //   230: aload_0         /* this */
        //   231: getfield        org/jfree/chart/renderer/CandlestickRenderer.candleWidth:D
        //   234: dstore          stickWidth
        //   236: goto            543
        //   239: dconst_0       
        //   240: dstore          xxWidth
        //   242: aload_0         /* this */
        //   243: getfield        org/jfree/chart/renderer/CandlestickRenderer.autoWidthMethod:I
        //   246: tableswitch {
        //                0: 272
        //                1: 314
        //                2: 407
        //          default: 478
        //        }
        //   272: aload           highLowData
        //   274: iload           series
        //   276: invokeinterface org/jfree/data/HighLowDataset.getItemCount:(I)I
        //   281: istore          itemCount
        //   283: iload           13
        //   285: ifeq            301
        //   288: aload_3         /* dataArea */
        //   289: invokevirtual   java/awt/geom/Rectangle2D.getHeight:()D
        //   292: iload           itemCount
        //   294: i2d            
        //   295: ddiv           
        //   296: dstore          xxWidth
        //   298: goto            478
        //   301: aload_3         /* dataArea */
        //   302: invokevirtual   java/awt/geom/Rectangle2D.getWidth:()D
        //   305: iload           itemCount
        //   307: i2d            
        //   308: ddiv           
        //   309: dstore          xxWidth
        //   311: goto            478
        //   314: aload           highLowData
        //   316: iload           series
        //   318: invokeinterface org/jfree/data/HighLowDataset.getItemCount:(I)I
        //   323: istore          itemCount
        //   325: ldc2_w          -1.0
        //   328: dstore          lastPos
        //   330: aload_3         /* dataArea */
        //   331: invokevirtual   java/awt/geom/Rectangle2D.getWidth:()D
        //   334: dstore          xxWidth
        //   336: iconst_0       
        //   337: istore          i
        //   339: iload           i
        //   341: iload           itemCount
        //   343: if_icmpge       404
        //   346: aload           domainAxis
        //   348: aload           highLowData
        //   350: iload           series
        //   352: iload           i
        //   354: invokeinterface org/jfree/data/HighLowDataset.getXValue:(II)Ljava/lang/Number;
        //   359: invokevirtual   java/lang/Number.doubleValue:()D
        //   362: aload_3         /* dataArea */
        //   363: aload           domainEdge
        //   365: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   368: dstore          pos
        //   370: dload           lastPos
        //   372: ldc2_w          -1.0
        //   375: dcmpl          
        //   376: ifeq            394
        //   379: dload           xxWidth
        //   381: dload           pos
        //   383: dload           lastPos
        //   385: dsub           
        //   386: invokestatic    java/lang/Math.abs:(D)D
        //   389: invokestatic    java/lang/Math.min:(DD)D
        //   392: dstore          xxWidth
        //   394: dload           pos
        //   396: dstore          lastPos
        //   398: iinc            i, 1
        //   401: goto            339
        //   404: goto            478
        //   407: aload           dataset
        //   409: checkcast       Lorg/jfree/data/IntervalXYDataset;
        //   412: astore          intervalXYData
        //   414: aload           domainAxis
        //   416: aload           intervalXYData
        //   418: iload           series
        //   420: iload           item
        //   422: invokeinterface org/jfree/data/IntervalXYDataset.getStartXValue:(II)Ljava/lang/Number;
        //   427: invokevirtual   java/lang/Number.doubleValue:()D
        //   430: aload_3         /* dataArea */
        //   431: aload           plot
        //   433: invokevirtual   org/jfree/chart/plot/XYPlot.getDomainAxisEdge:()Lorg/jfree/ui/RectangleEdge;
        //   436: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   439: dstore          startPos
        //   441: aload           domainAxis
        //   443: aload           intervalXYData
        //   445: iload           series
        //   447: iload           item
        //   449: invokeinterface org/jfree/data/IntervalXYDataset.getEndXValue:(II)Ljava/lang/Number;
        //   454: invokevirtual   java/lang/Number.doubleValue:()D
        //   457: aload_3         /* dataArea */
        //   458: aload           plot
        //   460: invokevirtual   org/jfree/chart/plot/XYPlot.getDomainAxisEdge:()Lorg/jfree/ui/RectangleEdge;
        //   463: invokevirtual   org/jfree/chart/axis/ValueAxis.valueToJava2D:(DLjava/awt/geom/Rectangle2D;Lorg/jfree/ui/RectangleEdge;)D
        //   466: dstore          endPos
        //   468: dload           endPos
        //   470: dload           startPos
        //   472: dsub           
        //   473: invokestatic    java/lang/Math.abs:(D)D
        //   476: dstore          xxWidth
        //   478: dload           xxWidth
        //   480: ldc2_w          2.0
        //   483: aload_0         /* this */
        //   484: getfield        org/jfree/chart/renderer/CandlestickRenderer.autoWidthGap:D
        //   487: dmul           
        //   488: dsub           
        //   489: dstore          xxWidth
        //   491: dload           xxWidth
        //   493: aload_0         /* this */
        //   494: getfield        org/jfree/chart/renderer/CandlestickRenderer.autoWidthFactor:D
        //   497: dmul           
        //   498: dstore          xxWidth
        //   500: dload           xxWidth
        //   502: aload_0         /* this */
        //   503: getfield        org/jfree/chart/renderer/CandlestickRenderer.maxCandleWidth:D
        //   506: invokestatic    java/lang/Math.min:(DD)D
        //   509: dstore          xxWidth
        //   511: dconst_1       
        //   512: aload_0         /* this */
        //   513: getfield        org/jfree/chart/renderer/CandlestickRenderer.maxCandleWidth:D
        //   516: invokestatic    java/lang/Math.min:(DD)D
        //   519: dload           xxWidth
        //   521: invokestatic    java/lang/Math.max:(DD)D
        //   524: dstore          volumeWidth
        //   526: ldc2_w          3.0
        //   529: aload_0         /* this */
        //   530: getfield        org/jfree/chart/renderer/CandlestickRenderer.maxCandleWidth:D
        //   533: invokestatic    java/lang/Math.min:(DD)D
        //   536: dload           xxWidth
        //   538: invokestatic    java/lang/Math.max:(DD)D
        //   541: dstore          stickWidth
        //   543: aload_0         /* this */
        //   544: iload           series
        //   546: iload           item
        //   548: invokevirtual   org/jfree/chart/renderer/CandlestickRenderer.getItemPaint:(II)Ljava/awt/Paint;
        //   551: astore          p
        //   553: aload_0         /* this */
        //   554: iload           series
        //   556: iload           item
        //   558: invokevirtual   org/jfree/chart/renderer/CandlestickRenderer.getItemStroke:(II)Ljava/awt/Stroke;
        //   561: astore          s
        //   563: aload_1         /* g2 */
        //   564: aload           s
        //   566: invokevirtual   java/awt/Graphics2D.setStroke:(Ljava/awt/Stroke;)V
        //   569: aload_0         /* this */
        //   570: getfield        org/jfree/chart/renderer/CandlestickRenderer.drawVolume:Z
        //   573: ifeq            736
        //   576: aload           highLowData
        //   578: iload           series
        //   580: iload           item
        //   582: invokeinterface org/jfree/data/HighLowDataset.getVolumeValue:(II)Ljava/lang/Number;
        //   587: invokevirtual   java/lang/Number.intValue:()I
        //   590: istore          volume
        //   592: iload           volume
        //   594: i2d            
        //   595: aload_0         /* this */
        //   596: getfield        org/jfree/chart/renderer/CandlestickRenderer.maxVolume:D
        //   599: ddiv           
        //   600: dstore          volumeHeight
        //   602: iload           13
        //   604: ifeq            622
        //   607: aload_3         /* dataArea */
        //   608: invokevirtual   java/awt/geom/Rectangle2D.getMinX:()D
        //   611: dstore          min
        //   613: aload_3         /* dataArea */
        //   614: invokevirtual   java/awt/geom/Rectangle2D.getMaxX:()D
        //   617: dstore          max
        //   619: goto            634
        //   622: aload_3         /* dataArea */
        //   623: invokevirtual   java/awt/geom/Rectangle2D.getMinY:()D
        //   626: dstore          min
        //   628: aload_3         /* dataArea */
        //   629: invokevirtual   java/awt/geom/Rectangle2D.getMaxY:()D
        //   632: dstore          max
        //   634: dload           volumeHeight
        //   636: dload           max
        //   638: dload           min
        //   640: dsub           
        //   641: dmul           
        //   642: dstore          zzVolume
        //   644: aload_1         /* g2 */
        //   645: getstatic       java/awt/Color.gray:Ljava/awt/Color;
        //   648: invokevirtual   java/awt/Graphics2D.setPaint:(Ljava/awt/Paint;)V
        //   651: aload_1         /* g2 */
        //   652: invokevirtual   java/awt/Graphics2D.getComposite:()Ljava/awt/Composite;
        //   655: astore          originalComposite
        //   657: aload_1         /* g2 */
        //   658: iconst_3       
        //   659: ldc             0.3
        //   661: invokestatic    java/awt/AlphaComposite.getInstance:(IF)Ljava/awt/AlphaComposite;
        //   664: invokevirtual   java/awt/Graphics2D.setComposite:(Ljava/awt/Composite;)V
        //   667: iload           13
        //   669: ifeq            701
        //   672: aload_1         /* g2 */
        //   673: new             Ljava/awt/geom/Rectangle2D$Double;
        //   676: dup            
        //   677: dload           min
        //   679: dload           xx
        //   681: dload           volumeWidth
        //   683: ldc2_w          2.0
        //   686: ddiv           
        //   687: dsub           
        //   688: dload           zzVolume
        //   690: dload           volumeWidth
        //   692: invokespecial   java/awt/geom/Rectangle2D$Double.<init>:(DDDD)V
        //   695: invokevirtual   java/awt/Graphics2D.fill:(Ljava/awt/Shape;)V
        //   698: goto            730
        //   701: aload_1         /* g2 */
        //   702: new             Ljava/awt/geom/Rectangle2D$Double;
        //   705: dup            
        //   706: dload           xx
        //   708: dload           volumeWidth
        //   710: ldc2_w          2.0
        //   713: ddiv           
        //   714: dsub           
        //   715: dload           max
        //   717: dload           zzVolume
        //   719: dsub           
        //   720: dload           volumeWidth
        //   722: dload           zzVolume
        //   724: invokespecial   java/awt/geom/Rectangle2D$Double.<init>:(DDDD)V
        //   727: invokevirtual   java/awt/Graphics2D.fill:(Ljava/awt/Shape;)V
        //   730: aload_1         /* g2 */
        //   731: aload           originalComposite
        //   733: invokevirtual   java/awt/Graphics2D.setComposite:(Ljava/awt/Composite;)V
        //   736: aload_1         /* g2 */
        //   737: aload           p
        //   739: invokevirtual   java/awt/Graphics2D.setPaint:(Ljava/awt/Paint;)V
        //   742: dload           yyOpen
        //   744: dload           yyClose
        //   746: invokestatic    java/lang/Math.max:(DD)D
        //   749: dstore          yyMaxOpenClose
        //   751: dload           yyOpen
        //   753: dload           yyClose
        //   755: invokestatic    java/lang/Math.min:(DD)D
        //   758: dstore          yyMinOpenClose
        //   760: aload           yOpen
        //   762: invokevirtual   java/lang/Number.doubleValue:()D
        //   765: aload           yClose
        //   767: invokevirtual   java/lang/Number.doubleValue:()D
        //   770: invokestatic    java/lang/Math.max:(DD)D
        //   773: dstore          maxOpenClose
        //   775: aload           yOpen
        //   777: invokevirtual   java/lang/Number.doubleValue:()D
        //   780: aload           yClose
        //   782: invokevirtual   java/lang/Number.doubleValue:()D
        //   785: invokestatic    java/lang/Math.min:(DD)D
        //   788: dstore          minOpenClose
        //   790: aload           yHigh
        //   792: invokevirtual   java/lang/Number.doubleValue:()D
        //   795: dload           maxOpenClose
        //   797: dcmpl          
        //   798: ifle            847
        //   801: iload           13
        //   803: ifeq            828
        //   806: aload_1         /* g2 */
        //   807: new             Ljava/awt/geom/Line2D$Double;
        //   810: dup            
        //   811: dload           yyHigh
        //   813: dload           xx
        //   815: dload           yyMaxOpenClose
        //   817: dload           xx
        //   819: invokespecial   java/awt/geom/Line2D$Double.<init>:(DDDD)V
        //   822: invokevirtual   java/awt/Graphics2D.draw:(Ljava/awt/Shape;)V
        //   825: goto            847
        //   828: aload_1         /* g2 */
        //   829: new             Ljava/awt/geom/Line2D$Double;
        //   832: dup            
        //   833: dload           xx
        //   835: dload           yyHigh
        //   837: dload           xx
        //   839: dload           yyMaxOpenClose
        //   841: invokespecial   java/awt/geom/Line2D$Double.<init>:(DDDD)V
        //   844: invokevirtual   java/awt/Graphics2D.draw:(Ljava/awt/Shape;)V
        //   847: aload           yLow
        //   849: invokevirtual   java/lang/Number.doubleValue:()D
        //   852: dload           minOpenClose
        //   854: dcmpg          
        //   855: ifge            904
        //   858: iload           13
        //   860: ifeq            885
        //   863: aload_1         /* g2 */
        //   864: new             Ljava/awt/geom/Line2D$Double;
        //   867: dup            
        //   868: dload           yyLow
        //   870: dload           xx
        //   872: dload           yyMinOpenClose
        //   874: dload           xx
        //   876: invokespecial   java/awt/geom/Line2D$Double.<init>:(DDDD)V
        //   879: invokevirtual   java/awt/Graphics2D.draw:(Ljava/awt/Shape;)V
        //   882: goto            904
        //   885: aload_1         /* g2 */
        //   886: new             Ljava/awt/geom/Line2D$Double;
        //   889: dup            
        //   890: dload           xx
        //   892: dload           yyLow
        //   894: dload           xx
        //   896: dload           yyMinOpenClose
        //   898: invokespecial   java/awt/geom/Line2D$Double.<init>:(DDDD)V
        //   901: invokevirtual   java/awt/Graphics2D.draw:(Ljava/awt/Shape;)V
        //   904: aconst_null    
        //   905: astore          body
        //   907: iload           13
        //   909: ifeq            942
        //   912: new             Ljava/awt/geom/Rectangle2D$Double;
        //   915: dup            
        //   916: dload           yyMinOpenClose
        //   918: dload           xx
        //   920: dload           stickWidth
        //   922: ldc2_w          2.0
        //   925: ddiv           
        //   926: dsub           
        //   927: dload           yyMaxOpenClose
        //   929: dload           yyMinOpenClose
        //   931: dsub           
        //   932: dload           stickWidth
        //   934: invokespecial   java/awt/geom/Rectangle2D$Double.<init>:(DDDD)V
        //   937: astore          body
        //   939: goto            969
        //   942: new             Ljava/awt/geom/Rectangle2D$Double;
        //   945: dup            
        //   946: dload           xx
        //   948: dload           stickWidth
        //   950: ldc2_w          2.0
        //   953: ddiv           
        //   954: dsub           
        //   955: dload           yyMinOpenClose
        //   957: dload           stickWidth
        //   959: dload           yyMaxOpenClose
        //   961: dload           yyMinOpenClose
        //   963: dsub           
        //   964: invokespecial   java/awt/geom/Rectangle2D$Double.<init>:(DDDD)V
        //   967: astore          body
        //   969: aload           yClose
        //   971: invokevirtual   java/lang/Number.doubleValue:()D
        //   974: aload           yOpen
        //   976: invokevirtual   java/lang/Number.doubleValue:()D
        //   979: dcmpl          
        //   980: ifle            1007
        //   983: aload_0         /* this */
        //   984: getfield        org/jfree/chart/renderer/CandlestickRenderer.upPaint:Ljava/awt/Paint;
        //   987: ifnull          1028
        //   990: aload_1         /* g2 */
        //   991: aload_0         /* this */
        //   992: getfield        org/jfree/chart/renderer/CandlestickRenderer.upPaint:Ljava/awt/Paint;
        //   995: invokevirtual   java/awt/Graphics2D.setPaint:(Ljava/awt/Paint;)V
        //   998: aload_1         /* g2 */
        //   999: aload           body
        //  1001: invokevirtual   java/awt/Graphics2D.fill:(Ljava/awt/Shape;)V
        //  1004: goto            1028
        //  1007: aload_0         /* this */
        //  1008: getfield        org/jfree/chart/renderer/CandlestickRenderer.downPaint:Ljava/awt/Paint;
        //  1011: ifnull          1022
        //  1014: aload_1         /* g2 */
        //  1015: aload_0         /* this */
        //  1016: getfield        org/jfree/chart/renderer/CandlestickRenderer.downPaint:Ljava/awt/Paint;
        //  1019: invokevirtual   java/awt/Graphics2D.setPaint:(Ljava/awt/Paint;)V
        //  1022: aload_1         /* g2 */
        //  1023: aload           body
        //  1025: invokevirtual   java/awt/Graphics2D.fill:(Ljava/awt/Shape;)V
        //  1028: aload_1         /* g2 */
        //  1029: aload           p
        //  1031: invokevirtual   java/awt/Graphics2D.setPaint:(Ljava/awt/Paint;)V
        //  1034: aload_1         /* g2 */
        //  1035: aload           body
        //  1037: invokevirtual   java/awt/Graphics2D.draw:(Ljava/awt/Shape;)V
        //  1040: aload           entities
        //  1042: ifnull          1135
        //  1045: aconst_null    
        //  1046: astore          tip
        //  1048: aload_0         /* this */
        //  1049: iload           series
        //  1051: iload           item
        //  1053: invokevirtual   org/jfree/chart/renderer/CandlestickRenderer.getToolTipGenerator:(II)Lorg/jfree/chart/labels/XYToolTipGenerator;
        //  1056: astore          generator
        //  1058: aload           generator
        //  1060: ifnull          1078
        //  1063: aload           generator
        //  1065: aload           dataset
        //  1067: iload           series
        //  1069: iload           item
        //  1071: invokeinterface org/jfree/chart/labels/XYToolTipGenerator.generateToolTip:(Lorg/jfree/data/XYDataset;II)Ljava/lang/String;
        //  1076: astore          tip
        //  1078: aconst_null    
        //  1079: astore          url
        //  1081: aload_0         /* this */
        //  1082: invokevirtual   org/jfree/chart/renderer/CandlestickRenderer.getURLGenerator:()Lorg/jfree/chart/urls/XYURLGenerator;
        //  1085: ifnull          1105
        //  1088: aload_0         /* this */
        //  1089: invokevirtual   org/jfree/chart/renderer/CandlestickRenderer.getURLGenerator:()Lorg/jfree/chart/urls/XYURLGenerator;
        //  1092: aload           dataset
        //  1094: iload           series
        //  1096: iload           item
        //  1098: invokeinterface org/jfree/chart/urls/XYURLGenerator.generateURL:(Lorg/jfree/data/XYDataset;II)Ljava/lang/String;
        //  1103: astore          url
        //  1105: new             Lorg/jfree/chart/entity/XYItemEntity;
        //  1108: dup            
        //  1109: aload           body
        //  1111: aload           dataset
        //  1113: iload           series
        //  1115: iload           item
        //  1117: aload           tip
        //  1119: aload           url
        //  1121: invokespecial   org/jfree/chart/entity/XYItemEntity.<init>:(Ljava/awt/Shape;Lorg/jfree/data/XYDataset;IILjava/lang/String;Ljava/lang/String;)V
        //  1124: astore          entity
        //  1126: aload           entities
        //  1128: aload           entity
        //  1130: invokeinterface org/jfree/chart/entity/EntityCollection.addEntity:(Lorg/jfree/chart/entity/ChartEntity;)V
        //  1135: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name               Signature
        //  -----  ------  ----  -----------------  ----------------------------------------------
        //  18     3       13    horiz              Z
        //  32     3       13    horiz              Z
        //  230    9       34    volumeWidth        D
        //  236    3       36    stickWidth         D
        //  283    31      40    itemCount          I
        //  370    28      44    pos                D
        //  339    65      43    i                  I
        //  325    82      40    itemCount          I
        //  330    77      41    lastPos            D
        //  414    64      43    intervalXYData     Lorg/jfree/data/IntervalXYDataset;
        //  441    37      44    startPos           D
        //  468    10      46    endPos             D
        //  242    301     38    xxWidth            D
        //  613    9       43    min                D
        //  619    3       45    max                D
        //  592    144     40    volume             I
        //  602    134     41    volumeHeight       D
        //  628    108     43    min                D
        //  634    102     45    max                D
        //  644    92      47    zzVolume           D
        //  657    79      49    originalComposite  Ljava/awt/Composite;
        //  1048   87      49    tip                Ljava/lang/String;
        //  1058   77      50    generator          Lorg/jfree/chart/labels/XYToolTipGenerator;
        //  1081   54      51    url                Ljava/lang/String;
        //  1126   9       52    entity             Lorg/jfree/chart/entity/XYItemEntity;
        //  0      1136    0     this               Lorg/jfree/chart/renderer/CandlestickRenderer;
        //  0      1136    1     g2                 Ljava/awt/Graphics2D;
        //  0      1136    2     state              Lorg/jfree/chart/renderer/XYItemRendererState;
        //  0      1136    3     dataArea           Ljava/awt/geom/Rectangle2D;
        //  0      1136    4     info               Lorg/jfree/chart/plot/PlotRenderingInfo;
        //  0      1136    5     plot               Lorg/jfree/chart/plot/XYPlot;
        //  0      1136    6     domainAxis         Lorg/jfree/chart/axis/ValueAxis;
        //  0      1136    7     rangeAxis          Lorg/jfree/chart/axis/ValueAxis;
        //  0      1136    8     dataset            Lorg/jfree/data/XYDataset;
        //  0      1136    9     series             I
        //  0      1136    10    item               I
        //  0      1136    11    crosshairState     Lorg/jfree/chart/plot/CrosshairState;
        //  0      1136    12    pass               I
        //  7      1129    14    orientation        Lorg/jfree/chart/plot/PlotOrientation;
        //  39     1097    15    entities           Lorg/jfree/chart/entity/EntityCollection;
        //  61     1075    16    highLowData        Lorg/jfree/data/HighLowDataset;
        //  74     1062    17    x                  Ljava/lang/Number;
        //  87     1049    18    yHigh              Ljava/lang/Number;
        //  100    1036    19    yLow               Ljava/lang/Number;
        //  113    1023    20    yOpen              Ljava/lang/Number;
        //  126    1010    21    yClose             Ljava/lang/Number;
        //  133    1003    22    domainEdge         Lorg/jfree/ui/RectangleEdge;
        //  148    988     23    xx                 D
        //  155    981     25    edge               Lorg/jfree/ui/RectangleEdge;
        //  170    966     26    yyHigh             D
        //  185    951     28    yyLow              D
        //  200    936     30    yyOpen             D
        //  215    921     32    yyClose            D
        //  526    610     34    volumeWidth        D
        //  543    593     36    stickWidth         D
        //  553    583     38    p                  Ljava/awt/Paint;
        //  563    573     39    s                  Ljava/awt/Stroke;
        //  751    385     40    yyMaxOpenClose     D
        //  760    376     42    yyMinOpenClose     D
        //  775    361     44    maxOpenClose       D
        //  790    346     46    minOpenClose       D
        //  907    229     48    body               Ljava/awt/Shape;
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
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof CandlestickRenderer) {
            final CandlestickRenderer renderer = (CandlestickRenderer)obj;
            boolean result = super.equals(obj);
            result = (result && this.candleWidth == renderer.getCandleWidth());
            result = (result && this.upPaint.equals(renderer.getUpPaint()));
            result = (result && this.downPaint.equals(renderer.getDownPaint()));
            result = (result && this.drawVolume == renderer.drawVolume);
            return result;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.upPaint, stream);
        SerialUtilities.writePaint(this.downPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.upPaint = SerialUtilities.readPaint(stream);
        this.downPaint = SerialUtilities.readPaint(stream);
    }
}
