// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Hashtable;

public class NFGlobal
{
    public static final int MaxNoteSets = 20;
    public static final int NONE = 0;
    public static final int DEFAULT = -1;
    public static final int BREAK = -2;
    public static final int CDL = 1;
    public static final int HTML = 2;
    public static final int SOLID = 1;
    public static final int DOTTED = 2;
    public static final int DASHED = 3;
    public static final int DOTDASH = 4;
    public static final int CIRCLE = 1;
    public static final int SQUARE = 2;
    public static final int DIAMOND = 3;
    public static final int TRIANGLEUP = 4;
    public static final int TRIANGLEDOWN = 5;
    public static final int CROSS = 6;
    public static final int VRECTANGLE = 7;
    public static final int HRECTANGLE = 8;
    public static final int BAR = 9;
    public static final int TRIANGLEBAR = 10;
    public static final int DIAMONDBAR = 11;
    public static final int CYLINDER = 12;
    public static final int PIEVERTICAL = 13;
    public static final int PIEHORIZONTAL = 14;
    public static final int IMAGE = 15;
    public static final int FILLED = 1;
    public static final int OUTLINED = 2;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    public static final int BOX = 1;
    public static final int SHADOW = 2;
    public static final int RAISED = 3;
    public static final int RECESS = 4;
    public static final int ETCHED = 5;
    public static final int TILE = 0;
    public static final int SIZE = 1;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    public static final int CENTER = 4;
    public static final int TOPRIGHT = 5;
    public static final int TOPLEFT = 6;
    public static final int BOTTOMRIGHT = 7;
    public static final int BOTTOMLEFT = 8;
    public static final int FROMTO = 1;
    public static final int TOFROM = 2;
    public static final int BOTH = 3;
    public static final int RECTANGLE = 4;
    public static final int ELLIPSE = 5;
    public static final int PIXEL = 9;
    public static final int PERCENT = 10;
    public static final int LINE = 1;
    public static final int BLOCK = 2;
    public static final int SHARP = 3;
    public static final int ROUND = 4;
    public static final int LINE_NORMAL = 0;
    public static final int LINE_FIT = 1;
    public static final int LINE_BOTH = 2;
    public static final int PATTERN_FSLASH = 1;
    public static final int PATTERN_BSLASH = 2;
    public static final int PATTERN_DGRID = 3;
    public static final int PATTERN_HORIZONTAL = 4;
    public static final int PATTERN_VERTICAL = 5;
    public static final int PATTERN_GRID = 6;
    public static final int PATTERN_GRADIENT_VERTICAL = 7;
    public static final int PATTERN_GRADIENT_HORIZONTAL = 8;
    public static final int PATTERN_GRADIENT_FDIAG = 9;
    public static final int PATTERN_GRADIENT_BDIAG = 10;
    public static final int PATTERN_IMAGE = 11;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int DECIMAL = 5;
    public static final int BARCHART = 1;
    public static final int BOXCHART = 2;
    public static final int COMBOCHART = 3;
    public static final int DIAGRAM = 4;
    public static final int PIECHART = 5;
    public static final int STRIPCHART = 6;
    public static final int STOCKCHART = 7;
    public static final int TIMECHART = 8;
    public static final int XYCHART = 9;
    public static final int LINECHART = 10;
    public static final int MULTICHART = 11;
    public static final int DIALCHART = 12;
    public static final int TABLECHART = 13;
    public static final int BUBBLECHART = 14;
    public static final int RADARCHART = 15;
    public static final int UNKNOWN = 0;
    public static final int MICROSOFT = 1;
    public static final int SYMANTEC = 2;
    public static final int SUN = 3;
    public static final int NETSCAPE = 4;
    public static int vendor;
    public static final String[] classNames;
    public static final String[] jclassNames;
    public static final String[] graphClassNames;
    public static final String[] jgraphClassNames;
    public static final String[] graphBeanClassNames;
    public static final String[] appletClassNames;
    public static final String[] jappletClassNames;
    public static final String[] classSymbol;
    private static boolean a;
    private static Object b;
    private static boolean c;
    
    public static void init() {
        if (NFGlobal.c) {
            return;
        }
        final String lowerCase = System.getProperty("java.vendor").toLowerCase();
        if (lowerCase.indexOf("microsoft") >= 0) {
            NFGlobal.vendor = 1;
        }
        else if (lowerCase.indexOf("netscape") >= 0) {
            NFGlobal.vendor = 4;
        }
        else if (lowerCase.indexOf("symantec") >= 0) {
            NFGlobal.vendor = 2;
        }
        else if (lowerCase.indexOf("sun") >= 0) {
            NFGlobal.vendor = 3;
        }
        else {
            NFGlobal.vendor = 0;
        }
        try {
            String string = "netcharts.util";
            final String property = System.getProperty("java.protocol.handler.pkgs");
            if (property != null) {
                string = property + "|" + string;
            }
            ((Hashtable<String, String>)System.getProperties()).put("java.protocol.handler.pkgs", string);
        }
        catch (Exception ex) {
            NFDebug.print("NFGlobal: Could not register Zip URL Protocol Handler.");
        }
        NFGlobal.c = true;
    }
    
    public static int getVendor() {
        return NFGlobal.vendor;
    }
    
    public static boolean getAllowUserInteraction() {
        boolean a = false;
        synchronized (NFGlobal.b) {
            a = NFGlobal.a;
        }
        return a;
    }
    
    public static void setAllowUserInteraction(final boolean a) {
        synchronized (NFGlobal.b) {
            NFGlobal.a = a;
        }
    }
    
    public static Object getChart(final int n) {
        try {
            if (n < 0 || n > NFGlobal.appletClassNames.length) {
                NFDebug.print("NFGlobal: Unknown chart type = " + n);
                return null;
            }
            return Class.forName(NFGlobal.appletClassNames[n]).newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Object getJChart(final int n) {
        try {
            if (n < 0 || n > NFGlobal.jappletClassNames.length) {
                NFDebug.print("NFGlobal: Unknown chart type = " + n);
                return null;
            }
            return Class.forName(NFGlobal.jappletClassNames[n]).newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Object getGraph(final int n) {
        try {
            if (n < 0 || n > NFGlobal.graphClassNames.length) {
                NFDebug.print("NFGlobal: Unknown graph type = " + n);
                return null;
            }
            return Class.forName(NFGlobal.graphClassNames[n]).newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Object getJGraph(final int n) {
        try {
            if (n < 0 || n > NFGlobal.jgraphClassNames.length) {
                NFDebug.print("NFGlobal: Unknown graph type = " + n);
                return null;
            }
            return Class.forName(NFGlobal.jgraphClassNames[n]).newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String getChartType(final int n) {
        String s = null;
        switch (n) {
            case 1: {
                s = "Bar Chart";
                break;
            }
            case 2: {
                s = "Box Chart";
                break;
            }
            case 3: {
                s = "Combo Chart";
                break;
            }
            case 4: {
                s = "Diagram";
                break;
            }
            case 5: {
                s = "Pie Chart";
                break;
            }
            case 6: {
                s = "Strip Chart";
                break;
            }
            case 7: {
                s = "Stock Chart";
                break;
            }
            case 8: {
                s = "Time Chart";
                break;
            }
            case 9: {
                s = "XY Chart";
                break;
            }
            case 10: {
                s = "Line Chart";
                break;
            }
            case 11: {
                s = "Multi Chart";
                break;
            }
            case 12: {
                s = "Dial Chart";
                break;
            }
            case 13: {
                s = "Table Chart";
                break;
            }
            case 14: {
                s = "Bubble Chart";
                break;
            }
            case 15: {
                s = "Radar Chart";
                break;
            }
        }
        return s;
    }
    
    static {
        NFGlobal.vendor = 0;
        classNames = new String[] { "DEFAULT", "NFBarchartApp.class", "NFBoxchartApp.class", "NFComboChartApp.class", "NFDiagramApp.class", "NFPiechartApp.class", "NFStripChartApp.class", "NFStockChartApp.class", "NFTimeChartApp.class", "NFXYChartApp.class", "NFLineChartApp.class", "NFMultiChartApp.class", "NFDialChartApp.class", "NFTableChartApp.class", "NFBubbleChartApp.class", "NFRadarChartApp.class" };
        jclassNames = new String[] { "DEFAULT", "JBarchartApp.class", "JBoxchartApp.class", "JComboChartApp.class", "JDiagramApp.class", "JPiechartApp.class", "JStripChartApp.class", "JStockChartApp.class", "JTimeChartApp.class", "JXYChartApp.class", "JLineChartApp.class", "JMultiChartApp.class", "JDialChartApp.class", "JTableChartApp.class", "JBubbleChartApp.class", "JRadarChartApp.class" };
        graphClassNames = new String[] { "DEFAULT", "netcharts.graphics.NFBarchart", "netcharts.graphics.NFBoxchart", "netcharts.graphics.NFComboChart", "netcharts.graphics.NFDiagram", "netcharts.graphics.NFPiechart", "netcharts.graphics.NFStripChart", "netcharts.graphics.NFStockChart", "netcharts.graphics.NFTimeChart", "netcharts.graphics.NFXYChart", "netcharts.graphics.NFLineChart", "netcharts.graphics.NFMultiChart", "netcharts.graphics.NFDialChart", "netcharts.graphics.NFTableChart", "netcharts.graphics.NFBubbleChart", "netcharts.graphics.NFRadarChart" };
        jgraphClassNames = new String[] { "DEFAULT", "netcharts.graphics.JBarchart", "netcharts.graphics.JBoxchart", "netcharts.graphics.JComboChart", "netcharts.graphics.JDiagram", "netcharts.graphics.JPiechart", "netcharts.graphics.JStripChart", "netcharts.graphics.JStockChart", "netcharts.graphics.JTimeChart", "netcharts.graphics.JXYChart", "netcharts.graphics.JLineChart", "netcharts.graphics.JMultiChart", "netcharts.graphics.JDialChart", "netcharts.graphics.JTableChart", "netcharts.graphics.JBubbleChart", "netcharts.graphics.JRadarChart" };
        graphBeanClassNames = new String[] { "DEFAULT", "chartworks.cb.beans.NFBarchart", "chartworks.cb.beans.NFBoxchart", "chartworks.cb.beans.NFComboChart", "chartworks.cb.beans.NFDiagram", "chartworks.cb.beans.NFPiechart", "chartworks.cb.beans.NFStripChart", "chartworks.cb.beans.NFStockChart", "chartworks.cb.beans.NFTimeChart", "chartworks.cb.beans.NFXYChart", "chartworks.cb.beans.NFLineChart", "chartworks.cb.beans.NFMultiChart", "chartworks.cb.beans.NFDialChart", "chartworks.cb.beans.NFTableChart", "chartworks.cb.beans.NFBubbleChart", "chartworks.cb.beans.NFRadarChart" };
        appletClassNames = new String[] { "DEFAULT", "netcharts.apps.NFBarchartApp", "netcharts.apps.NFBoxchartApp", "netcharts.apps.NFComboChartApp", "netcharts.apps.NFDiagramApp", "netcharts.apps.NFPiechartApp", "netcharts.apps.NFStripChartApp", "netcharts.apps.NFStockChartApp", "netcharts.apps.NFTimeChartApp", "netcharts.apps.NFXYChartApp", "netcharts.apps.NFLineChartApp", "netcharts.apps.NFMultiChartApp", "netcharts.apps.NFDialChartApp", "netcharts.apps.NFTableChartApp", "netcharts.apps.NFBubbleChartApp", "netcharts.apps.NFRadarChartApp" };
        jappletClassNames = new String[] { "DEFAULT", "netcharts.apps.JBarchartApp", "netcharts.apps.JBoxchartApp", "netcharts.apps.JComboChartApp", "netcharts.apps.JDiagramApp", "netcharts.apps.JPiechartApp", "netcharts.apps.JStripChartApp", "netcharts.apps.JStockChartApp", "netcharts.apps.JTimeChartApp", "netcharts.apps.JXYChartApp", "netcharts.apps.JLineChartApp", "netcharts.apps.JMultiChartApp", "netcharts.apps.JDialChartApp", "netcharts.apps.JTableChartApp", "netcharts.apps.JBubbleChartApp", "netcharts.apps.JRadarChartApp" };
        classSymbol = new String[] { "DEFAULT", "BARCHART", "BOXCHART", "COMBOCHART", "DIAGRAM", "PIECHART", "STRIPCHART", "STOCKCHART", "TIMECHART", "XYCHART", "LINECHART", "MULTICHART", "DIALCHART", "TABLECHART", "BUBBLECHART", "RADARCHART" };
        NFGlobal.a = true;
        NFGlobal.b = new Object();
        NFGlobal.c = false;
        init();
    }
}
