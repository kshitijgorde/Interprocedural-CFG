import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Windmill
{
    final int X_CENTER = 0;
    final int Y_CENTER = 1;
    final int ENTRY_ANGLE = 2;
    final int Z_BASE = 3;
    final double BASE_WIDTH = 4.0;
    final double LINTEL_WIDTH = 3.6;
    final double LINTEL_HEIGHT = 0.75;
    final double TOP_WIDTH = 2.5;
    final double WALL_HEIGHT = 3.0;
    final Color WALL_COLOR;
    final double ROOF_HEIGHT = 4.5;
    final Color ROOF_COLOR;
    final double ENTRY_WIDTH = 0.5;
    final Color ENTRY_COLOR;
    final Color OUTLET_COLOR;
    final double HUB_HEIGHT = 3.0;
    final int BLADES = 4;
    final double BLADE_WIDTH = 0.4;
    final long BLADE_PERIOD = 8000L;
    final Color BLADE_COLOR;
    final Color AXLE_COLOR;
    final double EXIT_VELOCITY = 5.0;
    final int POS_Y = 0;
    final int POS_X = 90;
    final int NEG_Y = 180;
    final int NEG_X = 270;
    final long LATENCY = 750L;
    public Windmill m_windmillNext;
    double m_dXmin;
    double m_dXmax;
    double m_dYmin;
    double m_dYmax;
    double m_dXcenter;
    double m_dYcenter;
    int m_nEntryAngle;
    int m_nOutletAngle;
    long m_lReleaseTime;
    static cgModel m_model;
    public cgObject m_object;
    static cgModel m_modelBlades;
    public cgObject m_objectBlades1;
    cgObject m_objectBlades2;
    cgMatrix m_matrixReference;
    cgMatrix m_matrixRotate;
    
    private boolean entryBlocked(final long n) {
        final double n2 = 1.5707963267948966;
        final double n3 = n2 / 2.0;
        double abs = this.bladeAngle(n) % n2;
        if (abs > n3) {
            abs = Math.abs(abs - n2);
        }
        return abs <= 0.2;
    }
    
    private void transformBlade(final double n) {
        this.m_matrixRotate.m_dXx = this.m_matrixReference.m_dXx;
        this.m_matrixRotate.m_dXy = this.m_matrixReference.m_dXy;
        this.m_matrixRotate.m_dXz = this.m_matrixReference.m_dXz;
        this.m_matrixRotate.m_dYx = this.m_matrixReference.m_dYx;
        this.m_matrixRotate.m_dYy = this.m_matrixReference.m_dYy;
        this.m_matrixRotate.m_dYz = this.m_matrixReference.m_dYz;
        this.m_matrixRotate.m_dZx = this.m_matrixReference.m_dZx;
        this.m_matrixRotate.m_dZy = this.m_matrixReference.m_dZy;
        this.m_matrixRotate.m_dZz = this.m_matrixReference.m_dZz;
        this.m_matrixRotate.rotateSelf(0.0, n, 0.0);
        this.m_objectBlades1.transform();
        if (this.m_windmillNext != null) {
            this.m_windmillNext.transformBlade(n);
        }
    }
    
    public void renderBladeChain(final cgViewpoint cgViewpoint) {
        cgViewpoint.renderObject(this.m_objectBlades2);
        if (this.m_windmillNext != null) {
            this.m_windmillNext.renderBladeChain(cgViewpoint);
        }
    }
    
    public boolean golfballPhysics(final Golfball3D golfball3D, final long n) {
        if (n >= this.m_lReleaseTime) {
            switch (this.m_nOutletAngle) {
                case 90: {
                    golfball3D.m_dX0 = this.m_dXmax;
                    golfball3D.m_dY0 = (this.m_dYmin + this.m_dYmax) / 2.0;
                    golfball3D.m_dAngle = 0.0;
                    break;
                }
                case 0: {
                    golfball3D.m_dX0 = (this.m_dXmin + this.m_dXmax) / 2.0;
                    golfball3D.m_dY0 = this.m_dYmax;
                    golfball3D.m_dAngle = 1.5707963267948966;
                    break;
                }
                case 270: {
                    golfball3D.m_dX0 = this.m_dXmin;
                    golfball3D.m_dY0 = (this.m_dYmin + this.m_dYmax) / 2.0;
                    golfball3D.m_dAngle = 3.141592653589793;
                    break;
                }
                case 180: {
                    golfball3D.m_dX0 = (this.m_dXmin + this.m_dXmax) / 2.0;
                    golfball3D.m_dY0 = this.m_dYmin;
                    golfball3D.m_dAngle = 4.71238898038469;
                    break;
                }
            }
            golfball3D.m_dAngle += (Math.random() - 0.5) * 0.25;
            golfball3D.m_windmill = null;
            golfball3D.m_dVelocity = 5.0 * (1.0 + (Math.random() - 0.5) * 0.25);
            return true;
        }
        return false;
    }
    
    public void transformBladeChain(final long n) {
        this.transformBlade(this.bladeAngle(n));
    }
    
    public Windmill(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.WALL_COLOR = Color.pink;
        this.ROOF_COLOR = new Color(128, 0, 128);
        this.ENTRY_COLOR = Color.black;
        this.OUTLET_COLOR = Color.black;
        this.BLADE_COLOR = Color.white;
        this.AXLE_COLOR = Color.white;
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length < 3 || numericArgs.length > 4) {
            throw new ParsingException("Windmill needs 3 or 4 arguments");
        }
        this.m_dXcenter = numericArgs[0];
        this.m_dYcenter = numericArgs[1];
        final double dx = this.m_dXcenter - 2.0;
        final double dx2 = this.m_dXcenter + 2.0;
        final double dy = this.m_dYcenter - 2.0;
        final double dy2 = this.m_dYcenter + 2.0;
        this.m_dXmin = dx - 0.075;
        this.m_dXmax = dx2 + 0.075;
        this.m_dYmin = dy - 0.075;
        this.m_dYmax = dy2 + 0.075;
        this.m_nEntryAngle = (int)numericArgs[2];
        double dz;
        if (numericArgs.length == 4) {
            dz = numericArgs[3];
        }
        else {
            dz = 0.0;
        }
        if (Windmill.m_model == null) {
            (Windmill.m_model = new cgModel(19, 14)).specifyVertex(0, new cgVector(-2.0, -2.0, 0.0));
            Windmill.m_model.specifyVertex(1, new cgVector(2.0, -2.0, 0.0));
            Windmill.m_model.specifyVertex(2, new cgVector(2.0, 2.0, 0.0));
            Windmill.m_model.specifyVertex(3, new cgVector(-2.0, 2.0, 0.0));
            Windmill.m_model.specifyVertex(4, new cgVector(-1.25, -1.25, 3.0));
            Windmill.m_model.specifyVertex(5, new cgVector(1.25, -1.25, 3.0));
            Windmill.m_model.specifyVertex(6, new cgVector(1.25, 1.25, 3.0));
            Windmill.m_model.specifyVertex(7, new cgVector(-1.25, 1.25, 3.0));
            Windmill.m_model.specifyVertex(8, new cgVector(-1.8, -1.8, 0.75));
            Windmill.m_model.specifyVertex(9, new cgVector(1.8, -1.8, 0.75));
            Windmill.m_model.specifyVertex(10, new cgVector(1.8, 1.8, 0.75));
            Windmill.m_model.specifyVertex(11, new cgVector(-1.8, 1.8, 0.75));
            Windmill.m_model.specifyVertex(12, new cgVector(0.0, 0.0, 4.5));
            Windmill.m_model.specifyVertex(13, new cgVector(-0.25, 2.0, 0.0));
            Windmill.m_model.specifyVertex(14, new cgVector(0.25, 2.0, 0.0));
            Windmill.m_model.specifyVertex(15, new cgVector(0.0, 1.8, 0.75));
            Windmill.m_model.specifyVertex(16, new cgVector(0.0, -1.8, 0.75));
            Windmill.m_model.specifyVertex(17, new cgVector(-0.25, -2.0, 0.0));
            Windmill.m_model.specifyVertex(18, new cgVector(0.25, -2.0, 0.0));
            Windmill.m_model.specifyFace(0, new cgFace(4, this.af(0, 8, 16, 17), this.WALL_COLOR, null));
            Windmill.m_model.specifyFace(1, new cgFace(4, this.af(1, 18, 16, 9), this.WALL_COLOR, null));
            Windmill.m_model.specifyFace(2, new cgFace(4, this.af(1, 5, 6, 2), this.WALL_COLOR, null, 1));
            Windmill.m_model.specifyFace(3, new cgFace(4, this.af(2, 10, 15, 14), this.WALL_COLOR, null));
            Windmill.m_model.specifyFace(4, new cgFace(4, this.af(3, 13, 15, 11), this.WALL_COLOR, null));
            Windmill.m_model.specifyFace(5, new cgFace(4, this.af(3, 7, 4, 0), this.WALL_COLOR, null, 2));
            Windmill.m_model.specifyFace(6, new cgFace(4, this.af(4, 5, 9, 8), this.WALL_COLOR, null, 0));
            Windmill.m_model.specifyFace(7, new cgFace(4, this.af(6, 7, 11, 10), this.WALL_COLOR, null));
            Windmill.m_model.specifyFace(8, new cgFace(3, this.af(6, 12, 7), this.ROOF_COLOR, null, 1));
            Windmill.m_model.specifyFace(9, new cgFace(3, this.af(7, 12, 4), this.ROOF_COLOR, null, 1));
            Windmill.m_model.specifyFace(10, new cgFace(3, this.af(4, 12, 5), this.ROOF_COLOR, null, 1));
            Windmill.m_model.specifyFace(11, new cgFace(3, this.af(5, 12, 6), this.ROOF_COLOR, null, 1));
            Windmill.m_model.specifyFace(12, new cgFace(3, this.af(13, 14, 15), this.ENTRY_COLOR, null));
            Windmill.m_model.specifyFace(13, new cgFace(3, this.af(16, 18, 17), this.OUTLET_COLOR, null));
        }
        this.m_object = new cgObject(Windmill.m_model);
        this.m_object.m_vector.m_dX = this.m_dXcenter;
        this.m_object.m_vector.m_dY = this.m_dYcenter;
        this.m_object.m_vector.m_dZ = dz;
        switch (this.m_nEntryAngle) {
            case 0: {
                this.m_nOutletAngle = 180;
                break;
            }
            case 90: {
                this.m_object.m_matrix.rotateWorld(0.0, 0.0, -1.5707963267948966);
                this.m_nOutletAngle = 270;
                break;
            }
            case 180: {
                this.m_object.m_matrix.rotateWorld(0.0, 0.0, 3.141592653589793);
                this.m_nOutletAngle = 0;
                break;
            }
            case 270: {
                this.m_object.m_matrix.rotateWorld(0.0, 0.0, 1.5707963267948966);
                this.m_nOutletAngle = 90;
                break;
            }
            default: {
                throw new ParsingException("Windmill angle must be 0,90,180, or 270");
            }
        }
        this.m_object.transform();
        if (Windmill.m_modelBlades == null) {
            (Windmill.m_modelBlades = new cgModel(10, 5)).specifyVertex(0, new cgVector(0.0, 0.0, 0.0));
            Windmill.m_modelBlades.specifyVertex(1, new cgVector(0.0, -0.5, 0.0));
            int n = 0;
            do {
                final double n2 = n / 4.0 * 3.141592653589793 * 2.0 - 1.5707963267948966;
                Windmill.m_modelBlades.specifyVertex(n * 2 + 2, new cgVector(-3.0 * Math.sin(n2 - 0.2), 0.0, 3.0 * Math.cos(n2 - 0.2)));
                Windmill.m_modelBlades.specifyVertex(n * 2 + 3, new cgVector(-3.0 * Math.sin(n2 + 0.2), 0.0, 3.0 * Math.cos(n2 + 0.2)));
            } while (++n < 4);
            Windmill.m_modelBlades.specifyFace(0, new cgFace(2, new int[] { 0, 1 }, this.AXLE_COLOR, null));
            int n3 = 0;
            do {
                final cgFace cgFace2;
                final cgFace cgFace = cgFace2 = new cgFace(3, new int[] { 0, n3 * 2 + 3, n3 * 2 + 2 }, this.BLADE_COLOR, this.BLADE_COLOR, 0);
                cgFace2.m_nFlags |= 0x4;
                Windmill.m_modelBlades.specifyFace(n3 + 1, cgFace);
            } while (++n3 < 4);
        }
        this.m_objectBlades1 = new cgObject(Windmill.m_modelBlades);
        this.m_matrixReference = new cgMatrix();
        this.m_matrixRotate = new cgMatrix();
        this.m_objectBlades1.m_matrix = this.m_matrixRotate;
        switch (this.m_nEntryAngle) {
            case 0: {
                this.m_objectBlades1.m_vector.m_dX = this.m_dXcenter;
                this.m_objectBlades1.m_vector.m_dY = dy2;
                this.m_objectBlades1.m_vector.m_dZ = dz + 3.0;
                break;
            }
            case 90: {
                this.m_objectBlades1.m_vector.m_dX = dx2;
                this.m_objectBlades1.m_vector.m_dY = this.m_dYcenter;
                this.m_objectBlades1.m_vector.m_dZ = dz + 3.0;
                this.m_matrixReference.rotateWorld(0.0, 0.0, -1.5707963267948966);
                break;
            }
            case 180: {
                this.m_objectBlades1.m_vector.m_dX = this.m_dXcenter;
                this.m_objectBlades1.m_vector.m_dY = dy;
                this.m_objectBlades1.m_vector.m_dZ = dz + 3.0;
                this.m_matrixReference.rotateWorld(0.0, 0.0, 3.141592653589793);
                break;
            }
            case 270: {
                this.m_objectBlades1.m_vector.m_dX = dx;
                this.m_objectBlades1.m_vector.m_dY = this.m_dYcenter;
                this.m_objectBlades1.m_vector.m_dZ = dz + 3.0;
                this.m_matrixReference.rotateWorld(0.0, 0.0, 1.5707963267948966);
                break;
            }
        }
        this.m_objectBlades1.transform();
        this.m_objectBlades2 = new cgObject(this.m_objectBlades1);
        this.m_objectBlades2.m_bSortPolys = false;
    }
    
    public double collisionAnalysis(final Golfball3D golfball3D, final boolean b, final long n) {
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
                    if (((dx0 == this.m_dXmax && this.m_nEntryAngle == 90) || (dx0 == this.m_dXmin && this.m_nEntryAngle == 270)) && n2 >= this.m_dYcenter - 0.25 && n2 <= this.m_dYcenter + 0.25 && !this.entryBlocked(n)) {
                        this.enterWindmill(golfball3D, n);
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
                    if (((n2 == this.m_dYmax && this.m_nEntryAngle == 0) || (n2 == this.m_dYmin && this.m_nEntryAngle == 180)) && dx2 >= this.m_dXcenter - 0.25 && dx2 <= this.m_dXcenter + 0.25 && !this.entryBlocked(n)) {
                        this.enterWindmill(golfball3D, n);
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
    
    private int[] af(final int n, final int n2, final int n3) {
        return new int[] { n, n2, n3 };
    }
    
    private int[] af(final int n, final int n2, final int n3, final int n4) {
        return new int[] { n, n2, n3, n4 };
    }
    
    private double bladeAngle(final long n) {
        return n % 8000L / 8000.0 * 3.141592653589793 * 2.0;
    }
    
    private void enterWindmill(final Golfball3D golfball3D, final long n) {
        golfball3D.m_windmill = this;
        golfball3D.m_dX0 = (this.m_dXmin + this.m_dXmax) / 2.0;
        golfball3D.m_dY0 = (this.m_dYmin + this.m_dYmax) / 2.0;
        this.m_lReleaseTime = n + 750L;
    }
}
