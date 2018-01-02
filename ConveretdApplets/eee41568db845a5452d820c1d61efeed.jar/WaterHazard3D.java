import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class WaterHazard3D
{
    final int X_MIN = 0;
    final int Y_MIN = 1;
    final int X_MAX = 2;
    final int Y_MAX = 3;
    final Color BODY_COLOR;
    final Color SHORE_COLOR;
    final Color GRASS_COLOR;
    final double SHORE_WIDTH = 0.4;
    final double DEPTH = 0.4;
    public WaterHazard3D m_waterhazardNext;
    double m_dX0;
    double m_dY0;
    double m_dX1;
    double m_dY1;
    double m_dXsize;
    double m_dYsize;
    cgModel m_model;
    public cgObject m_object;
    
    public double CollisionAnalysis(final Golfball3D golfball3D, final boolean b) {
        double n = 0.0;
        double dy0 = 0.0;
        if (golfball3D.m_dXmin >= this.m_dX1 || golfball3D.m_dXmax <= this.m_dX0 || golfball3D.m_dYmin >= this.m_dY1 || golfball3D.m_dYmax <= this.m_dY0) {
            return -1.0;
        }
        boolean b2 = false;
        if (golfball3D.m_dX1 > golfball3D.m_dX0 && golfball3D.m_dX0 < this.m_dX0) {
            b2 = true;
            n = this.m_dX0;
        }
        if (golfball3D.m_dX1 < golfball3D.m_dX0 && golfball3D.m_dX0 > this.m_dX1) {
            b2 = true;
            n = this.m_dX1;
        }
        if (b2) {
            dy0 = golfball3D.m_dY0 + (golfball3D.m_dY1 - golfball3D.m_dY0) * (n - golfball3D.m_dX0) / (golfball3D.m_dX1 - golfball3D.m_dX0);
            if (dy0 > this.m_dY0 && dy0 < this.m_dY1) {
                final double sqrt = Math.sqrt(Math.pow(n - golfball3D.m_dX0, 2.0) + Math.pow(dy0 - golfball3D.m_dY0, 2.0));
                if (b) {
                    golfball3D.m_bWaterBall = true;
                    if (golfball3D.m_dX1 > golfball3D.m_dX0) {
                        golfball3D.m_dX0 = this.m_dX0 - 0.075;
                    }
                    else {
                        golfball3D.m_dX0 = this.m_dX1 + 0.075;
                    }
                    golfball3D.m_dY0 = dy0;
                    golfball3D.m_dVelocity = 0.0;
                    golfball3D.m_dDistance = 0.0;
                    golfball3D.m_dX1 = golfball3D.m_dX0;
                    golfball3D.m_dY1 = golfball3D.m_dY0;
                    golfball3D.m_dFinalVelocity = golfball3D.m_dVelocity;
                    golfball3D.SetString("Penalty stroke");
                }
                return sqrt;
            }
        }
        boolean b3 = false;
        if (golfball3D.m_dY1 > golfball3D.m_dY0 && golfball3D.m_dY0 < this.m_dY0) {
            b3 = true;
            dy0 = this.m_dY0;
        }
        if (golfball3D.m_dY1 < golfball3D.m_dY0 && golfball3D.m_dY0 > this.m_dY1) {
            b3 = true;
            dy0 = this.m_dY1;
        }
        if (b3) {
            final double dx0 = golfball3D.m_dX0 + (golfball3D.m_dX1 - golfball3D.m_dX0) * (dy0 - golfball3D.m_dY0) / (golfball3D.m_dY1 - golfball3D.m_dY0);
            if (dx0 > this.m_dX0 && dx0 < this.m_dX1) {
                final double sqrt2 = Math.sqrt(Math.pow(dy0 - golfball3D.m_dY0, 2.0) + Math.pow(dx0 - golfball3D.m_dX0, 2.0));
                if (b) {
                    golfball3D.m_bWaterBall = true;
                    if (golfball3D.m_dY1 > golfball3D.m_dY0) {
                        golfball3D.m_dY0 = this.m_dY0 - 0.075;
                    }
                    else {
                        golfball3D.m_dY0 = this.m_dY1 + 0.075;
                    }
                    golfball3D.m_dX0 = dx0;
                    golfball3D.m_dVelocity = 0.0;
                    golfball3D.m_dDistance = 0.0;
                    golfball3D.m_dX1 = golfball3D.m_dX0;
                    golfball3D.m_dY1 = golfball3D.m_dY0;
                    golfball3D.m_dFinalVelocity = golfball3D.m_dVelocity;
                    golfball3D.SetString("Penalty stroke");
                }
                return sqrt2;
            }
        }
        return -1.0;
    }
    
    public WaterHazard3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.BODY_COLOR = new Color(52479);
        this.SHORE_COLOR = new Color(13421772);
        this.GRASS_COLOR = new Color(Color.HSBtoRGB(0.37f, 0.8f, 0.4f));
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length != 4) {
            throw new ParsingException("Water hazard needs at exactly 2 coordinates");
        }
        if (numericArgs[2] <= numericArgs[0] || numericArgs[3] <= numericArgs[1]) {
            throw new ParsingException("Invalid rectangle coordinates");
        }
        this.m_dX0 = numericArgs[0];
        this.m_dY0 = numericArgs[1];
        this.m_dXsize = numericArgs[2] - numericArgs[0];
        this.m_dYsize = numericArgs[3] - numericArgs[1];
        this.m_dX1 = this.m_dX0 + this.m_dXsize;
        this.m_dY1 = this.m_dY0 + this.m_dYsize;
        (this.m_model = new cgModel(8, 5)).specifyVertex(0, new cgVector(this.m_dX0, this.m_dY0, 0.0));
        this.m_model.specifyVertex(1, new cgVector(this.m_dX1, this.m_dY0, 0.0));
        this.m_model.specifyVertex(2, new cgVector(this.m_dX1, this.m_dY1, 0.0));
        this.m_model.specifyVertex(3, new cgVector(this.m_dX0, this.m_dY1, 0.0));
        this.m_model.specifyVertex(4, new cgVector(this.m_dX0 + 0.4, this.m_dY0 + 0.4, -0.4));
        this.m_model.specifyVertex(5, new cgVector(this.m_dX1 - 0.4, this.m_dY0 + 0.4, -0.4));
        this.m_model.specifyVertex(6, new cgVector(this.m_dX1 - 0.4, this.m_dY1 - 0.4, -0.4));
        this.m_model.specifyVertex(7, new cgVector(this.m_dX0 + 0.4, this.m_dY1 - 0.4, -0.4));
        final int[] array = { 0, 4, 5, 1 };
        final int[] array2 = { 1, 5, 6, 2 };
        final int[] array3 = { 2, 6, 7, 3 };
        final int[] array4 = { 3, 7, 4, 0 };
        final int[] array5 = { 7, 6, 5, 4 };
        this.m_model.specifyFace(0, new cgFace(4, array, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_model.specifyFace(1, new cgFace(4, array2, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_model.specifyFace(2, new cgFace(4, array3, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_model.specifyFace(3, new cgFace(4, array4, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_model.specifyFace(4, new cgFace(4, array5, this.BODY_COLOR, null));
        (this.m_object = new cgObject(this.m_model)).transform();
    }
}
