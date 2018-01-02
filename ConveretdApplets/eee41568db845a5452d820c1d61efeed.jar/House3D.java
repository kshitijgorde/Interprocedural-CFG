import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class House3D
{
    final int X_MIN = 0;
    final int Y_MIN = 1;
    final int X_MAX = 2;
    final int Y_MAX = 3;
    final int Z_BASE = 4;
    final double WALL_HEIGHT = 3.0;
    final Color WALL_COLOR;
    final double ROOF_HEIGHT = 4.5;
    final Color ROOF_COLOR;
    final double DOOR_HEIGHT = 0.75;
    final double DOOR_WIDTH = 0.5;
    final Color DOOR_COLOR;
    final double FLAG_BOTTOM = 5.25;
    final double FLAG_TOP = 6.0;
    final double FLAG_LENGTH = 1.5;
    final Color FLAG_COLOR;
    final double EXIT_VELOCITY = 5.0;
    final int NEG_Y_QUADRANT = 0;
    final int POS_X_QUADRANT = 1;
    final int POS_Y_QUADRANT = 2;
    final int NEG_X_QUADRANT = 3;
    final long QUADRANT_TIME = 2000L;
    final long LATENCY = 750L;
    public House3D m_houseNext;
    double m_dXmin;
    double m_dXmax;
    double m_dYmin;
    double m_dYmax;
    long m_lReleaseTime;
    cgModel m_model;
    public cgObject m_object;
    static cgModel m_modelFlag;
    public cgObject m_objectFlag1;
    cgObject m_objectFlag2;
    
    public void transformFlagChain(final long n) {
        this.transformFlag(n % 8000L / 8000.0 * 3.141592653589793 * 2.0);
    }
    
    public double CollisionAnalysis(final Golfball3D golfball3D, final boolean b, final long n) {
        double dx0 = 0.0;
        double n2 = 0.0;
        if (golfball3D.m_dXmin >= this.m_dXmax || golfball3D.m_dXmax <= this.m_dXmin || golfball3D.m_dYmin >= this.m_dYmax || golfball3D.m_dYmax <= this.m_dYmin) {
            return -1.0;
        }
        boolean b2 = false;
        if (golfball3D.m_dX1 > golfball3D.m_dX0 && golfball3D.m_dX0 < this.m_dXmin) {
            b2 = true;
            dx0 = this.m_dXmin;
        }
        if (golfball3D.m_dX1 < golfball3D.m_dX0 && golfball3D.m_dX0 > this.m_dXmax) {
            b2 = true;
            dx0 = this.m_dXmax;
        }
        if (b2) {
            n2 = golfball3D.m_dY0 + (golfball3D.m_dY1 - golfball3D.m_dY0) * (dx0 - golfball3D.m_dX0) / (golfball3D.m_dX1 - golfball3D.m_dX0);
            if (n2 > this.m_dYmin && n2 < this.m_dYmax) {
                final double sqrt = Math.sqrt(Math.pow(dx0 - golfball3D.m_dX0, 2.0) + Math.pow(n2 - golfball3D.m_dY0, 2.0));
                if (b) {
                    if (n2 >= (this.m_dYmin + this.m_dYmax) / 2.0 - 0.25 && n2 <= (this.m_dYmin + this.m_dYmax) / 2.0 + 0.25) {
                        this.EnterHouse(golfball3D, n);
                    }
                    else {
                        golfball3D.m_dAngle = Math.atan2(n2 - golfball3D.m_dY0, -(dx0 - golfball3D.m_dX0));
                        golfball3D.m_dX0 = dx0;
                        golfball3D.m_dY0 = n2;
                        golfball3D.m_dVelocity -= (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity) * (sqrt / golfball3D.m_dDistance);
                        golfball3D.m_dDistance -= sqrt;
                        golfball3D.m_dX1 = golfball3D.m_dX0 + Math.cos(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dY1 = golfball3D.m_dY0 + Math.sin(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dXmin = Math.min(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmin = Math.min(golfball3D.m_dY0, golfball3D.m_dY1);
                        golfball3D.m_dXmax = Math.max(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmax = Math.max(golfball3D.m_dY0, golfball3D.m_dY1);
                    }
                }
                return sqrt;
            }
        }
        boolean b3 = false;
        if (golfball3D.m_dY1 > golfball3D.m_dY0 && golfball3D.m_dY0 < this.m_dYmin) {
            b3 = true;
            n2 = this.m_dYmin;
        }
        if (golfball3D.m_dY1 < golfball3D.m_dY0 && golfball3D.m_dY0 > this.m_dYmax) {
            b3 = true;
            n2 = this.m_dYmax;
        }
        if (b3) {
            final double dx2 = golfball3D.m_dX0 + (golfball3D.m_dX1 - golfball3D.m_dX0) * (n2 - golfball3D.m_dY0) / (golfball3D.m_dY1 - golfball3D.m_dY0);
            if (dx2 > this.m_dXmin && dx2 < this.m_dXmax) {
                final double sqrt2 = Math.sqrt(Math.pow(dx2 - golfball3D.m_dX0, 2.0) + Math.pow(n2 - golfball3D.m_dY0, 2.0));
                if (b) {
                    if (dx2 >= (this.m_dXmin + this.m_dXmax) / 2.0 - 0.25 && dx2 <= (this.m_dXmin + this.m_dXmax) / 2.0 + 0.25) {
                        this.EnterHouse(golfball3D, n);
                    }
                    else {
                        golfball3D.m_dAngle = Math.atan2(-(n2 - golfball3D.m_dY0), dx2 - golfball3D.m_dX0);
                        golfball3D.m_dX0 = dx2;
                        golfball3D.m_dY0 = n2;
                        golfball3D.m_dVelocity -= (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity) * (sqrt2 / golfball3D.m_dDistance);
                        golfball3D.m_dDistance -= sqrt2;
                        golfball3D.m_dX1 = golfball3D.m_dX0 + Math.cos(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dY1 = golfball3D.m_dY0 + Math.sin(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dXmin = Math.min(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmin = Math.min(golfball3D.m_dY0, golfball3D.m_dY1);
                        golfball3D.m_dXmax = Math.max(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmax = Math.max(golfball3D.m_dY0, golfball3D.m_dY1);
                    }
                }
                return sqrt2;
            }
        }
        return -1.0;
    }
    
    private void EnterHouse(final Golfball3D golfball3D, final long n) {
        golfball3D.m_house = this;
        golfball3D.m_dX0 = (this.m_dXmin + this.m_dXmax) / 2.0;
        golfball3D.m_dY0 = (this.m_dYmin + this.m_dYmax) / 2.0;
        this.m_lReleaseTime = n + 750L;
    }
    
    private void transformFlag(final double n) {
        this.m_objectFlag1.m_matrix.reset();
        this.m_objectFlag1.m_matrix.rotateWorld(0.0, 0.0, n);
        this.m_objectFlag1.transform();
        if (this.m_houseNext != null) {
            this.m_houseNext.transformFlag(n);
        }
    }
    
    public boolean GolfballPhysics(final Golfball3D golfball3D, final long n) {
        if (n >= this.m_lReleaseTime) {
            final double n2 = n % 8000L / 8000.0 * 3.141592653589793 * 2.0;
            int n3 = 1;
            if (n2 >= 0.7853981633974483 && n2 < 2.356194490192345) {
                n3 = 2;
            }
            if (n2 >= 2.356194490192345 && n2 < 3.9269908169872414) {
                n3 = 3;
            }
            if (n2 >= 3.9269908169872414 && n2 < 5.497787143782138) {
                n3 = 0;
            }
            switch (n3) {
                case 1: {
                    golfball3D.m_dX0 = this.m_dXmax;
                    golfball3D.m_dY0 = (this.m_dYmin + this.m_dYmax) / 2.0;
                    golfball3D.m_dAngle = 0.0;
                    break;
                }
                case 2: {
                    golfball3D.m_dX0 = (this.m_dXmin + this.m_dXmax) / 2.0;
                    golfball3D.m_dY0 = this.m_dYmax;
                    golfball3D.m_dAngle = 1.5707963267948966;
                    break;
                }
                case 3: {
                    golfball3D.m_dX0 = this.m_dXmin;
                    golfball3D.m_dY0 = (this.m_dYmin + this.m_dYmax) / 2.0;
                    golfball3D.m_dAngle = 3.141592653589793;
                    break;
                }
                case 0: {
                    golfball3D.m_dX0 = (this.m_dXmin + this.m_dXmax) / 2.0;
                    golfball3D.m_dY0 = this.m_dYmin;
                    golfball3D.m_dAngle = 4.71238898038469;
                    break;
                }
            }
            golfball3D.m_dAngle += (Math.random() - 0.5) * 0.25;
            golfball3D.m_house = null;
            golfball3D.m_dVelocity = 5.0 * (1.0 + (Math.random() - 0.5) * 0.25);
            return true;
        }
        return false;
    }
    
    public House3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.WALL_COLOR = Color.white;
        this.ROOF_COLOR = Color.red;
        this.DOOR_COLOR = Color.black;
        this.FLAG_COLOR = new Color(0, 255, 255);
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length < 4 || numericArgs.length > 5) {
            throw new ParsingException("House needs 4 or 5 arguments");
        }
        final double n = numericArgs[0];
        final double n2 = numericArgs[2];
        final double dx = (n + n2) / 2.0;
        final double n3 = numericArgs[1];
        final double n4 = numericArgs[3];
        final double dy = (n3 + n4) / 2.0;
        double dz;
        if (numericArgs.length == 5) {
            dz = numericArgs[4];
        }
        else {
            dz = 0.0;
        }
        if (n2 - n < 0.5 || n4 - n3 < 0.5) {
            throw new ParsingException("House too small");
        }
        this.m_dXmin = n - 0.075;
        this.m_dXmax = n2 + 0.075;
        this.m_dYmin = n3 - 0.075;
        this.m_dYmax = n4 + 0.075;
        (this.m_model = new cgModel(25, 12)).specifyVertex(0, new cgVector(n, n3, dz));
        this.m_model.specifyVertex(1, new cgVector(n2, n3, dz));
        this.m_model.specifyVertex(2, new cgVector(n2, n4, dz));
        this.m_model.specifyVertex(3, new cgVector(n, n4, dz));
        this.m_model.specifyVertex(4, new cgVector(n, n3, dz + 3.0));
        this.m_model.specifyVertex(5, new cgVector(n2, n3, dz + 3.0));
        this.m_model.specifyVertex(6, new cgVector(n2, n4, dz + 3.0));
        this.m_model.specifyVertex(7, new cgVector(n, n4, dz + 3.0));
        this.m_model.specifyVertex(8, new cgVector(dx, dy, dz + 4.5));
        this.m_model.specifyVertex(9, new cgVector(dx - 0.25, n3, dz));
        this.m_model.specifyVertex(10, new cgVector(dx - 0.25, n3, dz + 0.75));
        this.m_model.specifyVertex(11, new cgVector(dx + 0.25, n3, dz + 0.75));
        this.m_model.specifyVertex(12, new cgVector(dx + 0.25, n3, dz));
        this.m_model.specifyVertex(13, new cgVector(n, dy + 0.25, dz));
        this.m_model.specifyVertex(14, new cgVector(n, dy + 0.25, dz + 0.75));
        this.m_model.specifyVertex(15, new cgVector(n, dy - 0.25, dz + 0.75));
        this.m_model.specifyVertex(16, new cgVector(n, dy - 0.25, dz));
        this.m_model.specifyVertex(17, new cgVector(dx + 0.25, n4, dz));
        this.m_model.specifyVertex(18, new cgVector(dx + 0.25, n4, dz + 0.75));
        this.m_model.specifyVertex(19, new cgVector(dx - 0.25, n4, dz + 0.75));
        this.m_model.specifyVertex(20, new cgVector(dx - 0.25, n4, dz));
        this.m_model.specifyVertex(21, new cgVector(n2, dy - 0.25, dz));
        this.m_model.specifyVertex(22, new cgVector(n2, dy - 0.25, dz + 0.75));
        this.m_model.specifyVertex(23, new cgVector(n2, dy + 0.25, dz + 0.75));
        this.m_model.specifyVertex(24, new cgVector(n2, dy + 0.25, dz));
        final int[] array = { 0, 4, 5, 1 };
        final int[] array2 = { 1, 5, 6, 2 };
        final int[] array3 = { 2, 6, 7, 3 };
        final int[] array4 = { 3, 7, 4, 0 };
        final int[] array5 = { 4, 8, 5 };
        final int[] array6 = { 5, 8, 6 };
        final int[] array7 = { 6, 8, 7 };
        final int[] array8 = { 7, 8, 4 };
        final int[] array9 = { 9, 10, 11, 12 };
        final int[] array10 = { 13, 14, 15, 16 };
        final int[] array11 = { 17, 18, 19, 20 };
        final int[] array12 = { 21, 22, 23, 24 };
        this.m_model.specifyFace(0, new cgFace(4, array, this.WALL_COLOR, null));
        this.m_model.specifyFace(1, new cgFace(4, array2, this.WALL_COLOR, null));
        this.m_model.specifyFace(2, new cgFace(4, array3, this.WALL_COLOR, null));
        this.m_model.specifyFace(3, new cgFace(4, array4, this.WALL_COLOR, null));
        this.m_model.specifyFace(4, new cgFace(3, array5, this.ROOF_COLOR, null));
        this.m_model.specifyFace(5, new cgFace(3, array6, this.ROOF_COLOR, null));
        this.m_model.specifyFace(6, new cgFace(3, array7, this.ROOF_COLOR, null));
        this.m_model.specifyFace(7, new cgFace(3, array8, this.ROOF_COLOR, null));
        this.m_model.specifyFace(8, new cgFace(4, array9, this.DOOR_COLOR, null));
        this.m_model.specifyFace(9, new cgFace(4, array10, this.DOOR_COLOR, null));
        this.m_model.specifyFace(10, new cgFace(4, array11, this.DOOR_COLOR, null));
        this.m_model.specifyFace(11, new cgFace(4, array12, this.DOOR_COLOR, null));
        (this.m_object = new cgObject(this.m_model)).transform();
        if (House3D.m_modelFlag == null) {
            (House3D.m_modelFlag = new cgModel(4, 2)).specifyVertex(0, new cgVector(0.0, 0.0, 4.5));
            House3D.m_modelFlag.specifyVertex(1, new cgVector(0.0, 0.0, 5.25));
            House3D.m_modelFlag.specifyVertex(2, new cgVector(0.0, 0.0, 6.0));
            House3D.m_modelFlag.specifyVertex(3, new cgVector(1.5, 0.0, 5.625));
            final int[] array13 = { 0, 2 };
            final int[] array14 = { 1, 2, 3 };
            House3D.m_modelFlag.specifyFace(0, new cgFace(2, array13, Color.white, null));
            final cgFace cgFace2;
            final cgFace cgFace = cgFace2 = new cgFace(3, array14, this.FLAG_COLOR, this.FLAG_COLOR);
            cgFace2.m_nFlags |= 0x4;
            House3D.m_modelFlag.specifyFace(1, cgFace);
        }
        this.m_objectFlag1 = new cgObject(House3D.m_modelFlag);
        this.m_objectFlag1.m_vector.m_dX = dx;
        this.m_objectFlag1.m_vector.m_dY = dy;
        this.m_objectFlag1.m_vector.m_dZ = dz;
        this.m_objectFlag2 = new cgObject(this.m_objectFlag1);
        this.m_objectFlag2.m_bSortPolys = false;
    }
    
    public void renderFlagChain(final cgViewpoint cgViewpoint) {
        cgViewpoint.renderObject(this.m_objectFlag2);
        if (this.m_houseNext != null) {
            this.m_houseNext.renderFlagChain(cgViewpoint);
        }
    }
}
