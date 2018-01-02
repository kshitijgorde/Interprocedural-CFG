// 
// Decompiled by Procyon v0.5.30
// 

class CSolZenArray extends WorldArray
{
    private MofPanel p;
    
    public CSolZenArray(final int n, final MofPanel p2) {
        super(n, 1);
        this.p = p2;
    }
    
    public void Compute() {
        if (!this.p.SolLat.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Length(); ++i) {
            this.SetValue(i, WorldArray.cos(this.row_to_rad(i) - this.p.SolLat.GetValue(i)));
        }
    }
}
