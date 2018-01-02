import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.util.Vector;
import java.awt.Font;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Plot3dStream
{
    private DataInputStream dis;
    public Plot3d plot;
    private Canvas3d canvas;
    public FormulaEditor formula;
    public int cwidth;
    public int cheight;
    public boolean showCoorAxis;
    private float xAxisScale;
    private float yAxisScale;
    private float zAxisScale;
    private float[] oTicks;
    private float[] oXAxis;
    private float[] oYAxis;
    private float[] oZAxis;
    public boolean computeAxis;
    public boolean showCube;
    public float xCubeSize;
    public float yCubeSize;
    public float zCubeSize;
    public float m0;
    public float[] cCube;
    public float[] oCube;
    public float[] vxCube;
    public float[] vyCube;
    public float[] vzCube;
    private final float amk = 0.1f;
    private final float ck = 0.8f;
    public int[] backColor;
    private int[] axisColor;
    private Font axisFont;
    private int[] cubeColor;
    private Font cubeFont;
    private int[] colorPoint;
    private int[] colorLine;
    private int[] colorSurface;
    private int[] colorText;
    private Font textFont;
    private short text_horz;
    private short text_vert;
    private int text_border;
    private int text_border_color;
    private boolean text_background;
    private int text_background_color;
    private float ratiusPoint;
    private float ratiusLine;
    private int color;
    private int font;
    private boolean fill;
    private int fill_color;
    private boolean wired;
    private float transparency;
    private int[][] colors;
    private Font[] fonts;
    public static final int[] WHITE;
    public static final int[] BLACK;
    private boolean moveable;
    private boolean show_label;
    public boolean mMoved;
    public int action;
    public final int NONE = 0;
    public final int MOVE_POINTS = 1;
    public final int MOVE_CENTER = 2;
    private String label;
    private int showLabelFont;
    private int showLabelColor;
    public Object[] movedPoint;
    public float zPoint;
    public float xScale;
    public float yScale;
    public float zScale;
    public float zoom;
    public float[] wcenter;
    private float[] initScale;
    private float[] initCubeSize;
    private float[][] coorTr;
    public float[][] proj;
    private float rProj;
    private float[][] cTr;
    public int[] drawPoint3Dcolor;
    public boolean computeTr;
    public boolean computeView;
    private Vector pImg;
    private Vector tImg;
    private Object[] tick;
    private Object[] xTickValues;
    private Object[] yTickValues;
    private Object[] zTickValues;
    private Object[][] axisLabels;
    private float[][][] net;
    private boolean[][] isOut;
    private int[] out;
    Geometry3d Composa;
    private float[] ab;
    private float[] zb;
    private float zMax;
    private float zMin;
    private boolean maxIsMoveable;
    private String minLabel;
    private int minColor;
    private int minFont;
    private int planes;
    private static final Color BACK_COLOR;
    private static final Color TEXT_COLOR;
    private static final Color BORDER_COLOR;
    
    public Plot3dStream(final InputStream inputStream, final Plot3d plot, final Canvas3d canvas) {
        this.showCoorAxis = true;
        this.xAxisScale = 0.5f;
        this.yAxisScale = 0.5f;
        this.zAxisScale = 0.5f;
        this.oTicks = new float[] { -1.0f, -1.0f, -1.0f };
        this.oXAxis = new float[] { -1.0f, 0.0f, 0.0f };
        this.oYAxis = new float[] { 0.0f, -1.0f, 0.0f };
        this.oZAxis = new float[] { 0.0f, 0.0f, -1.0f };
        this.computeAxis = true;
        this.showCube = true;
        this.xCubeSize = 2.0f;
        this.yCubeSize = 2.0f;
        this.zCubeSize = 2.0f;
        this.m0 = 0.0f;
        this.cCube = new float[] { 0.0f, 0.0f, 0.0f };
        this.oCube = new float[] { -1.0f, -1.0f, -1.0f };
        this.vxCube = new float[] { 2.0f, 0.0f, 0.0f };
        this.vyCube = new float[] { 0.0f, 2.0f, 0.0f };
        this.vzCube = new float[] { 0.0f, 0.0f, 2.0f };
        this.backColor = new int[] { 255, 255, 255 };
        this.axisColor = new int[] { 255, 0, 0 };
        this.axisFont = new Font("SansSerif", 0, 22);
        this.cubeColor = new int[] { 0, 0, 0 };
        this.cubeFont = new Font("SansSerif", 0, 22);
        this.colorPoint = new int[] { 255, 0, 0 };
        this.colorLine = new int[] { 0, 0, 0 };
        this.colorSurface = new int[] { 0, 0, 255 };
        this.colorText = new int[] { 0, 0, 0 };
        this.textFont = new Font("SansSerif", 0, 14);
        this.text_horz = 1;
        this.text_vert = 4;
        this.text_border = 1;
        this.text_border_color = -1;
        this.text_background = false;
        this.text_background_color = -1;
        this.ratiusPoint = 0.03f;
        this.ratiusLine = 0.01f;
        this.color = -1;
        this.font = -1;
        this.fill = false;
        this.fill_color = -1;
        this.wired = false;
        this.transparency = 1.0f;
        this.moveable = true;
        this.show_label = false;
        this.xScale = 1.0f;
        this.yScale = 1.0f;
        this.zScale = 1.0f;
        this.zoom = 1.0f;
        this.wcenter = new float[] { 100.0f, 100.0f, 100.0f };
        this.initScale = new float[] { this.xScale, this.yScale, this.zScale };
        this.initCubeSize = new float[] { this.xCubeSize, this.yCubeSize, this.zCubeSize };
        this.coorTr = Plot3d.zeroMatrix();
        this.rProj = 0.001f;
        this.computeTr = true;
        this.computeView = true;
        this.pImg = new Vector();
        this.tImg = new Vector();
        this.Composa = new Geometry3d();
        this.ab = null;
        this.zb = null;
        this.zMax = Float.MAX_VALUE;
        this.zMin = Float.MAX_VALUE;
        this.maxIsMoveable = false;
        this.minLabel = null;
        this.minColor = -1;
        this.minFont = -1;
        this.dis = new DataInputStream(inputStream);
        this.plot = plot;
        this.canvas = canvas;
        try {
            this.initializeStream(true);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void resetScale(final boolean b) {
        if (b) {
            this.m0 = this.defaultScale();
            this.xScale = this.m0;
            this.yScale = this.m0;
            this.zScale = this.m0;
            return;
        }
        final float defaultScale = this.defaultScale();
        final float n = defaultScale / this.m0;
        this.xScale *= n;
        this.yScale *= n;
        this.zScale *= n;
        this.m0 = defaultScale;
    }
    
    public final float defaultScale() {
        return 0.8f * this.zoom * Math.min(this.cwidth, this.cheight) / Math.max(Math.max(this.xCubeSize, this.yCubeSize), this.zCubeSize);
    }
    
    public final void computeCoorTransform() {
        this.wcenter[0] = Math.round(this.cwidth * 0.5f);
        this.wcenter[1] = Math.round(this.cheight * 0.5f);
        this.wcenter[2] = this.zCubeSize * this.zScale / 2.0f;
        final Plot3d plot = this.plot;
        this.cTr = Plot3d.translateMatrix(this.wcenter[0], this.wcenter[1], this.wcenter[2]);
        this.proj[0][2] = this.wcenter[0] * this.proj[3][2];
        this.proj[1][2] = this.wcenter[1] * this.proj[3][2];
        this.computeCoorTrMatrix();
        this.computeAxis = true;
    }
    
    public final void computeCoorTrMatrix() {
        this.coorTr[0][0] = this.xScale;
        this.coorTr[1][1] = this.yScale;
        this.coorTr[2][2] = this.zScale;
        this.coorTr[3][3] = 1.0f;
        this.coorTr[0][3] = -this.cCube[0] * this.xScale;
        this.coorTr[1][3] = -this.cCube[1] * this.yScale;
        this.coorTr[2][3] = -this.cCube[2] * this.zScale;
        this.wcenter[2] = this.zCubeSize * this.zScale / 2.0f;
        this.cTr[2][3] = this.wcenter[2];
    }
    
    private void initStream() {
        try {
            this.dis.readByte();
            this.dis.readByte();
            this.dis.readByte();
            this.dis.readUTF();
            this.dis.readInt();
            this.dis.readInt();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void initializeStream(final boolean b) {
        this.dis.readByte();
        this.dis.readByte();
        this.dis.readByte();
        this.canvas.setName(this.dis.readUTF());
        if (b) {
            this.cwidth = this.dis.readInt();
            this.cheight = this.dis.readInt();
        }
        else {
            this.dis.readInt();
            this.dis.readInt();
        }
        if (b) {
            this.action = 1;
            this.computeTr = true;
            this.computeView = true;
            this.rProj = 0.001f;
        }
        while (this.dis.available() > 0) {
            final short short1 = this.dis.readShort();
            final int wvfSize = this.wvfSize(short1, this.dis);
            switch (short1) {
                case 10: {
                    if (!b) {
                        for (int i = 0; i < wvfSize; ++i) {
                            this.dis.readByte();
                        }
                        continue;
                    }
                    if (wvfSize == 24) {
                        this.cCube[0] = this.dis.readFloat();
                        this.cCube[1] = this.dis.readFloat();
                        this.cCube[2] = this.dis.readFloat();
                        this.xCubeSize = this.dis.readFloat();
                        this.yCubeSize = this.dis.readFloat();
                        this.zCubeSize = this.dis.readFloat();
                        continue;
                    }
                    for (int j = 0; j < 3; ++j) {
                        this.oCube[j] = this.dis.readFloat();
                        this.cCube[j] = this.oCube[j];
                    }
                    this.readVCube(this.vxCube);
                    this.readVCube(this.vyCube);
                    this.readVCube(this.vzCube);
                    this.xCubeSize = this.norm(this.vxCube[0], this.vxCube[1], this.vxCube[2]);
                    this.yCubeSize = this.norm(this.vyCube[0], this.vyCube[1], this.vyCube[2]);
                    this.zCubeSize = this.norm(this.vzCube[0], this.vzCube[1], this.vzCube[2]);
                    continue;
                }
                case 11: {
                    final int int1 = this.dis.readInt();
                    this.colors = new int[int1][4];
                    for (int k = 0; k < int1; ++k) {
                        this.colors[k] = new int[] { this.dis.readUnsignedByte(), this.dis.readUnsignedByte(), this.dis.readUnsignedByte() };
                    }
                    continue;
                }
                case 12: {
                    final int int2 = this.dis.readInt();
                    this.fonts = new Font[int2];
                    for (int l = 0; l < int2; ++l) {
                        final String utf = this.dis.readUTF();
                        final int int3 = this.dis.readInt();
                        int n = 0;
                        switch (this.dis.readShort()) {
                            case 13: {
                                n = 0;
                                break;
                            }
                            case 15: {
                                n = 2;
                                break;
                            }
                            case 14: {
                                n = 1;
                                break;
                            }
                            case 16: {
                                n = 3;
                                break;
                            }
                            default: {
                                n = 0;
                                break;
                            }
                        }
                        this.fonts[l] = new Font(utf, n, int3);
                    }
                    continue;
                }
                case -32751: {
                    this.backColor = this.colors[this.dis.readInt()];
                    continue;
                }
                case 18: {
                    final short short2 = this.dis.readShort();
                    if (b) {
                        this.showCoorAxis = (short2 == 1);
                    }
                    this.axisFont = this.fonts[this.dis.readInt()];
                    this.axisColor = this.colors[this.dis.readInt()];
                    continue;
                }
                case 19: {
                    final short short3 = this.dis.readShort();
                    if (b) {
                        this.showCube = (short3 == 1);
                    }
                    this.cubeFont = this.fonts[this.dis.readInt()];
                    this.cubeColor = this.colors[this.dis.readInt()];
                    continue;
                }
                case 16404: {
                    final short short4 = this.dis.readShort();
                    if (b) {
                        this.canvas.mouseLabelMode = short4;
                        continue;
                    }
                    continue;
                }
                case -32742: {
                    this.color = this.dis.readInt();
                    continue;
                }
                case -32741: {
                    this.font = this.dis.readInt();
                    continue;
                }
                case 41: {
                    if (b) {
                        final float[][] array = new float[3][3];
                        for (int n2 = 0; n2 < 3; ++n2) {
                            for (int n3 = 0; n3 < 3; ++n3) {
                                array[n2][n3] = this.dis.readFloat();
                            }
                        }
                        this.initView(array);
                        continue;
                    }
                    for (int n4 = 0; n4 < wvfSize; ++n4) {
                        this.dis.readByte();
                    }
                    continue;
                }
                case -32726: {
                    this.rProj = this.dis.readFloat();
                    if (!b) {
                        this.proj[3][2] = this.rProj;
                        this.proj[0][2] = this.rProj * this.wcenter[0];
                        this.proj[1][2] = this.rProj * this.wcenter[1];
                        continue;
                    }
                    continue;
                }
                default: {
                    for (int n5 = 0; n5 < wvfSize; ++n5) {
                        this.dis.readByte();
                    }
                    continue;
                }
            }
        }
        this.dis.reset();
        if (b) {
            this.initCoorTransform();
            this.initScaleAndCubeSize();
        }
    }
    
    private void initScaleAndCubeSize() {
        this.initScale[0] = this.xScale;
        this.initScale[1] = this.yScale;
        this.initScale[2] = this.zScale;
        this.initCubeSize[0] = this.xCubeSize;
        this.initCubeSize[1] = this.yCubeSize;
        this.initCubeSize[2] = this.zCubeSize;
    }
    
    public final void restoreScaleAndCubeSize() {
        final float[] array = { this.initCubeSize[0] / this.xCubeSize, 0.0f, 0.0f };
        this.xCubeSize = this.initCubeSize[0];
        array[1] = this.initCubeSize[1] / this.yCubeSize;
        this.yCubeSize = this.initCubeSize[1];
        array[2] = this.initCubeSize[2] / this.zCubeSize;
        this.zCubeSize = this.initCubeSize[2];
        for (int i = 0; i < 3; ++i) {
            final float[] vxCube = this.vxCube;
            final int n = i;
            vxCube[n] *= array[i];
            final float[] vyCube = this.vyCube;
            final int n2 = i;
            vyCube[n2] *= array[i];
            final float[] vzCube = this.vzCube;
            final int n3 = i;
            vzCube[n3] *= array[i];
            this.oCube[i] = this.cCube[i] + (this.oCube[i] - this.cCube[i]) * array[i];
        }
        this.xScale = this.initScale[0];
        this.yScale = this.initScale[1];
        this.zScale = this.initScale[2];
        this.resetScale(false);
        this.computeCoorTransform();
    }
    
    private void readVCube(final float[] array) {
        for (int i = 0; i < 3; ++i) {
            array[i] = this.dis.readFloat();
            final float[] cCube = this.cCube;
            final int n = i;
            cCube[n] += array[i] / 2.0f;
        }
    }
    
    private void initCoorTransform() {
        if (this.computeTr) {
            if (this.computeView) {
                this.initView();
            }
            this.initProj();
            this.resetScale(this.computeView);
            this.computeCoorTransform();
        }
    }
    
    public final void initView() {
        final Plot3d plot = this.plot;
        final Plot3d plot2 = this.plot;
        plot.view = Plot3d.zeroMatrix();
        this.plot.view[0][0] = 1.0f;
        this.plot.view[1][1] = 1.0f;
        this.plot.view[2][2] = 1.0f;
        this.plot.view[3][3] = 1.0f;
        final float n = 0.19634955f;
        final Plot3d plot3 = this.plot;
        final Plot3d plot4 = this.plot;
        plot3.multiplyViewMatrix(Plot3d.spinMatrixX(8.0f * n));
        final Plot3d plot5 = this.plot;
        final Plot3d plot6 = this.plot;
        plot5.multiplyViewMatrix(Plot3d.spinMatrixY(13.0f * n));
        final Plot3d plot7 = this.plot;
        final Plot3d plot8 = this.plot;
        plot7.multiplyViewMatrix(Plot3d.spinMatrixX(2.0f * n));
    }
    
    public final void initProj() {
        final Plot3d plot = this.plot;
        this.proj = Plot3d.zeroMatrix();
        this.proj[0][0] = 1.0f;
        this.proj[1][1] = 1.0f;
        this.proj[2][2] = 1.0f;
        this.proj[3][3] = 1.0f;
        this.proj[3][2] = this.rProj;
    }
    
    private void initView(final float[][] array) {
        this.xScale = this.norm(array[0][0], array[1][0], array[2][0]);
        this.yScale = this.norm(array[0][1], array[1][1], array[2][1]);
        this.zScale = this.norm(array[0][2], array[1][2], array[2][2]);
        this.m0 = this.defaultScale();
        final Plot3d plot = this.plot;
        final Plot3d plot2 = this.plot;
        plot.view = Plot3d.zeroMatrix();
        this.plot.view[3][3] = 1.0f;
        this.scale(array, this.plot.view, 1.0f / this.xScale, 1.0f / this.yScale, 1.0f / this.zScale);
        this.computeView = false;
    }
    
    public final void setViewCube(final float[] array, final float[] array2, final float[][] array3) {
        if (array != null && array2 != null) {
            this.cCube = array.clone();
            this.oCube = array.clone();
            this.xCubeSize = array2[0];
            this.yCubeSize = array2[1];
            this.zCubeSize = array2[2];
            this.vxCube = new float[] { this.xCubeSize, 0.0f, 0.0f };
            this.vyCube = new float[] { 0.0f, this.yCubeSize, 0.0f };
            this.vzCube = new float[] { 0.0f, 0.0f, this.zCubeSize };
            final float[] oCube = this.oCube;
            final int n = 0;
            oCube[n] -= this.xCubeSize / 2.0f;
            final float[] oCube2 = this.oCube;
            final int n2 = 1;
            oCube2[n2] -= this.yCubeSize / 2.0f;
            final float[] oCube3 = this.oCube;
            final int n3 = 2;
            oCube3[n3] -= this.zCubeSize / 2.0f;
            this.computeTr = true;
            this.computeView = true;
            this.rProj = 0.001f;
            if (array3 != null) {
                this.computeView = false;
            }
            this.initCoorTransform();
            this.initScaleAndCubeSize();
        }
        if (array3 != null) {
            this.initView(array3);
        }
    }
    
    public final void scale(final float[][] array, final float[][] array2, final float n, final float n2, final float n3) {
        for (int i = 0; i < 3; ++i) {
            array2[i][0] = array[i][0] * n;
            array2[i][1] = array[i][1] * n2;
            array2[i][2] = array[i][2] * n3;
        }
    }
    
    private float norm(final float n, final float n2, final float n3) {
        return (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    private void initLabelParams() {
        this.label = null;
        this.zMax = Float.MAX_VALUE;
        this.zMin = Float.MAX_VALUE;
        this.maxIsMoveable = false;
        this.minLabel = null;
        this.minColor = -1;
        this.minFont = -1;
        this.planes = this.plot.getPlanes();
        if (this.ab == null || this.planes != this.ab.length) {
            this.ab = new float[this.planes];
            this.zb = new float[this.planes];
        }
        for (int i = 0; i < this.planes; ++i) {
            this.ab[i] = 0.0f;
            this.zb[i] = Float.MAX_VALUE;
        }
    }
    
    private void showMouseOverLabel() {
        if (this.plot.overFound) {
            this.canvas.setMouseOverLabel();
            final String mouseLabel = this.canvas.mouseLabel;
            if (this.canvas.isPointSelected(this.canvas.currentName) || (this.plot.over_z < this.zMin && (!this.maxIsMoveable || this.transparency == 1.0f))) {
                this.label = mouseLabel;
                this.showLabelFont = this.font;
                this.showLabelColor = this.color;
            }
            if (this.plot.over_z < this.zMin) {
                this.zMin = this.plot.over_z;
                this.minLabel = mouseLabel;
                this.minColor = this.color;
                this.minFont = this.font;
            }
            if (this.plot.over_z < this.zMax) {
                for (int i = this.planes - 1; i >= 0; --i) {
                    if (this.zb[i] > this.plot.over_z) {
                        for (int j = 1; j <= i; ++j) {
                            this.zb[j - 1] = this.zb[j];
                            this.ab[j - 1] = this.ab[j];
                        }
                        this.zb[i] = this.plot.over_z;
                        this.ab[i] = this.plot.setAlpha((int)(this.transparency * 255.0f)) / 255.0f;
                        break;
                    }
                }
                if (this.canvas.isPointSelected(this.canvas.currentName)) {
                    this.zMax = this.plot.over_z;
                    this.maxIsMoveable = true;
                }
                float n = 0.0f;
                for (int n2 = this.planes - 1; n2 >= 0 && this.zb[n2] < this.zMax; --n2) {
                    n += this.ab[n2];
                }
                if (n >= 1.0f) {
                    this.maxIsMoveable = false;
                    this.label = this.minLabel;
                    this.showLabelColor = this.minColor;
                    this.showLabelFont = this.minFont;
                }
            }
            this.plot.overFound = false;
        }
    }
    
    private void drawTriangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.plot.drawTriangle(n, n2, n3, n4, n5, n6, n7, n8, n9);
        this.showMouseOverLabel();
    }
    
    private void drawStream() {
        int n = 0;
        int n2 = 0;
        final float[][] view = this.plot.view;
        this.initStream();
        this.setDefaultAttributes();
        this.initLabelParams();
        this.canvas.clearMouseLabel();
        while (this.dis.available() > 0) {
            final short short1 = this.dis.readShort();
            final int wvfSize = this.wvfSize(short1, this.dis);
            switch (short1) {
                case 1: {
                    this.drawPoint3D(this.dis.readFloat(), this.dis.readFloat(), this.dis.readFloat());
                    continue;
                }
                case 2: {
                    final float float1 = this.dis.readFloat();
                    final float float2 = this.dis.readFloat();
                    final float float3 = this.dis.readFloat();
                    final float float4 = this.dis.readFloat();
                    final float float5 = this.dis.readFloat();
                    final float float6 = this.dis.readFloat();
                    this.setColor((this.color > -1) ? this.colors[this.color] : this.colorLine, this.transparency);
                    Geometry3d.drawSegment(this.plot, float1, float2, float3, float4, float5, float6, this.ratiusLine);
                    if (this.show_label) {
                        this.showLabel((float1 + float4) / 2.0f, (float2 + float5) / 2.0f, (float3 + float6) / 2.0f);
                        continue;
                    }
                    continue;
                }
                case 3: {
                    this.drawPoligonal3D(this.dis, 3, this.color, -1, true, false, false, true);
                    continue;
                }
                case 4: {
                    this.drawPoligonal3D(this.dis, 4, this.color, -1, true, false, false, true);
                    continue;
                }
                case 30:
                case 43:
                case 44: {
                    this.drawPoligonal3D(this.dis, this.dis.readInt(), this.fill_color, this.color, short1 == 43 || this.fill, short1 != 43 && this.wired, false, short1 == 44);
                    continue;
                }
                case 37: {
                    final int int1 = this.dis.readInt();
                    final int int2 = this.dis.readInt();
                    if (this.net == null || this.net.length != int1 || this.net[0].length != int2) {
                        this.net = new float[int1][int2][3];
                        this.isOut = new boolean[int1][int2];
                    }
                    for (int i = 0; i < this.net.length; ++i) {
                        for (int j = 0; j < this.net[i].length; ++j) {
                            for (int k = 0; k < 3; ++k) {
                                this.net[i][j][k] = this.dis.readFloat();
                                this.isOut[i][j] = false;
                            }
                        }
                    }
                    if (this.out != null) {
                        for (int l = 0; l < this.out.length; ++l) {
                            this.isOut[this.out[l] / int2][this.out[l] % int2] = true;
                        }
                    }
                    int n3 = 1;
                    for (int n4 = 0; n4 < this.net.length - 1; ++n4) {
                        for (int n5 = 0; n5 < this.net[n4].length - 1; ++n5) {
                            if (!this.isOut[n4][n5] && !this.isOut[n4 + 1][n5] && !this.isOut[n4 + 1][n5 + 1]) {
                                if (!this.isOut[n4][n5 + 1]) {
                                    if (n3 != 0 && this.show_label) {
                                        this.showLabel((this.net[n4][n5][0] + this.net[n4 + 1][n5][0] + this.net[n4 + 1][n5 + 1][0]) / 3.0f, (this.net[n4][n5][1] + this.net[n4 + 1][n5][1] + this.net[n4 + 1][n5 + 1][1]) / 3.0f, (this.net[n4][n5][2] + this.net[n4 + 1][n5][2] + this.net[n4 + 1][n5 + 1][2]) / 3.0f);
                                    }
                                    n3 = 0;
                                    if (this.wired && !this.plot.doNotPlot) {
                                        int n6 = 1;
                                        int n7 = 0;
                                        int n8 = n4;
                                        int n9 = n5;
                                        this.setColor((this.color > -1) ? this.colors[this.color] : this.colorSurface, this.transparency);
                                        do {
                                            final int n10 = n8;
                                            final int n11 = n9;
                                            n8 = n4 + n6;
                                            n9 = n5 + n7;
                                            this.plot.drawLine(this.net[n10][n11][0], this.net[n10][n11][1], this.net[n10][n11][2], this.net[n8][n9][0], this.net[n8][n9][1], this.net[n8][n9][2]);
                                            if (n7 == 0) {
                                                n7 = 1;
                                            }
                                            else if (n6 == 1) {
                                                n6 = 0;
                                            }
                                            else {
                                                n7 = 0;
                                            }
                                        } while (n6 + n7 != 0);
                                    }
                                    if (this.fill) {
                                        this.setColor((this.fill_color > -1) ? this.colors[this.fill_color] : this.colorSurface, this.transparency);
                                        this.drawTriangle(this.net[n4][n5][0], this.net[n4][n5][1], this.net[n4][n5][2], this.net[n4 + 1][n5][0], this.net[n4 + 1][n5][1], this.net[n4 + 1][n5][2], this.net[n4 + 1][n5 + 1][0], this.net[n4 + 1][n5 + 1][1], this.net[n4 + 1][n5 + 1][2]);
                                        this.drawTriangle(this.net[n4][n5][0], this.net[n4][n5][1], this.net[n4][n5][2], this.net[n4 + 1][n5 + 1][0], this.net[n4 + 1][n5 + 1][1], this.net[n4 + 1][n5 + 1][2], this.net[n4][n5 + 1][0], this.net[n4][n5 + 1][1], this.net[n4][n5 + 1][2]);
                                    }
                                }
                            }
                        }
                    }
                    continue;
                }
                case 38: {
                    final int int3 = this.dis.readInt();
                    if (this.out == null || this.out.length != int3) {
                        this.out = new int[int3];
                    }
                    for (int n12 = 0; n12 < int3; ++n12) {
                        this.out[n12] = this.dis.readInt();
                    }
                    continue;
                }
                case 39: {
                    for (int int4 = this.dis.readInt(), n13 = 0; n13 < int4; ++n13) {
                        this.dis.readShort();
                        this.dis.readInt();
                        this.drawPoligonal3D(this.dis, this.dis.readInt(), this.fill_color, this.color, this.fill, this.wired, true, true);
                    }
                    continue;
                }
                case 5: {
                    final float float7 = this.dis.readFloat();
                    final float float8 = this.dis.readFloat();
                    final float float9 = this.dis.readFloat();
                    final String utf = this.dis.readUTF();
                    ++n;
                    n2 = 1;
                    if (!this.plot.doNotPlot) {
                        this.drawText3D(float7, float8, float9, utf, n);
                        continue;
                    }
                    continue;
                }
                case 6: {
                    this.canvas.currentName = this.dis.readUTF();
                    this.plot.overFound = false;
                    n = 0;
                    continue;
                }
                case -16377: {
                    if (n2 == 0) {
                        this.showMouseOverLabel();
                    }
                    n2 = 0;
                    this.canvas.clearCurrent();
                    this.show_label = false;
                    this.moveable = true;
                    this.setDefaultAttributes();
                    this.plot.view = view;
                    continue;
                }
                case 8: {
                    this.canvas.currentLabel = this.dis.readUTF();
                    continue;
                }
                case 21: {
                    this.canvas.currentValue = this.dis.readUTF();
                    continue;
                }
                case 22: {
                    this.canvas.currentDefinition = this.dis.readUTF();
                    continue;
                }
                case -16361: {
                    this.show_label = true;
                    continue;
                }
                case -16375: {
                    this.moveable = false;
                    continue;
                }
                case 16408: {
                    this.fill = (this.dis.readShort() == 1);
                    continue;
                }
                case -32743: {
                    this.fill_color = this.dis.readInt();
                    continue;
                }
                case -32742: {
                    this.color = this.dis.readInt();
                    continue;
                }
                case -32741: {
                    this.font = this.dis.readInt();
                    continue;
                }
                case -32740: {
                    this.ratiusLine = this.dis.readFloat();
                    continue;
                }
                case -32739: {
                    this.ratiusPoint = this.dis.readFloat();
                    continue;
                }
                case 16419: {
                    this.text_horz = this.dis.readShort();
                    continue;
                }
                case 16420: {
                    this.text_vert = this.dis.readShort();
                    continue;
                }
                case -32737: {
                    this.text_border = this.dis.readInt();
                    continue;
                }
                case -32736: {
                    this.text_border_color = this.dis.readInt();
                    continue;
                }
                case -16351: {
                    this.text_background = true;
                    continue;
                }
                case -32734: {
                    this.text_background_color = this.dis.readInt();
                    continue;
                }
                case 16429: {
                    this.wired = (this.dis.readShort() == 1);
                    continue;
                }
                case -32722: {
                    this.transparency = this.dis.readFloat();
                    if (this.transparency > 1.0f) {
                        this.transparency = 1.0f;
                    }
                    if (this.transparency < 0.0f) {
                        this.transparency = 0.0f;
                    }
                    this.transparency = 1.0f - this.transparency;
                    continue;
                }
                case -32720: {
                    if (this.dis.readInt() != 0) {
                        this.plot.view = new float[][] { { 1.0f, 0.0f, 0.0f, this.cwidth }, { 0.0f, 1.0f, 0.0f, this.cheight }, { 0.0f, 0.0f, 1.0f, 0.0f }, { 0.0f, 0.0f, 0.0f, 1.0f } };
                        continue;
                    }
                    continue;
                }
                default: {
                    for (int n14 = 0; n14 < wvfSize; ++n14) {
                        this.dis.readByte();
                    }
                    continue;
                }
            }
        }
        this.dis.reset();
    }
    
    private int wvfSize(final short n, final DataInputStream dataInputStream) {
        switch ((short)(n & 0xFFFFC000)) {
            case -16384: {
                return 0;
            }
            case 16384: {
                return 2;
            }
            case Short.MIN_VALUE: {
                return 4;
            }
            default: {
                return dataInputStream.readInt();
            }
        }
    }
    
    private void drawPoligonal3D(final DataInputStream dataInputStream, final int n, final int n2, final int n3, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (n <= 0) {
            return;
        }
        final float float1 = dataInputStream.readFloat();
        final float float2 = dataInputStream.readFloat();
        final float float3 = dataInputStream.readFloat();
        if (n <= 1) {
            return;
        }
        float float4 = dataInputStream.readFloat();
        float float5 = dataInputStream.readFloat();
        float float6 = dataInputStream.readFloat();
        float float7 = 0.0f;
        float float8 = 0.0f;
        float float9 = 0.0f;
        float n4 = float1 + float4;
        float n5 = float2 + float5;
        float n6 = float3 + float6;
        final int[] array = (n2 > -1) ? this.colors[n2] : this.colorSurface;
        final int[] array2 = (n3 > -1) ? this.colors[n3] : this.colorLine;
        if (b2) {
            this.setColor(array2, this.transparency);
            if (b3 && !this.plot.doNotPlot) {
                this.plot.drawLine(float1, float2, float3, float4, float5, float6);
            }
            else {
                Geometry3d.drawSegment(this.plot, float1, float2, float3, float4, float5, float6, this.ratiusLine);
            }
        }
        for (int i = 2; i < n; ++i) {
            float7 = dataInputStream.readFloat();
            float8 = dataInputStream.readFloat();
            float9 = dataInputStream.readFloat();
            n4 += float7;
            n5 += float8;
            n6 += float9;
            if (b) {
                this.setColor(array, this.transparency);
                this.drawTriangle(float1, float2, float3, float4, float5, float6, float7, float8, float9);
            }
            if (b2) {
                this.setColor(array2, this.transparency);
                if (b3 && !this.plot.doNotPlot) {
                    this.plot.drawLine(float4, float5, float6, float7, float8, float9);
                }
                else {
                    Geometry3d.drawSegment(this.plot, float4, float5, float6, float7, float8, float9, this.ratiusLine);
                }
            }
            float4 = float7;
            float5 = float8;
            float6 = float9;
        }
        if (n > 2 && b4 && b2) {
            this.setColor(array2, this.transparency);
            if (b3 && !this.plot.doNotPlot) {
                this.plot.drawLine(float7, float8, float9, float1, float2, float3);
            }
            else {
                Geometry3d.drawSegment(this.plot, float7, float8, float9, float1, float2, float3, this.ratiusLine);
            }
        }
        if (this.show_label) {
            this.showLabel(n4 / n, n5 / n, n6 / n);
        }
    }
    
    private void drawPoint3D(final float n, final float n2, final float n3) {
        boolean pointSelected = this.canvas.isPointSelected(this.canvas.currentName);
        this.drawPoint3Dcolor = ((this.color > -1) ? this.colors[this.color] : this.colorPoint);
        final Object[] pointImage = this.getPointImage(this.ratiusPoint, false, this.drawPoint3Dcolor);
        this.Composa.preparePixels(this.plot, false, n, n2, n3, pointImage);
        if (this.canvas.mouseState == 1 && this.action == 1 && this.plot.overFound && this.moveable && this.plot.over_z < this.zMax) {
            if (!this.canvas.thereIsSelectedPoint()) {
                this.canvas.setSelectedPoint(this.canvas.currentName, new float[] { n, n2, n3 });
            }
            this.zPoint = this.plot.over_z + this.ratiusPoint / 2.0f;
            pointSelected = true;
        }
        if (!pointSelected) {
            this.canvas.setPointIfUndefined(this.canvas.currentName, new float[] { n, n2, n3 });
        }
        if (this.moveable && (this.plot.overFound || pointSelected)) {
            if (!pointSelected) {
                this.Composa.drawPixelsPoint(this.plot, pointSelected, n, n2, n3, this.getPointImage(this.ratiusPoint + 4.0f, true, this.drawPoint3Dcolor));
            }
            else {
                this.movedPoint = this.getPointImage(this.ratiusPoint + 4.0f, true, this.drawPoint3Dcolor);
            }
        }
        else if (!this.plot.doNotPlot) {
            this.Composa.drawPreparedPixels(this.plot, pointImage);
        }
        if (!pointSelected && this.show_label && !this.plot.doNotPlot) {
            this.showLabel(n, n2, n3);
        }
    }
    
    public final void drawMovedPoint() {
        this.Composa.drawPixelsPoint(this.plot, true, this.plot.over_x, this.plot.over_y, this.plot.over_z, this.movedPoint);
    }
    
    private Object[] getPointImage(final float n, final boolean b, final int[] array) {
        Object[] array2 = null;
        final int arrayColorToInt = arrayColorToInt(array);
        for (int i = 0; i < this.pImg.size(); ++i) {
            final double[] array3 = (double[])((Object[])this.pImg.elementAt(i))[0];
            if (array3[0] == n && array3[1] == arrayColorToInt && array3[2] == (b ? 0 : 1)) {
                array2 = (Object[])((Object[])this.pImg.elementAt(i))[1];
                break;
            }
        }
        if (array2 == null) {
            final int n2 = (int)n;
            final float n3 = (n2 - 1) / 2.0f;
            final int[] array4 = new int[n2 * n2];
            final float n4 = n2 * n2 / 4.0f;
            array2 = new Object[] { new Integer(n2), new Integer(n2), array4 };
            int n5 = 0;
            this.setColor(array, this.transparency);
            for (int j = 0; j < n2; ++j) {
                for (int k = 0; k < n2; ++k) {
                    final float n6 = n4 - ((n3 - j) * (n3 - j) + (n3 - k) * (n3 - k));
                    if (n6 > 0.0f) {
                        if (!b) {
                            array4[n5] = this.plot.getColor();
                        }
                        else {
                            array4[n5] = GraphicsUtils.colorMig(new Color(arrayColorToInt(this.drawPoint3Dcolor)), new Color(arrayColorToInt(this.drawPoint3Dcolor) ^ this.getBackColor()), (int)n6, (int)(n4 - n6)).getRGB();
                        }
                    }
                    else {
                        final int[] array5 = array4;
                        final int n7 = n5;
                        this.plot.getClass();
                        array5[n7] = 16777215;
                    }
                    ++n5;
                }
            }
            this.pImg.addElement(new Object[] { { n, arrayColorToInt, b ? 0.0 : 1.0 }, array2 });
        }
        return array2;
    }
    
    private void drawText3D(final float n, final float n2, final float n3, final String s, final int n4) {
        short n5 = 0;
        switch (this.text_horz) {
            case 1: {
                final Plot3d plot = this.plot;
                n5 = 1;
                break;
            }
            case 2: {
                final Plot3d plot2 = this.plot;
                n5 = 2;
                break;
            }
            case 5: {
                final Plot3d plot3 = this.plot;
                n5 = 3;
                break;
            }
        }
        switch (this.text_vert) {
            case 4: {
                final short n6 = n5;
                final Plot3d plot4 = this.plot;
                n5 = (short)(n6 | 0x20);
                break;
            }
            case 3: {
                final short n7 = n5;
                final Plot3d plot5 = this.plot;
                n5 = (short)(n7 | 0x10);
                break;
            }
            case 5: {
                final short n8 = n5;
                final Plot3d plot6 = this.plot;
                n5 = (short)(n8 | 0x30);
                break;
            }
        }
        AbstractBox abstractBox = null;
        if (n4 == 1) {
            abstractBox = this.canvas.getBox(this.canvas.currentName);
        }
        if (abstractBox == null) {
            abstractBox = this.formula.parse(s);
            abstractBox.ImposaColor(Plot3dStream.TEXT_COLOR);
            abstractBox.ImposaFontPropia((this.font > -1) ? this.fonts[this.font] : this.textFont);
            abstractBox.Composa(this.formula);
            if (n4 == 1) {
                this.canvas.setBox(this.canvas.currentName, abstractBox);
            }
        }
        this.plot.drawPixels(n, n2, n3, this.getTextImage(abstractBox, this.font, this.color, this.transparency, this.text_background, this.text_background_color, this.text_border, this.text_border_color), n5);
        if (this.plot.pixelsSet) {
            abstractBox.x = this.plot.pixelsX;
            abstractBox.y = this.plot.pixelsY;
        }
        if (this.action == 1 && this.plot.overFound && this.canvas.mouseState == 1 && !this.canvas.thereIsSelectedPoint()) {
            this.canvas.setSelectedPoint(this.canvas.currentName);
        }
        this.text_horz = 1;
        this.text_vert = 4;
        this.text_border = 1;
        this.text_border_color = -1;
        this.text_background = false;
        this.text_background_color = -1;
    }
    
    public final Object[] getTextImage(final Object o, final int n, final int n2, final float n3, final boolean b, final int n4, final int n5, final int n6) {
        Object[] prepareImage = null;
        for (int i = 0; i < this.tImg.size(); ++i) {
            final int[] array = (int[])((Object[])this.tImg.elementAt(i))[1];
            final boolean[] array2 = (boolean[])((Object[])this.tImg.elementAt(i))[2];
            if (((Object[])this.tImg.elementAt(i))[0].equals(o) && array[0] == n && array[1] == n2 && array2[0] == b && array[2] == n4 && array[3] == n5 && array[4] == n6) {
                prepareImage = (Object[])((Object[])this.tImg.elementAt(i))[3];
                break;
            }
        }
        if (prepareImage == null) {
            final int[] array3 = (n2 > -1) ? this.colors[n2] : this.colorText;
            final int[] array4 = (n4 > -1) ? this.colors[n4] : Plot3dStream.WHITE;
            final int[] array5 = (n6 > -1) ? this.colors[n6] : array3;
            prepareImage = this.prepareImage(this.label2Image(o, (n > -1) ? this.fonts[n] : this.textFont, array3, this.canvas, b, n5, array4, array5), array3, n3, b, n5, array4, array5);
            this.tImg.addElement(new Object[] { o, { n, n2, n4, n5, n6 }, { b }, prepareImage });
        }
        return prepareImage;
    }
    
    private Object[] prepareImage(final Image image, final int[] array, final float n, final boolean b, final int n2, final int[] array2, final int[] array3) {
        if (image == null) {
            return null;
        }
        final Plot3d plot = this.plot;
        final Object[] compileImage = Plot3d.compileImage(image);
        if (OmegaSystem.versio_java == 1) {
            this.setTextImageColor(compileImage, array, n, b, array2, array3);
        }
        else {
            this.setTransparency(compileImage, n);
        }
        return compileImage;
    }
    
    public final void clearCacheTextImage(final Object o) {
        for (int i = this.tImg.size() - 1; i >= 0; --i) {
            if (((Object[])this.tImg.elementAt(i))[0].equals(o)) {
                this.tImg.remove(i);
            }
        }
    }
    
    private void setTextImageColor(final Object[] array, final int[] array2, final float n, final boolean b, final int[] array3, final int[] array4) {
        final int rgb = Plot3dStream.TEXT_COLOR.getRGB();
        final int rgb2 = Plot3dStream.BACK_COLOR.getRGB();
        final int rgb3 = Plot3dStream.BORDER_COLOR.getRGB();
        final int n2 = (int)(255.0f * n) << 24 | array2[0] << 16 | array2[1] << 8 | array2[2];
        int n3;
        if (b) {
            n3 = ((int)(255.0f * n) << 24 | array3[0] << 16 | array3[1] << 8 | array3[2]);
        }
        else {
            this.plot.getClass();
            n3 = 16777215;
        }
        final int n4 = n3;
        final int n5 = (array4 != null) ? ((int)(255.0f * n) << 24 | array4[0] << 16 | array4[1] << 8 | array4[2]) : n2;
        final int[] array5 = (int[])array[2];
        for (int i = 0; i < array5.length; ++i) {
            final int n6 = array5[i];
            if (n6 == rgb2) {
                array5[i] = n4;
            }
            else if (n6 == rgb3) {
                array5[i] = n5;
            }
            else if (n6 == rgb) {
                array5[i] = n2;
            }
            else {
                array5[i] = (b ? this.mixColor(n2, n4, this.getAntialiasing(n6, rgb)) : ((n2 & 0xFFFFFF) | (int)(this.getAntialiasing(n6, rgb) * n) << 24));
            }
        }
    }
    
    private void setTransparency(final Object[] array, final float n) {
        final int[] array2 = (int[])array[2];
        for (int i = 0; i < array2.length; ++i) {
            final int n2 = array2[i];
            array2[i] = (n2 & 0xFFFFFF) + ((int)((n2 >> 24 & 0xFF) * n) << 24);
        }
    }
    
    private int mixColor(final int n, final int n2, final int n3) {
        final float n4 = n3 / 255.0f;
        return (int)((n >> 24 & 0xFF) * n4 + (n2 >> 24 & 0xFF) * (1.0f - n4)) << 24 | (int)((n >> 16 & 0xFF) * n4 + (n2 >> 16 & 0xFF) * (1.0f - n4)) << 16 | (int)((n >> 8 & 0xFF) * n4 + (n2 >> 8 & 0xFF) * (1.0f - n4)) << 8 | (int)((n & 0xFF) * n4 + (n2 & 0xFF) * (1.0f - n4));
    }
    
    private int getAntialiasing(final int n, final int n2) {
        return 255 - (((n & 0xFF0000) >> 16 & 0xFF) - ((n2 & 0xFF0000) >> 16 & 0xFF) + ((n & 0xFF00) >> 8 & 0xFF) - ((n2 & 0xFF00) >> 8 & 0xFF) + (n & 0xFF) - (n2 & 0xFF)) / 3;
    }
    
    private static Image createImage(final int n, final int n2, final Component component, final boolean b, final int n3, final int[] array, final int[] array2) {
        final Image image = IsGraphics2D.createImage(component, n + 2 * n3 + 2, n2 + 2 * n3);
        final Graphics graphics = image.getGraphics();
        if (OmegaSystem.versio_java >= 2) {
            IsGraphics2D.setAntialiasing(graphics, true, OmegaSystem.versio_java);
        }
        if (n3 > 0) {
            if (OmegaSystem.versio_java == 1) {
                graphics.setColor(Plot3dStream.BORDER_COLOR);
                graphics.fillRect(0, 0, n + 2 * n3 + 2, n2 + 2 * n3);
            }
            else {
                graphics.setColor(new Color(array2[0], array2[1], array2[2]));
                graphics.fillRect(0, 0, n + 2 * n3 + 2, n3);
                graphics.fillRect(0, n2 + n3, n + 2 * n3 + 2, n3);
                graphics.fillRect(0, 0, n3, n2 + 2 * n3);
                graphics.fillRect(n + n3 + 2, 0, n3, n2 + 2 * n3);
            }
        }
        if (OmegaSystem.versio_java == 1) {
            graphics.fillRect(n3, n3, n + 2, n2);
            graphics.setColor(Plot3dStream.BACK_COLOR);
        }
        else if (b) {
            graphics.setColor(new Color(array[0], array[1], array[2]));
            graphics.fillRect(n3, n3, n + 2, n2);
        }
        if (OmegaSystem.versio_java == 1) {
            graphics.setColor(Plot3dStream.TEXT_COLOR);
        }
        return image;
    }
    
    private Image text2Image(final String s, final Font font, final int[] array, final Component component, final boolean b, final int n, final int[] array2, final int[] array3) {
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(s);
        if (stringWidth + 2 * n == 0 || height + 2 * n == 0) {
            return null;
        }
        final Image image = createImage(stringWidth, height, component, b, n, array2, array3);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        if (OmegaSystem.versio_java == 1) {
            graphics.setColor(Plot3dStream.TEXT_COLOR);
        }
        else {
            graphics.setColor(new Color(array[0], array[1], array[2]));
        }
        graphics.drawString(s, n, fontMetrics.getAscent() + n);
        return image;
    }
    
    private Image label2Image(final Object o, final Font font, final int[] array, final Component component, final boolean b, final int y, final int[] array2, final int[] array3) {
        AbstractBox parse;
        if (o instanceof String) {
            parse = this.formula.parse((String)o);
            parse.ImposaColor(Plot3dStream.TEXT_COLOR);
            parse.ImposaFontPropia(font);
            parse.Composa(this.formula);
        }
        else {
            if (!(o instanceof AbstractBox)) {
                throw new Error("Expected String or AbstractBox.");
            }
            parse = (AbstractBox)o;
            if (parse instanceof Slider) {
                ((Slider)parse).setColor(GraphicsUtils.colorToString(GraphicsUtils.colorDissimulat(new Color(array[0], array[1], array[2]))));
            }
        }
        if (OmegaSystem.versio_java >= 2) {
            parse.ImposaColor(new Color(array[0], array[1], array[2]));
        }
        final Image image = createImage(parse.width, parse.height, component, b, y, array2, array3);
        final Graphics graphics = image.getGraphics();
        GraphicsUtils.setRendering(graphics, false);
        GraphicsUtils.setLineWidth(graphics, 1.0f);
        parse.x = y + 1;
        parse.y = y;
        parse.DrawRectCorrecte(this.formula, graphics);
        GraphicsUtils.setRendering(graphics, true);
        return image;
    }
    
    private void setDefaultAttributes() {
        this.ratiusPoint = 5.0f;
        this.ratiusLine = 2.0f;
        this.color = -1;
        this.font = -1;
        this.fill = false;
        this.fill_color = -1;
        this.wired = false;
        this.transparency = 1.0f;
    }
    
    public final synchronized void draw() {
        final float[][] view = this.plot.view;
        try {
            this.plot.view = this.multiplyViewMatrix();
            this.plot.init(this.cwidth, this.cheight, this.getBackColor());
            try {
                if (this.mMoved) {
                    this.plot.setOver = true;
                }
                this.plot.doNotPlot = false;
                this.drawStream();
                this.plot.setOver = false;
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            if (!this.plot.doNotPlot) {
                if (this.showCube) {
                    this.setColor(this.cubeColor, 1.0f);
                    this.drawCube(this.oCube[0], this.oCube[1], this.oCube[2], this.vxCube, this.vyCube, this.vzCube);
                }
                if (this.showCoorAxis) {
                    this.drawAxis();
                }
            }
            if (this.label != null) {
                final Object[] textImage = this.getTextImage(this.label, this.showLabelFont, -1, 1.0f, true, -1, 1, this.showLabelColor);
                final int intValue = (int)textImage[0];
                int n = (int)textImage[1];
                int over_x;
                if (this.cwidth < this.plot.over_x + intValue) {
                    over_x = this.cwidth - intValue;
                }
                else {
                    over_x = this.plot.over_x;
                }
                if (this.plot.over_y > n) {
                    n = this.plot.over_y;
                }
                final Plot3d plot = this.plot;
                final float n2 = over_x;
                final float n3 = n;
                final float n4 = -3.4028235E38f;
                final Object[] array = textImage;
                final Plot3d plot2 = this.plot;
                final int n5 = 16;
                final Plot3d plot3 = this.plot;
                plot.drawPixelsPrim(n2, n3, n4, array, (short)(n5 | 0x2));
            }
        }
        finally {
            this.plot.view = view;
        }
    }
    
    public final int getBackColor() {
        return arrayColorToInt(this.backColor);
    }
    
    public static final int arrayColorToInt(final int[] array) {
        int n = 255;
        if (array.length > 3) {
            n = array[3];
        }
        return n << 24 | array[0] << 16 | array[1] << 8 | array[2];
    }
    
    public final float[][] multiplyViewMatrix() {
        final Plot3d plot = this.plot;
        final float[][] multiplyMatrix = Plot3d.multiplyMatrix(this.plot.view, this.coorTr);
        final Plot3d plot2 = this.plot;
        final float[][] multiplyMatrix2 = Plot3d.multiplyMatrix(this.cTr, multiplyMatrix);
        final Plot3d plot3 = this.plot;
        return Plot3d.multiplyMatrix(this.proj, multiplyMatrix2);
    }
    
    private void setColor(final int[] array, final float n) {
        this.plot.setColor(array[0], array[1], array[2], (int)(n * 255.0f));
    }
    
    private void showLabel(final float n, final float n2, final float n3) {
        if (this.plot.doNotPlot) {
            return;
        }
        this.plot.setOver = false;
        this.plot.drawPixels(n, n2, n3, this.getTextImage(this.canvas.getDisplayLabel(), this.font, -1, 1.0f, true, -1, 1, -1));
        if (this.mMoved) {
            this.plot.setOver = true;
        }
        this.show_label = false;
    }
    
    private void drawCube(final float n, final float n2, final float n3, final float[] array, final float[] array2, final float[] array3) {
        this.plot.drawLine(n, n2, n3, n + array[0], n2 + array[1], n3 + array[2]);
        this.plot.drawLine(n + array[0], n2 + array[1], n3 + array[2], n + array[0] + array2[0], n2 + array[1] + array2[1], n3 + array[2] + array2[2]);
        this.plot.drawLine(n + array[0] + array2[0], n2 + array[1] + array2[1], n3 + array[2] + array2[2], n + array2[0], n2 + array2[1], n3 + array2[2]);
        this.plot.drawLine(n + array2[0], n2 + array2[1], n3 + array2[2], n, n2, n3);
        this.plot.drawLine(n + array3[0], n2 + array3[1], n3 + array3[2], n + array3[0] + array[0], n2 + array3[1] + array[1], n3 + array3[2] + array[2]);
        this.plot.drawLine(n + array[0] + array3[0], n2 + array[1] + array3[1], n3 + array[2] + array3[2], n + array[0] + array2[0] + array3[0], n2 + array[1] + array2[1] + array3[1], n3 + array[2] + array2[2] + array3[2]);
        this.plot.drawLine(n + array[0] + array2[0] + array3[0], n2 + array[1] + array2[1] + array3[1], n3 + array[2] + array2[2] + array3[2], n + array2[0] + array3[0], n2 + array2[1] + array3[1], n3 + array2[2] + array3[2]);
        this.plot.drawLine(n + array2[0] + array3[0], n2 + array2[1] + array3[1], n3 + array2[2] + array3[2], n + array3[0], n2 + array3[1], n3 + array3[2]);
        this.plot.drawLine(n, n2, n3, n + array3[0], n2 + array3[1], n3 + array3[2]);
        this.plot.drawLine(n + array[0], n2 + array[1], n3 + array[2], n + array[0] + array3[0], n2 + array[1] + array3[1], n3 + array[2] + array3[2]);
        this.plot.drawLine(n + array[0] + array2[0], n2 + array[1] + array2[1], n3 + array[2] + array2[2], n + array[0] + array2[0] + array3[0], n2 + array[1] + array2[1] + array3[1], n3 + array[2] + array2[2] + array3[2]);
        this.plot.drawLine(n + array2[0], n2 + array2[1], n3 + array2[2], n + array2[0] + array3[0], n2 + array2[1] + array3[1], n3 + array2[2] + array3[2]);
    }
    
    private void computeXAxis(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float n, final int n2, final int n3, final int n4) {
        float n5 = this.oCube[n3];
        float n6 = this.oCube[n3] + array3[n3];
        if (n5 > n6) {
            n5 = (n6 = n6);
        }
        if (0.0f < n5) {
            array[n3] = n5;
        }
        else if (0.0f > n6) {
            array[n3] = n6;
        }
        else {
            array[n3] = 0.0f;
        }
        float n7 = this.oCube[n4];
        float n8 = this.oCube[n4] + array4[n4];
        if (n7 > n8) {
            n7 = (n8 = n8);
        }
        if (0.0f < n7) {
            array[n4] = n7;
        }
        else if (0.0f > n8) {
            array[n4] = n8;
        }
        else {
            array[n4] = 0.0f;
        }
        float n9 = this.oCube[n2];
        final float n10 = this.oCube[n2] + array2[n2];
        if (n9 > n10) {
            n9 = n10;
        }
        array[n2] = n9;
    }
    
    private Object[] computeTicks(final float[] array, final int n, final float n2, final float n3) {
        final Vector vector = new Vector<Object[]>();
        float n4 = n3 * (int)(array[n] / n3);
        if (n4 <= array[n]) {
            n4 += n3;
        }
        while (n4 < array[n] + n2) {
            if (Math.abs(n4) >= n3 / 2.0f) {
                final Object[] prepareImage = this.prepareImage(this.text2Image(PlotCanvas.double2String(n4, n3, false), this.axisFont, this.axisColor, this.canvas, false, 0, null, null), this.axisColor, 1.0f, false, 0, null, null);
                if (prepareImage == null) {
                    continue;
                }
                final float[] array2 = { array[0], array[1], array[2] };
                array2[n] = n4;
                vector.addElement(new Object[] { prepareImage, array2 });
            }
            n4 += n3;
        }
        final Object[] array3 = new Object[vector.size()];
        vector.copyInto(array3);
        return array3;
    }
    
    private void drawTicks(final Object[] array) {
        for (int i = 0; i < array.length; ++i) {
            final float[] array2 = (float[])((Object[])array[i])[1];
            final Plot3d plot = this.plot;
            final float n = array2[0];
            final float n2 = array2[1];
            final float n3 = array2[2];
            final Object[] tick = this.tick;
            final Plot3d plot2 = this.plot;
            final int n4 = 3;
            final Plot3d plot3 = this.plot;
            plot.drawPixels(n, n2, n3, tick, (short)(n4 | 0x30));
            final Plot3d plot4 = this.plot;
            final float n5 = array2[0];
            final float n6 = array2[1];
            final float n7 = array2[2];
            final Object[] array3 = (Object[])((Object[])array[i])[0];
            final Plot3d plot5 = this.plot;
            final int n8 = 2;
            final Plot3d plot6 = this.plot;
            plot4.drawPixels(n5, n6, n7, array3, (short)(n8 | 0x20));
        }
    }
    
    private void computeAxis() {
        if (this.computeAxis) {
            this.computeXAxis(this.oXAxis, this.vxCube, this.vyCube, this.vzCube, this.xCubeSize, 0, 1, 2);
            this.computeXAxis(this.oYAxis, this.vyCube, this.vzCube, this.vxCube, this.yCubeSize, 1, 2, 0);
            this.computeXAxis(this.oZAxis, this.vzCube, this.vxCube, this.vyCube, this.zCubeSize, 2, 0, 1);
            final float n = (this.cTr[2][3] / this.zoom * this.rProj + 1.0f) / ((this.cTr[2][3] * this.rProj + 1.0f) * 0.8f);
            this.xAxisScale = this.calculateScale(this.xScale * n);
            this.yAxisScale = this.calculateScale(this.yScale * n);
            this.zAxisScale = this.calculateScale(this.zScale * n);
            this.xTickValues = this.computeTicks(this.oXAxis, 0, this.xCubeSize, this.xAxisScale);
            this.yTickValues = this.computeTicks(this.oYAxis, 1, this.yCubeSize, this.yAxisScale);
            this.zTickValues = this.computeTicks(this.oZAxis, 2, this.zCubeSize, this.zAxisScale);
            this.computeAxis = false;
        }
    }
    
    private void drawAxis() {
        this.setColor(this.axisColor, 1.0f);
        this.computeAxis();
        if (this.tick == null) {
            final int n = 3;
            final int[] array = new int[n * n];
            final int color = this.plot.getColor();
            for (int i = 0; i < array.length; ++i) {
                array[i] = color;
            }
            this.tick = new Object[] { new Integer(n), new Integer(n), array };
            final String[] array2 = { "X", "Y", "Z" };
            this.axisLabels = new Object[3][];
            for (int j = 0; j < 3; ++j) {
                this.axisLabels[j] = this.prepareImage(this.text2Image(array2[j], this.axisFont, this.axisColor, this.canvas, false, 0, null, null), this.axisColor, 1.0f, false, 0, null, null);
            }
        }
        this.plot.drawLine(this.oXAxis[0] - this.xCubeSize * 0.1f, this.oXAxis[1], this.oXAxis[2], this.oXAxis[0] + this.xCubeSize * 1.1f, this.oXAxis[1], this.oXAxis[2]);
        final Plot3d plot = this.plot;
        final float n2 = this.oXAxis[0] + this.xCubeSize * 1.1f;
        final float n3 = this.oXAxis[1];
        final float n4 = this.oXAxis[2];
        final Object[] array3 = this.axisLabels[0];
        final Plot3d plot2 = this.plot;
        final int n5 = 3;
        final Plot3d plot3 = this.plot;
        plot.drawPixels(n2, n3, n4, array3, (short)(n5 | 0x10));
        this.plot.drawLine(this.oYAxis[0], this.oYAxis[1] - this.yCubeSize * 0.1f, this.oYAxis[2], this.oYAxis[0], this.oYAxis[1] + this.yCubeSize * 1.1f, this.oYAxis[2]);
        final Plot3d plot4 = this.plot;
        final float n6 = this.oYAxis[0];
        final float n7 = this.oYAxis[1] + this.yCubeSize * 1.1f;
        final float n8 = this.oYAxis[2];
        final Object[] array4 = this.axisLabels[1];
        final Plot3d plot5 = this.plot;
        final int n9 = 3;
        final Plot3d plot6 = this.plot;
        plot4.drawPixels(n6, n7, n8, array4, (short)(n9 | 0x10));
        this.plot.drawLine(this.oZAxis[0], this.oZAxis[1], this.oZAxis[2] - this.zCubeSize * 0.1f, this.oZAxis[0], this.oZAxis[1], this.oZAxis[2] + this.zCubeSize * 1.1f);
        final Plot3d plot7 = this.plot;
        final float n10 = this.oZAxis[0];
        final float n11 = this.oZAxis[1];
        final float n12 = this.oZAxis[2] + this.zCubeSize * 1.1f;
        final Object[] array5 = this.axisLabels[2];
        final Plot3d plot8 = this.plot;
        final int n13 = 3;
        final Plot3d plot9 = this.plot;
        plot7.drawPixels(n10, n11, n12, array5, (short)(n13 | 0x10));
        this.drawTicks(this.xTickValues);
        this.drawTicks(this.yTickValues);
        this.drawTicks(this.zTickValues);
    }
    
    private float calculateScale(final float n) {
        final double n2 = 36.0f / n * 1.4;
        final double pow = Math.pow(10.0, (int)Math.floor(Math.log(n2) / Math.log(10.0)));
        int n3 = (int)Math.round(n2 / pow);
        if (n3 == 4) {
            n3 = 5;
        }
        if (n3 == 6) {
            n3 = 5;
        }
        if (n3 == 7) {
            n3 = 5;
        }
        if (n3 == 8) {
            n3 = 10;
        }
        if (n3 == 9) {
            n3 = 10;
        }
        return (float)(n3 * pow);
    }
    
    public final void free_resources() {
        this.dis = null;
        if (this.plot != null) {
            this.plot.free_resources();
        }
        this.plot = null;
        this.canvas = null;
        this.formula = null;
        this.net = null;
    }
    
    public final DataInputStream getData() {
        return this.dis;
    }
    
    public final void setData(final DataInputStream dis) {
        this.tImg.removeAllElements();
        this.canvas.clearBoxes();
        this.dis = dis;
    }
    
    public final FontMetrics getFontMetrics(final Font font) {
        return this.formula.getFontMetrics(font);
    }
    
    static {
        WHITE = new int[] { 255, 255, 255 };
        BLACK = new int[] { 0, 0, 0 };
        BACK_COLOR = new Color(255, 255, 255);
        TEXT_COLOR = new Color(0, 0, 0);
        BORDER_COLOR = new Color(255, 0, 0);
    }
}
