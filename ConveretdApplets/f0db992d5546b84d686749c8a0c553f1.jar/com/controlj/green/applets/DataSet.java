// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import com.controlj.green.applets.label.LogRecordLabelGenerator;
import java.awt.Rectangle;
import com.controlj.green.applets.draw.LargePoint;
import java.awt.Color;
import com.controlj.green.applets.draw.DrawDigitalLine;
import java.awt.Graphics;
import com.controlj.green.applets.draw.VerticalRange;
import com.controlj.green.applets.draw.HorizontalRange;
import com.controlj.green.applets.draw.DrawLine;
import com.controlj.green.applets.draw.Range;
import com.controlj.green.applets.draw.LargeRectangle;
import com.controlj.green.applets.draw.Marker;

public class DataSet
{
    private MetaData metaData;
    private Marker marker;
    private static int GAP_SIZE;
    private int arrowTailLength;
    private int arrowHeadLength;
    private int arrowMarkerScale;
    private Marker leftArrowMarker;
    private Marker rightArrowMarker;
    private int markerscale;
    private boolean markersEnabled;
    private LargeRectangle currentDataView;
    private LargeRectangle dataExtents;
    private Range hrange;
    private Range vrange;
    private DrawLine graphPen;
    private DrawLine logstatusPen;
    private DrawLine timechangePen;
    private DrawLine historianPen;
    private DrawLine questionablePen;
    private DrawLine specialPen;
    private BNLogRecord[] bndata;
    private boolean historicalOnly;
    private int lastHistoricalData;
    
    public boolean isHistoricalOnly() {
        return this.historicalOnly;
    }
    
    public void setHistoricalOnly(final boolean historicalOnly) {
        this.historicalOnly = historicalOnly;
    }
    
    public DataSet(final BNLogRecord[] d) throws Exception {
        this.metaData = new MetaData();
        this.marker = null;
        this.arrowTailLength = 13;
        this.arrowHeadLength = 3;
        this.arrowMarkerScale = 1;
        (this.leftArrowMarker = new Marker()).addVertex(new Marker.Vertex(true, 0, 3));
        this.leftArrowMarker.addVertex(new Marker.Vertex(true, this.arrowHeadLength, 0));
        this.leftArrowMarker.addVertex(new Marker.Vertex(true, this.arrowHeadLength, 2));
        this.leftArrowMarker.addVertex(new Marker.Vertex(true, this.arrowHeadLength + this.arrowTailLength, 3));
        this.leftArrowMarker.addVertex(new Marker.Vertex(true, this.arrowHeadLength, 4));
        this.leftArrowMarker.addVertex(new Marker.Vertex(true, this.arrowHeadLength, 6));
        this.leftArrowMarker.addVertex(new Marker.Vertex(true, 0, 3));
        (this.rightArrowMarker = new Marker()).addVertex(new Marker.Vertex(true, 0, 3));
        this.rightArrowMarker.addVertex(new Marker.Vertex(true, this.arrowTailLength, 2));
        this.rightArrowMarker.addVertex(new Marker.Vertex(true, this.arrowTailLength, 0));
        this.rightArrowMarker.addVertex(new Marker.Vertex(true, this.arrowTailLength + this.arrowHeadLength, 3));
        this.rightArrowMarker.addVertex(new Marker.Vertex(true, this.arrowTailLength, 6));
        this.rightArrowMarker.addVertex(new Marker.Vertex(true, this.arrowTailLength, 4));
        this.rightArrowMarker.addVertex(new Marker.Vertex(true, 0, 3));
        this.markerscale = 1;
        this.markersEnabled = false;
        this.currentDataView = new LargeRectangle();
        this.dataExtents = new LargeRectangle();
        this.hrange = new HorizontalRange();
        this.vrange = new VerticalRange();
        this.graphPen = null;
        this.logstatusPen = null;
        this.timechangePen = null;
        this.historianPen = null;
        this.questionablePen = null;
        this.specialPen = null;
        this.historicalOnly = false;
        if (d == null) {
            throw new Exception("DataSet: Error in parsed data!");
        }
        this.bndata = d;
        this.sortData();
        this.calculateRange();
    }
    
    public void draw(final Graphics g) {
        if (this.getHistoricalSize() <= 0) {
            return;
        }
        this.currentDataView.x = this.hrange.getValueMin();
        this.currentDataView.width = this.hrange.getValueRange();
        this.currentDataView.y = this.vrange.getValueMin();
        this.currentDataView.height = this.vrange.getValueRange();
        g.clipRect(this.hrange.getPixelMin(), this.vrange.getPixelMin(), this.hrange.getPixelRange() + 1, this.vrange.getPixelRange());
        final Color c = g.getColor();
        if (this.graphPen == null) {
            this.graphPen = new DrawLine();
        }
        this.graphPen.resetLineState();
        final Color gpColor = this.graphPen.getColor();
        if (this.specialPen == null) {
            this.specialPen = new DrawLine(1, 24, 1, this.graphPen.getColor());
        }
        this.specialPen.setColor(gpColor);
        this.specialPen.resetLineState();
        if (this.logstatusPen == null) {
            this.logstatusPen = new DrawLine(1, 24, 1, this.graphPen.getColor());
        }
        this.logstatusPen.setColor(gpColor);
        this.logstatusPen.resetLineState();
        if (this.timechangePen == null) {
            this.timechangePen = new DrawLine(1, 24, 1, this.graphPen.getColor());
        }
        this.timechangePen.setColor(gpColor);
        this.timechangePen.resetLineState();
        if (this.historianPen == null) {
            this.historianPen = new DrawLine(1, 24, 1, this.graphPen.getColor());
        }
        this.historianPen.setColor(gpColor);
        this.historianPen.resetLineState();
        if (this.questionablePen == null) {
            final int type = 1;
            if (this.graphPen instanceof DrawDigitalLine) {
                this.questionablePen = new DrawDigitalLine(new DrawLine.QuestionMarkMaker(15, type), this.graphPen.getColor());
            }
            else {
                this.questionablePen = new DrawLine(new DrawLine.QuestionMarkMaker(15, type), this.graphPen.getColor());
            }
            this.questionablePen.centerPatternOnLine(true);
        }
        this.questionablePen.setColor(gpColor);
        this.questionablePen.resetLineState();
        this.leftArrowMarker.setColor(gpColor);
        this.rightArrowMarker.setColor(gpColor);
        this.drawData(g, this.graphPen);
        g.setColor(c);
    }
    
    public double getMaxXValue() {
        return this.dataExtents.x + this.dataExtents.width;
    }
    
    public double getMinXValue() {
        return this.dataExtents.x;
    }
    
    public double getMaxYValue() {
        return this.dataExtents.y + this.dataExtents.height;
    }
    
    public double getMinYValue() {
        return this.dataExtents.y;
    }
    
    public double getMaxYValueInRange(final double xmin, final double xmax) {
        if (this.getSize() < 1) {
            return this.vrange.getValueMax();
        }
        int pointMin = 0;
        if (xmin > this.getMinXValue()) {
            pointMin = this.findFirstXValueLessThan(xmin);
        }
        final int pointMax = this.findFirstXValueGreaterThan(xmax);
        double largest = this.getPoint(pointMin).getYValue();
        ++pointMin;
        while (pointMin < pointMax) {
            largest = Math.max(largest, this.getPoint(pointMin).getYValue());
            ++pointMin;
        }
        return largest;
    }
    
    public double getMinYValueInRange(final double xmin, final double xmax) {
        if (this.getSize() < 1) {
            return this.vrange.getValueMin();
        }
        int pointMin = 0;
        if (xmin > this.getMinXValue()) {
            pointMin = this.findFirstXValueLessThan(xmin);
        }
        int pointMax = this.getSize() - 1;
        if (xmax < this.getMaxXValue()) {
            pointMax = this.findFirstXValueGreaterThan(xmax);
        }
        double smallest = this.getPoint(pointMin).getYValue();
        ++pointMin;
        while (pointMin < pointMax) {
            smallest = Math.min(smallest, this.getPoint(pointMin).getYValue());
            ++pointMin;
        }
        return smallest;
    }
    
    public int getHistoricalSize() {
        if (this.bndata == null) {
            return 0;
        }
        if (this.historicalOnly) {
            return this.lastHistoricalData + 1;
        }
        return this.bndata.length;
    }
    
    public int getSize() {
        if (this.bndata == null) {
            return 0;
        }
        return this.bndata.length;
    }
    
    public BNLogRecord getPoint(final int index) {
        return this.bndata[index];
    }
    
    public BNLogRecord[] getData() {
        return this.bndata;
    }
    
    public MetaData getMetaData() {
        return this.metaData;
    }
    
    public void setDrawLineHandler(final DrawLine dl) {
        this.graphPen = dl;
    }
    
    public DrawLine getDrawLineHandler() {
        return this.graphPen;
    }
    
    public void setLineColor(final Color c) {
        this.graphPen.setColor(c);
    }
    
    public void setXShift(final double shift) {
        this.hrange.setValueShift(shift);
    }
    
    public double getXShift() {
        return this.hrange.getValueShift();
    }
    
    public Range getHorizRange() {
        return this.hrange;
    }
    
    public void setHorizontalRange(final Range hrange) {
        this.hrange = hrange;
    }
    
    public Range getVertRange() {
        return this.vrange;
    }
    
    public void setVerticalRange(final Range vrange) {
        this.vrange = vrange;
    }
    
    public double getYShift() {
        return this.vrange.getValueShift();
    }
    
    public void setMarkerScale(final int scale) {
        this.markerscale = scale;
    }
    
    public int getMarkerScale() {
        return this.markerscale;
    }
    
    public void setMarker(final Marker m) {
        this.marker = m;
    }
    
    public Marker getMarker() {
        return this.marker;
    }
    
    public void setMarkersEnabled(final boolean enabled) {
        this.markersEnabled = enabled;
    }
    
    public boolean areMarkersEnabled() {
        return this.markersEnabled;
    }
    
    public BNLogRecord getClosestPoint(final int x, final int y) {
        if (this.getSize() <= 0) {
            return null;
        }
        BNLogRecord point = null;
        point = this.getPoint(0);
        double smallestDist = this.getPixelDistForComparison(this.getPoint(0), x, y);
        for (int i = 1; i < this.getHistoricalSize(); ++i) {
            final double currentDist = this.getPixelDistForComparison(this.getPoint(i), x, y);
            if (currentDist < smallestDist) {
                smallestDist = currentDist;
                point = this.getPoint(i);
            }
        }
        return point;
    }
    
    protected double getPixelDistForComparison(final BNLogRecord point, final int x, final int y) {
        if (point.isSpecial()) {
            final double spDiff = this.hrange.getPixelFromValue(point.getXValue()) - x;
            return spDiff * spDiff;
        }
        final double normXDiff = this.hrange.getPixelFromValue(point.getXValue()) - x;
        final double normYDiff = this.vrange.getPixelFromValue(point.getYValue()) - y;
        return normXDiff * normXDiff + normYDiff * normYDiff;
    }
    
    public double getPixelDistFromPoint(final BNLogRecord point, final int x, final int y) {
        return Math.sqrt(this.getPixelDistForComparison(point, x, y));
    }
    
    protected void drawData(final Graphics graphics, final DrawLine graphpen) {
        final Rectangle clip = graphics.getClipBounds();
        if (this.getHistoricalSize() < 1) {
            return;
        }
        int firstpoint = this.findFirstXValueLessThan(this.currentDataView.x);
        if (firstpoint < 0) {
            firstpoint = 0;
        }
        BNLogRecord datapoint0 = this.getPoint(firstpoint);
        if (firstpoint == 0 && this.getMetaData().hasMoreDataToLeft) {
            this.leftArrowMarker.drawFilledMarker(graphics, this.arrowMarkerScale, this.hrange.getPixelFromValue(datapoint0.getXValue()) - (this.arrowHeadLength + this.arrowTailLength) * this.arrowMarkerScale, this.vrange.getPixelFromValue(datapoint0.getYValue()) - 3 * this.arrowMarkerScale);
        }
        final double maxX = this.currentDataView.x + this.currentDataView.width;
        int i;
        for (i = firstpoint; i < this.getHistoricalSize(); ++i) {
            final BNLogRecord datapoint2 = this.getPoint(i);
            this.drawPointLine(graphics, datapoint2, clip, i);
            this.drawMarker(datapoint0, graphics);
            datapoint0 = datapoint2;
            if (this.getPoint(i).getXValue() > maxX) {
                break;
            }
        }
        BNLogRecord lastPoint = null;
        if (i > 1) {
            lastPoint = this.getPoint(i - 1);
        }
        else {
            lastPoint = this.getPoint(0);
        }
        this.drawMarker(lastPoint, graphics);
        if (this.getMetaData().isCov() && !this.getMetaData().hasMoreDataToRight && lastPoint == this.getPoint(this.getHistoricalSize() - 1)) {
            final double systemTimeValue = this.getMetaData().getCurrentSystemTimeValue();
            if (systemTimeValue > lastPoint.getXValue()) {
                final LargePoint lp0 = this.getMyPosition(this.getHistoricalSize() - 1, true);
                if (lp0 != null) {
                    final LargePoint lp2 = new LargePoint(systemTimeValue, lp0.y);
                    this.drawLine(graphics, graphpen, lp0, lp2);
                }
            }
        }
        if (this.getMetaData().hasMoreDataToRight && lastPoint == this.getPoint(this.getHistoricalSize() - 1) && !lastPoint.isSpecial()) {
            this.rightArrowMarker.drawFilledMarker(graphics, 1, this.hrange.getPixelFromValue(lastPoint.getXValue()), this.vrange.getPixelFromValue(lastPoint.getYValue()) - 6 * this.arrowMarkerScale);
        }
    }
    
    protected void drawLine(final Graphics graphics, final DrawLine pen, final LargePoint p1, final LargePoint p2) {
        if (pen.clipDataToExtents(this.currentDataView, p1, p2)) {
            pen.drawLine(graphics, this.hrange.getPixelFromValue(p1.x), this.vrange.getPixelFromValue(p1.y), this.hrange.getPixelFromValue(p2.x), this.vrange.getPixelFromValue(p2.y));
        }
    }
    
    protected void drawPointLine(final Graphics graphics, final BNLogRecord bnrecord, final Rectangle clip, final int pointpos) {
        final int x = this.hrange.getPixelFromValue(bnrecord.getXValue());
        if (bnrecord.isTimeChange()) {
            this.timechangePen.drawLine(graphics, x, clip.y, x, clip.y + clip.height);
            this.timechangePen.resetLineState();
        }
        else if (bnrecord.isLogStatus()) {
            this.logstatusPen.drawLine(graphics, x, clip.y, x, clip.y + clip.height);
            this.logstatusPen.resetLineState();
        }
        else if (bnrecord.isALCStatus()) {
            this.historianPen.drawLine(graphics, x, clip.y, x, clip.y + clip.height);
            this.historianPen.resetLineState();
        }
        else if (bnrecord.isSpecial()) {
            this.specialPen.drawLine(graphics, x, clip.y, x, clip.y + clip.height);
            this.specialPen.resetLineState();
        }
        final LargePoint prevPoint = this.getMyPosition(pointpos - 1, true);
        final LargePoint point = this.getMyPosition(pointpos, false);
        if (prevPoint != null && point != null) {
            this.drawLine(graphics, this.graphPen, prevPoint, point);
        }
    }
    
    private LargePoint getPreviousNormalPoint(final int pointPos) {
        for (int startPos = pointPos - 1; startPos >= 0; --startPos) {
            final BNLogRecord startRecord = this.getPoint(startPos);
            if (!startRecord.isSpecial()) {
                return new LargePoint(startRecord.getXValue(), startRecord.getYValue());
            }
        }
        return null;
    }
    
    private LargePoint getNextNormalPoint(final int pointPos) {
        for (int startPos = pointPos + 1; startPos < this.getSize(); ++startPos) {
            final BNLogRecord startRecord = this.getPoint(startPos);
            if (!startRecord.isSpecial()) {
                return new LargePoint(startRecord.getXValue(), startRecord.getYValue());
            }
        }
        if (this.getMetaData().isCov() && !this.getMetaData().hasMoreDataToRight && this.getSize() > 0 && this.getMetaData().getCurrentSystemTimeValue() > this.getPoint(this.getSize() - 1).getXValue()) {
            final LargePoint lp0 = this.getPreviousNormalPoint(pointPos);
            return new LargePoint(this.getMetaData().getCurrentSystemTimeValue(), lp0.y);
        }
        return null;
    }
    
    private LargePoint getMyPosition(final int pointPos, final boolean isStart) {
        if (pointPos < 0 || pointPos >= this.getSize()) {
            return null;
        }
        final BNLogRecord point = this.getPoint(pointPos);
        if (!point.isSpecial()) {
            return new LargePoint(point.getXValue(), point.getYValue());
        }
        final LargePoint start = this.getPreviousNormalPoint(pointPos);
        final LargePoint end = this.getNextNormalPoint(pointPos);
        if (start == null || end == null) {
            return null;
        }
        if (point.isTimeChange()) {
            final double xValueGap = Math.abs(this.hrange.getValueFromPixel(0) - this.hrange.getValueFromPixel(DataSet.GAP_SIZE));
            if (xValueGap < end.x - start.x) {
                double x = point.getXValue();
                if (isStart) {
                    x += xValueGap;
                }
                else {
                    x -= xValueGap;
                }
                return this.graphPen.getDataPointYOnLineAtX(x, start, end);
            }
        }
        else if (point.isLogStatus() || point.isFailure()) {
            return null;
        }
        return this.graphPen.getDataPointYOnLineAtX(point.getXValue(), start, end);
    }
    
    protected void drawMarker(final BNLogRecord record, final Graphics g) {
        if ((this.markersEnabled || this.graphPen.getPatternMaker() == null) && this.marker != null && !record.isSpecial()) {
            if (record.hasAnyFlag()) {
                this.marker.draw(g, this.markerscale * 2, this.hrange.getPixelFromValue(record.getXValue()), this.vrange.getPixelFromValue(record.getYValue()));
            }
            else {
                this.marker.draw(g, this.markerscale, this.hrange.getPixelFromValue(record.getXValue()), this.vrange.getPixelFromValue(record.getYValue()));
            }
        }
    }
    
    public int findFirstXValueLessThan(final double value) {
        if (this.getSize() <= 0 || this.getPoint(0).getXValue() > value) {
            return -1;
        }
        int low = 0;
        int high = this.getSize() - 1;
        int middle = (int)Math.floor((high - low) / 2);
        while (high - low >= 2) {
            if (this.getPoint(middle).getXValue() < value) {
                low = middle;
            }
            else {
                if (this.getPoint(middle).getXValue() <= value) {
                    return middle;
                }
                high = middle;
            }
            middle = low + (int)Math.floor((high - low) / 2);
        }
        if (this.getPoint(high).getXValue() < value) {
            return high;
        }
        return low;
    }
    
    public int findFirstXValueGreaterThan(final double value) {
        if (this.getSize() <= 0 || this.getPoint(this.getSize() - 1).getXValue() < value) {
            return -1;
        }
        final int index = this.findFirstXValueLessThan(value);
        if (index == -1) {
            return 0;
        }
        if (this.getPoint(index).getXValue() > value) {
            return index;
        }
        return index + 1;
    }
    
    public boolean isRecordInView(final BNLogRecord record) {
        return record.getXValue() >= this.currentDataView.x && record.getXValue() <= this.currentDataView.getOtherX() && record.getYValue() >= this.currentDataView.y && record.getYValue() <= this.currentDataView.getOtherY();
    }
    
    protected void calculateRange() {
        if (this.getSize() == 0) {
            final LargeRectangle dataExtents = this.dataExtents;
            final LargeRectangle dataExtents2 = this.dataExtents;
            final double n = 0.0;
            dataExtents2.width = n;
            dataExtents.x = n;
            final LargeRectangle dataExtents3 = this.dataExtents;
            final LargeRectangle dataExtents4 = this.dataExtents;
            final double n2 = 0.0;
            dataExtents4.height = n2;
            dataExtents3.y = n2;
            this.lastHistoricalData = -1;
            return;
        }
        final LargeRectangle dataExtents5 = this.dataExtents;
        final LargeRectangle dataExtents6 = this.dataExtents;
        final double xValue = this.getPoint(0).getXValue();
        dataExtents6.width = xValue;
        dataExtents5.x = xValue;
        boolean yNotInitialized = true;
        boolean needLastHistorical = true;
        this.lastHistoricalData = -1;
        for (int i = 0; i < this.getSize(); ++i) {
            final BNLogRecord point = this.getPoint(i);
            if (this.dataExtents.width < point.getXValue()) {
                this.dataExtents.width = point.getXValue();
            }
            else if (this.dataExtents.x > point.getXValue()) {
                this.dataExtents.x = point.getXValue();
            }
            if (!point.isSpecial()) {
                if (yNotInitialized) {
                    final LargeRectangle dataExtents7 = this.dataExtents;
                    final LargeRectangle dataExtents8 = this.dataExtents;
                    final double yValue = point.getYValue();
                    dataExtents8.height = yValue;
                    dataExtents7.y = yValue;
                    yNotInitialized = false;
                }
                if (this.dataExtents.height < point.getYValue()) {
                    this.dataExtents.height = point.getYValue();
                }
                else if (this.dataExtents.y > point.getYValue()) {
                    this.dataExtents.y = point.getYValue();
                }
            }
            if (needLastHistorical) {
                if (point.isHistorical()) {
                    this.lastHistoricalData = i;
                }
                else {
                    needLastHistorical = false;
                }
            }
        }
        this.dataExtents.width -= this.dataExtents.x;
        this.dataExtents.height -= this.dataExtents.y;
    }
    
    public void appendData(final BNLogRecord[] newData) {
        this.appendOrReplaceData(newData, false);
    }
    
    public void appendOrReplaceData(final BNLogRecord[] newData, final boolean replaceIfNoIntersect) {
        if (newData == null || newData.length == 0) {
            return;
        }
        this.sort(newData, 0, newData.length - 1);
        if (this.bndata.length == 0) {
            System.arraycopy(newData, 0, this.bndata = new BNLogRecord[newData.length], 0, newData.length);
        }
        else {
            final double startTimeOfNew = newData[0].getXValue();
            final double endTimeOfNew = newData[newData.length - 1].getXValue();
            final double startTimeOfCurrent = this.bndata[0].getXValue();
            final double endTimeOfCurrent = this.bndata[this.bndata.length - 1].getXValue();
            BNLogRecord[] tempArray = new BNLogRecord[0];
            if (endTimeOfCurrent < startTimeOfNew) {
                if (replaceIfNoIntersect) {
                    tempArray = new BNLogRecord[newData.length];
                    System.arraycopy(newData, 0, tempArray, 0, newData.length);
                }
                else {
                    tempArray = new BNLogRecord[this.bndata.length + newData.length];
                    System.arraycopy(this.bndata, 0, tempArray, 0, this.bndata.length);
                    System.arraycopy(newData, 0, tempArray, this.bndata.length, newData.length);
                }
            }
            else if (startTimeOfCurrent > endTimeOfNew) {
                if (replaceIfNoIntersect) {
                    tempArray = new BNLogRecord[newData.length];
                    System.arraycopy(newData, 0, tempArray, 0, newData.length);
                }
                else {
                    tempArray = new BNLogRecord[this.bndata.length + newData.length];
                    System.arraycopy(newData, 0, tempArray, 0, newData.length);
                    System.arraycopy(this.bndata, 0, tempArray, newData.length, this.bndata.length);
                }
            }
            else if (startTimeOfCurrent < startTimeOfNew && endTimeOfCurrent > endTimeOfNew) {
                final int endOfStart = this.findFirstXValueLessThan(startTimeOfNew) + 1;
                final int startOfEnd = this.findFirstXValueGreaterThan(endTimeOfNew);
                final int length = endOfStart + newData.length + (this.bndata.length - startOfEnd);
                tempArray = new BNLogRecord[length];
                System.arraycopy(this.bndata, 0, tempArray, 0, endOfStart);
                System.arraycopy(newData, 0, tempArray, endOfStart, newData.length);
                System.arraycopy(this.bndata, startOfEnd, tempArray, endOfStart + newData.length, this.bndata.length - startOfEnd);
            }
            else if (startTimeOfCurrent < startTimeOfNew && startTimeOfNew <= endTimeOfCurrent) {
                final int endOfStart = this.findFirstXValueLessThan(startTimeOfNew) + 1;
                tempArray = new BNLogRecord[newData.length + endOfStart];
                System.arraycopy(this.bndata, 0, tempArray, 0, endOfStart);
                System.arraycopy(newData, 0, tempArray, endOfStart, newData.length);
            }
            else if (startTimeOfCurrent <= endTimeOfNew && endTimeOfNew < endTimeOfCurrent) {
                final int startOfEnd2 = this.findFirstXValueGreaterThan(endTimeOfNew);
                tempArray = new BNLogRecord[newData.length + this.bndata.length - startOfEnd2];
                System.arraycopy(newData, 0, tempArray, 0, newData.length);
                System.arraycopy(this.bndata, startOfEnd2, tempArray, newData.length, this.bndata.length - startOfEnd2);
            }
            else {
                tempArray = new BNLogRecord[newData.length];
                System.arraycopy(newData, 0, tempArray, 0, newData.length);
            }
            if (TrendPlot.traceLevel >= 2) {
                TrendPlot.trace(this.getClass(), "appendOrReplaceData to " + this.getMetaData().pathtodata + ": orig=" + this.bndata.length + " appended=" + newData.length + " new=" + tempArray.length);
            }
            this.bndata = tempArray;
        }
        this.sortData();
        this.calculateRange();
    }
    
    public void deleteData(final int del, final boolean fromEnd) {
        final int length = this.getSize();
        if (length <= 0 || del <= 0) {
            return;
        }
        if (del >= length) {
            this.bndata = new BNLogRecord[0];
        }
        final BNLogRecord[] temp = new BNLogRecord[length - del];
        if (fromEnd) {
            this.metaData.hasMoreDataToRight = true;
            System.arraycopy(this.bndata, 0, temp, 0, length - del);
        }
        else {
            System.arraycopy(this.bndata, del, temp, 0, length - del);
            this.metaData.hasMoreDataToLeft = true;
        }
        this.bndata = temp;
        this.calculateRange();
    }
    
    public void deleteDataAtXValue(final double value, final boolean deleteFromRight) {
        if (this.getSize() <= 0) {
            return;
        }
        if (deleteFromRight) {
            final int pos = this.findFirstXValueGreaterThan(value);
            if (pos > 0) {
                this.deleteData(this.getSize() - pos, deleteFromRight);
            }
        }
        else {
            final int pos = this.findFirstXValueLessThan(value);
            if (pos > 0) {
                this.deleteData(this.findFirstXValueLessThan(value), deleteFromRight);
            }
        }
    }
    
    public void deleteAllData() {
        this.bndata = new BNLogRecord[0];
        this.calculateRange();
    }
    
    void sortData() {
        if (this.getSize() > 0) {
            this.sort(this.bndata, 0, this.getSize() - 1);
        }
    }
    
    private void oldSort(final BNLogRecord[] sortMe, final int low, final int high) {
        int lo = low;
        int hi = high;
        if (lo >= hi) {
            return;
        }
        if (lo == hi - 1) {
            if (sortMe[lo].getXValue() > sortMe[hi].getXValue()) {
                final BNLogRecord T = sortMe[lo];
                sortMe[lo] = sortMe[hi];
                sortMe[hi] = T;
            }
            return;
        }
        final BNLogRecord pivot = sortMe[(lo + hi) / 2];
        sortMe[(lo + hi) / 2] = sortMe[hi];
        sortMe[hi] = pivot;
        while (lo < hi) {
            while (sortMe[lo].getXValue() <= pivot.getXValue() && lo < hi) {
                ++lo;
            }
            while (pivot.getXValue() <= sortMe[hi].getXValue() && lo < hi) {
                --hi;
            }
            if (lo < hi) {
                final BNLogRecord T2 = sortMe[lo];
                sortMe[lo] = sortMe[hi];
                sortMe[hi] = T2;
            }
        }
        sortMe[high] = sortMe[hi];
        sortMe[hi] = pivot;
        this.sort(sortMe, low, lo - 1);
        this.sort(sortMe, hi + 1, high);
    }
    
    private void sort(final BNLogRecord[] sortMe, final int low, final int high) {
        int lo = low;
        int hi = high;
        if (lo >= hi) {
            return;
        }
        if (lo == hi - 1) {
            if (sortMe[lo].compareTo(sortMe[hi]) == 1) {
                final BNLogRecord T = sortMe[lo];
                sortMe[lo] = sortMe[hi];
                sortMe[hi] = T;
            }
            return;
        }
        final BNLogRecord pivot = sortMe[(lo + hi) / 2];
        sortMe[(lo + hi) / 2] = sortMe[hi];
        sortMe[hi] = pivot;
        while (lo < hi) {
            while (sortMe[lo].compareTo(pivot) <= 0 && lo < hi) {
                ++lo;
            }
            while (pivot.compareTo(sortMe[hi]) <= 0 && lo < hi) {
                --hi;
            }
            if (lo < hi) {
                final BNLogRecord T2 = sortMe[lo];
                sortMe[lo] = sortMe[hi];
                sortMe[hi] = T2;
            }
        }
        sortMe[high] = sortMe[hi];
        sortMe[hi] = pivot;
        this.sort(sortMe, low, lo - 1);
        this.sort(sortMe, hi + 1, high);
    }
    
    public String toDebugString() {
        final StringBuffer strbuff = new StringBuffer();
        strbuff.append(this.metaData.name);
        strbuff.append("\n");
        for (int size = this.getSize(), i = 0; i < size; ++i) {
            strbuff.append(i + 1 + "\t");
            strbuff.append(this.getPoint(i).toDebugString());
        }
        return strbuff.toString();
    }
    
    public String toString() {
        final StringBuffer strbuff = new StringBuffer();
        strbuff.append(this.metaData.name);
        strbuff.append("\n");
        for (int size = this.getSize(), i = 0; i < size; ++i) {
            strbuff.append("\t");
            strbuff.append(this.getPoint(i).toString());
        }
        return strbuff.toString();
    }
    
    public String toStringRange(final double start, final double end) {
        final StringBuffer strbuff = new StringBuffer();
        strbuff.append(this.metaData.name);
        strbuff.append("\n");
        final int startIndex = this.findFirstXValueGreaterThan(start);
        final int endIndex = this.findFirstXValueLessThan(end);
        int index = startIndex;
        while (index <= endIndex) {
            strbuff.append("\t");
            strbuff.append(this.getPoint(index++).toString());
        }
        return strbuff.toString();
    }
    
    static {
        DataSet.GAP_SIZE = 10;
    }
    
    public static class MetaData
    {
        public boolean hasMoreDataToLeft;
        public boolean hasMoreDataToRight;
        public String pathtodata;
        private int dataType;
        private String name;
        private boolean isCOV;
        private double currentTime;
        private LogRecordLabelGenerator pointLabelGen;
        
        public MetaData() {
            this.hasMoreDataToLeft = false;
            this.hasMoreDataToRight = false;
            this.name = null;
            this.isCOV = false;
            this.currentTime = 0.0;
            this.pointLabelGen = null;
        }
        
        public String toString() {
            return "Dataset=" + this.pathtodata + " moreonRight=" + this.hasMoreDataToRight + " moreonLeft=" + this.hasMoreDataToLeft;
        }
        
        public String getName() {
            return this.name;
        }
        
        public void setName(final String newname) {
            this.name = newname;
        }
        
        public int getType() {
            return this.dataType;
        }
        
        public void setType(final int t) {
            this.dataType = t;
        }
        
        public boolean isMultiState() {
            return 3 == this.dataType || 4 == this.dataType;
        }
        
        public boolean isBoolean() {
            return 1 == this.dataType || (this.isBinary() && !this.isMultiState());
        }
        
        public boolean isBinary() {
            return 3 == this.dataType || 4 == this.dataType || 1 == this.dataType || (10 == this.dataType && this.hasStateText());
        }
        
        public boolean isAnalog() {
            return 2 == this.dataType || 5 == this.dataType || 6 == this.dataType || (10 == this.dataType && !this.hasStateText());
        }
        
        public void setHasMoreData(final boolean dataToLeft, final boolean dataToRight) {
            this.hasMoreDataToLeft = dataToLeft;
            this.hasMoreDataToRight = dataToRight;
        }
        
        public void setLabelGenerator(final LogRecordLabelGenerator labelgen) {
            this.pointLabelGen = labelgen;
        }
        
        public LogRecordLabelGenerator getLabelGenerator() {
            return this.pointLabelGen;
        }
        
        public boolean hasStateText() {
            if (this.pointLabelGen != null && this.pointLabelGen.getStateText() != null) {
                final String[] arr = this.pointLabelGen.getStateText();
                for (int i = 0; i < arr.length; ++i) {
                    final String s = arr[i];
                    if (s != null && s.length() > 0) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        public void isCov(final boolean cov) {
            this.isCOV = cov;
        }
        
        public boolean isCov() {
            return this.isCOV;
        }
        
        public double getCurrentSystemTimeValue() {
            return this.currentTime;
        }
        
        public void setCurrentSystemTimeValue(final double time) {
            this.currentTime = time;
        }
    }
}
