import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Cubist extends Artist
{
    double[][] cube;
    Quaternion qx;
    Quaternion qy;
    Quaternion qz;
    Quaternion qxinv;
    Quaternion qyinv;
    Quaternion qzinv;
    boolean xrotor;
    boolean yrotor;
    boolean zrotor;
    
    Cubist() {
        super.noPoints = 8;
        super.s1 = new int[super.noPoints][2];
        this.cube = new double[super.noPoints][3];
        this.refcube();
        this.qx = new Quaternion("X", 1.0);
        this.qy = new Quaternion("Y", 1.0);
        this.qz = new Quaternion("Z", 1.0);
        this.qxinv = this.qx.inv();
        this.qyinv = this.qy.inv();
        this.qzinv = this.qz.inv();
        this.xrotor = true;
        this.yrotor = true;
        this.zrotor = true;
    }
    
    public void compose(final Graphics graphics) {
        graphics.setColor(Color.black);
        this.rotateCube();
        this.viewcoord(this.cube, super.s1);
        this.drawCube(graphics, super.s1);
    }
    
    public void drawCube(final Graphics graphics, final int[][] array) {
        for (int i = 0; i < 3; ++i) {
            this.drawLines(graphics, array[i][0], array[i][1], array[i + 1][0], array[i + 1][1]);
        }
        this.drawLines(graphics, array[3][0], array[3][1], array[0][0], array[0][1]);
        for (int j = 4; j < super.noPoints - 1; ++j) {
            this.drawLines(graphics, array[j][0], array[j][1], array[j + 1][0], array[j + 1][1]);
        }
        this.drawLines(graphics, array[7][0], array[7][1], array[4][0], array[4][1]);
        for (int k = 0; k < 4; ++k) {
            this.drawLines(graphics, array[k][0], array[k][1], array[k + 4][0], array[k + 4][1]);
        }
    }
    
    public void refcube() {
        this.cube[0] = new double[] { 1.0, 1.0, 1.0 };
        this.cube[1] = new double[] { -1.0, 1.0, 1.0 };
        this.cube[2] = new double[] { -1.0, 1.0, -1.0 };
        this.cube[3] = new double[] { 1.0, 1.0, -1.0 };
        this.cube[4] = new double[] { 1.0, -1.0, 1.0 };
        this.cube[5] = new double[] { -1.0, -1.0, 1.0 };
        this.cube[6] = new double[] { -1.0, -1.0, -1.0 };
        this.cube[7] = new double[] { 1.0, -1.0, -1.0 };
    }
    
    public void rotateCube() {
        for (int i = 0; i < 8; ++i) {
            if (this.xrotor) {
                this.cube[i] = this.qx.mult(this.cube[i]).mult(this.qxinv).vector;
            }
            if (this.yrotor) {
                this.cube[i] = this.qy.mult(this.cube[i]).mult(this.qyinv).vector;
            }
            if (this.zrotor) {
                this.cube[i] = this.qz.mult(this.cube[i]).mult(this.qzinv).vector;
            }
        }
    }
}
