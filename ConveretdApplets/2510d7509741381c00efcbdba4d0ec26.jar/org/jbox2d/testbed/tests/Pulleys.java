// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.PulleyJointDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.PulleyJoint;
import org.jbox2d.testbed.AbstractExample;

public class Pulleys extends AbstractExample
{
    PulleyJoint m_joint1;
    PrismaticJoint m_joint2;
    
    public Pulleys(final TestbedMain p) {
        super(p);
    }
    
    public void create() {
        Body ground = null;
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -10.0f);
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        final float a = 2.0f;
        final float b = 4.0f;
        final float y = 16.0f;
        final float L = 12.0f;
        final PolygonDef sd2 = new PolygonDef();
        sd2.setAsBox(a, b);
        sd2.density = 5.0f;
        final BodyDef bd2 = new BodyDef();
        bd2.position.set(-10.0f, y);
        final Body body1 = this.m_world.createDynamicBody(bd2);
        body1.createShape(sd2);
        body1.setMassFromShapes();
        bd2.position.set(10.0f, y);
        final Body body2 = this.m_world.createDynamicBody(bd2);
        body2.createShape(sd2);
        body2.setMassFromShapes();
        final PulleyJointDef pulleyDef = new PulleyJointDef();
        final Vec2 anchor1 = new Vec2(-10.0f, y + b);
        final Vec2 anchor2 = new Vec2(10.0f, y + b);
        final Vec2 groundAnchor1 = new Vec2(-10.0f, y + b + L);
        final Vec2 groundAnchor2 = new Vec2(10.0f, y + b + L);
        pulleyDef.initialize(body1, body2, groundAnchor1, groundAnchor2, anchor1, anchor2, 2.0f);
        this.m_joint1 = (PulleyJoint)this.m_world.createJoint(pulleyDef);
    }
    
    public void postStep() {
        final float ratio = this.m_joint1.getRatio();
        final float L = this.m_joint1.getLength1() + ratio * this.m_joint1.getLength2();
        this.m_debugDraw.drawString(5.0f, this.m_textLine, "L1 + " + ratio + " * L2 = " + L, Pulleys.white);
        this.m_textLine += Pulleys.textLineHeight;
    }
    
    public String getName() {
        return "Pulleys";
    }
}
