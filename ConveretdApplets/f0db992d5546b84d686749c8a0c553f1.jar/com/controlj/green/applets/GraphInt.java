// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import com.controlj.green.applets.label.TextLine;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;
import com.controlj.green.applets.comm.TrendDataRequest;
import com.controlj.green.applets.draw.DrawDigitalLine;
import com.controlj.green.applets.draw.DrawLine;
import com.controlj.green.applets.label.LogRecordLabelGenerator;
import java.awt.Color;
import com.controlj.green.applets.draw.Marker;
import com.controlj.green.applets.label.LabelGenerator;
import com.controlj.green.applets.label.DateLabelGenerator;
import com.controlj.green.applets.draw.VerticalRange;
import com.controlj.green.applets.draw.Range;
import com.controlj.green.applets.draw.HorizontalRange;
import com.controlj.green.applets.draw.Markers;
import java.util.TimeZone;
import java.util.Locale;
import java.awt.Font;
import com.controlj.green.applets.draw.LargeRectangle;

public class GraphInt extends Graph2D
{
    private LargeRectangle initialDataView;
    public boolean yAutoscale;
    public boolean xAutoscale;
    Font ctrlClickFont;
    GraphHistory history;
    
    public GraphInt() {
        this.initialDataView = new LargeRectangle();
        this.yAutoscale = true;
        this.xAutoscale = true;
        this.history = new GraphHistory(10);
    }
    
    public void initialize(final TrendProperties.SubGraph subgraphProps, final TrendData.SubGraph subgraphObject, final boolean autoScaleXAxis, final boolean enableGrid, final boolean isFirstSubgraphInApplet, final boolean isLastSubgraphInApplet, final Locale loc, final TimeZone timeZone, final int standardDate, final boolean standardTime, final String separator, final TrendResource labelText, final Markers markers) throws Exception {
        this.xAutoscale = autoScaleXAxis;
        this.yAutoscale = subgraphProps.yAxis_AutoScale;
        this.setBorderLeft(10);
        this.setBorderRight(10);
        if (subgraphProps.showLegend) {
            this.showLegend(true);
        }
        boolean hasAnalog = false;
        boolean hasBinary = false;
        for (int j = 0; j < subgraphObject.getNumDataSeries(); ++j) {
            final TrendData.DataSeries dataSeries = subgraphObject.getDataSeries(j);
            final BNLogRecord[] records = dataSeries.getRecords();
            final DataSet datasetobj = new DataSet(records);
            if (subgraphProps.getDataSeries(j) != null && subgraphProps.getDataSeries(j).isSourcePathIsValid()) {
                datasetobj.getMetaData().setHasMoreData(dataSeries.hasMoreDataToLeft, dataSeries.hasMoreDataToRight);
                datasetobj.getMetaData().setCurrentSystemTimeValue(dataSeries.getCurrentSystemTime());
            }
            else {
                datasetobj.getMetaData().setHasMoreData(false, false);
            }
            this.setDataSetProperties(datasetobj, subgraphProps.getDataSeries(j), dataSeries.getType(), timeZone, loc, standardDate, standardTime, separator, labelText, markers);
            this.attachDataSet(datasetobj);
            if (datasetobj.getMetaData().isAnalog()) {
                hasAnalog = true;
            }
            else {
                hasBinary = true;
            }
        }
        final Axis graphXaxis = new Axis(new HorizontalRange());
        this.setAxisProperties(graphXaxis, subgraphProps, loc, timeZone, standardDate, standardTime, separator, isLastSubgraphInApplet, enableGrid);
        this.attachBottomAxis(graphXaxis);
        if (hasBinary && hasAnalog) {
            final Axis graphYaxis = new Axis(new VerticalRange());
            this.setAxisProperties(graphYaxis, subgraphProps, loc, timeZone, standardDate, standardTime, separator, isLastSubgraphInApplet, enableGrid);
            this.attachLeftAxis(graphYaxis);
            final CompositeAxis newYAxis = new CompositeAxis(new VerticalRange());
            this.setAxisProperties(newYAxis, subgraphProps, loc, timeZone, standardDate, standardTime, separator, isLastSubgraphInApplet, enableGrid);
            newYAxis.setTitleText("");
            newYAxis.drawGrid(false);
            this.attachRightAxis(newYAxis);
        }
        else if (hasBinary) {
            final CompositeAxis newYAxis2 = new CompositeAxis(new VerticalRange());
            this.setAxisProperties(newYAxis2, subgraphProps, loc, timeZone, standardDate, standardTime, separator, isLastSubgraphInApplet, enableGrid);
            this.attachLeftAxis(newYAxis2);
        }
        else {
            final Axis graphYaxis = new Axis(new VerticalRange());
            this.setAxisProperties(graphYaxis, subgraphProps, loc, timeZone, standardDate, standardTime, separator, isLastSubgraphInApplet, enableGrid);
            this.attachLeftAxis(graphYaxis);
        }
        for (int loop = 0; loop < super.dataset.size(); ++loop) {
            final DataSet dataset = super.dataset.elementAt(loop);
            dataset.setHorizontalRange(this.getBottomAxis().getRange());
            if (dataset.getMetaData().isAnalog()) {
                dataset.setVerticalRange(this.getLeftAxis().getRange());
            }
        }
        if (!this.yAutoscale) {
            this.setYInitialDataView(subgraphProps.yAxis_InitialMinimum, subgraphProps.yAxis_InitialMaximum);
        }
    }
    
    private void setAxisProperties(final Axis axis, final TrendProperties.SubGraph subgraphProp, final Locale loc, final TimeZone timeZone, final int standardDate, final boolean standardTime, final String separator, final boolean enableLabelsOnXaxis, final boolean enableGrid) {
        if (axis.getRange() instanceof HorizontalRange) {
            axis.drawGrid(enableGrid);
            axis.forceEndLabels(axis.drawzero = false);
            final DateLabelGenerator dateLabelGen = new DateLabelGenerator(loc, timeZone, standardDate, standardTime, separator);
            if (enableLabelsOnXaxis) {
                axis.setLabelGenerator(dateLabelGen);
                axis.setAxisLabelEnabled(true);
                dateLabelGen.showTransitions(true);
            }
            else {
                axis.setLabelGenerator(dateLabelGen);
                axis.setAxisLabelEnabled(subgraphProp.showXAxisLabels);
            }
            axis.drawzero = false;
        }
        else {
            axis.setTitleText(subgraphProp.yAxis_Label);
            axis.setLabelGenerator(new LabelGenerator(loc));
            axis.setAxisLabelEnabled(true);
            axis.drawGrid(enableGrid);
            axis.forceEndLabels(false);
        }
        if (axis instanceof CompositeAxis) {
            this.setBinaryAxisProperties((CompositeAxis)axis);
        }
    }
    
    private void setDataSetProperties(final DataSet dataset, final TrendProperties.DataSeries seriesprop, final int datatype, final TimeZone timeZone, final Locale loc, final int standardDate, final boolean standardTime, final String separator, final TrendResource labelText, final Markers markers) {
        if (dataset == null || seriesprop == null) {
            return;
        }
        if (markers != null) {
            final Marker myMarker = new Marker(markers.getMarker(seriesprop.getMarkerType()));
            if (myMarker != null) {
                myMarker.setColor(new Color(seriesprop.getColor_Marker(false)));
                dataset.setMarker(myMarker);
            }
        }
        dataset.setMarkerScale(1);
        dataset.getMetaData().setName(seriesprop.getName());
        dataset.getMetaData().setType(datatype);
        final int stroke = seriesprop.getLineStroke();
        final int gap = seriesprop.getLineGap();
        final int thickness = seriesprop.getLineThickness();
        LogRecordLabelGenerator generator = null;
        if (dataset.getMetaData().isAnalog()) {
            generator = new LogRecordLabelGenerator(loc, timeZone, standardDate, standardTime, separator);
            dataset.setDrawLineHandler(new DrawLine(stroke, gap, thickness, new Color(seriesprop.getColor_Line(false))));
        }
        else {
            generator = new LogRecordLabelGenerator(loc, timeZone, standardDate, standardTime, separator);
            generator.setStateText(seriesprop.getStateTextArray());
            dataset.setDrawLineHandler(new DrawDigitalLine(stroke, gap, thickness, new Color(seriesprop.getColor_Line(false))));
        }
        generator.setInternationalizedText(labelText);
        dataset.getMetaData().isCov(seriesprop.isUsingCov());
        dataset.getMetaData().setLabelGenerator(generator);
        dataset.getMetaData().pathtodata = seriesprop.getSourcePath();
    }
    
    public void setColors(final TrendProperties props, final TrendProperties.SubGraph subgraph, final boolean printMode) {
        this.setBackground(new Color(props.getColor_GraphBackground(printMode)));
        this.setLegendColor(new Color(props.getColor_GraphTitle(printMode)));
        this.setDataBackground(new Color(props.getColor_DataBackground(printMode)));
        this.setAxisColors(new Color(props.getColor_Axis(printMode)), new Color(props.getColor_Grid(printMode)), new Color(props.getColor_YAxis_Title(printMode)), new Color(props.getColor_XAxis_Label(printMode)), new Color(props.getColor_YAxis_Label(printMode)));
        for (int j = 0; j < super.dataset.size(); ++j) {
            final DataSet d = super.dataset.elementAt(j);
            d.setLineColor(new Color(subgraph.getDataSeries(j).getColor_Line(printMode)));
            if (d.getMarker() != null) {
                d.getMarker().setColor(new Color(subgraph.getDataSeries(j).getColor_Marker(printMode)));
            }
        }
    }
    
    public void saveGraphState() {
        final GraphState currentState = new GraphState();
        currentState.minX = this.getHorizRange().getValueMin();
        currentState.minY = this.getVertRange().getValueMin();
        currentState.maxX = this.getHorizRange().getValueMax();
        currentState.maxY = this.getVertRange().getValueMax();
        if (TrendPlot.traceLevel >= 3) {
            TrendPlot.trace(this.getClass(), "saveAction: " + currentState + " deltaX: " + (currentState.maxX - currentState.minX));
        }
        this.history.push(currentState);
    }
    
    public void retrieveGraphState() {
        final GraphState oldState = this.history.pop();
        if (oldState != null) {
            this.getHorizRange().setValues(oldState.minX, oldState.maxX);
            this.getVertRange().setValues(oldState.minY, oldState.maxY);
            if (TrendPlot.traceLevel >= 3) {
                TrendPlot.trace(this.getClass(), "undoAction: " + oldState);
            }
        }
    }
    
    public void addDataToDataSets(final TrendData.SubGraph subgraph, final boolean addedToRight) {
        boolean wasDataInView = false;
        if (super.dataset == null || super.dataset.isEmpty() || subgraph == null) {
            return;
        }
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            final TrendData.DataSeries series = subgraph.getDataSeries(i);
            if (series != null) {
                d.getMetaData().setCurrentSystemTimeValue(series.getCurrentSystemTime());
                if (series.getNumRecords() > 0) {
                    final BNLogRecord[] records = series.getRecords();
                    d.appendData(records);
                    if (addedToRight) {
                        d.getMetaData().hasMoreDataToRight = series.hasMoreDataToRight;
                    }
                    else {
                        d.getMetaData().hasMoreDataToLeft = series.hasMoreDataToLeft;
                    }
                    double end = records[records.length - 1].getXValue();
                    if (d.getMetaData().isCov() && addedToRight) {
                        end = Math.max(end, d.getMetaData().getCurrentSystemTimeValue());
                    }
                    if (this.isDataInView(records[0].getXValue(), end)) {
                        wasDataInView = true;
                    }
                }
            }
        }
        if (wasDataInView) {
            this.repaint();
        }
    }
    
    public void addOrReplaceDataInDataSets(final TrendData.SubGraph subgraph) {
        boolean wasDataInView = false;
        if (super.dataset == null || super.dataset.isEmpty() || subgraph == null) {
            return;
        }
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            final TrendData.DataSeries series = subgraph.getDataSeries(i);
            if (series != null) {
                d.getMetaData().setCurrentSystemTimeValue(series.getCurrentSystemTime());
                if (series.getNumRecords() > 0) {
                    final BNLogRecord[] records = series.getRecords();
                    d.appendOrReplaceData(records, true);
                    d.getMetaData().hasMoreDataToRight = series.hasMoreDataToRight;
                    d.getMetaData().hasMoreDataToLeft = series.hasMoreDataToLeft;
                    if (this.isDataInView(records[0].getXValue(), records[records.length - 1].getXValue())) {
                        wasDataInView = true;
                    }
                }
            }
        }
        if (wasDataInView) {
            this.repaint();
        }
    }
    
    private boolean isDataInView(final double start, final double end) {
        return (start > this.getHorizRange().getValueMin() || end > this.getHorizRange().getValueMin()) && (start < this.getHorizRange().getValueMax() || end < this.getHorizRange().getValueMax());
    }
    
    public boolean addRequestJumpToString(final StringBuffer reqString, final long newStartTime, final boolean isFirstGraph) {
        TrendDataRequest.addGraphToRequest(reqString, isFirstGraph);
        final double newEndTime = newStartTime + this.getHorizRange().getValueRange();
        boolean needMoreData = false;
        if (super.dataset == null || super.dataset.isEmpty()) {
            return needMoreData;
        }
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if ((newStartTime < d.getMinXValue() && d.getMetaData().hasMoreDataToLeft) || (newEndTime > d.getMaxXValue() && d.getMetaData().hasMoreDataToRight)) {
                TrendDataRequest.addSeriesToRequest(reqString, d.getMetaData().pathtodata, newStartTime, 0L, -1L, i == 0);
                needMoreData = true;
            }
            else {
                TrendDataRequest.addSeriesToRequest(reqString, null, 0L, 0L, -1L, i == 0);
            }
        }
        return needMoreData;
    }
    
    public void addRequestCurrentToString(final StringBuffer reqString, final double dataLeftBoundary, final boolean isFirstGraph) {
        TrendDataRequest.addGraphToRequest(reqString, isFirstGraph);
        if (super.dataset == null || super.dataset.isEmpty()) {
            return;
        }
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            long sequenceNumber = -1L;
            long left;
            if (d.getSize() == 0) {
                left = (long)dataLeftBoundary;
            }
            else {
                left = (long)d.getMaxXValue();
                sequenceNumber = d.getPoint(d.getSize() - 1).getSequenceNumber();
            }
            TrendDataRequest.addSeriesToRequest(reqString, d.getMetaData().pathtodata, left, 0L, sequenceNumber, i == 0);
        }
    }
    
    public boolean addRequestToString(final StringBuffer reqString, final boolean rightSide, final double dataLeftBoundary, final double dataRightBoundary, final boolean isFirstGraph) {
        TrendDataRequest.addGraphToRequest(reqString, isFirstGraph);
        if (super.dataset == null || super.dataset.isEmpty()) {
            return false;
        }
        boolean needMoreData = false;
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            long left = 0L;
            long right = 0L;
            long sequenceNumber = -1L;
            String path = null;
            if (rightSide) {
                if (d.getMetaData().hasMoreDataToRight && (d.getSize() == 0 || dataRightBoundary > d.getMaxXValue())) {
                    if (d.getSize() == 0) {
                        left = (long)dataLeftBoundary;
                    }
                    else {
                        left = (long)d.getMaxXValue();
                        sequenceNumber = d.getPoint(d.getSize() - 1).getSequenceNumber();
                    }
                    right = 0L;
                    path = d.getMetaData().pathtodata;
                    needMoreData = true;
                }
            }
            else if (d.getMetaData().hasMoreDataToLeft && (d.getSize() == 0 || dataLeftBoundary < d.getMinXValue())) {
                if (d.getSize() == 0) {
                    right = (long)dataLeftBoundary;
                }
                else {
                    right = (long)d.getMinXValue();
                    sequenceNumber = d.getPoint(0).getSequenceNumber();
                }
                left = 0L;
                path = d.getMetaData().pathtodata;
                needMoreData = true;
            }
            TrendDataRequest.addSeriesToRequest(reqString, path, left, right, sequenceNumber, i == 0);
        }
        return needMoreData;
    }
    
    public double getNewXMinDataView(double smallestMinAllowed) {
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if (d.getMetaData().hasMoreDataToLeft) {
                smallestMinAllowed = Math.max(smallestMinAllowed, d.getMinXValue() + d.getXShift());
            }
        }
        return smallestMinAllowed;
    }
    
    public double getNewXMaxDataView(double biggestMaxAllowed) {
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if (d.getMetaData().hasMoreDataToRight) {
                biggestMaxAllowed = Math.min(biggestMaxAllowed, d.getMaxXValue() + d.getXShift());
            }
        }
        return biggestMaxAllowed;
    }
    
    public int getLeftAxisWidth() {
        if (super.axisLeft != null) {
            return super.axisLeft.calculateBreadth(this.getGraphics(), 0);
        }
        return 0;
    }
    
    public int getRightAxisWidth() {
        if (super.axisRight != null) {
            return super.axisRight.calculateBreadth(this.getGraphics(), 0);
        }
        return 0;
    }
    
    public double getXMaxDataOrSystemTime() {
        double max = 0.0;
        if (super.dataset == null | super.dataset.isEmpty()) {
            return max;
        }
        boolean maxNotSet = true;
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            if (d.getSize() != 0) {
                if (maxNotSet) {
                    max = d.getMaxXValue() + d.getXShift();
                    maxNotSet = false;
                }
                else {
                    max = Math.max(max, d.getMaxXValue() + d.getXShift());
                }
                if (d.getMetaData().isCov()) {
                    max = Math.max(max, d.getMetaData().getCurrentSystemTimeValue());
                }
            }
        }
        return max;
    }
    
    public void resetDataView() {
        this.getHorizRange().setValues(this.initialDataView.x, this.initialDataView.x + this.initialDataView.width);
        this.getVertRange().setValues(this.initialDataView.y, this.initialDataView.y + this.initialDataView.height);
    }
    
    public void setXInitialDataView(final double min, final double max) {
        this.initialDataView.x = min;
        this.initialDataView.width = max - min;
    }
    
    public void setYInitialDataView(final double min, final double max) {
        this.initialDataView.y = min;
        this.initialDataView.height = max - min;
    }
    
    public void setAxisColors(final Color axis, final Color grid, final Color title, final Color xLabel, final Color yLabel) {
        if (this.getBottomAxis() != null) {
            this.getBottomAxis().setAllColors(axis, grid, xLabel, title);
        }
        if (this.getLeftAxis() != null) {
            this.getLeftAxis().setAllColors(axis, grid, yLabel, title);
        }
        if (this.getTopAxis() != null) {
            this.getTopAxis().setAllColors(axis, grid, xLabel, title);
        }
        if (this.getRightAxis() != null) {
            this.getRightAxis().setAllColors(axis, grid, yLabel, title);
        }
    }
    
    public void setAxisFont(final Font font) {
        if (this.getTopAxis() != null) {
            this.getTopAxis().getTitle().setFont(font);
            this.getTopAxis().getLabel().setFont(font);
        }
        if (this.getBottomAxis() != null) {
            this.getBottomAxis().getTitle().setFont(font);
            this.getBottomAxis().getLabel().setFont(font);
        }
        if (this.getLeftAxis() != null) {
            this.getLeftAxis().getTitle().setFont(font);
            this.getLeftAxis().getLabel().setFont(font);
        }
        if (this.getRightAxis() != null) {
            this.getRightAxis().getTitle().setFont(font);
            this.getRightAxis().getLabel().setFont(font);
        }
    }
    
    public void setBinaryAxisProperties(final CompositeAxis axis) {
        int startValue = 0;
        int arrayposition = 1;
        int value = 0;
        boolean firstBinary = true;
        final Vector labels = new Vector();
        for (int loop = 0; loop < super.dataset.size(); ++loop) {
            final DataSet dataset = super.dataset.elementAt(loop);
            if (dataset.getMetaData().isBinary()) {
                final VerticalRange range = new VerticalRange();
                axis.addRange(range);
                dataset.setVerticalRange(range);
                final String[] stateText = dataset.getMetaData().getLabelGenerator().getStateText();
                if (firstBinary) {
                    firstBinary = false;
                    if (dataset.getMetaData().isBoolean()) {
                        labels.addElement(" ");
                        value = 0;
                    }
                    else {
                        if (!dataset.getMetaData().isMultiState()) {
                            return;
                        }
                        labels.addElement(" ");
                        value = 1;
                    }
                    startValue = value - 1;
                }
                if (stateText != null) {
                    range.setValueShift(arrayposition - 1);
                    for (int i = 0; i < stateText.length; ++i) {
                        if (stateText[i] == null) {
                            labels.addElement(value + "");
                        }
                        else {
                            labels.addElement(stateText[i]);
                        }
                        ++arrayposition;
                        ++value;
                    }
                }
            }
        }
        labels.addElement("");
        final String[] binaryLabels = new String[labels.size()];
        for (int j = 0; j < binaryLabels.length; ++j) {
            binaryLabels[j] = labels.elementAt(j);
        }
        axis.setFixedAxisLabels(binaryLabels, startValue, 1.0);
        axis.setMinorTicCount(0);
    }
    
    public void fixYAxisScale(final double startValue, final double stopValue, final double step) {
        if (step <= 0.0 || startValue == stopValue) {
            return;
        }
        final int numSteps = (int)Math.floor((stopValue - startValue) / step) + 1;
        final String[] labels = new String[numSteps];
        final LabelGenerator labeler = new LabelGenerator(this.getLocale());
        double current = startValue;
        for (int i = 0; i < numSteps; ++i) {
            labels[i] = labeler.makeLabel(current);
            current += step;
        }
        if (super.axisLeft != null) {
            super.axisLeft.setFixedAxisLabels(labels, startValue, step);
            super.axisLeft.setMinorTicCount(0);
        }
        if (super.axisRight != null) {
            super.axisRight.setFixedAxisLabels(labels, startValue, step);
            super.axisRight.setMinorTicCount(0);
        }
    }
    
    public void drawPointLabel(final Graphics g, final DataSet ds, final BNLogRecord brecord, int x, int y) {
        final Color color = ds.getDrawLineHandler().getColor();
        final Dimension textDim = new Dimension(0, 0);
        TextLine nameLine = null;
        if (ds.getMetaData().getName() != null && ds.getMetaData().getName().length() > 0) {
            final String labelText = ds.getMetaData().getName() + "\n";
            nameLine = new TextLine(labelText, color);
            nameLine.setFont(this.getCtrlClickFont());
            nameLine.setJustification(1);
            nameLine.setBackground(this.getBackground());
            textDim.width = Math.max(textDim.width, nameLine.getWidth(g));
            final Dimension dimension = textDim;
            dimension.height += nameLine.getHeight(g);
        }
        final LogRecordLabelGenerator pointLabelGen = ds.getMetaData().getLabelGenerator();
        final String xlabel = pointLabelGen.getRecordTimeDateLabel(brecord);
        final TextLine timeLine = new TextLine(xlabel, color);
        timeLine.setFont(this.getCtrlClickFont());
        timeLine.setJustification(1);
        timeLine.setBackground(this.getBackground());
        textDim.width = Math.max(textDim.width, timeLine.getWidth(g));
        final Dimension dimension2 = textDim;
        dimension2.height += timeLine.getHeight(g);
        final String ylabel = pointLabelGen.getRecordValueLabel(brecord);
        final TextLine valueLine = new TextLine(ylabel, color);
        valueLine.setJustification(0);
        valueLine.setFont(this.getCtrlClickFont());
        valueLine.setBackground(this.getBackground());
        textDim.width = Math.max(textDim.width, valueLine.getWidth(g));
        final Dimension dimension3 = textDim;
        dimension3.height += valueLine.getHeight(g);
        TextLine flagsLine = null;
        if (brecord.hasAnyFlag()) {
            final String flagslabel = pointLabelGen.getRecordFlagsLabel(brecord);
            flagsLine = new TextLine(flagslabel, color);
            flagsLine.setFont(this.getCtrlClickFont());
            flagsLine.setJustification(1);
            flagsLine.setBackground(this.getBackground());
            textDim.width = Math.max(textDim.width, flagsLine.getWidth(g));
            final Dimension dimension4 = textDim;
            dimension4.height += flagsLine.getHeight(g);
        }
        final int rightSide = ds.getHorizRange().getPixelMax();
        if (x + textDim.width > rightSide) {
            x = rightSide - textDim.width;
        }
        final int bottom = ds.getVertRange().getPixelMax() + ds.getVertRange().getPixelMin();
        if (y + textDim.height > bottom) {
            y = bottom - textDim.height;
        }
        g.setColor(color);
        if (brecord.isSpecial()) {
            g.drawLine(x + timeLine.getWidth(g) / 2, y, ds.getHorizRange().getPixelFromValue(brecord.getXValue()), y);
        }
        else {
            g.drawLine(x + timeLine.getWidth(g) / 2, y, ds.getHorizRange().getPixelFromValue(brecord.getXValue()), ds.getVertRange().getPixelFromValue(brecord.getYValue()));
        }
        if (nameLine != null) {
            nameLine.draw(g, x, y);
            y += nameLine.getHeight(g);
        }
        timeLine.draw(g, x, y);
        y += timeLine.getHeight(g);
        valueLine.draw(g, x + timeLine.getWidth(g) / 2, y);
        y += valueLine.getHeight(g);
        if (flagsLine != null) {
            flagsLine.draw(g, x, y);
            y += flagsLine.getHeight(g);
        }
        for (int i = 0; i < super.dataset.size(); ++i) {
            final DataSet d = this.getDataSetAt(i);
            int index = d.findFirstXValueLessThan(brecord.getXValue());
            if (index >= 0) {
                while (index > 0 && d.getPoint(index).getXValue() == brecord.getXValue()) {
                    --index;
                }
                if (index == -1 || d.getPoint(index).getXValue() != brecord.getXValue()) {
                    ++index;
                }
                while (index < d.getHistoricalSize() && d.getPoint(index).getXValue() == brecord.getXValue()) {
                    final BNLogRecord rec = d.getPoint(index);
                    if (rec != brecord && !brecord.isBoolean() && (rec.isSpecial() || brecord.isSpecial() || rec.getYValue() == brecord.getYValue())) {
                        y = this.drawAdditionalPointLabel(g, d, d.getPoint(index), x, y);
                    }
                    ++index;
                }
            }
        }
    }
    
    public int drawAdditionalPointLabel(final Graphics g, final DataSet ds, final BNLogRecord brecord, final int x, int y) {
        final Color color = ds.getDrawLineHandler().getColor();
        g.setColor(color);
        TextLine nameLine = null;
        if (ds.getMetaData().getName() != null && ds.getMetaData().getName().length() > 0) {
            final String labelText = ds.getMetaData().getName() + "\n";
            nameLine = new TextLine(labelText, color);
            nameLine.setFont(this.getCtrlClickFont());
            nameLine.setJustification(1);
            nameLine.setBackground(this.getBackground());
            nameLine.draw(g, x, y);
            y += nameLine.getHeight(g);
        }
        final LogRecordLabelGenerator pointLabelGen = ds.getMetaData().getLabelGenerator();
        final String ylabel = pointLabelGen.getRecordValueLabel(brecord);
        final TextLine valueLine = new TextLine(ylabel, color);
        valueLine.setJustification(1);
        valueLine.setFont(this.getCtrlClickFont());
        valueLine.setBackground(this.getBackground());
        valueLine.draw(g, x, y);
        y += valueLine.getHeight(g);
        TextLine flagLine = null;
        if (brecord.hasAnyFlag()) {
            final String flagslabel = pointLabelGen.getRecordFlagsLabel(brecord);
            flagLine = new TextLine(flagslabel, color);
            flagLine.setFont(this.getCtrlClickFont());
            flagLine.setJustification(1);
            flagLine.setBackground(this.getBackground());
            flagLine.draw(g, x, y);
            y += flagLine.getHeight(g);
        }
        return y;
    }
    
    public void setCtrlClickFont(final Font f) {
        this.ctrlClickFont = f;
    }
    
    public Font getCtrlClickFont() {
        return this.ctrlClickFont;
    }
    
    private static class GraphHistory
    {
        private int currentState;
        private GraphState[] history;
        
        public GraphHistory(final int maxStates) {
            this.currentState = 0;
            this.history = new GraphState[maxStates];
        }
        
        public void push(final GraphState state) {
            if (state.equals(this.history[this.currentState])) {
                return;
            }
            if (this.currentState >= this.history.length - 1) {
                this.currentState = 0;
            }
            else {
                ++this.currentState;
            }
            this.history[this.currentState] = state;
        }
        
        public GraphState pop() {
            final GraphState temp = this.history[this.currentState];
            if (temp == null) {
                return null;
            }
            this.history[this.currentState] = null;
            if (this.currentState <= 0) {
                this.currentState = this.history.length - 1;
            }
            else {
                --this.currentState;
            }
            return temp;
        }
    }
    
    private static class GraphState
    {
        public double minX;
        public double minY;
        public double maxX;
        public double maxY;
        
        public GraphState() {
            this.minX = 0.0;
            this.minY = 0.0;
            this.maxX = 0.0;
            this.maxY = 0.0;
        }
        
        public GraphState(final double minX, final double minY, final double maxX, final double maxY) {
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }
        
        public boolean equals(final Object test) {
            if (test instanceof GraphState) {
                final GraphState testState = (GraphState)test;
                if (this.minX == testState.minX && this.minY == testState.minY && this.maxX == testState.maxX && this.maxY == testState.maxY) {
                    return true;
                }
            }
            return false;
        }
        
        public String toString() {
            return "minX:" + (long)this.minX + " minY:" + this.minY + " maxX:" + (long)this.maxX + " maxY:" + this.maxY;
        }
    }
}
