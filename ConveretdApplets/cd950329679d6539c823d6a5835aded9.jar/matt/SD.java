// 
// Decompiled by Procyon v0.5.30
// 

package matt;

public class SD
{
    public static float sdFast(final float[] data) {
        float mean = 0.0f;
        final int n = data.length;
        if (n < 2) {
            return Float.NaN;
        }
        for (int i = 0; i < n; ++i) {
            mean += data[i];
        }
        mean /= n;
        float sum = 0.0f;
        for (int j = 0; j < n; ++j) {
            final float v = data[j] - mean;
            sum += v * v;
        }
        return (float)Math.sqrt(sum / (n - 1));
    }
    
    public static float sdKnuth(final float[] data) {
        final int n = data.length;
        if (n < 2) {
            return Float.NaN;
        }
        float avg = data[0];
        float sum = 0.0f;
        for (int i = 1; i < data.length; ++i) {
            final float newavg = avg + (data[i] - avg) / (i + 1);
            sum += (data[i] - avg) * (data[i] - newavg);
            avg = newavg;
        }
        return (float)Math.sqrt(sum / (n - 1));
    }
    
    public static void main(final String[] args) {
        final float[] data = { 10.0f, 100.0f, 50.0f };
        System.out.println(sdFast(data));
        System.out.println(sdKnuth(data));
    }
}
