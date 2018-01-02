// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.graph;

import edu.wise.exceptions.DataNotFoundException;
import edu.wise.utils.FormatUtils;
import VisualNumerics.math.Statistics;
import edu.wise.correl.InnerDataView;
import edu.wise.correl.CorrelData;
import edu.wise.correl.dataPoint;

public class CorrelGraph
{
    private static int[] x;
    private static int[] y;
    private static int[] YbarArr;
    private static int[] YpredArr;
    public static int width;
    public static int height;
    private static dataPoint[] dp;
    public static CorrelData cd;
    private static double zoom;
    private static double ymin;
    private static double ymax;
    private static double xmin;
    private static double xmax;
    private static double xrange;
    private static double yrange;
    public static int activePt;
    public static boolean fixedRange;
    private static double slope;
    private static double inter;
    public static final boolean DEBUG = false;
    public static InnerDataView idv;
    
    static {
        CorrelGraph.zoom = 0.0;
        CorrelGraph.ymin = 0.0;
        CorrelGraph.ymax = 0.0;
        CorrelGraph.xmin = 0.0;
        CorrelGraph.xmax = 0.0;
        CorrelGraph.xrange = 0.0;
        CorrelGraph.yrange = 0.0;
        CorrelGraph.slope = 0.0;
        CorrelGraph.inter = 0.0;
    }
    
    public CorrelGraph() {
    }
    
    public CorrelGraph(final int width, final int height, final InnerDataView idv, final CorrelData cd) {
        CorrelGraph.width = width;
        CorrelGraph.height = height;
        CorrelGraph.zoom = 0.75;
        CorrelGraph.cd = cd;
        CorrelGraph.idv = idv;
        CorrelGraph.activePt = -1;
        CorrelGraph.fixedRange = true;
        update(false);
    }
    
    public static void update(final boolean b) {
        screenMinMax(b);
        CorrelGraph.x = toScreenCoordinates(CorrelData.getXArr(), true);
        CorrelGraph.y = toScreenCoordinates(CorrelData.getYArr(), false);
        CorrelGraph.YpredArr = toScreenCoordinates(CorrelData.getYpredArr(), false);
        CorrelGraph.YbarArr = toScreenCoordinates(CorrelData.getYbarArr(), false);
        setDataPoints();
    }
    
    public static void screenMinMax() {
        screenMinMax(false);
    }
    
    public static void screenMinMax(final boolean b) {
        if (!b) {
            final double n = Statistics.minimum(CorrelData.getXArr()) / CorrelGraph.zoom;
            final double n2 = Statistics.minimum(CorrelData.getYArr()) / CorrelGraph.zoom;
            final double n3 = Statistics.maximum(CorrelData.getXArr()) / CorrelGraph.zoom;
            final double n4 = Statistics.maximum(CorrelData.getYArr()) / CorrelGraph.zoom;
            CorrelGraph.xrange = n3 - n;
            CorrelGraph.yrange = n4 - n2;
            final double average = Statistics.average(CorrelData.getXArr());
            final double average2 = Statistics.average(CorrelData.getYArr());
            CorrelGraph.xmin = average - CorrelGraph.xrange / 2.0;
            CorrelGraph.ymin = average2 - CorrelGraph.yrange / 2.0;
            CorrelGraph.xmax = average + CorrelGraph.xrange / 2.0;
            CorrelGraph.ymax = average2 + CorrelGraph.yrange / 2.0;
        }
        if (CorrelData.N() == 0) {
            CorrelGraph.xmin = (CorrelGraph.ymin = 0.0);
            CorrelGraph.xmax = (CorrelGraph.ymax = 12.0);
            CorrelGraph.xrange = (CorrelGraph.yrange = 12.0);
        }
    }
    
    public void setZoom(final double n) {
        if (n > 0.0) {
            CorrelGraph.zoom *= n;
            update(false);
        }
    }
    
    public static double[] convertToActual(final int n, final int n2) {
        return new double[] { CorrelGraph.xmin + CorrelGraph.xrange * (n / CorrelGraph.width), CorrelGraph.ymin + CorrelGraph.yrange * ((CorrelGraph.height - n2) / CorrelGraph.height) };
    }
    
    private static double[] convertToPercent(final double[] array, final boolean b) {
        final double[] array2 = new double[array.length];
        double n;
        double n2;
        if (b) {
            n = CorrelGraph.xmin;
            n2 = CorrelGraph.xrange;
        }
        else {
            n = CorrelGraph.ymin;
            n2 = CorrelGraph.yrange;
        }
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] - n) / n2;
        }
        return array2;
    }
    
    public static double toScreenCoordinates(final double n, final boolean b) {
        return toScreenCoordinates(new double[] { n }, b)[0];
    }
    
    public static int[] toScreenCoordinates(final double[] array, final boolean b) {
        final double[] convertToPercent = convertToPercent(array, b);
        final int[] array2 = new int[convertToPercent.length];
        for (int i = 0; i < array.length; ++i) {
            if (b) {
                array2[i] = (int)Math.round(convertToPercent[i] * CorrelGraph.width);
            }
            else {
                array2[i] = (int)Math.round(convertToPercent[i] * CorrelGraph.height);
            }
        }
        return array2;
    }
    
    public static boolean movePt(final int n, final int n2) {
        if (CorrelGraph.activePt >= 0 && CorrelGraph.activePt < CorrelData.getXArr().length) {
            final double n3 = convertToActual(n, n2)[0];
            final double n4 = convertToActual(n, n2)[1];
            if (CorrelGraph.fixedRange && n > 0 && n < CorrelGraph.width && n2 > 0 && n2 < CorrelGraph.height) {
                CorrelData.changePt(n3, n4, CorrelGraph.activePt);
                CorrelGraph.dp[CorrelGraph.activePt].update(n, n2);
            }
            update(true);
            return true;
        }
        return false;
    }
    
    public static String[] getLabels(final int n, final boolean b) {
        final String[] array = new String[n];
        double n2;
        double n3;
        if (b) {
            n2 = getXRange();
            n3 = getXmin();
        }
        else {
            n2 = getYRange();
            n3 = getYmin();
        }
        final double n4 = n2 / n;
        for (int i = 0; i < array.length; ++i) {
            array[i] = FormatUtils.rounder_str(n3 + n4 * i, 2);
        }
        return array;
    }
    
    public static CorrelData getDataSet() {
        return CorrelGraph.cd;
    }
    
    public static int[] getXArr() {
        return CorrelGraph.x;
    }
    
    public static int getActivePt() {
        return CorrelGraph.activePt;
    }
    
    public static int[] getYArr() {
        return CorrelGraph.y;
    }
    
    public static int[] getYbarArr() {
        return CorrelGraph.YbarArr;
    }
    
    public static int getYbar() throws DataNotFoundException {
        if (CorrelGraph.YbarArr.length < 1) {
            throw new DataNotFoundException("CorrelGraph.getYbarArr");
        }
        return CorrelGraph.YbarArr[0];
    }
    
    public static int[] getYpredArr() {
        return CorrelGraph.YpredArr;
    }
    
    public static dataPoint[] getDataPt_arr() {
        return CorrelGraph.dp;
    }
    
    public static double getZoom() {
        return CorrelGraph.zoom;
    }
    
    public static double getXmin() {
        return CorrelGraph.xmin;
    }
    
    public static double getYmin() {
        return CorrelGraph.ymin;
    }
    
    public static double getXmax() {
        return CorrelGraph.xmax;
    }
    
    public static double getYmax() {
        return CorrelGraph.ymax;
    }
    
    public static double getXRange() {
        return CorrelGraph.xrange;
    }
    
    public static double getYRange() {
        return CorrelGraph.yrange;
    }
    
    public static int dpLength() {
        return CorrelGraph.dp.length;
    }
    
    public static void setYpredArr(final int[] ypredArr) {
        CorrelGraph.YpredArr = ypredArr;
    }
    
    public static dataPoint getDataPt(final int n) throws DataNotFoundException {
        try {
            return CorrelGraph.dp[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DataNotFoundException("correlGraph.getDataPt[" + n + "]");
        }
    }
    
    public static int getXi(final int n) throws DataNotFoundException {
        try {
            return CorrelGraph.x[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DataNotFoundException("correlGraph.x[" + n + "]");
        }
    }
    
    public static int getYi(final int n) throws DataNotFoundException {
        try {
            return CorrelGraph.y[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DataNotFoundException("correlGraph.y[" + n + "]");
        }
    }
    
    public static int getYbarArr(final int n) throws DataNotFoundException {
        try {
            return CorrelGraph.YbarArr[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DataNotFoundException("correlGraph.YbarArr[" + n + "]");
        }
    }
    
    public static int getYpredArr(final int n) throws DataNotFoundException {
        if (n < 0 || n >= CorrelGraph.YpredArr.length) {
            throw new DataNotFoundException(n + " is not a valid case.");
        }
        return CorrelGraph.YpredArr[n];
    }
    
    public static void setActivePt(final int activePt) {
        CorrelGraph.activePt = activePt;
    }
    
    public static void fixedRange(final boolean fixedRange) {
        CorrelGraph.fixedRange = fixedRange;
    }
    
    public static void setDataPoints() {
        CorrelGraph.dp = new dataPoint[CorrelGraph.x.length];
        for (int i = 0; i < CorrelGraph.dp.length; ++i) {
            CorrelGraph.dp[i] = new dataPoint(CorrelGraph.x[i], CorrelGraph.height - CorrelGraph.y[i]);
            if (i == CorrelGraph.activePt) {
                CorrelGraph.dp[i].setActive(true);
            }
        }
    }
}
