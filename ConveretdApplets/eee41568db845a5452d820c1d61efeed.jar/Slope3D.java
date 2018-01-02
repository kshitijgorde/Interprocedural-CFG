import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Slope3D
{
    final Color SLOPE_COLOR;
    final double ACCELERATION = 4.0;
    final int NEG_Y = 0;
    final int POS_X = 1;
    final int POS_Y = 2;
    final int NEG_X = 3;
    final int X_MIN = 0;
    final int Y_MIN = 1;
    final int Z_MIN = 2;
    final int X_MAX = 3;
    final int Y_MAX = 4;
    final int Z_MAX = 5;
    final int DOWNSLOPE = 6;
    public Slope3D m_slopeNext;
    double m_dX0;
    double m_dY0;
    double m_dZ0;
    double m_dX1;
    double m_dY1;
    double m_dZ1;
    double m_dXsize;
    double m_dYsize;
    int m_ndxDownslope;
    cgModel m_model;
    public cgObject m_object;
    
    public boolean acceleration(final Golfball3D golfball3D, final double n) {
        if (golfball3D.m_dX0 < this.m_dX0 || golfball3D.m_dX0 > this.m_dX1 || golfball3D.m_dY0 < this.m_dY0 || golfball3D.m_dY0 > this.m_dY1) {
            return false;
        }
        double n2 = golfball3D.m_dVelocity * Math.cos(golfball3D.m_dAngle);
        double n3 = golfball3D.m_dVelocity * Math.sin(golfball3D.m_dAngle);
        switch (this.m_ndxDownslope) {
            case 0: {
                n3 -= n * 4.0;
                break;
            }
            case 1: {
                n2 += n * 4.0;
                break;
            }
            case 2: {
                n3 += n * 4.0;
                break;
            }
            case 3: {
                n2 -= n * 4.0;
                break;
            }
        }
        golfball3D.m_dAngle = Math.atan2(n3, n2);
        golfball3D.m_dVelocity = Math.sqrt(Math.pow(n2, 2.0) + Math.pow(n3, 2.0));
        if (golfball3D.m_dVelocity > 10.0) {
            golfball3D.m_dVelocity = 10.0;
        }
        return true;
    }
    
    public double getZcoord(final double n, final double n2) {
        if (n < this.m_dX0 || n > this.m_dX1 || n2 < this.m_dY0 || n2 > this.m_dY1) {
            return -1.0;
        }
        double n3 = 0.0;
        switch (this.m_ndxDownslope) {
            case 1: {
                n3 = (this.m_dX1 - n) / (this.m_dX1 - this.m_dX0);
                break;
            }
            case 3: {
                n3 = (n - this.m_dX0) / (this.m_dX1 - this.m_dX0);
                break;
            }
            case 2: {
                n3 = (this.m_dY1 - n2) / (this.m_dY1 - this.m_dY0);
                break;
            }
            case 0: {
                n3 = (n2 - this.m_dY0) / (this.m_dY1 - this.m_dY0);
                break;
            }
        }
        return this.m_dZ0 + (this.m_dZ1 - this.m_dZ0) * n3;
    }
    
    public Slope3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.SLOPE_COLOR = new Color(6723942);
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length != 7) {
            throw new ParsingException("Slope needs 7 parameters");
        }
        if (numericArgs[3] <= numericArgs[0] || numericArgs[4] <= numericArgs[1] || numericArgs[5] <= numericArgs[2]) {
            throw new ParsingException("Coordinates reversed");
        }
        this.m_dX0 = numericArgs[0];
        this.m_dY0 = numericArgs[1];
        this.m_dZ0 = numericArgs[2];
        this.m_dX1 = numericArgs[3];
        this.m_dY1 = numericArgs[4];
        this.m_dZ1 = numericArgs[5];
        this.m_dXsize = numericArgs[3] - numericArgs[0];
        this.m_dYsize = numericArgs[4] - numericArgs[1];
        switch ((int)numericArgs[6]) {
            case 0: {
                this.m_ndxDownslope = 2;
                break;
            }
            case 90: {
                this.m_ndxDownslope = 1;
                break;
            }
            case 180: {
                this.m_ndxDownslope = 0;
                break;
            }
            case 270: {
                this.m_ndxDownslope = 3;
                break;
            }
            default: {
                throw new ParsingException("Invalid downslope direction");
            }
        }
        (this.m_model = new cgModel(4, 1)).specifyVertex(0, new cgVector(this.m_dX0, this.m_dY0, (this.m_ndxDownslope == 3 || this.m_ndxDownslope == 0) ? this.m_dZ0 : this.m_dZ1));
        this.m_model.specifyVertex(1, new cgVector(this.m_dX1, this.m_dY0, (this.m_ndxDownslope == 1 || this.m_ndxDownslope == 0) ? this.m_dZ0 : this.m_dZ1));
        this.m_model.specifyVertex(2, new cgVector(this.m_dX1, this.m_dY1, (this.m_ndxDownslope == 1 || this.m_ndxDownslope == 2) ? this.m_dZ0 : this.m_dZ1));
        this.m_model.specifyVertex(3, new cgVector(this.m_dX0, this.m_dY1, (this.m_ndxDownslope == 3 || this.m_ndxDownslope == 2) ? this.m_dZ0 : this.m_dZ1));
        this.m_model.specifyFace(0, new cgFace(4, new int[] { 3, 2, 1, 0 }, this.SLOPE_COLOR, null));
        (this.m_object = new cgObject(this.m_model)).transform();
    }
}
