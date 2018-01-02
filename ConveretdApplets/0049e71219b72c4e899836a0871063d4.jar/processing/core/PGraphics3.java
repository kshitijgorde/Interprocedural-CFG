// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;

public class PGraphics3 extends PGraphics
{
    static final int LIGHT_AMBIENT_R = 0;
    static final int LIGHT_AMBIENT_G = 1;
    static final int LIGHT_AMBIENT_B = 2;
    static final int LIGHT_DIFFUSE_R = 3;
    static final int LIGHT_DIFFUSE_G = 4;
    static final int LIGHT_DIFFUSE_B = 5;
    static final int LIGHT_SPECULAR_R = 6;
    static final int LIGHT_SPECULAR_G = 7;
    static final int LIGHT_SPECULAR_B = 8;
    static final int LIGHT_COLOR_COUNT = 9;
    protected static final int MAX_LIGHTS = 8;
    static final int DEFAULT_LINES = 512;
    static final int DEFAULT_TRIANGLES = 256;
    static final int DEFAULT_TEXTURES = 3;
    public PMatrix modelview;
    public PMatrix modelviewInv;
    public PMatrix camera;
    public PMatrix cameraInv;
    boolean useBackfaceCulling;
    public float ambientR;
    public float ambientG;
    public float ambientB;
    public int ambientRi;
    public int ambientGi;
    public int ambientBi;
    public float specularR;
    public float specularG;
    public float specularB;
    public float specularA;
    public int specularRi;
    public int specularGi;
    public int specularBi;
    public int specularAi;
    public float emissiveR;
    public float emissiveG;
    public float emissiveB;
    public int emissiveRi;
    public int emissiveGi;
    public int emissiveBi;
    public float shininess;
    private boolean lightingDependsOnVertexPosition;
    public float[] tempLightingContribution;
    public float[] worldNormal;
    public float cameraFOV;
    public float cameraX;
    public float cameraY;
    public float cameraZ;
    public float cameraNear;
    public float cameraFar;
    public float cameraAspect;
    public boolean manipulatingCamera;
    public PMatrix projection;
    public PMatrix forwardTransform;
    public PMatrix reverseTransform;
    public int[] stencil;
    public float[] zbuffer;
    public int lightCount;
    public int[] lights;
    public float[] lightsX;
    public float[] lightsY;
    public float[] lightsZ;
    public float[] lightsNX;
    public float[] lightsNY;
    public float[] lightsNZ;
    public float[] lightsFalloffConstant;
    public float[] lightsFalloffLinear;
    public float[] lightsFalloffQuadratic;
    public float[] lightsSpotAngle;
    public float[] lightsSpotAngleCos;
    public float[] lightsSpotConcentration;
    public float[] lightsDiffuseR;
    public float[] lightsDiffuseG;
    public float[] lightsDiffuseB;
    public float[] lightsSpecularR;
    public float[] lightsSpecularG;
    public float[] lightsSpecularB;
    public float lightSpecularR;
    public float lightSpecularG;
    public float lightSpecularB;
    public float lightFalloffConstant;
    public float lightFalloffLinear;
    public float lightFalloffQuadratic;
    protected int vertex_start;
    protected int vertex_end;
    protected int vertex_end_including_clip_verts;
    int[] vertex_order;
    public int pathCount;
    public int[] pathOffset;
    public int[] pathLength;
    PLine line;
    public int[][] lines;
    public int lineCount;
    PTriangle triangle;
    public int[][] triangles;
    public float[][][] triangleColors;
    public int triangleCount;
    int shape_index;
    public int textureMode;
    public float textureU;
    public float textureV;
    public PImage textureImage;
    protected PImage[] textures;
    int texture_index;
    public float normalX;
    public float normalY;
    public float normalZ;
    public int normalMode;
    public int normalCount;
    public int sphereDetail;
    float[] sphereX;
    float[] sphereY;
    float[] sphereZ;
    
    public void resize(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        this.allocate();
        this.cameraFOV = 1.0471976f;
        this.cameraX = this.width / 2.0f;
        this.cameraY = this.height / 2.0f;
        this.cameraZ = this.cameraY / this.tan(this.cameraFOV / 2.0f);
        this.cameraNear = this.cameraZ / 10.0f;
        this.cameraFar = this.cameraZ * 10.0f;
        this.cameraAspect = this.width / this.height;
        this.lightsX = new float[8];
        this.lightsY = new float[8];
        this.lightsZ = new float[8];
        this.lightsDiffuseR = new float[8];
        this.lightsDiffuseG = new float[8];
        this.lightsDiffuseB = new float[8];
        this.lightsSpecularR = new float[8];
        this.lightsSpecularG = new float[8];
        this.lightsSpecularB = new float[8];
        this.lights = new int[8];
        this.lightsNX = new float[8];
        this.lightsNY = new float[8];
        this.lightsNZ = new float[8];
        this.lightsFalloffConstant = new float[8];
        this.lightsFalloffLinear = new float[8];
        this.lightsFalloffQuadratic = new float[8];
        this.lightsSpotAngle = new float[8];
        this.lightsSpotAngleCos = new float[8];
        this.lightsSpotConcentration = new float[8];
        this.projection = new PMatrix();
        this.modelview = new PMatrix(32);
        this.modelviewInv = new PMatrix(32);
        this.forwardTransform = this.modelview;
        this.reverseTransform = this.modelviewInv;
        this.camera = new PMatrix();
        this.cameraInv = new PMatrix();
        this.camera();
        this.perspective();
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
        this.zbuffer = new float[this.pixelCount];
        this.stencil = new int[this.pixelCount];
        this.line = new PLine(this);
        this.triangle = new PTriangle(this);
    }
    
    public void beginFrame() {
        super.beginFrame();
        this.modelview.set(this.camera);
        this.modelviewInv.set(this.cameraInv);
        this.lightCount = 0;
        this.lightingDependsOnVertexPosition = false;
        this.lightFalloff(1.0f, 0.0f, 0.0f);
        this.lightSpecular(0.0f, 0.0f, 0.0f);
        this.lineCount = 0;
        if (this.line != null) {
            this.line.reset();
        }
        this.pathCount = 0;
        this.triangleCount = 0;
        if (this.triangle != null) {
            this.triangle.reset();
        }
        this.vertex_start = 0;
        this.texture_index = 0;
        this.normal(0.0f, 0.0f, 1.0f);
    }
    
    public void endFrame() {
        if (this.hints[7]) {
            if (this.triangleCount > 0) {
                this.depth_sort_triangles();
                this.render_triangles();
            }
            if (this.lineCount > 0) {
                this.depth_sort_lines();
                this.render_lines();
            }
        }
        super.endFrame();
    }
    
    public void defaults() {
        super.defaults();
        this.manipulatingCamera = false;
        this.forwardTransform = this.modelview;
        this.reverseTransform = this.modelviewInv;
        this.perspective();
        this.textureMode(2);
        this.emissive(0.0f);
        this.specular(0.5f);
        this.shininess(1.0f);
    }
    
    public void beginShape(final int shape) {
        this.shape = shape;
        ++this.shape_index;
        if (this.shape_index == -1) {
            this.shape_index = 0;
        }
        if (this.hints[7]) {
            this.vertex_start = this.vertexCount;
            this.vertex_end = 0;
        }
        else {
            this.vertexCount = 0;
            if (this.line != null) {
                this.line.reset();
            }
            this.lineCount = 0;
            this.pathCount = 0;
            if (this.triangle != null) {
                this.triangle.reset();
            }
            this.triangleCount = 0;
        }
        this.textureImage = null;
        this.splineVertexCount = 0;
        this.normalMode = 0;
        this.normalCount = 0;
    }
    
    public void normal(final float normalX, final float normalY, final float normalZ) {
        this.normalX = normalX;
        this.normalY = normalY;
        this.normalZ = normalZ;
        if (this.shape != 0) {
            if (this.normalCount == 0) {
                for (int i = this.vertex_start; i < this.vertexCount; ++i) {
                    this.vertices[i][17] = this.normalX;
                    this.vertices[i][18] = this.normalY;
                    this.vertices[i][19] = this.normalZ;
                }
            }
            ++this.normalCount;
            if (this.normalCount == 1) {
                this.normalMode = 1;
            }
            else {
                this.normalMode = 2;
            }
        }
    }
    
    public void textureMode(final int textureMode) {
        this.textureMode = textureMode;
    }
    
    public void texture(final PImage textureImage) {
        this.textureImage = textureImage;
        if (this.texture_index == this.textures.length - 1) {
            final PImage[] textures = new PImage[this.texture_index << 1];
            System.arraycopy(this.textures, 0, textures, 0, this.texture_index);
            this.textures = textures;
        }
        if (this.textures[0] != null) {
            ++this.texture_index;
        }
        this.textures[this.texture_index] = textureImage;
    }
    
    public void vertex(final float n, final float n2) {
        this.setup_vertex(n, n2, 0.0f);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4) {
        this.texture_vertex(n3, n4);
        this.setup_vertex(n, n2, 0.0f);
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        this.setup_vertex(n, n2, n3);
    }
    
    public void vertex(final float n, final float n2, final float n3, final float n4, final float n5) {
        this.texture_vertex(n4, n5);
        this.setup_vertex(n, n2, n3);
    }
    
    protected void setup_vertex(final float n, final float n2, final float n3) {
        if (this.vertexCount == this.vertices.length) {
            final float[][] vertices = new float[this.vertexCount << 1][36];
            System.arraycopy(this.vertices, 0, vertices, 0, this.vertexCount);
            this.vertices = vertices;
        }
        final float[] array = this.vertices[this.vertexCount++];
        this.splineVertexCount = 0;
        array[9] = n;
        array[10] = n2;
        array[11] = n3;
        if (this.fill) {
            array[3] = this.fillR;
            array[4] = this.fillG;
            array[5] = this.fillB;
            array[6] = this.fillA;
            array[24] = this.ambientR;
            array[25] = this.ambientG;
            array[26] = this.ambientB;
            array[27] = this.specularR;
            array[28] = this.specularG;
            array[29] = this.specularB;
            array[30] = this.specularA;
            array[31] = this.shininess;
            array[32] = this.emissiveR;
            array[33] = this.emissiveG;
            array[34] = this.emissiveB;
        }
        if (this.stroke) {
            array[12] = this.strokeR;
            array[13] = this.strokeG;
            array[14] = this.strokeB;
            array[15] = this.strokeA;
            array[16] = this.strokeWeight;
        }
        if (this.textureImage != null) {
            array[7] = this.textureU;
            array[8] = this.textureV;
        }
        array[17] = this.normalX;
        array[18] = this.normalY;
        array[19] = this.normalZ;
        array[35] = 0.0f;
    }
    
    protected void texture_vertex(float textureU, float textureV) {
        if (this.textureImage == null) {
            throw new RuntimeException("need to set an image with texture() before using u and v coordinates");
        }
        if (this.textureMode == 2) {
            textureU /= this.textureImage.width;
            textureV /= this.textureImage.height;
        }
        this.textureU = textureU;
        this.textureV = textureV;
        if (this.textureU < 0.0f) {
            this.textureU = 0.0f;
        }
        else if (this.textureU > 1.0f) {
            this.textureU = 1.0f;
        }
        if (this.textureV < 0.0f) {
            this.textureV = 0.0f;
        }
        else if (this.textureV > 1.0f) {
            this.textureV = 1.0f;
        }
    }
    
    protected void spline_vertex(final float n, final float n2, final float n3, final boolean b) {
        if (this.splineVertices == null) {
            this.splineVertices = new float[128][36];
        }
        if (this.splineVertexCount == 128) {
            System.arraycopy(this.splineVertices[125], 0, this.splineVertices[0], 0, 36);
            System.arraycopy(this.splineVertices[126], 0, this.splineVertices[1], 0, 36);
            System.arraycopy(this.splineVertices[127], 0, this.splineVertices[2], 0, 36);
            this.splineVertexCount = 3;
        }
        final float[] array = this.splineVertices[this.splineVertexCount];
        array[9] = n;
        array[10] = n2;
        array[11] = n3;
        if (this.fill) {
            array[3] = this.fillR;
            array[4] = this.fillG;
            array[5] = this.fillB;
            array[6] = this.fillA;
        }
        if (this.stroke) {
            array[12] = this.strokeR;
            array[13] = this.strokeG;
            array[14] = this.strokeB;
            array[15] = this.strokeA;
            array[16] = this.strokeWeight;
        }
        if (this.textureImage != null) {
            array[7] = this.textureU;
            array[8] = this.textureV;
        }
        array[17] = this.normalX;
        array[18] = this.normalY;
        array[19] = this.normalZ;
        ++this.splineVertexCount;
        if (this.splineVertexCount > 3) {
            if (b) {
                if (this.splineVertexCount % 4 == 0) {
                    if (!this.bezier_inited) {
                        this.bezier_init();
                    }
                    this.spline3_segment(this.splineVertexCount - 4, this.splineVertexCount - 4, this.bezier_draw, this.bezier_detail);
                }
            }
            else {
                if (!this.curve_inited) {
                    this.curve_init();
                }
                this.spline3_segment(this.splineVertexCount - 4, this.splineVertexCount - 3, this.curve_draw, this.curve_detail);
            }
        }
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.bezierVertex(n, n2, 0.0f, n3, n4, 0.0f, n5, n6, 0.0f);
    }
    
    public void bezierVertex(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        if (this.splineVertexCount > 0) {
            final float[] array = this.splineVertices[this.splineVertexCount - 1];
            this.spline_vertex(array[9], array[10], array[11], true);
        }
        else {
            if (this.vertexCount <= 0) {
                throw new RuntimeException("A call to vertex() must be used before bezierVertex()");
            }
            final float[] array2 = this.vertices[this.vertexCount - 1];
            this.spline_vertex(array2[9], array2[10], array2[11], true);
        }
        this.spline_vertex(n, n2, n3, true);
        this.spline_vertex(n4, n5, n6, true);
        this.spline_vertex(n7, n8, n9, true);
    }
    
    public void curveVertex(final float n, final float n2) {
        this.spline_vertex(n, n2, 0.0f, false);
    }
    
    public void curveVertex(final float n, final float n2, final float n3) {
        this.spline_vertex(n, n2, n3, false);
    }
    
    public void endShape() {
        this.vertex_end = this.vertexCount;
        this.vertex_end_including_clip_verts = this.vertex_end;
        if (this.vertexCount == 0) {
            this.shape = 0;
            return;
        }
        for (int i = this.vertex_start; i < this.vertex_end; ++i) {
            final float[] array = this.vertices[i];
            array[20] = this.modelview.m00 * array[9] + this.modelview.m01 * array[10] + this.modelview.m02 * array[11] + this.modelview.m03;
            array[21] = this.modelview.m10 * array[9] + this.modelview.m11 * array[10] + this.modelview.m12 * array[11] + this.modelview.m13;
            array[22] = this.modelview.m20 * array[9] + this.modelview.m21 * array[10] + this.modelview.m22 * array[11] + this.modelview.m23;
            array[23] = this.modelview.m30 * array[9] + this.modelview.m31 * array[10] + this.modelview.m32 * array[11] + this.modelview.m33;
            if (array[23] != 0.0f && array[23] != 1.0f) {
                final float[] array2 = array;
                final int n = 20;
                array2[n] /= array[23];
                final float[] array3 = array;
                final int n2 = 21;
                array3[n2] /= array[23];
                final float[] array4 = array;
                final int n3 = 22;
                array4[n3] /= array[23];
            }
            array[23] = 1.0f;
        }
        if (this.stroke) {
            switch (this.shape) {
                case 16: {
                    for (int vertex_end = this.vertex_end, j = this.vertex_start; j < vertex_end; ++j) {
                        this.add_path();
                        this.add_line(j, j);
                    }
                    break;
                }
                case 32:
                case 33:
                case 34: {
                    final int lineCount = this.lineCount;
                    final int n4 = this.vertex_end - 1;
                    int n5 = 0;
                    if (this.shape == 32) {
                        n5 = 1;
                    }
                    final int n6 = n5 + 1;
                    if (this.shape != 32) {
                        this.add_path();
                    }
                    for (int k = this.vertex_start; k < n4; k += n6) {
                        if (this.shape == 32) {
                            this.add_path();
                        }
                        this.add_line(k, k + 1);
                    }
                    if (this.shape == 34) {
                        this.add_line(n4, this.lines[lineCount][1]);
                    }
                    break;
                }
                case 64: {
                    for (int l = this.vertex_start; l < this.vertex_end - 2; l += 3) {
                        this.add_path();
                        final int n7 = l - this.vertex_start;
                        this.add_line(l, l + 1);
                        this.add_line(l + 1, l + 2);
                        this.add_line(l + 2, l);
                    }
                    break;
                }
                case 65: {
                    final int n8 = this.vertex_end - 1;
                    this.add_path();
                    for (int vertex_start = this.vertex_start; vertex_start < n8; ++vertex_start) {
                        final int n9 = vertex_start - this.vertex_start;
                        this.add_line(vertex_start, vertex_start + 1);
                    }
                    for (int n10 = this.vertex_end - 2, vertex_start2 = this.vertex_start; vertex_start2 < n10; ++vertex_start2) {
                        this.add_path();
                        this.add_line(vertex_start2, vertex_start2 + 2);
                    }
                    break;
                }
                case 66: {
                    for (int n11 = this.vertex_start + 1; n11 < this.vertex_end; ++n11) {
                        this.add_path();
                        this.add_line(this.vertex_start, n11);
                    }
                    this.add_path();
                    for (int n12 = this.vertex_start + 1; n12 < this.vertex_end - 1; ++n12) {
                        this.add_line(n12, n12 + 1);
                    }
                    this.add_line(this.vertex_end - 1, this.vertex_start + 1);
                    break;
                }
                case 128: {
                    for (int vertex_start3 = this.vertex_start; vertex_start3 < this.vertex_end; vertex_start3 += 4) {
                        this.add_path();
                        final int n13 = vertex_start3 - this.vertex_start;
                        this.add_line(vertex_start3, vertex_start3 + 1);
                        this.add_line(vertex_start3 + 1, vertex_start3 + 2);
                        this.add_line(vertex_start3 + 2, vertex_start3 + 3);
                        this.add_line(vertex_start3 + 3, vertex_start3);
                    }
                    break;
                }
                case 129: {
                    for (int vertex_start4 = this.vertex_start; vertex_start4 < this.vertex_end - 3; vertex_start4 += 2) {
                        this.add_path();
                        this.add_line(vertex_start4, vertex_start4 + 2);
                        this.add_line(vertex_start4 + 2, vertex_start4 + 3);
                        this.add_line(vertex_start4 + 3, vertex_start4 + 1);
                        this.add_line(vertex_start4 + 1, vertex_start4);
                    }
                    break;
                }
                case 256: {
                    final int lineCount2 = this.lineCount;
                    final int n14 = this.vertex_end - 1;
                    this.add_path();
                    for (int vertex_start5 = this.vertex_start; vertex_start5 < n14; ++vertex_start5) {
                        this.add_line(vertex_start5, vertex_start5 + 1);
                    }
                    this.add_line(n14, this.lines[lineCount2][1]);
                    break;
                }
            }
        }
        if (this.fill) {
            switch (this.shape) {
                case 66: {
                    for (int n15 = this.vertex_end - 1, n16 = this.vertex_start + 1; n16 < n15; ++n16) {
                        this.add_triangle(this.vertex_start, n16, n16 + 1);
                    }
                    break;
                }
                case 64:
                case 65: {
                    for (int n17 = this.vertex_end - 2, n18 = (this.shape == 64) ? 3 : 1, vertex_start6 = this.vertex_start; vertex_start6 < n17; vertex_start6 += n18) {
                        if (vertex_start6 % 2 == 0) {
                            this.add_triangle(vertex_start6, vertex_start6 + 2, vertex_start6 + 1);
                        }
                        else {
                            this.add_triangle(vertex_start6, vertex_start6 + 1, vertex_start6 + 2);
                        }
                    }
                    break;
                }
                case 128: {
                    for (int n19 = this.vertexCount - 3, vertex_start7 = this.vertex_start; vertex_start7 < n19; vertex_start7 += 4) {
                        this.add_triangle(vertex_start7, vertex_start7 + 1, vertex_start7 + 2);
                        this.add_triangle(vertex_start7, vertex_start7 + 2, vertex_start7 + 3);
                    }
                    break;
                }
                case 129: {
                    for (int n20 = this.vertexCount - 3, vertex_start8 = this.vertex_start; vertex_start8 < n20; vertex_start8 += 2) {
                        this.add_triangle(vertex_start8, vertex_start8 + 2, vertex_start8 + 1);
                        this.add_triangle(vertex_start8 + 2, vertex_start8 + 3, vertex_start8 + 1);
                    }
                    break;
                }
                case 256: {
                    this.triangulate_polygon();
                    break;
                }
            }
        }
        if (this.lightCount > 0 && this.fill) {
            this.handle_lighting();
        }
        else {
            this.handle_no_lighting();
        }
        for (int vertex_start9 = this.vertex_start; vertex_start9 < this.vertex_end_including_clip_verts; ++vertex_start9) {
            final float[] array5 = this.vertices[vertex_start9];
            float n21 = this.projection.m00 * array5[20] + this.projection.m01 * array5[21] + this.projection.m02 * array5[22] + this.projection.m03 * array5[23];
            float n22 = this.projection.m10 * array5[20] + this.projection.m11 * array5[21] + this.projection.m12 * array5[22] + this.projection.m13 * array5[23];
            float n23 = this.projection.m20 * array5[20] + this.projection.m21 * array5[21] + this.projection.m22 * array5[22] + this.projection.m23 * array5[23];
            final float n24 = this.projection.m30 * array5[20] + this.projection.m31 * array5[21] + this.projection.m32 * array5[22] + this.projection.m33 * array5[23];
            if (n24 != 0.0f && n24 != 1.0f) {
                n21 /= n24;
                n22 /= n24;
                n23 /= n24;
            }
            array5[0] = this.width * (1.0f + n21) / 2.0f;
            array5[1] = this.height * (1.0f + n22) / 2.0f;
            array5[2] = (n23 + 1.0f) / 2.0f;
        }
        if (!this.hints[7]) {
            if (this.fill) {
                this.render_triangles();
            }
            if (this.stroke) {
                this.render_lines();
            }
        }
        this.shape = 0;
    }
    
    protected final void add_path() {
        if (this.pathCount == this.pathOffset.length) {
            final int[] pathOffset = new int[this.pathCount << 1];
            System.arraycopy(this.pathOffset, 0, pathOffset, 0, this.pathCount);
            this.pathOffset = pathOffset;
            final int[] pathLength = new int[this.pathCount << 1];
            System.arraycopy(this.pathLength, 0, pathLength, 0, this.pathCount);
            this.pathLength = pathLength;
        }
        this.pathOffset[this.pathCount] = this.lineCount;
        this.pathLength[this.pathCount] = 0;
        ++this.pathCount;
    }
    
    protected void add_line(final int n, final int n2) {
        this.add_line_with_clip(n, n2);
    }
    
    protected final void add_line_with_clip(final int n, final int n2) {
        final float n3 = this.vertices[n][22];
        final float n4 = this.vertices[n2][22];
        if (n3 > this.cameraNear) {
            if (n4 > this.cameraNear) {
                return;
            }
            this.add_line_no_clip(this.interpolate_clip_vertex(n, n2), n2);
        }
        else {
            if (n4 <= this.cameraNear) {
                this.add_line_no_clip(n, n2);
                return;
            }
            this.add_line_no_clip(n, this.interpolate_clip_vertex(n, n2));
        }
    }
    
    protected final void add_line_no_clip(final int n, final int n2) {
        if (this.lineCount == this.lines.length) {
            final int[][] lines = new int[this.lineCount << 1][5];
            System.arraycopy(this.lines, 0, lines, 0, this.lineCount);
            this.lines = lines;
        }
        this.lines[this.lineCount][1] = n;
        this.lines[this.lineCount][2] = n2;
        this.lines[this.lineCount][0] = -1;
        this.lines[this.lineCount][3] = (this.strokeCap | this.strokeJoin);
        this.lines[this.lineCount][4] = (int)(this.strokeWeight + 0.5f);
        ++this.lineCount;
        final int[] pathLength = this.pathLength;
        final int n3 = this.pathCount - 1;
        ++pathLength[n3];
    }
    
    protected void add_triangle(final int n, final int n2, final int n3) {
        this.add_triangle_with_clip(n, n2, n3);
    }
    
    protected final void add_triangle_with_clip(final int n, final int n2, final int n3) {
        boolean b = false;
        boolean b2 = false;
        int n4 = 0;
        this.cameraNear = -8.0f;
        if (this.vertices[n][22] > this.cameraNear) {
            b = true;
            ++n4;
        }
        if (this.vertices[n2][22] > this.cameraNear) {
            b2 = true;
            ++n4;
        }
        if (this.vertices[n3][22] > this.cameraNear) {
            ++n4;
        }
        if (n4 == 0) {
            this.add_triangle_no_clip(n, n2, n3);
            return;
        }
        if (n4 == 3) {
            return;
        }
        if (n4 == 2) {
            int n5;
            int n6;
            int n7;
            if (!b) {
                n5 = n;
                n6 = n2;
                n7 = n3;
            }
            else if (!b2) {
                n5 = n2;
                n6 = n;
                n7 = n3;
            }
            else {
                n5 = n3;
                n6 = n2;
                n7 = n;
            }
            this.add_triangle_no_clip(n5, this.interpolate_clip_vertex(n5, n6), this.interpolate_clip_vertex(n5, n7));
            return;
        }
        int n8;
        int n9;
        int n10;
        if (b) {
            n8 = n3;
            n9 = n2;
            n10 = n;
        }
        else if (b2) {
            n8 = n;
            n9 = n3;
            n10 = n2;
        }
        else {
            n8 = n;
            n9 = n2;
            n10 = n3;
        }
        final int interpolate_clip_vertex = this.interpolate_clip_vertex(n8, n10);
        final int interpolate_clip_vertex2 = this.interpolate_clip_vertex(n9, n10);
        this.add_triangle_no_clip(n8, interpolate_clip_vertex, n9);
        this.add_triangle_no_clip(n9, interpolate_clip_vertex, interpolate_clip_vertex2);
    }
    
    private final int interpolate_clip_vertex(final int n, final int n2) {
        float[] array;
        float[] array2;
        if (this.vertices[n][22] < this.vertices[n2][22]) {
            array = this.vertices[n2];
            array2 = this.vertices[n];
        }
        else {
            array = this.vertices[n];
            array2 = this.vertices[n2];
        }
        final float n3 = array[22];
        final float n4 = array2[22];
        final float n5 = n3 - n4;
        if (n5 == 0.0f) {
            return n;
        }
        final float n6 = (this.cameraNear - n4) / n5;
        final float n7 = 1.0f - n6;
        this.vertex(n6 * array[9] + n7 * array2[9], n6 * array[10] + n7 * array2[10], n6 * array[11] + n7 * array2[11]);
        final int n8 = this.vertexCount - 1;
        ++this.vertex_end_including_clip_verts;
        final float[] array3 = this.vertices[n8];
        array3[0] = n6 * array[0] + n7 * array2[0];
        array3[1] = n6 * array[1] + n7 * array2[1];
        array3[2] = n6 * array[2] + n7 * array2[2];
        array3[20] = n6 * array[20] + n7 * array2[20];
        array3[21] = n6 * array[21] + n7 * array2[21];
        array3[22] = n6 * array[22] + n7 * array2[22];
        array3[23] = n6 * array[23] + n7 * array2[23];
        array3[3] = n6 * array[3] + n7 * array2[3];
        array3[4] = n6 * array[4] + n7 * array2[4];
        array3[5] = n6 * array[5] + n7 * array2[5];
        array3[6] = n6 * array[6] + n7 * array2[6];
        array3[7] = n6 * array[7] + n7 * array2[7];
        array3[8] = n6 * array[8] + n7 * array2[8];
        array3[12] = n6 * array[12] + n7 * array2[12];
        array3[13] = n6 * array[13] + n7 * array2[13];
        array3[14] = n6 * array[14] + n7 * array2[14];
        array3[15] = n6 * array[15] + n7 * array2[15];
        array3[17] = n6 * array[17] + n7 * array2[17];
        array3[18] = n6 * array[18] + n7 * array2[18];
        array3[19] = n6 * array[19] + n7 * array2[19];
        array3[16] = n6 * array[16] + n7 * array2[16];
        array3[24] = n6 * array[24] + n7 * array2[24];
        array3[25] = n6 * array[25] + n7 * array2[25];
        array3[26] = n6 * array[26] + n7 * array2[26];
        array3[27] = n6 * array[27] + n7 * array2[27];
        array3[28] = n6 * array[28] + n7 * array2[28];
        array3[29] = n6 * array[29] + n7 * array2[29];
        array3[30] = n6 * array[30] + n7 * array2[30];
        array3[32] = n6 * array[32] + n7 * array2[32];
        array3[33] = n6 * array[33] + n7 * array2[33];
        array3[34] = n6 * array[34] + n7 * array2[34];
        array3[31] = n6 * array[31] + n7 * array2[31];
        array3[35] = 0.0f;
        return n8;
    }
    
    protected final void add_triangle_no_clip(final int n, final int n2, final int n3) {
        if (this.triangleCount == this.triangles.length) {
            final int[][] triangles = new int[this.triangleCount << 1][5];
            System.arraycopy(this.triangles, 0, triangles, 0, this.triangleCount);
            this.triangles = triangles;
            final float[][][] triangleColors = new float[this.triangleCount << 1][3][8];
            System.arraycopy(this.triangleColors, 0, triangleColors, 0, this.triangleCount);
            this.triangleColors = triangleColors;
        }
        this.triangles[this.triangleCount][1] = n;
        this.triangles[this.triangleCount][2] = n2;
        this.triangles[this.triangleCount][3] = n3;
        if (this.textureImage == null) {
            this.triangles[this.triangleCount][4] = -1;
        }
        else {
            this.triangles[this.triangleCount][4] = this.texture_index;
        }
        this.triangles[this.triangleCount][0] = this.shape_index;
        ++this.triangleCount;
    }
    
    protected void depth_sort_triangles() {
    }
    
    protected void render_triangles() {
        for (int i = 0; i < this.triangleCount; ++i) {
            final float[] array = this.vertices[this.triangles[i][1]];
            final float[] array2 = this.vertices[this.triangles[i][2]];
            final float[] array3 = this.vertices[this.triangles[i][3]];
            final int n = this.triangles[i][4];
            final int index = this.triangles[i][0];
            this.triangle.reset();
            final float min = this.min(1.0f, this.triangleColors[i][0][0] + this.triangleColors[i][0][4]);
            final float min2 = this.min(1.0f, this.triangleColors[i][0][1] + this.triangleColors[i][0][5]);
            final float min3 = this.min(1.0f, this.triangleColors[i][0][2] + this.triangleColors[i][0][6]);
            final float min4 = this.min(1.0f, this.triangleColors[i][1][0] + this.triangleColors[i][1][4]);
            final float min5 = this.min(1.0f, this.triangleColors[i][1][1] + this.triangleColors[i][1][5]);
            final float min6 = this.min(1.0f, this.triangleColors[i][1][2] + this.triangleColors[i][1][6]);
            final float min7 = this.min(1.0f, this.triangleColors[i][2][0] + this.triangleColors[i][2][4]);
            final float min8 = this.min(1.0f, this.triangleColors[i][2][1] + this.triangleColors[i][2][5]);
            final float min9 = this.min(1.0f, this.triangleColors[i][2][2] + this.triangleColors[i][2][6]);
            if (n > -1 && this.textures[n] != null) {
                this.triangle.setTexture(this.textures[n]);
                this.triangle.setUV(array[7], array[8], array2[7], array2[8], array3[7], array3[8]);
            }
            this.triangle.setIntensities(min, min2, min3, array[6], min4, min5, min6, array2[6], min7, min8, min9, array3[6]);
            this.triangle.setVertices(array[0], array[1], array[2], array2[0], array2[1], array2[2], array3[0], array3[1], array3[2]);
            this.triangle.setIndex(index);
            this.triangle.render();
        }
    }
    
    protected void depth_sort_lines() {
    }
    
    protected void render_lines() {
        for (int i = 0; i < this.lineCount; ++i) {
            final float[] array = this.vertices[this.lines[i][1]];
            final float[] array2 = this.vertices[this.lines[i][2]];
            final int index = this.lines[i][0];
            this.line.reset();
            this.line.setIntensities(array[12], array[13], array[14], array[15], array2[12], array2[13], array2[14], array2[15]);
            this.line.setVertices(array[0], array[1], array[2], array2[0], array2[1], array2[2]);
            this.line.setIndex(index);
            this.line.draw();
        }
    }
    
    private final void triangulate_polygon() {
        float n = 0.0f;
        int n2 = this.vertex_end - 1;
        for (int i = this.vertex_start; i < this.vertex_end; n2 = i++) {
            n += this.vertices[i][9] * this.vertices[n2][10] - this.vertices[n2][9] * this.vertices[i][10];
        }
        if (n > 0.0f) {
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
            final float n9 = -this.vertices[this.vertex_order[n7]][9];
            final float n10 = this.vertices[this.vertex_order[n7]][10];
            final float n11 = -this.vertices[this.vertex_order[n6]][9];
            final float n12 = this.vertices[this.vertex_order[n6]][10];
            final float n13 = -this.vertices[this.vertex_order[n8]][9];
            final float n14 = this.vertices[this.vertex_order[n8]][10];
            if (1.0E-4f > (n11 - n9) * (n14 - n10) - (n12 - n10) * (n13 - n9)) {
                continue;
            }
            for (int n15 = 0; n15 < l; ++n15) {
                if (n15 != n7 && n15 != n6 && n15 != n8) {
                    final float n16 = -this.vertices[this.vertex_order[n15]][9];
                    final float n17 = this.vertices[this.vertex_order[n15]][10];
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
            this.add_triangle(this.vertex_order[n7], this.vertex_order[n6], this.vertex_order[n8]);
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
    
    private final void toWorldNormal(final float n, final float n2, final float n3, final float[] array) {
        array[0] = this.modelviewInv.m00 * n + this.modelviewInv.m10 * n2 + this.modelviewInv.m20 * n3 + this.modelviewInv.m30;
        array[1] = this.modelviewInv.m01 * n + this.modelviewInv.m11 * n2 + this.modelviewInv.m21 * n3 + this.modelviewInv.m31;
        array[2] = this.modelviewInv.m02 * n + this.modelviewInv.m12 * n2 + this.modelviewInv.m22 * n3 + this.modelviewInv.m32;
        array[3] = this.modelviewInv.m03 * n + this.modelviewInv.m13 * n2 + this.modelviewInv.m23 * n3 + this.modelviewInv.m33;
        if (array[3] != 0.0f && array[3] != 1.0f) {
            final int n4 = 0;
            array[n4] /= array[3];
            final int n5 = 1;
            array[n5] /= array[3];
            final int n6 = 2;
            array[n6] /= array[3];
        }
        array[3] = 1.0f;
        final float mag = this.mag(array[0], array[1], array[2]);
        if (mag != 0.0f && mag != 1.0f) {
            final int n7 = 0;
            array[n7] /= mag;
            final int n8 = 1;
            array[n8] /= mag;
            final int n9 = 2;
            array[n9] /= mag;
        }
    }
    
    private final void calc_lighting_contribution(final int n, final float[] array) {
        this.calc_lighting_contribution(n, array, false);
    }
    
    private final void calc_lighting_contribution(final int n, final float[] array, final boolean b) {
        final float[] array2 = this.vertices[n];
        final float n2 = array2[27];
        final float n3 = array2[28];
        final float n4 = array2[29];
        float n5 = array2[20];
        float n6 = array2[21];
        float n7 = array2[22];
        final float n8 = array2[31];
        float n9;
        float n10;
        float n11;
        if (!b) {
            this.toWorldNormal(array2[17], array2[18], array2[19], this.worldNormal);
            n9 = this.worldNormal[0];
            n10 = this.worldNormal[1];
            n11 = this.worldNormal[2];
        }
        else {
            n9 = array2[17];
            n10 = array2[18];
            n11 = array2[19];
        }
        if (this.dot(n9, n10, n11, -n5, -n6, -n7) < 0.0f) {
            n9 = -n9;
            n10 = -n10;
            n11 = -n11;
        }
        array[0] = 0.0f;
        array[2] = (array[1] = 0.0f);
        array[4] = (array[3] = 0.0f);
        array[6] = (array[5] = 0.0f);
        array[8] = (array[7] = 0.0f);
        for (int i = 0; i < this.lightCount; ++i) {
            float n12 = this.lightsFalloffConstant[i];
            float pow = 1.0f;
            if (this.lights[i] == 0) {
                if (this.lightsFalloffQuadratic[i] != 0.0f || this.lightsFalloffLinear[i] != 0.0f) {
                    final float mag = this.mag(this.lightsX[i] - n5, this.lightsY[i] - n6, this.lightsZ[i] - n7);
                    n12 += this.lightsFalloffQuadratic[i] * mag + this.lightsFalloffLinear[i] * this.sqrt(mag);
                }
                if (n12 == 0.0f) {
                    n12 = 1.0f;
                }
                final int n13 = 0;
                array[n13] += this.lightsDiffuseR[i] / n12;
                final int n14 = 1;
                array[n14] += this.lightsDiffuseG[i] / n12;
                final int n15 = 2;
                array[n15] += this.lightsDiffuseB[i] / n12;
            }
            else {
                float n16;
                float n17;
                float n18;
                float n19;
                if (this.lights[i] == 1) {
                    n16 = -this.lightsNX[i];
                    n17 = -this.lightsNY[i];
                    n18 = -this.lightsNZ[i];
                    n12 = 1.0f;
                    n19 = n9 * n16 + n10 * n17 + n11 * n18;
                    if (n19 <= 0.0f) {
                        continue;
                    }
                }
                else {
                    n16 = this.lightsX[i] - n5;
                    n17 = this.lightsY[i] - n6;
                    n18 = this.lightsZ[i] - n7;
                    final float mag2 = this.mag(n16, n17, n18);
                    if (mag2 != 0.0f) {
                        n16 /= mag2;
                        n17 /= mag2;
                        n18 /= mag2;
                    }
                    n19 = n9 * n16 + n10 * n17 + n11 * n18;
                    if (n19 <= 0.0f) {
                        continue;
                    }
                    if (this.lights[i] == 3) {
                        final float n20 = -(this.lightsNX[i] * n16 + this.lightsNY[i] * n17 + this.lightsNZ[i] * n18);
                        if (n20 <= this.lightsSpotAngleCos[i]) {
                            continue;
                        }
                        pow = this.pow(n20, this.lightsSpotConcentration[i]);
                    }
                    if (this.lightsFalloffQuadratic[i] != 0.0f || this.lightsFalloffLinear[i] != 0.0f) {
                        n12 += this.lightsFalloffQuadratic[i] * mag2 + this.lightsFalloffLinear[i] * this.sqrt(mag2);
                    }
                }
                if (n12 == 0.0f) {
                    n12 = 1.0f;
                }
                final float n21 = n19 * pow / n12;
                final int n22 = 3;
                array[n22] += this.lightsDiffuseR[i] * n21;
                final int n23 = 4;
                array[n23] += this.lightsDiffuseG[i] * n21;
                final int n24 = 5;
                array[n24] += this.lightsDiffuseB[i] * n21;
                if ((n2 > 0.0f || n3 > 0.0f || n4 > 0.0f) && (this.lightsSpecularR[i] > 0.0f || this.lightsSpecularG[i] > 0.0f || this.lightsSpecularB[i] > 0.0f)) {
                    final float mag3 = this.mag(n5, n6, n7);
                    if (mag3 != 0.0f) {
                        n5 /= mag3;
                        n6 /= mag3;
                        n7 /= mag3;
                    }
                    float n25 = n16 - n5;
                    float n26 = n17 - n6;
                    float n27 = n18 - n7;
                    final float mag4 = this.mag(n25, n26, n27);
                    if (mag4 != 0.0f) {
                        n25 /= mag4;
                        n26 /= mag4;
                        n27 /= mag4;
                    }
                    final float n28 = n25 * n9 + n26 * n10 + n27 * n11;
                    if (n28 > 0.0f) {
                        final float n29 = this.pow(n28, n8) * pow / n12;
                        final int n30 = 6;
                        array[n30] += this.lightsSpecularR[i] * n29;
                        final int n31 = 7;
                        array[n31] += this.lightsSpecularG[i] * n29;
                        final int n32 = 8;
                        array[n32] += this.lightsSpecularB[i] * n29;
                    }
                }
            }
        }
    }
    
    private final void apply_lighting_contribution(final int n, final float[] array) {
        final float[] array2 = this.vertices[n];
        array2[3] = this.min(1.0f, array2[32] + array2[24] * array[0] + array2[3] * array[3]);
        array2[4] = this.min(1.0f, array2[33] + array2[25] * array[1] + array2[4] * array[4]);
        array2[5] = this.min(1.0f, array2[34] + array2[26] * array[0] + array2[5] * array[5]);
        array2[6] = this.min(1.0f, array2[6]);
        array2[27] = this.min(1.0f, array2[27] * array[6]);
        array2[28] = this.min(1.0f, array2[28] * array[7]);
        array2[29] = this.min(1.0f, array2[29] * array[8]);
        array2[30] = this.min(1.0f, array2[30]);
        array2[35] = 1.0f;
    }
    
    private final void light_vertex_always(final int n, final float[] array) {
        this.calc_lighting_contribution(n, array);
        this.apply_lighting_contribution(n, array);
    }
    
    private final void light_vertex_if_not_already_lit(final int n, final float[] array) {
        if (this.vertices[n][35] == 0.0f) {
            this.light_vertex_always(n, array);
        }
    }
    
    private final void copy_prelit_vertex_color_to_triangle(final int n, final int n2, final int n3) {
        final float[] array = this.triangleColors[n][n3];
        final float[] array2 = this.vertices[n2];
        array[0] = array2[3];
        array[1] = array2[4];
        array[2] = array2[5];
        array[3] = array2[6];
        array[4] = array2[27];
        array[5] = array2[28];
        array[6] = array2[29];
        array[7] = array2[30];
    }
    
    private final void copy_vertex_color_to_triangle(final int n, final int n2, final int n3, final float[] array) {
        final float[] array2 = this.triangleColors[n][n3];
        final float[] array3 = this.vertices[n2];
        array2[0] = this.min(1.0f, array3[32] + array3[24] * array[0] + array3[3] * array[3]);
        array2[1] = this.min(1.0f, array3[33] + array3[25] * array[1] + array3[4] * array[4]);
        array2[2] = this.min(1.0f, array3[34] + array3[26] * array[0] + array3[5] * array[5]);
        array2[3] = this.min(1.0f, array3[6]);
        array2[4] = this.min(1.0f, array3[27] * array[6]);
        array2[5] = this.min(1.0f, array3[28] * array[7]);
        array2[6] = this.min(1.0f, array3[29] * array[8]);
        array2[7] = this.min(1.0f, array3[30]);
    }
    
    private final void light_triangle(final int n, final float[] array) {
        this.copy_vertex_color_to_triangle(n, this.triangles[n][1], 0, array);
        this.copy_vertex_color_to_triangle(n, this.triangles[n][2], 1, array);
        this.copy_vertex_color_to_triangle(n, this.triangles[n][3], 2, array);
    }
    
    private final void crossProduct(final float[] array, final float[] array2, final float[] array3) {
        array3[0] = array[1] * array2[2] - array[2] * array2[1];
        array3[1] = array[2] * array2[0] - array[0] * array2[2];
        array3[2] = array[0] * array2[1] - array[1] * array2[0];
    }
    
    private final void light_triangle(final int n) {
        if (this.normalMode == 2) {
            final int n2 = this.triangles[n][1];
            this.light_vertex_if_not_already_lit(n2, this.tempLightingContribution);
            this.copy_prelit_vertex_color_to_triangle(n, n2, 0);
            final int n3 = this.triangles[n][2];
            this.light_vertex_if_not_already_lit(n3, this.tempLightingContribution);
            this.copy_prelit_vertex_color_to_triangle(n, n3, 1);
            final int n4 = this.triangles[n][3];
            this.light_vertex_if_not_already_lit(n4, this.tempLightingContribution);
            this.copy_prelit_vertex_color_to_triangle(n, n4, 2);
        }
        else if (!this.lightingDependsOnVertexPosition) {
            final int n5 = this.triangles[n][1];
            final int n6 = this.triangles[n][2];
            final int n7 = this.triangles[n][3];
            final float[] array = { this.vertices[n6][20] - this.vertices[n5][20], this.vertices[n6][21] - this.vertices[n5][21], this.vertices[n6][22] - this.vertices[n5][22] };
            final float[] array2 = { this.vertices[n7][20] - this.vertices[n5][20], this.vertices[n7][21] - this.vertices[n5][21], this.vertices[n7][22] - this.vertices[n5][22] };
            final float[] array3 = new float[3];
            this.crossProduct(array, array2, array3);
            final float mag = this.mag(array3[0], array3[1], array3[2]);
            if (mag != 0.0f && mag != 1.0f) {
                final float[] array4 = array3;
                final int n8 = 0;
                array4[n8] /= mag;
                final float[] array5 = array3;
                final int n9 = 1;
                array5[n9] /= mag;
                final float[] array6 = array3;
                final int n10 = 2;
                array6[n10] /= mag;
            }
            this.vertices[n5][17] = array3[0];
            this.vertices[n5][18] = array3[1];
            this.vertices[n5][19] = array3[2];
            this.calc_lighting_contribution(n5, this.tempLightingContribution, true);
            this.copy_vertex_color_to_triangle(n, n5, 0, this.tempLightingContribution);
            this.copy_vertex_color_to_triangle(n, n6, 1, this.tempLightingContribution);
            this.copy_vertex_color_to_triangle(n, n7, 2, this.tempLightingContribution);
        }
        else if (this.normalMode == 1) {
            final int n11 = this.triangles[n][1];
            this.vertices[n11][17] = this.vertices[this.vertex_start][17];
            this.vertices[n11][18] = this.vertices[this.vertex_start][18];
            this.vertices[n11][19] = this.vertices[this.vertex_start][19];
            this.calc_lighting_contribution(n11, this.tempLightingContribution);
            this.copy_vertex_color_to_triangle(n, n11, 0, this.tempLightingContribution);
            final int n12 = this.triangles[n][2];
            this.vertices[n12][17] = this.vertices[this.vertex_start][17];
            this.vertices[n12][18] = this.vertices[this.vertex_start][18];
            this.vertices[n12][19] = this.vertices[this.vertex_start][19];
            this.calc_lighting_contribution(n12, this.tempLightingContribution);
            this.copy_vertex_color_to_triangle(n, n12, 1, this.tempLightingContribution);
            final int n13 = this.triangles[n][3];
            this.vertices[n13][17] = this.vertices[this.vertex_start][17];
            this.vertices[n13][18] = this.vertices[this.vertex_start][18];
            this.vertices[n13][19] = this.vertices[this.vertex_start][19];
            this.calc_lighting_contribution(n13, this.tempLightingContribution);
            this.copy_vertex_color_to_triangle(n, n13, 2, this.tempLightingContribution);
        }
        else {
            final int n14 = this.triangles[n][1];
            final int n15 = this.triangles[n][2];
            final int n16 = this.triangles[n][3];
            final float[] array7 = { this.vertices[n15][20] - this.vertices[n14][20], this.vertices[n15][21] - this.vertices[n14][21], this.vertices[n15][22] - this.vertices[n14][22] };
            final float[] array8 = { this.vertices[n16][20] - this.vertices[n14][20], this.vertices[n16][21] - this.vertices[n14][21], this.vertices[n16][22] - this.vertices[n14][22] };
            final float[] array9 = new float[3];
            this.crossProduct(array7, array8, array9);
            final float mag2 = this.mag(array9[0], array9[1], array9[2]);
            if (mag2 != 0.0f && mag2 != 1.0f) {
                final float[] array10 = array9;
                final int n17 = 0;
                array10[n17] /= mag2;
                final float[] array11 = array9;
                final int n18 = 1;
                array11[n18] /= mag2;
                final float[] array12 = array9;
                final int n19 = 2;
                array12[n19] /= mag2;
            }
            this.vertices[n14][17] = array9[0];
            this.vertices[n14][18] = array9[1];
            this.vertices[n14][19] = array9[2];
            this.calc_lighting_contribution(n14, this.tempLightingContribution, true);
            this.copy_vertex_color_to_triangle(n, n14, 0, this.tempLightingContribution);
            this.vertices[n15][17] = array9[0];
            this.vertices[n15][18] = array9[1];
            this.vertices[n15][19] = array9[2];
            this.calc_lighting_contribution(n15, this.tempLightingContribution, true);
            this.copy_vertex_color_to_triangle(n, n15, 1, this.tempLightingContribution);
            this.vertices[n16][17] = array9[0];
            this.vertices[n16][18] = array9[1];
            this.vertices[n16][19] = array9[2];
            this.calc_lighting_contribution(n16, this.tempLightingContribution, true);
            this.copy_vertex_color_to_triangle(n, n16, 2, this.tempLightingContribution);
        }
    }
    
    protected void handle_lighting() {
        if (!this.lightingDependsOnVertexPosition && this.normalMode == 1) {
            this.calc_lighting_contribution(this.vertex_start, this.tempLightingContribution);
            for (int i = 0; i < this.triangleCount; ++i) {
                this.light_triangle(i, this.tempLightingContribution);
            }
        }
        else {
            for (int j = 0; j < this.triangleCount; ++j) {
                this.light_triangle(j);
            }
        }
    }
    
    protected void handle_no_lighting() {
        for (int i = 0; i < this.triangleCount; ++i) {
            this.copy_prelit_vertex_color_to_triangle(i, this.triangles[i][1], 0);
            this.copy_prelit_vertex_color_to_triangle(i, this.triangles[i][2], 1);
            this.copy_prelit_vertex_color_to_triangle(i, this.triangles[i][3], 2);
        }
    }
    
    public void point(final float n, final float n2) {
        this.point(n, n2, 0.0f);
    }
    
    public void point(final float n, final float n2, final float n3) {
        this.beginShape(32);
        this.vertex(n, n2, n3);
        this.vertex(n + 1.0E-4f, n2 + 1.0E-4f, n3);
        this.endShape();
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
        this.line(n, n2, 0.0f, n3, n4, 0.0f);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.beginShape(32);
        this.vertex(n, n2, n3);
        this.vertex(n4, n5, n6);
        this.endShape();
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.beginShape(64);
        this.normal(0.0f, 0.0f, 1.0f);
        this.vertex(n, n2);
        this.vertex(n3, n4);
        this.vertex(n5, n6);
        this.endShape();
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.beginShape(128);
        this.normal(0.0f, 0.0f, 1.0f);
        this.vertex(n, n2);
        this.vertex(n3, n4);
        this.vertex(n5, n6);
        this.vertex(n7, n8);
        this.endShape();
    }
    
    protected void rectImpl(final float n, final float n2, final float n3, final float n4) {
        this.quad(n, n2, n3, n2, n3, n4, n, n4);
    }
    
    protected void ellipseImpl(final float n, final float n2, final float n3, final float n4) {
        final float n5 = n3 / 2.0f;
        final float n6 = n4 / 2.0f;
        final float n7 = n + n5;
        final float n8 = n2 + n6;
        final int n9 = (int)(4 + Math.sqrt(n5 + n6) * 3);
        final float n10 = 720.0f / n9;
        float n11 = 0.0f;
        if (this.fill) {
            final boolean stroke = this.stroke;
            this.stroke = false;
            this.beginShape(66);
            this.normal(0.0f, 0.0f, 1.0f);
            this.vertex(n7, n8);
            for (int i = 0; i < n9; ++i) {
                this.vertex(n7 + PGraphics3.cosLUT[(int)n11] * n5, n8 + PGraphics3.sinLUT[(int)n11] * n6);
                n11 += n10;
            }
            this.vertex(n7 + PGraphics3.cosLUT[0] * n5, n8 + PGraphics3.sinLUT[0] * n6);
            this.endShape();
            this.stroke = stroke;
        }
        if (this.stroke) {
            final boolean fill = this.fill;
            this.fill = false;
            float n12 = 0.0f;
            this.beginShape(34);
            for (int j = 0; j < n9; ++j) {
                this.vertex(n7 + PGraphics3.cosLUT[(int)n12] * n5, n8 + PGraphics3.sinLUT[(int)n12] * n6);
                n12 += n10;
            }
            this.endShape();
            this.fill = fill;
        }
    }
    
    protected void arcImpl(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        final float n7 = n3 / 2.0f;
        final float n8 = n4 / 2.0f;
        final float n9 = n + n7;
        final float n10 = n2 + n8;
        if (this.fill) {
            final boolean stroke = this.stroke;
            this.stroke = false;
            final int n11 = (int)(0.5f + n5 / 6.2831855f * 720.0f);
            final int n12 = (int)(0.5f + n6 / 6.2831855f * 720.0f);
            this.beginShape(66);
            this.vertex(n9, n10);
            for (int n13 = 1, i = n11; i < n12; i += n13) {
                final int n14 = i % 720;
                this.vertex(n9 + PGraphics3.cosLUT[n14] * n7, n10 + PGraphics3.sinLUT[n14] * n8);
            }
            this.vertex(n9 + PGraphics3.cosLUT[n12 % 720] * n7, n10 + PGraphics3.sinLUT[n12 % 720] * n8);
            this.endShape();
            this.stroke = stroke;
        }
        if (this.stroke) {
            final boolean fill = this.fill;
            this.fill = false;
            final int n15 = (int)(0.5f + n5 / 6.2831855f * 720.0f);
            final int n16 = (int)(0.5f + n6 / 6.2831855f * 720.0f);
            this.beginShape(33);
            for (int n17 = 1, j = n15; j < n16; j += n17) {
                final int n18 = j % 720;
                this.vertex(n9 + PGraphics3.cosLUT[n18] * n7, n10 + PGraphics3.sinLUT[n18] * n8);
            }
            this.vertex(n9 + PGraphics3.cosLUT[n16 % 720] * n7, n10 + PGraphics3.sinLUT[n16 % 720] * n8);
            this.endShape();
            this.fill = fill;
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
        if (this.triangle != null) {
            this.triangle.setCulling(true);
        }
        this.beginShape(128);
        this.normal(0.0f, 0.0f, 1.0f);
        this.vertex(n4, n6, n8);
        this.vertex(n5, n6, n8);
        this.vertex(n5, n7, n8);
        this.vertex(n4, n7, n8);
        this.normal(1.0f, 0.0f, 0.0f);
        this.vertex(n5, n6, n8);
        this.vertex(n5, n6, n9);
        this.vertex(n5, n7, n9);
        this.vertex(n5, n7, n8);
        this.normal(0.0f, 0.0f, -1.0f);
        this.vertex(n5, n6, n9);
        this.vertex(n4, n6, n9);
        this.vertex(n4, n7, n9);
        this.vertex(n5, n7, n9);
        this.normal(-1.0f, 0.0f, 0.0f);
        this.vertex(n4, n6, n9);
        this.vertex(n4, n6, n8);
        this.vertex(n4, n7, n8);
        this.vertex(n4, n7, n9);
        this.normal(0.0f, 1.0f, 0.0f);
        this.vertex(n4, n6, n9);
        this.vertex(n5, n6, n9);
        this.vertex(n5, n6, n8);
        this.vertex(n4, n6, n8);
        this.normal(0.0f, -1.0f, 0.0f);
        this.vertex(n4, n7, n8);
        this.vertex(n5, n7, n8);
        this.vertex(n5, n7, n9);
        this.vertex(n4, n7, n9);
        this.endShape();
        if (this.triangle != null) {
            this.triangle.setCulling(false);
        }
    }
    
    public void sphereDetail(int sphereDetail) {
        if (sphereDetail < 3) {
            sphereDetail = 3;
        }
        if (sphereDetail == this.sphereDetail) {
            return;
        }
        final float n = 720.0f / sphereDetail;
        final float[] array = new float[sphereDetail];
        final float[] array2 = new float[sphereDetail];
        for (int i = 0; i < sphereDetail; ++i) {
            array[i] = PGraphics3.cosLUT[(int)(i * n) % 720];
            array2[i] = PGraphics3.sinLUT[(int)(i * n) % 720];
        }
        final int n2 = sphereDetail * (sphereDetail - 1) + 2;
        int n3 = 0;
        this.sphereX = new float[n2];
        this.sphereY = new float[n2];
        this.sphereZ = new float[n2];
        float n5;
        final float n4 = n5 = 360.0f / sphereDetail;
        for (int j = 1; j < sphereDetail; ++j) {
            final float n6 = PGraphics3.sinLUT[(int)n5 % 720];
            final float n7 = -PGraphics3.cosLUT[(int)n5 % 720];
            for (int k = 0; k < sphereDetail; ++k) {
                this.sphereX[n3] = array[k] * n6;
                this.sphereY[n3] = n7;
                this.sphereZ[n3++] = array2[k] * n6;
            }
            n5 += n4;
        }
        this.sphereDetail = sphereDetail;
    }
    
    public void sphere(final float n) {
        final float n2 = 0.0f;
        final float n3 = 0.0f;
        final float n4 = 0.0f;
        if (this.sphereDetail == 0) {
            this.sphereDetail(30);
        }
        this.pushMatrix();
        if (n2 != 0.0f && n3 != 0.0f && n4 != 0.0f) {
            this.translate(n2, n3, n4);
        }
        this.scale(n);
        if (this.triangle != null) {
            this.triangle.setCulling(true);
        }
        this.beginShape(65);
        for (int i = 0; i < this.sphereDetail; ++i) {
            this.normal(0.0f, -1.0f, 0.0f);
            this.vertex(0.0f, -1.0f, 0.0f);
            this.normal(this.sphereX[i], this.sphereY[i], this.sphereZ[i]);
            this.vertex(this.sphereX[i], this.sphereY[i], this.sphereZ[i]);
        }
        this.vertex(0.0f, -1.0f, 0.0f);
        this.normal(this.sphereX[0], this.sphereY[0], this.sphereZ[0]);
        this.vertex(this.sphereX[0], this.sphereY[0], this.sphereZ[0]);
        this.endShape();
        int n5 = 0;
        for (int j = 2; j < this.sphereDetail; ++j) {
            int n7;
            final int n6 = n7 = n5;
            int n8;
            n5 = (n8 = n5 + this.sphereDetail);
            this.beginShape(65);
            for (int k = 0; k < this.sphereDetail; ++k) {
                this.normal(this.sphereX[n7], this.sphereY[n7], this.sphereZ[n7]);
                this.vertex(this.sphereX[n7], this.sphereY[n7], this.sphereZ[n7++]);
                this.normal(this.sphereX[n8], this.sphereY[n8], this.sphereZ[n8]);
                this.vertex(this.sphereX[n8], this.sphereY[n8], this.sphereZ[n8++]);
            }
            final int n9 = n6;
            final int n10 = n5;
            this.normal(this.sphereX[n9], this.sphereY[n9], this.sphereZ[n9]);
            this.vertex(this.sphereX[n9], this.sphereY[n9], this.sphereZ[n9]);
            this.normal(this.sphereX[n10], this.sphereY[n10], this.sphereZ[n10]);
            this.vertex(this.sphereX[n10], this.sphereY[n10], this.sphereZ[n10]);
            this.endShape();
        }
        this.beginShape(65);
        for (int l = 0; l < this.sphereDetail; ++l) {
            final int n11 = n5 + l;
            this.normal(this.sphereX[n11], this.sphereY[n11], this.sphereZ[n11]);
            this.vertex(this.sphereX[n11], this.sphereY[n11], this.sphereZ[n11]);
            this.normal(0.0f, 1.0f, 0.0f);
            this.vertex(0.0f, 1.0f, 0.0f);
        }
        this.normal(this.sphereX[n5], this.sphereY[n5], this.sphereZ[n5]);
        this.vertex(this.sphereX[n5], this.sphereY[n5], this.sphereZ[n5]);
        this.normal(0.0f, 1.0f, 0.0f);
        this.vertex(0.0f, 1.0f, 0.0f);
        this.endShape();
        this.popMatrix();
        if (this.triangle != null) {
            this.triangle.setCulling(false);
        }
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.bezier(n, n2, 0.0f, n3, n4, 0.0f, n5, n6, 0.0f, n7, n8, 0.0f);
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.beginShape(33);
        this.vertex(n, n2, n3);
        this.bezierVertex(n4, n5, n6, n7, n8, n9, n10, n11, n12);
        this.endShape();
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.curve(n, n2, 0.0f, n3, n4, 0.0f, n5, n6, 0.0f, n7, n8, 0.0f);
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.beginShape(33);
        this.curveVertex(n, n2, n3);
        this.curveVertex(n4, n5, n6);
        this.curveVertex(n7, n8, n9);
        this.curveVertex(n10, n11, n12);
        this.endShape();
    }
    
    protected void imageImpl(final PImage pImage, final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8) {
        final boolean stroke = this.stroke;
        final boolean fill = this.fill;
        final int textureMode = this.textureMode;
        this.stroke = false;
        this.fill = true;
        this.textureMode = 2;
        final float fillR = this.fillR;
        final float fillG = this.fillG;
        final float fillB = this.fillB;
        final float fillA = this.fillA;
        if (this.tint) {
            this.fillR = this.tintR;
            this.fillG = this.tintG;
            this.fillB = this.tintB;
            this.fillA = this.tintA;
        }
        else {
            this.fillR = 1.0f;
            this.fillG = 1.0f;
            this.fillB = 1.0f;
            this.fillA = 1.0f;
        }
        this.beginShape(128);
        this.texture(pImage);
        this.vertex(n, n2, n5, n6);
        this.vertex(n, n4, n5, n8);
        this.vertex(n3, n4, n7, n8);
        this.vertex(n3, n2, n7, n6);
        this.endShape();
        this.stroke = stroke;
        this.fill = fill;
        this.textureMode = textureMode;
        this.fillR = fillR;
        this.fillG = fillG;
        this.fillB = fillB;
        this.fillA = fillA;
    }
    
    public void translate(final float n, final float n2) {
        this.translate(n, n2, 0.0f);
    }
    
    public void translate(final float n, final float n2, final float n3) {
        this.forwardTransform.translate(n, n2, n3);
        this.reverseTransform.invTranslate(n, n2, n3);
    }
    
    public void rotate(final float n) {
        this.rotateZ(n);
    }
    
    public void rotateX(final float n) {
        this.forwardTransform.rotateX(n);
        this.reverseTransform.invRotateX(n);
    }
    
    public void rotateY(final float n) {
        this.forwardTransform.rotateY(n);
        this.reverseTransform.invRotateY(n);
    }
    
    public void rotateZ(final float n) {
        this.forwardTransform.rotateZ(n);
        this.reverseTransform.invRotateZ(n);
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        this.forwardTransform.rotate(n, n2, n3, n4);
        this.reverseTransform.invRotate(n, n2, n3, n4);
    }
    
    public void scale(final float n) {
        this.scale(n, n, n);
    }
    
    public void scale(final float n, final float n2) {
        this.scale(n, n2, 1.0f);
    }
    
    public void scale(final float n, final float n2, final float n3) {
        this.forwardTransform.scale(n, n2, n3);
        this.reverseTransform.invScale(n, n2, n3);
    }
    
    public void pushMatrix() {
        if (!this.modelview.push()) {
            throw new RuntimeException("too many calls to pushMatrix()");
        }
        this.modelviewInv.push();
    }
    
    public void popMatrix() {
        if (!this.modelview.pop()) {
            throw new RuntimeException("too many calls to popMatrix() (and not enough to pushMatrix)");
        }
        this.modelviewInv.pop();
    }
    
    public void resetMatrix() {
        this.forwardTransform.reset();
        this.reverseTransform.reset();
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.forwardTransform.apply(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
        this.reverseTransform.invApply(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void printMatrix() {
        this.modelview.print();
    }
    
    public void beginCamera() {
        if (this.manipulatingCamera) {
            throw new RuntimeException("cannot call beginCamera while already in camera manipulation mode");
        }
        this.manipulatingCamera = true;
        this.forwardTransform = this.cameraInv;
        this.reverseTransform = this.camera;
    }
    
    public void endCamera() {
        if (!this.manipulatingCamera) {
            throw new RuntimeException("cannot call endCamera while not in camera manipulation mode");
        }
        this.modelview.set(this.camera);
        this.modelviewInv.set(this.cameraInv);
        this.forwardTransform = this.modelview;
        this.reverseTransform = this.modelviewInv;
        this.manipulatingCamera = false;
    }
    
    public void camera() {
        this.camera(this.cameraX, this.cameraY, this.cameraZ, this.cameraX, this.cameraY, 0.0f, 0.0f, 1.0f, 0.0f);
    }
    
    public void camera(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
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
        this.camera.set(n13, n14, n15, 0.0f, n16, n17, n18, 0.0f, n10, n11, n12, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.camera.translate(-n, -n2, -n3);
        this.cameraInv.reset();
        this.cameraInv.invApply(n13, n14, n15, 0.0f, n16, n17, n18, 0.0f, n10, n11, n12, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.cameraInv.invTranslate(-n, -n2, -n3);
        this.modelview.set(this.camera);
        this.modelviewInv.set(this.cameraInv);
    }
    
    public void printCamera() {
        this.camera.print();
    }
    
    public void ortho() {
        this.ortho(0.0f, this.width, 0.0f, this.height, -10.0f, 10.0f);
    }
    
    public void ortho(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.projection.set(2.0f / (n2 - n), 0.0f, 0.0f, -(n2 + n) / (n2 - n), 0.0f, 2.0f / (n4 - n3), 0.0f, -(n4 + n3) / (n4 - n3), 0.0f, 0.0f, -2.0f / (n6 - n5), -(n6 + n5) / (n6 - n5), 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void perspective() {
        this.perspective(this.cameraFOV, this.cameraAspect, this.cameraNear, this.cameraFar);
    }
    
    public void perspective(final float n, final float n2, final float n3, final float n4) {
        final float n5 = n3 * this.tan(n / 2.0f);
        final float n6 = -n5;
        this.frustum(n6 * n2, n5 * n2, n6, n5, n3, n4);
    }
    
    public void frustum(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.projection.set(2.0f * n5 / (n2 - n), 0.0f, (n2 + n) / (n2 - n), 0.0f, 0.0f, 2.0f * n5 / (n4 - n3), (n4 + n3) / (n4 - n3), 0.0f, 0.0f, 0.0f, -(n6 + n5) / (n6 - n5), -(2.0f * n6 * n5) / (n6 - n5), 0.0f, 0.0f, -1.0f, 0.0f);
    }
    
    public void printProjection() {
        this.projection.print();
    }
    
    public float screenX(final float n, final float n2) {
        return this.screenX(n, n2, 0.0f);
    }
    
    public float screenY(final float n, final float n2) {
        return this.screenY(n, n2, 0.0f);
    }
    
    public float screenX(final float n, final float n2, final float n3) {
        final float n4 = this.modelview.m00 * n + this.modelview.m01 * n2 + this.modelview.m02 * n3 + this.modelview.m03;
        final float n5 = this.modelview.m10 * n + this.modelview.m11 * n2 + this.modelview.m12 * n3 + this.modelview.m13;
        final float n6 = this.modelview.m20 * n + this.modelview.m21 * n2 + this.modelview.m22 * n3 + this.modelview.m23;
        final float n7 = this.modelview.m30 * n + this.modelview.m31 * n2 + this.modelview.m32 * n3 + this.modelview.m33;
        float n8 = this.projection.m00 * n4 + this.projection.m01 * n5 + this.projection.m02 * n6 + this.projection.m03 * n7;
        final float n9 = this.projection.m30 * n4 + this.projection.m31 * n5 + this.projection.m32 * n6 + this.projection.m33 * n7;
        if (n9 != 0.0f) {
            n8 /= n9;
        }
        return this.width * (1.0f + n8) / 2.0f;
    }
    
    public float screenY(final float n, final float n2, final float n3) {
        final float n4 = this.modelview.m00 * n + this.modelview.m01 * n2 + this.modelview.m02 * n3 + this.modelview.m03;
        final float n5 = this.modelview.m10 * n + this.modelview.m11 * n2 + this.modelview.m12 * n3 + this.modelview.m13;
        final float n6 = this.modelview.m20 * n + this.modelview.m21 * n2 + this.modelview.m22 * n3 + this.modelview.m23;
        final float n7 = this.modelview.m30 * n + this.modelview.m31 * n2 + this.modelview.m32 * n3 + this.modelview.m33;
        float n8 = this.projection.m10 * n4 + this.projection.m11 * n5 + this.projection.m12 * n6 + this.projection.m13 * n7;
        final float n9 = this.projection.m30 * n4 + this.projection.m31 * n5 + this.projection.m32 * n6 + this.projection.m33 * n7;
        if (n9 != 0.0f) {
            n8 /= n9;
        }
        return this.height * (1.0f + n8) / 2.0f;
    }
    
    public float screenZ(final float n, final float n2, final float n3) {
        final float n4 = this.modelview.m00 * n + this.modelview.m01 * n2 + this.modelview.m02 * n3 + this.modelview.m03;
        final float n5 = this.modelview.m10 * n + this.modelview.m11 * n2 + this.modelview.m12 * n3 + this.modelview.m13;
        final float n6 = this.modelview.m20 * n + this.modelview.m21 * n2 + this.modelview.m22 * n3 + this.modelview.m23;
        final float n7 = this.modelview.m30 * n + this.modelview.m31 * n2 + this.modelview.m32 * n3 + this.modelview.m33;
        float n8 = this.projection.m20 * n4 + this.projection.m21 * n5 + this.projection.m22 * n6 + this.projection.m23 * n7;
        final float n9 = this.projection.m30 * n4 + this.projection.m31 * n5 + this.projection.m32 * n6 + this.projection.m33 * n7;
        if (n9 != 0.0f) {
            n8 /= n9;
        }
        return (n8 + 1.0f) / 2.0f;
    }
    
    public float modelX(final float n, final float n2, final float n3) {
        final float n4 = this.modelview.m00 * n + this.modelview.m01 * n2 + this.modelview.m02 * n3 + this.modelview.m03;
        final float n5 = this.modelview.m30 * n + this.modelview.m31 * n2 + this.modelview.m32 * n3 + this.modelview.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public float modelY(final float n, final float n2, final float n3) {
        final float n4 = this.modelview.m10 * n + this.modelview.m11 * n2 + this.modelview.m12 * n3 + this.modelview.m13;
        final float n5 = this.modelview.m30 * n + this.modelview.m31 * n2 + this.modelview.m32 * n3 + this.modelview.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public float modelZ(final float n, final float n2, final float n3) {
        final float n4 = this.modelview.m20 * n + this.modelview.m21 * n2 + this.modelview.m22 * n3 + this.modelview.m23;
        final float n5 = this.modelview.m30 * n + this.modelview.m31 * n2 + this.modelview.m32 * n3 + this.modelview.m33;
        return (n5 != 0.0f) ? (n4 / n5) : n4;
    }
    
    public void background(final PImage pImage) {
        super.background(pImage);
        for (int i = 0; i < this.pixelCount; ++i) {
            this.zbuffer[i] = Float.MAX_VALUE;
            this.stencil[i] = 0;
        }
    }
    
    public void clear() {
        for (int i = 0; i < this.pixelCount; ++i) {
            this.pixels[i] = this.backgroundColor;
            this.zbuffer[i] = Float.MAX_VALUE;
            this.stencil[i] = 0;
        }
    }
    
    public void smooth() {
        throw new RuntimeException("smooth() not available with P3D");
    }
    
    public void noSmooth() {
        throw new RuntimeException("noSmooth() not available with P3D");
    }
    
    public void fill(final int n) {
        super.fill(n);
        this.colorAmbient();
    }
    
    public void fill(final float n) {
        super.fill(n);
        this.colorAmbient();
    }
    
    public void fill(final float n, final float n2) {
        super.fill(n, n2);
        this.colorAmbient();
    }
    
    public void fill(final float n, final float n2, final float n3) {
        super.fill(n, n2, n3);
        this.colorAmbient();
    }
    
    public void fill(final float n, final float n2, final float n3, final float n4) {
        super.fill(n, n2, n3, n4);
        this.colorAmbient();
    }
    
    public void ambient(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorModeX) {
            this.ambient((float)n);
        }
        else {
            this.colorFrom(n);
            this.colorAmbient();
        }
    }
    
    public void ambient(final float n) {
        this.colorCalc(n);
        this.colorAmbient();
    }
    
    public void ambient(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.colorAmbient();
    }
    
    private final void colorAmbient() {
        this.ambientR = this.calcR;
        this.ambientG = this.calcG;
        this.ambientB = this.calcB;
        this.ambientRi = this.calcRi;
        this.ambientGi = this.calcGi;
        this.ambientBi = this.calcBi;
    }
    
    public void specular(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorModeX) {
            this.specular((float)n);
        }
        else {
            this.colorFrom(n);
            this.colorSpecular();
        }
    }
    
    public void specular(final float n) {
        this.colorCalc(n);
        this.colorSpecular();
    }
    
    public void specular(final float n, final float n2) {
        this.colorCalc(n, n2);
        this.colorSpecular();
    }
    
    public void specular(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.colorSpecular();
    }
    
    public void specular(final float n, final float n2, final float n3, final float n4) {
        this.colorCalc(n, n2, n3, n4);
        this.colorSpecular();
    }
    
    protected void colorSpecular() {
        this.specularR = this.calcR;
        this.specularG = this.calcG;
        this.specularB = this.calcB;
        this.specularA = this.calcA;
        this.specularRi = this.calcRi;
        this.specularGi = this.calcGi;
        this.specularBi = this.calcBi;
        this.specularAi = this.calcAi;
    }
    
    public void emissive(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.colorModeX) {
            this.emissive((float)n);
        }
        else {
            this.colorFrom(n);
            this.colorEmissive();
        }
    }
    
    public void emissive(final float n) {
        this.colorCalc(n);
        this.colorEmissive();
    }
    
    public void emissive(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.colorEmissive();
    }
    
    protected void colorEmissive() {
        this.emissiveR = this.calcR;
        this.emissiveG = this.calcG;
        this.emissiveB = this.calcB;
        this.emissiveRi = this.calcRi;
        this.emissiveGi = this.calcGi;
        this.emissiveBi = this.calcBi;
    }
    
    public void shininess(final float shininess) {
        this.shininess = shininess;
    }
    
    public void lights() {
        final int colorMode = this.colorMode;
        this.colorMode = 1;
        this.lightFalloff(1.0f, 0.0f, 0.0f);
        this.lightSpecular(0.0f, 0.0f, 0.0f);
        this.ambientLight(this.colorModeX * 0.5f, this.colorModeY * 0.5f, this.colorModeZ * 0.5f);
        this.directionalLight(this.colorModeX * 0.5f, this.colorModeY * 0.5f, this.colorModeZ * 0.5f, 0.0f, 0.0f, -1.0f);
        this.colorMode = colorMode;
        this.lightingDependsOnVertexPosition = false;
    }
    
    public void ambientLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.lightCount == 8) {
            throw new RuntimeException("can only create 8 lights");
        }
        this.colorCalc(n, n2, n3);
        this.lightsDiffuseR[this.lightCount] = this.calcR;
        this.lightsDiffuseG[this.lightCount] = this.calcG;
        this.lightsDiffuseB[this.lightCount] = this.calcB;
        this.lights[this.lightCount] = 0;
        this.lightsFalloffConstant[this.lightCount] = this.lightFalloffConstant;
        this.lightsFalloffLinear[this.lightCount] = this.lightFalloffLinear;
        this.lightsFalloffQuadratic[this.lightCount] = this.lightFalloffQuadratic;
        this.lightPosition(this.lightCount, n4, n5, n6);
        ++this.lightCount;
    }
    
    public void ambientLight(final float n, final float n2, final float n3) {
        this.ambientLight(n, n2, n3, 0.0f, 0.0f, 0.0f);
    }
    
    public void directionalLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.lightCount == 8) {
            throw new RuntimeException("can only create 8 lights");
        }
        this.colorCalc(n, n2, n3);
        this.lightsDiffuseR[this.lightCount] = this.calcR;
        this.lightsDiffuseG[this.lightCount] = this.calcG;
        this.lightsDiffuseB[this.lightCount] = this.calcB;
        this.lights[this.lightCount] = 1;
        this.lightsFalloffConstant[this.lightCount] = this.lightFalloffConstant;
        this.lightsFalloffLinear[this.lightCount] = this.lightFalloffLinear;
        this.lightsFalloffQuadratic[this.lightCount] = this.lightFalloffQuadratic;
        this.lightsSpecularR[this.lightCount] = this.lightSpecularR;
        this.lightsSpecularG[this.lightCount] = this.lightSpecularG;
        this.lightsSpecularB[this.lightCount] = this.lightSpecularB;
        this.lightDirection(this.lightCount, n4, n5, n6);
        ++this.lightCount;
    }
    
    public void pointLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.lightCount == 8) {
            throw new RuntimeException("can only create 8 lights");
        }
        this.colorCalc(n, n2, n3);
        this.lightsDiffuseR[this.lightCount] = this.calcR;
        this.lightsDiffuseG[this.lightCount] = this.calcG;
        this.lightsDiffuseB[this.lightCount] = this.calcB;
        this.lights[this.lightCount] = 2;
        this.lightsFalloffConstant[this.lightCount] = this.lightFalloffConstant;
        this.lightsFalloffLinear[this.lightCount] = this.lightFalloffLinear;
        this.lightsFalloffQuadratic[this.lightCount] = this.lightFalloffQuadratic;
        this.lightsSpecularR[this.lightCount] = this.lightSpecularR;
        this.lightsSpecularG[this.lightCount] = this.lightSpecularG;
        this.lightsSpecularB[this.lightCount] = this.lightSpecularB;
        this.lightPosition(this.lightCount, n4, n5, n6);
        ++this.lightCount;
        this.lightingDependsOnVertexPosition = true;
    }
    
    public void spotLight(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11) {
        if (this.lightCount == 8) {
            throw new RuntimeException("can only create 8 lights");
        }
        this.colorCalc(n, n2, n3);
        this.lightsDiffuseR[this.lightCount] = this.calcR;
        this.lightsDiffuseG[this.lightCount] = this.calcG;
        this.lightsDiffuseB[this.lightCount] = this.calcB;
        this.lights[this.lightCount] = 3;
        this.lightsFalloffConstant[this.lightCount] = this.lightFalloffConstant;
        this.lightsFalloffLinear[this.lightCount] = this.lightFalloffLinear;
        this.lightsFalloffQuadratic[this.lightCount] = this.lightFalloffQuadratic;
        this.lightsSpecularR[this.lightCount] = this.lightSpecularR;
        this.lightsSpecularG[this.lightCount] = this.lightSpecularG;
        this.lightsSpecularB[this.lightCount] = this.lightSpecularB;
        this.lightPosition(this.lightCount, n4, n5, n6);
        this.lightDirection(this.lightCount, n7, n8, n9);
        this.lightsSpotAngle[this.lightCount] = n10;
        this.lightsSpotAngleCos[this.lightCount] = this.max(0.0f, this.cos(n10));
        this.lightsSpotConcentration[this.lightCount] = n11;
        ++this.lightCount;
        this.lightingDependsOnVertexPosition = true;
    }
    
    public void lightFalloff(final float lightFalloffConstant, final float lightFalloffLinear, final float lightFalloffQuadratic) {
        this.lightFalloffConstant = lightFalloffConstant;
        this.lightFalloffLinear = lightFalloffLinear;
        this.lightFalloffQuadratic = lightFalloffQuadratic;
        this.lightingDependsOnVertexPosition = true;
    }
    
    public void lightSpecular(final float n, final float n2, final float n3) {
        this.colorCalc(n, n2, n3);
        this.lightSpecularR = this.calcR;
        this.lightSpecularG = this.calcG;
        this.lightSpecularB = this.calcB;
        this.lightingDependsOnVertexPosition = true;
    }
    
    protected void lightPosition(final int n, final float n2, final float n3, final float n4) {
        this.lightsX[n] = this.modelview.m00 * n2 + this.modelview.m01 * n3 + this.modelview.m02 * n4 + this.modelview.m03;
        this.lightsY[n] = this.modelview.m10 * n2 + this.modelview.m11 * n3 + this.modelview.m12 * n4 + this.modelview.m13;
        this.lightsZ[n] = this.modelview.m20 * n2 + this.modelview.m21 * n3 + this.modelview.m22 * n4 + this.modelview.m23;
    }
    
    protected void lightDirection(final int n, final float n2, final float n3, final float n4) {
        this.lightsNX[n] = this.modelviewInv.m00 * n2 + this.modelviewInv.m10 * n3 + this.modelviewInv.m20 * n4 + this.modelviewInv.m30;
        this.lightsNY[n] = this.modelviewInv.m01 * n2 + this.modelviewInv.m11 * n3 + this.modelviewInv.m21 * n4 + this.modelviewInv.m31;
        this.lightsNZ[n] = this.modelviewInv.m02 * n2 + this.modelviewInv.m12 * n3 + this.modelviewInv.m22 * n4 + this.modelviewInv.m32;
        final float mag = this.mag(this.lightsNX[n], this.lightsNY[n], this.lightsNZ[n]);
        if (mag == 0.0f || mag == 1.0f) {
            return;
        }
        final float[] lightsNX = this.lightsNX;
        lightsNX[n] /= mag;
        final float[] lightsNY = this.lightsNY;
        lightsNY[n] /= mag;
        final float[] lightsNZ = this.lightsNZ;
        lightsNZ[n] /= mag;
    }
    
    private final float mag(final float n, final float n2) {
        return (float)Math.sqrt(n * n + n2 * n2);
    }
    
    private final float mag(final float n, final float n2, final float n3) {
        return (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    private final float min(final float n, final float n2) {
        return (n < n2) ? n : n2;
    }
    
    private final float max(final float n, final float n2) {
        return (n > n2) ? n : n2;
    }
    
    private final float max(final float n, final float n2, final float n3) {
        return Math.max(n, Math.max(n2, n3));
    }
    
    private final float sq(final float n) {
        return n * n;
    }
    
    private final float sqrt(final float n) {
        return (float)Math.sqrt(n);
    }
    
    private final float pow(final float n, final float n2) {
        return (float)Math.pow(n, n2);
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
    
    private final float dot(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return n * n4 + n2 * n5 + n3 * n6;
    }
    
    private final /* synthetic */ void this() {
        this.useBackfaceCulling = false;
        this.tempLightingContribution = new float[9];
        this.worldNormal = new float[4];
        this.lightCount = 0;
        this.vertex_order = new int[512];
        this.pathOffset = new int[64];
        this.pathLength = new int[64];
        this.lines = new int[512][5];
        this.triangles = new int[256][5];
        this.triangleColors = new float[256][3][8];
        this.textures = new PImage[3];
        this.sphereDetail = 0;
    }
    
    public PGraphics3() {
        this.this();
        this.forwardTransform = this.modelview;
        this.reverseTransform = this.modelviewInv;
    }
    
    public PGraphics3(final int n, final int n2, final PApplet pApplet) {
        super(n, n2, pApplet);
        this.this();
        this.forwardTransform = this.modelview;
        this.reverseTransform = this.modelviewInv;
    }
}
