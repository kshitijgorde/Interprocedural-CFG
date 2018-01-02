import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Triangle
{
    private static final double PERSPECTIVE = 1.002;
    private static int tri_count;
    private int tri_num;
    private Array3D[] tripoints;
    
    public Triangle() {
        this.tripoints = new Array3D[3];
    }
    
    public Triangle(final Array3D array3D, final Array3D array3D2, final Array3D array3D3) {
        (this.tripoints = new Array3D[3])[0] = array3D;
        this.tripoints[1] = array3D2;
        this.tripoints[2] = array3D3;
        ++Triangle.tri_count;
        this.tri_num = Triangle.tri_count;
    }
    
    public void set_triangle(final Array3D array3D, final Array3D array3D2, final Array3D array3D3) {
        this.tripoints[0] = array3D;
        this.tripoints[1] = array3D2;
        this.tripoints[2] = array3D3;
    }
    
    public void draw_triangle(final Graphics graphics, final int n, final int n2, final Array3D array3D, final boolean b, final boolean b2, final boolean b3) {
        if (b) {
            graphics.drawLine((int)this.get_screen_x(0, n, array3D), (int)this.get_screen_y(0, n2, array3D), (int)this.get_screen_x(1, n, array3D), (int)this.get_screen_y(1, n2, array3D));
        }
        if (b2) {
            graphics.drawLine((int)this.get_screen_x(1, n, array3D), (int)this.get_screen_y(1, n2, array3D), (int)this.get_screen_x(2, n, array3D), (int)this.get_screen_y(2, n2, array3D));
        }
        if (b3) {
            graphics.drawLine((int)this.get_screen_x(2, n, array3D), (int)this.get_screen_y(2, n2, array3D), (int)this.get_screen_x(0, n, array3D), (int)this.get_screen_y(0, n2, array3D));
        }
    }
    
    public Array3D get_point_c() {
        return this.tripoints[2];
    }
    
    static {
        Triangle.tri_count = 0;
    }
    
    public Array3D get_point_b() {
        return this.tripoints[1];
    }
    
    public double get_screen_x(final int n, final int n2, final Array3D array3D) {
        return (-array3D.get_x() + this.tripoints[n].get_x()) * Math.pow(1.002, -array3D.get_z() + this.tripoints[n].get_z()) + n2 / 2.0;
    }
    
    public Array3D get_point_a() {
        return this.tripoints[0];
    }
    
    public double get_screen_y(final int n, final int n2, final Array3D array3D) {
        return (-array3D.get_y() + this.tripoints[n].get_y()) * Math.pow(1.002, -array3D.get_z() + this.tripoints[n].get_z()) + n2 / 2.0;
    }
}
