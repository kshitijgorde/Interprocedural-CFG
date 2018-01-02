// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class BipedTest extends AbstractExample
{
    private boolean firstTime;
    Biped m_biped;
    
    public BipedTest(final TestbedMain p) {
        super(p);
        this.firstTime = true;
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 20.0f, 15.0f);
            this.firstTime = false;
        }
        final float k_restitution = 1.4f;
        this.settings.drawJoints = false;
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, 20.0f);
        final Body body = this.m_world.createStaticBody(bd);
        final PolygonDef sd = new PolygonDef();
        sd.density = 0.0f;
        sd.restitution = 1.4f;
        sd.setAsBox(0.1f, 10.0f, new Vec2(-10.0f, 0.0f), 0.0f);
        body.createShape(sd);
        sd.setAsBox(0.1f, 10.0f, new Vec2(10.0f, 0.0f), 0.0f);
        body.createShape(sd);
        sd.setAsBox(0.1f, 10.0f, new Vec2(0.0f, -10.0f), 1.5707964f);
        body.createShape(sd);
        sd.setAsBox(0.1f, 10.0f, new Vec2(0.0f, 10.0f), -1.5707964f);
        body.createShape(sd);
        this.m_biped = new Biped(this.m_world, new Vec2(0.0f, 20.0f));
        for (int i = 0; i < 8; ++i) {
            final BodyDef bd2 = new BodyDef();
            bd2.position.set(5.0f, 20.0f + i);
            bd2.isBullet = true;
            final Body body2 = this.m_world.createDynamicBody(bd2);
            body2.setLinearVelocity(new Vec2(0.0f, -100.0f));
            body2.setAngularVelocity(this.parent.random(-50.0f, 50.0f));
            final CircleDef sd2 = new CircleDef();
            sd2.radius = 0.25f;
            sd2.density = 15.0f;
            sd2.restitution = 1.4f;
            body2.createShape(sd2);
            body2.setMassFromShapes();
        }
    }
    
    public String getName() {
        return "Biped Test";
    }
}
