// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Mat22;
import org.jbox2d.common.XForm;
import org.jbox2d.common.Vec2;

public class CollidePoly
{
    static int clipSegmentToLine(final ClipVertex[] vOut, final ClipVertex[] vIn, final Vec2 normal, final float offset) {
        int numOut = 0;
        final float distance0 = Vec2.dot(normal, vIn[0].v) - offset;
        final float distance2 = Vec2.dot(normal, vIn[1].v) - offset;
        if (distance0 <= 0.0f) {
            vOut[numOut] = new ClipVertex();
            vOut[numOut].id = new ContactID(vIn[0].id);
            vOut[numOut++].v = vIn[0].v.clone();
        }
        if (distance2 <= 0.0f) {
            vOut[numOut] = new ClipVertex();
            vOut[numOut].id = new ContactID(vIn[1].id);
            vOut[numOut++].v = vIn[1].v.clone();
        }
        if (distance0 * distance2 < 0.0f) {
            final float interp = distance0 / (distance0 - distance2);
            vOut[numOut] = new ClipVertex();
            vOut[numOut].v.x = vIn[0].v.x + interp * (vIn[1].v.x - vIn[0].v.x);
            vOut[numOut].v.y = vIn[0].v.y + interp * (vIn[1].v.y - vIn[0].v.y);
            if (distance0 > 0.0f) {
                vOut[numOut].id = new ContactID(vIn[0].id);
            }
            else {
                vOut[numOut].id = new ContactID(vIn[1].id);
            }
            ++numOut;
        }
        return numOut;
    }
    
    static float edgeSeparation(final PolygonShape poly1, final XForm xf1, final int edge1, final PolygonShape poly2, final XForm xf2) {
        assert edge1 >= 0 && edge1 < poly1.m_vertexCount;
        final Vec2 normal1World = Mat22.mul(xf1.R, poly1.m_normals[edge1]);
        final Vec2 normal1 = Mat22.mulT(xf2.R, normal1World);
        int index = 0;
        float minDot = Float.MAX_VALUE;
        for (int i = 0; i < poly2.m_vertexCount; ++i) {
            final float dot = Vec2.dot(poly2.m_vertices[i], normal1);
            if (dot < minDot) {
                minDot = dot;
                index = i;
            }
        }
        final Vec2 v1 = XForm.mul(xf1, poly1.m_vertices[edge1]);
        final Vec2 v2 = XForm.mul(xf2, poly2.m_vertices[index]);
        final float separation = Vec2.dot(v2.sub(v1), normal1World);
        return separation;
    }
    
    static MaxSeparation findMaxSeparation(final PolygonShape poly1, final XForm xf1, final PolygonShape poly2, final XForm xf2) {
        final MaxSeparation separation = new MaxSeparation();
        final int count1 = poly1.m_vertexCount;
        final Vec2 d = XForm.mul(xf2, poly2.m_centroid).subLocal(XForm.mul(xf1, poly1.m_centroid));
        final Vec2 dLocal1 = Mat22.mulT(xf1.R, d);
        int edge = 0;
        float maxDot = -3.4028235E38f;
        for (int i = 0; i < count1; ++i) {
            final float dot = Vec2.dot(poly1.m_normals[i], dLocal1);
            if (dot > maxDot) {
                maxDot = dot;
                edge = i;
            }
        }
        float s = edgeSeparation(poly1, xf1, edge, poly2, xf2);
        if (s > 0.0f) {
            separation.bestSeparation = s;
            return separation;
        }
        final int prevEdge = (edge - 1 >= 0) ? (edge - 1) : (count1 - 1);
        final float sPrev = edgeSeparation(poly1, xf1, prevEdge, poly2, xf2);
        if (sPrev > 0.0f) {
            separation.bestSeparation = sPrev;
            return separation;
        }
        final int nextEdge = (edge + 1 < count1) ? (edge + 1) : 0;
        final float sNext = edgeSeparation(poly1, xf1, nextEdge, poly2, xf2);
        if (sNext > 0.0f) {
            separation.bestSeparation = sNext;
            return separation;
        }
        int increment;
        int bestEdge;
        float bestSeparation;
        if (sPrev > s && sPrev > sNext) {
            increment = -1;
            bestEdge = prevEdge;
            bestSeparation = sPrev;
        }
        else {
            if (sNext <= s) {
                separation.bestFaceIndex = edge;
                separation.bestSeparation = s;
                return separation;
            }
            increment = 1;
            bestEdge = nextEdge;
            bestSeparation = sNext;
        }
        while (true) {
            if (increment == -1) {
                edge = ((bestEdge - 1 >= 0) ? (bestEdge - 1) : (count1 - 1));
            }
            else {
                edge = ((bestEdge + 1 < count1) ? (bestEdge + 1) : 0);
            }
            s = edgeSeparation(poly1, xf1, edge, poly2, xf2);
            if (s > 0.0f) {
                separation.bestSeparation = s;
                return separation;
            }
            if (s <= bestSeparation) {
                separation.bestFaceIndex = bestEdge;
                separation.bestSeparation = bestSeparation;
                return separation;
            }
            bestEdge = edge;
            bestSeparation = s;
        }
    }
    
    static void findIncidentEdge(final ClipVertex[] c, final PolygonShape poly1, final XForm xf1, final int edge1, final PolygonShape poly2, final XForm xf2) {
        assert edge1 >= 0 && edge1 < poly1.m_vertexCount;
        final Vec2 normal1 = Mat22.mulT(xf2.R, Mat22.mul(xf1.R, poly1.m_normals[edge1]));
        int index = 0;
        float minDot = Float.MAX_VALUE;
        for (int i = 0; i < poly2.m_vertexCount; ++i) {
            final float dot = Vec2.dot(normal1, poly2.m_normals[i]);
            if (dot < minDot) {
                minDot = dot;
                index = i;
            }
        }
        final int i2 = index;
        final int i3 = (i2 + 1 < poly2.m_vertexCount) ? (i2 + 1) : 0;
        c[0] = new ClipVertex();
        c[1] = new ClipVertex();
        c[0].v = XForm.mul(xf2, poly2.m_vertices[i2]);
        c[0].id.features.referenceFace = edge1;
        c[0].id.features.incidentEdge = i2;
        c[0].id.features.incidentVertex = 0;
        c[1].v = XForm.mul(xf2, poly2.m_vertices[i3]);
        c[1].id.features.referenceFace = edge1;
        c[1].id.features.incidentEdge = i3;
        c[1].id.features.incidentVertex = 1;
    }
    
    public static void collidePoly(final Manifold manif, final PolygonShape polyA, final XForm xfA, final PolygonShape polyB, final XForm xfB) {
        manif.pointCount = 0;
        final MaxSeparation sepA = findMaxSeparation(polyA, xfA, polyB, xfB);
        if (sepA.bestSeparation > 0.0f) {
            return;
        }
        final MaxSeparation sepB = findMaxSeparation(polyB, xfB, polyA, xfA);
        if (sepB.bestSeparation > 0.0f) {
            return;
        }
        final XForm xf1 = new XForm();
        final XForm xf2 = new XForm();
        final float k_relativeTol = 0.98f;
        final float k_absoluteTol = 0.001f;
        PolygonShape poly1;
        PolygonShape poly2;
        int edge1;
        byte flip;
        if (sepB.bestSeparation > k_relativeTol * sepA.bestSeparation + k_absoluteTol) {
            poly1 = polyB;
            poly2 = polyA;
            xf1.set(xfB);
            xf2.set(xfA);
            edge1 = sepB.bestFaceIndex;
            flip = 1;
        }
        else {
            poly1 = polyA;
            poly2 = polyB;
            xf1.set(xfA);
            xf2.set(xfB);
            edge1 = sepA.bestFaceIndex;
            flip = 0;
        }
        final ClipVertex[] incidentEdge = new ClipVertex[2];
        findIncidentEdge(incidentEdge, poly1, xf1, edge1, poly2, xf2);
        final int count1 = poly1.m_vertexCount;
        final Vec2[] vert1s = poly1.m_vertices;
        Vec2 v11 = vert1s[edge1];
        Vec2 v12 = (edge1 + 1 < count1) ? vert1s[edge1 + 1] : vert1s[0];
        final Vec2 sideNormal = Mat22.mul(xf1.R, v12.sub(v11));
        sideNormal.normalize();
        final Vec2 frontNormal = Vec2.cross(sideNormal, 1.0f);
        v11 = XForm.mul(xf1, v11);
        v12 = XForm.mul(xf1, v12);
        final float frontOffset = Vec2.dot(frontNormal, v11);
        final float sideOffset1 = -Vec2.dot(sideNormal, v11);
        final float sideOffset2 = Vec2.dot(sideNormal, v12);
        final ClipVertex[] clipPoints1 = new ClipVertex[2];
        final ClipVertex[] clipPoints2 = new ClipVertex[2];
        int np = clipSegmentToLine(clipPoints1, incidentEdge, sideNormal.negate(), sideOffset1);
        if (np < 2) {
            return;
        }
        np = clipSegmentToLine(clipPoints2, clipPoints1, sideNormal, sideOffset2);
        if (np < 2) {
            return;
        }
        manif.normal = ((flip != 0) ? frontNormal.negate() : frontNormal.clone());
        int pointCount = 0;
        for (int i = 0; i < 2; ++i) {
            final float separation = Vec2.dot(frontNormal, clipPoints2[i].v) - frontOffset;
            if (separation <= 0.0f) {
                final ManifoldPoint cp = manif.points[pointCount];
                cp.separation = separation;
                cp.localPoint1 = XForm.mulT(xfA, clipPoints2[i].v);
                cp.localPoint2 = XForm.mulT(xfB, clipPoints2[i].v);
                cp.id = new ContactID(clipPoints2[i].id);
                cp.id.features.flip = flip;
                ++pointCount;
            }
        }
        manif.pointCount = pointCount;
    }
    
    static class ClipVertex
    {
        Vec2 v;
        ContactID id;
        
        public ClipVertex() {
            this.v = new Vec2();
            this.id = new ContactID();
        }
    }
}
