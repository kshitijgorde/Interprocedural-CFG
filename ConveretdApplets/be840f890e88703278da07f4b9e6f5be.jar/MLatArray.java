// 
// Decompiled by Procyon v0.5.30
// 

class MLatArray extends WorldArray
{
    float mlon_1;
    float mlat_1;
    private MofPanel p;
    
    public MLatArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.mlon_1 = 1.2042772f;
        this.mlat_1 = 1.3664182f;
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.MLat.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            final float col_to_rad = this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.SetValue(j, i, MLatArray.halfpi - this.compute_range(this.mlat_1, this.mlon_1, this.row_to_rad(j), col_to_rad));
            }
        }
    }
}
