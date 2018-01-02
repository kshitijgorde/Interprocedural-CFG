import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Fairway3D
{
    final int FAIRWAY_X0 = 0;
    final int FAIRWAY_Y0 = 1;
    final int FAIRWAY_X1 = 2;
    final int FAIRWAY_Y1 = 3;
    public static final Color FAIRWAY_COLOR;
    public Fairway3D m_fairwayNext;
    double m_dZ;
    int m_nVertices;
    double m_dX0;
    double m_dY0;
    double m_dXsize;
    double m_dYsize;
    double[] m_dX;
    double[] m_dY;
    cgModel m_model;
    public cgObject m_object;
    
    static {
        FAIRWAY_COLOR = new Color(6723942);
    }
    
    public Fairway3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length < 4) {
            throw new ParsingException("Fairway needs at least 2 XY coordinates");
        }
        if (numericArgs.length % 2 == 1) {
            this.m_dZ = numericArgs[numericArgs.length - 1];
        }
        else {
            this.m_dZ = 0.0;
        }
        if (numericArgs.length / 2 == 2) {
            if (numericArgs[2] <= numericArgs[0] || numericArgs[3] <= numericArgs[1]) {
                throw new ParsingException("Invalid XY rectangle coordinates");
            }
            this.m_nVertices = 0;
            this.m_dX0 = numericArgs[0];
            this.m_dY0 = numericArgs[1];
            this.m_dXsize = numericArgs[2] - numericArgs[0];
            this.m_dYsize = numericArgs[3] - numericArgs[1];
            final double[] tessellateRange = CarpetGolf3D.tessellateRange(this.m_dX0, this.m_dX0 + this.m_dXsize);
            final int length = tessellateRange.length;
            final double[] tessellateRange2 = CarpetGolf3D.tessellateRange(this.m_dY0, this.m_dY0 + this.m_dYsize);
            final int length2 = tessellateRange2.length;
            this.m_model = new cgModel(length * length2, (length - 1) * (length2 - 1));
            for (int i = 0; i < length2; ++i) {
                for (int j = 0; j < length; ++j) {
                    this.m_model.specifyVertex(j + i * length, new cgVector(tessellateRange[j], tessellateRange2[i], this.m_dZ));
                }
            }
            for (int k = 0; k < length2 - 1; ++k) {
                for (int l = 0; l < length - 1; ++l) {
                    this.m_model.specifyFace(k * (length - 1) + l, new cgFace(4, new int[] { (k + 1) * length + l, (k + 1) * length + l + 1, k * length + l + 1, k * length + l }, Fairway3D.FAIRWAY_COLOR, null));
                }
            }
        }
        else {
            this.m_nVertices = numericArgs.length / 2;
            this.m_dX = new double[this.m_nVertices];
            this.m_dY = new double[this.m_nVertices];
            this.m_model = new cgModel(this.m_nVertices, 1);
            final int[] array = new int[this.m_nVertices];
            for (int n = 0; n < this.m_nVertices; ++n) {
                this.m_dX[n] = numericArgs[n * 2];
                this.m_dY[n] = numericArgs[n * 2 + 1];
                this.m_model.specifyVertex(n, new cgVector(this.m_dX[n], this.m_dY[n], this.m_dZ));
                array[n] = n;
            }
            this.m_model.specifyFace(0, new cgFace(this.m_nVertices, array, Fairway3D.FAIRWAY_COLOR, null));
        }
        (this.m_object = new cgObject(this.m_model)).transform();
    }
    
    public double getZcoord(final double n, final double n2) {
        if (this.m_nVertices != 0) {
            double n3 = this.m_dX[0] - n;
            double n4 = this.m_dY[0] - n2;
            double n5 = 0.0;
            for (int i = 1; i <= this.m_nVertices; ++i) {
                final int n6 = i % this.m_nVertices;
                final double n7 = this.m_dX[n6] - n;
                final double n8 = this.m_dY[n6] - n2;
                final double n9 = n3 * n8 - n4 * n7;
                if ((n9 > 0.0 && n5 < 0.0) || (n9 < 0.0 && n5 > 0.0)) {
                    return -1.0;
                }
                n3 = n7;
                n4 = n8;
                if (n9 != 0.0) {
                    n5 = n9;
                }
            }
            return this.m_dZ;
        }
        if (n >= this.m_dX0 && n <= this.m_dX0 + this.m_dXsize && n2 >= this.m_dY0 && n2 <= this.m_dY0 + this.m_dYsize) {
            return this.m_dZ;
        }
        return -1.0;
    }
}
