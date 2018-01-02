// 
// Decompiled by Procyon v0.5.30
// 

class Shot
{
    private static final double TWOPI = 6.283185307179586;
    Common com;
    int rad;
    
    public Shot(final int n, final int n2, final int n3, final int rad) {
        this.com = new Common(4, 2);
        this.com.centre.set_Array3D(n, n2, n3);
        this.rad = rad;
        this.com.set_visible(false);
        this.com.set_on_web(true);
        this.com.set_alive(false);
        this.com.set_red_colour(204, 0);
        this.com.set_green_colour(102, 0);
        this.com.set_blue_colour(255, 0);
        this.com.verts[0].set_Array3D(n - rad, n2 - rad, n3);
        this.com.verts[1].set_Array3D(n + rad, n2 - rad, n3);
        this.com.verts[2].set_Array3D(n + rad, n2 + rad, n3);
        this.com.verts[3].set_Array3D(n - rad, n2 + rad, n3);
        this.com.tris[0].set_triangle(this.com.verts[0], this.com.verts[1], this.com.verts[2]);
        this.com.tris[1].set_triangle(this.com.verts[2], this.com.verts[3], this.com.verts[0]);
    }
    
    public void clear_unwanted_lines() {
        int n = 0;
        do {
            this.com.set_lines(n, 0, 1, true);
            this.com.set_lines(n, 1, 2, true);
            this.com.set_lines(n, 2, 0, true);
        } while (++n < 2);
    }
    
    public void move_shot_to(final double n, final double n2, final double n3) {
        this.com.verts[0].set_Array3D(n - this.rad, n2 - this.rad, n3);
        this.com.verts[1].set_Array3D(n + this.rad, n2 - this.rad, n3);
        this.com.verts[2].set_Array3D(n + this.rad, n2 + this.rad, n3);
        this.com.verts[3].set_Array3D(n - this.rad, n2 + this.rad, n3);
        this.com.centre.set_Array3D(n, n2, n3);
    }
}
