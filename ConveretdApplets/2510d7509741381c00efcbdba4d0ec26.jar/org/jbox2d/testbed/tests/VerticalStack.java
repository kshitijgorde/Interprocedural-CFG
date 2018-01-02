// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class VerticalStack extends AbstractExample
{
    private boolean firstTime;
    
    public VerticalStack(final TestbedMain parent) {
        super(parent);
        this.firstTime = true;
    }
    
    public String getName() {
        return "Vertical Stack";
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 10.0f, 10.0f);
            this.firstTime = false;
        }
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f, new Vec2(0.0f, -10.0f), 0.0f);
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, 0.0f);
        final Body ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd.setAsBox(0.1f, 10.0f, new Vec2(20.0f, 10.0f), 0.0f);
        ground.createShape(sd);
        final float[] xs = { 0.0f, -10.0f, -5.0f, 5.0f, 10.0f };
        for (int j = 0; j < xs.length; ++j) {
            final PolygonDef sd2 = new PolygonDef();
            sd2.setAsBox(0.5f, 0.5f);
            sd2.density = 1.0f;
            sd2.friction = 0.3f;
            for (int i = 0; i < 12; ++i) {
                final BodyDef bd2 = new BodyDef();
                bd2.isBullet = true;
                bd2.allowSleep = true;
                bd2.position.set(xs[j] + this.parent.random(-0.05f, 0.05f), 0.752f + 1.54f * i);
                final Body body = this.m_world.createDynamicBody(bd2);
                body.createShape(sd2);
                body.setMassFromShapes();
            }
        }
    }
    
    public String getExampleInstructions() {
        return "Press , to shoot sideways bullet\n";
    }
    
    public void postStep() {
        if (this.newKeyDown[44]) {
            this.launchBomb(new Vec2(-40.0f, this.parent.random(1.0f, 10.0f)), new Vec2(200.0f, this.parent.random(-5.0f, 5.0f)));
        }
    }
}
