// 
// Decompiled by Procyon v0.5.30
// 

class MLonArray extends WorldArray
{
    float mlon_1;
    float mlat_1;
    private MofPanel p;
    
    public MLonArray(final int n, final int n2, final MofPanel p3) {
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
                final float row_to_rad = this.row_to_rad(j);
                final float compute_range = this.compute_range(this.mlat_1, this.mlon_1, row_to_rad, col_to_rad);
                final float compute_azim = this.compute_azim(this.mlat_1, this.mlon_1, row_to_rad, col_to_rad);
                float asin = WorldArray.asin(WorldArray.fmax(WorldArray.fmin(WorldArray.sin(MLonArray.halfpi - row_to_rad) * WorldArray.sin(col_to_rad - this.mlon_1) / WorldArray.sin(compute_range), 1.0f), -1.0f));
                if (compute_azim < MLonArray.halfpi || compute_azim > MLonArray.twopi - MLonArray.halfpi) {
                    asin = MLonArray.pi - asin;
                }
                if (asin > MLonArray.pi) {
                    asin -= MLonArray.twopi;
                }
                this.SetValue(j, i, asin);
            }
        }
    }
}
