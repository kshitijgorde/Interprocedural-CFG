// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.Color;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;

public class PGraphics extends PImage implements PConstants
{
    static final int MATRIX_STACK_DEPTH = 32;
    static final int DEFAULT_VERTICES = 512;
    static final int DEFAULT_SPLINE_VERTICES = 128;
    static final float[] sinLUT;
    static final float[] cosLUT;
    static final float SINCOS_PRECISION = 0.5f;
    static final int SINCOS_LENGTH = 720;
    public int width1;
    public int height1;
    public int pixelCount;
    DirectColorModel cm;
    MemoryImageSource mis;
    public Image image;
    protected boolean[] hints;
    public int colorMode;
    public float colorModeX;
    public float colorModeY;
    public float colorModeZ;
    public float colorModeA;
    boolean colorScale;
    boolean colorRgb255;
    public boolean tint;
    public int tintColor;
    boolean tintAlpha;
    float tintR;
    float tintG;
    float tintB;
    float tintA;
    int tintRi;
    int tintGi;
    int tintBi;
    int tintAi;
    public boolean fill;
    public int fillColor;
    boolean fillAlpha;
    float fillR;
    float fillG;
    float fillB;
    float fillA;
    int fillRi;
    int fillGi;
    int fillBi;
    int fillAi;
    public boolean stroke;
    public int strokeColor;
    boolean strokeAlpha;
    float strokeR;
    float strokeG;
    float strokeB;
    float strokeA;
    int strokeRi;
    int strokeGi;
    int strokeBi;
    int strokeAi;
    public int backgroundColor;
    float backgroundR;
    float backgroundG;
    float backgroundB;
    int backgroundRi;
    int backgroundGi;
    int backgroundBi;
    protected float calcR;
    protected float calcG;
    protected float calcB;
    protected float calcA;
    int calcRi;
    int calcGi;
    int calcBi;
    int calcAi;
    int calcColor;
    boolean calcAlpha;
    int cacheHsbKey;
    float[] cacheHsbValue;
    public float strokeWeight;
    public int strokeJoin;
    public int strokeCap;
    public float m00;
    public float m01;
    public float m02;
    public float m10;
    public float m11;
    public float m12;
    float[][] matrixStack;
    int matrixStackDepth;
    Path path;
    protected int shape;
    public float[][] vertices;
    int vertexCount;
    protected boolean bezier_inited;
    protected int bezier_detail;
    protected float[][] bezier_basis;
    protected PMatrix bezierBasis;
    protected float[][] bezier_forward;
    protected float[][] bezier_draw;
    protected boolean curve_inited;
    protected int curve_detail;
    protected float curve_tightness;
    protected float[][] curve_basis;
    protected float[][] curve_forward;
    protected float[][] curve_draw;
    protected PMatrix bezierBasisInverse;
    protected PMatrix curveToBezierMatrix;
    protected float[][] splineVertices;
    protected int splineVertexCount;
    public int rectMode;
    public int ellipseMode;
    public PFont textFont;
    public int textAlign;
    public int textMode;
    public float textSize;
    public float textLeading;
    
    public void resize(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        this.allocate();
    }
    
    public void requestDisplay(final PApplet pApplet) {
        pApplet.display();
    }
    
    protected void allocate() {
        this.pixelCount = this.width * this.height;
        this.pixels = new int[this.pixelCount];
        this.backgroundColor |= 0xFF000000;
        for (int i = 0; i < this.pixelCount; ++i) {
            this.pixels[i] = this.backgroundColor;
        }
        this.cm = new DirectColorModel(32, 16711680, 65280, 255);
        (this.mis = new MemoryImageSource(this.width, this.height, this.pixels, 0, this.width)).setFullBufferUpdates(true);
        this.mis.setAnimated(true);
        this.image = Toolkit.getDefaultToolkit().createImage(this.mis);
    }
    
    public void beginFrame() {
        this.resetMatrix();
        this.vertexCount = 0;
    }
    
    public void endFrame() {
        this.mis.newPixels(this.pixels, this.cm, 0, this.width);
    }
    
    public void defaults() {
        this.colorMode(1, 255.0f);
        this.fill(255.0f);
        this.stroke(0);
        this.strokeWeight(1.0f);
        this.strokeCap(2);
        this.strokeJoin(8);
        this.background(204);
        this.shape = 0;
        this.rectMode(this.matrixStackDepth = 0);
        this.ellipseMode(3);
        this.textFont = null;
        this.textSize = 12.0f;
        this.textLeading = 14.0f;
        this.textAlign = 37;
        this.textMode = 3;
    }
    
    public void hint(final int n) {
        this.hints[n] = true;
    }
    
    public void unhint(final int n) {
        this.hints[n] = false;
    }
    
    public void beginShape() {
        this.beginShape(256);
    }
    
    public void beginShape(final int shape) {
        this.shape = shape;
        this.vertexCount = 0;
        this.splineVertexCount = 0;
    }
    
    public void normal(final float n, final float n2, final float n3) {
        this.depthError("normal");
    }
    
    public void textureMode(final int n) {
        this.depthError("textureMode");
    }
    
    public void texture(final PImage pImage) {
        this.depthError("texture");
    }
    
    public void vertex(final float n, final float n2) {
        this.splineVertexCount = 0;
        if (this.vertexCount == this.vertices.length) {
            final float[][] vertices = new float[this.vertexCount << 1][36];
            System.arraycopy(this.vertices, 0, vertices, 0, this.vertexCount);
            this.vertices = vertices;
        }
        this.vertices[this.vertexCount][9] = n;
        this.vertices[this.vertexCount][10] = n;
        ++this.vertexCount;
        switch (this.shape) {
            case 16: {
                this.point(n, n2);
                break;
            }
            case 32: {
                if (this.vertexCount % 2 == 0) {
                    this.line(this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], n, n2);
                }
                break;
            }
            case 33:
            case 34: {
                if (this.vertexCount == 1) {
                    (this.path = new Path()).moveTo(n, n2);
                }
                else {
                    this.path.lineTo(n, n2);
                }
                break;
            }
            case 64: {
                if (this.vertexCount % 3 == 0) {
                    this.triangle(this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10], this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], n, n2);
                }
                break;
            }
            case 65: {
                if (this.vertexCount == 3) {
                    this.triangle(this.vertices[0][9], this.vertices[0][10], this.vertices[1][9], this.vertices[1][10], n, n2);
                }
                else if (this.vertexCount > 3) {
                    (this.path = new Path()).moveTo(this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10]);
                    this.path.lineTo(this.vertices[this.vertexCount - 1][9], this.vertices[this.vertexCount - 1][10]);
                    this.path.lineTo(this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10]);
                    this.draw_shape(this.path);
                }
                break;
            }
            case 66: {
                if (this.vertexCount == 3) {
                    this.triangle(this.vertices[0][9], this.vertices[0][10], this.vertices[1][9], this.vertices[1][10], n, n2);
                }
                else if (this.vertexCount > 3) {
                    (this.path = new Path()).moveTo(this.vertices[0][9], this.vertices[0][10]);
                    this.path.lineTo(this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10]);
                    this.path.lineTo(n, n2);
                    this.draw_shape(this.path);
                }
                break;
            }
            case 128: {
                if (this.vertexCount % 4 == 0) {
                    this.quad(this.vertices[this.vertexCount - 4][9], this.vertices[this.vertexCount - 4][10], this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10], this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10], n, n2);
                }
                break;
            }
            case 129: {
                if (this.vertexCount == 4) {
                    this.quad(this.vertices[0][9], this.vertices[0][10], this.vertices[2][9], this.vertices[2][10], n, n2, this.vertices[1][9], this.vertices[1][10]);
                }
                else if (this.vertexCount > 4) {
                    (this.path = new Path()).moveTo(this.vertices[this.vertexCount - 3][9], this.vertices[this.vertexCount - 3][10]);
                    this.path.lineTo(this.vertices[this.vertexCount - 1][9], this.vertices[this.vertexCount - 1][10]);
                    this.path.lineTo(n, n2);
                    this.path.lineTo(this.vertices[this.vertexCount - 2][9], this.vertices[this.vertexCount - 2][10]);
                    this.draw_shape(this.path);
                }
                break;
            }
            case 256: {
                if (this.vertexCount == 1) {
                    (this.path = new Path()).moveTo(n, n2);
                }
                else {
                    this.path.lineTo(n, n2);
                }
                break;
            }
        }
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("vertex");
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4) {
        throw new RuntimeException("vertex() with u, v coordinates can only be used with OPENGL or P3D");
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4, final float n5) {
        throw new RuntimeException("vertex() with u, v coordinates can only be used with OPENGL or P3D");
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
    }
    
    protected void bezier_vertex(final float n, final float n2) {
        this.vertexCount = 0;
        if (this.splineVertices == null) {
            this.splineVertices = new float[128][36];
        }
        if (this.splineVertexCount == 128) {
            System.arraycopy(this.splineVertices[125], 0, this.splineVertices[0], 0, 36);
            System.arraycopy(this.splineVertices[126], 0, this.splineVertices[1], 0, 36);
            this.splineVertexCount = 3;
        }
        this.splineVertices[this.splineVertexCount][9] = n;
        this.splineVertices[this.splineVertexCount][10] = n2;
        ++this.splineVertexCount;
        switch (this.shape) {
            case 33:
            case 34:
            case 256: {
                if (this.splineVertexCount == 1) {
                    this.path.moveTo(n, n2);
                }
                else if (this.splineVertexCount >= 4) {
                    this.path.curveTo(this.splineVertices[this.splineVertexCount - 3][9], this.splineVertices[this.splineVertexCount - 3][10], this.splineVertices[this.splineVertexCount - 2][9], this.splineVertices[this.splineVertexCount - 2][10], n, n2);
                }
            }
            default: {
                throw new RuntimeException("bezierVertex() can only be used with LINE_LOOP and POLYGON shapes");
            }
        }
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.depthErrorXYZ("bezierVertex");
    }
    
    public void curveVertex(final float n, final float n2) {
    }
    
    public void curveVertex(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("curveVertex");
    }
    
    public void endShape() {
        switch (this.shape = 0) {
            case 33: {
                this.stroke_shape(this.path);
                break;
            }
            case 34: {
                this.path.closePath();
                this.stroke_shape(this.path);
                break;
            }
            case 256: {
                this.path.closePath();
                this.draw_shape(this.path);
                break;
            }
        }
    }
    
    protected void fill_shape(final Path path) {
        final boolean fill = this.fill;
    }
    
    protected void stroke_shape(final Path path) {
        final boolean stroke = this.stroke;
    }
    
    protected void draw_shape(final Path path) {
        final boolean fill = this.fill;
        final boolean stroke = this.stroke;
    }
    
    public void point(final float n, final float n2) {
    }
    
    public void point(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("point");
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
    }
    
    public void line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.depthErrorXYZ("line");
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
    }
    
    public void rectMode(final int rectMode) {
        this.rectMode = rectMode;
    }
    
    public void rect(float n, float n2, float n3, float n4) {
        switch (this.rectMode) {
            case 0: {
                n3 += n;
                n4 += n2;
                break;
            }
            case 2: {
                final float n5 = n3;
                final float n6 = n4;
                n3 = n + n5;
                n4 = n2 + n6;
                n -= n5;
                n2 -= n6;
                break;
            }
            case 3: {
                final float n7 = n3 / 2.0f;
                final float n8 = n4 / 2.0f;
                n3 = n + n7;
                n4 = n2 + n8;
                n -= n7;
                n2 -= n8;
                break;
            }
        }
        if (n > n3) {
            final float n9 = n;
            n = n3;
            n3 = n9;
        }
        if (n2 > n4) {
            final float n10 = n2;
            n2 = n4;
            n4 = n10;
        }
        this.rectImpl(n, n2, n3, n4);
    }
    
    protected void rectImpl(final float n, final float n2, final float n3, final float n4) {
    }
    
    public void ellipseMode(final int ellipseMode) {
        this.ellipseMode = ellipseMode;
    }
    
    public void ellipse(final float n, final float n2, final float n3, final float n4) {
        float n5 = n;
        float n6 = n2;
        float n7 = n3;
        float n8 = n4;
        if (this.ellipseMode == 1) {
            n7 = n3 - n;
            n8 = n4 - n2;
        }
        else if (this.ellipseMode == 2) {
            n5 = n - n3;
            n6 = n2 - n4;
            n7 = n3 * 2.0f;
            n8 = n4 * 2.0f;
        }
        else if (this.ellipseMode == 3) {
            n5 = n - n3 / 2.0f;
            n6 = n2 - n4 / 2.0f;
        }
        if (n7 < 0.0f) {
            n5 += n7;
            n7 = -n7;
        }
        if (n8 < 0.0f) {
            n6 += n8;
            n8 = -n8;
        }
        this.ellipseImpl(n5, n6, n7, n8);
    }
    
    protected void ellipseImpl(final float n, final float n2, final float n3, final float n4) {
    }
    
    public void arc(final float n, final float n2, final float n3, final float n4, final float n5, float n6) {
        float n7 = n;
        float n8 = n2;
        float n9 = n3;
        float n10 = n4;
        if (this.ellipseMode == 1) {
            n9 = n3 - n;
            n10 = n4 - n2;
        }
        else if (this.ellipseMode == 2) {
            n7 = n - n3;
            n8 = n2 - n4;
            n9 = n3 * 2.0f;
            n10 = n4 * 2.0f;
        }
        else if (this.ellipseMode == 3) {
            n7 = n - n3 / 2.0f;
            n8 = n2 - n4 / 2.0f;
        }
        if (Float.isInfinite(n5) || Float.isInfinite(n6)) {
            return;
        }
        while (n6 < n5) {
            n6 += 6.2831855f;
        }
        this.arcImpl(n7, n8, n9, n10, n5, n6);
    }
    
    protected void arcImpl(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
    }
    
    public void box(final float n) {
        this.depthError("box");
    }
    
    public void box(final float n, final float n2, final float n3) {
        this.depthError("box");
    }
    
    public void sphereDetail(final int n) {
        this.depthError("sphereDetail");
    }
    
    public void sphere(final float n) {
        this.depthError("sphere");
    }
    
    public float bezierPoint(final float n, final float n2, final float n3, final float n4, final float n5) {
        final float n6 = 1.0f - n5;
        return n * n6 * n6 * n6 + 3 * n2 * n5 * n6 * n6 + 3 * n3 * n5 * n5 * n6 + n4 * n5 * n5 * n5;
    }
    
    public float bezierTangent(final float n, final float n2, final float n3, final float n4, final float n5) {
        final float n6 = 1.0f - n5;
        return n * 3 * n5 * n5 + n2 * 3 * n5 * (2.0f - 3 * n5) + n3 * 3 * (3 * n5 * n5 - 4 * n5 + 1.0f) + n4 * -3.0f * n6 * n6;
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.beginShape(33);
        this.vertex(n, n2);
        this.bezierVertex(n3, n4, n5, n6, n7, n8);
        this.endShape();
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.depthErrorXYZ("bezier");
    }
    
    protected void bezier_init() {
        this.bezierDetail(this.bezier_detail);
    }
    
    public void bezierDetail(final int bezier_detail) {
        if (this.bezier_forward == null) {
            this.bezier_forward = new float[4][4];
            this.bezier_draw = new float[4][4];
        }
        this.bezier_detail = bezier_detail;
        this.bezier_inited = true;
        this.setup_spline_forward(bezier_detail, this.bezier_forward);
        this.mult_spline_matrix(this.bezier_forward, this.bezier_basis, this.bezier_draw, 4);
    }
    
    protected void curve_init() {
        this.curve_mode(this.curve_detail, this.curve_tightness);
    }
    
    public void curveDetail(final int n) {
        this.curve_mode(n, this.curve_tightness);
    }
    
    public void curveTightness(final float n) {
        this.curve_mode(this.curve_detail, n);
    }
    
    protected void curve_mode(final int curve_detail, final float n) {
        this.curve_detail = curve_detail;
        if (this.curve_basis == null) {
            this.curve_basis = new float[4][4];
            this.curve_forward = new float[4][4];
            this.curve_draw = new float[4][4];
            this.curve_inited = true;
        }
        final float[][] curve_basis = this.curve_basis;
        curve_basis[0][0] = n - 1.0f;
        curve_basis[0][1] = n + 3;
        curve_basis[0][2] = -3.0f - n;
        curve_basis[0][3] = 1.0f - n;
        curve_basis[1][0] = 2.0f * (1.0f - n);
        curve_basis[1][1] = -5.0f - n;
        curve_basis[1][2] = 2.0f * (n + 2.0f);
        curve_basis[1][3] = n - 1.0f;
        curve_basis[2][0] = n - 1.0f;
        curve_basis[2][1] = 0.0f;
        curve_basis[2][2] = 1.0f - n;
        curve_basis[2][3] = 0.0f;
        curve_basis[3][0] = 0.0f;
        curve_basis[3][1] = 2.0f;
        curve_basis[3][2] = 0.0f;
        curve_basis[3][3] = 0.0f;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                final float[] array = curve_basis[i];
                final int n2 = j;
                array[n2] /= 2.0f;
            }
        }
        this.setup_spline_forward(curve_detail, this.curve_forward);
        if (this.bezierBasisInverse == null) {
            this.bezierBasisInverse = new PMatrix(this.bezierBasis).invert();
        }
        (this.curveToBezierMatrix = new PMatrix(curve_basis[0][0], curve_basis[0][1], curve_basis[0][2], curve_basis[0][3], curve_basis[1][0], curve_basis[1][1], curve_basis[1][2], curve_basis[1][3], curve_basis[2][0], curve_basis[2][1], curve_basis[2][2], curve_basis[2][3], curve_basis[3][0], curve_basis[3][1], curve_basis[3][2], curve_basis[3][3])).preApply(this.bezierBasisInverse);
        this.mult_spline_matrix(this.curve_forward, this.curve_basis, this.curve_draw, 4);
    }
    
    public float curvePoint(final float n, final float n2, final float n3, final float n4, final float n5) {
        if (!this.curve_inited) {
            this.curve_init();
        }
        final float n6 = n5 * n5;
        final float n7 = n5 * n6;
        final float[][] curve_basis = this.curve_basis;
        return n * (n7 * curve_basis[0][0] + n6 * curve_basis[1][0] + n5 * curve_basis[2][0] + curve_basis[3][0]) + n2 * (n7 * curve_basis[0][1] + n6 * curve_basis[1][1] + n5 * curve_basis[2][1] + curve_basis[3][1]) + n3 * (n7 * curve_basis[0][2] + n6 * curve_basis[1][2] + n5 * curve_basis[2][2] + curve_basis[3][2]) + n4 * (n7 * curve_basis[0][3] + n6 * curve_basis[1][3] + n5 * curve_basis[2][3] + curve_basis[3][3]);
    }
    
    public float curveTangent(final float n, final float n2, final float n3, final float n4, final float n5) {
        System.err.println("curveTangent not yet implemented");
        return 0.0f;
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.beginShape(33);
        this.curveVertex(n, n2);
        this.curveVertex(n3, n4);
        this.curveVertex(n5, n6);
        this.curveVertex(n7, n8);
        this.endShape();
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.depthErrorXYZ("curve");
    }
    
    protected void setup_spline_forward(final int n, final float[][] array) {
        final float n2 = 1.0f / n;
        final float n3 = n2 * n2;
        final float n4 = n3 * n2;
        array[0][0] = 0.0f;
        array[0][1] = 0.0f;
        array[0][2] = 0.0f;
        array[0][3] = 1.0f;
        array[1][0] = n4;
        array[1][1] = n3;
        array[1][2] = n2;
        array[1][3] = 0.0f;
        array[2][0] = 6.0f * n4;
        array[2][1] = 2.0f * n3;
        array[2][2] = 0.0f;
        array[2][3] = 0.0f;
        array[3][0] = 6.0f * n4;
        array[3][1] = 0.0f;
        array[3][2] = 0.0f;
        array[3][3] = 0.0f;
    }
    
    protected void mult_spline_matrix(final float[][] array, final float[][] array2, final float[][] array3, final int n) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < n; ++j) {
                array3[i][j] = 0.0f;
            }
        }
        for (int k = 0; k < 4; ++k) {
            for (int l = 0; l < n; ++l) {
                for (int n2 = 0; n2 < 4; ++n2) {
                    array3[k][l] += array[k][n2] * array2[n2][l];
                }
            }
        }
    }
    
    protected void spline2_segment(final int n, final int n2, final float[][] array, final int n3) {
        final float n4 = this.splineVertices[n][9];
        final float n5 = this.splineVertices[n][10];
        final float n6 = this.splineVertices[n + 1][9];
        final float n7 = this.splineVertices[n + 1][10];
        final float n8 = this.splineVertices[n + 2][9];
        final float n9 = this.splineVertices[n + 2][10];
        final float n10 = this.splineVertices[n + 3][9];
        final float n11 = this.splineVertices[n + 3][10];
        float n12 = this.splineVertices[n2][9];
        float n13 = this.splineVertices[n2][10];
        float n14 = array[1][0] * n4 + array[1][1] * n6 + array[1][2] * n8 + array[1][3] * n10;
        float n15 = array[2][0] * n4 + array[2][1] * n6 + array[2][2] * n8 + array[2][3] * n10;
        final float n16 = array[3][0] * n4 + array[3][1] * n6 + array[3][2] * n8 + array[3][3] * n10;
        float n17 = array[1][0] * n5 + array[1][1] * n7 + array[1][2] * n9 + array[1][3] * n11;
        float n18 = array[2][0] * n5 + array[2][1] * n7 + array[2][2] * n9 + array[2][3] * n11;
        final float n19 = array[3][0] * n5 + array[3][1] * n7 + array[3][2] * n9 + array[3][3] * n11;
        final int splineVertexCount = this.splineVertexCount;
        this.vertex(n12, n13);
        for (int i = 0; i < n3; ++i) {
            n12 += n14;
            n14 += n15;
            n15 += n16;
            n13 += n17;
            n17 += n18;
            n18 += n19;
            this.vertex(n12, n13);
        }
        this.splineVertexCount = splineVertexCount;
    }
    
    protected void spline3_segment(final int n, final int n2, final float[][] array, final int n3) {
        final float n4 = this.splineVertices[n][9];
        final float n5 = this.splineVertices[n][10];
        final float n6 = this.splineVertices[n][11];
        final float n7 = this.splineVertices[n + 1][9];
        final float n8 = this.splineVertices[n + 1][10];
        final float n9 = this.splineVertices[n + 1][11];
        final float n10 = this.splineVertices[n + 2][9];
        final float n11 = this.splineVertices[n + 2][10];
        final float n12 = this.splineVertices[n + 2][11];
        final float n13 = this.splineVertices[n + 3][9];
        final float n14 = this.splineVertices[n + 3][10];
        final float n15 = this.splineVertices[n + 3][11];
        float n16 = this.splineVertices[n2][9];
        float n17 = this.splineVertices[n2][10];
        float n18 = this.splineVertices[n2][11];
        float n19 = array[1][0] * n4 + array[1][1] * n7 + array[1][2] * n10 + array[1][3] * n13;
        float n20 = array[2][0] * n4 + array[2][1] * n7 + array[2][2] * n10 + array[2][3] * n13;
        final float n21 = array[3][0] * n4 + array[3][1] * n7 + array[3][2] * n10 + array[3][3] * n13;
        float n22 = array[1][0] * n5 + array[1][1] * n8 + array[1][2] * n11 + array[1][3] * n14;
        float n23 = array[2][0] * n5 + array[2][1] * n8 + array[2][2] * n11 + array[2][3] * n14;
        final float n24 = array[3][0] * n5 + array[3][1] * n8 + array[3][2] * n11 + array[3][3] * n14;
        float n25 = array[1][0] * n6 + array[1][1] * n9 + array[1][2] * n12 + array[1][3] * n15;
        float n26 = array[2][0] * n6 + array[2][1] * n9 + array[2][2] * n12 + array[2][3] * n15;
        final float n27 = array[3][0] * n6 + array[3][1] * n9 + array[3][2] * n12 + array[3][3] * n15;
        final int splineVertexCount = this.splineVertexCount;
        this.vertex(n16, n17, n18);
        for (int i = 0; i < n3; ++i) {
            n16 += n19;
            n19 += n20;
            n20 += n21;
            n17 += n22;
            n22 += n23;
            n23 += n24;
            n18 += n25;
            n25 += n26;
            n26 += n27;
            this.vertex(n16, n17, n18);
        }
        this.splineVertexCount = splineVertexCount;
    }
    
    public void image(final PImage pImage, final float n, final float n2) {
        this.imageImpl(pImage, n, n2, n + pImage.width, n2 + pImage.height, 0, 0, pImage.width, pImage.height);
    }
    
    public void image(final PImage pImage, final float n, final float n2, final float n3, final float n4) {
        this.image(pImage, n, n2, n3, n4, 0, 0, pImage.width, pImage.height);
    }
    
    public void image(final PImage pImage, float n, float n2, float n3, float n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.imageMode == 0) {
            if (n3 < 0.0f) {
                n += n3;
                n3 = -n3;
            }
            if (n4 < 0.0f) {
                n2 += n4;
                n4 = -n4;
            }
            this.imageImpl(pImage, n, n2, n + n3, n2 + n4, n5, n6, n7, n8);
        }
        else if (this.imageMode == 1) {
            if (n3 < n) {
                final float n9 = n;
                n = n3;
                n3 = n9;
            }
            if (n4 < n2) {
                final float n10 = n2;
                n2 = n4;
                n4 = n10;
            }
            this.imageImpl(pImage, n, n2, n3, n4, n5, n6, n7, n8);
        }
    }
    
    protected void imageImpl(final PImage pImage, final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8) {
        System.err.println("unimplemented imageImpl() in PGraphics");
    }
    
    public void textFont(final PFont pFont, final float n) {
        this.textFont(pFont);
        this.textSize(n);
    }
    
    public void textFont(final PFont textFont) {
        if (textFont != null) {
            this.textFont = textFont;
            this.textSize(this.textFont.size);
            return;
        }
        throw new RuntimeException("a null PFont was passed to textFont()");
    }
    
    public void textSize(final float textSize) {
        if (this.textFont == null) {
            throw new RuntimeException("use textFont() before textSize()");
        }
        if (this.textMode == 128 && textSize != this.textFont.size) {
            throw new RuntimeException("can't use textSize() with textMode(SCREEN)");
        }
        this.textSize = textSize;
        this.textLeading = this.textSize * ((this.textFont.ascent() + this.textFont.descent()) * 1.275f);
    }
    
    public void textLeading(final float textLeading) {
        this.textLeading = textLeading;
    }
    
    public void textAlign(final int textAlign) {
        this.textAlign = textAlign;
    }
    
    public void textMode(final int textMode) {
        if (textMode != 128 && textMode != 3) {
            throw new RuntimeException("Only textMode(SCREEN) or textMode(MODEL) can be used. Maybe you meant textAlign()?");
        }
        if (this.textFont != null) {
            this.textMode = textMode;
            if (this.textMode == 128) {
                this.textSize(this.textFont.size);
            }
            return;
        }
        throw new RuntimeException("use textFont() before textMode()");
    }
    
    public float textAscent() {
        if (this.textFont != null) {
            return this.textFont.ascent() * this.textSize;
        }
        throw new RuntimeException("use textFont() before textAscent()");
    }
    
    public float textDescent() {
        if (this.textFont != null) {
            return this.textFont.descent() * this.textSize;
        }
        throw new RuntimeException("use textFont() before textDescent()");
    }
    
    public float textWidth(final char c) {
        if (this.textFont != null) {
            return this.textFont.width(c) * this.textSize;
        }
        throw new RuntimeException("use textFont() before textWidth()");
    }
    
    public float textWidth(final String s) {
        if (this.textFont != null) {
            return this.textFont.width(s) * this.textSize;
        }
        throw new RuntimeException("use textFont() before textWidth()");
    }
    
    public void text(final char c, final float n, final float n2) {
        this.text(c, n, n2, 0.0f);
    }
    
    public void text(final char c, final float n, final float n2, final float n3) {
        if (n3 != 0.0f && this.textMode == 128) {
            throw new RuntimeException("textMode(SCREEN) cannot have a z coordinate");
        }
        if (this.textFont != null) {
            if (this.textMode == 128) {
                this.loadPixels();
            }
            this.textFont.text(c, n, n2, n3, this);
            if (this.textMode == 128) {
                this.updatePixels();
            }
            return;
        }
        throw new RuntimeException("use textFont() before text()");
    }
    
    public void text(final String s, final float n, final float n2) {
        this.text(s, n, n2, 0.0f);
    }
    
    public void text(final String s, final float n, final float n2, final float n3) {
        if (n3 != 0.0f && this.textMode == 128) {
            throw new RuntimeException("textMode(SCREEN) cannot have a z coordinate");
        }
        if (this.textFont != null) {
            if (this.textMode == 128) {
                this.loadPixels();
            }
            this.textFont.text(s, n, n2, n3, this);
            if (this.textMode == 128) {
                this.updatePixels();
            }
            return;
        }
        throw new RuntimeException("use textFont() before text()");
    }
    
    public void text(final String s, final float n, final float n2, final float n3, final float n4) {
        this.text(s, n, n2, n3, n4, 0.0f);
    }
    
    public void text(final String s, float n, float n2, float n3, float n4, final float n5) {
        if (this.textFont != null) {
            switch (this.rectMode) {
                case 0: {
                    n3 += n;
                    n4 += n2;
                    break;
                }
                case 2: {
                    final float n6 = n3;
                    final float n7 = n4;
                    n3 = n + n6;
                    n4 = n2 + n7;
                    n -= n6;
                    n2 -= n7;
                    break;
                }
                case 3: {
                    final float n8 = n3 / 2.0f;
                    final float n9 = n4 / 2.0f;
                    n3 = n + n8;
                    n4 = n2 + n9;
                    n -= n8;
                    n2 -= n9;
                    break;
                }
            }
            if (n3 < n) {
                final float n10 = n;
                n = n3;
                n3 = n10;
            }
            if (n4 < n2) {
                final float n11 = n2;
                n2 = n4;
                n4 = n11;
            }
            if (this.textMode == 128) {
                this.loadPixels();
            }
            this.textFont.text(s, n, n2, n3, n4, n5, this);
            if (this.textMode == 128) {
                this.updatePixels();
            }
            return;
        }
        throw new RuntimeException("use textFont() before text()");
    }
    
    public void text(final int n, final float n2, final float n3) {
        this.text(String.valueOf(n), n2, n3);
    }
    
    public void text(final int n, final float n2, final float n3, final float n4) {
        this.text(String.valueOf(n), n2, n3, n4);
    }
    
    public void text(final float n, final float n2, final float n3) {
        this.text(PApplet.nfs(n, 0, 3), n2, n3);
    }
    
    public void text(final float n, final float n2, final float n3, final float n4) {
    }
    
    protected void textImpl(final char c, final float n, final float n2, final float n3) {
        final int index = this.textFont.index(c);
        if (index == -1) {
            return;
        }
        final PImage pImage = this.textFont.images[index];
        if (this.textMode == 3) {
            final float n4 = this.textFont.height[index] / this.textFont.fheight;
            final float n5 = this.textFont.width[index] / this.textFont.fwidth;
            final float n6 = this.textFont.leftExtent[index] / this.textFont.fwidth;
            final float n7 = this.textFont.topExtent[index] / this.textFont.fheight;
            final float n8 = n + n6 * this.textSize;
            final float n9 = n2 - n7 * this.textSize;
            this.textImplObject(pImage, n8, n9, n3, n8 + n5 * this.textSize, n9 + n4 * this.textSize, n3, this.textFont.width[index], this.textFont.height[index]);
        }
        else if (this.textMode == 128) {
            this.textImplScreen(pImage, (int)n + this.textFont.leftExtent[index], (int)n2 - this.textFont.topExtent[index], this.textFont.width[index], this.textFont.height[index]);
        }
    }
    
    protected void textImplObject(final PImage pImage, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8) {
        final boolean tint = this.tint;
        final int tintColor = this.tintColor;
        final float tintR = this.tintR;
        final float tintG = this.tintG;
        final float tintB = this.tintB;
        final float tintA = this.tintA;
        final boolean tintAlpha = this.tintAlpha;
        this.tint = true;
        this.tintColor = this.fillColor;
        this.tintR = this.fillR;
        this.tintG = this.fillG;
        this.tintB = this.fillB;
        this.tintA = this.fillA;
        this.tintAlpha = this.fillAlpha;
        this.imageImpl(pImage, n, n2, n4, n5, 0, 0, n7, n8);
        this.tint = tint;
        this.tintColor = tintColor;
        this.tintR = tintR;
        this.tintG = tintG;
        this.tintB = tintB;
        this.tintA = tintA;
        this.tintAlpha = tintAlpha;
    }
    
    protected void textImplScreen(final PImage pImage, int n, int n2, int n3, int n4) {
        int n5 = 0;
        int n6 = 0;
        if (n >= this.width || n2 >= this.height || n + n3 < 0 || n2 + n4 < 0) {
            return;
        }
        if (n < 0) {
            n5 -= n;
            n3 += n;
            n = 0;
        }
        if (n2 < 0) {
            n6 -= n2;
            n4 += n2;
            n2 = 0;
        }
        if (n + n3 > this.width) {
            n3 -= n + n3 - this.width;
        }
        if (n2 + n4 > this.height) {
            n4 -= n2 + n4 - this.height;
        }
        final int fillRi = this.fillRi;
        final int fillGi = this.fillGi;
        final int fillBi = this.fillBi;
        final int fillAi = this.fillAi;
        final int[] pixels = pImage.pixels;
        for (int i = n6; i < n6 + n4; ++i) {
            for (int j = n5; j < n5 + n3; ++j) {
                final int n7 = fillAi * pixels[i * this.textFont.twidth + j] >> 8;
                final int n8 = n7 ^ 0xFF;
                final int n9 = pixels[i * pImage.width + j];
                final int n10 = this.pixels[(n2 + i - n6) * this.width + (n + j - n5)];
                this.pixels[(n2 + i - n6) * this.width + n + j - n5] = (0xFF000000 | (n7 * fillRi + n8 * (n10 >> 16 & 0xFF) & 0xFF00) << 8 | (n7 * fillGi + n8 * (n10 >> 8 & 0xFF) & 0xFF00) | n7 * fillBi + n8 * (n10 & 0xFF) >> 8);
            }
        }
    }
    
    public void translate(final float n, final float n2) {
        this.m02 += n * this.m00 + n2 * this.m01 + this.m02;
        this.m12 += n * this.m10 + n2 * this.m11 + this.m12;
    }
    
    public void translate(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("translate");
    }
    
    public void rotate(final float n) {
        final float n2 = (float)Math.cos(n);
        final float n3 = (float)Math.sin(n);
        this.applyMatrix(n2, -n3, 0.0f, n3, n2, 0.0f);
    }
    
    public void rotateX(final float n) {
        this.depthError("rotateX");
    }
    
    public void rotateY(final float n) {
        this.depthError("rotateY");
    }
    
    public void rotateZ(final float n) {
        this.depthError("rotateZ");
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        throw new RuntimeException("rotate(angle, x, y, z) can only be used with P3D or OPENGL");
    }
    
    public void scale(final float n) {
        this.applyMatrix(n, 0.0f, 0.0f, 0.0f, n, 0.0f);
    }
    
    public void scale(final float n, final float n2) {
        this.applyMatrix(n, 0.0f, 0.0f, 0.0f, n2, 0.0f);
    }
    
    public void scale(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("scale");
    }
    
    public void pushMatrix() {
        if (this.matrixStackDepth + 1 == 32) {
            throw new RuntimeException("too many calls to pushMatrix()");
        }
        final float[] array = this.matrixStack[this.matrixStackDepth];
        array[0] = this.m00;
        array[1] = this.m01;
        array[2] = this.m02;
        array[3] = this.m10;
        array[4] = this.m11;
        array[5] = this.m12;
        ++this.matrixStackDepth;
    }
    
    public void popMatrix() {
        if (this.matrixStackDepth == 0) {
            throw new RuntimeException("too many calls to popMatrix() (and not enough to pushMatrix)");
        }
        --this.matrixStackDepth;
        final float[] array = this.matrixStack[this.matrixStackDepth];
        this.m00 = array[0];
        this.m01 = array[1];
        this.m02 = array[2];
        this.m10 = array[3];
        this.m11 = array[4];
        this.m12 = array[5];
    }
    
    public void resetMatrix() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        final float m00 = this.m00 * n + this.m01 * n4;
        final float m2 = this.m00 * n2 + this.m01 * n5;
        final float m3 = this.m00 * n3 + this.m01 * n6 + this.m02;
        final float m4 = this.m10 * n + this.m11 * n4;
        final float m5 = this.m10 * n2 + this.m11 * n5;
        final float m6 = this.m10 * n3 + this.m11 * n6 + this.m12;
        this.m00 = m00;
        this.m01 = m2;
        this.m02 = m3;
        this.m10 = m4;
        this.m11 = m5;
        this.m12 = m6;
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        throw new RuntimeException("applyMatrix() with a 4x4 matrix can only be used with OPENGL or P3D");
    }
    
    public void printMatrix() {
        float n = Math.abs(this.m00);
        if (Math.abs(this.m01) > n) {
            n = Math.abs(this.m01);
        }
        if (Math.abs(this.m02) > n) {
            n = Math.abs(this.m02);
        }
        if (Math.abs(this.m10) > n) {
            n = Math.abs(this.m10);
        }
        if (Math.abs(this.m11) > n) {
            n = Math.abs(this.m11);
        }
        if (Math.abs(this.m12) > n) {
            n = Math.abs(this.m12);
        }
        if (Float.isNaN(n) || Float.isInfinite(n)) {
            n = 1000000.0f;
        }
        int n2 = 1;
        int n3 = (int)n;
        while ((n3 /= 10) != 0) {
            ++n2;
        }
        System.out.println(PApplet.nfs(this.m00, n2, 4) + ' ' + PApplet.nfs(this.m01, n2, 4) + ' ' + PApplet.nfs(this.m02, n2, 4));
        System.out.println(PApplet.nfs(this.m10, n2, 4) + ' ' + PApplet.nfs(this.m11, n2, 4) + ' ' + PApplet.nfs(this.m12, n2, 4));
        System.out.println();
    }
    
    public void cameraMode(final int n) {
        this.depthError("cameraMode");
    }
    
    public void beginCamera() {
        this.depthError("beginCamera");
    }
    
    public void endCamera() {
        this.depthError("endCamera");
    }
    
    public void camera() {
        this.depthError("camera");
    }
    
    public void camera(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.depthError("camera");
    }
    
    public void ortho() {
        this.depthError("ortho");
    }
    
    public void ortho(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.depthError("ortho");
    }
    
    public void perspective() {
        this.depthError("perspective");
    }
    
    public void perspective(final float n, final float n2, final float n3, final float n4) {
        this.depthError("perspective");
    }
    
    public void frustum(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.depthError("frustum");
    }
    
    public void printCamera() {
        this.depthError("printCamera");
    }
    
    public void printProjection() {
        this.depthError("printCamera");
    }
    
    public float screenX(final float n, final float n2) {
        return this.m00 * n + this.m01 * n2 + this.m02;
    }
    
    public float screenY(final float n, final float n2) {
        return this.m10 * n + this.m11 * n2 + this.m12;
    }
    
    public float screenX(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("screenX");
        return 0.0f;
    }
    
    public float screenY(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("screenY");
        return 0.0f;
    }
    
    public float screenZ(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("screenZ");
        return 0.0f;
    }
    
    public float modelX(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("modelX");
        return 0.0f;
    }
    
    public float modelY(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("modelY");
        return 0.0f;
    }
    
    public float modelZ(final float n, final float n2, final float n3) {
        this.depthErrorXYZ("modelZ");
        return 0.0f;
    }
    
    public void colorMode(final int n) {
        this.colorMode(n, this.colorModeX, this.colorModeY, this.colorModeZ, this.colorModeA);
    }
    
    public void colorMode(final int n, final float n2) {
        this.colorMode(n, n2, n2, n2, n2);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4) {
        this.colorMode(n, n2, n3, n4, this.colorModeA);
    }
    
    public void colorMode(final int colorMode, final float colorModeX, final float colorModeY, final float colorModeZ, final float colorModeA) {
        this.colorMode = colorMode;
        this.colorModeX = colorModeX;
        this.colorModeY = colorModeY;
        this.colorModeZ = colorModeZ;
        this.colorModeA = colorModeA;
        boolean colorScale = false;
        if (colorModeA != 1.0f || colorModeX != colorModeY || colorModeY != colorModeZ || colorModeZ != colorModeA) {
            colorScale = true;
        }
        this.colorScale = colorScale;
        boolean colorRgb255 = false;
        if (this.colorMode == 1 && this.colorModeA == 255.0f && this.colorModeX == 255.0f && this.colorModeY == 255.0f && this.colorModeZ == 255.0f) {
            colorRgb255 = true;
        }
        this.colorRgb255 = colorRgb255;
    }
    
    protected void colorCalc(final float n) {
        this.colorCalc(n, this.colorModeA);
    }
    
    protected void colorCalc(float colorModeX, float colorModeA) {
        if (colorModeX > this.colorModeX) {
            colorModeX = this.colorModeX;
        }
        if (colorModeA > this.colorModeA) {
            colorModeA = this.colorModeA;
        }
        if (colorModeX < 0.0f) {
            colorModeX = 0.0f;
        }
        if (colorModeA < 0.0f) {
            colorModeA = 0.0f;
        }
        this.calcR = (this.colorScale ? (colorModeX / this.colorModeX) : colorModeX);
        this.calcG = this.calcR;
        this.calcB = this.calcR;
        this.calcA = (this.colorScale ? (colorModeA / this.colorModeA) : colorModeA);
        this.calcRi = (int)(this.calcR * 255.0f);
        this.calcGi = (int)(this.calcG * 255.0f);
        this.calcBi = (int)(this.calcB * 255.0f);
        this.calcAi = (int)(this.calcA * 255.0f);
        this.calcColor = (this.calcAi << 24 | this.calcRi << 16 | this.calcGi << 8 | this.calcBi);
        boolean calcAlpha = false;
        if (this.calcAi != 255) {
            calcAlpha = true;
        }
        this.calcAlpha = calcAlpha;
    }
    
    protected void colorCalc(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3, this.colorModeA);
    }
    
    protected void colorCalc(float colorModeX, float colorModeY, float colorModeZ, float colorModeA) {
        if (colorModeX > this.colorModeX) {
            colorModeX = this.colorModeX;
        }
        if (colorModeY > this.colorModeY) {
            colorModeY = this.colorModeY;
        }
        if (colorModeZ > this.colorModeZ) {
            colorModeZ = this.colorModeZ;
        }
        if (colorModeA > this.colorModeA) {
            colorModeA = this.colorModeA;
        }
        if (colorModeX < 0.0f) {
            colorModeX = 0.0f;
        }
        if (colorModeY < 0.0f) {
            colorModeY = 0.0f;
        }
        if (colorModeZ < 0.0f) {
            colorModeZ = 0.0f;
        }
        if (colorModeA < 0.0f) {
            colorModeA = 0.0f;
        }
        switch (this.colorMode) {
            case 1: {
                if (this.colorScale) {
                    this.calcR = colorModeX / this.colorModeX;
                    this.calcG = colorModeY / this.colorModeY;
                    this.calcB = colorModeZ / this.colorModeZ;
                    this.calcA = colorModeA / this.colorModeA;
                }
                else {
                    this.calcR = colorModeX;
                    this.calcG = colorModeY;
                    this.calcB = colorModeZ;
                    this.calcA = colorModeA;
                }
                break;
            }
            case 3: {
                colorModeX /= this.colorModeX;
                colorModeY /= this.colorModeY;
                colorModeZ /= this.colorModeZ;
                this.calcA = (this.colorScale ? (colorModeA / this.colorModeA) : colorModeA);
                if (colorModeY == 0.0f) {
                    final float calcR = colorModeZ;
                    this.calcB = calcR;
                    this.calcG = calcR;
                    this.calcR = calcR;
                }
                else {
                    final float n = (colorModeX - (int)colorModeX) * 6.0f;
                    final float n2 = n - (int)n;
                    final float n3 = colorModeZ * (1.0f - colorModeY);
                    final float calcB = colorModeZ * (1.0f - colorModeY * n2);
                    final float calcR2 = colorModeZ * (1.0f - colorModeY * (1.0f - n2));
                    switch ((int)n) {
                        case 0: {
                            this.calcR = colorModeZ;
                            this.calcG = calcR2;
                            this.calcB = n3;
                            break;
                        }
                        case 1: {
                            this.calcR = calcB;
                            this.calcG = colorModeZ;
                            this.calcB = n3;
                            break;
                        }
                        case 2: {
                            this.calcR = n3;
                            this.calcG = colorModeZ;
                            this.calcB = calcR2;
                            break;
                        }
                        case 3: {
                            this.calcR = n3;
                            this.calcG = calcB;
                            this.calcB = colorModeZ;
                            break;
                        }
                        case 4: {
                            this.calcR = calcR2;
                            this.calcG = n3;
                            this.calcB = colorModeZ;
                            break;
                        }
                        case 5: {
                            this.calcR = colorModeZ;
                            this.calcG = n3;
                            this.calcB = calcB;
                            break;
                        }
                    }
                }
                break;
            }
        }
        this.calcRi = (int)(255.0f * this.calcR);
        this.calcGi = (int)(255.0f * this.calcG);
        this.calcBi = (int)(255.0f * this.calcB);
        this.calcAi = (int)(255.0f * this.calcA);
        this.calcColor = (this.calcAi << 24 | this.calcRi << 16 | this.calcGi << 8 | this.calcBi);
        boolean calcAlpha = false;
        if (this.calcAi != 255) {
            calcAlpha = true;
        }
        this.calcAlpha = calcAlpha;
    }
    
    protected void colorFrom(final int calcColor) {
        this.calcColor = calcColor;
        this.calcAi = (calcColor >> 24 & 0xFF);
        this.calcRi = (calcColor >> 16 & 0xFF);
        this.calcGi = (calcColor >> 8 & 0xFF);
        this.calcBi = (calcColor & 0xFF);
        this.calcA = this.calcAi / 255.0f;
        this.calcR = this.calcRi / 255.0f;
        this.calcG = this.calcGi / 255.0f;
        this.calcB = this.calcBi / 255.0f;
        boolean calcAlpha = false;
        if (this.calcAi != 255) {
            calcAlpha = true;
        }
        this.calcAlpha = calcAlpha;
    }
    
    protected void colorTint() {
        this.tint = true;
        this.tintR = this.calcR;
        this.tintG = this.calcG;
        this.tintB = this.calcB;
        this.tintA = this.calcA;
        this.tintRi = this.calcRi;
        this.tintGi = this.calcGi;
        this.tintBi = this.calcBi;
        this.tintAi = this.calcAi;
        this.tintColor = this.calcColor;
        this.tintAlpha = this.calcAlpha;
    }
    
    protected void colorFill() {
        this.fill = true;
        this.fillR = this.calcR;
        this.fillG = this.calcG;
        this.fillB = this.calcB;
        this.fillA = this.calcA;
        this.fillRi = this.calcRi;
        this.fillGi = this.calcGi;
        this.fillBi = this.calcBi;
        this.fillAi = this.calcAi;
        this.fillColor = this.calcColor;
        this.fillAlpha = this.calcAlpha;
    }
    
    protected void colorStroke() {
        this.stroke = true;
        this.strokeR = this.calcR;
        this.strokeG = this.calcG;
        this.strokeB = this.calcB;
        this.strokeA = this.calcA;
        this.strokeRi = this.calcRi;
        this.strokeGi = this.calcGi;
        this.strokeBi = this.calcBi;
        this.strokeAi = this.calcAi;
        this.strokeColor = this.calcColor;
        this.strokeAlpha = this.calcAlpha;
    }
    
    protected void colorBackground() {
        this.backgroundR = this.calcR;
        this.backgroundG = this.calcG;
        this.backgroundB = this.calcB;
        this.backgroundRi = this.calcRi;
        this.backgroundGi = this.calcGi;
        this.backgroundBi = this.calcBi;
        this.backgroundColor = this.calcColor;
    }
    
    public void noTint() {
        this.tint = false;
    }
    
    public void tint(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorModeX) {
            this.tint((float)n);
        }
        else {
            this.colorFrom(n);
            this.colorTint();
        }
    }
    
    public void tint(final float n) {
        this.colorCalc(n);
        this.colorTint();
    }
    
    public void tint(final float n, final float n2) {
        this.colorCalc(n, n2);
        this.colorTint();
    }
    
    public void tint(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.colorTint();
    }
    
    public void tint(final float n, final float n2, final float n3, final float n4) {
        this.colorCalc(n, n2, n3, n4);
        this.colorTint();
    }
    
    public void noFill() {
        this.fill = false;
    }
    
    public void fill(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorModeX) {
            this.fill((float)n);
        }
        else {
            this.colorFrom(n);
            this.colorFill();
        }
    }
    
    public void fill(final float n) {
        this.colorCalc(n);
        this.colorFill();
    }
    
    public void fill(final float n, final float n2) {
        this.colorCalc(n, n2);
        this.colorFill();
    }
    
    public void fill(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.colorFill();
    }
    
    public void fill(final float n, final float n2, final float n3, final float n4) {
        this.colorCalc(n, n2, n3, n4);
        this.colorFill();
    }
    
    public void ambient(final int n) {
        this.depthError("ambient");
    }
    
    public void ambient(final float n) {
        this.depthError("ambient");
    }
    
    public void ambient(final float n, final float n2, final float n3) {
        this.depthError("ambient");
    }
    
    public void specular(final int n) {
        this.depthError("specular");
    }
    
    public void specular(final float n) {
        this.depthError("specular");
    }
    
    public void specular(final float n, final float n2) {
        this.depthError("specular");
    }
    
    public void specular(final float n, final float n2, final float n3) {
        this.depthError("specular");
    }
    
    public void specular(final float n, final float n2, final float n3, final float n4) {
        this.depthError("specular");
    }
    
    public void shininess(final float n) {
        this.depthError("shininess");
    }
    
    public void emissive(final int n) {
        this.depthError("emissive");
    }
    
    public void emissive(final float n) {
        this.depthError("emissive");
    }
    
    public void emissive(final float n, final float n2, final float n3) {
        this.depthError("emissive");
    }
    
    public void lights() {
        this.depthError("lights");
    }
    
    public void ambientLight(final float n, final float n2, final float n3) {
        this.depthError("ambientLight");
    }
    
    public void ambientLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.depthError("ambientLight");
    }
    
    public void directionalLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.depthError("directionalLight");
    }
    
    public void pointLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.depthError("pointLight");
    }
    
    public void spotLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11) {
        this.depthError("spotLight");
    }
    
    public void lightFalloff(final float n, final float n2, final float n3) {
        this.depthError("lightFalloff");
    }
    
    public void lightSpecular(final float n, final float n2, final float n3) {
        this.depthError("lightSpecular");
    }
    
    public void strokeWeight(final float strokeWeight) {
        this.strokeWeight = strokeWeight;
    }
    
    public void strokeJoin(final int strokeJoin) {
        this.strokeJoin = strokeJoin;
    }
    
    public void strokeCap(final int strokeCap) {
        this.strokeCap = strokeCap;
    }
    
    public void noStroke() {
        this.stroke = false;
    }
    
    public void stroke(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorModeX) {
            this.stroke((float)n);
        }
        else {
            this.colorFrom(n);
            this.colorStroke();
        }
    }
    
    public void stroke(final float n) {
        this.colorCalc(n);
        this.colorStroke();
    }
    
    public void stroke(final float n, final float n2) {
        this.colorCalc(n, n2);
        this.colorStroke();
    }
    
    public void stroke(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.colorStroke();
    }
    
    public void stroke(final float n, final float n2, final float n3, final float n4) {
        this.colorCalc(n, n2, n3, n4);
        this.colorStroke();
    }
    
    public void background(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorModeX) {
            this.background((float)n);
        }
        else {
            this.colorFrom(n);
            this.colorBackground();
        }
        this.clear();
    }
    
    public void background(final float n) {
        this.colorCalc(n);
        this.colorBackground();
        this.clear();
    }
    
    public void background(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.colorBackground();
        this.clear();
    }
    
    public void background(final PImage pImage) {
        if (pImage.width != this.width || pImage.height != this.height) {
            throw new RuntimeException("background image must be the same size as your application");
        }
        if (pImage.format != 1 && pImage.format != 2) {
            throw new RuntimeException("background images should be RGB or ARGB");
        }
        this.backgroundColor = 0;
        System.arraycopy(pImage.pixels, 0, this.pixels, 0, this.pixels.length);
    }
    
    protected void clear() {
        for (int i = 0; i < this.pixelCount; ++i) {
            this.pixels[i] = this.backgroundColor;
        }
    }
    
    protected void depthError(final String s) {
        throw new RuntimeException(s + "() can only be used with P3D or OPENGL.");
    }
    
    protected void depthErrorXYZ(final String s) {
        throw new RuntimeException(s + "(x, y, z) can only be used with OPENGL or P3D, use " + s + "(x, y) instead.");
    }
    
    public final int color(int n) {
        if (this.colorRgb255) {
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            return 0xFF000000 | n << 16 | n << 8 | n;
        }
        this.colorCalc(n);
        return this.calcColor;
    }
    
    public final int color(final float n) {
        this.colorCalc(n);
        return this.calcColor;
    }
    
    public final int color(int n, int n2) {
        if (this.colorRgb255) {
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            if (n2 > 255) {
                n2 = 255;
            }
            else if (n2 < 0) {
                n2 = 0;
            }
            return (n2 & 0xFF) << 24 | n << 16 | n << 8 | n;
        }
        this.colorCalc(n, n2);
        return this.calcColor;
    }
    
    public final int color(final float n, final float n2) {
        this.colorCalc(n, n2);
        return this.calcColor;
    }
    
    public final int color(int n, int n2, int n3) {
        if (this.colorRgb255) {
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            if (n2 > 255) {
                n2 = 255;
            }
            else if (n2 < 0) {
                n2 = 0;
            }
            if (n3 > 255) {
                n3 = 255;
            }
            else if (n3 < 0) {
                n3 = 0;
            }
            return 0xFF000000 | n << 16 | n2 << 8 | n3;
        }
        this.colorCalc(n, n2, n3);
        return this.calcColor;
    }
    
    public final int color(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        return this.calcColor;
    }
    
    public final int color(int n, int n2, int n3, int n4) {
        if (this.colorRgb255) {
            if (n4 > 255) {
                n4 = 255;
            }
            else if (n4 < 0) {
                n4 = 0;
            }
            if (n > 255) {
                n = 255;
            }
            else if (n < 0) {
                n = 0;
            }
            if (n2 > 255) {
                n2 = 255;
            }
            else if (n2 < 0) {
                n2 = 0;
            }
            if (n3 > 255) {
                n3 = 255;
            }
            else if (n3 < 0) {
                n3 = 0;
            }
            return n4 << 24 | n << 16 | n2 << 8 | n3;
        }
        this.colorCalc(n, n2, n3, n4);
        return this.calcColor;
    }
    
    public final int color(final float n, final float n2, final float n3, final float n4) {
        this.colorCalc(n, n2, n3, n4);
        return this.calcColor;
    }
    
    public final float alpha(final int n) {
        final float n2 = n >> 24 & 0xFF;
        if (this.colorModeA == 255.0f) {
            return n2;
        }
        return n2 / 255.0f * this.colorModeA;
    }
    
    public final float red(final int n) {
        final float n2 = n >> 16 & 0xFF;
        if (this.colorRgb255) {
            return n2;
        }
        return n2 / 255.0f * this.colorModeX;
    }
    
    public final float green(final int n) {
        final float n2 = n >> 8 & 0xFF;
        if (this.colorRgb255) {
            return n2;
        }
        return n2 / 255.0f * this.colorModeY;
    }
    
    public final float blue(final int n) {
        final float n2 = n & 0xFF;
        if (this.colorRgb255) {
            return n2;
        }
        return n2 / 255.0f * this.colorModeZ;
    }
    
    public final float hue(final int cacheHsbKey) {
        if (cacheHsbKey != this.cacheHsbKey) {
            Color.RGBtoHSB(cacheHsbKey >> 16 & 0xFF, cacheHsbKey >> 8 & 0xFF, cacheHsbKey & 0xFF, this.cacheHsbValue);
            this.cacheHsbKey = cacheHsbKey;
        }
        return this.cacheHsbValue[0] * this.colorModeX;
    }
    
    public final float saturation(final int cacheHsbKey) {
        if (cacheHsbKey != this.cacheHsbKey) {
            Color.RGBtoHSB(cacheHsbKey >> 16 & 0xFF, cacheHsbKey >> 8 & 0xFF, cacheHsbKey & 0xFF, this.cacheHsbValue);
            this.cacheHsbKey = cacheHsbKey;
        }
        return this.cacheHsbValue[1] * this.colorModeY;
    }
    
    public final float brightness(final int cacheHsbKey) {
        if (cacheHsbKey != this.cacheHsbKey) {
            Color.RGBtoHSB(cacheHsbKey >> 16 & 0xFF, cacheHsbKey >> 8 & 0xFF, cacheHsbKey & 0xFF, this.cacheHsbValue);
            this.cacheHsbKey = cacheHsbKey;
        }
        return this.cacheHsbValue[2] * this.colorModeZ;
    }
    
    public void mask(final int[] array) {
        super.mask(array);
    }
    
    public void mask(final PImage pImage) {
        super.mask(pImage);
    }
    
    private final /* synthetic */ void this() {
        this.hints = new boolean[8];
        this.cacheHsbValue = new float[3];
        this.matrixStack = new float[32][16];
        this.vertices = new float[512][36];
        this.bezier_inited = false;
        this.bezier_detail = 20;
        final float[][] bezier_basis = { { -1.0f, 3, -3.0f, 1.0f }, { 3, -6.0f, 3, 0.0f }, { -3.0f, 3, 0.0f, 0.0f }, null };
        final int n = 3;
        final float[] array = new float[4];
        array[0] = 1.0f;
        bezier_basis[n] = array;
        this.bezier_basis = bezier_basis;
        this.bezierBasis = new PMatrix(-1.0f, 3, -3.0f, 1.0f, 3, -6.0f, 3, 0.0f, -3.0f, 3, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
        this.curve_inited = false;
        this.curve_detail = 20;
        this.curve_tightness = 0.0f;
    }
    
    public PGraphics() {
        this.this();
    }
    
    public PGraphics(final int n, final int n2, final PApplet pApplet) {
        this.this();
        if (pApplet != null) {
            pApplet.addListeners();
        }
        this.resize(n, n2);
    }
    
    static {
        sinLUT = new float[720];
        cosLUT = new float[720];
        for (int i = 0; i < 720; ++i) {
            PGraphics.sinLUT[i] = (float)Math.sin(i * 0.017453292f * 0.5f);
            PGraphics.cosLUT[i] = (float)Math.cos(i * 0.017453292f * 0.5f);
        }
    }
    
    class Path
    {
        public void moveTo(final float n, final float n2) {
        }
        
        public void lineTo(final float n, final float n2) {
        }
        
        public void curveTo(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        }
        
        public void closePath() {
        }
    }
}
