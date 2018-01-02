// 
// Decompiled by Procyon v0.5.30
// 

class LocationTranslator extends Translator
{
    protected float[] m_target;
    protected int m_step;
    protected int m_last;
    private int m_method;
    
    LocationTranslator(final Controller c) {
        this(c, (float[])c.getHost().getPipeline().getSource().getProperty("invp"));
    }
    
    LocationTranslator(final Controller c, final float[] vp) {
        this.m_step = 1;
        this.m_controller = c;
        (this.m_target = Util.copy(vp))[3] = Math.max(this.m_target[3], 0.4f);
        final float[] dp = Util.delta(c.getHost().getViewpoint(), this.m_target);
        final float da = (float)Math.sqrt(dp[0] * dp[0] + dp[1] * dp[1] + dp[2] * dp[2] + dp[3] * dp[3]);
        this.m_last = Math.max((int)(da * 40.0f / this.getIntensity()), 2);
        this.m_method = 3;
    }
    
    LocationTranslator(final Controller c, final float[] vp, final int method) {
        this.m_step = 1;
        this.m_controller = c;
        this.m_method = method;
        (this.m_target = Util.copy(vp))[3] = Math.max(this.m_target[3], 0.4f);
        final float[] dp = Util.delta(c.getHost().getViewpoint(), this.m_target, this.m_method);
        if (dp[0] < 0.0f && this.m_method == 1) {
            final float[] array = dp;
            final int n = 0;
            array[n] += 6.283185307179586;
        }
        else if (dp[0] > 0.0f && this.m_method == 0) {
            final float[] array2 = dp;
            final int n2 = 0;
            array2[n2] -= 6.283185307179586;
        }
        final float da = (float)Math.sqrt(dp[0] * dp[0] + dp[1] * dp[1] + dp[2] * dp[2] + dp[3] * dp[3]);
        this.m_last = Math.max((int)(da * 40.0f / this.getIntensity()), 2);
    }
    
    boolean updateViewpoint(final float[] vp) {
        if (this.m_method == 3) {
            return this.updateViewpointSimple(vp);
        }
        if (!this.m_controller.isActive(this)) {
            return false;
        }
        if (this.m_method == 2) {
            if (vp[0] - this.m_target[0] > 3.1415927f) {
                final int n = 0;
                vp[n] -= 6.2831855f;
            }
            else if (this.m_target[0] - vp[0] > 3.1415927f) {
                final int n2 = 0;
                vp[n2] += 6.2831855f;
            }
        }
        final float[] dv = Util.delta(vp, this.m_target, this.m_method);
        if (dv[0] < 0.0 && this.m_method == 1) {
            final float[] array = dv;
            final int n3 = 0;
            array[n3] += 6.2831855f;
        }
        else if (dv[0] > 0.0 && this.m_method == 0) {
            final float[] array2 = dv;
            final int n4 = 0;
            array2[n4] -= 6.2831855f;
        }
        final float RATIO = (this.m_last - this.m_step - 1) / (this.m_last - this.m_step);
        vp[0] = this.m_target[0] - dv[0] * RATIO;
        vp[1] = this.m_target[1] - dv[1] * RATIO;
        vp[2] = this.m_target[2] - dv[2] * RATIO;
        vp[3] = (float)Math.exp(Math.log(this.m_target[3]) - dv[3] * RATIO);
        if (++this.m_step == this.m_last) {
            this.m_controller.retireControl(this);
        }
        return true;
    }
    
    boolean updateViewpointSimple(final float[] vp) {
        if (!this.m_controller.isActive(this)) {
            return false;
        }
        if (vp[0] - this.m_target[0] > 3.1415927f) {
            final int n = 0;
            vp[n] -= 6.2831855f;
        }
        else if (this.m_target[0] - vp[0] > 3.1415927f) {
            final int n2 = 0;
            vp[n2] += 6.2831855f;
        }
        final float[] dv = Util.delta(vp, this.m_target);
        final float RATIO = (this.m_last - this.m_step - 1) / (this.m_last - this.m_step);
        vp[0] = this.m_target[0] - dv[0] * RATIO;
        vp[1] = this.m_target[1] - dv[1] * RATIO;
        vp[2] = this.m_target[2] - dv[2] * RATIO;
        vp[3] = (float)Math.exp(Math.log(this.m_target[3]) - dv[3] * RATIO);
        if (++this.m_step == this.m_last) {
            this.m_controller.retireControl(this);
        }
        return true;
    }
}
