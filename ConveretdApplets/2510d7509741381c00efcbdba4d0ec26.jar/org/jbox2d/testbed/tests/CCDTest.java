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

public class CCDTest extends AbstractExample
{
    private boolean firstTime;
    
    public CCDTest(final TestbedMain _parent) {
        super(_parent);
        this.firstTime = true;
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 20.0f, 20.0f);
            this.firstTime = false;
        }
        final float k_restitution = 1.4f;
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
        sd.setAsBox(0.1f, 10.0f, new Vec2(0.0f, -10.0f), 1.57075f);
        body.createShape(sd);
        sd.setAsBox(0.1f, 10.0f, new Vec2(0.0f, 10.0f), -1.57075f);
        body.createShape(sd);
        final PolygonDef sd_bottom = new PolygonDef();
        sd_bottom.setAsBox(1.5f, 0.15f);
        sd_bottom.density = 4.0f;
        final PolygonDef sd_left = new PolygonDef();
        sd_left.setAsBox(0.15f, 2.7f, new Vec2(-1.45f, 2.35f), 0.2f);
        sd_left.density = 4.0f;
        final PolygonDef sd_right = new PolygonDef();
        sd_right.setAsBox(0.15f, 2.7f, new Vec2(1.45f, 2.35f), -0.2f);
        sd_right.density = 4.0f;
        final BodyDef bd2 = new BodyDef();
        bd2.position.set(0.0f, 15.0f);
        bd2.isBullet = true;
        final Body body2 = this.m_world.createDynamicBody(bd2);
        body2.createShape(sd_bottom);
        body2.createShape(sd_left);
        body2.createShape(sd_right);
        body2.setMassFromShapes();
        for (int i = 0; i < 10; ++i) {
            final BodyDef bd3 = new BodyDef();
            bd3.position.set(0.0f, 15.5f + i);
            bd3.isBullet = true;
            final Body body3 = this.m_world.createDynamicBody(bd3);
            body3.setAngularVelocity(this.parent.random(-50.0f, 50.0f));
            final CircleDef sd2 = new CircleDef();
            sd2.radius = 0.25f;
            sd2.density = 1.0f;
            sd2.restitution = 0.0f;
            sd2.friction = 0.05f;
            body3.createShape(sd2);
            body3.setMassFromShapes();
        }
    }
    
    public String getName() {
        return "Continuous Collision Test";
    }
}
