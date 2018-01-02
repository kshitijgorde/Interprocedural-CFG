// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed;

public class TestSettings
{
    public int hz;
    public int iterationCount;
    public boolean enableWarmStarting;
    public boolean enablePositionCorrection;
    public boolean enableTOI;
    public boolean pause;
    public boolean singleStep;
    public boolean drawShapes;
    public boolean drawJoints;
    public boolean drawCoreShapes;
    public boolean drawOBBs;
    public boolean drawCOMs;
    public boolean drawStats;
    public boolean drawImpulses;
    public boolean drawAABBs;
    public boolean drawPairs;
    public boolean drawContactPoints;
    public boolean drawContactNormals;
    public boolean drawContactForces;
    public boolean drawFrictionForces;
    
    public TestSettings() {
        this.hz = 60;
        this.iterationCount = 10;
        this.drawStats = true;
        this.drawAABBs = false;
        this.drawPairs = false;
        this.drawShapes = true;
        this.drawJoints = true;
        this.drawCoreShapes = false;
        this.drawContactPoints = false;
        this.drawContactNormals = false;
        this.drawContactForces = false;
        this.drawFrictionForces = false;
        this.drawOBBs = false;
        this.drawCOMs = false;
        this.enableWarmStarting = true;
        this.enablePositionCorrection = true;
        this.enableTOI = true;
        this.pause = false;
        this.singleStep = false;
    }
}
