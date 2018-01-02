import java.awt.image.PixelGrabber;
import java.util.Stack;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class draw_panel extends Canvas implements groupboard_consts
{
    int[] fillPixels;
    boolean[] fillDone;
    int prev_width;
    int prev_height;
    boolean warning_given;
    static final int DEFAULT_MAX_DRAW_ARRAY_SIZE = 100000;
    int max_draw_array_size;
    static final int CIRCLE_STEPS = 40;
    String font_name;
    static final int FONTSIZE1 = 10;
    static final int FONTSIZE2 = 15;
    static final int FONTSIZE3 = 20;
    static final int FONTSIZE4 = 25;
    static final int FONTSIZE5 = 30;
    static final int FONTSIZE6 = 35;
    static final int FONTSIZE7 = 40;
    static final int FONTSIZE8 = 45;
    static final int FONTSIZE9 = 50;
    static final int FONTSIZE10 = 55;
    static final int MAX_TEXT_LEN = 400;
    static final int MAX_COMPRESSED_DATA = 4;
    static final int MIN_LEN_SQUARED = 25;
    save_pic_window pic_win;
    private int last_ibeam_x;
    private int last_ibeam_y;
    int[] compress_dx;
    int[] compress_dy;
    String last_name;
    String last_bg;
    Image last_bg_image;
    long last_name_time;
    private int last_name_x;
    private int last_name_y;
    boolean use_compression;
    boolean use_lossy_compression;
    int compressed_used;
    private int last_set_type;
    private int last_x;
    private int last_y;
    private int last_thickness;
    private int last_id;
    private Color last_col;
    Color brown;
    Color[] col_array;
    Hashtable col_names;
    private Font[] font_list;
    Image off_screen_buf;
    Graphics off_screen_gc;
    draw_log_window log_win;
    boolean clear_disabled;
    boolean draw_disabled;
    boolean load_disabled;
    Color current_colour;
    int items_used;
    int thickness;
    draw_item[] draw_data;
    Object mutex;
    private int draw_mode;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int i;
    private String string_buf;
    int string_thickness;
    groupboard parent;
    private boolean in_gallery;
    private boolean multiuser;
    groupboard_gui gui;
    private Color bg_colour;
    private Color xor_colour;
    
    draw_panel(final groupboard parent, final groupboard_gui gui, final boolean in_gallery, final boolean multiuser, final int n, final int n2, final int n3) {
        this.max_draw_array_size = 100000;
        this.font_name = "TimesRoman";
        this.compress_dx = new int[4];
        this.compress_dy = new int[4];
        this.use_compression = true;
        this.use_lossy_compression = false;
        this.compressed_used = 0;
        this.brown = new Color(165, 42, 42);
        this.col_array = new Color[256];
        this.col_names = new Hashtable();
        this.clear_disabled = false;
        this.draw_disabled = false;
        this.load_disabled = false;
        this.warning_given = false;
        this.max_draw_array_size = parent.get_int_arg("MAX_DRAW_ARRAY_SIZE", 100000);
        final String get_arg = parent.get_arg("FONT_NAME");
        if (null != get_arg) {
            this.font_name = get_arg;
        }
        else if (parent.use_utf) {
            if (parent.new_jdk) {
                this.font_name = "serif";
            }
            else {
                this.font_name = "TimesRoman";
            }
        }
        (this.font_list = new Font[10])[0] = new Font(this.font_name, 0, 10);
        this.font_list[1] = new Font(this.font_name, 0, 15);
        this.font_list[2] = new Font(this.font_name, 0, 20);
        this.font_list[3] = new Font(this.font_name, 0, 25);
        this.font_list[4] = new Font(this.font_name, 0, 30);
        this.font_list[5] = new Font(this.font_name, 0, 35);
        this.font_list[6] = new Font(this.font_name, 0, 40);
        this.font_list[7] = new Font(this.font_name, 0, 45);
        this.font_list[8] = new Font(this.font_name, 0, 50);
        this.font_list[9] = new Font(this.font_name, 0, 55);
        for (int i = 0; i < 256; ++i) {
            this.col_array[i] = Color.black;
        }
        this.col_array[0] = Color.white;
        this.col_array[1] = Color.red;
        this.col_array[2] = Color.green;
        this.col_array[3] = Color.blue;
        this.col_array[4] = Color.pink;
        this.col_array[5] = Color.orange;
        this.col_array[6] = Color.black;
        this.col_array[7] = Color.gray;
        this.col_array[8] = Color.magenta;
        this.col_array[9] = Color.cyan;
        this.col_array[10] = this.brown;
        this.col_names.put(Color.white, "white");
        this.col_names.put(Color.red, "red");
        this.col_names.put(Color.green, "green");
        this.col_names.put(Color.blue, "blue");
        this.col_names.put(Color.pink, "pink");
        this.col_names.put(Color.orange, "orange");
        this.col_names.put(Color.black, "black");
        this.col_names.put(Color.gray, "gray");
        this.col_names.put(Color.magenta, "magenta");
        this.col_names.put(Color.cyan, "cyan");
        this.col_names.put(this.brown, "brown");
        for (int j = 0; j < 6; ++j) {
            for (int k = 0; k < 6; ++k) {
                for (int l = 0; l < 6; ++l) {
                    this.col_array[j + k * 6 + l * 36 + 12] = new Color(j * 51, k * 51, l * 51);
                }
            }
        }
        final int n4 = -1;
        this.last_ibeam_y = n4;
        this.last_ibeam_x = n4;
        this.xor_colour = new Color(parent.get_int_arg("DRAWING_USER_RED", 255), parent.get_int_arg("DRAWING_USER_GREEN", 255), parent.get_int_arg("DRAWING_USER_BLUE", 255));
        this.use_compression = parent.get_boolean_arg("USE_COMPRESSION", this.use_compression);
        this.use_lossy_compression = parent.get_boolean_arg("USE_LOSSY_COMPRESSION", this.use_lossy_compression);
        this.mutex = new Object();
        this.multiuser = multiuser;
        this.in_gallery = in_gallery;
        this.parent = parent;
        this.gui = gui;
        if (groupboard.is_applet) {
            this.off_screen_buf = parent.createImage(parent.board_width, parent.board_height);
        }
        else {
            final Frame frame = new Frame();
            frame.pack();
            frame.show();
            this.off_screen_buf = frame.createImage(parent.board_width, parent.board_height);
            frame.dispose();
        }
        this.off_screen_gc = this.off_screen_buf.getGraphics();
        this.bg_colour = new Color(n, n2, n3);
        if (null == this.bg_colour) {
            System.out.println("Can't create colour");
            this.bg_colour = Color.white;
        }
        this.setBackground(this.bg_colour);
        this.setForeground(Color.black);
        this.set_current_colour(this.col_array[parent.get_int_arg("DEFAULT_COLOUR", 6)]);
        this.off_screen_gc.setColor(this.bg_colour);
        this.off_screen_gc.fillRect(0, 0, parent.board_width, parent.board_height);
        this.draw_data = new draw_item[this.max_draw_array_size];
        this.thickness = parent.get_int_arg("DEFAULT_THICKNESS", 1);
        this.string_thickness = this.thickness;
        this.items_used = 0;
        this.string_buf = "";
        this.draw_mode = parent.get_int_arg("DEFAULT_TOOL", 4);
        this.update_panel(this.off_screen_gc, false);
    }
    
    void set_background(String string) {
        if (null != string) {
            if (-1 == string.indexOf(47)) {
                final int get_int_arg = this.parent.get_int_arg("BOARD_ID", 1);
                string = "data/" + get_int_arg / 1000 + "/" + get_int_arg % 1000 + "/backgrounds/" + string;
            }
            this.last_bg = string;
            if (this.parent.get_boolean_arg("CLEAR_ON_SET_BACKGROUND", true)) {
                this.clear_array(true, true);
            }
            this.add_to_array(10, 0, 0, 0, 0, 0, 0, string, null, true);
            final Graphics graphics = this.getGraphics();
            this.update_panel(this.off_screen_gc, true);
            this.update_panel(graphics, true);
            graphics.dispose();
        }
    }
    
    void set_current_colour(final Color current_colour) {
        this.current_colour = current_colour;
    }
    
    void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, int n5) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        if (this.parent.new_jdk) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            if (!this.parent.disable_anti_aliasing) {
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        }
        if (n5 == 1) {
            graphics.drawLine(n, n2, n3, n4);
        }
        else {
            --n5;
            if (this.parent.new_jdk) {
                final Line2D.Float float1 = new Line2D.Float(n, n2, n3, n4);
                final Graphics2D graphics2D2 = (Graphics2D)graphics;
                final Stroke stroke = graphics2D2.getStroke();
                graphics2D2.setStroke(new BasicStroke(n5 * 2, 1, 2, 0.0f, null, 0.0f));
                graphics2D2.draw(float1);
                graphics2D2.setStroke(stroke);
                return;
            }
            graphics.fillOval(n - n5, n2 - n5, n5 * 2, n5 * 2);
            graphics.fillOval(n3 - n5, n4 - n5, n5 * 2, n5 * 2);
            final float n6 = n3 - n;
            final float n7 = n4 - n2;
            final float n8 = n5 * (float)Math.sqrt(1.0 / (1.0f + n6 * n6 / (n7 * n7)));
            float n9;
            if (n7 == 0.0f) {
                n9 = n5;
            }
            else {
                n9 = -(n8 * n6) / n7;
            }
            array[0] = Math.round(n - n8);
            array[1] = Math.round(n + n8);
            array[2] = Math.round(n3 + n8);
            array[3] = Math.round(n3 - n8);
            array2[0] = Math.round(n2 - n9);
            array2[1] = Math.round(n2 + n9);
            array2[2] = Math.round(n4 + n9);
            array2[3] = Math.round(n4 - n9);
            graphics.fillPolygon(array, array2, 4);
        }
    }
    
    void set_thickness(final int thickness) {
        this.thickness = thickness;
    }
    
    void set_draw_mode(final int draw_mode) {
        if (this.draw_mode == 3 && draw_mode != 3 && this.string_buf.length() != 0) {
            this.add_to_array(3, this.x1, this.y1, 0, 0, this.string_thickness, 0, this.string_buf, this.current_colour, true);
            this.string_buf = "";
            final Graphics graphics = this.getGraphics();
            this.update_panel(this.off_screen_gc, true);
            this.update_panel(graphics, true);
            graphics.dispose();
        }
        this.draw_mode = draw_mode;
    }
    
    public boolean handleEvent(final Event event) {
        this.parent.last_event_time = System.currentTimeMillis();
        switch (event.id) {
            case 401: {
                if (this.draw_mode == 3 && !this.parent.use_utf) {
                    this.remove_ibeam();
                    final Graphics graphics = this.getGraphics();
                    if (!this.in_gallery && !this.draw_disabled) {
                        if (10 == event.key) {
                            if (this.string_buf.length() != 0) {
                                this.add_to_array(3, this.x1, this.y1, 0, 0, this.string_thickness, 0, this.string_buf, this.current_colour, true);
                                this.string_buf = "";
                                this.update_panel(this.off_screen_gc, true);
                                this.update_panel(graphics, true);
                            }
                            graphics.setFont(this.font_list[this.string_thickness - 1]);
                            this.off_screen_gc.setFont(this.font_list[this.string_thickness - 1]);
                            this.y1 += graphics.getFontMetrics().getHeight();
                        }
                        else if (8 == event.key || 127 == event.key) {
                            if (this.string_buf.length() != 0) {
                                this.string_buf = this.string_buf.substring(0, this.string_buf.length() - 1);
                            }
                            this.repaint();
                        }
                        else {
                            if (this.string_buf.length() < 400) {
                                this.string_buf += (char)event.key;
                            }
                            if (this.string_buf.length() == 1) {
                                this.string_thickness = this.thickness;
                            }
                            this.update_text(graphics);
                        }
                        graphics.setFont(this.font_list[this.string_thickness - 1]);
                        this.display_ibeam(this.x1 + graphics.getFontMetrics().stringWidth(this.string_buf), this.y1);
                    }
                    graphics.dispose();
                    return true;
                }
                return false;
            }
            case 501: {
                if (!this.in_gallery && !this.draw_disabled) {
                    switch (this.draw_mode) {
                        case 4: {
                            this.x1 = event.x;
                            this.y1 = event.y;
                            this.last_col = null;
                            this.compressed_used = 0;
                            return true;
                        }
                        case 3: {
                            if (this.string_buf.length() > 0) {
                                this.add_to_array(3, this.x1, this.y1, 0, 0, this.string_thickness, 0, this.string_buf, this.current_colour, true);
                                this.string_buf = "";
                                final Graphics graphics2 = this.getGraphics();
                                this.update_panel(this.off_screen_gc, true);
                                this.update_panel(graphics2, true);
                                graphics2.dispose();
                            }
                            if (this.parent.use_utf) {
                                break;
                            }
                            this.display_ibeam(event.x, event.y);
                            break;
                        }
                    }
                    this.x1 = event.x;
                    this.y1 = event.y;
                }
                return true;
            }
            case 502: {
                if (this.draw_disabled) {
                    new message_box(this.parent, "Error", "Drawing is currently disabled");
                }
                if (!this.in_gallery && !this.draw_disabled) {
                    final Graphics graphics3 = this.getGraphics();
                    this.copy_from_offscreen_buf(graphics3);
                    graphics3.dispose();
                    switch (this.draw_mode) {
                        case 3: {
                            if (this.parent.use_utf) {
                                if (null != this.parent.tb) {
                                    this.parent.tb.dispose();
                                }
                                this.parent.tb = new text_box(this.parent, event.x, event.y);
                                break;
                            }
                            final Graphics graphics4 = this.getGraphics();
                            this.display_ibeam(this.x1 + graphics4.getFontMetrics().stringWidth(this.string_buf), this.y1);
                            graphics4.dispose();
                            break;
                        }
                        case 1:
                        case 2:
                        case 11:
                        case 12:
                        case 13: {
                            this.last_col = null;
                            this.add_to_array(this.draw_mode, this.x1, this.y1, event.x - this.x1, event.y - this.y1, this.thickness, 0, "", this.current_colour, true);
                            final Graphics graphics5 = this.getGraphics();
                            this.update_panel(this.off_screen_gc, true);
                            this.update_panel(graphics5, true);
                            graphics5.dispose();
                            break;
                        }
                        case 64: {
                            if (this.items_used == 1 && this.draw_data[0].object_type == 64) {
                                this.clear_array(true, true);
                            }
                            this.add_to_array(64, this.x1, this.y1, this.x1, this.y1, this.thickness, 0, "", this.current_colour, true);
                            this.x1 = event.x;
                            this.y1 = event.y;
                            this.update_panel(this.off_screen_gc, true);
                            final Graphics graphics6 = this.getGraphics();
                            this.copy_from_offscreen_buf(graphics6);
                            graphics6.dispose();
                            break;
                        }
                        case 4: {
                            this.add_to_array(1, this.x1, this.y1, event.x - this.x1, event.y - this.y1, this.thickness, 0, "", this.current_colour, true);
                            this.x1 = event.x;
                            this.y1 = event.y;
                            final Graphics graphics7 = this.getGraphics();
                            this.update_panel(this.off_screen_gc, true);
                            this.update_panel(graphics7, true);
                            graphics7.dispose();
                            if (this.use_compression && this.compressed_used > 0) {
                                this.send_compressed_data();
                                break;
                            }
                            break;
                        }
                    }
                }
                return true;
            }
            case 506: {
                if (!this.in_gallery && !this.draw_disabled) {
                    switch (this.draw_mode) {
                        case 1:
                        case 2:
                        case 11:
                        case 12:
                        case 13: {
                            this.x2 = event.x;
                            this.y2 = event.y;
                            final Graphics graphics8 = this.getGraphics();
                            this.copy_from_offscreen_buf(graphics8);
                            graphics8.setColor(this.current_colour);
                            switch (this.draw_mode) {
                                case 1: {
                                    this.drawLine(graphics8, this.x1, this.y1, this.x2, this.y2, this.thickness);
                                    break;
                                }
                                case 2: {
                                    this.draw_oval(graphics8, this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1, this.thickness);
                                    break;
                                }
                                case 12: {
                                    if (this.parent.new_jdk) {
                                        final Graphics2D graphics2D = (Graphics2D)graphics8;
                                        if (!this.parent.disable_anti_aliasing) {
                                            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                        }
                                    }
                                    graphics8.fillOval(Math.min(this.x1, this.x2), Math.min(this.y1, this.y2), Math.abs(this.x2 - this.x1), Math.abs(this.y2 - this.y1));
                                    break;
                                }
                                case 11: {
                                    this.drawLine(graphics8, this.x1, this.y1, this.x1, this.y2, this.thickness);
                                    this.drawLine(graphics8, this.x2, this.y1, this.x2, this.y2, this.thickness);
                                    this.drawLine(graphics8, this.x1, this.y2, this.x2, this.y2, this.thickness);
                                    this.drawLine(graphics8, this.x1, this.y1, this.x2, this.y1, this.thickness);
                                    break;
                                }
                                case 13: {
                                    graphics8.fillRect(Math.min(this.x1, this.x2), Math.min(this.y1, this.y2), Math.abs(this.x2 - this.x1), Math.abs(this.y2 - this.y1));
                                    break;
                                }
                            }
                            graphics8.dispose();
                            break;
                        }
                        case 4: {
                            if (this.use_compression && this.use_lossy_compression) {
                                final int n = event.x - this.x1;
                                final int n2 = event.y - this.y1;
                                if (n * n + n2 * n2 < 25) {
                                    final Graphics graphics9 = this.getGraphics();
                                    this.copy_from_offscreen_buf(graphics9);
                                    graphics9.setColor(this.current_colour);
                                    this.drawLine(graphics9, this.x1, this.y1, event.x, event.y, this.thickness);
                                    graphics9.dispose();
                                    break;
                                }
                            }
                            this.add_to_array(1, this.x1, this.y1, event.x - this.x1, event.y - this.y1, this.thickness, 0, "", this.current_colour, true);
                            this.x1 = event.x;
                            this.y1 = event.y;
                            final Graphics graphics10 = this.getGraphics();
                            this.update_panel(this.off_screen_gc, true);
                            this.update_panel(graphics10, true);
                            graphics10.dispose();
                            break;
                        }
                    }
                }
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    void draw_oval(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        double n7;
        for (double n6 = 0.0; n6 < 6.283185307179586; n6 = n7) {
            n7 = n6 + 0.15707963267948966;
            this.drawLine(graphics, (int)(n + n3 * Math.sin(n6)), (int)(n2 + n4 * Math.cos(n6)), (int)(n + n3 * Math.sin(n7)), (int)(n2 + n4 * Math.cos(n7)), n5);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.copy_from_offscreen_buf(graphics);
        this.update_text(graphics);
    }
    
    void update_text(final Graphics graphics) {
        graphics.setColor(this.current_colour);
        if (this.string_buf.length() != 0 && this.draw_mode == 3) {
            graphics.setFont(this.font_list[this.string_thickness - 1]);
            graphics.drawString(this.string_buf, this.x1, this.y1);
        }
    }
    
    void update_panel(final Graphics graphics, final boolean b) {
        if (graphics != this.off_screen_gc && this.parent.get_boolean_arg("mirror", false)) {
            this.repaint();
            return;
        }
        synchronized (this.mutex) {
            for (int i = 0; i < this.items_used; ++i) {
                if (i == 0 && b) {
                    i = this.items_used - 1;
                }
                graphics.setColor(this.draw_data[i].col);
                switch (this.draw_data[i].object_type) {
                    case 1: {
                        final int x = this.draw_data[i].x;
                        final int y = this.draw_data[i].y;
                        this.drawLine(graphics, x, y, x + this.draw_data[i].width, y + this.draw_data[i].height, this.draw_data[i].thickness);
                        break;
                    }
                    case 3: {
                        if (this.parent.new_jdk) {
                            final Graphics2D graphics2D = (Graphics2D)graphics;
                            if (!this.parent.disable_anti_aliasing) {
                                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            }
                        }
                        graphics.setFont(this.font_list[this.draw_data[i].thickness - 1]);
                        graphics.drawString(this.draw_data[i].text, this.draw_data[i].x, this.draw_data[i].y);
                        break;
                    }
                    case 64: {
                        if (this.parent.get_boolean_arg("DISABLE_FLOOD_FILL", false)) {
                            break;
                        }
                        if (i == 0) {
                            final Rectangle bounds = this.bounds();
                            graphics.fillRect(0, 0, bounds.width, bounds.height);
                            break;
                        }
                        if (graphics == this.off_screen_gc) {
                            this.fill_area(this.off_screen_buf, this.off_screen_gc, this.draw_data[i].x, this.draw_data[i].y);
                            break;
                        }
                        this.copy_from_offscreen_buf(graphics);
                        break;
                    }
                    case 2: {
                        this.draw_oval(graphics, this.draw_data[i].x, this.draw_data[i].y, this.draw_data[i].width, this.draw_data[i].height, this.draw_data[i].thickness);
                        break;
                    }
                    case 12: {
                        final int x2 = this.draw_data[i].x;
                        final int y2 = this.draw_data[i].y;
                        final int n = x2 + this.draw_data[i].width;
                        final int n2 = y2 + this.draw_data[i].height;
                        if (this.parent.new_jdk) {
                            final Graphics2D graphics2D2 = (Graphics2D)graphics;
                            if (!this.parent.disable_anti_aliasing) {
                                graphics2D2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            }
                        }
                        graphics.fillOval(Math.min(x2, n), Math.min(y2, n2), Math.abs(this.draw_data[i].width), Math.abs(this.draw_data[i].height));
                        break;
                    }
                    case 13: {
                        final int x3 = this.draw_data[i].x;
                        final int y3 = this.draw_data[i].y;
                        graphics.fillRect(Math.min(x3, x3 + this.draw_data[i].width), Math.min(y3, y3 + this.draw_data[i].height), Math.abs(this.draw_data[i].width), Math.abs(this.draw_data[i].height));
                        break;
                    }
                    case 11: {
                        final int x4 = this.draw_data[i].x;
                        final int y4 = this.draw_data[i].y;
                        final int n3 = x4 + this.draw_data[i].width;
                        final int n4 = y4 + this.draw_data[i].height;
                        this.drawLine(graphics, x4, y4, x4, n4, this.draw_data[i].thickness);
                        this.drawLine(graphics, n3, y4, n3, n4, this.draw_data[i].thickness);
                        this.drawLine(graphics, x4, n4, n3, n4, this.draw_data[i].thickness);
                        this.drawLine(graphics, x4, y4, n3, y4, this.draw_data[i].thickness);
                        break;
                    }
                    case 10: {
                        final String replace_all_text = this.parent.replace_all_text(this.draw_data[i].text, " ", "%20");
                        if (null == this.draw_data[i].image) {
                            this.draw_data[i].image = this.parent.load_image(replace_all_text);
                            final groupboard parent = this.parent;
                            if (groupboard.is_applet) {
                                System.out.println("setting background " + this.parent.getCodeBase().toString() + replace_all_text);
                            }
                            else {
                                System.out.println("setting background " + replace_all_text);
                            }
                        }
                        int n5 = 0;
                        int n6 = 0;
                        if (this.parent.get_boolean_arg("CENTRE_IN_WINDOW", false)) {
                            n5 = (this.parent.board_width - this.draw_data[i].image.getWidth(this)) / 2;
                            n6 = (this.parent.board_height - this.draw_data[i].image.getHeight(this)) / 2;
                        }
                        graphics.drawImage(this.draw_data[i].image, n5, n6, this);
                        this.last_bg_image = this.draw_data[i].image;
                        this.last_bg = replace_all_text;
                        break;
                    }
                }
            }
            this.update_text(graphics);
        }
    }
    
    void copy_from_offscreen_buf(final Graphics graphics) {
        final int n = -1;
        this.last_ibeam_y = n;
        this.last_ibeam_x = n;
        synchronized (this.mutex) {
            if (null != graphics) {
                if (this.parent.get_boolean_arg("mirror", false)) {
                    graphics.drawImage(this.off_screen_buf, 0, 0, this.parent.board_width - 1, this.parent.board_height - 1, this.parent.board_width - 1, 0, 0, this.parent.board_height - 1, null);
                }
                else {
                    graphics.drawImage(this.off_screen_buf, 0, 0, null);
                }
            }
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (0x0 != (n & 0x20)) {
            this.parent.log_message("");
            this.update_panel(this.off_screen_gc, false);
            this.repaint();
        }
        else if (0x0 != (n & 0x40)) {
            this.parent.log_message("Error downloading background picture");
        }
        else {
            final Graphics graphics = this.getGraphics();
            if (null != graphics) {
                if ((this.last_bg_image == null || this.last_bg_image == image) && this.parent.new_jdk) {
                    graphics.drawImage(image, n2, n3, n2 + n4 - 1, n3 + n5 - 1, n2, n3, n2 + n4 - 1, n3 + n5 - 1, this);
                }
                graphics.dispose();
            }
        }
        return (n & 0x60) == 0x0;
    }
    
    draw_item find_last_drawing_action(final int n) {
        for (int i = this.items_used - 1; i >= 0; --i) {
            if (this.draw_data[i].object_type == 1 && this.draw_data[i].id == n) {
                return this.draw_data[i];
            }
        }
        return null;
    }
    
    void add_to_array(final int object_type, final int x, final int y, final int width, final int height, final int n, final int n2, final String text, final Color color, boolean b) {
        if (this.use_compression && b) {
            if (object_type == 1 && width > -128 && width < 128 && height > -128 && height < 128 && this.last_thickness == n && this.last_id == n2 && this.last_col == color) {
                this.compress_dx[this.compressed_used] = width;
                this.compress_dy[this.compressed_used] = height;
                ++this.compressed_used;
                b = false;
                if (this.compressed_used >= 4) {
                    this.send_compressed_data();
                }
            }
            else if (this.compressed_used > 0) {
                this.send_compressed_data();
            }
            this.last_x = x + width;
            this.last_y = y + height;
            this.last_thickness = n;
            this.last_id = n2;
            this.last_col = color;
        }
        synchronized (this.mutex) {
            if (this.items_used >= this.max_draw_array_size) {
                if (!this.warning_given) {
                    this.warning_given = true;
                    new message_box(this.parent, "Error", "Maximum drawing element limit reached. You must increase MAX_DRAW_ARRAY_SIZE html parameter.");
                }
                return;
            }
            int i = 0;
            this.i = 0;
            while (this.i < this.col_array.length) {
                if (color == this.col_array[this.i]) {
                    i = this.i;
                    break;
                }
                ++this.i;
            }
            if (b) {
                try {
                    synchronized (this.parent.os) {
                        this.parent.os.writeByte(object_type);
                        this.parent.os.writeByte(n);
                        this.parent.os.writeShort(x);
                        this.parent.os.writeShort(y);
                        this.parent.os.writeShort(width);
                        this.parent.os.writeShort(height);
                        this.parent.os.writeByte(i);
                        String string_to_utf = text;
                        if (this.parent.use_utf) {
                            string_to_utf = this.parent.string_to_utf(string_to_utf);
                        }
                        this.parent.os.writeShort(string_to_utf.length());
                        if (string_to_utf.length() > 0) {
                            this.parent.os.writeBytes(string_to_utf);
                        }
                    }
                }
                catch (IOException ex) {
                    this.parent.dataError();
                }
            }
            if (this.items_used > 0 && object_type == 10 && this.parent.get_boolean_arg("CLEAR_ON_SET_BACKGROUND", true)) {
                this.clear_array(false, true);
            }
            else if (this.items_used > 0 && this.draw_data[this.items_used - 1].object_type == 10 && object_type == 10 && text == this.draw_data[this.items_used - 1].text) {
                --this.items_used;
            }
            this.draw_data[this.items_used] = new draw_item();
            this.draw_data[this.items_used].object_type = object_type;
            this.draw_data[this.items_used].x = x;
            this.draw_data[this.items_used].y = y;
            this.draw_data[this.items_used].width = width;
            this.draw_data[this.items_used].height = height;
            this.draw_data[this.items_used].thickness = n;
            this.draw_data[this.items_used].text = text;
            this.draw_data[this.items_used].col = color;
            this.draw_data[this.items_used].id = n2;
            if (null != this.log_win) {
                this.log_win.add_item(this.draw_data[this.items_used]);
            }
            ++this.items_used;
        }
    }
    
    void save_pic() {
        if (null == this.pic_win) {
            if (this.string_buf.length() > 0) {
                this.add_to_array(3, this.x1, this.y1, 0, 0, this.string_thickness, 0, this.string_buf, this.current_colour, true);
                this.string_buf = "";
                final Graphics graphics = this.getGraphics();
                this.update_panel(this.off_screen_gc, true);
                this.update_panel(graphics, true);
                graphics.dispose();
            }
            this.pic_win = new save_pic_window(this.parent, this);
            if (null != this.parent.get_arg("SAVE_FILENAME")) {
                this.pic_win = null;
            }
        }
        else {
            this.pic_win.toFront();
        }
    }
    
    void show_log() {
        if (null == this.log_win) {
            this.log_win = new draw_log_window(this);
        }
    }
    
    void clear_array(final boolean b, final boolean b2) {
        if (b) {
            try {
                this.parent.os.writeByte(5);
            }
            catch (IOException ex) {
                this.parent.dataError();
            }
            if (this.parent.use_tunnel) {
                this.parent.t.force_post();
            }
        }
        final Graphics graphics = this.getGraphics();
        if (null != graphics) {
            synchronized (this.mutex) {
                this.items_used = 0;
                this.string_buf = "";
                final Rectangle bounds = this.bounds();
                graphics.clearRect(0, 0, bounds.width, bounds.height);
                this.off_screen_gc.setColor(this.bg_colour);
                this.off_screen_gc.fillRect(0, 0, bounds.width, bounds.height);
                if (!b && !b2) {
                    this.last_bg = null;
                    this.last_bg_image = null;
                }
                if (b && !b2) {
                    if (null != this.last_bg) {
                        this.add_to_array(10, 0, 0, 0, 0, 0, 0, this.last_bg, null, true);
                    }
                    else {
                        final String get_arg = this.parent.get_arg("BACKGROUND_PIC");
                        if (null != get_arg) {
                            final int get_int_arg = this.parent.get_int_arg("BOARD_ID", 1);
                            this.add_to_array(10, 0, 0, 0, 0, 0, 0, "data/" + get_int_arg / 1000 + "/" + get_int_arg % 1000 + "/backgrounds/" + get_arg, null, true);
                        }
                    }
                }
                this.update_panel(this.off_screen_gc, false);
            }
            graphics.dispose();
            this.repaint();
        }
    }
    
    void send_compressed_data() {
        try {
            final int n = 40;
            final int n2 = 0;
            final int n3 = 0;
            synchronized (this.parent.os) {
                this.parent.os.writeByte(n);
                this.parent.os.writeByte(this.compressed_used);
                this.parent.os.writeByte(this.compress_dx[0]);
                this.parent.os.writeByte(this.compress_dy[0]);
                this.parent.os.writeByte(this.compress_dx[1]);
                this.parent.os.writeByte(this.compress_dy[1]);
                this.parent.os.writeByte(this.compress_dx[2]);
                this.parent.os.writeByte(this.compress_dy[2]);
                this.parent.os.writeByte(this.compress_dx[3]);
                this.parent.os.writeByte(this.compress_dy[3]);
                this.parent.os.writeByte(n2);
                this.parent.os.writeShort(n3);
            }
            this.compressed_used = 0;
        }
        catch (IOException ex) {
            this.parent.dataError();
        }
    }
    
    void remove_display_user() {
        if (null == this.gui.chat) {
            return;
        }
        if (null != this.last_name) {
            final Graphics graphics = this.getGraphics();
            if (null != graphics) {
                int get_int_arg = this.parent.get_int_arg("DRAWING_USER_FONTSIZE", 0);
                if (get_int_arg < 0) {
                    get_int_arg = 0;
                }
                if (get_int_arg > 9) {
                    get_int_arg = 9;
                }
                graphics.setFont(this.font_list[get_int_arg]);
                final int n = this.last_name_x - 1;
                final int n2 = this.last_name_y - 1;
                final int n3 = graphics.getFontMetrics().stringWidth(this.last_name) + 8;
                final int height = graphics.getFontMetrics().getHeight();
                final int n4 = n2 - height;
                final int n5 = height * 2;
                graphics.drawImage(this.off_screen_buf, n, n4, n + n3 - 1, n4 + n5 - 1, n, n4, n + n3 - 1, n4 + n5 - 1, null);
                graphics.dispose();
            }
            this.last_name = null;
        }
    }
    
    void display_user(final Graphics graphics, final int n, final int last_name_x, final int last_name_y) {
        if (null == this.gui.chat) {
            return;
        }
        synchronized (this.mutex) {
            String last_name = this.gui.chat.user_hash.get(new Integer(n));
            if (last_name == null) {
                last_name = "Unknown";
            }
            if (this.gui.chat.ip_hash.get(new Integer(n)) == null) {}
            int get_int_arg = this.parent.get_int_arg("DRAWING_USER_FONTSIZE", 0);
            if (get_int_arg < 0) {
                get_int_arg = 0;
            }
            if (get_int_arg > 9) {
                get_int_arg = 9;
            }
            if (!this.parent.is_mac_osx) {}
            graphics.setColor(Color.black);
            graphics.setFont(this.font_list[get_int_arg]);
            graphics.drawString(last_name, last_name_x, last_name_y);
            this.last_name = last_name;
            this.last_name_x = last_name_x;
            this.last_name_y = last_name_y;
            if (!this.parent.is_mac_osx) {}
            this.last_name_time = System.currentTimeMillis();
        }
    }
    
    void display_ibeam(final int last_ibeam_x, final int last_ibeam_y) {
        this.remove_ibeam();
        final Graphics graphics = this.getGraphics();
        graphics.setXORMode(this.xor_colour);
        this.drawLine(graphics, last_ibeam_x, last_ibeam_y, last_ibeam_x + this.thickness * 2, last_ibeam_y, 1);
        this.drawLine(graphics, last_ibeam_x, last_ibeam_y - this.thickness * 8, last_ibeam_x + this.thickness * 2, last_ibeam_y - this.thickness * 8, 1);
        this.drawLine(graphics, last_ibeam_x + this.thickness, last_ibeam_y, last_ibeam_x + this.thickness, last_ibeam_y - this.thickness * 8, 1);
        graphics.setPaintMode();
        graphics.dispose();
        this.last_ibeam_x = last_ibeam_x;
        this.last_ibeam_y = last_ibeam_y;
    }
    
    synchronized void remove_ibeam() {
        if (this.last_ibeam_x != -1 || this.last_ibeam_y != -1) {
            final Graphics graphics = this.getGraphics();
            graphics.setXORMode(this.xor_colour);
            this.drawLine(graphics, this.last_ibeam_x, this.last_ibeam_y, this.last_ibeam_x + this.thickness * 2, this.last_ibeam_y, 1);
            this.drawLine(graphics, this.last_ibeam_x, this.last_ibeam_y - this.thickness * 8, this.last_ibeam_x + this.thickness * 2, this.last_ibeam_y - this.thickness * 8, 1);
            this.drawLine(graphics, this.last_ibeam_x + this.thickness, this.last_ibeam_y, this.last_ibeam_x + this.thickness, this.last_ibeam_y - this.thickness * 8, 1);
            graphics.setPaintMode();
            graphics.dispose();
        }
        final int n = -1;
        this.last_ibeam_y = n;
        this.last_ibeam_x = n;
    }
    
    void fill_area(final Image image, final Graphics graphics, final int n, final int n2) {
        if (!this.parent.allow_fills) {
            return;
        }
        if (n < 0 || n >= this.parent.board_width || n2 < 0 || n2 >= this.parent.board_height) {
            return;
        }
        if (this.parent.new_jdk) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            if (!this.parent.disable_anti_aliasing) {
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            }
        }
        final Stack stack = new Stack<fill_co_ord>();
        if (null == this.fillPixels || this.prev_width != this.parent.board_width || this.prev_height != this.parent.board_height) {
            this.fillPixels = new int[this.parent.board_width * this.parent.board_height];
        }
        if (null == this.fillDone || this.prev_width != this.parent.board_width || this.prev_height != this.parent.board_height) {
            this.fillDone = new boolean[this.parent.board_width * this.parent.board_height];
        }
        else {
            for (int i = 0; i < this.prev_width * this.prev_height; ++i) {
                this.fillDone[i] = false;
            }
        }
        this.prev_width = this.parent.board_width;
        this.prev_height = this.parent.board_height;
        this.fetchPixels(image, this.fillPixels, 0, 0, this.parent.board_width, this.parent.board_height);
        final int n3 = this.fillPixels[n + n2 * this.parent.board_width];
        stack.push(new fill_co_ord(n, n2));
        while (!stack.empty()) {
            final fill_co_ord fill_co_ord = stack.pop();
            int x;
            int j = x = fill_co_ord.x;
            int n4 = j - 1 + fill_co_ord.y * this.parent.board_width;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            final int n8 = n3 >> 16 & 0xFF;
            final int n9 = n3 >> 8 & 0xFF;
            final int n10 = n3 & 0xFF;
            int n11 = 0;
            int n12 = n3;
            while (j > 0) {
                if (this.fillPixels[n4] != n3 || n11 != 0) {
                    if (this.parent.disable_anti_aliasing) {
                        break;
                    }
                    if (this.fillPixels[n4] == n12) {
                        ++j;
                        break;
                    }
                    n12 = this.fillPixels[n4];
                    n11 = 1;
                    final int n13 = (this.fillPixels[n4] >> 16 & 0xFF) - n8;
                    final int n14 = (this.fillPixels[n4] >> 8 & 0xFF) - n9;
                    final int n15 = (this.fillPixels[n4] & 0xFF) - n10;
                    if (Math.abs(n13) < Math.abs(n5) || n13 * n5 < 0) {
                        ++j;
                        break;
                    }
                    if (Math.abs(n14) < Math.abs(n6) || n14 * n6 < 0) {
                        ++j;
                        break;
                    }
                    if (Math.abs(n15) < Math.abs(n7) || n15 * n7 < 0) {
                        ++j;
                        break;
                    }
                    n5 = n13;
                    n6 = n14;
                    n7 = n15;
                }
                else {
                    --x;
                }
                --j;
                --n4;
            }
            int x2;
            int k = x2 = fill_co_ord.x;
            int n16 = k + 1 + fill_co_ord.y * this.parent.board_width;
            int n17 = n3;
            int n18 = 0;
            int n19 = 0;
            int n20 = 0;
            int n21 = 0;
            while (k < this.parent.board_width - 1) {
                if (this.fillPixels[n16] != n3 || n21 != 0) {
                    if (this.parent.disable_anti_aliasing) {
                        break;
                    }
                    if (this.fillPixels[n16] == n17) {
                        --k;
                        break;
                    }
                    n17 = this.fillPixels[n16];
                    n21 = 1;
                    final int n22 = (this.fillPixels[n16] >> 16 & 0xFF) - n8;
                    final int n23 = (this.fillPixels[n16] >> 8 & 0xFF) - n9;
                    final int n24 = (this.fillPixels[n16] & 0xFF) - n10;
                    if (Math.abs(n22) < Math.abs(n18) || n22 * n18 < 0) {
                        --k;
                        break;
                    }
                    if (Math.abs(n23) < Math.abs(n19) || n23 * n19 < 0) {
                        --k;
                        break;
                    }
                    if (Math.abs(n24) < Math.abs(n20) || n24 * n20 < 0) {
                        --k;
                        break;
                    }
                    n18 = n22;
                    n19 = n23;
                    n20 = n24;
                }
                else {
                    ++x2;
                }
                ++k;
                ++n16;
            }
            graphics.drawLine(j, fill_co_ord.y, k, fill_co_ord.y);
            int n25 = 1;
            int n26 = 1;
            for (int l = x; l <= x2; ++l) {
                final int n27 = l + fill_co_ord.y * this.parent.board_width;
                this.fillDone[n27] = true;
                if (fill_co_ord.y > 0) {
                    final int n28 = n27 - this.parent.board_width;
                    if (!this.fillDone[n28] && this.fillPixels[n28] == n3) {
                        if (n25 != 0 || l == 0) {
                            stack.push(new fill_co_ord(l, fill_co_ord.y - 1));
                        }
                        else {
                            this.fillDone[n28] = true;
                        }
                        n25 = 0;
                    }
                    else {
                        n25 = 1;
                    }
                }
                if (fill_co_ord.y < this.parent.board_height - 1) {
                    final int n29 = n27 + this.parent.board_width;
                    if (!this.fillDone[n29] && this.fillPixels[n29] == n3) {
                        if (n26 != 0 || l == 0) {
                            stack.push(new fill_co_ord(l, fill_co_ord.y + 1));
                        }
                        else {
                            this.fillDone[n29] = true;
                        }
                        n26 = 0;
                    }
                    else {
                        n26 = 1;
                    }
                }
            }
        }
    }
    
    private void fetchPixels(final Image image, final int[] array, final int n, final int n2, final int n3, final int n4) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println(" fetchPixels: Error! Interrupted waiting for pixels!");
            return;
        }
        if (this.parent.new_jdk && (pixelGrabber.getStatus() & 0x80) != 0x0) {
            System.err.println(" fetchPixels: Error! Image fetch aborted or failed!");
        }
    }
}
