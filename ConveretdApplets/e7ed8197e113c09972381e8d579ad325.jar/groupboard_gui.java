import java.awt.Event;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class groupboard_gui extends Panel implements groupboard_consts
{
    private boolean use_title;
    chat_frame fr_chat;
    frame_games fr_games;
    draw_controls controls;
    private boolean separate_window;
    private static final int ADVERT_WIDTH = 468;
    private static final int ADVERT_HEIGHT = 60;
    chat_area chat;
    groupboard parent;
    private Button bboard_button;
    private Button float_button;
    private Button games_button;
    Label users_label;
    private Label title;
    private List users;
    advert_area ad_area;
    draw_panel draw_data;
    
    groupboard_gui(final groupboard parent, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final boolean b8, final boolean b9, final boolean b10, final boolean b11, final String s, final int n, final int n2, final boolean separate_window, final boolean b12, final int n3, final int n4, final int n5) {
        this.use_title = true;
        this.use_title = parent.get_boolean_arg("DISPLAY_TITLE", true);
        boolean get_boolean_arg;
        if (s == null) {
            get_boolean_arg = !parent.get_boolean_arg("VIEW_ONLY", false);
        }
        else {
            get_boolean_arg = parent.get_boolean_arg("ALLOW_GALLERY_EDIT", false);
        }
        final int get_int_arg = parent.get_int_arg("BACKGROUND_RED", 0);
        final int get_int_arg2 = parent.get_int_arg("BACKGROUND_GREEN", 0);
        final int get_int_arg3 = parent.get_int_arg("BACKGROUND_BLUE", 0);
        Color color = null;
        if (null != parent.get_arg("BACKGROUND_RED")) {
            color = new Color(get_int_arg, get_int_arg2, get_int_arg3);
            if (null != color) {
                this.setBackground(color);
            }
        }
        final int get_int_arg4 = parent.get_int_arg("FOREGROUND_RED", 0);
        final int get_int_arg5 = parent.get_int_arg("FOREGROUND_GREEN", 0);
        final int get_int_arg6 = parent.get_int_arg("FOREGROUND_BLUE", 0);
        if (null != parent.get_arg("FOREGROUND_RED")) {
            final Color foreground = new Color(get_int_arg4, get_int_arg5, get_int_arg6);
            if (null != foreground) {
                this.setForeground(foreground);
            }
        }
        this.separate_window = separate_window;
        this.parent = parent;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        this.setLayout(layout);
        Font font;
        if (parent.use_utf && parent.new_jdk) {
            font = new Font("serif", 0, 25);
        }
        else {
            font = new Font("TimesRoman", 0, 25);
        }
        if (this.use_title) {
            this.add(this.title = new Label("                                                                               ", 1));
            if (null != font) {
                this.title.setFont(font);
            }
            layout.setConstraints(this.title, gridBagConstraints);
        }
        if (b10) {
            (this.ad_area = new advert_area(parent)).resize(468, 60);
            this.add(this.ad_area);
            layout.setConstraints(this.ad_area, gridBagConstraints);
            (parent.changer = new ad_changer(this.ad_area)).start();
        }
        if (b7) {
            this.draw_data = new draw_panel(parent, this, !get_boolean_arg, b11, n3, n4, n5);
            if (b11 && b12) {
                this.add(this.users_label = new Label("There are " + parent.num_clients + " " + "other users connected"));
                layout.setConstraints(this.users_label, gridBagConstraints);
            }
            else if (b10) {
                final Label label = new Label(" ");
                this.add(label);
                layout.setConstraints(label, gridBagConstraints);
            }
            this.draw_data.resize(n, n2);
            this.add(this.draw_data);
            layout.setConstraints(this.draw_data, gridBagConstraints);
            if (get_boolean_arg && !parent.get_boolean_arg("HIDE_GUI", false)) {
                this.controls = new draw_controls(this.draw_data, b, b2, b3, b5, separate_window && !groupboard.is_applet, color);
                if (null != color) {
                    this.controls.setBackground(color);
                }
                this.add(this.controls);
                layout.setConstraints(this.controls, gridBagConstraints);
            }
        }
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        if (get_boolean_arg && !parent.get_boolean_arg("HIDE_GUI", false)) {
            if (b8) {
                if (b7 || b6) {
                    panel.add(this.bboard_button = new Button(" Message Board "));
                }
                else {
                    this.add(parent.bb = new bboard_gui(parent.os, parent.is_admin, parent, null));
                    layout.setConstraints(parent.bb, gridBagConstraints);
                    parent.bb.get_article_index();
                }
            }
            if (b9 && b11) {
                if (b7) {
                    panel.add(this.games_button = new Button(" Games "));
                }
                else {
                    (parent.games = new games_gui(parent.is_admin, parent, null)).list_games();
                    if (separate_window) {
                        this.fr_games = new frame_games(parent.is, parent.os, parent.is_admin, parent, parent.games);
                    }
                    else {
                        this.add(parent.games);
                        layout.setConstraints(parent.games, gridBagConstraints);
                    }
                }
            }
            if (groupboard.is_applet && (b7 || b9)) {
                if (separate_window) {
                    panel.add(this.float_button = new Button("Unfloat"));
                }
                else {
                    panel.add(this.float_button = new Button("Float"));
                }
            }
        }
        this.add(panel);
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 10, 10, 10);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(panel, gridBagConstraints);
        if (b6) {
            this.chat = new chat_area(parent, b4, b8, parent.get_boolean_arg("SHOW_IP_ADDRESSES", false));
            if (!parent.get_boolean_arg("HIDE_CHAT", false)) {
                if (separate_window && b7 && parent.get_boolean_arg("SEPARATE_CHAT_WINDOW", true)) {
                    this.fr_chat = new chat_frame(this.chat, this);
                }
                else {
                    this.add(this.chat);
                    gridBagConstraints.fill = 1;
                    gridBagConstraints.insets = new Insets(0, 0, 0, 0);
                    layout.setConstraints(this.chat, gridBagConstraints);
                }
            }
        }
        if (null != this.draw_data) {
            this.draw_data.repaint();
        }
    }
    
    public void set_title(final String text) {
        if (this.use_title) {
            this.title.setText(text);
        }
    }
    
    public boolean handleEvent(final Event event) {
        this.parent.last_event_time = System.currentTimeMillis();
        switch (event.id) {
            case 1001: {
                if (event.target == this.bboard_button) {
                    this.parent.show_message_board();
                    return true;
                }
                if (event.target == this.games_button) {
                    this.parent.show_games();
                    return true;
                }
                if (event.target == this.float_button) {
                    if (this.separate_window) {
                        this.parent.make_unfloat();
                    }
                    else {
                        this.parent.make_float();
                    }
                    return true;
                }
                return false;
            }
            default: {
                return false;
            }
        }
    }
}
