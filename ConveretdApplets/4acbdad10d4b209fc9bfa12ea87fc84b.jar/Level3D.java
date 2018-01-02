// 
// Decompiled by Procyon v0.5.30
// 

class Level3D
{
    Common com;
    private int length_of_level;
    private static final double TWOPI = 6.283185307179586;
    private int rad;
    
    public void move_level_to(final int n, final int n2, final int n3) {
        this.com.verts[0].set_Array3D(n, n2 - this.rad, n3 + this.length_of_level / 2);
        this.com.verts[1].set_Array3D(n, n2 - this.rad, n3 - this.length_of_level / 2);
        this.com.verts[2].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[3].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[4].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[5].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[6].set_Array3D(n + this.rad, n2, n3 + this.length_of_level / 2);
        this.com.verts[7].set_Array3D(n + this.rad, n2, n3 - this.length_of_level / 2);
        this.com.verts[8].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[9].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[10].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[11].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[12].set_Array3D(n, n2 + this.rad, n3 + this.length_of_level / 2);
        this.com.verts[13].set_Array3D(n, n2 + this.rad, n3 - this.length_of_level / 2);
        this.com.verts[14].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[15].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[16].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[17].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[18].set_Array3D(n - this.rad, n2, n3 + this.length_of_level / 2);
        this.com.verts[19].set_Array3D(n - this.rad, n2, n3 - this.length_of_level / 2);
        this.com.verts[20].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[21].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[22].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[23].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[24].set_Array3D(n, n2 - this.rad, n3 + this.length_of_level / 2);
        this.com.verts[25].set_Array3D(n, n2 - this.rad, n3 - this.length_of_level / 2);
    }
    
    public Level3D(final int n, final int n2, final int n3, final int rad, final int length_of_level, final int n4) {
        this.com = new Common(26, 24);
        this.com.centre.set_Array3D(n, n2, n3);
        this.length_of_level = length_of_level;
        this.rad = rad;
        this.com.set_red_colour(0, 0);
        this.com.set_green_colour(0, 0);
        this.com.set_blue_colour(0, 0);
        this.com.set_red_colour(0, 1);
        this.com.set_green_colour(0, 1);
        this.com.set_blue_colour(255, 1);
        this.com.set_base_blue_colour(200);
        this.com.set_base_green_colour(0);
        this.com.set_base_red_colour(0);
        int n5 = 0;
        do {
            this.com.set_red_fill(0, 0, n5);
            this.com.set_green_fill(0, 0, n5);
            this.com.set_blue_fill(0, 0, n5);
            this.com.set_red_fill(this.com.get_base_red_colour(), 1, n5);
            this.com.set_green_fill(this.com.get_base_green_colour(), 1, n5);
            this.com.set_blue_fill(this.com.get_base_blue_colour(), 1, n5);
        } while (++n5 < 12);
        this.com.verts[0].set_Array3D(n, n2 - this.rad, n3 + this.length_of_level / 2);
        this.com.verts[1].set_Array3D(n, n2 - this.rad, n3 - this.length_of_level / 2);
        this.com.verts[2].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[3].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[4].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[5].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[6].set_Array3D(n + this.rad, n2, n3 + this.length_of_level / 2);
        this.com.verts[7].set_Array3D(n + this.rad, n2, n3 - this.length_of_level / 2);
        this.com.verts[8].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[9].set_Array3D(n + Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[10].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[11].set_Array3D(n + Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[12].set_Array3D(n, n2 + this.rad, n3 + this.length_of_level / 2);
        this.com.verts[13].set_Array3D(n, n2 + this.rad, n3 - this.length_of_level / 2);
        this.com.verts[14].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[15].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 + Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[16].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[17].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 + Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[18].set_Array3D(n - this.rad, n2, n3 + this.length_of_level / 2);
        this.com.verts[19].set_Array3D(n - this.rad, n2, n3 - this.length_of_level / 2);
        this.com.verts[20].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[21].set_Array3D(n - Math.cos(0.5235987755982988) * this.rad, n2 - Math.sin(0.5235987755982988) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[22].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 + this.length_of_level / 2);
        this.com.verts[23].set_Array3D(n - Math.cos(1.0471975511965976) * this.rad, n2 - Math.sin(1.0471975511965976) * this.rad, n3 - this.length_of_level / 2);
        this.com.verts[24].set_Array3D(n, n2 - this.rad, n3 + this.length_of_level / 2);
        this.com.verts[25].set_Array3D(n, n2 - this.rad, n3 - this.length_of_level / 2);
        this.com.tris[0].set_triangle(this.com.verts[0], this.com.verts[1], this.com.verts[2]);
        this.com.tris[1].set_triangle(this.com.verts[1], this.com.verts[2], this.com.verts[3]);
        this.com.tris[2].set_triangle(this.com.verts[2], this.com.verts[3], this.com.verts[4]);
        this.com.tris[3].set_triangle(this.com.verts[3], this.com.verts[4], this.com.verts[5]);
        this.com.tris[4].set_triangle(this.com.verts[4], this.com.verts[5], this.com.verts[6]);
        this.com.tris[5].set_triangle(this.com.verts[5], this.com.verts[6], this.com.verts[7]);
        this.com.tris[6].set_triangle(this.com.verts[6], this.com.verts[7], this.com.verts[8]);
        this.com.tris[7].set_triangle(this.com.verts[7], this.com.verts[8], this.com.verts[9]);
        this.com.tris[8].set_triangle(this.com.verts[8], this.com.verts[9], this.com.verts[10]);
        this.com.tris[9].set_triangle(this.com.verts[9], this.com.verts[10], this.com.verts[11]);
        this.com.tris[10].set_triangle(this.com.verts[10], this.com.verts[11], this.com.verts[12]);
        this.com.tris[11].set_triangle(this.com.verts[11], this.com.verts[12], this.com.verts[13]);
        this.com.tris[12].set_triangle(this.com.verts[12], this.com.verts[13], this.com.verts[14]);
        this.com.tris[13].set_triangle(this.com.verts[13], this.com.verts[14], this.com.verts[15]);
        this.com.tris[14].set_triangle(this.com.verts[14], this.com.verts[15], this.com.verts[16]);
        this.com.tris[15].set_triangle(this.com.verts[15], this.com.verts[16], this.com.verts[17]);
        this.com.tris[16].set_triangle(this.com.verts[16], this.com.verts[17], this.com.verts[18]);
        this.com.tris[17].set_triangle(this.com.verts[17], this.com.verts[18], this.com.verts[19]);
        this.com.tris[18].set_triangle(this.com.verts[18], this.com.verts[19], this.com.verts[20]);
        this.com.tris[19].set_triangle(this.com.verts[19], this.com.verts[20], this.com.verts[21]);
        this.com.tris[20].set_triangle(this.com.verts[20], this.com.verts[21], this.com.verts[22]);
        this.com.tris[21].set_triangle(this.com.verts[21], this.com.verts[22], this.com.verts[23]);
        this.com.tris[22].set_triangle(this.com.verts[22], this.com.verts[23], this.com.verts[0]);
        this.com.tris[23].set_triangle(this.com.verts[23], this.com.verts[0], this.com.verts[1]);
    }
    
    public void clear_unwanted_lines() {
        int n = 0;
        do {
            this.com.set_lines(n, 0, 1, false);
            this.com.set_lines(n, 1, 2, false);
            this.com.set_lines(n, 2, 0, true);
        } while (++n < 24);
        int i = 0;
        do {
            this.com.set_lines(i, 0, 1, true);
            i += 2;
        } while (i < 24);
    }
    
    public void convert_tris_to_polys(final int n, final int n2, final Array3D array3D) {
        int n3 = 0;
        do {
            this.com.set_poly_total_x(0, n3, (int)this.com.tris[2 * n3].get_screen_x(0, n, array3D));
            this.com.set_poly_total_x(1, n3, (int)this.com.tris[2 * n3].get_screen_x(1, n, array3D));
            this.com.set_poly_total_x(2, n3, (int)this.com.tris[2 * n3 + 1].get_screen_x(2, n, array3D));
            this.com.set_poly_total_x(3, n3, (int)this.com.tris[2 * n3].get_screen_x(2, n, array3D));
            this.com.set_poly_total_y(0, n3, (int)this.com.tris[2 * n3].get_screen_y(0, n, array3D));
            this.com.set_poly_total_y(1, n3, (int)this.com.tris[2 * n3].get_screen_y(1, n, array3D));
            this.com.set_poly_total_y(2, n3, (int)this.com.tris[2 * n3 + 1].get_screen_y(2, n, array3D));
            this.com.set_poly_total_y(3, n3, (int)this.com.tris[2 * n3].get_screen_y(2, n, array3D));
        } while (++n3 < 12);
    }
    
    public int get_length_of_level() {
        return this.length_of_level;
    }
}
