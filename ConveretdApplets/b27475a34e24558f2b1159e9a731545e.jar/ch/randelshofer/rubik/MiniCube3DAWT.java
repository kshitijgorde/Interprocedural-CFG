// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik;

import java.awt.event.ActionListener;
import ch.randelshofer.geom3d.Shape3D;
import java.awt.Color;

public class MiniCube3DAWT extends AbstractCube3DAWT
{
    public static final Color[] STICKER_COLORS;
    
    public void setStickerColor(final int n, final int n2, final Color color) {
        Label_1122: {
            switch (n) {
                case 0: {
                    switch (n2) {
                        case 0: {
                            super.cornerShapes[0].setBackgroundColor(1, color);
                            break;
                        }
                        case 1: {
                            super.edgeShapes[0].setBackgroundColor(1, color);
                            break;
                        }
                        case 2: {
                            super.cornerShapes[2].setBackgroundColor(2, color);
                            break;
                        }
                        case 3: {
                            super.edgeShapes[1].setBackgroundColor(0, color);
                            break;
                        }
                        case 4: {
                            super.sideShapes[0].setBackgroundColor(0, color);
                            break;
                        }
                        case 5: {
                            super.edgeShapes[4].setBackgroundColor(0, color);
                            break;
                        }
                        case 6: {
                            super.cornerShapes[1].setBackgroundColor(2, color);
                            break;
                        }
                        case 7: {
                            super.edgeShapes[2].setBackgroundColor(1, color);
                            break;
                        }
                        case 8: {
                            super.cornerShapes[3].setBackgroundColor(1, color);
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    switch (n2) {
                        case 0: {
                            super.cornerShapes[2].setBackgroundColor(1, color);
                            break;
                        }
                        case 1: {
                            super.edgeShapes[3].setBackgroundColor(0, color);
                            break;
                        }
                        case 2: {
                            super.cornerShapes[4].setBackgroundColor(2, color);
                            break;
                        }
                        case 3: {
                            super.edgeShapes[4].setBackgroundColor(1, color);
                            break;
                        }
                        case 4: {
                            super.sideShapes[1].setBackgroundColor(0, color);
                            break;
                        }
                        case 5: {
                            super.edgeShapes[7].setBackgroundColor(1, color);
                            break;
                        }
                        case 6: {
                            super.cornerShapes[3].setBackgroundColor(2, color);
                            break;
                        }
                        case 7: {
                            super.edgeShapes[5].setBackgroundColor(0, color);
                            break;
                        }
                        case 8: {
                            super.cornerShapes[5].setBackgroundColor(1, color);
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (n2) {
                        case 0: {
                            super.cornerShapes[1].setBackgroundColor(0, color);
                            break;
                        }
                        case 1: {
                            super.edgeShapes[2].setBackgroundColor(0, color);
                            break;
                        }
                        case 2: {
                            super.cornerShapes[3].setBackgroundColor(0, color);
                            break;
                        }
                        case 3: {
                            super.edgeShapes[11].setBackgroundColor(1, color);
                            break;
                        }
                        case 4: {
                            super.sideShapes[2].setBackgroundColor(0, color);
                            break;
                        }
                        case 5: {
                            super.edgeShapes[5].setBackgroundColor(1, color);
                            break;
                        }
                        case 6: {
                            super.cornerShapes[7].setBackgroundColor(0, color);
                            break;
                        }
                        case 7: {
                            super.edgeShapes[8].setBackgroundColor(0, color);
                            break;
                        }
                        case 8: {
                            super.cornerShapes[5].setBackgroundColor(0, color);
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (n2) {
                        case 0: {
                            super.cornerShapes[4].setBackgroundColor(1, color);
                            break;
                        }
                        case 1: {
                            super.edgeShapes[6].setBackgroundColor(1, color);
                            break;
                        }
                        case 2: {
                            super.cornerShapes[6].setBackgroundColor(2, color);
                            break;
                        }
                        case 3: {
                            super.edgeShapes[7].setBackgroundColor(0, color);
                            break;
                        }
                        case 4: {
                            super.sideShapes[3].setBackgroundColor(0, color);
                            break;
                        }
                        case 5: {
                            super.edgeShapes[10].setBackgroundColor(0, color);
                            break;
                        }
                        case 6: {
                            super.cornerShapes[5].setBackgroundColor(2, color);
                            break;
                        }
                        case 7: {
                            super.edgeShapes[8].setBackgroundColor(1, color);
                            break;
                        }
                        case 8: {
                            super.cornerShapes[7].setBackgroundColor(1, color);
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    switch (n2) {
                        case 0: {
                            super.cornerShapes[6].setBackgroundColor(1, color);
                            break;
                        }
                        case 1: {
                            super.edgeShapes[9].setBackgroundColor(0, color);
                            break;
                        }
                        case 2: {
                            super.cornerShapes[0].setBackgroundColor(2, color);
                            break;
                        }
                        case 3: {
                            super.edgeShapes[10].setBackgroundColor(1, color);
                            break;
                        }
                        case 4: {
                            super.sideShapes[4].setBackgroundColor(0, color);
                            break;
                        }
                        case 5: {
                            super.edgeShapes[1].setBackgroundColor(1, color);
                            break;
                        }
                        case 6: {
                            super.cornerShapes[7].setBackgroundColor(2, color);
                            break;
                        }
                        case 7: {
                            super.edgeShapes[11].setBackgroundColor(0, color);
                            break;
                        }
                        case 8: {
                            super.cornerShapes[1].setBackgroundColor(1, color);
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    switch (n2) {
                        case 0: {
                            super.cornerShapes[6].setBackgroundColor(0, color);
                            break Label_1122;
                        }
                        case 1: {
                            super.edgeShapes[6].setBackgroundColor(0, color);
                            break Label_1122;
                        }
                        case 2: {
                            super.cornerShapes[4].setBackgroundColor(0, color);
                            break Label_1122;
                        }
                        case 3: {
                            super.edgeShapes[9].setBackgroundColor(1, color);
                            break Label_1122;
                        }
                        case 4: {
                            super.sideShapes[5].setBackgroundColor(0, color);
                            break Label_1122;
                        }
                        case 5: {
                            super.edgeShapes[3].setBackgroundColor(1, color);
                            break Label_1122;
                        }
                        case 6: {
                            super.cornerShapes[0].setBackgroundColor(0, color);
                            break Label_1122;
                        }
                        case 7: {
                            super.edgeShapes[0].setBackgroundColor(0, color);
                            break Label_1122;
                        }
                        case 8: {
                            super.cornerShapes[2].setBackgroundColor(0, color);
                            break Label_1122;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    protected void initCorners() {
        final float[] array = { -8.0f, 8.0f, 9.0f, -8.0f, -8.0f, 9.0f, 8.0f, 8.0f, 9.0f, 8.0f, -8.0f, 9.0f, 8.0f, 8.0f, -9.0f, 8.0f, -8.0f, -9.0f, -8.0f, 8.0f, -9.0f, -8.0f, -8.0f, -9.0f, -9.0f, 8.0f, 8.0f, -9.0f, -8.0f, 8.0f, 9.0f, 8.0f, 8.0f, 9.0f, -8.0f, 8.0f, 9.0f, 8.0f, -8.0f, 9.0f, -8.0f, -8.0f, -9.0f, 8.0f, -8.0f, -9.0f, -8.0f, -8.0f, -8.0f, 9.0f, 8.0f, -8.0f, -9.0f, 8.0f, 8.0f, 9.0f, 8.0f, 8.0f, -9.0f, 8.0f, 8.0f, 9.0f, -8.0f, 8.0f, -9.0f, -8.0f, -8.0f, 9.0f, -8.0f, -8.0f, -9.0f, -8.0f };
        final int[][] array2 = { { 16, 22, 20, 18 }, { 0, 2, 3, 1 }, { 14, 8, 9, 15 }, { 12, 13, 11, 10 }, { 17, 19, 21, 23 }, { 4, 6, 7, 5 }, { 17, 9, 1 }, { 19, 3, 11 }, { 23, 7, 15 }, { 16, 0, 8 }, { 18, 10, 2 }, { 22, 14, 6 }, { 20, 4, 12 }, { 16, 18, 2, 0 }, { 18, 20, 12, 10 }, { 20, 22, 6, 4 }, { 22, 16, 8, 14 }, { 19, 17, 1, 3 }, { 21, 19, 11, 13 }, { 23, 21, 5, 7 }, { 17, 23, 15, 9 }, { 3, 2, 10, 11 }, { 0, 1, 9, 8 }, { 4, 5, 13, 12 }, { 7, 6, 14, 15 } };
        final Color[][][] array3 = new Color[8][array2.length][0];
        final Color[] array4 = { AbstractCube3DAWT.PART_FILL_COLOR, null };
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < array2.length; ++j) {
                array3[i][j] = array4;
            }
        }
        array3[0][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[0][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[0][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[1][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[1][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[1][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[2][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[2][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[2][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[3][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[3][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[3][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[4][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[4][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[4][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[5][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[5][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[5][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[6][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[6][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[6][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[7][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[7][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[7][2] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        for (int k = 0; k < 8; ++k) {
            super.cornerShapes[k] = new Shape3D(array, array2, array3[k]);
        }
    }
    
    protected void initEdges() {
        final float[] array = { -8.0f, 8.0f, 9.0f, -8.0f, -8.0f, 9.0f, 8.0f, 8.0f, 9.0f, 8.0f, -8.0f, 9.0f, 8.0f, 8.0f, -9.0f, 8.0f, -8.0f, -9.0f, -8.0f, 8.0f, -9.0f, -8.0f, -8.0f, -9.0f, -9.0f, 8.0f, 8.0f, -9.0f, -8.0f, 8.0f, 9.0f, 8.0f, 8.0f, 9.0f, -8.0f, 8.0f, 9.0f, 8.0f, -8.0f, 9.0f, -8.0f, -8.0f, -9.0f, 8.0f, -8.0f, -9.0f, -8.0f, -8.0f, -8.0f, 9.0f, 8.0f, -8.0f, -9.0f, 8.0f, 8.0f, 9.0f, 8.0f, 8.0f, -9.0f, 8.0f, 8.0f, 9.0f, -8.0f, 8.0f, -9.0f, -8.0f, -8.0f, 9.0f, -8.0f, -8.0f, -9.0f, -8.0f };
        final int[][] array2 = { { 0, 2, 3, 1 }, { 16, 22, 20, 18 }, { 14, 8, 9, 15 }, { 12, 13, 11, 10 }, { 17, 19, 21, 23 }, { 4, 6, 7, 5 }, { 17, 9, 1 }, { 19, 3, 11 }, { 16, 0, 8 }, { 18, 10, 2 }, { 22, 14, 6 }, { 20, 4, 12 }, { 16, 18, 2, 0 }, { 18, 20, 12, 10 }, { 20, 22, 6, 4 }, { 22, 16, 8, 14 }, { 19, 17, 1, 3 }, { 21, 19, 11, 13 }, { 17, 23, 15, 9 }, { 3, 2, 10, 11 }, { 0, 1, 9, 8 }, { 4, 5, 13, 12 }, { 7, 6, 14, 15 } };
        final Color[][][] array3 = new Color[12][array2.length][0];
        final Color[] array4 = { AbstractCube3DAWT.PART_FILL_COLOR, null };
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < array2.length; ++j) {
                array3[i][j] = array4;
            }
        }
        array3[0][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[0][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[1][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[1][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[2][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[2][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[3][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[3][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[4][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[4][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[5][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[5][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[6][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[6][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[7][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[7][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[8][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[8][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[9][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[9][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[10][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[10][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[11][1] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[11][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        for (int k = 0; k < 12; ++k) {
            super.edgeShapes[k] = new Shape3D(array, array2, array3[k]);
        }
    }
    
    protected void initSides() {
        final float[] array = { -8.0f, 8.0f, 9.0f, -8.0f, -8.0f, 9.0f, 8.0f, 8.0f, 9.0f, 8.0f, -8.0f, 9.0f, 8.0f, 8.0f, -9.0f, 8.0f, -8.0f, -9.0f, -8.0f, 8.0f, -9.0f, -8.0f, -8.0f, -9.0f, -9.0f, 8.0f, 8.0f, -9.0f, -8.0f, 8.0f, 9.0f, 8.0f, 8.0f, 9.0f, -8.0f, 8.0f, 9.0f, 8.0f, -8.0f, 9.0f, -8.0f, -8.0f, -9.0f, 8.0f, -8.0f, -9.0f, -8.0f, -8.0f, -8.0f, 9.0f, 8.0f, -8.0f, -9.0f, 8.0f, 8.0f, 9.0f, 8.0f, 8.0f, -9.0f, 8.0f, 8.0f, 9.0f, -8.0f, 8.0f, -9.0f, -8.0f, -8.0f, 9.0f, -8.0f, -8.0f, -9.0f, -8.0f };
        final int[][] array2 = { { 0, 2, 3, 1 }, { 16, 22, 20, 18 }, { 14, 8, 9, 15 }, { 12, 13, 11, 10 }, { 17, 19, 21, 23 }, { 17, 9, 1 }, { 19, 3, 11 }, { 16, 0, 8 }, { 18, 10, 2 }, { 16, 18, 2, 0 }, { 18, 20, 12, 10 }, { 22, 16, 8, 14 }, { 19, 17, 1, 3 }, { 21, 19, 11, 13 }, { 17, 23, 15, 9 }, { 3, 2, 10, 11 }, { 0, 1, 9, 8 } };
        final Color[][][] array3 = new Color[6][array2.length][0];
        final Color[] array4 = { AbstractCube3DAWT.PART_FILL_COLOR, null };
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < array2.length; ++j) {
                array3[i][j] = array4;
            }
        }
        array3[0][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[0], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[1][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[1], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[2][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[2], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[3][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[3], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[4][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[4], AbstractCube3DAWT.PART_BORDER_COLOR };
        array3[5][0] = new Color[] { MiniCube3DAWT.STICKER_COLORS[5], AbstractCube3DAWT.PART_BORDER_COLOR };
        for (int k = 0; k < 6; ++k) {
            super.sideShapes[k] = new Shape3D(array, array2, array3[k]);
        }
    }
    
    protected void initCenter() {
        (super.centerShape = new Shape3D(new float[0], new int[0][], new Color[0][])).setVisible(false);
    }
    
    protected void initActions() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 3; ++j) {
                super.cornerShapes[i].setAction(j, new CrnrActn(this, i, j));
            }
        }
        for (int k = 0; k < 12; ++k) {
            for (int l = 0; l < 2; ++l) {
                super.edgeShapes[k].setAction(l, new EdgeAction(this, k, l));
            }
        }
        for (int n = 0; n < 6; ++n) {
            super.sideShapes[n].setAction(0, new SideAction(this, n));
        }
    }
    
    public String getName() {
        return "Rubik's Cube (simplified 3D model)";
    }
    
    static {
        STICKER_COLORS = new Color[] { new Color(231, 16, 0), new Color(33, 33, 189), new Color(247, 247, 247), new Color(255, 74, 0), new Color(49, 148, 0), new Color(247, 247, 0) };
    }
}
