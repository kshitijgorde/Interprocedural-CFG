// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Mat22;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;

public class CollideCircle
{
    public static void collideCircle(final Manifold manifold, final CircleShape circle1, final XForm xf1, final CircleShape circle2, final XForm xf2) {
        manifold.pointCount = 0;
        final Vec2 p1 = XForm.mul(xf1, circle1.m_localPosition);
        final Vec2 p2 = XForm.mul(xf2, circle2.m_localPosition);
        final Vec2 d = p2.sub(p1);
        final float distSqr = Vec2.dot(d, d);
        final float radiusSum = circle1.m_radius + circle2.m_radius;
        if (distSqr > radiusSum * radiusSum) {
            return;
        }
        float separation;
        if (distSqr < 1.1920929E-7f) {
            separation = -radiusSum;
            manifold.normal.set(0.0f, 1.0f);
        }
        else {
            final float dist = (float)Math.sqrt(distSqr);
            separation = dist - radiusSum;
            final float a = 1.0f / dist;
            manifold.normal.x = a * d.x;
            manifold.normal.y = a * d.y;
        }
        manifold.pointCount = 1;
        manifold.points[0].id.zero();
        manifold.points[0].separation = separation;
        p1.addLocal(manifold.normal.mul(circle1.m_radius));
        p2.subLocal(manifold.normal.mul(circle2.m_radius));
        final Vec2 p3 = new Vec2(0.5f * (p1.x + p2.x), 0.5f * (p1.y + p2.y));
        manifold.points[0].localPoint1 = XForm.mulT(xf1, p3);
        manifold.points[0].localPoint2 = XForm.mulT(xf2, p3);
    }
    
    public static void collidePolygonAndCircle(final Manifold manifold, final PolygonShape polygon, final XForm xf1, final CircleShape circle, final XForm xf2) {
        manifold.pointCount = 0;
        final Vec2 c = XForm.mul(xf2, circle.m_localPosition);
        final Vec2 cLocal = XForm.mulT(xf1, c);
        int normalIndex = 0;
        float separation = -3.4028235E38f;
        final float radius = circle.m_radius;
        for (int i = 0; i < polygon.m_vertexCount; ++i) {
            final float s = Vec2.dot(polygon.m_normals[i], cLocal.sub(polygon.m_vertices[i]));
            if (s > circle.m_radius) {
                return;
            }
            if (s > separation) {
                normalIndex = i;
                separation = s;
            }
        }
        if (separation < 1.1920929E-7f) {
            manifold.pointCount = 1;
            manifold.normal = Mat22.mul(xf1.R, polygon.m_normals[normalIndex]);
            manifold.points[0].id.features.incidentEdge = normalIndex;
            manifold.points[0].id.features.incidentVertex = Integer.MAX_VALUE;
            manifold.points[0].id.features.referenceFace = Integer.MAX_VALUE;
            manifold.points[0].id.features.flip = 0;
            final Vec2 position = c.sub(manifold.normal.mul(radius));
            manifold.points[0].localPoint1 = XForm.mulT(xf1, position);
            manifold.points[0].localPoint2 = XForm.mulT(xf2, position);
            manifold.points[0].separation = separation - radius;
            return;
        }
        final int vertIndex1 = normalIndex;
        final int vertIndex2 = (vertIndex1 + 1 < polygon.m_vertexCount) ? (vertIndex1 + 1) : 0;
        final Vec2 e = polygon.m_vertices[vertIndex2].sub(polygon.m_vertices[vertIndex1]);
        final float length = e.normalize();
        if (length < 1.1920929E-7f) {
            final Vec2 d = cLocal.sub(polygon.m_vertices[vertIndex1]);
            final float dist = d.normalize();
            if (dist > radius) {
                return;
            }
            manifold.pointCount = 1;
            manifold.normal = Mat22.mul(xf1.R, d);
            manifold.points[0].id.features.incidentEdge = Integer.MAX_VALUE;
            manifold.points[0].id.features.incidentVertex = vertIndex1;
            manifold.points[0].id.features.referenceFace = Integer.MAX_VALUE;
            manifold.points[0].id.features.flip = 0;
            final Vec2 position2 = c.sub(manifold.normal.mul(radius));
            manifold.points[0].localPoint1 = XForm.mulT(xf1, position2);
            manifold.points[0].localPoint2 = XForm.mulT(xf2, position2);
            manifold.points[0].separation = dist - radius;
        }
        else {
            final float u = Vec2.dot(cLocal.sub(polygon.m_vertices[vertIndex1]), e);
            manifold.points[0].id.features.incidentEdge = Integer.MAX_VALUE;
            manifold.points[0].id.features.incidentVertex = Integer.MAX_VALUE;
            manifold.points[0].id.features.referenceFace = Integer.MAX_VALUE;
            manifold.points[0].id.features.flip = 0;
            final Vec2 p = new Vec2();
            if (u <= 0.0f) {
                p.set(polygon.m_vertices[vertIndex1]);
                manifold.points[0].id.features.incidentVertex = vertIndex1;
            }
            else if (u >= length) {
                p.set(polygon.m_vertices[vertIndex2]);
                manifold.points[0].id.features.incidentVertex = vertIndex2;
            }
            else {
                p.set(polygon.m_vertices[vertIndex1]);
                final Vec2 vec2 = p;
                vec2.x += u * e.x;
                final Vec2 vec3 = p;
                vec3.y += u * e.y;
                manifold.points[0].id.features.incidentEdge = vertIndex1;
            }
            final Vec2 d2 = cLocal.sub(p);
            final float dist2 = d2.normalize();
            if (dist2 > radius) {
                return;
            }
            manifold.pointCount = 1;
            manifold.normal = Mat22.mul(xf1.R, d2);
            final Vec2 position3 = c.sub(manifold.normal.mul(radius));
            manifold.points[0].localPoint1 = XForm.mulT(xf1, position3);
            manifold.points[0].localPoint2 = XForm.mulT(xf2, position3);
            manifold.points[0].separation = dist2 - radius;
        }
    }
}
