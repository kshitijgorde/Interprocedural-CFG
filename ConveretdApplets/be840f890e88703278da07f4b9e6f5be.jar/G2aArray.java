// 
// Decompiled by Procyon v0.5.30
// 

class G2aArray extends WorldArray
{
    public float dn_1;
    public float t9_d_1;
    public float t9_d_2;
    public float t9_d_3;
    public float t9_d_4;
    public float g8_d_1;
    public float g0_d_1;
    public float g0_d_2;
    public float g0_d_3;
    public float g0_d_4;
    public float g0_d_5;
    public float g2_d_1;
    public float g2_d_2;
    public float g2_d_3;
    public float g2_d_4;
    public float u_day_1;
    public float u1_day_1;
    public float noon_1;
    public float noon_2;
    public float noon_3;
    public float noon_4;
    public float noon_m1;
    public float noon_m2;
    public float noon_m3;
    public float noon_m4;
    public float noon_maxday;
    public float noon_minday;
    public float g0_d_ak1;
    public float g0_d_ak2;
    public float g0_d_ak3;
    public float t9_n_1;
    public float t9_n_2;
    public float t9_n_3;
    public float t9_n_4;
    public float g8_n_1;
    public float g0_n_1;
    public float g0_n_2;
    public float g0_n_3;
    public float u_n_1;
    public float u1_n_1;
    public float g2_n_1;
    public float g2_n_2;
    public float g2_n_3;
    public float g2_n_4;
    public float a1_1;
    public float a1_2;
    public float a1_3;
    public float a1_4;
    public float a1_5;
    public float g0_n_ak1;
    public float g0_n_ak2;
    public float g0_n_ak3;
    private MofPanel p;
    
    public G2aArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.dn_1 = 1.8325957f;
        this.t9_d_1 = 9.7f;
        this.t9_d_2 = 0.1f;
        this.t9_d_3 = 9.6f;
        this.t9_d_4 = 0.1f;
        this.g8_d_1 = 2.5f;
        this.g0_d_1 = 0.8f;
        this.g0_d_2 = 1.2f;
        this.g0_d_3 = 0.24f;
        this.g0_d_4 = 1.0f;
        this.g0_d_5 = 1.0f;
        this.g2_d_1 = 0.4f;
        this.g2_d_2 = 0.576f;
        this.g2_d_3 = 1.0f;
        this.g2_d_4 = 1.0f;
        this.u_day_1 = -1.0f;
        this.u1_day_1 = -1.0f;
        this.noon_1 = -13.9f;
        this.noon_2 = -0.093f;
        this.noon_3 = 3.16f;
        this.noon_4 = 0.5f;
        this.noon_m1 = 1.8f;
        this.noon_m2 = -0.0625f;
        this.noon_m3 = 0.6f;
        this.noon_m4 = 0.003f;
        this.noon_maxday = 22.0f;
        this.noon_minday = 12.0f;
        this.g0_d_ak1 = 0.0f;
        this.g0_d_ak2 = 0.0f;
        this.g0_d_ak3 = 0.125f;
        this.t9_n_1 = 9.7f;
        this.t9_n_2 = 0.1f;
        this.t9_n_3 = 15.0f;
        this.t9_n_4 = 0.1f;
        this.g8_n_1 = 3.14159f;
        this.g0_n_1 = 1.0f;
        this.g0_n_2 = 1.0f;
        this.g0_n_3 = 0.8f;
        this.u_n_1 = -1.2f;
        this.u1_n_1 = -1.0f;
        this.g2_n_1 = 16.0f;
        this.g2_n_2 = 142.0f;
        this.g2_n_3 = 0.5f;
        this.g2_n_4 = 0.5f;
        this.a1_1 = -155.9f;
        this.a1_2 = -1.47f;
        this.a1_3 = 42.7f;
        this.a1_4 = 0.5f;
        this.a1_5 = 158.5f;
        this.g0_n_ak1 = 0.0f;
        this.g0_n_ak2 = 0.0375f;
        this.g0_n_ak3 = 0.0f;
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.DayLength.IsChanged() && !this.p.CSolZen.IsChanged() && !this.p.TSunRise.IsChanged() && !this.p.TSunSet.IsChanged() && !this.p.AK.IsChanged() && !this.p.SSN.IsChanged() && !this.p.SF.IsChanged() && !this.p.Time.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.row_to_rad(j);
                final float getValue = this.p.DayLength.GetValue(j);
                final float getValue2 = this.p.CSolZen.GetValue(j);
                final float getValue3 = this.p.TSunRise.GetValue(j, i);
                final float getValue4 = this.p.TSunSet.GetValue(j, i);
                final float getValue5 = this.p.AK.GetValue();
                this.p.SSN.GetValue();
                final float getValue6 = this.p.SF.GetValue();
                final float getValue7 = this.p.Time.GetValue();
                final float fabs = WorldArray.fabs(getValue2);
                float n3;
                if (getValue2 > WorldArray.cos(this.dn_1)) {
                    float n = getValue7;
                    if ((getValue4 < getValue3 && (getValue7 - getValue4) * (getValue3 - getValue7) > 0.0) || (getValue4 >= getValue3 && (getValue7 - getValue3) * (getValue4 - getValue7) <= 0.0)) {
                        if (getValue4 > getValue7) {
                            n += 24.0f;
                        }
                        final float fmax = WorldArray.fmax(this.t9_n_1 * WorldArray.pow(WorldArray.fmax(fabs, this.t9_n_2), this.t9_n_3), this.t9_n_4);
                        final float n2 = this.g8_n_1 * fmax / getValue;
                        n3 = (this.a1_1 + this.a1_2 * getValue6 + this.a1_3 * WorldArray.pow(getValue6, this.a1_4)) / this.a1_5 * (1.0f - this.g0_n_ak1 * getValue5) * WorldArray.pow(this.g2_n_1 + this.g2_n_2 * WorldArray.pow(this.g0_n_3 * fabs * (n2 * (WorldArray.exp(WorldArray.fmin(WorldArray.fmax(this.u1_n_1 * getValue / fmax, -75.0f), 75.0f)) + this.g0_n_1)) * WorldArray.exp(WorldArray.fmin(WorldArray.fmax(this.u_n_1 * (n - getValue4) / 2.0f, -75.0f), 75.0f)) / (this.g0_n_2 + n2 * n2), this.g2_n_3 + this.g0_n_ak2 * getValue5), this.g2_n_4 + this.g0_n_ak3 * getValue5);
                    }
                    else {
                        final float n4 = this.noon_1 + this.noon_2 * getValue6 + this.noon_3 * WorldArray.pow(getValue6, this.noon_4);
                        final float n5 = 1.0f - (1.0f - (this.noon_m1 + this.noon_m2 * WorldArray.fmax(WorldArray.fmin(getValue, this.noon_maxday), this.noon_minday))) * (this.noon_m3 + this.noon_m4 * getValue6);
                        if (getValue3 > getValue7) {
                            n += 24.0f;
                        }
                        final float n6 = (n - getValue3) / getValue;
                        final float fmax2 = WorldArray.fmax(this.t9_d_1 * WorldArray.pow(WorldArray.fmax(fabs, this.t9_d_2), this.t9_d_3), this.t9_d_4);
                        final float n7 = this.g8_d_1 * fmax2 / getValue;
                        n3 = n4 * n5 * (1.0f - this.g0_d_ak1 * getValue5) * WorldArray.pow(this.g2_d_1 + this.g2_d_2 * WorldArray.pow(this.g0_d_3 + this.g0_d_2 * fabs * (WorldArray.sin(G2aArray.pi * WorldArray.pow(n6, this.g0_d_4)) + n7 * (WorldArray.exp(WorldArray.fmin(WorldArray.fmax(this.u_day_1 * (n - getValue3) / fmax2, -87.0f), 87.0f)) - WorldArray.cos(G2aArray.pi * WorldArray.pow(n6, this.g0_d_5)))) / (this.g0_d_1 + n7 * n7), this.g2_d_3 + this.g0_d_ak2 * getValue5), this.g2_d_4 + this.g0_d_ak3 * getValue5);
                    }
                }
                else {
                    n3 = (this.a1_1 + this.a1_2 * getValue6 + this.a1_3 * WorldArray.pow(getValue6, this.a1_4)) / this.a1_5 * (1.0f - this.g0_n_ak1 * getValue5) * WorldArray.pow(this.g2_n_1 + this.g2_n_2 * WorldArray.pow(0.0f, this.g2_n_3 + this.g0_n_ak2 * getValue5), this.g2_n_4 + this.g0_n_ak3 * getValue5);
                }
                this.SetValue(j, i, n3);
            }
        }
    }
}
