import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Barrier3D
{
    final int BARRIER_X0 = 0;
    final int BARRIER_Y0 = 1;
    final int BARRIER_Z0 = 2;
    final int BARRIER_X1 = 3;
    final int BARRIER_Y1 = 4;
    final int BARRIER_Z1 = 5;
    final double BARRIER_THICKNESS = 0.25;
    final double BARRIER_HEIGHT = 0.25;
    public static final Color BARRIER_COLOR;
    public Barrier3D m_barrierNext;
    double m_dXmin;
    double m_dXmax;
    double m_dYmin;
    double m_dYmax;
    boolean m_bAligned;
    double m_dX0;
    double m_dY0;
    double m_dXsize;
    double m_dYsize;
    double m_dAngle;
    double m_dCos;
    double m_dSin;
    double m_dXorigin;
    double m_dYorigin;
    double m_dLength;
    final double m_dYtrans;
    double[] m_dXdraw;
    double[] m_dYdraw;
    cgModel m_model;
    public cgObject m_object;
    
    public double CollisionAnalysis(final Golfball3D golfball3D, final boolean b) {
        double dx0 = 0.0;
        double n = 0.0;
        if (golfball3D.m_dXmin >= this.m_dXmax || golfball3D.m_dXmax <= this.m_dXmin || golfball3D.m_dYmin >= this.m_dYmax || golfball3D.m_dYmax <= this.m_dYmin) {
            return -1.0;
        }
        if (this.m_bAligned) {
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
                n = golfball3D.m_dY0 + (golfball3D.m_dY1 - golfball3D.m_dY0) * (dx0 - golfball3D.m_dX0) / (golfball3D.m_dX1 - golfball3D.m_dX0);
                if (n > this.m_dYmin && n < this.m_dYmax) {
                    final double sqrt = Math.sqrt(Math.pow(dx0 - golfball3D.m_dX0, 2.0) + Math.pow(n - golfball3D.m_dY0, 2.0));
                    if (b) {
                        golfball3D.m_dAngle = Math.atan2(n - golfball3D.m_dY0, -(dx0 - golfball3D.m_dX0));
                        golfball3D.m_dX0 = dx0;
                        golfball3D.m_dY0 = n;
                        golfball3D.m_dVelocity -= (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity) * (sqrt / golfball3D.m_dDistance);
                        golfball3D.m_dDistance -= sqrt;
                        golfball3D.m_dX1 = golfball3D.m_dX0 + Math.cos(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dY1 = golfball3D.m_dY0 + Math.sin(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dXmin = Math.min(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmin = Math.min(golfball3D.m_dY0, golfball3D.m_dY1);
                        golfball3D.m_dXmax = Math.max(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmax = Math.max(golfball3D.m_dY0, golfball3D.m_dY1);
                    }
                    return sqrt;
                }
            }
            boolean b3 = false;
            if (golfball3D.m_dY1 > golfball3D.m_dY0 && golfball3D.m_dY0 < this.m_dYmin) {
                b3 = true;
                n = this.m_dYmin;
            }
            if (golfball3D.m_dY1 < golfball3D.m_dY0 && golfball3D.m_dY0 > this.m_dYmax) {
                b3 = true;
                n = this.m_dYmax;
            }
            if (b3) {
                final double dx2 = golfball3D.m_dX0 + (golfball3D.m_dX1 - golfball3D.m_dX0) * (n - golfball3D.m_dY0) / (golfball3D.m_dY1 - golfball3D.m_dY0);
                if (dx2 > this.m_dXmin && dx2 < this.m_dXmax) {
                    final double sqrt2 = Math.sqrt(Math.pow(dx2 - golfball3D.m_dX0, 2.0) + Math.pow(n - golfball3D.m_dY0, 2.0));
                    if (b) {
                        golfball3D.m_dAngle = Math.atan2(-(n - golfball3D.m_dY0), dx2 - golfball3D.m_dX0);
                        golfball3D.m_dX0 = dx2;
                        golfball3D.m_dY0 = n;
                        golfball3D.m_dVelocity -= (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity) * (sqrt2 / golfball3D.m_dDistance);
                        golfball3D.m_dDistance -= sqrt2;
                        golfball3D.m_dX1 = golfball3D.m_dX0 + Math.cos(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dY1 = golfball3D.m_dY0 + Math.sin(golfball3D.m_dAngle) * golfball3D.m_dDistance;
                        golfball3D.m_dXmin = Math.min(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmin = Math.min(golfball3D.m_dY0, golfball3D.m_dY1);
                        golfball3D.m_dXmax = Math.max(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmax = Math.max(golfball3D.m_dY0, golfball3D.m_dY1);
                    }
                    return sqrt2;
                }
            }
        }
        else {
            final double n2 = (golfball3D.m_dX0 - this.m_dXorigin) * this.m_dCos + (golfball3D.m_dY0 - this.m_dYorigin) * this.m_dSin;
            final double n3 = (golfball3D.m_dY0 - this.m_dYorigin) * this.m_dCos - (golfball3D.m_dX0 - this.m_dXorigin) * this.m_dSin;
            final double n4 = (golfball3D.m_dX1 - this.m_dXorigin) * this.m_dCos + (golfball3D.m_dY1 - this.m_dYorigin) * this.m_dSin;
            final double n5 = (golfball3D.m_dY1 - this.m_dYorigin) * this.m_dCos - (golfball3D.m_dX1 - this.m_dXorigin) * this.m_dSin;
            final double min = Math.min(n2, n4);
            final double max = Math.max(n2, n4);
            final double min2 = Math.min(n3, n5);
            final double max2 = Math.max(n3, n5);
            if (min >= this.m_dLength || max <= 0.0 || min2 >= this.m_dYtrans || max2 <= -this.m_dYtrans) {
                return -1.0;
            }
            boolean b4 = false;
            if (n4 > n2 && n2 < 0.0) {
                b4 = true;
                dx0 = 0.0;
            }
            if (n4 < n2 && n2 > this.m_dLength) {
                b4 = true;
                dx0 = this.m_dLength;
            }
            if (b4) {
                n = n3 + (n5 - n3) * (dx0 - n2) / (n4 - n2);
                if (n > -this.m_dYtrans && n < this.m_dYtrans) {
                    final double sqrt3 = Math.sqrt(Math.pow(dx0 - n2, 2.0) + Math.pow(n - n3, 2.0));
                    if (b) {
                        final double atan2 = Math.atan2(n - n3, -(dx0 - n2));
                        final double n6 = dx0;
                        final double n7 = n;
                        golfball3D.m_dVelocity -= (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity) * (sqrt3 / golfball3D.m_dDistance);
                        golfball3D.m_dDistance -= sqrt3;
                        final double n8 = n6 + Math.cos(atan2) * golfball3D.m_dDistance;
                        final double n9 = n7 + Math.sin(atan2) * golfball3D.m_dDistance;
                        golfball3D.m_dX0 = n6 * this.m_dCos - n7 * this.m_dSin + this.m_dXorigin;
                        golfball3D.m_dY0 = n7 * this.m_dCos + n6 * this.m_dSin + this.m_dYorigin;
                        golfball3D.m_dX1 = n8 * this.m_dCos - n9 * this.m_dSin + this.m_dXorigin;
                        golfball3D.m_dY1 = n9 * this.m_dCos + n8 * this.m_dSin + this.m_dYorigin;
                        golfball3D.m_dAngle = Math.atan2(golfball3D.m_dY1 - golfball3D.m_dY0, golfball3D.m_dX1 - golfball3D.m_dX0);
                        golfball3D.m_dXmin = Math.min(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmin = Math.min(golfball3D.m_dY0, golfball3D.m_dY1);
                        golfball3D.m_dXmax = Math.max(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmax = Math.max(golfball3D.m_dY0, golfball3D.m_dY1);
                    }
                    return sqrt3;
                }
            }
            boolean b5 = false;
            if (n5 > n3 && n3 < -this.m_dYtrans) {
                b5 = true;
                n = -this.m_dYtrans;
            }
            if (n5 < n3 && n3 > this.m_dYtrans) {
                b5 = true;
                n = this.m_dYtrans;
            }
            if (b5) {
                final double n10 = n2 + (n4 - n2) * (n - n3) / (n5 - n3);
                if (n10 > 0.0 && n10 < this.m_dLength) {
                    final double sqrt4 = Math.sqrt(Math.pow(n10 - n2, 2.0) + Math.pow(n - n3, 2.0));
                    if (b) {
                        final double atan3 = Math.atan2(-(n - n3), n10 - n2);
                        final double n11 = n10;
                        final double n12 = n;
                        golfball3D.m_dVelocity -= (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity) * (sqrt4 / golfball3D.m_dDistance);
                        golfball3D.m_dDistance -= sqrt4;
                        final double n13 = n11 + Math.cos(atan3) * golfball3D.m_dDistance;
                        final double n14 = n12 + Math.sin(atan3) * golfball3D.m_dDistance;
                        golfball3D.m_dX0 = n11 * this.m_dCos - n12 * this.m_dSin + this.m_dXorigin;
                        golfball3D.m_dY0 = n12 * this.m_dCos + n11 * this.m_dSin + this.m_dYorigin;
                        golfball3D.m_dX1 = n13 * this.m_dCos - n14 * this.m_dSin + this.m_dXorigin;
                        golfball3D.m_dY1 = n14 * this.m_dCos + n13 * this.m_dSin + this.m_dYorigin;
                        golfball3D.m_dAngle = Math.atan2(golfball3D.m_dY1 - golfball3D.m_dY0, golfball3D.m_dX1 - golfball3D.m_dX0);
                        golfball3D.m_dXmin = Math.min(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmin = Math.min(golfball3D.m_dY0, golfball3D.m_dY1);
                        golfball3D.m_dXmax = Math.max(golfball3D.m_dX0, golfball3D.m_dX1);
                        golfball3D.m_dYmax = Math.max(golfball3D.m_dY0, golfball3D.m_dY1);
                    }
                    return sqrt4;
                }
            }
        }
        return -1.0;
    }
    
    static {
        BARRIER_COLOR = new Color(16777062);
    }
    
    public Barrier3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.m_bAligned = true;
        this.m_dYtrans = 0.1625;
        final int n = 100;
        boolean b = true;
        boolean b2 = true;
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length != 4 && numericArgs.length % 3 != 0) {
            throw new ParsingException("Wrong number of Barrier args");
        }
        int n2;
        double[] array;
        if (numericArgs.length == 4) {
            n2 = 2;
            array = new double[] { numericArgs[0], numericArgs[1], 0.0, numericArgs[2], numericArgs[3], 0.0 };
        }
        else {
            n2 = numericArgs.length / 3;
            array = numericArgs;
        }
        for (int i = 1; i < n2; ++i) {
            if (array[0] != array[i * 3 + 0]) {
                b2 = false;
            }
            if (array[1] != array[i * 3 + 1]) {
                b = false;
            }
        }
        if (n2 > 2 && !b && !b2) {
            throw new ParsingException("Barrier not straight in XY plane");
        }
        final int n3 = 3 * (n2 - 1) + 0;
        final int n4 = 3 * (n2 - 1) + 1;
        if (b) {
            if (array[0] > array[n3]) {
                for (int j = 0; j < n2 / 2; ++j) {
                    final int n5 = n2 - j - 1;
                    final double n6 = array[j * 3 + 0];
                    final double n7 = array[j * 3 + 1];
                    final double n8 = array[j * 3 + 2];
                    array[j * 3 + 0] = array[n5 * 3 + 0];
                    array[j * 3 + 1] = array[n5 * 3 + 1];
                    array[j * 3 + 2] = array[n5 * 3 + 2];
                    array[n5 * 3 + 0] = n6;
                    array[n5 * 3 + 1] = n7;
                    array[n5 * 3 + 2] = n8;
                }
            }
            final double dXsize = array[n3] - array[0];
            final int n9 = n2 + (int)(dXsize / 5.0) + 1;
            if (n9 > n) {
                throw new ParsingException("Barrier too long or too complex");
            }
            final double[] array2 = new double[3 * n9];
            array2[0] = array[0];
            array2[1] = array[1];
            array2[2] = array[2];
            int n10 = 1;
            for (int k = 0; k < n2 - 1; ++k) {
                if (array[k * 3 + 0] >= array[(k + 1) * 3 + 0]) {
                    throw new ParsingException("Barrier points out of order");
                }
                if (array[k * 3 + 2] != array[(k + 1) * 3 + 2]) {
                    array2[n10 * 3 + 0] = array[(k + 1) * 3 + 0];
                    array2[n10 * 3 + 1] = array[(k + 1) * 3 + 1];
                    array2[n10 * 3 + 2] = array[(k + 1) * 3 + 2];
                    ++n10;
                }
                else {
                    final double[] tessellateRange = CarpetGolf3D.tessellateRange(array[k * 3 + 0], array[(k + 1) * 3 + 0]);
                    for (int l = 1; l < tessellateRange.length; ++l) {
                        array2[n10 * 3 + 0] = tessellateRange[l];
                        array2[n10 * 3 + 1] = array[k * 3 + 1];
                        array2[n10 * 3 + 2] = array[k * 3 + 2];
                        ++n10;
                    }
                }
            }
            final int n11 = n10;
            this.m_dX0 = array2[0];
            this.m_dY0 = array2[1] - 0.125;
            this.m_dXsize = dXsize;
            this.m_dYsize = 0.25;
            this.m_dXmin = this.m_dX0 - 0.075;
            this.m_dXmax = this.m_dX0 + this.m_dXsize + 0.075;
            this.m_dYmin = this.m_dY0 - 0.075;
            this.m_dYmax = this.m_dY0 + this.m_dYsize + 0.075;
            this.m_model = new cgModel(n11 * 4, (n11 - 1) * 3 + 2);
            for (int n12 = 0; n12 < n11; ++n12) {
                final double n13 = array2[n12 * 3 + 0];
                final double n14 = array2[n12 * 3 + 1];
                final double n15 = array2[n12 * 3 + 2];
                this.m_model.specifyVertex(n12 * 4, new cgVector(n13, n14 - 0.125, n15));
                this.m_model.specifyVertex(n12 * 4 + 1, new cgVector(n13, n14 - 0.125, n15 + 0.25));
                this.m_model.specifyVertex(n12 * 4 + 2, new cgVector(n13, n14 + 0.125, n15 + 0.25));
                this.m_model.specifyVertex(n12 * 4 + 3, new cgVector(n13, n14 + 0.125, n15));
            }
            for (int n16 = 0; n16 < n11 - 1; ++n16) {
                int n17 = 0;
                do {
                    this.m_model.specifyFace(n16 * 3 + n17, new cgFace(4, new int[] { n16 * 4 + n17, n16 * 4 + n17 + 1, (n16 + 1) * 4 + n17 + 1, (n16 + 1) * 4 + n17 }, Barrier3D.BARRIER_COLOR, null));
                } while (++n17 < 3);
            }
            this.m_model.specifyFace((n11 - 1) * 3, new cgFace(4, new int[] { 3, 2, 1, 0 }, Barrier3D.BARRIER_COLOR, null));
            this.m_model.specifyFace((n11 - 1) * 3 + 1, new cgFace(4, new int[] { (n11 - 1) * 4, (n11 - 1) * 4 + 1, (n11 - 1) * 4 + 2, (n11 - 1) * 4 + 3 }, Barrier3D.BARRIER_COLOR, null));
            (this.m_object = new cgObject(this.m_model)).transform();
        }
        else {
            if (!b2) {
                this.m_bAligned = false;
                this.m_dXdraw = new double[4];
                this.m_dYdraw = new double[4];
                this.m_dAngle = Math.atan2(array[4] - array[1], array[3] - array[0]);
                this.m_dSin = Math.sin(this.m_dAngle);
                this.m_dCos = Math.cos(this.m_dAngle);
                final double n18 = array[0];
                final double n19 = array[1];
                final double n20 = array[3];
                final double n21 = array[4];
                this.m_dXdraw[0] = n18 - this.m_dSin * 0.125;
                this.m_dYdraw[0] = n19 + this.m_dCos * 0.125;
                this.m_dXdraw[1] = n20 - this.m_dSin * 0.125;
                this.m_dYdraw[1] = n21 + this.m_dCos * 0.125;
                this.m_dXdraw[2] = n20 + this.m_dSin * 0.125;
                this.m_dYdraw[2] = n21 - this.m_dCos * 0.125;
                this.m_dXdraw[3] = n18 + this.m_dSin * 0.125;
                this.m_dYdraw[3] = n19 - this.m_dCos * 0.125;
                final double[] array3 = new double[4];
                final double[] array4 = new double[4];
                final double n22 = array[0] - this.m_dYtrans * this.m_dCos;
                final double n23 = array[1] - this.m_dYtrans * this.m_dSin;
                final double n24 = array[3] + this.m_dYtrans * this.m_dCos;
                final double n25 = array[4] + this.m_dYtrans * this.m_dSin;
                array3[0] = n22 - this.m_dSin * this.m_dYtrans;
                array4[0] = n23 + this.m_dCos * this.m_dYtrans;
                array3[1] = n24 - this.m_dSin * this.m_dYtrans;
                array4[1] = n25 + this.m_dCos * this.m_dYtrans;
                array3[2] = n24 + this.m_dSin * this.m_dYtrans;
                array4[2] = n25 - this.m_dCos * this.m_dYtrans;
                array3[3] = n22 + this.m_dSin * this.m_dYtrans;
                array4[3] = n23 - this.m_dCos * this.m_dYtrans;
                this.m_dXmin = array3[0];
                this.m_dYmin = array4[0];
                this.m_dXmax = this.m_dXmin;
                this.m_dYmax = this.m_dYmin;
                int n26 = 1;
                do {
                    this.m_dXmin = Math.min(this.m_dXmin, array3[n26]);
                    this.m_dXmax = Math.max(this.m_dXmax, array3[n26]);
                    this.m_dYmin = Math.min(this.m_dYmin, array4[n26]);
                    this.m_dYmax = Math.max(this.m_dYmax, array4[n26]);
                } while (++n26 < 4);
                this.m_dXorigin = (array3[0] + array3[3]) / 2.0;
                this.m_dYorigin = (array4[0] + array4[3]) / 2.0;
                this.m_dLength = Math.sqrt(Math.pow(array3[1] - array3[0], 2.0) + Math.pow(array4[1] - array4[0], 2.0));
                (this.m_model = new cgModel(8, 5)).specifyVertex(0, new cgVector(this.m_dXdraw[3], this.m_dYdraw[3], array[2]));
                this.m_model.specifyVertex(1, new cgVector(this.m_dXdraw[2], this.m_dYdraw[2], array[5]));
                this.m_model.specifyVertex(2, new cgVector(this.m_dXdraw[1], this.m_dYdraw[1], array[5]));
                this.m_model.specifyVertex(3, new cgVector(this.m_dXdraw[0], this.m_dYdraw[0], array[2]));
                this.m_model.specifyVertex(4, new cgVector(this.m_dXdraw[3], this.m_dYdraw[3], array[2] + 0.25));
                this.m_model.specifyVertex(5, new cgVector(this.m_dXdraw[2], this.m_dYdraw[2], array[5] + 0.25));
                this.m_model.specifyVertex(6, new cgVector(this.m_dXdraw[1], this.m_dYdraw[1], array[5] + 0.25));
                this.m_model.specifyVertex(7, new cgVector(this.m_dXdraw[0], this.m_dYdraw[0], array[2] + 0.25));
                final int[] array5 = { 0, 4, 5, 1 };
                final int[] array6 = { 1, 5, 6, 2 };
                final int[] array7 = { 2, 6, 7, 3 };
                final int[] array8 = { 3, 7, 4, 0 };
                final int[] array9 = { 7, 6, 5, 4 };
                this.m_model.specifyFace(0, new cgFace(4, array5, Barrier3D.BARRIER_COLOR, null));
                this.m_model.specifyFace(1, new cgFace(4, array6, Barrier3D.BARRIER_COLOR, null));
                this.m_model.specifyFace(2, new cgFace(4, array7, Barrier3D.BARRIER_COLOR, null));
                this.m_model.specifyFace(3, new cgFace(4, array8, Barrier3D.BARRIER_COLOR, null));
                this.m_model.specifyFace(4, new cgFace(4, array9, Barrier3D.BARRIER_COLOR, null));
                (this.m_object = new cgObject(this.m_model)).transform();
                return;
            }
            if (array[1] > array[n4]) {
                for (int n27 = 0; n27 < n2 / 2; ++n27) {
                    final int n28 = n2 - n27 - 1;
                    final double n29 = array[n27 * 3 + 0];
                    final double n30 = array[n27 * 3 + 1];
                    final double n31 = array[n27 * 3 + 2];
                    array[n27 * 3 + 0] = array[n28 * 3 + 0];
                    array[n27 * 3 + 1] = array[n28 * 3 + 1];
                    array[n27 * 3 + 2] = array[n28 * 3 + 2];
                    array[n28 * 3 + 0] = n29;
                    array[n28 * 3 + 1] = n30;
                    array[n28 * 3 + 2] = n31;
                }
            }
            final double dYsize = array[n4] - array[1];
            final int n32 = n2 + (int)(dYsize / 5.0) + 1;
            if (n32 > n) {
                throw new ParsingException("Barrier too long or too complex");
            }
            final double[] array10 = new double[3 * n32];
            array10[0] = array[0];
            array10[1] = array[1];
            array10[2] = array[2];
            int n33 = 1;
            for (int n34 = 0; n34 < n2 - 1; ++n34) {
                if (array[n34 * 3 + 1] >= array[(n34 + 1) * 3 + 1]) {
                    throw new ParsingException("Barrier points out of order");
                }
                if (array[n34 * 3 + 2] != array[(n34 + 1) * 3 + 2]) {
                    array10[n33 * 3 + 0] = array[(n34 + 1) * 3 + 0];
                    array10[n33 * 3 + 1] = array[(n34 + 1) * 3 + 1];
                    array10[n33 * 3 + 2] = array[(n34 + 1) * 3 + 2];
                    ++n33;
                }
                else {
                    final double[] tessellateRange2 = CarpetGolf3D.tessellateRange(array[n34 * 3 + 1], array[(n34 + 1) * 3 + 1]);
                    for (int n35 = 1; n35 < tessellateRange2.length; ++n35) {
                        array10[n33 * 3 + 0] = array[n34 * 3 + 0];
                        array10[n33 * 3 + 1] = tessellateRange2[n35];
                        array10[n33 * 3 + 2] = array[n34 * 3 + 2];
                        ++n33;
                    }
                }
            }
            final int n36 = n33;
            this.m_dX0 = array10[0] - 0.125;
            this.m_dY0 = array10[1];
            this.m_dXsize = 0.25;
            this.m_dYsize = dYsize;
            this.m_dXmin = this.m_dX0 - 0.075;
            this.m_dXmax = this.m_dX0 + this.m_dXsize + 0.075;
            this.m_dYmin = this.m_dY0 - 0.075;
            this.m_dYmax = this.m_dY0 + this.m_dYsize + 0.075;
            this.m_model = new cgModel(n36 * 4, (n36 - 1) * 3 + 2);
            for (int n37 = 0; n37 < n36; ++n37) {
                final double n38 = array10[n37 * 3 + 0];
                final double n39 = array10[n37 * 3 + 1];
                final double n40 = array10[n37 * 3 + 2];
                this.m_model.specifyVertex(n37 * 4, new cgVector(n38 + 0.125, n39, n40));
                this.m_model.specifyVertex(n37 * 4 + 1, new cgVector(n38 + 0.125, n39, n40 + 0.25));
                this.m_model.specifyVertex(n37 * 4 + 2, new cgVector(n38 - 0.125, n39, n40 + 0.25));
                this.m_model.specifyVertex(n37 * 4 + 3, new cgVector(n38 - 0.125, n39, n40));
            }
            for (int n41 = 0; n41 < n36 - 1; ++n41) {
                int n42 = 0;
                do {
                    this.m_model.specifyFace(n41 * 3 + n42, new cgFace(4, new int[] { n41 * 4 + n42, n41 * 4 + n42 + 1, (n41 + 1) * 4 + n42 + 1, (n41 + 1) * 4 + n42 }, Barrier3D.BARRIER_COLOR, null));
                } while (++n42 < 3);
            }
            this.m_model.specifyFace((n36 - 1) * 3, new cgFace(4, new int[] { 3, 2, 1, 0 }, Barrier3D.BARRIER_COLOR, null));
            this.m_model.specifyFace((n36 - 1) * 3 + 1, new cgFace(4, new int[] { (n36 - 1) * 4, (n36 - 1) * 4 + 1, (n36 - 1) * 4 + 2, (n36 - 1) * 4 + 3 }, Barrier3D.BARRIER_COLOR, null));
            (this.m_object = new cgObject(this.m_model)).transform();
        }
    }
}
