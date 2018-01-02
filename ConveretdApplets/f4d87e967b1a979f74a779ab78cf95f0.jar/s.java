import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends t implements Serializable
{
    public i p;
    public i d;
    public i a;
    public g n;
    public e v;
    String[] i;
    w l;
    
    public s() {
        this.p = new i("src1", this, 1, false);
        this.d = new i("src2", this, 1, false);
        this.a = new i("dst", this, 3, false);
        this.n = new g("LUT", this, 1, false);
        this.v = new e("blend mode", this, 1, true);
        this.i = new String[] { "blendmodes/blendmode_hardlight.jpg", "blendmodes/blendmode_softlight.jpg", "blendmodes/blendmode_screen.jpg", "blendmodes/blendmode_overlay.jpg", "blendmodes/blendmode_multiply.jpg", "blendmodes/blendmode_add.jpg", "blendmodes/blendmode_sub.jpg", "blendmodes/blendmode_dodge.jpg", "blendmodes/blendmode_burn.jpg", "blendmodes/blendmode_lighten.jpg", "blendmodes/blendmode_darken.jpg", "blendmodes/blendmode_difference.jpg", "blendmodes/blendmode_exclusion.jpg", "blendmodes/blendmode_opacity90.jpg", "blendmodes/blendmode_opacity80.jpg", "blendmodes/blendmode_opacity70.jpg", "blendmodes/blendmode_opacity60.jpg", "blendmodes/blendmode_opacity50.jpg", "blendmodes/blendmode_opacity40.jpg", "blendmodes/blendmode_opacity30.jpg", "blendmodes/blendmode_opacity20.jpg", "blendmodes/blendmode_opacity10.jpg" };
        this.l = new w();
    }
    
    public final boolean initialize() {
        this.v.p(-1);
        return true;
    }
    
    public final String[] p() {
        return this.i;
    }
    
    public final void connectorChanged(final v v) throws Exception {
        super.connectorChanged(v);
        if (v == this.v) {
            if (this.i == null) {
                throw new Exception("BlendLUT: blendModes[] is null");
            }
            if (this.v.p() < 0 || this.v.p() >= this.i.length) {
                throw new Exception("BlendLUT: blend mode index out of range");
            }
            try {
                this.l.p(this.i[this.v.p()]);
                this.l.d.p((v)this.n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final boolean execute() {
        final int[] a = this.p.a;
        final int[] a2 = this.d.a;
        final int[] a3 = this.a.a;
        final byte[] a4 = this.n.a;
        if (a == null || a2 == null || a3 == null || a4 == null) {
            return false;
        }
        for (int i = 0; i < a3.length; ++i) {
            a3[i] = ((a4[((a[i] >> 16 & 0xFF) << 8) + (a2[i] >> 16 & 0xFF)] & 0xFF) << 16 | (a4[((a[i] >> 8 & 0xFF) << 8) + (a2[i] >> 8 & 0xFF)] & 0xFF) << 8 | (a4[((a[i] & 0xFF) << 8) + (a2[i] & 0xFF)] & 0xFF));
        }
        return true;
    }
}
