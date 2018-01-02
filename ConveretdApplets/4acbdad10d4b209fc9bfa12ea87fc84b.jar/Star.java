// 
// Decompiled by Procyon v0.5.30
// 

class Star
{
    private static final double TWOPI = 6.283185307179586;
    private int radius;
    Common com;
    
    public void move_star_to(final double n, final double n2, final double n3) {
        this.com.verts[0].set_Array3D(n, n2 - this.radius, n3);
        this.com.verts[1].set_Array3D(n + 2 * this.radius, n2 - 2 * this.radius, n3);
        this.com.verts[2].set_Array3D(n + this.radius, n2, n3);
        this.com.verts[3].set_Array3D(n + 2 * this.radius, n2 + 2 * this.radius, n3);
        this.com.verts[4].set_Array3D(n, n2 + this.radius, n3);
        this.com.verts[5].set_Array3D(n - 2 * this.radius, n2 + 2 * this.radius, n3);
        this.com.verts[6].set_Array3D(n - this.radius, n2, n3);
        this.com.verts[7].set_Array3D(n - 2 * this.radius, n2 - 2 * this.radius, n3);
        this.com.centre.set_Array3D(n, n2, n3);
    }
    
    public Star(final int n, final int n2, final int n3, final int radius) {
        this.com = new Common(8, 4);
        this.com.centre.set_Array3D(n, n2, n3);
        this.radius = radius;
        this.com.set_blue_colour(0, 0);
        this.com.set_blue_colour(0, 1);
        this.com.set_green_colour(0, 0);
        this.com.set_green_colour(0, 1);
        this.com.set_red_colour(0, 0);
        this.com.set_red_colour(255, 1);
        this.com.set_baddy_value(1000);
        this.com.set_visible(false);
        this.com.set_on_web(false);
        this.com.set_alive(true);
        this.com.set_top_of_web(false);
        this.com.reset_counter();
        this.com.verts[0].set_Array3D(n, n2 - radius, n3);
        this.com.verts[1].set_Array3D(n + 2 * radius, n2 - 2 * radius, n3);
        this.com.verts[2].set_Array3D(n + radius, n2, n3);
        this.com.verts[3].set_Array3D(n + 2 * radius, n2 + 2 * radius, n3);
        this.com.verts[4].set_Array3D(n, n2 + radius, n3);
        this.com.verts[5].set_Array3D(n - 2 * radius, n2 + 2 * radius, n3);
        this.com.verts[6].set_Array3D(n - radius, n2, n3);
        this.com.verts[7].set_Array3D(n - 2 * radius, n2 - 2 * radius, n3);
        this.com.tris[0].set_triangle(this.com.verts[0], this.com.verts[1], this.com.verts[2]);
        this.com.tris[1].set_triangle(this.com.verts[2], this.com.verts[3], this.com.verts[4]);
        this.com.tris[2].set_triangle(this.com.verts[4], this.com.verts[5], this.com.verts[6]);
        this.com.tris[3].set_triangle(this.com.verts[6], this.com.verts[7], this.com.verts[0]);
    }
    
    public Star() {
    }
    
    public void clear_unwanted_lines() {
        int n = 0;
        do {
            this.com.set_lines(n, 0, 1, true);
            this.com.set_lines(n, 1, 2, true);
            this.com.set_lines(n, 2, 0, true);
        } while (++n < 4);
    }
    
    public void move_star_to_grid_position() {
        this.com.centre.set_x(Math.sin(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.com.centre.set_y(Math.cos(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.move_star_to(this.com.centre.get_x(), this.com.centre.get_y(), this.com.centre.get_z());
    }
}
