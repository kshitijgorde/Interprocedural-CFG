import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class draw_controls extends Panel implements groupboard_consts
{
    static final int MAX_SPLIT_WIDTH = 750;
    private draw_panel target;
    private Color bg_colour;
    
    draw_controls(final draw_panel target, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final Color color) {
        this.bg_colour = color;
        final boolean b6 = target.parent.get_int_arg("WIDTH", 0) < 750 && !target.parent.get_boolean_arg("EXTENDED_PALETTE", false);
        Panel panel;
        if (b6) {
            panel = new Panel();
            panel.setLayout(new FlowLayout());
        }
        else {
            panel = this;
        }
        this.target = target;
        this.setLayout(new FlowLayout());
        if (null != color) {
            this.setBackground(color);
        }
        else {
            this.setBackground(Color.lightGray);
        }
        if (!target.parent.get_boolean_arg("EXTENDED_PALETTE", false)) {
            final CheckboxGroup checkboxGroup = new CheckboxGroup();
            final Checkbox checkbox;
            panel.add(checkbox = new Checkbox(null, checkboxGroup, false));
            checkbox.setBackground(Color.white);
            final Checkbox checkbox2;
            panel.add(checkbox2 = new Checkbox(null, checkboxGroup, false));
            checkbox2.setBackground(Color.red);
            final Checkbox checkbox3;
            panel.add(checkbox3 = new Checkbox(null, checkboxGroup, false));
            checkbox3.setBackground(Color.green);
            final Checkbox checkbox4;
            panel.add(checkbox4 = new Checkbox(null, checkboxGroup, false));
            checkbox4.setBackground(Color.blue);
            final Checkbox checkbox5;
            panel.add(checkbox5 = new Checkbox(null, checkboxGroup, false));
            checkbox5.setBackground(Color.pink);
            final Checkbox checkbox6;
            panel.add(checkbox6 = new Checkbox(null, checkboxGroup, false));
            checkbox6.setBackground(Color.orange);
            final Checkbox current;
            panel.add(current = new Checkbox(null, checkboxGroup, true));
            current.setBackground(Color.black);
            final Checkbox checkbox7;
            panel.add(checkbox7 = new Checkbox(null, checkboxGroup, true));
            checkbox7.setBackground(Color.gray);
            final Checkbox checkbox8;
            panel.add(checkbox8 = new Checkbox(null, checkboxGroup, true));
            checkbox8.setBackground(Color.magenta);
            final Checkbox checkbox9;
            panel.add(checkbox9 = new Checkbox(null, checkboxGroup, true));
            checkbox9.setBackground(Color.cyan);
            final Checkbox checkbox10;
            panel.add(checkbox10 = new Checkbox(null, checkboxGroup, true));
            checkbox10.setBackground(target.brown);
            checkboxGroup.setCurrent(current);
        }
        final Choice choice = new Choice();
        choice.addItem("Freehand");
        choice.addItem("Text");
        if (null != color) {
            choice.setBackground(color);
        }
        else {
            choice.setBackground(Color.lightGray);
        }
        if (!b4) {
            choice.addItem("Lines");
            choice.addItem("Ovals");
            choice.addItem("Rectangles");
            choice.addItem("Filled Ovals");
            choice.addItem("Filled Rectangles");
            if (!target.parent.get_boolean_arg("DISABLE_FLOOD_FILL", false)) {
                choice.addItem("Flood Fill");
            }
        }
        panel.add(choice);
        Panel panel2;
        if (b6) {
            this.setLayout(new BorderLayout());
            this.add("North", panel);
            panel2 = new Panel();
            panel2.setLayout(new FlowLayout());
        }
        else {
            panel2 = this;
        }
        panel2.add(new Label("Size"));
        final Choice choice2 = new Choice();
        choice2.addItem("1");
        choice2.addItem("2");
        choice2.addItem("3");
        choice2.addItem("4");
        choice2.addItem("5");
        choice2.addItem("6");
        choice2.addItem("7");
        choice2.addItem("8");
        choice2.addItem("9");
        choice2.addItem("10");
        choice2.select(target.parent.get_int_arg("DEFAULT_THICKNESS", 1) - 1);
        if (null != color) {
            choice2.setBackground(color);
        }
        else {
            choice2.setBackground(Color.lightGray);
        }
        panel2.add(choice2);
        if (target.parent.get_boolean_arg("EXTENDED_PALETTE", false)) {
            panel2.add(new Button("Colour"));
        }
        if (target.parent.get_boolean_arg("ALLOW_CLEAR", true)) {
            panel2.add(new Button("Clear"));
        }
        if (b3) {
            panel2.add(new Button("Set Background"));
        }
        if (b2) {
            panel2.add(new Button("Load"));
        }
        if (b) {
            panel2.add(new Button("Save"));
        }
        if (b5) {
            panel2.add(new Button("Print"));
        }
        if (b6) {
            this.add("South", panel2);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        if (null != this.bg_colour) {
            graphics.setColor(this.bg_colour);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        graphics.draw3DRect(0, 0, bounds.width, bounds.height, false);
    }
    
    public boolean action(final Event event, final Object o) {
        this.target.parent.last_event_time = System.currentTimeMillis();
        if (event.target instanceof Checkbox) {
            this.target.set_current_colour(((Component)event.target).getBackground());
        }
        else if (event.target instanceof Button) {
            final String s = (String)o;
            if (s.equals("Clear")) {
                if (this.target.clear_disabled || this.target.draw_disabled) {
                    new message_box(this.target.parent, "Error", "Clearing is currently disabled");
                    return true;
                }
                this.target.clear_array(true, false);
            }
            else {
                if (s.equals("Colour")) {
                    this.target.parent.show_extended_palette();
                    return true;
                }
                if (s.equals("Load")) {
                    if (!this.target.load_disabled && !this.target.draw_disabled) {
                        this.target.parent.load_pic();
                    }
                    else {
                        new message_box(this.target.parent, "Error", "Loading is currently disabled");
                    }
                    return true;
                }
                if (s.equals("Set Background")) {
                    if (!this.target.load_disabled && !this.target.draw_disabled) {
                        this.target.parent.set_background();
                    }
                    else {
                        new message_box(this.target.parent, "Error", "Loading is currently disabled");
                    }
                    return true;
                }
                if (s.equals("Save")) {
                    if (!this.target.draw_disabled) {
                        this.target.save_pic();
                    }
                    else {
                        new message_box(this.target.parent, "Error", "Drawing is currently disabled");
                    }
                    return true;
                }
                if (s.equals("Print")) {
                    this.target.parent.print_pic();
                    return true;
                }
            }
        }
        else if (event.target instanceof Choice) {
            final String s2 = (String)o;
            if (s2.equals("Lines")) {
                this.target.set_draw_mode(1);
            }
            else if (s2.equals("Freehand")) {
                this.target.set_draw_mode(4);
            }
            else if (s2.equals("Text")) {
                this.target.set_draw_mode(3);
            }
            else if (s2.equals("Ovals")) {
                this.target.set_draw_mode(2);
            }
            else if (s2.equals("Rectangles")) {
                this.target.set_draw_mode(11);
            }
            else if (s2.equals("Filled Ovals")) {
                this.target.set_draw_mode(12);
            }
            else if (s2.equals("Filled Rectangles")) {
                this.target.set_draw_mode(13);
            }
            else if (s2.equals("Flood Fill")) {
                this.target.set_draw_mode(64);
            }
            else if (s2.equals("1")) {
                this.target.set_thickness(1);
            }
            else if (s2.equals("2")) {
                this.target.set_thickness(2);
            }
            else if (s2.equals("3")) {
                this.target.set_thickness(3);
            }
            else if (s2.equals("4")) {
                this.target.set_thickness(4);
            }
            else if (s2.equals("5")) {
                this.target.set_thickness(5);
            }
            else if (s2.equals("6")) {
                this.target.set_thickness(6);
            }
            else if (s2.equals("7")) {
                this.target.set_thickness(7);
            }
            else if (s2.equals("8")) {
                this.target.set_thickness(8);
            }
            else if (s2.equals("9")) {
                this.target.set_thickness(9);
            }
            else if (s2.equals("10")) {
                this.target.set_thickness(10);
            }
        }
        this.target.requestFocus();
        return true;
    }
}
