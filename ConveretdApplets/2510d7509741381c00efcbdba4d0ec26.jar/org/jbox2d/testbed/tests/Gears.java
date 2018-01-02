// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.GearJointDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.PrismaticJointDef;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.dynamics.joints.GearJoint;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.testbed.AbstractExample;

public class Gears extends AbstractExample
{
    RevoluteJoint m_joint1;
    RevoluteJoint m_joint2;
    PrismaticJoint m_joint3;
    GearJoint m_joint4;
    GearJoint m_joint5;
    
    public Gears(final TestbedMain _parent) {
        super(_parent);
    }
    
    public String getName() {
        return "Gears";
    }
    
    public void create() {
        Body ground = null;
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -10.0f);
        ground = this.m_world.createStaticBody(bd);
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        ground.createShape(sd);
        final CircleDef circle1 = new CircleDef();
        circle1.radius = 1.0f;
        circle1.density = 5.0f;
        final CircleDef circle2 = new CircleDef();
        circle2.radius = 2.0f;
        circle2.density = 5.0f;
        final PolygonDef box = new PolygonDef();
        box.setAsBox(0.5f, 5.0f);
        box.density = 5.0f;
        final BodyDef bd2 = new BodyDef();
        bd2.position.set(-3.0f, 12.0f);
        final Body body1 = this.m_world.createDynamicBody(bd2);
        body1.createShape(circle1);
        body1.setMassFromShapes();
        final RevoluteJointDef jd1 = new RevoluteJointDef();
        jd1.body1 = ground;
        jd1.body2 = body1;
        jd1.localAnchor1 = ground.getLocalPoint(bd2.position);
        jd1.localAnchor2 = body1.getLocalPoint(bd2.position);
        jd1.referenceAngle = body1.getAngle() - ground.getAngle();
        this.m_joint1 = (RevoluteJoint)this.m_world.createJoint(jd1);
        final BodyDef bd3 = new BodyDef();
        bd3.position.set(0.0f, 12.0f);
        final Body body2 = this.m_world.createDynamicBody(bd3);
        body2.createShape(circle2);
        body2.setMassFromShapes();
        final RevoluteJointDef jd2 = new RevoluteJointDef();
        jd2.initialize(ground, body2, bd3.position);
        this.m_joint2 = (RevoluteJoint)this.m_world.createJoint(jd2);
        final BodyDef bd4 = new BodyDef();
        bd4.position.set(2.5f, 12.0f);
        final Body body3 = this.m_world.createDynamicBody(bd4);
        body3.createShape(box);
        body3.setMassFromShapes();
        final PrismaticJointDef jd3 = new PrismaticJointDef();
        jd3.initialize(ground, body3, bd4.position, new Vec2(0.0f, 1.0f));
        jd3.lowerTranslation = -5.0f;
        jd3.upperTranslation = 5.0f;
        jd3.enableLimit = true;
        this.m_joint3 = (PrismaticJoint)this.m_world.createJoint(jd3);
        final GearJointDef jd4 = new GearJointDef();
        jd4.body1 = body1;
        jd4.body2 = body2;
        jd4.joint1 = this.m_joint1;
        jd4.joint2 = this.m_joint2;
        jd4.ratio = circle2.radius / circle1.radius;
        this.m_joint4 = (GearJoint)this.m_world.createJoint(jd4);
        final GearJointDef jd5 = new GearJointDef();
        jd5.body1 = body2;
        jd5.body2 = body3;
        jd5.joint1 = this.m_joint2;
        jd5.joint2 = this.m_joint3;
        jd5.ratio = -1.0f / circle2.radius;
        this.m_joint5 = (GearJoint)this.m_world.createJoint(jd5);
    }
}
