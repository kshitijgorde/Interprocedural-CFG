// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.io.Serializable;

public class TrendProperties implements Serializable
{
    private String titleText;
    private int titleFontSize;
    private int fontSize;
    private boolean gridEnabled;
    private boolean xAxis_Autoscale;
    private long xAxis_InitialRange;
    private int color_DataBackground;
    private int color_GraphBackground;
    private int color_GraphTitle;
    private int color_Grid;
    private int color_Axis;
    private int color_XAxis_Label;
    private int color_YAxis_Label;
    private int color_YAxis_Title;
    private int printcolor_DataBackground;
    private int printcolor_GraphBackground;
    private int printcolor_GraphTitle;
    private int printcolor_Grid;
    private int printcolor_Axis;
    private int printcolor_XAxis_Label;
    private int printcolor_YAxis_Label;
    private int printcolor_YAxis_Title;
    private TrendResource displayTextResource;
    private SubGraph[] subgraphs;
    
    public TrendProperties() {
        this.gridEnabled = true;
        this.xAxis_Autoscale = true;
        this.xAxis_InitialRange = 0L;
    }
    
    public void setColors(final int dataBackgroundColor, final int graphBackgroundColor, final int graphTitleColor, final int gridColor, final int axisColor, final int xAxis_LabelColor, final int yAxis_LabelColor, final int yAxis_TitleColor) {
        this.color_DataBackground = dataBackgroundColor;
        this.color_GraphBackground = graphBackgroundColor;
        this.color_GraphTitle = graphTitleColor;
        this.color_Grid = gridColor;
        this.color_Axis = axisColor;
        this.color_XAxis_Label = xAxis_LabelColor;
        this.color_YAxis_Label = yAxis_LabelColor;
        this.color_YAxis_Title = yAxis_TitleColor;
    }
    
    public void setPrintColors(final int dataBackgroundColor, final int graphBackgroundColor, final int graphTitleColor, final int gridColor, final int axisColor, final int xAxis_LabelColor, final int yAxis_LabelColor, final int yAxis_TitleColor) {
        this.printcolor_DataBackground = dataBackgroundColor;
        this.printcolor_GraphBackground = graphBackgroundColor;
        this.printcolor_GraphTitle = graphTitleColor;
        this.printcolor_Grid = gridColor;
        this.printcolor_Axis = axisColor;
        this.printcolor_XAxis_Label = xAxis_LabelColor;
        this.printcolor_YAxis_Label = yAxis_LabelColor;
        this.printcolor_YAxis_Title = yAxis_TitleColor;
    }
    
    public int getColor_DataBackground(final boolean printColor) {
        if (printColor) {
            return this.printcolor_DataBackground;
        }
        return this.color_DataBackground;
    }
    
    public void setColor_DataBackground(final int color_DataBackground, final boolean printColor) {
        if (printColor) {
            this.printcolor_DataBackground = color_DataBackground;
        }
        else {
            this.color_DataBackground = color_DataBackground;
        }
    }
    
    public int getColor_GraphBackground(final boolean printColor) {
        if (printColor) {
            return this.printcolor_GraphBackground;
        }
        return this.color_GraphBackground;
    }
    
    public void setColor_GraphBackground(final int color_GraphBackground, final boolean printColor) {
        if (printColor) {
            this.printcolor_GraphBackground = color_GraphBackground;
        }
        else {
            this.color_GraphBackground = color_GraphBackground;
        }
    }
    
    public int getColor_GraphTitle(final boolean printColor) {
        if (printColor) {
            return this.printcolor_GraphTitle;
        }
        return this.color_GraphTitle;
    }
    
    public void setColor_GraphTitle(final int color_GraphTitle, final boolean printColor) {
        if (printColor) {
            this.printcolor_GraphTitle = color_GraphTitle;
        }
        else {
            this.color_GraphTitle = color_GraphTitle;
        }
    }
    
    public int getColor_Grid(final boolean printColor) {
        if (printColor) {
            return this.printcolor_Grid;
        }
        return this.color_Grid;
    }
    
    public void setColor_Grid(final int color_Grid, final boolean printColor) {
        if (printColor) {
            this.printcolor_Grid = color_Grid;
        }
        else {
            this.color_Grid = color_Grid;
        }
    }
    
    public int getColor_Axis(final boolean printColor) {
        if (printColor) {
            return this.printcolor_Axis;
        }
        return this.color_Axis;
    }
    
    public void setColor_Axis(final int color_Axis, final boolean printColor) {
        if (printColor) {
            this.printcolor_Axis = color_Axis;
        }
        else {
            this.color_Axis = color_Axis;
        }
    }
    
    public int getColor_XAxis_Label(final boolean printColor) {
        if (printColor) {
            return this.printcolor_XAxis_Label;
        }
        return this.color_XAxis_Label;
    }
    
    public void setColor_XAxis_Label(final int color_XAxis_Label, final boolean printColor) {
        if (printColor) {
            this.printcolor_XAxis_Label = color_XAxis_Label;
        }
        else {
            this.color_XAxis_Label = color_XAxis_Label;
        }
    }
    
    public int getColor_YAxis_Label(final boolean printColor) {
        if (printColor) {
            return this.printcolor_YAxis_Label;
        }
        return this.color_YAxis_Label;
    }
    
    public void setColor_YAxis_Label(final int color_YAxis_Label, final boolean printColor) {
        if (printColor) {
            this.printcolor_YAxis_Label = color_YAxis_Label;
        }
        else {
            this.color_YAxis_Label = color_YAxis_Label;
        }
    }
    
    public int getColor_YAxis_Title(final boolean printColor) {
        if (printColor) {
            return this.printcolor_YAxis_Title;
        }
        return this.color_YAxis_Title;
    }
    
    public void setColor_YAxis_Title(final int color_YAxis_Title, final boolean printColor) {
        if (printColor) {
            this.printcolor_YAxis_Title = color_YAxis_Title;
        }
        else {
            this.color_YAxis_Title = color_YAxis_Title;
        }
    }
    
    public void setXAxisParameters(final boolean autoscale, final long range) {
        this.xAxis_Autoscale = autoscale;
        this.xAxis_InitialRange = range;
    }
    
    public String getTitleText() {
        return this.titleText;
    }
    
    public void setTitleText(final String titleText) {
        this.titleText = titleText;
    }
    
    public int getTitleFontSize() {
        return this.titleFontSize;
    }
    
    public void setTitleFontSize(final int titleFontSize) {
        this.titleFontSize = titleFontSize;
    }
    
    public int getFontSize() {
        return this.fontSize;
    }
    
    public void setFontSize(final int fontSize) {
        this.fontSize = fontSize;
    }
    
    public boolean isGridEnabled() {
        return this.gridEnabled;
    }
    
    public void setGridEnabled(final boolean doEnableGrid) {
        this.gridEnabled = doEnableGrid;
    }
    
    public boolean isXAxis_Autoscale() {
        return this.xAxis_Autoscale;
    }
    
    public void setXAxis_Autoscale(final boolean xAxis_Autoscale) {
        this.xAxis_Autoscale = xAxis_Autoscale;
    }
    
    public long getXAxis_InitialRange() {
        return this.xAxis_InitialRange;
    }
    
    public void setXAxis_InitialRange(final long xAxis_InitialRange) {
        this.xAxis_InitialRange = xAxis_InitialRange;
    }
    
    public void setSubGraphs(final SubGraph[] sg) {
        this.subgraphs = sg;
    }
    
    public SubGraph[] getSubGraphs() {
        return this.subgraphs;
    }
    
    public SubGraph getSubGraph(final int i) {
        if (this.subgraphs == null || i >= this.subgraphs.length) {
            return null;
        }
        return this.subgraphs[i];
    }
    
    public void setDisplayTextResource(final TrendResource resource) {
        this.displayTextResource = resource;
    }
    
    public String getDisplayText(final String key) {
        return this.displayTextResource.get(key);
    }
    
    public int getNumSubGraphs() {
        if (this.subgraphs == null) {
            return 0;
        }
        return this.subgraphs.length;
    }
    
    public static class SubGraph implements Serializable
    {
        public int graphType;
        public String yAxis_Label;
        public boolean yAxis_AutoScale;
        public float yAxis_InitialMinimum;
        public float yAxis_InitialMaximum;
        public boolean showXAxisLabels;
        public boolean showLegend;
        private DataSeries[] series;
        
        public SubGraph() {
        }
        
        public SubGraph(final int graphType, final String yAxis_Label, final boolean yAxis_AutoScale, final float yAxis_InitialMinimum, final float yAxis_InitialMaximum, final boolean showXAxisLabels, final boolean showLegend) {
            this.graphType = graphType;
            this.yAxis_Label = yAxis_Label;
            this.yAxis_AutoScale = yAxis_AutoScale;
            this.yAxis_InitialMinimum = yAxis_InitialMinimum;
            this.yAxis_InitialMaximum = yAxis_InitialMaximum;
            this.showXAxisLabels = showXAxisLabels;
            this.showLegend = showLegend;
        }
        
        public void setDataSeries(final DataSeries[] s) {
            this.series = s;
        }
        
        public DataSeries[] getAllDataSeries() {
            return this.series;
        }
        
        public DataSeries getDataSeries(final int seriesnum) {
            if (this.series == null || seriesnum >= this.series.length) {
                return null;
            }
            return this.series[seriesnum];
        }
        
        public int getNumDataSeries() {
            if (this.series == null) {
                return 0;
            }
            return this.series.length;
        }
    }
    
    public static class DataSeries implements Serializable
    {
        private String sourcePath;
        private boolean sourcePathIsValid;
        private int lineStyle;
        private boolean isUsingCov;
        private int color_Line;
        private int printcolor_Line;
        private int markerType;
        private int color_Marker;
        private int printcolor_Marker;
        private String[] stateTextArray;
        private String name;
        
        public DataSeries() {
            this.isUsingCov = false;
        }
        
        public DataSeries(final String dataSourcePath, final boolean pathIsValid, final int lineStyle, final int lineColor, final int print_lineColor, final int markerType, final int markerColor, final int print_markerColor, final String binaryText_Active, final String binaryText_Inactive, final String name, final boolean usingCov) {
            this.isUsingCov = false;
            this.sourcePath = dataSourcePath;
            this.sourcePathIsValid = pathIsValid;
            this.lineStyle = lineStyle;
            this.color_Line = lineColor;
            this.printcolor_Line = print_lineColor;
            this.markerType = markerType;
            this.color_Marker = markerColor;
            this.printcolor_Marker = print_markerColor;
            this.stateTextArray = new String[] { binaryText_Active, binaryText_Inactive };
            this.name = name;
            this.isUsingCov = usingCov;
        }
        
        public void setSourcePath(final String sourcePath, final boolean isValid) {
            this.sourcePath = sourcePath;
            this.sourcePathIsValid = isValid;
        }
        
        public void setLineProperties(final int lineStyle, final int lineColor, final int print_lineColor) {
            this.lineStyle = lineStyle;
            this.color_Line = lineColor;
            this.printcolor_Line = print_lineColor;
        }
        
        public void setMarkerProperties(final int markerType, final int markerColor, final int print_markerColor) {
            this.markerType = markerType;
            this.color_Marker = markerColor;
            this.printcolor_Marker = print_markerColor;
        }
        
        public void setStateText(final String[] states) {
            this.stateTextArray = states;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
        
        public boolean isUsingCov() {
            return this.isUsingCov;
        }
        
        public void setLineStyle(final int style) {
            this.lineStyle = style;
        }
        
        public int getLineStyle() {
            return this.lineStyle;
        }
        
        public int getLineStroke() {
            return this.lineStyle & 0xFF;
        }
        
        public void setLineStroke(final int stroke) {
            this.lineStyle = (this.lineStyle & 0xFFFFFF00) + stroke;
        }
        
        public int getLineGap() {
            return (this.lineStyle & 0xFF00) >>> 8;
        }
        
        public void setLineGap(final int gap) {
            this.lineStyle = (this.lineStyle & 0xFFFF00FF) + (gap << 8);
        }
        
        public int getLineThickness() {
            return (this.lineStyle & 0xFF0000) >>> 16;
        }
        
        public void setLineThickness(final int thickness) {
            this.lineStyle = (this.lineStyle & 0xFF00FFFF) + (thickness << 16);
        }
        
        public void setUsingCov(final boolean usingCov) {
            this.isUsingCov = usingCov;
        }
        
        public String getSourcePath() {
            return this.sourcePath;
        }
        
        public void setSourcePath(final String sourcePath) {
            this.sourcePath = sourcePath;
        }
        
        public boolean isSourcePathIsValid() {
            return this.sourcePathIsValid;
        }
        
        public void setSourcePathIsValid(final boolean sourcePathIsValid) {
            this.sourcePathIsValid = sourcePathIsValid;
        }
        
        public int getMarkerType() {
            return this.markerType;
        }
        
        public void setMarkerType(final int markerType) {
            this.markerType = markerType;
        }
        
        public String[] getStateTextArray() {
            return this.stateTextArray;
        }
        
        public void setStateTextArray(final String[] stateTextArray) {
            this.stateTextArray = stateTextArray;
        }
        
        public int getColor_Line(final boolean printMode) {
            if (printMode) {
                return this.printcolor_Line;
            }
            return this.color_Line;
        }
        
        public void setColor_Line(final int color_Line, final boolean printMode) {
            if (printMode) {
                this.printcolor_Line = color_Line;
            }
            else {
                this.color_Line = color_Line;
            }
        }
        
        public int getColor_Marker(final boolean printMode) {
            if (printMode) {
                return this.printcolor_Marker;
            }
            return this.color_Marker;
        }
        
        public void setColor_Marker(final int color_Marker, final boolean printMode) {
            if (printMode) {
                this.printcolor_Marker = color_Marker;
            }
            else {
                this.color_Marker = color_Marker;
            }
        }
    }
}
