import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class ViewerFlat extends Viewer
{
    int FACE;
    int CELL;
    int BORD;
    int OFFSETX;
    int OFFSETY;
    int[] facecoordX;
    int[] facecoordY;
    final int[][] moves;
    
    public ViewerFlat(final int n, final int n2, final ActionListener actionListener) {
        super(n, n2, actionListener);
        this.moves = new int[][] { { -2, -2, -2, -8, -11, -8, 5, 5, 5, -6, -6, -6, 9, 12, 9, 3, 3, 3, -2, -2, -2, -8, -11, -8, 5, 5, 5, -2, -2, -2, -8, -11, -8, 5, 5, 5, -3, -3, -3, -9, -12, -9, 6, 6, 6, -2, -2, -2, -8, -11, -8, 5, 5, 5 }, { 6, -9, -3, 6, -12, -3, 6, -9, -3, 1, 7, -4, 1, 10, -4, 1, 7, -4, 1, 7, -4, 1, 10, -4, 1, 7, -4, 3, 9, -6, 3, 12, -6, 3, 9, -6, 1, 7, -4, 1, 10, -4, 1, 7, -4, 4, -7, -1, 4, -10, -1, 4, -7, -1 }, { 2, 2, 2, 8, 11, 8, -5, -5, -5, 6, 6, 6, -9, -12, -9, -3, -3, -3, 2, 2, 2, 8, 11, 8, -5, -5, -5, 2, 2, 2, 8, 11, 8, -5, -5, -5, 3, 3, 3, 9, 12, 9, -6, -6, -6, 2, 2, 2, 8, 11, 8, -5, -5, -5 }, { -6, 9, 3, -6, 12, 3, -6, 9, 3, -1, -7, 4, -1, -10, 4, -1, -7, 4, -1, -7, 4, -1, -10, 4, -1, -7, 4, -3, -9, 6, -3, -12, 6, -3, -9, 6, -1, -7, 4, -1, -10, 4, -1, -7, 4, -4, 7, 1, -4, 10, 1, -4, 7, 1 } };
        this.CELL = n / 12;
        this.FACE = n2 / 9;
        if (this.FACE < this.CELL) {
            this.CELL = this.FACE;
        }
        this.FACE = this.CELL * 3;
        this.BORD = 2;
        this.OFFSETX = (n - 4 * this.FACE) / 2;
        this.OFFSETY = (n2 - 3 * this.FACE) / 2;
        this.facecoordX = new int[6];
        this.facecoordY = new int[6];
        this.facecoordX[0] = this.OFFSETX;
        this.facecoordY[0] = this.OFFSETY + this.FACE;
        this.facecoordX[1] = this.OFFSETX + this.FACE;
        this.facecoordY[1] = this.OFFSETY;
        this.facecoordX[2] = this.OFFSETX + this.FACE;
        this.facecoordY[2] = this.OFFSETY + this.FACE;
        this.facecoordX[3] = this.OFFSETX + 2 * this.FACE;
        this.facecoordY[3] = this.OFFSETY + this.FACE;
        this.facecoordX[4] = this.OFFSETX + this.FACE;
        this.facecoordY[4] = this.OFFSETY + 2 * this.FACE;
        this.facecoordX[5] = this.OFFSETX + 3 * this.FACE;
        this.facecoordY[5] = this.OFFSETY + this.FACE;
    }
    
    public void paint(final Graphics graphics) {
        if (super.offImage == null) {
            this.initialise();
        }
        super.offGraphics.setColor(this.getBackground());
        super.offGraphics.fillRect(0, 0, super.width, super.height);
        final CubePosition cubePos = Cubie.settings.cubePos;
        cubePos.getFaceletColors(cubePos.cubeletPerm, cubePos.cubeletOri, cubePos.faceOri, cubePos.faceletColor, cubePos.faceletOri);
        int n = 0;
        for (int i = 0; i < 6; ++i) {
            super.offGraphics.setColor(super.baseColor);
            super.offGraphics.fillRect(this.facecoordX[i], this.facecoordY[i], this.FACE, this.FACE);
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    super.offGraphics.setColor(super.colors[Cubie.settings.cubePos.cubeletPerm[20 + Cubie.settings.cubePos.faceletColor[n]] - 20]);
                    super.offGraphics.fillRect(this.facecoordX[i] + this.BORD + k * this.CELL, this.facecoordY[i] + this.BORD + j * this.CELL, this.CELL - 2 * this.BORD, this.CELL - 2 * this.BORD);
                    if (Cubie.settings.superGroup) {
                        super.offGraphics.setColor(super.baseColor);
                        if (Cubie.settings.cubePos.faceletOri[n] == 0) {
                            super.offGraphics.fillRect(this.facecoordX[i] + this.BORD + k * this.CELL + (this.CELL - this.BORD * 2) / 4, this.facecoordY[i] + this.BORD + j * this.CELL, this.CELL / 2 - this.BORD, this.CELL / 2 - this.BORD);
                        }
                        else if (Cubie.settings.cubePos.faceletOri[n] == 3) {
                            super.offGraphics.fillRect(this.facecoordX[i] + k * this.CELL + this.CELL / 2, this.facecoordY[i] + this.BORD + j * this.CELL + (this.CELL - this.BORD * 2) / 4, this.CELL / 2 - this.BORD, this.CELL / 2 - this.BORD);
                        }
                        else if (Cubie.settings.cubePos.faceletOri[n] == 2) {
                            super.offGraphics.fillRect(this.facecoordX[i] + this.BORD + k * this.CELL + (this.CELL - this.BORD * 2) / 4, this.facecoordY[i] + j * this.CELL + this.CELL / 2, this.CELL / 2 - this.BORD, this.CELL / 2 - this.BORD);
                        }
                        else if (Cubie.settings.cubePos.faceletOri[n] == 1) {
                            super.offGraphics.fillRect(this.facecoordX[i] + k * this.CELL + this.BORD, this.facecoordY[i] + this.BORD + j * this.CELL + (this.CELL - this.BORD * 2) / 4, this.CELL / 2 - this.BORD, this.CELL / 2 - this.BORD);
                        }
                    }
                    ++n;
                }
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
        for (int i = 0; i < 6; ++i) {
            if (n >= this.facecoordX[i] && n < this.facecoordX[i] + this.FACE && n2 >= this.facecoordY[i] && n2 < this.facecoordY[i] + this.FACE) {
                return i * 9 + 3 * ((n2 - this.facecoordY[i]) / this.CELL) + (n - this.facecoordX[i]) / this.CELL;
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
