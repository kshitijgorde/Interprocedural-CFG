// 
// Decompiled by Procyon v0.5.30
// 

class SinCos
{
    int[] sinus;
    int[] cosinus;
    
    public int zDSin(int n) {
        if (n < 0) {
            n += 360;
        }
        return this.sinus[n];
    }
    
    public int zRSin(final double n) {
        return this.zDSin((int)(n * 180.0 / 3.141592653589793));
    }
    
    public int zDCos(int n) {
        if (n < 0) {
            n += 360;
        }
        return this.cosinus[n];
    }
    
    public int zRCos(final double n) {
        return this.zDCos((int)(n * 180.0 / 3.141592653589793));
    }
    
    public SinCos() {
        this.sinus = new int[361];
        this.cosinus = new int[361];
        for (int i = 0; i <= 360; ++i) {
            this.sinus[i] = (int)(Math.sin(i * 3.141592653589793 / 180.0) * 256.0);
            this.cosinus[i] = (int)(Math.cos(i * 3.141592653589793 / 180.0) * 256.0);
        }
    }
}
