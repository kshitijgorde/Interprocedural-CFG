// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class DominoTower extends AbstractExample
{
    private boolean firstTime;
    final float dwidth = 0.2f;
    final float dheight = 1.0f;
    float ddensity;
    final float dfriction = 0.1f;
    int baseCount;
    
    public DominoTower(final TestbedMain _parent) {
        super(_parent);
        this.firstTime = true;
        this.baseCount = 25;
    }
    
    public void makeDomino(final float x, final float y, final boolean horizontal, final World world) {
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(0.1f, 0.5f);
        sd.density = this.ddensity;
        final BodyDef bd = new BodyDef();
        sd.friction = 0.1f;
        sd.restitution = 0.65f;
        bd.position = new Vec2(x, y);
        bd.angle = (horizontal ? 1.5707964f : 0.0f);
        final Body myBody = world.createDynamicBody(bd);
        myBody.createShape(sd);
        myBody.setMassFromShapes();
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 12.0f, 10.0f);
            this.firstTime = false;
            this.settings.hz = 120;
        }
        PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(0.0f, -10.0f);
        this.m_world.createStaticBody(bd).createShape(sd);
        this.ddensity = 10.0f;
        sd = new PolygonDef();
        sd.setAsBox(0.7f, 0.7f);
        sd.density = 35.0f;
        bd = new BodyDef();
        sd.friction = 0.0f;
        sd.restitution = 0.85f;
        bd.isBullet = true;
        bd.position = new Vec2(30.0f, 50.0f);
        Body b = this.m_world.createDynamicBody(bd);
        b.createShape(sd);
        b.setLinearVelocity(new Vec2(-25.0f, -25.0f));
        b.setAngularVelocity(6.7f);
        b.setMassFromShapes();
        sd.density = 25.0f;
        bd.position = new Vec2(-30.0f, 25.0f);
        b = this.m_world.createDynamicBody(bd);
        b.createShape(sd);
        b.setLinearVelocity(new Vec2(35.0f, -10.0f));
        b.setAngularVelocity(-8.3f);
        b.setMassFromShapes();
        for (int i = 0; i < this.baseCount; ++i) {
            final float currX = i * 1.5f * 1.0f - 1.5f * this.baseCount / 2.0f;
            this.makeDomino(currX, 0.5f, false, this.m_world);
            this.makeDomino(currX, 1.1f, true, this.m_world);
        }
        for (int j = 1; j < this.baseCount; ++j) {
            if (j > 3) {
                this.ddensity *= 0.8f;
            }
            final float currY = 0.5f + 1.386f * j;
            for (int k = 0; k < this.baseCount - j; ++k) {
                final float currX2 = k * 1.5f * 1.0f - 1.5f * (this.baseCount - j) / 2.0f;
                this.ddensity *= 2.5f;
                if (k == 0) {
                    this.makeDomino(currX2 - 1.25f + 0.1f, currY - 0.2f, false, this.m_world);
                }
                if (k == this.baseCount - j - 1 && j != 1) {
                    this.makeDomino(currX2 + 1.25f - 0.1f, currY - 0.2f, false, this.m_world);
                }
                this.ddensity /= 2.5f;
                this.makeDomino(currX2, currY, false, this.m_world);
                this.makeDomino(currX2, currY + 0.6f, true, this.m_world);
                this.makeDomino(currX2, currY - 0.6f, true, this.m_world);
            }
        }
    }
    
    public String getName() {
        return "Domino Tower Stress Test";
    }
}
