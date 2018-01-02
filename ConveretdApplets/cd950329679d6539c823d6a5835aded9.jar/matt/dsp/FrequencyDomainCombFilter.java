// 
// Decompiled by Procyon v0.5.30
// 

package matt.dsp;

public class FrequencyDomainCombFilter
{
    private float[] fftMag;
    private float fundamental;
    private int sampleRate;
    private int frameSize;
    private float binSize;
    
    FrequencyDomainCombFilter() {
        this.fftMag = null;
        this.fundamental = 0.0f;
        this.sampleRate = 0;
        this.frameSize = 0;
        this.binSize = 0.0f;
    }
    
    FrequencyDomainCombFilter(final float fundamental, final int sampleRate) {
        this.fundamental = fundamental;
        this.sampleRate = sampleRate;
    }
    
    private void calculateBinSize() {
        this.binSize = this.sampleRate / this.getFrameSize();
    }
    
    public float[] getFftMag() {
        return this.fftMag;
    }
    
    public void setFftMag(final float[] fftMag) {
        this.fftMag = fftMag;
    }
    
    public float getFundamental() {
        return this.fundamental;
    }
    
    public void setFundamental(final float fundamental) {
        this.fundamental = fundamental;
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public void setSampleRate(final int sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    public float getBinSize() {
        return this.binSize;
    }
    
    public int getFrameSize() {
        return this.frameSize;
    }
    
    public void setFrameSize(final int frameSize) {
        this.frameSize = frameSize;
        this.calculateBinSize();
    }
    
    private boolean harmonicInRange(final float lower, final float upper) {
        for (int numHarmonics = 30, i = 1; i <= numHarmonics; ++i) {
            final float harmonic = this.fundamental * i;
            if (lower <= harmonic && upper >= harmonic) {
                return true;
            }
        }
        return false;
    }
    
    float calculateOutputEnergy() {
        float output = 0.0f;
        float harmonicfilter = 0.0f;
        for (int filterSize = this.fftMag.length / 2, filterIndex = 0; filterIndex < filterSize; ++filterIndex) {
            final float lowerBinFrequency = filterIndex * this.binSize - this.binSize / 2.0f;
            final float upperBinFrequency = filterIndex * this.binSize + this.binSize / 2.0f;
            if (this.harmonicInRange(lowerBinFrequency, upperBinFrequency)) {
                harmonicfilter = 1.0f;
            }
            else {
                harmonicfilter = 0.0f;
            }
            output += this.fftMag[filterIndex] * harmonicfilter;
        }
        return output;
    }
}
