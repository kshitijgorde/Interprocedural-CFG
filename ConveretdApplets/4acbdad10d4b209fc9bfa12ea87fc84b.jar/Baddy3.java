// 
// Decompiled by Procyon v0.5.30
// 

class Baddy3
{
    private static final double TWOPI = 6.283185307179586;
    private int radius0;
    private int radius1;
    Common com;
    Common com_shot;
    
    public void clear_shot_unwanted_lines() {
        int n = 0;
        do {
            this.com_shot.set_lines(n, 0, 1, true);
            this.com_shot.set_lines(n, 1, 2, true);
            this.com_shot.set_lines(n, 2, 0, false);
        } while (++n < 2);
    }
    
    public void move_baddy3_shot_to(final double n, final double n2, final double n3) {
        this.com_shot.verts[0].set_Array3D(n, n2 - this.radius1, n3);
        this.com_shot.verts[1].set_Array3D(n + this.radius0, n2, n3);
        this.com_shot.verts[2].set_Array3D(n, n2 + this.radius1, n3);
        this.com_shot.verts[3].set_Array3D(n - this.radius0, n2, n3);
        this.com_shot.centre.set_Array3D(n, n2, n3);
        this.com_shot.rotate_z_axis(-this.com.get_grid_position() * 30 + 75);
    }
    
    public Baddy3(final int n, final int n2, final int n3, final int radius0, final int radius2) {
        this.com = new Common(8, 4);
        this.com_shot = new Common(4, 2);
        this.com.centre.set_Array3D(n, n2, n3);
        this.radius0 = radius0;
        this.radius1 = radius2;
        this.com.set_blue_colour(0, 0);
        this.com.set_blue_colour(0, 1);
        this.com.set_green_colour(0, 0);
        this.com.set_green_colour(255, 1);
        this.com.set_red_colour(0, 0);
        this.com.set_red_colour(0, 1);
        this.com_shot.set_blue_colour(255, 0);
        this.com_shot.set_blue_colour(255, 1);
        this.com_shot.set_green_colour(255, 0);
        this.com_shot.set_green_colour(255, 1);
        this.com_shot.set_red_colour(255, 0);
        this.com_shot.set_red_colour(255, 1);
        this.com.set_baddy_value(1500);
        this.com_shot.set_baddy_value(50);
        this.com.set_visible(false);
        this.com_shot.set_visible(false);
        this.com.set_on_web(false);
        this.com.set_alive(true);
        this.com.set_top_of_web(false);
        this.com_shot.set_on_web(false);
        this.com_shot.set_alive(true);
        this.com_shot.set_top_of_web(false);
        this.com.reset_counter();
        this.com_shot.reset_counter();
        this.com_shot.set_shot(false);
        this.com.verts[0].set_Array3D(n, n2 - radius2, n3);
        this.com.verts[1].set_Array3D(n + radius0, n2 - radius2, n3);
        this.com.verts[2].set_Array3D(n + radius0, n2, n3);
        this.com.verts[3].set_Array3D(n + radius0, n2 + radius2, n3);
        this.com.verts[4].set_Array3D(n, n2 + radius2, n3);
        this.com.verts[5].set_Array3D(n - radius0, n2 + radius2, n3);
        this.com.verts[6].set_Array3D(n - radius0, n2, n3);
        this.com.verts[7].set_Array3D(n - radius0, n2 - radius2, n3);
        this.com.tris[0].set_triangle(this.com.verts[0], this.com.verts[1], this.com.verts[2]);
        this.com.tris[1].set_triangle(this.com.verts[2], this.com.verts[3], this.com.verts[4]);
        this.com.tris[2].set_triangle(this.com.verts[4], this.com.verts[5], this.com.verts[6]);
        this.com.tris[3].set_triangle(this.com.verts[6], this.com.verts[7], this.com.verts[0]);
        this.clear_unwanted_lines();
        this.com_shot.verts[0].set_Array3D(n, n2 - radius2, n3);
        this.com_shot.verts[1].set_Array3D(n + radius0, n2, n3);
        this.com_shot.verts[2].set_Array3D(n, n2 + radius2, n3);
        this.com_shot.verts[3].set_Array3D(n - radius0, n2, n3);
        this.com_shot.tris[0].set_triangle(this.com_shot.verts[0], this.com_shot.verts[1], this.com_shot.verts[2]);
        this.com_shot.tris[1].set_triangle(this.com_shot.verts[2], this.com_shot.verts[3], this.com_shot.verts[0]);
        this.clear_shot_unwanted_lines();
    }
    
    public Baddy3() {
    }
    
    public void clear_unwanted_lines() {
        int n = 0;
        do {
            this.com.set_lines(n, 0, 1, true);
            this.com.set_lines(n, 1, 2, true);
            this.com.set_lines(n, 2, 0, true);
        } while (++n < 4);
    }
    
    public void move_baddy3_to(final double n, final double n2, final double n3) {
        this.com.verts[0].set_Array3D(n, n2 - this.radius1, n3);
        this.com.verts[1].set_Array3D(n + this.radius0, n2 - this.radius1, n3);
        this.com.verts[2].set_Array3D(n + this.radius0, n2, n3);
        this.com.verts[3].set_Array3D(n + this.radius0, n2 + this.radius1, n3);
        this.com.verts[4].set_Array3D(n, n2 + this.radius1, n3);
        this.com.verts[5].set_Array3D(n - this.radius0, n2 + this.radius1, n3);
        this.com.verts[6].set_Array3D(n - this.radius0, n2, n3);
        this.com.verts[7].set_Array3D(n - this.radius0, n2 - this.radius1, n3);
        this.com.centre.set_Array3D(n, n2, n3);
        this.com.rotate_z_axis(-this.com.get_grid_position() * 30 + 75);
    }
    
    public void move_baddy3_shot_to_grid_position() {
        this.com_shot.centre.set_x(Math.sin(0.017453292519943295 * (this.com_shot.get_grid_position() * 30 + 15)) * 180.0);
        this.com_shot.centre.set_y(Math.cos(0.017453292519943295 * (this.com_shot.get_grid_position() * 30 + 15)) * 180.0);
        this.move_baddy3_shot_to(this.com_shot.centre.get_x(), this.com_shot.centre.get_y(), this.com_shot.centre.get_z());
    }
    
    public void move_baddy3_to_grid_position() {
        this.com.centre.set_x(Math.sin(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.com.centre.set_y(Math.cos(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.move_baddy3_to(this.com.centre.get_x(), this.com.centre.get_y(), this.com.centre.get_z());
    }
}
