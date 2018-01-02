import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class SlidingBridge
{
    final int X_MIN = 0;
    final int Y_MIN = 1;
    final int X_MAX = 2;
    final int Y_MAX = 3;
    final int ORIENTATION = 4;
    final int Z_BRIDGE = 5;
    final Color WATER_COLOR;
    final Color SHORE_COLOR;
    final Color GRASS_COLOR;
    final Color BRIDGE_COLOR;
    final double POOL_DEPTH = 0.4;
    final double BRIDGE_WIDTH = 1.5;
    final double SAFETY_MARGIN = 1.0;
    final long BRIDGE_PERIOD = 8000L;
    final double RESET_DISTANCE = 0.1;
    final double MIN_VELOCITY = 2.0;
    public SlidingBridge m_slidingbridgeNext;
    double m_dPoolXmin;
    double m_dPoolYmin;
    double m_dPoolXmax;
    double m_dPoolYmax;
    double m_dBridgeXmin;
    double m_dBridgeYmin;
    double m_dBridgeXmax;
    double m_dBridgeYmax;
    public double m_dBridgeZ;
    long m_lBridgeTime;
    double m_dBridgeXsize;
    double m_dBridgeYsize;
    boolean m_bBridgeVaryX;
    double m_dBridgeRange;
    double m_dXreset;
    double m_dYreset;
    double m_dRelativeX;
    double m_dRelativeY;
    cgModel m_modelPool;
    public cgObject m_objectPool;
    cgModel m_modelBridge;
    public cgObject m_objectBridge1;
    cgObject m_objectBridge2;
    
    private void collisionHandler(final Golfball3D golfball3D, final long n, final double n2, final double n3) {
        if (n2 == this.m_dPoolXmin) {
            this.m_dXreset = n2 - 0.1;
            this.m_dYreset = n3;
        }
        if (n2 == this.m_dPoolXmax) {
            this.m_dXreset = n2 + 0.1;
            this.m_dYreset = n3;
        }
        if (n3 == this.m_dPoolYmin) {
            this.m_dXreset = n2;
            this.m_dYreset = n3 - 0.1;
        }
        if (n3 == this.m_dPoolYmax) {
            this.m_dXreset = n2;
            this.m_dYreset = n3 + 0.1;
        }
        this.calculateBridgeRectangle(n);
        final double n4 = n2 - this.m_dBridgeXmin;
        final double n5 = n3 - this.m_dBridgeYmin;
        if (n4 < 0.0 || n4 > this.m_dBridgeXsize || n5 < 0.0 || n5 > this.m_dBridgeYsize) {
            this.waterBall(golfball3D);
            return;
        }
        if (this.ranOffBridge(golfball3D)) {
            return;
        }
        this.m_dRelativeX = golfball3D.m_dX1 - this.m_dBridgeXmin;
        this.m_dRelativeY = golfball3D.m_dY1 - this.m_dBridgeYmin;
        golfball3D.m_slidingbridge = this;
        golfball3D.m_dFinalVelocity = Math.max(golfball3D.m_dFinalVelocity, 2.0);
        golfball3D.m_dVelocity = golfball3D.m_dFinalVelocity;
        golfball3D.m_dDistance = 0.0;
        golfball3D.m_dX0 = golfball3D.m_dX1;
        golfball3D.m_dY0 = golfball3D.m_dY1;
    }
    
    public boolean golfballPhysics(final Golfball3D golfball3D, final long n, final double n2) {
        this.calculateBridgeRectangle(n);
        golfball3D.m_dX0 = this.m_dRelativeX + this.m_dBridgeXmin;
        golfball3D.m_dY0 = this.m_dRelativeY + this.m_dBridgeYmin;
        golfball3D.m_dDistance = golfball3D.m_dVelocity * n2;
        golfball3D.m_dX1 = golfball3D.m_dX0 + golfball3D.m_dDistance * Math.cos(golfball3D.m_dAngle);
        golfball3D.m_dY1 = golfball3D.m_dY0 + golfball3D.m_dDistance * Math.sin(golfball3D.m_dAngle);
        if (this.ranOffBridge(golfball3D)) {
            return true;
        }
        golfball3D.m_dX0 = golfball3D.m_dX1;
        golfball3D.m_dY0 = golfball3D.m_dY1;
        golfball3D.m_dDistance = 0.0;
        this.m_dRelativeX = golfball3D.m_dX0 - this.m_dBridgeXmin;
        this.m_dRelativeY = golfball3D.m_dY0 - this.m_dBridgeYmin;
        return false;
    }
    
    public SlidingBridge(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.WATER_COLOR = new Color(52479);
        this.SHORE_COLOR = new Color(13395456);
        this.GRASS_COLOR = new Color(Color.HSBtoRGB(0.37f, 0.8f, 0.4f));
        this.BRIDGE_COLOR = Fairway3D.FAIRWAY_COLOR;
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length < 5 || numericArgs.length > 6) {
            throw new ParsingException("Sliding Bridge needs 5 or 6 arguments");
        }
        if (numericArgs[0] >= numericArgs[2] || numericArgs[1] >= numericArgs[3]) {
            throw new ParsingException("Pool rectangle coords inconsistent");
        }
        this.m_dPoolXmin = numericArgs[0];
        this.m_dPoolXmax = numericArgs[2];
        this.m_dPoolYmin = numericArgs[1];
        this.m_dPoolYmax = numericArgs[3];
        (this.m_modelPool = new cgModel(8, 5)).specifyVertex(0, new cgVector(this.m_dPoolXmin, this.m_dPoolYmin, -0.4));
        this.m_modelPool.specifyVertex(1, new cgVector(this.m_dPoolXmax, this.m_dPoolYmin, -0.4));
        this.m_modelPool.specifyVertex(2, new cgVector(this.m_dPoolXmax, this.m_dPoolYmax, -0.4));
        this.m_modelPool.specifyVertex(3, new cgVector(this.m_dPoolXmin, this.m_dPoolYmax, -0.4));
        this.m_modelPool.specifyVertex(4, new cgVector(this.m_dPoolXmin, this.m_dPoolYmin, 0.0));
        this.m_modelPool.specifyVertex(5, new cgVector(this.m_dPoolXmax, this.m_dPoolYmin, 0.0));
        this.m_modelPool.specifyVertex(6, new cgVector(this.m_dPoolXmax, this.m_dPoolYmax, 0.0));
        this.m_modelPool.specifyVertex(7, new cgVector(this.m_dPoolXmin, this.m_dPoolYmax, 0.0));
        final int[] array = { 5, 4, 0, 1 };
        final int[] array2 = { 6, 5, 1, 2 };
        final int[] array3 = { 7, 6, 2, 3 };
        final int[] array4 = { 4, 7, 3, 0 };
        final int[] array5 = { 3, 2, 1, 0 };
        this.m_modelPool.specifyFace(0, new cgFace(4, array, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_modelPool.specifyFace(1, new cgFace(4, array2, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_modelPool.specifyFace(2, new cgFace(4, array3, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_modelPool.specifyFace(3, new cgFace(4, array4, this.SHORE_COLOR, this.GRASS_COLOR, false, true));
        this.m_modelPool.specifyFace(4, new cgFace(4, array5, this.WATER_COLOR, null));
        (this.m_objectPool = new cgObject(this.m_modelPool)).transform();
        switch ((int)numericArgs[4]) {
            case 0:
            case 180: {
                this.m_bBridgeVaryX = true;
                this.m_dBridgeXsize = 1.5;
                this.m_dBridgeYsize = this.m_dPoolYmax - this.m_dPoolYmin;
                this.m_dBridgeRange = this.m_dPoolXmax - this.m_dPoolXmin - 3.5;
                break;
            }
            case 90:
            case 270: {
                this.m_bBridgeVaryX = false;
                this.m_dBridgeXsize = this.m_dPoolXmax - this.m_dPoolXmin;
                this.m_dBridgeYsize = 1.5;
                this.m_dBridgeRange = this.m_dPoolYmax - this.m_dPoolYmin - 3.5;
                break;
            }
            default: {
                throw new ParsingException("Bridge orientation not 0,90,180,270");
            }
        }
        if (this.m_dBridgeRange <= 0.0) {
            throw new ParsingException("Pool too narrow for bridge");
        }
        this.m_dBridgeZ = 0.0;
        if (numericArgs.length == 6) {
            this.m_dBridgeZ = numericArgs[5];
        }
        if (this.m_dBridgeZ < 0.0) {
            throw new ParsingException("Bridge elevaiton < 0");
        }
        (this.m_modelBridge = new cgModel(4, 1)).specifyVertex(0, new cgVector(0.0, 0.0, 0.0));
        this.m_modelBridge.specifyVertex(1, new cgVector(this.m_dBridgeXsize, 0.0, 0.0));
        this.m_modelBridge.specifyVertex(2, new cgVector(this.m_dBridgeXsize, this.m_dBridgeYsize, 0.0));
        this.m_modelBridge.specifyVertex(3, new cgVector(0.0, this.m_dBridgeYsize, 0.0));
        final cgFace cgFace2;
        final cgFace cgFace = cgFace2 = new cgFace(4, new int[] { 3, 2, 1, 0 }, this.BRIDGE_COLOR, null);
        cgFace2.m_nFlags |= 0x4;
        this.m_modelBridge.specifyFace(0, cgFace);
        (this.m_objectBridge1 = new cgObject(this.m_modelBridge)).transform();
        this.m_objectBridge2 = new cgObject(this.m_objectBridge1);
        this.m_objectBridge2.m_bSortPolys = false;
    }
    
    private void calculateBridgeRectangle(final long lBridgeTime) {
        if (lBridgeTime != this.m_lBridgeTime) {
            final double n = lBridgeTime % 8000L / 8000.0;
            double n2;
            if (n < 0.5) {
                n2 = 1.0 + this.m_dBridgeRange * n * 2.0;
            }
            else {
                n2 = 1.0 + this.m_dBridgeRange * (1.0 - (n - 0.5) * 2.0);
            }
            if (this.m_bBridgeVaryX) {
                this.m_dBridgeXmin = this.m_dPoolXmin + n2;
                this.m_dBridgeXmax = this.m_dBridgeXmin + this.m_dBridgeXsize;
                this.m_dBridgeYmin = this.m_dPoolYmin;
                this.m_dBridgeYmax = this.m_dPoolYmax;
            }
            else {
                this.m_dBridgeXmin = this.m_dPoolXmin;
                this.m_dBridgeXmax = this.m_dPoolXmax;
                this.m_dBridgeYmin = this.m_dPoolYmin + n2;
                this.m_dBridgeYmax = this.m_dBridgeYmin + this.m_dBridgeYsize;
            }
            this.m_lBridgeTime = lBridgeTime;
        }
    }
    
    public double collisionAnalysis(final Golfball3D golfball3D, final boolean b, final long n) {
        double n2 = 0.0;
        double n3 = 0.0;
        if (golfball3D.m_dXmin >= this.m_dPoolXmax || golfball3D.m_dXmax <= this.m_dPoolXmin || golfball3D.m_dYmin >= this.m_dPoolYmax || golfball3D.m_dYmax <= this.m_dPoolYmin) {
            return -1.0;
        }
        boolean b2 = false;
        if (golfball3D.m_dX1 > golfball3D.m_dX0 && golfball3D.m_dX0 < this.m_dPoolXmin) {
            b2 = true;
            n2 = this.m_dPoolXmin;
        }
        if (golfball3D.m_dX1 < golfball3D.m_dX0 && golfball3D.m_dX0 > this.m_dPoolXmax) {
            b2 = true;
            n2 = this.m_dPoolXmax;
        }
        if (b2) {
            n3 = golfball3D.m_dY0 + (golfball3D.m_dY1 - golfball3D.m_dY0) * (n2 - golfball3D.m_dX0) / (golfball3D.m_dX1 - golfball3D.m_dX0);
            if (n3 > this.m_dPoolYmin && n3 < this.m_dPoolYmax) {
                final double sqrt = Math.sqrt(Math.pow(n2 - golfball3D.m_dX0, 2.0) + Math.pow(n3 - golfball3D.m_dY0, 2.0));
                if (b) {
                    this.collisionHandler(golfball3D, n, n2, n3);
                }
                return sqrt;
            }
        }
        boolean b3 = false;
        if (golfball3D.m_dY1 > golfball3D.m_dY0 && golfball3D.m_dY0 < this.m_dPoolYmin) {
            b3 = true;
            n3 = this.m_dPoolYmin;
        }
        if (golfball3D.m_dY1 < golfball3D.m_dY0 && golfball3D.m_dY0 > this.m_dPoolYmax) {
            b3 = true;
            n3 = this.m_dPoolYmax;
        }
        if (b3) {
            final double n4 = golfball3D.m_dX0 + (golfball3D.m_dX1 - golfball3D.m_dX0) * (n3 - golfball3D.m_dY0) / (golfball3D.m_dY1 - golfball3D.m_dY0);
            if (n4 > this.m_dPoolXmin && n4 < this.m_dPoolXmax) {
                final double sqrt2 = Math.sqrt(Math.pow(n4 - golfball3D.m_dX0, 2.0) + Math.pow(n3 - golfball3D.m_dY0, 2.0));
                if (b) {
                    this.collisionHandler(golfball3D, n, n4, n3);
                }
                return sqrt2;
            }
        }
        return -1.0;
    }
    
    private void waterBall(final Golfball3D golfball3D) {
        golfball3D.m_bWaterBall = true;
        golfball3D.m_slidingbridge = null;
        golfball3D.m_dX0 = this.m_dXreset;
        golfball3D.m_dY0 = this.m_dYreset;
        golfball3D.m_dVelocity = 0.0;
        golfball3D.m_dDistance = 0.0;
        golfball3D.m_dX1 = golfball3D.m_dX0;
        golfball3D.m_dY1 = golfball3D.m_dY0;
        golfball3D.m_dFinalVelocity = golfball3D.m_dVelocity;
        golfball3D.SetString("Penalty stroke");
    }
    
    public void renderBridgeChain(final cgViewpoint cgViewpoint) {
        cgViewpoint.renderObject(this.m_objectBridge2);
        if (this.m_slidingbridgeNext != null) {
            this.m_slidingbridgeNext.renderBridgeChain(cgViewpoint);
        }
    }
    
    public void transformBridgeChain(final long n) {
        this.calculateBridgeRectangle(n);
        this.m_objectBridge1.m_vector.m_dX = this.m_dBridgeXmin;
        this.m_objectBridge1.m_vector.m_dY = this.m_dBridgeYmin;
        this.m_objectBridge1.m_vector.m_dZ = this.m_dBridgeZ;
        this.m_objectBridge1.transform();
        if (this.m_slidingbridgeNext != null) {
            this.m_slidingbridgeNext.transformBridgeChain(n);
        }
    }
    
    private boolean ranOffBridge(final Golfball3D golfball3D) {
        final double n = golfball3D.m_dX0 - this.m_dBridgeXmin;
        final double n2 = golfball3D.m_dY0 - this.m_dBridgeYmin;
        final double n3 = golfball3D.m_dX1 - this.m_dBridgeXmin;
        final double n4 = golfball3D.m_dY1 - this.m_dBridgeYmin;
        if (n3 < 0.0 && this.m_bBridgeVaryX) {
            final double n5 = n2 + (n4 - n2) * -n / (n3 - n);
            if (n5 > 0.0 && n5 < this.m_dBridgeYsize) {
                this.waterBall(golfball3D);
                return true;
            }
        }
        if (n3 > this.m_dBridgeXsize && this.m_bBridgeVaryX) {
            final double n6 = n2 + (n4 - n2) * (this.m_dBridgeXsize - n) / (n3 - n);
            if (n6 > 0.0 && n6 < this.m_dBridgeYsize) {
                this.waterBall(golfball3D);
                return true;
            }
        }
        if (n4 < 0.0 && !this.m_bBridgeVaryX) {
            final double n7 = n + (n3 - n) * -n2 / (n4 - n2);
            if (n7 > 0.0 && n7 < this.m_dBridgeXsize) {
                this.waterBall(golfball3D);
                return true;
            }
        }
        if (n4 > this.m_dBridgeYsize && !this.m_bBridgeVaryX) {
            final double n8 = n + (n3 - n) * (this.m_dBridgeYsize - n2) / (n4 - n2);
            if (n8 > 0.0 && n8 < this.m_dBridgeXsize) {
                this.waterBall(golfball3D);
                return true;
            }
        }
        if ((!this.m_bBridgeVaryX && (n3 < 0.0 || n3 > this.m_dBridgeXsize)) || (this.m_bBridgeVaryX && (n4 < 0.0 || n4 > this.m_dBridgeYsize))) {
            double dx0;
            double dy0;
            if (n3 > this.m_dBridgeXsize && !this.m_bBridgeVaryX) {
                dx0 = this.m_dPoolXmax;
                dy0 = golfball3D.m_dY0 + (dx0 - golfball3D.m_dX0) / (golfball3D.m_dX1 - golfball3D.m_dX0) * (golfball3D.m_dY1 - golfball3D.m_dY0);
            }
            else if (n3 < 0.0 && !this.m_bBridgeVaryX) {
                dx0 = this.m_dPoolXmin;
                dy0 = golfball3D.m_dY0 + (dx0 - golfball3D.m_dX0) / (golfball3D.m_dX1 - golfball3D.m_dX0) * (golfball3D.m_dY1 - golfball3D.m_dY0);
            }
            else if (n4 > this.m_dBridgeYsize && this.m_bBridgeVaryX) {
                dy0 = this.m_dPoolYmax;
                dx0 = golfball3D.m_dX0 + (dy0 - golfball3D.m_dY0) / (golfball3D.m_dY1 - golfball3D.m_dY0) * (golfball3D.m_dX1 - golfball3D.m_dX0);
            }
            else {
                dy0 = this.m_dPoolYmin;
                dx0 = golfball3D.m_dX0 + (dy0 - golfball3D.m_dY0) / (golfball3D.m_dY1 - golfball3D.m_dY0) * (golfball3D.m_dX1 - golfball3D.m_dX0);
            }
            final double n9 = dx0 - golfball3D.m_dX0;
            final double n10 = dy0 - golfball3D.m_dY0;
            final double sqrt = Math.sqrt(n9 * n9 + n10 * n10);
            golfball3D.m_slidingbridge = null;
            golfball3D.m_dVelocity -= (golfball3D.m_dVelocity - golfball3D.m_dFinalVelocity) * sqrt / golfball3D.m_dDistance;
            golfball3D.m_dDistance -= sqrt;
            golfball3D.m_dDistance = Math.max(golfball3D.m_dDistance, 0.0);
            golfball3D.m_dX0 = dx0;
            golfball3D.m_dY0 = dy0;
            return true;
        }
        return false;
    }
}
