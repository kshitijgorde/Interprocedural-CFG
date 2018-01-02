// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.XForm;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.AABB;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class CompoundShapes extends AbstractExample
{
    private boolean firstTime;
    private final int stackHeight = 100;
    
    public CompoundShapes(final TestbedMain _parent) {
        super(_parent);
        this.firstTime = true;
    }
    
    public void createWorld() {
        this.m_worldAABB = new AABB();
        this.m_worldAABB.lowerBound = new Vec2(-200.0f, -100.0f);
        this.m_worldAABB.upperBound = new Vec2(200.0f, 500.0f);
        final Vec2 gravity = new Vec2(0.0f, -10.0f);
        final boolean doSleep = true;
        this.m_world = new World(this.m_worldAABB, gravity, doSleep);
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(0.0f, 10.0f, 15.0f);
            this.firstTime = false;
        }
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -10.0f);
        final Body body = this.m_world.createStaticBody(bd);
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        body.createShape(sd);
        final CircleDef sd2 = new CircleDef();
        sd2.radius = 0.5f;
        sd2.localPosition.set(-0.5f, 0.5f);
        sd2.density = 2.0f;
        final CircleDef sd3 = new CircleDef();
        sd3.radius = 0.5f;
        sd3.localPosition.set(0.5f, 0.5f);
        sd3.density = 0.0f;
        for (int i = 0; i < 100; ++i) {
            final float x = this.parent.random(-0.1f, 0.1f);
            final BodyDef bd2 = new BodyDef();
            bd2.position.set(x + 5.0f, 1.05f + 2.5f * i);
            bd2.angle = this.parent.random(-3.1415f, 3.1415f);
            final Body body2 = this.m_world.createDynamicBody(bd2);
            body2.createShape(sd2);
            body2.createShape(sd3);
            body2.setMassFromShapes();
        }
        final PolygonDef sd4 = new PolygonDef();
        sd4.setAsBox(0.25f, 0.5f);
        sd4.density = 2.0f;
        final PolygonDef sd5 = new PolygonDef();
        sd5.setAsBox(0.25f, 0.5f, new Vec2(0.0f, -0.5f), 1.57075f);
        sd5.density = 2.0f;
        for (int i = 0; i < 100; ++i) {
            final float x = this.parent.random(-0.1f, 0.1f);
            final BodyDef bd2 = new BodyDef();
            bd2.position.set(x - 5.0f, 1.05f + 2.5f * i);
            bd2.angle = this.parent.random(-3.1415f, 3.1415f);
            final Body body2 = this.m_world.createDynamicBody(bd2);
            body2.createShape(sd4);
            body2.createShape(sd5);
            body2.setMassFromShapes();
        }
        final XForm xf1 = new XForm();
        xf1.R.set(1.1070646f);
        xf1.position = Mat22.mul(xf1.R, new Vec2(1.0f, 0.0f));
        final PolygonDef sd6 = new PolygonDef();
        sd6.vertices.add(XForm.mul(xf1, new Vec2(-1.0f, 0.0f)));
        sd6.vertices.add(XForm.mul(xf1, new Vec2(1.0f, 0.0f)));
        sd6.vertices.add(XForm.mul(xf1, new Vec2(0.0f, 0.5f)));
        sd6.density = 2.0f;
        final XForm xf2 = new XForm();
        xf2.R.set(-1.1070646f);
        xf2.position = Mat22.mul(xf2.R, new Vec2(-1.0f, 0.0f));
        final PolygonDef sd7 = new PolygonDef();
        sd7.vertices.add(XForm.mul(xf2, new Vec2(-1.0f, 0.0f)));
        sd7.vertices.add(XForm.mul(xf2, new Vec2(1.0f, 0.0f)));
        sd7.vertices.add(XForm.mul(xf2, new Vec2(0.0f, 0.5f)));
        sd7.density = 2.0f;
        for (int j = 0; j < 100; ++j) {
            final float x2 = this.parent.random(-0.1f, 0.1f);
            final BodyDef bd3 = new BodyDef();
            bd3.position.set(x2, 2.05f + 2.5f * j);
            bd3.angle = 0.0f;
            final Body body3 = this.m_world.createDynamicBody(bd3);
            body3.createShape(sd6);
            body3.createShape(sd7);
            body3.setMassFromShapes();
        }
        final PolygonDef sd_bottom = new PolygonDef();
        sd_bottom.setAsBox(1.5f, 0.15f);
        sd_bottom.density = 4.0f;
        final PolygonDef sd_left = new PolygonDef();
        sd_left.setAsBox(0.15f, 2.7f, new Vec2(-1.45f, 2.35f), 0.2f);
        sd_left.density = 4.0f;
        final PolygonDef sd_right = new PolygonDef();
        sd_right.setAsBox(0.15f, 2.7f, new Vec2(1.45f, 2.35f), -0.2f);
        sd_right.density = 4.0f;
        final BodyDef bd4 = new BodyDef();
        bd4.position.set(0.0f, 2.0f);
        final Body body4 = this.m_world.createDynamicBody(bd4);
        body4.createShape(sd_bottom);
        body4.createShape(sd_left);
        body4.createShape(sd_right);
        body4.setMassFromShapes();
    }
    
    public String getName() {
        return "Compound Shapes";
    }
}
