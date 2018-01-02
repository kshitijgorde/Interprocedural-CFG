// 
// Decompiled by Procyon v0.5.30
// 

class LPLOFArray extends WorldArray
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
    
    public LPLOFArray(final int n, final int n2, final MofPanel p3) {
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
        this.IsMOF = false;
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
                float n2 = getValue2 + LPLOFArray.pi;
                if (n2 > LPLOFArray.twopi) {
                    n2 -= LPLOFArray.twopi;
                }
                final float n3 = LPLOFArray.twopi - getValue;
                final int n4 = (int)(n3 / this.maxhop) + 1;
                final float getValue3 = this.p.LatRad.GetValue();
                final float getValue4 = this.p.LonRad.GetValue();
                float n5 = 0.0f;
                float fmin2 = 1000.0f;
                final float n6 = n3 / (2.0f * n4);
                for (int k = 1; k <= n4; ++k) {
                    final float n7 = n6 * (2.0f * k - 1.0f);
                    final float compute_lat = WorldArray.compute_lat(getValue3, getValue4, n7, n2);
                    float compute_lon = WorldArray.compute_lon(getValue3, getValue4, n7, n2);
                    final int rad_to_row = this.rad_to_row(compute_lat);
                    if (compute_lon < 0.0f) {
                        compute_lon += LPLOFArray.twopi;
                    }
                    final int rad_to_col = this.rad_to_col(compute_lon);
                    n5 += this.p.H.GetValue(rad_to_row, rad_to_col);
                    fmin2 = WorldArray.fmin(fmin2, this.p.H.GetValue(rad_to_row, rad_to_col));
                }
                final float n8 = n5 / n4;
                final float n9 = fmin2;
                final int n10 = (int)(n3 / (2.0f * WorldArray.acos(LPLOFArray.r0 / (n9 + LPLOFArray.r0)))) + 1;
                final float n11 = n3 / (2.0f * n10);
                final float n12 = this.m9_1 / WorldArray.cos(WorldArray.fmin(WorldArray.fmax(WorldArray.atan(WorldArray.sin(n11) / ((LPLOFArray.r0 + n9) / LPLOFArray.r0 - WorldArray.cos(n11))), this.min_phi), this.max_phi));
                final float n13 = this.sex_1 - this.sex_2 * WorldArray.pow(WorldArray.cos(WorldArray.fmin(WorldArray.fmax(WorldArray.atan((WorldArray.cos(n11) - LPLOFArray.r0 / (n9 + LPLOFArray.r0)) / WorldArray.sin(n11)), this.min_takeoff), this.max_takeoff)), this.sex_3);
                for (int l = 1; l <= n10; ++l) {
                    final float n14 = n11 * (2.0f * l - 1.0f);
                    final float compute_lat2 = WorldArray.compute_lat(getValue3, getValue4, n14, n2);
                    float compute_lon2 = WorldArray.compute_lon(getValue3, getValue4, n14, n2);
                    final int rad_to_row2 = this.rad_to_row(compute_lat2);
                    if (compute_lon2 < 0.0f) {
                        compute_lon2 += LPLOFArray.twopi;
                    }
                    final int rad_to_col2 = this.rad_to_col(compute_lon2);
                    fmin = WorldArray.fmin(fmin, this.p.G2.GetValue(rad_to_row2, rad_to_col2));
                    n += this.p.LS.GetValue(rad_to_row2, rad_to_col2);
                }
                final float fmin3 = WorldArray.fmin(WorldArray.fmax(fmin * n12, 1.0), 50.0);
                final float n15 = n * WorldArray.pow(WorldArray.fabs(n13), this.sex_4);
                float fmin4 = WorldArray.fmin(WorldArray.fmax(this.clof_1 + this.clof_2 * n15 + this.clof_3 * WorldArray.pow(n15, this.clof_4), 1.0), 50.0);
                if (fmin3 < fmin4 && this.lp_blackout_flag) {
                    fmin4 = 50.0f;
                }
                this.SetValue(j, i, fmin4);
            }
        }
    }
}
