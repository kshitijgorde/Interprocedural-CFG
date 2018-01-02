// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import matt.dsp.TimeDomainCombFilter;

public class ODFCalculator extends Thread
{
    int numFilters;
    private float[] frame;
    private float[] signal;
    TimeDomainCombFilter[] tdFilters;
    private float[] oldPowers;
    private float[] powers;
    private int odfIndex;
    private float[] odf;
    private int sampleRate;
    private int hopIndex;
    
    public ODFCalculator() {
        this.numFilters = 12;
        this.tdFilters = new TimeDomainCombFilter[this.numFilters];
        this.oldPowers = new float[this.numFilters];
        this.powers = new float[this.numFilters];
        this.odfIndex = 0;
    }
    
    public void run() {
        int currentSample = 0;
        final int frameSize = Integer.parseInt("" + MattProperties.getString("frameSize"));
        final int hopSize = (int)(frameSize * 0.25f);
        for (int frameIndex = 0; frameIndex < frameSize; ++frameIndex) {
            currentSample = this.hopIndex * hopSize + frameIndex;
            this.frame[frameIndex] = this.signal[currentSample];
        }
        for (int i = 0; i < this.tdFilters.length; ++i) {
            this.tdFilters[i].setFrame(this.getFrame());
            this.powers[i] = this.tdFilters[i].calculateHarmonicity();
            if (this.hopIndex > 0) {
                final float[] odf = this.odf;
                final int odfIndex = this.odfIndex;
                odf[odfIndex] += (float)Math.pow(this.powers[i] - this.oldPowers[i], 2.0);
            }
            this.oldPowers[i] = this.powers[i];
        }
        if (this.hopIndex > 0) {
            ++this.odfIndex;
        }
    }
    
    public float[] getFrame() {
        return this.frame;
    }
    
    public void setFrame(final float[] frame) {
        this.frame = frame;
    }
    
    public float[] getSignal() {
        return this.signal;
    }
    
    public void setSignal(final float[] signal) {
        this.signal = signal;
    }
    
    public TimeDomainCombFilter[] getTdFilters() {
        return this.tdFilters;
    }
    
    public void setTdFilters(final TimeDomainCombFilter[] tdFilters) {
        this.tdFilters = tdFilters;
    }
    
    public int getOdfIndex() {
        return this.odfIndex;
    }
    
    public void setOdfIndex(final int odfIndex) {
        this.odfIndex = odfIndex;
    }
    
    public float[] getOdf() {
        return this.odf;
    }
    
    public void setOdf(final float[] odf) {
        this.odf = odf;
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public void setSampleRate(final int sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    public int getHopIndex() {
        return this.hopIndex;
    }
    
    public void setHopIndex(final int hopIndex) {
        this.hopIndex = hopIndex;
    }
}
