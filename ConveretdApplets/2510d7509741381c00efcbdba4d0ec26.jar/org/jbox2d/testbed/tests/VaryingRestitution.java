// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.testbed.AbstractExample;

public class VaryingRestitution extends AbstractExample
{
    public VaryingRestitution(final TestbedMain _parent) {
        super(_parent);
    }
    
    public void create() {
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(0.0f, -10.0f);
        this.m_world.createStaticBody(bd).createShape(sd);
        final CircleDef sd2 = new CircleDef();
        sd2.radius = 0.6f;
        sd2.density = 5.0f;
        bd = new BodyDef();
        final float[] restitution = { 0.0f, 0.1f, 0.3f, 0.5f, 0.75f, 0.9f, 1.0f };
        for (int i = 0; i < restitution.length; ++i) {
            sd2.restitution = restitution[i];
            bd.position = new Vec2(-10.0f + 3.0f * i, 10.0f);
            final Body myBody = this.m_world.createDynamicBody(bd);
            myBody.createShape(sd2);
            myBody.setMassFromShapes();
        }
    }
    
    public String getName() {
        return "Varying Restitution";
    }
}
