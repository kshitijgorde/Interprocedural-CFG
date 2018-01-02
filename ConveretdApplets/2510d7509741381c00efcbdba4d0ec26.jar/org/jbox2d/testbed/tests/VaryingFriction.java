// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class VaryingFriction extends AbstractExample
{
    public VaryingFriction(final TestbedMain _parent) {
        super(_parent);
    }
    
    public String getName() {
        return "Varying Friction";
    }
    
    public void create() {
        PolygonDef sd = new PolygonDef();
        sd.setAsBox(100.0f, 20.0f);
        BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -20.0f);
        Body ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(13.0f, 0.25f);
        bd = new BodyDef();
        bd.position.set(-4.0f, 22.0f);
        bd.angle = -0.25f;
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(0.25f, 1.0f);
        bd = new BodyDef();
        bd.position.set(10.5f, 19.0f);
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(13.0f, 0.25f);
        bd = new BodyDef();
        bd.position.set(4.0f, 14.0f);
        bd.angle = 0.25f;
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(0.25f, 1.0f);
        bd = new BodyDef();
        bd.position.set(-10.5f, 11.0f);
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(13.0f, 0.25f);
        bd = new BodyDef();
        bd.position.set(-4.0f, 6.0f);
        bd.angle = -0.25f;
        ground = this.m_world.createStaticBody(bd);
        ground.createShape(sd);
        sd = new PolygonDef();
        sd.setAsBox(0.5f, 0.5f);
        sd.density = 25.0f;
        final float[] friction = { 0.75f, 0.5f, 0.35f, 0.1f, 0.0f };
        for (int i = 0; i < 5; ++i) {
            final BodyDef bd2 = new BodyDef();
            bd2.position.set(-15.0f + 4.0f * i, 28.0f);
            final Body body = this.m_world.createDynamicBody(bd2);
            sd.friction = friction[i];
            body.createShape(sd);
            body.setMassFromShapes();
        }
    }
}
