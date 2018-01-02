// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.common;

public class Settings
{
    public static final float EPSILON = 1.1920929E-7f;
    public static final float pi = 3.1415927f;
    public static final float lengthUnitsPerMeter = 1.0f;
    public static final float massUnitsPerKilogram = 1.0f;
    public static final float timeUnitsPerSecond = 1.0f;
    public static final int maxManifoldPoints = 2;
    public static final int maxShapesPerBody = 64;
    public static final int maxPolygonVertices = 8;
    public static final int maxProxies = 2048;
    public static final int maxPairs = 16384;
    public static final float linearSlop = 0.005f;
    public static final float angularSlop = 0.03490659f;
    public static final float velocityThreshold = 1.0f;
    public static final float maxLinearCorrection = 0.2f;
    public static final float maxAngularCorrection = 0.13962635f;
    public static final float contactBaumgarte = 0.2f;
    public static final float timeToSleep = 0.5f;
    public static final float linearSleepTolerance = 0.01f;
    public static final float angularSleepTolerance = 0.011111111f;
    public static final float toiSlop = 0.04f;
    public static final float maxLinearVelocity = 200.0f;
    public static final float maxLinearVelocitySquared = 40000.0f;
    public static final float maxAngularVelocity = 250.0f;
    public static final float maxAngularVelocitySquared = 62500.0f;
    public static int maxTOIContactsPerIsland;
    
    static {
        Settings.maxTOIContactsPerIsland = 32;
    }
}
