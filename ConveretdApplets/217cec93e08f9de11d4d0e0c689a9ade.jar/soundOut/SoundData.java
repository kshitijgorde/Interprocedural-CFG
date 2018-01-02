// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import sTools.Parser;

public class SoundData
{
    private int numPts;
    private Parser ampFunc;
    private String ampStr;
    private double[] ampVec;
    
    public SoundData(final int numPts) {
        this.numPts = numPts;
        this.ampVec = new double[numPts];
        this.ampStr = "sin(400*pi*t)";
        (this.ampFunc = new Parser(1)).defineVariable(1, "t");
        this.addAmpFunc(this.ampStr);
    }
    
    public double[] getAmpVec() {
        return this.ampVec;
    }
    
    public void addAmpFunc(final String ampStr) {
        final double n = 1.25E-4;
        this.ampStr = ampStr;
        this.ampFunc.define(this.ampStr);
        this.ampFunc.parse();
        if (this.ampFunc.getErrorCode() != 0) {
            System.out.println(String.valueOf("Failed to parse amp(t): ").concat(String.valueOf(this.ampStr)));
            System.out.println(String.valueOf(String.valueOf(String.valueOf("Parse error: ").concat(String.valueOf(this.ampFunc.getErrorString()))).concat(String.valueOf(" at function 1, position "))).concat(String.valueOf(this.ampFunc.getErrorPosition())));
            return;
        }
        for (int i = 0; i < this.numPts; ++i) {
            final double n2 = i * n;
            double evaluate;
            try {
                evaluate = this.ampFunc.evaluate(n2);
            }
            catch (Exception ex) {
                evaluate = 0.0;
            }
            final double[] ampVec = this.ampVec;
            final int n3 = i;
            ampVec[n3] += evaluate;
        }
    }
    
    public void newAmpFunc(final String s) {
        for (int i = 0; i < this.numPts; ++i) {
            this.ampVec[i] = 0.0;
        }
        this.addAmpFunc(s);
    }
}
