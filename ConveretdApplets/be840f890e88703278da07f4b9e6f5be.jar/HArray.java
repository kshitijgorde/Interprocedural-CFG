// 
// Decompiled by Procyon v0.5.30
// 

class HArray extends WorldArray
{
    public float dn_1;
    public float h_min;
    public float h_max;
    public float h_d_1;
    public float h_d_2;
    public float h_d_3;
    public float h_d_4;
    public float h_d_5;
    public float h_d_6;
    public float h_d_7;
    public float h_d_8;
    public float h_d_9;
    public float h_d_10;
    public float h_n_1;
    public float h_n_2;
    public float h_n_3;
    public float h_n_4;
    public float h_n_5;
    public float h_n_6;
    public float h_n_7;
    public float h_n_8;
    public float h_n_9;
    public float h_n_10;
    public float h_n;
    private MofPanel p;
    
    public HArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.dn_1 = 1.8325957f;
        this.h_min = 100.0f;
        this.h_max = 500.0f;
        this.h_d_1 = 1.0f;
        this.h_d_2 = 0.0f;
        this.h_d_3 = 1.0f;
        this.h_d_4 = 1.0f;
        this.h_d_5 = 2.0f;
        this.h_d_6 = 2.0f;
        this.h_d_7 = 230.0f;
        this.h_d_8 = 200.0f;
        this.h_d_9 = 0.5f;
        this.h_d_10 = -10.0f;
        this.h_n_1 = 1.0f;
        this.h_n_2 = 0.0f;
        this.h_n_3 = 1.0f;
        this.h_n_4 = 1.0f;
        this.h_n_5 = 2.0f;
        this.h_n_6 = 1.0f;
        this.h_n_7 = 230.0f;
        this.h_n_8 = 0.0f;
        this.h_n_9 = 0.5f;
        this.h_n_10 = -10.0f;
        this.h_n = 300.0f;
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.CSolZen.IsChanged() && !this.p.TSunRise.IsChanged() && !this.p.TSunSet.IsChanged() && !this.p.SSN.IsChanged() && !this.p.AK.IsChanged() && !this.p.DayLength.IsChanged() && !this.p.Time.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.row_to_rad(j);
                final float getValue = this.p.CSolZen.GetValue(j);
                final float getValue2 = this.p.TSunRise.GetValue(j, i);
                final float getValue3 = this.p.TSunSet.GetValue(j, i);
                final float getValue4 = this.p.SSN.GetValue();
                final float getValue5 = this.p.AK.GetValue();
                final float getValue6 = this.p.DayLength.GetValue(j);
                final float getValue7 = this.p.Time.GetValue();
                WorldArray.fabs(getValue);
                float h_n;
                if (getValue > WorldArray.cos(this.dn_1)) {
                    float n = getValue7;
                    if ((getValue3 < getValue2 && (getValue7 - getValue3) * (getValue2 - getValue7) > 0.0) || (getValue3 >= getValue2 && (getValue7 - getValue2) * (getValue3 - getValue7) <= 0.0)) {
                        if (getValue3 > getValue7) {
                            n += 24.0f;
                        }
                        h_n = this.h_n_7 + this.h_n_8 * ((WorldArray.pow(this.h_n_1 * getValue + this.h_n_2, this.h_n_3) + this.h_n_4) / this.h_n_5) * WorldArray.sin(WorldArray.pow((n - getValue3) / getValue6, this.h_n_6) * HArray.pi) + this.h_n_9 * getValue4 + this.h_n_10 * getValue5;
                    }
                    else {
                        if (getValue2 > getValue7) {
                            n += 24.0f;
                        }
                        h_n = this.h_d_7 + this.h_d_8 * ((WorldArray.pow(this.h_d_1 * getValue + this.h_d_2, this.h_d_3) + this.h_d_4) / this.h_d_5) * WorldArray.sin(WorldArray.pow((n - getValue2) / getValue6, this.h_d_6) * HArray.pi) + this.h_d_9 * getValue4 + this.h_d_10 * getValue5;
                    }
                }
                else {
                    h_n = this.h_n;
                }
                this.SetValue(j, i, WorldArray.fmin(this.h_max, WorldArray.fmax(this.h_min, h_n)));
            }
        }
    }
}
