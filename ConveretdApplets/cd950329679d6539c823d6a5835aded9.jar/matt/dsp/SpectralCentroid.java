// 
// Decompiled by Procyon v0.5.30
// 

package matt.dsp;

public class SpectralCentroid
{
    private float[] fftMag;
    private float binWidth;
    private float sampleRate;
    private float frameSize;
    
    public float[] getFftMag() {
        return this.fftMag;
    }
    
    public void setFftMag(final float[] fftMag) {
        this.fftMag = fftMag;
    }
    
    float getBinWidth() {
        return this.binWidth = this.sampleRate / this.frameSize;
    }
    
    float calculate() {
        float topLine = 0.0f;
        float bottomLine = 0.0f;
        this.binWidth = this.sampleRate / this.frameSize;
        for (int i = 0; i < this.fftMag.length; ++i) {
            topLine += i * this.binWidth * this.fftMag[i];
            bottomLine += this.fftMag[i];
        }
        return topLine / bottomLine;
    }
    
    public float getSampleRate() {
        return this.sampleRate;
    }
    
    public void setSampleRate(final float sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    public float getFrameSize() {
        return this.frameSize;
    }
    
    public void setFrameSize(final float frameSize) {
        this.frameSize = frameSize;
    }
}
