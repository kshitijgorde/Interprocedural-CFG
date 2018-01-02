// 
// Decompiled by Procyon v0.5.30
// 

class AzimArray extends WorldArray
{
    private MofPanel p;
    
    public AzimArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.LatRad.IsChanged() && !this.p.LonRad.IsChanged()) {
            return;
        }
        final float getValue = this.p.LatRad.GetValue();
        final float getValue2 = this.p.LonRad.GetValue();
        for (int i = 0; i < this.Width(); ++i) {
            final float col_to_rad = this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.SetValue(j, i, this.compute_azim(getValue, getValue2, this.row_to_rad(j), col_to_rad));
            }
        }
    }
}
