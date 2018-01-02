// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.XForm;
import org.jbox2d.common.Vec2;

public class Distance
{
    public static int g_GJK_Iterations;
    
    static {
        Distance.g_GJK_Iterations = 0;
    }
    
    protected static int ProcessTwo(final Vec2 x1, final Vec2 x2, final Vec2[] p1s, final Vec2[] p2s, final Vec2[] points) {
        final Vec2 r = new Vec2(-points[1].x, -points[1].y);
        final Vec2 d = new Vec2(points[0].x - points[1].x, points[0].y - points[1].y);
        final float length = d.normalize();
        float lambda = Vec2.dot(r, d);
        if (lambda <= 0.0f || length < 1.1920929E-7f) {
            x1.set(p1s[1]);
            x2.set(p2s[1]);
            p1s[0].set(p1s[1]);
            p2s[0].set(p2s[1]);
            points[0].set(points[1]);
            return 1;
        }
        lambda /= length;
        x1.set(p1s[1].x + lambda * (p1s[0].x - p1s[1].x), p1s[1].y + lambda * (p1s[0].y - p1s[1].y));
        x2.set(p2s[1].x + lambda * (p2s[0].x - p2s[1].x), p2s[1].y + lambda * (p2s[0].y - p2s[1].y));
        return 2;
    }
    
    protected static int ProcessThree(final Vec2 x1, final Vec2 x2, final Vec2[] p1s, final Vec2[] p2s, final Vec2[] points) {
        final Vec2 a = points[0];
        final Vec2 b = points[1];
        final Vec2 c = points[2];
        final Vec2 ab = b.sub(a);
        final Vec2 ac = c.sub(a);
        final Vec2 bc = c.sub(b);
        final float sn = -Vec2.dot(a, ab);
        final float sd = Vec2.dot(b, ab);
        final float tn = -Vec2.dot(a, ac);
        final float td = Vec2.dot(c, ac);
        final float un = -Vec2.dot(b, bc);
        final float ud = Vec2.dot(c, bc);
        if (td <= 0.0f && ud <= 0.0f) {
            x1.set(p1s[2]);
            x2.set(p2s[2]);
            p1s[0].set(p1s[2]);
            p2s[0].set(p2s[2]);
            points[0].set(points[2]);
            return 1;
        }
        assert tn > 0.0f;
        assert un > 0.0f;
        final float n = Vec2.cross(ab, ac);
        final float vc = n * Vec2.cross(a, b);
        assert sd > 0.0f;
        final float va = n * Vec2.cross(b, c);
        if (va <= 0.0f && un >= 0.0f && ud >= 0.0f && un + ud > 0.0f) {
            assert un + ud > 0.0f;
            final float lambda = un / (un + ud);
            x1.set(p1s[1].x + lambda * (p1s[2].x - p1s[1].x), p1s[1].y + lambda * (p1s[2].y - p1s[1].y));
            x2.set(p2s[1].x + lambda * (p2s[2].x - p2s[1].x), p2s[1].y + lambda * (p2s[2].y - p2s[1].y));
            p1s[0].set(p1s[2]);
            p2s[0].set(p2s[2]);
            points[0].set(points[2]);
            return 2;
        }
        else {
            final float vb = n * Vec2.cross(c, a);
            if (vb <= 0.0f && tn >= 0.0f && td >= 0.0f && tn + td > 0.0f) {
                assert tn + td > 0.0f;
                final float lambda2 = tn / (tn + td);
                x1.set(p1s[0].x + lambda2 * (p1s[2].x - p1s[0].x), p1s[0].y + lambda2 * (p1s[2].y - p1s[0].y));
                x2.set(p2s[0].x + lambda2 * (p2s[2].x - p2s[0].x), p2s[0].y + lambda2 * (p2s[2].y - p2s[0].y));
                p1s[1].set(p1s[2]);
                p2s[1].set(p2s[2]);
                points[1].set(points[2]);
                return 2;
            }
            else {
                float denom = va + vb + vc;
                assert denom > 0.0f;
                denom = 1.0f / denom;
                final float u = va * denom;
                final float v = vb * denom;
                final float w = 1.0f - u - v;
                x1.set(u * p1s[0].x + v * p1s[1].x + w * p1s[2].x, u * p1s[0].y + v * p1s[1].y + w * p1s[2].y);
                x2.set(u * p2s[0].x + v * p2s[1].x + w * p2s[2].x, u * p2s[0].y + v * p2s[1].y + w * p2s[2].y);
                return 3;
            }
        }
    }
    
    protected static boolean InPoints(final Vec2 w, final Vec2[] points, final int pointCount) {
        final float k_tolerance = 1.1920929E-5f;
        for (int i = 0; i < pointCount; ++i) {
            final Vec2 d = Vec2.abs(w.sub(points[i]));
            final Vec2 m = Vec2.max(Vec2.abs(w), Vec2.abs(points[i]));
            if (d.x < k_tolerance * (m.x + 1.0f) && d.y < k_tolerance * (m.y + 1.0f)) {
                return true;
            }
        }
        return false;
    }
    
    public static float DistanceGeneric(final Vec2 x1, final Vec2 x2, final SupportsGenericDistance shape1, final XForm xf1, final SupportsGenericDistance shape2, final XForm xf2) {
        final Vec2[] p1s = new Vec2[3];
        final Vec2[] p2s = new Vec2[3];
        final Vec2[] points = new Vec2[3];
        for (int i = 0; i < 3; ++i) {
            p1s[i] = new Vec2();
            p2s[i] = new Vec2();
            points[i] = new Vec2();
        }
        int pointCount = 0;
        x1.set(shape1.getFirstVertex(xf1));
        x2.set(shape2.getFirstVertex(xf2));
        float vSqr = 0.0f;
        final int maxIterations = 20;
        for (int iter = 0; iter < maxIterations; ++iter) {
            final Vec2 v = x2.sub(x1);
            final Vec2 w1 = shape1.support(xf1, v);
            final Vec2 w2 = shape2.support(xf2, v.negate());
            vSqr = Vec2.dot(v, v);
            final Vec2 w3 = w2.sub(w1);
            final float vw = Vec2.dot(v, w3);
            if (vSqr - vw <= 0.01f * vSqr || InPoints(w3, points, pointCount)) {
                if (pointCount == 0) {
                    x1.set(w1);
                    x2.set(w2);
                }
                Distance.g_GJK_Iterations = iter;
                return (float)Math.sqrt(vSqr);
            }
            switch (pointCount) {
                case 0: {
                    p1s[0].set(w1);
                    p2s[0].set(w2);
                    points[0].set(w3);
                    x1.set(p1s[0]);
                    x2.set(p2s[0]);
                    ++pointCount;
                    break;
                }
                case 1: {
                    p1s[1].set(w1);
                    p2s[1].set(w2);
                    points[1].set(w3);
                    pointCount = ProcessTwo(x1, x2, p1s, p2s, points);
                    break;
                }
                case 2: {
                    p1s[2].set(w1);
                    p2s[2].set(w2);
                    points[2].set(w3);
                    pointCount = ProcessThree(x1, x2, p1s, p2s, points);
                    break;
                }
            }
            if (pointCount == 3) {
                Distance.g_GJK_Iterations = iter;
                return 0.0f;
            }
            float maxSqr = -3.4028235E38f;
            for (int j = 0; j < pointCount; ++j) {
                maxSqr = Math.max(maxSqr, Vec2.dot(points[j], points[j]));
            }
            if (pointCount == 3 || vSqr <= 1.1920929E-5f * maxSqr) {
                Distance.g_GJK_Iterations = iter;
                v.set(x2.x - x1.x, x2.y - x1.y);
                vSqr = Vec2.dot(v, v);
                return (float)Math.sqrt(vSqr);
            }
        }
        Distance.g_GJK_Iterations = maxIterations;
        return (float)Math.sqrt(vSqr);
    }
    
    protected static float DistanceCC(final Vec2 x1, final Vec2 x2, final CircleShape circle1, final XForm xf1, final CircleShape circle2, final XForm xf2) {
        final Vec2 p1 = XForm.mul(xf1, circle1.m_localPosition);
        final Vec2 p2 = XForm.mul(xf2, circle2.m_localPosition);
        final Vec2 d = new Vec2(p2.x - p1.x, p2.y - p1.y);
        final float dSqr = Vec2.dot(d, d);
        final float r1 = circle1.m_radius - 0.04f;
        final float r2 = circle2.m_radius - 0.04f;
        final float r3 = r1 + r2;
        if (dSqr > r3 * r3) {
            final float dLen = d.normalize();
            final float distance = dLen - r3;
            x1.set(p1.x + r1 * d.x, p1.y + r1 * d.y);
            x2.set(p2.x - r2 * d.x, p2.y - r2 * d.y);
            return distance;
        }
        if (dSqr > 1.4210855E-14f) {
            d.normalize();
            x1.set(p1.x + r1 * d.x, p1.y + r1 * d.y);
            x2.set(x1);
            return 0.0f;
        }
        x1.set(p1);
        x2.set(x1);
        return 0.0f;
    }
    
    protected static float DistancePC(final Vec2 x1, final Vec2 x2, final PolygonShape polygon, final XForm xf1, final CircleShape circle, final XForm xf2) {
        final Point point = new Point(new Vec2(0.0f, 0.0f));
        point.p = XForm.mul(xf2, circle.m_localPosition);
        float distance = DistanceGeneric(x1, x2, polygon, xf1, point, XForm.identity);
        final float r = circle.getRadius() - 0.04f;
        if (distance > r) {
            distance -= r;
            final Vec2 d = x2.sub(x1);
            d.normalize();
            x2.x -= r * d.x;
            x2.y -= r * d.y;
        }
        else {
            distance = 0.0f;
            x2.set(x1);
        }
        return distance;
    }
    
    public static float distance(final Vec2 x1, final Vec2 x2, final Shape shape1, final XForm xf1, final Shape shape2, final XForm xf2) {
        final ShapeType type1 = shape1.getType();
        final ShapeType type2 = shape2.getType();
        if (type1 == ShapeType.CIRCLE_SHAPE && type2 == ShapeType.CIRCLE_SHAPE) {
            return DistanceCC(x1, x2, (CircleShape)shape1, xf1, (CircleShape)shape2, xf2);
        }
        if (type1 == ShapeType.POLYGON_SHAPE && type2 == ShapeType.CIRCLE_SHAPE) {
            return DistancePC(x1, x2, (PolygonShape)shape1, xf1, (CircleShape)shape2, xf2);
        }
        if (type1 == ShapeType.CIRCLE_SHAPE && type2 == ShapeType.POLYGON_SHAPE) {
            return DistancePC(x2, x1, (PolygonShape)shape2, xf2, (CircleShape)shape1, xf1);
        }
        if (type1 == ShapeType.POLYGON_SHAPE && type2 == ShapeType.POLYGON_SHAPE) {
            return DistanceGeneric(x1, x2, (SupportsGenericDistance)shape1, xf1, (SupportsGenericDistance)shape2, xf2);
        }
        return 0.0f;
    }
}
