// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class Bridge extends AbstractExample
{
    private boolean firstTime;
    
    public Bridge(final TestbedMain _parent) {
        super(_parent);
        this.firstTime = true;
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 10.0f, 20.0f);
            this.firstTime = false;
        }
        Body ground = null;
        PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 0.2f);
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, 0.0f);
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(0.65f, 0.125f);
        sd.density = 20.0f;
        sd.friction = 0.2f;
        final RevoluteJointDef jd = new RevoluteJointDef();
        final int numPlanks = 30;
        Body prevBody = ground;
        for (int i = 0; i < numPlanks; ++i) {
            final BodyDef bd2 = new BodyDef();
            bd2.position.set(-14.5f + 1.0f * i, 5.0f);
            final Body body = this.m_world.createDynamicBody(bd2);
            body.createShape(sd);
            body.setMassFromShapes();
            final Vec2 anchor = new Vec2(-15.0f + 1.0f * i, 5.0f);
            jd.initialize(prevBody, body, anchor);
            this.m_world.createJoint(jd);
            prevBody = body;
        }
        final Vec2 anchor2 = new Vec2(-15.0f + 1.0f * numPlanks, 5.0f);
        jd.initialize(prevBody, ground, anchor2);
        this.m_world.createJoint(jd);
        final PolygonDef pd2 = new PolygonDef();
        pd2.setAsBox(1.0f, 1.0f);
        pd2.density = 5.0f;
        pd2.friction = 0.2f;
        pd2.restitution = 0.1f;
        final BodyDef bd3 = new BodyDef();
        bd3.position.set(0.0f, 10.0f);
        final Body body2 = this.m_world.createDynamicBody(bd3);
        body2.createShape(pd2);
        body2.setMassFromShapes();
        final CircleDef cd = new CircleDef();
        cd.radius = 0.9f;
        cd.density = 5.0f;
        cd.friction = 0.2f;
        final BodyDef bd4 = new BodyDef();
        bd4.position.set(0.0f, 12.0f);
        final Body body3 = this.m_world.createDynamicBody(bd4);
        body3.createShape(cd);
        cd.localPosition.set(0.0f, 1.0f);
        body3.createShape(cd);
        body3.setMassFromShapes();
    }
    
    public String getName() {
        return "Bridge";
    }
}
