// 
// Decompiled by Procyon v0.5.30
// 

public class Random
{
    private static final int A = 48271;
    private static final int M = Integer.MAX_VALUE;
    private static final int Q = 44488;
    private static final int R = 3399;
    private int toestand;
    
    public Random() {
        this((int)(System.currentTimeMillis() % 2147483647L));
    }
    
    public Random(int toestand) {
        if (toestand < 0) {
            toestand += Integer.MAX_VALUE;
        }
        this.toestand = toestand;
        if (this.toestand == 0) {
            this.toestand = 1;
        }
    }
    
    public int poisson(final double n) {
        double exp;
        double randomDouble;
        int n2;
        for (exp = Math.exp(-n), randomDouble = this.randomDouble(), n2 = 0; randomDouble > exp; randomDouble *= this.randomDouble(), ++n2) {}
        return n2;
    }
    
    public double randomDouble() {
        return this.randomInt() / 2.147483647E9;
    }
    
    public int randomInt() {
        final int toestand = 48271 * (this.toestand % 44488) - 3399 * (this.toestand / 44488);
        if (toestand >= 0) {
            this.toestand = toestand;
        }
        else {
            this.toestand = toestand + Integer.MAX_VALUE;
        }
        return this.toestand;
    }
    
    public int randomInt(final int n, final int n2) {
        return (int)(this.randomInt() / (2.147483647E9 / (n2 - n + 1))) + n;
    }
}
