// 
// Decompiled by Procyon v0.5.30
// 

class MBlastG
{
    private int radius;
    Common com;
    
    public void move_mega_blast_to(final double n, final double n2, final double n3) {
        this.com.verts[0].set_Array3D(n, n2 - this.radius, n3);
        this.com.verts[1].set_Array3D(n + this.radius, n2, n3);
        this.com.verts[2].set_Array3D(n, n2 + this.radius, n3);
        this.com.verts[3].set_Array3D(n - this.radius, n2, n3);
        this.com.centre.set_Array3D(n, n2, n3);
    }
    
    public MBlastG(final int n, final int n2, final int n3, final int radius) {
        this.com = new Common(4, 2);
        this.com.centre.set_Array3D(n, n2, n3);
        this.radius = radius;
        this.com.set_blue_colour(255, 0);
        this.com.set_blue_colour(0, 1);
        this.com.set_green_colour(255, 0);
        this.com.set_green_colour(0, 1);
        this.com.set_red_colour(255, 0);
        this.com.set_red_colour(255, 1);
        this.com.set_visible(false);
        this.com.set_on_web(false);
        this.com.set_alive(true);
        this.com.set_top_of_web(false);
        this.com.reset_counter();
        this.com.verts[0].set_Array3D(n, n2 - radius, n3);
        this.com.verts[1].set_Array3D(n + radius, n2, n3);
        this.com.verts[2].set_Array3D(n, n2 + radius, n3);
        this.com.verts[3].set_Array3D(n - radius, n2, n3);
        this.com.tris[0].set_triangle(this.com.verts[0], this.com.verts[1], this.com.verts[2]);
        this.com.tris[1].set_triangle(this.com.verts[2], this.com.verts[3], this.com.verts[0]);
        this.clear_unwanted_lines();
    }
    
    public void clear_unwanted_lines() {
        int n = 0;
        do {
            this.com.set_lines(n, 0, 1, true);
            this.com.set_lines(n, 1, 2, true);
            this.com.set_lines(n, 2, 0, false);
        } while (++n < 2);
    }
}
