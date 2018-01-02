// 
// Decompiled by Procyon v0.5.30
// 

public class SonicForce
{
    private double samplingRate;
    private double durationOfForce;
    public double[] force;
    private int nSamples;
    
    public SonicForce(final double samplingRate, final double durationOfForce) {
        this.samplingRate = samplingRate;
        this.durationOfForce = durationOfForce;
        this.nSamples = (int)(this.samplingRate * this.durationOfForce);
        this.force = new double[this.nSamples];
    }
    
    public void makeForce(final double n) {
        for (int i = 0; i < this.nSamples; ++i) {
            this.force[i] = (Math.random() - 0.5) * 2.0 * n;
        }
    }
}
