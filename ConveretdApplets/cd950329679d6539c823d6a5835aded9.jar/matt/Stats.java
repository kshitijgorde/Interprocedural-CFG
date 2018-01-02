// 
// Decompiled by Procyon v0.5.30
// 

package matt;

public class Stats
{
    private float[] data;
    private int start;
    private int end;
    
    public Stats(final float[] data) {
        this.setData(data);
    }
    
    public float[] getData() {
        return this.data;
    }
    
    public void setData(final float[] data) {
        this.data = data;
        this.start = 0;
        this.end = data.length;
    }
    
    float average() {
        assert this.data != null && this.end != 0;
        float sum = 0.0f;
        for (int i = this.start; i < this.end; ++i) {
            sum += this.data[i];
        }
        return sum / (this.end - this.start);
    }
    
    float standardDeviation() {
        assert this.data != null && this.end != 0;
        final float average = this.average();
        float averageDifferenceSquared = 0.0f;
        for (int i = this.start; i < this.end; ++i) {
            final float difference = this.data[i] - average;
            averageDifferenceSquared += (float)Math.pow(difference, 2.0);
        }
        averageDifferenceSquared /= this.end - this.start;
        final float stdDev = (float)Math.sqrt(averageDifferenceSquared);
        return stdDev;
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
}
