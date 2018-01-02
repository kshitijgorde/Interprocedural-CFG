// 
// Decompiled by Procyon v0.5.30
// 

class SolAngleArray extends WorldArray
{
    private MofPanel p;
    
    public SolAngleArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.TNoon.IsChanged() && !this.p.Time.IsChanged() && !this.p.SolLat.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                final float row_to_rad = this.row_to_rad(j);
                final float getValue = this.p.TNoon.GetValue(j, i);
                final float getValue2 = this.p.Time.GetValue();
                final float getValue3 = this.p.SolLat.GetValue();
                this.SetValue(j, i, WorldArray.acos(WorldArray.sin(row_to_rad) * WorldArray.sin(getValue3) + WorldArray.cos(row_to_rad) * WorldArray.cos(getValue3) * WorldArray.cos((getValue2 - getValue) * 15.0f * SolAngleArray.dtr)));
            }
        }
    }
}
