// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed;

import processing.core.PImage;
import processing.core.PGraphics3D;
import org.jbox2d.common.XForm;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;
import processing.core.PFont;
import processing.core.PApplet;
import org.jbox2d.dynamics.DebugDraw;

public class ProcessingDebugDraw extends DebugDraw
{
    public static ProcessingDebugDraw screen;
    private boolean firstTime;
    public PApplet g;
    public PFont m_font;
    public float fontHeight;
    public float transX;
    public float transY;
    public float scaleFactor;
    public float yFlip;
    
    public void setCamera(final float x, final float y, final float scale) {
        this.transX = PApplet.map(x, 0.0f, -1.0f, this.g.width * 0.5f, this.g.width * 0.5f + scale);
        this.transY = PApplet.map(y, 0.0f, this.yFlip * 1.0f, this.g.height * 0.5f, this.g.height * 0.5f + scale);
        this.scaleFactor = scale;
    }
    
    public ProcessingDebugDraw(final PApplet pApplet) {
        this.firstTime = true;
        this.transX = 320.0f;
        this.transY = 240.0f;
        this.scaleFactor = 20.0f;
        this.yFlip = -1.0f;
        ProcessingDebugDraw.screen = this;
        this.g = pApplet;
        this.m_font = this.g.createFont("LucidaGrande-Bold", 12.0f);
        this.fontHeight = 14.0f;
    }
    
    public Vec2 worldToScreen(final Vec2 world) {
        final float x = PApplet.map(world.x, 0.0f, 1.0f, this.transX, this.transX + this.scaleFactor);
        float y = PApplet.map(world.y, 0.0f, 1.0f, this.transY, this.transY + this.scaleFactor);
        if (this.yFlip == -1.0f) {
            y = PApplet.map(y, 0.0f, (float)this.g.height, (float)this.g.height, 0.0f);
        }
        return new Vec2(x, y);
    }
    
    public Vec2 worldToScreen(final float x, final float y) {
        return this.worldToScreen(new Vec2(x, y));
    }
    
    public Vec2 screenToWorld(final Vec2 screen) {
        final float x = PApplet.map(screen.x, this.transX, this.transX + this.scaleFactor, 0.0f, 1.0f);
        float y = screen.y;
        if (this.yFlip == -1.0f) {
            y = PApplet.map(y, (float)this.g.height, 0.0f, 0.0f, (float)this.g.height);
        }
        y = PApplet.map(y, this.transY, this.transY + this.scaleFactor, 0.0f, 1.0f);
        return new Vec2(x, y);
    }
    
    public Vec2 screenToWorld(final float x, final float y) {
        return this.screenToWorld(new Vec2(x, y));
    }
    
    public void drawCircle(Vec2 center, float radius, final Color3f color) {
        center = this.worldToScreen(center);
        radius *= this.scaleFactor;
        this.g.noFill();
        final float k_segments = 16.0f;
        final float k_increment = 6.2831855f / k_segments;
        float theta = 0.0f;
        this.g.stroke(color.x, color.y, color.z);
        this.g.noFill();
        this.g.beginShape(256);
        for (int i = 0; i < k_segments; ++i) {
            final float vx = center.x + radius * (float)Math.cos(theta);
            final float vy = center.y + radius * (float)Math.sin(theta);
            this.g.vertex(vx, vy);
            theta += k_increment;
        }
        this.g.vertex(center.x + radius, center.y);
        this.g.endShape();
    }
    
    public void drawSolidCircle(Vec2 center, float radius, Vec2 axis, final Color3f color) {
        center = this.worldToScreen(center);
        radius *= this.scaleFactor;
        axis = new Vec2(axis.x, axis.y * this.yFlip);
        final float k_segments = 16.0f;
        final float k_increment = 6.2831855f / k_segments;
        float theta = 0.0f;
        this.g.fill(0.5f * color.x, 0.5f * color.y, 0.5f * color.z, 127.5f);
        this.g.stroke(color.x, color.y, color.z, 255.0f);
        this.g.beginShape(256);
        for (int i = 0; i < k_segments; ++i) {
            final float vx = center.x + radius * (float)Math.cos(theta);
            final float vy = center.y + radius * (float)Math.sin(theta);
            this.g.vertex(vx, vy);
            theta += k_increment;
        }
        this.g.vertex(center.x + radius, center.y);
        this.g.endShape();
        final Vec2 p = new Vec2(center.x + radius * axis.x, center.y + radius * axis.y);
        this.g.beginShape(32);
        this.g.vertex(center.x, center.y);
        this.g.vertex(p.x, p.y);
        this.g.endShape();
    }
    
    public void drawPolygon(final Vec2[] vertices, final int vertexCount, final Color3f color) {
        this.g.stroke(color.x, color.y, color.z);
        this.g.noFill();
        for (int i = 0; i < vertexCount; ++i) {
            final int ind = (i + 1 < vertexCount) ? (i + 1) : (i + 1 - vertexCount);
            final Vec2 v1 = this.worldToScreen(vertices[i]);
            final Vec2 v2 = this.worldToScreen(vertices[ind]);
            this.g.line(v1.x, v1.y, v2.x, v2.y);
        }
    }
    
    public void drawSolidPolygon(final Vec2[] vertices, final int vertexCount, final Color3f color) {
        this.g.noStroke();
        this.g.fill(0.5f * color.x, 0.5f * color.y, 0.5f * color.z, 127.5f);
        this.g.beginShape(256);
        for (int i = 0; i < vertexCount; ++i) {
            final Vec2 v = this.worldToScreen(vertices[i]);
            this.g.vertex(v.x, v.y);
        }
        this.g.endShape();
        this.g.stroke(color.x, color.y, color.z, 255.0f);
        for (int i = 0; i < vertexCount; ++i) {
            final int ind = (i + 1 < vertexCount) ? (i + 1) : (i + 1 - vertexCount);
            final Vec2 v2 = this.worldToScreen(vertices[i]);
            final Vec2 v3 = this.worldToScreen(vertices[ind]);
            this.g.line(v2.x, v2.y, v3.x, v3.y);
        }
    }
    
    public void drawSegment(Vec2 p1, Vec2 p2, final Color3f color) {
        p1 = this.worldToScreen(p1);
        p2 = this.worldToScreen(p2);
        this.g.stroke(color.x, color.y, color.z);
        this.g.beginShape(32);
        this.g.vertex(p1.x, p1.y);
        this.g.vertex(p2.x, p2.y);
        this.g.endShape();
    }
    
    public void drawXForm(final XForm xf) {
        final Vec2 p1 = xf.position.clone();
        final Vec2 p2 = new Vec2();
        final float k_axisScale = 0.4f;
        this.g.beginShape(32);
        final Vec2 p1world = this.worldToScreen(p1);
        this.g.stroke(1.0f, 0.0f, 0.0f);
        this.g.vertex(p1world.x, p1world.y);
        p2.x = p1.x + k_axisScale * xf.R.col1.x;
        p2.y = p1.y + k_axisScale * xf.R.col1.y;
        Vec2 p2world = this.worldToScreen(p2);
        this.g.vertex(p2world.x, p2world.y);
        this.g.stroke(0.0f, 1.0f, 0.0f);
        this.g.vertex(p1world.x, p1world.y);
        p2.x = p1.x + k_axisScale * xf.R.col2.x;
        p2.y = p1.x + k_axisScale * xf.R.col2.y;
        p2world = this.worldToScreen(p2);
        this.g.vertex(p2.x, p2.y);
        this.g.endShape();
    }
    
    public void drawString(final float x, final float y, final String s, final Color3f color) {
        if (this.firstTime) {
            this.g.textFont(this.m_font);
            if (this.g.g instanceof PGraphics3D) {
                this.g.textMode(256);
            }
            this.firstTime = false;
        }
        this.g.fill(color.x, color.y, color.z);
        this.g.text(s, x, y);
    }
    
    public void drawPoint(Vec2 position, final float f, final Color3f color) {
        position = this.worldToScreen(position);
        final float k_segments = 5.0f;
        final float k_increment = 6.2831855f / k_segments;
        final float k_radius = 3.0f;
        float theta = 0.0f;
        this.g.fill(color.x, color.y, color.z);
        this.g.noStroke();
        this.g.beginShape(256);
        for (int i = 0; i < k_segments; ++i) {
            final float vx = position.x + k_radius * (float)Math.cos(theta);
            final float vy = position.y + k_radius * (float)Math.sin(theta);
            this.g.vertex(vx, vy);
            theta += k_increment;
        }
        this.g.endShape();
    }
    
    public void drawImage(final PImage image, Vec2 position, final float rotation, float localScale, Vec2 localOffset, final float halfImageWidth, final float halfImageHeight) {
        position = this.worldToScreen(position);
        localOffset = this.worldToScreenVector(localOffset);
        localScale *= this.scaleFactor;
        this.g.pushMatrix();
        this.g.translate(position.x, position.y);
        this.g.rotate(-rotation);
        this.g.translate(localOffset.x, localOffset.y);
        this.g.scale(localScale);
        this.g.image(image, -halfImageWidth, -halfImageHeight);
        this.g.popMatrix();
    }
    
    public Vec2 worldToScreenVector(final Vec2 world) {
        return world.mul(this.scaleFactor);
    }
    
    public Vec2 worldToScreenVector(final float x, final float y) {
        return this.worldToScreenVector(new Vec2(x, y));
    }
}
