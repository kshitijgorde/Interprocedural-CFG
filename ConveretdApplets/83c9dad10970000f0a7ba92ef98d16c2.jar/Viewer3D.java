import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Polygon;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

final class Viewer3D extends Viewer implements Runnable
{
    int OFFSETX;
    int OFFSETY;
    int radius;
    Color[][] fgColor;
    float[][] eye;
    final float[][] coordVec;
    final int numAnimSteps = 16;
    float[][][][] animVec;
    final int[][] facelets;
    Polygon[] faceletsO;
    float[][] moveDirs;
    boolean eventType;
    boolean twisting;
    int twistMove;
    int twistDir;
    float[][][] animEye;
    int anim;
    int[] axisOrder;
    float[][][] statEye;
    float[] ccorn;
    float[] vr;
    int[] rectx;
    int[] recty;
    final int[][] moves;
    
    public Viewer3D(final int n, final int n2, final ActionListener actionListener) {
        super(n, n2, actionListener);
        this.fgColor = new Color[8][20];
        this.eye = new float[3][3];
        this.coordVec = new float[][] { { 1.0f, 0.0f, 0.0f }, { 0.0f, 1.0f, 0.0f }, { 0.0f, 0.0f, 1.0f }, { -1.0f, 0.0f, 0.0f }, { 0.0f, -1.0f, 0.0f }, { 0.0f, 0.0f, -1.0f } };
        this.animVec = new float[6][33][3][3];
        this.facelets = new int[][] { { 0, 9, -1, -1, -1, 47 }, { -1, 10, -1, -1, -1, 46 }, { -1, 11, -1, 29, -1, 45 }, { 1, 12, -1, -1, -1, -1 }, { -1, 13, -1, -1, -1, -1 }, { -1, 14, -1, 28, -1, -1 }, { 2, 15, 18, -1, -1, -1 }, { -1, 16, 19, -1, -1, -1 }, { -1, 17, 20, 27, -1, -1 }, { 3, -1, -1, -1, -1, 50 }, { -1, -1, -1, -1, -1, 49 }, { -1, -1, -1, 32, -1, 48 }, { 4, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, 31, -1, -1 }, { 5, -1, 21, -1, -1, -1 }, { -1, -1, 22, -1, -1, -1 }, { -1, -1, 23, 30, -1, -1 }, { 6, -1, -1, -1, 42, 53 }, { -1, -1, -1, -1, 43, 52 }, { -1, -1, -1, 35, 44, 51 }, { 7, -1, -1, -1, 39, -1 }, { -1, -1, -1, -1, 40, -1 }, { -1, -1, -1, 34, 41, -1 }, { 8, -1, 24, -1, 36, -1 }, { -1, -1, 25, -1, 37, -1 }, { -1, -1, 26, 33, 38, -1 } };
        this.faceletsO = new Polygon[54];
        this.moveDirs = new float[54][4];
        this.eventType = true;
        this.twisting = false;
        this.animEye = new float[3][3][3];
        this.axisOrder = new int[] { 0, 1, 2 };
        this.statEye = new float[][][] { this.eye, this.eye, this.eye };
        this.ccorn = new float[3];
        this.vr = new float[3];
        this.rectx = new int[6];
        this.recty = new int[6];
        this.moves = new int[][] { { -2, -2, -2, -8, -11, -8, 5, 5, 5, -6, -6, -6, 9, 12, 9, 3, 3, 3, -2, -2, -2, -8, -11, -8, 5, 5, 5, -2, -2, -2, -8, -11, -8, 5, 5, 5, -3, -3, -3, -9, -12, -9, 6, 6, 6, -2, -2, -2, -8, -11, -8, 5, 5, 5 }, { -6, 9, 3, -6, 12, 3, -6, 9, 3, -1, -7, 4, -1, -10, 4, -1, -7, 4, -1, -7, 4, -1, -10, 4, -1, -7, 4, -3, -9, 6, -3, -12, 6, -3, -9, 6, -1, -7, 4, -1, -10, 4, -1, -7, 4, -4, 7, 1, -4, 10, 1, -4, 7, 1 }, { 2, 2, 2, 8, 11, 8, -5, -5, -5, 6, 6, 6, -9, -12, -9, -3, -3, -3, 2, 2, 2, 8, 11, 8, -5, -5, -5, 2, 2, 2, 8, 11, 8, -5, -5, -5, 3, 3, 3, 9, 12, 9, -6, -6, -6, 2, 2, 2, 8, 11, 8, -5, -5, -5 }, { 6, -9, -3, 6, -12, -3, 6, -9, -3, 1, 7, -4, 1, 10, -4, 1, 7, -4, 1, 7, -4, 1, 10, -4, 1, 7, -4, 3, 9, -6, 3, 12, -6, 3, 9, -6, 1, 7, -4, 1, 10, -4, 1, 7, -4, 4, -7, -1, 4, -10, -1, 4, -7, -1 } };
        this.OFFSETX = n / 2;
        this.OFFSETY = n2 / 2;
        this.radius = n * 3 / 16;
        if (n2 < n) {
            this.radius = n2 * 3 / 16;
        }
        for (int i = 0; i < 20; ++i) {
            final int n3 = (40 - i) * i / 20;
            this.fgColor[0][i] = new Color(50 + 10 * n3, 0, 0);
            this.fgColor[1][i] = new Color(0, 0, 50 + 10 * n3);
            this.fgColor[2][i] = new Color(50 + 10 * n3, 50 + 10 * n3, 0);
            this.fgColor[3][i] = new Color(50 + 10 * n3, 30 + 5 * n3, 10 + 2 * n3);
            this.fgColor[4][i] = new Color(0, 50 + 6 * n3, 0);
            this.fgColor[5][i] = new Color(50 + 10 * n3, 50 + 10 * n3, 50 + 10 * n3);
            this.fgColor[6][i] = new Color(2 * n3, 2 * n3, 2 * n3);
            this.fgColor[7][i] = new Color(25 + 2 * n3, 25 + 2 * n3, 25 + 2 * n3);
        }
        for (int j = 0; j <= 16; ++j) {
            this.animVec[3][j][1][0] = 0.0f;
            this.animVec[3][j][1][1] = 16 - j;
            this.animVec[3][j][1][2] = -j;
            this.animVec[3][j + 16][1][0] = 0.0f;
            this.animVec[3][j + 16][1][1] = -j;
            this.animVec[3][j + 16][1][2] = -16 + j;
            this.animVec[4][j][0][0] = 16 - j;
            this.animVec[4][j][0][1] = 0.0f;
            this.animVec[4][j][0][2] = j;
            this.animVec[4][j + 16][0][0] = -j;
            this.animVec[4][j + 16][0][1] = 0.0f;
            this.animVec[4][j + 16][0][2] = 16 - j;
            this.animVec[2][j][0][0] = 16 - j;
            this.animVec[2][j][0][1] = -j;
            this.animVec[2][j][0][2] = 0.0f;
            this.animVec[2][j + 16][0][0] = -j;
            this.animVec[2][j + 16][0][1] = -16 + j;
            this.animVec[2][j + 16][0][2] = 0.0f;
            this.animVec[0][j][1][0] = 0.0f;
            this.animVec[0][j][1][1] = 16 - j;
            this.animVec[0][j][1][2] = j;
            this.animVec[0][j + 16][1][0] = 0.0f;
            this.animVec[0][j + 16][1][1] = -j;
            this.animVec[0][j + 16][1][2] = 16 - j;
            this.animVec[1][j][0][0] = 16 - j;
            this.animVec[1][j][0][1] = 0.0f;
            this.animVec[1][j][0][2] = -j;
            this.animVec[1][j + 16][0][0] = -j;
            this.animVec[1][j + 16][0][1] = 0.0f;
            this.animVec[1][j + 16][0][2] = -16 + j;
            this.animVec[5][j][0][0] = 16 - j;
            this.animVec[5][j][0][1] = j;
            this.animVec[5][j][0][2] = 0.0f;
            this.animVec[5][j + 16][0][0] = -j;
            this.animVec[5][j + 16][0][1] = 16 - j;
            this.animVec[5][j + 16][0][2] = 0.0f;
        }
        for (int k = 0; k <= 32; ++k) {
            this.normalize(this.animVec[0][k][1]);
            this.animVec[0][k][0][0] = 1.0f;
            this.animVec[0][k][0][1] = 0.0f;
            this.animVec[0][k][0][2] = 0.0f;
            this.vecProd(this.animVec[0][k][2], this.animVec[0][k][0], this.animVec[0][k][1]);
            this.normalize(this.animVec[1][k][0]);
            this.animVec[1][k][1][0] = 0.0f;
            this.animVec[1][k][1][1] = 1.0f;
            this.animVec[1][k][1][2] = 0.0f;
            this.vecProd(this.animVec[1][k][2], this.animVec[1][k][0], this.animVec[1][k][1]);
            this.normalize(this.animVec[2][k][0]);
            this.animVec[2][k][2][0] = 0.0f;
            this.animVec[2][k][2][1] = 0.0f;
            this.animVec[2][k][2][2] = 1.0f;
            this.vecProd(this.animVec[2][k][1], this.animVec[2][k][2], this.animVec[2][k][0]);
            this.normalize(this.animVec[3][k][1]);
            this.animVec[3][k][0][0] = 1.0f;
            this.animVec[3][k][0][1] = 0.0f;
            this.animVec[3][k][0][2] = 0.0f;
            this.vecProd(this.animVec[3][k][2], this.animVec[3][k][0], this.animVec[3][k][1]);
            this.normalize(this.animVec[4][k][0]);
            this.animVec[4][k][1][0] = 0.0f;
            this.animVec[4][k][1][1] = 1.0f;
            this.animVec[4][k][1][2] = 0.0f;
            this.vecProd(this.animVec[4][k][2], this.animVec[4][k][0], this.animVec[4][k][1]);
            this.normalize(this.animVec[5][k][0]);
            this.animVec[5][k][2][0] = 0.0f;
            this.animVec[5][k][2][1] = 0.0f;
            this.animVec[5][k][2][2] = 1.0f;
            this.vecProd(this.animVec[5][k][1], this.animVec[5][k][2], this.animVec[5][k][0]);
        }
        this.reset();
    }
    
    public void reset() {
        this.eye[0][0] = 0.9f;
        this.eye[0][1] = 0.0f;
        this.eye[0][2] = -0.4359f;
        this.eye[1][0] = 0.19f;
        this.eye[1][1] = 0.9f;
        this.eye[1][2] = 0.3923f;
        this.vecProd(this.eye[2], this.eye[0], this.eye[1]);
        this.repaint();
    }
    
    public void run() {
        this.animCube(this.twistMove, this.twistDir);
        Cubie.settings.cubePos.doMove(this.twistMove, this.twistDir, false);
        this.repaint();
        this.doEvent(this.eventType);
        this.eventType = true;
        this.twisting = false;
    }
    
    public void animCube(final int n, int n2) {
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        final int[] array3 = new int[3];
        int n3 = 0;
        if (n2 < 0) {
            n2 = -n2;
            n3 = 3;
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    this.animEye[i][j][k] = this.eye[j][k];
                }
            }
            final int[] array4 = array3;
            final int n4 = i;
            final int[] array5 = array2;
            final int n5 = i;
            final int[] array6 = array;
            final int n6 = i;
            final boolean b = false;
            array6[n6] = (b ? 1 : 0);
            array4[n4] = (array5[n5] = (b ? 1 : 0));
        }
        if (n < 3) {
            array2[0] = n2;
            array[0] = n + n3;
            this.axisOrder[0] = n;
        }
        else if (n < 6) {
            array2[2] = n2;
            array[2] = n - n3;
            this.axisOrder[0] = n - 3;
        }
        else if (n < 9) {
            array2[1] = n2;
            array[1] = n - 6 + n3;
            this.axisOrder[0] = n - 6;
        }
        else if (n < 12) {
            this.axisOrder[0] = n - 9;
            final int[] array7 = array2;
            final int n7 = 0;
            final int[] array8 = array2;
            final int n8 = 1;
            final int[] array9 = array2;
            final int n9 = 2;
            final int n10 = n2;
            array9[n9] = n10;
            array7[n7] = (array8[n8] = n10);
            final int[] array10 = array;
            final int n11 = 0;
            final int[] array11 = array;
            final int n12 = 1;
            final int[] array12 = array;
            final int n13 = 2;
            final int n14 = n - 9 + n3;
            array12[n13] = n14;
            array10[n11] = (array11[n12] = n14);
        }
        else if (n < 15) {
            this.axisOrder[0] = n - 12;
            array2[0] = (array2[2] = n2);
            array[2] = (array[0] = n - 12 + n3);
        }
        else if (n < 18) {
            this.axisOrder[0] = n - 15;
            array2[0] = (array2[2] = n2);
            array[0] = n - 15 + n3;
            array[2] = n - 15 + 3 - n3;
        }
        if (this.axisOrder[0] == 0) {
            this.axisOrder[0] = 0;
            this.axisOrder[1] = 1;
            this.axisOrder[2] = 2;
        }
        else if (this.axisOrder[0] == 1) {
            this.axisOrder[0] = 1;
            this.axisOrder[1] = 2;
            this.axisOrder[2] = 0;
        }
        else {
            this.axisOrder[0] = 2;
            this.axisOrder[1] = 0;
            this.axisOrder[2] = 1;
            final int n15 = array2[0];
            array2[0] = array2[2];
            array2[2] = n15;
            final int n16 = array[0];
            array[0] = array[2];
            array[2] = n16;
        }
        for (int l = 1; l < 16; ++l) {
            for (int n17 = 0; n17 < 3; ++n17) {
                final int[] array13 = array3;
                final int n18 = n17;
                array13[n18] += array2[n17];
                this.rotateVecs(this.animEye[n17], this.animVec[array[n17]][array3[n17]]);
            }
            this.anim = l;
            this.repaint();
            try {
                Thread.sleep(15L);
            }
            catch (Exception ex) {}
        }
        if (array2[1] != 0) {
            final int[] array14 = array3;
            final int n19 = 1;
            array14[n19] += array2[1];
            this.rotateVecs(this.animEye[0], this.animVec[array[1]][array3[1]]);
            for (int n20 = 0; n20 < 3; ++n20) {
                for (int n21 = 0; n21 < 3; ++n21) {
                    this.eye[n20][n21] = this.animEye[0][n20][n21];
                }
            }
        }
        this.anim = 0;
    }
    
    public void paint(final Graphics graphics) {
        if (super.offImage == null) {
            this.initialise();
        }
        super.offGraphics.setColor(this.getBackground());
        super.offGraphics.fillRect(0, 0, super.width, super.height);
        final CubePosition cubePos = Cubie.settings.cubePos;
        cubePos.getFaceletColors(cubePos.cubeletPerm, cubePos.cubeletOri, cubePos.faceOri, cubePos.faceletColor, cubePos.faceletOri);
        if (this.anim == 0) {
            this.drawCube(this.statEye);
        }
        else {
            this.drawCube(this.animEye);
        }
        graphics.drawImage(super.offImage, 0, 0, this);
    }
    
    private void drawCube(final float[][][] array) {
        final int[] array2 = new int[3];
        final int[] array3 = new int[3];
        for (int i = 0; i < 54; ++i) {
            this.faceletsO[i] = null;
        }
        if (array[0][2][this.axisOrder[0]] > 0.0f) {
            array3[this.axisOrder[0]] = 1;
            array2[this.axisOrder[0]] = 0;
        }
        else {
            array3[this.axisOrder[0]] = -1;
            array2[this.axisOrder[0]] = 2;
        }
        for (int j = 0; j < 3; ++j) {
            for (int k = 1; k < 3; ++k) {
                if (array[array2[this.axisOrder[0]]][2][this.axisOrder[k]] > 0.0f) {
                    array3[this.axisOrder[k]] = 1;
                    array2[this.axisOrder[k]] = 0;
                }
                else {
                    array3[this.axisOrder[k]] = -1;
                    array2[this.axisOrder[k]] = 2;
                }
            }
            for (int l = 0; l < 3; ++l) {
                for (int n = 0; n < 3; ++n) {
                    this.drawCubelet(array2, array[array2[this.axisOrder[0]]]);
                    final int[] array4 = array2;
                    final int n2 = this.axisOrder[2];
                    array4[n2] += array3[this.axisOrder[2]];
                }
                final int[] array5 = array2;
                final int n3 = this.axisOrder[2];
                array5[n3] -= 3 * array3[this.axisOrder[2]];
                final int[] array6 = array2;
                final int n4 = this.axisOrder[1];
                array6[n4] += array3[this.axisOrder[1]];
            }
            final int[] array7 = array2;
            final int n5 = this.axisOrder[1];
            array7[n5] -= 3 * array3[this.axisOrder[1]];
            final int[] array8 = array2;
            final int n6 = this.axisOrder[0];
            array8[n6] += array3[this.axisOrder[0]];
        }
    }
    
    private void drawCubelet(final int[] array, final float[][] array2) {
        final int n = array[0] + array[1] * 9 + array[2] * 3;
        this.ccorn[0] = array[0] - 1.5f;
        this.ccorn[1] = array[1] - 1.5f;
        this.ccorn[2] = array[2] - 1.5f;
        if (array2[2][0] > 0.0f) {
            final float[] ccorn = this.ccorn;
            final int n2 = 0;
            ++ccorn[n2];
            final float[] ccorn2 = this.ccorn;
            final int n3 = 2;
            ++ccorn2[n3];
            this.drawFacelet(this.ccorn, this.coordVec[1], this.coordVec[5], this.coordVec[0], array2, this.facelets[n][3]);
            final float[] ccorn3 = this.ccorn;
            final int n4 = 0;
            --ccorn3[n4];
            final float[] ccorn4 = this.ccorn;
            final int n5 = 2;
            --ccorn4[n5];
        }
        else {
            this.drawFacelet(this.ccorn, this.coordVec[1], this.coordVec[2], this.coordVec[0], array2, this.facelets[n][0]);
        }
        if (array2[2][1] > 0.0f) {
            final float[] ccorn5 = this.ccorn;
            final int n6 = 1;
            ++ccorn5[n6];
            final float[] ccorn6 = this.ccorn;
            final int n7 = 2;
            ++ccorn6[n7];
            this.drawFacelet(this.ccorn, this.coordVec[5], this.coordVec[0], this.coordVec[1], array2, this.facelets[n][4]);
            final float[] ccorn7 = this.ccorn;
            final int n8 = 1;
            --ccorn7[n8];
            final float[] ccorn8 = this.ccorn;
            final int n9 = 2;
            --ccorn8[n9];
        }
        else {
            this.drawFacelet(this.ccorn, this.coordVec[2], this.coordVec[0], this.coordVec[1], array2, this.facelets[n][1]);
        }
        if (array2[2][2] > 0.0f) {
            final float[] ccorn9 = this.ccorn;
            final int n10 = 2;
            ++ccorn9[n10];
            this.drawFacelet(this.ccorn, this.coordVec[1], this.coordVec[0], this.coordVec[2], array2, this.facelets[n][2]);
            final float[] ccorn10 = this.ccorn;
            final int n11 = 2;
            --ccorn10[n11];
            return;
        }
        final float[] ccorn11 = this.ccorn;
        final int n12 = 0;
        ++ccorn11[n12];
        this.drawFacelet(this.ccorn, this.coordVec[1], this.coordVec[3], this.coordVec[2], array2, this.facelets[n][5]);
        final float[] ccorn12 = this.ccorn;
        final int n13 = 0;
        --ccorn12[n13];
    }
    
    private void drawFacelet(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[][] array5, final int n) {
        final float[] array6 = array5[2];
        int n2 = (int)((array4[0] * array6[0] + array4[1] * array6[1] + array4[2] * array6[2]) * 20.0f);
        if (n2 < 0) {
            n2 = -n2;
        }
        if (n2 > 19) {
            n2 = 19;
        }
        for (int i = 0; i < 4; ++i) {
            this.vr[0] = array[0];
            this.vr[1] = array[1];
            this.vr[2] = array[2];
            if (i == 1 || i == 2) {
                final float[] vr = this.vr;
                final int n3 = 0;
                vr[n3] += array2[0];
                final float[] vr2 = this.vr;
                final int n4 = 1;
                vr2[n4] += array2[1];
                final float[] vr3 = this.vr;
                final int n5 = 2;
                vr3[n5] += array2[2];
            }
            if (i >= 2) {
                final float[] vr4 = this.vr;
                final int n6 = 0;
                vr4[n6] += array3[0];
                final float[] vr5 = this.vr;
                final int n7 = 1;
                vr5[n7] += array3[1];
                final float[] vr6 = this.vr;
                final int n8 = 2;
                vr6[n8] += array3[2];
            }
            final int[] rectx = this.rectx;
            final int n9 = i;
            final int offsetx = this.OFFSETX;
            final float[] vr7 = this.vr;
            final float[] array7 = array5[0];
            rectx[n9] = offsetx + (int)((vr7[0] * array7[0] + vr7[1] * array7[1] + vr7[2] * array7[2]) * this.radius);
            final int[] recty = this.recty;
            final int n10 = i;
            final int offsety = this.OFFSETY;
            final float[] vr8 = this.vr;
            final float[] array8 = array5[1];
            recty[n10] = offsety + (int)((vr8[0] * array8[0] + vr8[1] * array8[1] + vr8[2] * array8[2]) * this.radius);
        }
        super.offGraphics.setColor(this.fgColor[6][n2]);
        super.offGraphics.fillPolygon(this.rectx, this.recty, 4);
        final float[] array9 = array5[2];
        int n11 = (int)((array2[0] * array9[0] + array2[1] * array9[1] + array2[2] * array9[2]) * 20.0f);
        if (n11 < 0) {
            n11 = -n11;
        }
        if (n11 > 19) {
            n11 = 19;
        }
        super.offGraphics.setColor(this.fgColor[7][19 - n11]);
        super.offGraphics.drawLine(this.rectx[0], this.recty[0], this.rectx[1], this.recty[1]);
        super.offGraphics.drawLine(this.rectx[2], this.recty[2], this.rectx[3], this.recty[3]);
        final float[] array10 = array5[2];
        int n12 = (int)((array3[0] * array10[0] + array3[1] * array10[1] + array3[2] * array10[2]) * 20.0f);
        if (n12 < 0) {
            n12 = -n12;
        }
        if (n12 > 19) {
            n12 = 19;
        }
        super.offGraphics.setColor(this.fgColor[7][19 - n12]);
        super.offGraphics.drawLine(this.rectx[3], this.recty[3], this.rectx[0], this.recty[0]);
        super.offGraphics.drawLine(this.rectx[1], this.recty[1], this.rectx[2], this.recty[2]);
        if (n < 0) {
            return;
        }
        this.faceletsO[n] = new Polygon(this.rectx, this.recty, 4);
        final float[] array11 = this.moveDirs[n];
        final int n13 = 0;
        final float[] array12 = array5[0];
        array11[n13] = array2[0] * array12[0] + array2[1] * array12[1] + array2[2] * array12[2];
        final float[] array13 = this.moveDirs[n];
        final int n14 = 1;
        final float[] array14 = array5[1];
        array13[n14] = array2[0] * array14[0] + array2[1] * array14[1] + array2[2] * array14[2];
        final float[] array15 = this.moveDirs[n];
        final int n15 = 2;
        final float[] array16 = array5[0];
        array15[n15] = array3[0] * array16[0] + array3[1] * array16[1] + array3[2] * array16[2];
        final float[] array17 = this.moveDirs[n];
        final int n16 = 3;
        final float[] array18 = array5[1];
        array17[n16] = array3[0] * array18[0] + array3[1] * array18[1] + array3[2] * array18[2];
        final int n17 = Cubie.settings.cubePos.cubeletPerm[20 + Cubie.settings.cubePos.faceletColor[n]] - 20;
        for (int j = 0; j < 4; ++j) {
            this.vr[0] = array[0] + 0.1f * (array2[0] + array3[0]);
            this.vr[1] = array[1] + 0.1f * (array2[1] + array3[1]);
            this.vr[2] = array[2] + 0.1f * (array2[2] + array3[2]);
            if (j == 1 || j == 2) {
                final float[] vr9 = this.vr;
                final int n18 = 0;
                vr9[n18] += 0.8f * array2[0];
                final float[] vr10 = this.vr;
                final int n19 = 1;
                vr10[n19] += 0.8f * array2[1];
                final float[] vr11 = this.vr;
                final int n20 = 2;
                vr11[n20] += 0.8f * array2[2];
            }
            if (j >= 2) {
                final float[] vr12 = this.vr;
                final int n21 = 0;
                vr12[n21] += 0.8f * array3[0];
                final float[] vr13 = this.vr;
                final int n22 = 1;
                vr13[n22] += 0.8f * array3[1];
                final float[] vr14 = this.vr;
                final int n23 = 2;
                vr14[n23] += 0.8f * array3[2];
            }
            final int[] rectx2 = this.rectx;
            final int n24 = j;
            final int offsetx2 = this.OFFSETX;
            final float[] vr15 = this.vr;
            final float[] array19 = array5[0];
            rectx2[n24] = offsetx2 + (int)((vr15[0] * array19[0] + vr15[1] * array19[1] + vr15[2] * array19[2]) * this.radius);
            final int[] recty2 = this.recty;
            final int n25 = j;
            final int offsety2 = this.OFFSETY;
            final float[] vr16 = this.vr;
            final float[] array20 = array5[1];
            recty2[n25] = offsety2 + (int)((vr16[0] * array20[0] + vr16[1] * array20[1] + vr16[2] * array20[2]) * this.radius);
        }
        super.offGraphics.setColor(this.fgColor[n17][n2]);
        super.offGraphics.fillPolygon(this.rectx, this.recty, 4);
        if (Cubie.settings.superGroup) {
            final int n26 = Cubie.settings.cubePos.faceletOri[n];
            if (n26 == 0) {
                this.addpnt(array, array2, array3, array5, 0, 0.5f, 0.3f);
                this.addpnt(array, array2, array3, array5, 1, 0.5f, 0.7f);
                this.addpnt(array, array2, array3, array5, 2, 0.05f, 0.7f);
                this.addpnt(array, array2, array3, array5, 3, 0.05f, 0.3f);
            }
            else if (n26 == 1) {
                this.addpnt(array, array2, array3, array5, 0, 0.3f, 0.5f);
                this.addpnt(array, array2, array3, array5, 1, 0.7f, 0.5f);
                this.addpnt(array, array2, array3, array5, 2, 0.7f, 0.05f);
                this.addpnt(array, array2, array3, array5, 3, 0.3f, 0.05f);
            }
            else if (n26 == 2) {
                this.addpnt(array, array2, array3, array5, 0, 0.5f, 0.3f);
                this.addpnt(array, array2, array3, array5, 1, 0.5f, 0.7f);
                this.addpnt(array, array2, array3, array5, 2, 0.95f, 0.7f);
                this.addpnt(array, array2, array3, array5, 3, 0.95f, 0.3f);
            }
            else if (n26 == 3) {
                this.addpnt(array, array2, array3, array5, 0, 0.3f, 0.5f);
                this.addpnt(array, array2, array3, array5, 1, 0.7f, 0.5f);
                this.addpnt(array, array2, array3, array5, 2, 0.7f, 0.95f);
                this.addpnt(array, array2, array3, array5, 3, 0.3f, 0.95f);
            }
            super.offGraphics.setColor(this.fgColor[6][n2]);
            super.offGraphics.fillPolygon(this.rectx, this.recty, 4);
        }
    }
    
    private void addpnt(final float[] array, final float[] array2, final float[] array3, final float[][] array4, final int n, final float n2, final float n3) {
        this.vr[0] = array[0] + n2 * array2[0] + n3 * array3[0];
        this.vr[1] = array[1] + n2 * array2[1] + n3 * array3[1];
        this.vr[2] = array[2] + n2 * array2[2] + n3 * array3[2];
        final int[] rectx = this.rectx;
        final int offsetx = this.OFFSETX;
        final float[] vr = this.vr;
        final float[] array5 = array4[0];
        rectx[n] = offsetx + (int)((vr[0] * array5[0] + vr[1] * array5[1] + vr[2] * array5[2]) * this.radius);
        final int[] recty = this.recty;
        final int offsety = this.OFFSETY;
        final float[] vr2 = this.vr;
        final float[] array6 = array4[1];
        recty[n] = offsety + (int)((vr2[0] * array6[0] + vr2[1] * array6[1] + vr2[2] * array6[2]) * this.radius);
    }
    
    private void normalize(final float[] array) {
        final float n = (float)Math.sqrt(array[0] * array[0] + array[1] * array[1] + array[2] * array[2]);
        final int n2 = 0;
        array[n2] /= n;
        final int n3 = 1;
        array[n3] /= n;
        final int n4 = 2;
        array[n4] /= n;
    }
    
    private float dotProd(final float[] array, final float[] array2) {
        return array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2];
    }
    
    private void vecProd(final float[] array, final float[] array2, final float[] array3) {
        array[0] = array2[1] * array3[2] - array2[2] * array3[1];
        array[1] = array2[2] * array3[0] - array2[0] * array3[2];
        array[2] = array2[0] * array3[1] - array2[1] * array3[0];
    }
    
    private void rotateVecs(final float[][] array, final float[][] array2) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                array[i][j] = array2[0][j] * this.eye[i][0] + array2[1][j] * this.eye[i][1] + array2[2][j] * this.eye[i][2];
            }
        }
    }
    
    protected int getFacelet(final int n, final int n2) {
        for (int i = 0; i < 54; ++i) {
            if (this.faceletsO[i] != null && this.faceletsO[i].contains(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private void executeMouseMove(final int n, final int n2, final int n3) {
        int n4 = -1;
        if (n < 0) {
            return;
        }
        final float n5 = n2 * this.moveDirs[n][1] - n3 * this.moveDirs[n][0];
        final float n6 = n2 * this.moveDirs[n][3] - n3 * this.moveDirs[n][2];
        if (n5 > n6 && n5 > -n6) {
            n4 = 0;
        }
        else if (n5 < n6 && n5 < -n6) {
            n4 = 2;
        }
        else if (n6 > n5 && n6 > -n5) {
            n4 = 1;
        }
        else if (n6 < n5 && n6 < -n5) {
            n4 = 3;
        }
        if (n4 >= 0 && n >= 0) {
            final int n7 = this.moves[n4][n];
            if (n7 < 0) {
                this.tryMove(-n7 - 1, -1);
                return;
            }
            this.tryMove(n7 - 1, 1);
        }
    }
    
    void doMove(final int n, final int n2) {
        if (!Cubie.settings.lockViewer) {
            this.doMove2(n, n2);
        }
    }
    
    void doMove2(final int twistMove, final int twistDir) {
        if (!this.twisting) {
            this.twisting = true;
            this.twistMove = twistMove;
            this.twistDir = twistDir;
            new Thread(this).start();
        }
    }
    
    public boolean showMove(final int n, int n2) {
        if (this.twisting) {
            return false;
        }
        if (n2 > 2) {
            n2 -= 4;
        }
        this.eventType = false;
        this.doMove2(n, n2);
        return true;
    }
    
    protected void checkMouseMove(final int lastX, final int lastY, final int n) {
        if (super.lastF < 0) {
            final int n2 = lastX - super.lastX;
            final int n3 = lastY - super.lastY;
            super.lastX = lastX;
            super.lastY = lastY;
            final float n4 = n2 / this.radius / 2.0f;
            final float[] array = this.eye[0];
            final int n5 = 0;
            array[n5] += n4 * this.eye[2][0];
            final float[] array2 = this.eye[0];
            final int n6 = 1;
            array2[n6] += n4 * this.eye[2][1];
            final float[] array3 = this.eye[0];
            final int n7 = 2;
            array3[n7] += n4 * this.eye[2][2];
            this.normalize(this.eye[0]);
            this.vecProd(this.eye[2], this.eye[0], this.eye[1]);
            final float n8 = n3 / this.radius / 2.0f;
            final float[] array4 = this.eye[1];
            final int n9 = 0;
            array4[n9] += n8 * this.eye[2][0];
            final float[] array5 = this.eye[1];
            final int n10 = 1;
            array5[n10] += n8 * this.eye[2][1];
            final float[] array6 = this.eye[1];
            final int n11 = 2;
            array6[n11] += n8 * this.eye[2][2];
            this.normalize(this.eye[1]);
            this.vecProd(this.eye[2], this.eye[0], this.eye[1]);
            this.repaint();
            return;
        }
        final int n12 = lastX - super.lastX;
        final int n13 = lastY - super.lastY;
        if (n12 * n12 + n13 * n13 > n) {
            this.executeMouseMove(this.getFacelet(super.lastX, super.lastY), n12, n13);
            super.moved = true;
        }
    }
}
