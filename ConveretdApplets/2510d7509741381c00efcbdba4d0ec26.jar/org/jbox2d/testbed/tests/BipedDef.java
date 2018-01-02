// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;

public class BipedDef
{
    public static int count;
    static float k_scale;
    public BodyDef LFootDef;
    public BodyDef RFootDef;
    public BodyDef LCalfDef;
    public BodyDef RCalfDef;
    public BodyDef LThighDef;
    public BodyDef RThighDef;
    public BodyDef PelvisDef;
    public BodyDef StomachDef;
    public BodyDef ChestDef;
    public BodyDef NeckDef;
    public BodyDef HeadDef;
    public BodyDef LUpperArmDef;
    public BodyDef RUpperArmDef;
    public BodyDef LForearmDef;
    public BodyDef RForearmDef;
    public BodyDef LHandDef;
    public BodyDef RHandDef;
    public PolygonDef LFootPoly;
    public PolygonDef RFootPoly;
    public PolygonDef LCalfPoly;
    public PolygonDef RCalfPoly;
    public PolygonDef LThighPoly;
    public PolygonDef RThighPoly;
    public PolygonDef PelvisPoly;
    public PolygonDef StomachPoly;
    public PolygonDef ChestPoly;
    public PolygonDef NeckPoly;
    public PolygonDef LUpperArmPoly;
    public PolygonDef RUpperArmPoly;
    public PolygonDef LForearmPoly;
    public PolygonDef RForearmPoly;
    public PolygonDef LHandPoly;
    public PolygonDef RHandPoly;
    public CircleDef HeadCirc;
    public RevoluteJointDef LAnkleDef;
    public RevoluteJointDef RAnkleDef;
    public RevoluteJointDef LKneeDef;
    public RevoluteJointDef RKneeDef;
    public RevoluteJointDef LHipDef;
    public RevoluteJointDef RHipDef;
    public RevoluteJointDef LowerAbsDef;
    public RevoluteJointDef UpperAbsDef;
    public RevoluteJointDef LowerNeckDef;
    public RevoluteJointDef UpperNeckDef;
    public RevoluteJointDef LShoulderDef;
    public RevoluteJointDef RShoulderDef;
    public RevoluteJointDef LElbowDef;
    public RevoluteJointDef RElbowDef;
    public RevoluteJointDef LWristDef;
    public RevoluteJointDef RWristDef;
    
    static {
        BipedDef.count = 0;
        BipedDef.k_scale = 3.0f;
    }
    
    public BipedDef() {
        this.LFootDef = new BodyDef();
        this.RFootDef = new BodyDef();
        this.LCalfDef = new BodyDef();
        this.RCalfDef = new BodyDef();
        this.LThighDef = new BodyDef();
        this.RThighDef = new BodyDef();
        this.PelvisDef = new BodyDef();
        this.StomachDef = new BodyDef();
        this.ChestDef = new BodyDef();
        this.NeckDef = new BodyDef();
        this.HeadDef = new BodyDef();
        this.LUpperArmDef = new BodyDef();
        this.RUpperArmDef = new BodyDef();
        this.LForearmDef = new BodyDef();
        this.RForearmDef = new BodyDef();
        this.LHandDef = new BodyDef();
        this.RHandDef = new BodyDef();
        this.LFootPoly = new PolygonDef();
        this.RFootPoly = new PolygonDef();
        this.LCalfPoly = new PolygonDef();
        this.RCalfPoly = new PolygonDef();
        this.LThighPoly = new PolygonDef();
        this.RThighPoly = new PolygonDef();
        this.PelvisPoly = new PolygonDef();
        this.StomachPoly = new PolygonDef();
        this.ChestPoly = new PolygonDef();
        this.NeckPoly = new PolygonDef();
        this.LUpperArmPoly = new PolygonDef();
        this.RUpperArmPoly = new PolygonDef();
        this.LForearmPoly = new PolygonDef();
        this.RForearmPoly = new PolygonDef();
        this.LHandPoly = new PolygonDef();
        this.RHandPoly = new PolygonDef();
        this.HeadCirc = new CircleDef();
        this.LAnkleDef = new RevoluteJointDef();
        this.RAnkleDef = new RevoluteJointDef();
        this.LKneeDef = new RevoluteJointDef();
        this.RKneeDef = new RevoluteJointDef();
        this.LHipDef = new RevoluteJointDef();
        this.RHipDef = new RevoluteJointDef();
        this.LowerAbsDef = new RevoluteJointDef();
        this.UpperAbsDef = new RevoluteJointDef();
        this.LowerNeckDef = new RevoluteJointDef();
        this.UpperNeckDef = new RevoluteJointDef();
        this.LShoulderDef = new RevoluteJointDef();
        this.RShoulderDef = new RevoluteJointDef();
        this.LElbowDef = new RevoluteJointDef();
        this.RElbowDef = new RevoluteJointDef();
        this.LWristDef = new RevoluteJointDef();
        this.RWristDef = new RevoluteJointDef();
        this.setMotorTorque(2.0f);
        this.setMotorSpeed(0.0f);
        this.setDensity(20.0f);
        this.setRestitution(0.0f);
        this.setLinearDamping(0.0f);
        this.setAngularDamping(0.005f);
        this.setGroupIndex(--BipedDef.count);
        this.enableMotor();
        this.enableLimit();
        this.defaultVertices();
        this.defaultPositions();
        this.defaultJoints();
        final PolygonDef lFootPoly = this.LFootPoly;
        final PolygonDef rFootPoly = this.RFootPoly;
        final float n = 0.85f;
        rFootPoly.friction = n;
        lFootPoly.friction = n;
    }
    
    public void isFast(final boolean b) {
    }
    
    public void setGroupIndex(final int i) {
        this.LFootPoly.groupIndex = i;
        this.RFootPoly.groupIndex = i;
        this.LCalfPoly.groupIndex = i;
        this.RCalfPoly.groupIndex = i;
        this.LThighPoly.groupIndex = i;
        this.RThighPoly.groupIndex = i;
        this.PelvisPoly.groupIndex = i;
        this.StomachPoly.groupIndex = i;
        this.ChestPoly.groupIndex = i;
        this.NeckPoly.groupIndex = i;
        this.HeadCirc.groupIndex = i;
        this.LUpperArmPoly.groupIndex = i;
        this.RUpperArmPoly.groupIndex = i;
        this.LForearmPoly.groupIndex = i;
        this.RForearmPoly.groupIndex = i;
        this.LHandPoly.groupIndex = i;
        this.RHandPoly.groupIndex = i;
    }
    
    public void setLinearDamping(final float f) {
        this.LFootDef.linearDamping = f;
        this.RFootDef.linearDamping = f;
        this.LCalfDef.linearDamping = f;
        this.RCalfDef.linearDamping = f;
        this.LThighDef.linearDamping = f;
        this.RThighDef.linearDamping = f;
        this.PelvisDef.linearDamping = f;
        this.StomachDef.linearDamping = f;
        this.ChestDef.linearDamping = f;
        this.NeckDef.linearDamping = f;
        this.HeadDef.linearDamping = f;
        this.LUpperArmDef.linearDamping = f;
        this.RUpperArmDef.linearDamping = f;
        this.LForearmDef.linearDamping = f;
        this.RForearmDef.linearDamping = f;
        this.LHandDef.linearDamping = f;
        this.RHandDef.linearDamping = f;
    }
    
    public void setAngularDamping(final float f) {
        this.LFootDef.angularDamping = f;
        this.RFootDef.angularDamping = f;
        this.LCalfDef.angularDamping = f;
        this.RCalfDef.angularDamping = f;
        this.LThighDef.angularDamping = f;
        this.RThighDef.angularDamping = f;
        this.PelvisDef.angularDamping = f;
        this.StomachDef.angularDamping = f;
        this.ChestDef.angularDamping = f;
        this.NeckDef.angularDamping = f;
        this.HeadDef.angularDamping = f;
        this.LUpperArmDef.angularDamping = f;
        this.RUpperArmDef.angularDamping = f;
        this.LForearmDef.angularDamping = f;
        this.RForearmDef.angularDamping = f;
        this.LHandDef.angularDamping = f;
        this.RHandDef.angularDamping = f;
    }
    
    public void setMotorTorque(final float f) {
        this.LAnkleDef.maxMotorTorque = f;
        this.RAnkleDef.maxMotorTorque = f;
        this.LKneeDef.maxMotorTorque = f;
        this.RKneeDef.maxMotorTorque = f;
        this.LHipDef.maxMotorTorque = f;
        this.RHipDef.maxMotorTorque = f;
        this.LowerAbsDef.maxMotorTorque = f;
        this.UpperAbsDef.maxMotorTorque = f;
        this.LowerNeckDef.maxMotorTorque = f;
        this.UpperNeckDef.maxMotorTorque = f;
        this.LShoulderDef.maxMotorTorque = f;
        this.RShoulderDef.maxMotorTorque = f;
        this.LElbowDef.maxMotorTorque = f;
        this.RElbowDef.maxMotorTorque = f;
        this.LWristDef.maxMotorTorque = f;
        this.RWristDef.maxMotorTorque = f;
    }
    
    public void setMotorSpeed(final float f) {
        this.LAnkleDef.motorSpeed = f;
        this.RAnkleDef.motorSpeed = f;
        this.LKneeDef.motorSpeed = f;
        this.RKneeDef.motorSpeed = f;
        this.LHipDef.motorSpeed = f;
        this.RHipDef.motorSpeed = f;
        this.LowerAbsDef.motorSpeed = f;
        this.UpperAbsDef.motorSpeed = f;
        this.LowerNeckDef.motorSpeed = f;
        this.UpperNeckDef.motorSpeed = f;
        this.LShoulderDef.motorSpeed = f;
        this.RShoulderDef.motorSpeed = f;
        this.LElbowDef.motorSpeed = f;
        this.RElbowDef.motorSpeed = f;
        this.LWristDef.motorSpeed = f;
        this.RWristDef.motorSpeed = f;
    }
    
    public void setDensity(final float f) {
        this.LFootPoly.density = f;
        this.RFootPoly.density = f;
        this.LCalfPoly.density = f;
        this.RCalfPoly.density = f;
        this.LThighPoly.density = f;
        this.RThighPoly.density = f;
        this.PelvisPoly.density = f;
        this.StomachPoly.density = f;
        this.ChestPoly.density = f;
        this.NeckPoly.density = f;
        this.HeadCirc.density = f;
        this.LUpperArmPoly.density = f;
        this.RUpperArmPoly.density = f;
        this.LForearmPoly.density = f;
        this.RForearmPoly.density = f;
        this.LHandPoly.density = f;
        this.RHandPoly.density = f;
    }
    
    public void setRestitution(final float f) {
        this.LFootPoly.restitution = f;
        this.RFootPoly.restitution = f;
        this.LCalfPoly.restitution = f;
        this.RCalfPoly.restitution = f;
        this.LThighPoly.restitution = f;
        this.RThighPoly.restitution = f;
        this.PelvisPoly.restitution = f;
        this.StomachPoly.restitution = f;
        this.ChestPoly.restitution = f;
        this.NeckPoly.restitution = f;
        this.HeadCirc.restitution = f;
        this.LUpperArmPoly.restitution = f;
        this.RUpperArmPoly.restitution = f;
        this.LForearmPoly.restitution = f;
        this.RForearmPoly.restitution = f;
        this.LHandPoly.restitution = f;
        this.RHandPoly.restitution = f;
    }
    
    public void enableLimit() {
        this.setLimit(true);
    }
    
    public void disableLimit() {
        this.setLimit(false);
    }
    
    public void setLimit(final boolean b) {
        this.LAnkleDef.enableLimit = b;
        this.RAnkleDef.enableLimit = b;
        this.LKneeDef.enableLimit = b;
        this.RKneeDef.enableLimit = b;
        this.LHipDef.enableLimit = b;
        this.RHipDef.enableLimit = b;
        this.LowerAbsDef.enableLimit = b;
        this.UpperAbsDef.enableLimit = b;
        this.LowerNeckDef.enableLimit = b;
        this.UpperNeckDef.enableLimit = b;
        this.LShoulderDef.enableLimit = b;
        this.RShoulderDef.enableLimit = b;
        this.LElbowDef.enableLimit = b;
        this.RElbowDef.enableLimit = b;
        this.LWristDef.enableLimit = b;
        this.RWristDef.enableLimit = b;
    }
    
    public void enableMotor() {
        this.setMotor(true);
    }
    
    public void disableMotor() {
        this.setMotor(false);
    }
    
    public void setMotor(final boolean b) {
        this.LAnkleDef.enableMotor = b;
        this.RAnkleDef.enableMotor = b;
        this.LKneeDef.enableMotor = b;
        this.RKneeDef.enableMotor = b;
        this.LHipDef.enableMotor = b;
        this.RHipDef.enableMotor = b;
        this.LowerAbsDef.enableMotor = b;
        this.UpperAbsDef.enableMotor = b;
        this.LowerNeckDef.enableMotor = b;
        this.UpperNeckDef.enableMotor = b;
        this.LShoulderDef.enableMotor = b;
        this.RShoulderDef.enableMotor = b;
        this.LElbowDef.enableMotor = b;
        this.RElbowDef.enableMotor = b;
        this.LWristDef.enableMotor = b;
        this.RWristDef.enableMotor = b;
    }
    
    public void defaultVertices() {
        this.LFootPoly.vertices.add(new Vec2(0.033f * BipedDef.k_scale, 0.143f * BipedDef.k_scale));
        this.LFootPoly.vertices.add(new Vec2(0.023f * BipedDef.k_scale, 0.033f * BipedDef.k_scale));
        this.LFootPoly.vertices.add(new Vec2(0.267f * BipedDef.k_scale, 0.035f * BipedDef.k_scale));
        this.LFootPoly.vertices.add(new Vec2(0.265f * BipedDef.k_scale, 0.065f * BipedDef.k_scale));
        this.LFootPoly.vertices.add(new Vec2(0.117f * BipedDef.k_scale, 0.143f * BipedDef.k_scale));
        this.RFootPoly.vertices.add(new Vec2(0.033f * BipedDef.k_scale, 0.143f * BipedDef.k_scale));
        this.RFootPoly.vertices.add(new Vec2(0.023f * BipedDef.k_scale, 0.033f * BipedDef.k_scale));
        this.RFootPoly.vertices.add(new Vec2(0.267f * BipedDef.k_scale, 0.035f * BipedDef.k_scale));
        this.RFootPoly.vertices.add(new Vec2(0.265f * BipedDef.k_scale, 0.065f * BipedDef.k_scale));
        this.RFootPoly.vertices.add(new Vec2(0.117f * BipedDef.k_scale, 0.143f * BipedDef.k_scale));
        this.LCalfPoly.vertices.add(new Vec2(0.089f * BipedDef.k_scale, 0.016f * BipedDef.k_scale));
        this.RCalfPoly.vertices.add(new Vec2(0.089f * BipedDef.k_scale, 0.016f * BipedDef.k_scale));
        this.LCalfPoly.vertices.add(new Vec2(0.178f * BipedDef.k_scale, 0.016f * BipedDef.k_scale));
        this.RCalfPoly.vertices.add(new Vec2(0.178f * BipedDef.k_scale, 0.016f * BipedDef.k_scale));
        this.LCalfPoly.vertices.add(new Vec2(0.205f * BipedDef.k_scale, 0.417f * BipedDef.k_scale));
        this.RCalfPoly.vertices.add(new Vec2(0.205f * BipedDef.k_scale, 0.417f * BipedDef.k_scale));
        this.LCalfPoly.vertices.add(new Vec2(0.095f * BipedDef.k_scale, 0.417f * BipedDef.k_scale));
        this.RCalfPoly.vertices.add(new Vec2(0.095f * BipedDef.k_scale, 0.417f * BipedDef.k_scale));
        this.LThighPoly.vertices.add(new Vec2(0.137f * BipedDef.k_scale, 0.032f * BipedDef.k_scale));
        this.RThighPoly.vertices.add(new Vec2(0.137f * BipedDef.k_scale, 0.032f * BipedDef.k_scale));
        this.LThighPoly.vertices.add(new Vec2(0.243f * BipedDef.k_scale, 0.032f * BipedDef.k_scale));
        this.RThighPoly.vertices.add(new Vec2(0.243f * BipedDef.k_scale, 0.032f * BipedDef.k_scale));
        this.LThighPoly.vertices.add(new Vec2(0.318f * BipedDef.k_scale, 0.343f * BipedDef.k_scale));
        this.RThighPoly.vertices.add(new Vec2(0.318f * BipedDef.k_scale, 0.343f * BipedDef.k_scale));
        this.LThighPoly.vertices.add(new Vec2(0.142f * BipedDef.k_scale, 0.343f * BipedDef.k_scale));
        this.RThighPoly.vertices.add(new Vec2(0.142f * BipedDef.k_scale, 0.343f * BipedDef.k_scale));
        this.PelvisPoly.vertices.add(new Vec2(0.105f * BipedDef.k_scale, 0.051f * BipedDef.k_scale));
        this.PelvisPoly.vertices.add(new Vec2(0.277f * BipedDef.k_scale, 0.053f * BipedDef.k_scale));
        this.PelvisPoly.vertices.add(new Vec2(0.32f * BipedDef.k_scale, 0.233f * BipedDef.k_scale));
        this.PelvisPoly.vertices.add(new Vec2(0.112f * BipedDef.k_scale, 0.233f * BipedDef.k_scale));
        this.PelvisPoly.vertices.add(new Vec2(0.067f * BipedDef.k_scale, 0.152f * BipedDef.k_scale));
        this.StomachPoly.vertices.add(new Vec2(0.088f * BipedDef.k_scale, 0.043f * BipedDef.k_scale));
        this.StomachPoly.vertices.add(new Vec2(0.284f * BipedDef.k_scale, 0.043f * BipedDef.k_scale));
        this.StomachPoly.vertices.add(new Vec2(0.295f * BipedDef.k_scale, 0.231f * BipedDef.k_scale));
        this.StomachPoly.vertices.add(new Vec2(0.1f * BipedDef.k_scale, 0.231f * BipedDef.k_scale));
        this.ChestPoly.vertices.add(new Vec2(0.091f * BipedDef.k_scale, 0.042f * BipedDef.k_scale));
        this.ChestPoly.vertices.add(new Vec2(0.283f * BipedDef.k_scale, 0.042f * BipedDef.k_scale));
        this.ChestPoly.vertices.add(new Vec2(0.177f * BipedDef.k_scale, 0.289f * BipedDef.k_scale));
        this.ChestPoly.vertices.add(new Vec2(0.065f * BipedDef.k_scale, 0.289f * BipedDef.k_scale));
        this.HeadCirc.radius = BipedDef.k_scale * 0.115f;
        this.NeckPoly.vertices.add(new Vec2(0.038f * BipedDef.k_scale, 0.054f * BipedDef.k_scale));
        this.NeckPoly.vertices.add(new Vec2(0.149f * BipedDef.k_scale, 0.054f * BipedDef.k_scale));
        this.NeckPoly.vertices.add(new Vec2(0.154f * BipedDef.k_scale, 0.102f * BipedDef.k_scale));
        this.NeckPoly.vertices.add(new Vec2(0.054f * BipedDef.k_scale, 0.113f * BipedDef.k_scale));
        this.LUpperArmPoly.vertices.add(new Vec2(0.092f * BipedDef.k_scale, 0.059f * BipedDef.k_scale));
        this.LUpperArmPoly.vertices.add(new Vec2(0.159f * BipedDef.k_scale, 0.059f * BipedDef.k_scale));
        this.LUpperArmPoly.vertices.add(new Vec2(0.169f * BipedDef.k_scale, 0.335f * BipedDef.k_scale));
        this.LUpperArmPoly.vertices.add(new Vec2(0.078f * BipedDef.k_scale, 0.335f * BipedDef.k_scale));
        this.LUpperArmPoly.vertices.add(new Vec2(0.064f * BipedDef.k_scale, 0.248f * BipedDef.k_scale));
        this.RUpperArmPoly.vertices.add(new Vec2(0.092f * BipedDef.k_scale, 0.059f * BipedDef.k_scale));
        this.RUpperArmPoly.vertices.add(new Vec2(0.159f * BipedDef.k_scale, 0.059f * BipedDef.k_scale));
        this.RUpperArmPoly.vertices.add(new Vec2(0.169f * BipedDef.k_scale, 0.335f * BipedDef.k_scale));
        this.RUpperArmPoly.vertices.add(new Vec2(0.078f * BipedDef.k_scale, 0.335f * BipedDef.k_scale));
        this.RUpperArmPoly.vertices.add(new Vec2(0.064f * BipedDef.k_scale, 0.248f * BipedDef.k_scale));
        this.LForearmPoly.vertices.add(new Vec2(0.082f * BipedDef.k_scale, 0.054f * BipedDef.k_scale));
        this.LForearmPoly.vertices.add(new Vec2(0.138f * BipedDef.k_scale, 0.054f * BipedDef.k_scale));
        this.LForearmPoly.vertices.add(new Vec2(0.149f * BipedDef.k_scale, 0.296f * BipedDef.k_scale));
        this.LForearmPoly.vertices.add(new Vec2(0.088f * BipedDef.k_scale, 0.296f * BipedDef.k_scale));
        this.RForearmPoly.vertices.add(new Vec2(0.082f * BipedDef.k_scale, 0.054f * BipedDef.k_scale));
        this.RForearmPoly.vertices.add(new Vec2(0.138f * BipedDef.k_scale, 0.054f * BipedDef.k_scale));
        this.RForearmPoly.vertices.add(new Vec2(0.149f * BipedDef.k_scale, 0.296f * BipedDef.k_scale));
        this.RForearmPoly.vertices.add(new Vec2(0.088f * BipedDef.k_scale, 0.296f * BipedDef.k_scale));
        this.LHandPoly.vertices.add(new Vec2(0.066f * BipedDef.k_scale, 0.031f * BipedDef.k_scale));
        this.LHandPoly.vertices.add(new Vec2(0.123f * BipedDef.k_scale, 0.02f * BipedDef.k_scale));
        this.LHandPoly.vertices.add(new Vec2(0.16f * BipedDef.k_scale, 0.127f * BipedDef.k_scale));
        this.LHandPoly.vertices.add(new Vec2(0.127f * BipedDef.k_scale, 0.178f * BipedDef.k_scale));
        this.LHandPoly.vertices.add(new Vec2(0.074f * BipedDef.k_scale, 0.178f * BipedDef.k_scale));
        this.RHandPoly.vertices.add(new Vec2(0.066f * BipedDef.k_scale, 0.031f * BipedDef.k_scale));
        this.RHandPoly.vertices.add(new Vec2(0.123f * BipedDef.k_scale, 0.02f * BipedDef.k_scale));
        this.RHandPoly.vertices.add(new Vec2(0.16f * BipedDef.k_scale, 0.127f * BipedDef.k_scale));
        this.RHandPoly.vertices.add(new Vec2(0.127f * BipedDef.k_scale, 0.178f * BipedDef.k_scale));
        this.RHandPoly.vertices.add(new Vec2(0.074f * BipedDef.k_scale, 0.178f * BipedDef.k_scale));
    }
    
    public void defaultJoints() {
        Vec2 anchor = new Vec2(-0.045f, -0.75f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LAnkleDef.localAnchor1 = anchor.sub(this.LFootDef.position);
        this.RAnkleDef.localAnchor1 = anchor.sub(this.LFootDef.position);
        this.LAnkleDef.localAnchor2 = anchor.sub(this.LCalfDef.position);
        this.RAnkleDef.localAnchor2 = anchor.sub(this.LCalfDef.position);
        final RevoluteJointDef lAnkleDef = this.LAnkleDef;
        final RevoluteJointDef rAnkleDef = this.RAnkleDef;
        final float n = 0.0f;
        rAnkleDef.referenceAngle = n;
        lAnkleDef.referenceAngle = n;
        final RevoluteJointDef lAnkleDef2 = this.LAnkleDef;
        final RevoluteJointDef rAnkleDef2 = this.RAnkleDef;
        final float n2 = -0.5235988f;
        rAnkleDef2.lowerAngle = n2;
        lAnkleDef2.lowerAngle = n2;
        final RevoluteJointDef lAnkleDef3 = this.LAnkleDef;
        final RevoluteJointDef rAnkleDef3 = this.RAnkleDef;
        final float n3 = 0.5235988f;
        rAnkleDef3.upperAngle = n3;
        lAnkleDef3.upperAngle = n3;
        anchor = new Vec2(-0.03f, -0.355f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LKneeDef.localAnchor1 = anchor.sub(this.LCalfDef.position);
        this.RKneeDef.localAnchor1 = anchor.sub(this.LCalfDef.position);
        this.LKneeDef.localAnchor2 = anchor.sub(this.LThighDef.position);
        this.RKneeDef.localAnchor2 = anchor.sub(this.LThighDef.position);
        final RevoluteJointDef lKneeDef = this.LKneeDef;
        final RevoluteJointDef rKneeDef = this.RKneeDef;
        final float n4 = 0.0f;
        rKneeDef.referenceAngle = n4;
        lKneeDef.referenceAngle = n4;
        final RevoluteJointDef lKneeDef2 = this.LKneeDef;
        final RevoluteJointDef rKneeDef2 = this.RKneeDef;
        final float n5 = 0.0f;
        rKneeDef2.lowerAngle = n5;
        lKneeDef2.lowerAngle = n5;
        final RevoluteJointDef lKneeDef3 = this.LKneeDef;
        final RevoluteJointDef rKneeDef3 = this.RKneeDef;
        final float n6 = 2.6179938f;
        rKneeDef3.upperAngle = n6;
        lKneeDef3.upperAngle = n6;
        anchor = new Vec2(0.005f, -0.045f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LHipDef.localAnchor1 = anchor.sub(this.LThighDef.position);
        this.RHipDef.localAnchor1 = anchor.sub(this.LThighDef.position);
        this.LHipDef.localAnchor2 = anchor.sub(this.PelvisDef.position);
        this.RHipDef.localAnchor2 = anchor.sub(this.PelvisDef.position);
        final RevoluteJointDef lHipDef = this.LHipDef;
        final RevoluteJointDef rHipDef = this.RHipDef;
        final float n7 = 0.0f;
        rHipDef.referenceAngle = n7;
        lHipDef.referenceAngle = n7;
        final RevoluteJointDef lHipDef2 = this.LHipDef;
        final RevoluteJointDef rHipDef2 = this.RHipDef;
        final float n8 = -2.268928f;
        rHipDef2.lowerAngle = n8;
        lHipDef2.lowerAngle = n8;
        final RevoluteJointDef lHipDef3 = this.LHipDef;
        final RevoluteJointDef rHipDef3 = this.RHipDef;
        final float n9 = 0.0f;
        rHipDef3.upperAngle = n9;
        lHipDef3.upperAngle = n9;
        anchor = new Vec2(0.035f, 0.135f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LowerAbsDef.localAnchor1 = anchor.sub(this.PelvisDef.position);
        this.LowerAbsDef.localAnchor2 = anchor.sub(this.StomachDef.position);
        this.LowerAbsDef.referenceAngle = 0.0f;
        this.LowerAbsDef.lowerAngle = -0.5235988f;
        this.LowerAbsDef.upperAngle = 0.5235988f;
        anchor = new Vec2(0.045f, 0.32f);
        anchor.mulLocal(BipedDef.k_scale);
        this.UpperAbsDef.localAnchor1 = anchor.sub(this.StomachDef.position);
        this.UpperAbsDef.localAnchor2 = anchor.sub(this.ChestDef.position);
        this.UpperAbsDef.referenceAngle = 0.0f;
        this.UpperAbsDef.lowerAngle = -0.5235988f;
        this.UpperAbsDef.upperAngle = 0.17453292f;
        anchor = new Vec2(-0.015f, 0.575f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LowerNeckDef.localAnchor1 = anchor.sub(this.ChestDef.position);
        this.LowerNeckDef.localAnchor2 = anchor.sub(this.NeckDef.position);
        this.LowerNeckDef.referenceAngle = 0.0f;
        this.LowerNeckDef.lowerAngle = -0.17453292f;
        this.LowerNeckDef.upperAngle = 0.17453292f;
        anchor = new Vec2(-0.005f, 0.63f);
        anchor.mulLocal(BipedDef.k_scale);
        this.UpperNeckDef.localAnchor1 = anchor.sub(this.ChestDef.position);
        this.UpperNeckDef.localAnchor2 = anchor.sub(this.HeadDef.position);
        this.UpperNeckDef.referenceAngle = 0.0f;
        this.UpperNeckDef.lowerAngle = -0.61086524f;
        this.UpperNeckDef.upperAngle = 0.7853982f;
        anchor = new Vec2(-0.015f, 0.545f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LShoulderDef.localAnchor1 = anchor.sub(this.ChestDef.position);
        this.RShoulderDef.localAnchor1 = anchor.sub(this.ChestDef.position);
        this.LShoulderDef.localAnchor2 = anchor.sub(this.LUpperArmDef.position);
        this.RShoulderDef.localAnchor2 = anchor.sub(this.LUpperArmDef.position);
        final RevoluteJointDef lShoulderDef = this.LShoulderDef;
        final RevoluteJointDef rShoulderDef = this.RShoulderDef;
        final float n10 = 0.0f;
        rShoulderDef.referenceAngle = n10;
        lShoulderDef.referenceAngle = n10;
        final RevoluteJointDef lShoulderDef2 = this.LShoulderDef;
        final RevoluteJointDef rShoulderDef2 = this.RShoulderDef;
        final float n11 = -1.0471976f;
        rShoulderDef2.lowerAngle = n11;
        lShoulderDef2.lowerAngle = n11;
        final RevoluteJointDef lShoulderDef3 = this.LShoulderDef;
        final RevoluteJointDef rShoulderDef3 = this.RShoulderDef;
        final float n12 = 3.1415927f;
        rShoulderDef3.upperAngle = n12;
        lShoulderDef3.upperAngle = n12;
        anchor = new Vec2(-0.005f, 0.29f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LElbowDef.localAnchor1 = anchor.sub(this.LForearmDef.position);
        this.RElbowDef.localAnchor1 = anchor.sub(this.LForearmDef.position);
        this.LElbowDef.localAnchor2 = anchor.sub(this.LUpperArmDef.position);
        this.RElbowDef.localAnchor2 = anchor.sub(this.LUpperArmDef.position);
        final RevoluteJointDef lElbowDef = this.LElbowDef;
        final RevoluteJointDef rElbowDef = this.RElbowDef;
        final float n13 = 0.0f;
        rElbowDef.referenceAngle = n13;
        lElbowDef.referenceAngle = n13;
        final RevoluteJointDef lElbowDef2 = this.LElbowDef;
        final RevoluteJointDef rElbowDef2 = this.RElbowDef;
        final float n14 = -2.7925267f;
        rElbowDef2.lowerAngle = n14;
        lElbowDef2.lowerAngle = n14;
        final RevoluteJointDef lElbowDef3 = this.LElbowDef;
        final RevoluteJointDef rElbowDef3 = this.RElbowDef;
        final float n15 = 0.0f;
        rElbowDef3.upperAngle = n15;
        lElbowDef3.upperAngle = n15;
        anchor = new Vec2(-0.01f, 0.045f);
        anchor.mulLocal(BipedDef.k_scale);
        this.LWristDef.localAnchor1 = anchor.sub(this.LHandDef.position);
        this.RWristDef.localAnchor1 = anchor.sub(this.LHandDef.position);
        this.LWristDef.localAnchor2 = anchor.sub(this.LForearmDef.position);
        this.RWristDef.localAnchor2 = anchor.sub(this.LForearmDef.position);
        final RevoluteJointDef lWristDef = this.LWristDef;
        final RevoluteJointDef rWristDef = this.RWristDef;
        final float n16 = 0.0f;
        rWristDef.referenceAngle = n16;
        lWristDef.referenceAngle = n16;
        final RevoluteJointDef lWristDef2 = this.LWristDef;
        final RevoluteJointDef rWristDef2 = this.RWristDef;
        final float n17 = -0.17453292f;
        rWristDef2.lowerAngle = n17;
        lWristDef2.lowerAngle = n17;
        final RevoluteJointDef lWristDef3 = this.LWristDef;
        final RevoluteJointDef rWristDef3 = this.RWristDef;
        final float n18 = 0.17453292f;
        rWristDef3.upperAngle = n18;
        lWristDef3.upperAngle = n18;
    }
    
    public void defaultPositions() {
        this.LFootDef.position = new Vec2(-0.122f, -0.901f).mulLocal(BipedDef.k_scale);
        this.RFootDef.position = new Vec2(-0.122f, -0.901f).mulLocal(BipedDef.k_scale);
        this.LCalfDef.position = new Vec2(-0.177f, -0.771f).mulLocal(BipedDef.k_scale);
        this.RCalfDef.position = new Vec2(-0.177f, -0.771f).mulLocal(BipedDef.k_scale);
        this.LThighDef.position = new Vec2(-0.217f, -0.391f).mulLocal(BipedDef.k_scale);
        this.RThighDef.position = new Vec2(-0.217f, -0.391f).mulLocal(BipedDef.k_scale);
        this.LUpperArmDef.position = new Vec2(-0.127f, 0.228f).mulLocal(BipedDef.k_scale);
        this.RUpperArmDef.position = new Vec2(-0.127f, 0.228f).mulLocal(BipedDef.k_scale);
        this.LForearmDef.position = new Vec2(-0.117f, -0.011f).mulLocal(BipedDef.k_scale);
        this.RForearmDef.position = new Vec2(-0.117f, -0.011f).mulLocal(BipedDef.k_scale);
        this.LHandDef.position = new Vec2(-0.112f, -0.136f).mulLocal(BipedDef.k_scale);
        this.RHandDef.position = new Vec2(-0.112f, -0.136f).mulLocal(BipedDef.k_scale);
        this.PelvisDef.position = new Vec2(-0.177f, -0.101f).mulLocal(BipedDef.k_scale);
        this.StomachDef.position = new Vec2(-0.142f, 0.088f).mulLocal(BipedDef.k_scale);
        this.ChestDef.position = new Vec2(-0.132f, 0.282f).mulLocal(BipedDef.k_scale);
        this.NeckDef.position = new Vec2(-0.102f, 0.518f).mulLocal(BipedDef.k_scale);
        this.HeadDef.position = new Vec2(0.022f, 0.738f).mulLocal(BipedDef.k_scale);
    }
}
