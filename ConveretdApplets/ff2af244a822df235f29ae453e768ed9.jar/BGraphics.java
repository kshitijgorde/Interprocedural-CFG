import java.awt.Color;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.zip.GZIPInputStream;
import java.io.InputStream;
import javax.accessibility.Accessible;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BGraphics extends BImage implements BConstants
{
    static boolean[] hints;
    static final int MAX_LIGHTS = 10;
    static final int MATRIX_STACK_DEPTH = 32;
    static final int DEFAULT_VERTICES = 512;
    static final int DEFAULT_LINES = 512;
    static final int DEFAULT_TRIANGLES = 256;
    static final int DEFAULT_TEXTURES = 3;
    static final int CVERTEX_ALLOC = 128;
    static final float[] sinLUT;
    static final float[] cosLUT;
    static final float SINCOS_PRECISION = 0.5f;
    static final int SINCOS_LENGTH = 720;
    static final int STRINGS = 0;
    static final int INTS = 1;
    static final int FLOATS = 2;
    static final int DOUBLES = 3;
    public Applet applet;
    public int width1;
    public int height1;
    int pixelCount;
    int[] stencil;
    float[] zbuffer;
    boolean depthTest;
    DirectColorModel cm;
    MemoryImageSource mis;
    Image image;
    int color_mode;
    boolean color_scale;
    float colorMaxX;
    float colorMaxY;
    float colorMaxZ;
    float colorMaxA;
    boolean _tint;
    boolean tint_alpha;
    float tintR;
    float tintG;
    float tintB;
    float tintA;
    int tintRi;
    int tintGi;
    int tintBi;
    int tintAi;
    int tint;
    boolean _fill;
    boolean fill_alpha;
    float fillR;
    float fillG;
    float fillB;
    float fillA;
    int fillRi;
    int fillGi;
    int fillBi;
    int fillAi;
    int fill;
    boolean _stroke;
    boolean stroke_alpha;
    float strokeR;
    float strokeG;
    float strokeB;
    float strokeA;
    int strokeRi;
    int strokeGi;
    int strokeBi;
    int strokeAi;
    int stroke;
    float strokeWeight;
    int strokeJoin;
    int strokeMiter;
    boolean _background;
    float backR;
    float backG;
    float backB;
    int backRi;
    int backGi;
    int backBi;
    int background;
    float calcR;
    float calcG;
    float calcB;
    float calcA;
    int calcRi;
    int calcGi;
    int calcBi;
    int calcAi;
    int calci;
    boolean calc_alpha;
    int cacheHsbKey;
    float[] cacheHsbValue;
    boolean lighting;
    float[] lightR;
    float[] lightG;
    float[] lightB;
    float[] lightX;
    float[] lightY;
    float[] lightZ;
    int[] lightKind;
    float m00;
    float m01;
    float m02;
    float m03;
    float m10;
    float m11;
    float m12;
    float m13;
    float m20;
    float m21;
    float m22;
    float m23;
    float m30;
    float m31;
    float m32;
    float m33;
    float[][] matrixStack;
    int matrixStackDepth;
    int camera_mode;
    int dimensions;
    float fov;
    float eyeX;
    float eyeY;
    float eyeDist;
    float nearDist;
    float farDist;
    float aspect;
    float p00;
    float p01;
    float p02;
    float p03;
    float p10;
    float p11;
    float p12;
    float p13;
    float p20;
    float p21;
    float p22;
    float p23;
    float p30;
    float p31;
    float p32;
    float p33;
    boolean shape;
    int shapeKind;
    BPolygon polygon;
    BPolygon fpolygon;
    BPolygon spolygon;
    float[][] svertices;
    BPolygon tpolygon;
    int TPOLYGON_MAX_VERTICES;
    int[] tpolygon_vertex_order;
    int shape_index;
    float[][] vertices;
    int vertex_count;
    int vertex_start;
    int vertex_end;
    int[] vertex_order;
    BLine line;
    int[][] lines;
    int lines_count;
    BTriangle triangle;
    int[][] triangles;
    int triangles_count;
    public boolean clip;
    public boolean z_order;
    float[][] frustum;
    float[] cp;
    int texture_mode;
    float textureU;
    float textureV;
    float normalX;
    float normalY;
    float normalZ;
    boolean texture;
    private BImage textureImage;
    BImage[] textures;
    int texture_index;
    boolean unchangedZ;
    boolean strokeChanged;
    boolean fillChanged;
    boolean normalChanged;
    float[][] cvertex;
    int cvertexIndex;
    boolean cverticesFlat;
    int image_mode;
    int rect_mode;
    int ellipse_mode;
    int sphere_detail;
    float[] sphereX;
    float[] sphereY;
    float[] sphereZ;
    int text_mode;
    int text_space;
    BFont text_font;
    boolean drawing_text;
    private boolean bezier_inited;
    private int bezier_segments;
    private float[][] bezier_basis;
    private float[][] bezier_forward;
    private float[][] bezier_draw;
    private boolean curve_inited;
    private int curve_segments;
    private float curve_tightness;
    private float[][] curve_basis;
    private float[][] curve_forward;
    private float[][] curve_draw;
    int sort_mode;
    String[] sort_strings;
    int[] sort_ints;
    float[] sort_floats;
    double[] sort_doubles;
    Object[] sort_objects;
    
    public void resize(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        this.allocate();
        this.background(this.background);
        this.fov = 60.0f;
        this.eyeX = this.width / 2.0f;
        this.eyeY = this.height / 2.0f;
        this.eyeDist = this.eyeY / this.tan(3.1415927f * this.fov / 360.0f);
        this.nearDist = this.eyeDist / 10.0f;
        this.farDist = this.eyeDist * 10.0f;
        this.aspect = this.width / this.height;
        this.beginCamera();
        this.perspective(this.fov, this.aspect, this.nearDist, this.farDist);
        this.lookat(this.eyeX, this.eyeY, this.eyeDist, this.eyeX, this.eyeY, 0.0f, 0.0f, 1.0f, 0.0f);
        this.endCamera();
        this.camera_mode = 3;
    }
    
    private final void allocate() {
        this.pixelCount = this.width * this.height;
        this.pixels = new int[this.pixelCount];
        this.zbuffer = new float[this.pixelCount];
        if (BGraphics.hints[2]) {
            this.stencil = new int[this.pixelCount];
        }
        for (int i = 0; i < this.pixelCount; ++i) {
            this.pixels[i] = -1;
        }
        this.cm = new DirectColorModel(32, 16711680, 65280, 255);
        (this.mis = new MemoryImageSource(this.width, this.height, this.pixels, 0, this.width)).setFullBufferUpdates(true);
        this.mis.setAnimated(true);
        this.image = Toolkit.getDefaultToolkit().createImage(this.mis);
        this.line = new BLine(this);
        if (BGraphics.hints[2]) {
            this.triangle = new BTriangle(this);
        }
    }
    
    public void defaults() {
        this.depthTest = true;
        this.colorMode(1, 255.0f);
        this.fill(255.0f);
        this.stroke(0);
        this.strokeWeight(1.0f);
        this.background(204);
        this.shape = false;
        this.shapeKind = 0;
        if (!BGraphics.hints[2]) {
            this.polygon = new BPolygon(this);
            this.fpolygon = new BPolygon(this);
            this.spolygon = new BPolygon(this);
            this.spolygon.vertexCount = 4;
            this.svertices = new float[2][];
        }
        this.text_font = null;
        this.noLights();
        this.matrixStackDepth = 0;
        this.lightR = new float[10];
        this.lightG = new float[10];
        this.lightB = new float[10];
        this.lightX = new float[10];
        this.lightY = new float[10];
        this.lightZ = new float[10];
        (this.lightKind = new int[10])[0] = 1;
        this.lightR[0] = 0.0f;
        this.lightG[0] = 0.0f;
        this.lightB[0] = 0.0f;
        this.lightX[0] = 0.0f;
        this.lightY[0] = 0.0f;
        this.lightZ[0] = 0.0f;
        this.lightKind[1] = 2;
        this.lightX[1] = this.eyeX;
        this.lightY[1] = this.eyeY;
        this.lightZ[1] = this.eyeDist;
        this.lightR[1] = 1.0f;
        this.lightG[1] = 1.0f;
        this.lightB[1] = 1.0f;
        for (int i = 2; i < 10; ++i) {
            this.lightKind[i] = 0;
        }
    }
    
    public void beginFrame() {
        this.resetMatrix();
        this.normalX = 0.0f;
        this.normalY = 0.0f;
        this.normalZ = 1.0f;
        if (BGraphics.hints[2]) {
            this.shape_index = 0;
            this.vertex_count = 0;
            this.vertex_start = 0;
            this.vertex_end = 0;
            this.lines_count = 0;
            this.line.reset();
            this.triangles_count = 0;
            this.triangle.reset();
            this.texture_index = 0;
        }
    }
    
    public void endFrame() {
        if (BGraphics.hints[2] && this.z_order) {
            for (int i = 0; i < this.triangles_count; ++i) {
                final float[] array = this.vertices[this.triangles[i][0]];
                final float[] array2 = this.vertices[this.triangles[i][1]];
                final float[] array3 = this.vertices[this.triangles[i][2]];
                final int n = this.triangles[i][4];
                final int index = this.triangles[i][3];
                this.triangle.reset();
                if (n > -1 && this.textures[n] != null) {
                    this.triangle.setTexture(this.textures[n]);
                    this.triangle.setUV(array[7], array[8], array2[7], array2[8], array3[7], array3[8]);
                }
                this.triangle.setIntensities(array[3], array[4], array[5], array[6], array2[3], array2[4], array2[5], array2[6], array3[3], array3[4], array3[5], array3[6]);
                this.triangle.setVertices(array[0], array[1], array[2], array2[0], array2[1], array2[2], array3[0], array3[1], array3[2]);
                this.triangle.setIndex(index);
                this.triangle.render();
            }
            for (int j = 0; j < this.lines_count; ++j) {
                final float[] array4 = this.vertices[this.lines[j][0]];
                final float[] array5 = this.vertices[this.lines[j][1]];
                final int index2 = this.lines[j][2];
                this.line.reset();
                this.line.setIntensities(array4[12], array4[13], array4[14], array4[15], array5[12], array5[13], array5[14], array5[15]);
                this.line.setVertices(array4[0], array4[1], array4[2], array5[0], array5[1], array5[2]);
                this.line.setIndex(index2);
                this.line.draw();
            }
        }
    }
    
    public final float[] nextVertex() {
        if (!BGraphics.hints[2]) {
            return this.polygon.nextVertex();
        }
        if (this.vertex_count == this.vertices.length) {
            final float[][] vertices = new float[this.vertex_count << 1][24];
            System.arraycopy(this.vertices, 0, vertices, 0, this.vertex_count);
            this.vertices = vertices;
            System.out.println("allocating more vertices " + this.vertices.length);
        }
        return this.vertices[this.vertex_count++];
    }
    
    public final void addTexture(final BImage bImage) {
        if (this.texture_index == this.textures.length - 1) {
            final BImage[] textures = new BImage[this.texture_index << 1];
            System.arraycopy(this.textures, 0, textures, 0, this.texture_index);
            this.textures = textures;
            System.out.println("allocating more textures " + this.textures.length);
        }
        if (this.textures[0] != null) {
            ++this.texture_index;
        }
        this.textures[this.texture_index] = bImage;
    }
    
    public final void addLine(final int n, final int n2) {
        if (this.lines_count == this.lines.length) {
            final int[][] lines = new int[this.lines_count << 1][4];
            System.arraycopy(this.lines, 0, lines, 0, this.lines_count);
            this.lines = lines;
            System.out.println("allocating more lines " + this.lines.length);
        }
        this.lines[this.lines_count][0] = n;
        this.lines[this.lines_count][1] = n2;
        if (this.smooth && !this._stroke) {
            this.lines[this.lines_count][2] = this.shape_index;
        }
        else {
            this.lines[this.lines_count][2] = -1;
        }
        this.lines[this.lines_count][3] = (this.strokeMiter | this.strokeJoin);
        ++this.lines_count;
    }
    
    public final void addTriangle(final int n, final int n2, final int n3) {
        if (this.triangles_count == this.triangles.length) {
            final int[][] triangles = new int[this.triangles_count << 1][5];
            System.arraycopy(this.triangles, 0, triangles, 0, this.triangles_count);
            this.triangles = triangles;
            System.out.println("allocating more triangles " + this.triangles.length);
        }
        this.triangles[this.triangles_count][0] = n;
        this.triangles[this.triangles_count][1] = n2;
        this.triangles[this.triangles_count][2] = n3;
        if (this.textureImage == null) {
            this.triangles[this.triangles_count][4] = -1;
        }
        else {
            this.triangles[this.triangles_count][4] = this.texture_index;
        }
        this.triangles[this.triangles_count][3] = this.shape_index;
        ++this.triangles_count;
    }
    
    private final void calc_lighting(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, float n7, float n8, float n9, final float[] array, final int n10) {
        if (!this.lighting) {
            array[n10] = n;
            array[n10 + 1] = n2;
            array[n10 + 2] = n3;
            return;
        }
        final float mag = this.mag(n7, n8, n9);
        if (mag != 0.0f) {
            n7 /= mag;
            n8 /= mag;
            n9 /= mag;
        }
        float n11 = 0.0f;
        float n12 = 0.0f;
        float n13 = 0.0f;
        for (int n14 = 1; n14 < 10 && this.lightKind[n14] != 0; ++n14) {
            float n15 = this.lightX[n14] - n4;
            float n16 = this.lightY[n14] - n5;
            float n17 = this.lightZ[n14] - n6;
            final float mag2 = this.mag(n15, n16, n17);
            if (mag2 != 0.0f) {
                n15 /= mag2;
                n16 /= mag2;
                n17 /= mag2;
            }
            final float n18 = n7 * n15 + n8 * n16 + n9 * n17;
            if (n18 > 0.0f) {
                n11 += this.lightR[n14] * n18;
                n12 += this.lightG[n14] * n18;
                n13 += this.lightB[n14] * n18;
            }
        }
        array[n10] = this.lightR[0] + n * n11;
        array[n10 + 1] = this.lightG[0] + n2 * n12;
        array[n10 + 2] = this.lightB[0] + n3 * n13;
        if (array[n10] > 1.0f) {
            array[n10] = 1.0f;
        }
        if (array[n10 + 1] > 1.0f) {
            array[n10 + 1] = 1.0f;
        }
        if (array[n10 + 2] > 1.0f) {
            array[n10 + 2] = 1.0f;
        }
    }
    
    public void beginShape() {
        this.beginShape(256);
    }
    
    public void beginShape(final int shapeKind) {
        this.shape = true;
        this.shapeKind = shapeKind;
        if (BGraphics.hints[2]) {
            ++this.shape_index;
            if (this.shape_index == -1) {
                this.shape_index = 0;
            }
            if (this.z_order) {
                this.vertex_start = this.vertex_count;
                this.vertex_end = 0;
            }
            else {
                this.vertex_count = 0;
                this.line.reset();
                this.lines_count = 0;
                this.triangle.reset();
                this.triangles_count = 0;
            }
            this.textureImage = null;
        }
        else {
            this.polygon.reset(0);
            this.fpolygon.reset(4);
            this.spolygon.reset(4);
            this.texture = false;
            this.polygon.interpUV = false;
        }
        this.cvertexIndex = 0;
        this.cverticesFlat = true;
        this.unchangedZ = true;
        this.strokeChanged = false;
        this.fillChanged = false;
        this.normalChanged = false;
    }
    
    public void texture(final BImage bImage) {
        if (BGraphics.hints[2]) {
            if (this.z_order) {
                this.addTexture(bImage);
            }
            else {
                this.triangle.setTexture(bImage);
            }
            this.textureImage = bImage;
        }
        else {
            this.texture = true;
            this.polygon.texture(bImage);
        }
    }
    
    public void textureMode(final int texture_mode) {
        this.texture_mode = texture_mode;
    }
    
    protected void vertex_texture(final float textureU, final float textureV) {
        if (BGraphics.hints[2]) {
            if (this.textureImage == null) {
                this.message(2, "gotta use texture() after beginShape() and before vertexTexture()");
                return;
            }
            if (this.texture_mode == 1) {
                this.textureU = ((textureU < this.textureImage.width) ? textureU : this.textureImage.width);
                if (this.textureU < 0.0f) {
                    this.textureU = 0.0f;
                }
                this.textureV = ((textureV < this.textureImage.height) ? textureV : this.textureImage.height);
                if (this.textureV < 0.0f) {
                    this.textureV = 0.0f;
                }
                this.textureU = textureU / this.textureImage.width;
                this.textureV = textureV / this.textureImage.height;
            }
            else {
                this.textureU = textureU;
                this.textureV = textureV;
                if (this.textureU < 0.0f) {
                    this.textureU = 0.0f;
                }
                if (this.textureV < 0.0f) {
                    this.textureV = 0.0f;
                }
                if (this.textureU > 1.0f) {
                    this.textureU = 1.0f;
                }
                if (this.textureV > 1.0f) {
                    this.textureV = 1.0f;
                }
            }
        }
        else {
            if (!this.texture) {
                this.message(2, "gotta use texture() after beginShape() and before vertex()");
                return;
            }
            if (this.texture_mode == 1) {
                this.textureU = ((textureU < this.polygon.twidth) ? textureU : this.polygon.twidth);
                if (this.textureU < 0.0f) {
                    this.textureU = 0.0f;
                }
                this.textureV = ((textureV < this.polygon.theight) ? textureV : this.polygon.theight);
                if (this.textureV < 0.0f) {
                    this.textureV = 0.0f;
                }
            }
            else {
                if (this.textureU < 0.0f) {
                    this.textureU = 0.0f;
                }
                if (this.textureV < 0.0f) {
                    this.textureV = 0.0f;
                }
                if (this.textureU > 1.0f) {
                    this.textureU = 1.0f;
                }
                if (this.textureV > 1.0f) {
                    this.textureV = 1.0f;
                }
                this.textureU = textureU * this.polygon.twidth;
                this.textureV = textureV * this.polygon.theight;
            }
        }
    }
    
    public void normal(final float normalX, final float normalY, final float normalZ) {
        if (this.shape && !this.normalChanged) {
            if (BGraphics.hints[2]) {
                for (int i = this.vertex_start; i < this.vertex_end; ++i) {
                    this.vertices[i][16] = this.normalX;
                    this.vertices[i][17] = this.normalY;
                    this.vertices[i][18] = this.normalZ;
                }
                for (int j = this.vertex_start; j < this.vertex_end; ++j) {
                    this.vertices[j][16] = this.normalX;
                    this.vertices[j][17] = this.normalY;
                    this.vertices[j][18] = this.normalZ;
                }
            }
            else {
                for (int k = 0; k < this.polygon.vertexCount; ++k) {
                    this.polygon.vertices[k][16] = this.normalX;
                    this.polygon.vertices[k][17] = this.normalY;
                    this.polygon.vertices[k][18] = this.normalZ;
                }
            }
            this.normalChanged = true;
        }
        this.normalX = normalX;
        this.normalY = normalY;
        this.normalZ = normalZ;
    }
    
    public void vertex(final float n, final float n2) {
        this.setup_vertex(this.nextVertex(), n, n2, 0.0f);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4) {
        this.vertex_texture(n3, n4);
        this.setup_vertex(this.nextVertex(), n, n2, 0.0f);
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        this.unchangedZ = false;
        this.dimensions = 3;
        this.setup_vertex(this.nextVertex(), n, n2, n3);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4, final float n5) {
        this.vertex_texture(n4, n5);
        this.unchangedZ = false;
        this.dimensions = 3;
        this.setup_vertex(this.nextVertex(), n, n2, n3);
    }
    
    private final void setup_vertex(final float[] array, final float n, final float n2, final float n3) {
        this.cvertexIndex = 0;
        array[9] = n;
        array[10] = n2;
        array[11] = n3;
        if (this._fill) {
            array[3] = this.fillR;
            array[4] = this.fillG;
            array[5] = this.fillB;
            array[6] = this.fillA;
        }
        if (this._stroke) {
            array[12] = this.strokeR;
            array[13] = this.strokeG;
            array[14] = this.strokeB;
            array[15] = this.strokeA;
            array[23] = this.strokeWeight;
        }
        if ((BGraphics.hints[2] && this.textureImage != null) || (!BGraphics.hints[2] && this.texture)) {
            array[7] = this.textureU;
            array[8] = this.textureV;
        }
        if (this.normalChanged) {
            array[16] = this.normalX;
            array[17] = this.normalY;
            array[18] = this.normalZ;
        }
    }
    
    private final void c_vertex(final float n, final float n2, final float n3, final boolean b) {
        if (this.cvertexIndex == 128) {
            System.arraycopy(this.cvertex[125], 0, this.cvertex[0], 0, 24);
            System.arraycopy(this.cvertex[126], 0, this.cvertex[1], 0, 24);
            System.arraycopy(this.cvertex[127], 0, this.cvertex[2], 0, 24);
            this.cvertexIndex = 3;
        }
        final int n4 = this.cvertexIndex + 1;
        if (this.cverticesFlat && n3 != 0.0f) {
            this.cverticesFlat = false;
        }
        this.setup_vertex(this.cvertex[this.cvertexIndex], n, n2, n3);
        this.cvertexIndex = n4;
        if (this.cvertexIndex > 3) {
            if (b) {
                if (this.cvertexIndex % 4 == 0) {
                    if (!this.bezier_inited) {
                        this.bezier_init();
                    }
                    if (this.cverticesFlat) {
                        this.spline_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10], this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.bezier_draw, this.bezier_segments);
                    }
                    else {
                        this.spline_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 4][11], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 3][11], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 2][11], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10], this.cvertex[this.cvertexIndex - 1][11], this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 4][11], this.bezier_draw, this.bezier_segments);
                    }
                }
            }
            else {
                if (!this.curve_inited) {
                    this.curve_init();
                }
                if (this.cverticesFlat) {
                    this.spline_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.curve_draw, this.curve_segments);
                }
                else {
                    this.spline_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 4][11], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 3][11], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 2][11], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10], this.cvertex[this.cvertexIndex - 1][11], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 3][11], this.curve_draw, this.curve_segments);
                }
            }
        }
        this.cvertexIndex = n4;
    }
    
    public void bezierVertex(final float n, final float n2) {
        this.c_vertex(n, n2, 0.0f, true);
    }
    
    public void bezierVertex(final float n, final float n2, final float n3) {
        this.c_vertex(n, n2, n3, true);
    }
    
    public void curveVertex(final float n, final float n2) {
        this.c_vertex(n, n2, 0.0f, false);
    }
    
    public void curveVertex(final float n, final float n2, final float n3) {
        this.c_vertex(n, n2, n3, false);
    }
    
    public void endShape_newgraphics() {
        this.shape = false;
        this.vertex_end = this.vertex_count;
        boolean b = false;
        if (this._stroke || this.smooth) {
            b = true;
        }
        boolean b2 = b;
        if (this.textureImage != null && this.textureImage.format == 4) {
            b2 = false;
        }
        if (b2) {
            switch (this.shapeKind) {
                case 16: {
                    for (int vertex_end = this.vertex_end, i = this.vertex_start; i < vertex_end; ++i) {
                        this.addLine(i, i);
                    }
                    break;
                }
                case 32:
                case 33:
                case 34: {
                    final int lines_count = this.lines_count;
                    final int n = this.vertex_end - 1;
                    int n2 = 0;
                    if (this.shapeKind == 32) {
                        n2 = 1;
                    }
                    for (int n3 = n2 + 1, j = this.vertex_start; j < n; j += n3) {
                        this.addLine(j, j + 1);
                    }
                    if (this.shapeKind == 34) {
                        this.addLine(n, this.lines[lines_count][0]);
                    }
                    break;
                }
                case 64:
                case 65: {
                    for (int n4 = this.vertex_end - 1, k = this.vertex_start; k < n4; ++k) {
                        final int n5 = k - this.vertex_start;
                        this.addLine(k, k + 1);
                        if (this.shapeKind == 64 && n5 % 3 == 1) {
                            ++k;
                        }
                    }
                    for (int n6 = this.vertex_end - 2, n7 = (this.shapeKind == 65) ? 1 : 3, l = this.vertex_start; l < n6; l += n7) {
                        this.addLine(l, l + 2);
                    }
                    break;
                }
                case 128:
                case 129: {
                    for (int n8 = this.vertex_end - 1, vertex_start = this.vertex_start; vertex_start < n8; ++vertex_start) {
                        final int n9 = vertex_start - this.vertex_start;
                        this.addLine(vertex_start, vertex_start + 1);
                        if (this.shapeKind == 128 && n9 % 4 == 2) {
                            ++vertex_start;
                        }
                    }
                    for (int n10 = this.vertex_end - 2, n11 = (this.shapeKind == 129) ? 2 : 4, vertex_start2 = this.vertex_start; vertex_start2 < n10; vertex_start2 += n11) {
                        this.addLine(vertex_start2, vertex_start2 + 3);
                    }
                    break;
                }
                case 256:
                case 257:
                case 258: {
                    final int lines_count2 = this.lines_count;
                    final int n12 = this.vertex_end - 1;
                    for (int vertex_start3 = this.vertex_start; vertex_start3 < n12; ++vertex_start3) {
                        this.addLine(vertex_start3, vertex_start3 + 1);
                    }
                    this.addLine(n12, this.lines[lines_count2][0]);
                    break;
                }
            }
        }
        if (this._fill) {
            switch (this.shapeKind) {
                case 64:
                case 65: {
                    for (int n13 = this.vertex_end - 2, n14 = (this.shapeKind == 64) ? 3 : 1, vertex_start4 = this.vertex_start; vertex_start4 < n13; vertex_start4 += n14) {
                        this.addTriangle(vertex_start4, vertex_start4 + 1, vertex_start4 + 2);
                    }
                    break;
                }
                case 128:
                case 129: {
                    for (int n15 = this.vertex_count - 3, n16 = (this.shapeKind == 128) ? 4 : 2, vertex_start5 = this.vertex_start; vertex_start5 < n15; vertex_start5 += n16) {
                        this.addTriangle(vertex_start5, vertex_start5 + 1, vertex_start5 + 2);
                        this.addTriangle(vertex_start5, vertex_start5 + 2, vertex_start5 + 3);
                    }
                    break;
                }
                case 256:
                case 257:
                case 258: {
                    this.triangulate_polygon();
                    break;
                }
            }
        }
        if (this.camera_mode == 3 && this.dimensions == 0) {
            for (int vertex_start6 = this.vertex_start; vertex_start6 < this.vertex_end; ++vertex_start6) {
                this.vertices[vertex_start6][0] = this.vertices[vertex_start6][9];
                this.vertices[vertex_start6][1] = this.vertices[vertex_start6][10];
            }
        }
        else if (this.camera_mode == 3 && this.dimensions == 2) {
            for (int vertex_start7 = this.vertex_start; vertex_start7 < this.vertex_end; ++vertex_start7) {
                this.vertices[vertex_start7][0] = this.m00 * this.vertices[vertex_start7][9] + this.m01 * this.vertices[vertex_start7][10] + this.m03;
                this.vertices[vertex_start7][1] = this.m10 * this.vertices[vertex_start7][9] + this.m11 * this.vertices[vertex_start7][10] + this.m13;
            }
        }
        else if (this.camera_mode == 2) {
            for (int vertex_start8 = this.vertex_start; vertex_start8 < this.vertex_end; ++vertex_start8) {
                final float[] array = this.vertices[vertex_start8];
                array[0] = array[9] - array[11];
                array[1] = -array[9] / 2.0f + array[10] - array[11] / 2.0f;
                array[2] = array[11];
            }
        }
        else {
            for (int vertex_start9 = this.vertex_start; vertex_start9 < this.vertex_end; ++vertex_start9) {
                final float[] array2 = this.vertices[vertex_start9];
                array2[19] = this.m00 * array2[9] + this.m01 * array2[10] + this.m02 * array2[11] + this.m03;
                array2[20] = this.m10 * array2[9] + this.m11 * array2[10] + this.m12 * array2[11] + this.m13;
                array2[21] = this.m20 * array2[9] + this.m21 * array2[10] + this.m22 * array2[11] + this.m23;
                array2[22] = this.m30 * array2[9] + this.m31 * array2[10] + this.m32 * array2[11] + this.m33;
            }
        }
        if (!this.normalChanged) {
            this.vertices[this.vertex_start][16] = this.normalX;
            this.vertices[this.vertex_start][17] = this.normalY;
            this.vertices[this.vertex_start][18] = this.normalZ;
        }
        int vertex_start10 = this.vertex_start;
        Label_1648: {
            break Label_1648;
            int n17;
            int vertex_end2;
            do {
                final float[] array3 = this.vertices[vertex_start10];
                final float n18 = this.m00 * array3[16] + this.m01 * array3[17] + this.m02 * array3[18] + this.m03;
                final float n19 = this.m10 * array3[16] + this.m11 * array3[17] + this.m12 * array3[18] + this.m13;
                final float n20 = this.m20 * array3[16] + this.m21 * array3[17] + this.m22 * array3[18] + this.m23;
                final float n21 = this.m30 * array3[16] + this.m31 * array3[17] + this.m32 * array3[18] + this.m33;
                if (n21 != 0.0f) {
                    array3[16] = n18 / n21;
                    array3[17] = n19 / n21;
                    array3[18] = n20 / n21;
                }
                else {
                    array3[16] = n18;
                    array3[17] = n19;
                    array3[18] = n20;
                }
                final float mag = this.mag(array3[16], array3[17], array3[18]);
                if (mag != 0.0f) {
                    final float[] array4 = array3;
                    final int n22 = 16;
                    array4[n22] /= mag;
                    final float[] array5 = array3;
                    final int n23 = 17;
                    array5[n23] /= mag;
                    final float[] array6 = array3;
                    final int n24 = 18;
                    array6[n24] /= mag;
                }
                ++vertex_start10;
                n17 = vertex_start10;
                vertex_end2 = 1;
                if (this.normalChanged) {
                    vertex_end2 = this.vertex_end;
                }
            } while (n17 < vertex_end2);
        }
        if (this.lighting) {
            final float[] array7 = this.vertices[this.vertex_start];
            for (int vertex_start11 = this.vertex_start; vertex_start11 < this.vertex_end; ++vertex_start11) {
                final float[] array8 = this.vertices[vertex_start11];
                if (this.normalChanged) {
                    if (this._fill) {
                        this.calc_lighting(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 3);
                    }
                    if (this._stroke) {
                        this.calc_lighting(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 12);
                    }
                }
                else {
                    if (this._fill) {
                        this.calc_lighting(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 3);
                    }
                    if (this._stroke) {
                        this.calc_lighting(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 12);
                    }
                }
            }
        }
        if (this.camera_mode == 3 && this.dimensions == 3) {
            for (int vertex_start12 = this.vertex_start; vertex_start12 < this.vertex_end; ++vertex_start12) {
                final float[] array9 = this.vertices[vertex_start12];
                float n25 = this.p00 * array9[19] + this.p01 * array9[20] + this.p02 * array9[21] + this.p03 * array9[22];
                float n26 = this.p10 * array9[19] + this.p11 * array9[20] + this.p12 * array9[21] + this.p13 * array9[22];
                float n27 = this.p20 * array9[19] + this.p21 * array9[20] + this.p22 * array9[21] + this.p23 * array9[22];
                final float n28 = this.p30 * array9[19] + this.p31 * array9[20] + this.p32 * array9[21] + this.p33 * array9[22];
                if (n28 != 0.0f) {
                    n25 /= n28;
                    n26 /= n28;
                    n27 /= n28;
                }
                array9[0] = this.width * (1.0f + n25) / 2.0f;
                array9[1] = this.height * (1.0f + n26) / 2.0f;
                array9[2] = (n27 + 1.0f) / 2.0f;
            }
        }
        if (this.z_order) {
            return;
        }
        if (this._fill) {
            for (int n29 = 0; n29 < this.triangles_count; ++n29) {
                final float[] array10 = this.vertices[this.triangles[n29][0]];
                final float[] array11 = this.vertices[this.triangles[n29][1]];
                final float[] array12 = this.vertices[this.triangles[n29][2]];
                final int index = this.triangles[n29][3];
                if (this.textureImage != null) {
                    this.triangle.setUV(array10[7], array10[8], array11[7], array11[8], array12[7], array12[8]);
                }
                this.triangle.setIntensities(array10[3], array10[4], array10[5], array10[6], array11[3], array11[4], array11[5], array11[6], array12[3], array12[4], array12[5], array12[6]);
                this.triangle.setVertices(array10[0], array10[1], array10[2], array11[0], array11[1], array11[2], array12[0], array12[1], array12[2]);
                this.triangle.setIndex(index);
                this.triangle.render();
            }
        }
        if (this._stroke || this.smooth) {
            for (int n30 = 0; n30 < this.lines_count; ++n30) {
                final float[] array13 = this.vertices[this.lines[n30][0]];
                final float[] array14 = this.vertices[this.lines[n30][1]];
                final int index2 = this.lines[n30][2];
                this.line.setIntensities(array13[12], array13[13], array13[14], array13[15], array14[12], array14[13], array14[14], array14[15]);
                this.line.setVertices(array13[0], array13[1], array13[2], array14[0], array14[1], array14[2]);
                this.line.setIndex(index2);
                this.line.draw();
            }
        }
        this.shapeKind = 0;
    }
    
    private final void triangulate_polygon() {
        float n = 0.0f;
        int n2 = this.vertex_end - 1;
        for (int i = this.vertex_start; i < this.vertex_end; n2 = i++) {
            n += this.vertices[i][0] * this.vertices[n2][1] - this.vertices[n2][0] * this.vertices[i][1];
        }
        if (0.0f < n) {
            for (int j = this.vertex_start; j < this.vertex_end; ++j) {
                this.vertex_order[j - this.vertex_start] = j;
            }
        }
        else {
            for (int k = this.vertex_start; k < this.vertex_end; ++k) {
                final int n3 = k - this.vertex_start;
                this.vertex_order[n3] = this.vertex_end - 1 - n3;
            }
        }
        int l = this.vertex_end - this.vertex_start;
        int n4 = 2 * l;
        int n5 = 0;
        int n6 = l - 1;
        while (l > 2) {
            boolean b = true;
            if (n4-- <= 0) {
                break;
            }
            int n7 = n6;
            if (l <= n7) {
                n7 = 0;
            }
            n6 = n7 + 1;
            if (l <= n6) {
                n6 = 0;
            }
            int n8 = n6 + 1;
            if (l <= n8) {
                n8 = 0;
            }
            final float n9 = -this.vertices[this.vertex_order[n7]][0];
            final float n10 = this.vertices[this.vertex_order[n7]][1];
            final float n11 = -this.vertices[this.vertex_order[n6]][0];
            final float n12 = this.vertices[this.vertex_order[n6]][1];
            final float n13 = -this.vertices[this.vertex_order[n8]][0];
            final float n14 = this.vertices[this.vertex_order[n8]][1];
            if (1.0E-4f > (n11 - n9) * (n14 - n10) - (n12 - n10) * (n13 - n9)) {
                continue;
            }
            for (int n15 = 0; n15 < l; ++n15) {
                if (n15 != n7 && n15 != n6 && n15 != n8) {
                    final float n16 = -this.vertices[this.vertex_order[n15]][0];
                    final float n17 = this.vertices[this.vertex_order[n15]][1];
                    final float n18 = n13 - n11;
                    final float n19 = n14 - n12;
                    final float n20 = n9 - n13;
                    final float n21 = n10 - n14;
                    final float n22 = n11 - n9;
                    final float n23 = n12 - n10;
                    final float n24 = n16 - n9;
                    final float n25 = n17 - n10;
                    final float n26 = n16 - n11;
                    final float n27 = n17 - n12;
                    final float n28 = n16 - n13;
                    final float n29 = n17 - n14;
                    final float n30 = n18 * n27 - n19 * n26;
                    final float n31 = n22 * n25 - n23 * n24;
                    final float n32 = n20 * n29 - n21 * n28;
                    if (n30 >= 0.0f && n32 >= 0.0f && n31 >= 0.0f) {
                        b = false;
                    }
                }
            }
            if (!b) {
                continue;
            }
            this.addTriangle(this.vertex_order[n7], this.vertex_order[n6], this.vertex_order[n8]);
            ++n5;
            int n33 = n6;
            for (int n34 = n6 + 1; n34 < l; ++n34) {
                this.vertex_order[n33] = this.vertex_order[n34];
                ++n33;
            }
            --l;
            n4 = 2 * l;
        }
    }
    
    private final void sortTriangles() {
    }
    
    public void endShape() {
        if (BGraphics.hints[2]) {
            this.endShape_newgraphics();
            return;
        }
        this.shape = false;
        int vertexCount = this.polygon.vertexCount;
        final float[][] vertices = this.polygon.vertices;
        if (this.camera_mode == 3 && this.dimensions == 0) {
            this.polygon.interpZ = false;
            this.spolygon.interpZ = false;
            for (int i = 0; i < vertexCount; ++i) {
                vertices[i][0] = vertices[i][9];
                vertices[i][1] = vertices[i][10];
            }
        }
        else if (this.camera_mode == 3 && this.dimensions == 2) {
            this.polygon.interpZ = false;
            this.spolygon.interpZ = false;
            for (int j = 0; j < vertexCount; ++j) {
                vertices[j][0] = this.m00 * vertices[j][9] + this.m01 * vertices[j][10] + this.m03;
                vertices[j][1] = this.m10 * vertices[j][9] + this.m11 * vertices[j][10] + this.m13;
            }
        }
        else if (this.camera_mode == 2) {
            for (final float[] array : vertices) {
                array[0] = array[9] - array[11];
                array[1] = -array[9] / 2.0f + array[10] - array[11] / 2.0f;
                array[2] = array[11];
            }
        }
        else {
            this.polygon.interpZ = true;
            this.spolygon.interpZ = true;
            for (final float[] array2 : vertices) {
                final float n = this.m00 * array2[9] + this.m01 * array2[10] + this.m02 * array2[11] + this.m03;
                final float n2 = this.m10 * array2[9] + this.m11 * array2[10] + this.m12 * array2[11] + this.m13;
                final float n3 = this.m20 * array2[9] + this.m21 * array2[10] + this.m22 * array2[11] + this.m23;
                final float n4 = this.m30 * array2[9] + this.m31 * array2[10] + this.m32 * array2[11] + this.m33;
                float n5 = this.p00 * n + this.p01 * n2 + this.p02 * n3 + this.p03 * n4;
                float n6 = this.p10 * n + this.p11 * n2 + this.p12 * n3 + this.p13 * n4;
                float n7 = this.p20 * n + this.p21 * n2 + this.p22 * n3 + this.p23 * n4;
                final float n8 = this.p30 * n + this.p31 * n2 + this.p32 * n3 + this.p33 * n4;
                if (n8 != 0.0f) {
                    n5 /= n8;
                    n6 /= n8;
                    n7 /= n8;
                }
                array2[0] = this.width * (1.0f + n5) / 2.0f;
                array2[1] = this.height * (1.0f + n6) / 2.0f;
                array2[2] = (n7 + 1.0f) / 2.0f;
            }
        }
        boolean b = true;
        final int thin_flat_lineClipCode = this.thin_flat_lineClipCode(vertices[0][0], vertices[0][1]);
        for (int n9 = 1; n9 < vertexCount; ++n9) {
            if (this.thin_flat_lineClipCode(vertices[n9][0], vertices[n9][1]) != thin_flat_lineClipCode) {
                b = false;
                break;
            }
        }
        if (thin_flat_lineClipCode != 0 && b) {
            return;
        }
        if (!this.normalChanged) {
            vertices[0][16] = this.normalX;
            vertices[0][17] = this.normalY;
            vertices[0][18] = this.normalZ;
        }
        int n10 = 0;
        Label_1088: {
            break Label_1088;
            int n11;
            int n12;
            do {
                final float[] array3 = vertices[n10];
                final float n13 = this.m00 * array3[16] + this.m01 * array3[17] + this.m02 * array3[18] + this.m03;
                final float n14 = this.m10 * array3[16] + this.m11 * array3[17] + this.m12 * array3[18] + this.m13;
                final float n15 = this.m20 * array3[16] + this.m21 * array3[17] + this.m22 * array3[18] + this.m23;
                final float n16 = this.m30 * array3[16] + this.m31 * array3[17] + this.m32 * array3[18] + this.m33;
                if (n16 != 0.0f) {
                    array3[16] = n13 / n16;
                    array3[17] = n14 / n16;
                    array3[18] = n15 / n16;
                }
                else {
                    array3[16] = n13;
                    array3[17] = n14;
                    array3[18] = n15;
                }
                final float mag = this.mag(array3[16], array3[17], array3[18]);
                if (mag != 0.0f) {
                    final float[] array4 = array3;
                    final int n17 = 16;
                    array4[n17] /= mag;
                    final float[] array5 = array3;
                    final int n18 = 17;
                    array5[n18] /= mag;
                    final float[] array6 = array3;
                    final int n19 = 18;
                    array6[n19] /= mag;
                }
                ++n10;
                n11 = n10;
                n12 = 1;
                if (this.normalChanged) {
                    n12 = vertexCount;
                }
            } while (n11 < n12);
        }
        if (this.polygon.interpUV) {
            this.fpolygon.texture(this.polygon.timage);
        }
        if (!this.lighting) {
            this.spolygon.interpRGBA = this.strokeChanged;
            this.fpolygon.interpRGBA = this.fillChanged;
        }
        else {
            this.spolygon.interpRGBA = true;
            this.fpolygon.interpRGBA = true;
            final float[] array7 = this.polygon.vertices[0];
            for (int n20 = 0; n20 < vertexCount; ++n20) {
                final float[] array8 = this.polygon.vertices[n20];
                if (this.normalChanged) {
                    if (this._fill) {
                        this.calc_lighting(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 3);
                    }
                    if (this._stroke) {
                        this.calc_lighting(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 12);
                    }
                }
                else {
                    if (this._fill) {
                        this.calc_lighting(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 3);
                    }
                    if (this._stroke) {
                        this.calc_lighting(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 12);
                    }
                }
            }
        }
        if (this.shapeKind == 256) {
            this.shapeKind = (this.isConvex() ? 1 : 0) + 257;
        }
        switch (this.shapeKind) {
            case 16: {
                if (this.dimensions == 0 && this.unchangedZ && this.strokeWeight == 1.0f && !this.lighting) {
                    if (!this.strokeChanged) {
                        for (int n21 = 0; n21 < vertexCount; ++n21) {
                            this.thin_point((int)vertices[n21][0], (int)vertices[n21][1], 0.0f, this.stroke);
                        }
                    }
                    else {
                        for (int n22 = 0; n22 < vertexCount; ++n22) {
                            this.thin_point((int)vertices[n22][0], (int)vertices[n22][1], 0.0f, float_color(vertices[n22][12], vertices[n22][13], vertices[n22][14]));
                        }
                    }
                }
                else {
                    final float[] array9 = vertices[0];
                    for (int n23 = 0; n23 < vertexCount; ++n23) {
                        final float[] array10 = vertices[n23];
                        if (n23 == 0 || this.lighting || this.strokeChanged) {
                            this.calc_lighting(array10[12], array10[13], array10[14], array10[0], array10[1], array10[2], array10[16], array10[17], array10[18], array9, 3);
                        }
                        this.thick_point(array10[0], array10[1], array10[2], array9[3], array9[4], array9[5], array9[15]);
                    }
                }
                break;
            }
            case 32:
            case 33:
            case 34: {
                if (!this._stroke) {
                    return;
                }
                if (this.shapeKind == 34) {
                    final float[] array11 = this.polygon.vertices[0];
                    final float[] nextVertex = this.polygon.nextVertex();
                    ++vertexCount;
                    nextVertex[0] = array11[0];
                    nextVertex[1] = array11[1];
                    nextVertex[2] = array11[2];
                    nextVertex[12] = array11[12];
                    nextVertex[13] = array11[13];
                    nextVertex[14] = array11[14];
                }
                int n24 = 0;
                if (this.shapeKind == 32) {
                    n24 = 1;
                }
                this.draw_lines(vertices, vertexCount - 1, 1, n24 + 1, 0);
                break;
            }
            case 64:
            case 65: {
                final int n25 = (this.shapeKind == 64) ? 3 : 1;
                if (this._fill) {
                    this.fpolygon.vertexCount = 3;
                    for (int n26 = 0; n26 < vertexCount - 2; n26 += n25) {
                        for (int n27 = 0; n27 < 3; ++n27) {
                            this.fpolygon.vertices[n27][3] = vertices[n26 + n27][3];
                            this.fpolygon.vertices[n27][4] = vertices[n26 + n27][4];
                            this.fpolygon.vertices[n27][5] = vertices[n26 + n27][5];
                            this.fpolygon.vertices[n27][6] = vertices[n26 + n27][6];
                            this.fpolygon.vertices[n27][0] = vertices[n26 + n27][0];
                            this.fpolygon.vertices[n27][1] = vertices[n26 + n27][1];
                            this.fpolygon.vertices[n27][2] = vertices[n26 + n27][2];
                            if (this.polygon.interpUV) {
                                this.fpolygon.vertices[n27][7] = vertices[n26 + n27][7];
                                this.fpolygon.vertices[n27][8] = vertices[n26 + n27][8];
                            }
                        }
                        this.fpolygon.render();
                    }
                }
                if (this._stroke) {
                    if (this.shapeKind == 65) {
                        this.draw_lines(vertices, vertexCount - 1, 1, 1, 0);
                    }
                    else {
                        this.draw_lines(vertices, vertexCount - 1, 1, 1, 3);
                    }
                    this.draw_lines(vertices, vertexCount - 2, 2, n25, 0);
                }
                break;
            }
            case 128:
            case 129: {
                final int n28 = (this.shapeKind == 128) ? 4 : 2;
                if (this._fill) {
                    this.fpolygon.vertexCount = 4;
                    for (int n29 = 0; n29 < vertexCount - 3; n29 += n28) {
                        for (int n30 = 0; n30 < 4; ++n30) {
                            this.fpolygon.vertices[n30][3] = vertices[n29 + n30][3];
                            this.fpolygon.vertices[n30][4] = vertices[n29 + n30][4];
                            this.fpolygon.vertices[n30][5] = vertices[n29 + n30][5];
                            this.fpolygon.vertices[n30][6] = vertices[n29 + n30][6];
                            this.fpolygon.vertices[n30][0] = vertices[n29 + n30][0];
                            this.fpolygon.vertices[n30][1] = vertices[n29 + n30][1];
                            this.fpolygon.vertices[n30][2] = vertices[n29 + n30][2];
                            if (this.polygon.interpUV) {
                                this.fpolygon.vertices[n30][7] = vertices[n29 + n30][7];
                                this.fpolygon.vertices[n30][8] = vertices[n29 + n30][8];
                            }
                        }
                        this.fpolygon.render();
                    }
                }
                if (this._stroke) {
                    if (this.shapeKind == 129) {
                        this.draw_lines(vertices, vertexCount - 1, 1, 1, 0);
                    }
                    else {
                        this.draw_lines(vertices, vertexCount, 1, 1, 4);
                    }
                    this.draw_lines(vertices, vertexCount - 2, 3, n28, 0);
                }
                break;
            }
            case 256:
            case 257: {
                if (this._fill) {
                    final boolean smooth = this.smooth;
                    if (this._stroke && !BGraphics.hints[4]) {
                        this.smooth = false;
                    }
                    this.concaveRender();
                    if (this._stroke && !BGraphics.hints[4]) {
                        this.smooth = smooth;
                    }
                }
                if (this._stroke) {
                    this.draw_lines(vertices, vertexCount - 1, 1, 1, 0);
                    this.svertices[0] = vertices[vertexCount - 1];
                    this.svertices[1] = vertices[0];
                    this.draw_lines(this.svertices, 1, 1, 1, 0);
                }
                break;
            }
            case 258: {
                if (this._fill) {
                    this.polygon.render();
                    if (this._stroke) {
                        this.polygon.unexpand();
                    }
                }
                if (this._stroke) {
                    this.draw_lines(vertices, vertexCount - 1, 1, 1, 0);
                    this.svertices[0] = vertices[vertexCount - 1];
                    this.svertices[1] = vertices[0];
                    this.draw_lines(this.svertices, 1, 1, 1, 0);
                }
                break;
            }
        }
    }
    
    private final boolean isConvex() {
        final float[][] vertices = this.polygon.vertices;
        final int vertexCount = this.polygon.vertexCount;
        int n = 0;
        if (vertexCount < 3) {
            return true;
        }
        for (int i = 0; i < vertexCount; ++i) {
            final int n2 = (i + 1) % vertexCount;
            final int n3 = (i + 2) % vertexCount;
            final float n4 = (vertices[n2][0] - vertices[i][0]) * (vertices[n3][1] - vertices[n2][1]) - (vertices[n2][1] - vertices[i][1]) * (vertices[n3][0] - vertices[n2][0]);
            if (n4 < 0.0f) {
                n |= 0x1;
            }
            else if (n4 > 0.0f) {
                n |= 0x2;
            }
            if (n == 3) {
                return false;
            }
        }
        return n == 0 || true;
    }
    
    private final void concaveRender() {
        final float[][] vertices = this.polygon.vertices;
        if (this.tpolygon == null) {
            this.tpolygon = new BPolygon(this);
            this.tpolygon_vertex_order = new int[this.TPOLYGON_MAX_VERTICES];
        }
        this.tpolygon.reset(3);
        if (this.texture) {
            this.tpolygon.texture(this.polygon.timage);
        }
        this.tpolygon.interpX = this.polygon.interpX;
        this.tpolygon.interpZ = this.polygon.interpZ;
        this.tpolygon.interpUV = this.polygon.interpUV;
        this.tpolygon.interpRGBA = this.polygon.interpRGBA;
        final int vertexCount = this.polygon.vertexCount;
        final float[] array = { vertices[0][0], vertices[0][1] };
        int n = 0;
        for (int i = 0; i < vertexCount; ++i) {
            if (vertices[i][1] < array[1] || (vertices[i][1] == array[1] && vertices[i][0] > array[0])) {
                n = i;
                array[0] = vertices[n][0];
                array[1] = vertices[n][1];
            }
        }
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        final float[] array4 = new float[2];
        final int n2 = (n + (vertexCount - 1)) % vertexCount;
        for (int j = 0; j < 2; ++j) {
            array2[j] = vertices[n2][j];
            array3[j] = vertices[n][j];
            array4[j] = vertices[(n + 1) % vertexCount][j];
        }
        if (array2[0] * array3[1] - array2[1] * array3[0] + array2[1] * array4[0] - array2[0] * array4[1] + array3[0] * array4[1] - array4[0] * array3[1] <= 0.0f) {
            for (int k = 0; k < vertexCount; ++k) {
                this.tpolygon_vertex_order[k] = k;
            }
        }
        else {
            for (int l = 0; l < vertexCount; ++l) {
                this.tpolygon_vertex_order[l] = vertexCount - 1 - l;
            }
        }
        int n3 = vertexCount;
        int n4 = 2 * n3;
        int n5 = 0;
        int n6 = n3 - 1;
        while (n3 > 2) {
            boolean b = true;
            if (n4-- <= 0) {
                break;
            }
            int n7 = n6;
            if (n3 <= n7) {
                n7 = 0;
            }
            n6 = n7 + 1;
            if (n3 <= n6) {
                n6 = 0;
            }
            int n8 = n6 + 1;
            if (n3 <= n8) {
                n8 = 0;
            }
            final float n9 = -vertices[this.tpolygon_vertex_order[n7]][0];
            final float n10 = vertices[this.tpolygon_vertex_order[n7]][1];
            final float n11 = -vertices[this.tpolygon_vertex_order[n6]][0];
            final float n12 = vertices[this.tpolygon_vertex_order[n6]][1];
            final float n13 = -vertices[this.tpolygon_vertex_order[n8]][0];
            final float n14 = vertices[this.tpolygon_vertex_order[n8]][1];
            if (1.0E-4f > (n11 - n9) * (n14 - n10) - (n12 - n10) * (n13 - n9)) {
                continue;
            }
            for (int n15 = 0; n15 < n3; ++n15) {
                if (n15 != n7 && n15 != n6 && n15 != n8) {
                    final float n16 = -vertices[this.tpolygon_vertex_order[n15]][0];
                    final float n17 = vertices[this.tpolygon_vertex_order[n15]][1];
                    final float n18 = n13 - n11;
                    final float n19 = n14 - n12;
                    final float n20 = n9 - n13;
                    final float n21 = n10 - n14;
                    final float n22 = n11 - n9;
                    final float n23 = n12 - n10;
                    final float n24 = n16 - n9;
                    final float n25 = n17 - n10;
                    final float n26 = n16 - n11;
                    final float n27 = n17 - n12;
                    final float n28 = n16 - n13;
                    final float n29 = n17 - n14;
                    final float n30 = n18 * n27 - n19 * n26;
                    final float n31 = n22 * n25 - n23 * n24;
                    final float n32 = n20 * n29 - n21 * n28;
                    if (n30 >= 0.0f && n32 >= 0.0f && n31 >= 0.0f) {
                        b = false;
                    }
                }
            }
            if (!b) {
                continue;
            }
            this.renderTriangle(new int[] { this.tpolygon_vertex_order[n7], this.tpolygon_vertex_order[n6], this.tpolygon_vertex_order[n8] });
            ++n5;
            int n33 = n6;
            for (int n34 = n6 + 1; n34 < n3; ++n34) {
                this.tpolygon_vertex_order[n33] = this.tpolygon_vertex_order[n34];
                ++n33;
            }
            --n3;
            n4 = 2 * n3;
        }
    }
    
    private final void renderTriangle(final int[] array) {
        for (int i = 0; i < 3; ++i) {
            final float[] array2 = this.polygon.vertices[array[i]];
            final float[] array3 = this.tpolygon.vertices[i];
            for (int j = 0; j < 24; ++j) {
                array3[j] = array2[j];
            }
        }
        this.tpolygon.render();
    }
    
    private final void thin_pointAt(final int n, final int n2, final float n3, final int n4) {
        final int n5 = n2 * this.width + n;
        this.pixels[n5] = n4;
        this.zbuffer[n5] = n3;
    }
    
    private final void thin_pointAtIndex(final int n, final float n2, final int n3) {
        this.pixels[n] = n3;
        this.zbuffer[n] = n2;
    }
    
    private final void thick_point(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        this.spolygon.reset(4);
        this.spolygon.interpRGBA = false;
        final float n8 = this.strokeWeight / 2.0f;
        final float[] array = this.spolygon.vertices[0];
        array[0] = n - n8;
        array[1] = n2 - n8;
        array[2] = n3;
        array[3] = n4;
        array[4] = n5;
        array[5] = n6;
        array[6] = n7;
        final float[] array2 = this.spolygon.vertices[1];
        array2[0] = n + n8;
        array2[1] = n2 - n8;
        array2[2] = n3;
        final float[] array3 = this.spolygon.vertices[2];
        array3[0] = n + n8;
        array3[1] = n2 + n8;
        array3[2] = n3;
        final float[] array4 = this.spolygon.vertices[3];
        array4[0] = n - n8;
        array4[1] = n2 + n8;
        array4[2] = n3;
        this.spolygon.render();
    }
    
    private final void thin_flat_line(final int n, final int n2, final int n3, final int n4) {
        final int thin_flat_lineClipCode = this.thin_flat_lineClipCode(n, n2);
        final int thin_flat_lineClipCode2 = this.thin_flat_lineClipCode(n3, n4);
        if ((thin_flat_lineClipCode & thin_flat_lineClipCode2) != 0x0) {
            return;
        }
        final int n5 = thin_flat_lineClipCode | thin_flat_lineClipCode2;
        int j;
        int k;
        int n6;
        int n7;
        if (n5 != 0) {
            float max = 0.0f;
            float min = 1.0f;
            for (int i = 0; i < 4; ++i) {
                if ((n5 >> i) % 2 == 1) {
                    final float thin_flat_lineSlope = this.thin_flat_lineSlope(n, n2, n3, n4, i + 1);
                    if ((thin_flat_lineClipCode >> i) % 2 == 1) {
                        max = Math.max(thin_flat_lineSlope, max);
                    }
                    else {
                        min = Math.min(thin_flat_lineSlope, min);
                    }
                }
            }
            if (max > min) {
                return;
            }
            j = (int)(n + max * (n3 - n));
            k = (int)(n2 + max * (n4 - n2));
            n6 = (int)(n + min * (n3 - n));
            n7 = (int)(n2 + min * (n4 - n2));
        }
        else {
            j = n;
            n6 = n3;
            k = n2;
            n7 = n4;
        }
        boolean b = false;
        int n8 = n7 - k;
        int n9 = n6 - j;
        if (Math.abs(n8) > Math.abs(n9)) {
            final int n10 = n8;
            n8 = n9;
            n9 = n10;
            b = true;
        }
        int n11;
        if (n9 == 0) {
            n11 = 0;
        }
        else {
            n11 = (n8 << 16) / n9;
        }
        if (j == n6) {
            if (k > n7) {
                final int n12 = k;
                k = n7;
                n7 = n12;
            }
            int n13 = k * this.width + j;
            for (int l = k; l <= n7; ++l) {
                this.thin_pointAtIndex(n13, 0.0f, this.stroke);
                n13 += this.width;
            }
            return;
        }
        if (k == n7) {
            if (j > n6) {
                final int n14 = j;
                j = n6;
                n6 = n14;
            }
            int n15 = k * this.width + j;
            for (int n16 = j; n16 <= n6; ++n16) {
                this.thin_pointAtIndex(n15++, 0.0f, this.stroke);
            }
            return;
        }
        if (b) {
            if (n9 > 0) {
                final int n17 = n9 + k;
                int n18 = 32768 + (j << 16);
                while (k <= n17) {
                    this.thin_pointAt(n18 >> 16, k, 0.0f, this.stroke);
                    n18 += n11;
                    ++k;
                }
                return;
            }
            final int n19 = n9 + k;
            int n20 = 32768 + (j << 16);
            while (k >= n19) {
                this.thin_pointAt(n20 >> 16, k, 0.0f, this.stroke);
                n20 -= n11;
                --k;
            }
        }
        else {
            if (n9 > 0) {
                final int n21 = n9 + j;
                int n22 = 32768 + (k << 16);
                while (j <= n21) {
                    this.thin_pointAt(j, n22 >> 16, 0.0f, this.stroke);
                    n22 += n11;
                    ++j;
                }
                return;
            }
            final int n23 = n9 + j;
            int n24 = 32768 + (k << 16);
            while (j >= n23) {
                this.thin_pointAt(j, n24 >> 16, 0.0f, this.stroke);
                n24 -= n11;
                --j;
            }
        }
    }
    
    private final int thin_flat_lineClipCode(final float n, final float n2) {
        final int n3 = ((n2 < 0.0f) ? 8 : 0) | ((n2 > this.height1) ? 4 : 0) | ((n < 0.0f) ? 2 : 0);
        boolean b = false;
        if (n > this.width1) {
            b = true;
        }
        return n3 | (b ? 1 : 0);
    }
    
    private final float thin_flat_lineSlope(final float n, final float n2, final float n3, final float n4, final int n5) {
        switch (n5) {
            case 4: {
                return -n2 / (n4 - n2);
            }
            case 3: {
                return (this.height1 - n2) / (n4 - n2);
            }
            case 2: {
                return -n / (n3 - n);
            }
            case 1: {
                return (this.width1 - n) / (n3 - n);
            }
            default: {
                return -1.0f;
            }
        }
    }
    
    private final boolean flat_line_retribution(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        final float n8 = this.m00 * this.strokeWeight + this.m01 * this.strokeWeight;
        if (this.strokeWeight < 2.0f && !BGraphics.hints[0]) {
            final int stroke = this.stroke;
            this.stroke = float_color(n5, n6, n7);
            this.thin_flat_line((int)n, (int)n2, (int)n3, (int)n4);
            this.stroke = stroke;
            return true;
        }
        return false;
    }
    
    private final void thick_flat_line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        final BPolygon spolygon = this.spolygon;
        boolean interpRGBA = false;
        if (n3 != n9 || n4 != n10 || n5 != n11 || n6 != n12) {
            interpRGBA = true;
        }
        spolygon.interpRGBA = interpRGBA;
        this.spolygon.interpZ = false;
        if (!this.spolygon.interpRGBA && this.flat_line_retribution(n, n2, n7, n8, n3, n4, n5)) {
            return;
        }
        final float n13 = n7 - n + 1.0E-4f;
        final float n14 = n8 - n2 + 1.0E-4f;
        final float n15 = this.strokeWeight / this.sqrt(n13 * n13 + n14 * n14);
        final float n16 = n15 * n14;
        final float n17 = n15 * n13;
        final float n18 = n15 * n14;
        final float n19 = n15 * n13;
        this.spolygon.reset(4);
        final float[] array = this.spolygon.vertices[0];
        array[0] = n + n16;
        array[1] = n2 - n17;
        array[3] = n3;
        array[4] = n4;
        array[5] = n5;
        array[6] = n6;
        final float[] array2 = this.spolygon.vertices[1];
        array2[0] = n - n16;
        array2[1] = n2 + n17;
        array2[3] = n3;
        array2[4] = n4;
        array2[5] = n5;
        array2[6] = n6;
        final float[] array3 = this.spolygon.vertices[2];
        array3[0] = n7 - n18;
        array3[1] = n8 + n19;
        array3[3] = n9;
        array3[4] = n10;
        array3[5] = n11;
        array3[6] = n12;
        final float[] array4 = this.spolygon.vertices[3];
        array4[0] = n7 + n18;
        array4[1] = n8 - n19;
        array4[3] = n9;
        array4[4] = n10;
        array4[5] = n11;
        array4[6] = n12;
        this.spolygon.render();
    }
    
    private final void spatial_line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10) {
        this.spatial_line(n, n2, 0.0f, n3, n4, n5, n6, n7, 0.0f, n8, n9, n10);
    }
    
    private final void spatial_line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        final BPolygon spolygon = this.spolygon;
        boolean interpRGBA = false;
        if (n4 != n10 || n5 != n11 || n6 != n12) {
            interpRGBA = true;
        }
        spolygon.interpRGBA = interpRGBA;
        if (!this.spolygon.interpRGBA && this.flat_line_retribution(n, n2, n7, n8, n4, n5, n6)) {
            return;
        }
        this.spolygon.interpZ = true;
        final float n13 = n7 - n + 1.0E-4f;
        final float n14 = n8 - n2 + 1.0E-4f;
        final float n15 = this.strokeWeight / this.sqrt(n13 * n13 + n14 * n14);
        final float n16 = n15 * n14;
        final float n17 = n15 * n13;
        final float n18 = n15 * n14;
        final float n19 = n15 * n13;
        this.spolygon.reset(4);
        final float[] array = this.spolygon.vertices[0];
        array[0] = n + n16;
        array[1] = n2 - n17;
        array[2] = n3;
        array[3] = n4;
        array[4] = n5;
        array[5] = n6;
        final float[] array2 = this.spolygon.vertices[1];
        array2[0] = n - n16;
        array2[1] = n2 + n17;
        array2[2] = n3;
        array2[3] = n4;
        array2[4] = n5;
        array2[5] = n6;
        final float[] array3 = this.spolygon.vertices[2];
        array3[0] = n7 - n18;
        array3[1] = n8 + n19;
        array3[2] = n9;
        array3[3] = n10;
        array3[4] = n11;
        array3[5] = n12;
        final float[] array4 = this.spolygon.vertices[3];
        array4[0] = n7 + n18;
        array4[1] = n8 - n19;
        array4[2] = n9;
        array4[3] = n10;
        array4[4] = n11;
        array4[5] = n12;
        this.spolygon.render();
    }
    
    private final void draw_lines(final float[][] array, final int n, final int n2, final int n3, final int n4) {
        if (this.strokeWeight < 2.0f) {
            for (int i = 0; i < n; i += n3) {
                if (n4 == 0 || (i + n2) % n4 != 0) {
                    final float[] array2 = array[i];
                    final float[] array3 = array[i + n2];
                    this.line.reset();
                    this.line.setIntensities(array2[12], array2[13], array2[14], array2[15], array3[12], array3[13], array3[14], array3[15]);
                    this.line.setVertices(array2[0], array2[1], array2[2], array3[0], array3[1], array3[2]);
                    this.line.draw();
                }
            }
        }
        else if (this.dimensions != 3 && this.unchangedZ) {
            if (this.strokeWeight < 2.0f && !this.lighting && !this.strokeChanged) {
                for (int j = 0; j < n; j += n3) {
                    if (n4 == 0 || (j + n2) % n4 != 0) {
                        this.thin_flat_line((int)array[j][0], (int)array[j][1], (int)array[j + n2][0], (int)array[j + n2][1]);
                    }
                }
            }
            else {
                for (int k = 0; k < n; k += n3) {
                    if (n4 == 0 || (k + n2) % n4 != 0) {
                        final float[] array4 = array[k];
                        final float[] array5 = array[k + n2];
                        this.thick_flat_line(array4[0], array4[1], array4[12], array4[13], array4[14], array4[15], array5[0], array5[1], array5[12], array5[13], array5[14], array5[15]);
                    }
                }
            }
        }
        else {
            for (int l = 0; l < n; l += n3) {
                if (n4 == 0 || (l + n2) % n4 != 0) {
                    final float[] array6 = array[l];
                    final float[] array7 = array[l + n2];
                    this.spatial_line(array6[0], array6[1], array6[2], array6[12], array6[13], array6[14], array7[0], array7[1], array7[2], array7[12], array7[13], array7[14]);
                }
            }
        }
    }
    
    private final void thin_point(final int n, final int n2, final float n3, final int n4) {
        if (n < 0 || n > this.width1 || n2 < 0 || n2 > this.height1) {
            return;
        }
        final int n5 = n2 * this.width + n;
        this.pixels[n5] = n4;
        this.zbuffer[n5] = n3;
    }
    
    private final void flat_rect(int n, int n2, int n3, int n4) {
        if (n4 < n2) {
            final int n5 = n2;
            n2 = n4;
            n4 = n5;
        }
        if (n3 < n) {
            final int n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n > this.width1 || n3 < 0 || n2 > this.height1 || n4 < 0) {
            return;
        }
        if (this._fill) {
            int n7 = n;
            int n8 = n2;
            int width = n3;
            int height = n4;
            if (n7 < 0) {
                n7 = 0;
            }
            if (width > this.width) {
                width = this.width;
            }
            if (n8 < 0) {
                n8 = 0;
            }
            if (height > this.height) {
                height = this.height;
            }
            final int n9 = width - n7;
            final int n10 = height - n8;
            final int[] array = new int[n9];
            for (int i = 0; i < n9; ++i) {
                array[i] = this.fill;
            }
            int n11 = n8 * this.width + n7;
            for (int j = 0; j < n10; ++j) {
                System.arraycopy(array, 0, this.pixels, n11, n9);
                n11 += this.width;
            }
        }
        if (!BGraphics.hints[2] && this._stroke) {
            if (this.strokeWeight == 1.0f) {
                this.thin_flat_line(n, n2, n3, n2);
                this.thin_flat_line(n3, n2, n3, n4);
                this.thin_flat_line(n3, n4, n, n4);
                this.thin_flat_line(n, n4, n, n2);
            }
            else {
                this.thick_flat_line(n, n2, this.fillR, this.fillG, this.fillB, this.fillA, n3, n2, this.fillR, this.fillG, this.fillB, this.fillA);
                this.thick_flat_line(n3, n2, this.fillR, this.fillG, this.fillB, this.fillA, n3, n4, this.fillR, this.fillG, this.fillB, this.fillA);
                this.thick_flat_line(n3, n4, this.fillR, this.fillG, this.fillB, this.fillA, n, n4, this.fillR, this.fillG, this.fillB, this.fillA);
                this.thick_flat_line(n, n4, this.fillR, this.fillG, this.fillB, this.fillA, n, n2, this.fillR, this.fillG, this.fillB, this.fillA);
            }
        }
    }
    
    private final void flat_circle(int n, int n2, final int n3) {
        if (this.dimensions == 2) {
            n = (int)this.screenX(n, n2, 0.0f);
            n2 = (int)this.screenY(n, n2, 0.0f);
        }
        if (this._fill) {
            this.flat_circle_fill(n, n2, n3);
        }
        if (this._stroke) {
            this.flat_circle_stroke(n, n2, n3);
        }
    }
    
    private final void flat_circle_stroke(final int n, final int n2, final int n3) {
        int i = 0;
        int n4 = n3;
        int n5 = 1;
        int n6 = 2 * n3 - 1;
        int n7 = 0;
        while (i < n4) {
            this.thin_point(n + i, n2 + n4, 0.0f, this.stroke);
            this.thin_point(n + n4, n2 - i, 0.0f, this.stroke);
            this.thin_point(n - i, n2 - n4, 0.0f, this.stroke);
            this.thin_point(n - n4, n2 + i, 0.0f, this.stroke);
            ++i;
            n7 += n5;
            n5 += 2;
            if (n6 < 2 * n7) {
                --n4;
                n7 -= n6;
                n6 -= 2;
            }
            if (i > n4) {
                break;
            }
            this.thin_point(n + n4, n2 + i, 0.0f, this.stroke);
            this.thin_point(n + i, n2 - n4, 0.0f, this.stroke);
            this.thin_point(n - n4, n2 - i, 0.0f, this.stroke);
            this.thin_point(n - i, n2 + n4, 0.0f, this.stroke);
        }
    }
    
    private final void flat_circle_fill(final int n, final int n2, final int n3) {
        int i = 0;
        int n4 = n3;
        int n5 = 1;
        int n6 = 2 * n3 - 1;
        int n7 = 0;
        while (i < n4) {
            for (int j = n; j < n + i; ++j) {
                this.thin_point(j, n2 + n4, 0.0f, this.fill);
            }
            for (int k = n; k < n + n4; ++k) {
                this.thin_point(k, n2 - i, 0.0f, this.fill);
            }
            for (int l = n - i; l < n; ++l) {
                this.thin_point(l, n2 - n4, 0.0f, this.fill);
            }
            for (int n8 = n - n4; n8 < n; ++n8) {
                this.thin_point(n8, n2 + i, 0.0f, this.fill);
            }
            ++i;
            n7 += n5;
            n5 += 2;
            if (n6 < 2 * n7) {
                --n4;
                n7 -= n6;
                n6 -= 2;
            }
            if (i > n4) {
                break;
            }
            for (int n9 = n; n9 < n + n4; ++n9) {
                this.thin_point(n9, n2 + i, 0.0f, this.fill);
            }
            for (int n10 = n; n10 < n + i; ++n10) {
                this.thin_point(n10, n2 - n4, 0.0f, this.fill);
            }
            for (int n11 = n - n4; n11 < n; ++n11) {
                this.thin_point(n11, n2 - i, 0.0f, this.fill);
            }
            for (int n12 = n - i; n12 < n; ++n12) {
                this.thin_point(n12, n2 + n4, 0.0f, this.fill);
            }
        }
    }
    
    private final void flat_ellipse_symmetry(final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (b) {
            for (int i = n - n3 + 1; i < n + n3; ++i) {
                this.thin_point(i, n2 - n4, 0.0f, this.fill);
                this.thin_point(i, n2 + n4, 0.0f, this.fill);
            }
        }
        else {
            this.thin_point(n - n3, n2 + n4, 0.0f, this.stroke);
            this.thin_point(n + n3, n2 + n4, 0.0f, this.stroke);
            this.thin_point(n - n3, n2 - n4, 0.0f, this.stroke);
            this.thin_point(n + n3, n2 - n4, 0.0f, this.stroke);
        }
    }
    
    private final void flat_ellipse_internal(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int n5 = n3 * n3;
        final int n6 = n4 * n4;
        int n7 = 0;
        int i = n4;
        int n8 = n5 * (1 - 2 * n4) + 2 * n6;
        int n9 = n6 - 2 * n5 * (2 * n4 - 1);
        this.flat_ellipse_symmetry(n, n2, n7, i, b);
        do {
            if (n8 < 0) {
                n8 += 2 * n6 * (2 * n7 + 3);
                n9 += 4 * n6 * (n7 + 1);
                ++n7;
            }
            else if (n9 < 0) {
                n8 += 2 * n6 * (2 * n7 + 3) - 4 * n5 * (i - 1);
                n9 += 4 * n6 * (n7 + 1) - 2 * n5 * (2 * i - 3);
                ++n7;
                --i;
            }
            else {
                n8 -= 4 * n5 * (i - 1);
                n9 -= 2 * n5 * (2 * i - 3);
                --i;
            }
            this.flat_ellipse_symmetry(n, n2, n7, i, b);
        } while (i > 0);
    }
    
    private final void flat_ellipse(int n, int n2, final int n3, final int n4) {
        if (this.dimensions == 2) {
            n = (int)this.screenX(n, n2, 0.0f);
            n2 = (int)this.screenY(n, n2, 0.0f);
        }
        if (this._fill) {
            this.flat_ellipse_internal(n, n2, n3, n4, true);
        }
        if (this._stroke) {
            this.flat_ellipse_internal(n, n2, n3, n4, false);
        }
    }
    
    public void flat_image(final BImage bImage, int n, int n2) {
        int n3 = 0;
        int n4 = 0;
        final int width = bImage.width;
        final int height = bImage.height;
        if (this.image_mode == 3) {
            n -= bImage.width / 2;
            n2 -= bImage.height / 2;
        }
        int width2 = n + bImage.width;
        int height2 = n2 + bImage.height;
        if (n > this.width1 || width2 < 0 || n2 > this.height1 || height2 < 0) {
            return;
        }
        if (n < 0) {
            n3 -= n;
            n = 0;
        }
        if (n2 < 0) {
            n4 -= n2;
            n2 = 0;
        }
        if (width2 > this.width) {
            final int n5 = width - (width2 - this.width);
            width2 = this.width;
        }
        if (height2 > this.height) {
            final int n6 = height - (height2 - this.height);
            height2 = this.height;
        }
        int n7 = n4 * bImage.width + n3;
        int n8 = n2 * this.width;
        if (bImage.format == 2) {
            for (int i = n2; i < height2; ++i) {
                int n9 = 0;
                for (int j = n; j < width2; ++j) {
                    this.pixels[n8 + j] = _blend(this.pixels[n8 + j], bImage.pixels[n7 + n9], bImage.pixels[n7 + n9++] >>> 24);
                }
                n7 += bImage.width;
                n8 += this.width;
            }
        }
        else if (bImage.format == 4) {
            for (int k = n2; k < height2; ++k) {
                int n10 = 0;
                for (int l = n; l < width2; ++l) {
                    this.pixels[n8 + l] = _blend(this.pixels[n8 + l], this.fill, bImage.pixels[n7 + n10++]);
                }
                n7 += bImage.width;
                n8 += this.width;
            }
        }
        else if (bImage.format == 1) {
            int n11 = n8 + n;
            final int n12 = width2 - n;
            for (int n13 = n2; n13 < height2; ++n13) {
                System.arraycopy(bImage.pixels, n7, this.pixels, n11, n12);
                n7 += bImage.width;
                n11 += this.width;
            }
        }
    }
    
    public void point(final float n, final float n2) {
        this.beginShape(16);
        this.vertex(n, n2);
        this.endShape();
    }
    
    public void point(final float n, final float n2, final float n3) {
        this.beginShape(16);
        this.vertex(n, n2, n3);
        this.endShape();
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
        this.beginShape(32);
        this.vertex(n, n2);
        this.vertex(n3, n4);
        this.endShape();
    }
    
    public void line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.beginShape(32);
        this.vertex(n, n2, n3);
        this.vertex(n4, n5, n6);
        this.endShape();
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.beginShape(64);
        this.vertex(n, n2);
        this.vertex(n3, n4);
        this.vertex(n5, n6);
        this.endShape();
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.beginShape(128);
        this.vertex(n, n2);
        this.vertex(n3, n4);
        this.vertex(n5, n6);
        this.vertex(n7, n8);
        this.endShape();
    }
    
    public void rectMode(final int rect_mode) {
        this.rect_mode = rect_mode;
    }
    
    public void rect(float n, float n2, float n3, float n4) {
        switch (this.rect_mode) {
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
        if (this.dimensions == 0 && !this.lighting && !this.fill_alpha) {
            this.flat_rect((int)n, (int)n2, (int)n3, (int)n4);
        }
        else {
            this.beginShape(128);
            this.vertex(n, n2);
            this.vertex(n3, n2);
            this.vertex(n3, n4);
            this.vertex(n, n4);
            this.endShape();
        }
    }
    
    public void ellipseMode(final int ellipse_mode) {
        this.ellipse_mode = ellipse_mode;
    }
    
    public void ellipse(float n, float n2, float n3, float n4) {
        switch (this.ellipse_mode) {
            case 3: {
                n3 /= 2.0f;
                n4 /= 2.0f;
                break;
            }
            case 0: {
                n3 /= 2.0f;
                n4 /= 2.0f;
                n += n3;
                n2 += n4;
                break;
            }
            case 1: {
                n3 -= n;
                n4 -= n2;
                break;
            }
        }
        final int n5 = (int)(4 + Math.sqrt(n3 + n4) * 3);
        boolean b = false;
        if (!this.lighting && !this.smooth && this.strokeWeight == 1.0f && !this.fill_alpha && !this.stroke_alpha) {
            b = true;
        }
        final boolean b2 = b;
        boolean b3 = false;
        if (this.dimensions == 0 || (this.dimensions == 2 && this.m00 == this.m11 && this.m00 == 1.0f)) {
            b3 = true;
        }
        final boolean b4 = b3;
        if (b2 && b4) {
            if (n3 == n4) {
                this.flat_circle((int)n, (int)n2, (int)n3);
            }
            else {
                this.flat_ellipse((int)n, (int)n2, (int)n3, (int)n4);
            }
        }
        else {
            final float n6 = 720.0f / n5;
            float n7 = 0.0f;
            this.beginShape(256);
            for (int i = 0; i < n5; ++i) {
                this.vertex(n + BGraphics.cosLUT[(int)n7] * n3, n2 + BGraphics.sinLUT[(int)n7] * n4);
                n7 += n6;
            }
            if (!BGraphics.hints[2]) {
                this.vertex(n + BGraphics.cosLUT[0] * n3, n2 + BGraphics.sinLUT[0] * n4);
            }
            this.endShape();
        }
    }
    
    public void box(final float n) {
        this.box(n, n, n);
    }
    
    public void box(final float n, final float n2, final float n3) {
        final float n4 = -n / 2.0f;
        final float n5 = n / 2.0f;
        final float n6 = -n2 / 2.0f;
        final float n7 = n2 / 2.0f;
        final float n8 = -n3 / 2.0f;
        final float n9 = n3 / 2.0f;
        if (BGraphics.hints[2]) {
            this.triangle.setCulling(true);
        }
        this.beginShape(128);
        this.vertex(n4, n6, n8);
        this.vertex(n5, n6, n8);
        this.vertex(n5, n7, n8);
        this.vertex(n4, n7, n8);
        this.vertex(n5, n6, n8);
        this.vertex(n5, n6, n9);
        this.vertex(n5, n7, n9);
        this.vertex(n5, n7, n8);
        this.vertex(n5, n6, n9);
        this.vertex(n4, n6, n9);
        this.vertex(n4, n7, n9);
        this.vertex(n5, n7, n9);
        this.vertex(n4, n6, n9);
        this.vertex(n4, n6, n8);
        this.vertex(n4, n7, n8);
        this.vertex(n4, n7, n9);
        this.vertex(n4, n6, n9);
        this.vertex(n5, n6, n9);
        this.vertex(n5, n6, n8);
        this.vertex(n4, n6, n8);
        this.vertex(n4, n7, n8);
        this.vertex(n5, n7, n8);
        this.vertex(n5, n7, n9);
        this.vertex(n4, n7, n9);
        this.endShape();
        if (BGraphics.hints[2]) {
            this.triangle.setCulling(false);
        }
    }
    
    public void sphereDetail(int sphere_detail) {
        if (sphere_detail < 3) {
            sphere_detail = 3;
        }
        if (sphere_detail != this.sphere_detail) {
            final float n = 720.0f / sphere_detail;
            final float[] array = new float[sphere_detail];
            final float[] array2 = new float[sphere_detail];
            for (int i = 0; i < sphere_detail; ++i) {
                array[i] = BGraphics.cosLUT[(int)(i * n) % 720];
                array2[i] = BGraphics.sinLUT[(int)(i * n) % 720];
            }
            final int n2 = sphere_detail * (sphere_detail - 1) + 2;
            int n3 = 0;
            this.sphereX = new float[n2];
            this.sphereY = new float[n2];
            this.sphereZ = new float[n2];
            float n5;
            final float n4 = n5 = 360.0f / sphere_detail;
            for (int j = 1; j < sphere_detail; ++j) {
                final float n6 = BGraphics.sinLUT[(int)n5 % 720];
                final float n7 = -BGraphics.cosLUT[(int)n5 % 720];
                for (int k = 0; k < sphere_detail; ++k) {
                    this.sphereX[n3] = array[k] * n6;
                    this.sphereY[n3] = n7;
                    this.sphereZ[n3++] = array2[k] * n6;
                }
                n5 += n4;
            }
            this.sphere_detail = sphere_detail;
        }
    }
    
    public void sphere(final float n) {
        this.sphere(0.0f, 0.0f, 0.0f, n);
    }
    
    public void sphere(final float n, final float n2, final float n3, final float n4) {
        if (this.sphere_detail == 0) {
            this.sphereDetail(30);
        }
        this.push();
        if (n != 0.0f && n2 != 0.0f && n3 != 0.0f) {
            this.translate(n, n2, n3);
        }
        this.scale(n4);
        if (BGraphics.hints[2]) {
            this.triangle.setCulling(true);
        }
        this.beginShape(65);
        for (int i = 0; i < this.sphere_detail; ++i) {
            this.vertex(0.0f, -1.0f, 0.0f);
            this.vertex(this.sphereX[i], this.sphereY[i], this.sphereZ[i]);
        }
        this.vertex(0.0f, -1.0f, 0.0f);
        this.vertex(this.sphereX[0], this.sphereY[0], this.sphereZ[0]);
        this.endShape();
        int n5 = 0;
        for (int j = 2; j < this.sphere_detail; ++j) {
            int n7;
            final int n6 = n7 = n5;
            int n8;
            n5 = (n8 = n5 + this.sphere_detail);
            this.beginShape(65);
            for (int k = 0; k < this.sphere_detail; ++k) {
                this.vertex(this.sphereX[n7], this.sphereY[n7], this.sphereZ[n7++]);
                this.vertex(this.sphereX[n8], this.sphereY[n8], this.sphereZ[n8++]);
            }
            final int n9 = n6;
            final int n10 = n5;
            this.vertex(this.sphereX[n9], this.sphereY[n9], this.sphereZ[n9]);
            this.vertex(this.sphereX[n10], this.sphereY[n10], this.sphereZ[n10]);
            this.endShape();
        }
        this.beginShape(65);
        for (int l = 0; l < this.sphere_detail; ++l) {
            final int n11 = n5 + l;
            this.vertex(0.0f, 1.0f, 0.0f);
            this.vertex(this.sphereX[n11], this.sphereY[n11], this.sphereZ[n11]);
        }
        this.vertex(0.0f, 1.0f, 0.0f);
        this.vertex(this.sphereX[n5], this.sphereY[n5], this.sphereZ[n5]);
        this.endShape();
        this.pop();
        if (BGraphics.hints[2]) {
            this.triangle.setCulling(false);
        }
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
        this.bezierVertex(n, n2);
        this.bezierVertex(n3, n4);
        this.bezierVertex(n5, n6);
        this.bezierVertex(n7, n8);
        this.endShape();
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.beginShape(33);
        this.bezierVertex(n, n2, n3);
        this.bezierVertex(n4, n5, n6);
        this.bezierVertex(n7, n8, n9);
        this.bezierVertex(n10, n11, n12);
        this.endShape();
    }
    
    private final void bezier_init() {
        this.bezierSegments(this.bezier_segments);
    }
    
    public void bezierSegments(final int bezier_segments) {
        if (this.bezier_forward == null) {
            this.bezier_forward = new float[4][4];
            this.bezier_draw = new float[4][4];
        }
        this.bezier_segments = bezier_segments;
        this.bezier_inited = true;
        this.setup_spline_forward(bezier_segments, this.bezier_forward);
        this.mult_spline_matrix(this.bezier_forward, this.bezier_basis, this.bezier_draw, 4);
    }
    
    private final void curve_init() {
        this.curve_mode(this.curve_segments, this.curve_tightness);
    }
    
    public void curveSegments(final int n) {
        this.curve_mode(n, this.curve_tightness);
    }
    
    public void curveTightness(final float n) {
        this.curve_mode(this.curve_segments, n);
    }
    
    private final void curve_mode(final int curve_segments, final float n) {
        this.curve_segments = curve_segments;
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
        this.setup_spline_forward(curve_segments, this.curve_forward);
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
        this.curveVertex(n, n2);
        this.curveVertex(n3, n4);
        this.curveVertex(n5, n6);
        this.curveVertex(n7, n8);
        this.curveVertex(n7, n8);
        this.endShape();
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.beginShape(33);
        this.curveVertex(n, n2, n3);
        this.curveVertex(n, n2, n3);
        this.curveVertex(n4, n5, n6);
        this.curveVertex(n7, n8, n9);
        this.curveVertex(n10, n11, n12);
        this.curveVertex(n10, n11, n12);
        this.endShape();
    }
    
    private final void setup_spline_forward(final int n, final float[][] array) {
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
    
    private final void mult_spline_matrix(final float[][] array, final float[][] array2, final float[][] array3, final int n) {
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
    
    private final void spline_segment(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, float n9, float n10, final float[][] array, final int n11) {
        float n12 = array[1][0] * n + array[1][1] * n3 + array[1][2] * n5 + array[1][3] * n7;
        float n13 = array[2][0] * n + array[2][1] * n3 + array[2][2] * n5 + array[2][3] * n7;
        final float n14 = array[3][0] * n + array[3][1] * n3 + array[3][2] * n5 + array[3][3] * n7;
        float n15 = array[1][0] * n2 + array[1][1] * n4 + array[1][2] * n6 + array[1][3] * n8;
        float n16 = array[2][0] * n2 + array[2][1] * n4 + array[2][2] * n6 + array[2][3] * n8;
        final float n17 = array[3][0] * n2 + array[3][1] * n4 + array[3][2] * n6 + array[3][3] * n8;
        this.vertex(n9, n10);
        for (int i = 0; i < n11; ++i) {
            n9 += n12;
            n12 += n13;
            n13 += n14;
            n10 += n15;
            n15 += n16;
            n16 += n17;
            this.vertex(n9, n10);
        }
    }
    
    private final void spline_segment(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, float n13, float n14, float n15, final float[][] array, final int n16) {
        float n17 = array[1][0] * n + array[1][1] * n4 + array[1][2] * n7 + array[1][3] * n10;
        float n18 = array[2][0] * n + array[2][1] * n4 + array[2][2] * n7 + array[2][3] * n10;
        final float n19 = array[3][0] * n + array[3][1] * n4 + array[3][2] * n7 + array[3][3] * n10;
        float n20 = array[1][0] * n2 + array[1][1] * n5 + array[1][2] * n8 + array[1][3] * n11;
        float n21 = array[2][0] * n2 + array[2][1] * n5 + array[2][2] * n8 + array[2][3] * n11;
        final float n22 = array[3][0] * n2 + array[3][1] * n5 + array[3][2] * n8 + array[3][3] * n11;
        float n23 = array[1][0] * n3 + array[1][1] * n6 + array[1][2] * n9 + array[1][3] * n12;
        float n24 = array[2][0] * n3 + array[2][1] * n6 + array[2][2] * n9 + array[2][3] * n12;
        final float n25 = array[3][0] * n3 + array[3][1] * n6 + array[3][2] * n9 + array[3][3] * n12;
        this.vertex(n13, n14, n15);
        for (int i = 0; i < n16; ++i) {
            n13 += n17;
            n17 += n18;
            n18 += n19;
            n14 += n20;
            n20 += n21;
            n21 += n22;
            n15 += n23;
            n23 += n24;
            n24 += n25;
            this.vertex(n13, n14, n15);
        }
    }
    
    private final Image gimmeImage(final URL url, final boolean b) {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.connect();
            if (!b) {
                return defaultToolkit.getImage(url);
            }
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                for (int i = bufferedInputStream.read(); i != -1; i = bufferedInputStream.read()) {
                    byteArrayOutputStream.write(i);
                }
            }
            catch (IOException ex) {
                return null;
            }
            bufferedInputStream.close();
            return defaultToolkit.createImage(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    public BImage loadImage(final String s) {
        return this.loadImage(s, true);
    }
    
    public BImage loadImage(final String s, final boolean b) {
        Image image;
        if (s.startsWith("http://")) {
            try {
                image = this.gimmeImage(new URL(s), b);
            }
            catch (MalformedURLException ex) {
                System.err.println("error loading image from " + s);
                ex.printStackTrace();
                return null;
            }
        }
        else {
            image = this.gimmeImage(this.getClass().getResource(s), b);
            if (image == null) {
                image = this.gimmeImage(this.getClass().getResource("data/" + s), b);
            }
        }
        if (image == null) {
            System.err.println("could not load image " + s);
            return null;
        }
        Accessible applet = this.applet;
        if (applet == null) {
            applet = new Frame();
            ((Frame)applet).pack();
        }
        final MediaTracker mediaTracker = new MediaTracker((Component)applet);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            ex2.printStackTrace();
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex3) {
            ex3.printStackTrace();
        }
        if (s.toLowerCase().endsWith(".gif")) {
            for (int i = 0; i < array.length; ++i) {
                if ((array[i] & 0xFF000000) != 0xFF000000) {
                    return new BImage(array, width, height, 2);
                }
            }
        }
        return new BImage(array, width, height, 1);
    }
    
    public void imageMode(final int image_mode) {
        this.image_mode = image_mode;
    }
    
    public void image(final BImage bImage, final float n, final float n2) {
        if (this.dimensions == 0 && !this.lighting && !this._tint && this.image_mode != 2) {
            this.flat_image(bImage, (int)n, (int)n2);
        }
        else {
            this.image(bImage, n, n2, bImage.width, bImage.height, 0.0f, 0.0f, bImage.width, bImage.height);
        }
    }
    
    public void image(final BImage bImage, final float n, final float n2, final float n3, final float n4) {
        this.image(bImage, n, n2, n3, n4, 0.0f, 0.0f, bImage.width, bImage.height);
    }
    
    public void image(final BImage bImage, float n, float n2, float n3, float n4, final float n5, final float n6, final float n7, final float n8) {
        switch (this.image_mode) {
            case 0: {
                n3 += n;
                n4 += n2;
                break;
            }
            case 3: {
                n3 /= 2.0f;
                n4 /= 2.0f;
            }
            case 2: {
                final float n9 = n3;
                final float n10 = n4;
                n3 = n + n9;
                n4 = n2 + n10;
                n -= n9;
                n2 -= n10;
                break;
            }
        }
        final boolean stroke = this._stroke;
        final boolean fill = this._fill;
        final float fillR = this.fillR;
        final float fillG = this.fillG;
        final float fillB = this.fillB;
        final float fillA = this.fillA;
        this._stroke = false;
        this._fill = true;
        if (this._tint) {
            this.fillR = this.tintR;
            this.fillG = this.tintG;
            this.fillB = this.tintB;
            this.fillA = this.tintA;
        }
        else {
            final float n11 = 1.0f;
            this.fillA = n11;
            this.fillB = n11;
            this.fillG = n11;
            this.fillR = n11;
        }
        this.beginShape(128);
        this.texture(bImage);
        this.vertex(n, n2, n5, n6);
        this.vertex(n, n4, n5, n8);
        this.vertex(n3, n4, n7, n8);
        this.vertex(n3, n2, n7, n6);
        this.endShape();
        this._stroke = stroke;
        this._fill = fill;
        this.fillR = fillR;
        this.fillG = fillG;
        this.fillB = fillB;
        this.fillA = fillA;
    }
    
    public void cache(final BImage bImage) {
    }
    
    public void cache(final BImage[] array) {
    }
    
    protected void cache(final BImage bImage, final int n) {
    }
    
    public BFont loadFont(final String s) {
        try {
            final BFont bFont = new BFont(s, this);
            if (bFont == null) {
                System.err.println("Make sure that the font has been copied");
                System.err.println("to the data folder of your sketch.");
            }
            return bFont;
        }
        catch (IOException ex) {
            System.err.println("Could not load font " + s);
            System.err.println("Make sure that the font has been copied");
            System.err.println("to the data folder of your sketch.");
            ex.printStackTrace();
            return null;
        }
    }
    
    public void textFont(final BFont text_font) {
        if (text_font == null) {
            System.err.println("Ignoring improperly loaded font in textFont()");
            return;
        }
        this.text_font = text_font;
        if (this.text_space != 2) {
            this.text_font.size();
        }
        else {
            this.text_font.size(this.text_font.iwidth);
        }
        this.text_font.leading();
    }
    
    public void textFont(final BFont text_font, final float n) {
        if (text_font == null) {
            System.err.println("Ignoring improperly loaded font in textFont()");
            return;
        }
        this.text_font = text_font;
        if (this.text_space != 2) {
            this.text_font.size(n);
        }
        else {
            System.err.println("Cannot set size of SCREEN_SPACE fonts");
            this.text_font.size(this.text_font.iwidth);
        }
        this.text_font.leading();
    }
    
    public void textSize(final float n) {
        if (this.text_font == null) {
            System.err.println("First set a font before setting its size.");
            return;
        }
        if (this.text_space == 2) {
            System.err.println("Cannot set size of SCREEN_SPACE fonts.");
            return;
        }
        this.text_font.size(n);
    }
    
    public void textLeading(final float n) {
        if (this.text_font == null) {
            System.err.println("First set a font before setting its leading.");
            return;
        }
        this.text_font.leading(n);
    }
    
    public void textMode(final int text_mode) {
        this.text_mode = text_mode;
    }
    
    public void textSpace(final int text_space) {
        this.text_space = text_space;
        if (text_space == 2 && this.text_font != null) {
            this.text_font.size(this.text_font.iwidth);
            this.text_font.leading();
        }
    }
    
    public void text(final char c, float n, final float n2) {
        if (this.text_font == null) {
            System.err.println("text(): first set a font before drawing text");
            return;
        }
        if (this.text_mode == 2) {
            n -= this.text_font.width(c) / 2.0f;
        }
        else if (this.text_mode == 4) {
            n -= this.text_font.width(c);
        }
        this.text_font.text(c, n, n2, this);
    }
    
    public void text(final String s, float n, final float n2) {
        if (this.text_font == null) {
            System.err.println("text(): first set a font before drawing text");
            return;
        }
        if (this.text_mode == 2) {
            n -= this.text_font.width(s) / 2.0f;
        }
        else if (this.text_mode == 4) {
            n -= this.text_font.width(s);
        }
        this.text_font.text(s, n, n2, this);
    }
    
    public void text(final int n, final float n2, final float n3) {
        this.text(String.valueOf(n), n2, n3);
    }
    
    public void text(final float n, final float n2, final float n3) {
        this.text(BApplet.nfs(n, 0, 4), n2, n3);
    }
    
    public void push() {
        if (this.matrixStackDepth + 1 == 32) {
            this.message(1, "matrix stack overflow, to much pushmatrix");
            return;
        }
        final float[] array = this.matrixStack[this.matrixStackDepth];
        array[0] = this.m00;
        array[1] = this.m01;
        array[2] = this.m02;
        array[3] = this.m03;
        array[4] = this.m10;
        array[5] = this.m11;
        array[6] = this.m12;
        array[7] = this.m13;
        array[8] = this.m20;
        array[9] = this.m21;
        array[10] = this.m22;
        array[11] = this.m23;
        array[12] = this.m30;
        array[13] = this.m31;
        array[14] = this.m32;
        array[15] = this.m33;
        ++this.matrixStackDepth;
    }
    
    public void pop() {
        if (this.matrixStackDepth == 0) {
            this.message(1, "matrix stack underflow, to many popmatrix");
            return;
        }
        --this.matrixStackDepth;
        final float[] array = this.matrixStack[this.matrixStackDepth];
        this.m00 = array[0];
        this.m01 = array[1];
        this.m02 = array[2];
        this.m03 = array[3];
        this.m10 = array[4];
        this.m11 = array[5];
        this.m12 = array[6];
        this.m13 = array[7];
        this.m20 = array[8];
        this.m21 = array[9];
        this.m22 = array[10];
        this.m23 = array[11];
        this.m30 = array[12];
        this.m31 = array[13];
        this.m32 = array[14];
        this.m33 = array[15];
        if (this.matrixStackDepth == 0 && this.m00 == 1.0f && this.m01 == 0.0f && this.m02 == 0.0f && this.m03 == 0.0f && this.m10 == 0.0f && this.m11 == 1.0f && this.m12 == 0.0f && this.m13 == 0.0f && this.m20 == 0.0f && this.m21 == 0.0f && this.m22 == 1.0f && this.m23 == 0.0f && this.m30 == 0.0f && this.m31 == 0.0f && this.m32 == 0.0f && this.m33 == 1.0f) {
            this.dimensions = 0;
        }
    }
    
    public void resetMatrix() {
        this.dimensions = 0;
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m03 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.m13 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 1.0f;
        this.m23 = 0.0f;
        this.m30 = 0.0f;
        this.m31 = 0.0f;
        this.m32 = 0.0f;
        this.m33 = 1.0f;
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        final float m00 = this.m00 * n + this.m01 * n5 + this.m02 * n9 + this.m03 * n13;
        final float m2 = this.m00 * n2 + this.m01 * n6 + this.m02 * n10 + this.m03 * n14;
        final float m3 = this.m00 * n3 + this.m01 * n7 + this.m02 * n11 + this.m03 * n15;
        final float m4 = this.m00 * n4 + this.m01 * n8 + this.m02 * n12 + this.m03 * n16;
        final float m5 = this.m10 * n + this.m11 * n5 + this.m12 * n9 + this.m13 * n13;
        final float m6 = this.m10 * n2 + this.m11 * n6 + this.m12 * n10 + this.m13 * n14;
        final float m7 = this.m10 * n3 + this.m11 * n7 + this.m12 * n11 + this.m13 * n15;
        final float m8 = this.m10 * n4 + this.m11 * n8 + this.m12 * n12 + this.m13 * n16;
        final float m9 = this.m20 * n + this.m21 * n5 + this.m22 * n9 + this.m23 * n13;
        final float m10 = this.m20 * n2 + this.m21 * n6 + this.m22 * n10 + this.m23 * n14;
        final float m11 = this.m20 * n3 + this.m21 * n7 + this.m22 * n11 + this.m23 * n15;
        final float m12 = this.m20 * n4 + this.m21 * n8 + this.m22 * n12 + this.m23 * n16;
        final float m13 = this.m30 * n + this.m31 * n5 + this.m32 * n9 + this.m33 * n13;
        final float m14 = this.m30 * n2 + this.m31 * n6 + this.m32 * n10 + this.m33 * n14;
        final float m15 = this.m30 * n3 + this.m31 * n7 + this.m32 * n11 + this.m33 * n15;
        final float m16 = this.m30 * n4 + this.m31 * n8 + this.m32 * n12 + this.m33 * n16;
        this.m00 = m00;
        this.m01 = m2;
        this.m02 = m3;
        this.m03 = m4;
        this.m10 = m5;
        this.m11 = m6;
        this.m12 = m7;
        this.m13 = m8;
        this.m20 = m9;
        this.m21 = m10;
        this.m22 = m11;
        this.m23 = m12;
        this.m30 = m13;
        this.m31 = m14;
        this.m32 = m15;
        this.m33 = m16;
    }
    
    public void beginCamera() {
        this.resetMatrix();
    }
    
    public void cameraMode(final int camera_mode) {
        this.camera_mode = camera_mode;
    }
    
    public void endCamera() {
        this.p00 = this.m00;
        this.p01 = this.m01;
        this.p02 = this.m02;
        this.p03 = this.m03;
        this.p10 = this.m10;
        this.p11 = this.m11;
        this.p12 = this.m12;
        this.p13 = this.m13;
        this.p20 = this.m20;
        this.p21 = this.m21;
        this.p22 = this.m22;
        this.p23 = this.m23;
        this.p30 = this.m30;
        this.p31 = this.m31;
        this.p32 = this.m32;
        this.p33 = this.m33;
        this.resetMatrix();
    }
    
    public float screenX(final float n, final float n2, final float n3) {
        final float n4 = this.m00 * n + this.m01 * n2 + this.m02 * n3 + this.m03;
        final float n5 = this.m10 * n + this.m11 * n2 + this.m12 * n3 + this.m13;
        final float n6 = this.m20 * n + this.m21 * n2 + this.m22 * n3 + this.m23;
        final float n7 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        float n8 = this.p00 * n4 + this.p01 * n5 + this.p02 * n6 + this.p03 * n7;
        final float n9 = this.p30 * n4 + this.p31 * n5 + this.p32 * n6 + this.p33 * n7;
        if (n9 != 0.0f) {
            n8 /= n9;
        }
        return this.width * (1.0f + n8) / 2.0f;
    }
    
    public float screenY(final float n, final float n2, final float n3) {
        final float n4 = this.m00 * n + this.m01 * n2 + this.m02 * n3 + this.m03;
        final float n5 = this.m10 * n + this.m11 * n2 + this.m12 * n3 + this.m13;
        final float n6 = this.m20 * n + this.m21 * n2 + this.m22 * n3 + this.m23;
        final float n7 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        float n8 = this.p10 * n4 + this.p11 * n5 + this.p12 * n6 + this.p13 * n7;
        final float n9 = this.p30 * n4 + this.p31 * n5 + this.p32 * n6 + this.p33 * n7;
        if (n9 != 0.0f) {
            n8 /= n9;
        }
        return this.height * (1.0f + n8) / 2.0f;
    }
    
    public float screenZ(final float n, final float n2, final float n3) {
        final float n4 = this.m00 * n + this.m01 * n2 + this.m02 * n3 + this.m03;
        final float n5 = this.m10 * n + this.m11 * n2 + this.m12 * n3 + this.m13;
        final float n6 = this.m20 * n + this.m21 * n2 + this.m22 * n3 + this.m23;
        final float n7 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        float n8 = this.p20 * n4 + this.p21 * n5 + this.p22 * n6 + this.p23 * n7;
        final float n9 = this.p30 * n4 + this.p31 * n5 + this.p32 * n6 + this.p33 * n7;
        if (n9 != 0.0f) {
            n8 /= n9;
        }
        return (n8 + 1.0f) / 2.0f;
    }
    
    public float objectX(final float n, final float n2, final float n3) {
        final float n4 = this.m00 * n + this.m01 * n2 + this.m02 * n3 + this.m03;
        final float n5 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public float objectY(final float n, final float n2, final float n3) {
        final float n4 = this.m10 * n + this.m11 * n2 + this.m12 * n3 + this.m13;
        final float n5 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public float objectZ(final float n, final float n2, final float n3) {
        final float n4 = this.m20 * n + this.m21 * n2 + this.m22 * n3 + this.m23;
        final float n5 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public void ortho(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.applyMatrix(2.0f / (n2 - n), 0.0f, 0.0f, -(n2 + n) / (n2 - n), 0.0f, 2.0f / (n4 - n3), 0.0f, -(n4 + n3) / (n4 - n3), 0.0f, 0.0f, -2.0f / (n6 - n5), -(n6 + n5) / (n6 - n5), 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void perspective(final float n, final float n2, final float n3, final float n4) {
        final float n5 = n3 * this.tan(n * 3.1415927f / 360.0f);
        final float n6 = -n5;
        this.frustum(n6 * n2, n5 * n2, n6, n5, n3, n4);
    }
    
    public void frustum(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.applyMatrix(2.0f * n5 / (n2 - n), 0.0f, (n2 + n) / (n2 - n), 0.0f, 0.0f, 2.0f * n5 / (n4 - n3), (n4 + n3) / (n4 - n3), 0.0f, 0.0f, 0.0f, -(n6 + n5) / (n6 - n5), -(2.0f * n6 * n5) / (n6 - n5), 0.0f, 0.0f, -1.0f, 0.0f);
    }
    
    public void lookat(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        float n10 = n - n4;
        float n11 = n2 - n5;
        float n12 = n3 - n6;
        final float sqrt = this.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
        if (sqrt != 0.0f) {
            n10 /= sqrt;
            n11 /= sqrt;
            n12 /= sqrt;
        }
        float n13 = n8 * n12 - n9 * n11;
        float n14 = -n7 * n12 + n9 * n10;
        float n15 = n7 * n11 - n8 * n10;
        float n16 = n11 * n15 - n12 * n14;
        float n17 = -n10 * n15 + n12 * n13;
        float n18 = n10 * n14 - n11 * n13;
        final float sqrt2 = this.sqrt(n13 * n13 + n14 * n14 + n15 * n15);
        if (sqrt2 != 0.0f) {
            n13 /= sqrt2;
            n14 /= sqrt2;
            n15 /= sqrt2;
        }
        final float sqrt3 = this.sqrt(n16 * n16 + n17 * n17 + n18 * n18);
        if (sqrt3 != 0.0f) {
            n16 /= sqrt3;
            n17 /= sqrt3;
            n18 /= sqrt3;
        }
        this.applyMatrix(n13, n14, n15, 0.0f, n16, n17, n18, 0.0f, n10, n11, n12, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.translate(-n, -n2, -n3);
    }
    
    public void translate(final float n, final float n2) {
        if (this.dimensions == 3) {
            this.translate(n, n2, 0.0f);
        }
        else {
            if (this.dimensions == 0) {
                this.dimensions = 2;
            }
            this.m03 += n * this.m00 + n2 * this.m01 + this.m02;
            this.m13 += n * this.m10 + n2 * this.m11 + this.m12;
            this.m23 += n * this.m20 + n2 * this.m21 + this.m22;
            this.m33 += n * this.m30 + n2 * this.m31 + this.m32;
        }
    }
    
    public void translate(final float n, final float n2, final float n3) {
        this.dimensions = 3;
        this.m03 += n * this.m00 + n2 * this.m01 + n3 * this.m02;
        this.m13 += n * this.m10 + n2 * this.m11 + n3 * this.m12;
        this.m23 += n * this.m20 + n2 * this.m21 + n3 * this.m22;
        this.m33 += n * this.m30 + n2 * this.m31 + n3 * this.m32;
    }
    
    public void rotateX(final float n) {
        this.dimensions = 3;
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        this.applyMatrix(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, cos, -sin, 0.0f, 0.0f, sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void rotateY(final float n) {
        this.dimensions = 3;
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        this.applyMatrix(cos, 0.0f, sin, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -sin, 0.0f, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void rotate(final float n) {
        this.rotateZ(n);
    }
    
    public void rotateZ(final float n) {
        if (this.dimensions == 0) {
            this.dimensions = 2;
        }
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        this.applyMatrix(cos, -sin, 0.0f, 0.0f, sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        this.dimensions = 3;
        final float cos = this.cos(n);
        final float sin = this.sin(n);
        final float n5 = 1.0f - cos;
        this.applyMatrix(n5 * n2 * n2 + cos, n5 * n2 * n3 - sin * n4, n5 * n2 * n4 + sin * n3, 0.0f, n5 * n2 * n3 + sin * n4, n5 * n3 * n3 + cos, n5 * n3 * n4 - sin * n2, 0.0f, n5 * n2 * n4 - sin * n3, n5 * n3 * n4 + sin * n2, n5 * n4 * n4 + cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void scale(final float n) {
        if (this.dimensions == 3) {
            this.applyMatrix(n, 0.0f, 0.0f, 0.0f, 0.0f, n, 0.0f, 0.0f, 0.0f, 0.0f, n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        }
        else {
            this.dimensions = 2;
            this.applyMatrix(n, 0.0f, 0.0f, 0.0f, 0.0f, n, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        }
    }
    
    public void scale(final float n, final float n2) {
        if (this.dimensions == 0) {
            this.dimensions = 2;
        }
        this.applyMatrix(n, 0.0f, 0.0f, 0.0f, 0.0f, n2, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void scale(final float n, final float n2, final float n3) {
        this.dimensions = 3;
        this.applyMatrix(n, 0.0f, 0.0f, 0.0f, 0.0f, n2, 0.0f, 0.0f, 0.0f, 0.0f, n3, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void transform(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.dimensions = 3;
        this.applyMatrix(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void colorMode(final int color_mode) {
        this.color_mode = color_mode;
    }
    
    public void colorMode(final int n, final float n2) {
        this.colorMode(n, n2, n2, n2, n2);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4) {
        this.colorMode(n, n2, n3, n4, this.colorMaxA);
    }
    
    public void colorMode(final int color_mode, final float colorMaxX, final float colorMaxY, final float colorMaxZ, final float colorMaxA) {
        this.color_mode = color_mode;
        this.colorMaxX = colorMaxX;
        this.colorMaxY = colorMaxY;
        this.colorMaxZ = colorMaxZ;
        this.colorMaxA = colorMaxA;
        boolean color_scale = false;
        if (colorMaxA != 1.0f || colorMaxX != colorMaxY || colorMaxY != colorMaxZ || colorMaxZ != colorMaxA) {
            color_scale = true;
        }
        this.color_scale = color_scale;
    }
    
    protected void calc_color(final float n) {
        this.calc_color(n, this.colorMaxA);
    }
    
    protected void calc_color(float colorMaxX, float colorMaxA) {
        if (colorMaxX > this.colorMaxX) {
            colorMaxX = this.colorMaxX;
        }
        if (colorMaxA > this.colorMaxA) {
            colorMaxA = this.colorMaxA;
        }
        if (colorMaxX < 0.0f) {
            colorMaxX = 0.0f;
        }
        if (colorMaxA < 0.0f) {
            colorMaxA = 0.0f;
        }
        this.calcR = (this.color_scale ? (colorMaxX / this.colorMaxX) : colorMaxX);
        this.calcG = this.calcR;
        this.calcB = this.calcR;
        this.calcA = (this.color_scale ? (colorMaxA / this.colorMaxA) : colorMaxA);
        this.calcRi = (int)(this.calcR * 255.0f);
        this.calcGi = (int)(this.calcG * 255.0f);
        this.calcBi = (int)(this.calcB * 255.0f);
        this.calcAi = (int)(this.calcA * 255.0f);
        this.calci = (this.calcAi << 24 | this.calcRi << 16 | this.calcGi << 8 | this.calcBi);
        boolean calc_alpha = false;
        if (this.calcAi != 255) {
            calc_alpha = true;
        }
        this.calc_alpha = calc_alpha;
    }
    
    protected void calc_color(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3, this.colorMaxA);
    }
    
    protected void calc_color(float colorMaxX, float colorMaxY, float colorMaxZ, float colorMaxA) {
        if (colorMaxX > this.colorMaxX) {
            colorMaxX = this.colorMaxX;
        }
        if (colorMaxY > this.colorMaxY) {
            colorMaxY = this.colorMaxY;
        }
        if (colorMaxZ > this.colorMaxZ) {
            colorMaxZ = this.colorMaxZ;
        }
        if (colorMaxA > this.colorMaxA) {
            colorMaxA = this.colorMaxA;
        }
        if (colorMaxX < 0.0f) {
            colorMaxX = 0.0f;
        }
        if (colorMaxY < 0.0f) {
            colorMaxY = 0.0f;
        }
        if (colorMaxZ < 0.0f) {
            colorMaxZ = 0.0f;
        }
        if (colorMaxA < 0.0f) {
            colorMaxA = 0.0f;
        }
        switch (this.color_mode) {
            case 1: {
                if (this.color_scale) {
                    this.calcR = colorMaxX / this.colorMaxX;
                    this.calcG = colorMaxY / this.colorMaxY;
                    this.calcB = colorMaxZ / this.colorMaxZ;
                    this.calcA = colorMaxA / this.colorMaxA;
                }
                else {
                    this.calcR = colorMaxX;
                    this.calcG = colorMaxY;
                    this.calcB = colorMaxZ;
                    this.calcA = colorMaxA;
                }
                break;
            }
            case 3: {
                colorMaxX /= this.colorMaxX;
                colorMaxY /= this.colorMaxY;
                colorMaxZ /= this.colorMaxZ;
                this.calcA = (this.color_scale ? (colorMaxA / this.colorMaxA) : colorMaxA);
                if (colorMaxY == 0.0f) {
                    final float calcR = colorMaxZ;
                    this.calcB = calcR;
                    this.calcG = calcR;
                    this.calcR = calcR;
                }
                else {
                    final float n = (colorMaxX - (int)colorMaxX) * 6.0f;
                    final float n2 = n - (int)n;
                    final float n3 = colorMaxZ * (1.0f - colorMaxY);
                    final float calcB = colorMaxZ * (1.0f - colorMaxY * n2);
                    final float calcR2 = colorMaxZ * (1.0f - colorMaxY * (1.0f - n2));
                    switch ((int)n) {
                        case 0: {
                            this.calcR = colorMaxZ;
                            this.calcG = calcR2;
                            this.calcB = n3;
                            break;
                        }
                        case 1: {
                            this.calcR = calcB;
                            this.calcG = colorMaxZ;
                            this.calcB = n3;
                            break;
                        }
                        case 2: {
                            this.calcR = n3;
                            this.calcG = colorMaxZ;
                            this.calcB = calcR2;
                            break;
                        }
                        case 3: {
                            this.calcR = n3;
                            this.calcG = calcB;
                            this.calcB = colorMaxZ;
                            break;
                        }
                        case 4: {
                            this.calcR = calcR2;
                            this.calcG = n3;
                            this.calcB = colorMaxZ;
                            break;
                        }
                        case 5: {
                            this.calcR = colorMaxZ;
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
        this.calci = (this.calcAi << 24 | this.calcRi << 16 | this.calcGi << 8 | this.calcBi);
        boolean calc_alpha = false;
        if (this.calcAi != 255) {
            calc_alpha = true;
        }
        this.calc_alpha = calc_alpha;
    }
    
    protected void unpack_for_calc(final int calci) {
        this.calci = calci;
        this.calcAi = (calci >> 24 & 0xFF);
        this.calcRi = (calci >> 16 & 0xFF);
        this.calcGi = (calci >> 8 & 0xFF);
        this.calcBi = (calci & 0xFF);
        this.calcA = this.calcAi / 255.0f;
        this.calcR = this.calcRi / 255.0f;
        this.calcG = this.calcGi / 255.0f;
        this.calcB = this.calcBi / 255.0f;
        boolean calc_alpha = false;
        if (this.calcAi != 255) {
            calc_alpha = true;
        }
        this.calc_alpha = calc_alpha;
    }
    
    protected void calc_tint() {
        this._tint = true;
        this.tintR = this.calcR;
        this.tintG = this.calcG;
        this.tintB = this.calcB;
        this.tintA = this.calcA;
        this.tintRi = this.calcRi;
        this.tintGi = this.calcGi;
        this.tintBi = this.calcBi;
        this.tintAi = this.calcAi;
        this.tint = this.calci;
        this.tint_alpha = this.calc_alpha;
    }
    
    protected void calc_fill() {
        this._fill = true;
        this.fillChanged = true;
        this.fillR = this.calcR;
        this.fillG = this.calcG;
        this.fillB = this.calcB;
        this.fillA = this.calcA;
        this.fillRi = this.calcRi;
        this.fillGi = this.calcGi;
        this.fillBi = this.calcBi;
        this.fillAi = this.calcAi;
        this.fill = this.calci;
        this.fill_alpha = this.calc_alpha;
    }
    
    protected void calc_stroke() {
        this._stroke = true;
        this.strokeChanged = true;
        this.strokeR = this.calcR;
        this.strokeG = this.calcG;
        this.strokeB = this.calcB;
        this.strokeA = this.calcA;
        this.strokeRi = this.calcRi;
        this.strokeGi = this.calcGi;
        this.strokeBi = this.calcBi;
        this.strokeAi = this.calcAi;
        this.stroke = this.calci;
        this.stroke_alpha = this.calc_alpha;
    }
    
    protected void calc_background() {
        this._background = true;
        this.backR = this.calcR;
        this.backG = this.calcG;
        this.backB = this.calcB;
        this.backRi = this.calcRi;
        this.backGi = this.calcGi;
        this.backBi = this.calcBi;
        this.background = this.calci;
    }
    
    public void noTint() {
        this._tint = false;
    }
    
    public void tint(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorMaxX) {
            this.tint((float)n);
        }
        else {
            this.unpack_for_calc(n);
            this.calc_tint();
        }
    }
    
    public void tint(final float n) {
        this.calc_color(n);
        this.calc_tint();
    }
    
    public void tint(final float n, final float n2) {
        this.calc_color(n, n2);
        this.calc_tint();
    }
    
    public void tint(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3);
        this.calc_tint();
    }
    
    public void tint(final float n, final float n2, final float n3, final float n4) {
        this.calc_color(n, n2, n3, n4);
        this.calc_tint();
    }
    
    public void noFill() {
        this._fill = false;
    }
    
    public void fill(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorMaxX) {
            this.fill((float)n);
        }
        else {
            this.unpack_for_calc(n);
            this.calc_fill();
        }
    }
    
    public void fill(final float n) {
        this.calc_color(n);
        this.calc_fill();
    }
    
    public void fill(final float n, final float n2) {
        this.calc_color(n, n2);
        this.calc_fill();
    }
    
    public void fill(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3);
        this.calc_fill();
    }
    
    public void fill(final float n, final float n2, final float n3, final float n4) {
        this.calc_color(n, n2, n3, n4);
        this.calc_fill();
    }
    
    public void strokeWeight(final float strokeWeight) {
        this.strokeWeight = strokeWeight;
    }
    
    public void strokeJoin(final int strokeJoin) {
        this.strokeJoin = strokeJoin;
    }
    
    public void strokeMiter(final int strokeMiter) {
        this.strokeMiter = strokeMiter;
    }
    
    public void noStroke() {
        this._stroke = false;
    }
    
    public void stroke(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorMaxX) {
            this.stroke((float)n);
        }
        else {
            this.unpack_for_calc(n);
            this.calc_stroke();
        }
    }
    
    public void stroke(final float n) {
        this.calc_color(n);
        this.calc_stroke();
    }
    
    public void stroke(final float n, final float n2) {
        this.calc_color(n, n2);
        this.calc_stroke();
    }
    
    public void stroke(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3);
        this.calc_stroke();
    }
    
    public void stroke(final float n, final float n2, final float n3, final float n4) {
        this.calc_color(n, n2, n3, n4);
        this.calc_stroke();
    }
    
    public void background(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorMaxX) {
            this.background((float)n);
        }
        else {
            this.unpack_for_calc(n);
            this.calc_background();
        }
        this.clear();
    }
    
    public void background(final float n) {
        this.calc_color(n);
        this.calc_background();
        this.clear();
    }
    
    public void background(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3);
        this.calc_background();
        this.clear();
    }
    
    public void background(final BImage bImage) {
        if (bImage.width != this.width || bImage.height != this.height) {
            System.err.println("background image must be the same size as your application");
            return;
        }
        if (bImage.format != 1 && bImage.format != 2) {
            System.err.println("background images should be RGB or RGBA");
            return;
        }
        System.arraycopy(bImage.pixels, 0, this.pixels, 0, this.pixels.length);
        for (int i = 0; i < this.pixelCount; ++i) {
            this.zbuffer[i] = Float.MAX_VALUE;
        }
    }
    
    public void clear() {
        for (int i = 0; i < this.pixelCount; ++i) {
            this.pixels[i] = this.background;
            this.zbuffer[i] = Float.MAX_VALUE;
        }
    }
    
    public void lights() {
        this.lighting = true;
    }
    
    public void noLights() {
        this.lighting = false;
    }
    
    public void smooth() {
        this.smooth = true;
    }
    
    public void noSmooth() {
        this.smooth = false;
    }
    
    public void hint(final int n) {
        BGraphics.hints[n] = true;
    }
    
    public void unhint(final int n) {
        BGraphics.hints[n] = false;
    }
    
    public void message(final int n, final String s) {
        switch (n) {
            case 1: {
                System.err.println("bagel complaint: " + s);
                break;
            }
            case 2: {
                System.err.println("bagel problem: " + s);
                break;
            }
        }
    }
    
    public void message(final int n, final String s, final Exception ex) {
        this.message(n, s);
        ex.printStackTrace();
    }
    
    public InputStream loadStream(final String s) throws IOException {
        InputStream inputStream = null;
        final boolean endsWith = s.toLowerCase().endsWith(".gz");
        if (s.startsWith("http://")) {
            try {
                final InputStream openStream = new URL(s).openStream();
                return endsWith ? new GZIPInputStream(openStream) : openStream;
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        try {
            inputStream = this.getClass().getResourceAsStream(s);
            if (inputStream != null) {
                return endsWith ? new GZIPInputStream(inputStream) : inputStream;
            }
        }
        catch (IOException ex2) {}
        try {
            inputStream = this.getClass().getResourceAsStream("data/" + s);
            if (inputStream != null) {
                return endsWith ? new GZIPInputStream(inputStream) : inputStream;
            }
        }
        catch (IOException ex3) {}
        try {
            try {
                inputStream = new FileInputStream(new File("data", s));
                if (inputStream != null) {
                    return endsWith ? new GZIPInputStream(inputStream) : inputStream;
                }
            }
            catch (IOException ex4) {}
            try {
                inputStream = new FileInputStream(s);
                if (inputStream != null) {
                    return endsWith ? new GZIPInputStream(inputStream) : inputStream;
                }
            }
            catch (IOException ex5) {}
        }
        catch (SecurityException ex6) {}
        if (inputStream == null) {
            throw new IOException("loadStream() could not open " + s);
        }
        return null;
    }
    
    public byte[] loadBytes(final String s) {
        try {
            return this.loadBytes(this.loadStream(s));
        }
        catch (IOException ex) {
            System.err.println("problem loading bytes from " + s);
            ex.printStackTrace();
            return null;
        }
    }
    
    public byte[] loadBytes(final InputStream inputStream) {
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = bufferedInputStream.read(); i != -1; i = bufferedInputStream.read()) {
                byteArrayOutputStream.write(i);
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String[] loadStrings(final String s) {
        try {
            return this.loadStrings(this.loadStream(s));
        }
        catch (IOException ex) {
            System.err.println("problem loading strings from " + s);
            ex.printStackTrace();
            return null;
        }
    }
    
    public String[] loadStrings(final InputStream inputStream) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String[] array = new String[100];
            int n = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (n == array.length) {
                    final String[] array2 = new String[n << 1];
                    System.arraycopy(array, 0, array2, 0, n);
                    array = array2;
                }
                array[n++] = line;
            }
            bufferedReader.close();
            if (n == array.length) {
                return array;
            }
            final String[] array3 = new String[n];
            System.arraycopy(array, 0, array3, 0, n);
            return array3;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void saveBytes(final String s, final byte[] array) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(s);
            this.saveBytes(fileOutputStream, array);
            fileOutputStream.close();
        }
        catch (IOException ex) {
            System.err.println("error saving bytes to " + s);
            ex.printStackTrace();
        }
    }
    
    public void saveBytes(final OutputStream outputStream, final byte[] array) {
        try {
            outputStream.write(array);
            outputStream.flush();
        }
        catch (IOException ex) {
            System.err.println("error while saving bytes");
            ex.printStackTrace();
        }
    }
    
    public void saveStrings(final String s, final String[] array) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(s);
            this.saveStrings(fileOutputStream, array);
            fileOutputStream.close();
        }
        catch (IOException ex) {
            System.err.println("error while saving strings");
            ex.printStackTrace();
        }
    }
    
    public void saveStrings(final OutputStream outputStream, final String[] array) {
        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
        for (int i = 0; i < array.length; ++i) {
            printWriter.println(array[i]);
        }
        printWriter.flush();
    }
    
    public void sort(final String[] array) {
        this.sort(array, array.length, null);
    }
    
    public void sort(final String[] array, final Object[] array2) {
        this.sort(array, array.length, array2);
    }
    
    public void sort(final int[] array) {
        this.sort(array, array.length, null);
    }
    
    public void sort(final int[] array, final Object[] array2) {
        this.sort(array, array.length, array2);
    }
    
    public void sort(final float[] array) {
        this.sort(array, array.length, null);
    }
    
    public void sort(final float[] array, final Object[] array2) {
        this.sort(array, array.length, array2);
    }
    
    public void sort(final double[] array) {
        this.sort(array, array.length, null);
    }
    
    public void sort(final double[] array, final Object[] array2) {
        this.sort(array, array.length, array2);
    }
    
    public void sort(final String[] sort_strings, final int n, final Object[] sort_objects) {
        if (n == 0) {
            return;
        }
        this.sort_mode = 0;
        this.sort_strings = sort_strings;
        this.sort_objects = sort_objects;
        this.sort_internal(0, n - 1);
    }
    
    public void sort(final int[] sort_ints, final int n, final Object[] sort_objects) {
        if (n == 0) {
            return;
        }
        this.sort_mode = 1;
        this.sort_ints = sort_ints;
        this.sort_objects = sort_objects;
        this.sort_internal(0, n - 1);
    }
    
    public void sort(final float[] sort_floats, final int n, final Object[] sort_objects) {
        if (n == 0) {
            return;
        }
        this.sort_mode = 2;
        this.sort_floats = sort_floats;
        this.sort_objects = sort_objects;
        this.sort_internal(0, n - 1);
    }
    
    public void sort(final double[] sort_doubles, final int n, final Object[] sort_objects) {
        if (n == 0) {
            return;
        }
        this.sort_mode = 3;
        this.sort_doubles = sort_doubles;
        this.sort_objects = sort_objects;
        this.sort_internal(0, n - 1);
    }
    
    protected void sort_internal(final int n, final int n2) {
        this.sort_swap((n + n2) / 2, n2);
        final int sort_partition = this.sort_partition(n - 1, n2);
        this.sort_swap(sort_partition, n2);
        if (sort_partition - n > 1) {
            this.sort_internal(n, sort_partition - 1);
        }
        if (n2 - sort_partition > 1) {
            this.sort_internal(sort_partition + 1, n2);
        }
    }
    
    protected int sort_partition(int n, int n2) {
        while (true) {
            if (this.sort_compare(++n, n2) >= 0) {
                while (n2 != 0 && this.sort_compare(--n2, n2) > 0) {}
                this.sort_swap(n, n2);
                if (n >= n2) {
                    break;
                }
                continue;
            }
        }
        this.sort_swap(n, n2);
        return n;
    }
    
    protected void sort_swap(final int n, final int n2) {
        switch (this.sort_mode) {
            case 0: {
                final String s = this.sort_strings[n];
                this.sort_strings[n] = this.sort_strings[n2];
                this.sort_strings[n2] = s;
                break;
            }
            case 1: {
                final int n3 = this.sort_ints[n];
                this.sort_ints[n] = this.sort_ints[n2];
                this.sort_ints[n2] = n3;
                break;
            }
            case 2: {
                final float n4 = this.sort_floats[n];
                this.sort_floats[n] = this.sort_floats[n2];
                this.sort_floats[n2] = n4;
                break;
            }
            case 3: {
                final double n5 = this.sort_doubles[n];
                this.sort_doubles[n] = this.sort_doubles[n2];
                this.sort_doubles[n2] = n5;
                break;
            }
        }
        if (this.sort_objects != null) {
            final Object o = this.sort_objects[n];
            this.sort_objects[n] = this.sort_objects[n2];
            this.sort_objects[n2] = o;
        }
    }
    
    protected int sort_compare(final int n, final int n2) {
        switch (this.sort_mode) {
            case 0: {
                return this.sort_strings[n].compareTo(this.sort_strings[n2]);
            }
            case 1: {
                if (this.sort_ints[n] < this.sort_ints[n2]) {
                    return -1;
                }
                final int n3 = 1;
                int n4 = 0;
                if (this.sort_ints[n] == this.sort_ints[n2]) {
                    n4 = 1;
                }
                return n3 - n4;
            }
            case 2: {
                if (this.sort_floats[n] < this.sort_floats[n2]) {
                    return -1;
                }
                final int n5 = 1;
                int n6 = 0;
                if (this.sort_floats[n] == this.sort_floats[n2]) {
                    n6 = 1;
                }
                return n5 - n6;
            }
            case 3: {
                if (this.sort_doubles[n] < this.sort_doubles[n2]) {
                    return -1;
                }
                final int n7 = 1;
                int n8 = 0;
                if (this.sort_doubles[n] == this.sort_doubles[n2]) {
                    n8 = 1;
                }
                return n7 - n8;
            }
            default: {
                return 0;
            }
        }
    }
    
    public final int color(final int n, final int n2, final int n3) {
        if (this.color_mode == 1 && !this.color_scale) {
            return 0xFF000000 | n << 16 | n2 << 8 | n3;
        }
        this.calc_color(n, n2, n3);
        return this.calci;
    }
    
    public final int color(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3);
        return this.calci;
    }
    
    public final int color(final int n, final int n2, final int n3, final int n4) {
        if (this.color_mode == 1 && !this.color_scale) {
            return n4 << 24 | n << 16 | n2 << 8 | n3;
        }
        this.calc_color(n, n2, n3, n4);
        return this.calci;
    }
    
    public final int color(final float n, final float n2, final float n3, final float n4) {
        this.calc_color(n, n2, n3, n4);
        return this.calci;
    }
    
    public final float alpha(final int n) {
        final float n2 = n >> 24 & 0xFF;
        if (this.colorMaxA == 255.0f) {
            return n2;
        }
        return n2 / 255.0f * this.colorMaxA;
    }
    
    public final float red(final int n) {
        final float n2 = n >> 16 & 0xFF;
        if (this.color_mode == 1 && !this.color_scale) {
            return n2;
        }
        return n2 / 255.0f * this.colorMaxX;
    }
    
    public final float green(final int n) {
        final float n2 = n >> 8 & 0xFF;
        if (this.color_mode == 1 && !this.color_scale) {
            return n2;
        }
        return n2 / 255.0f * this.colorMaxY;
    }
    
    public final float blue(final int n) {
        final float n2 = n & 0xFF;
        if (this.color_mode == 1 && !this.color_scale) {
            return n2;
        }
        return n2 / 255.0f * this.colorMaxZ;
    }
    
    public final float hue(final int cacheHsbKey) {
        if (cacheHsbKey != this.cacheHsbKey) {
            Color.RGBtoHSB(cacheHsbKey >> 16 & 0xFF, cacheHsbKey >> 8 & 0xFF, cacheHsbKey & 0xFF, this.cacheHsbValue);
            this.cacheHsbKey = cacheHsbKey;
        }
        return this.cacheHsbValue[0] * this.colorMaxX;
    }
    
    public final float saturation(final int cacheHsbKey) {
        if (cacheHsbKey != this.cacheHsbKey) {
            Color.RGBtoHSB(cacheHsbKey >> 16 & 0xFF, cacheHsbKey >> 8 & 0xFF, cacheHsbKey & 0xFF, this.cacheHsbValue);
            this.cacheHsbKey = cacheHsbKey;
        }
        return this.cacheHsbValue[1] * this.colorMaxY;
    }
    
    public final float brightness(final int cacheHsbKey) {
        if (cacheHsbKey != this.cacheHsbKey) {
            Color.RGBtoHSB(cacheHsbKey >> 16 & 0xFF, cacheHsbKey >> 8 & 0xFF, cacheHsbKey & 0xFF, this.cacheHsbValue);
            this.cacheHsbKey = cacheHsbKey;
        }
        return this.cacheHsbValue[2] * this.colorMaxZ;
    }
    
    private static final int float_color(final float n, final float n2, final float n3) {
        return 0xFF000000 | (int)(255.0f * n) << 16 | (int)(255.0f * n2) << 8 | (int)(255.0f * n3);
    }
    
    public static final int _blend(final int n, final int n2, int n3) {
        n3 = n3 * (n2 >>> 24) >> 8;
        final int n4 = n3 ^ 0xFF;
        return 0xFF000000 | (n4 * (n >> 16 & 0xFF) + n3 * (n2 >> 16 & 0xFF) & 0xFF00) << 8 | (n4 * (n >> 8 & 0xFF) + n3 * (n2 >> 8 & 0xFF) & 0xFF00) | n4 * (n & 0xFF) + n3 * (n2 & 0xFF) >> 8;
    }
    
    private final float mag(final float n, final float n2) {
        return (float)Math.sqrt(n * n + n2 * n2);
    }
    
    private final float mag(final float n, final float n2, final float n3) {
        return (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    private final float sq(final float n) {
        return n * n;
    }
    
    private final float sqrt(final float n) {
        return (float)Math.sqrt(n);
    }
    
    private final float abs(final float n) {
        return (n < 0.0f) ? (-n) : n;
    }
    
    private final float sin(final float n) {
        return (float)Math.sin(n);
    }
    
    private final float cos(final float n) {
        return (float)Math.cos(n);
    }
    
    private final float tan(final float n) {
        return (float)Math.tan(n);
    }
    
    private final /* synthetic */ void this() {
        this.cacheHsbValue = new float[3];
        this.matrixStack = new float[32][16];
        this.TPOLYGON_MAX_VERTICES = 512;
        this.vertices = new float[512][24];
        this.vertex_order = new int[512];
        this.lines = new int[512][4];
        this.triangles = new int[256][5];
        this.clip = true;
        this.z_order = true;
        this.frustum = new float[6][4];
        this.cp = new float[16];
        this.texture_mode = 1;
        this.textures = new BImage[3];
        this.cvertex = new float[128][24];
        this.image_mode = 0;
        this.rect_mode = 0;
        this.ellipse_mode = 0;
        this.sphere_detail = 0;
        this.text_mode = 1;
        this.text_space = 3;
        this.drawing_text = false;
        this.bezier_inited = false;
        this.bezier_segments = 20;
        final float[][] bezier_basis = { { -1.0f, 3, -3.0f, 1.0f }, { 3, -6.0f, 3, 0.0f }, { -3.0f, 3, 0.0f, 0.0f }, null };
        final int n = 3;
        final float[] array = new float[4];
        array[0] = 1.0f;
        bezier_basis[n] = array;
        this.bezier_basis = bezier_basis;
        this.curve_inited = false;
        this.curve_segments = 20;
        this.curve_tightness = 0.0f;
    }
    
    public BGraphics() {
        this.this();
    }
    
    public BGraphics(final int n, final int n2) {
        this.this();
        this.resize(n, n2);
        this.defaults();
    }
    
    static {
        BGraphics.hints = new boolean[5];
        sinLUT = new float[720];
        cosLUT = new float[720];
        for (int i = 0; i < 720; ++i) {
            BGraphics.sinLUT[i] = (float)Math.sin(i * 0.017453292f * 0.5f);
            BGraphics.cosLUT[i] = (float)Math.cos(i * 0.017453292f * 0.5f);
        }
    }
}
