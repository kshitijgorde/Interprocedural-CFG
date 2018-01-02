// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.collision.Distance;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.collision.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.testbed.AbstractExample;

public class DistanceTest extends AbstractExample
{
    Body m_body1;
    Body m_body2;
    Shape m_shape1;
    Shape m_shape2;
    private boolean firstTime;
    
    public DistanceTest(final TestbedMain _parent) {
        super(_parent);
        this.firstTime = true;
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 10.0f, 20.0f);
            this.firstTime = false;
        }
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(1.0f, 1.0f);
        sd.density = 0.0f;
        BodyDef bd = new BodyDef();
        bd.position.set(0.0f, 10.0f);
        this.m_body1 = this.m_world.createStaticBody(bd);
        this.m_shape1 = this.m_body1.createShape(sd);
        final CircleDef sd2 = new CircleDef();
        sd2.radius = 2.0f;
        sd2.density = 1.0f;
        bd = new BodyDef();
        bd.position.set(0.0f, 10.0f);
        this.m_body2 = this.m_world.createDynamicBody(bd);
        this.m_shape2 = this.m_body2.createShape(sd2);
        this.m_body2.setMassFromShapes();
        this.m_world.m_gravity.set(0.0f, 0.0f);
    }
    
    public void step() {
        this.settings.pause = true;
        this.settings.enablePositionCorrection = false;
        super.step();
        this.settings.enablePositionCorrection = true;
        this.settings.pause = false;
        final Vec2 x1 = new Vec2();
        final Vec2 x2 = new Vec2();
        final float distance = Distance.distance(x1, x2, this.m_shape1, this.m_body1.getXForm(), this.m_shape2, this.m_body2.getXForm());
        this.m_debugDraw.drawString(5.0f, this.m_textLine, "distance = " + distance, DistanceTest.white);
        this.m_textLine += 15;
        this.m_debugDraw.drawString(5.0f, this.m_textLine, "iterations = " + Distance.g_GJK_Iterations, DistanceTest.white);
        this.m_textLine += 15;
        this.m_debugDraw.drawPoint(x1, 2.0f, DistanceTest.white);
        this.m_debugDraw.drawPoint(x2, 2.0f, DistanceTest.white);
        this.m_debugDraw.drawSegment(x1, x2, DistanceTest.white);
    }
    
    public void keyPressed(final int key) {
        final Vec2 p = this.m_body2.getPosition();
        float a = this.m_body2.getAngle();
        switch (key) {
            case 97: {
                final Vec2 vec2 = p;
                vec2.x -= 0.1f;
                break;
            }
            case 100: {
                final Vec2 vec3 = p;
                vec3.x += 0.1f;
                break;
            }
            case 115: {
                final Vec2 vec4 = p;
                vec4.y -= 0.1f;
                break;
            }
            case 119: {
                final Vec2 vec5 = p;
                vec5.y += 0.1f;
                break;
            }
            case 113: {
                a += 0.31415927f;
                break;
            }
            case 101: {
                a -= 0.31415927f;
                break;
            }
        }
        this.m_body2.setXForm(p, a);
    }
    
    public String getName() {
        return "Distance Test";
    }
}
