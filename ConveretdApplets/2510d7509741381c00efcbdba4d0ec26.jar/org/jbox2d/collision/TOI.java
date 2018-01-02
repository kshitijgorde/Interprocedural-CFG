// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.XForm;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Sweep;

public class TOI
{
    public static float timeOfImpact(final Shape shape1, final Sweep sweep1, final Shape shape2, final Sweep sweep2) {
        final float r1 = shape1.getSweepRadius();
        final float r2 = shape2.getSweepRadius();
        assert sweep1.t0 == sweep2.t0;
        assert 1.0f - sweep1.t0 > 1.1920929E-7f;
        final float t0 = sweep1.t0;
        final Vec2 v1 = sweep1.c.sub(sweep1.c0);
        final Vec2 v2 = sweep2.c.sub(sweep2.c0);
        final float omega1 = sweep1.a - sweep1.a0;
        final float omega2 = sweep2.a - sweep2.a0;
        float alpha = 0.0f;
        final Vec2 p1 = new Vec2();
        final Vec2 p2 = new Vec2();
        final int k_maxIterations = 20;
        int iter = 0;
        Vec2 normal = new Vec2(0.0f, 0.0f);
        float distance = 0.0f;
        float targetDistance = 0.0f;
        while (true) {
            final float t2 = (1.0f - alpha) * t0 + alpha;
            final XForm xf1 = new XForm();
            final XForm xf2 = new XForm();
            sweep1.getXForm(xf1, t2);
            sweep2.getXForm(xf2, t2);
            distance = Distance.distance(p1, p2, shape1, xf1, shape2, xf2);
            if (iter == 0) {
                if (distance > 0.08f) {
                    targetDistance = 0.06f;
                }
                else {
                    targetDistance = Math.max(0.002f, distance - 0.02f);
                }
            }
            if (distance - targetDistance < 0.002f) {
                break;
            }
            if (iter == 20) {
                break;
            }
            normal = p2.sub(p1);
            normal.normalize();
            final float approachVelocityBound = Vec2.dot(normal, v1.sub(v2)) + Math.abs(omega1) * r1 + Math.abs(omega2) * r2;
            if (Math.abs(approachVelocityBound) < 1.1920929E-7f) {
                alpha = 1.0f;
                break;
            }
            final float dAlpha = (distance - targetDistance) / approachVelocityBound;
            final float newAlpha = alpha + dAlpha;
            if (newAlpha < 0.0f || 1.0f < newAlpha) {
                alpha = 1.0f;
                break;
            }
            if (newAlpha < 1.0000119f * alpha) {
                break;
            }
            alpha = newAlpha;
            ++iter;
        }
        return alpha;
    }
}
