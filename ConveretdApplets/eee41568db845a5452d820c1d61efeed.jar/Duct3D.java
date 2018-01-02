import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Duct3D
{
    public Duct3D m_ductNext;
    final int ENTRANCE_X = 0;
    final int ENTRANCE_Y = 1;
    final int ENTRANCE_Z = 2;
    final Color ENTRANCE_COLOR;
    final double ENTRANCE_RADIUS = 0.175;
    final int ENTRANCE_VERTICES = 6;
    final double SKIP_VELOCITY = 5.0;
    final double RIM_VELOCITY = 2.5;
    double m_dX0;
    double m_dY0;
    double m_dXmin;
    double m_dXmax;
    double m_dYmin;
    double m_dYmax;
    static cgModel m_modelEntrance;
    public cgObject m_objectEntrance;
    final int EXIT_X = 3;
    final int EXIT_Y = 4;
    final int EXIT_Z = 5;
    final int EXIT_ANGLE = 6;
    final Color EXIT_COLOR;
    final double EXIT_RADIUS = 0.175;
    final int EXIT_VERTICES = 6;
    final double EXIT_VELOCITY = 5.0;
    final long LATENCY = 500L;
    long m_lReleaseTime;
    double m_dExitHeading;
    double m_dExitXemerge;
    double m_dExitYemerge;
    static cgModel m_modelExit;
    public cgObject m_objectExit;
    
    public double CollisionAnalysis(final Golfball3D golfball3D, final boolean b, final long n) {
        if (golfball3D.m_dXmin >= this.m_dXmax || golfball3D.m_dXmax <= this.m_dXmin || golfball3D.m_dYmin >= this.m_dYmax || golfball3D.m_dYmax <= this.m_dYmin) {
            return -1.0;
        }
        if (golfball3D.m_dVelocity >= 5.0) {
            return -1.0;
        }
        final double n2 = (this.m_dX0 - golfball3D.m_dX0) * Math.cos(golfball3D.m_dAngle) + (this.m_dY0 - golfball3D.m_dY0) * Math.sin(golfball3D.m_dAngle);
        final double n3 = (this.m_dY0 - golfball3D.m_dY0) * Math.cos(golfball3D.m_dAngle) - (this.m_dX0 - golfball3D.m_dX0) * Math.sin(golfball3D.m_dAngle);
        if (Math.abs(n3) >= 0.175) {
            return -1.0;
        }
        final double sqrt = Math.sqrt(Math.pow(0.175, 2.0) - Math.pow(n3, 2.0));
        final double n4 = n2 - sqrt;
        if (n2 + sqrt <= 0.0 || n4 >= golfball3D.m_dDistance) {
            return -1.0;
        }
        double n5;
        if (golfball3D.m_dVelocity >= 2.5) {
            if (n2 <= 0.0 || n2 >= golfball3D.m_dDistance || n3 == 0.0) {
                return -1.0;
            }
            n5 = n2;
        }
        else {
            n5 = n4;
            if (n5 < 0.0) {
                n5 = 0.0;
            }
        }
        if (b) {
            if (golfball3D.m_dVelocity < 2.5) {
                golfball3D.m_duct = this;
                golfball3D.m_dX0 = this.m_dExitXemerge;
                golfball3D.m_dY0 = this.m_dExitYemerge;
                this.m_lReleaseTime = n + 500L;
            }
            else {
                final double abs = Math.abs(n3);
                double n6 = abs / 0.175 * ((5.0 - golfball3D.m_dVelocity) / 2.5) * 3.141592653589793 / 2.0;
                if (n3 < 0.0) {
                    n6 *= -1.0;
                }
                double n7;
                if (n3 > 0.0) {
                    n7 = golfball3D.m_dAngle + 1.5707963267948966;
                }
                else {
                    n7 = golfball3D.m_dAngle - 1.5707963267948966;
                }
                final double n8 = n7 + n6;
                if (n3 > 0.0) {
                    golfball3D.m_dAngle = n8 - 1.5707963267948966;
                }
                else {
                    golfball3D.m_dAngle = n8 + 1.5707963267948966;
                }
                golfball3D.m_dX0 = this.m_dX0 + abs * Math.cos(n8);
                golfball3D.m_dY0 = this.m_dY0 + abs * Math.sin(n8);
                if (n5 < golfball3D.m_dDistance / 10.0) {
                    n5 = golfball3D.m_dDistance / 10.0;
                }
                golfball3D.m_dVelocity -= n5 / golfball3D.m_dDistance * (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity);
                golfball3D.m_dDistance -= n5;
                golfball3D.m_dX1 = golfball3D.m_dX0 + golfball3D.m_dDistance * Math.cos(golfball3D.m_dAngle);
                golfball3D.m_dY1 = golfball3D.m_dY0 + golfball3D.m_dDistance * Math.sin(golfball3D.m_dAngle);
                golfball3D.m_dXmin = Math.min(golfball3D.m_dX0, golfball3D.m_dX1);
                golfball3D.m_dXmax = Math.max(golfball3D.m_dX0, golfball3D.m_dX1);
                golfball3D.m_dYmin = Math.min(golfball3D.m_dY0, golfball3D.m_dY1);
                golfball3D.m_dYmax = Math.max(golfball3D.m_dY0, golfball3D.m_dY1);
            }
        }
        return n5;
    }
    
    public boolean GolfballPhysics(final Golfball3D golfball3D, final long n) {
        if (n >= this.m_lReleaseTime) {
            golfball3D.m_dX0 = this.m_dExitXemerge;
            golfball3D.m_dY0 = this.m_dExitYemerge;
            golfball3D.m_dAngle = this.m_dExitHeading;
            golfball3D.m_dAngle += (Math.random() - 0.5) * 0.25;
            golfball3D.m_duct = null;
            golfball3D.m_dVelocity = 5.0 * (1.0 + (Math.random() - 0.5) * 0.25);
            return true;
        }
        return false;
    }
    
    public Duct3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.ENTRANCE_COLOR = Color.red;
        this.EXIT_COLOR = Color.black;
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length != 7) {
            throw new ParsingException("Duct requires seven arguments");
        }
        this.m_dX0 = numericArgs[0];
        this.m_dY0 = numericArgs[1];
        this.m_dXmin = this.m_dX0 - 0.175;
        this.m_dXmax = this.m_dX0 + 0.175;
        this.m_dYmin = this.m_dY0 - 0.175;
        this.m_dYmax = this.m_dY0 + 0.175;
        this.m_dExitXemerge = numericArgs[3];
        this.m_dExitYemerge = numericArgs[4];
        this.m_dExitHeading = (90.0 - numericArgs[6]) / 180.0 * 3.141592653589793;
        if (Duct3D.m_modelEntrance == null) {
            Duct3D.m_modelEntrance = new cgModel(6, 1);
            final int[] array = new int[6];
            int n = 0;
            do {
                final double n2 = n / 6.0 * 2.0 * 3.141592653589793;
                Duct3D.m_modelEntrance.specifyVertex(n, new cgVector(0.175 * Math.cos(n2), 0.175 * Math.sin(n2), 0.0));
                array[n] = 6 - n - 1;
            } while (++n < 6);
            Duct3D.m_modelEntrance.specifyFace(0, new cgFace(6, array, this.ENTRANCE_COLOR, null));
        }
        if (Duct3D.m_modelExit == null) {
            Duct3D.m_modelExit = new cgModel(6, 1);
            final int[] array2 = new int[6];
            int n3 = 0;
            do {
                final double n4 = n3 / 6.0 * 2.0 * 3.141592653589793;
                Duct3D.m_modelExit.specifyVertex(n3, new cgVector(0.0, 0.175 * Math.cos(n4), 0.175 * Math.sin(n4)));
                array2[n3] = 6 - n3 - 1;
            } while (++n3 < 6);
            Duct3D.m_modelExit.specifyFace(0, new cgFace(6, array2, this.EXIT_COLOR, null));
        }
        this.m_objectEntrance = new cgObject(Duct3D.m_modelEntrance);
        this.m_objectEntrance.m_vector = new cgVector(numericArgs[0], numericArgs[1], numericArgs[2]);
        this.m_objectEntrance.transform();
        this.m_objectExit = new cgObject(Duct3D.m_modelExit);
        this.m_objectExit.m_vector = new cgVector(numericArgs[3], numericArgs[4], numericArgs[5]);
        this.m_objectExit.m_matrix.rotateWorld(0.0, 0.0, (90.0 - numericArgs[6]) / 180.0 * 3.141592653589793);
        this.m_objectExit.transform();
    }
}
