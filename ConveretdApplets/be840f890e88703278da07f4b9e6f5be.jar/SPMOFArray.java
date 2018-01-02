// 
// Decompiled by Procyon v0.5.30
// 

class SPMOFArray extends WorldArray
{
    public float maxhop;
    public boolean blackout_flag;
    public boolean lp_blackout_flag;
    public float max_takeoff;
    public float min_takeoff;
    public float sex_1;
    public float sex_2;
    public float sex_3;
    public float sex_4;
    public float clof_1;
    public float clof_2;
    public float clof_3;
    public float clof_4;
    public float m9_1;
    public float min_phi;
    public float max_phi;
    public float te_minlat;
    public float te_maxlat;
    public float te_azim;
    public float te_1;
    private MofPanel p;
    
    public SPMOFArray(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.maxhop = 0.62784f;
        this.blackout_flag = false;
        this.lp_blackout_flag = true;
        this.max_takeoff = 0.5235988f;
        this.min_takeoff = 0.017453292f;
        this.sex_1 = 1.0f;
        this.sex_2 = 0.965f;
        this.sex_3 = 2.0f;
        this.sex_4 = -0.5f;
        this.clof_1 = 0.0f;
        this.clof_2 = 3.0f;
        this.clof_3 = 3.0f;
        this.clof_4 = 0.5f;
        this.m9_1 = 1.44f;
        this.min_phi = 0.5235988f;
        this.max_phi = 1.553343f;
        this.te_minlat = 0.0f;
        this.te_maxlat = 1.0471976f;
        this.te_azim = 1.5707964f;
        this.te_1 = 1.2f;
        this.p = p3;
        this.IsMOF = true;
    }
    
    public void Compute() {
        if (!this.p.Pathlen.IsChanged() && !this.p.Azim.IsChanged() && !this.p.LatRad.IsChanged() && !this.p.LonRad.IsChanged() && !this.p.H.IsChanged() && !this.p.G2.IsChanged() && !this.p.LS.IsChanged() && !this.p.MLat.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.row_to_rad(j);
                final float getValue = this.p.Pathlen.GetValue(j, i);
                final float getValue2 = this.p.Azim.GetValue(j, i);
                float n = 0.0f;
                float fmin = 100.0f;
                final int n2 = (int)(getValue / this.maxhop) + 1;
                final float getValue3 = this.p.LatRad.GetValue();
                final float getValue4 = this.p.LonRad.GetValue();
                float n3 = 0.0f;
                float fmin2 = 1000.0f;
                final float n4 = getValue / (2.0f * n2);
                for (int k = 1; k <= n2; ++k) {
                    final float n5 = n4 * (2.0f * k - 1.0f);
                    final float compute_lat = WorldArray.compute_lat(getValue3, getValue4, n5, getValue2);
                    float compute_lon = WorldArray.compute_lon(getValue3, getValue4, n5, getValue2);
                    final int rad_to_row = this.rad_to_row(compute_lat);
                    if (compute_lon < 0.0f) {
                        compute_lon += SPMOFArray.twopi;
                    }
                    final int rad_to_col = this.rad_to_col(compute_lon);
                    n3 += this.p.H.GetValue(rad_to_row, rad_to_col);
                    fmin2 = WorldArray.fmin(fmin2, this.p.H.GetValue(rad_to_row, rad_to_col));
                }
                final float n6 = n3 / n2;
                final float n7 = fmin2;
                final int n8 = (int)(getValue / (2.0f * WorldArray.acos(SPMOFArray.r0 / (n7 + SPMOFArray.r0)))) + 1;
                final float n9 = getValue / (2.0f * n8);
                final float n10 = this.m9_1 / WorldArray.cos(WorldArray.fmin(WorldArray.fmax(WorldArray.atan(WorldArray.sin(n9) / ((SPMOFArray.r0 + n7) / SPMOFArray.r0 - WorldArray.cos(n9))), this.min_phi), this.max_phi));
                final float n11 = this.sex_1 - this.sex_2 * WorldArray.pow(WorldArray.cos(WorldArray.fmin(WorldArray.fmax(WorldArray.atan((WorldArray.cos(n9) - SPMOFArray.r0 / (n7 + SPMOFArray.r0)) / WorldArray.sin(n9)), this.min_takeoff), this.max_takeoff)), this.sex_3);
                for (int l = 1; l <= n8; ++l) {
                    final float n12 = n9 * (2.0f * l - 1.0f);
                    final float compute_lat2 = WorldArray.compute_lat(getValue3, getValue4, n12, getValue2);
                    float compute_lon2 = WorldArray.compute_lon(getValue3, getValue4, n12, getValue2);
                    final int rad_to_row2 = this.rad_to_row(compute_lat2);
                    if (compute_lon2 < 0.0f) {
                        compute_lon2 += SPMOFArray.twopi;
                    }
                    final int rad_to_col2 = this.rad_to_col(compute_lon2);
                    fmin = WorldArray.fmin(fmin, this.p.G2.GetValue(rad_to_row2, rad_to_col2));
                    n += this.p.LS.GetValue(rad_to_row2, rad_to_col2);
                }
                final float getValue5 = this.p.MLat.GetValue(this.rad_to_row(getValue3), this.rad_to_col(getValue4));
                final float getValue6 = this.p.MLat.GetValue(j, i);
                if (WorldArray.sygn(getValue6) != WorldArray.sygn(getValue5) && WorldArray.fabs(getValue6) >= this.te_minlat && WorldArray.fabs(getValue6) <= this.te_maxlat && WorldArray.fabs(getValue5) >= this.te_minlat && WorldArray.fabs(getValue5) <= this.te_maxlat && ((getValue3 > 0.0f && getValue2 > SPMOFArray.pi - this.te_azim && getValue2 < SPMOFArray.pi + this.te_azim) || (getValue3 < 0.0f && (getValue2 < this.te_azim || getValue2 > SPMOFArray.twopi - this.te_azim)))) {
                    fmin *= this.te_1;
                }
                float fmin3 = WorldArray.fmin(WorldArray.fmax(fmin * n10, 1.0), 50.0);
                final float n13 = n * WorldArray.pow(WorldArray.fabs(n11), this.sex_4);
                if (fmin3 < WorldArray.fmin(WorldArray.fmax(this.clof_1 + this.clof_2 * n13 + this.clof_3 * WorldArray.pow(n13, this.clof_4), 1.0), 50.0) && this.blackout_flag) {
                    fmin3 = 0.0f;
                }
                this.SetValue(j, i, fmin3);
            }
        }
    }
}
