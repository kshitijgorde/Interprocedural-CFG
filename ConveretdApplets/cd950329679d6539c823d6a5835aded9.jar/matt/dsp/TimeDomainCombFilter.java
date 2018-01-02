// 
// Decompiled by Procyon v0.5.30
// 

package matt.dsp;

public class TimeDomainCombFilter
{
    private float[] frame;
    private int frequency;
    private int sampleRate;
    private int delay;
    
    public float[] getFrame() {
        return this.frame;
    }
    
    public void setFrame(final float[] frame) {
        this.frame = frame;
    }
    
    public int getFrequency() {
        return this.frequency;
    }
    
    public void setFrequency(final int frequency) {
        this.frequency = frequency;
        this.delay = (int)(1.0f / frequency * this.sampleRate);
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public void setSampleRate(final int sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public float calculateOutputPower() {
        float power = 0.0f;
        for (int i = 0; i < this.frame.length + this.delay; ++i) {
            if (i < this.delay) {
                power += (float)Math.pow(this.frame[i], 2.0);
            }
            else if (i >= this.frame.length) {
                power += (float)Math.pow(this.frame[i - this.delay], 2.0);
            }
            else {
                power += (float)Math.pow(this.frame[i] + this.frame[i - this.delay], 2.0);
            }
        }
        return power;
    }
    
    private float calculateInputPower() {
        float power = 0.0f;
        for (int i = 0; i < this.frame.length; ++i) {
            power += (float)Math.pow(this.frame[i], 2.0);
        }
        return power;
    }
    
    public float calculateHarmonicity() {
        final float inputPower = this.calculateInputPower();
        final float outputPower = this.calculateOutputPower();
        final float power = outputPower / (4.0f * inputPower);
        return power;
    }
}
