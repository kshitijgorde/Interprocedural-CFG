// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.ADSR;
import nc.particle.ParticleElementData;
import nc.particle.ParticleEmitterData;

public class ParticleDatabase
{
    static final int LAYER_BACKGROUND = 0;
    static final int LAYER_FOREGROUND = 1;
    ParticleEmitterData mFireworkSplash;
    ParticleEmitterData mSpreadingSplash;
    ParticleEmitterData mRocket;
    ParticleEmitterData mSpray;
    ParticleEmitterData mLongSpray;
    ParticleEmitterData mForegroundSplash;
    ParticleEmitterData mBombSplash;
    ParticleEmitterData mShootingStar;
    ParticleEmitterData mFireworkBloom;
    ParticleElementData mSparkly;
    ParticleElementData mFlame;
    ParticleElementData mRotator;
    ParticleElementData mNova;
    ParticleElementData mStar;
    ParticleElementData mBloomSparkly;
    ParticleElementData mTwinkle;
    
    public ParticleDatabase(final Res res) {
        final ParticleEmitterData particleEmitterData = this.mFireworkSplash = new ParticleEmitterData();
        particleEmitterData.mLayer = 0;
        particleEmitterData.mMaxElements = 50;
        particleEmitterData.mEmitRate = 40.0f;
        particleEmitterData.mLifeTime = 1.0f;
        particleEmitterData.mEmitADSR = new ADSR(0.5f, 0.5f, 0.0f, 0.0f);
        particleEmitterData.mEmitDirection = 2;
        particleEmitterData.mEmitXRange = 200.0f;
        particleEmitterData.mEmitYRange = 40.0f;
        particleEmitterData.mEmitSpeed = 20.0f;
        particleEmitterData.mEmitSpeedRange = 10.0f;
        final ParticleEmitterData particleEmitterData2 = this.mSpreadingSplash = new ParticleEmitterData();
        particleEmitterData2.mLayer = 0;
        particleEmitterData2.mMaxElements = 10;
        particleEmitterData2.mEmitRate = 50.0f;
        particleEmitterData2.mEmitADSR = new ADSR(0.0f, 0.2f, 0.0f, 0.0f);
        particleEmitterData2.mEmitDirection = 2;
        particleEmitterData2.mEmitXRange = 30.0f;
        particleEmitterData2.mEmitYRange = 30.0f;
        particleEmitterData2.mEmitSpeed = 10.0f;
        particleEmitterData2.mEmitSpeedRange = 0.0f;
        final ParticleEmitterData particleEmitterData3 = this.mSpray = new ParticleEmitterData();
        particleEmitterData3.mLayer = 0;
        particleEmitterData3.mMaxElements = 100;
        particleEmitterData3.mEmitRate = 50.0f;
        particleEmitterData3.mEmitADSR = new ADSR(0.0f, 0.0f, 1.0f, 0.0f);
        particleEmitterData3.mEmitDirection = 3;
        particleEmitterData3.mEmitDirectionX = 0.0f;
        particleEmitterData3.mEmitDirectionY = 20.0f;
        particleEmitterData3.mEmitDirectionXRange = 200.0f;
        particleEmitterData3.mEmitDirectionYRange = 0.0f;
        particleEmitterData3.mEmitXRange = 20.0f;
        particleEmitterData3.mEmitYRange = 20.0f;
        particleEmitterData3.mEmitSpeed = 400.0f;
        particleEmitterData3.mEmitSpeedRange = 300.0f;
        particleEmitterData3.mLifeTime = 0.6f;
        final ParticleEmitterData particleEmitterData4 = this.mLongSpray = new ParticleEmitterData();
        particleEmitterData4.mLayer = 0;
        particleEmitterData4.mMaxElements = 100;
        particleEmitterData4.mEmitRate = 50.0f;
        particleEmitterData4.mEmitADSR = new ADSR(0.0f, 0.0f, 1.0f, 0.0f);
        particleEmitterData4.mEmitDirection = 3;
        particleEmitterData4.mEmitDirectionX = 0.0f;
        particleEmitterData4.mEmitDirectionY = 20.0f;
        particleEmitterData4.mEmitDirectionXRange = 200.0f;
        particleEmitterData4.mEmitDirectionYRange = 0.0f;
        particleEmitterData4.mEmitXRange = 20.0f;
        particleEmitterData4.mEmitYRange = 20.0f;
        particleEmitterData4.mEmitSpeed = 400.0f;
        particleEmitterData4.mEmitSpeedRange = 300.0f;
        particleEmitterData4.mLifeTime = 1.2f;
        final ParticleEmitterData particleEmitterData5 = this.mFireworkBloom = new ParticleEmitterData();
        particleEmitterData5.mLayer = 0;
        particleEmitterData5.mMaxElements = 100;
        particleEmitterData5.mEmitRate = 1500.0f;
        particleEmitterData5.mLifeTime = 0.2f;
        particleEmitterData5.mEmitADSR = new ADSR(0.0f, 0.0f, 1.0f, 0.0f);
        particleEmitterData5.mEmitDirection = 2;
        particleEmitterData5.mEmitXRange = 5.0f;
        particleEmitterData5.mEmitYRange = 5.0f;
        particleEmitterData5.mEmitSpeed = 150.0f;
        particleEmitterData5.mEmitSpeedRange = 300.0f;
        particleEmitterData5.mElementTimeStepScaleRange = 0.5f;
        this.mBloomSparkly = new ParticleElementData(res.mImages[7], 1, 14, true, 0.0f, 0.0f, true, 1.5f, 1, 0.0f, 0.0f, 7.5f, 0.84f);
        this.mSparkly = new ParticleElementData(res.mImages[7], 1, 14, true, 0.0f, 0.0f, true, 1.5f, 1, 0.0f, 100.0f, 5.0f, 1.0f);
        this.mTwinkle = new ParticleElementData(res.mImages[7], 1, 14, true, 0.0f, 0.0f, false, 1.5f, 1, 0.0f, 0.0f, 1.0f, 1.0f);
        final ParticleEmitterData particleEmitterData6 = this.mRocket = new ParticleEmitterData();
        particleEmitterData6.mLayer = 1;
        particleEmitterData6.mMaxElements = 20;
        particleEmitterData6.mEmitRate = 15.0f;
        particleEmitterData6.mEmitADSR = new ADSR(0.0f, 0.0f, 1.0f, 2.0f);
        particleEmitterData6.mEmitDirection = 1;
        particleEmitterData6.mEmitXRange = 8.0f;
        particleEmitterData6.mEmitYRange = 8.0f;
        particleEmitterData6.mEmitDirectionXRange = 4.0f;
        particleEmitterData6.mEmitDirectionYRange = 4.0f;
        particleEmitterData6.mEmitSpeed = 50.0f;
        particleEmitterData6.mEmitSpeedRange = 5.0f;
        particleEmitterData6.mLifeTime = 4.0f;
        this.mFlame = new ParticleElementData(res.mImages[8], 1, 15, true, 0.0f, 0.0f, false, 0.0f, 1, 0.0f, 0.0f, 10.0f, 0.5f);
        this.mRotator = new ParticleElementData(res.mImages[6], 1, 9, true, 0.0f, 0.0f, true, 1.0f, 1, 0.0f, 0.0f, 10.0f, 0.7f);
        this.mNova = new ParticleElementData(res.mImages[5], 1, 10, true, 0.0f, 0.0f, true, 1.0f, 1, 0.0f, 0.0f, 10.0f, 0.5f);
        final ParticleEmitterData particleEmitterData7 = this.mForegroundSplash = new ParticleEmitterData();
        particleEmitterData7.mLayer = 1;
        particleEmitterData7.mMaxElements = 50;
        particleEmitterData7.mEmitRate = 200.0f;
        particleEmitterData7.mEmitADSR = new ADSR(0.1f, 0.1f, 0.0f, 0.0f);
        particleEmitterData7.mEmitDirection = 2;
        particleEmitterData7.mEmitXRange = 30.0f;
        particleEmitterData7.mEmitYRange = 20.0f;
        particleEmitterData7.mEmitSpeed = 20.0f;
        particleEmitterData7.mEmitSpeedRange = 10.0f;
        this.mStar = new ParticleElementData(res.mImages[30], 0, 1, false, 1.0f, 0.0f, true, 0.5f, 0, 0.0f, 5.0f, 1.0f, 1.0f);
        final ParticleEmitterData particleEmitterData8 = this.mBombSplash = new ParticleEmitterData();
        particleEmitterData8.mLayer = 1;
        particleEmitterData8.mMaxElements = 8;
        particleEmitterData8.mEmitRate = 200.0f;
        particleEmitterData8.mEmitADSR = new ADSR(0.1f, 0.1f, 0.0f, 0.0f);
        particleEmitterData8.mEmitDirection = 2;
        particleEmitterData8.mEmitXRange = 30.0f;
        particleEmitterData8.mEmitYRange = 30.0f;
        particleEmitterData8.mEmitSpeed = 20.0f;
        particleEmitterData8.mEmitSpeedRange = 10.0f;
        particleEmitterData8.mLifeTime = 0.2f;
        final ParticleEmitterData particleEmitterData9 = this.mShootingStar = new ParticleEmitterData();
        particleEmitterData9.mLayer = 0;
        particleEmitterData9.mMaxElements = 100;
        particleEmitterData9.mEmitRate = 50.0f;
        particleEmitterData9.mLifeTime = 0.5f;
        particleEmitterData9.mEmitADSR = new ADSR(0.0f, 0.0f, 1.0f, 0.25f);
        particleEmitterData9.mEmitDirection = 2;
        particleEmitterData9.mEmitDirectionXRange = 10.0f;
        particleEmitterData9.mEmitDirectionYRange = 10.0f;
        particleEmitterData9.mEmitXRange = 10.0f;
        particleEmitterData9.mEmitYRange = 10.0f;
        particleEmitterData9.mEmitSpeed = 20.0f;
        particleEmitterData9.mEmitSpeedRange = 10.0f;
        particleEmitterData9.mDrag = 0.01f;
    }
}