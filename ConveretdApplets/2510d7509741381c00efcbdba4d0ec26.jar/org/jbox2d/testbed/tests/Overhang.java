// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class Overhang extends AbstractExample
{
    public Overhang(final TestbedMain p) {
        super(p);
    }
    
    public String getName() {
        return "Overhang";
    }
    
    public void create() {
        PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        final BodyDef bd = new BodyDef();
        bd.position = new Vec2(0.0f, -10.0f);
        this.m_world.createStaticBody(bd).createShape(sd);
        sd = new PolygonDef();
        final float w = 4.0f;
        final float h = 0.25f;
        sd.setAsBox(w, h);
        sd.density = 1.0f;
        sd.friction = 0.3f;
        sd.restitution = 0.0f;
        final BodyDef bd2 = new BodyDef();
        final int numSlats = 8;
        float lastCMX = 0.0f;
        final float eps = 0.14f;
        for (int i = 0; i < numSlats; ++i) {
            final float newX = lastCMX + w - eps;
            lastCMX = (i * lastCMX + newX) / (i + 1);
            bd2.position = new Vec2(newX, 0.25f + 2.0f * h * (numSlats - i - 1));
            final Body myBody = this.m_world.createDynamicBody(bd2);
            myBody.createShape(sd);
            myBody.setMassFromShapes();
        }
    }
}
