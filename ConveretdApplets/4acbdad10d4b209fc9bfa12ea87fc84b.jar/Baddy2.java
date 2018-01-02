// 
// Decompiled by Procyon v0.5.30
// 

class Baddy2
{
    private static final double TWOPI = 6.283185307179586;
    private int radius;
    Common com;
    
    public void move_baddy2_to_grid_position() {
        this.com.centre.set_x(Math.sin(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.com.centre.set_y(Math.cos(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.move_baddy2_to(this.com.centre.get_x(), this.com.centre.get_y(), this.com.centre.get_z());
    }
    
    public Baddy2(final int n, final int n2, final int n3, final int radius) {
        this.com = new Common(5, 2);
        this.com.centre.set_Array3D(n, n2, n3);
        this.radius = radius;
        this.com.set_blue_colour(0, 0);
        this.com.set_blue_colour(0, 1);
        this.com.set_green_colour(0, 0);
        this.com.set_green_colour(255, 1);
        this.com.set_red_colour(0, 0);
        this.com.set_red_colour(0, 1);
        this.com.set_baddy_value(2000);
        this.com.set_visible(false);
        this.com.set_on_web(false);
        this.com.set_alive(true);
        this.com.set_top_of_web(false);
        this.com.reset_counter();
        this.com.verts[0].set_Array3D(n, n2, n3);
        this.com.verts[1].set_Array3D(n - radius, n2 - radius / 2, n3);
        this.com.verts[2].set_Array3D(n - radius, n2 + radius / 2, n3);
        this.com.verts[3].set_Array3D(n + radius, n2 + radius / 2, n3);
        this.com.verts[4].set_Array3D(n + radius, n2 - radius / 2, n3);
        this.com.tris[0].set_triangle(this.com.verts[0], this.com.verts[1], this.com.verts[2]);
        this.com.tris[1].set_triangle(this.com.verts[0], this.com.verts[3], this.com.verts[4]);
        this.clear_unwanted_lines();
    }
    
    public Baddy2() {
    }
    
    public void clear_unwanted_lines() {
        int n = 0;
        do {
            this.com.set_lines(n, 0, 1, true);
            this.com.set_lines(n, 1, 2, true);
            this.com.set_lines(n, 2, 0, true);
        } while (++n < 2);
    }
    
    public void move_baddy2_to(final double n, final double n2, final double n3) {
        this.com.verts[0].set_Array3D(n, n2, n3);
        this.com.verts[1].set_Array3D(n - this.radius, n2 - this.radius / 2, n3);
        this.com.verts[2].set_Array3D(n - this.radius, n2 + this.radius / 2, n3);
        this.com.verts[3].set_Array3D(n + this.radius, n2 + this.radius / 2, n3);
        this.com.verts[4].set_Array3D(n + this.radius, n2 - this.radius / 2, n3);
        this.com.centre.set_Array3D(n, n2, n3);
        this.com.rotate_z_axis(-this.com.get_grid_position() * 30 - 15);
    }
}
