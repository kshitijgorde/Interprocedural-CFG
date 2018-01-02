// 
// Decompiled by Procyon v0.5.30
// 

package nc.particle;

import nc.ADSR;

public class ParticleEmitterData
{
    private static final ADSR skDefaultADSR;
    public int mLayer;
    public int mMaxElements;
    public float mEmitRate;
    public ADSR mEmitADSR;
    public int mEmitDirection;
    public float mEmitXRange;
    public float mEmitYRange;
    public float mEmitDirectionX;
    public float mEmitDirectionY;
    public float mEmitDirectionXRange;
    public float mEmitDirectionYRange;
    public float mEmitSpeed;
    public float mEmitSpeedRange;
    public float mAccX;
    public float mAccY;
    public float mDrag;
    public float mLifeTime;
    public float mElementTimeStepScaleRange;
    
    public ParticleEmitterData() {
        this.mLayer = 0;
        this.mMaxElements = 10;
        this.mEmitRate = 10.0f;
        this.mEmitADSR = ParticleEmitterData.skDefaultADSR;
        this.mEmitDirection = 2;
        this.mEmitXRange = 40.0f;
        this.mEmitYRange = 30.0f;
        this.mEmitDirectionX = 0.0f;
        this.mEmitDirectionY = 0.0f;
        this.mEmitDirectionXRange = 0.05f;
        this.mEmitDirectionYRange = 0.05f;
        this.mEmitSpeed = 5.0f;
        this.mEmitSpeedRange = 2.0f;
        this.mAccX = 0.0f;
        this.mAccY = 0.0f;
        this.mDrag = 0.0f;
        this.mLifeTime = 1.0f;
        this.mElementTimeStepScaleRange = 0.0f;
    }
    
    static {
        skDefaultADSR = new ADSR(0.0f, 0.2f, 0.0f, 0.0f);
    }
}
