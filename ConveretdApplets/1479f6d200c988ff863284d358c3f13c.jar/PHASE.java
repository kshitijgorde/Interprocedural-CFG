// 
// Decompiled by Procyon v0.5.30
// 

class PHASE
{
    int year;
    int locOffset;
    boolean demo;
    String[] Str;
    double[] jde;
    
    String[] PHASE(final int YEAR, final int offset, final boolean dem) {
        this.year = YEAR;
        this.locOffset = offset;
        this.demo = dem;
        this.getMoonPhases();
        return this.Str;
    }
    
    public void getMoonPhases() {
        int j = 0;
        final double YEAR = this.year - 0.1;
        final int kStart = (int)Math.round((YEAR - 2000.0) * 12.3685);
        for (int i = 0; i < 15; ++i) {
            final moonPhase myMoonPhase = new moonPhase(this.locOffset, this.demo, kStart + i);
            this.Str[j] = myMoonPhase.fullMoonStr();
            this.jde[j] = myMoonPhase.jdeTime();
            ++j;
        }
    }
    
    PHASE() {
        this.Str = new String[15];
        this.jde = new double[15];
    }
}
