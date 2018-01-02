// 
// Decompiled by Procyon v0.5.30
// 

class SineTable
{
    static final int SINTABSIZE = 4096;
    static float[] value;
    
    static {
        SineTable.value = new float[4096];
        int n = 0;
        do {
            SineTable.value[n] = (float)Math.sin(n / 4096.0 * 2.0 * 3.141592653589793);
        } while (++n < 4096);
    }
}
