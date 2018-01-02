// 
// Decompiled by Procyon v0.5.30
// 

class EfloyParam
{
    float min;
    float max;
    float step;
    float value;
    int nsteps;
    String name;
    boolean mutatable;
    
    public EfloyParam(final float mn, final float mx, final float st, final float vl, final String nm, final boolean mt) {
        this.min = mn;
        this.max = mx;
        this.step = st;
        this.name = nm;
        this.value = vl;
        this.mutatable = mt;
        this.nsteps = (int)((mx - mn) / this.step);
    }
    
    public EfloyParam(final float mn, final float mx, final int ns, final float vl, final String nm, final boolean mt) {
        this.min = mn;
        this.max = mx;
        this.nsteps = ns;
        this.name = nm;
        this.value = vl;
        this.mutatable = mt;
        this.step = (mx - mn) / this.nsteps;
    }
    
    char EncodeValue() {
        final int n = (int)((this.value - this.min) / this.step);
        final char k = (char)(65 + n);
        return k;
    }
    
    float DecodeValue(final char kar) {
        try {
            final int n = kar - 'A';
            final float k = this.min + n * this.step;
            return k;
        }
        catch (Exception e) {
            return 0.0f;
        }
    }
}
