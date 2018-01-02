// 
// Decompiled by Procyon v0.5.30
// 

package matt;

public class TranscribedNote
{
    private int midiNote;
    private float frequency;
    private float start;
    private float duration;
    private float unmergedDuration;
    private float unmergedStart;
    private float energy;
    private String name;
    private int quaverQ;
    private int multiple;
    
    public TranscribedNote() {
        this.name = "NA";
    }
    
    public TranscribedNote(final float frequency, final float start, final float duration) {
        this.name = "NA";
        this.setFrequency(frequency);
        this.start = start;
        this.unmergedStart = start;
        this.duration = duration;
        this.unmergedDuration = duration;
    }
    
    public float getFrequency() {
        return this.frequency;
    }
    
    public void setFrequency(final float frequency) {
        this.frequency = frequency;
    }
    
    public float getStart() {
        return this.start;
    }
    
    public void setStart(final float start) {
        this.start = start;
    }
    
    public float getDuration() {
        return this.duration;
    }
    
    public void setDuration(final float duration) {
        this.duration = duration;
    }
    
    public String getSpelling() {
        return this.name;
    }
    
    public void setSpelling(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return "" + this.getStart() + "\t" + this.getDuration() + "\t" + this.getFrequency() + "\t" + EnergyCalculator.formatEnergy(this.getEnergy()) + "\t" + this.getSpelling() + "\t" + this.getMultiple() + "\t" + this.getQuaverQ();
    }
    
    public float getEnergy() {
        return this.energy;
    }
    
    public void setEnergy(final float energy) {
        this.energy = energy;
    }
    
    public int getQuaverQ() {
        return this.quaverQ;
    }
    
    public void setQuaverQ(final int quaverQ) {
        this.quaverQ = quaverQ;
    }
    
    public int getMultiple() {
        return this.multiple;
    }
    
    public void setMultiple(final int multiple) {
        this.multiple = multiple;
    }
    
    public float getUnmergedDuration() {
        return this.unmergedDuration;
    }
    
    public void setUnmergedDuration(final float unmergedDuration) {
        this.unmergedDuration = unmergedDuration;
    }
    
    public float getUnmergedStart() {
        return this.unmergedStart;
    }
    
    public void setUnmergedStart(final float unmergedStart) {
        this.unmergedStart = unmergedStart;
    }
    
    public int getMidiNote() {
        return this.midiNote;
    }
    
    public void setMidiNote(final int midiNote) {
        this.midiNote = midiNote;
    }
}
