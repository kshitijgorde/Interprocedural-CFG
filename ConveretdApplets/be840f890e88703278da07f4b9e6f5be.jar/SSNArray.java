// 
// Decompiled by Procyon v0.5.30
// 

class SSNArray extends WorldArray
{
    public float fms;
    public float fm1;
    public float fm2;
    public float fm3;
    public float fm4;
    private MofPanel p;
    
    public SSNArray(final MofPanel p) {
        super(1, 1);
        this.fms = 0.52998f;
        this.fm1 = 0.728f;
        this.fm2 = 0.00356f;
        this.fm3 = 63.75f;
        this.fm4 = 0.00178f;
        this.p = p;
    }
    
    public void Compute() {
        if (!this.p.SF.IsChanged()) {
            return;
        }
        this.SetValue(WorldArray.fmax(WorldArray.fmin((WorldArray.sqrt(this.fms - this.fm2 * (this.fm3 - this.p.SF.GetValue())) - this.fm1) / this.fm4, 250.0), 0.0));
    }
}
