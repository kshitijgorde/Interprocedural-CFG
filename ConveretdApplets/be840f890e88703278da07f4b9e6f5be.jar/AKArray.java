// 
// Decompiled by Procyon v0.5.30
// 

class AKArray extends WorldArray
{
    public float ak_1;
    public float ak_2;
    private MofPanel p;
    
    public AKArray(final MofPanel p) {
        super(1, 1);
        this.ak_1 = 0.5f;
        this.ak_2 = 0.5f;
        this.p = p;
    }
    
    public void Compute() {
        if (!this.p.AIndex.IsChanged() && !this.p.KIndex.IsChanged()) {
            return;
        }
        final float getValue = this.p.AIndex.GetValue();
        final float getValue2 = this.p.KIndex.GetValue();
        float n;
        if (getValue > 0.0f) {
            n = this.ak_1 * getValue2 + this.ak_2 * WorldArray.log(getValue);
        }
        else {
            n = getValue2;
        }
        this.SetValue(n);
    }
}
