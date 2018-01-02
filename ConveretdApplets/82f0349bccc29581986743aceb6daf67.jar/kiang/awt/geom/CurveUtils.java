// 
// Decompiled by Procyon v0.5.30
// 

package kiang.awt.geom;

import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

public class CurveUtils
{
    public static Point2D getPointOnQuadCurve(final QuadCurve2D curve, final double t) {
        if (curve == null) {
            throw new NullPointerException("curve must be non-null!");
        }
        if (t < 0.0 || t > 1.0) {
            throw new IllegalArgumentException("t must be between 0 and 1!");
        }
        final double ax = getQuadAx(curve);
        final double bx = getQuadBx(curve);
        final double ay = getQuadAy(curve);
        final double by = getQuadBy(curve);
        final double tSquared = t * t;
        final double x = ax * tSquared + bx * t + curve.getX1();
        final double y = ay * tSquared + by * t + curve.getY1();
        return new Point2D.Double(x, y);
    }
    
    public static int solveQuadCurveForX(final QuadCurve2D curve, final double x, final double[] solutions) {
        final double a = getQuadAx(curve);
        final double b = getQuadBx(curve);
        final double c = curve.getX1() - x;
        final double[] eqn = { c, b, a };
        final int roots = QuadCurve2D.solveQuadratic(eqn);
        return copyValidSolutions(roots, eqn, solutions);
    }
    
    public static int solveQuadCurveForY(final QuadCurve2D curve, final double y, final double[] solutions) {
        final double a = getQuadAy(curve);
        final double b = getQuadBy(curve);
        final double c = curve.getY1() - y;
        final double[] eqn = { c, b, a };
        final int roots = QuadCurve2D.solveQuadratic(eqn, solutions);
        return copyValidSolutions(roots, eqn, solutions);
    }
    
    private static double getQuadAx(final QuadCurve2D curve) {
        return curve.getX1() - 2.0 * curve.getCtrlX() + curve.getX2();
    }
    
    private static double getQuadBx(final QuadCurve2D curve) {
        return 2.0 * (-curve.getX1() + curve.getCtrlX());
    }
    
    private static double getQuadAy(final QuadCurve2D curve) {
        return curve.getY1() - 2.0 * curve.getCtrlY() + curve.getY2();
    }
    
    private static double getQuadBy(final QuadCurve2D curve) {
        return 2.0 * (-curve.getY1() + curve.getCtrlY());
    }
    
    public static Point2D getPointOnCubicCurve(final CubicCurve2D curve, final double t) {
        if (curve == null) {
            throw new NullPointerException("curve must be non-null!");
        }
        if (t < 0.0 || t > 1.0) {
            throw new IllegalArgumentException("t must be between 0 and 1!");
        }
        final double ax = getCubicAx(curve);
        final double bx = getCubicBx(curve);
        final double cx = getCubicCx(curve);
        final double ay = getCubicAy(curve);
        final double by = getCubicBy(curve);
        final double cy = getCubicCy(curve);
        final double tSquared = t * t;
        final double tCubed = t * tSquared;
        final double x = ax * tCubed + bx * tSquared + cx * t + curve.getX1();
        final double y = ay * tCubed + by * tSquared + cy * t + curve.getY1();
        return new Point2D.Double(x, y);
    }
    
    public static int solveCubicCurveForX(final CubicCurve2D curve, final double x, final double[] solutions) {
        final double a = getCubicAx(curve);
        final double b = getCubicBx(curve);
        final double c = getCubicCx(curve);
        final double d = curve.getX1() - x;
        final double[] eqn = { d, c, b, a };
        final int rootCount = CubicCurve2D.solveCubic(eqn);
        return copyValidSolutions(rootCount, eqn, solutions);
    }
    
    public static int solveCubicCurveForY(final CubicCurve2D curve, final double y, final double[] solutions) {
        final double a = getCubicAy(curve);
        final double b = getCubicBy(curve);
        final double c = getCubicCy(curve);
        final double d = curve.getY1() - y;
        final double[] eqn = { d, c, b, a };
        final int rootCount = CubicCurve2D.solveCubic(eqn);
        return copyValidSolutions(rootCount, eqn, solutions);
    }
    
    private static double getCubicAx(final CubicCurve2D curve) {
        return curve.getX2() - curve.getX1() - getCubicBx(curve) - getCubicCx(curve);
    }
    
    private static double getCubicAy(final CubicCurve2D curve) {
        return curve.getY2() - curve.getY1() - getCubicBy(curve) - getCubicCy(curve);
    }
    
    private static double getCubicBx(final CubicCurve2D curve) {
        return 3.0 * (curve.getCtrlX2() - curve.getCtrlX1()) - getCubicCx(curve);
    }
    
    private static double getCubicBy(final CubicCurve2D curve) {
        return 3.0 * (curve.getCtrlY2() - curve.getCtrlY1()) - getCubicCy(curve);
    }
    
    private static double getCubicCx(final CubicCurve2D curve) {
        return 3.0 * (curve.getCtrlX1() - curve.getX1());
    }
    
    private static double getCubicCy(final CubicCurve2D curve) {
        return 3.0 * (curve.getCtrlY1() - curve.getY1());
    }
    
    private static int copyValidSolutions(final int rootCount, final double[] roots, final double[] solutions) {
        int solutionCount = 0;
        for (int i = 0; i < rootCount; ++i) {
            if (roots[i] >= 0.0 && roots[i] <= 1.0) {
                boolean unique = true;
                for (int j = 0; j < solutionCount; ++j) {
                    if (roots[i] == roots[j]) {
                        unique = false;
                        break;
                    }
                }
                if (unique) {
                    if (solutionCount < solutions.length) {
                        solutions[solutionCount] = roots[i];
                    }
                    ++solutionCount;
                }
            }
        }
        return solutionCount;
    }
    
    public static double quadCurveLength(final QuadCurve2D curve, final double flatness) {
        if (curve == null) {
            throw new NullPointerException("curve must be non-null!");
        }
        if (flatness <= 0.0) {
            throw new IllegalArgumentException("flatness must be greater than 0!");
        }
        if (curve.getFlatness() > flatness) {
            final QuadCurve2D left = new QuadCurve2D.Double();
            final QuadCurve2D right = new QuadCurve2D.Double();
            curve.subdivide(left, right);
            return quadCurveLength(left, flatness) + quadCurveLength(right, flatness);
        }
        return Point2D.distance(curve.getX1(), curve.getY1(), curve.getX2(), curve.getY2());
    }
    
    public static double cubicCurveLength(final CubicCurve2D curve, final double flatness) {
        if (curve == null) {
            throw new NullPointerException("curve must be non-null!");
        }
        if (flatness <= 0.0) {
            throw new IllegalArgumentException("flatness must be greater than 0!");
        }
        if (curve.getFlatness() > flatness) {
            final CubicCurve2D left = new CubicCurve2D.Double();
            final CubicCurve2D right = new CubicCurve2D.Double();
            curve.subdivide(left, right);
            return cubicCurveLength(left, flatness) + cubicCurveLength(right, flatness);
        }
        return Point2D.distance(curve.getX1(), curve.getY1(), curve.getX2(), curve.getY2());
    }
}
