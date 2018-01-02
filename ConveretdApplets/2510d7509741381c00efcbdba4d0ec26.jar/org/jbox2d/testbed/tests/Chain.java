// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class Chain extends AbstractExample
{
    private boolean firstTime;
    
    public Chain(final TestbedMain _parent) {
        super(_parent);
        this.firstTime = true;
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 10.0f, 10.0f);
            this.firstTime = false;
        }
        Body ground = null;
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -10.0f);
        ground = this.m_world.createStaticBody(bd);
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        ground.createShape(sd);
        final PolygonDef sd2 = new PolygonDef();
        sd2.setAsBox(0.6f, 0.125f);
        sd2.density = 20.0f;
        sd2.friction = 0.2f;
        final RevoluteJointDef jd = new RevoluteJointDef();
        jd.collideConnected = false;
        final float y = 25.0f;
        Body prevBody = ground;
        for (int i = 0; i < 30; ++i) {
            final BodyDef bd2 = new BodyDef();
            bd2.position.set(0.5f + i, y);
            final Body body = this.m_world.createDynamicBody(bd2);
            body.createShape(sd2);
            body.setMassFromShapes();
            final Vec2 anchor = new Vec2(i, y);
            jd.initialize(prevBody, body, anchor);
            this.m_world.createJoint(jd);
            prevBody = body;
        }
    }
    
    public String getName() {
        return "Chain Test";
    }
}
