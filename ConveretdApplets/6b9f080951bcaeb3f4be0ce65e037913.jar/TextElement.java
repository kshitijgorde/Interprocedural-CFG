import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

package java.lang;

import java.util.Formatter;
import java.util.Locale;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
import java.nio.charset.Charset;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.io.ObjectStreamField;
import java.io.Serializable;

class TextElement
{
    public static final int NODE_NONE = -2;
    public static final int NODE_EMPTY = -1;
    public static final int NODE_LEAF = 0;
    public static final int NODE_STYLEFORM = 1;
    public static final int NODE_HOLDFORM = 2;
    public static final int NODE_SEQUENCEFORM = 3;
    public static final int NODE_SUBSCRIPT = 4;
    public static final int NODE_SUPERSCRIPT = 5;
    public static final int NODE_SUBSUPERSCRIPT = 6;
    public static final int NODE_OVERSCRIPT = 7;
    public static final int NODE_UNDERSCRIPT = 8;
    public static final int NODE_UNDEROVERSCRIPT = 9;
    public Vector children;
    public int node_type;
    public String font_url;
    public String font_family;
    public int font_weight;
    public int font_slant;
    public int font_size;
    public Color font_color;
    public Color font_background;
    public Vector font_substitutions;
    public double script_size_multipliers;
    public int script_min_size;
    public double script_baseline_shift1;
    public double script_baseline_shift2;
    public double script_baseline_shift3;
    public double script_baseline_shift4;
    public String text;
    public int variable_index;
    public String actual_text;
    public boolean is_root;
    public boolean is_held;
    public boolean is_variable;
    public Font font;
    public int font_width;
    public int font_ascent;
    public int font_descent;
    public int baseline_shift;
    public int box_width;
    public int box_height;
    public int box_ascent;
    public int box_descent;
    public int box_x_offset;
    public int box_y_offset;
    
    public TextElement() {
        this.children = null;
        this.node_type = -2;
        this.font_url = null;
        this.font_family = null;
        this.font_weight = -1;
        this.font_slant = -1;
        this.font_size = -1;
        this.font_color = null;
        this.font_background = null;
        this.font_substitutions = null;
        this.script_size_multipliers = -1.0;
        this.script_min_size = -1;
        this.script_baseline_shift1 = -101.0;
        this.script_baseline_shift2 = -101.0;
        this.script_baseline_shift3 = -101.0;
        this.script_baseline_shift4 = -101.0;
        this.text = null;
        this.variable_index = -1;
        this.actual_text = null;
        this.is_root = false;
        this.is_held = false;
        this.is_variable = false;
        this.font = null;
        this.font_width = 0;
        this.font_ascent = 0;
        this.font_descent = 0;
        this.baseline_shift = 0;
        this.box_width = 0;
        this.box_height = 0;
        this.box_ascent = 0;
        this.box_descent = 0;
        this.box_x_offset = 0;
        this.box_y_offset = 0;
    }
    
    public TextElement(final TextElement other) {
        this.children = other.children;
        this.node_type = other.node_type;
        this.font_url = other.font_url;
        this.font_family = other.font_family;
        this.font_weight = other.font_weight;
        this.font_slant = other.font_slant;
        this.font_size = other.font_size;
        this.font_color = other.font_color;
        this.font_background = other.font_background;
        this.font_substitutions = other.font_substitutions;
        this.script_size_multipliers = other.script_size_multipliers;
        this.script_min_size = other.script_min_size;
        this.script_baseline_shift1 = other.script_baseline_shift1;
        this.script_baseline_shift2 = other.script_baseline_shift2;
        this.script_baseline_shift3 = other.script_baseline_shift3;
        this.script_baseline_shift4 = other.script_baseline_shift4;
        this.text = other.text;
        this.variable_index = other.variable_index;
        this.actual_text = other.actual_text;
        this.is_root = other.is_root;
        this.is_held = other.is_held;
        this.is_variable = other.is_variable;
        this.font = other.font;
        this.font_width = other.font_width;
        this.font_ascent = other.font_ascent;
        this.font_descent = other.font_descent;
        this.baseline_shift = other.baseline_shift;
        this.box_width = other.box_width;
        this.box_height = other.box_height;
        this.box_ascent = other.box_ascent;
        this.box_descent = other.box_descent;
        this.box_x_offset = other.box_x_offset;
        this.box_y_offset = other.box_y_offset;
    }
    
    public void set_textstyle_options(final Graphics3D g3d, final Color standard_color) {
        if (null != g3d) {
            if (null == this.font_color) {
                if (null != g3d.option_TextStyle_font_color) {
                    this.font_color = g3d.option_TextStyle_font_color;
                }
                else if (null != standard_color) {
                    this.font_color = standard_color;
                }
                else {
                    this.font_color = g3d.option_DefaultColor;
                }
            }
            if (null == this.font_background && null != g3d.option_TextStyle_font_background) {
                this.font_background = g3d.option_TextStyle_font_background;
            }
            if (null == this.font_url) {
                this.font_url = g3d.option_TextStyle_font_url;
            }
            if (null == this.font_family) {
                this.font_family = g3d.option_TextStyle_font_family;
            }
            if (this.font_weight < 0) {
                this.font_weight = g3d.option_TextStyle_font_weight;
            }
            if (this.font_slant < 0) {
                this.font_slant = g3d.option_TextStyle_font_slant;
            }
            if (this.font_size < 0) {
                this.font_size = g3d.option_TextStyle_font_size;
            }
            if (null == this.font_color) {
                this.font_color = g3d.option_TextStyle_font_color;
            }
            if (null == this.font_background) {
                this.font_background = g3d.option_TextStyle_font_background;
            }
            if (null == this.font_substitutions) {
                this.font_substitutions = g3d.option_TextStyle_font_substitutions;
            }
            if (this.script_size_multipliers < 0.0) {
                this.script_size_multipliers = g3d.option_TextStyle_script_size_multipliers;
            }
            if (this.script_min_size < 0) {
                this.script_min_size = g3d.option_TextStyle_script_min_size;
            }
            if (this.script_baseline_shift1 < -100.0 && g3d.option_TextStyle_script_baseline_shift1 >= -100.0) {
                this.script_baseline_shift1 = g3d.option_TextStyle_script_baseline_shift1;
            }
            if (this.script_baseline_shift2 < -100.0 && g3d.option_TextStyle_script_baseline_shift2 >= -100.0) {
                this.script_baseline_shift2 = g3d.option_TextStyle_script_baseline_shift2;
            }
            if (this.script_baseline_shift3 < -100.0 && g3d.option_TextStyle_script_baseline_shift3 >= -100.0) {
                this.script_baseline_shift3 = g3d.option_TextStyle_script_baseline_shift3;
            }
            if (this.script_baseline_shift4 < -100.0 && g3d.option_TextStyle_script_baseline_shift4 >= -100.0) {
                this.script_baseline_shift4 = g3d.option_TextStyle_script_baseline_shift4;
            }
        }
        this.propagate_options();
    }
    
    public void propagate_options() {
        if (0 == this.node_type && this.variable_index >= 0) {
            this.is_variable = true;
        }
        if (null == this.children) {
            return;
        }
        for (int index = 0; index < this.children.size(); ++index) {
            final TextElement child = this.children.elementAt(index);
            if (null != child) {
                if (null == child.font_url) {
                    child.font_url = this.font_url;
                }
                if (null == child.font_family) {
                    child.font_family = this.font_family;
                }
                if (child.font_weight < 0) {
                    child.font_weight = this.font_weight;
                }
                if (child.font_slant < 0) {
                    child.font_slant = this.font_slant;
                }
                if (child.font_size < 0) {
                    if (index > 0 && (this.node_type == 4 || this.node_type == 5 || this.node_type == 6 || this.node_type == 7 || this.node_type == 8 || this.node_type == 9)) {
                        child.font_size = (int)(this.font_size * this.script_size_multipliers + 0.5);
                        if (child.font_size < this.script_min_size) {
                            child.font_size = this.script_min_size;
                        }
                    }
                    else {
                        child.font_size = this.font_size;
                    }
                }
                if (null == child.font_color) {
                    child.font_color = this.font_color;
                }
                if (null == child.font_background) {
                    child.font_background = this.font_background;
                }
                if (null == child.font_substitutions) {
                    child.font_substitutions = this.font_substitutions;
                }
                if (child.script_size_multipliers < 0.0) {
                    child.script_size_multipliers = this.script_size_multipliers;
                }
                if (child.script_min_size < 0) {
                    child.script_min_size = this.script_min_size;
                }
                if (child.script_baseline_shift1 < -100.0 && this.script_baseline_shift1 >= -100.0) {
                    child.script_baseline_shift1 = this.script_baseline_shift1;
                }
                if (child.script_baseline_shift2 < -100.0 && this.script_baseline_shift2 >= -100.0) {
                    child.script_baseline_shift2 = this.script_baseline_shift2;
                }
                if (child.script_baseline_shift3 < -100.0 && this.script_baseline_shift3 >= -100.0) {
                    child.script_baseline_shift3 = this.script_baseline_shift3;
                }
                if (child.script_baseline_shift4 < -100.0 && this.script_baseline_shift4 >= -100.0) {
                    child.script_baseline_shift4 = this.script_baseline_shift4;
                }
                child.propagate_options();
                if (child.is_variable) {
                    this.is_variable = true;
                }
            }
        }
        if (this.script_baseline_shift3 < -200.0) {
            this.script_baseline_shift3 = 0.9;
        }
        if (this.script_baseline_shift4 < -200.0) {
            this.script_baseline_shift4 = 1.2;
        }
    }
    
    public void set_font(final Graphics g) {
        if (-2 == this.node_type || -1 == this.node_type) {
            return;
        }
        String actual_font_family = this.font_family;
        if (null != this.font_substitutions) {
            for (int subs_index = 0; subs_index < this.font_substitutions.size(); subs_index += 2) {
                if (0 == actual_font_family.compareTo(this.font_substitutions.elementAt(subs_index))) {
                    actual_font_family = this.font_substitutions.elementAt(subs_index + 1);
                    break;
                }
            }
        }
        g.setFont(this.font = new Font(actual_font_family, this.font_weight | this.font_slant, this.font_size));
        final FontMetrics fm = g.getFontMetrics();
        this.font_ascent = fm.getMaxAscent();
        this.font_descent = fm.getMaxDescent();
        this.actual_text = this.text;
        if (null != this.text) {
            this.font_width = fm.stringWidth(this.text);
        }
        else {
            this.font_width = 0;
        }
        if (null != this.children) {
            for (int index = 0; index < this.children.size(); ++index) {
                final TextElement child = this.children.elementAt(index);
                if (null != child) {
                    child.set_font(g);
                    if ((1 == index && this.node_type == 4) || (1 == index && this.node_type == 6)) {
                        child.baseline_shift = (int)(0.5 * this.font_ascent * this.script_baseline_shift1 + 0.5);
                    }
                    else if ((1 == index && this.node_type == 5) || (2 == index && this.node_type == 6)) {
                        child.baseline_shift = -(int)(0.5 * this.font_ascent * this.script_baseline_shift2 + 0.5);
                    }
                    else if (this.script_baseline_shift3 >= -100.0 && ((1 == index && this.node_type == 8) || (1 == index && this.node_type == 9))) {
                        child.baseline_shift = (int)(0.5 * this.font_ascent * this.script_baseline_shift3 + 0.5);
                    }
                    else if (this.script_baseline_shift4 >= -100.0 && ((1 == index && this.node_type == 7) || (2 == index && this.node_type == 9))) {
                        child.baseline_shift = -(int)(0.5 * this.font_ascent * this.script_baseline_shift4 + 0.5);
                    }
                }
            }
        }
    }
    
    public void set_box_sizes(final Graphics g, final Evaluator evaluator) {
        if (-2 == this.node_type || -1 == this.node_type) {
            this.box_width = 0;
            this.box_height = 0;
            this.box_ascent = 0;
            this.box_descent = 0;
        }
        else if (0 == this.node_type) {
            if (null != evaluator && this.variable_index >= 0) {
                g.setFont(this.font);
                this.actual_text = Float.toString((float)evaluator.getVariableValue(this.variable_index));
                final FontMetrics fm = g.getFontMetrics();
                this.font_width = fm.stringWidth(this.actual_text);
            }
            this.box_width = this.font_width;
            this.box_height = this.font_ascent + this.font_descent;
            this.box_ascent = this.font_ascent;
            this.box_descent = this.font_descent;
        }
        if (null != this.children && this.children.size() > 0) {
            for (int index = 0; index < this.children.size(); ++index) {
                final TextElement child = this.children.elementAt(index);
                if (null != child) {
                    child.set_box_sizes(g, evaluator);
                }
            }
            if (null != this.children.elementAt(0)) {
                final TextElement first_child = this.children.elementAt(0);
                if (9 == this.node_type || 8 == this.node_type || 7 == this.node_type) {
                    this.box_width = first_child.box_width;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            if (this.box_width < child.box_width) {
                                this.box_width = child.box_width;
                            }
                        }
                    }
                }
                else if (6 == this.node_type) {
                    this.box_width = 0;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            if (this.box_width < child.box_width) {
                                this.box_width = child.box_width;
                            }
                        }
                    }
                    this.box_width += first_child.box_width;
                }
                else {
                    this.box_width = first_child.box_width;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            this.box_width += child.box_width;
                        }
                    }
                }
                if (4 == this.node_type || 5 == this.node_type || 6 == this.node_type || (8 == this.node_type && this.script_baseline_shift3 >= -100.0) || (7 == this.node_type && this.script_baseline_shift4 >= -100.0) || (9 == this.node_type && (this.script_baseline_shift3 >= -100.0 || this.script_baseline_shift4 >= -100.0))) {
                    this.box_ascent = first_child.box_ascent;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            if (index == 2 && 9 == this.node_type && this.script_baseline_shift4 < -100.0) {
                                this.box_ascent = first_child.box_ascent + child.box_height;
                            }
                            else if (this.box_ascent < -child.baseline_shift + child.box_ascent) {
                                this.box_ascent = -child.baseline_shift + child.box_ascent;
                            }
                        }
                    }
                    this.box_descent = first_child.box_descent;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            if (index == 1 && 9 == this.node_type && this.script_baseline_shift3 < -100.0) {
                                this.box_descent = first_child.box_descent + child.box_height;
                            }
                            else if (this.box_descent < child.baseline_shift + child.box_descent) {
                                this.box_descent = child.baseline_shift + child.box_descent;
                            }
                        }
                    }
                }
                else if (7 == this.node_type) {
                    this.box_ascent = first_child.box_ascent;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            this.box_ascent += child.box_height;
                        }
                    }
                    this.box_descent = first_child.box_descent;
                }
                else if (8 == this.node_type) {
                    this.box_ascent = first_child.box_ascent;
                    this.box_descent = first_child.box_descent;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            this.box_descent += child.box_height;
                        }
                    }
                }
                else if (9 == this.node_type) {
                    this.box_ascent = first_child.box_ascent;
                    this.box_descent = first_child.box_descent;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            if (1 == index) {
                                this.box_descent += child.box_height;
                            }
                            else if (2 == index) {
                                this.box_ascent += child.box_height;
                            }
                        }
                    }
                }
                else {
                    this.box_ascent = first_child.box_ascent;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            if (this.box_ascent < child.box_ascent) {
                                this.box_ascent = child.box_ascent;
                            }
                        }
                    }
                    this.box_descent = first_child.box_descent;
                    for (int index = 1; index < this.children.size(); ++index) {
                        final TextElement child = this.children.elementAt(index);
                        if (null != child) {
                            if (this.box_descent < child.box_descent) {
                                this.box_descent = child.box_descent;
                            }
                        }
                    }
                }
                this.box_height = this.box_descent + this.box_ascent;
            }
        }
    }
    
    public void set_childrens_box_offsets() {
        if (null != this.children && this.children.size() > 0) {
            int next_box_x_offset = this.box_x_offset;
            for (int index = 0; index < this.children.size(); ++index) {
                final TextElement child = this.children.elementAt(index);
                if (null != child) {
                    if (0 == index && (8 == this.node_type || 9 == this.node_type || 7 == this.node_type)) {
                        child.box_x_offset = this.box_x_offset + (this.box_width - child.box_width) / 2;
                        child.box_y_offset = this.box_y_offset + this.box_ascent - child.box_ascent;
                    }
                    else if (0 == index) {
                        child.box_x_offset = this.box_x_offset;
                        child.box_y_offset = this.box_y_offset + this.box_ascent - child.box_ascent;
                        next_box_x_offset = child.box_x_offset + child.box_width;
                    }
                    else if (1 == index && (8 == this.node_type || 9 == this.node_type)) {
                        child.box_x_offset = this.box_x_offset + (this.box_width - child.box_width) / 2;
                        if (this.script_baseline_shift3 < -100.0) {
                            child.box_y_offset = this.box_y_offset + this.box_height - child.box_height;
                        }
                        else {
                            child.box_y_offset = this.box_y_offset + this.box_ascent + child.baseline_shift - child.box_ascent;
                        }
                    }
                    else if ((1 == index && 7 == this.node_type) || (2 == index && 9 == this.node_type)) {
                        child.box_x_offset = this.box_x_offset + (this.box_width - child.box_width) / 2;
                        if (this.script_baseline_shift4 < -100.0) {
                            child.box_y_offset = this.box_y_offset;
                        }
                        else {
                            child.box_y_offset = this.box_y_offset + this.box_ascent + child.baseline_shift - child.box_ascent;
                        }
                    }
                    else if (4 == this.node_type || 6 == this.node_type || 5 == this.node_type) {
                        child.box_x_offset = next_box_x_offset;
                        child.box_y_offset = this.box_y_offset + this.box_ascent + child.baseline_shift - child.box_ascent;
                    }
                    else if (3 == this.node_type) {
                        child.box_x_offset = next_box_x_offset;
                        child.box_y_offset = this.box_y_offset + this.box_ascent - child.box_ascent;
                        next_box_x_offset = child.box_x_offset + child.box_width;
                    }
                    child.set_childrens_box_offsets();
                }
            }
        }
    }
    
    public void paint_children_backgrounds(final Graphics g, final int text_point_x, final int text_point_y) {
        if (-2 == this.node_type || -1 == this.node_type) {
            return;
        }
        if (null != this.font_background) {
            g.setColor(this.font_background);
            if (this.is_root) {
                g.fillRect(text_point_x + this.box_x_offset - 2, text_point_y + this.box_y_offset - 2, this.box_width + 4, this.box_height + 4);
            }
            else {
                g.fillRect(text_point_x + this.box_x_offset, text_point_y + this.box_y_offset, this.box_width, this.box_height);
            }
        }
        if (null != this.children && this.children.size() > 0) {
            for (int index = 0; index < this.children.size(); ++index) {
                final TextElement child = this.children.elementAt(index);
                if (null != child) {
                    child.paint_children_backgrounds(g, text_point_x, text_point_y);
                }
            }
        }
    }
    
    public void paint_children_foregrounds(final Graphics g, final int text_point_x, final int text_point_y) {
        if (-2 == this.node_type || -1 == this.node_type) {
            return;
        }
        if (0 == this.node_type) {
            g.setFont(this.font);
            g.setColor(this.font_color);
            g.drawString(this.actual_text, text_point_x + this.box_x_offset, text_point_y + this.box_y_offset + this.box_ascent);
            return;
        }
        if (null != this.children && this.children.size() > 0) {
            for (int index = 0; index < this.children.size(); ++index) {
                final TextElement child = this.children.elementAt(index);
                if (null != child) {
                    child.paint_children_foregrounds(g, text_point_x, text_point_y);
                }
            }
        }
    }
}
