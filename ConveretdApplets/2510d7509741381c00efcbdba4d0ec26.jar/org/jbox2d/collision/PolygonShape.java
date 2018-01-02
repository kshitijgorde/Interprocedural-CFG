// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import java.util.List;
import org.jbox2d.common.XForm;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class PolygonShape extends Shape implements SupportsGenericDistance
{
    private static boolean m_debug;
    public Vec2 m_centroid;
    public OBB m_obb;
    public Vec2[] m_vertices;
    public Vec2[] m_normals;
    public Vec2[] m_coreVertices;
    public int m_vertexCount;
    
    static {
        PolygonShape.m_debug = true;
    }
    
    public PolygonShape(final ShapeDef def) {
        super(def);
        assert def.type == ShapeType.POLYGON_SHAPE;
        this.m_type = ShapeType.POLYGON_SHAPE;
        final PolygonDef poly = (PolygonDef)def;
        this.m_vertexCount = poly.vertexCount();
        this.m_vertices = new Vec2[this.m_vertexCount];
        this.m_normals = new Vec2[this.m_vertexCount];
        this.m_coreVertices = new Vec2[this.m_vertexCount];
        this.m_obb = new OBB();
        assert 3 <= this.m_vertexCount && this.m_vertexCount <= 8;
        for (int i = 0; i < this.m_vertexCount; ++i) {
            this.m_vertices[i] = poly.vertices.get(i).clone();
        }
        for (int i = 0; i < this.m_vertexCount; ++i) {
            final int i2 = i;
            final int i3 = (i + 1 < this.m_vertexCount) ? (i + 1) : 0;
            final Vec2 edge = this.m_vertices[i3].sub(this.m_vertices[i2]);
            assert edge.lengthSquared() > 1.4210855E-14f;
            (this.m_normals[i] = Vec2.cross(edge, 1.0f)).normalize();
        }
        if (PolygonShape.m_debug) {
            for (int i = 0; i < this.m_vertexCount; ++i) {
                for (int j = 0; j < this.m_vertexCount; ++j) {
                    if (j != i) {
                        if (j != (i + 1) % this.m_vertexCount) {
                            final float s = Vec2.dot(this.m_normals[i], this.m_vertices[j].sub(this.m_vertices[i]));
                            assert s < -0.005f;
                        }
                    }
                }
            }
            for (int i = 1; i < this.m_vertexCount; ++i) {
                float cross = Vec2.cross(this.m_normals[i - 1], this.m_normals[i]);
                cross = MathUtils.clamp(cross, -1.0f, 1.0f);
                final float angle = (float)Math.asin(cross);
                assert angle > 0.03490659f;
            }
        }
        this.m_centroid = computeCentroid(poly.vertices);
        computeOBB(this.m_obb, this.m_vertices);
        for (int i = 0; i < this.m_vertexCount; ++i) {
            final int i2 = (i - 1 >= 0) ? (i - 1) : (this.m_vertexCount - 1);
            final int i3 = i;
            final Vec2 n1 = this.m_normals[i2];
            final Vec2 n2 = this.m_normals[i3];
            final Vec2 v = this.m_vertices[i].sub(this.m_centroid);
            final Vec2 d = new Vec2();
            d.x = Vec2.dot(n1, v) - 0.04f;
            d.y = Vec2.dot(n2, v) - 0.04f;
            if (PolygonShape.m_debug && (d.x < 0.0f || d.y < 0.0f)) {
                System.out.println("Error, dumping details: ");
                System.out.println("d.x: " + d.x + "d.y: " + d.y);
                System.out.println("n1: " + n1 + "; n2: " + n2);
                System.out.println("v: " + v);
            }
            assert d.x >= 0.0f;
            assert d.y >= 0.0f;
            final Mat22 A = new Mat22();
            A.col1.x = n1.x;
            A.col2.x = n1.y;
            A.col1.y = n2.x;
            A.col2.y = n2.y;
            this.m_coreVertices[i] = A.solve(d).addLocal(this.m_centroid);
        }
        if (PolygonShape.m_debug) {
            System.out.println("\nDumping polygon shape...");
            System.out.println("Vertices: ");
            for (int i = 0; i < this.m_vertexCount; ++i) {
                System.out.println(this.m_vertices[i]);
            }
            System.out.println("Core Vertices: ");
            for (int i = 0; i < this.m_vertexCount; ++i) {
                System.out.println(this.m_coreVertices[i]);
            }
            System.out.println("Normals: ");
            for (int i = 0; i < this.m_vertexCount; ++i) {
                System.out.println(this.m_normals[i]);
            }
            System.out.println("Centroid: " + this.m_centroid);
        }
    }
    
    public void updateSweepRadius(final Vec2 center) {
        this.m_sweepRadius = 0.0f;
        for (int i = 0; i < this.m_vertexCount; ++i) {
            final Vec2 d = this.m_coreVertices[i].sub(center);
            this.m_sweepRadius = Math.max(this.m_sweepRadius, d.length());
        }
    }
    
    public boolean testPoint(final XForm xf, final Vec2 p) {
        final Vec2 pLocal = Mat22.mulT(xf.R, p.sub(xf.position));
        if (PolygonShape.m_debug) {
            System.out.println("--testPoint debug--");
            System.out.println("Vertices: ");
            for (int i = 0; i < this.m_vertexCount; ++i) {
                System.out.println(this.m_vertices[i]);
            }
            System.out.println("pLocal: " + pLocal);
        }
        for (int i = 0; i < this.m_vertexCount; ++i) {
            final float dot = Vec2.dot(this.m_normals[i], pLocal.sub(this.m_vertices[i]));
            if (dot > 0.0f) {
                return false;
            }
        }
        return true;
    }
    
    public Vec2 centroid(final XForm xf) {
        return XForm.mul(xf, this.m_centroid);
    }
    
    public Vec2 support(final XForm xf, final Vec2 d) {
        final Vec2 dLocal = Mat22.mulT(xf.R, d);
        int bestIndex = 0;
        float bestValue = Vec2.dot(this.m_coreVertices[0], dLocal);
        for (int i = 1; i < this.m_vertexCount; ++i) {
            final float value = Vec2.dot(this.m_coreVertices[i], dLocal);
            if (value > bestValue) {
                bestIndex = i;
                bestValue = value;
            }
        }
        return XForm.mul(xf, this.m_coreVertices[bestIndex]);
    }
    
    public static Vec2 computeCentroid(final List<Vec2> vs) {
        final int count = vs.size();
        assert count >= 3;
        final Vec2 c = new Vec2();
        float area = 0.0f;
        final Vec2 pRef = new Vec2();
        final float inv3 = 0.33333334f;
        for (int i = 0; i < count; ++i) {
            final Vec2 p1 = pRef;
            final Vec2 p2 = vs.get(i);
            final Vec2 p3 = (i + 1 < count) ? vs.get(i + 1) : vs.get(0);
            final Vec2 e1 = p2.sub(p1);
            final Vec2 e2 = p3.sub(p1);
            final float D = Vec2.cross(e1, e2);
            final float triangleArea = 0.5f * D;
            area += triangleArea;
            final Vec2 vec2 = c;
            vec2.x += triangleArea * 0.33333334f * (p1.x + p2.x + p3.x);
            final Vec2 vec3 = c;
            vec3.y += triangleArea * 0.33333334f * (p1.y + p2.y + p3.y);
        }
        assert area > 1.1920929E-7f;
        c.mulLocal(1.0f / area);
        return c;
    }
    
    public static void computeOBB(final OBB obb, final Vec2[] vs) {
        final int count = vs.length;
        assert count <= 8;
        final Vec2[] p = new Vec2[9];
        for (int i = 0; i < count; ++i) {
            p[i] = vs[i];
        }
        p[count] = p[0];
        float minArea = Float.MAX_VALUE;
        for (int j = 1; j <= count; ++j) {
            final Vec2 root = p[j - 1];
            final Vec2 ux = p[j].sub(root);
            final float length = ux.normalize();
            assert length > 1.1920929E-7f;
            final Vec2 uy = new Vec2(-ux.y, ux.x);
            Vec2 lower = new Vec2(Float.MAX_VALUE, Float.MAX_VALUE);
            Vec2 upper = new Vec2(-3.4028235E38f, -3.4028235E38f);
            for (int k = 0; k < count; ++k) {
                final Vec2 d = p[k].sub(root);
                final Vec2 r = new Vec2();
                r.x = Vec2.dot(ux, d);
                r.y = Vec2.dot(uy, d);
                lower = Vec2.min(lower, r);
                upper = Vec2.max(upper, r);
            }
            final float area = (upper.x - lower.x) * (upper.y - lower.y);
            if (area < 0.95f * minArea) {
                minArea = area;
                obb.R.col1.set(ux);
                obb.R.col2.set(uy);
                final Vec2 center = new Vec2(0.5f * (lower.x + upper.x), 0.5f * (lower.y + upper.y));
                obb.center = root.add(Mat22.mul(obb.R, center));
                obb.extents = new Vec2(0.5f * (upper.x - lower.x), 0.5f * (upper.y - lower.y));
            }
        }
        assert minArea < Float.MAX_VALUE;
    }
    
    public void computeAABB(final AABB aabb, final XForm xf) {
        final Mat22 R = Mat22.mul(xf.R, this.m_obb.R);
        final Mat22 absR = Mat22.abs(R);
        final Vec2 h = Mat22.mul(absR, this.m_obb.extents);
        final Vec2 position = xf.position.add(Mat22.mul(xf.R, this.m_obb.center));
        aabb.lowerBound = position.sub(h);
        aabb.upperBound = position.add(h);
    }
    
    public void computeSweptAABB(final AABB aabb, final XForm transform1, final XForm transform2) {
        final AABB aabb2 = new AABB();
        final AABB aabb3 = new AABB();
        this.computeAABB(aabb2, transform1);
        this.computeAABB(aabb3, transform2);
        aabb.lowerBound = Vec2.min(aabb2.lowerBound, aabb3.lowerBound);
        aabb.upperBound = Vec2.max(aabb2.upperBound, aabb3.upperBound);
    }
    
    public void computeMass(final MassData massData) {
        assert this.m_vertexCount >= 3;
        final Vec2 center = new Vec2(0.0f, 0.0f);
        float area = 0.0f;
        float I = 0.0f;
        final Vec2 pRef = new Vec2(0.0f, 0.0f);
        final float k_inv3 = 0.33333334f;
        for (int i = 0; i < this.m_vertexCount; ++i) {
            final Vec2 p1 = pRef;
            final Vec2 p2 = this.m_vertices[i];
            final Vec2 p3 = (i + 1 < this.m_vertexCount) ? this.m_vertices[i + 1] : this.m_vertices[0];
            final Vec2 e1 = p2.sub(p1);
            final Vec2 e2 = p3.sub(p1);
            final float D = Vec2.cross(e1, e2);
            final float triangleArea = 0.5f * D;
            area += triangleArea;
            final Vec2 vec2 = center;
            vec2.x += triangleArea * k_inv3 * (p1.x + p2.x + p3.x);
            final Vec2 vec3 = center;
            vec3.y += triangleArea * k_inv3 * (p1.y + p2.y + p3.y);
            final float px = p1.x;
            final float py = p1.y;
            final float ex1 = e1.x;
            final float ey1 = e1.y;
            final float ex2 = e2.x;
            final float ey2 = e2.y;
            final float intx2 = k_inv3 * (0.25f * (ex1 * ex1 + ex2 * ex1 + ex2 * ex2) + (px * ex1 + px * ex2)) + 0.5f * px * px;
            final float inty2 = k_inv3 * (0.25f * (ey1 * ey1 + ey2 * ey1 + ey2 * ey2) + (py * ey1 + py * ey2)) + 0.5f * py * py;
            I += D * (intx2 + inty2);
        }
        massData.mass = this.m_density * area;
        assert area > 1.1920929E-7f;
        center.mulLocal(1.0f / area);
        massData.center = center.clone();
        massData.I = I * this.m_density;
    }
    
    public Vec2 getFirstVertex(final XForm xf) {
        return XForm.mul(xf, this.m_coreVertices[0]);
    }
    
    public OBB getOBB() {
        return this.m_obb.clone();
    }
    
    public Vec2 getCentroid() {
        return this.m_centroid.clone();
    }
    
    public int getVertexCount() {
        return this.m_vertexCount;
    }
    
    public Vec2[] getVertices() {
        return this.m_vertices;
    }
    
    public Vec2[] getCoreVertices() {
        return this.m_coreVertices;
    }
}
