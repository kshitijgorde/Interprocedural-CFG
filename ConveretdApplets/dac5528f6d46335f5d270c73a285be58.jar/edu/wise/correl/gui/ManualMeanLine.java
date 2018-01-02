// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import edu.wise.exceptions.DataNotFoundException;
import VisualNumerics.math.Statistics;
import edu.wise.correl.CorrelData;
import edu.wise.graph.CorrelGraph;

public class ManualMeanLine extends ManualRegLine
{
    private double ybar;
    int[] new_pts;
    private static CorrelGraph cg;
    public static final boolean DEBUG = false;
    
    public ManualMeanLine(final CorrelGraph correlGraph) {
        super(correlGraph);
        this.ybar = Statistics.average(CorrelData.getYArr());
        super.slope = 0.0;
        super.intercept = 0.0;
    }
    
    public int[] getRegLine() {
        CorrelGraph.getDataSet();
        CorrelData.setRegType(1);
        final double[] array = new double[2];
        final double[] array2 = new double[2];
        array[0] = CorrelGraph.getXmin();
        array[1] = CorrelGraph.getXmax();
        array2[0] = (array2[1] = CorrelData.Ybar());
        array[1] = 100.0;
        final int[] screenCoordinates = CorrelGraph.toScreenCoordinates(array, true);
        final int[] screenCoordinates2 = CorrelGraph.toScreenCoordinates(array2, false);
        final int[] array3 = { screenCoordinates[0], CorrelGraph.height - screenCoordinates2[0], screenCoordinates[1], CorrelGraph.height - screenCoordinates2[1] };
        this.update(array3);
        this.ybar = Statistics.average(CorrelData.getYArr());
        return array3;
    }
    
    public void setRegLine(final int n, final int n2) {
        final double n3 = CorrelGraph.convertToActual(n, n2)[1];
        final double ybar = CorrelData.Ybar();
        final double[] array = { CorrelGraph.getXmin(), CorrelGraph.getXmax() };
        final double[] array2 = { n3, n3 };
        final int[] screenCoordinates = CorrelGraph.toScreenCoordinates(array, true);
        final int[] screenCoordinates2 = CorrelGraph.toScreenCoordinates(array2, false);
        final int[] array3 = { screenCoordinates[0], CorrelGraph.height - screenCoordinates2[0], screenCoordinates[1], CorrelGraph.height - screenCoordinates2[1] };
        final double[] ypredArr = new double[CorrelGraph.getYpredArr().length];
        final double[] array4 = new double[CorrelGraph.getYpredArr().length];
        final double[] array5 = new double[CorrelGraph.getYpredArr().length];
        for (int i = 0; i < ypredArr.length; ++i) {
            try {
                ypredArr[i] = n3;
                this.addSSy(Math.pow(CorrelData.getYi(i) - ybar, 2.0));
                this.addSSErr(Math.pow(CorrelData.getYi(i) - ypredArr[i], 2.0));
            }
            catch (DataNotFoundException ex) {}
        }
        this.ybar = ypredArr[0];
        this.update(array3);
        CorrelGraph.getDataSet();
        CorrelData.setYpredArr(ypredArr);
    }
    
    public double getMean() {
        return this.ybar;
    }
    
    public void setMean(final double ybar) {
        this.ybar = ybar;
    }
    
    public String toString() {
        return "manualMeanLine." + super.toString();
    }
}
