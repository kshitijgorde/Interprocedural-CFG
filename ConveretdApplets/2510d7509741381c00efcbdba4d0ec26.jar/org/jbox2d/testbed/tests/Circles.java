// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class Circles extends AbstractExample
{
    private boolean firstTime;
    
    public Circles(final TestbedMain parent) {
        super(parent);
        this.firstTime = true;
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 20.0f, 5.0f);
            this.firstTime = false;
        }
        final Body ground = this.m_world.getGroundBody();
        Body leftWall = null;
        Body rightWall = null;
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        sd.friction = 1.0f;
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(0.0f, -10.0f);
        this.m_world.createStaticBody(bd).createShape(sd);
        sd.setAsBox(3.0f, 50.0f);
        bd = new BodyDef();
        bd.position = new Vec2(53.0f, 25.0f);
        rightWall = this.m_world.createStaticBody(bd);
        rightWall.createShape(sd);
        bd.position = new Vec2(-53.0f, 25.0f);
        leftWall = this.m_world.createStaticBody(bd);
        leftWall.createShape(sd);
        bd = new BodyDef();
        sd.setAsBox(20.0f, 3.0f);
        bd.angle = -0.7853982f;
        bd.position = new Vec2(-40.0f, 0.0f);
        Body myBod = this.m_world.createStaticBody(bd);
        myBod.createShape(sd);
        bd.angle = 0.7853982f;
        bd.position = new Vec2(40.0f, 0.0f);
        myBod = this.m_world.createStaticBody(bd);
        myBod.createShape(sd);
        CircleDef cd = new CircleDef();
        bd = new BodyDef();
        final int numPieces = 5;
        final float radius = 6.0f;
        bd.position = new Vec2(0.0f, 10.0f);
        final Body body = this.m_world.createDynamicBody(bd);
        for (int i = 0; i < numPieces; ++i) {
            cd = new CircleDef();
            cd.radius = 1.2f;
            cd.density = 25.0f;
            cd.friction = 0.1f;
            cd.restitution = 0.9f;
            final float xPos = radius * (float)Math.cos(6.283185307179586 * (i / numPieces));
            final float yPos = radius * (float)Math.sin(6.283185307179586 * (i / numPieces));
            cd.localPosition = new Vec2(xPos, yPos);
            body.createShape(cd);
        }
        body.setMassFromShapes();
        final RevoluteJointDef rjd = new RevoluteJointDef();
        rjd.initialize(body, ground, body.getPosition());
        rjd.motorSpeed = 3.1415927f;
        rjd.maxMotorTorque = 1000000.0f;
        rjd.enableMotor = true;
        this.m_world.createJoint(rjd);
        final int loadSize = 45;
        for (int j = 0; j < 10; ++j) {
            for (int k = 0; k < loadSize; ++k) {
                final CircleDef circ = new CircleDef();
                final BodyDef bod = new BodyDef();
                circ.radius = 1.0f + ((k % 2 == 0) ? 1.0f : -1.0f) * 0.5f * (k / loadSize);
                circ.density = 5.0f;
                circ.friction = 0.1f;
                circ.restitution = 0.5f;
                final float xPos2 = -45.0f + 2 * k;
                final float yPos2 = 50.0f + j;
                bod.position = new Vec2(xPos2, yPos2);
                final Body myBody = this.m_world.createDynamicBody(bod);
                myBody.createShape(circ);
                myBody.setMassFromShapes();
            }
        }
    }
    
    public String getName() {
        return "Circle Stress Test";
    }
}
