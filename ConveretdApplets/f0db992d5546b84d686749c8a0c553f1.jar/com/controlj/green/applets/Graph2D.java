// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import com.controlj.green.applets.draw.Marker;
import com.controlj.green.applets.draw.DrawLine;
import java.awt.Font;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.controlj.green.applets.draw.HorizontalRange;
import com.controlj.green.applets.draw.VerticalRange;
import com.controlj.green.applets.draw.Range;
import com.controlj.green.applets.label.TextLine;
import java.awt.Color;
import java.util.Vector;
import java.awt.Canvas;

public class Graph2D extends Canvas
{
    protected Axis axisTop;
    protected Axis axisRight;
    protected Axis axisBottom;
    protected Axis axisLeft;
    protected boolean lockAxes;
    protected Vector dataset;
    protected Color dataBackgroundColor;
    protected int minGraphLeftAxisWidth;
    protected int minGraphRightAxisWidth;
    private int borderTop;
    private int borderBottom;
    private int borderLeft;
    private int borderRight;
    private TextLine legendTextLine;
    private boolean showLegend;
    
    public Graph2D() {
        this.lockAxes = false;
        this.dataset = new Vector(10);
        this.dataBackgroundColor = null;
        this.minGraphLeftAxisWidth = 0;
        this.minGraphRightAxisWidth = 0;
        this.borderTop = 5;
        this.borderBottom = 5;
        this.borderLeft = 20;
        this.borderRight = 20;
        this.legendTextLine = new TextLine(" ");
        this.showLegend = false;
    }
    
    public Range getVertRange() {
        if (this.axisLeft != null) {
            return this.axisLeft.getRange();
        }
        if (this.axisRight != null) {
            return this.axisRight.getRange();
        }
        if (this.dataset.size() > 0) {
            return this.getDataSetAt(0).getVertRange();
        }
        return new VerticalRange();
    }
    
    public Range getSecondaryVertRange() {
        final Range prime = this.getVertRange();
        if (this.axisLeft != null && this.axisLeft.getRange() != prime) {
            return this.axisLeft.getRange();
        }
        if (this.axisRight != null && this.axisRight.getRange() != prime) {
            return this.axisRight.getRange();
        }
        return null;
    }
    
    public Range getHorizRange() {
        if (this.axisBottom != null) {
            return this.axisBottom.getRange();
        }
        if (this.axisTop != null) {
            return this.axisTop.getRange();
        }
        if (this.dataset.size() > 0) {
            return this.getDataSetAt(0).getHorizRange();
        }
        return new HorizontalRange();
    }
    
    public Range getSecondaryHorizRange() {
        final Range prime = this.getHorizRange();
        if (this.axisTop != null && this.axisTop.getRange() != prime) {
            return this.axisTop.getRange();
        }
        if (this.axisBottom != null && this.axisBottom.getRange() != prime) {
            return this.axisBottom.getRange();
        }
        return null;
    }
    
    private void synchronizeSecondaryRanges() {
        if (this.dataset == null || this.dataset.isEmpty()) {
            return;
        }
        final Range primeVert = this.getVertRange();
        final Range secondaryVert = this.getSecondaryVertRange();
        if (secondaryVert != null) {
            secondaryVert.setPixelMin(primeVert.getPixelMin());
            secondaryVert.setPixelMax(primeVert.getPixelMax());
            if (this.lockAxes) {
                secondaryVert.setValues(primeVert.getValueMin(), primeVert.getValueMax());
            }
            else if (!secondaryVert.isValueLocked()) {
                final double minXValue = this.getHorizRange().getValueMin();
                final double maxXValue = this.getHorizRange().getValueMax();
                double minYValue = 0.0;
                double maxYValue = 0.0;
                boolean uninitialized = true;
                for (int i = 0; i < this.dataset.size(); ++i) {
                    final DataSet set = this.getDataSetAt(i);
                    if (set.getSize() > 0 && set.getVertRange() == secondaryVert) {
                        if (uninitialized) {
                            minYValue = set.getMinYValueInRange(minXValue, maxXValue);
                            maxYValue = set.getMaxYValueInRange(minXValue, maxXValue);
                            uninitialized = false;
                        }
                        else {
                            minYValue = Math.min(minYValue, set.getMinYValueInRange(minXValue, maxXValue));
                            maxYValue = Math.max(maxYValue, set.getMaxYValueInRange(minXValue, maxXValue));
                        }
                    }
                }
                double tempvalue = (maxYValue - minYValue) * 0.1;
                if (tempvalue == 0.0) {
                    if (minYValue == 0.0) {
                        tempvalue = 1.0;
                    }
                    else {
                        tempvalue = Math.abs(minYValue) * 0.1;
                    }
                }
                secondaryVert.setValues(minYValue - tempvalue, maxYValue + tempvalue);
            }
        }
    }
    
    public void attachDataSet(final DataSet d) {
        if (d != null) {
            this.dataset.addElement(d);
        }
    }
    
    public void detachDataSet(final DataSet d) {
        if (d != null) {
            this.dataset.removeElement(d);
        }
    }
    
    public void detachDataSets() {
        if (this.dataset == null | this.dataset.isEmpty()) {
            return;
        }
        this.dataset.removeAllElements();
    }
    
    public DataSet getDataSetAt(final int i) {
        return this.dataset.elementAt(i);
    }
    
    public int getNumDataSets() {
        return this.dataset.size();
    }
    
    public void attachLeftAxis(final Axis a) {
        if (a == null) {
            return;
        }
        a.setTextOnDefaultSide(true);
        this.axisLeft = a;
    }
    
    public void attachRightAxis(final Axis a) {
        if (a == null) {
            return;
        }
        a.setTextOnDefaultSide(false);
        this.axisRight = a;
    }
    
    public void attachBottomAxis(final Axis a) {
        if (a == null) {
            return;
        }
        a.setTextOnDefaultSide(true);
        this.axisBottom = a;
    }
    
    public void attachTopAxis(final Axis a) {
        if (a == null) {
            return;
        }
        a.setTextOnDefaultSide(false);
        this.axisTop = a;
    }
    
    public Axis getBottomAxis() {
        return this.axisBottom;
    }
    
    public Axis getTopAxis() {
        return this.axisTop;
    }
    
    public Axis getLeftAxis() {
        return this.axisLeft;
    }
    
    public Axis getRightAxis() {
        return this.axisRight;
    }
    
    public void detachAxis(final Axis axis) {
        if (axis == this.axisBottom) {
            this.axisBottom = null;
        }
        else if (axis == this.axisTop) {
            this.axisTop = null;
        }
        else if (axis == this.axisLeft) {
            this.axisLeft = null;
        }
        else if (axis == this.axisRight) {
            this.axisRight = null;
        }
    }
    
    public void detachAxes() {
        this.axisBottom = null;
        this.axisLeft = null;
        this.axisTop = null;
        this.axisRight = null;
    }
    
    public boolean hasAxes() {
        return this.axisLeft != null || this.axisBottom != null || this.axisRight != null || this.axisTop != null;
    }
    
    public boolean hasVerticalAxis() {
        return this.axisLeft != null || this.axisRight != null;
    }
    
    public boolean hasHorizontalAxis() {
        return this.axisBottom != null || this.axisTop != null;
    }
    
    public boolean hasData() {
        if (this.dataset == null || this.dataset.isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            if (this.getDataSetAt(i).getSize() != 0) {
                return true;
            }
        }
        return false;
    }
    
    public int getNumDataPoints() {
        int size = 0;
        if (this.dataset == null || this.dataset.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            size += this.getDataSetAt(i).getSize();
        }
        return size;
    }
    
    public void deleteDataPointsAtXValue(final double removeBoundary, final boolean removeFromRight) {
        if (this.dataset == null || this.dataset.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            this.getDataSetAt(i).deleteDataAtXValue(removeBoundary, removeFromRight);
        }
    }
    
    public void deleteDataPoints(final int thisMany, final boolean removeFromRight) {
        if (this.dataset == null || this.dataset.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            this.getDataSetAt(i).deleteData(thisMany, removeFromRight);
        }
    }
    
    public double getXMaxData() {
        double max = 0.0;
        if (this.dataset == null | this.dataset.isEmpty()) {
            return max;
        }
        boolean maxNotSet = true;
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if (d.getSize() != 0) {
                if (maxNotSet) {
                    max = d.getMaxXValue() + d.getXShift();
                    maxNotSet = false;
                }
                else {
                    max = Math.max(max, d.getMaxXValue() + d.getXShift());
                }
            }
        }
        return max;
    }
    
    public double getYMaxData() {
        double max = 0.0;
        if (this.dataset == null | this.dataset.isEmpty()) {
            return max;
        }
        boolean maxNotSet = true;
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if (d.getSize() != 0) {
                if (maxNotSet) {
                    max = d.getMaxYValue() + d.getYShift();
                    maxNotSet = false;
                }
                else {
                    max = Math.max(max, d.getMaxYValue() + d.getYShift());
                }
            }
        }
        return max;
    }
    
    public double getXMinData() {
        double min = 0.0;
        if (this.dataset == null | this.dataset.isEmpty()) {
            return min;
        }
        boolean minNotSet = true;
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if (d.getSize() != 0) {
                if (minNotSet) {
                    min = d.getMinXValue() + d.getXShift();
                    minNotSet = false;
                }
                else {
                    min = Math.min(min, d.getMinXValue() + d.getXShift());
                }
            }
        }
        return min;
    }
    
    public double getYMinData() {
        double min = 0.0;
        if (this.dataset == null | this.dataset.isEmpty()) {
            return min;
        }
        boolean minNotSet = true;
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if (d.getSize() != 0) {
                if (minNotSet) {
                    min = d.getMinYValue() + d.getYShift();
                    minNotSet = false;
                }
                else {
                    min = Math.min(min, d.getMinYValue() + d.getYShift());
                }
            }
        }
        return min;
    }
    
    public void setGraphBackground(final Color c) {
        if (c == null) {
            return;
        }
        this.setBackground(c);
    }
    
    public void setBorders(final int top, final int left, final int bottom, final int right) {
        this.borderTop = top;
        this.borderBottom = bottom;
        this.borderLeft = left;
        this.borderRight = right;
    }
    
    public void setBorderTop(final int top) {
        this.borderTop = top;
    }
    
    public void setBorderLeft(final int left) {
        this.borderLeft = left;
    }
    
    public void setBorderRight(final int right) {
        this.borderRight = right;
    }
    
    public void setBorderBottom(final int bottom) {
        this.borderBottom = bottom;
    }
    
    public Color getDataBackground() {
        return this.dataBackgroundColor;
    }
    
    public void setDataBackground(final Color c) {
        this.dataBackgroundColor = c;
    }
    
    public void paint(final Graphics g) {
        final int iwidth = this.getSize().width;
        final int iheight = this.getSize().height;
        final Image osi = this.createImage(iwidth, iheight);
        final boolean usingDoubleBuffer = osi != null;
        Graphics tempGraphics = g;
        if (usingDoubleBuffer) {
            tempGraphics = osi.getGraphics();
        }
        tempGraphics.clipRect(0, 0, iwidth, iheight);
        final Rectangle drawingRectangle = this.getDrawingBounds(g);
        if (this.dataBackgroundColor == null) {
            this.dataBackgroundColor = this.getBackground();
        }
        this.paintFirst(tempGraphics, drawingRectangle);
        final Rectangle dataDrawingArea = this.calculateDataDrawingArea(tempGraphics, drawingRectangle);
        this.setDataDrawingArea(dataDrawingArea);
        this.forceMinimumViewSize();
        this.synchronizeSecondaryRanges();
        this.drawAllAxes(tempGraphics, dataDrawingArea);
        this.paintBeforeData(tempGraphics, dataDrawingArea);
        if (!this.dataset.isEmpty()) {
            for (int i = 0; i < this.dataset.size(); ++i) {
                this.getDataSetAt(i).draw(tempGraphics);
            }
        }
        this.paintLast(tempGraphics, dataDrawingArea);
        if (usingDoubleBuffer) {
            g.drawImage(osi, 0, 0, this);
            tempGraphics.dispose();
        }
    }
    
    private void setDataDrawingArea(final Rectangle dataDrawingArea) {
        final Range horiz = this.getHorizRange();
        if (horiz != null) {
            horiz.setPixelMin(dataDrawingArea.x);
            horiz.setPixelMax(dataDrawingArea.x + dataDrawingArea.width);
        }
        final Range vert = this.getVertRange();
        if (vert != null) {
            vert.setPixelMin(dataDrawingArea.y);
            vert.setPixelMax(dataDrawingArea.y + dataDrawingArea.height);
        }
    }
    
    private Rectangle getDrawingBounds(final Graphics g) {
        final Rectangle drawingRectangle = this.getBounds();
        drawingRectangle.x = 0;
        drawingRectangle.y = 0;
        final int legendPadding = this.showLegend ? this.legendTextLine.getAscent(g) : 0;
        final Rectangle rectangle = drawingRectangle;
        rectangle.x += this.borderLeft;
        final Rectangle rectangle2 = drawingRectangle;
        rectangle2.y += this.borderTop + legendPadding;
        final Rectangle rectangle3 = drawingRectangle;
        rectangle3.width -= this.borderLeft + this.borderRight;
        final Rectangle rectangle4 = drawingRectangle;
        rectangle4.height -= this.borderBottom + this.borderTop + legendPadding;
        return drawingRectangle;
    }
    
    public void paintFirst(final Graphics g, final Rectangle r) {
    }
    
    public void paintBeforeData(final Graphics g, final Rectangle r) {
        if (!this.showLegend) {
            return;
        }
        int legendOffset = 30;
        int legendWidth = 0;
        for (int i = 0; i < this.dataset.size(); ++i) {
            legendWidth = this.drawLegendForDataSeries(g, this.getDataSetAt(i), legendOffset, 7, 20);
            legendOffset += legendWidth + 30;
        }
    }
    
    public void paintLast(final Graphics g, final Rectangle r) {
    }
    
    public void update(final Graphics g) {
        final Color c = g.getColor();
        final Rectangle r = this.getBounds();
        r.x = 0;
        r.y = 0;
        g.setColor(this.getBackground());
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(c);
        this.paint(g);
    }
    
    private void forceMinimumViewSize() {
        final Range primeVert = this.getVertRange();
        double minY = primeVert.getValueMin();
        double maxY = primeVert.getValueMax();
        if (minY == maxY) {
            if (minY == 0.0) {
                minY = -1.0;
                maxY = 1.0;
            }
            else {
                final double temp = minY;
                minY = temp - 0.1 * temp;
                maxY = temp + 0.1 * temp;
            }
            primeVert.setValues(minY, maxY);
        }
        final Range primeHoriz = this.getHorizRange();
        double minX = primeHoriz.getValueMin();
        double maxX = primeHoriz.getValueMax();
        if (minX == maxX) {
            if (minX == 0.0) {
                minX = -1.0;
                maxX = 1.0;
            }
            else {
                final double temp2 = minX;
                minX = temp2 - 0.1 * temp2;
                maxX = temp2 + 0.1 * temp2;
            }
            primeHoriz.setValues(minX, maxX);
        }
    }
    
    public int getMinGraphLeftAxisWidth() {
        return this.minGraphLeftAxisWidth;
    }
    
    public void setMinGraphLeftAxisWidth(final int newmin) {
        this.minGraphLeftAxisWidth = newmin;
    }
    
    public void resetMinGraphLeftAxisWidth() {
        this.minGraphLeftAxisWidth = 0;
    }
    
    public int getMinGraphRightAxisWidth() {
        return this.minGraphRightAxisWidth;
    }
    
    public void setMinGraphRightAxisWidth(final int newmin) {
        this.minGraphRightAxisWidth = newmin;
    }
    
    public void resetMinGraphRightAxisWidth() {
        this.minGraphRightAxisWidth = 0;
    }
    
    public Rectangle getDataBounds() {
        return new Rectangle(this.getHorizRange().getPixelMin(), this.getVertRange().getPixelMin(), this.getHorizRange().getPixelRange(), this.getVertRange().getPixelRange());
    }
    
    public void setGridVisibility(final boolean horiz, final boolean vert) {
        if (this.axisTop != null) {
            this.axisTop.drawGrid(horiz);
        }
        if (this.axisBottom != null) {
            if (this.axisTop == null) {
                this.axisBottom.drawGrid(horiz);
            }
            else {
                this.axisBottom.drawGrid(false);
            }
        }
        if (this.axisLeft != null) {
            this.axisLeft.drawGrid(vert);
        }
        if (this.axisRight != null) {
            if (this.axisLeft == null) {
                this.axisRight.drawGrid(vert);
            }
            else {
                this.axisRight.drawGrid(false);
            }
        }
    }
    
    protected Rectangle calculateDataDrawingArea(final Graphics g, final Rectangle r) {
        int waxis = this.getMinGraphLeftAxisWidth();
        int x = r.x;
        int y = r.y;
        int width = r.width;
        int height = r.height;
        if (this.axisLeft != null) {
            waxis = this.axisLeft.calculateBreadth(g, this.getMinGraphLeftAxisWidth());
            if (waxis > this.getMinGraphLeftAxisWidth()) {
                this.setMinGraphLeftAxisWidth(waxis);
            }
        }
        x += waxis;
        width -= waxis;
        waxis = this.getMinGraphRightAxisWidth();
        if (this.axisRight != null) {
            waxis = this.axisRight.calculateBreadth(g, this.getMinGraphRightAxisWidth());
            if (waxis > this.getMinGraphRightAxisWidth()) {
                this.setMinGraphRightAxisWidth(waxis);
            }
        }
        width -= waxis;
        if (this.axisTop != null) {
            waxis = this.axisTop.calculateBreadth(g, 0);
            y += waxis;
            height -= waxis;
        }
        if (this.axisBottom != null) {
            waxis = this.axisBottom.calculateBreadth(g, 0);
            height -= waxis;
        }
        return new Rectangle(x, y, width, height);
    }
    
    protected void drawAllAxes(final Graphics g, final Rectangle dr) {
        final Color c = g.getColor();
        g.setColor(this.dataBackgroundColor);
        g.fillRect(dr.x, dr.y, dr.width, dr.height);
        g.setColor(c);
        if (this.axisLeft != null) {
            this.axisLeft.drawAxis(g, this, dr.x, dr.y, dr.height, dr.width);
        }
        if (this.axisRight != null) {
            this.axisRight.drawAxis(g, this, dr.x + dr.width, dr.y, dr.height, dr.width);
        }
        if (this.axisTop != null) {
            this.axisTop.drawAxis(g, this, dr.x, dr.y, dr.width, dr.height);
        }
        if (this.axisBottom != null) {
            this.axisBottom.drawAxis(g, this, dr.x, dr.y + dr.height, dr.width, dr.height);
        }
    }
    
    public void setLegendFont(final Font f) {
        if (f == null) {
            return;
        }
        this.legendTextLine.setFont(f);
    }
    
    public void setLegendColor(final Color c) {
        if (c == null) {
            return;
        }
        this.legendTextLine.setColor(c);
    }
    
    private int drawLegendForDataSeries(final Graphics g, final DataSet ds, final int x, final int y, final int legend_length) {
        String name = ds.getMetaData().getName();
        if (name == null) {
            name = "?";
        }
        this.legendTextLine.setText(name);
        this.legendTextLine.setJustification(1);
        final Color c = g.getColor();
        DrawLine graphPen = ds.getDrawLineHandler();
        if (graphPen == null) {
            graphPen = new DrawLine();
        }
        graphPen.resetLineState();
        graphPen.drawLine(g, x, y, x + legend_length, y);
        final Marker marker = ds.getMarker();
        if (ds.areMarkersEnabled() && marker != null) {
            marker.draw(g, 1, x + legend_length / 2, y);
        }
        this.legendTextLine.draw(g, x + legend_length + this.legendTextLine.charWidth(g, ' '), y + this.legendTextLine.getAscent(g) / 3);
        g.setColor(c);
        return this.legendTextLine.getWidth(g);
    }
    
    public void showLegend(final boolean show) {
        this.showLegend = show;
    }
    
    public void setMarkersEnabled(final boolean enabled) {
        for (int i = 0; i < this.dataset.size(); ++i) {
            this.getDataSetAt(i).setMarkersEnabled(enabled);
        }
    }
    
    public boolean areMarkersEnabled() {
        for (int i = 0; i < this.dataset.size(); ++i) {
            if (this.getDataSetAt(i).areMarkersEnabled()) {
                return true;
            }
        }
        return false;
    }
}
