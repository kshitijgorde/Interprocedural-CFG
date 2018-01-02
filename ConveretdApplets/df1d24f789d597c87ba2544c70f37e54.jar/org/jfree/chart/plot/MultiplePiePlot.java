// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.awt.Stroke;
import java.awt.Shape;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import java.awt.Insets;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.data.CategoryToPieDataset;
import java.awt.Rectangle;
import org.jfree.data.DatasetUtilities;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.title.TextTitle;
import java.awt.Font;
import java.awt.Paint;
import org.jfree.data.PieDataset;
import org.jfree.util.TableOrder;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.JFreeChart;
import java.io.Serializable;

public class MultiplePiePlot extends Plot implements Cloneable, Serializable
{
    private JFreeChart pieChart;
    private CategoryDataset dataset;
    private TableOrder dataExtractOrder;
    private double limit;
    
    public MultiplePiePlot() {
        this(null);
    }
    
    public MultiplePiePlot(final CategoryDataset dataset) {
        this.limit = 0.0;
        this.dataset = dataset;
        final PiePlot piePlot = new PiePlot(null);
        this.pieChart = new JFreeChart(piePlot);
        this.dataExtractOrder = TableOrder.BY_COLUMN;
        this.pieChart.setBackgroundPaint(null);
        final TextTitle seriesTitle = new TextTitle("Series Title", new Font("SansSerif", 1, 12));
        seriesTitle.setPosition(RectangleEdge.BOTTOM);
        this.pieChart.setTitle(seriesTitle);
    }
    
    public JFreeChart getPieChart() {
        return this.pieChart;
    }
    
    public void setPieChart(final JFreeChart pieChart) {
        this.pieChart = pieChart;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public TableOrder getDataExtractOrder() {
        return this.dataExtractOrder;
    }
    
    public void setDataExtractOrder(final TableOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Null 'order' argument");
        }
        this.dataExtractOrder = order;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public double getLimit() {
        return this.limit;
    }
    
    public void setLimit(final double limit) {
        this.limit = limit;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public String getPlotType() {
        return "Multiple Pie Plot";
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D plotArea, final PlotState parentState, final PlotRenderingInfo info) {
        final Insets insets = this.getInsets();
        if (insets != null) {
            plotArea.setRect(plotArea.getX() + insets.left, plotArea.getY() + insets.top, plotArea.getWidth() - insets.left - insets.right, plotArea.getHeight() - insets.top - insets.bottom);
        }
        this.drawBackground(g2, plotArea);
        this.drawOutline(g2, plotArea);
        if (DatasetUtilities.isEmptyOrNull(this.dataset)) {
            this.drawNoDataMessage(g2, plotArea);
            return;
        }
        int pieCount = 0;
        if (this.dataExtractOrder == TableOrder.BY_ROW) {
            pieCount = this.dataset.getRowCount();
        }
        else {
            pieCount = this.dataset.getColumnCount();
        }
        int displayCols = (int)Math.ceil(Math.sqrt(pieCount));
        int displayRows = (int)Math.ceil(pieCount / displayCols);
        if (displayCols > displayRows && plotArea.getWidth() < plotArea.getHeight()) {
            final int temp = displayCols;
            displayCols = displayRows;
            displayRows = temp;
        }
        final int x = (int)plotArea.getX();
        final int y = (int)plotArea.getY();
        final int width = (int)plotArea.getWidth() / displayCols;
        final int height = (int)plotArea.getHeight() / displayRows;
        int row = 0;
        int column = 0;
        final int diff = displayRows * displayCols - pieCount;
        int xoffset = 0;
        final Rectangle rect = new Rectangle();
        for (int pieIndex = 0; pieIndex < pieCount; ++pieIndex) {
            rect.setBounds(x + xoffset + width * column, y + height * row, width, height);
            String title = null;
            if (this.dataExtractOrder == TableOrder.BY_ROW) {
                title = this.dataset.getRowKey(pieIndex).toString();
            }
            else {
                title = this.dataset.getColumnKey(pieIndex).toString();
            }
            this.pieChart.setTitle(title);
            PieDataset piedataset = null;
            final PieDataset dd = new CategoryToPieDataset(this.dataset, this.dataExtractOrder, pieIndex);
            if (this.limit > 0.0) {
                piedataset = DatasetUtilities.limitPieDataset(dd, this.limit);
            }
            else {
                piedataset = dd;
            }
            final PiePlot piePlot = (PiePlot)this.pieChart.getPlot();
            piePlot.setDataset(piedataset);
            piePlot.setPieIndex(pieIndex);
            ChartRenderingInfo subinfo = null;
            if (info != null) {
                subinfo = new ChartRenderingInfo();
            }
            this.pieChart.draw(g2, rect, subinfo);
            if (info != null) {
                info.getOwner().getEntityCollection().addEntities(subinfo.getEntityCollection());
                info.addSubplotInfo(subinfo.getPlotInfo());
            }
            if (++column == displayCols) {
                column = 0;
                if (++row == displayRows - 1 && diff != 0) {
                    xoffset = diff * width / 2;
                }
            }
        }
    }
    
    public LegendItemCollection getLegendItems() {
        final LegendItemCollection result = new LegendItemCollection();
        if (this.dataset != null) {
            List keys = null;
            if (this.dataExtractOrder == TableOrder.BY_ROW) {
                keys = this.dataset.getColumnKeys();
            }
            else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
                keys = this.dataset.getRowKeys();
            }
            if (keys != null) {
                int section = 0;
                final Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    final String description;
                    final String label = description = iterator.next().toString();
                    final Shape shape = null;
                    final PiePlot plot = (PiePlot)this.pieChart.getPlot();
                    final Paint paint = plot.getSectionPaint(section);
                    final Paint outlinePaint = plot.getSectionOutlinePaint(section);
                    final Stroke stroke = plot.getSectionOutlineStroke(section);
                    final LegendItem item = new LegendItem(label, description, shape, true, paint, stroke, outlinePaint, stroke);
                    result.add(item);
                    ++section;
                }
            }
        }
        return result;
    }
}
