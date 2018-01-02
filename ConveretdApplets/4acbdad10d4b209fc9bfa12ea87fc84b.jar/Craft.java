// 
// Decompiled by Procyon v0.5.30
// 

class Craft
{
    private static final double TWOPI = 6.283185307179586;
    Common com;
    private int jumping;
    private int[] ptx;
    private int[] pty;
    
    public Craft(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final boolean b) {
        this.ptx = new int[3];
        this.pty = new int[3];
        this.com = new Common(7, 4);
        this.com.centre.set_Array3D(n, n2, n3);
        this.jumping = 0;
        this.com.velocity.set_Array3D(0.0, 0.0, 0.0);
        this.com.set_grid_position(1);
        this.com.set_red_colour(255, 0);
        this.com.set_green_colour(255, 0);
        this.com.set_blue_colour(0, 0);
        this.com.set_visible(b);
        this.com.set_alive(true);
        this.ptx[0] = n4;
        this.ptx[1] = n6;
        this.ptx[2] = n8;
        this.pty[0] = n5;
        this.pty[1] = n7;
        this.pty[2] = n9;
        this.com.verts[0].set_Array3D(n - this.ptx[0], n2 - this.pty[0], n3);
        this.com.verts[1].set_Array3D(n - this.ptx[1], n2 - this.pty[1], n3);
        this.com.verts[2].set_Array3D(n - this.ptx[2], n2 - this.pty[2], n3);
        this.com.verts[3].set_Array3D(n, n2 + this.pty[0], n3);
        this.com.verts[4].set_Array3D(n + this.ptx[2], n2, n3);
        this.com.verts[5].set_Array3D(n + this.ptx[1], n2, n3);
        this.com.verts[6].set_Array3D(n + this.ptx[0], n2 - this.pty[0], n3);
        this.com.tris[0].set_triangle(this.com.verts[0], this.com.verts[1], this.com.verts[2]);
        this.com.tris[1].set_triangle(this.com.verts[1], this.com.verts[2], this.com.verts[3]);
        this.com.tris[2].set_triangle(this.com.verts[3], this.com.verts[4], this.com.verts[5]);
        this.com.tris[3].set_triangle(this.com.verts[4], this.com.verts[5], this.com.verts[6]);
        this.clear_unwanted_lines();
    }
    
    public void resize_craft(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.ptx[0] = n;
        this.ptx[1] = n3;
        this.ptx[2] = n5;
        this.pty[0] = n2;
        this.pty[1] = n4;
        this.pty[2] = n6;
        this.com.verts[0].set_Array3D(this.com.centre.get_x() - this.ptx[0], this.com.centre.get_y() - this.pty[0], this.com.centre.get_z());
        this.com.verts[1].set_Array3D(this.com.centre.get_x() - this.ptx[1], this.com.centre.get_y() - this.pty[1], this.com.centre.get_z());
        this.com.verts[2].set_Array3D(this.com.centre.get_x() - this.ptx[2], this.com.centre.get_y() - this.pty[2], this.com.centre.get_z());
        this.com.verts[3].set_Array3D(this.com.centre.get_x(), this.com.centre.get_y() + this.pty[0], this.com.centre.get_z());
        this.com.verts[4].set_Array3D(this.com.centre.get_x() + this.ptx[2], this.com.centre.get_y(), this.com.centre.get_z());
        this.com.verts[5].set_Array3D(this.com.centre.get_x() + this.ptx[1], this.com.centre.get_y(), this.com.centre.get_z());
        this.com.verts[6].set_Array3D(this.com.centre.get_x() + this.ptx[0], this.com.centre.get_y() - this.pty[0], this.com.centre.get_z());
    }
    
    public void clear_unwanted_lines() {
        int n = 0;
        do {
            this.com.set_lines(n, 0, 1, true);
            this.com.set_lines(n, 1, 2, true);
            this.com.set_lines(n, 2, 0, true);
        } while (++n < 4);
    }
    
    public void move_craft_to(final double n, final double n2, final double n3) {
        this.com.verts[0].set_Array3D(n - this.ptx[0], n2 - this.pty[0], n3);
        this.com.verts[1].set_Array3D(n - this.ptx[1], n2 - this.pty[1], n3);
        this.com.verts[2].set_Array3D(n - this.ptx[2], n2 - this.pty[2], n3);
        this.com.verts[3].set_Array3D(n, n2 + this.pty[0], n3);
        this.com.verts[4].set_Array3D(n + this.ptx[2], n2, n3);
        this.com.verts[5].set_Array3D(n + this.ptx[1], n2, n3);
        this.com.verts[6].set_Array3D(n + this.ptx[0], n2 - this.pty[0], n3);
        this.com.centre.set_Array3D(n, n2, n3);
        this.com.rotate_z_axis(-this.com.get_grid_position() * 30 - 15);
    }
    
    public void set_jumping(final int jumping) {
        this.jumping = jumping;
    }
    
    public int get_jumping() {
        return this.jumping;
    }
    
    public void move_craft_to_grid_position() {
        this.com.centre.set_x(Math.sin(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.com.centre.set_y(Math.cos(0.017453292519943295 * (this.com.get_grid_position() * 30 + 15)) * 180.0);
        this.move_craft_to(this.com.centre.get_x(), this.com.centre.get_y(), this.com.centre.get_z());
    }
}
