import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Primitive3D
{
    public int count_points;
    public int[][] original_expressions;
    public double[][] original_points;
    public double[][] original_scaled_offsets;
    public String text;
    public String font_url;
    public Object font;
    public int font_weight;
    public int font_slant;
    public int font_size;
    public int font_y_offset;
    public Color standard_color;
    public Color edge_color;
    public Color front_face_color;
    public Color back_face_color;
    public Color front_diffuse_color;
    public Color back_diffuse_color;
    public Color front_specular_color;
    public Color back_specular_color;
    public double front_specular_exponent;
    public double back_specular_exponent;
    public int first_point;
    public int second_point;
    public int third_point;
    public int fourth_point;
    public double original_point_size;
    public boolean is_absolute_point_size;
    public double original_thickness;
    public boolean is_absolute_thickness;
    public double original_edge_thickness;
    public boolean is_absolute_edge_thickness;
    public boolean is_filled;
    public boolean is_outlined;
    public Color front_color;
    public Color back_color;
    public int[] points;
    public boolean[] edge_flags;
    public double point_diameter;
    public int last_z_coordinate;
    public Vector edge_primitives;
    public static String text_dragable_point;
    
    public void addEdge(final Primitive3D edge) {
        if (this.edge_primitives == null) {
            this.edge_primitives = new Vector(4);
        }
        this.edge_primitives.addElement(edge);
    }
    
    public Primitive3D() {
        this.count_points = this.count_points;
        this.original_expressions = null;
        this.original_points = null;
        this.original_scaled_offsets = null;
        this.edge_flags = null;
        this.text = null;
        this.font_url = null;
        this.font = null;
        this.font_weight = -1;
        this.font_slant = -1;
        this.font_size = -1;
        this.standard_color = null;
        this.edge_color = null;
        this.front_face_color = null;
        this.back_face_color = null;
        this.front_diffuse_color = new Color(1.0f, 1.0f, 1.0f);
        this.back_diffuse_color = new Color(1.0f, 1.0f, 1.0f);
        this.front_specular_color = new Color(0.0f, 0.0f, 0.0f);
        this.back_specular_color = new Color(0.0f, 0.0f, 0.0f);
        this.front_specular_exponent = 1.0;
        this.back_specular_exponent = 1.0;
        this.original_point_size = 0.01;
        this.is_absolute_point_size = false;
        this.original_thickness = 0.5;
        this.is_absolute_thickness = true;
        this.original_edge_thickness = 0.5;
        this.is_absolute_edge_thickness = true;
        this.is_filled = false;
        this.is_outlined = true;
        this.first_point = 0;
        this.second_point = 0;
        this.third_point = 0;
        this.fourth_point = 0;
        this.front_color = Color.black;
        this.back_color = Color.black;
        this.points = null;
        this.point_diameter = 0.0;
        this.last_z_coordinate = 0;
        this.edge_primitives = null;
    }
    
    public Primitive3D(final Primitive3D other) {
        this.count_points = other.count_points;
        this.original_expressions = other.original_expressions;
        this.original_points = other.original_points;
        this.original_scaled_offsets = other.original_scaled_offsets;
        this.edge_flags = other.edge_flags;
        this.text = other.text;
        this.font_url = other.font_url;
        this.font = other.font;
        this.font_weight = other.font_weight;
        this.font_slant = other.font_slant;
        this.font_size = other.font_size;
        this.standard_color = other.standard_color;
        this.edge_color = other.edge_color;
        this.front_face_color = other.front_face_color;
        this.back_face_color = other.back_face_color;
        this.front_diffuse_color = other.front_diffuse_color;
        this.back_diffuse_color = other.back_diffuse_color;
        this.front_specular_color = other.front_specular_color;
        this.back_specular_color = other.back_specular_color;
        this.front_specular_exponent = other.front_specular_exponent;
        this.back_specular_exponent = other.back_specular_exponent;
        this.original_point_size = other.original_point_size;
        this.is_absolute_point_size = other.is_absolute_point_size;
        this.original_thickness = other.original_thickness;
        this.is_absolute_thickness = other.is_absolute_thickness;
        this.original_edge_thickness = other.original_edge_thickness;
        this.is_absolute_edge_thickness = other.is_absolute_edge_thickness;
        this.is_filled = other.is_filled;
        this.is_outlined = other.is_outlined;
        this.first_point = other.first_point;
        this.second_point = other.second_point;
        this.third_point = other.third_point;
        this.fourth_point = other.fourth_point;
        this.front_color = other.front_color;
        this.back_color = other.back_color;
        this.points = other.points;
        this.point_diameter = other.point_diameter;
        this.edge_primitives = other.edge_primitives;
    }
    
    static {
        Primitive3D.text_dragable_point = "Press left mouse button to drag this point.";
    }
}
