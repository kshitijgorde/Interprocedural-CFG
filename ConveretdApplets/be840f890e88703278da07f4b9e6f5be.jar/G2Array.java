// 
// Decompiled by Procyon v0.5.30
// 

class G2Array extends WorldArray
{
    public float x_1;
    public float x_2;
    public float x_3;
    public float x_4;
    public float x_5;
    public float x_6;
    public float x_7;
    public float x_8;
    public float plr_flag;
    public float nplr_1;
    public float nplr_2;
    public float nplr_3;
    public float nplr_4;
    public float nplr_5;
    public float nplr_6;
    public float splr_1;
    public float splr_2;
    public float splr_3;
    public float splr_4;
    public float splr_5;
    public float splr_6;
    public float splr_7;
    public float splr_8;
    public float splr_9;
    public float splr_10;
    public float splr_11;
    public float g2_7;
    public float g2_8;
    public float g2_9;
    private MofPanel p;
    
    public G2Array(final int n, final int n2, final MofPanel p3) {
        super(n, n2);
        this.x_1 = 2.2f;
        this.x_2 = 0.2f;
        this.x_3 = 1000.0f;
        this.x_4 = 6.0f;
        this.x_5 = 18.0f;
        this.x_6 = 100.0f;
        this.x_7 = -1.1f;
        this.x_8 = -0.1f;
        this.plr_flag = 1.0f;
        this.nplr_1 = 2.0f;
        this.nplr_2 = 0.012f;
        this.nplr_3 = -1.2f;
        this.nplr_4 = -0.41015f;
        this.nplr_5 = 1.0f;
        this.nplr_6 = 0.3f;
        this.splr_1 = 2.5f;
        this.splr_2 = 50.0f;
        this.splr_3 = 0.5f;
        this.splr_4 = 1.3f;
        this.splr_5 = 0.002f;
        this.splr_6 = 1.3f;
        this.splr_7 = 0.005f;
        this.splr_8 = 1.0f;
        this.splr_9 = 1.0f;
        this.splr_10 = 0.4f;
        this.splr_11 = 1.0f;
        this.g2_7 = 2.85f;
        this.g2_8 = 8.12f;
        this.g2_9 = 0.66f;
        this.p = p3;
    }
    
    public void Compute() {
        if (!this.p.Time.IsChanged() && !this.p.Day.IsChanged() && !this.p.Month.IsChanged() && !this.p.DayLength.IsChanged() && !this.p.MLat.IsChanged() && !this.p.MLon.IsChanged() && !this.p.SSN.IsChanged() && !this.p.AK.IsChanged()) {
            return;
        }
        for (int i = 0; i < this.Width(); ++i) {
            final float col_to_rad = this.col_to_rad(i);
            for (int j = 0; j < this.Length(); ++j) {
                this.row_to_rad(j);
                final float getValue = this.p.G2a.GetValue(j, i);
                final float getValue2 = this.p.MLat.GetValue(j, i);
                final float getValue3 = this.p.MLon.GetValue(j, i);
                final float getValue4 = this.p.Time.GetValue(j, i);
                final float getValue5 = this.p.Month.GetValue();
                final float getValue6 = this.p.Day.GetValue();
                final float getValue7 = this.p.SSN.GetValue();
                final float getValue8 = this.p.AK.GetValue();
                final float getValue9 = this.p.DayLength.GetValue(j);
                float n;
                if (col_to_rad >= 0.0) {
                    n = col_to_rad;
                }
                else {
                    n = col_to_rad + G2Array.twopi;
                }
                float n2 = getValue4 - n * G2Array.rtd / 15.0f;
                if (n2 < 0.0) {
                    n2 += 24.0f;
                }
                else if (n2 >= 24.0) {
                    n2 -= 24.0f;
                }
                final float n3 = n2 * G2Array.pi / 12.0f;
                final float n4 = G2Array.pi * (getValue5 + (getValue6 + getValue4 / 24.0f) / 30.0f - 0.5f) / 12.0f;
                final float sin = WorldArray.sin(n4);
                final float cos = WorldArray.cos(getValue2);
                float n5;
                if (getValue2 >= 0.0) {
                    n5 = (this.nplr_1 + this.nplr_2 * getValue7) * WorldArray.exp(this.nplr_3 * (WorldArray.cos(getValue2 + this.nplr_4 * WorldArray.cos(n3)) - cos)) * (this.nplr_5 + this.nplr_6 * sin);
                }
                else {
                    final float cos2 = WorldArray.cos(n4 * n4);
                    final float sin2 = WorldArray.sin(getValue3 / 2.0f);
                    final float cos3 = WorldArray.cos(getValue3 / 2.0f - G2Array.pi / 20.0f);
                    final float sin3 = WorldArray.sin(getValue3);
                    final float n6 = sin * ((sin2 - sin3) / 2.0f - WorldArray.pow(sin2, 8.0f)) - (1.0f + sin) * cos2 * (sin3 / WorldArray.sqrt(WorldArray.fabs(sin3))) * WorldArray.exp(-4.0f * sin2 * sin2);
                    final float pow = WorldArray.pow(cos3, 4.0f);
                    n5 = this.splr_1 + getValue7 / this.splr_2 + cos2 * (this.splr_3 + (this.splr_4 + this.splr_5 * getValue7) * pow) + (this.splr_6 + this.splr_7 * getValue7) * WorldArray.cos(n3 - G2Array.pi * (this.splr_8 + n6)) + (this.splr_9 + this.splr_10 * (this.splr_11 - sin * sin)) * WorldArray.exp(-sin * pow);
                }
                final float exp = WorldArray.exp(-WorldArray.fmin(WorldArray.pow(WorldArray.fabs((this.x_1 + (this.x_2 + getValue7 / this.x_3) * WorldArray.sin(getValue2) + this.x_7 * WorldArray.fmax(WorldArray.fmin((getValue9 - this.x_5) * this.x_6, 1.0f), 0.0f) + this.x_8 * getValue8) * cos), this.x_4), 75.0f));
                float n7 = (1.0f - exp) * getValue * getValue / this.g2_8 + this.g2_9 * exp * n5;
                if (n7 < 0.0f) {
                    n7 = 0.0f;
                }
                this.SetValue(j, i, this.g2_7 * WorldArray.sqrt(n7));
            }
        }
    }
}
