import java.util.Iterator;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolygonDiagram
{
    public static double IMPROVE_FRACTION;
    protected int diagramType;
    protected boolean improveLayout;
    protected boolean simpleVersion;
    protected boolean convex;
    protected Curve pa;
    protected Curve pb;
    protected Curve pc;
    protected double a;
    protected double b;
    protected double c;
    protected double ab;
    protected double ac;
    protected double bc;
    protected double abc;
    public static ArrayList<ArrayList<Point2D.Double>> polygons;
    
    static {
        PolygonDiagram.IMPROVE_FRACTION = 0.3;
        PolygonDiagram.polygons = new ArrayList<ArrayList<Point2D.Double>>();
    }
    
    public PolygonDiagram(final double a, final double b, final double c, final double ab, final double ac, final double bc, final double abc, final int diagramType, final boolean improveLayout, final boolean simpleVersion) throws CannotDrawDiagramException {
        this.a = a;
        this.b = b;
        this.c = c;
        this.ab = ab;
        this.ac = ac;
        this.bc = bc;
        this.abc = abc;
        this.diagramType = diagramType;
        this.improveLayout = improveLayout;
        this.simpleVersion = simpleVersion;
        this.setupDiagram();
    }
    
    protected PolygonDiagram() {
        this.pa = null;
        this.pb = null;
        this.pc = null;
    }
    
    public Curve getPA() {
        return this.pa;
    }
    
    public Curve getPB() {
        return this.pb;
    }
    
    public Curve getPC() {
        return this.pc;
    }
    
    public int getDiagramType() {
        return this.diagramType;
    }
    
    public boolean getSimpleVersion() {
        return this.simpleVersion;
    }
    
    public boolean isConvex() {
        return this.convex;
    }
    
    public void setupDiagram() throws CannotDrawDiagramException {
        if (this.diagramType == 0) {
            this.convex = this.setupDiagram0();
        }
        if (this.diagramType == 1) {
            this.convex = this.setupDiagram1();
        }
        if (this.diagramType == 2) {
            this.convex = this.setupDiagram2();
        }
        if (this.diagramType == 3) {
            this.convex = this.setupDiagram3();
        }
    }
    
    public boolean setupDiagram0() throws CannotDrawDiagramException {
        final double scale = 1.0 / this.abc;
        this.a *= scale;
        this.b *= scale;
        this.c *= scale;
        this.ab *= scale;
        this.ac *= scale;
        this.bc *= scale;
        this.abc *= scale;
        final double eqBase = Math.sqrt(this.abc * 4.0 / Math.sqrt(3.0));
        final double eqHeight = 2.0 * this.abc / eqBase;
        final Point2D.Double abEq1 = new Point2D.Double(eqBase / 2.0, 0.0);
        final Point2D.Double acEq1 = new Point2D.Double(-eqBase / 2.0, 0.0);
        final Point2D.Double bcEq1 = new Point2D.Double(0.0, -eqHeight);
        final Point2D.Double abEq2 = new Point2D.Double(abEq1.x, abEq1.y);
        final Point2D.Double acEq2 = new Point2D.Double(acEq1.x, acEq1.y);
        final Point2D.Double bcEq2 = new Point2D.Double(bcEq1.x, bcEq1.y);
        final double eqCentreX = (abEq1.x + acEq1.x + bcEq1.x) / 3.0;
        final double eqCentreY = (abEq1.y + acEq1.y + bcEq1.y) / 3.0;
        final Point2D.Double eqCentre = new Point2D.Double(eqCentreX, eqCentreY);
        final Point2D.Double abIntersect1 = new Point2D.Double(this.calculateABx(), this.calculateABy());
        final Point2D.Double acIntersect1 = new Point2D.Double(this.calculateACx(), this.calculateACy());
        final Point2D.Double bcIntersect1 = new Point2D.Double(this.calculateBCx(), this.calculateBCy());
        final Point2D.Double abIntersect2 = new Point2D.Double(abIntersect1.x, abIntersect1.y);
        final Point2D.Double acIntersect2 = new Point2D.Double(acIntersect1.x, acIntersect1.y);
        final Point2D.Double bcIntersect2 = new Point2D.Double(bcIntersect1.x, bcIntersect1.y);
        final Point2D.Double aOutside = trianglePointFurthestFrom(abIntersect1, acIntersect1, this.a, eqCentre);
        final Point2D.Double bOutside = trianglePointFurthestFrom(bcIntersect1, abIntersect1, this.b, eqCentre);
        final Point2D.Double cOutside = trianglePointFurthestFrom(acIntersect1, bcIntersect1, this.c, eqCentre);
        this.pa = new Curve(abEq1, acEq1, abIntersect1, aOutside, acIntersect1);
        this.pb = new Curve(bcEq1, abEq2, bcIntersect1, bOutside, abIntersect2);
        this.pc = new Curve(acEq2, bcEq2, acIntersect2, cOutside, bcIntersect2);
        final Point2D.Double newPA4 = this.findP4FromP1P2P3P5(this.pa, this.a);
        final Point2D.Double newPB4 = this.findP4FromP1P2P3P5(this.pb, this.b);
        final Point2D.Double newPC4 = this.findP4FromP1P2P3P5(this.pc, this.c);
        this.pa.setP4(newPA4);
        this.pb.setP4(newPB4);
        this.pc.setP4(newPC4);
        return this.testAlwaysConvex();
    }
    
    public static Point2D.Double trianglePointFurthestFrom(final Point2D.Double p1, final Point2D.Double p2, final double area, final Point2D.Double farPoint) {
        final double base = Util.distance(p1, p2);
        final double height = 2.0 * area / base;
        final Point2D.Double midPoint = Util.betweenPoints(p1, p2, 0.5);
        final double baseAngle = Util.calculateAngle(p1.x, p1.y, p2.x, p2.y);
        final double heightAngle = baseAngle + 90.0;
        final double heightAngleRad = Math.toRadians(heightAngle);
        final double x1 = midPoint.x - Math.cos(heightAngleRad) * height;
        final double x2 = midPoint.x + Math.cos(heightAngleRad) * height;
        final double y1 = midPoint.y - Math.sin(heightAngleRad) * height;
        final double y2 = midPoint.y + Math.sin(heightAngleRad) * height;
        final Point2D.Double retP1 = new Point2D.Double(x1, y1);
        final Point2D.Double retP2 = new Point2D.Double(x2, y2);
        final double distance1 = Util.distance(retP1, farPoint);
        final double distance2 = Util.distance(retP2, farPoint);
        if (distance1 > distance2) {
            return retP1;
        }
        return retP2;
    }
    
    public double calculateACx() throws CannotDrawDiagramException {
        final double A = this.ac;
        final double B = this.ab;
        final double C = this.abc;
        final double D = this.bc;
        final double X = 1.0 / Math.sqrt(Math.sqrt(3.0));
        final double forSqrt = (A + B + C + D) * (C * C * (A + B + C + D) - 4.0 * A * B * D);
        if (forSqrt < 0.0) {
            throw new CannotDrawDiagramException();
        }
        final double ret = (X * A * (4.0 * B + 3.0 * C + 2.0 * D) + C * X * (B + C + D) - X * Math.sqrt(forSqrt)) / (2.0 * C * (B + C + D));
        return ret;
    }
    
    public double calculateACy() throws CannotDrawDiagramException {
        final double A = this.ac;
        final double C = this.abc;
        final double X = 1.0 / Math.sqrt(Math.sqrt(3.0));
        final double pp = this.calculateACx();
        final double ww = A * X - pp * C;
        final double ret = (X * (A + C) + ww) / (X * X);
        return -ret;
    }
    
    public double calculateABx() throws CannotDrawDiagramException {
        final double A = this.ac;
        final double B = this.ab;
        final double C = this.abc;
        final double X = 1.0 / Math.sqrt(Math.sqrt(3.0));
        final double pp = this.calculateACx();
        final double ww = A * X - pp * C;
        final double ret = pp * X * B / ww;
        return ret;
    }
    
    public double calculateABy() throws CannotDrawDiagramException {
        final double A = this.ac;
        final double B = this.ab;
        final double C = this.abc;
        final double X = 1.0 / Math.sqrt(Math.sqrt(3.0));
        final double pp = this.calculateACx();
        final double ww = A * X - pp * C;
        final double ret = (ww * (B + C) + A * B * X) / (X * ww);
        return -ret;
    }
    
    public double calculateBCx() throws CannotDrawDiagramException {
        final double A = this.ac;
        final double B = this.ab;
        final double C = this.abc;
        final double X = 1.0 / Math.sqrt(Math.sqrt(3.0));
        final double pp = this.calculateACx();
        final double ww = A * X - pp * C;
        final double yy = -this.calculateBCy();
        final double ret = -X * (ww * (B + C - yy * X) + B * X * (A - yy * pp)) / (ww * (B + C) + A * B * X);
        return ret;
    }
    
    public double calculateBCy() throws CannotDrawDiagramException {
        final double A = this.ac;
        final double B = this.ab;
        final double C = this.abc;
        final double X = 1.0 / Math.sqrt(Math.sqrt(3.0));
        final double pp = this.calculateACx();
        final double ww = A * X - pp * C;
        final double ret = (B * A * X + ww * (B + C)) * (ww + (A + C) * X) / X / (ww * ww + ww * X * C + A * B * X * X);
        return -ret;
    }
    
    public boolean setupDiagram2() throws CannotDrawDiagramException {
        final double oldA = this.a;
        final double oldB = this.b;
        final double oldC = this.c;
        this.scaleSingleSetZonesForZeroCoefficentCheck(true);
        final boolean convex = this.setupDiagram_after_adjusting_abc(oldA, oldB, oldC);
        if (convex) {
            return true;
        }
        this.a = oldA;
        this.b = oldB;
        this.c = oldC;
        this.scaleSingleSetZonesForZeroCoefficentCheck(false);
        return this.setupDiagram_after_adjusting_abc(oldA, oldB, oldC);
    }
    
    public boolean setupDiagram_after_adjusting_abc(final double oldA, final double oldB, final double oldC) throws CannotDrawDiagramException {
        final boolean diag1_convex = this.setupDiagram1_internal();
        final boolean apos = this.a >= 0.0;
        final boolean bpos = this.b >= 0.0;
        final boolean cpos = this.c >= 0.0;
        final double areaDifferenceA = oldA - this.a;
        final double areaDifferenceB = oldB - this.b;
        final double areaDifferenceC = oldC - this.c;
        final boolean adjusting_inwards = (apos && areaDifferenceA < 0.0) || (bpos && areaDifferenceB < 0.0) || (cpos && areaDifferenceC < 0.0);
        final boolean awayFlagA = areaDifferenceA < 0.0 ^ apos;
        Point2D.Double newA = new Point2D.Double(0.0, 0.0);
        if (apos && awayFlagA) {
            newA = this.findP4FromP1P2P3P5(this.pa, Math.abs(areaDifferenceA));
        }
        else {
            newA = findTrianglePointToMakeArea(this.pa.getP3(), this.pa.getP5(), this.pb.getP1(), awayFlagA, Math.abs(areaDifferenceA));
        }
        this.pa.setP4(newA);
        final boolean awayFlagB = areaDifferenceB < 0.0 ^ bpos;
        Point2D.Double newB = new Point2D.Double(0.0, 0.0);
        if (bpos && awayFlagB) {
            newB = this.findP4FromP1P2P3P5(this.pb, Math.abs(areaDifferenceB));
        }
        else {
            newB = findTrianglePointToMakeArea(this.pb.getP3(), this.pb.getP5(), this.pc.getP1(), awayFlagB, Math.abs(areaDifferenceB));
        }
        this.pb.setP4(newB);
        final boolean awayFlagC = areaDifferenceC < 0.0 ^ cpos;
        Point2D.Double newC = new Point2D.Double(0.0, 0.0);
        if (cpos && awayFlagC) {
            newC = this.findP4FromP1P2P3P5(this.pc, Math.abs(areaDifferenceC));
        }
        else {
            newC = findTrianglePointToMakeArea(this.pc.getP3(), this.pc.getP5(), this.pa.getP1(), awayFlagC, Math.abs(areaDifferenceC));
        }
        this.pc.setP4(newC);
        return diag1_convex && !adjusting_inwards;
    }
    
    public static Point2D.Double findTrianglePointToMakeArea(final Point2D.Double b1, final Point2D.Double b2, final Point2D.Double referencePoint, final boolean awayFlag, final double area) {
        final double base = Util.distance(b1, b2);
        final double height = area * 2.0 / base;
        final Point2D.Double perpendicularPoint = Util.perpendicularPoint(referencePoint, b1, b2);
        final double perpendicularDistance = Util.distance(referencePoint, perpendicularPoint);
        double ratio = 1.0 + height / perpendicularDistance;
        if (!awayFlag) {
            ratio = 1.0 - height / perpendicularDistance;
        }
        final double base_vec_x = b2.x - b1.x;
        final double base_vec_y = b2.y - b1.y;
        final double base_len = Math.sqrt(base_vec_x * base_vec_x + base_vec_y * base_vec_y);
        if (base_len < 1.0E-5) {
            Util.debug(1, "crash alert!");
        }
        final double base_uvec_x = base_vec_x / base_len;
        final double base_uvec_y = base_vec_y / base_len;
        final double step_x = referencePoint.x - b1.x;
        final double step_y = referencePoint.y - b1.y;
        final double dot_prod = step_x * base_uvec_x + step_y * base_uvec_y;
        Point2D.Double move_from;
        if (!awayFlag && (dot_prod < 0.0 || dot_prod > base_len)) {
            move_from = Util.betweenPoints(b1, b2, 0.5);
        }
        else {
            move_from = perpendicularPoint;
        }
        final Point2D.Double ret = Util.betweenPoints(referencePoint, move_from, ratio);
        return ret;
    }
    
    double findRatio(final double a, final double b, final double c, final double ab, final double bc, final double ac, final double abc) {
        final double X = (bc * ac - c * abc) / ((abc + bc) * (abc + ac));
        final double Y = (ab * ac - a * abc) / ((abc + ab) * (abc + ac));
        final double Z = (ab * bc - b * abc) / ((abc + ab) * (abc + bc));
        Util.debug(3, "X = " + X);
        Util.debug(3, "Y = " + Y);
        Util.debug(3, "Z = " + Z);
        final double _a = 1.0 - Y;
        final double _b = X + Y - Z - 1.0;
        final double _c = Z * (1.0 - X);
        final double result = (-_b + Math.sqrt(_b * _b - 4.0 * _a * _c)) / (2.0 * _a);
        return result;
    }
    
    public boolean setupDiagram1() throws CannotDrawDiagramException {
        double coeff = this.coefficientCheck();
        boolean discarded_non_convex = false;
        if (coeff >= 0.0) {
            final boolean convex = this.setupDiagram1_internal();
            if (convex) {
                return true;
            }
            discarded_non_convex = true;
        }
        double best_coeff = Double.MAX_VALUE;
        boolean seen_convex = false;
        final double oldA = this.a;
        final double oldB = this.b;
        final double oldC = this.c;
        boolean reduce_a = false;
        boolean reduce_b = false;
        boolean reduce_c = false;
        this.a = 0.0;
        coeff = this.coefficientCheck();
        if (coeff >= 0.0) {
            best_coeff = coeff;
            reduce_a = true;
            reduce_b = false;
            reduce_c = false;
            this.convex = this.setupDiagram1_internal();
            if (this.convex) {
                seen_convex = true;
            }
        }
        this.b = 0.0;
        coeff = this.coefficientCheck();
        if (coeff >= 0.0 && coeff < best_coeff) {
            boolean is_best = true;
            if (seen_convex) {
                if (!(this.convex = this.setupDiagram1_internal())) {
                    is_best = false;
                }
            }
            else {
                seen_convex = this.setupDiagram1_internal();
            }
            if (is_best) {
                best_coeff = coeff;
                reduce_a = true;
                reduce_b = true;
                reduce_c = false;
            }
        }
        this.a = oldA;
        coeff = this.coefficientCheck();
        if (coeff >= 0.0 && coeff < best_coeff) {
            boolean is_best = true;
            if (seen_convex) {
                if (!(this.convex = this.setupDiagram1_internal())) {
                    is_best = false;
                }
            }
            else {
                seen_convex = this.setupDiagram1_internal();
            }
            if (is_best) {
                best_coeff = coeff;
                reduce_a = false;
                reduce_b = true;
                reduce_c = false;
            }
        }
        this.c = 0.0;
        coeff = this.coefficientCheck();
        if (coeff >= 0.0 && coeff < best_coeff) {
            boolean is_best = true;
            if (seen_convex) {
                if (!(this.convex = this.setupDiagram1_internal())) {
                    is_best = false;
                }
            }
            else {
                seen_convex = this.setupDiagram1_internal();
            }
            if (is_best) {
                best_coeff = coeff;
                reduce_a = false;
                reduce_b = true;
                reduce_c = true;
            }
        }
        this.a = 0.0;
        coeff = this.coefficientCheck();
        if (coeff >= 0.0 && coeff < best_coeff) {
            boolean is_best = true;
            if (seen_convex) {
                if (!(this.convex = this.setupDiagram1_internal())) {
                    is_best = false;
                }
            }
            else {
                seen_convex = this.setupDiagram1_internal();
            }
            if (is_best) {
                best_coeff = coeff;
                reduce_a = true;
                reduce_b = true;
                reduce_c = true;
            }
        }
        this.b = oldB;
        coeff = this.coefficientCheck();
        if (coeff >= 0.0 && coeff < best_coeff) {
            boolean is_best = true;
            if (seen_convex) {
                if (!(this.convex = this.setupDiagram1_internal())) {
                    is_best = false;
                }
            }
            else {
                seen_convex = this.setupDiagram1_internal();
            }
            if (is_best) {
                best_coeff = coeff;
                reduce_a = true;
                reduce_b = false;
                reduce_c = true;
            }
        }
        this.a = oldA;
        coeff = this.coefficientCheck();
        if (coeff >= 0.0 && coeff < best_coeff) {
            boolean is_best = true;
            if (seen_convex) {
                if (!(this.convex = this.setupDiagram1_internal())) {
                    is_best = false;
                }
            }
            else {
                seen_convex = this.setupDiagram1_internal();
            }
            if (is_best) {
                best_coeff = coeff;
                reduce_a = false;
                reduce_b = false;
                reduce_c = true;
            }
        }
        if (best_coeff != Double.MAX_VALUE) {
            this.a = (reduce_a ? 0.0 : oldA);
            this.b = (reduce_b ? 0.0 : oldB);
            this.c = (reduce_c ? 0.0 : oldC);
            coeff = this.coefficientCheck();
            final boolean template_convex = this.setupDiagram1_internal();
            this.convex = this.setupDiagram_after_adjusting_abc(oldA, oldB, oldC);
            return template_convex && this.convex;
        }
        if (discarded_non_convex) {
            this.a = oldA;
            this.b = oldB;
            this.c = oldC;
            return this.setupDiagram1_internal();
        }
        throw new CannotDrawDiagramException();
    }
    
    public boolean setupDiagram1_internal() throws CannotDrawDiagramException {
        if (this.coefficientCheck() < 0.0) {
            throw new CannotDrawDiagramException();
        }
        final double sqrt3 = Math.sqrt(3.0);
        final double alpha = this.findRatio(this.a, this.b, this.c, this.ab, this.bc, this.ac, this.abc);
        final double beta = this.findRatio(this.b, this.c, this.a, this.bc, this.ac, this.ab, this.abc);
        final double gamma = this.findRatio(this.c, this.a, this.b, this.ac, this.ab, this.bc, this.abc);
        Util.debug(2, "alpha = " + alpha);
        Util.debug(2, "beta = " + beta);
        Util.debug(2, "gamma = " + gamma);
        final double D43 = -Math.sqrt(this.abc / sqrt3);
        final double D44 = 0.0;
        final double F43 = -D43;
        final double F44 = 0.0;
        final double lambda = F43;
        final double H43 = 0.0;
        final double H44 = -F43 * sqrt3;
        final Point2D.Double a1 = new Point2D.Double(F43, F44);
        final Point2D.Double a2 = new Point2D.Double(D43, D44);
        final Point2D.Double b1 = new Point2D.Double(H43, H44);
        final Point2D.Double b2 = new Point2D.Double(F43, F44);
        final Point2D.Double c1 = new Point2D.Double(D43, D44);
        final Point2D.Double c2 = new Point2D.Double(H43, H44);
        final double ABx = gamma * (-lambda - 2.0 * this.ab / (sqrt3 * lambda)) - (1.0 - gamma) * this.ab / (sqrt3 * lambda);
        final double ABy = -(1.0 - gamma) * (sqrt3 * lambda + this.ab / lambda);
        final double ACx = (1.0 - beta) * (lambda + 2.0 * this.ac / (sqrt3 * lambda)) + beta * this.ac / (sqrt3 * lambda);
        final double ACy = -beta * (sqrt3 * lambda + this.ac / lambda);
        final double BCx = (2.0 * alpha - 1.0) * (lambda + this.bc / (sqrt3 * lambda));
        final double BCy = this.bc / lambda;
        final Point2D.Double a3 = new Point2D.Double(ABx, ABy);
        final Point2D.Double a4 = new Point2D.Double(ACx, ACy);
        final Point2D.Double a5 = Util.betweenPoints(a3, a4, 0.5);
        final Point2D.Double b3 = new Point2D.Double(BCx, BCy);
        final Point2D.Double b4 = new Point2D.Double(ABx, ABy);
        final Point2D.Double b5 = Util.betweenPoints(b3, b4, 0.5);
        final Point2D.Double c3 = new Point2D.Double(ACx, ACy);
        final Point2D.Double c4 = new Point2D.Double(BCx, BCy);
        final Point2D.Double c5 = Util.betweenPoints(c3, c4, 0.5);
        this.pa = new Curve(a1, a2, a3, a5, a4);
        this.pb = new Curve(b1, b2, b3, b5, b4);
        this.pc = new Curve(c1, c2, c3, c5, c4);
        if (alpha < 1.0E-5 || beta < 1.0E-5 || gamma < 1.0E-5 || alpha > 1.00001 || beta > 1.00001 || gamma > 1.00001) {
            Util.debug(2, "diagram of type 1 is not convex");
            return false;
        }
        Util.debug(2, "diagram of type 1 is convex");
        return true;
    }
    
    public double coefficientCheck() {
        final double X = (this.bc * this.ac - this.c * this.abc) / ((this.abc + this.bc) * (this.abc + this.ac));
        final double Y = (this.ab * this.ac - this.a * this.abc) / ((this.abc + this.ab) * (this.abc + this.ac));
        final double Z = (this.ab * this.bc - this.b * this.abc) / ((this.abc + this.ab) * (this.abc + this.bc));
        final double coefficient = (1.0 - X - Y - Z) * (1.0 - X - Y - Z) - 4.0 * X * Y * Z;
        Util.debug(2, "coefficient is " + coefficient);
        return coefficient;
    }
    
    protected boolean scaleSingleSetZonesForZeroCoefficentCheck(final boolean allow_neg_areas) {
        final double oldA = this.a;
        final double oldB = this.b;
        final double oldC = this.c;
        final double minA = allow_neg_areas ? (-this.abc - this.ab - this.ac) : 0.0;
        final double minB = allow_neg_areas ? (-this.abc - this.ab - this.bc) : 0.0;
        final double minC = allow_neg_areas ? (-this.abc - this.ac - this.bc) : 0.0;
        double lastLower = 0.0;
        double lastHigher = 2.0;
        double coefficient;
        for (coefficient = this.coefficientCheck(); coefficient < 0.0; coefficient = this.coefficientCheck()) {
            lastHigher *= lastHigher;
            this.a = oldA * lastHigher;
            this.b = oldB * lastHigher;
            this.c = oldC * lastHigher;
        }
        while ((Math.abs(coefficient) > 1.0E-5 || coefficient < 0.0) && Math.abs(lastHigher - lastLower) >= 1.0E-8) {
            final double nextTry = lastLower + (lastHigher - lastLower) / 2.0;
            this.a = oldA * nextTry + minA * (1.0 - nextTry);
            this.b = oldB * nextTry + minB * (1.0 - nextTry);
            this.c = oldC * nextTry + minC * (1.0 - nextTry);
            Util.debug(2, "next a, b, c  " + this.a + ", " + this.b + ", " + this.c);
            coefficient = this.coefficientCheck();
            if (coefficient < 0.0) {
                lastLower = nextTry;
            }
            else {
                lastHigher = nextTry;
            }
            if (nextTry < 1.0E-5) {
                Util.debug(2, "stop with a, b, c " + this.a + ", " + this.b + ", " + this.c);
                return false;
            }
        }
        Util.debug(2, "found reduced a, b, c " + this.a + ", " + this.b + ", " + this.c);
        return true;
    }
    
    public boolean setupDiagram3() throws CannotDrawDiagramException {
        final double oldA = this.a;
        final double oldB = this.b;
        final double oldC = this.c;
        final double oldAB = this.ab;
        final double oldAC = this.ac;
        final double oldBC = this.bc;
        final double oldABC = this.abc;
        this.scaleSingleSetZonesForZeroCoefficentCheck(false);
        this.setupDiagram_after_adjusting_abc(oldA, oldB, oldC);
        final double angleA = Math.toDegrees(Util.angle(this.pc.getP3(), this.pc.getP2(), this.pa.getP3()));
        final double angleB = Math.toDegrees(Util.angle(this.pa.getP3(), this.pa.getP2(), this.pb.getP3()));
        final double angleC = Math.toDegrees(Util.angle(this.pb.getP3(), this.pb.getP2(), this.pc.getP3()));
        if (angleA <= 60.0 || angleB <= 60.0 || angleC <= 60.0) {
            this.a = 4.0 * this.abc;
            this.b = 4.0 * this.abc;
            this.c = 4.0 * this.abc;
            this.ab = 2.0 * this.abc;
            this.ac = 2.0 * this.abc;
            this.bc = 2.0 * this.abc;
            this.setupDiagram2();
        }
        this.a = oldA;
        this.b = oldB;
        this.c = oldC;
        this.ab = oldAB;
        this.ac = oldAC;
        this.bc = oldBC;
        this.abc = oldABC;
        final Point2D.Double a1Extend = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pc.getP3(), this.pc.getP5());
        this.pa.setP1(a1Extend);
        final Point2D.Double a2Extend = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP3(), this.pb.getP5());
        this.pa.setP2(a2Extend);
        final Point2D.Double b1Extend = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pa.getP3(), this.pa.getP5());
        this.pb.setP1(b1Extend);
        final Point2D.Double b2Extend = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pc.getP3(), this.pc.getP5());
        this.pb.setP2(b2Extend);
        final Point2D.Double c1Extend = Util.intersectionPointOfTwoLines(this.pc.getP1(), this.pc.getP2(), this.pb.getP3(), this.pb.getP5());
        this.pc.setP1(c1Extend);
        final Point2D.Double c2Extend = Util.intersectionPointOfTwoLines(this.pc.getP1(), this.pc.getP2(), this.pa.getP3(), this.pa.getP5());
        this.pc.setP2(c2Extend);
        this.scaleTwoSetAreas();
        this.searchForExactTwoSetAreas(true);
        if (this.twoSetAreaQuality() > 1.0E-5) {
            this.searchForExactTwoSetAreas(false);
        }
        final boolean convex = this.placeOneSetPoints();
        if (!convex || this.crossingLineSegments().size() > 0 || this.twoSetAreaQuality() > 1.0E-5) {
            System.out.println("Failed to draw Type 3. Convex: " + convex + ", crossing segment count: " + this.crossingLineSegments().size() + ", two set area quality: " + this.twoSetAreaQuality());
            return false;
        }
        return true;
    }
    
    private boolean placeOneSetPoints() {
        boolean convex = true;
        final Point2D.Double abMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP1(), this.pb.getP2());
        final Point2D.Double acMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double bcMiddle = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double[] cutOutA = { bcMiddle, this.pb.getP1(), this.pc.getP2() };
        final Point2D.Double bottomOfAHeight = Util.perpendicularPoint(bcMiddle, this.pb.getP1(), this.pc.getP2());
        final double heightCutOutA = Util.distance(bcMiddle, bottomOfAHeight);
        final double baseCutOutA = Util.distance(cutOutA[1], cutOutA[2]);
        final double areaCutOutA = 0.5 * heightCutOutA * baseCutOutA;
        final Point2D.Double[] cutOutB = { acMiddle, this.pc.getP1(), this.pa.getP2() };
        final Point2D.Double bottomOfBHeight = Util.perpendicularPoint(acMiddle, this.pc.getP1(), this.pa.getP2());
        final double heightCutOutB = Util.distance(acMiddle, bottomOfBHeight);
        final double baseCutOutB = Util.distance(cutOutB[1], cutOutB[2]);
        final double areaCutOutB = 0.5 * heightCutOutB * baseCutOutB;
        final Point2D.Double[] cutOutC = { abMiddle, this.pa.getP1(), this.pb.getP2() };
        final Point2D.Double bottomOfCHeight = Util.perpendicularPoint(abMiddle, this.pa.getP1(), this.pb.getP2());
        final double heightCutOutC = Util.distance(abMiddle, bottomOfCHeight);
        final double baseCutOutC = Util.distance(cutOutC[1], cutOutC[2]);
        final double areaCutOutC = 0.5 * heightCutOutC * baseCutOutC;
        final double remainingAreaA = this.a - areaCutOutA;
        final double remainingAreaB = this.b - areaCutOutB;
        final double remainingAreaC = this.c - areaCutOutC;
        if (remainingAreaA < 0.0 || remainingAreaB < 0.0 || remainingAreaC < 0.0) {
            convex = false;
            this.improveLayout = false;
            System.out.println("A +ve for convex: " + remainingAreaA + ", B +ve for convex: " + remainingAreaB + ", C +ve for convex: " + remainingAreaC);
        }
        final double baseA = Util.distance(this.pa.getP3(), this.pa.getP5());
        final double baseB = Util.distance(this.pb.getP3(), this.pb.getP5());
        final double baseC = Util.distance(this.pc.getP3(), this.pc.getP5());
        final double requiredHeightA = 2.0 * remainingAreaA / baseA;
        final double requiredHeightB = 2.0 * remainingAreaB / baseB;
        final double requiredHeightC = 2.0 * remainingAreaC / baseC;
        Point2D.Double newPA4 = this.findP4FromP1P2P3P5(this.pa, remainingAreaA);
        Point2D.Double newPB4 = this.findP4FromP1P2P3P5(this.pb, remainingAreaB);
        Point2D.Double newPC4 = this.findP4FromP1P2P3P5(this.pc, remainingAreaC);
        if (remainingAreaA < 0.0) {
            this.pa.setP3Point5(new Point2D.Double(this.pb.getP1().x, this.pb.getP1().y));
            this.pa.setP4Point5(new Point2D.Double(this.pc.getP2().x, this.pc.getP2().y));
            newPA4 = findTrianglePointToMakeArea(this.pa.getP3Point5(), this.pa.getP4Point5(), bcMiddle, false, -remainingAreaA);
        }
        if (remainingAreaB < 0.0) {
            this.pb.setP3Point5(new Point2D.Double(this.pc.getP1().x, this.pc.getP1().y));
            this.pb.setP4Point5(new Point2D.Double(this.pa.getP2().x, this.pa.getP2().y));
            newPB4 = findTrianglePointToMakeArea(this.pb.getP3Point5(), this.pb.getP4Point5(), acMiddle, false, -remainingAreaB);
        }
        if (remainingAreaC < 0.0) {
            this.pc.setP3Point5(new Point2D.Double(this.pa.getP1().x, this.pa.getP1().y));
            this.pc.setP4Point5(new Point2D.Double(this.pb.getP2().x, this.pb.getP2().y));
            newPC4 = findTrianglePointToMakeArea(this.pc.getP3Point5(), this.pc.getP4Point5(), abMiddle, false, -remainingAreaC);
        }
        this.pa.setP4(newPA4);
        this.pb.setP4(newPB4);
        this.pc.setP4(newPC4);
        final Point2D.Double oldPA3 = new Point2D.Double(this.pa.getP3().x, this.pa.getP3().y);
        final Point2D.Double oldPB3 = new Point2D.Double(this.pb.getP3().x, this.pb.getP3().y);
        final Point2D.Double oldPC3 = new Point2D.Double(this.pc.getP3().x, this.pc.getP3().y);
        final Point2D.Double oldPA4 = new Point2D.Double(this.pa.getP4().x, this.pa.getP4().y);
        final Point2D.Double oldPB4 = new Point2D.Double(this.pb.getP4().x, this.pb.getP4().y);
        final Point2D.Double oldPC4 = new Point2D.Double(this.pc.getP4().x, this.pc.getP4().y);
        final Point2D.Double oldPA5 = new Point2D.Double(this.pa.getP5().x, this.pa.getP5().y);
        final Point2D.Double oldPB5 = new Point2D.Double(this.pb.getP5().x, this.pb.getP5().y);
        final Point2D.Double oldPC5 = new Point2D.Double(this.pc.getP5().x, this.pc.getP5().y);
        if (this.improveLayout) {
            boolean loop = true;
            double improveFraction = PolygonDiagram.IMPROVE_FRACTION;
            while (loop) {
                this.pa.setP3(new Point2D.Double(oldPA3.x, oldPA3.y));
                this.pa.setP4(new Point2D.Double(oldPA4.x, oldPA4.y));
                this.pa.setP5(new Point2D.Double(oldPA5.x, oldPA5.y));
                final boolean convexImprovement = this.improveOutsidePointsType3(this.pa, requiredHeightA, improveFraction);
                if (convexImprovement) {
                    loop = false;
                }
                else if (improveFraction < 1.0E-5) {
                    loop = false;
                    this.pa.setP3(new Point2D.Double(oldPA3.x, oldPA3.y));
                    this.pa.setP4(new Point2D.Double(oldPA4.x, oldPA4.y));
                    this.pa.setP5(new Point2D.Double(oldPA5.x, oldPA5.y));
                }
                else {
                    improveFraction /= 2.0;
                }
            }
            loop = true;
            improveFraction = PolygonDiagram.IMPROVE_FRACTION;
            while (loop) {
                this.pb.setP3(new Point2D.Double(oldPB3.x, oldPB3.y));
                this.pb.setP4(new Point2D.Double(oldPB4.x, oldPB4.y));
                this.pb.setP5(new Point2D.Double(oldPB5.x, oldPB5.y));
                final boolean convexImprovement = this.improveOutsidePointsType3(this.pb, requiredHeightB, improveFraction);
                if (convexImprovement) {
                    loop = false;
                }
                else if (improveFraction < 1.0E-5) {
                    loop = false;
                    this.pb.setP3(new Point2D.Double(oldPB3.x, oldPB3.y));
                    this.pb.setP4(new Point2D.Double(oldPB4.x, oldPB4.y));
                    this.pb.setP5(new Point2D.Double(oldPB5.x, oldPB5.y));
                }
                else {
                    improveFraction /= 2.0;
                }
            }
            loop = true;
            improveFraction = PolygonDiagram.IMPROVE_FRACTION;
            while (loop) {
                this.pc.setP3(new Point2D.Double(oldPC3.x, oldPC3.y));
                this.pc.setP4(new Point2D.Double(oldPC4.x, oldPC4.y));
                this.pc.setP5(new Point2D.Double(oldPC5.x, oldPC5.y));
                final boolean convexImprovement = this.improveOutsidePointsType3(this.pc, requiredHeightC, improveFraction);
                if (convexImprovement) {
                    loop = false;
                }
                else if (improveFraction < 1.0E-5) {
                    loop = false;
                    this.pc.setP3(new Point2D.Double(oldPC3.x, oldPC3.y));
                    this.pc.setP4(new Point2D.Double(oldPC4.x, oldPC4.y));
                    this.pc.setP5(new Point2D.Double(oldPC5.x, oldPC5.y));
                }
                else {
                    improveFraction /= 2.0;
                }
            }
        }
        return convex;
    }
    
    protected boolean improveOutsidePointsType3(final Curve curve, final double currentHeight, final double fraction) {
        final double moveP3Distance = currentHeight * fraction;
        final double moveLineP2P3Fraction = moveP3Distance / Util.distance(curve.getP2(), curve.getP3());
        final Point2D.Double newP3 = Util.betweenPoints(curve.getP3(), curve.getP2(), -moveLineP2P3Fraction);
        final double moveP5Distance = currentHeight * fraction;
        final double moveLineP5P1Fraction = moveP5Distance / Util.distance(curve.getP5(), curve.getP1());
        final Point2D.Double newP4 = Util.betweenPoints(curve.getP5(), curve.getP1(), -moveLineP5P1Fraction);
        final double addedAreaOfP3 = Util.computeTriangleArea(curve.getP3(), newP3, curve.getP4());
        final double addedAreaOfP4 = Util.computeTriangleArea(curve.getP5(), newP4, curve.getP4());
        final double baseTop = Util.distance(newP3, newP4);
        final Point2D.Double perpendicularPoint = Util.perpendicularPoint(curve.getP4(), newP3, newP4);
        final double heightTop = Util.distance(perpendicularPoint, curve.getP4());
        final double currentAreaTop = 0.5 * heightTop * baseTop;
        final double reductionTop = addedAreaOfP3 + addedAreaOfP4;
        final double reductionFractionTop = reductionTop / currentAreaTop;
        final Point2D.Double newP5 = Util.betweenPoints(curve.getP4(), perpendicularPoint, reductionFractionTop);
        curve.setP3(newP3);
        curve.setP4(newP5);
        curve.setP5(newP4);
        return !Util.isConcave(curve.getPolygon());
    }
    
    protected Point2D.Double findP4FromP1P2P3P5(final Curve curve, final double area) {
        final double base = Util.distance(curve.getP3(), curve.getP5());
        final double requiredHeight = 2.0 * area / base;
        final Point2D.Double middle = Util.midPoint(curve.getP3(), curve.getP5());
        final double angleP2P3 = Util.lineAngle(curve.getP2(), curve.getP3());
        final double angleP1P5 = Util.lineAngle(curve.getP1(), curve.getP5());
        final double angleOut = (angleP2P3 + angleP1P5) / 2.0;
        final double xPointOnIntersectLine = middle.x + Math.cos(angleOut);
        final double yPointOnIntersectLine = middle.y - Math.sin(angleOut);
        final Point2D.Double pointOnIntersectLine = new Point2D.Double(xPointOnIntersectLine, yPointOnIntersectLine);
        final Line2D.Double baseLine = new Line2D.Double(curve.getP5(), curve.getP3());
        final Line2D.Double heightLine = Util.parallelLine(baseLine, requiredHeight);
        final Point2D.Double heightLineP1 = new Point2D.Double(heightLine.x1, heightLine.y1);
        final Point2D.Double heightLineP2 = new Point2D.Double(heightLine.x2, heightLine.y2);
        final Point2D.Double p4 = Util.intersectionPointOfTwoLines(heightLineP1, heightLineP2, middle, pointOnIntersectLine);
        if (area < 0.0) {
            Util.intersectionPointOfTwoLines(heightLineP2, heightLineP1, middle, pointOnIntersectLine);
        }
        return p4;
    }
    
    protected void searchForExactTwoSetAreas(final boolean forceConvex) {
        for (double move = 0.1; move > 1.0E-5; move /= 2.0) {
            final boolean improvement = this.moveBestCoreCornerPoint(move, forceConvex);
            if (!improvement) {}
        }
    }
    
    protected boolean moveBestCoreCornerPoint(final double move, final boolean forceConvex) {
        boolean improvement = false;
        Point2D.Double bestPoint1 = null;
        Point2D.Double bestPoint2 = null;
        double bestX = 0.0;
        double bestY = 0.0;
        final double bestQuality = this.twoSetAreaQuality();
        Point2D.Double point1 = this.pa.getP3();
        Point2D.Double point2 = this.pb.getP5();
        double oldX = point1.x;
        double oldY = point1.y;
        double newX = oldX + move;
        double newY = oldY;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        double newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX + move;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX + move;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        point1.x = oldX;
        point1.y = oldY;
        point2.x = oldX;
        point2.y = oldY;
        this.resetPointsOneAndTwo();
        point1 = this.pc.getP3();
        point2 = this.pa.getP5();
        oldX = point1.x;
        oldY = point1.y;
        newX = oldX + move;
        newY = oldY;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX + move;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX + move;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        point1.x = oldX;
        point1.y = oldY;
        point2.x = oldX;
        point2.y = oldY;
        this.resetPointsOneAndTwo();
        point1 = this.pb.getP3();
        point2 = this.pc.getP5();
        oldX = point1.x;
        oldY = point1.y;
        newX = oldX + move;
        newY = oldY;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX + move;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX - move;
        newY = oldY + move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        newX = oldX + move;
        newY = oldY - move;
        point1.x = newX;
        point1.y = newY;
        point2.x = newX;
        point2.y = newY;
        this.resetPointsOneAndTwo();
        newQuality = this.twoSetAreaQuality();
        if (newQuality < bestQuality && this.notOneSetCrossingLineSegments().size() == 0 && this.convexType3(forceConvex)) {
            improvement = true;
            bestPoint1 = point1;
            bestPoint2 = point2;
            bestX = newX;
            bestY = newY;
        }
        point1.x = oldX;
        point1.y = oldY;
        point2.x = oldX;
        point2.y = oldY;
        this.resetPointsOneAndTwo();
        if (improvement) {
            bestPoint1.x = bestX;
            bestPoint1.y = bestY;
            bestPoint2.x = bestX;
            bestPoint2.y = bestY;
            this.resetPointsOneAndTwo();
        }
        return improvement;
    }
    
    protected boolean convexType3(final boolean performTest) {
        if (!performTest) {
            return true;
        }
        final Point2D.Double abMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP1(), this.pb.getP2());
        final Point2D.Double acMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double bcMiddle = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double[] cutOutA = { bcMiddle, this.pb.getP1(), this.pc.getP2() };
        final double areaCutOutA = Util.computePolygonArea(cutOutA);
        final Point2D.Double[] cutOutB = { acMiddle, this.pc.getP1(), this.pa.getP2() };
        final double areaCutOutB = Util.computePolygonArea(cutOutB);
        final Point2D.Double[] cutOutC = { abMiddle, this.pa.getP1(), this.pb.getP2() };
        final double areaCutOutC = Util.computePolygonArea(cutOutC);
        return areaCutOutA <= this.a && areaCutOutB <= this.b && areaCutOutC <= this.c;
    }
    
    ArrayList<Line2D.Double[]> crossingLineSegments() {
        final ArrayList<Line2D.Double[]> ret = new ArrayList<Line2D.Double[]>();
        final ArrayList<Line2D.Double> lines = this.findLineSegments();
        for (int i1 = 0; i1 < lines.size(); ++i1) {
            for (int i2 = i1 + 1; i2 < lines.size(); ++i2) {
                final Line2D.Double line1 = lines.get(i1);
                final Line2D.Double line2 = lines.get(i2);
                double x = line1.getX1();
                double y = line1.getY1();
                final Point2D.Double p1 = new Point2D.Double(x, y);
                x = line1.getX2();
                y = line1.getY2();
                final Point2D.Double p2 = new Point2D.Double(x, y);
                x = line2.getX1();
                y = line2.getY1();
                final Point2D.Double q1 = new Point2D.Double(x, y);
                x = line2.getX2();
                y = line2.getY2();
                final Point2D.Double q2 = new Point2D.Double(x, y);
                if (Math.abs(p1.x - q1.x) >= 1.0E-5 || Math.abs(p1.y - q1.y) >= 1.0E-5) {
                    if (Math.abs(p1.x - q2.x) >= 1.0E-5 || Math.abs(p1.y - q2.y) >= 1.0E-5) {
                        if (Math.abs(p2.x - q1.x) >= 1.0E-5 || Math.abs(p2.y - q1.y) >= 1.0E-5) {
                            if (Math.abs(p2.x - q2.x) >= 1.0E-5 || Math.abs(p2.y - q2.y) >= 1.0E-5) {
                                if (Util.linesCross(p1, p2, q1, q2)) {
                                    final Line2D.Double[] pair = { line1, line2 };
                                    ret.add(pair);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    
    ArrayList<Line2D.Double[]> notOneSetCrossingLineSegments() {
        final ArrayList<Line2D.Double[]> ret = new ArrayList<Line2D.Double[]>();
        final ArrayList<Line2D.Double> lines = this.notOneSetFindLineSegments();
        for (int i1 = 0; i1 < lines.size(); ++i1) {
            for (int i2 = i1 + 1; i2 < lines.size(); ++i2) {
                final Line2D.Double line1 = lines.get(i1);
                final Line2D.Double line2 = lines.get(i2);
                double x = line1.getX1();
                double y = line1.getY1();
                final Point2D.Double p1 = new Point2D.Double(x, y);
                x = line1.getX2();
                y = line1.getY2();
                final Point2D.Double p2 = new Point2D.Double(x, y);
                x = line2.getX1();
                y = line2.getY1();
                final Point2D.Double q1 = new Point2D.Double(x, y);
                x = line2.getX2();
                y = line2.getY2();
                final Point2D.Double q2 = new Point2D.Double(x, y);
                if (Math.abs(p1.x - q1.x) >= 1.0E-5 || Math.abs(p1.y - q1.y) >= 1.0E-5) {
                    if (Math.abs(p1.x - q2.x) >= 1.0E-5 || Math.abs(p1.y - q2.y) >= 1.0E-5) {
                        if (Math.abs(p2.x - q1.x) >= 1.0E-5 || Math.abs(p2.y - q1.y) >= 1.0E-5) {
                            if (Math.abs(p2.x - q2.x) >= 1.0E-5 || Math.abs(p2.y - q2.y) >= 1.0E-5) {
                                if (Util.linesCross(p1, p2, q1, q2)) {
                                    final Line2D.Double[] pair = { line1, line2 };
                                    ret.add(pair);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    
    ArrayList<Line2D.Double> findLineSegments() {
        final ArrayList<Line2D.Double> ret = new ArrayList<Line2D.Double>();
        final Point2D.Double abMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP1(), this.pb.getP2());
        final Point2D.Double acMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double bcMiddle = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double abIntersect = Util.intersectionPointOfTwoLines(this.pa.getP2(), this.pa.getP3(), this.pb.getP5(), this.pb.getP1());
        final Point2D.Double acIntersect = Util.intersectionPointOfTwoLines(this.pc.getP2(), this.pc.getP3(), this.pa.getP5(), this.pa.getP1());
        final Point2D.Double bcIntersect = Util.intersectionPointOfTwoLines(this.pb.getP2(), this.pb.getP3(), this.pc.getP5(), this.pc.getP1());
        ret.add(new Line2D.Double(this.pa.getP1(), abMiddle));
        ret.add(new Line2D.Double(abMiddle, acMiddle));
        ret.add(new Line2D.Double(acMiddle, this.pa.getP2()));
        if (this.improveLayout) {
            ret.add(new Line2D.Double(this.pa.getP2(), abIntersect));
            ret.add(new Line2D.Double(abIntersect, this.pa.getP3()));
        }
        else {
            ret.add(new Line2D.Double(this.pa.getP2(), this.pa.getP3()));
        }
        if (this.pa.getP3Point5() != null) {
            ret.add(new Line2D.Double(this.pa.getP3(), this.pa.getP3Point5()));
            ret.add(new Line2D.Double(this.pa.getP3Point5(), this.pa.getP4()));
        }
        else {
            ret.add(new Line2D.Double(this.pa.getP3(), this.pa.getP4()));
        }
        if (this.pa.getP4Point5() != null) {
            ret.add(new Line2D.Double(this.pa.getP4(), this.pa.getP4Point5()));
            ret.add(new Line2D.Double(this.pa.getP4Point5(), this.pa.getP5()));
        }
        else {
            ret.add(new Line2D.Double(this.pa.getP4(), this.pa.getP5()));
        }
        if (this.improveLayout) {
            ret.add(new Line2D.Double(this.pa.getP5(), acIntersect));
            ret.add(new Line2D.Double(acIntersect, this.pa.getP1()));
        }
        else {
            ret.add(new Line2D.Double(this.pa.getP5(), this.pa.getP1()));
        }
        ret.add(new Line2D.Double(this.pb.getP1(), bcMiddle));
        ret.add(new Line2D.Double(bcMiddle, abMiddle));
        ret.add(new Line2D.Double(abMiddle, this.pb.getP2()));
        if (this.improveLayout) {
            ret.add(new Line2D.Double(this.pb.getP2(), bcIntersect));
            ret.add(new Line2D.Double(bcIntersect, this.pb.getP3()));
        }
        else {
            ret.add(new Line2D.Double(this.pb.getP2(), this.pb.getP3()));
        }
        if (this.pb.getP3Point5() != null) {
            ret.add(new Line2D.Double(this.pb.getP3(), this.pb.getP3Point5()));
            ret.add(new Line2D.Double(this.pb.getP3Point5(), this.pb.getP4()));
        }
        else {
            ret.add(new Line2D.Double(this.pb.getP3(), this.pb.getP4()));
        }
        if (this.pb.getP4Point5() != null) {
            ret.add(new Line2D.Double(this.pb.getP4(), this.pb.getP4Point5()));
            ret.add(new Line2D.Double(this.pb.getP4Point5(), this.pb.getP5()));
        }
        else {
            ret.add(new Line2D.Double(this.pb.getP4(), this.pb.getP5()));
        }
        if (this.improveLayout) {
            ret.add(new Line2D.Double(this.pb.getP5(), abIntersect));
            ret.add(new Line2D.Double(abIntersect, this.pb.getP1()));
        }
        else {
            ret.add(new Line2D.Double(this.pb.getP5(), this.pb.getP1()));
        }
        ret.add(new Line2D.Double(this.pc.getP1(), acMiddle));
        ret.add(new Line2D.Double(acMiddle, bcMiddle));
        ret.add(new Line2D.Double(bcMiddle, this.pc.getP2()));
        if (this.improveLayout) {
            ret.add(new Line2D.Double(this.pc.getP2(), acIntersect));
            ret.add(new Line2D.Double(acIntersect, this.pc.getP3()));
        }
        else {
            ret.add(new Line2D.Double(this.pc.getP2(), this.pc.getP3()));
        }
        if (this.pc.getP3Point5() != null) {
            ret.add(new Line2D.Double(this.pc.getP3(), this.pc.getP3Point5()));
            ret.add(new Line2D.Double(this.pc.getP3Point5(), this.pc.getP4()));
        }
        else {
            ret.add(new Line2D.Double(this.pc.getP3(), this.pc.getP4()));
        }
        if (this.pc.getP4Point5() != null) {
            ret.add(new Line2D.Double(this.pc.getP4(), this.pc.getP4Point5()));
            ret.add(new Line2D.Double(this.pc.getP4Point5(), this.pc.getP5()));
        }
        else {
            ret.add(new Line2D.Double(this.pc.getP4(), this.pc.getP5()));
        }
        if (this.improveLayout) {
            ret.add(new Line2D.Double(this.pc.getP5(), bcIntersect));
            ret.add(new Line2D.Double(bcIntersect, this.pc.getP1()));
        }
        else {
            ret.add(new Line2D.Double(this.pc.getP5(), this.pc.getP1()));
        }
        return ret;
    }
    
    ArrayList<Line2D.Double> notOneSetFindLineSegments() {
        final ArrayList<Line2D.Double> ret = new ArrayList<Line2D.Double>();
        final Point2D.Double abMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP1(), this.pb.getP2());
        final Point2D.Double acMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double bcMiddle = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pc.getP1(), this.pc.getP2());
        ret.add(new Line2D.Double(this.pa.getP1(), abMiddle));
        ret.add(new Line2D.Double(abMiddle, acMiddle));
        ret.add(new Line2D.Double(acMiddle, this.pa.getP2()));
        ret.add(new Line2D.Double(this.pa.getP2(), this.pa.getP3()));
        ret.add(new Line2D.Double(this.pa.getP5(), this.pa.getP1()));
        ret.add(new Line2D.Double(this.pb.getP1(), bcMiddle));
        ret.add(new Line2D.Double(bcMiddle, abMiddle));
        ret.add(new Line2D.Double(abMiddle, this.pb.getP2()));
        ret.add(new Line2D.Double(this.pb.getP2(), this.pb.getP3()));
        ret.add(new Line2D.Double(this.pb.getP5(), this.pb.getP1()));
        ret.add(new Line2D.Double(this.pc.getP1(), acMiddle));
        ret.add(new Line2D.Double(acMiddle, bcMiddle));
        ret.add(new Line2D.Double(bcMiddle, this.pc.getP2()));
        ret.add(new Line2D.Double(this.pc.getP2(), this.pc.getP3()));
        ret.add(new Line2D.Double(this.pc.getP5(), this.pc.getP1()));
        return ret;
    }
    
    protected void scaleTwoSetAreas() {
        final PolygonDiagram startingDiagram = this.clone();
        double bestFraction = 0.27;
        double bestQuality = Double.MAX_VALUE;
        for (double delta = 0.1; delta > 1.0E-5; delta /= 2.0) {
            boolean improvement = false;
            this.pa = startingDiagram.pa.clone();
            this.pb = startingDiagram.pb.clone();
            this.pc = startingDiagram.pc.clone();
            double newFraction = bestFraction + delta;
            this.moveCoreBorderInByFractionOfCutOut(newFraction);
            double newQuality = this.twoSetAreaQuality();
            if (newQuality < bestQuality) {
                bestQuality = newQuality;
                bestFraction = newFraction;
                improvement = true;
            }
            this.pa = startingDiagram.pa.clone();
            this.pb = startingDiagram.pb.clone();
            this.pc = startingDiagram.pc.clone();
            newFraction = bestFraction - delta;
            this.moveCoreBorderInByFractionOfCutOut(newFraction);
            newQuality = this.twoSetAreaQuality();
            if (newQuality < bestQuality) {
                bestQuality = newQuality;
                bestFraction = newFraction;
                improvement = true;
            }
            if (!improvement) {}
        }
        this.pa = startingDiagram.pa.clone();
        this.pb = startingDiagram.pb.clone();
        this.pc = startingDiagram.pc.clone();
        this.moveCoreBorderInByFractionOfCutOut(bestFraction);
    }
    
    public double twoSetAreaQuality() {
        final Point2D.Double[][] polyZones = this.findZonePolygonArrays();
        final double areaAB = Util.computePolygonArea(polyZones[3]);
        final double areaAC = Util.computePolygonArea(polyZones[5]);
        final double areaBC = Util.computePolygonArea(polyZones[6]);
        final double diffAB = areaAB - this.ab;
        final double diffAC = areaAC - this.ac;
        final double diffBC = areaBC - this.bc;
        final double quality = diffAB * diffAB + diffAC * diffAC + diffBC * diffBC;
        return quality;
    }
    
    public void moveCoreBorderInByFractionOfCutOut(final double fraction) {
        final Point2D.Double abMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP1(), this.pb.getP2());
        final Point2D.Double acMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double bcMiddle = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double[] cutOutA = { bcMiddle, this.pb.getP1(), this.pc.getP2() };
        final Point2D.Double bottomOfAHeight = Util.perpendicularPoint(bcMiddle, this.pb.getP1(), this.pc.getP2());
        final double heightCutOutA = Util.distance(bcMiddle, bottomOfAHeight);
        final double moveADistance = heightCutOutA * fraction;
        final Point2D.Double[] cutOutB = { acMiddle, this.pc.getP1(), this.pa.getP2() };
        final Point2D.Double bottomOfBHeight = Util.perpendicularPoint(acMiddle, this.pc.getP1(), this.pa.getP2());
        final double heightCutOutB = Util.distance(acMiddle, bottomOfBHeight);
        final double moveBDistance = heightCutOutB * fraction;
        final Point2D.Double[] cutOutC = { abMiddle, this.pa.getP1(), this.pb.getP2() };
        final Point2D.Double bottomOfCHeight = Util.perpendicularPoint(abMiddle, this.pa.getP1(), this.pb.getP2());
        final double heightCutOutC = Util.distance(abMiddle, bottomOfCHeight);
        final double moveCDistance = heightCutOutC * fraction;
        final Line2D.Double baseLineA = new Line2D.Double(cutOutA[1], cutOutA[2]);
        final Line2D.Double baseLineB = new Line2D.Double(cutOutB[1], cutOutB[2]);
        final Line2D.Double baseLineC = new Line2D.Double(cutOutC[1], cutOutC[2]);
        final Line2D.Double parallelA = Util.parallelLine(baseLineA, moveADistance);
        final Line2D.Double parallelB = Util.parallelLine(baseLineB, moveBDistance);
        final Line2D.Double parallelC = Util.parallelLine(baseLineC, moveCDistance);
        final double parallelA1X = parallelA.getX1();
        final double parallelA1Y = parallelA.getY1();
        final double parallelA2X = parallelA.getX2();
        final double parallelA2Y = parallelA.getY2();
        final Point2D.Double parallelA2 = new Point2D.Double(parallelA1X, parallelA1Y);
        final Point2D.Double parallelA3 = new Point2D.Double(parallelA2X, parallelA2Y);
        final double parallelB1X = parallelB.getX1();
        final double parallelB1Y = parallelB.getY1();
        final double parallelB2X = parallelB.getX2();
        final double parallelB2Y = parallelB.getY2();
        final Point2D.Double parallelB2 = new Point2D.Double(parallelB1X, parallelB1Y);
        final Point2D.Double parallelB3 = new Point2D.Double(parallelB2X, parallelB2Y);
        final double parallelC1X = parallelC.getX1();
        final double parallelC1Y = parallelC.getY1();
        final double parallelC2X = parallelC.getX2();
        final double parallelC2Y = parallelC.getY2();
        final Point2D.Double parallelC2 = new Point2D.Double(parallelC1X, parallelC1Y);
        final Point2D.Double parallelC3 = new Point2D.Double(parallelC2X, parallelC2Y);
        final Point2D.Double meetAB1 = Util.intersectionPointOfTwoLines(parallelA2, parallelA3, parallelB2, parallelB3);
        final Point2D.Double meetAB2 = new Point2D.Double(meetAB1.x, meetAB1.y);
        final Point2D.Double meetAC1 = Util.intersectionPointOfTwoLines(parallelA2, parallelA3, parallelC2, parallelC3);
        final Point2D.Double meetAC2 = new Point2D.Double(meetAC1.x, meetAC1.y);
        final Point2D.Double meetBC1 = Util.intersectionPointOfTwoLines(parallelB2, parallelB3, parallelC2, parallelC3);
        final Point2D.Double meetBC2 = new Point2D.Double(meetBC1.x, meetBC1.y);
        this.pa.setP3(meetAB1);
        this.pb.setP5(meetAB2);
        this.pc.setP3(meetAC1);
        this.pa.setP5(meetAC2);
        this.pb.setP3(meetBC1);
        this.pc.setP5(meetBC2);
        this.resetPointsOneAndTwo();
    }
    
    protected void resetPointsOneAndTwo() {
        final Point2D.Double newAP1 = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP3(), this.pc.getP3());
        final Point2D.Double newBP1 = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pa.getP3(), this.pc.getP3());
        final Point2D.Double newCP1 = Util.intersectionPointOfTwoLines(this.pc.getP1(), this.pc.getP2(), this.pa.getP3(), this.pb.getP3());
        final Point2D.Double newAP2 = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP5(), this.pc.getP5());
        final Point2D.Double newBP2 = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pa.getP5(), this.pc.getP5());
        final Point2D.Double newCP2 = Util.intersectionPointOfTwoLines(this.pc.getP1(), this.pc.getP2(), this.pa.getP5(), this.pb.getP5());
        this.pa.setP1(newAP1);
        this.pb.setP1(newBP1);
        this.pc.setP1(newCP1);
        this.pa.setP2(newAP2);
        this.pb.setP2(newBP2);
        this.pc.setP2(newCP2);
    }
    
    public Curve[] getCurves() {
        final Curve[] ps = { null, this.pa, this.pb, this.pc };
        return ps;
    }
    
    protected Area[] findAreaIntersections() {
        final Area areaA = new Area(this.pa.getPolygon());
        final Area areaB = new Area(this.pb.getPolygon());
        final Area areaC = new Area(this.pc.getPolygon());
        final Area[] zones = new Area[8];
        (zones[1] = new Area(areaA)).subtract(areaB);
        zones[1].subtract(areaC);
        (zones[2] = new Area(areaB)).subtract(areaA);
        zones[2].subtract(areaC);
        (zones[3] = new Area(areaA)).intersect(areaB);
        zones[3].subtract(areaC);
        (zones[4] = new Area(areaC)).subtract(areaA);
        zones[4].subtract(areaB);
        (zones[5] = new Area(areaA)).intersect(areaC);
        zones[5].subtract(areaB);
        (zones[6] = new Area(areaB)).intersect(areaC);
        zones[6].subtract(areaA);
        (zones[7] = new Area(areaA)).intersect(areaB);
        zones[7].intersect(areaC);
        return zones;
    }
    
    public Point2D.Double[][] findZonePolygonArrays() {
        Point2D.Double[][] ret = null;
        if (this.diagramType == 0) {
            ret = this.findZonePolygonArrays0();
        }
        if (this.diagramType == 1) {
            ret = this.findZonePolygonArrays1();
        }
        if (this.diagramType == 2) {
            ret = this.findZonePolygonArrays2();
        }
        if (this.diagramType == 3) {
            ret = this.findZonePolygonArrays3();
        }
        return ret;
    }
    
    public Point2D.Double[][] findZonePolygonArrays3() {
        final Point2D.Double[][] polygonArrays = new Point2D.Double[8][3];
        final Point2D.Double abMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pb.getP1(), this.pb.getP2());
        final Point2D.Double acMiddle = Util.intersectionPointOfTwoLines(this.pa.getP1(), this.pa.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double bcMiddle = Util.intersectionPointOfTwoLines(this.pb.getP1(), this.pb.getP2(), this.pc.getP1(), this.pc.getP2());
        final Point2D.Double abIntersect = Util.intersectionPointOfTwoLines(this.pa.getP2(), this.pa.getP3(), this.pb.getP5(), this.pb.getP1());
        final Point2D.Double acIntersect = Util.intersectionPointOfTwoLines(this.pc.getP2(), this.pc.getP3(), this.pa.getP5(), this.pa.getP1());
        final Point2D.Double bcIntersect = Util.intersectionPointOfTwoLines(this.pb.getP2(), this.pb.getP3(), this.pc.getP5(), this.pc.getP1());
        polygonArrays[0] = null;
        int size = 6;
        if (this.pa.getP3Point5() != null) {
            ++size;
        }
        if (this.pa.getP4Point5() != null) {
            ++size;
        }
        if (this.improveLayout) {
            size += 2;
        }
        Point2D.Double[] p = new Point2D.Double[size];
        int i = 0;
        p[i] = bcMiddle;
        ++i;
        p[i] = this.pb.getP1();
        ++i;
        if (this.improveLayout) {
            p[i] = abIntersect;
            ++i;
        }
        p[i] = this.pa.getP3();
        ++i;
        if (this.pa.getP3Point5() != null) {
            p[i] = this.pa.getP3Point5();
            ++i;
        }
        p[i] = this.pa.getP4();
        ++i;
        if (this.pa.getP4Point5() != null) {
            p[i] = this.pa.getP4Point5();
            ++i;
        }
        p[i] = this.pa.getP5();
        ++i;
        if (this.improveLayout) {
            p[i] = acIntersect;
            ++i;
        }
        p[i] = this.pc.getP2();
        ++i;
        polygonArrays[1] = p;
        size = 6;
        if (this.pb.getP3Point5() != null) {
            ++size;
        }
        if (this.pb.getP4Point5() != null) {
            ++size;
        }
        if (this.improveLayout) {
            size += 2;
        }
        p = new Point2D.Double[size];
        i = 0;
        p[i] = acMiddle;
        ++i;
        p[i] = this.pc.getP1();
        ++i;
        if (this.improveLayout) {
            p[i] = bcIntersect;
            ++i;
        }
        p[i] = this.pb.getP3();
        ++i;
        if (this.pb.getP3Point5() != null) {
            p[i] = this.pb.getP3Point5();
            ++i;
        }
        p[i] = this.pb.getP4();
        ++i;
        if (this.pb.getP4Point5() != null) {
            p[i] = this.pb.getP4Point5();
            ++i;
        }
        p[i] = this.pb.getP5();
        ++i;
        if (this.improveLayout) {
            p[i] = abIntersect;
            ++i;
        }
        p[i] = this.pa.getP2();
        ++i;
        polygonArrays[2] = p;
        size = 6;
        if (this.pc.getP3Point5() != null) {
            ++size;
        }
        if (this.pc.getP4Point5() != null) {
            ++size;
        }
        if (this.improveLayout) {
            size += 2;
        }
        p = new Point2D.Double[size];
        i = 0;
        p[i] = abMiddle;
        ++i;
        p[i] = this.pa.getP1();
        ++i;
        if (this.improveLayout) {
            p[i] = acIntersect;
            ++i;
        }
        p[i] = this.pc.getP3();
        ++i;
        if (this.pc.getP3Point5() != null) {
            p[i] = this.pc.getP3Point5();
            ++i;
        }
        p[i] = this.pc.getP4();
        ++i;
        if (this.pc.getP4Point5() != null) {
            p[i] = this.pc.getP4Point5();
            ++i;
        }
        p[i] = this.pc.getP5();
        ++i;
        if (this.improveLayout) {
            p[i] = bcIntersect;
            ++i;
        }
        p[i] = this.pb.getP2();
        ++i;
        polygonArrays[4] = p;
        p = new Point2D.Double[] { acMiddle, this.pa.getP2(), null, null, null };
        if (this.improveLayout) {
            p[2] = abIntersect;
        }
        else {
            p[2] = this.pa.getP3();
        }
        p[3] = this.pb.getP1();
        p[4] = bcMiddle;
        polygonArrays[3] = p;
        p = new Point2D.Double[] { bcMiddle, this.pc.getP2(), null, null, null };
        if (this.improveLayout) {
            p[2] = acIntersect;
        }
        else {
            p[2] = this.pc.getP3();
        }
        p[3] = this.pa.getP1();
        p[4] = abMiddle;
        polygonArrays[5] = p;
        p = new Point2D.Double[] { abMiddle, this.pb.getP2(), null, null, null };
        if (this.improveLayout) {
            p[2] = bcIntersect;
        }
        else {
            p[2] = this.pb.getP3();
        }
        p[3] = this.pc.getP1();
        p[4] = acMiddle;
        polygonArrays[6] = p;
        p = new Point2D.Double[] { abMiddle, acMiddle, bcMiddle };
        polygonArrays[7] = p;
        return polygonArrays;
    }
    
    public Point2D.Double[][] findZonePolygonArrays2() {
        return this.findZonePolygonArrays1();
    }
    
    public Point2D.Double[][] findZonePolygonArrays1() {
        final Point2D.Double[][] polygonArrays = new Point2D.Double[8][3];
        polygonArrays[0] = null;
        Point2D.Double[] p = { this.pa.getP3(), this.pa.getP4(), this.pa.getP5(), this.pb.getP1() };
        polygonArrays[1] = p;
        p = new Point2D.Double[] { this.pb.getP3(), this.pb.getP4(), this.pb.getP5(), this.pc.getP1() };
        polygonArrays[2] = p;
        p = new Point2D.Double[] { this.pc.getP3(), this.pc.getP4(), this.pc.getP5(), this.pa.getP1() };
        polygonArrays[4] = p;
        p = new Point2D.Double[] { this.pb.getP1(), this.pa.getP2(), this.pa.getP3() };
        polygonArrays[3] = p;
        p = new Point2D.Double[] { this.pa.getP1(), this.pc.getP2(), this.pc.getP3() };
        polygonArrays[5] = p;
        p = new Point2D.Double[] { this.pc.getP1(), this.pb.getP2(), this.pb.getP3() };
        polygonArrays[6] = p;
        p = new Point2D.Double[] { this.pa.getP1(), this.pb.getP1(), this.pc.getP1() };
        polygonArrays[7] = p;
        return polygonArrays;
    }
    
    public Point2D.Double[][] findZonePolygonArrays0() {
        final Point2D.Double[][] polygonArrays = new Point2D.Double[8][3];
        polygonArrays[0] = null;
        Point2D.Double[] p = { this.pa.getP3(), this.pa.getP4(), this.pa.getP5() };
        polygonArrays[1] = p;
        p = new Point2D.Double[] { this.pb.getP3(), this.pb.getP4(), this.pb.getP5() };
        polygonArrays[2] = p;
        p = new Point2D.Double[] { this.pc.getP3(), this.pc.getP4(), this.pc.getP5() };
        polygonArrays[4] = p;
        p = new Point2D.Double[] { this.pb.getP1(), this.pa.getP2(), this.pa.getP3() };
        polygonArrays[3] = p;
        p = new Point2D.Double[] { this.pa.getP1(), this.pc.getP2(), this.pc.getP3() };
        polygonArrays[5] = p;
        p = new Point2D.Double[] { this.pc.getP1(), this.pb.getP2(), this.pb.getP3() };
        polygonArrays[6] = p;
        p = new Point2D.Double[] { this.pa.getP1(), this.pb.getP1(), this.pc.getP1() };
        polygonArrays[7] = p;
        return polygonArrays;
    }
    
    protected void scaleToMiddleArea(final double middleZoneArea) {
        final Area[] zoneArea = this.findAreaIntersections();
        final double currentMiddleArea = Util.findAreaOfArea(zoneArea[7]);
        final double scaleFactor = Math.sqrt(middleZoneArea) / Math.sqrt(currentMiddleArea);
        this.scaleDiagram(scaleFactor);
    }
    
    public Point findDiagramCentrePoint() {
        final Point2D.Double centreDouble = this.findDiagramCentre();
        final Point centre = new Point(Util.convertToInteger(centreDouble.x), Util.convertToInteger(centreDouble.y));
        return centre;
    }
    
    public Point2D.Double[] findBoundingBox() {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (final Point2D.Double point : this.pa.getPointList()) {
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }
        for (final Point2D.Double point : this.pb.getPointList()) {
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }
        for (final Point2D.Double point : this.pc.getPointList()) {
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }
        final Point2D.Double[] ret = { new Point2D.Double(minX, minY), new Point2D.Double(maxX, maxY) };
        return ret;
    }
    
    public Point2D.Double findDiagramCentre() {
        final Point2D.Double[] bbox = this.findBoundingBox();
        final double retx = bbox[0].x + (bbox[1].x - bbox[0].x) / 2.0;
        final double rety = bbox[0].y + (bbox[1].y - bbox[0].y) / 2.0;
        return new Point2D.Double(retx, rety);
    }
    
    public void scaleDiagram(final double multiplier) {
        if (multiplier == 0.0) {
            return;
        }
        final Point centre = this.findDiagramCentrePoint();
        this.pa.scale(centre, multiplier);
        this.pb.scale(centre, multiplier);
        this.pc.scale(centre, multiplier);
        for (final ArrayList<Point2D.Double> polygon : PolygonDiagram.polygons) {
            for (final Point2D.Double point : polygon) {
                final double x = Util.scaleCoordinate(point.x, centre.x, multiplier);
                final double y = Util.scaleCoordinate(point.y, centre.y, multiplier);
                point.setLocation(x, y);
            }
        }
    }
    
    public void moveDiagram(final double xMove, final double yMove) {
        this.pa.move(xMove, yMove);
        this.pb.move(xMove, yMove);
        this.pc.move(xMove, yMove);
        for (final ArrayList<Point2D.Double> polygon : PolygonDiagram.polygons) {
            for (final Point2D.Double point : polygon) {
                point.setLocation(point.x + xMove, point.y + yMove);
            }
        }
    }
    
    public void outputDimensions() {
        final Area[] zoneArea = this.findAreaIntersections();
        final double middleArea = Util.findAreaOfArea(zoneArea[7]);
        System.out.println("Absolute - Relative ");
        for (int zone = 1; zone < 8; ++zone) {
            int zoneToOutput = zone;
            if (zoneToOutput == 3) {
                zoneToOutput = 4;
            }
            else if (zoneToOutput == 4) {
                zoneToOutput = 3;
            }
            final double areaOfZone = Util.findAreaOfArea(zoneArea[zoneToOutput]);
            final double scaledArea = areaOfZone / middleArea;
            System.out.println(String.valueOf(Util.zoneIndex[zoneToOutput]) + " " + areaOfZone + " - " + scaledArea);
        }
    }
    
    public double findWidth() {
        final Point2D.Double[] bbox = this.findBoundingBox();
        return bbox[1].x - bbox[0].x;
    }
    
    public double findHeight() {
        final Point2D.Double[] bbox = this.findBoundingBox();
        return bbox[1].y - bbox[0].y;
    }
    
    public boolean testAlwaysConvex() {
        final double abScale = this.ab / this.abc;
        final double acScale = this.ac / this.abc;
        final double bcScale = this.bc / this.abc;
        final boolean ret = abScale + acScale + bcScale + 1.0 >= abScale * acScale * bcScale * 4.0;
        return ret;
    }
    
    public PolygonDiagram clone() {
        final PolygonDiagram cloneDiagram = new PolygonDiagram();
        cloneDiagram.diagramType = this.diagramType;
        cloneDiagram.improveLayout = this.improveLayout;
        cloneDiagram.simpleVersion = this.simpleVersion;
        cloneDiagram.convex = this.convex;
        final Curve clonePA = this.pa.clone();
        final Curve clonePB = this.pb.clone();
        final Curve clonePC = this.pc.clone();
        cloneDiagram.pa = clonePA;
        cloneDiagram.pb = clonePB;
        cloneDiagram.pc = clonePC;
        cloneDiagram.a = this.a;
        cloneDiagram.b = this.b;
        cloneDiagram.c = this.c;
        cloneDiagram.ab = this.ab;
        cloneDiagram.ac = this.ac;
        cloneDiagram.bc = this.bc;
        cloneDiagram.abc = this.abc;
        return cloneDiagram;
    }
    
    double getChecksum() {
        double checksum = 0.0;
        int count = 1;
        for (final Point2D.Double point : this.pa.getPointList()) {
            if (point != null) {
                checksum += point.x * Math.log(count);
                ++count;
                checksum += point.y * Math.log(count);
                ++count;
            }
        }
        ++count;
        for (final Point2D.Double point : this.pb.getPointList()) {
            if (point != null) {
                checksum += point.x * Math.log(count);
                ++count;
                checksum += point.y * Math.log(count);
                ++count;
            }
        }
        ++count;
        for (final Point2D.Double point : this.pc.getPointList()) {
            if (point != null) {
                checksum += point.x * Math.log(count);
                ++count;
                checksum += point.y * Math.log(count);
                ++count;
            }
        }
        Util.debug(3, "checksum from pts is " + Util.round(checksum, 3));
        if (this.convex) {
            Util.debug(3, "add to checksum for convexity");
            checksum += 1.23;
            Util.debug(3, "checksum is " + Util.round(checksum, 3));
        }
        return checksum;
    }
}
