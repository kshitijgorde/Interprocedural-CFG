// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class Pyramid extends AbstractExample
{
    private boolean firstTime;
    
    public Pyramid(final TestbedMain t) {
        super(t);
        this.firstTime = true;
    }
    
    public String getName() {
        return "Pyramid Stress Test";
    }
    
    public void create() {
        if (this.firstTime) {
            this.setCamera(2.0f, 12.0f, 10.0f);
            this.firstTime = false;
        }
        PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -10.0f);
        final Body ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        final float a = 0.5f;
        sd.setAsBox(a, a);
        sd.density = 5.0f;
        sd.restitution = 0.0f;
        sd.friction = 0.9f;
        final Vec2 x = new Vec2(-10.0f, 0.75f);
        final Vec2 y = new Vec2();
        final Vec2 deltaX = new Vec2(0.5625f, 2.0f);
        final Vec2 deltaY = new Vec2(1.125f, 0.0f);
        for (int i = 0; i < 25; ++i) {
            y.set(x);
            for (int j = i; j < 25; ++j) {
                final BodyDef bd2 = new BodyDef();
                bd2.position.set(y);
                final Body body = this.m_world.createDynamicBody(bd2);
                body.createShape(sd);
                body.setMassFromShapes();
                y.addLocal(deltaY);
            }
            x.addLocal(deltaX);
        }
    }
}
