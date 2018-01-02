// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import edu.wise.exceptions.DataNotFoundException;
import VisualNumerics.math.Statistics;
import edu.wise.graph.CorrelGraph;
import edu.wise.correl.CorrelData;

public class ManualRegLine extends regLine
{
    public int[] YpredArr;
    public int[] YbarArr;
    static CorrelData dataSet;
    static CorrelGraph cg;
    int[] newRegPts;
    private double SSy;
    private double SSErr;
    public static final boolean DEBUG = false;
    
    public ManualRegLine() {
    }
    
    public ManualRegLine(final CorrelGraph cg) {
        ManualRegLine.dataSet = CorrelGraph.getDataSet();
        ManualRegLine.cg = cg;
        this.newRegPts = new int[CorrelData.getXArr().length];
        super.slope = 0.0;
        super.intercept = 0.0;
        this.SSy = 0.0;
        this.SSErr = 0.0;
    }
    
    public void reset() {
        this.SSy = 0.0;
        this.SSErr = 0.0;
    }
    
    public void update(final int[] array) {
        super.update(array[0], array[1], array[2], array[3]);
    }
    
    public void update(final int n, final int n2, final int n3, final int n4) {
        super.update(n, n2, n3, n4);
    }
    
    public int[] getRegLine() {
        final double[] array = new double[2];
        final double[] array2 = new double[2];
        final double[] linearFit = Statistics.linearFit(CorrelData.getXArr(), CorrelData.getYArr());
        array[0] = CorrelGraph.getXmin();
        array[1] = CorrelGraph.getXmax();
        array2[0] = linearFit[1] * array[0] + linearFit[0];
        array2[1] = linearFit[1] * array[1] + linearFit[0];
        final int[] screenCoordinates = CorrelGraph.toScreenCoordinates(array, true);
        final int[] screenCoordinates2 = CorrelGraph.toScreenCoordinates(array2, false);
        final int[] array3 = { screenCoordinates[0], CorrelGraph.height - screenCoordinates2[0], screenCoordinates[1], CorrelGraph.height - screenCoordinates2[1] };
        this.update(array3);
        return array3;
    }
    
    public void setRegLine(final int n, final int n2) {
        final double n3 = CorrelGraph.convertToActual(n, n2)[0];
        final double n4 = CorrelGraph.convertToActual(n, n2)[1];
        final double xbar = CorrelData.xbar();
        final double ybar = CorrelData.Ybar();
        final double n5 = (CorrelData.slope() * xbar + CorrelData.intercept() - n4) / (xbar - n3);
        if (Math.abs(n5 * Statistics.standardDeviation(CorrelData.getXArr()) / Statistics.standardDeviation(CorrelData.getYArr())) <= 1.0) {
            final double n6 = -(n5 * n3) + n4;
            final double[] array = { CorrelGraph.getXmin(), CorrelGraph.getXmax() };
            final double[] array2 = { n5 * array[0] + n6, n5 * array[1] + n6 };
            final int[] screenCoordinates = CorrelGraph.toScreenCoordinates(array, true);
            final int[] screenCoordinates2 = CorrelGraph.toScreenCoordinates(array2, false);
            final int[] array3 = { screenCoordinates[0], CorrelGraph.height - screenCoordinates2[0], screenCoordinates[1], CorrelGraph.height - screenCoordinates2[1] };
            final double[] ypredArr = new double[CorrelGraph.getYpredArr().length];
            final double[] array4 = new double[CorrelGraph.getYpredArr().length];
            final double[] array5 = new double[CorrelGraph.getYpredArr().length];
            this.YpredArr = new int[CorrelGraph.getYpredArr().length];
            this.YbarArr = new int[CorrelGraph.getYpredArr().length];
            for (int i = 0; i < ypredArr.length; ++i) {
                try {
                    ypredArr[i] = n6 + CorrelData.getXi(i) * n5;
                    this.addSSy(Math.pow(CorrelData.getYi(i) - ybar, 2.0));
                    this.addSSErr(Math.pow(CorrelData.getYi(i) - ypredArr[i], 2.0));
                    array4[i] = Math.pow(CorrelData.getYi(i) - ybar, 2.0);
                    array5[i] = ybar;
                }
                catch (DataNotFoundException ex) {}
            }
            this.update(array3);
            CorrelGraph.getDataSet();
            CorrelData.setYpredArr(ypredArr);
        }
        CorrelGraph.update(true);
    }
    
    public void addSSy(final double n) {
        this.SSy += n;
    }
    
    public void addSSErr(final double n) {
        this.SSErr += n;
    }
    
    public double getSSErr() {
        return this.SSErr;
    }
    
    public double getSSy() {
        return this.SSy;
    }
    
    public String toString() {
        return "manualRegLine." + super.toString();
    }
}
