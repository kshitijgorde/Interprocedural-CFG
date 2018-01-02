import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Cup3D
{
    final Color CUP_COLOR;
    final double CUP_RADIUS = 0.175;
    final int CUP_VERTICES = 6;
    final double PIN_HEIGHT = 5.0;
    final int PIN_VERTICES = 4;
    final Color POLE_COLOR;
    final Color FLAG_COLOR;
    final double FLAG_WIDTH = 1.5;
    final double FLAG_HEIGHT = 0.75;
    final double SKIP_VELOCITY;
    final double RIM_VELOCITY;
    public final long LATENCY = 2000L;
    public long m_lReleaseTime;
    double m_dX0;
    double m_dY0;
    double m_dZ0;
    double m_dXmin;
    double m_dXmax;
    double m_dYmin;
    double m_dYmax;
    static cgModel m_model;
    public cgObject m_object;
    
    public double CollisionAnalysis(final Golfball3D golfball3D, final boolean b, final long n) {
        if (golfball3D.m_dXmin >= this.m_dXmax || golfball3D.m_dXmax <= this.m_dXmin || golfball3D.m_dYmin >= this.m_dYmax || golfball3D.m_dYmax <= this.m_dYmin) {
            return -1.0;
        }
        if (golfball3D.m_dVelocity >= this.SKIP_VELOCITY) {
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
        if (golfball3D.m_dVelocity >= this.RIM_VELOCITY) {
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
            if (golfball3D.m_dVelocity < this.RIM_VELOCITY) {
                golfball3D.m_cup = this;
                golfball3D.m_dVelocity = 5.0;
                golfball3D.m_dFinalVelocity = 5.0;
                this.m_lReleaseTime = n + 2000L;
            }
            else {
                final double abs = Math.abs(n3);
                double n6 = abs / 0.175 * ((this.SKIP_VELOCITY - golfball3D.m_dVelocity) / (this.SKIP_VELOCITY - this.RIM_VELOCITY)) * 3.141592653589793 / 2.0;
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
            golfball3D.m_cup = null;
            return true;
        }
        return false;
    }
    
    public Cup3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.CUP_COLOR = Color.black;
        this.POLE_COLOR = Color.white;
        this.FLAG_COLOR = Color.red;
        this.SKIP_VELOCITY = 5.0;
        this.RIM_VELOCITY = 2.5;
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length < 2) {
            throw new ParsingException("Cup needs at least 2 arguments");
        }
        if (numericArgs.length > 4) {
            throw new ParsingException("Cup needs no more than 4 arguments");
        }
        this.m_dX0 = numericArgs[0];
        this.m_dY0 = numericArgs[1];
        if (numericArgs.length > 2) {
            this.m_dZ0 = numericArgs[2];
        }
        else {
            this.m_dZ0 = 0.0;
        }
        this.m_dXmin = this.m_dX0 - 0.175;
        this.m_dXmax = this.m_dX0 + 0.175;
        this.m_dYmin = this.m_dY0 - 0.175;
        this.m_dYmax = this.m_dY0 + 0.175;
        if (Cup3D.m_model == null) {
            Cup3D.m_model = new cgModel(10, 3);
            final int[] array = new int[6];
            int n = 0;
            do {
                final double n2 = n / 6.0 * 2.0 * 3.141592653589793;
                Cup3D.m_model.specifyVertex(n, new cgVector(0.175 * Math.cos(n2), 0.175 * Math.sin(n2), this.m_dZ0));
                array[n] = 6 - n - 1;
            } while (++n < 6);
            Cup3D.m_model.specifyFace(0, new cgFace(6, array, this.CUP_COLOR, null));
            final int[] array2 = { 6, 7 };
            Cup3D.m_model.specifyVertex(6, new cgVector(0.0, 0.0, 0.0));
            Cup3D.m_model.specifyVertex(7, new cgVector(0.0, 0.0, 5.0));
            Cup3D.m_model.specifyFace(1, new cgFace(2, array2, this.POLE_COLOR, null));
            final int[] array3 = { 7, 8, 9 };
            Cup3D.m_model.specifyVertex(8, new cgVector(0.0, 1.5, 4.625));
            Cup3D.m_model.specifyVertex(9, new cgVector(0.0, 0.0, 4.25));
            Cup3D.m_model.specifyFace(2, new cgFace(3, array3, this.FLAG_COLOR, this.FLAG_COLOR));
        }
        this.m_object = new cgObject(Cup3D.m_model);
        this.m_object.m_vector.m_dX = this.m_dX0;
        this.m_object.m_vector.m_dY = this.m_dY0;
        this.m_object.m_vector.m_dZ = this.m_dZ0;
        if (numericArgs.length >= 4) {
            this.m_object.m_matrix.rotateWorld(0.0, 0.0, -numericArgs[3] / 180.0 * 3.141592653589793);
        }
        this.m_object.transform();
    }
}
