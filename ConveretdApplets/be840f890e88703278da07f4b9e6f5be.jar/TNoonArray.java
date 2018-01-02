// 
// Decompiled by Procyon v0.5.30
// 

class TNoonArray extends WorldArray
{
    private MofPanel p;
    
    public TNoonArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.Julian.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            final float col_to_rad = this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.row_to_rad(j);
                final float n = (float)(2.0f * TNoonArray.pi * this.p.Julian.GetValue() / 365.25);
                float n2 = (float)(3.82 * col_to_rad + 12.0 + 0.13 * (WorldArray.sin(n) + 1.2 * WorldArray.sin(2.0f * n)));
                if (n2 > 24.0) {
                    n2 -= 24.0f;
                }
                else if (n2 <= 0.0) {
                    n2 += 24.0f;
                }
                this.SetValue(j, i, n2);
            }
        }
    }
}
