import java.awt.Event;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.awt.Toolkit;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Live extends Applet implements Runnable
{
    Evaluator evaluator;
    Graphics3D ps3D;
    Graphics3D painted_ps3D;
    Vector frames;
    int frames_count;
    int current_frame_index;
    double spinning_display_time;
    int animation_direction;
    double animate_min;
    double animate_max;
    double animate_step;
    int animate_variable_index;
    double animate_variable_value;
    double valid_animate_variable_value;
    boolean is_new_animate_frame;
    long animated_time;
    long spinned_time;
    boolean is_animating;
    boolean is_spinning;
    double spin_x;
    double spin_y;
    Thread painter;
    Image front_image_buffer;
    Graphics front_graphics_buffer;
    Image back_image_buffer;
    Graphics back_graphics_buffer;
    Image second_image_buffer;
    Graphics second_graphics_buffer;
    Image preload_bg_image;
    Image bg_image;
    Image bg_rotated_image;
    Image bg_right_image;
    Image bg_rotated_right_image;
    int bg_width;
    int bg_height;
    boolean bg_is_fixed;
    boolean bg_is_cylindrical;
    boolean bg_is_spherical;
    Color background_color;
    Color foreground_color;
    Color point_edge_color;
    int min_height_width;
    Primitive3D active_primitive;
    Primitive3D dragged_point;
    int text_x;
    int text_y;
    int applet_width;
    int applet_height;
    boolean is_dragging;
    boolean is_dragging_point;
    int visible_faces;
    boolean show_faces;
    int mouse_drag_action;
    AudioClip audio_enter;
    AudioClip audio_loop_enter;
    AudioClip audio_first_frame;
    static String empty_string;
    static String enter_plain_string;
    static String enter_drag_string;
    static String enter_drag_animated_string;
    static String enter_animated_string;
    static String enter_init_string;
    static String syntax_error_string;
    static String url_error_string;
    static String target_string;
    int down_mouse_x;
    int down_mouse_y;
    double down_mouse_point_x;
    double down_mouse_point_y;
    double down_mouse_point_z;
    long down_time;
    double last_x;
    double last_y;
    long last_drag_time;
    double llast_x;
    double llast_y;
    long llast_drag_time;
    boolean is_control_down;
    boolean is_meta_down;
    boolean is_shift_down;
    boolean is_mouse_here;
    double length_view_point_factor;
    double magnification_factor;
    int cut_primitives_count;
    Quaternion rotation;
    int bg_x_offset;
    int bg_y_offset;
    boolean is_stereo;
    double stereo_distance;
    double preceding_length_view_point_factor;
    double preceding_magnification_factor;
    int preceding_cut_primitives_count;
    Quaternion preceding_rotation;
    int preceding_bg_x_offset;
    int preceding_bg_y_offset;
    double preceding_stereo_distance;
    int preceding_frame_index;
    double[] center_offset;
    double[] sizes_scales;
    Quaternion home_rotation;
    int home_bg_x_offset;
    int home_bg_y_offset;
    double home_magnification_factor;
    double home_length_view_point_factor;
    boolean home_is_stereo;
    double home_stereo_distance;
    int home_cut_primitives_count;
    boolean is_new_dragging;
    boolean is_enforced_reprojection;
    boolean initialized;
    boolean is_stopped;
    static String double_chars;
    
    public Live() {
        this.frames_count = 0;
        this.spinning_display_time = 0.05;
        this.animation_direction = 1;
        this.animate_min = 0.0;
        this.animate_max = 0.0;
        this.animate_step = 1.0;
        this.animate_variable_index = -1;
        this.animate_variable_value = 0.0;
        this.valid_animate_variable_value = 0.0;
        this.is_new_animate_frame = true;
        this.animated_time = 0L;
        this.spinned_time = 0L;
        this.is_animating = true;
        this.is_spinning = false;
        this.spin_x = 0.0;
        this.spin_y = 0.0;
        this.painter = null;
        this.min_height_width = 100;
        this.active_primitive = null;
        this.dragged_point = null;
        this.text_x = 10;
        this.text_y = 0;
        this.applet_width = this.getSize().width;
        this.applet_height = this.getSize().height;
        this.is_dragging = false;
        this.is_dragging_point = false;
        this.show_faces = true;
        this.mouse_drag_action = 1;
        this.audio_enter = null;
        this.audio_loop_enter = null;
        this.audio_first_frame = null;
        this.down_mouse_x = 50;
        this.down_mouse_y = 50;
        this.down_mouse_point_x = 0.0;
        this.down_mouse_point_y = 0.0;
        this.down_mouse_point_z = 0.0;
        this.down_time = 0L;
        this.last_x = 50.0;
        this.last_y = 50.0;
        this.last_drag_time = 0L;
        this.llast_x = 50.0;
        this.llast_y = 50.0;
        this.llast_drag_time = 0L;
        this.is_control_down = false;
        this.is_meta_down = false;
        this.is_shift_down = false;
        this.is_mouse_here = false;
        this.length_view_point_factor = 1.0;
        this.magnification_factor = 1.0;
        this.cut_primitives_count = 0;
        this.rotation = new Quaternion(1.0, 0.0, 0.0, 0.0);
        this.bg_x_offset = 0;
        this.bg_y_offset = 0;
        this.stereo_distance = 0.05;
        this.preceding_length_view_point_factor = 1.0;
        this.preceding_magnification_factor = 1.0;
        this.preceding_cut_primitives_count = 0;
        this.preceding_rotation = this.rotation;
        this.preceding_bg_x_offset = 0;
        this.preceding_bg_y_offset = 0;
        this.preceding_stereo_distance = 0.05;
        this.preceding_frame_index = 0;
        this.center_offset = new double[] { 0.0, 0.0, 0.0 };
        this.sizes_scales = new double[] { 1.0, 1.0, 1.0 };
        this.home_rotation = this.rotation;
        this.home_bg_x_offset = this.bg_x_offset;
        this.home_bg_y_offset = this.bg_y_offset;
        this.home_magnification_factor = this.magnification_factor;
        this.home_length_view_point_factor = this.length_view_point_factor;
        this.home_is_stereo = this.is_stereo;
        this.home_stereo_distance = this.stereo_distance;
        this.home_cut_primitives_count = this.cut_primitives_count;
        this.is_new_dragging = false;
        this.is_enforced_reprojection = false;
        this.initialized = false;
        this.is_stopped = false;
    }
    
    public void init() {
        this.is_stopped = false;
    }
    
    public void initialize() {
        if (!this.initialized) {
            System.out.println("LiveGraphics3D 1.80 by Martin_Kraus_Germany@yahoo.com");
            this.text_y = 0;
            this.ps3D = null;
            final InputStream logo_stream = this.getClass().getResourceAsStream("liveinit.gif");
            Image logo_image = null;
            if (null != logo_stream) {
                final Toolkit toolkit = Toolkit.getDefaultToolkit();
                try {
                    final int logo_size = 5000;
                    byte[] logo_bytes = new byte[logo_size];
                    logo_stream.read(logo_bytes, 0, logo_size);
                    logo_image = toolkit.createImage(logo_bytes);
                    logo_bytes = null;
                }
                catch (IOException ex) {}
                if (null != logo_image) {
                    MediaTracker tracker = new MediaTracker(this);
                    tracker.addImage(logo_image, 0);
                    try {
                        tracker.waitForAll();
                    }
                    catch (InterruptedException ex2) {}
                    tracker = null;
                }
            }
            String string_bgcolor = this.getParameter("BGCOLOR");
            if (null != string_bgcolor) {
                string_bgcolor = string_bgcolor.trim();
                if ('#' == string_bgcolor.charAt(0)) {
                    string_bgcolor = string_bgcolor.substring(1);
                }
                this.background_color = new Color(Integer.parseInt(string_bgcolor, 16));
                if (384 < this.background_color.getRed() + this.background_color.getBlue() + this.background_color.getGreen()) {
                    this.foreground_color = Color.black;
                }
                else {
                    this.foreground_color = Color.white;
                }
            }
            else {
                this.background_color = Color.white;
                this.foreground_color = Color.black;
            }
            this.set_applet_size();
            this.front_image_buffer = this.createImage(this.applet_width, this.applet_height);
            this.front_graphics_buffer = this.front_image_buffer.getGraphics();
            this.back_image_buffer = this.createImage(this.applet_width, this.applet_height);
            this.back_graphics_buffer = this.back_image_buffer.getGraphics();
            if (null == this.front_image_buffer || null == this.back_image_buffer || null == this.front_graphics_buffer || null == this.back_graphics_buffer) {
                System.out.println("LiveGraphics3D error: cannot create graphics buffers, applet stopped.");
                return;
            }
            this.front_graphics_buffer.setFont(new Font("Dialog", 0, 10));
            if (null == logo_image) {
                this.front_graphics_buffer.setColor(this.background_color);
                this.front_graphics_buffer.fillRect(0, 0, this.applet_width, this.applet_height);
                this.print_message(this.front_graphics_buffer, "LiveGraphics3D 1.80", false);
                this.print_message(this.front_graphics_buffer, "Initializing. Please wait.", false);
            }
            else {
                this.front_graphics_buffer.setColor(Color.white);
                this.front_graphics_buffer.fillRect(0, 0, this.applet_width, this.applet_height);
                this.front_graphics_buffer.drawImage(logo_image, (this.applet_width - logo_image.getWidth(null)) / 2, (this.applet_height - logo_image.getHeight(null)) / 2, null);
                logo_image = null;
            }
            this.repaint();
            final String string_preload_background = this.getParameter("PRELOAD_BACKGROUND");
            if (null != string_preload_background) {
                try {
                    this.preload_bg_image = this.getImage(new URL(this.getDocumentBase(), string_preload_background));
                    if (null != this.preload_bg_image) {
                        MediaTracker tracker2 = new MediaTracker(this);
                        tracker2.addImage(this.preload_bg_image, 0);
                        try {
                            tracker2.waitForAll();
                        }
                        catch (InterruptedException ex3) {}
                        tracker2 = null;
                        this.front_graphics_buffer.drawImage(this.preload_bg_image, 0, 0, this.applet_width, this.applet_height, null);
                        this.repaint();
                    }
                }
                catch (MalformedURLException ex4) {}
            }
            final String string_audio_enter = this.getParameter("AUDIO_ENTER");
            if (null != string_audio_enter) {
                this.audio_enter = this.getAudioClip(this.getDocumentBase(), string_audio_enter);
            }
            final String string_audio_loop_enter = this.getParameter("AUDIO_LOOP_ENTER");
            if (null != string_audio_loop_enter) {
                this.audio_loop_enter = this.getAudioClip(this.getDocumentBase(), string_audio_loop_enter);
            }
            final String string_audio_first_frame = this.getParameter("AUDIO_FIRST_FRAME");
            if (null != string_audio_first_frame) {
                this.audio_first_frame = this.getAudioClip(this.getDocumentBase(), string_audio_first_frame);
            }
            String string_pecolor = this.getParameter("POINT_EDGE_COLOR");
            if (null != string_pecolor) {
                string_pecolor = string_pecolor.trim();
                if ('#' == string_pecolor.charAt(0)) {
                    string_pecolor = string_pecolor.substring(1);
                }
                this.point_edge_color = new Color(Integer.parseInt(string_pecolor, 16));
            }
            else {
                this.point_edge_color = null;
            }
            double initial_phi = 0.0;
            double initial_theta = 0.0;
            String string_initial_rotation = this.getParameter("INITIAL_ROTATION");
            if (null != string_initial_rotation && !Character.isDigit(string_initial_rotation.charAt(0)) && '-' != string_initial_rotation.charAt(0)) {
                final String url = this.getDocumentBase().toString();
                if (null == url) {
                    string_initial_rotation = null;
                }
                else {
                    String label = string_initial_rotation.concat("=");
                    int label_index;
                    if ((label_index = url.indexOf(label)) >= 0) {
                        string_initial_rotation = url.substring(label_index + string_initial_rotation.length() + 1);
                    }
                    else {
                        label = string_initial_rotation.concat("%3D");
                        if ((label_index = url.indexOf(label)) >= 0) {
                            string_initial_rotation = url.substring(label_index + string_initial_rotation.length() + 3);
                        }
                        else {
                            string_initial_rotation = null;
                        }
                    }
                }
            }
            if (null != string_initial_rotation) {
                final int label_index = string_initial_rotation.indexOf(",");
                if (label_index < 0) {
                    initial_phi = this.get_double_value(string_initial_rotation);
                }
                else {
                    initial_phi = this.get_double_value(string_initial_rotation.substring(0, label_index));
                }
                if (label_index > 0) {
                    initial_theta = this.get_double_value(string_initial_rotation.substring(label_index + 1));
                }
            }
            final String string_spin_x = this.getParameter("SPIN_X");
            this.spin_x = 0.0;
            if (null != string_spin_x) {
                this.spin_x = this.get_double_value(string_spin_x);
            }
            final String string_spin_y = this.getParameter("SPIN_Y");
            this.spin_y = 0.0;
            if (null != string_spin_y) {
                this.spin_y = this.get_double_value(string_spin_y);
            }
            if (this.spin_x != 0.0 || this.spin_y != 0.0) {
                this.is_spinning = true;
            }
            final String string_visible_faces = this.getParameter("VISIBLE_FACES");
            this.visible_faces = 3;
            if (null != string_visible_faces) {
                if (string_visible_faces.equalsIgnoreCase("FRONT")) {
                    this.visible_faces = 1;
                }
                else if (string_visible_faces.equalsIgnoreCase("BACK")) {
                    this.visible_faces = 2;
                }
            }
            final String string_stripped_primitives = this.getParameter("STRIPPED_PRIMITIVES");
            if (null != string_stripped_primitives) {
                this.cut_primitives_count = (int)this.get_double_value(string_stripped_primitives);
                if (this.cut_primitives_count < 0) {
                    this.cut_primitives_count = 0;
                }
            }
            else {
                this.cut_primitives_count = 0;
            }
            final String string_mouse_drag_action = this.getParameter("MOUSE_DRAG_ACTION");
            this.mouse_drag_action = 1;
            if (null != string_mouse_drag_action && string_mouse_drag_action.equalsIgnoreCase("NONE")) {
                this.mouse_drag_action = 0;
            }
            final String independent_variables = this.getParameter("INDEPENDENT_VARIABLES");
            this.evaluator = null;
            if (null != independent_variables) {
                this.evaluator = new Evaluator();
                Parser parser = new Parser(independent_variables, this.evaluator);
                if (!parser.scan_independent_variables()) {
                    this.print_message(this.front_graphics_buffer, "LiveGraphics3D error in INDEPENDENT_VARIABLES.");
                    this.print_syntax_error(this.front_graphics_buffer, parser);
                    this.evaluator = null;
                    this.repaint();
                }
                parser = null;
            }
            final String dependent_variables = this.getParameter("DEPENDENT_VARIABLES");
            if (null != dependent_variables) {
                if (null == this.evaluator) {
                    this.evaluator = new Evaluator();
                }
                Parser parser2 = new Parser(dependent_variables, this.evaluator);
                if (!parser2.scan_dependent_variables()) {
                    this.print_message(this.front_graphics_buffer, "LiveGraphics3D error in DEPENDENT_VARIABLES.");
                    this.print_syntax_error(this.front_graphics_buffer, parser2);
                    this.evaluator = null;
                    this.repaint();
                }
                else {
                    this.evaluator.prepareVariables();
                    if (!this.evaluator.evaluate()) {
                        this.print_message(this.front_graphics_buffer, "LiveGraphics3D evaluation error in DEPENDENT_VARIABLES.");
                        this.print_syntax_error(this.front_graphics_buffer, parser2);
                        this.evaluator = null;
                        this.repaint();
                    }
                }
                parser2 = null;
            }
            else if (null != this.evaluator) {
                this.evaluator.prepareVariables();
            }
            String input_text = this.getParameter("INPUT");
            if (null == input_text) {
                final String string_input_archive = this.getParameter("INPUT_ARCHIVE");
                final String string_input_file = this.getParameter("INPUT_FILE");
                if (null != string_input_file) {
                    try {
                        InputStream input_stream = null;
                        if (null != string_input_archive) {
                            try {
                                ZipInputStream archive_stream;
                                ZipEntry archive_entry;
                                for (archive_stream = new ZipInputStream(new URL(this.getDocumentBase(), string_input_archive).openStream()), archive_entry = null, archive_entry = archive_stream.getNextEntry(); null != archive_entry && !string_input_file.equals(archive_entry.getName()); archive_entry = archive_stream.getNextEntry()) {}
                                if (null != archive_entry) {
                                    input_stream = archive_stream;
                                }
                            }
                            catch (IOException ex5) {}
                        }
                        if (null == input_stream) {
                            input_stream = new URL(this.getDocumentBase(), string_input_file).openStream();
                        }
                        final int buffer_length = 20000;
                        byte[] buffer = new byte[buffer_length];
                        int read_bytes = buffer_length;
                        StringBuffer string_buffer = new StringBuffer();
                        while (-1 < read_bytes) {
                            read_bytes = input_stream.read(buffer, 0, buffer_length);
                            if (-1 < read_bytes) {
                                string_buffer.append(new String(buffer, 0, read_bytes));
                            }
                        }
                        buffer = null;
                        input_text = string_buffer.toString();
                        string_buffer = null;
                    }
                    catch (IOException e) {
                        this.print_message(this.front_graphics_buffer, "LiveGraphics3D error:");
                        this.print_message(this.front_graphics_buffer, "Can't read " + string_input_file);
                    }
                }
            }
            Parser parser3 = new Parser(input_text, this.evaluator);
            if (null != parser3.text && parser3.scan_animation() && null != parser3.scanned_frames && 0 < parser3.scanned_frames.size()) {
                this.frames = parser3.scanned_frames;
                this.animation_direction = parser3.scanned_AnimationDirection;
                this.animate_min = parser3.scanned_Animate_min;
                this.animate_variable_value = this.animate_min;
                this.valid_animate_variable_value = this.animate_variable_value;
                this.is_new_animate_frame = true;
                this.animate_max = parser3.scanned_Animate_max;
                this.animate_step = parser3.scanned_Animate_step;
                this.animate_variable_index = parser3.scanned_Animate_variable;
                if (this.frames.size() > 1 || this.animate_variable_index < 0 || null == this.evaluator) {
                    this.frames_count = this.frames.size();
                    this.animate_variable_index = -1;
                }
                else {
                    this.frames_count = (int)Math.floor(1.000000000001 + (this.animate_max - this.animate_min) / this.animate_step);
                    if (this.frames_count < 1) {
                        this.frames_count = 1;
                    }
                }
                if (this.frames_count <= 1) {
                    this.is_animating = false;
                }
                if (null != this.evaluator && this.animate_variable_index >= 0) {
                    this.valid_animate_variable_value = this.evaluator.getVariableValue(this.animate_variable_index);
                }
                parser3 = null;
                String string_background = this.getParameter("BACKGROUND");
                this.bg_is_fixed = false;
                this.bg_is_cylindrical = false;
                this.bg_is_spherical = false;
                if (null != string_background) {
                    this.bg_is_fixed = true;
                }
                else {
                    string_background = this.getParameter("CYLINDRICAL_BACKGROUND");
                    if (null != string_background) {
                        this.bg_is_cylindrical = true;
                    }
                    else {
                        string_background = this.getParameter("SPHERICAL_BACKGROUND");
                        if (null != string_background) {
                            this.bg_is_spherical = true;
                        }
                    }
                }
                this.bg_image = null;
                this.bg_right_image = null;
                MediaTracker tracker3 = null;
                if (null != string_background) {
                    tracker3 = new MediaTracker(this);
                    try {
                        this.bg_image = this.getImage(new URL(this.getDocumentBase(), string_background));
                        if (null != this.bg_image) {
                            tracker3.addImage(this.bg_image, 0);
                        }
                    }
                    catch (MalformedURLException e2) {
                        this.print_message(this.front_graphics_buffer, "LiveGraphics3D error:");
                        this.print_message(this.front_graphics_buffer, "bad URL " + string_background);
                        this.bg_image = null;
                        tracker3 = null;
                        this.bg_is_fixed = false;
                        this.bg_is_cylindrical = false;
                        this.bg_is_spherical = false;
                    }
                }
                if (null != this.bg_image) {
                    string_background = this.getParameter("RIGHT_BACKGROUND");
                    if (null != string_background) {
                        try {
                            this.bg_right_image = this.getImage(new URL(this.getDocumentBase(), string_background));
                            if (null != this.bg_right_image) {
                                tracker3.addImage(this.bg_right_image, 0);
                            }
                        }
                        catch (MalformedURLException ex6) {}
                    }
                }
                final String string_magnification = this.getParameter("MAGNIFICATION");
                if (null != string_magnification) {
                    this.magnification_factor = Math.abs(this.get_double_value(string_magnification));
                    if (this.magnification_factor == 0.0) {
                        this.magnification_factor = 1.0;
                    }
                }
                else {
                    this.magnification_factor = 1.0;
                }
                this.preceding_magnification_factor = this.magnification_factor;
                final String string_stereo_distance = this.getParameter("STEREO_DISTANCE");
                if (null != string_stereo_distance) {
                    this.is_stereo = true;
                    this.stereo_distance = this.get_double_value(string_stereo_distance);
                }
                else {
                    this.stereo_distance = 0.05;
                    this.is_stereo = false;
                }
                this.preceding_stereo_distance = this.stereo_distance;
                this.center_offset[0] = 0.0;
                this.center_offset[1] = 0.0;
                this.center_offset[2] = 0.0;
                this.sizes_scales[0] = 1.0;
                this.sizes_scales[1] = 1.0;
                this.sizes_scales[2] = 1.0;
                for (int frame_index = 0; frame_index < this.frames.size(); ++frame_index) {
                    (this.ps3D = this.frames.elementAt(frame_index)).setGlobalParameters(this.applet_width, this.applet_height, this.background_color, this.point_edge_color);
                    this.ps3D.preparePrimitives(this.back_graphics_buffer, this.evaluator, this.center_offset, this.sizes_scales);
                }
                this.current_frame_index = 0;
                if (null != this.bg_image) {
                    if (null != tracker3) {
                        try {
                            tracker3.waitForAll();
                        }
                        catch (InterruptedException ex7) {}
                    }
                    this.bg_width = this.bg_image.getWidth(null);
                    this.bg_height = this.bg_image.getHeight(null);
                    if (this.bg_width < this.applet_width || this.bg_height < this.applet_height) {
                        this.bg_is_fixed = true;
                        this.bg_is_cylindrical = false;
                        this.bg_is_spherical = false;
                    }
                    this.bg_x_offset = 0;
                    this.bg_y_offset = 0;
                    if (this.bg_is_spherical) {
                        this.bg_x_offset = (int)(-initial_phi * this.bg_width / 2.0 / 180.0);
                        this.bg_y_offset = (int)(-initial_theta * this.bg_height / 180.0);
                        this.bg_rotated_image = this.createRotatedImage(this.bg_image);
                        if (null != this.bg_right_image) {
                            this.bg_rotated_right_image = this.createRotatedImage(this.bg_right_image);
                        }
                    }
                    else if (this.bg_is_cylindrical) {
                        this.bg_x_offset = (int)(-initial_phi * this.bg_width / 2.0 / 180.0);
                        this.bg_y_offset = (int)(-initial_theta * this.bg_width / 2.0 / 180.0);
                    }
                    if (this.bg_is_cylindrical || this.bg_is_spherical) {
                        this.rotation = this.adjust_bg_offsets();
                    }
                    if (null == this.bg_right_image) {
                        this.bg_right_image = this.bg_image;
                        this.bg_rotated_right_image = this.bg_rotated_image;
                    }
                }
                tracker3 = null;
                if (!this.bg_is_cylindrical && !this.bg_is_spherical) {
                    (this.rotation = new Quaternion(initial_theta * 3.141592653589793 / 180.0, 1.0, 0.0, 0.0, false)).multiply(new Quaternion(initial_phi * 3.141592653589793 / 180.0, 0.0, 1.0, 0.0, false));
                }
                this.preceding_rotation = this.rotation;
                (this.ps3D = this.frames.elementAt(this.current_frame_index)).setPerspective(this.ps3D.initial_length_view_point * this.length_view_point_factor, this.ps3D.initial_magnification * this.magnification_factor, this.is_stereo, this.stereo_distance);
                this.ps3D.setCutPrimitivesCount(this.cut_primitives_count, this.visible_faces, this.show_faces);
                this.ps3D.setQuaternion(this.rotation.product(this.ps3D.initial_rotation));
                this.ps3D.projectPoints(false);
                this.home_rotation = this.rotation;
                this.home_bg_x_offset = this.bg_x_offset;
                this.home_bg_y_offset = this.bg_y_offset;
                this.home_magnification_factor = this.magnification_factor;
                this.home_length_view_point_factor = this.length_view_point_factor;
                this.home_is_stereo = this.is_stereo;
                this.home_stereo_distance = this.stereo_distance;
                this.home_cut_primitives_count = this.cut_primitives_count;
                this.paintGraphics3D();
            }
            else {
                if (null == parser3.text) {
                    this.print_message(this.front_graphics_buffer, "LiveGraphics3D error: applet parameter");
                    this.print_message(this.front_graphics_buffer, "\"INPUT\" or \"INPUT_FILE\" missing.");
                }
                else {
                    this.print_syntax_error(this.front_graphics_buffer, parser3);
                }
                this.ps3D = null;
            }
            parser3 = null;
            this.preload_bg_image = null;
            System.gc();
            if (null == this.ps3D) {
                this.print_message(this.front_graphics_buffer, "LiveGraphics3D initialization failed.", false);
                this.repaint();
            }
            else {
                this.initialized = true;
            }
        }
    }
    
    public boolean isInitialized() {
        return this.initialized;
    }
    
    public double getVersionNumber() {
        return 1.8;
    }
    
    public boolean setGraphics3D(final String input_text) {
        Parser parser = new Parser(input_text, this.evaluator);
        this.initialized = false;
        this.text_y = 0;
        if (null != parser.text && parser.scan_animation() && null != parser.scanned_frames && 0 < parser.scanned_frames.size()) {
            this.frames = parser.scanned_frames;
            this.animation_direction = parser.scanned_AnimationDirection;
            this.animate_min = parser.scanned_Animate_min;
            this.animate_variable_value = this.animate_min;
            this.valid_animate_variable_value = this.animate_variable_value;
            this.is_new_animate_frame = true;
            this.animate_max = parser.scanned_Animate_max;
            this.animate_step = parser.scanned_Animate_step;
            this.animate_variable_index = parser.scanned_Animate_variable;
            if (this.frames.size() > 1 || this.animate_variable_index < 0 || null == this.evaluator) {
                this.frames_count = this.frames.size();
                this.animate_variable_index = -1;
            }
            else {
                this.frames_count = (int)Math.floor(1.000000000001 + (this.animate_max - this.animate_min) / this.animate_step);
                if (this.frames_count < 1) {
                    this.frames_count = 1;
                }
            }
            if (this.frames_count <= 1) {
                this.is_animating = false;
            }
            if (null != this.evaluator && this.animate_variable_index >= 0) {
                this.valid_animate_variable_value = this.evaluator.getVariableValue(this.animate_variable_index);
            }
            parser = null;
            this.center_offset[0] = 0.0;
            this.center_offset[1] = 0.0;
            this.center_offset[2] = 0.0;
            this.sizes_scales[0] = 1.0;
            this.sizes_scales[1] = 1.0;
            this.sizes_scales[2] = 1.0;
            for (int frame_index = 0; frame_index < this.frames.size(); ++frame_index) {
                (this.ps3D = this.frames.elementAt(frame_index)).setGlobalParameters(this.applet_width, this.applet_height, this.background_color, this.point_edge_color);
                this.ps3D.preparePrimitives(this.back_graphics_buffer, this.evaluator, this.center_offset, this.sizes_scales);
            }
            this.current_frame_index = 0;
            (this.ps3D = this.frames.elementAt(this.current_frame_index)).setPerspective(this.ps3D.initial_length_view_point * this.length_view_point_factor, this.ps3D.initial_magnification * this.magnification_factor, this.is_stereo, this.stereo_distance);
            this.ps3D.setCutPrimitivesCount(this.cut_primitives_count, this.visible_faces, this.show_faces);
            this.ps3D.setQuaternion(this.rotation.product(this.ps3D.initial_rotation));
            this.ps3D.projectPoints(false);
            this.paintGraphics3D();
            System.gc();
            return this.initialized = true;
        }
        if (null == parser.text) {
            this.print_message(this.front_graphics_buffer, "LiveGraphics3D error: not enough memory");
        }
        else {
            this.print_syntax_error(this.front_graphics_buffer, parser);
        }
        this.ps3D = null;
        parser = null;
        this.preload_bg_image = null;
        System.gc();
        this.repaint();
        this.initialized = true;
        return false;
    }
    
    public boolean setInputFile(final String string_input_file, final String string_input_archive) {
        String input_text = null;
        if (null != string_input_file) {
            try {
                InputStream input_stream = null;
                if (null != string_input_archive) {
                    try {
                        ZipInputStream archive_stream;
                        ZipEntry archive_entry;
                        for (archive_stream = new ZipInputStream(new URL(this.getDocumentBase(), string_input_archive).openStream()), archive_entry = null, archive_entry = archive_stream.getNextEntry(); null != archive_entry && !string_input_file.equals(archive_entry.getName()); archive_entry = archive_stream.getNextEntry()) {}
                        if (null != archive_entry) {
                            input_stream = archive_stream;
                        }
                    }
                    catch (IOException ex) {}
                }
                if (null == input_stream) {
                    input_stream = new URL(this.getDocumentBase(), string_input_file).openStream();
                }
                final int buffer_length = 20000;
                byte[] buffer = new byte[buffer_length];
                int read_bytes = buffer_length;
                StringBuffer string_buffer = new StringBuffer();
                while (-1 < read_bytes) {
                    read_bytes = input_stream.read(buffer, 0, buffer_length);
                    if (-1 < read_bytes) {
                        string_buffer.append(new String(buffer, 0, read_bytes));
                    }
                }
                buffer = null;
                input_text = string_buffer.toString();
                string_buffer = null;
            }
            catch (IOException e) {
                this.print_message(this.front_graphics_buffer, "LiveGraphics3D error in setInputFile:");
                this.print_message(this.front_graphics_buffer, "Can't read " + string_input_file);
            }
        }
        if (null == input_text) {
            this.print_message(this.front_graphics_buffer, "LiveGraphics3D error in setInputFile:");
            this.print_message(this.front_graphics_buffer, "No input file; function aborted.");
            return false;
        }
        return this.setGraphics3D(input_text);
    }
    
    public boolean setMagnification(final double new_magnification) {
        if (new_magnification > 0.0) {
            this.magnification_factor = new_magnification;
            return this.is_new_dragging = true;
        }
        return false;
    }
    
    public double getMagnification() {
        return this.magnification_factor;
    }
    
    public boolean setStereoDistance(final double new_stereo_distance) {
        if (new_stereo_distance == 0.0) {
            this.is_stereo = false;
        }
        else {
            this.is_stereo = true;
        }
        this.stereo_distance = new_stereo_distance;
        return this.is_new_dragging = true;
    }
    
    public double getStereoDistance() {
        if (this.is_stereo) {
            return this.stereo_distance;
        }
        return 0.0;
    }
    
    public boolean setStrippedPrimitives(final int new_stripped_primitives_count) {
        this.cut_primitives_count = new_stripped_primitives_count;
        return this.is_new_dragging = true;
    }
    
    public int getStrippedPrimitives() {
        return this.cut_primitives_count;
    }
    
    public boolean setSpin(final double new_spin_x, final double new_spin_y) {
        this.spin_x = new_spin_x;
        this.spin_y = new_spin_y;
        if (this.spin_x != 0.0 || this.spin_y != 0.0) {
            this.is_spinning = true;
        }
        else {
            this.is_spinning = false;
        }
        return this.is_new_dragging = true;
    }
    
    public double getSpinX() {
        return this.spin_x;
    }
    
    public double getSpinY() {
        return this.spin_y;
    }
    
    public boolean setViewPointAndViewVertical(final double vpx, final double vpy, final double vpz, final double vvx, final double vvy, final double vvz) {
        if (null == this.ps3D) {
            return false;
        }
        final double[] view_point = { vpx, vpy, vpz };
        final double[] view_vertical = { vvx, vvy, vvz };
        this.ps3D.set_rotation_from_view_parameters(view_point, view_vertical);
        this.rotation = new Quaternion(0.0, 1.0, 0.0, 0.0, false);
        this.length_view_point_factor = 1.0;
        if (this.is_spinning) {
            this.preceding_rotation = this.rotation;
            this.last_x = this.down_mouse_x;
            this.last_y = this.down_mouse_y;
        }
        return this.is_new_dragging = true;
    }
    
    public boolean rotateToViewPointAndViewVertical(final double vpx, final double vpy, final double vpz, final double vvx, final double vvy, final double vvz) {
        if (null == this.ps3D) {
            return false;
        }
        final double[] view_point = { vpx, vpy, vpz };
        final double[] view_vertical = { vvx, vvy, vvz };
        final Quaternion orig_rotation = this.ps3D.rotation;
        final Quaternion orig_initial_rotation = this.ps3D.initial_rotation;
        final double orig_initial_length_view_point = this.ps3D.initial_length_view_point;
        this.ps3D.set_rotation_from_view_parameters(view_point, view_vertical);
        this.rotation = this.ps3D.initial_rotation.product(orig_initial_rotation.conjugated());
        if (orig_initial_length_view_point != 0.0) {
            this.length_view_point_factor = this.ps3D.initial_length_view_point / orig_initial_length_view_point;
        }
        else {
            this.length_view_point_factor = this.ps3D.initial_length_view_point;
        }
        this.ps3D.rotation = orig_rotation;
        this.ps3D.initial_rotation = orig_initial_rotation;
        this.ps3D.initial_length_view_point = orig_initial_length_view_point;
        if (this.is_spinning) {
            this.preceding_rotation = this.rotation;
            this.last_x = this.down_mouse_x;
            this.last_y = this.down_mouse_y;
        }
        return this.is_new_dragging = true;
    }
    
    public double getViewPointX() {
        final double[] view_point = { 0.0, 0.0, 1.0 };
        if (null == this.painted_ps3D) {
            return 1.3;
        }
        final double[] real_view_point = this.painted_ps3D.getQuaternion().conjugated().rotated(view_point);
        return real_view_point[0] * this.painted_ps3D.length_view_point;
    }
    
    public double getViewPointY() {
        final double[] view_point = { 0.0, 0.0, 1.0 };
        if (null == this.painted_ps3D) {
            return -2.4;
        }
        final double[] real_view_point = this.painted_ps3D.getQuaternion().conjugated().rotated(view_point);
        return real_view_point[1] * this.painted_ps3D.length_view_point;
    }
    
    public double getViewPointZ() {
        final double[] view_point = { 0.0, 0.0, 1.0 };
        if (null == this.painted_ps3D) {
            return 2.0;
        }
        final double[] real_view_point = this.painted_ps3D.getQuaternion().conjugated().rotated(view_point);
        return real_view_point[2] * this.painted_ps3D.length_view_point;
    }
    
    public double getViewVerticalX() {
        final double[] view_vertical = { 0.0, 1.0, 0.0 };
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        final double[] real_view_vertical = this.painted_ps3D.getQuaternion().conjugated().rotated(view_vertical);
        return real_view_vertical[0] / this.painted_ps3D.option_BoxRatios[0];
    }
    
    public double getViewVerticalY() {
        final double[] view_vertical = { 0.0, 1.0, 0.0 };
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        final double[] real_view_vertical = this.painted_ps3D.getQuaternion().conjugated().rotated(view_vertical);
        return real_view_vertical[1] / this.painted_ps3D.option_BoxRatios[1];
    }
    
    public double getViewVerticalZ() {
        final double[] view_vertical = { 0.0, 1.0, 0.0 };
        if (null == this.painted_ps3D) {
            return 1.0;
        }
        final double[] real_view_vertical = this.painted_ps3D.getQuaternion().conjugated().rotated(view_vertical);
        return real_view_vertical[2] / this.painted_ps3D.option_BoxRatios[2];
    }
    
    public double getPlotRangeXMin() {
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        return this.painted_ps3D.original_center[0] - this.painted_ps3D.original_sizes[0] / 2.0;
    }
    
    public double getPlotRangeXMax() {
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        return this.painted_ps3D.original_center[0] + this.painted_ps3D.original_sizes[0] / 2.0;
    }
    
    public double getPlotRangeYMin() {
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        return this.painted_ps3D.original_center[1] - this.painted_ps3D.original_sizes[1] / 2.0;
    }
    
    public double getPlotRangeYMax() {
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        return this.painted_ps3D.original_center[1] + this.painted_ps3D.original_sizes[1] / 2.0;
    }
    
    public double getPlotRangeZMin() {
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        return this.painted_ps3D.original_center[2] - this.painted_ps3D.original_sizes[2] / 2.0;
    }
    
    public double getPlotRangeZMax() {
        if (null == this.painted_ps3D) {
            return 0.0;
        }
        return this.painted_ps3D.original_center[2] + this.painted_ps3D.original_sizes[2] / 2.0;
    }
    
    public boolean setPlotRange(final double xmin, final double xmax, final double ymin, final double ymax, final double zmin, final double zmax) {
        this.center_offset[0] = (xmin + xmax) / 2.0;
        this.center_offset[1] = (ymin + ymax) / 2.0;
        this.center_offset[2] = (zmin + zmax) / 2.0;
        this.sizes_scales[0] = Math.abs(xmax - xmin);
        this.sizes_scales[1] = Math.abs(ymax - ymin);
        this.sizes_scales[2] = Math.abs(zmax - zmin);
        this.apply_new_offset();
        this.is_enforced_reprojection = true;
        return this.is_new_dragging = true;
    }
    
    void setFrameInternal(final int frame_index) {
        if (frame_index < 0 || frame_index >= this.frames_count) {
            return;
        }
        if (this.frames.size() > 1) {
            this.ps3D = this.frames.elementAt(frame_index);
        }
        else {
            this.ps3D = this.frames.elementAt(0);
            if (this.animate_variable_index > -1) {
                this.animate_variable_value = this.animate_min + frame_index * this.animate_step;
                this.is_new_animate_frame = true;
                if (null != this.evaluator) {
                    this.evaluator.setVariableValue(this.animate_variable_index, this.animate_variable_value);
                }
            }
        }
    }
    
    public boolean setFrame(final int new_frame_index) {
        int frames_per_period = this.frames_count;
        this.current_frame_index = new_frame_index;
        if (0 == this.animation_direction) {
            frames_per_period = 2 * this.frames_count - 2;
        }
        if (this.current_frame_index >= 0) {
            this.current_frame_index %= frames_per_period;
        }
        else {
            this.current_frame_index = (frames_per_period - -this.current_frame_index % frames_per_period) % frames_per_period;
        }
        if (0 == this.animation_direction) {
            if (this.current_frame_index < this.frames_count) {
                this.setFrameInternal(this.current_frame_index);
            }
            else {
                this.setFrameInternal(2 * this.frames_count - this.current_frame_index - 2);
            }
        }
        else {
            this.setFrameInternal(this.current_frame_index);
        }
        if (this.is_animating) {
            this.animated_time = System.currentTimeMillis();
        }
        return this.is_new_dragging = true;
    }
    
    public int getFrame() {
        return this.current_frame_index;
    }
    
    public boolean isAnimationPlaying() {
        return this.is_animating;
    }
    
    public boolean startAnimation() {
        return this.is_animating = true;
    }
    
    public boolean stopAnimation() {
        this.is_animating = false;
        return true;
    }
    
    public double getVariable(final String name) {
        if (null == this.evaluator) {
            return Double.NaN;
        }
        final int index = this.evaluator.getVariableIndex(name);
        if (index < 0) {
            return Double.NaN;
        }
        return this.evaluator.getVariableValue(index);
    }
    
    public boolean setVariable(final String name, final double value) {
        if (null == this.evaluator) {
            return false;
        }
        final int index = this.evaluator.getVariableIndex(name);
        if (index < 0 || !this.evaluator.isVariableIndependent(index)) {
            return false;
        }
        final double old_value = this.evaluator.getVariableValue(index);
        this.evaluator.setVariableValue(index, value);
        if (!this.evaluator.evaluate()) {
            this.evaluator.setVariableValue(index, old_value);
        }
        this.is_enforced_reprojection = true;
        return this.is_new_dragging = true;
    }
    
    public double get_double_value(final String text) {
        int position;
        for (position = 0; position < text.length() && 0 > Live.double_chars.indexOf(text.charAt(position)); ++position) {}
        if (position >= text.length()) {
            return 0.0;
        }
        final int start_position = position;
        while (position < text.length() && 0 <= Live.double_chars.indexOf(text.charAt(position))) {
            ++position;
        }
        final int end_position = position;
        double result;
        try {
            result = Double.valueOf(text.substring(start_position, end_position));
        }
        catch (NumberFormatException e) {
            result = 0.0;
        }
        return result;
    }
    
    public Image createRotatedImage(final Image original) {
        final int width = original.getWidth(null);
        final int height = original.getHeight(null);
        final Image rotated_image = this.createImage(width + 1, height + 1);
        Graphics rotated_graphics = rotated_image.getGraphics();
        rotated_graphics.drawImage(original, 0, 0, null);
        for (int y = 0; y < height / 2; ++y) {
            rotated_graphics.copyArea(0, y, width, 1, 0, height - y - y);
            rotated_graphics.copyArea(0, height - 1 - y, width, 1, 0, y - (height - 1 - y));
        }
        rotated_graphics.copyArea(0, height - (height / 2 - 1), width, height / 2, 0, -1);
        for (int x = 0; x < width / 2; ++x) {
            rotated_graphics.copyArea(x, 0, 1, height, width - x - x, 0);
            rotated_graphics.copyArea(width - 1 - x, 0, 1, height, x - (width - 1 - x), 0);
        }
        rotated_graphics.copyArea(width - (width / 2 - 1), 0, width / 2, height, -1, 0);
        rotated_graphics = null;
        return rotated_image;
    }
    
    public void start() {
        this.is_stopped = false;
        this.set_applet_size();
        this.is_control_down = false;
        this.is_meta_down = false;
        this.is_shift_down = false;
        this.is_new_dragging = false;
        this.is_dragging = false;
        this.is_dragging_point = false;
        if (!this.initialized) {
            this.is_animating = true;
        }
        this.down_time = System.currentTimeMillis() - 10000L;
        (this.painter = new Thread(this)).start();
    }
    
    public void set_applet_size() {
        this.applet_width = this.getSize().width;
        this.applet_height = this.getSize().height;
        if (this.applet_height < this.applet_width) {
            this.min_height_width = this.applet_height;
        }
        else {
            this.min_height_width = this.applet_width;
        }
    }
    
    public void print_message(final Graphics g, final String text) {
        this.print_message(g, text, true);
    }
    
    public void print_message(final Graphics g, final String text, final boolean into_console) {
        if (into_console) {
            System.out.println(text);
        }
        if (this.text_y == 0) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.applet_width, this.applet_height);
        }
        this.text_y = this.text_y + g.getFontMetrics().getAscent() + 2;
        g.setColor(this.foreground_color);
        g.drawString(text, this.text_x, this.text_y);
    }
    
    public void print_syntax_error(final Graphics g, final Parser parser) {
        if (parser.text_index + 1 >= parser.text.length()) {
            parser.text_index = parser.text.length() - 1;
        }
        if (parser.text_index > 30) {
            this.print_message(g, "LiveGraphics3D syntax error:");
            this.print_message(g, "..." + parser.text.substring(parser.text_index - 30, parser.text_index + 1));
        }
        else {
            this.print_message(g, "LiveGraphics3D syntax error:");
            this.print_message(g, parser.text.substring(0, parser.text_index + 1));
        }
    }
    
    public void stop() {
        this.is_stopped = true;
    }
    
    public void free_resources() {
        this.ps3D = null;
        this.painted_ps3D = null;
        if (null != this.frames) {
            this.frames.removeAllElements();
            this.frames = null;
        }
        this.front_image_buffer = null;
        this.back_image_buffer = null;
        this.second_image_buffer = null;
        this.preload_bg_image = null;
        this.bg_image = null;
        this.bg_rotated_image = null;
        this.bg_right_image = null;
        this.bg_rotated_right_image = null;
        if (null != this.front_graphics_buffer) {
            this.front_graphics_buffer.dispose();
            this.front_graphics_buffer = null;
        }
        if (null != this.back_graphics_buffer) {
            this.back_graphics_buffer.dispose();
            this.back_graphics_buffer = null;
        }
        if (null != this.second_graphics_buffer) {
            this.second_graphics_buffer.dispose();
            this.second_graphics_buffer = null;
        }
        this.audio_enter = null;
        this.audio_loop_enter = null;
        this.audio_first_frame = null;
        System.gc();
    }
    
    public void destroy() {
        this.is_stopped = true;
    }
    
    public void finalize() {
        this.free_resources();
    }
    
    public void run() {
        this.is_stopped = false;
        try {
            this.initialize();
            if (!this.initialized) {
                final Thread painter = this.painter;
                Thread.sleep(10000L);
            }
        }
        catch (Exception e) {
            System.out.println("LiveGraphics3D caught exception in initialize():\n   " + e.toString());
        }
        if (!this.initialized) {
            System.out.println("LiveGraphics3D tries to initialize once more...");
            this.initialize();
            if (!this.initialized) {
                this.is_stopped = true;
            }
        }
        while (!this.is_stopped) {
            try {
                if (null == this.ps3D) {
                    try {
                        final Thread painter2 = this.painter;
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException e2) {}
                }
                else {
                    if (this.is_new_dragging) {
                        this.is_new_dragging = false;
                        if (!this.is_dragging_point) {
                            if (this.is_control_down) {
                                this.length_view_point_factor = this.preceding_length_view_point_factor * Math.pow(4.0, (this.down_mouse_y - this.last_y) / this.min_height_width);
                                this.stereo_distance = this.preceding_stereo_distance + 0.25 * (this.last_x - this.down_mouse_x) / this.applet_width;
                            }
                            else if (this.is_shift_down) {
                                this.magnification_factor = this.preceding_magnification_factor / Math.pow(4.0, (this.down_mouse_y - this.last_y) / this.min_height_width);
                                if (!this.bg_is_cylindrical && !this.bg_is_spherical) {
                                    this.rotation = new Quaternion(4.71238898038469 * (this.down_mouse_x - this.last_x) / this.min_height_width, 0.0, 0.0, 1.0, false).product(this.preceding_rotation);
                                }
                            }
                            else if (this.is_meta_down) {
                                this.cut_primitives_count = this.preceding_cut_primitives_count - (this.down_mouse_y - (int)this.last_y) / 8;
                                if (this.cut_primitives_count < 0) {
                                    this.cut_primitives_count = 0;
                                }
                                int offset_frame_index = ((int)this.last_x - this.down_mouse_x) / 8;
                                if (-1 == this.animation_direction) {
                                    offset_frame_index = -offset_frame_index;
                                }
                                this.current_frame_index = this.preceding_frame_index + offset_frame_index;
                                int frames_per_period = this.frames_count;
                                if (0 == this.animation_direction) {
                                    frames_per_period = 2 * this.frames_count - 2;
                                }
                                if (this.current_frame_index >= 0) {
                                    this.current_frame_index %= frames_per_period;
                                }
                                else {
                                    this.current_frame_index = (frames_per_period - -this.current_frame_index % frames_per_period) % frames_per_period;
                                }
                                if (0 == this.animation_direction) {
                                    if (this.current_frame_index < this.frames_count) {
                                        this.setFrameInternal(this.current_frame_index);
                                    }
                                    else {
                                        this.setFrameInternal(2 * this.frames_count - this.current_frame_index - 2);
                                    }
                                }
                                else {
                                    this.setFrameInternal(this.current_frame_index);
                                }
                            }
                            else if (this.is_dragging || this.is_spinning) {
                                if (this.bg_is_cylindrical || this.bg_is_spherical) {
                                    this.bg_x_offset = this.preceding_bg_x_offset - ((int)this.last_x - this.down_mouse_x);
                                    this.bg_y_offset = this.preceding_bg_y_offset - ((int)this.last_y - this.down_mouse_y);
                                    this.rotation = this.adjust_bg_offsets();
                                }
                                else {
                                    double nx = this.last_y - this.down_mouse_y;
                                    double ny = this.last_x - this.down_mouse_x;
                                    final double abs = Math.sqrt(nx * nx + ny * ny);
                                    if (abs > 1.0) {
                                        nx /= abs;
                                        ny /= abs;
                                        this.rotation = new Quaternion(4.71238898038469 * abs / this.min_height_width, nx, ny, 0.0, false).product(this.preceding_rotation);
                                    }
                                    else {
                                        this.rotation = this.preceding_rotation;
                                    }
                                }
                            }
                        }
                        this.ps3D.setPerspective(this.ps3D.initial_length_view_point * this.length_view_point_factor, this.ps3D.initial_magnification * this.magnification_factor, this.is_stereo, this.stereo_distance);
                        this.ps3D.setCutPrimitivesCount(this.cut_primitives_count, this.visible_faces, this.show_faces);
                        this.ps3D.setQuaternion(this.rotation.product(this.ps3D.initial_rotation));
                        if (!this.is_dragging_point) {
                            if (!this.is_new_animate_frame || null == this.evaluator || this.animate_variable_index < 0) {
                                if (this.is_enforced_reprojection) {
                                    this.ps3D.projectPoints(true);
                                }
                                else {
                                    this.ps3D.projectPoints(false);
                                }
                            }
                            else {
                                if (!this.evaluator.evaluate()) {
                                    this.animate_variable_value = this.valid_animate_variable_value;
                                    this.evaluator.setVariableValue(this.animate_variable_index, this.animate_variable_value);
                                }
                                else {
                                    this.valid_animate_variable_value = this.animate_variable_value;
                                }
                                this.ps3D.projectPoints(true);
                            }
                        }
                        else if (null != this.evaluator && null != this.dragged_point) {
                            final int point_index = this.dragged_point.points[0];
                            final double point_scale = this.ps3D.point_scale[point_index];
                            if (0.0 >= point_scale) {
                                this.ps3D.projectPoints(false);
                            }
                            else {
                                final double[] a_vector = { 0.0, 0.0, 0.0 };
                                final double[] b_vector = { 0.0, 0.0, 0.0 };
                                double a_square = 0.0;
                                double b_square = 0.0;
                                double a_dot_b = 0.0;
                                int a_index = 0;
                                int b_index = 0;
                                final double[] vector = { 0.0, 0.0, 0.0 };
                                final Expression[] expressions = { null, null, null };
                                final double[] old_expression_values = { 0.0, 0.0, 0.0 };
                                final double xy_factor = this.ps3D.pixel_height;
                                int independent_variables_count = 0;
                                final double[] scale_factors = { 1.0, 1.0, 1.0 };
                                for (int axis_index = 0; axis_index < 3; ++axis_index) {
                                    final int expression_index = this.dragged_point.original_expressions[0][axis_index];
                                    if (expression_index >= 0) {
                                        final Expression expression = this.evaluator.getExpression(expression_index);
                                        if (expression.isAtomicIndependentVariable()) {
                                            expressions[axis_index] = expression;
                                        }
                                    }
                                }
                                for (int axis_index = 0; axis_index < 3; ++axis_index) {
                                    final Expression expression2 = expressions[axis_index];
                                    if (null != expression2) {
                                        old_expression_values[axis_index] = expression2.getCurrentValue();
                                        ++independent_variables_count;
                                    }
                                }
                                if (3 > independent_variables_count) {
                                    double length = 0.0;
                                    int basis_index = 0;
                                    vector[1] = (vector[0] = 0.0);
                                    vector[2] = 1.0;
                                    final double[] normal = this.ps3D.getQuaternion().conjugated().rotated(vector);
                                    for (int axis_index2 = 0; axis_index2 < 3; ++axis_index2) {
                                        if (null != expressions[axis_index2]) {
                                            vector[0] = 0.0;
                                            vector[2] = (vector[1] = 0.0);
                                            vector[axis_index2] = 1.0;
                                            final double dot_product = normal[axis_index2];
                                            final double[] array = vector;
                                            final int n = 0;
                                            array[n] -= dot_product * normal[0];
                                            final double[] array2 = vector;
                                            final int n2 = 1;
                                            array2[n2] -= dot_product * normal[1];
                                            final double[] array3 = vector;
                                            final int n3 = 2;
                                            array3[n3] -= dot_product * normal[2];
                                            length = vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2];
                                            if (2 == independent_variables_count) {
                                                if (0 == basis_index) {
                                                    a_square = length;
                                                    a_vector[0] = vector[0];
                                                    a_vector[1] = vector[1];
                                                    a_vector[2] = vector[2];
                                                    a_index = axis_index2;
                                                    basis_index = 1;
                                                }
                                                else if (1 == basis_index) {
                                                    b_square = length;
                                                    b_vector[0] = vector[0];
                                                    b_vector[1] = vector[1];
                                                    b_vector[2] = vector[2];
                                                    b_index = axis_index2;
                                                    a_dot_b = a_vector[0] * b_vector[0] + a_vector[1] * b_vector[1] + a_vector[2] * b_vector[2];
                                                    basis_index = 2;
                                                }
                                                length = 1.0;
                                            }
                                            if (length < 0.1) {
                                                length = 0.1;
                                            }
                                            scale_factors[axis_index2] = 1.0 / length;
                                        }
                                    }
                                }
                                vector[0] = point_scale * (this.last_x - this.down_mouse_x) / xy_factor;
                                vector[1] = point_scale * (this.down_mouse_y - this.last_y) / xy_factor;
                                vector[2] = 0.0;
                                final double[] translation = this.ps3D.getQuaternion().conjugated().rotated(vector);
                                if (2 == independent_variables_count) {
                                    final double numerator = a_square * b_square - a_dot_b * a_dot_b;
                                    if (numerator > 0.0) {
                                        final double t_dot_a = translation[0] * a_vector[0] + translation[1] * a_vector[1] + translation[2] * a_vector[2];
                                        final double t_dot_b = translation[0] * b_vector[0] + translation[1] * b_vector[1] + translation[2] * b_vector[2];
                                        translation[0] = 0.0;
                                        translation[2] = (translation[1] = 0.0);
                                        translation[a_index] = (t_dot_a * b_square - t_dot_b * a_dot_b) / numerator;
                                        translation[b_index] = (t_dot_b * a_square - t_dot_a * a_dot_b) / numerator;
                                    }
                                }
                                vector[0] = translation[0] * scale_factors[0] + this.down_mouse_point_x;
                                vector[1] = translation[1] * scale_factors[1] + this.down_mouse_point_y;
                                vector[2] = translation[2] * scale_factors[2] + this.down_mouse_point_z;
                                for (int axis_index = 0; axis_index < 3; ++axis_index) {
                                    final Expression expression2 = expressions[axis_index];
                                    if (null != expression2) {
                                        expression2.setIndependentVariable(vector[axis_index] / this.ps3D.scalings[axis_index] + this.ps3D.original_center[axis_index]);
                                    }
                                }
                                if (!this.evaluator.evaluate()) {
                                    for (int axis_index = 0; axis_index < 3; ++axis_index) {
                                        final Expression expression2 = expressions[axis_index];
                                        if (null != expression2) {
                                            expression2.setIndependentVariable(old_expression_values[axis_index]);
                                        }
                                    }
                                }
                                this.ps3D.projectPoints(true);
                            }
                        }
                        this.is_enforced_reprojection = false;
                        this.paintGraphics3D();
                        if (null != this.audio_first_frame && 0 == this.current_frame_index && this.is_animating && this.frames_count > 1 && (this.is_dragging || this.is_mouse_here)) {
                            this.audio_first_frame.play();
                        }
                    }
                    else {
                        try {
                            if ((this.is_animating && this.frames_count > 1 && (this.is_dragging || this.is_mouse_here) && !this.is_meta_down) || (this.is_spinning && this.is_mouse_here)) {
                                final Thread painter3 = this.painter;
                                Thread.sleep(5L);
                            }
                            else {
                                final Thread painter4 = this.painter;
                                Thread.sleep(20L);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                    final long now = System.currentTimeMillis();
                    final long millis_since_animated = now - this.animated_time;
                    final long millis_since_spinned = now - this.spinned_time;
                    int variable_index = -1;
                    if (null != this.evaluator && null != this.ps3D.option_AnimationDisplayTimeString) {
                        variable_index = this.evaluator.getVariableIndex(this.ps3D.option_AnimationDisplayTimeString);
                    }
                    double animation_display_time;
                    if (variable_index >= 0) {
                        animation_display_time = this.evaluator.getVariableValue(variable_index);
                    }
                    else {
                        animation_display_time = this.ps3D.option_AnimationDisplayTime;
                    }
                    if (animation_display_time < 0.01) {
                        animation_display_time = 0.01;
                    }
                    final boolean is_frame_old = millis_since_animated >= (long)(1000.0 * animation_display_time);
                    if (this.is_spinning && this.is_mouse_here && (millis_since_spinned >= (long)(1000.0 * this.spinning_display_time) || (this.is_animating && this.frames_count > 1 && !this.is_meta_down && is_frame_old))) {
                        this.spinned_time = now;
                        this.is_new_dragging = true;
                        this.last_x += this.spin_x;
                        this.last_y += this.spin_y;
                    }
                    if (!this.is_animating || this.frames_count <= 1 || (!this.is_dragging && !this.is_mouse_here) || this.is_meta_down || !is_frame_old) {
                        continue;
                    }
                    this.animated_time = now;
                    this.is_new_dragging = true;
                    if (1 == this.animation_direction) {
                        ++this.current_frame_index;
                        if (this.current_frame_index >= this.frames_count) {
                            this.current_frame_index = 0;
                        }
                        this.setFrameInternal(this.current_frame_index);
                    }
                    else if (-1 == this.animation_direction) {
                        --this.current_frame_index;
                        if (this.current_frame_index < 0) {
                            this.current_frame_index = this.frames_count - 1;
                        }
                        this.setFrameInternal(this.current_frame_index);
                    }
                    else {
                        if (0 != this.animation_direction) {
                            continue;
                        }
                        ++this.current_frame_index;
                        if (this.current_frame_index >= 2 * this.frames_count - 2) {
                            this.current_frame_index = 0;
                        }
                        if (this.current_frame_index < this.frames_count) {
                            this.setFrameInternal(this.current_frame_index);
                        }
                        else {
                            this.setFrameInternal(2 * this.frames_count - this.current_frame_index - 2);
                        }
                    }
                }
            }
            catch (Exception e) {
                System.out.println("LiveGraphics3D caught exception in run():\n   " + e.toString());
            }
        }
    }
    
    Quaternion adjust_bg_offsets() {
        while (this.bg_x_offset > this.bg_width) {
            this.bg_x_offset -= this.bg_width;
        }
        while (this.bg_x_offset < 0) {
            this.bg_x_offset += this.bg_width;
        }
        final double phi = -6.283185307179586 * this.bg_x_offset / this.bg_width;
        double theta = 0.0;
        if (this.bg_is_cylindrical) {
            final int max_y_offset = (this.bg_height - this.applet_height) / 2;
            if (this.bg_y_offset > max_y_offset) {
                this.bg_y_offset = max_y_offset;
            }
            else if (this.bg_y_offset < -max_y_offset) {
                this.bg_y_offset = -max_y_offset;
            }
            theta = -6.283185307179586 * this.bg_y_offset / this.bg_width;
        }
        else if (this.bg_is_spherical) {
            while (this.bg_y_offset > this.bg_height + this.bg_height) {
                this.bg_y_offset = this.bg_y_offset - this.bg_height - this.bg_height;
            }
            while (this.bg_y_offset < 0) {
                this.bg_y_offset = this.bg_y_offset + this.bg_height + this.bg_height;
            }
            theta = -3.141592653589793 * this.bg_y_offset / this.bg_height;
        }
        (this.rotation = new Quaternion(theta, 1.0, 0.0, 0.0, false)).multiply(new Quaternion(phi, 0.0, 1.0, 0.0, false));
        return this.rotation;
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        this.is_mouse_here = true;
        this.requestFocus();
        if (null != this.audio_enter) {
            this.audio_enter.play();
        }
        if (null != this.audio_loop_enter) {
            this.audio_loop_enter.loop();
        }
        if (this.initialized) {
            if (null == this.ps3D) {
                this.getAppletContext().showStatus(Live.syntax_error_string);
                return true;
            }
            if (!this.is_dragging_point && !this.is_dragging) {
                if (this.frames_count <= 1) {
                    if (0 == this.mouse_drag_action) {
                        this.getAppletContext().showStatus(Live.enter_plain_string);
                    }
                    else {
                        this.getAppletContext().showStatus(Live.enter_drag_string);
                    }
                }
                else if (0 == this.mouse_drag_action) {
                    this.getAppletContext().showStatus(Live.enter_animated_string);
                }
                else {
                    this.getAppletContext().showStatus(Live.enter_drag_animated_string);
                }
            }
        }
        else {
            this.getAppletContext().showStatus(Live.enter_init_string);
        }
        return true;
    }
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        this.is_mouse_here = false;
        if (null != this.audio_loop_enter) {
            this.audio_loop_enter.stop();
        }
        if (null != this.active_primitive) {
            this.drawTextRectangle(this.active_primitive);
            this.active_primitive = null;
            this.getAppletContext().showStatus(Live.empty_string);
            if (!this.is_dragging && !this.is_new_dragging) {
                this.repaint();
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.is_mouse_here = true;
        if (!this.initialized || null == this.painted_ps3D || x < 0 || x > this.applet_width || y < 0 || y > this.applet_height || this.is_dragging_point) {
            return true;
        }
        this.mark_hyperlink(x, y);
        return true;
    }
    
    public synchronized void mark_hyperlink(int x, final int y) {
        final Primitive3D old_active_primitive = this.active_primitive;
        this.active_primitive = null;
        if (!this.is_animating && !this.is_spinning) {
            int[] pixel_xs = this.painted_ps3D.left_pixel_xs;
            final int[] pixel_ys = this.painted_ps3D.pixel_ys;
            if (this.painted_ps3D.is_stereo && x > this.painted_ps3D.pixel_width) {
                pixel_xs = this.painted_ps3D.right_pixel_xs;
                x -= this.painted_ps3D.pixel_width;
            }
            for (int ordered_index = this.painted_ps3D.count_ordered_primitives - 1; ordered_index >= 0; --ordered_index) {
                final int primitive_index = this.painted_ps3D.order[ordered_index];
                if (this.painted_ps3D.min_primitive_index <= primitive_index && primitive_index <= this.painted_ps3D.max_primitive_index) {
                    final Primitive3D primitive = this.painted_ps3D.primitives.elementAt(primitive_index);
                    if (null != primitive.font_url && 0 == primitive.is_inactive_flags) {
                        if (primitive.font_url == Primitive3D.text_dragable_point) {
                            final int point_index = primitive.points[0];
                            if (0.0f < this.painted_ps3D.point_scale[point_index]) {
                                int radius;
                                if (primitive.is_absolute_point_size) {
                                    radius = (int)(primitive.point_diameter / 2.0) + 4;
                                }
                                else {
                                    radius = (int)(primitive.point_diameter / this.painted_ps3D.point_scale[point_index] / 2.0) + 4;
                                }
                                if (pixel_xs[point_index] - radius <= x && pixel_ys[point_index] - radius <= y && pixel_xs[point_index] + radius >= x && pixel_ys[point_index] + radius >= y) {
                                    this.active_primitive = primitive;
                                    break;
                                }
                            }
                        }
                        else if (0 == primitive.count_points) {
                            if (this.painted_ps3D.pixel_width / 2 + primitive.first_point <= x && 2 + primitive.second_point <= y && this.painted_ps3D.pixel_width / 2 + primitive.first_point + primitive.third_point >= x && 2 + primitive.second_point + primitive.fourth_point >= y) {
                                this.active_primitive = primitive;
                                break;
                            }
                        }
                        else {
                            final int point_index2 = primitive.points[0];
                            if (0.0f < this.painted_ps3D.point_scale[point_index2] && pixel_xs[point_index2] + primitive.first_point <= x && pixel_ys[point_index2] + primitive.second_point <= y && pixel_xs[point_index2] + primitive.first_point + primitive.third_point >= x && pixel_ys[point_index2] + primitive.second_point + primitive.fourth_point >= y) {
                                this.active_primitive = primitive;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (old_active_primitive != this.active_primitive && (old_active_primitive != null || this.active_primitive != null)) {
            if (null != old_active_primitive) {
                this.drawTextRectangle(old_active_primitive);
                if (null == this.active_primitive) {
                    this.getAppletContext().showStatus(Live.empty_string);
                }
            }
            if (null != this.active_primitive) {
                String url_text = this.active_primitive.font_url;
                final int end_position = url_text.indexOf(Live.target_string);
                if (end_position > 0) {
                    url_text = url_text.substring(0, end_position - 1);
                }
                this.drawTextRectangle(this.active_primitive);
                this.getAppletContext().showStatus(url_text);
            }
            this.repaint();
        }
    }
    
    public synchronized void drawTextRectangle(final Primitive3D primitive) {
        if (primitive.font_url == Primitive3D.text_dragable_point) {
            final int point_index = primitive.points[0];
            if (0.0f < this.painted_ps3D.point_scale[point_index]) {
                int radius;
                if (primitive.is_absolute_point_size) {
                    radius = (int)(primitive.point_diameter / 2.0) + 4;
                }
                else {
                    radius = (int)(primitive.point_diameter / this.painted_ps3D.point_scale[point_index] / 2.0) + 4;
                }
                this.front_graphics_buffer.setColor(Color.white);
                this.front_graphics_buffer.setXORMode(Color.black);
                this.front_graphics_buffer.drawRect(this.painted_ps3D.left_pixel_xs[point_index] - radius, this.painted_ps3D.pixel_ys[point_index] - radius, 2 * radius - 1, 2 * radius - 1);
                if (this.painted_ps3D.is_stereo) {
                    this.front_graphics_buffer.drawRect(this.painted_ps3D.pixel_width + this.painted_ps3D.right_pixel_xs[point_index] - radius, this.painted_ps3D.pixel_ys[point_index] - radius, 2 * radius - 1, 2 * radius - 1);
                }
                this.front_graphics_buffer.setPaintMode();
            }
        }
        else if (0 == primitive.count_points) {
            this.front_graphics_buffer.setColor(Color.white);
            this.front_graphics_buffer.setXORMode(Color.black);
            this.front_graphics_buffer.drawRect(this.painted_ps3D.pixel_width / 2 + primitive.first_point, 2 + primitive.second_point, primitive.third_point - 1, primitive.fourth_point - 1);
            if (this.painted_ps3D.is_stereo) {
                this.front_graphics_buffer.drawRect(this.painted_ps3D.pixel_width + this.painted_ps3D.pixel_width / 2 + primitive.first_point, 2 + primitive.second_point, primitive.third_point - 1, primitive.fourth_point - 1);
            }
            this.front_graphics_buffer.setPaintMode();
        }
        else {
            final int point_index2 = primitive.points[0];
            if (0.0f < this.painted_ps3D.point_scale[point_index2]) {
                this.front_graphics_buffer.setColor(Color.white);
                this.front_graphics_buffer.setXORMode(Color.black);
                this.front_graphics_buffer.drawRect(this.painted_ps3D.left_pixel_xs[point_index2] + primitive.first_point, this.painted_ps3D.pixel_ys[point_index2] + primitive.second_point, primitive.third_point - 1, primitive.fourth_point - 1);
                if (this.painted_ps3D.is_stereo) {
                    this.front_graphics_buffer.drawRect(this.painted_ps3D.pixel_width + this.painted_ps3D.right_pixel_xs[point_index2] + primitive.first_point, this.painted_ps3D.pixel_ys[point_index2] + primitive.second_point, primitive.third_point - 1, primitive.fourth_point - 1);
                }
                this.front_graphics_buffer.setPaintMode();
            }
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        this.is_mouse_here = true;
        if (null == this.painted_ps3D) {
            return true;
        }
        if (null != this.active_primitive && null != this.active_primitive.text) {
            String url_text = this.active_primitive.font_url;
            String target_text = null;
            final int end_position = url_text.indexOf(Live.target_string);
            if (end_position > 0) {
                target_text = url_text.substring(end_position + Live.target_string.length());
                url_text = url_text.substring(0, end_position - 1);
            }
            URL new_url;
            try {
                new_url = new URL(url_text);
            }
            catch (MalformedURLException e) {
                this.getAppletContext().showStatus(Live.url_error_string + e.getMessage());
                new_url = null;
            }
            if (null != new_url) {
                if (null == target_text) {
                    this.getAppletContext().showDocument(new_url);
                }
                else {
                    this.getAppletContext().showDocument(new_url, target_text);
                }
            }
        }
        else {
            final long new_down_time = System.currentTimeMillis();
            if (null != this.active_primitive && 1 == this.active_primitive.count_points) {
                this.down_time = new_down_time;
                this.is_animating = false;
                this.is_dragging = false;
                this.is_dragging_point = true;
                this.dragged_point = this.active_primitive;
                this.down_mouse_point_x = this.painted_ps3D.xs[this.active_primitive.points[0]];
                this.down_mouse_point_y = this.painted_ps3D.ys[this.active_primitive.points[0]];
                this.down_mouse_point_z = this.painted_ps3D.zs[this.active_primitive.points[0]];
            }
            else {
                if (new_down_time > this.down_time && new_down_time - this.down_time < 500L) {
                    if (this.is_animating || this.frames_count > 1) {
                        this.is_animating = !this.is_animating;
                    }
                    this.down_time = new_down_time - 1000L;
                }
                else {
                    this.down_time = new_down_time;
                }
                this.is_dragging = true;
                this.is_dragging_point = false;
            }
            this.preceding_rotation = this.rotation;
            this.preceding_length_view_point_factor = this.length_view_point_factor;
            this.preceding_magnification_factor = this.magnification_factor;
            this.preceding_cut_primitives_count = this.cut_primitives_count;
            this.preceding_bg_x_offset = this.bg_x_offset;
            this.preceding_bg_y_offset = this.bg_y_offset;
            this.preceding_stereo_distance = this.stereo_distance;
            this.preceding_frame_index = this.current_frame_index;
            this.down_mouse_x = x;
            this.down_mouse_y = y;
            this.last_x = this.down_mouse_x;
            this.last_y = this.down_mouse_y;
            this.last_drag_time = new_down_time;
            this.is_spinning = false;
            this.spin_x = 0.0;
            this.spin_y = 0.0;
            this.is_shift_down = false;
            this.is_control_down = false;
            this.is_meta_down = false;
            if (evt.shiftDown()) {
                this.is_shift_down = true;
            }
            else if (evt.controlDown()) {
                this.is_control_down = true;
            }
            else if (evt.metaDown()) {
                this.is_meta_down = true;
            }
        }
        if (this.is_dragging_point) {
            this.drawTextRectangle(this.active_primitive);
            this.active_primitive = null;
            this.getAppletContext().showStatus(Live.empty_string);
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        if (!this.is_shift_down && !this.is_meta_down && !this.is_control_down && this.is_mouse_here && this.llast_drag_time != 0L && !this.is_dragging_point) {
            this.calculateSpin(evt, x, y);
        }
        else {
            this.is_spinning = false;
            this.spin_x = 0.0;
            this.spin_y = 0.0;
        }
        this.llast_drag_time = 0L;
        this.is_dragging = false;
        this.is_dragging_point = false;
        this.is_shift_down = false;
        this.is_meta_down = false;
        this.is_control_down = false;
        if (!this.initialized || null == this.painted_ps3D || !this.is_mouse_here || x < 0 || x > this.applet_width || y < 0 || y > this.applet_height) {
            return true;
        }
        this.mark_hyperlink(x, y);
        return true;
    }
    
    void calculateSpin(final Event evt, final int x, final int y) {
        double final_x = this.last_x;
        double final_y = this.last_y;
        long final_time = this.last_drag_time;
        final double initial_x = this.llast_x;
        final double initial_y = this.llast_y;
        final long initial_time = this.llast_drag_time;
        final long now = System.currentTimeMillis();
        if (x == (int)this.last_x && y == (int)this.last_y && (now - final_time > 500L || now >= final_time + (final_time - initial_time) + (final_time - initial_time))) {
            this.spin_x = 0.0;
            this.spin_y = 0.0;
        }
        else {
            if (now > final_time) {
                final_x = x;
                final_y = y;
                final_time = now;
            }
            if (final_time <= initial_time) {
                this.spin_x = 0.0;
                this.spin_y = 0.0;
            }
            else {
                final double time_scale = this.spinning_display_time;
                final double time_fraction = 1000.0 * time_scale / (final_time - initial_time);
                this.spin_x = time_fraction * (final_x - initial_x);
                this.spin_y = time_fraction * (final_y - initial_y);
                final double abs = Math.sqrt(this.spin_x * this.spin_x + this.spin_y * this.spin_y);
                if (abs > this.min_height_width / 8.0) {
                    this.spin_x = this.spin_x / abs * this.min_height_width / 8.0;
                    this.spin_y = this.spin_y / abs * this.min_height_width / 8.0;
                }
            }
        }
        if (Math.abs(this.spin_x) > 0.1 || Math.abs(this.spin_y) > 0.1) {
            this.is_spinning = true;
        }
        else {
            this.is_spinning = false;
            this.spin_x = 0.0;
            this.spin_y = 0.0;
        }
    }
    
    public boolean mouseDrag(final Event evt, final int x, final int y) {
        if (null == this.ps3D || (null != this.active_primitive && null != this.active_primitive.text) || (!this.is_dragging && !this.is_dragging_point) || (0 == this.mouse_drag_action && !this.is_dragging_point && !this.is_shift_down && !this.is_control_down && !this.is_meta_down)) {
            return true;
        }
        if ((this.is_dragging_point || null != this.preceding_rotation) && (x != (int)this.last_x || y != (int)this.last_y)) {
            this.is_new_dragging = true;
            if (this.is_dragging && (this.is_shift_down || this.is_control_down || this.is_meta_down)) {
                if (Math.abs((int)this.last_x - x) > Math.abs((int)this.last_y - y)) {
                    this.down_mouse_y = this.down_mouse_y + y - (int)this.last_y;
                }
                else {
                    this.down_mouse_x = this.down_mouse_x + x - (int)this.last_x;
                }
            }
            final long now = System.currentTimeMillis();
            if (this.last_drag_time != now) {
                this.llast_x = this.last_x;
                this.llast_y = this.last_y;
                this.llast_drag_time = this.last_drag_time;
            }
            this.last_x = x;
            this.last_y = y;
            this.last_drag_time = now;
            this.spin_x = 0.0;
            this.spin_y = 0.0;
            this.is_spinning = false;
        }
        return true;
    }
    
    public boolean keyDown(final Event evt, final int key) {
        if (null == this.painted_ps3D) {
            return true;
        }
        if (100 == key) {
            System.out.println("LiveGraphics3D debugging information: ");
            System.out.println("     initialized: " + this.initialized);
            System.out.println("      is_stopped: " + this.is_stopped);
            System.out.println("   is_mouse_here: " + this.is_mouse_here);
            System.out.println("    is_animating: " + this.is_animating);
            System.out.println("     is_spinning: " + this.is_spinning);
            System.out.println("          spin_x: " + this.spin_x);
            System.out.println("          spin_y: " + this.spin_y);
        }
        if (102 == key) {
            this.show_faces = !this.show_faces;
            this.is_new_dragging = true;
        }
        if (111 == key) {
            double[] view_point = { 0.0, 0.0, 1.0 };
            double[] view_vertical = { 0.0, 1.0, 0.0 };
            view_point = this.painted_ps3D.getQuaternion().conjugated().rotated(view_point);
            view_vertical = this.painted_ps3D.getQuaternion().conjugated().rotated(view_vertical);
            String options = "LiveGraphics3D parameters:\n   <PARAM NAME=MAGNIFICATION VALUE=" + this.magnification_factor + ">\n";
            if (this.is_stereo) {
                options = options + "   <PARAM NAME=STEREO_DISTANCE VALUE=" + this.stereo_distance + ">\n";
            }
            if (this.cut_primitives_count > 0) {
                options = options + "   <PARAM NAME=STRIPPED_PRIMITIVES VALUE=" + this.cut_primitives_count + ">\n";
            }
            if (0.0 != this.spin_x) {
                options = options + "   <PARAM NAME=SPIN_X VALUE=" + this.spin_x + ">\n";
            }
            if (0.0 != this.spin_y) {
                options = options + "   <PARAM NAME=SPIN_Y VALUE=" + this.spin_y + ">\n";
            }
            if (null != this.evaluator && this.evaluator.isVariableIndependent(0)) {
                options += "   <PARAM NAME=INDEPENDENT_VARIABLES VALUE=\"{\n";
                for (int index = 0; this.evaluator.isVariableIndependent(index); ++index) {
                    options = options + "       " + this.evaluator.variable_names.elementAt(index) + "->" + this.evaluator.getVariableValue(index);
                    if (this.evaluator.isVariableIndependent(index + 1)) {
                        options += ",\n";
                    }
                    else {
                        options += "}\">\n";
                    }
                }
            }
            options = options + "Graphics3D options:\n   ViewPoint->{" + view_point[0] * this.painted_ps3D.length_view_point + ", " + view_point[1] * this.painted_ps3D.length_view_point + ", " + view_point[2] * this.painted_ps3D.length_view_point + "},\n   ViewVertical->{" + view_vertical[0] / this.painted_ps3D.option_BoxRatios[0] + ", " + view_vertical[1] / this.painted_ps3D.option_BoxRatios[1] + ", " + view_vertical[2] / this.painted_ps3D.option_BoxRatios[2] + "},\n   PlotRange->{{" + (this.painted_ps3D.original_center[0] - this.painted_ps3D.original_sizes[0] / 2.0) + ", " + (this.painted_ps3D.original_center[0] + this.painted_ps3D.original_sizes[0] / 2.0) + "}, {" + (this.painted_ps3D.original_center[1] - this.painted_ps3D.original_sizes[1] / 2.0) + ", " + (this.painted_ps3D.original_center[1] + this.painted_ps3D.original_sizes[1] / 2.0) + "}, {" + (this.painted_ps3D.original_center[2] - this.painted_ps3D.original_sizes[2] / 2.0) + ", " + (this.painted_ps3D.original_center[2] + this.painted_ps3D.original_sizes[2] / 2.0) + "}}";
            System.out.println(options);
        }
        else if (115 == key) {
            if (!this.is_stereo) {
                this.is_stereo = true;
            }
            else if (this.stereo_distance > 0.0) {
                this.stereo_distance = -this.stereo_distance;
                if (this.stereo_distance > 0.0) {
                    this.stereo_distance = -0.05;
                }
            }
            else {
                this.stereo_distance = -this.stereo_distance;
                this.is_stereo = false;
            }
            this.is_new_dragging = true;
        }
        else if (88 == key) {
            this.center_offset[0] += 0.25 / this.magnification_factor;
            this.apply_new_offset();
            this.is_enforced_reprojection = true;
            this.is_new_dragging = true;
        }
        else if (120 == key) {
            this.center_offset[0] -= 0.25 / this.magnification_factor;
            this.apply_new_offset();
            this.is_enforced_reprojection = true;
            this.is_new_dragging = true;
        }
        else if (89 == key) {
            this.center_offset[1] += 0.25 / this.magnification_factor;
            this.apply_new_offset();
            this.is_enforced_reprojection = true;
            this.is_new_dragging = true;
        }
        else if (121 == key) {
            this.center_offset[1] -= 0.25 / this.magnification_factor;
            this.apply_new_offset();
            this.is_enforced_reprojection = true;
            this.is_new_dragging = true;
        }
        else if (90 == key) {
            this.center_offset[2] += 0.25 / this.magnification_factor;
            this.apply_new_offset();
            this.is_enforced_reprojection = true;
            this.is_new_dragging = true;
        }
        else if (122 == key) {
            this.center_offset[2] -= 0.25 / this.magnification_factor;
            this.apply_new_offset();
            this.is_enforced_reprojection = true;
            this.is_new_dragging = true;
        }
        else if (1000 == key) {
            this.rotation = this.home_rotation;
            this.bg_x_offset = this.home_bg_x_offset;
            this.bg_y_offset = this.home_bg_y_offset;
            this.magnification_factor = this.home_magnification_factor;
            this.length_view_point_factor = this.home_length_view_point_factor;
            this.is_stereo = this.home_is_stereo;
            this.stereo_distance = this.home_stereo_distance;
            this.cut_primitives_count = this.home_cut_primitives_count;
            this.spin_x = 0.0;
            this.spin_y = 0.0;
            this.is_spinning = false;
            this.show_faces = true;
            if (null != this.evaluator) {
                this.evaluator.setInitialVariableValues();
            }
            this.setFrame(0);
            if (null != this.evaluator) {
                this.evaluator.evaluate();
            }
            if (this.center_offset[0] != 0.0 || this.center_offset[1] != 0.0 || this.center_offset[2] != 0.0 || this.sizes_scales[0] != 1.0 || this.sizes_scales[1] != 1.0 || this.sizes_scales[2] != 1.0) {
                this.center_offset[0] = 0.0;
                this.center_offset[1] = 0.0;
                this.center_offset[2] = 0.0;
                this.sizes_scales[0] = 1.0;
                this.sizes_scales[1] = 1.0;
                this.sizes_scales[2] = 1.0;
                this.apply_new_offset();
            }
            this.is_enforced_reprojection = true;
            this.is_new_dragging = true;
        }
        return true;
    }
    
    void apply_new_offset() {
        for (int frame_index = 0; frame_index < this.frames.size(); ++frame_index) {
            final Graphics3D g3D = this.frames.elementAt(frame_index);
            g3D.preparePrimitives(this.back_graphics_buffer, this.evaluator, this.center_offset, this.sizes_scales);
        }
        if (null != this.evaluator) {
            this.evaluator.evaluate();
        }
        if (this.frames.size() > 1) {
            for (int frame_index = 0; frame_index < this.frames.size(); ++frame_index) {
                final Graphics3D g3D = this.frames.elementAt(frame_index);
                g3D.projectPoints(true);
            }
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        if (null != this.front_image_buffer) {
            g.drawImage(this.front_image_buffer, 0, 0, this);
        }
        else if (null != this.back_image_buffer) {
            g.drawImage(this.back_image_buffer, 0, 0, this);
        }
    }
    
    public void paintGraphics3D() {
        if (this.ps3D.is_stereo && null == this.second_graphics_buffer) {
            this.second_image_buffer = this.createImage((this.applet_width + 1) / 2, this.applet_height);
            this.second_graphics_buffer = this.second_image_buffer.getGraphics();
        }
        if (null == this.bg_image) {
            this.back_graphics_buffer.setColor(this.ps3D.option_Background);
            this.back_graphics_buffer.fillRect(0, 0, this.ps3D.pixel_width, this.ps3D.pixel_height);
            if (this.ps3D.is_stereo) {
                this.second_graphics_buffer.setColor(this.ps3D.option_Background);
                this.second_graphics_buffer.fillRect(0, 0, this.ps3D.pixel_width, this.ps3D.pixel_height);
            }
        }
        else {
            Image first_bg_image = this.bg_image;
            Image first_bg_rotated_image = this.bg_rotated_image;
            Image second_bg_image = this.bg_right_image;
            Image second_bg_rotated_image = this.bg_rotated_right_image;
            if (this.ps3D.is_stereo && this.ps3D.stereo_distance < 0.0) {
                first_bg_image = this.bg_right_image;
                first_bg_rotated_image = this.bg_rotated_right_image;
                second_bg_image = this.bg_image;
                second_bg_rotated_image = this.bg_rotated_image;
            }
            if (this.bg_is_spherical) {
                final int bg_rotated_x_offset = (this.bg_width / 2 + this.bg_width - this.bg_x_offset) % this.bg_width;
                final int start_y = ((this.ps3D.pixel_height - this.bg_height) / 2 + this.bg_height + this.bg_y_offset) % (this.bg_height + this.bg_height) - this.bg_height;
                int start_x = 0;
                if (start_y > 0) {
                    int x;
                    for (start_x = (x = ((this.ps3D.pixel_width + this.bg_width) / 2 + bg_rotated_x_offset - (int)this.ps3D.pixel_stereo_offset) % this.bg_width - this.bg_width); x < this.ps3D.pixel_width; x += this.bg_width) {
                        this.back_graphics_buffer.drawImage(first_bg_rotated_image, x, start_y - this.bg_height, null);
                    }
                    if (this.ps3D.is_stereo) {
                        for (start_x = (x = ((this.ps3D.pixel_width + this.bg_width) / 2 + bg_rotated_x_offset + (int)this.ps3D.pixel_stereo_offset) % this.bg_width - this.bg_width); x < this.ps3D.pixel_width; x += this.bg_width) {
                            this.second_graphics_buffer.drawImage(second_bg_rotated_image, x, start_y - this.bg_height, null);
                        }
                    }
                }
                int x;
                for (start_x = (x = ((this.ps3D.pixel_width + this.bg_width) / 2 + this.bg_x_offset - (int)this.ps3D.pixel_stereo_offset) % this.bg_width - this.bg_width); x < this.ps3D.pixel_width; x += this.bg_width) {
                    this.back_graphics_buffer.drawImage(first_bg_image, x, start_y, null);
                }
                if (this.ps3D.is_stereo) {
                    for (start_x = (x = ((this.ps3D.pixel_width + this.bg_width) / 2 + this.bg_x_offset + (int)this.ps3D.pixel_stereo_offset) % this.bg_width - this.bg_width); x < this.ps3D.pixel_width; x += this.bg_width) {
                        this.second_graphics_buffer.drawImage(second_bg_image, x, start_y, null);
                    }
                }
                if (start_y + this.bg_height < this.ps3D.pixel_height) {
                    for (start_x = (x = ((this.ps3D.pixel_width + this.bg_width) / 2 + bg_rotated_x_offset - (int)this.ps3D.pixel_stereo_offset) % this.bg_width - this.bg_width); x < this.ps3D.pixel_width; x += this.bg_width) {
                        this.back_graphics_buffer.drawImage(first_bg_rotated_image, x, start_y + this.bg_height, null);
                    }
                    if (this.ps3D.is_stereo) {
                        for (start_x = (x = ((this.ps3D.pixel_width + this.bg_width) / 2 + bg_rotated_x_offset + (int)this.ps3D.pixel_stereo_offset) % this.bg_width - this.bg_width); x < this.ps3D.pixel_width; x += this.bg_width) {
                            this.second_graphics_buffer.drawImage(second_bg_rotated_image, x, start_y + this.bg_height, null);
                        }
                    }
                }
            }
            else {
                int local_pixel_stereo_offset = (int)this.ps3D.pixel_stereo_offset;
                if (this.bg_is_fixed) {
                    local_pixel_stereo_offset = 0;
                }
                int y;
                for (int start_y = y = ((this.ps3D.pixel_height + this.bg_height) / 2 + this.bg_y_offset) % this.bg_height - this.bg_height; y < this.ps3D.pixel_height; y += this.bg_height) {
                    int x2;
                    for (int start_x2 = x2 = ((this.ps3D.pixel_width + this.bg_width) / 2 + this.bg_x_offset - local_pixel_stereo_offset) % this.bg_width - this.bg_width; x2 < this.ps3D.pixel_width; x2 += this.bg_width) {
                        this.back_graphics_buffer.drawImage(first_bg_image, x2, y, null);
                    }
                    if (this.ps3D.is_stereo) {
                        for (int start_x2 = x2 = ((this.ps3D.pixel_width + this.bg_width) / 2 + this.bg_x_offset + local_pixel_stereo_offset) % this.bg_width - this.bg_width; x2 < this.ps3D.pixel_width; x2 += this.bg_width) {
                            this.second_graphics_buffer.drawImage(second_bg_image, x2, y, null);
                        }
                    }
                }
            }
        }
        this.ps3D.paint(this.back_graphics_buffer, this.second_graphics_buffer, this.second_image_buffer, this.evaluator);
        this.painted_ps3D = this.ps3D;
        final Image temp_image_buffer = this.front_image_buffer;
        final Graphics temp_graphics_buffer = this.front_graphics_buffer;
        this.front_image_buffer = this.back_image_buffer;
        this.front_graphics_buffer = this.back_graphics_buffer;
        this.back_image_buffer = temp_image_buffer;
        this.back_graphics_buffer = temp_graphics_buffer;
        if (null != this.active_primitive) {
            if (0 == this.active_primitive.is_inactive_flags) {
                this.drawTextRectangle(this.active_primitive);
            }
            else {
                this.active_primitive = null;
                this.getAppletContext().showStatus(Live.empty_string);
            }
        }
        if (null != this.dragged_point && 0 != this.dragged_point.is_inactive_flags) {
            this.dragged_point = null;
            this.llast_drag_time = 0L;
            this.is_dragging = false;
            this.is_dragging_point = false;
            this.is_shift_down = false;
            this.is_meta_down = false;
            this.is_control_down = false;
        }
        this.repaint();
    }
    
    static {
        Live.empty_string = "";
        Live.enter_plain_string = "LiveGraphics3D 1.80";
        Live.enter_drag_string = "LiveGraphics3D 1.80: Please drag to rotate.";
        Live.enter_drag_animated_string = "LiveGraphics3D 1.80: Drag to rotate; double click to stop/restart.";
        Live.enter_animated_string = "LiveGraphics3D 1.80: Please double click to stop/restart.";
        Live.enter_init_string = "Initializing LiveGraphics3D 1.80. Please wait.";
        Live.syntax_error_string = "LiveGraphics3D applet stopped because of a syntax error.";
        Live.url_error_string = "Malformed URL: ";
        Live.target_string = "target=";
        Live.double_chars = ".+-0123456789eE";
    }
}
