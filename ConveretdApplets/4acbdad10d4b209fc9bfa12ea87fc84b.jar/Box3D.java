import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Box3D
{
    Array3D[] boxvert;
    Triangle[] boxtri;
    Array3D centre;
    Array3D velocity;
    
    public void move_box3d(final double amountx, final double amounty, final double amountz) {
        this.boxvert[0].set_Array3D(this.boxvert[0].get_x() + amountx, this.boxvert[0].get_y() + amounty, this.boxvert[0].get_z() + amountz);
        this.boxvert[1].set_Array3D(this.boxvert[1].get_x() + amountx, this.boxvert[1].get_y() + amounty, this.boxvert[1].get_z() + amountz);
        this.boxvert[2].set_Array3D(this.boxvert[2].get_x() + amountx, this.boxvert[2].get_y() + amounty, this.boxvert[2].get_z() + amountz);
        this.boxvert[3].set_Array3D(this.boxvert[3].get_x() + amountx, this.boxvert[3].get_y() + amounty, this.boxvert[3].get_z() + amountz);
        this.boxvert[4].set_Array3D(this.boxvert[4].get_x() + amountx, this.boxvert[4].get_y() + amounty, this.boxvert[4].get_z() + amountz);
        this.boxvert[5].set_Array3D(this.boxvert[5].get_x() + amountx, this.boxvert[5].get_y() + amounty, this.boxvert[5].get_z() + amountz);
        this.boxvert[6].set_Array3D(this.boxvert[6].get_x() + amountx, this.boxvert[6].get_y() + amounty, this.boxvert[6].get_z() + amountz);
        this.boxvert[7].set_Array3D(this.boxvert[7].get_x() + amountx, this.boxvert[7].get_y() + amounty, this.boxvert[7].get_z() + amountz);
        this.centre.set_Array3D(this.centre.get_x() + amountx, this.centre.get_y() + amounty, this.centre.get_z() + amountz);
    }
    
    public void draw_box(final Graphics gBuffer, final int width, final int height) {
        for (int i = 0; i < 12; ++i) {
            this.boxtri[i].draw_triangle(gBuffer, width, height);
        }
    }
    
    public Box3D(final double centrex, final double centrey, final double centrez, final double sizex, final double sizey, final double sizez) {
        this.boxvert = new Array3D[8];
        this.boxtri = new Triangle[12];
        this.centre = new Array3D();
        this.velocity = new Array3D();
        this.centre.set_Array3D(centrex, centrey, centrez);
        this.boxvert[0] = new Array3D(centrex + 0.5 * sizex, centrey + 0.5 * sizey, centrez + 0.5 * sizez);
        this.boxvert[1] = new Array3D(centrex - 0.5 * sizex, centrey + 0.5 * sizey, centrez + 0.5 * sizez);
        this.boxvert[2] = new Array3D(centrex - 0.5 * sizex, centrey + 0.5 * sizey, centrez - 0.5 * sizez);
        this.boxvert[3] = new Array3D(centrex + 0.5 * sizex, centrey + 0.5 * sizey, centrez - 0.5 * sizez);
        this.boxvert[4] = new Array3D(centrex + 0.5 * sizex, centrey - 0.5 * sizey, centrez + 0.5 * sizez);
        this.boxvert[5] = new Array3D(centrex - 0.5 * sizex, centrey - 0.5 * sizey, centrez + 0.5 * sizez);
        this.boxvert[6] = new Array3D(centrex - 0.5 * sizex, centrey - 0.5 * sizey, centrez - 0.5 * sizez);
        this.boxvert[7] = new Array3D(centrex + 0.5 * sizex, centrey - 0.5 * sizey, centrez - 0.5 * sizez);
        this.boxtri[0] = new Triangle(this.boxvert[0], this.boxvert[1], this.boxvert[3]);
        this.boxtri[1] = new Triangle(this.boxvert[1], this.boxvert[2], this.boxvert[3]);
        this.boxtri[2] = new Triangle(this.boxvert[0], this.boxvert[4], this.boxvert[5]);
        this.boxtri[3] = new Triangle(this.boxvert[0], this.boxvert[1], this.boxvert[5]);
        this.boxtri[4] = new Triangle(this.boxvert[2], this.boxvert[3], this.boxvert[7]);
        this.boxtri[5] = new Triangle(this.boxvert[2], this.boxvert[6], this.boxvert[7]);
        this.boxtri[6] = new Triangle(this.boxvert[0], this.boxvert[4], this.boxvert[7]);
        this.boxtri[7] = new Triangle(this.boxvert[0], this.boxvert[3], this.boxvert[7]);
        this.boxtri[8] = new Triangle(this.boxvert[5], this.boxvert[1], this.boxvert[2]);
        this.boxtri[9] = new Triangle(this.boxvert[5], this.boxvert[6], this.boxvert[2]);
        this.boxtri[10] = new Triangle(this.boxvert[4], this.boxvert[5], this.boxvert[6]);
        this.boxtri[11] = new Triangle(this.boxvert[4], this.boxvert[7], this.boxvert[6]);
    }
}
