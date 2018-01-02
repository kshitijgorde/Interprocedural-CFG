// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.text.DecimalFormat;

public class EnergyCalculator
{
    private float[] signal;
    private int start;
    private int end;
    static DecimalFormat formatter;
    public static final float SILENCE_THRESHOLD = 6.0E8f;
    
    public EnergyCalculator() {
    }
    
    public EnergyCalculator(final float[] signal, final int start, final int end) {
        this.signal = signal;
        this.start = start;
        this.end = end;
    }
    
    public float calculateEnergy() {
        float energy = 0.0f;
        for (int i = this.start; i <= this.end && i != this.signal.length; ++i) {
            energy += Math.abs(this.signal[i]);
        }
        return energy;
    }
    
    public float calculateAverageEnergy() {
        float energy = this.calculateEnergy();
        final float size = this.end - this.start - 1;
        energy /= size;
        return energy;
    }
    
    public float calculateMaxEnergy() {
        float energy = 0.0f;
        for (int i = this.start; i <= this.end; ++i) {
            final float current = Math.abs(this.signal[i]);
            if (current > energy) {
                energy = current;
            }
        }
        return energy;
    }
    
    public static String formatEnergy(final float energy) {
        return EnergyCalculator.formatter.format(new Float(energy));
    }
    
    public int getStart() {
        return this.start;
    }
    
    public void setStart(final int start) {
        this.start = start;
    }
    
    public int getEnd() {
        return this.end;
    }
    
    public void setEnd(final int end) {
        this.end = end;
    }
    
    public float[] getSignal() {
        return this.signal;
    }
    
    public void setSignal(final float[] signal) {
        this.signal = signal;
        this.start = 0;
        this.end = signal.length - 1;
    }
    
    static {
        EnergyCalculator.formatter = new DecimalFormat("#,##0.0#");
    }
}
