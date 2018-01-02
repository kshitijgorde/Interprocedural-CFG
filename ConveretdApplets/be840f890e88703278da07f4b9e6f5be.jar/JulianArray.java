// 
// Decompiled by Procyon v0.5.30
// 

class JulianArray extends WorldArray
{
    private MofPanel p;
    
    public JulianArray(final MofPanel p) {
        super(1, 1);
        this.p = p;
    }
    
    public void Compute() {
        if (!this.p.Day.IsChanged() && !this.p.Month.IsChanged()) {
            return;
        }
        this.SetValue(10 + (int)((this.p.Month.GetValue() - 1.0f) * 30.4 + this.p.Day.GetValue()));
    }
}
