// 
// Decompiled by Procyon v0.5.30
// 

class SolLatArray extends WorldArray
{
    private MofPanel p;
    
    public SolLatArray(final MofPanel p) {
        super(1, 1);
        this.p = p;
    }
    
    public void Compute() {
        if (!this.p.Julian.IsChanged()) {
            return;
        }
        this.SetValue(-0.409f * WorldArray.cos((float)(2.0f * SolLatArray.pi * this.p.Julian.GetValue() / 365.25)));
    }
}
