// 
// Decompiled by Procyon v0.5.30
// 

class TSunSetArray extends WorldArray
{
    private MofPanel p;
    
    public TSunSetArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.TNoon.IsChanged() && !this.p.DayLength.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.row_to_rad(j);
                float n = this.p.TNoon.GetValue(j, i) + this.p.DayLength.GetValue(j) / 2.0f;
                if (n > 24.0) {
                    n -= 24.0f;
                }
                this.SetValue(j, i, n);
            }
        }
    }
}
