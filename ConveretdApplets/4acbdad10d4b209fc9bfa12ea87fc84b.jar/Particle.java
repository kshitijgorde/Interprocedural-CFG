import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Particle
{
    private static final double PERSPECTIVE = 1.002;
    Common[] com;
    int itssize;
    
    public Particle(final int n, final int n2, final int n3, final int n4, final int itssize) {
        this.com = new Common[3];
        int n5 = 0;
        do {
            this.com[n5] = new Common(0, 0);
            this.com[n5].centre.set_Array3D(n, n2, n3 - n5 * 10);
            this.com[n5].velocity.set_Array3D(0.0, 0.0, n4);
            this.com[n5].set_blue_colour(255 - n5 * 85, 0);
            this.com[n5].set_red_colour(255 - n5 * 85, 0);
            this.com[n5].set_green_colour(255 - n5 * 85, 0);
        } while (++n5 < 3);
        this.itssize = itssize;
    }
    
    public void draw_particles(final Graphics graphics, final int n, final int n2, final Array3D array3D) {
        int n3 = 2;
        do {
            graphics.setColor(new Color(this.com[n3].get_red_colour(0), this.com[n3].get_green_colour(0), this.com[n3].get_blue_colour(0)));
            graphics.fillRect((int)this.get_screen_x(n, array3D, n3), (int)this.get_screen_y(n2, array3D, n3), this.itssize, this.itssize);
        } while (--n3 >= 0);
    }
    
    public double get_screen_x(final int n, final Array3D array3D, final int n2) {
        return (-array3D.get_x() + this.com[n2].centre.get_x()) * Math.pow(1.002, -array3D.get_z() + this.com[n2].centre.get_z()) + n / 2.0;
    }
    
    public int get_size() {
        return this.itssize;
    }
    
    public double get_screen_y(final int n, final Array3D array3D, final int n2) {
        return (-array3D.get_y() + this.com[n2].centre.get_y()) * Math.pow(1.002, -array3D.get_z() + this.com[n2].centre.get_z()) + n / 2.0;
    }
}
