import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

final class ViewerBox extends Viewer
{
    int CELL;
    int BORD;
    int OFFSETX;
    int OFFSETY;
    Polygon[] faceletsI;
    Polygon[] faceletsO;
    Polygon[][] faceOriMarks;
    final int[][] moves;
    
    public ViewerBox(final int n, final int n2, final ActionListener actionListener) {
        super(n, n2, actionListener);
        this.moves = new int[][] { { 2, 2, 2, 8, 11, 8, -5, -5, -5, -6, -6, -6, 9, 12, 9, 3, 3, 3, -2, -2, -2, -8, -11, -8, 5, 5, 5, 2, 2, 2, 8, 11, 8, -5, -5, -5, -3, -3, -3, -9, -12, -9, 6, 6, 6, 2, 2, 2, 8, 11, 8, -5, -5, -5 }, { 6, -9, -3, 6, -12, -3, 6, -9, -3, -1, -7, 4, -1, -10, 4, -1, -7, 4, 1, 7, -4, 1, 10, -4, 1, 7, -4, 3, 9, -6, 3, 12, -6, 3, 9, -6, -1, -7, 4, -1, -10, 4, -1, -7, 4, 4, -7, -1, 4, -10, -1, 4, -7, -1 }, { -2, -2, -2, -8, -11, -8, 5, 5, 5, 6, 6, 6, -9, -12, -9, -3, -3, -3, 2, 2, 2, 8, 11, 8, -5, -5, -5, -2, -2, -2, -8, -11, -8, 5, 5, 5, 3, 3, 3, 9, 12, 9, -6, -6, -6, -2, -2, -2, -8, -11, -8, 5, 5, 5 }, { -6, 9, 3, -6, 12, 3, -6, 9, 3, 1, 7, -4, 1, 10, -4, 1, 7, -4, -1, -7, 4, -1, -10, 4, -1, -7, 4, -3, -9, 6, -3, -12, 6, -3, -9, 6, 1, 7, -4, 1, 10, -4, 1, 7, -4, -4, 7, 1, -4, 10, 1, -4, 7, 1 } };
        this.CELL = n / 16;
        this.BORD = n2 / 16;
        if (this.BORD < this.CELL) {
            this.CELL = this.BORD;
        }
        this.BORD = 2;
        this.OFFSETX = (n - 16 * this.CELL) / 2;
        this.OFFSETY = (n2 - 16 * this.CELL) / 2;
        this.faceletsI = new Polygon[54];
        this.faceletsO = new Polygon[54];
        this.faceOriMarks = new Polygon[54][4];
        for (int i = 0; i < 54; ++i) {
            this.faceletsI[i] = new Polygon();
            this.faceletsO[i] = new Polygon();
            for (int j = 0; j < 4; ++j) {
                this.faceOriMarks[i][j] = new Polygon();
            }
        }
        int n3 = 0;
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                this.doFacelet(this.CELL * (4 - l), this.CELL * (4 - l) + this.CELL * (8 + 2 * l) * k / 3, this.CELL * (5 - l), this.CELL * (5 - l) + this.CELL * (6 + 2 * l) * k / 3, this.CELL * (5 - l), this.CELL * (5 - l) + this.CELL * (6 + 2 * l) * (k + 1) / 3, this.CELL * (4 - l), this.CELL * (4 - l) + this.CELL * (8 + 2 * l) * (k + 1) / 3, this.BORD, 2 * this.BORD * (3 - k) / 3, -this.BORD, 2 * this.BORD * k / 3, -this.BORD, 2 * this.BORD * (k - 2) / 3, this.BORD, -2 * this.BORD * (k + 1) / 3, n3);
                ++n3;
            }
        }
        for (int n4 = 0; n4 < 3; ++n4) {
            for (int n5 = 0; n5 < 3; ++n5) {
                this.doFacelet(this.CELL * (5 - n4) + this.CELL * (6 + 2 * n4) * (n5 + 1) / 3, this.CELL * (5 - n4), this.CELL * (5 - n4) + this.CELL * (6 + 2 * n4) * n5 / 3, this.CELL * (5 - n4), this.CELL * (4 - n4) + this.CELL * (8 + 2 * n4) * n5 / 3, this.CELL * (4 - n4), this.CELL * (4 - n4) + this.CELL * (8 + 2 * n4) * (n5 + 1) / 3, this.CELL * (4 - n4), 2 * this.BORD * (n5 - 2) / 3, -this.BORD, 2 * this.BORD * n5 / 3, -this.BORD, 2 * this.BORD * (3 - n5) / 3, this.BORD, -2 * this.BORD * (n5 + 1) / 3, this.BORD, n3);
                ++n3;
            }
        }
        this.faceletsO[n3].addPoint(0, 0);
        this.faceletsO[n3].addPoint(6 * this.CELL, 0);
        this.faceletsO[n3].addPoint(6 * this.CELL, 2 * this.CELL);
        this.faceletsO[n3].addPoint(2 * this.CELL, 2 * this.CELL);
        this.faceletsO[n3].addPoint(2 * this.CELL, 6 * this.CELL);
        this.faceletsO[n3].addPoint(0, 6 * this.CELL);
        this.faceletsI[n3].addPoint(this.BORD, this.BORD);
        this.faceletsI[n3].addPoint(6 * this.CELL - this.BORD, this.BORD);
        this.faceletsI[n3].addPoint(6 * this.CELL - this.BORD, 2 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(2 * this.CELL - this.BORD, 2 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(2 * this.CELL - this.BORD, 6 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(this.BORD, 6 * this.CELL - this.BORD);
        this.faceOriMarks[n3][0].addPoint((4 * this.CELL + this.BORD) / 2, 0);
        this.faceOriMarks[n3][0].addPoint((8 * this.CELL - this.BORD) / 2, 0);
        this.faceOriMarks[n3][0].addPoint((8 * this.CELL - this.BORD) / 2, this.CELL);
        this.faceOriMarks[n3][0].addPoint((4 * this.CELL + this.BORD) / 2, this.CELL);
        this.faceOriMarks[n3][3].addPoint(4 * this.CELL, (this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(6 * this.CELL, (this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(6 * this.CELL, (3 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(4 * this.CELL, (3 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][2].addPoint((this.CELL + this.BORD) / 2, 4 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((3 * this.CELL - this.BORD) / 2, 4 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((3 * this.CELL - this.BORD) / 2, 6 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((this.CELL + this.BORD) / 2, 6 * this.CELL);
        this.faceOriMarks[n3][1].addPoint(0, (4 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(this.CELL, (4 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(this.CELL, (8 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(0, (8 * this.CELL - this.BORD) / 2);
        ++n3;
        this.doFacelet(10 * this.CELL, 0, 6 * this.CELL, 0, 6 * this.CELL, 2 * this.CELL, 10 * this.CELL, 2 * this.CELL, -this.BORD, this.BORD, this.BORD, this.BORD, this.BORD, -this.BORD, -this.BORD, -this.BORD, n3);
        ++n3;
        this.faceletsO[n3].addPoint(10 * this.CELL, 0);
        this.faceletsO[n3].addPoint(16 * this.CELL, 0);
        this.faceletsO[n3].addPoint(16 * this.CELL, 6 * this.CELL);
        this.faceletsO[n3].addPoint(14 * this.CELL, 6 * this.CELL);
        this.faceletsO[n3].addPoint(14 * this.CELL, 2 * this.CELL);
        this.faceletsO[n3].addPoint(10 * this.CELL, 2 * this.CELL);
        this.faceletsI[n3].addPoint(10 * this.CELL + this.BORD, this.BORD);
        this.faceletsI[n3].addPoint(16 * this.CELL - this.BORD, this.BORD);
        this.faceletsI[n3].addPoint(16 * this.CELL - this.BORD, 6 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(14 * this.CELL + this.BORD, 6 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(14 * this.CELL + this.BORD, 2 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(10 * this.CELL + this.BORD, 2 * this.CELL - this.BORD);
        this.faceOriMarks[n3][0].addPoint((24 * this.CELL + this.BORD) / 2, 0);
        this.faceOriMarks[n3][0].addPoint((28 * this.CELL - this.BORD) / 2, 0);
        this.faceOriMarks[n3][0].addPoint((28 * this.CELL - this.BORD) / 2, this.CELL);
        this.faceOriMarks[n3][0].addPoint((24 * this.CELL + this.BORD) / 2, this.CELL);
        this.faceOriMarks[n3][3].addPoint(15 * this.CELL, (4 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(16 * this.CELL, (4 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(16 * this.CELL, (8 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(15 * this.CELL, (8 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][2].addPoint((29 * this.CELL + this.BORD) / 2, 4 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((31 * this.CELL - this.BORD) / 2, 4 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((31 * this.CELL - this.BORD) / 2, 6 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((29 * this.CELL + this.BORD) / 2, 6 * this.CELL);
        this.faceOriMarks[n3][1].addPoint(10 * this.CELL, (this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(12 * this.CELL, (this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(12 * this.CELL, (3 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(10 * this.CELL, (3 * this.CELL - this.BORD) / 2);
        ++n3;
        this.doFacelet(2 * this.CELL, 6 * this.CELL, 0, 6 * this.CELL, 0, 10 * this.CELL, 2 * this.CELL, 10 * this.CELL, -this.BORD, this.BORD, this.BORD, this.BORD, this.BORD, -this.BORD, -this.BORD, -this.BORD, n3);
        ++n3;
        ++n3;
        this.doFacelet(16 * this.CELL, 6 * this.CELL, 14 * this.CELL, 6 * this.CELL, 14 * this.CELL, 10 * this.CELL, 16 * this.CELL, 10 * this.CELL, -this.BORD, this.BORD, this.BORD, this.BORD, this.BORD, -this.BORD, -this.BORD, -this.BORD, n3);
        ++n3;
        this.faceletsO[n3].addPoint(0, 10 * this.CELL);
        this.faceletsO[n3].addPoint(2 * this.CELL, 10 * this.CELL);
        this.faceletsO[n3].addPoint(2 * this.CELL, 14 * this.CELL);
        this.faceletsO[n3].addPoint(6 * this.CELL, 14 * this.CELL);
        this.faceletsO[n3].addPoint(6 * this.CELL, 16 * this.CELL);
        this.faceletsO[n3].addPoint(0, 16 * this.CELL);
        this.faceletsI[n3].addPoint(this.BORD, 10 * this.CELL + this.BORD);
        this.faceletsI[n3].addPoint(2 * this.CELL - this.BORD, 10 * this.CELL + this.BORD);
        this.faceletsI[n3].addPoint(2 * this.CELL - this.BORD, 14 * this.CELL + this.BORD);
        this.faceletsI[n3].addPoint(6 * this.CELL - this.BORD, 14 * this.CELL + this.BORD);
        this.faceletsI[n3].addPoint(6 * this.CELL - this.BORD, 16 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(this.BORD, 16 * this.CELL - this.BORD);
        this.faceOriMarks[n3][0].addPoint((this.CELL + this.BORD) / 2, 10 * this.CELL);
        this.faceOriMarks[n3][0].addPoint((this.CELL + this.BORD) / 2, 12 * this.CELL);
        this.faceOriMarks[n3][0].addPoint((3 * this.CELL - this.BORD) / 2, 12 * this.CELL);
        this.faceOriMarks[n3][0].addPoint((3 * this.CELL - this.BORD) / 2, 10 * this.CELL);
        this.faceOriMarks[n3][3].addPoint(4 * this.CELL, (29 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(4 * this.CELL, (31 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(6 * this.CELL, (31 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(6 * this.CELL, (29 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][2].addPoint((4 * this.CELL + this.BORD) / 2, 15 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((4 * this.CELL + this.BORD) / 2, 16 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((8 * this.CELL - this.BORD) / 2, 16 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((8 * this.CELL - this.BORD) / 2, 15 * this.CELL);
        this.faceOriMarks[n3][1].addPoint(0, (24 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(0, (28 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(this.CELL, (28 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(this.CELL, (24 * this.CELL + this.BORD) / 2);
        ++n3;
        this.doFacelet(10 * this.CELL, 14 * this.CELL, 6 * this.CELL, 14 * this.CELL, 6 * this.CELL, 16 * this.CELL, 10 * this.CELL, 16 * this.CELL, -this.BORD, this.BORD, this.BORD, this.BORD, this.BORD, -this.BORD, -this.BORD, -this.BORD, n3);
        ++n3;
        this.faceletsO[n3].addPoint(14 * this.CELL, 10 * this.CELL);
        this.faceletsO[n3].addPoint(16 * this.CELL, 10 * this.CELL);
        this.faceletsO[n3].addPoint(16 * this.CELL, 16 * this.CELL);
        this.faceletsO[n3].addPoint(10 * this.CELL, 16 * this.CELL);
        this.faceletsO[n3].addPoint(10 * this.CELL, 14 * this.CELL);
        this.faceletsO[n3].addPoint(14 * this.CELL, 14 * this.CELL);
        this.faceletsI[n3].addPoint(14 * this.CELL + this.BORD, 10 * this.CELL + this.BORD);
        this.faceletsI[n3].addPoint(16 * this.CELL - this.BORD, 10 * this.CELL + this.BORD);
        this.faceletsI[n3].addPoint(16 * this.CELL - this.BORD, 16 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(10 * this.CELL + this.BORD, 16 * this.CELL - this.BORD);
        this.faceletsI[n3].addPoint(10 * this.CELL + this.BORD, 14 * this.CELL + this.BORD);
        this.faceletsI[n3].addPoint(14 * this.CELL + this.BORD, 14 * this.CELL + this.BORD);
        this.faceOriMarks[n3][0].addPoint((29 * this.CELL + this.BORD) / 2, 10 * this.CELL);
        this.faceOriMarks[n3][0].addPoint((29 * this.CELL + this.BORD) / 2, 12 * this.CELL);
        this.faceOriMarks[n3][0].addPoint((31 * this.CELL - this.BORD) / 2, 12 * this.CELL);
        this.faceOriMarks[n3][0].addPoint((31 * this.CELL - this.BORD) / 2, 10 * this.CELL);
        this.faceOriMarks[n3][3].addPoint(16 * this.CELL, (24 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(16 * this.CELL, (28 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(15 * this.CELL, (28 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][3].addPoint(15 * this.CELL, (24 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][2].addPoint((24 * this.CELL + this.BORD) / 2, 15 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((24 * this.CELL + this.BORD) / 2, 16 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((28 * this.CELL - this.BORD) / 2, 16 * this.CELL);
        this.faceOriMarks[n3][2].addPoint((28 * this.CELL - this.BORD) / 2, 15 * this.CELL);
        this.faceOriMarks[n3][1].addPoint(10 * this.CELL, (29 * this.CELL + this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(10 * this.CELL, (31 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(12 * this.CELL, (31 * this.CELL - this.BORD) / 2);
        this.faceOriMarks[n3][1].addPoint(12 * this.CELL, (29 * this.CELL + this.BORD) / 2);
        ++n3;
        for (int n6 = 0; n6 < 3; ++n6) {
            for (int n7 = 0; n7 < 3; ++n7) {
                this.doFacelet(this.CELL * (13 - n7), this.CELL * (3 + n7) + this.CELL * (10 - 2 * n7) * n6 / 3, this.CELL * (14 - n7), this.CELL * (2 + n7) + this.CELL * (12 - 2 * n7) * n6 / 3, this.CELL * (14 - n7), this.CELL * (2 + n7) + this.CELL * (12 - 2 * n7) * (n6 + 1) / 3, this.CELL * (13 - n7), this.CELL * (3 + n7) + this.CELL * (10 - 2 * n7) * (n6 + 1) / 3, this.BORD, 2 * this.BORD * n6 / 3, -this.BORD, 2 * this.BORD * (3 - n6) / 3, -this.BORD, -2 * this.BORD * (n6 + 1) / 3, this.BORD, 2 * this.BORD * (n6 - 2) / 3, n3);
                ++n3;
            }
        }
        for (int n8 = 0; n8 < 3; ++n8) {
            for (int n9 = 0; n9 < 3; ++n9) {
                this.doFacelet(this.CELL * (2 + n8) + this.CELL * (12 - 2 * n8) * (n9 + 1) / 3, this.CELL * (14 - n8), this.CELL * (2 + n8) + this.CELL * (12 - 2 * n8) * n9 / 3, this.CELL * (14 - n8), this.CELL * (3 + n8) + this.CELL * (10 - 2 * n8) * n9 / 3, this.CELL * (13 - n8), this.CELL * (3 + n8) + this.CELL * (10 - 2 * n8) * (n9 + 1) / 3, this.CELL * (13 - n8), -2 * this.BORD * (n9 + 1) / 3, -this.BORD, 2 * this.BORD * (3 - n9) / 3, -this.BORD, 2 * this.BORD * n9 / 3, this.BORD, 2 * this.BORD * (n9 - 3) / 3, this.BORD, n3);
                ++n3;
            }
        }
        for (int n10 = 0; n10 < 3; ++n10) {
            for (int n11 = 0; n11 < 3; ++n11) {
                this.doFacelet(this.CELL * (9 - 2 * n11), this.CELL * (5 + 2 * n10), this.CELL * (11 - 2 * n11), this.CELL * (5 + 2 * n10), this.CELL * (11 - 2 * n11), this.CELL * (7 + 2 * n10), this.CELL * (9 - 2 * n11), this.CELL * (7 + 2 * n10), this.BORD, this.BORD, -this.BORD, this.BORD, -this.BORD, -this.BORD, this.BORD, -this.BORD, n3);
                ++n3;
            }
        }
        for (int n12 = 0; n12 < 54; ++n12) {
            this.faceletsI[n12].translate(this.OFFSETX, this.OFFSETY);
            this.faceletsO[n12].translate(this.OFFSETX, this.OFFSETY);
            for (int n13 = 0; n13 < 4; ++n13) {
                this.faceOriMarks[n12][n13].translate(this.OFFSETX, this.OFFSETY);
            }
        }
    }
    
    private void doFacelet(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16, final int n17) {
        this.faceletsO[n17].addPoint(n, n2);
        this.faceletsO[n17].addPoint(n3, n4);
        this.faceletsO[n17].addPoint(n5, n6);
        this.faceletsO[n17].addPoint(n7, n8);
        this.faceletsI[n17].addPoint(n + n9, n2 + n10);
        this.faceletsI[n17].addPoint(n3 + n11, n4 + n12);
        this.faceletsI[n17].addPoint(n5 + n13, n6 + n14);
        this.faceletsI[n17].addPoint(n7 + n15, n8 + n16);
        this.faceOriMarks[n17][0].addPoint(((n + n9) * 3 + (n3 + n11) + 2) / 4, ((n2 + n10) * 3 + (n4 + n12) + 2) / 4);
        this.faceOriMarks[n17][0].addPoint((n + n9 + 3 * (n3 + n11) + 2) / 4, (n2 + n10 + 3 * (n4 + n12) + 2) / 4);
        this.faceOriMarks[n17][0].addPoint((n + n9 + n7 + n15 + 3 * (n3 + n11 + n5 + n13) + 4) / 8, (n2 + n10 + n8 + n16 + 3 * (n4 + n12 + n6 + n14) + 4) / 8);
        this.faceOriMarks[n17][0].addPoint(((n + n9 + n7 + n15) * 3 + (n3 + n11 + n5 + n13) + 4) / 8, ((n2 + n10 + n8 + n16) * 3 + (n4 + n12 + n6 + n14) + 4) / 8);
        this.faceOriMarks[n17][1].addPoint(((n3 + n11) * 3 + (n5 + n13) + 2) / 4, ((n4 + n12) * 3 + (n6 + n14) + 2) / 4);
        this.faceOriMarks[n17][1].addPoint((n3 + n11 + 3 * (n5 + n13) + 2) / 4, (n4 + n12 + 3 * (n6 + n14) + 2) / 4);
        this.faceOriMarks[n17][1].addPoint((n3 + n11 + n + n9 + 3 * (n5 + n13 + n7 + n15) + 4) / 8, (n4 + n12 + n2 + n10 + 3 * (n6 + n14 + n8 + n16) + 4) / 8);
        this.faceOriMarks[n17][1].addPoint(((n3 + n11 + n + n9) * 3 + (n5 + n13 + n7 + n15) + 4) / 8, ((n4 + n12 + n2 + n10) * 3 + (n6 + n14 + n8 + n16) + 4) / 8);
        this.faceOriMarks[n17][2].addPoint(((n5 + n13) * 3 + (n7 + n15) + 2) / 4, ((n6 + n14) * 3 + (n8 + n16) + 2) / 4);
        this.faceOriMarks[n17][2].addPoint((n5 + n13 + 3 * (n7 + n15) + 2) / 4, (n6 + n14 + 3 * (n8 + n16) + 2) / 4);
        this.faceOriMarks[n17][2].addPoint((n5 + n13 + n3 + n11 + 3 * (n7 + n15 + n + n9) + 4) / 8, (n6 + n14 + n4 + n12 + 3 * (n8 + n16 + n2 + n10) + 4) / 8);
        this.faceOriMarks[n17][2].addPoint(((n5 + n13 + n3 + n11) * 3 + (n7 + n15 + n + n9) + 4) / 8, ((n6 + n14 + n4 + n12) * 3 + (n8 + n16 + n2 + n10) + 4) / 8);
        this.faceOriMarks[n17][3].addPoint(((n7 + n15) * 3 + (n + n9) + 2) / 4, ((n8 + n16) * 3 + (n2 + n10) + 2) / 4);
        this.faceOriMarks[n17][3].addPoint((n7 + n15 + 3 * (n + n9) + 2) / 4, (n8 + n16 + 3 * (n2 + n10) + 2) / 4);
        this.faceOriMarks[n17][3].addPoint((n7 + n15 + n5 + n13 + 3 * (n + n9 + n3 + n11) + 4) / 8, (n8 + n16 + n6 + n14 + 3 * (n2 + n10 + n4 + n12) + 4) / 8);
        this.faceOriMarks[n17][3].addPoint(((n7 + n15 + n5 + n13) * 3 + (n + n9 + n3 + n11) + 4) / 8, ((n8 + n16 + n6 + n14) * 3 + (n2 + n10 + n4 + n12) + 4) / 8);
    }
    
    public void paint(final Graphics graphics) {
        if (super.offImage == null) {
            this.initialise();
        }
        super.offGraphics.setColor(this.getBackground());
        super.offGraphics.fillRect(0, 0, super.width, super.height);
        super.offGraphics.setColor(super.baseColor);
        super.offGraphics.fillRect(this.OFFSETX, this.OFFSETY, 16 * this.CELL, 16 * this.CELL);
        final CubePosition cubePos = Cubie.settings.cubePos;
        cubePos.getFaceletColors(cubePos.cubeletPerm, cubePos.cubeletOri, cubePos.faceOri, cubePos.faceletColor, cubePos.faceletOri);
        for (int i = 0; i < 54; ++i) {
            super.offGraphics.setColor(super.colors[Cubie.settings.cubePos.cubeletPerm[20 + Cubie.settings.cubePos.faceletColor[i]] - 20]);
            super.offGraphics.fillPolygon(this.faceletsI[i]);
            if (Cubie.settings.superGroup) {
                super.offGraphics.setColor(super.baseColor);
                super.offGraphics.fillPolygon(this.faceOriMarks[i][Cubie.settings.cubePos.faceletOri[i]]);
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
        for (int i = 0; i < 54; ++i) {
            if (this.faceletsO[i].contains(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private void executeMouseMove(final int n, final int n2, final int n3) {
        int n4 = -1;
        if (n2 > n3 && n2 > -n3) {
            n4 = 0;
        }
        else if (n2 < n3 && n2 < -n3) {
            n4 = 2;
        }
        else if (n3 > n2 && n3 > -n2) {
            n4 = 1;
        }
        else if (n3 < n2 && n3 < -n2) {
            n4 = 3;
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
