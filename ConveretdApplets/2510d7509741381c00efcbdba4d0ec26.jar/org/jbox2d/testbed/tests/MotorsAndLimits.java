// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.PrismaticJointDef;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.testbed.AbstractExample;

public class MotorsAndLimits extends AbstractExample
{
    RevoluteJoint m_joint1;
    RevoluteJoint m_joint2;
    PrismaticJoint m_joint3;
    
    public MotorsAndLimits(final TestbedMain _parent) {
        super(_parent);
    }
    
    public String getExampleInstructions() {
        return "[l] toggles prismatic limit\n[m] toggles motor\n[p] reverses prismatic motor direction";
    }
    
    public String getName() {
        return "Motors And Limits";
    }
    
    public void create() {
        Body ground = null;
        PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -10.0f);
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(2.0f, 0.5f);
        sd.density = 5.0f;
        sd.friction = 0.05f;
        bd = new BodyDef();
        final RevoluteJointDef rjd = new RevoluteJointDef();
        Body body = null;
        Body prevBody = ground;
        final float y = 8.0f;
        bd.position.set(3.0f, y);
        body = this.m_world.createDynamicBody(bd);
        body.createShape(sd);
        body.setMassFromShapes();
        rjd.initialize(prevBody, body, new Vec2(0.0f, y));
        rjd.motorSpeed = 3.1415927f;
        rjd.maxMotorTorque = 10000.0f;
        rjd.enableMotor = true;
        this.m_joint1 = (RevoluteJoint)this.m_world.createJoint(rjd);
        prevBody = body;
        bd.position.set(9.0f, y);
        body = this.m_world.createDynamicBody(bd);
        body.createShape(sd);
        body.setMassFromShapes();
        rjd.initialize(prevBody, body, new Vec2(6.0f, y));
        rjd.motorSpeed = 1.5707964f;
        rjd.maxMotorTorque = 2000.0f;
        rjd.enableMotor = true;
        rjd.lowerAngle = -1.5707964f;
        rjd.upperAngle = 1.5707964f;
        rjd.enableLimit = true;
        this.m_joint2 = (RevoluteJoint)this.m_world.createJoint(rjd);
        bd.position.set(-10.0f, 10.0f);
        bd.angle = 1.5707964f;
        body = this.m_world.createDynamicBody(bd);
        body.createShape(sd);
        body.setMassFromShapes();
        final PrismaticJointDef pjd = new PrismaticJointDef();
        pjd.initialize(ground, body, new Vec2(-10.0f, 10.0f), new Vec2(1.0f, 0.0f));
        pjd.motorSpeed = 10.0f;
        pjd.maxMotorForce = 1000.0f;
        pjd.enableMotor = true;
        pjd.lowerTranslation = 0.0f;
        pjd.upperTranslation = 20.0f;
        pjd.enableLimit = true;
        this.m_joint3 = (PrismaticJoint)this.m_world.createJoint(pjd);
    }
    
    public void preStep() {
        if (this.newKeyDown[108]) {
            this.m_joint2.enableLimit(!this.m_joint2.isLimitEnabled());
            this.m_joint3.enableLimit(!this.m_joint3.isLimitEnabled());
            this.m_joint2.getBody1().wakeUp();
            this.m_joint3.getBody2().wakeUp();
        }
        if (this.newKeyDown[109]) {
            this.m_joint1.enableMotor(!this.m_joint1.isMotorEnabled());
            this.m_joint2.enableMotor(!this.m_joint2.isMotorEnabled());
            this.m_joint3.enableMotor(!this.m_joint3.isMotorEnabled());
            this.m_joint2.getBody1().wakeUp();
            this.m_joint3.getBody2().wakeUp();
        }
        if (this.newKeyDown[112]) {
            this.m_joint3.getBody2().wakeUp();
            this.m_joint3.setMotorSpeed(-this.m_joint3.getMotorSpeed());
            this.settings.pause = false;
        }
    }
    
    public void postStep() {
        final float torque1 = this.m_joint1.getMotorTorque();
        final float torque2 = this.m_joint2.getMotorTorque();
        final float force3 = this.m_joint3.getMotorForce();
        this.m_debugDraw.drawString(5.0f, this.m_textLine, "Motor Torque = " + torque1 + ", " + torque2 + " : Motor Force = " + force3, MotorsAndLimits.white);
        this.m_textLine += MotorsAndLimits.textLineHeight;
    }
}
