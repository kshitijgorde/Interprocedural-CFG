// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.ui.TextAnchor;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.LegendItem;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.labels.ItemLabelPosition;
import java.awt.Font;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.CategoryLabelGenerator;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Paint;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.Range;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.plot.CategoryPlot;

public interface CategoryItemRenderer
{
    CategoryPlot getPlot();
    
    void setPlot(final CategoryPlot p0);
    
    void addChangeListener(final RendererChangeListener p0);
    
    void removeChangeListener(final RendererChangeListener p0);
    
    Range getRangeExtent(final CategoryDataset p0);
    
    CategoryItemRendererState initialise(final Graphics2D p0, final Rectangle2D p1, final CategoryPlot p2, final int p3, final PlotRenderingInfo p4);
    
    Paint getItemPaint(final int p0, final int p1);
    
    void setPaint(final Paint p0);
    
    Paint getSeriesPaint(final int p0);
    
    void setSeriesPaint(final int p0, final Paint p1);
    
    Paint getBasePaint();
    
    void setBasePaint(final Paint p0);
    
    Paint getItemOutlinePaint(final int p0, final int p1);
    
    void setOutlinePaint(final Paint p0);
    
    Paint getSeriesOutlinePaint(final int p0);
    
    void setSeriesOutlinePaint(final int p0, final Paint p1);
    
    Paint getBaseOutlinePaint();
    
    void setBaseOutlinePaint(final Paint p0);
    
    Stroke getItemStroke(final int p0, final int p1);
    
    void setStroke(final Stroke p0);
    
    Stroke getSeriesStroke(final int p0);
    
    void setSeriesStroke(final int p0, final Stroke p1);
    
    Stroke getBaseStroke();
    
    void setBaseStroke(final Stroke p0);
    
    Stroke getItemOutlineStroke(final int p0, final int p1);
    
    void setOutlineStroke(final Stroke p0);
    
    Stroke getSeriesOutlineStroke(final int p0);
    
    void setSeriesOutlineStroke(final int p0, final Stroke p1);
    
    Stroke getBaseOutlineStroke();
    
    void setBaseOutlineStroke(final Stroke p0);
    
    Shape getItemShape(final int p0, final int p1);
    
    void setShape(final Shape p0);
    
    Shape getSeriesShape(final int p0);
    
    void setSeriesShape(final int p0, final Shape p1);
    
    Shape getBaseShape();
    
    void setBaseShape(final Shape p0);
    
    boolean isItemLabelVisible(final int p0, final int p1);
    
    boolean isSeriesItemLabelsVisible(final int p0);
    
    void setItemLabelsVisible(final boolean p0);
    
    void setItemLabelsVisible(final Boolean p0);
    
    void setItemLabelsVisible(final Boolean p0, final boolean p1);
    
    void setSeriesItemLabelsVisible(final int p0, final boolean p1);
    
    void setSeriesItemLabelsVisible(final int p0, final Boolean p1);
    
    void setSeriesItemLabelsVisible(final int p0, final Boolean p1, final boolean p2);
    
    Boolean getBaseItemLabelsVisible();
    
    void setBaseItemLabelsVisible(final boolean p0);
    
    void setBaseItemLabelsVisible(final Boolean p0);
    
    void setBaseItemLabelsVisible(final Boolean p0, final boolean p1);
    
    CategoryLabelGenerator getLabelGenerator(final int p0, final int p1);
    
    CategoryLabelGenerator getSeriesLabelGenerator(final int p0);
    
    void setLabelGenerator(final CategoryLabelGenerator p0);
    
    void setSeriesLabelGenerator(final int p0, final CategoryLabelGenerator p1);
    
    CategoryLabelGenerator getBaseLabelGenerator();
    
    void setBaseLabelGenerator(final CategoryLabelGenerator p0);
    
    CategoryToolTipGenerator getToolTipGenerator(final int p0, final int p1);
    
    CategoryToolTipGenerator getToolTipGenerator();
    
    void setToolTipGenerator(final CategoryToolTipGenerator p0);
    
    CategoryToolTipGenerator getSeriesToolTipGenerator(final int p0);
    
    void setSeriesToolTipGenerator(final int p0, final CategoryToolTipGenerator p1);
    
    CategoryToolTipGenerator getBaseToolTipGenerator();
    
    void setBaseToolTipGenerator(final CategoryToolTipGenerator p0);
    
    Font getItemLabelFont(final int p0, final int p1);
    
    Font getItemLabelFont();
    
    void setItemLabelFont(final Font p0);
    
    Font getSeriesItemLabelFont(final int p0);
    
    void setSeriesItemLabelFont(final int p0, final Font p1);
    
    Font getBaseItemLabelFont();
    
    void setBaseItemLabelFont(final Font p0);
    
    Paint getItemLabelPaint(final int p0, final int p1);
    
    Paint getItemLabelPaint();
    
    void setItemLabelPaint(final Paint p0);
    
    Paint getSeriesItemLabelPaint(final int p0);
    
    void setSeriesItemLabelPaint(final int p0, final Paint p1);
    
    Paint getBaseItemLabelPaint();
    
    void setBaseItemLabelPaint(final Paint p0);
    
    ItemLabelPosition getPositiveItemLabelPosition(final int p0, final int p1);
    
    ItemLabelPosition getPositiveItemLabelPosition();
    
    void setPositiveItemLabelPosition(final ItemLabelPosition p0);
    
    void setPositiveItemLabelPosition(final ItemLabelPosition p0, final boolean p1);
    
    ItemLabelPosition getSeriesPositiveItemLabelPosition(final int p0);
    
    void setSeriesPositiveItemLabelPosition(final int p0, final ItemLabelPosition p1);
    
    void setSeriesPositiveItemLabelPosition(final int p0, final ItemLabelPosition p1, final boolean p2);
    
    ItemLabelPosition getBasePositiveItemLabelPosition();
    
    void setBasePositiveItemLabelPosition(final ItemLabelPosition p0);
    
    void setBasePositiveItemLabelPosition(final ItemLabelPosition p0, final boolean p1);
    
    ItemLabelPosition getNegativeItemLabelPosition(final int p0, final int p1);
    
    ItemLabelPosition getNegativeItemLabelPosition();
    
    void setNegativeItemLabelPosition(final ItemLabelPosition p0);
    
    void setNegativeItemLabelPosition(final ItemLabelPosition p0, final boolean p1);
    
    ItemLabelPosition getSeriesNegativeItemLabelPosition(final int p0);
    
    void setSeriesNegativeItemLabelPosition(final int p0, final ItemLabelPosition p1);
    
    void setSeriesNegativeItemLabelPosition(final int p0, final ItemLabelPosition p1, final boolean p2);
    
    ItemLabelPosition getBaseNegativeItemLabelPosition();
    
    void setBaseNegativeItemLabelPosition(final ItemLabelPosition p0);
    
    void setBaseNegativeItemLabelPosition(final ItemLabelPosition p0, final boolean p1);
    
    CategoryURLGenerator getItemURLGenerator(final int p0, final int p1);
    
    CategoryURLGenerator getSeriesItemURLGenerator(final int p0);
    
    void setItemURLGenerator(final CategoryURLGenerator p0);
    
    void setSeriesItemURLGenerator(final int p0, final CategoryURLGenerator p1);
    
    CategoryURLGenerator getBaseItemURLGenerator();
    
    void setBaseItemURLGenerator(final CategoryURLGenerator p0);
    
    LegendItem getLegendItem(final int p0, final int p1);
    
    void drawBackground(final Graphics2D p0, final CategoryPlot p1, final Rectangle2D p2);
    
    void drawOutline(final Graphics2D p0, final CategoryPlot p1, final Rectangle2D p2);
    
    void drawItem(final Graphics2D p0, final CategoryItemRendererState p1, final Rectangle2D p2, final CategoryPlot p3, final CategoryAxis p4, final ValueAxis p5, final CategoryDataset p6, final int p7, final int p8);
    
    void drawDomainGridline(final Graphics2D p0, final CategoryPlot p1, final Rectangle2D p2, final double p3);
    
    void drawRangeGridline(final Graphics2D p0, final CategoryPlot p1, final ValueAxis p2, final Rectangle2D p3, final double p4);
    
    void drawRangeMarker(final Graphics2D p0, final CategoryPlot p1, final ValueAxis p2, final Marker p3, final Rectangle2D p4);
    
    ItemLabelAnchor getItemLabelAnchor(final int p0, final int p1);
    
    ItemLabelAnchor getSeriesItemLabelAnchor(final int p0);
    
    void setItemLabelAnchor(final ItemLabelAnchor p0);
    
    void setSeriesItemLabelAnchor(final int p0, final ItemLabelAnchor p1);
    
    ItemLabelAnchor getBaseItemLabelAnchor();
    
    void setBaseItemLabelAnchor(final ItemLabelAnchor p0);
    
    TextAnchor getItemLabelTextAnchor(final int p0, final int p1);
    
    TextAnchor getSeriesItemLabelTextAnchor(final int p0);
    
    void setItemLabelTextAnchor(final TextAnchor p0);
    
    void setSeriesItemLabelTextAnchor(final int p0, final TextAnchor p1);
    
    TextAnchor getBaseItemLabelTextAnchor();
    
    void setBaseItemLabelTextAnchor(final TextAnchor p0);
}
