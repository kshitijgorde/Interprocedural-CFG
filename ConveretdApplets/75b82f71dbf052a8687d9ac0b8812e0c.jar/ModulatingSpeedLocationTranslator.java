// 
// Decompiled by Procyon v0.5.30
// 

public class ModulatingSpeedLocationTranslator extends ConstantSpeedLocationTranslator
{
    protected float m_constantTime;
    protected float m_rampUpTime;
    protected float m_rampDownTime;
    protected long m_startTime;
    protected long m_pauseTime;
    protected long m_suspendTime;
    
    public ModulatingSpeedLocationTranslator(final Controller controller, final float[] vpTarget, final float rampUpTime, final float constantTime, final float rampDownTime, final int method) {
        super(controller, vpTarget, method);
        this.m_constantTime = 0.0f;
        this.m_rampUpTime = 0.0f;
        this.m_rampDownTime = 0.0f;
        this.m_startTime = 0L;
        this.m_pauseTime = 0L;
        this.m_suspendTime = 0L;
        this.m_constantTime = constantTime;
        this.m_rampUpTime = rampUpTime;
        this.m_rampDownTime = rampDownTime;
        this.m_startTime = ConstantSpeedLocationTranslator.queryHiResCounter();
    }
    
    public void resume() {
        if (this.m_startTime == 0L) {
            this.m_startTime = ConstantSpeedLocationTranslator.queryHiResCounter();
        }
        else {
            this.m_pauseTime += queryHiResCounter() - this.m_suspendTime;
        }
    }
    
    public void suspend() {
        this.m_suspendTime = ConstantSpeedLocationTranslator.queryHiResCounter();
    }
    
    public float getSpeed() {
        final float[] vp = { this.m_target[0] - this.m_startVP[0], this.m_target[1] - this.m_startVP[1], this.m_target[2] - this.m_startVP[2], this.m_target[3] - this.m_startVP[3] };
        float panRight;
        for (panRight = vp[0]; panRight < 0.0f; panRight += 6.283185307179586) {}
        while (panRight > 6.283185307179586) {
            panRight -= 6.283185307179586;
        }
        float panLeft;
        for (panLeft = vp[0]; panLeft < -6.283185307179586; panLeft += 6.283185307179586) {}
        while (panLeft > 0.0f) {
            panLeft -= 6.283185307179586;
        }
        switch (this.m_dir) {
            case 0: {
                vp[0] = panLeft;
                break;
            }
            case 1: {
                vp[0] = panRight;
                break;
            }
            case 2: {
                vp[0] = ((Math.abs(panRight) > Math.abs(panLeft)) ? panLeft : panRight);
                break;
            }
        }
        final float totalTime = this.m_rampUpTime + this.m_constantTime + this.m_rampDownTime;
        final float currentTime = (queryHiResCounter() - this.m_startTime - this.m_pauseTime) * 0.001f;
        float maxDist = Math.max(Math.max(Math.abs(vp[0]), Math.abs(vp[1])), Math.abs(vp[2]));
        if (maxDist <= 0.0f) {
            maxDist = Math.abs(vp[3]);
        }
        final float constantSpeed = maxDist * 2.0f / (this.m_rampUpTime + 2.0f * this.m_constantTime + this.m_rampDownTime);
        if (currentTime < this.m_rampUpTime && this.m_rampUpTime > 0.0f) {
            this.m_speed = currentTime / this.m_rampUpTime * constantSpeed;
        }
        else if (currentTime >= this.m_rampUpTime + this.m_constantTime && this.m_rampDownTime > 0.0f) {
            final float relTime = currentTime - this.m_rampUpTime - this.m_constantTime;
            this.m_speed = (this.m_rampDownTime - relTime) / this.m_rampDownTime * constantSpeed;
        }
        else {
            this.m_speed = constantSpeed;
        }
        final float totalRotateTime = this.m_rampUpTime + this.m_constantTime + this.m_rampDownTime;
        if (currentTime > totalRotateTime + totalRotateTime * 0.01) {
            this.m_controller.retireControl(this);
            return -1.0f;
        }
        if (this.m_speed < 0.0f) {
            this.m_controller.retireControl(this);
            return -1.0f;
        }
        return this.m_speed;
    }
}
