// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.particle.ParticleSystem;
import sexy.gui.SexyColor;

public class Fireworks
{
    static final int skTouchHack = 1;
    static final SexyColor skBlue;
    static final SexyColor skGreen;
    static final SexyColor skRed;
    static final SexyColor skYellow;
    static final SexyColor skPurple;
    
    private static SexyColor randomColour(final RocketManiaApplet rocketManiaApplet) {
        SexyColor sexyColor = Fireworks.skBlue;
        switch (rocketManiaApplet.mBoard.mRand.Next() % 5) {
            case 0: {
                sexyColor = Fireworks.skPurple;
                break;
            }
            case 1: {
                sexyColor = Fireworks.skBlue;
                break;
            }
            case 2: {
                sexyColor = Fireworks.skGreen;
                break;
            }
            case 3: {
                sexyColor = Fireworks.skYellow;
                break;
            }
            case 4: {
                sexyColor = Fireworks.skRed;
                break;
            }
        }
        return sexyColor;
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    static {
        skBlue = new SexyColor(64, 64, 255);
        skGreen = new SexyColor(64, 255, 64);
        skRed = new SexyColor(255, 64, 64);
        skYellow = new SexyColor(255, 224, 64);
        skPurple = new SexyColor(255, 64, 192);
    }
    
    static void AddFirework(final RocketManiaApplet rocketManiaApplet, final ParticleSystem particleSystem, final ParticleDatabase particleDatabase, final int n, final int n2) {
        final int n3 = 120 + rocketManiaApplet.mBoard.mRand.Next() % 360;
        final int n4 = rocketManiaApplet.mBoard.mRand.Next() % 40;
        switch (n) {
            case 0: {
                particleSystem.CreateParticleEmitter(particleDatabase.mSpray, particleDatabase.mSparkly, randomColour(rocketManiaApplet), n3, -10.0f, 0.0f, 0.0f);
                particleSystem.CreateParticleEmitter(particleDatabase.mSpray, particleDatabase.mSparkly, randomColour(rocketManiaApplet), n3, -10.0f, 0.0f, 0.0f);
                particleSystem.CreateParticleEmitter(particleDatabase.mSpray, particleDatabase.mSparkly, randomColour(rocketManiaApplet), n3, -10.0f, 0.0f, 0.0f);
                rocketManiaApplet.PlaySound(16);
            }
            case 1: {
                particleSystem.CreateParticleEmitter(particleDatabase.mSpreadingSplash, particleDatabase.mRotator, randomColour(rocketManiaApplet), n3, n2 + n4, 0.0f, 0.0f);
                rocketManiaApplet.PlaySound(4);
            }
            case 2: {
                particleSystem.CreateParticleEmitter(particleDatabase.mFireworkSplash, particleDatabase.mNova, randomColour(rocketManiaApplet), n3, n2 + n4, 0.0f, 0.0f);
                rocketManiaApplet.PlaySound(16);
            }
            case 3: {
                particleSystem.CreateParticleEmitter(particleDatabase.mLongSpray, particleDatabase.mSparkly, randomColour(rocketManiaApplet), n3, -10.0f, 0.0f, 0.0f);
                particleSystem.CreateParticleEmitter(particleDatabase.mLongSpray, particleDatabase.mSparkly, randomColour(rocketManiaApplet), n3, -10.0f, 0.0f, 0.0f);
                rocketManiaApplet.PlaySound(16);
            }
            case 4: {
                int n5 = 0;
                do {
                    particleSystem.CreateParticleEmitter(particleDatabase.mFireworkBloom, particleDatabase.mBloomSparkly, randomColour(rocketManiaApplet), n3 - 40 + rocketManiaApplet.mBoard.mRand.Next() % 80, n2 + n4 - 20 + rocketManiaApplet.mBoard.mRand.Next() % 40, 0.0f, 0.0f);
                } while (++n5 < 2);
                rocketManiaApplet.PlaySound(4);
            }
            case 5: {
                particleSystem.CreateParticleEmitter(particleDatabase.mSpreadingSplash, particleDatabase.mNova, randomColour(rocketManiaApplet), n3, n2 + n4, 0.0f, 0.0f);
                rocketManiaApplet.PlaySound(16);
            }
            case 6: {
                int n6 = 0;
                do {
                    particleSystem.CreateParticleEmitter(particleDatabase.mFireworkBloom, particleDatabase.mBloomSparkly, randomColour(rocketManiaApplet), 120 + rocketManiaApplet.mBoard.mRand.Next() % 360, n2 + rocketManiaApplet.mBoard.mRand.Next() % 80, 0.0f, 0.0f);
                } while (++n6 < 5);
                rocketManiaApplet.PlaySound(4);
            }
            case 7: {
                particleSystem.CreateParticleEmitter(particleDatabase.mShootingStar, particleDatabase.mSparkly, randomColour(rocketManiaApplet), 100.0f, 10 + n2, 400.0f, 400.0f);
                particleSystem.CreateParticleEmitter(particleDatabase.mShootingStar, particleDatabase.mSparkly, randomColour(rocketManiaApplet), 500.0f, 10 + n2, -400.0f, 400.0f);
                particleSystem.CreateParticleEmitter(particleDatabase.mShootingStar, particleDatabase.mSparkly, randomColour(rocketManiaApplet), 250 + rocketManiaApplet.mBoard.mRand.Next() % 60, -20.0f, 400.0f, 400.0f);
                particleSystem.CreateParticleEmitter(particleDatabase.mShootingStar, particleDatabase.mSparkly, randomColour(rocketManiaApplet), 250 + rocketManiaApplet.mBoard.mRand.Next() % 60, -20.0f, -400.0f, 400.0f);
                rocketManiaApplet.PlaySound(16);
            }
            case 8: {
                particleSystem.CreateParticleEmitter(particleDatabase.mSpreadingSplash, particleDatabase.mRotator, randomColour(rocketManiaApplet), 150.0f, n2 + n4, 0.0f, 0.0f);
                particleSystem.CreateParticleEmitter(particleDatabase.mSpreadingSplash, particleDatabase.mRotator, randomColour(rocketManiaApplet), 450.0f, n2 + n4, 0.0f, 0.0f);
                rocketManiaApplet.PlaySound(4);
            }
            case 9: {
                AddFirework(rocketManiaApplet, particleSystem, particleDatabase, rocketManiaApplet.mBoard.mRand.Next() % 9, n2);
                AddFirework(rocketManiaApplet, particleSystem, particleDatabase, rocketManiaApplet.mBoard.mRand.Next() % 9, n2);
            }
            default: {}
        }
    }
}
