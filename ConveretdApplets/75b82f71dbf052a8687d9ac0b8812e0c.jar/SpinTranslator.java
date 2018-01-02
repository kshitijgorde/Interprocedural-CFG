// 
// Decompiled by Procyon v0.5.30
// 

class SpinTranslator extends Translator
{
    protected float mDp;
    protected float scale;
    
    SpinTranslator(final Controller c, final int spinSpeed) {
        this.mDp = 1.1f;
        this.m_controller = c;
        this.scale = spinSpeed;
        this.scale = Util.limit(this.scale, -10.0f, 10.0f) * 0.00127f;
    }
    
    boolean updateViewpoint(final float[] vp) {
        if (this.scale == 0.0f) {
            this.m_controller.retireControl(this);
            return false;
        }
        if (!this.m_controller.isActive(this)) {
            return false;
        }
        if (this.m_controller.getHost().getDecoder() != null) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            return false;
        }
        final Pipeline pipeline = this.m_controller.getHost().getPipeline();
        final Frame src = pipeline.getSource();
        final float hFovMin = pipeline.gethFOVMin(vp[3]);
        final float hFovMax = pipeline.gethFOVMax(vp[3]);
        final float dscale = this.scale * this.getIntensity();
        if (hFovMin != hFovMax && (vp[0] > hFovMax - 1.0E-4f || vp[0] < hFovMin + 1.0E-4f)) {
            this.mDp = -this.mDp;
        }
        final int n = 0;
        vp[n] += dscale * this.mDp;
        final float dt = (float)Math.log(Math.abs(vp[1]) + 1.0f) * Math.abs(this.scale);
        if (vp[1] > 0.0f) {
            final int n2 = 1;
            vp[n2] -= dt;
        }
        else if (vp[1] < 0.0f) {
            final int n3 = 1;
            vp[n3] += dt;
        }
        return true;
    }
}
