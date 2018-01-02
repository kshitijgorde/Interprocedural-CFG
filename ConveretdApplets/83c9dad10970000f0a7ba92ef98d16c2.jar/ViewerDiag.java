import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

final class ViewerDiag extends Viewer
{
    Polygon[] faceletsI;
    Polygon[] faceletsO;
    Polygon[][] faceOriMarks;
    Polygon[] cubeBack;
    final int[][] moves;
    
    public ViewerDiag(final int n, final int n2, final ActionListener actionListener) {
        super(n, n2, actionListener);
        this.faceletsI = new Polygon[54];
        this.faceletsO = new Polygon[54];
        this.faceOriMarks = new Polygon[54][4];
        this.cubeBack = new Polygon[4];
        this.moves = new int[][] { { -2, -2, -2, -8, -11, -8, 5, 5, 5, -6, -6, -6, 9, 12, 9, 3, 3, 3, -2, -2, -2, -8, -11, -8, 5, 5, 5, 2, 2, 2, 8, 11, 8, -5, -5, -5, -3, -3, -3, -9, -12, -9, 6, 6, 6, 2, 2, 2, 8, 11, 8, -5, -5, -5 }, { 6, -9, -3, 6, -12, -3, 6, -9, -3, -1, -7, 4, -1, -10, 4, -1, -7, 4, 1, 7, -4, 1, 10, -4, 1, 7, -4, 3, 9, -6, 3, 12, -6, 3, 9, -6, 1, 7, -4, 1, 10, -4, 1, 7, -4, 4, -7, -1, 4, -10, -1, 4, -7, -1 }, { 2, 2, 2, 8, 11, 8, -5, -5, -5, 6, 6, 6, -9, -12, -9, -3, -3, -3, 2, 2, 2, 8, 11, 8, -5, -5, -5, -2, -2, -2, -8, -11, -8, 5, 5, 5, 3, 3, 3, 9, 12, 9, -6, -6, -6, -2, -2, -2, -8, -11, -8, 5, 5, 5 }, { -6, 9, 3, -6, 12, 3, -6, 9, 3, 1, 7, -4, 1, 10, -4, 1, 7, -4, -1, -7, 4, -1, -10, 4, -1, -7, 4, -3, -9, 6, -3, -12, 6, -3, -9, 6, -1, -7, 4, -1, -10, 4, -1, -7, 4, -4, 7, 1, -4, 10, 1, -4, 7, 1 } };
        int n3 = (int)(n2 / 21.15);
        final int n4 = n / 22;
        if (n4 < n3) {
            n3 = n4;
        }
        final int n5 = n3 * 2;
        final int n6 = (int)(2.3 * n3);
        final int n7 = 3 * n5;
        final int n8 = 3 * n6;
        final int n9 = 3 * n3;
        final int n10 = (n - 11 * n5) / 2;
        final int n11 = (n2 + n2 - 11 * n6 - 17 * n3) / 4;
        for (int i = 0; i < 54; ++i) {
            this.faceletsI[i] = new Polygon();
            this.faceletsO[i] = new Polygon();
            for (int j = 0; j < 4; ++j) {
                this.faceOriMarks[i][j] = new Polygon();
            }
        }
        final double n12 = 7.0 / n8;
        this.dopoly(n7, 0, -n7, n9, 0, n8, 0, n12);
        this.dopoly(n7 * 2 - n5 / 2, n8 - n9 - (n6 - n3) / 2, n7, n9, -n7, n9, 9, n12);
        this.dopoly(n7 - n5 / 2, n8 - (n6 - n3) / 2, n7, n9, 0, n8, 18, n12);
        this.dopoly(n7 * 2 - n5 / 2, n8 + n9 - (n6 - n3) / 2, n7, -n9, 0, n8, 27, n12);
        this.dopoly(n7 - n5 / 2, n9 * 2 + n8 * 2 - (n6 + n3) / 2, n7, n9, n7, -n9, 36, n12);
        this.dopoly(n7 * 4 - n5, n9, -n7, -n9, 0, n8, 45, n12);
        for (int k = 0; k < 54; ++k) {
            this.faceletsI[k].translate(n10, n11);
            this.faceletsO[k].translate(n10, n11);
            for (int l = 0; l < 4; ++l) {
                this.faceOriMarks[k][l].translate(n10, n11);
            }
        }
        for (int n13 = 0; n13 < 4; ++n13) {
            this.cubeBack[n13] = new Polygon();
        }
        this.cubeBack[0].addPoint(0, 0);
        this.cubeBack[0].addPoint(0, n8);
        this.cubeBack[0].addPoint(n7, n8 + n9);
        this.cubeBack[0].addPoint(n7 * 2, n8);
        this.cubeBack[0].addPoint(n7 * 2, 0);
        this.cubeBack[0].addPoint(n7, -n9);
        this.cubeBack[0].translate(n10 + n7 - n5 / 2, n11 + n8 - (n6 - n3) / 2);
        this.cubeBack[1].addPoint(n7, 0);
        this.cubeBack[1].addPoint(0, n9);
        this.cubeBack[1].addPoint(0, n9 + n8);
        this.cubeBack[1].addPoint(n7, n8);
        this.cubeBack[1].translate(n10, n11);
        this.cubeBack[2].addPoint(0, -n9);
        this.cubeBack[2].addPoint(-n7, 0);
        this.cubeBack[2].addPoint(0, n9);
        this.cubeBack[2].addPoint(n7, 0);
        this.cubeBack[2].translate(n10 + n7 * 2 - n5 / 2, n11 + n8 * 2 + n9 * 2 - (n6 + n3) / 2);
        this.cubeBack[3].addPoint(0, n8);
        this.cubeBack[3].addPoint(n7, n8 + n9);
        this.cubeBack[3].addPoint(n7, n9);
        this.cubeBack[3].addPoint(0, 0);
        this.cubeBack[3].translate(n10 + n7 * 3 - n5, n11);
    }
    
    private void dopoly(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, int n7, final double n8) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.faceletsO[n7].addPoint(j * n3 / 3 + i * n5 / 3, j * n4 / 3 + i * n6 / 3);
                this.faceletsO[n7].addPoint((j + 1) * n3 / 3 + i * n5 / 3, (j + 1) * n4 / 3 + i * n6 / 3);
                this.faceletsO[n7].addPoint((j + 1) * n3 / 3 + (i + 1) * n5 / 3, (j + 1) * n4 / 3 + (i + 1) * n6 / 3);
                this.faceletsO[n7].addPoint(j * n3 / 3 + (i + 1) * n5 / 3, j * n4 / 3 + (i + 1) * n6 / 3);
                this.faceletsO[n7].translate(n, n2);
                this.faceletsI[n7].addPoint((int)((n8 + j) * n3 / 3.0 + (n8 + i) * n5 / 3.0), (int)((n8 + j) * n4 / 3.0 + (n8 + i) * n6 / 3.0));
                this.faceletsI[n7].addPoint((int)((-n8 + j + 1.0) * n3 / 3.0 + (n8 + i) * n5 / 3.0), (int)((-n8 + j + 1.0) * n4 / 3.0 + (n8 + i) * n6 / 3.0));
                this.faceletsI[n7].addPoint((int)((-n8 + j + 1.0) * n3 / 3.0 + (-n8 + i + 1.0) * n5 / 3.0), (int)((-n8 + j + 1.0) * n4 / 3.0 + (-n8 + i + 1.0) * n6 / 3.0));
                this.faceletsI[n7].addPoint((int)((n8 + j) * n3 / 3.0 + (-n8 + i + 1.0) * n5 / 3.0), (int)((n8 + j) * n4 / 3.0 + (-n8 + i + 1.0) * n6 / 3.0));
                this.faceletsI[n7].translate(n, n2);
                this.faceOriMarks[n7][0].addPoint((int)((j + n8 / 2.0 + 0.25) * n3 / 3.0 + i * n5 / 3), (int)((j + n8 / 2.0 + 0.25) * n4 / 3.0 + i * n6 / 3));
                this.faceOriMarks[n7][0].addPoint((int)((j - n8 / 2.0 + 0.75) * n3 / 3.0 + i * n5 / 3), (int)((j - n8 / 2.0 + 0.75) * n4 / 3.0 + i * n6 / 3));
                this.faceOriMarks[n7][0].addPoint((int)((j - n8 / 2.0 + 0.75) * n3 / 3.0 + (i + 0.5) * n5 / 3.0), (int)((j - n8 / 2.0 + 0.75) * n4 / 3.0 + (i + 0.5) * n6 / 3.0));
                this.faceOriMarks[n7][0].addPoint((int)((j + n8 / 2.0 + 0.25) * n3 / 3.0 + (i + 0.5) * n5 / 3.0), (int)((j + n8 / 2.0 + 0.25) * n4 / 3.0 + (i + 0.5) * n6 / 3.0));
                this.faceOriMarks[n7][0].translate(n, n2);
                this.faceOriMarks[n7][3].addPoint((int)((j + 0.5) * n3 / 3.0 + (i + n8 / 2.0 + 0.25) * n5 / 3.0), (int)((j + 0.5) * n4 / 3.0 + (i + n8 / 2.0 + 0.25) * n6 / 3.0));
                this.faceOriMarks[n7][3].addPoint((int)((j + 1) * n3 / 3 + (i + n8 / 2.0 + 0.25) * n5 / 3.0), (int)((j + 1) * n4 / 3 + (i + n8 / 2.0 + 0.25) * n6 / 3.0));
                this.faceOriMarks[n7][3].addPoint((int)((j + 1) * n3 / 3 + (i - n8 / 2.0 + 0.75) * n5 / 3.0), (int)((j + 1) * n4 / 3 + (i - n8 / 2.0 + 0.75) * n6 / 3.0));
                this.faceOriMarks[n7][3].addPoint((int)((j + 0.5) * n3 / 3.0 + (i - n8 / 2.0 + 0.75) * n5 / 3.0), (int)((j + 0.5) * n4 / 3.0 + (i - n8 / 2.0 + 0.75) * n6 / 3.0));
                this.faceOriMarks[n7][3].translate(n, n2);
                this.faceOriMarks[n7][2].addPoint((int)((j + n8 / 2.0 + 0.25) * n3 / 3.0 + (i + 0.5) * n5 / 3.0), (int)((j + n8 / 2.0 + 0.25) * n4 / 3.0 + (i + 0.5) * n6 / 3.0));
                this.faceOriMarks[n7][2].addPoint((int)((j - n8 / 2.0 + 0.75) * n3 / 3.0 + (i + 0.5) * n5 / 3.0), (int)((j - n8 / 2.0 + 0.75) * n4 / 3.0 + (i + 0.5) * n6 / 3.0));
                this.faceOriMarks[n7][2].addPoint((int)((j - n8 / 2.0 + 0.75) * n3 / 3.0 + (i + 1) * n5 / 3), (int)((j - n8 / 2.0 + 0.75) * n4 / 3.0 + (i + 1) * n6 / 3));
                this.faceOriMarks[n7][2].addPoint((int)((j + n8 / 2.0 + 0.25) * n3 / 3.0 + (i + 1) * n5 / 3), (int)((j + n8 / 2.0 + 0.25) * n4 / 3.0 + (i + 1) * n6 / 3));
                this.faceOriMarks[n7][2].translate(n, n2);
                this.faceOriMarks[n7][1].addPoint((int)(j * n3 / 3 + (i + n8 / 2.0 + 0.25) * n5 / 3.0), (int)(j * n4 / 3 + (i + n8 / 2.0 + 0.25) * n6 / 3.0));
                this.faceOriMarks[n7][1].addPoint((int)((j + 0.5) * n3 / 3.0 + (i + n8 / 2.0 + 0.25) * n5 / 3.0), (int)((j + 0.5) * n4 / 3.0 + (i + n8 / 2.0 + 0.25) * n6 / 3.0));
                this.faceOriMarks[n7][1].addPoint((int)((j + 0.5) * n3 / 3.0 + (i - n8 / 2.0 + 0.75) * n5 / 3.0), (int)((j + 0.5) * n4 / 3.0 + (i - n8 / 2.0 + 0.75) * n6 / 3.0));
                this.faceOriMarks[n7][1].addPoint((int)(j * n3 / 3 + (i - n8 / 2.0 + 0.75) * n5 / 3.0), (int)(j * n4 / 3 + (i - n8 / 2.0 + 0.75) * n6 / 3.0));
                this.faceOriMarks[n7][1].translate(n, n2);
                ++n7;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (super.offImage == null) {
            this.initialise();
        }
        super.offGraphics.setColor(this.getBackground());
        super.offGraphics.fillRect(0, 0, super.width, super.height);
        final CubePosition cubePos = Cubie.settings.cubePos;
        cubePos.getFaceletColors(cubePos.cubeletPerm, cubePos.cubeletOri, cubePos.faceOri, cubePos.faceletColor, cubePos.faceletOri);
        super.offGraphics.setColor(super.baseColor);
        for (int i = 1; i < 4; ++i) {
            super.offGraphics.fillPolygon(this.cubeBack[i]);
        }
        for (int j = 0; j < 9; ++j) {
            super.offGraphics.setColor(super.colors[Cubie.settings.cubePos.cubeletPerm[20 + Cubie.settings.cubePos.faceletColor[j]] - 20]);
            super.offGraphics.fillPolygon(this.faceletsI[j]);
            if (Cubie.settings.superGroup) {
                super.offGraphics.setColor(super.baseColor);
                super.offGraphics.fillPolygon(this.faceOriMarks[j][Cubie.settings.cubePos.faceletOri[j]]);
            }
        }
        for (int k = 36; k < 54; ++k) {
            super.offGraphics.setColor(super.colors[Cubie.settings.cubePos.cubeletPerm[20 + Cubie.settings.cubePos.faceletColor[k]] - 20]);
            super.offGraphics.fillPolygon(this.faceletsI[k]);
            if (Cubie.settings.superGroup) {
                super.offGraphics.setColor(super.baseColor);
                super.offGraphics.fillPolygon(this.faceOriMarks[k][Cubie.settings.cubePos.faceletOri[k]]);
            }
        }
        super.offGraphics.setColor(super.baseColor);
        super.offGraphics.fillPolygon(this.cubeBack[0]);
        for (int l = 9; l < 36; ++l) {
            super.offGraphics.setColor(super.colors[Cubie.settings.cubePos.cubeletPerm[20 + Cubie.settings.cubePos.faceletColor[l]] - 20]);
            super.offGraphics.fillPolygon(this.faceletsI[l]);
            if (Cubie.settings.superGroup) {
                super.offGraphics.setColor(super.baseColor);
                super.offGraphics.fillPolygon(this.faceOriMarks[l][Cubie.settings.cubePos.faceletOri[l]]);
            }
        }
        graphics.drawImage(super.offImage, 0, 0, this);
    }
    
    protected void checkMouseMove(final int n, final int n2, final int n3) {
        if (!super.moved) {
            final int n4 = n - super.lastX;
            final int n5 = n2 - super.lastY;
            if (n4 * n4 + n5 * n5 > n3) {
                this.executeMouseMove(this.getFacelet(super.lastX, super.lastY), n4, n5);
                super.moved = true;
            }
        }
    }
    
    protected int getFacelet(final int n, final int n2) {
        for (int i = 9; i < 54; ++i) {
            if (this.faceletsO[i].contains(n, n2)) {
                return i;
            }
        }
        for (int j = 0; j < 9; ++j) {
            if (this.faceletsO[j].contains(n, n2)) {
                return j;
            }
        }
        return -1;
    }
    
    private void executeMouseMove(final int n, final int n2, final int n3) {
        int n4 = -1;
        if ((n >= 9 && n < 18) || (n >= 36 && n < 45)) {
            if (n2 > 0 && n3 > 0) {
                n4 = 0;
            }
            else if (n2 < 0 && n3 < 0) {
                n4 = 2;
            }
            else if (n2 > 0 && n3 < 0) {
                n4 = 1;
            }
            else if (n2 < 0 && n3 > 0) {
                n4 = 3;
            }
        }
        else if ((n >= 18 && n < 27) || (n >= 45 && n < 54)) {
            if (n2 + n3 + n3 > 0 && 3 * n2 > 2 * n3) {
                n4 = 0;
            }
            else if (n2 + n3 + n3 < 0 && 3 * n2 < 2 * n3) {
                n4 = 2;
            }
            else if (n2 + n3 + n3 > 0 && 3 * n2 < 2 * n3) {
                n4 = 1;
            }
            else if (n2 + n3 + n3 < 0 && 3 * n2 > 2 * n3) {
                n4 = 3;
            }
        }
        else if ((n >= 0 && n < 9) || (n >= 27 && n < 36)) {
            if (n3 + n3 > n2 && 2 * n3 + 3 * n2 < 0) {
                n4 = 0;
            }
            else if (n3 + n3 < n2 && 2 * n3 + 3 * n2 > 0) {
                n4 = 2;
            }
            else if (n3 + n3 > n2 && 2 * n3 + 3 * n2 > 0) {
                n4 = 1;
            }
            else if (n3 + n3 < n2 && 2 * n3 + 3 * n2 < 0) {
                n4 = 3;
            }
        }
        if (n4 >= 0 && n >= 0) {
            final int n5 = this.moves[n4][n];
            if (n5 < 0) {
                this.tryMove(-n5 - 1, -1);
                return;
            }
            this.tryMove(n5 - 1, 1);
        }
    }
}
