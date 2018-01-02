import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Poly2
{
    static Hashtable D;
    public int versio;
    public float width;
    public float height;
    protected float abs;
    public float baseline;
    protected float addElement;
    protected float append;
    protected int[] charAt;
    protected float[][] cos;
    public String source;
    public String firstToken;
    public int nextToken;
    protected Hashtable dibuixaPoligon;
    public float source_width;
    public float source_height;
    
    public Poly2() {
        this.dibuixaPoligon = new Hashtable();
    }
    
    public final void draw(final int n, int n2, final int n3, final boolean b, final boolean b2, final Graphics graphics) {
        final DoubleRectangle doubleRectangle = new DoubleRectangle();
        final DoubleRectangle abs = this.abs(n, n2, n3);
        --n2;
        if (!GraphicsUtils.isFileGraphics(graphics) && OmegaSystem.versio_java < 2) {
            PolyJava1 polyJava1 = this.dibuixaPoligon.get(new Integer(n3));
            if (polyJava1 instanceof Image) {
                final Image image = (Image)polyJava1;
                final Image image2 = this.dibuixaPoligon.get(new Integer(n3));
                if (image2 != null) {
                    graphics.drawImage(image2, (int)abs.x, (int)abs.y, null);
                    return;
                }
            }
            if (polyJava1 == null) {
                polyJava1 = this.D(abs, b);
                this.dibuixaPoligon.put(new Integer(n3), polyJava1);
            }
            if (polyJava1 instanceof PolyJava1) {
                polyJava1.I(graphics);
                return;
            }
        }
        GraphicsUtils.setRendering(graphics, true);
        this.draw(abs, b, b2, graphics);
        GraphicsUtils.setRendering(graphics, false);
    }
    
    public final void draw(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final Graphics graphics) {
        final DoubleRectangle doubleRectangle = new DoubleRectangle();
        doubleRectangle.x = n;
        doubleRectangle.y = n2 - 1;
        doubleRectangle.height = n4;
        doubleRectangle.width = n3;
        GraphicsUtils.setRendering(graphics, true);
        this.draw(doubleRectangle, b, b2, graphics);
        GraphicsUtils.setRendering(graphics, false);
    }
    
    private void draw(final DoubleRectangle doubleRectangle, final boolean b, final boolean b2, final Graphics graphics) {
        final float[][] cos = this.cos;
        final float[] array = new float[cos.length];
        final float[] array2 = new float[cos.length];
        if (graphics instanceof VecGraphics) {
            ((VecGraphics)graphics).dibuixaPoly(this, doubleRectangle, b, b2);
            return;
        }
        if (OmegaSystem.versio_java < 2) {
            this.D(doubleRectangle, b).I(graphics);
            return;
        }
        int i = 0;
        int n = 0;
        while (i < this.charAt.length >> 1) {
            final int n2 = this.charAt[i << 1];
            if (b) {
                for (int j = 0; j < n2; ++j) {
                    array[j] = (float)doubleRectangle.x + (float)(cos[j + n][0] * doubleRectangle.width);
                    array2[j] = (float)doubleRectangle.y + (float)(cos[j + n][1] * doubleRectangle.height);
                }
            }
            else {
                for (int k = 0; k < n2; ++k) {
                    array[k] = (float)doubleRectangle.x + (float)doubleRectangle.width - 1.0f - (float)(cos[k + n][0] * doubleRectangle.width);
                    array2[k] = (float)doubleRectangle.y + (float)(cos[k + n][1] * doubleRectangle.height);
                }
            }
            if (this.versio == 2) {
                IsGraphics2D.dibuixaPoligon(array, array2, n2, 1.0f, graphics, true, false);
            }
            else {
                IsGraphics2D.dibuixaPoligon(array, array2, n2, 1.0f, graphics, true, true);
            }
            n += n2;
            ++i;
        }
    }
    
    private final PolyJava1 D(final DoubleRectangle doubleRectangle, final boolean b) {
        final float[][] cos = this.cos;
        final float[] array = new float[cos.length];
        final float[] array2 = new float[cos.length];
        final double[] array3 = { 0.0 };
        final double[] array4 = { 0.0 };
        final double n = doubleRectangle.y + this.addElement * doubleRectangle.height;
        final double n2 = doubleRectangle.x + this.append * doubleRectangle.width;
        double n3 = (int)Math.round(n) - n;
        double n4 = (int)Math.round(n2) - n2;
        if (n4 * n4 + n3 * n3 > 0.0625) {
            if (n4 < 0.0) {
                n4 += 0.5;
            }
            else {
                n4 -= 0.5;
            }
            if (n3 < 0.0) {
                n3 += 0.5;
            }
            else {
                n3 -= 0.5;
            }
        }
        final PolyJava1 polyJava1 = new PolyJava1();
        polyJava1.I = new int[this.charAt.length >> 1];
        polyJava1.Z = new int[this.charAt.length >> 1][][];
        int i = 0;
        int n5 = 0;
        while (i < this.charAt.length >> 1) {
            final int n6 = this.charAt[i << 1];
            final boolean b2 = this.charAt[(i << 1) + 1] == 1;
            polyJava1.I[i] = n6;
            if (b) {
                for (int j = 0; j < n6; ++j) {
                    array[j] = (float)doubleRectangle.x + (float)(cos[j + n5][0] * doubleRectangle.width);
                    array2[j] = (float)doubleRectangle.y + (float)(cos[j + n5][1] * doubleRectangle.height);
                }
            }
            else {
                for (int k = 0; k < n6; ++k) {
                    array[k] = (float)doubleRectangle.x + (float)doubleRectangle.width - 1.0f - (float)(cos[k + n5][0] * doubleRectangle.width);
                    array2[k] = (float)doubleRectangle.y + (float)(cos[k + n5][1] * doubleRectangle.height);
                }
            }
            int[] array5;
            int[] array6;
            if (this.versio == 2) {
                array5 = new int[n6];
                array6 = new int[n6];
                for (int l = 0; l < n6; ++l) {
                    normal((l == 0) ? ((double)array[n6 - 1]) : ((double)array[l - 1]), (l == 0) ? ((double)array2[n6 - 1]) : ((double)array2[l - 1]), array[l], array2[l], (l == n6 - 1) ? ((double)array[0]) : ((double)array[l + 1]), (l == n6 - 1) ? ((double)array2[0]) : ((double)array2[l + 1]), array3, array4);
                    double n7 = array3[0];
                    double n8 = array4[0];
                    if (Math.abs(n7) > 2.0 || Math.abs(n8) > 2.0) {
                        final double n9 = Math.sqrt(n7 * n7 + n8 * n8) / 2.0;
                        n7 /= n9;
                        n8 /= n9;
                    }
                    double n10;
                    double n11;
                    if (b2) {
                        n10 = 0.5 * n7;
                        n11 = 0.5 * n8;
                    }
                    else {
                        n10 = -0.5 * n7;
                        n11 = -0.5 * n8;
                    }
                    array5[l] = myRound2(array[l] + n10 - 0.5, (float)n4);
                    array6[l] = myRound2(array2[l] + n11 - 0.5, (float)n3);
                }
            }
            else {
                array5 = new int[n6];
                array6 = new int[n6];
                for (int n12 = 0; n12 < n6; ++n12) {
                    array5[n12] = (int)array[n12];
                    array6[n12] = (int)array2[n12];
                }
            }
            (polyJava1.Z[i] = new int[2][])[0] = array5;
            polyJava1.Z[i][1] = array6;
            n5 += n6;
            ++i;
        }
        return polyJava1;
    }
    
    private static final void normal(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double[] array, final double[] array2) {
        final double n7 = n3 - n;
        final double n8 = n4 - n2;
        final double sqrt = Math.sqrt(n7 * n7 + n8 * n8);
        final double n9 = -(n5 - n3);
        final double n10 = -(n6 - n4);
        final double sqrt2 = Math.sqrt(n9 * n9 + n10 * n10);
        final double n11 = n7 * n10 - n8 * n9;
        if (Math.abs(n11) < 1.0E-6) {
            if (Math.abs(sqrt) < 1.0E-6) {
                array[0] = -n10 / sqrt2;
                array2[0] = n9 / sqrt2;
                return;
            }
            if (Math.abs(sqrt2) < 1.0E-6) {
                array[0] = n8 / sqrt;
                array2[0] = -n7 / sqrt;
                return;
            }
        }
        array[0] = -(n9 * sqrt + n7 * sqrt2) / n11;
        array2[0] = -(n10 * sqrt + n8 * sqrt2) / n11;
    }
    
    private static int myRound2(final double n, final float n2) {
        return (int)Math.round(n + n2);
    }
    
    private final DoubleRectangle abs(final int n, final int n2, final int n3) {
        final DoubleRectangle doubleRectangle = new DoubleRectangle();
        final float factor = this.factor(n3);
        doubleRectangle.y = n2 - this.baseline * factor;
        doubleRectangle.height = this.height * factor;
        doubleRectangle.x = n;
        doubleRectangle.width = this.width * factor;
        return doubleRectangle;
    }
    
    public final float factor(final int n) {
        return n / (this.baseline - this.abs);
    }
    
    public final int getWidth(final int n) {
        return Math.round(this.width * this.factor(n));
    }
    
    public final int getHeight(final int n) {
        return Math.round(this.height * this.factor(n));
    }
    
    public final int getBaseline(final int n) {
        return Math.round(this.baseline * this.factor(n));
    }
    
    public static final Poly2 create(String lowerCase, final float n) {
        final Poly2 poly2 = Poly2.D.get(lowerCase);
        if (poly2 != null) {
            return poly2;
        }
        double double1 = 1.0;
        double double2 = 1.0;
        double double3 = 0.5;
        double double4 = 0.0;
        final int[] array = { 0 };
        final Poly2 poly3 = new Poly2();
        Poly2.D.put(lowerCase, poly3);
        poly3.versio = 2;
        poly3.source = lowerCase;
        lowerCase = lowerCase.toLowerCase();
        String firstToken = extractToken(lowerCase, array);
        if (firstToken.startsWith("v")) {
            poly3.versio = (int)extractDouble(lowerCase, array);
            firstToken = extractToken(lowerCase, array);
        }
        if (firstToken.startsWith("m")) {
            double1 = extractDouble(lowerCase, array);
            double2 = extractDouble(lowerCase, array);
            double3 = extractDouble(lowerCase, array);
            double4 = extractDouble(lowerCase, array);
            firstToken = extractToken(lowerCase, array);
        }
        else {
            final Poly2 poly4 = poly3;
            final Poly2 poly5 = poly3;
            final float n2 = 1.0f;
            poly5.source_height = n2;
            poly4.source_width = n2;
        }
        if (firstToken.startsWith("d")) {
            poly3.source_width = (float)extractDouble(lowerCase, array);
            poly3.source_height = (float)extractDouble(lowerCase, array);
            firstToken = extractToken(lowerCase, array);
        }
        else {
            final Poly2 poly6 = poly3;
            final Poly2 poly7 = poly3;
            final float n3 = 1.0f;
            poly7.source_height = n3;
            poly6.source_width = n3;
        }
        poly3.nextToken = array[0];
        poly3.firstToken = firstToken;
        poly3.addElement(n);
        poly3.width = (float)double1;
        poly3.height = (float)double2;
        poly3.abs = (float)double4;
        poly3.baseline = (float)double3;
        return poly3;
    }
    
    private final void addElement(final float n) {
        final int[] array = new int[20];
        int n2 = 0;
        double n3 = 0.0;
        double double1 = 1.0;
        final Vector vector = new Vector<Double>();
        final int[] array2 = { this.nextToken };
        double n4 = 0.0;
        double n5 = 0.0;
        for (String s = this.firstToken; !s.equals("") && !s.startsWith("e"); s = extractToken(this.source, array2)) {
            if (s.startsWith("clock")) {
                array[2 * (n2 - 1) + 1] = 1;
                s = extractToken(this.source, array2);
            }
            if (s.startsWith("i") || s.startsWith("f")) {
                if (n2 > 0) {
                    array[2 * n2] = vector.size() / 2;
                    array[2 * n2 + 1] = (s.startsWith("i") ? 0 : 1);
                    ++n2;
                }
                else {
                    array[0] = vector.size();
                    array[1] = (s.startsWith("i") ? 0 : 1);
                    ++n2;
                }
                n3 = 0.0;
                double1 = 1.0;
                n4 = extractDouble(this.source, array2);
                n5 = -extractDouble(this.source, array2);
                vector.addElement(new Double(n4));
                vector.addElement(new Double(n5));
            }
            else if (s.startsWith("oy")) {
                this.addElement = (float)(extractDouble(this.source, array2) / this.source_height);
            }
            else if (s.startsWith("ox")) {
                this.append = (float)(extractDouble(this.source, array2) / this.source_width);
            }
            if (s.startsWith("s")) {
                n4 += extractDouble(this.source, array2);
                n5 -= extractDouble(this.source, array2);
                vector.addElement(new Double(n4));
                vector.addElement(new Double(n5));
            }
            else if (s.startsWith("l")) {
                final double double2 = extractDouble(this.source, array2);
                n4 += double2 * Math.cos(n3);
                n5 -= double2 * Math.sin(n3);
                vector.addElement(new Double(n4));
                vector.addElement(new Double(n5));
            }
            else if (s.startsWith("al")) {
                n4 = extractDouble(this.source, array2);
                n5 = -extractDouble(this.source, array2);
                vector.addElement(new Double(n4));
                vector.addElement(new Double(n5));
            }
            else if (s.startsWith("g")) {
                n3 += extractDouble(this.source, array2) * 3.141592653589793 / 180.0;
            }
            else if (s.equals("ag")) {
                n3 = extractDouble(this.source, array2) * 3.141592653589793 / 180.0;
            }
            else if (s.startsWith("t")) {
                double1 = extractDouble(this.source, array2);
            }
            else if (s.startsWith("a")) {
                final double double3 = extractDouble(this.source, array2);
                final double double4 = extractDouble(this.source, array2);
                final double n6 = n3;
                final double n7 = Integer.parseInt(extractToken(this.source, array2)) * 3.141592653589793 / 180.0;
                final double n8 = n6 + ((n7 >= 0.0) ? -1.5707963267948966 : 1.5707963267948966);
                n3 += n7;
                final int round = Math.round(n);
                final double n9 = n4 - double3 * Math.cos(n8);
                final double n10 = n5 + double4 * Math.sin(n8);
                for (int i = 0; i < round; ++i) {
                    n4 = double3 * Math.cos(n8 + (i + 1) * (n7 / round)) + n9;
                    n5 = -double4 * Math.sin(n8 + (i + 1) * (n7 / round)) + n10;
                    vector.addElement(new Double(n4));
                    vector.addElement(new Double(n5));
                }
            }
            else if (s.startsWith("z")) {
                final double double5 = extractDouble(this.source, array2);
                final double double6 = extractDouble(this.source, array2);
                final double n11 = extractDouble(this.source, array2) * 3.141592653589793 / 180.0;
                final double double7 = extractDouble(this.source, array2);
                final double n12 = double1 * Math.cos(n3);
                final double n13 = -double1 * Math.sin(n3);
                double1 = double7;
                n3 = n11;
                final double n14 = -double1 * Math.cos(n3);
                final double n15 = double1 * Math.sin(n3);
                final double n16 = n4 + n12;
                final double n17 = n5 + n13;
                final double n18 = n4 + double5;
                final double n19 = n5 - double6;
                final double n20 = n18 + n14;
                final double n21 = n19 + n15;
                final double n22 = n4;
                final double n23 = n5;
                for (int round2 = Math.round(n), j = 1; j <= round2; ++j) {
                    final double n24 = j / round2;
                    final double n25 = 1.0 - n24;
                    final double n26 = n25 * n25;
                    final double n27 = n26 * n25;
                    final double n28 = n24 * n24;
                    final double n29 = n28 * n24;
                    n4 = n27 * n22 + 3.0 * n26 * n24 * n16 + 3.0 * n25 * n28 * n20 + n29 * n18;
                    n5 = n27 * n23 + 3.0 * n26 * n24 * n17 + 3.0 * n25 * n28 * n21 + n29 * n19;
                    vector.addElement(new Double(n4));
                    vector.addElement(new Double(n5));
                }
            }
        }
        final int n30 = vector.size() / 2;
        this.charAt = new int[2 * n2];
        for (int k = 0; k < n2; ++k) {
            this.charAt[2 * k + 1] = array[2 * k + 1];
        }
        array[2 * n2] = vector.size() >> 1;
        for (int l = 0; l < n2; ++l) {
            this.charAt[2 * l] = array[2 * (l + 1)] - array[2 * l];
        }
        this.cos = new float[n30][2];
        for (int n31 = 0; n31 < n30; ++n31) {
            final double doubleValue = vector.elementAt(2 * n31);
            final double doubleValue2 = vector.elementAt(2 * n31 + 1);
            this.cos[n31][0] = (float)(doubleValue / this.source_width);
            this.cos[n31][1] = (float)(doubleValue2 / this.source_height);
        }
    }
    
    public static final double extractDouble(final String s, final int[] array) {
        return Double.valueOf(extractToken(s, array));
    }
    
    public static final String extractToken(final String s, final int[] array) {
        String string = "";
        if (array[0] >= s.length()) {
            return "";
        }
        char c;
        int i;
        for (c = s.charAt(array[0]); (i = tipusCaracter(c)) == -1; c = s.charAt(array[0])) {
            final int n = 0;
            ++array[n];
            if (array[0] >= s.length()) {
                return "";
            }
        }
        do {
            string += c;
            final int n2 = 0;
            ++array[n2];
            if (array[0] >= s.length()) {
                break;
            }
            c = s.charAt(array[0]);
        } while (i == tipusCaracter(c));
        return string;
    }
    
    private static int tipusCaracter(final char c) {
        if (c == '.' || c == '-') {
            return 1;
        }
        if (c >= '0' && c <= '9') {
            return 1;
        }
        if (c >= 'A' && c <= 'z') {
            return 0;
        }
        return -1;
    }
    
    static {
        Poly2.D = new Hashtable();
    }
}
