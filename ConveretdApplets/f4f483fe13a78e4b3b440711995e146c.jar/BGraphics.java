import java.awt.Color;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BGraphics implements BConstants
{
    static final int MATRIX_STACK_DEPTH = 32;
    static final int CVERTEX_ALLOC = 128;
    static final int circleAccuracy = 30;
    static final int textureOrientation = 1;
    static final int sphereAccuracy = 30;
    Applet applet;
    int width;
    int height;
    int width1;
    int height1;
    int pixelCount;
    public int[] pixels;
    boolean depthTest;
    float[] zbuffer;
    int frameCount;
    int _colorMode;
    boolean colorScale;
    float colorMaxX;
    float colorMaxY;
    float colorMaxZ;
    float colorMaxA;
    boolean _fill;
    boolean fillAlpha;
    float fillR;
    float fillG;
    float fillB;
    float fillA;
    int fillRi;
    int fillGi;
    int fillBi;
    int fillAi;
    int filli;
    boolean _stroke;
    boolean strokeAlpha;
    float strokeR;
    float strokeG;
    float strokeB;
    float strokeA;
    int strokeRi;
    int strokeGi;
    int strokeBi;
    int strokeAi;
    int strokei;
    float _strokeWidth;
    int _strokeMode;
    boolean _background;
    float backR;
    float backG;
    float backB;
    int backRi;
    int backGi;
    int backBi;
    int backi;
    float calcR;
    float calcG;
    float calcB;
    float calcA;
    int calcRi;
    int calcGi;
    int calcBi;
    int calcAi;
    int calci;
    boolean calcAlpha;
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
    boolean smooth;
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
    int _cameraMode;
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
    BPolygon3 polygon;
    BPolygon3 fpolygon;
    BPolygon3 spolygon;
    float[][] svertices;
    boolean texture;
    float textureU;
    float textureV;
    float normalX;
    float normalY;
    float normalZ;
    boolean unchangedZ;
    boolean strokeChanged;
    boolean fillChanged;
    boolean normalChanged;
    float[][] cvertex;
    int cvertexIndex;
    boolean cverticesFlat;
    float[] circleX;
    float[] circleY;
    int image_mode;
    int rect_mode;
    int ellipse_mode;
    float[][] sphereX;
    float[] sphereY;
    float[][] sphereZ;
    int text_mode;
    BFont currentFont;
    boolean[] hints;
    
    public void defaults() {
        this.frameCount = 0;
        this.depthTest = true;
        this.colorMode(1, 255.0f);
        this.fill(255.0f);
        this.stroke(0);
        this.strokeWidth(1.0f);
        this.background(204);
        this._cameraMode = -1;
        this.shape = false;
        this.shapeKind = 0;
        this.polygon = new BPolygon3(this);
        this.fpolygon = new BPolygon3(this);
        this.spolygon = new BPolygon3(this);
        this.spolygon.vertexCount = 4;
        this.svertices = new float[2][];
        this.currentFont = null;
        this.noLights();
        this.hints = new boolean[3];
        for (int i = 0; i < 3; ++i) {
            this.hints[i] = false;
        }
    }
    
    public void beginFrame() {
        if (this._cameraMode == -1) {
            this.beginCamera();
            this.perspective(this.fov, this.aspect, this.nearDist, this.farDist);
            this.lookat(this.eyeX, this.eyeY, this.eyeDist, this.eyeX, this.eyeY, 0.0f, 0.0f, 1.0f, 0.0f);
            this.endCamera();
            this._cameraMode = 3;
        }
        if (this._background) {
            this.clear();
        }
        this.resetMatrix();
        this.normalX = 0.0f;
        this.normalY = 0.0f;
        this.normalZ = 1.0f;
    }
    
    public void clear() {
        for (int i = 0; i < this.pixelCount; ++i) {
            this.pixels[i] = this.backi;
            this.zbuffer[i] = Float.MAX_VALUE;
        }
    }
    
    public void endFrame() {
        ++this.frameCount;
    }
    
    private void calc_color(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, float n7, float n8, float n9, final float[] array, final int n10) {
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
        this.cvertexIndex = 0;
        this.cverticesFlat = true;
        this.polygon.reset(0);
        this.fpolygon.reset(4);
        this.spolygon.reset(4);
        this.texture = false;
        this.polygon.interpUV = false;
        this.unchangedZ = true;
        this.strokeChanged = false;
        this.fillChanged = false;
        this.normalChanged = false;
    }
    
    public void textureImage(final BImage bImage) {
        this.texture = true;
        this.polygon.texture(bImage);
    }
    
    public void vertexTexture(final float n, final float n2) {
        if (!this.texture) {
            this.message(2, "gotta use textureImage() after beginShape() and before vertexTexture()");
            return;
        }
        this.textureU = ((n < this.polygon.twidth1) ? n : this.polygon.twidth1);
        if (this.textureU < 0.0f) {
            this.textureU = 0.0f;
        }
        this.textureV = ((n2 < this.polygon.theight1) ? n2 : this.polygon.theight1);
        if (this.textureV < 0.0f) {
            this.textureV = 0.0f;
        }
    }
    
    public void vertexNormal(final float normalX, final float normalY, final float normalZ) {
        if (!this.normalChanged) {
            for (int i = 0; i < this.polygon.vertexCount; ++i) {
                this.polygon.vertices[i][16] = this.normalX;
                this.polygon.vertices[i][17] = this.normalY;
                this.polygon.vertices[i][18] = this.normalZ;
            }
            this.normalChanged = true;
        }
        this.normalChanged = true;
        this.normalX = normalX;
        this.normalY = normalY;
        this.normalZ = normalZ;
    }
    
    public void vertex(final float n, final float n2) {
        this.setup_vertex(this.polygon.nextVertex(), n, n2, 0.0f);
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        this.unchangedZ = false;
        this.dimensions = 3;
        this.setup_vertex(this.polygon.nextVertex(), n, n2, n3);
    }
    
    private void setup_vertex(final float[] array, final float n, final float n2, final float n3) {
        this.cvertexIndex = 0;
        array[9] = n;
        array[10] = n2;
        array[11] = n3;
        if (this._stroke) {
            array[12] = this.strokeR;
            array[13] = this.strokeG;
            array[14] = this.strokeB;
            array[15] = this.strokeA;
        }
        if (this._fill) {
            array[3] = this.fillR;
            array[4] = this.fillG;
            array[5] = this.fillB;
            array[6] = this.fillA;
        }
        if (this.texture) {
            array[7] = this.textureU;
            array[8] = this.textureV;
        }
        if (this.normalChanged) {
            array[16] = this.normalX;
            array[17] = this.normalY;
            array[18] = this.normalZ;
        }
    }
    
    private void c_vertex(final float n, final float n2, final float n3, final boolean b) {
        if (this.cvertexIndex == 128) {
            System.arraycopy(this.cvertex[125], 0, this.cvertex[0], 0, 19);
            System.arraycopy(this.cvertex[126], 0, this.cvertex[1], 0, 19);
            System.arraycopy(this.cvertex[127], 0, this.cvertex[2], 0, 19);
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
                if (this.cverticesFlat) {
                    this.bezier_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10]);
                }
                else {
                    this.bezier_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 4][11], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 3][11], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 2][11], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10], this.cvertex[this.cvertexIndex - 1][11]);
                }
            }
            else if (this.cverticesFlat) {
                this.curve_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10]);
            }
            else {
                this.curve_segment(this.cvertex[this.cvertexIndex - 4][9], this.cvertex[this.cvertexIndex - 4][10], this.cvertex[this.cvertexIndex - 4][11], this.cvertex[this.cvertexIndex - 3][9], this.cvertex[this.cvertexIndex - 3][10], this.cvertex[this.cvertexIndex - 3][11], this.cvertex[this.cvertexIndex - 2][9], this.cvertex[this.cvertexIndex - 2][10], this.cvertex[this.cvertexIndex - 2][11], this.cvertex[this.cvertexIndex - 1][9], this.cvertex[this.cvertexIndex - 1][10], this.cvertex[this.cvertexIndex - 1][11]);
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
    
    public void endShape() {
        this.shape = false;
        int vertexCount = this.polygon.vertexCount;
        final float[][] vertices = this.polygon.vertices;
        if (this._cameraMode == 3 && this.dimensions == 0) {
            this.polygon.interpZ = false;
            this.spolygon.interpZ = false;
            for (int i = 0; i < vertexCount; ++i) {
                vertices[i][0] = vertices[i][9];
                vertices[i][1] = vertices[i][10];
            }
        }
        else if (this._cameraMode == 3 && this.dimensions == 2) {
            this.polygon.interpZ = false;
            this.spolygon.interpZ = false;
            for (int j = 0; j < vertexCount; ++j) {
                vertices[j][0] = this.m00 * vertices[j][9] + this.m01 * vertices[j][10] + this.m03;
                vertices[j][1] = this.m10 * vertices[j][9] + this.m11 * vertices[j][10] + this.m13;
            }
        }
        else if (this._cameraMode == 2) {
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
        for (int n10 = 0; n10 < (this.normalChanged ? vertexCount : true); ++n10) {
            final float[] array3 = vertices[n10];
            final float n11 = this.m00 * array3[16] + this.m01 * array3[17] + this.m02 * array3[18] + this.m03;
            final float n12 = this.m10 * array3[16] + this.m11 * array3[17] + this.m12 * array3[18] + this.m13;
            final float n13 = this.m20 * array3[16] + this.m21 * array3[17] + this.m22 * array3[18] + this.m23;
            final float n14 = this.m30 * array3[16] + this.m31 * array3[17] + this.m32 * array3[18] + this.m33;
            if (n14 != 0.0f) {
                array3[16] = n11 / n14;
                array3[17] = n12 / n14;
                array3[18] = n13 / n14;
            }
            else {
                array3[16] = n11;
                array3[17] = n12;
                array3[18] = n13;
            }
            final float mag = this.mag(array3[16], array3[17], array3[18]);
            if (mag != 0.0f) {
                final float[] array4 = array3;
                final int n15 = 16;
                array4[n15] /= mag;
                final float[] array5 = array3;
                final int n16 = 17;
                array5[n16] /= mag;
                final float[] array6 = array3;
                final int n17 = 18;
                array6[n17] /= mag;
            }
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
            for (int n18 = 0; n18 < vertexCount; ++n18) {
                final float[] array8 = this.polygon.vertices[n18];
                if (this.normalChanged) {
                    if (this._fill) {
                        this.calc_color(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 3);
                    }
                    if (this._stroke) {
                        this.calc_color(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 12);
                    }
                }
                else {
                    if (this._fill) {
                        this.calc_color(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 3);
                    }
                    if (this._stroke) {
                        this.calc_color(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 12);
                    }
                }
            }
        }
        switch (this.shapeKind) {
            case 16: {
                if (this.dimensions == 0 && this.unchangedZ && this._strokeWidth == 1.0f && !this.lighting) {
                    if (!this.strokeChanged) {
                        for (int n19 = 0; n19 < vertexCount; ++n19) {
                            this.thin_point((int)vertices[n19][0], (int)vertices[n19][1], 0.0f, this.strokei);
                        }
                    }
                    else {
                        for (int n20 = 0; n20 < vertexCount; ++n20) {
                            this.thin_point((int)vertices[n20][0], (int)vertices[n20][1], 0.0f, float_color(vertices[n20][12], vertices[n20][13], vertices[n20][14]));
                        }
                    }
                }
                else {
                    final float[] array9 = vertices[0];
                    for (int n21 = 0; n21 < vertexCount; ++n21) {
                        final float[] array10 = vertices[n21];
                        if (n21 == 0 || this.lighting || this.strokeChanged) {
                            this.calc_color(array10[12], array10[13], array10[14], array10[0], array10[1], array10[2], array10[16], array10[17], array10[18], array9, 3);
                        }
                        this.thick_point(array10[0], array10[1], array10[2], array9[3], array9[4], array9[5], array9[6]);
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
                this.draw_lines(vertices, vertexCount - 1, 1, (this.shapeKind == 32) ? 2 : 1, 0);
                break;
            }
            case 64:
            case 65: {
                final int n22 = (this.shapeKind == 64) ? 3 : 1;
                if (this._fill) {
                    this.fpolygon.vertexCount = 3;
                    for (int n23 = 0; n23 < vertexCount - 2; n23 += n22) {
                        for (int n24 = 0; n24 < 3; ++n24) {
                            this.fpolygon.vertices[n24][3] = vertices[n23 + n24][3];
                            this.fpolygon.vertices[n24][4] = vertices[n23 + n24][4];
                            this.fpolygon.vertices[n24][5] = vertices[n23 + n24][5];
                            this.fpolygon.vertices[n24][6] = vertices[n23 + n24][6];
                            this.fpolygon.vertices[n24][0] = vertices[n23 + n24][0];
                            this.fpolygon.vertices[n24][1] = vertices[n23 + n24][1];
                            this.fpolygon.vertices[n24][2] = vertices[n23 + n24][2];
                            if (this.polygon.interpUV) {
                                this.fpolygon.vertices[n24][7] = vertices[n23 + n24][7];
                                this.fpolygon.vertices[n24][8] = vertices[n23 + n24][8];
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
                    this.draw_lines(vertices, vertexCount - 2, 2, n22, 0);
                }
                break;
            }
            case 128:
            case 129: {
                final int n25 = (this.shapeKind == 128) ? 4 : 2;
                if (this._fill) {
                    this.fpolygon.vertexCount = 4;
                    for (int n26 = 0; n26 < vertexCount - 3; n26 += n25) {
                        for (int n27 = 0; n27 < 4; ++n27) {
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
                    if (this.shapeKind == 129) {
                        this.draw_lines(vertices, vertexCount - 1, 1, 1, 0);
                    }
                    else {
                        this.draw_lines(vertices, vertexCount, 1, 1, 4);
                    }
                    this.draw_lines(vertices, vertexCount - 2, 3, n25, 0);
                }
                break;
            }
            case 256:
            case 257:
            case 258: {
                if (this._fill) {
                    this.polygon.render();
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
    
    private void thin_point(final int n, final int n2, final float n3, final int n4) {
        if (n < 0 || n > this.width1 || n2 < 0 || n2 > this.height1) {
            return;
        }
        final int n5 = n2 * this.width + n;
        this.pixels[n5] = n4;
        this.zbuffer[n5] = n3;
    }
    
    private void thick_point(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        this.spolygon.reset(4);
        this.spolygon.interpRGBA = false;
        final float n8 = this._strokeWidth / 2.0f;
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
    
    private void thin_flat_line(int n, int n2, int n3, int n4) {
        if (n < 0 || n2 < 0 || n3 > this.width1 || n4 > this.height1) {
            float n5 = n;
            float n6 = n2;
            float n7 = n3;
            float n8 = n4;
            int n9 = this.thin_flat_lineClipCode(n, n2);
            int n10 = this.thin_flat_lineClipCode(n3, n4);
            while ((n9 | n10) != 0x0) {
                if ((n9 & n10) != 0x0) {
                    return;
                }
                final float n11 = n7 - n5;
                final float n12 = n8 - n6;
                if (n9 != 0) {
                    if ((n9 & 0x8) == 0x8) {
                        n6 += (0.0f - n5) * n12 / n11;
                        n5 = 0.0f;
                    }
                    else if ((n9 & 0x4) == 0x4) {
                        n6 += (this.width1 - n5) * n12 / n11;
                        n5 = this.width1;
                    }
                    else if ((n9 & 0x2) == 0x2) {
                        n5 += (0.0f - n6) * n11 / n12;
                        n6 = 0.0f;
                    }
                    else if ((n9 & 0x1) == 0x1) {
                        n5 += (this.height1 - n6) * n11 / n12;
                        n6 = this.height1;
                    }
                    n9 = this.thin_flat_lineClipCode(n5, n6);
                }
                else {
                    if (n10 == 0) {
                        continue;
                    }
                    if ((n10 & 0x8) == 0x8) {
                        n8 += (0.0f - n7) * n12 / n11;
                        n7 = 0.0f;
                    }
                    else if ((n10 & 0x4) == 0x4) {
                        n8 += (this.width1 - n7) * n12 / n11;
                        n7 = this.width1;
                    }
                    else if ((n10 & 0x2) == 0x2) {
                        n7 += (0.0f - n8) * n11 / n12;
                        n8 = 0.0f;
                    }
                    else if ((n10 & 0x1) == 0x1) {
                        n7 += (this.height1 - n8) * n11 / n12;
                        n8 = this.height1;
                    }
                    n10 = this.thin_flat_lineClipCode(n7, n8);
                }
            }
            n = (int)n5;
            n2 = (int)n6;
            n3 = (int)n7;
            n4 = (int)n8;
        }
        int n13 = 0;
        int n14 = 0;
        int n15 = 0;
        int n16 = 0;
        if (n == n3) {
            final int n17 = n;
            int n18;
            int n19;
            if (n2 < n4) {
                n18 = n2;
                n19 = n4;
            }
            else {
                n18 = n4;
                n19 = n2;
            }
            for (int i = n18; i <= n19; ++i) {
                this.thin_point(n17, i, 0.0f, this.strokei);
            }
            return;
        }
        final float n20 = (n4 - n2) / (n3 - n);
        int n21;
        if (n20 >= 0.0f && n20 <= 1.0f) {
            n21 = 1;
        }
        else if (n20 > 1.0f) {
            n21 = 2;
        }
        else if (n20 < -1.0f) {
            n21 = 3;
        }
        else {
            n21 = 4;
        }
        int n22;
        int n23;
        int n24;
        int n25;
        if (((n21 == 1 || n21 == 2 || n21 == 4) && n > n3) || (n21 == 3 && n < n3)) {
            n22 = n3;
            n23 = n;
            n24 = n4;
            n25 = n2;
        }
        else {
            n22 = n;
            n24 = n2;
            n23 = n3;
            n25 = n4;
        }
        final int n26 = n23 - n22;
        final int n27 = n25 - n24;
        int n28;
        int n29;
        int n30;
        if (n21 == 1) {
            n13 = n26;
            n28 = 2 * n27 - n26;
            n15 = 1;
            n29 = 2 * n27;
            n30 = 2 * (n27 - n26);
        }
        else if (n21 == 2) {
            n14 = n27;
            n28 = 2 * n26 - n27;
            n16 = 1;
            n29 = 2 * n26;
            n30 = 2 * (n26 - n27);
        }
        else if (n21 == 3) {
            n14 = -n27;
            n28 = -2 * n26 - n27;
            n16 = -1;
            n29 = -2 * n26;
            n30 = -2 * (n26 + n27);
        }
        else {
            n13 = -n26;
            n28 = -2 * n27 - n26;
            n15 = -1;
            n29 = -2 * n27;
            n30 = -2 * (n27 + n26);
        }
        if (n21 == 1 || n21 == 4) {
            final float n31 = 1.0f / (2.0f * this.sqrt(n26 * n26 + n27 * n27));
            final float n32 = 2 * n13 * n31;
            int j = n22;
            int n33 = n24;
            this.thin_point(j, n33, 0.0f, this.strokei);
            while (j < n23) {
                int n34;
                if (n28 < 0) {
                    n34 = n28 + n26;
                    n28 += n29;
                    ++j;
                }
                else {
                    n34 = n28 - n26;
                    n28 += n30;
                    ++j;
                    n33 += n15;
                }
                final float n35 = n34 * n31;
                this.thin_point(j, n33, 0.0f, this.strokei);
            }
        }
        else {
            final float n36 = 1.0f / (2.0f * this.sqrt(n26 * n26 + n27 * n27));
            final float n37 = 2 * n14 * n36;
            int n38 = n22;
            int k = n24;
            this.thin_point(n38, k, 0.0f, this.strokei);
            while (k < n25) {
                int n39;
                if (n28 < 0) {
                    n39 = n28 + n27;
                    n28 += n29;
                    ++k;
                }
                else {
                    n39 = n28 - n27;
                    n28 += n30;
                    ++k;
                    n38 += n16;
                }
                final float n40 = n39 * n36;
                this.thin_point(n38, k, 0.0f, this.strokei);
            }
        }
    }
    
    private int thin_flat_lineClipCode(final float n, final float n2) {
        return ((n < 0.0f) ? 8 : 0) | ((n > this.width1) ? 4 : 0) | ((n2 < 0.0f) ? 2 : 0) | ((n2 > this.height1) ? 1 : 0);
    }
    
    private boolean flat_line_retribution(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        final float n8 = this.m00 * this._strokeWidth + this.m01 * this._strokeWidth;
        if (this._strokeWidth < 2.0f && !this.hints[1]) {
            final int strokei = this.strokei;
            this.strokei = float_color(n5, n6, n7);
            this.thin_flat_line((int)n, (int)n2, (int)n3, (int)n4);
            this.strokei = strokei;
            return true;
        }
        return false;
    }
    
    private void thick_flat_line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.spolygon.interpRGBA = (n3 != n9 || n4 != n10 || n5 != n11 || n6 != n12);
        this.spolygon.interpZ = false;
        if (!this.spolygon.interpRGBA && this.flat_line_retribution(n, n2, n7, n8, n3, n4, n5)) {
            return;
        }
        final float n13 = n7 - n + 1.0E-4f;
        final float n14 = n8 - n2 + 1.0E-4f;
        final float n15 = this._strokeWidth / this.sqrt(n13 * n13 + n14 * n14);
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
    
    private void spatial_line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10) {
        this.spatial_line(n, n2, 0.0f, n3, n4, n5, n6, n7, 0.0f, n8, n9, n10);
    }
    
    private void spatial_line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.spolygon.interpRGBA = (n4 != n10 || n5 != n11 || n6 != n12);
        if (!this.spolygon.interpRGBA && this.flat_line_retribution(n, n2, n7, n8, n4, n5, n6)) {
            return;
        }
        this.spolygon.interpZ = true;
        final float n13 = n7 - n + 1.0E-4f;
        final float n14 = n8 - n2 + 1.0E-4f;
        final float n15 = this._strokeWidth / this.sqrt(n13 * n13 + n14 * n14);
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
    
    private void draw_lines(final float[][] array, final int n, final int n2, final int n3, final int n4) {
        if (this.dimensions != 3 && this.unchangedZ) {
            if (this._strokeWidth < 2.0f && !this.lighting && !this.strokeChanged) {
                for (int i = 0; i < n; i += n3) {
                    if (n4 == 0 || (i + n2) % n4 != 0) {
                        this.thin_flat_line((int)array[i][0], (int)array[i][1], (int)array[i + n2][0], (int)array[i + n2][1]);
                    }
                }
            }
            else {
                for (int j = 0; j < n; j += n3) {
                    if (n4 == 0 || (j + n2) % n4 != 0) {
                        final float[] array2 = array[j];
                        final float[] array3 = array[j + n2];
                        this.thick_flat_line(array2[0], array2[1], array2[12], array2[13], array2[14], array2[15], array3[0], array3[1], array3[12], array3[13], array3[14], array3[15]);
                    }
                }
            }
        }
        else {
            for (int k = 0; k < n; k += n3) {
                if (n4 == 0 || (k + n2) % n4 != 0) {
                    final float[] array4 = array[k];
                    final float[] array5 = array[k + n2];
                    this.spatial_line(array4[0], array4[1], array4[2], array4[12], array4[13], array4[14], array5[0], array5[1], array5[2], array5[12], array5[13], array5[14]);
                }
            }
        }
    }
    
    private void flat_rect(int n, int n2, int n3, int n4) {
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
            int n9 = n8 * this.width;
            for (int i = n8; i < height; ++i) {
                for (int j = n7; j < width; ++j) {
                    this.pixels[n9 + j] = this.filli;
                }
                n9 += this.width;
            }
        }
        if (this._stroke) {
            if (this._strokeWidth == 1.0f) {
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
    
    private void flat_circle(final int n, final int n2, final int n3) {
        int i = 0;
        int n4 = n3;
        int n5 = 1;
        int n6 = 2 * n3 - 1;
        int n7 = 0;
        while (i < n4) {
            this.thin_point(n + i, n2 + n4, 0.0f, this.strokei);
            this.thin_point(n + n4, n2 - i, 0.0f, this.strokei);
            this.thin_point(n - i, n2 - n4, 0.0f, this.strokei);
            this.thin_point(n - n4, n2 + i, 0.0f, this.strokei);
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
            this.thin_point(n + n4, n2 + i, 0.0f, this.strokei);
            this.thin_point(n + i, n2 - n4, 0.0f, this.strokei);
            this.thin_point(n - n4, n2 - i, 0.0f, this.strokei);
            this.thin_point(n - i, n2 + n4, 0.0f, this.strokei);
        }
    }
    
    private void flat_ellipse(final int n, final int n2, final int n3, final int n4) {
    }
    
    private void bezier_segment(float n, float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        float n9 = -0.142625f * n + 0.135375f * n3 + 0.007125f * n5 + 1.25E-4f * n7;
        float n10 = 0.01425f * n - 0.02775f * n3 + 0.01275f * n5 + 7.5E-4f * n7;
        final float n11 = -7.5E-4f * n + 0.00225f * n3 - 0.00225f * n5 + 7.5E-4f * n7;
        float n12 = -0.142625f * n2 + 0.135375f * n4 + 0.007125f * n6 + 1.25E-4f * n8;
        float n13 = 0.01425f * n2 - 0.02775f * n4 + 0.01275f * n6 + 7.5E-4f * n8;
        final float n14 = -7.5E-4f * n2 + 0.00225f * n4 - 0.00225f * n6 + 7.5E-4f * n8;
        this.vertex(n, n2);
        for (int i = 0; i < 20; ++i) {
            n += n9;
            n9 += n10;
            n10 += n11;
            n2 += n12;
            n12 += n13;
            n13 += n14;
            this.vertex(n, n2);
        }
    }
    
    private void bezier_segment(float n, float n2, float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        float n13 = -0.142625f * n + 0.135375f * n4 + 0.007125f * n7 + 1.25E-4f * n10;
        float n14 = 0.01425f * n - 0.02775f * n4 + 0.01275f * n7 + 7.5E-4f * n10;
        final float n15 = -7.5E-4f * n + 0.00225f * n4 - 0.00225f * n7 + 7.5E-4f * n10;
        float n16 = -0.142625f * n2 + 0.135375f * n5 + 0.007125f * n8 + 1.25E-4f * n11;
        float n17 = 0.01425f * n2 - 0.02775f * n5 + 0.01275f * n8 + 7.5E-4f * n11;
        final float n18 = -7.5E-4f * n2 + 0.00225f * n5 - 0.00225f * n8 + 7.5E-4f * n11;
        float n19 = -0.142625f * n3 + 0.135375f * n6 + 0.007125f * n9 + 1.25E-4f * n12;
        float n20 = 0.01425f * n3 - 0.02775f * n6 + 0.01275f * n9 + 7.5E-4f * n12;
        final float n21 = -7.5E-4f * n3 + 0.00225f * n6 - 0.00225f * n9 + 7.5E-4f * n12;
        this.vertex(n, n2, n3);
        for (int i = 0; i < 20; ++i) {
            n += n13;
            n13 += n14;
            n14 += n15;
            n2 += n16;
            n16 += n17;
            n17 += n18;
            n3 += n19;
            n19 += n20;
            n20 += n21;
            this.vertex(n, n2, n3);
        }
    }
    
    private void curve_segment(final float n, final float n2, float n3, float n4, final float n5, final float n6, final float n7, final float n8) {
        float n9 = -0.022562502f * n + -0.006062496f * n3 + 0.029812502f * n5 + -0.0011875001f * n7;
        float n10 = 0.0046250005f * n + -0.011375001f * n3 + 0.008874999f * n5 + -0.002125f * n7;
        final float n11 = -3.7500003E-4f * n + 0.001125f * n3 + -0.001125f * n5 + 3.7500003E-4f * n7;
        float n12 = -0.022562502f * n2 + -0.006062496f * n4 + 0.029812502f * n6 + -0.0011875001f * n8;
        float n13 = 0.0046250005f * n2 + -0.011375001f * n4 + 0.008874999f * n6 + -0.002125f * n8;
        final float n14 = -3.7500003E-4f * n2 + 0.001125f * n4 + -0.001125f * n6 + 3.7500003E-4f * n8;
        this.vertex(n3, n4);
        for (int i = 0; i < 20; ++i) {
            n3 += n9;
            n9 += n10;
            n10 += n11;
            n4 += n12;
            n12 += n13;
            n13 += n14;
            this.vertex(n3, n4);
        }
    }
    
    private void curve_segment(final float n, final float n2, final float n3, float n4, float n5, float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        float n13 = -0.022562502f * n + -0.006062496f * n4 + 0.029812502f * n7 + -0.0011875001f * n10;
        float n14 = 0.0046250005f * n + -0.011375001f * n4 + 0.008874999f * n7 + -0.002125f * n10;
        final float n15 = -3.7500003E-4f * n + 0.001125f * n4 + -0.001125f * n7 + 3.7500003E-4f * n10;
        float n16 = -0.022562502f * n2 + -0.006062496f * n5 + 0.029812502f * n8 + -0.0011875001f * n11;
        float n17 = 0.0046250005f * n2 + -0.011375001f * n5 + 0.008874999f * n8 + -0.002125f * n11;
        final float n18 = -3.7500003E-4f * n2 + 0.001125f * n5 + -0.001125f * n8 + 3.7500003E-4f * n11;
        float n19 = -0.022562502f * n3 + -0.006062496f * n6 + 0.029812502f * n9 + -0.0011875001f * n12;
        float n20 = 0.0046250005f * n3 + -0.011375001f * n6 + 0.008874999f * n9 + -0.002125f * n12;
        final float n21 = -3.7500003E-4f * n3 + 0.001125f * n6 + -0.001125f * n9 + 3.7500003E-4f * n12;
        this.vertex(n4, n5, n6);
        for (int i = 0; i < 20; ++i) {
            n4 += n13;
            n13 += n14;
            n14 += n15;
            n5 += n16;
            n16 += n17;
            n17 += n18;
            n6 += n19;
            n19 += n20;
            n20 += n21;
            this.vertex(n4, n5, n6);
        }
    }
    
    public void simage(final BImage bImage, int n, int n2) {
        int n3 = 0;
        int n4 = 0;
        final int width = bImage.width;
        final int height = bImage.height;
        int width2 = n + bImage.width;
        int height2 = n2 + bImage.height;
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
        if (bImage.format == 4) {
            for (int i = n2; i < height2; ++i) {
                for (int j = n; j < width2; ++j) {
                    this.pixels[n8 + j] = blend(this.pixels[n8 + j], this.filli, bImage.pixels[n7 + (j - n)]);
                }
                n7 += bImage.width;
                n8 += this.width;
            }
        }
        else if (bImage.format == 1) {
            for (int k = n2; k < height2; ++k) {
                System.arraycopy(bImage.pixels, n7, this.pixels, n8 + n, width2 - n);
                n7 += bImage.width;
                n8 += this.width;
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
        if (this.dimensions == 0 && !this.lighting && !this.fillAlpha) {
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
        if (this.dimensions != 0 || this.lighting || n3 == n4) {}
        if (this.dimensions != 0 || !this.lighting) {}
        if (this.circleX == null) {
            this.circleX = new float[30];
            this.circleY = new float[30];
            final float n5 = 0.033333335f;
            float n6 = 0.0f;
            for (int i = 0; i < 30; ++i) {
                this.circleX[i] = this.cos(6.2831855f * n6);
                this.circleY[i] = this.sin(6.2831855f * n6);
                n6 += n5;
            }
        }
        this.beginShape(256);
        for (int j = 0; j < 30; ++j) {
            this.vertex(n + this.circleX[j] * n3, n2 + this.circleY[j] * n4);
        }
        this.vertex(n + this.circleX[0] * n3, n2 + this.circleY[0] * n4);
        this.endShape();
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
        this.beginShape(129);
        this.vertex(n4, n6, n9);
        this.vertex(n4, n6, n8);
        this.vertex(n5, n6, n8);
        this.vertex(n5, n6, n9);
        this.vertex(n5, n7, n9);
        this.vertex(n5, n7, n8);
        this.vertex(n4, n7, n8);
        this.vertex(n4, n7, n9);
        this.vertex(n4, n6, n9);
        this.vertex(n4, n6, n8);
        this.endShape();
        this.beginShape(128);
        this.vertex(n5, n6, n8);
        this.vertex(n4, n6, n8);
        this.vertex(n4, n7, n8);
        this.vertex(n5, n7, n8);
        this.vertex(n4, n6, n9);
        this.vertex(n5, n6, n9);
        this.vertex(n5, n7, n9);
        this.vertex(n4, n7, n9);
        this.endShape();
    }
    
    public void sphere(final float n) {
        if (this.sphereX == null) {
            final float[] array = new float[30];
            final float[] array2 = new float[30];
            final float n2 = 0.03448276f;
            float n3 = 0.0f;
            for (int i = 0; i < 30; ++i) {
                array[i] = this.cos(6.2831855f * n3);
                array2[i] = this.sin(6.2831855f * n3);
                n3 += n2;
            }
            this.sphereX = new float[30][30];
            this.sphereY = new float[30];
            this.sphereZ = new float[30][30];
            float n4 = 0.0f;
            final float n5 = 0.03448276f;
            for (int j = 0; j < 30; ++j) {
                this.sphereY[j] = this.sin(6.2831855f * n4);
                final float cos = this.cos(6.2831855f * n4);
                for (int k = 0; k < 30; ++k) {
                    this.sphereX[j][k] = array[k] * cos;
                    this.sphereZ[j][k] = array2[k] * cos;
                }
                n4 += n5;
            }
        }
        for (int l = 1; l < 29; ++l) {
            this.beginShape(65);
            for (int n6 = 0; n6 < 30; ++n6) {
                this.vertex(this.sphereX[l][n6] * n, this.sphereY[l] * n, this.sphereZ[l][n6] * n);
                this.vertex(this.sphereX[l + 1][n6] * n, this.sphereY[l + 1] * n, this.sphereZ[l + 1][n6] * n);
            }
            this.vertex(this.sphereX[l][0] * n, this.sphereY[l] * n, this.sphereZ[l][0] * n);
            this.vertex(this.sphereX[l + 1][0] * n, this.sphereY[l + 1] * n, this.sphereZ[l + 1][0] * n);
            this.endShape();
        }
    }
    
    public void potato() {
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
    
    private Image pissImage(final URL url, final boolean b) {
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
                image = this.pissImage(new URL(s), b);
            }
            catch (MalformedURLException ex) {
                System.err.println("error loading image from " + s);
                ex.printStackTrace();
                return null;
            }
        }
        else {
            image = this.pissImage(this.getClass().getResource(s), b);
            if (image == null) {
                this.pissImage(this.getClass().getResource("data/" + s), b);
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
        return new BImage(array, width, height, 1);
    }
    
    public void imageMode(final int image_mode) {
        this.image_mode = image_mode;
    }
    
    public void image(final BImage bImage, final float n, final float n2) {
        if (this.dimensions == 0 && !this.lighting && this.image_mode == 3 && this.image_mode == 2) {
            this.simage(bImage, (int)n, (int)n2);
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
        this._stroke = false;
        this._fill = true;
        this.beginShape(128);
        this.textureImage(bImage);
        this.vertexTexture(n5, n6);
        this.vertex(n, n2);
        this.vertexTexture(n5, n8);
        this.vertex(n, n4);
        this.vertexTexture(n7, n8);
        this.vertex(n3, n4);
        this.vertexTexture(n7, n6);
        this.vertex(n3, n2);
        this.endShape();
        this._stroke = stroke;
        this._fill = fill;
    }
    
    public void cache(final BImage bImage) {
    }
    
    public void cache(final BImage[] array) {
    }
    
    protected void cache(final BImage bImage, final int n) {
    }
    
    public BFont loadFont(final String s) {
        final BFont bFont = new BFont(s, this);
        if (!bFont.valid) {
            return null;
        }
        return bFont;
    }
    
    public void setFont(final BFont currentFont) {
        (this.currentFont = currentFont).resetSize();
    }
    
    public void setFont(final BFont currentFont, final float size) {
        (this.currentFont = currentFont).setSize(size);
    }
    
    public void text(final char c, final float n, final float n2) {
        if (this.currentFont == null) {
            System.err.println("text(): first set a font before drawing text");
        }
        this.currentFont.drawChar(c, n, n2);
    }
    
    public void text(final String s, final float n, final float n2) {
        if (this.currentFont == null) {
            System.err.println("text(): first set a font before drawing text");
        }
        this.currentFont.drawString(s, n, n2);
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
    
    public void cameraMode(final int cameraMode) {
        this._cameraMode = cameraMode;
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
        if (this._cameraMode == 3 && this.dimensions == 0) {
            return n;
        }
        if (this._cameraMode == 3 && this.dimensions == 2) {
            return this.m00 * n + this.m01 * n2 + this.m03;
        }
        if (this._cameraMode == 2) {
            return n - n3;
        }
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
        if (this._cameraMode == 3 && this.dimensions == 0) {
            return n2;
        }
        if (this._cameraMode == 3 && this.dimensions == 2) {
            return this.m10 * n + this.m11 * n2 + this.m13;
        }
        if (this._cameraMode == 2) {
            return -n / 2.0f + n2 - n3 / 2.0f;
        }
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
        if (this._cameraMode == 3 && this.dimensions <= 2) {
            return 0.0f;
        }
        if (this._cameraMode == 2) {
            return n3;
        }
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
        if (this._cameraMode == 3 && this.dimensions == 0) {
            return n;
        }
        if (this._cameraMode == 3 && this.dimensions == 2) {
            return this.m00 * n + this.m01 * n2 + this.m03;
        }
        if (this._cameraMode == 2) {
            return n - n3;
        }
        final float n4 = this.m00 * n + this.m01 * n2 + this.m02 * n3 + this.m03;
        final float n5 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public float objectY(final float n, final float n2, final float n3) {
        if (this._cameraMode == 3 && this.dimensions == 0) {
            return n2;
        }
        if (this._cameraMode == 3 && this.dimensions == 2) {
            return this.m10 * n + this.m11 * n2 + this.m13;
        }
        if (this._cameraMode == 2) {
            return -n / 2.0f + n2 - n3 / 2.0f;
        }
        final float n4 = this.m10 * n + this.m11 * n2 + this.m12 * n3 + this.m13;
        final float n5 = this.m30 * n + this.m31 * n2 + this.m32 * n3 + this.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public float objectZ(final float n, final float n2, final float n3) {
        if (this._cameraMode == 3 && this.dimensions <= 2) {
            return 0.0f;
        }
        if (this._cameraMode == 2) {
            return n3;
        }
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
    
    public void shearX(final float n) {
        this.message(1, "shear not implemented");
    }
    
    public void shearY(final float n) {
        this.message(1, "shear not implemented");
    }
    
    public void rotate(final float n) {
        this.rotateZ(n);
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
    
    public void colorMode(final int colorMode) {
        this._colorMode = colorMode;
    }
    
    public void colorMode(final int n, final float n2) {
        this.colorMode(n, n2, n2, n2, n2);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4) {
        this.colorMode(n, n2, n3, n4, 1.0f);
    }
    
    public void colorMode(final int colorMode, final float colorMaxX, final float colorMaxY, final float colorMaxZ, final float colorMaxA) {
        this._colorMode = colorMode;
        this.colorMaxX = colorMaxX;
        this.colorMaxY = colorMaxY;
        this.colorMaxZ = colorMaxZ;
        this.colorMaxA = colorMaxA;
        this.colorScale = (colorMaxX != colorMaxY || colorMaxY != colorMaxZ || colorMaxZ != colorMaxA || colorMaxA != 1.0f);
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
        this.calcR = (this.colorScale ? (colorMaxX / this.colorMaxX) : colorMaxX);
        this.calcG = this.calcR;
        this.calcB = this.calcR;
        this.calcA = (this.colorScale ? (colorMaxA / this.colorMaxA) : colorMaxA);
        this.calcRi = (int)(this.calcR * 255.0f);
        this.calcGi = (int)(this.calcG * 255.0f);
        this.calcBi = (int)(this.calcB * 255.0f);
        this.calcAi = (int)(this.calcA * 255.0f);
        this.calci = (this.calcAi << 24 | this.calcRi << 16 | this.calcGi << 8 | this.calcBi);
        this.calcAlpha = (this.calcAi != 255);
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
        switch (this._colorMode) {
            case 1: {
                if (this.colorScale) {
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
                this.calcA = (this.colorScale ? (colorMaxA / this.colorMaxA) : colorMaxA);
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
        this.calcAlpha = (this.calcAi != 255);
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
        this.filli = this.calci;
        this.fillAlpha = this.calcAlpha;
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
        this.strokei = this.calci;
        this.strokeAlpha = this.calcAlpha;
    }
    
    protected void calc_background() {
        this._background = true;
        this.backR = this.calcR;
        this.backG = this.calcG;
        this.backB = this.calcB;
        this.backRi = this.calcRi;
        this.backGi = this.calcGi;
        this.backBi = this.calcBi;
        this.backi = this.calci;
    }
    
    public void noFill() {
        this._fill = false;
    }
    
    public void fill(final int calci) {
        if ((calci & 0xFF000000) == 0x0) {
            this.fill((float)calci);
        }
        else {
            this.calci = calci;
            this.calcRi = (calci >> 16 & 0xFF);
            this.calcGi = (calci >> 8 & 0xFF);
            this.calcBi = (calci & 0xFF);
            this.calcAi = 255;
            this.calcR = this.calcRi / 255.0f;
            this.calcG = this.calcGi / 255.0f;
            this.calcB = this.calcBi / 255.0f;
            this.calcA = 1.0f;
            this.calcAlpha = false;
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
    
    public void strokeWidth(final float strokeWidth) {
        this._strokeWidth = strokeWidth;
    }
    
    public void strokeMode(final int strokeMode) {
        this._strokeMode = strokeMode;
    }
    
    public void noStroke() {
        this._stroke = false;
    }
    
    public void stroke(final int calci) {
        if ((calci & 0xFF000000) == 0x0) {
            this.stroke((float)calci);
        }
        else {
            this.calci = calci;
            this.calcRi = (calci >> 16 & 0xFF);
            this.calcGi = (calci >> 8 & 0xFF);
            this.calcBi = (calci & 0xFF);
            this.calcAi = 255;
            this.calcR = this.calcRi / 255.0f;
            this.calcG = this.calcGi / 255.0f;
            this.calcB = this.calcBi / 255.0f;
            this.calcA = 1.0f;
            this.calcAlpha = false;
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
    
    public void noBackground() {
        this._background = false;
        this.backi = -1;
    }
    
    public void background(final int calci) {
        if ((calci & 0xFF000000) == 0x0) {
            this.background((float)calci);
        }
        else {
            this.calci = calci;
            this.calcRi = (calci >> 16 & 0xFF);
            this.calcGi = (calci >> 8 & 0xFF);
            this.calcBi = (calci & 0xFF);
            this.calcAi = 255;
            this.calcR = this.calcRi / 255.0f;
            this.calcG = this.calcGi / 255.0f;
            this.calcB = this.calcBi / 255.0f;
            this.calcA = 1.0f;
            this.calcAlpha = false;
            this.calc_background();
        }
    }
    
    public void background(final float n) {
        this.calc_color(n);
        this.calc_background();
    }
    
    public void background(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3);
        this.calc_background();
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
        this.hints[n] = true;
    }
    
    public void unhint(final int n) {
        this.hints[n] = false;
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
        InputStream inputStream;
        if (s.startsWith("http://")) {
            try {
                inputStream = new URL(s).openStream();
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
                inputStream = null;
            }
        }
        else {
            inputStream = this.getClass().getResourceAsStream(s);
            if (inputStream == null) {
                inputStream = this.getClass().getResourceAsStream("data/" + s);
            }
        }
        if (inputStream == null) {
            throw new IOException("could not open " + s + " as a stream");
        }
        if (s.toLowerCase().endsWith(".gz")) {
            return new GZIPInputStream(inputStream);
        }
        return inputStream;
    }
    
    public byte[] loadBytes(final String s) {
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.loadStream(s));
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
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.loadStream(s)));
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
    
    public int getPixel(final int n, final int n2) {
        return this.pixels[n2 * this.width + n];
    }
    
    public void setPixel(final int n, final int n2, final int n3) {
        this.pixels[n2 * this.width + n] = n3;
    }
    
    public final int color(final float n, final float n2, final float n3) {
        this.calc_color(n, n2, n3);
        return this.calci;
    }
    
    public final float red(final int n) {
        final float n2 = n >> 16 & 0xFF;
        if (this.colorMaxX == 255.0f) {
            return n2;
        }
        return n2 / 255.0f * this.colorMaxX;
    }
    
    public final float green(final int n) {
        final float n2 = n >> 8 & 0xFF;
        if (this.colorMaxY == 255.0f) {
            return n2;
        }
        return n2 / 255.0f * this.colorMaxY;
    }
    
    public final float blue(final int n) {
        final float n2 = n & 0xFF;
        if (this.colorMaxZ == 255.0f) {
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
    
    public static final int blend(final int n, final int n2, int n3) {
        n3 = n3 * (n2 >> 24 & 0xFF) >> 8;
        final int n4 = 255 - n3;
        return 0xFF000000 | n4 * (n >> 16 & 0xFF) + n3 * (n2 >> 16 & 0xFF) >> 8 << 16 | n4 * (n >> 8 & 0xFF) + n3 * (n2 >> 8 & 0xFF) >> 8 << 8 | n4 * (n & 0xFF) + n3 * (n2 & 0xFF) >> 8;
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
    
    public BGraphics() {
        this.cacheHsbValue = new float[3];
        this.smooth = false;
        this.matrixStack = new float[32][16];
        this.cvertex = new float[128][19];
        this.image_mode = 0;
        this.rect_mode = 0;
        this.ellipse_mode = 0;
        this.text_mode = 1;
    }
    
    public BGraphics(final int width, final int height) {
        this.cacheHsbValue = new float[3];
        this.smooth = false;
        this.matrixStack = new float[32][16];
        this.cvertex = new float[128][19];
        this.image_mode = 0;
        this.rect_mode = 0;
        this.ellipse_mode = 0;
        this.text_mode = 1;
        this.width = width;
        this.height = height;
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        this.pixelCount = this.width * this.height;
        this.pixels = new int[this.pixelCount];
        this.zbuffer = new float[this.pixelCount];
        this.defaults();
        this.matrixStackDepth = 0;
        this.fov = 60.0f;
        this.eyeX = this.width / 2.0f;
        this.eyeY = this.height / 2.0f;
        this.eyeDist = this.eyeY / this.tan(3.1415927f * this.fov / 360.0f);
        this.nearDist = this.eyeDist / 10.0f;
        this.farDist = this.eyeDist * 10.0f;
        this.aspect = this.width / this.height;
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
        this.circleX = null;
        this.sphereX = null;
    }
}
