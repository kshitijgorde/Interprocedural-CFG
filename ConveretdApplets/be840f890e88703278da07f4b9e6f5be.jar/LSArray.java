// 
// Decompiled by Procyon v0.5.30
// 

class LSArray extends WorldArray
{
    public float fe_1;
    public float fe_2;
    public float fe_3;
    public float fe_4;
    public float fe_5;
    public float fe_6;
    public float fe_7;
    public float fe_8;
    public float fe_9;
    public float fe_10;
    public float ls_1;
    public float ls_2;
    private MofPanel p;
    
    public LSArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.fe_1 = 1.4835298f;
        this.fe_2 = 0.3333f;
        this.fe_3 = 3.4f;
        this.fe_4 = 1.0f;
        this.fe_5 = 0.0016f;
        this.fe_6 = -0.4f;
        this.fe_7 = 3.4f;
        this.fe_8 = 1.0f;
        this.fe_9 = 0.0016f;
        this.fe_10 = 0.08726646f;
        this.ls_1 = 0.028f;
        this.ls_2 = 2.0f;
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.SSN.IsChanged() && !this.p.SolAngle.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.row_to_rad(j);
                float getValue = this.p.SolAngle.GetValue(j, i);
                final float getValue2 = this.p.SSN.GetValue();
                float n;
                if (getValue < this.fe_1) {
                    final float fe_2 = this.fe_2;
                    if (getValue > 89.99 * LSArray.dtr) {
                        getValue = 89.99f * LSArray.dtr;
                    }
                    n = this.fe_3 * (this.fe_4 + this.fe_5 * getValue2) * WorldArray.pow(WorldArray.cos(getValue), fe_2);
                }
                else {
                    n = this.fe_7 * (this.fe_8 + this.fe_9 * getValue2) * WorldArray.pow((getValue - this.fe_1 + this.fe_10) * LSArray.rtd, this.fe_6);
                }
                this.SetValue(j, i, this.ls_1 * WorldArray.pow(n, this.ls_2));
            }
        }
    }
}
