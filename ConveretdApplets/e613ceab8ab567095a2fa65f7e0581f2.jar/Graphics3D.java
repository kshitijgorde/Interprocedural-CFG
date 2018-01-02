import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Graphics3D
{
    public Color point_edge_color;
    public int full_pixel_width;
    public int full_pixel_height;
    public int pixel_width;
    public int pixel_height;
    public boolean is_stereo;
    public double stereo_distance;
    public double stereo_offset;
    public double pixel_stereo_offset;
    public double length_view_point;
    public double initial_length_view_point;
    public double magnification;
    public double initial_magnification;
    public double width;
    public double height;
    public double eye_distance;
    public Quaternion rotation;
    public Quaternion initial_rotation;
    public double[] original_sizes;
    public double[] original_center;
    public double[] scalings;
    public double[] scaled_scalings;
    public int old_pixel_width;
    public int old_pixel_height;
    public double old_width;
    public double old_height;
    public boolean old_is_stereo;
    public double old_stereo_distance;
    public double old_eye_distance;
    public Quaternion old_rotation;
    public Color option_AmbientLight;
    public boolean[] option_Axes;
    public Primitive3D[] option_AxesStyle;
    public Primitive3D[] option_AxesLabel;
    public Primitive3D option_PlotLabel;
    public int[] option_AxesEdge;
    public Vector[] option_Ticks;
    public double[] ticks_max_in_length;
    public double[] ticks_max_out_length;
    public Color option_Background;
    public boolean option_Boxed;
    public double[] option_BoxRatios;
    public Primitive3D option_BoxStyle;
    public Color option_DefaultColor;
    public boolean option_Lighting;
    public Vector option_LightSources_vectors;
    public Vector option_LightSources_colors;
    public double[][] option_PlotRange;
    public double[] option_ViewPoint;
    public double[] option_ViewVertical;
    public String option_TextStyle_font_url;
    public String option_TextStyle_font_family;
    public int option_TextStyle_font_weight;
    public int option_TextStyle_font_slant;
    public int option_TextStyle_font_size;
    public Color option_TextStyle_font_color;
    public Color option_TextStyle_font_background;
    public double option_AnimationDisplayTime;
    public String option_AnimationDisplayTimeString;
    int max_count_points;
    int count_points;
    float[] xs;
    float[] ys;
    float[] zs;
    int[] point_flags;
    public int[] left_pixel_xs;
    public int[] right_pixel_xs;
    public int[] pixel_ys;
    float[] point_scale;
    int[] temp_xs;
    int[] temp_ys;
    int[] temp_line_xs;
    int[] temp_line_ys;
    int visible_faces;
    boolean show_faces;
    int count_primitives;
    int original_count_primitives;
    int min_primitive_index;
    int max_primitive_index;
    Vector primitives;
    int max_primitive_count_points;
    double[] max_original_coordinates;
    double[] min_original_coordinates;
    int count_ordered_primitives;
    int[] order;
    float[] rotated_center_zs;
    
    public void setGlobalParameters(final int full_pixel_width, final int full_pixel_height, final Color background, final Color new_point_edge_color) {
        this.point_edge_color = new_point_edge_color;
        if (null == this.option_Background) {
            this.option_Background = background;
        }
        this.full_pixel_width = full_pixel_width;
        this.full_pixel_height = full_pixel_height;
    }
    
    public Graphics3D() {
        this.option_Axes = new boolean[] { false, false, false };
        this.option_AxesStyle = new Primitive3D[3];
        this.option_AxesLabel = new Primitive3D[3];
        this.option_PlotLabel = null;
        this.option_AxesEdge = new int[] { -1, -1, -1 };
        this.ticks_max_in_length = new double[] { 0.0, 0.0, 0.0 };
        this.ticks_max_out_length = new double[] { 0.0, 0.0, 0.0 };
        this.option_Boxed = true;
        this.option_Lighting = true;
        this.option_ViewPoint = new double[] { 1.3, -2.4, 2.0 };
        this.option_ViewVertical = new double[] { 0.0, 0.0, 1.0 };
        this.option_TextStyle_font_weight = -1;
        this.option_TextStyle_font_slant = -1;
        this.option_TextStyle_font_size = -1;
        this.option_AnimationDisplayTime = 0.05;
        this.option_AnimationDisplayTimeString = null;
        this.point_edge_color = null;
        this.option_Background = null;
        this.full_pixel_width = 0;
        this.full_pixel_height = 0;
        this.pixel_width = 0;
        this.pixel_height = 0;
        this.is_stereo = false;
        this.stereo_distance = 0.0;
        this.primitives = new Vector();
        this.count_primitives = 0;
        this.original_count_primitives = 0;
        this.min_primitive_index = 0;
        this.max_primitive_index = 0;
        this.visible_faces = 3;
        this.show_faces = true;
        this.rotation = new Quaternion(1.0, 0.0, 0.0, 0.0);
        this.width = 1000.0;
        this.height = 1000.0;
        this.eye_distance = 8000.0;
        this.length_view_point = 3.38378;
        this.initial_length_view_point = 3.38378;
        this.magnification = 1.0;
        this.initial_magnification = 1.0;
        (this.original_sizes = new double[3])[0] = -1.0;
        this.original_sizes[1] = -1.0;
        this.original_sizes[2] = -1.0;
        this.original_center = new double[3];
        this.scalings = new double[3];
        this.scaled_scalings = new double[3];
        this.old_rotation = new Quaternion(0.0, 0.0, 0.0, 0.0);
        final double[][] vectors = { { 0.707107, 0.0, 0.707107 }, { 0.57735, 0.57735, 0.57735 }, { 0.0, 0.707107, 0.707107 }, { -0.408248, -0.408248, -0.816497 } };
        (this.option_LightSources_vectors = new Vector()).addElement(vectors[0]);
        this.option_LightSources_vectors.addElement(vectors[1]);
        this.option_LightSources_vectors.addElement(vectors[2]);
        this.option_LightSources_vectors.addElement(vectors[3]);
        (this.option_LightSources_colors = new Vector()).addElement(new Color(1.0f, 0.0f, 0.0f));
        this.option_LightSources_colors.addElement(new Color(0.0f, 1.0f, 0.0f));
        this.option_LightSources_colors.addElement(new Color(0.0f, 0.0f, 1.0f));
        this.option_LightSources_colors.addElement(new Color(1.0f, 1.0f, 1.0f));
    }
    
    public Quaternion getQuaternion() {
        return this.rotation;
    }
    
    public void setQuaternion(final Quaternion q) {
        this.rotation = q;
    }
    
    public void multiplyQuaternion(final Quaternion q) {
        (this.rotation = q.product(this.rotation)).normalize();
    }
    
    public void projectPoints(final boolean force_new_calculation) {
        if (!force_new_calculation && this.old_rotation.equals(this.rotation) && this.old_eye_distance == this.eye_distance && this.old_pixel_width == this.pixel_width && this.old_pixel_height == this.pixel_height && this.old_width == this.width && this.old_height == this.height && this.old_is_stereo == this.is_stereo && this.old_stereo_distance == this.stereo_distance) {
            return;
        }
        this.old_rotation.s = this.rotation.s;
        this.old_rotation.x = this.rotation.x;
        this.old_rotation.y = this.rotation.y;
        this.old_rotation.z = this.rotation.z;
        this.old_eye_distance = this.eye_distance;
        this.old_pixel_width = this.pixel_width;
        this.old_pixel_height = this.pixel_height;
        this.old_width = this.width;
        this.old_height = this.height;
        this.old_is_stereo = this.is_stereo;
        this.old_stereo_distance = this.stereo_distance;
        final double sx = this.rotation.s * this.rotation.x;
        final double sy = this.rotation.s * this.rotation.y;
        final double sz = this.rotation.s * this.rotation.z;
        final double xx = this.rotation.x * this.rotation.x;
        final double xy = this.rotation.x * this.rotation.y;
        final double xz = this.rotation.x * this.rotation.z;
        final double yy = this.rotation.y * this.rotation.y;
        final double yz = this.rotation.y * this.rotation.z;
        final double zz = this.rotation.z * this.rotation.z;
        final double xy_factor = 2 * this.pixel_height;
        final double z_factor = 2.0 * this.height / this.eye_distance;
        final float matrix11 = (float)(xy_factor * (0.5 - yy - zz));
        final float matrix12 = (float)(xy_factor * (xy - sz));
        final float matrix13 = (float)(xy_factor * (xz + sy));
        final float matrix14 = (float)(xy_factor * (xy + sz));
        final float matrix15 = (float)(xy_factor * (0.5 - xx - zz));
        final float matrix16 = (float)(xy_factor * (yz - sx));
        final float matrix17 = (float)(z_factor * (xz - sy));
        final float matrix18 = (float)(z_factor * (yz + sx));
        final float matrix19 = (float)(z_factor * (0.5 - xx - yy));
        final float scaled_eye_distance = (float)(this.eye_distance * z_factor / 2.0);
        final float half_pixel_width = this.pixel_width / 2.0f;
        final float half_pixel_height = this.pixel_height / 2.0f;
        if (!this.is_stereo) {
            this.pixel_stereo_offset = 0.0;
            for (int index = 0; index < this.count_points; ++index) {
                final float rotated_x = matrix11 * this.xs[index] + matrix12 * this.ys[index] + matrix13 * this.zs[index];
                final float rotated_y = matrix14 * this.xs[index] + matrix15 * this.ys[index] + matrix16 * this.zs[index];
                final float rotated_z = matrix17 * this.xs[index] + matrix18 * this.ys[index] + matrix19 * this.zs[index];
                final float denominator = scaled_eye_distance - rotated_z;
                if (denominator <= 0.0) {
                    this.point_scale[index] = 0.0f;
                }
                else {
                    this.left_pixel_xs[index] = (int)(half_pixel_width + rotated_x / denominator);
                    this.pixel_ys[index] = (int)(half_pixel_height - rotated_y / denominator);
                    this.point_scale[index] = denominator;
                }
            }
        }
        else {
            final float scaled_stereo_offset = (float)(this.stereo_offset * xy_factor);
            this.pixel_stereo_offset = scaled_stereo_offset / scaled_eye_distance;
            final float left_pixel_offset = half_pixel_width - (float)this.pixel_stereo_offset;
            final float right_pixel_offset = half_pixel_width + (float)this.pixel_stereo_offset;
            for (int index = 0; index < this.count_points; ++index) {
                final float rotated_x = matrix11 * this.xs[index] + matrix12 * this.ys[index] + matrix13 * this.zs[index];
                final float rotated_y = matrix14 * this.xs[index] + matrix15 * this.ys[index] + matrix16 * this.zs[index];
                final float rotated_z = matrix17 * this.xs[index] + matrix18 * this.ys[index] + matrix19 * this.zs[index];
                final float denominator = scaled_eye_distance - rotated_z;
                if (denominator <= 0.0) {
                    this.point_scale[index] = 0.0f;
                }
                else {
                    this.left_pixel_xs[index] = (int)(left_pixel_offset + (rotated_x + scaled_stereo_offset) / denominator);
                    this.right_pixel_xs[index] = (int)(right_pixel_offset + (rotated_x - scaled_stereo_offset) / denominator);
                    this.pixel_ys[index] = (int)(half_pixel_height - rotated_y / denominator);
                    this.point_scale[index] = denominator;
                }
            }
        }
        if (0 == this.count_points) {
            return;
        }
        for (int primitive_index = 0; primitive_index < this.count_primitives; ++primitive_index) {
            final Primitive3D primitive = this.primitives.elementAt(primitive_index);
            final int count_primitive_points = primitive.count_points;
            if (3 <= count_primitive_points) {
                if (3 == count_primitive_points) {
                    final float temp = this.point_scale[primitive.first_point] + this.point_scale[primitive.second_point] + this.point_scale[primitive.fourth_point];
                    this.rotated_center_zs[primitive_index] = temp + temp;
                }
                else {
                    final float temp = this.point_scale[primitive.first_point] + this.point_scale[primitive.third_point];
                    this.rotated_center_zs[primitive_index] = temp + temp + this.point_scale[primitive.second_point] + this.point_scale[primitive.fourth_point];
                }
            }
            else if (2 == count_primitive_points) {
                final float temp = this.point_scale[primitive.first_point] + this.point_scale[primitive.third_point];
                this.rotated_center_zs[primitive_index] = temp + temp + temp;
            }
            else if (1 == count_primitive_points) {
                this.rotated_center_zs[primitive_index] = 6.0f * this.point_scale[primitive.points[0]];
            }
            else {
                this.rotated_center_zs[primitive_index] = 1.0f;
            }
        }
        for (int order_index = 0; order_index < this.count_ordered_primitives; ++order_index) {
            final int primitive_index = this.order[order_index];
            float rotated_center_z;
            int sorted_index;
            for (rotated_center_z = this.rotated_center_zs[primitive_index], sorted_index = order_index - 1; sorted_index >= 0 && this.rotated_center_zs[this.order[sorted_index]] < rotated_center_z; --sorted_index) {
                this.order[sorted_index + 1] = this.order[sorted_index];
            }
            this.order[sorted_index + 1] = primitive_index;
        }
    }
    
    public void addPrimitive(final Primitive3D primitive) {
        if (null == primitive || (primitive.count_points <= 0 && null == primitive.text) || this.count_primitives != this.primitives.size()) {
            return;
        }
        this.primitives.addElement(primitive);
        if (primitive.count_points > this.max_primitive_count_points) {
            this.max_primitive_count_points = primitive.count_points;
        }
        this.max_count_points += primitive.count_points;
        ++this.count_primitives;
        this.max_primitive_index = this.count_primitives - 1;
    }
    
    public boolean xor(final boolean a, final boolean b) {
        return (a && !b) || (!a && b);
    }
    
    public void preparePrimitives(final Graphics g, final Evaluator evaluator, final double[] center_offset, final double[] sizes_scales) {
        int point_index = 0;
        if (this.original_count_primitives < this.count_primitives) {
            for (int primitive_index = this.count_primitives - 1; primitive_index >= this.original_count_primitives; --primitive_index) {
                this.primitives.removeElementAt(primitive_index);
            }
            this.count_primitives = this.original_count_primitives;
            System.gc();
        }
        for (int primitive_index = 0; primitive_index < this.count_primitives; ++primitive_index) {
            Primitive3D primitive;
            double[] coors;
            int axis_index;
            double coor;
            for (primitive = this.primitives.elementAt(primitive_index), point_index = 0; point_index < primitive.count_points; ++point_index) {
                coors = primitive.original_points[point_index];
                if (null != coors) {
                    if (null != this.max_original_coordinates) {
                        for (axis_index = 0; axis_index < 3; ++axis_index) {
                            coor = coors[axis_index];
                            if (coor > this.max_original_coordinates[axis_index]) {
                                this.max_original_coordinates[axis_index] = coor;
                            }
                            if (coor < this.min_original_coordinates[axis_index]) {
                                this.min_original_coordinates[axis_index] = coor;
                            }
                        }
                    }
                    else {
                        this.max_original_coordinates = new double[3];
                        this.min_original_coordinates = new double[3];
                        for (axis_index = 0; axis_index < 3; ++axis_index) {
                            coor = coors[axis_index];
                            this.max_original_coordinates[axis_index] = coor;
                            this.min_original_coordinates[axis_index] = coor;
                        }
                    }
                }
            }
        }
        if (null == this.max_original_coordinates) {
            this.max_original_coordinates = new double[3];
            this.min_original_coordinates = new double[3];
            for (int axis_index = 0; axis_index < 3; ++axis_index) {
                this.max_original_coordinates[axis_index] = 0.0;
                this.min_original_coordinates[axis_index] = 0.0;
            }
        }
        for (int axis_index = 0; axis_index < 3; ++axis_index) {
            if (null != this.option_PlotRange && Double.NEGATIVE_INFINITY != this.option_PlotRange[axis_index][0] && Double.POSITIVE_INFINITY != this.option_PlotRange[axis_index][1]) {
                if (this.option_PlotRange[axis_index][1] < this.option_PlotRange[axis_index][0]) {
                    final double swap_double = this.option_PlotRange[axis_index][0];
                    this.option_PlotRange[axis_index][0] = this.option_PlotRange[axis_index][1];
                    this.option_PlotRange[axis_index][1] = swap_double;
                }
                this.original_sizes[axis_index] = this.option_PlotRange[axis_index][1] - this.option_PlotRange[axis_index][0];
                this.original_center[axis_index] = (this.option_PlotRange[axis_index][1] + this.option_PlotRange[axis_index][0]) * 0.5;
            }
            else {
                this.original_sizes[axis_index] = this.max_original_coordinates[axis_index] - this.min_original_coordinates[axis_index];
                if (this.max_original_coordinates[axis_index] == this.min_original_coordinates[axis_index]) {
                    this.original_sizes[axis_index] = 2.1;
                }
                else {
                    this.original_sizes[axis_index] *= 1.05;
                }
                this.original_center[axis_index] = (this.max_original_coordinates[axis_index] + this.min_original_coordinates[axis_index]) * 0.5;
            }
        }
        for (int axis_index = 0; axis_index < 3; ++axis_index) {
            this.original_center[axis_index] += center_offset[axis_index] * this.original_sizes[axis_index];
            this.original_sizes[axis_index] *= sizes_scales[axis_index];
        }
        if (null == this.option_Background) {
            this.option_Background = Color.white;
        }
        if (null == this.option_DefaultColor) {
            this.option_DefaultColor = new Color((this.option_Background.getRed() >= 128) ? 0 : 255, (this.option_Background.getGreen() >= 128) ? 0 : 255, (this.option_Background.getBlue() >= 128) ? 0 : 255);
        }
        if (null == this.option_AmbientLight) {
            this.option_AmbientLight = Color.black;
        }
        if (null == this.option_BoxRatios) {
            this.option_BoxRatios = new double[3];
            for (int axis_index = 0; axis_index < 3; ++axis_index) {
                this.option_BoxRatios[axis_index] = this.original_sizes[axis_index];
            }
        }
        double max_box_ratio = this.option_BoxRatios[0];
        if (this.option_BoxRatios[1] > max_box_ratio) {
            max_box_ratio = this.option_BoxRatios[1];
        }
        if (this.option_BoxRatios[2] > max_box_ratio) {
            max_box_ratio = this.option_BoxRatios[2];
        }
        for (int axis_index = 0; axis_index < 3; ++axis_index) {
            this.option_BoxRatios[axis_index] /= max_box_ratio;
            this.scalings[axis_index] = this.option_BoxRatios[axis_index] / this.original_sizes[axis_index];
            this.scaled_scalings[axis_index] = this.option_BoxRatios[axis_index];
        }
        final double diagonal_length = Math.sqrt(this.option_BoxRatios[0] * this.option_BoxRatios[0] + this.option_BoxRatios[1] * this.option_BoxRatios[1] + this.option_BoxRatios[2] * this.option_BoxRatios[2]);
        if (null == this.option_Ticks) {
            this.option_Ticks = new Vector[3];
            for (int index = 0; index < 3; ++index) {
                this.option_Ticks[index] = new Vector();
            }
        }
        if (this.option_Boxed) {
            if (null == this.option_BoxStyle) {
                this.option_BoxStyle = new Primitive3D();
            }
            if (null == this.option_BoxStyle.standard_color) {
                this.option_BoxStyle.standard_color = this.option_DefaultColor;
            }
        }
        final double[][] two_nothings = { null, null };
        boolean has_label = false;
        boolean has_ticks = false;
        for (int axis_index = 0; axis_index < 3; ++axis_index) {
            boolean was_axis = false;
            for (int line_index = 0; line_index < 4; ++line_index) {
                boolean is_axis = false;
                int line_code = line_index;
                if (1 == axis_index) {
                    if (1 == line_code) {
                        line_code = 2;
                    }
                    else if (2 == line_code) {
                        line_code = 1;
                    }
                }
                if (this.option_Axes[axis_index] && !was_axis) {
                    if (0 <= this.option_AxesEdge[axis_index]) {
                        if (line_code == this.option_AxesEdge[axis_index]) {
                            is_axis = true;
                        }
                    }
                    else if (-1 == this.option_AxesEdge[axis_index]) {
                        if (3 == line_index) {
                            is_axis = true;
                        }
                        else if (this.xor((line_index & 0x1) == 0x1, this.option_ViewPoint[(axis_index + 1) % 3] >= 0.0) && this.xor((line_index & 0x2) == 0x2, this.option_ViewPoint[(axis_index + 2) % 3] <= 0.0) && Math.abs(this.option_ViewPoint[(axis_index + 1) % 3]) <= Math.abs(this.option_ViewPoint[(axis_index + 2) % 3])) {
                            is_axis = true;
                        }
                        else if (this.xor((line_index & 0x1) == 0x1, this.option_ViewPoint[(axis_index + 1) % 3] <= 0.0) && this.xor((line_index & 0x2) == 0x2, this.option_ViewPoint[(axis_index + 2) % 3] >= 0.0) && Math.abs(this.option_ViewPoint[(axis_index + 1) % 3]) >= Math.abs(this.option_ViewPoint[(axis_index + 2) % 3])) {
                            is_axis = true;
                        }
                    }
                }
                if (is_axis || this.option_Boxed) {
                    final double[][] scaled_offsets = new double[2][3];
                    Primitive3D primitive;
                    if (is_axis) {
                        was_axis = true;
                        if (null != this.option_Ticks && null != this.option_Ticks[axis_index]) {
                            has_ticks = true;
                            if (this.option_Ticks[axis_index].isEmpty()) {
                                try {
                                    this.ticks_max_in_length[axis_index] = 0.01;
                                    this.ticks_max_out_length[axis_index] = 0.0;
                                    double[] steps;
                                    double factor;
                                    int index2;
                                    for (steps = new double[] { 0.25, 0.2, 0.1, 0.05, 0.025, 0.02, 0.01 }, factor = Math.pow(10.0, Math.ceil(Math.log(this.original_sizes[axis_index]) / Math.log(10.0))), index2 = 1; index2 < 7 && this.original_sizes[axis_index] / factor / steps[index2] <= 5.0; ++index2) {}
                                    --index2;
                                    final double step = steps[index2] * factor;
                                    for (int workaround = 0; workaround < 1; ++workaround) {}
                                    final double original_start = this.original_center[axis_index] - this.original_sizes[axis_index] / 2.0;
                                    final double original_end = this.original_center[axis_index] + this.original_sizes[axis_index] / 2.0;
                                    double mark_position = Math.ceil(original_start / step) * step;
                                    for (int tick_index = 0; tick_index < 10 && mark_position <= original_end; mark_position += step, ++tick_index) {
                                        if (Math.abs(mark_position) < Math.abs(step / 100.0)) {
                                            mark_position = 0.0;
                                        }
                                        final Primitive3D tick_primitive = new Primitive3D();
                                        tick_primitive.front_specular_exponent = 0.01;
                                        tick_primitive.back_specular_exponent = 0.0;
                                        tick_primitive.original_point_size = mark_position;
                                        tick_primitive.text = Float.toString((float)mark_position);
                                        tick_primitive.is_outlined = false;
                                        this.option_Ticks[axis_index].addElement(tick_primitive);
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException ex) {}
                            }
                            for (int tick_index2 = 0; tick_index2 < this.option_Ticks[axis_index].size(); ++tick_index2) {
                                try {
                                    final Primitive3D text_primitive = new Primitive3D(this.option_Ticks[axis_index].elementAt(tick_index2));
                                    final Primitive3D line_primitive = this.option_Ticks[axis_index].elementAt(tick_index2);
                                    final double mark_position2 = line_primitive.original_point_size;
                                    final double in_length = line_primitive.front_specular_exponent;
                                    final double out_length = line_primitive.back_specular_exponent;
                                    line_primitive.text = null;
                                    line_primitive.count_points = 2;
                                    line_primitive.original_points = new double[2][3];
                                    line_primitive.original_points[0][axis_index] = mark_position2;
                                    line_primitive.original_points[1][axis_index] = mark_position2;
                                    line_primitive.original_scaled_offsets = new double[2][3];
                                    line_primitive.original_scaled_offsets[0][axis_index] = 0.0;
                                    line_primitive.original_scaled_offsets[1][axis_index] = 0.0;
                                    text_primitive.count_points = 1;
                                    text_primitive.original_points = new double[1][3];
                                    text_primitive.original_points[0][axis_index] = mark_position2;
                                    text_primitive.original_scaled_offsets = new double[1][3];
                                    text_primitive.original_scaled_offsets[0][axis_index] = 0.0;
                                    text_primitive.original_point_size = 0.0;
                                    text_primitive.original_thickness = 0.0;
                                    for (int index3 = 1; index3 < 3; ++index3) {
                                        final int other_axis = (axis_index + index3) % 3;
                                        double factor2 = 1.0;
                                        if ((line_index & index3) == 0x0) {
                                            factor2 = -1.0;
                                        }
                                        final double other_axis_minimum = this.original_center[other_axis] - this.original_sizes[other_axis] / 2.0;
                                        line_primitive.original_points[0][other_axis] = other_axis_minimum;
                                        line_primitive.original_points[1][other_axis] = other_axis_minimum;
                                        line_primitive.original_scaled_offsets[0][other_axis] = (factor2 + 1.0) / 2.0 - factor2 * in_length * diagonal_length / 1.4142136 / this.option_BoxRatios[other_axis];
                                        line_primitive.original_scaled_offsets[1][other_axis] = (factor2 + 1.0) / 2.0 + factor2 * out_length * diagonal_length / 1.4142136 / this.option_BoxRatios[other_axis];
                                        for (int workaround2 = 0; workaround2 < 1; ++workaround2) {}
                                        text_primitive.original_points[0][other_axis] = other_axis_minimum;
                                        text_primitive.original_scaled_offsets[0][other_axis] = (factor2 + 1.0) / 2.0 + factor2 * (0.05 + this.ticks_max_in_length[axis_index] + this.ticks_max_out_length[axis_index]) * diagonal_length / 1.4142136 / this.option_BoxRatios[other_axis];
                                    }
                                    this.addPrimitive(line_primitive);
                                    this.addPrimitive(text_primitive);
                                }
                                catch (ArrayIndexOutOfBoundsException ex2) {}
                            }
                        }
                        if (null != this.option_AxesLabel[axis_index]) {
                            has_label = true;
                            primitive = this.option_AxesLabel[axis_index];
                            primitive.count_points = 1;
                            primitive.original_point_size = 0.0;
                            primitive.original_thickness = 0.0;
                            primitive.original_points = two_nothings;
                            primitive.original_scaled_offsets = new double[1][3];
                            primitive.original_scaled_offsets[0][axis_index] = 0.5;
                            for (int index4 = 1; index4 < 3; ++index4) {
                                double factor = 1.0;
                                if ((line_index & index4) == 0x0) {
                                    factor = -1.0;
                                }
                                final int other_axis2 = (axis_index + index4) % 3;
                                primitive.original_scaled_offsets[0][other_axis2] = (factor + 1.0) / 2.0 + factor * (0.15 + this.ticks_max_in_length[axis_index] + this.ticks_max_out_length[axis_index]) * diagonal_length / 1.4142136 / this.option_BoxRatios[other_axis2];
                            }
                            this.addPrimitive(primitive);
                        }
                        if (null != this.option_AxesStyle[axis_index]) {
                            primitive = new Primitive3D(this.option_AxesStyle[axis_index]);
                        }
                        else {
                            primitive = new Primitive3D();
                        }
                        if (null == primitive.standard_color) {
                            primitive.standard_color = this.option_DefaultColor;
                        }
                    }
                    else {
                        primitive = new Primitive3D(this.option_BoxStyle);
                    }
                    primitive.is_filled = false;
                    primitive.count_points = 2;
                    primitive.original_points = two_nothings;
                    primitive.original_scaled_offsets = scaled_offsets;
                    primitive.original_scaled_offsets[0][axis_index] = 0.0;
                    primitive.original_scaled_offsets[0][(axis_index + 1) % 3] = (line_index & 0x1);
                    primitive.original_scaled_offsets[0][(axis_index + 2) % 3] = (line_index & 0x2) / 2;
                    primitive.original_scaled_offsets[1][axis_index] = 1.0;
                    primitive.original_scaled_offsets[1][(axis_index + 1) % 3] = (line_index & 0x1);
                    primitive.original_scaled_offsets[1][(axis_index + 2) % 3] = (line_index & 0x2) / 2;
                    this.addPrimitive(primitive);
                }
            }
        }
        if (null != this.option_PlotLabel) {
            final Primitive3D primitive = this.option_PlotLabel;
            primitive.count_points = 0;
            primitive.original_point_size = 0.0;
            primitive.original_thickness = 1.0;
            primitive.original_points = null;
            primitive.original_scaled_offsets = null;
            this.addPrimitive(primitive);
        }
        final float[] coordinates = new float[3];
        this.xs = new float[this.max_count_points];
        this.ys = new float[this.max_count_points];
        this.zs = new float[this.max_count_points];
        this.point_flags = new int[this.max_count_points];
        this.count_points = 0;
        for (int primitive_index = 0; primitive_index < this.count_primitives; ++primitive_index) {
            final Primitive3D primitive = this.primitives.elementAt(primitive_index);
            if (null == primitive.points && primitive.count_points > 0) {
                primitive.points = new int[primitive.count_points + 1];
            }
            int last_old_point_index = -1;
            boolean is_point_dependent = false;
            point_index = 0;
            for (int original_point_index = 0; original_point_index < primitive.count_points; ++original_point_index) {
                if (null != primitive.original_points[original_point_index]) {
                    for (int axis_index = 0; axis_index < 3; ++axis_index) {
                        coordinates[axis_index] = (float)(this.scalings[axis_index] * (primitive.original_points[original_point_index][axis_index] - this.original_center[axis_index]) + this.scaled_scalings[axis_index] * primitive.original_scaled_offsets[original_point_index][axis_index]);
                    }
                    is_point_dependent = (null != evaluator && null != primitive.original_expressions && (primitive.original_expressions[original_point_index][0] >= 0 || primitive.original_expressions[original_point_index][1] >= 0 || primitive.original_expressions[original_point_index][2] >= 0));
                }
                else {
                    for (int axis_index = 0; axis_index < 3; ++axis_index) {
                        coordinates[axis_index] = (float)(this.scaled_scalings[axis_index] * (primitive.original_scaled_offsets[original_point_index][axis_index] - 0.5));
                    }
                    is_point_dependent = false;
                }
                int old_point_index;
                if (is_point_dependent) {
                    old_point_index = this.count_points;
                    this.point_flags[old_point_index] |= 0x2;
                    for (int axis_index = 0; axis_index < 3; ++axis_index) {
                        final int expression_index = primitive.original_expressions[original_point_index][axis_index];
                        if (expression_index >= 0) {
                            evaluator.setExpressionCoordinate(expression_index, this, old_point_index, axis_index);
                        }
                    }
                }
                else {
                    old_point_index = 0;
                }
                while (old_point_index < this.count_points && ((this.point_flags[old_point_index] & 0x2) == 0x2 || coordinates[0] != this.xs[old_point_index] || coordinates[1] != this.ys[old_point_index] || coordinates[2] != this.zs[old_point_index])) {
                    ++old_point_index;
                }
                if (primitive.is_filled) {
                    this.point_flags[old_point_index] |= 0x1;
                }
                if (old_point_index >= this.count_points) {
                    this.xs[old_point_index] = coordinates[0];
                    this.ys[old_point_index] = coordinates[1];
                    this.zs[old_point_index] = coordinates[2];
                    this.count_points = old_point_index + 1;
                }
                if (last_old_point_index != old_point_index) {
                    primitive.points[point_index] = old_point_index;
                    if (primitive.edge_flags != null) {
                        primitive.edge_flags[point_index] = primitive.edge_flags[original_point_index];
                    }
                    last_old_point_index = old_point_index;
                    ++point_index;
                }
            }
            if (primitive.is_filled && 4 <= point_index && primitive.points[0] == primitive.points[point_index - 1]) {
                --point_index;
            }
            if (2 == primitive.count_points) {
                if (1 == point_index) {
                    primitive.points[1] = primitive.points[0];
                }
            }
            else if (3 <= primitive.count_points) {
                if (1 == point_index) {
                    primitive.points[1] = primitive.points[0];
                    point_index = 2;
                }
                if (2 == point_index) {
                    primitive.points[2] = primitive.points[1];
                    point_index = 3;
                }
                primitive.count_points = point_index;
            }
            if (null != primitive.text) {
                primitive.point_diameter = -1.0;
            }
            else {
                if (1 == primitive.count_points && is_point_dependent) {
                    for (int axis_index = 0; axis_index < 3; ++axis_index) {
                        final int expression_index = primitive.original_expressions[0][axis_index];
                        if (expression_index >= 0 && evaluator.getExpression(expression_index).isAtomicIndependentVariable()) {
                            primitive.font_url = Primitive3D.text_dragable_point;
                        }
                    }
                }
                if (!primitive.is_filled && 1 == primitive.count_points) {
                    if (primitive.is_absolute_point_size) {
                        primitive.point_diameter = primitive.original_point_size;
                    }
                    else {
                        primitive.point_diameter = primitive.original_point_size * this.full_pixel_height * diagonal_length;
                    }
                }
                else if (primitive.is_filled) {
                    if (primitive.is_absolute_edge_thickness) {
                        primitive.point_diameter = primitive.original_edge_thickness;
                    }
                    else {
                        primitive.point_diameter = primitive.original_edge_thickness * this.full_pixel_height * diagonal_length;
                    }
                }
                else if (primitive.is_absolute_thickness) {
                    primitive.point_diameter = primitive.original_thickness;
                }
                else {
                    primitive.point_diameter = primitive.original_thickness * this.full_pixel_height * diagonal_length;
                }
                if (primitive.point_diameter < 0.0) {
                    primitive.point_diameter = 0.0;
                }
                if (1 == primitive.count_points) {
                    primitive.first_point = primitive.points[0];
                    primitive.second_point = primitive.points[0];
                    primitive.third_point = primitive.points[0];
                    primitive.fourth_point = primitive.points[0];
                }
                else if (2 == primitive.count_points) {
                    primitive.first_point = primitive.points[0];
                    primitive.second_point = primitive.points[0];
                    primitive.third_point = primitive.points[1];
                    primitive.fourth_point = primitive.points[1];
                }
                else if (3 == primitive.count_points) {
                    primitive.first_point = primitive.points[0];
                    primitive.second_point = primitive.points[1];
                    primitive.third_point = primitive.points[1];
                    primitive.fourth_point = primitive.points[2];
                }
                else if (4 == primitive.count_points) {
                    int min_edge_index = 0;
                    double min_edge_distance = 0.0;
                    double edge_distance = 0.0;
                    for (int edge_index = 0; edge_index < 4; ++edge_index) {
                        edge_distance = this.getPointDistance(primitive, edge_index);
                        if (0 == edge_index || min_edge_distance > edge_distance) {
                            min_edge_index = edge_index;
                            min_edge_distance = edge_distance;
                        }
                    }
                    if (1 == min_edge_index || 2 == min_edge_index) {
                        primitive.first_point = primitive.points[0];
                        primitive.second_point = primitive.points[1];
                        primitive.third_point = primitive.points[2];
                        primitive.fourth_point = primitive.points[3];
                    }
                    else {
                        primitive.first_point = primitive.points[2];
                        primitive.second_point = primitive.points[3];
                        primitive.third_point = primitive.points[0];
                        primitive.fourth_point = primitive.points[1];
                    }
                }
                else if (4 < primitive.count_points) {
                    double distance_sum = 0.0;
                    for (int edge_index2 = 0; edge_index2 < primitive.count_points; ++edge_index2) {
                        distance_sum += this.getPointDistance(primitive, edge_index2);
                    }
                    final double distance = distance_sum / 3.0;
                    int i = 1;
                    double i_times_distance = distance;
                    int second_point_index = 0;
                    distance_sum = 0.0;
                    primitive.first_point = primitive.points[0];
                    for (int edge_index3 = 0; i < 3 && edge_index3 < primitive.count_points; ++edge_index3) {
                        final double last_distance_sum = distance_sum;
                        distance_sum += this.getPointDistance(primitive, edge_index3);
                        if (distance_sum > i_times_distance) {
                            if (distance_sum - i_times_distance < i_times_distance - last_distance_sum) {
                                point_index = (edge_index3 + 1) % primitive.count_points;
                            }
                            else {
                                point_index = edge_index3;
                            }
                            if (i == 1) {
                                primitive.second_point = primitive.points[point_index];
                                second_point_index = point_index;
                            }
                            else if (i == 2) {
                                primitive.third_point = primitive.points[(second_point_index + point_index) / 2];
                                primitive.fourth_point = primitive.points[point_index];
                            }
                            ++i;
                            i_times_distance += distance;
                        }
                    }
                }
            }
            if (primitive.font_url != Primitive3D.text_dragable_point) {
                primitive.original_expressions = null;
            }
        }
        if (null != this.option_ViewPoint && null != this.option_ViewVertical) {
            this.set_rotation_from_view_parameters(this.option_ViewPoint, this.option_ViewVertical);
            if (has_label || has_ticks) {
                this.setPerspective(this.length_view_point, 0.66, this.is_stereo, this.stereo_distance);
            }
            else {
                this.setPerspective(this.length_view_point, 1.0, this.is_stereo, this.stereo_distance);
            }
        }
        else if (has_label || has_ticks) {
            this.setPerspective(3.38378, 0.66, this.is_stereo, this.stereo_distance);
        }
        else {
            this.setPerspective(3.38378, 1.0, this.is_stereo, this.stereo_distance);
        }
        this.initial_magnification = this.magnification;
        for (int primitive_index = 0; primitive_index < this.count_primitives; ++primitive_index) {
            final Primitive3D primitive = this.primitives.elementAt(primitive_index);
            if (null == primitive.standard_color) {
                primitive.standard_color = this.option_DefaultColor;
            }
            if (null != primitive.text) {
                if (null == primitive.front_face_color) {
                    if (null != this.option_TextStyle_font_color) {
                        primitive.front_face_color = this.option_TextStyle_font_color;
                    }
                    else {
                        primitive.front_face_color = primitive.standard_color;
                    }
                }
                if (null == primitive.back_face_color && null != this.option_TextStyle_font_background) {
                    primitive.back_face_color = this.option_TextStyle_font_background;
                }
                primitive.front_color = primitive.front_face_color;
                primitive.back_color = primitive.back_face_color;
            }
            else {
                if (null == primitive.edge_color) {
                    primitive.edge_color = this.option_DefaultColor;
                }
                if (null == primitive.front_face_color) {
                    primitive.front_face_color = this.option_DefaultColor;
                }
                if (null == primitive.back_face_color && null == primitive.text) {
                    primitive.back_face_color = this.option_DefaultColor;
                }
                if (!this.option_Lighting) {
                    primitive.front_color = primitive.front_face_color;
                    primitive.back_color = primitive.back_face_color;
                }
            }
            if (primitive.is_filled && this.option_Lighting && 2 < primitive.count_points) {
                double[] normal_vector = new double[3];
                final int[][] face_colors = { { primitive.front_diffuse_color.getRed() * this.option_AmbientLight.getRed() / 255, primitive.front_diffuse_color.getGreen() * this.option_AmbientLight.getGreen() / 255, primitive.front_diffuse_color.getBlue() * this.option_AmbientLight.getBlue() / 255 }, { primitive.back_diffuse_color.getRed() * this.option_AmbientLight.getRed() / 255, primitive.back_diffuse_color.getGreen() * this.option_AmbientLight.getGreen() / 255, primitive.back_diffuse_color.getBlue() * this.option_AmbientLight.getBlue() / 255 } };
                final float a_x = this.xs[primitive.second_point] - this.xs[primitive.first_point];
                final float a_y = this.ys[primitive.second_point] - this.ys[primitive.first_point];
                final float a_z = this.zs[primitive.second_point] - this.zs[primitive.first_point];
                final float b_x = this.xs[primitive.fourth_point] - this.xs[primitive.second_point];
                final float b_y = this.ys[primitive.fourth_point] - this.ys[primitive.second_point];
                final float b_z = this.zs[primitive.fourth_point] - this.zs[primitive.second_point];
                normal_vector[0] = a_y * b_z - a_z * b_y;
                normal_vector[1] = a_z * b_x - a_x * b_z;
                normal_vector[2] = a_x * b_y - a_y * b_x;
                final double vector_length = Math.sqrt(normal_vector[0] * normal_vector[0] + normal_vector[1] * normal_vector[1] + normal_vector[2] * normal_vector[2]);
                normal_vector[0] /= vector_length;
                normal_vector[1] /= vector_length;
                normal_vector[2] /= vector_length;
                normal_vector = this.rotation.rotated(normal_vector);
                for (int light_index = 0; light_index < this.option_LightSources_vectors.size(); ++light_index) {
                    final double[] light_source_vector = this.option_LightSources_vectors.elementAt(light_index);
                    final Color light_source_color = this.option_LightSources_colors.elementAt(light_index);
                    double dot_product = normal_vector[0] * light_source_vector[0] + normal_vector[1] * light_source_vector[1] + normal_vector[2] * light_source_vector[2];
                    int face_index;
                    Color diffuse_color;
                    if (dot_product > 0.0) {
                        face_index = 0;
                        diffuse_color = primitive.front_diffuse_color;
                    }
                    else {
                        dot_product = -dot_product;
                        face_index = 1;
                        diffuse_color = primitive.back_diffuse_color;
                    }
                    if (null != diffuse_color) {
                        dot_product /= 255.0;
                        face_colors[face_index][0] += (int)(diffuse_color.getRed() * light_source_color.getRed() * dot_product);
                        face_colors[face_index][1] += (int)(diffuse_color.getGreen() * light_source_color.getGreen() * dot_product);
                        face_colors[face_index][2] += (int)(diffuse_color.getBlue() * light_source_color.getBlue() * dot_product);
                    }
                    Color specular_color = null;
                    double specular_exponent = 1.0;
                    dot_product = normal_vector[0] * light_source_vector[0] + normal_vector[1] * light_source_vector[1] + normal_vector[2] * light_source_vector[2];
                    if (normal_vector[2] >= 0.0) {
                        if (0.0 < dot_product) {
                            face_index = 0;
                            specular_color = primitive.front_specular_color;
                            specular_exponent = primitive.front_specular_exponent;
                        }
                    }
                    else if (0.0 > dot_product) {
                        face_index = 1;
                        specular_color = primitive.back_specular_color;
                        specular_exponent = primitive.back_specular_exponent;
                    }
                    if (null != specular_color) {
                        final double[] reflected_view = { 2.0 * normal_vector[0] * normal_vector[2], 2.0 * normal_vector[1] * normal_vector[2], 2.0 * normal_vector[2] * normal_vector[2] - 1.0 };
                        dot_product = reflected_view[0] * light_source_vector[0] + reflected_view[1] * light_source_vector[1] + reflected_view[2] * light_source_vector[2];
                        ++dot_product;
                        if (dot_product < 0.0) {
                            dot_product = 0.0;
                        }
                        dot_product = Math.pow(Math.abs(dot_product / 2.0), specular_exponent);
                        dot_product /= 255.0;
                        face_colors[face_index][0] += (int)(specular_color.getRed() * light_source_color.getRed() * dot_product);
                        face_colors[face_index][1] += (int)(specular_color.getGreen() * light_source_color.getGreen() * dot_product);
                        face_colors[face_index][2] += (int)(specular_color.getBlue() * light_source_color.getBlue() * dot_product);
                    }
                }
                for (int color_index = 0; color_index < 3; ++color_index) {
                    for (int face_index2 = 0; face_index2 < 2; ++face_index2) {
                        if (0 > face_colors[face_index2][color_index]) {
                            face_colors[face_index2][color_index] = 0;
                        }
                        if (255 < face_colors[face_index2][color_index]) {
                            face_colors[face_index2][color_index] = 255;
                        }
                    }
                }
                primitive.front_color = new Color(face_colors[0][0], face_colors[0][1], face_colors[0][2]);
                primitive.back_color = new Color(face_colors[1][0], face_colors[1][1], face_colors[1][2]);
            }
        }
        this.order = new int[this.count_primitives];
        int ordered_primitive_index = 0;
        for (int primitive_index = 0; primitive_index < this.count_primitives; ++primitive_index) {
            boolean is_edge = false;
            final Primitive3D primitive = this.primitives.elementAt(primitive_index);
            if (!primitive.is_filled && primitive.count_points > 1) {
                boolean all_points_in_polygons;
                for (all_points_in_polygons = true, point_index = 0; point_index < primitive.count_points && all_points_in_polygons; ++point_index) {
                    if ((this.point_flags[primitive.points[point_index]] & 0x1) == 0x0) {
                        all_points_in_polygons = false;
                    }
                }
                if (all_points_in_polygons) {
                    Primitive3D primitive2 = null;
                    for (int primitive2_index = 0; primitive2_index < this.count_primitives; ++primitive2_index) {
                        boolean all_points_in_polygon = true;
                        primitive2 = this.primitives.elementAt(primitive2_index);
                        if (primitive2.is_filled) {
                            for (point_index = 0; point_index < primitive.count_points && all_points_in_polygon; ++point_index) {
                                boolean point_in_polygon = false;
                                for (int point2_index = 0; point2_index < primitive2.count_points && !point_in_polygon; ++point2_index) {
                                    if (primitive.points[point_index] == primitive2.points[point2_index]) {
                                        point_in_polygon = true;
                                    }
                                }
                                if (!point_in_polygon) {
                                    all_points_in_polygon = false;
                                }
                            }
                            if (all_points_in_polygon) {
                                primitive2.addEdge(primitive);
                                is_edge = true;
                            }
                        }
                    }
                }
            }
            if (!is_edge) {
                this.order[ordered_primitive_index] = primitive_index;
                ++ordered_primitive_index;
            }
        }
        this.count_ordered_primitives = ordered_primitive_index;
        this.left_pixel_xs = new int[this.count_points];
        this.right_pixel_xs = new int[this.count_points];
        this.pixel_ys = new int[this.count_points];
        this.point_scale = new float[this.count_points];
        this.temp_xs = new int[this.max_primitive_count_points + 1];
        this.temp_ys = new int[this.max_primitive_count_points + 1];
        this.temp_line_xs = new int[6];
        this.temp_line_ys = new int[6];
        this.rotated_center_zs = new float[this.count_primitives];
        if (null == this.option_TextStyle_font_family) {
            this.option_TextStyle_font_family = "Courier";
        }
        if (-1 == this.option_TextStyle_font_weight) {
            this.option_TextStyle_font_weight = 0;
        }
        if (-1 == this.option_TextStyle_font_slant) {
            this.option_TextStyle_font_slant = 0;
        }
        if (-1 == this.option_TextStyle_font_size) {
            this.option_TextStyle_font_size = 10;
        }
        for (int primitive_index = 0; primitive_index < this.count_primitives; ++primitive_index) {
            final Primitive3D primitive = this.primitives.elementAt(primitive_index);
            if (null != primitive.text) {
                if (null == primitive.font_url) {
                    primitive.font_url = this.option_TextStyle_font_url;
                }
                if (null == primitive.font) {
                    primitive.font = this.option_TextStyle_font_family;
                }
                if (-1 == primitive.font_size) {
                    primitive.font_size = this.option_TextStyle_font_size;
                }
                if (-1 == primitive.font_weight) {
                    primitive.font_weight = this.option_TextStyle_font_weight;
                }
                if (-1 == primitive.font_slant) {
                    primitive.font_slant = this.option_TextStyle_font_slant;
                }
                primitive.font = new Font((String)primitive.font, primitive.font_weight | primitive.font_slant, primitive.font_size);
                g.setFont((Font)primitive.font);
                final FontMetrics fm = g.getFontMetrics();
                primitive.font_size = fm.getMaxAscent() + fm.getMaxDescent();
                if (null == evaluator || !primitive.is_outlined) {
                    primitive.font_weight = fm.stringWidth(primitive.text);
                }
                else {
                    primitive.font_weight = fm.stringWidth(primitive.text);
                }
                primitive.font_slant = -(int)(primitive.font_weight * (primitive.original_point_size + 1.0) / 2.0);
                primitive.font_y_offset = (int)(primitive.font_size * (primitive.original_thickness + 1.0) / 2.0) - fm.getMaxDescent();
                primitive.first_point = primitive.font_slant - 2;
                primitive.second_point = primitive.font_y_offset - fm.getMaxAscent() - 2;
                primitive.third_point = primitive.font_weight + 4;
                primitive.fourth_point = primitive.font_size + 4;
            }
        }
    }
    
    public boolean setCoordinateValue(final int point_index, final int axis_index, final double value) {
        final float coordinate = (float)(this.scalings[axis_index] * (value - this.original_center[axis_index]));
        if (0 == axis_index) {
            this.xs[point_index] = coordinate;
        }
        else if (1 == axis_index) {
            this.ys[point_index] = coordinate;
        }
        else if (2 == axis_index) {
            this.zs[point_index] = coordinate;
        }
        return true;
    }
    
    public void set_rotation_from_view_parameters(double[] view_point, double[] view_vertical) {
        double[] box_ratios = null;
        final double[] rotation_axis = new double[3];
        final double[] normalized_view_point = new double[3];
        if (null == view_point || view_point.length < 3) {
            view_point = this.option_ViewPoint;
            if (null == view_point || view_point.length < 3) {
                view_point = new double[] { 1.3, -2.4, 2.0 };
            }
        }
        if (null == view_vertical || view_vertical.length < 3) {
            view_vertical = this.option_ViewVertical;
            if (null == view_vertical || view_vertical.length < 3) {
                view_vertical = new double[] { 0.0, 0.0, 1.0 };
            }
        }
        if (null == box_ratios || box_ratios.length < 3) {
            box_ratios = this.option_BoxRatios;
            if (null == box_ratios || box_ratios.length < 3) {
                box_ratios = new double[] { 1.0, 1.0, 1.0 };
            }
        }
        final double length_view_point = Math.sqrt(view_point[0] * view_point[0] + view_point[1] * view_point[1] + view_point[2] * view_point[2]);
        normalized_view_point[0] = view_point[0] / length_view_point;
        normalized_view_point[1] = view_point[1] / length_view_point;
        normalized_view_point[2] = view_point[2] / length_view_point;
        rotation_axis[0] = normalized_view_point[1];
        rotation_axis[1] = -normalized_view_point[0];
        rotation_axis[2] = 0.0;
        double length_rotation_axis = Math.sqrt(rotation_axis[0] * rotation_axis[0] + rotation_axis[1] * rotation_axis[1] + rotation_axis[2] * rotation_axis[2]);
        if (1.0 < length_rotation_axis) {
            length_rotation_axis = 1.0;
        }
        double rotation_angle = Math.asin(length_rotation_axis);
        if (rotation_angle < 1.0E-4) {
            if (0.0 < view_point[2]) {
                this.rotation = new Quaternion(0.0, 1.0, 0.0, 0.0, false);
            }
            else {
                this.rotation = new Quaternion(3.141592653589793, 1.0, 0.0, 0.0, false);
            }
        }
        else {
            if (0.0 > view_point[2]) {
                rotation_angle = 3.141592653589793 - rotation_angle;
            }
            this.rotation = new Quaternion(rotation_angle, rotation_axis[0], rotation_axis[1], rotation_axis[2], true);
        }
        double[] rotated_view_vertical = { view_vertical[0] * box_ratios[0], view_vertical[1] * box_ratios[1], view_vertical[2] * box_ratios[2] };
        rotated_view_vertical = this.rotation.rotated(rotated_view_vertical);
        if (1.0E-7 > Math.abs(rotated_view_vertical[0])) {
            if (0.0 < rotated_view_vertical[1]) {
                rotation_angle = 0.0;
            }
            else {
                rotation_angle = 3.141592653589793;
            }
        }
        else {
            rotation_angle = Math.atan2(rotated_view_vertical[0], rotated_view_vertical[1]);
        }
        this.rotation = new Quaternion(rotation_angle, 0.0, 0.0, 1.0, false).product(this.rotation);
        this.initial_length_view_point = length_view_point;
        this.initial_rotation = this.rotation;
    }
    
    public double getPointDistance(final Primitive3D primitive, final int edge_index) {
        final int point1 = primitive.points[edge_index];
        final int point2 = primitive.points[(edge_index + 1) % primitive.count_points];
        return Math.abs(this.xs[point2] - this.xs[point1]) + Math.abs(this.ys[point2] - this.ys[point1]) + Math.abs(this.zs[point2] - this.zs[point1]);
    }
    
    public void setPerspective(double length_view_point, final double magnification, final boolean is_stereo, final double stereo_distance) {
        this.length_view_point = length_view_point;
        this.magnification = magnification;
        this.is_stereo = is_stereo;
        this.stereo_distance = stereo_distance;
        if (!is_stereo) {
            this.pixel_width = this.full_pixel_width;
            this.pixel_height = this.full_pixel_height;
        }
        else {
            this.pixel_width = (this.full_pixel_width + 1) / 2;
            this.pixel_height = this.full_pixel_height;
        }
        final double radius = 0.5 * Math.sqrt(this.option_BoxRatios[0] * this.option_BoxRatios[0] + this.option_BoxRatios[1] * this.option_BoxRatios[1] + this.option_BoxRatios[2] * this.option_BoxRatios[2]);
        if (length_view_point < 1.01 * radius) {
            length_view_point = 1.01 * radius;
            this.length_view_point = length_view_point;
        }
        this.eye_distance = length_view_point;
        this.width = 2.0 * Math.sqrt(length_view_point * length_view_point * radius * radius / (length_view_point * length_view_point - radius * radius));
        this.height = this.width;
        if (this.pixel_width < this.pixel_height) {
            this.height = this.height * this.pixel_height / this.pixel_width;
        }
        else {
            this.width = this.width * this.pixel_width / this.pixel_height;
        }
        this.eye_distance /= magnification;
        this.width /= magnification;
        this.height /= magnification;
        this.stereo_offset = stereo_distance / 2.0 * radius * 2.0;
    }
    
    public void setCutPrimitivesCount(final int count, final int visible_faces_default, final boolean new_show_faces) {
        this.min_primitive_index = 0;
        this.max_primitive_index = this.count_primitives - count - 1;
        if (count > 0) {
            this.visible_faces = 3;
        }
        else {
            this.visible_faces = visible_faces_default;
        }
        this.show_faces = new_show_faces;
    }
    
    public void paint(final Graphics left_graphics, final Graphics right_graphics, final Image right_image, final Evaluator evaluator) {
        if (!this.is_stereo) {
            this.paintPrimitives(left_graphics, this.left_pixel_xs, evaluator);
        }
        else {
            this.paintPrimitives(left_graphics, this.left_pixel_xs, evaluator);
            this.paintPrimitives(right_graphics, this.right_pixel_xs, evaluator);
            left_graphics.drawImage(right_image, this.pixel_width, 0, null);
        }
    }
    
    public boolean setInactiveFlags(int lower_bound, int higher_bound, final int flags) {
        if (lower_bound > higher_bound || higher_bound < 0 || lower_bound >= this.count_primitives) {
            return true;
        }
        if (lower_bound < 0) {
            lower_bound = 0;
        }
        if (higher_bound >= this.count_primitives) {
            higher_bound = this.count_primitives - 1;
        }
        for (int primitive_index = lower_bound; primitive_index <= higher_bound; ++primitive_index) {
            final Primitive3D primitive = this.primitives.elementAt(primitive_index);
            if (null != primitive) {
                primitive.is_inactive_flags |= flags;
            }
        }
        return true;
    }
    
    public boolean clearInactiveFlags(int lower_bound, int higher_bound, final int flags) {
        if (lower_bound > higher_bound || higher_bound < 0 || lower_bound >= this.count_primitives) {
            return true;
        }
        if (lower_bound < 0) {
            lower_bound = 0;
        }
        if (higher_bound >= this.count_primitives) {
            higher_bound = this.count_primitives - 1;
        }
        for (int primitive_index = lower_bound; primitive_index <= higher_bound; ++primitive_index) {
            final Primitive3D primitive = this.primitives.elementAt(primitive_index);
            if (null != primitive) {
                primitive.is_inactive_flags = (primitive.is_inactive_flags | flags) - flags;
            }
        }
        return true;
    }
    
    public void paintPrimitives(final Graphics g, final int[] pixel_xs, final Evaluator evaluator) {
        int edge_index = 0;
        for (int ordered_index = 0; ordered_index < this.count_ordered_primitives; ++ordered_index) {
            final int primitive_index = this.order[ordered_index];
            if (this.min_primitive_index <= primitive_index && primitive_index <= this.max_primitive_index) {
                final Primitive3D polygon;
                Primitive3D primitive = polygon = this.primitives.elementAt(primitive_index);
                edge_index = -1;
                if (primitive != null) {
                    if (0 == primitive.is_inactive_flags) {
                        while (primitive != null) {
                            boolean is_visible = true;
                            int count_primitive_points = primitive.count_points;
                            if (null != primitive.text) {
                                if (0 == primitive.count_points) {
                                    if (null != primitive.back_color) {
                                        g.setColor(primitive.back_color);
                                        g.fillRect(this.pixel_width / 2 + primitive.first_point, 2 + primitive.second_point, primitive.third_point, primitive.fourth_point);
                                    }
                                    g.setColor(primitive.front_color);
                                    g.setFont((Font)primitive.font);
                                    g.drawString(primitive.text, this.pixel_width / 2 + primitive.font_slant, 2 + primitive.font_y_offset);
                                }
                                else {
                                    final int point_index = primitive.points[0];
                                    if (0.0f != this.point_scale[point_index]) {
                                        int variable_index = -1;
                                        String actual_text = null;
                                        g.setFont((Font)primitive.font);
                                        if (null != evaluator && primitive.is_outlined) {
                                            variable_index = evaluator.getVariableIndex(primitive.text);
                                        }
                                        if (variable_index >= 0) {
                                            actual_text = Float.toString((float)evaluator.getVariableValue(variable_index));
                                            final FontMetrics fm = g.getFontMetrics();
                                            primitive.font_weight = fm.stringWidth(actual_text);
                                            primitive.font_slant = -(int)(primitive.font_weight * (primitive.original_point_size + 1.0) / 2.0);
                                            primitive.font_y_offset = (int)(primitive.font_size * (primitive.original_thickness + 1.0) / 2.0) - fm.getMaxDescent();
                                            primitive.first_point = primitive.font_slant - 2;
                                            primitive.second_point = primitive.font_y_offset - fm.getMaxAscent() - 2;
                                            primitive.third_point = primitive.font_weight + 4;
                                            primitive.fourth_point = primitive.font_size + 4;
                                        }
                                        else {
                                            actual_text = primitive.text;
                                        }
                                        if (null != primitive.back_color) {
                                            g.setColor(primitive.back_color);
                                            g.fillRect(pixel_xs[point_index] + primitive.first_point, this.pixel_ys[point_index] + primitive.second_point, primitive.third_point, primitive.fourth_point);
                                        }
                                        g.setColor(primitive.front_color);
                                        g.drawString(actual_text, pixel_xs[point_index] + primitive.font_slant, this.pixel_ys[point_index] + primitive.font_y_offset);
                                    }
                                }
                            }
                            else if (1 == count_primitive_points) {
                                final int point = primitive.points[0];
                                if (0.0f != this.point_scale[point]) {
                                    g.setColor(primitive.standard_color);
                                    int diameter;
                                    if (primitive.is_absolute_point_size) {
                                        diameter = (int)primitive.point_diameter;
                                    }
                                    else {
                                        diameter = (int)(primitive.point_diameter / this.point_scale[point]);
                                    }
                                    if (diameter >= 4) {
                                        final int radius = diameter / 2;
                                        g.fillOval(pixel_xs[point] - radius, this.pixel_ys[point] - radius, diameter, diameter);
                                        if (null != this.point_edge_color) {
                                            g.setColor(this.point_edge_color);
                                            g.drawOval(pixel_xs[point] - radius, this.pixel_ys[point] - radius, diameter, diameter);
                                        }
                                    }
                                    else {
                                        if (null != this.point_edge_color) {
                                            g.setColor(this.point_edge_color);
                                        }
                                        if (diameter < 3) {
                                            g.drawLine(pixel_xs[point], this.pixel_ys[point], pixel_xs[point] + 1, this.pixel_ys[point]);
                                        }
                                        else {
                                            g.drawLine(pixel_xs[point] - 1, this.pixel_ys[point], pixel_xs[point] + 1, this.pixel_ys[point]);
                                            g.drawLine(pixel_xs[point] - 1, this.pixel_ys[point] + 1, pixel_xs[point] + 1, this.pixel_ys[point] + 1);
                                        }
                                    }
                                }
                            }
                            else if (1 < count_primitive_points) {
                                int point_index;
                                for (point_index = 0; point_index < count_primitive_points; ++point_index) {
                                    final int point = primitive.points[point_index];
                                    if (0.0f == this.point_scale[point]) {
                                        break;
                                    }
                                    this.temp_xs[point_index] = pixel_xs[point];
                                    this.temp_ys[point_index] = this.pixel_ys[point];
                                }
                                if (point_index >= count_primitive_points) {
                                    boolean is_absolute_thickness = true;
                                    if (primitive.is_filled && 2 < count_primitive_points) {
                                        int z_coordinate = (pixel_xs[primitive.second_point] - pixel_xs[primitive.first_point]) * (this.pixel_ys[primitive.fourth_point] - this.pixel_ys[primitive.second_point]) - (pixel_xs[primitive.fourth_point] - pixel_xs[primitive.second_point]) * (this.pixel_ys[primitive.second_point] - this.pixel_ys[primitive.first_point]);
                                        if (0 == z_coordinate) {
                                            z_coordinate = primitive.last_z_coordinate;
                                        }
                                        else {
                                            primitive.last_z_coordinate = z_coordinate;
                                        }
                                        if (0 >= z_coordinate) {
                                            if ((this.visible_faces & 0x1) == 0x1) {
                                                g.setColor(primitive.front_color);
                                            }
                                            else {
                                                is_visible = false;
                                            }
                                        }
                                        else if (0 < z_coordinate) {
                                            if ((this.visible_faces & 0x2) == 0x2) {
                                                g.setColor(primitive.back_color);
                                            }
                                            else {
                                                is_visible = false;
                                            }
                                        }
                                        if (is_visible) {
                                            if (this.show_faces) {
                                                g.fillPolygon(this.temp_xs, this.temp_ys, count_primitive_points);
                                            }
                                            if (primitive.is_outlined) {
                                                this.temp_xs[count_primitive_points] = this.temp_xs[0];
                                                this.temp_ys[count_primitive_points] = this.temp_ys[0];
                                                primitive.points[count_primitive_points] = primitive.points[0];
                                                ++count_primitive_points;
                                                g.setColor(primitive.edge_color);
                                                is_absolute_thickness = primitive.is_absolute_edge_thickness;
                                            }
                                        }
                                    }
                                    else if (!primitive.is_filled) {
                                        g.setColor(primitive.standard_color);
                                        is_absolute_thickness = primitive.is_absolute_thickness;
                                    }
                                    if (!primitive.is_filled || (is_visible && primitive.is_outlined && 3 < count_primitive_points)) {
                                        if (is_absolute_thickness && 2 > (int)primitive.point_diameter) {
                                            if (null == primitive.edge_flags && (primitive.is_filled || 2 >= count_primitive_points)) {
                                                g.drawPolygon(this.temp_xs, this.temp_ys, count_primitive_points);
                                            }
                                            else {
                                                int x_start = this.temp_xs[0];
                                                int y_start = this.temp_ys[0];
                                                if (null == primitive.edge_flags) {
                                                    for (point_index = 1; point_index < count_primitive_points; ++point_index) {
                                                        final int x_end = this.temp_xs[point_index];
                                                        final int y_end = this.temp_ys[point_index];
                                                        g.drawLine(x_start, y_start, x_end, y_end);
                                                        x_start = x_end;
                                                        y_start = y_end;
                                                    }
                                                }
                                                else {
                                                    for (point_index = 1; point_index < count_primitive_points; ++point_index) {
                                                        final int x_end = this.temp_xs[point_index];
                                                        final int y_end = this.temp_ys[point_index];
                                                        if (primitive.edge_flags[point_index - 1]) {
                                                            g.drawLine(x_start, y_start, x_end, y_end);
                                                        }
                                                        x_start = x_end;
                                                        y_start = y_end;
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            final int diameter = (int)primitive.point_diameter;
                                            int diameter_start;
                                            if (!is_absolute_thickness) {
                                                diameter_start = (int)(primitive.point_diameter / this.point_scale[primitive.points[0]]);
                                            }
                                            else {
                                                diameter_start = (int)primitive.point_diameter;
                                            }
                                            --diameter_start;
                                            int radius_start = diameter_start / 2;
                                            int x_start2 = this.temp_xs[0] - radius_start;
                                            int y_start2 = this.temp_ys[0] - radius_start;
                                            for (point_index = 1; point_index < count_primitive_points; ++point_index) {
                                                int count_line_points = 0;
                                                int diameter_end;
                                                if (!is_absolute_thickness) {
                                                    diameter_end = (int)(primitive.point_diameter / this.point_scale[primitive.points[point_index]]);
                                                }
                                                else {
                                                    diameter_end = (int)primitive.point_diameter;
                                                }
                                                --diameter_end;
                                                final int radius_end = diameter_end / 2;
                                                final int x_end2 = this.temp_xs[point_index] - radius_end;
                                                final int y_end2 = this.temp_ys[point_index] - radius_end;
                                                if (null == primitive.edge_flags || primitive.edge_flags[point_index - 1]) {
                                                    g.drawLine(x_start2, y_start2, x_end2, y_end2);
                                                    if (0 < diameter_start || 0 < diameter_end) {
                                                        if (0 >= diameter_start) {
                                                            this.temp_line_xs[0] = x_start2;
                                                            this.temp_line_ys[0] = y_start2;
                                                            count_line_points = 1;
                                                        }
                                                        if (y_start2 < y_end2) {
                                                            if (x_start2 < x_end2) {
                                                                if (0 < diameter_start) {
                                                                    this.temp_line_xs[0] = x_start2;
                                                                    this.temp_line_ys[0] = y_start2 + diameter_start;
                                                                    this.temp_line_xs[1] = x_start2;
                                                                    this.temp_line_ys[1] = y_start2;
                                                                    this.temp_line_xs[2] = x_start2 + diameter_start;
                                                                    this.temp_line_ys[2] = y_start2;
                                                                    count_line_points = 3;
                                                                }
                                                                if (0 < diameter_end) {
                                                                    this.temp_line_xs[count_line_points] = x_end2 + diameter_end;
                                                                    this.temp_line_ys[count_line_points] = y_end2;
                                                                    this.temp_line_xs[count_line_points + 1] = x_end2 + diameter_end;
                                                                    this.temp_line_ys[count_line_points + 1] = y_end2 + diameter_end;
                                                                    this.temp_line_xs[count_line_points + 2] = x_end2;
                                                                    this.temp_line_ys[count_line_points + 2] = y_end2 + diameter_end;
                                                                    count_line_points += 3;
                                                                }
                                                            }
                                                            else {
                                                                if (0 < diameter_start) {
                                                                    this.temp_line_xs[0] = x_start2;
                                                                    this.temp_line_ys[0] = y_start2;
                                                                    this.temp_line_xs[1] = x_start2 + diameter_start;
                                                                    this.temp_line_ys[1] = y_start2;
                                                                    this.temp_line_xs[2] = x_start2 + diameter_start;
                                                                    this.temp_line_ys[2] = y_start2 + diameter_start;
                                                                    count_line_points = 3;
                                                                }
                                                                if (0 < diameter_end) {
                                                                    this.temp_line_xs[count_line_points] = x_end2 + diameter_end;
                                                                    this.temp_line_ys[count_line_points] = y_end2 + diameter_end;
                                                                    this.temp_line_xs[count_line_points + 1] = x_end2;
                                                                    this.temp_line_ys[count_line_points + 1] = y_end2 + diameter_end;
                                                                    this.temp_line_xs[count_line_points + 2] = x_end2;
                                                                    this.temp_line_ys[count_line_points + 2] = y_end2;
                                                                    count_line_points += 3;
                                                                }
                                                            }
                                                        }
                                                        else if (x_start2 > x_end2) {
                                                            if (0 < diameter_start) {
                                                                this.temp_line_xs[0] = x_start2 + diameter_start;
                                                                this.temp_line_ys[0] = y_start2;
                                                                this.temp_line_xs[1] = x_start2 + diameter_start;
                                                                this.temp_line_ys[1] = y_start2 + diameter_start;
                                                                this.temp_line_xs[2] = x_start2;
                                                                this.temp_line_ys[2] = y_start2 + diameter_start;
                                                                count_line_points = 3;
                                                            }
                                                            if (0 < diameter_end) {
                                                                this.temp_line_xs[count_line_points] = x_end2;
                                                                this.temp_line_ys[count_line_points] = y_end2 + diameter_end;
                                                                this.temp_line_xs[count_line_points + 1] = x_end2;
                                                                this.temp_line_ys[count_line_points + 1] = y_end2;
                                                                this.temp_line_xs[count_line_points + 2] = x_end2 + diameter_end;
                                                                this.temp_line_ys[count_line_points + 2] = y_end2;
                                                                count_line_points += 3;
                                                            }
                                                        }
                                                        else {
                                                            if (0 < diameter_start) {
                                                                this.temp_line_xs[0] = x_start2 + diameter_start;
                                                                this.temp_line_ys[0] = y_start2 + diameter_start;
                                                                this.temp_line_xs[1] = x_start2;
                                                                this.temp_line_ys[1] = y_start2 + diameter_start;
                                                                this.temp_line_xs[2] = x_start2;
                                                                this.temp_line_ys[2] = y_start2;
                                                                count_line_points = 3;
                                                            }
                                                            if (0 < diameter_end) {
                                                                this.temp_line_xs[count_line_points] = x_end2;
                                                                this.temp_line_ys[count_line_points] = y_end2;
                                                                this.temp_line_xs[count_line_points + 1] = x_end2 + diameter_end;
                                                                this.temp_line_ys[count_line_points + 1] = y_end2;
                                                                this.temp_line_xs[count_line_points + 2] = x_end2 + diameter_end;
                                                                this.temp_line_ys[count_line_points + 2] = y_end2 + diameter_end;
                                                                count_line_points += 3;
                                                            }
                                                        }
                                                        if (0 >= diameter_end) {
                                                            this.temp_line_xs[count_line_points] = x_end2;
                                                            this.temp_line_ys[count_line_points] = y_end2;
                                                            ++count_line_points;
                                                        }
                                                        g.fillPolygon(this.temp_line_xs, this.temp_line_ys, count_line_points);
                                                    }
                                                }
                                                diameter_start = diameter_end;
                                                radius_start = radius_end;
                                                x_start2 = x_end2;
                                                y_start2 = y_end2;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!is_visible || !polygon.is_filled || polygon.edge_primitives == null || polygon.edge_primitives.size() <= edge_index + 1) {
                                primitive = null;
                            }
                            else {
                                ++edge_index;
                                primitive = polygon.edge_primitives.elementAt(edge_index);
                            }
                        }
                    }
                }
            }
        }
    }
}
