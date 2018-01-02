import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Common
{
    private static final double TWOPI = 6.283185307179586;
    private boolean visible;
    private int grid_position;
    private int number_of_verts;
    private int number_of_tris;
    private int[] red_colour;
    private int[] green_colour;
    private int[] blue_colour;
    private int[][] red_fill;
    private int[][] green_fill;
    private int[][] blue_fill;
    private int base_red_colour;
    private int base_green_colour;
    private int base_blue_colour;
    Array3D[] verts;
    Triangle[] tris;
    private boolean[][][] lines;
    private int[][] poly_total_x;
    private int[][] poly_total_y;
    private int[] poly_x;
    private int[] poly_y;
    private boolean itsalive;
    private boolean itson_web;
    private boolean itstop_of_web;
    private boolean clockwise_direction;
    private boolean going_left;
    private boolean going_right;
    private int baddy_value;
    private int counter;
    private boolean itsfirst;
    private int itsshot_start;
    private boolean dead_on_this_go;
    private boolean shot;
    Array3D centre;
    Array3D velocity;
    
    public void set_red_colour(final int n, final int n2) {
        this.red_colour[n2] = n;
    }
    
    public int get_red_colour(final int n) {
        return this.red_colour[n];
    }
    
    public void set_blue_colour(final int n, final int n2) {
        this.blue_colour[n2] = n;
    }
    
    public int get_blue_colour(final int n) {
        return this.blue_colour[n];
    }
    
    public void set_poly_x(final int n, final int n2) {
        this.poly_x[n] = n2;
    }
    
    public void reset_counter() {
        this.counter = 0;
    }
    
    public int get_counter() {
        return this.counter;
    }
    
    public void set_shot(final boolean shot) {
        this.shot = shot;
    }
    
    public void draw_object_poly_line(final Graphics graphics) {
        graphics.drawPolygon(this.poly_x, this.poly_y, 4);
    }
    
    public void set_poly_y(final int n, final int n2) {
        this.poly_y[n] = n2;
    }
    
    public boolean get_shot() {
        return this.shot;
    }
    
    public void set_top_of_web(final boolean itstop_of_web) {
        this.itstop_of_web = itstop_of_web;
    }
    
    public void set_green_colour(final int n, final int n2) {
        this.green_colour[n2] = n;
    }
    
    public int get_green_colour(final int n) {
        return this.green_colour[n];
    }
    
    public void move_object(final double n, final double n2, final double n3) {
        for (int i = 0; i < this.number_of_verts; ++i) {
            this.verts[i].set_Array3D(this.verts[i].get_x() + n, this.verts[i].get_y() + n2, this.verts[i].get_z() + n3);
        }
        this.centre.set_Array3D(this.centre.get_x() + n, this.centre.get_y() + n2, this.centre.get_z() + n3);
    }
    
    public boolean clockwise_direction() {
        return this.clockwise_direction;
    }
    
    public void set_first(final boolean itsfirst) {
        this.itsfirst = itsfirst;
    }
    
    public boolean get_first() {
        return this.itsfirst;
    }
    
    public boolean on_web() {
        return this.itson_web;
    }
    
    public void draw_object_poly(final Graphics graphics) {
        graphics.fillPolygon(this.poly_x, this.poly_y, 4);
    }
    
    public void set_red_fill(final int n, final int n2, final int n3) {
        this.red_fill[n2][n3] = n;
    }
    
    public int get_red_fill(final int n, final int n2) {
        return this.red_fill[n][n2];
    }
    
    public void set_number_of_verts(final int number_of_verts) {
        this.number_of_verts = number_of_verts;
    }
    
    public int get_number_of_verts() {
        return this.number_of_verts;
    }
    
    public void set_number_of_tris(final int number_of_tris) {
        this.number_of_tris = number_of_tris;
    }
    
    public int get_number_of_tris() {
        return this.number_of_tris;
    }
    
    public void set_blue_fill(final int n, final int n2, final int n3) {
        this.blue_fill[n2][n3] = n;
    }
    
    public int get_blue_fill(final int n, final int n2) {
        return this.blue_fill[n][n2];
    }
    
    public void draw_level_by_poly(final Graphics graphics, final int n, final int n2, final Array3D array3D) {
        int n3 = 0;
        do {
            this.convert_single_tri_to_poly(n, n2, array3D, n3);
            graphics.fillPolygon(this.poly_x, this.poly_y, 4);
        } while (++n3 < 12);
    }
    
    public void set_base_red_colour(final int base_red_colour) {
        this.base_red_colour = base_red_colour;
    }
    
    public void set_green_fill(final int n, final int n2, final int n3) {
        this.green_fill[n2][n3] = n;
    }
    
    public int get_green_fill(final int n, final int n2) {
        return this.green_fill[n][n2];
    }
    
    public int get_base_red_colour() {
        return this.base_red_colour;
    }
    
    public void set_base_blue_colour(final int base_blue_colour) {
        this.base_blue_colour = base_blue_colour;
    }
    
    public int get_base_blue_colour() {
        return this.base_blue_colour;
    }
    
    public void set_lines(final int n, final int n2, final int n3, final boolean b) {
        this.lines[n][n2][n3] = b;
    }
    
    public void set_poly_total_x(final int n, final int n2, final int n3) {
        this.poly_total_x[n][n2] = n3;
    }
    
    public void set_clockwise_direction(final boolean clockwise_direction) {
        this.clockwise_direction = clockwise_direction;
    }
    
    public void set_poly_total_y(final int n, final int n2, final int n3) {
        this.poly_total_y[n][n2] = n3;
    }
    
    public void set_on_web(final boolean itson_web) {
        this.itson_web = itson_web;
    }
    
    public void set_total_to_single_poly(final int n) {
        int n2 = 0;
        do {
            this.poly_x[n2] = this.poly_total_x[n2][n];
            this.poly_y[n2] = this.poly_total_y[n2][n];
        } while (++n2 < 4);
    }
    
    public void convert_single_tri_to_poly(final int n, final int n2, final Array3D array3D, final int n3) {
        this.set_poly_x(0, (int)this.tris[2 * n3].get_screen_x(0, n, array3D));
        this.set_poly_x(1, (int)this.tris[2 * n3].get_screen_x(1, n, array3D));
        this.set_poly_x(2, (int)this.tris[2 * n3 + 1].get_screen_x(2, n, array3D));
        this.set_poly_x(3, (int)this.tris[2 * n3].get_screen_x(2, n, array3D));
        this.set_poly_y(0, (int)this.tris[2 * n3].get_screen_x(0, n, array3D));
        this.set_poly_y(1, (int)this.tris[2 * n3].get_screen_x(1, n, array3D));
        this.set_poly_y(2, (int)this.tris[2 * n3 + 1].get_screen_x(2, n, array3D));
        this.set_poly_y(3, (int)this.tris[2 * n3].get_screen_x(2, n, array3D));
    }
    
    public void increase_grid_pos() {
        this.set_grid_position(this.get_grid_position() + 1);
        if (this.get_grid_position() > 11) {
            this.set_grid_position(0);
        }
    }
    
    public boolean get_visible() {
        return this.visible;
    }
    
    public int get_grid_position() {
        return this.grid_position;
    }
    
    public boolean get_going_left() {
        return this.going_left;
    }
    
    public void set_going_left(final boolean going_left) {
        this.going_left = going_left;
    }
    
    public void set_visible(final boolean visible) {
        this.visible = visible;
    }
    
    public void rotate_z_axis(final double n) {
        for (int i = 0; i < this.number_of_verts; ++i) {
            final double n2 = (this.verts[i].get_x() - this.centre.get_x()) * Math.cos(0.017453292519943295 * n) - (this.verts[i].get_y() - this.centre.get_y()) * Math.sin(0.017453292519943295 * n) + this.centre.get_x();
            this.verts[i].set_y((this.verts[i].get_x() - this.centre.get_x()) * Math.sin(0.017453292519943295 * n) + (this.verts[i].get_y() - this.centre.get_y()) * Math.cos(0.017453292519943295 * n) + this.centre.get_y());
            this.verts[i].set_x(n2);
        }
    }
    
    public void rotate_x_axis(final double n) {
        for (int i = 0; i < this.number_of_verts; ++i) {
            final double n2 = (this.verts[i].get_z() - this.centre.get_z()) * Math.cos(0.017453292519943295 * n) - (this.verts[i].get_y() - this.centre.get_y()) * Math.sin(0.017453292519943295 * n) + this.centre.get_z();
            this.verts[i].set_y((this.verts[i].get_z() - this.centre.get_z()) * Math.sin(0.017453292519943295 * n) + (this.verts[i].get_y() - this.centre.get_y()) * Math.cos(0.017453292519943295 * n) + this.centre.get_y());
            this.verts[i].set_z(n2);
        }
    }
    
    public void set_grid_position(final int grid_position) {
        this.grid_position = grid_position;
    }
    
    public void decrease_grid_pos() {
        this.set_grid_position(this.get_grid_position() - 1);
        if (this.get_grid_position() < 0) {
            this.set_grid_position(11);
        }
    }
    
    public void set_dead_on_this_go(final boolean dead_on_this_go) {
        this.dead_on_this_go = dead_on_this_go;
    }
    
    public boolean get_dead_on_this_go() {
        return this.dead_on_this_go;
    }
    
    public boolean alive() {
        return this.itsalive;
    }
    
    public void set_shot_start(final int itsshot_start) {
        this.itsshot_start = itsshot_start;
    }
    
    public int get_shot_start() {
        return this.itsshot_start;
    }
    
    public void increase_counter(final int n) {
        this.counter += n;
    }
    
    public void set_alive(final boolean itsalive) {
        this.itsalive = itsalive;
    }
    
    public void set_base_green_colour(final int base_green_colour) {
        this.base_green_colour = base_green_colour;
    }
    
    public int get_base_green_colour() {
        return this.base_green_colour;
    }
    
    public void draw_object(final Graphics graphics, final int n, final int n2, final Array3D array3D) {
        graphics.setColor(new Color(this.red_colour[0], this.green_colour[0], this.blue_colour[0]));
        for (int i = 0; i < this.number_of_tris; ++i) {
            this.tris[i].draw_triangle(graphics, n, n2, array3D, this.lines[i][0][1], this.lines[i][1][2], this.lines[i][2][0]);
        }
    }
    
    public boolean get_going_right() {
        return this.going_right;
    }
    
    public void set_going_right(final boolean going_right) {
        this.going_right = going_right;
    }
    
    public boolean top_of_web() {
        return this.itstop_of_web;
    }
    
    public Common(final int number_of_verts, final int number_of_tris) {
        this.centre = new Array3D();
        this.velocity = new Array3D();
        this.verts = new Array3D[number_of_verts];
        this.tris = new Triangle[number_of_tris];
        this.lines = new boolean[number_of_tris][3][3];
        this.poly_total_x = new int[4][12];
        this.poly_total_y = new int[4][12];
        this.poly_x = new int[4];
        this.poly_y = new int[4];
        this.red_colour = new int[2];
        this.green_colour = new int[2];
        this.blue_colour = new int[2];
        this.red_fill = new int[2][12];
        this.green_fill = new int[2][12];
        this.blue_fill = new int[2][12];
        this.number_of_verts = number_of_verts;
        this.number_of_tris = number_of_tris;
        for (int i = 0; i < number_of_verts; ++i) {
            this.verts[i] = new Array3D();
        }
        for (int j = 0; j < number_of_tris; ++j) {
            this.tris[j] = new Triangle();
        }
    }
    
    public Common(final boolean visible) {
        this.centre = new Array3D();
        this.velocity = new Array3D();
        this.visible = visible;
    }
    
    public void rotate_y_axis(final double n) {
        for (int i = 0; i < this.number_of_verts; ++i) {
            final double n2 = (this.verts[i].get_x() - this.centre.get_x()) * Math.cos(0.017453292519943295 * n) - (this.verts[i].get_z() - this.centre.get_z()) * Math.sin(0.017453292519943295 * n) + this.centre.get_x();
            this.verts[i].set_z((this.verts[i].get_x() - this.centre.get_x()) * Math.sin(0.017453292519943295 * n) + (this.verts[i].get_z() - this.centre.get_z()) * Math.cos(0.017453292519943295 * n) + this.centre.get_z());
            this.verts[i].set_x(n2);
        }
    }
    
    public void set_baddy_value(final int baddy_value) {
        this.baddy_value = baddy_value;
    }
    
    public int get_baddy_value() {
        return this.baddy_value;
    }
}
