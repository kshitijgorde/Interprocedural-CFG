// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.awt.font.LineMetrics;
import java.awt.FontMetrics;
import org.jfree.chart.plot.Plot;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Shape;
import java.awt.Paint;
import org.jfree.data.ValueDataset;
import org.jfree.chart.plot.MeterPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.LegendChangeEvent;

public class MeterLegend extends StandardLegend
{
    private String legendText;
    private boolean showNormal;
    private boolean showWarning;
    private boolean showCritical;
    
    public MeterLegend() {
        this("");
    }
    
    public MeterLegend(final String legendText) {
        this.showNormal = true;
        this.showWarning = true;
        this.showCritical = true;
        this.legendText = legendText;
    }
    
    public MeterLegend(final JFreeChart chart, final String legendText) {
        super(chart);
        this.showNormal = true;
        this.showWarning = true;
        this.showCritical = true;
        this.legendText = legendText;
    }
    
    public String getLegendText() {
        return this.legendText;
    }
    
    public void setLegendText(final String text) {
        this.legendText = text;
        this.notifyListeners(new LegendChangeEvent(this));
    }
    
    public Rectangle2D draw(final Graphics2D g2, final Rectangle2D available) {
        return this.draw(g2, available, (this.getAnchor() & 0x1) != 0x0, (this.getAnchor() & 0x2) != 0x0);
    }
    
    private boolean updateInformation(final MeterPlot plot, final ValueDataset data, final int type, final int index, final LegendItem[] legendItems, final Paint[] legendItemColors) {
        boolean ret = false;
        String label = null;
        Paint paint = null;
        switch (type) {
            case 0: {
                paint = plot.getNormalPaint();
                label = "Normal";
                break;
            }
            case 1: {
                paint = plot.getWarningPaint();
                label = "Warning";
                break;
            }
            case 2: {
                paint = plot.getCriticalPaint();
                label = "Critical";
                break;
            }
            case 3: {
                paint = MeterPlot.DEFAULT_BACKGROUND_PAINT;
                label = "Meter Graph";
                break;
            }
            default: {
                return false;
            }
        }
        label += "  Range: ";
        legendItems[index] = new LegendItem(label, label, null, true, null, null, null, null);
        legendItemColors[index] = paint;
        ret = true;
        return ret;
    }
    
    protected Rectangle2D draw(final Graphics2D g2, final Rectangle2D available, final boolean horizontal, final boolean inverted) {
        int legendCount = 0;
        final Plot plot = this.getChart().getPlot();
        if (!(plot instanceof MeterPlot)) {
            throw new IllegalArgumentException("Plot must be MeterPlot");
        }
        final MeterPlot meterPlot = (MeterPlot)plot;
        final ValueDataset data = meterPlot.getDataset();
        legendCount = 1;
        ++legendCount;
        if (this.showCritical) {
            ++legendCount;
        }
        if (this.showWarning) {
            ++legendCount;
        }
        if (this.showNormal) {
            ++legendCount;
        }
        final LegendItem[] legendItems = new LegendItem[legendCount];
        final Color[] legendItemColors = new Color[legendCount];
        int currentItem = 0;
        final String label = this.legendText + ((data.getValue() != null) ? ("   Current Value: " + data.getValue().toString()) : "");
        legendItems[currentItem] = new LegendItem(label, label, null, true, null, null, null, null);
        legendItemColors[currentItem] = null;
        ++currentItem;
        if (this.updateInformation(meterPlot, data, 3, currentItem, legendItems, legendItemColors)) {
            ++currentItem;
        }
        if (this.showCritical && this.updateInformation(meterPlot, data, 2, currentItem, legendItems, legendItemColors)) {
            ++currentItem;
        }
        if (this.showWarning && this.updateInformation(meterPlot, data, 1, currentItem, legendItems, legendItemColors)) {
            ++currentItem;
        }
        if (this.showNormal && this.updateInformation(meterPlot, data, 0, currentItem, legendItems, legendItemColors)) {
            ++currentItem;
        }
        if (legendItems == null) {
            return available;
        }
        Rectangle2D legendArea = new Rectangle2D.Double();
        final double availableWidth = available.getWidth();
        final double availableHeight = available.getHeight();
        Point2D translation = new Point2D.Double();
        final DrawableLegendItem[] items = new DrawableLegendItem[legendItems.length];
        g2.setFont(this.getItemFont());
        if (horizontal) {
            final double xstart = available.getX() + this.getOuterGap().getLeftSpace(availableWidth);
            final double xlimit = available.getMaxX() + this.getOuterGap().getRightSpace(availableWidth) - 1.0;
            double maxRowWidth = 0.0;
            double xoffset = 0.0;
            double rowHeight = 0.0;
            double totalHeight = 0.0;
            boolean startingNewRow = true;
            for (int i = 0; i < legendItems.length; ++i) {
                items[i] = this.createLegendItem(g2, legendItems[i], xoffset, totalHeight);
                if (!startingNewRow && items[i].getX() + items[i].getWidth() + xstart > xlimit) {
                    maxRowWidth = Math.max(maxRowWidth, xoffset);
                    xoffset = 0.0;
                    totalHeight += rowHeight;
                    --i;
                    startingNewRow = true;
                }
                else {
                    rowHeight = Math.max(rowHeight, items[i].getHeight());
                    xoffset += items[i].getWidth();
                    startingNewRow = false;
                }
            }
            maxRowWidth = Math.max(maxRowWidth, xoffset);
            totalHeight += rowHeight;
            legendArea = new Rectangle2D.Double(0.0, 0.0, maxRowWidth, totalHeight);
            final double yloc = inverted ? (available.getMaxY() - totalHeight - this.getOuterGap().getBottomSpace(availableHeight)) : (available.getY() + this.getOuterGap().getTopSpace(availableHeight));
            final double xloc = available.getX() + available.getWidth() / 2.0 - maxRowWidth / 2.0;
            translation = new Point2D.Double(xloc, yloc);
        }
        else {
            double totalHeight2 = 0.0;
            double maxWidth = 0.0;
            g2.setFont(this.getItemFont());
            for (int j = 0; j < items.length; ++j) {
                items[j] = this.createLegendItem(g2, legendItems[j], 0.0, totalHeight2);
                totalHeight2 += items[j].getHeight();
                maxWidth = Math.max(maxWidth, items[j].getWidth());
            }
            legendArea = new Rectangle2D.Float(0.0f, 0.0f, (float)maxWidth, (float)totalHeight2);
            final double xloc2 = inverted ? (available.getMaxX() - maxWidth - this.getOuterGap().getRightSpace(availableWidth)) : (available.getX() + this.getOuterGap().getLeftSpace(availableWidth));
            final double yloc2 = available.getY() + available.getHeight() / 2.0 - totalHeight2 / 2.0;
            translation = new Point2D.Double(xloc2, yloc2);
        }
        g2.translate(translation.getX(), translation.getY());
        g2.setPaint(this.getBackgroundPaint());
        g2.fill(legendArea);
        g2.setPaint(this.getOutlinePaint());
        g2.setStroke(this.getOutlineStroke());
        g2.draw(legendArea);
        for (int k = 0; k < items.length; ++k) {
            final Color color = legendItemColors[k];
            if (color != null) {
                g2.setPaint(color);
                g2.fill(items[k].getMarker());
            }
            g2.setPaint(this.getItemPaint());
            g2.drawString(items[k].getItem().getLabel(), (float)items[k].getLabelPosition().getX(), (float)items[k].getLabelPosition().getY());
        }
        g2.translate(-translation.getX(), -translation.getY());
        if (horizontal) {
            final double y = available.getY();
            final double yloc3 = inverted ? y : (y + legendArea.getHeight() + this.getOuterGap().getBottomSpace(availableHeight));
            return new Rectangle2D.Double(available.getX(), yloc3, availableWidth, availableHeight - legendArea.getHeight() - this.getOuterGap().getTopSpace(availableHeight) - this.getOuterGap().getBottomSpace(availableHeight));
        }
        final double xloc3 = inverted ? available.getX() : (available.getX() + legendArea.getWidth() + this.getOuterGap().getLeftSpace(availableWidth) + this.getOuterGap().getRightSpace(availableWidth));
        return new Rectangle2D.Double(xloc3, available.getY(), availableWidth - legendArea.getWidth() - this.getOuterGap().getLeftSpace(availableWidth) - this.getOuterGap().getRightSpace(availableWidth), availableHeight);
    }
    
    private DrawableLegendItem createLegendItem(final Graphics graphics, final LegendItem item, final double x, final double y) {
        final int innerGap = 2;
        final FontMetrics fm = graphics.getFontMetrics();
        final LineMetrics lm = fm.getLineMetrics(item.getLabel(), graphics);
        final float textHeight = lm.getHeight();
        final DrawableLegendItem drawable = new DrawableLegendItem(item);
        float xloc = (float)(x + innerGap + 1.15f * textHeight);
        float yloc = (float)(y + innerGap + (textHeight - lm.getLeading() - lm.getDescent()));
        drawable.setLabelPosition(new Point2D.Float(xloc, yloc));
        final float boxDim = textHeight * 0.7f;
        xloc = (float)(x + innerGap + 0.15f * textHeight);
        yloc = (float)(y + innerGap + 0.15f * textHeight);
        drawable.setMarker(new Rectangle2D.Float(xloc, yloc, boxDim, boxDim));
        final float width = (float)(drawable.getLabelPosition().getX() - x + fm.stringWidth(item.getLabel()) + 0.5 * textHeight);
        final float height = 2 * innerGap + textHeight;
        drawable.setBounds(x, y, width, height);
        return drawable;
    }
}
