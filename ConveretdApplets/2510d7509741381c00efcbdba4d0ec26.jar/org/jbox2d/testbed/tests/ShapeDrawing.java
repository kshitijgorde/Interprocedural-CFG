// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed.tests;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.testbed.TestbedMain;
import org.jbox2d.common.Vec2;
import org.jbox2d.testbed.AbstractExample;

public class ShapeDrawing extends AbstractExample
{
    private boolean activeMouseStroke;
    private Vec2[] mouseStroke;
    private final int mouseStrokeMaxLength = 1000;
    private int mouseStrokeLength;
    private final float minMouseStrokeChange = 0.2f;
    private float strokeWidth;
    
    public ShapeDrawing(final TestbedMain _parent) {
        super(_parent);
        this.strokeWidth = 0.2f;
    }
    
    public void create() {
        this.activeMouseStroke = false;
        this.mouseStroke = new Vec2[1000];
        for (int i = 0; i < this.mouseStroke.length; ++i) {
            this.mouseStroke[i] = new Vec2();
        }
        this.mouseStrokeLength = 0;
        Body ground = null;
        final BodyDef bd = new BodyDef();
        bd.position.set(0.0f, -10.0f);
        ground = this.m_world.createStaticBody(bd);
        final PolygonDef sd = new PolygonDef();
        sd.setAsBox(50.0f, 10.0f);
        ground.createShape(sd);
    }
    
    public void preStep() {
        if (this.activeMouseStroke && this.mouseStrokeLength > 1) {
            for (int i = 0; i < this.mouseStrokeLength - 1; ++i) {
                this.m_debugDraw.drawSegment(this.mouseStroke[i], this.mouseStroke[i + 1], ShapeDrawing.white);
            }
        }
    }
    
    public void beginMouseStroke() {
        this.activeMouseStroke = true;
        this.mouseStrokeLength = 0;
        this.addStrokeSegment();
    }
    
    public void addStrokeSegment() {
        assert this.activeMouseStroke;
        if (this.mouseStrokeLength == 0) {
            this.mouseStroke[this.mouseStrokeLength++].set(this.mouseWorld);
        }
        else if (this.mouseStrokeLength < 1000) {
            final Vec2 worldDiff = this.mouseStroke[this.mouseStrokeLength - 1].sub(this.mouseWorld);
            final float norm = worldDiff.length();
            System.out.println(String.valueOf(norm) + " vs " + 0.2f);
            if (norm > 0.2f) {
                System.out.println("Creating...");
                this.mouseStroke[this.mouseStrokeLength++].set(this.mouseWorld);
            }
        }
    }
    
    public void finalizeStroke() {
        if (this.mouseStrokeLength < 2) {
            return;
        }
        final BodyDef myBodyDef = new BodyDef();
        myBodyDef.isBullet = true;
        final Body myBody = this.m_world.createDynamicBody(myBodyDef);
        for (int i = 0; i < this.mouseStrokeLength - 1; ++i) {
            final PolygonDef sd = new PolygonDef();
            sd.density = 2.0f;
            sd.friction = 0.3f;
            System.out.println(this.mouseStroke[i] + " " + this.mouseStroke[i + 1]);
            this.createStrokeRect(this.mouseStroke[i], this.mouseStroke[i + 1], this.strokeWidth, myBody, sd);
        }
        myBody.setMassFromShapes();
        this.activeMouseStroke = false;
        this.mouseStrokeLength = 0;
    }
    
    public void createStrokeRect(final Vec2 start, final Vec2 end, final float radius, final Body body, final PolygonDef sd) {
        final Vec2 tangent = end.sub(start);
        final Vec2 perp = new Vec2(tangent.y, -tangent.x);
        perp.normalize();
        perp.mulLocal(radius);
        sd.vertices.add(start.add(perp));
        sd.vertices.add(end.add(perp));
        sd.vertices.add(end.sub(perp));
        sd.vertices.add(start.sub(perp));
        body.createShape(sd);
    }
    
    public void mouseDown(final Vec2 p) {
        this.mouseScreen.set(p);
        this.mouseWorld.set(this.m_debugDraw.screenToWorld(p));
        if (this.parent.mouseButton == 39) {
            return;
        }
        if (this.parent.shiftKey) {
            this.spawnBomb(this.mouseWorld);
            return;
        }
        System.out.println("Mouse down");
        this.beginMouseStroke();
    }
    
    public void mouseUp() {
        if (this.bombSpawning) {
            this.completeBombSpawn();
        }
        if (this.activeMouseStroke) {
            this.finalizeStroke();
        }
        this.activeMouseStroke = false;
    }
    
    public void mouseMove(final Vec2 p) {
        this.mouseScreen.set(p);
        this.mouseWorld.set(this.m_debugDraw.screenToWorld(p));
        if (this.activeMouseStroke) {
            this.addStrokeSegment();
        }
    }
    
    public String getName() {
        return "Shape Drawing Example";
    }
    
    public String getExampleInstructions() {
        return "Use the mouse to paint.\nMouse dragging is disabled in this demo.";
    }
}
