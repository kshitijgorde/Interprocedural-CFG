// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.dynamics.Body;
import processing.core.PImage;
import org.jbox2d.common.Vec2;
import org.jbox2d.testbed.AbstractExample;

public class SpriteBinding extends AbstractExample
{
    public Vec2[] localCoords;
    public Vec2[] texCoords;
    public PImage myImage;
    public Body body;
    
    public SpriteBinding(final TestbedMain _parent) {
        super(_parent);
    }
    
    public String getName() {
        return "Sprite Binding";
    }
    
    public void create() {
        final int numBoxes = 15;
        final int numRows = 6;
        final Body[] boxes = new Body[numRows * numBoxes];
        final PolygonDef groundBoxDef = new PolygonDef();
        groundBoxDef.setAsBox(50.0f, 10.0f);
        groundBoxDef.density = 0.0f;
        final BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0.0f, -10.0f);
        this.m_world.createStaticBody(groundBodyDef).createShape(groundBoxDef);
        final PolygonDef boxDef = new PolygonDef();
        boxDef.setAsBox(1.0f, 1.0f);
        boxDef.density = 1.0f;
        boxDef.friction = 0.3f;
        boxDef.restitution = 0.3f;
        for (int i = 0; i < numBoxes; ++i) {
            for (int j = 0; j < numRows; ++j) {
                final BodyDef bodyDef = new BodyDef();
                bodyDef.position.set(-numBoxes - 3.0f + i * 2.5f, 4.0f + j * 5.0f);
                (boxes[i + numBoxes * j] = this.m_world.createDynamicBody(bodyDef)).createShape(boxDef);
                boxes[i + numBoxes * j].setMassFromShapes();
                boxes[i + numBoxes * j].setAngularVelocity(this.parent.random(-0.5f, 0.5f));
            }
        }
        this.myImage = this.parent.loadImage("noise.png");
        final Vec2 localOffset = new Vec2(0.0f, 0.0f);
        final float scale = 1.9f / this.myImage.width;
        final float rot = 0.0f;
        for (int i = 0; i < boxes.length; ++i) {
            this.bindImage(this.myImage, localOffset, rot, scale, boxes[i]);
        }
    }
}
