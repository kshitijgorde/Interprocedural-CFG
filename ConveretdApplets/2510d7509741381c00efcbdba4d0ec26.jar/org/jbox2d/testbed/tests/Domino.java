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

public class Domino extends AbstractExample
{
    public Domino(final TestbedMain _parent) {
        super(_parent);
    }
    
    public void create() {
        PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(0.0f, -10.0f);
        this.m_world.createStaticBody(bd).createShape(sd);
        for (int i = 0; i < 4; ++i) {
            final PolygonDef sd2 = new PolygonDef();
            sd2.setAsBox(15.0f, 0.125f);
            final BodyDef bd2 = new BodyDef();
            bd2.position = new Vec2(0.0f, 5.0f + 5.0f * i);
            this.m_world.createStaticBody(bd2).createShape(sd2);
        }
        sd = new PolygonDef();
        sd.setAsBox(0.125f, 2.0f);
        sd.density = 25.0f;
        bd = new BodyDef();
        final float friction = 0.5f;
        final int numPerRow = 25;
        for (int j = 0; j < 4; ++j) {
            for (int k = 0; k < numPerRow; ++k) {
                sd.friction = friction;
                bd.position = new Vec2(-14.75f + k * (29.5f / (numPerRow - 1)), 7.3f + 5.0f * j);
                if (j == 2 && k == 0) {
                    bd.angle = -0.1f;
                    final Vec2 position = bd.position;
                    position.x += 0.1f;
                }
                else if (j == 3 && k == numPerRow - 1) {
                    bd.angle = 0.1f;
                    final Vec2 position2 = bd.position;
                    position2.x -= 0.1f;
                }
                else {
                    bd.angle = 0.0f;
                }
                final Body myBody = this.m_world.createDynamicBody(bd);
                myBody.createShape(sd);
                myBody.setMassFromShapes();
            }
        }
    }
    
    public String getName() {
        return "Domino Test";
    }
}
