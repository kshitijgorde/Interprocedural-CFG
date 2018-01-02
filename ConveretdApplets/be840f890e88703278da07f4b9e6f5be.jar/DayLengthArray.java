// 
// Decompiled by Procyon v0.5.30
// 

class DayLengthArray extends WorldArray
{
    public float day_length_1;
    public float day_length_2;
    public float day_length_3;
    private MofPanel p;
    
    public DayLengthArray(final int n, final MofPanel p2) {
        super(n, 1);
        this.day_length_1 = 1.8325957f;
        this.day_length_2 = 12.0f;
        this.day_length_3 = 7.6394f;
        this.p = p2;
    }
    
    public void Compute() {
        if (!this.p.SolLat.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Length(); ++i) {
            final float row_to_rad = this.row_to_rad(i);
            final float getValue = this.p.SolLat.GetValue(i);
            this.SetValue(i, this.day_length_2 - WorldArray.asin(WorldArray.fmax(WorldArray.fmin((WorldArray.cos(this.day_length_1) + WorldArray.sin(-1.0f * getValue) * WorldArray.sin(row_to_rad)) / (WorldArray.cos(-1.0f * getValue) * WorldArray.cos(row_to_rad) + 0.001f), 1.0), -1.0)) * this.day_length_3);
        }
    }
}
