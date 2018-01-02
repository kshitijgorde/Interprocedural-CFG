import java.awt.Image;
import java.awt.PrintJob;
import java.awt.image.ImageObserver;
import java.util.Properties;
import java.awt.Frame;
import java.awt.Graphics;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class groupboard extends Applet implements groupboard_consts
{
    boolean asked_for_password;
    static boolean error_given;
    boolean need_to_print;
    long last_error_time;
    text_box tb;
    private ByteArrayOutputStream b;
    private DataOutputStream d;
    boolean use_utf;
    boolean disable_anti_aliasing;
    boolean rtl;
    boolean beep_on_whisper;
    boolean allow_fills;
    long last_event_time;
    boolean ignored_stop;
    public game_base base;
    colour_selector coloursel;
    private housekeeping housekeeping_thread;
    tunnel_writer tunnel_thread;
    ad_changer changer;
    boolean use_tunnel;
    tunnel t;
    boolean new_jdk;
    boolean is_mac_osx;
    float jdk_main_version;
    loading_window loading_win;
    private int bg_red;
    boolean is_admin;
    private int bg_green;
    private int bg_blue;
    private boolean display_num_users;
    String password;
    String username;
    private boolean need_password;
    boolean need_username;
    banlist_window banlist_win;
    board_info_window board_info_win;
    set_background_window set_win;
    load_pic_window load_pic_win;
    String gallery_pic;
    boolean min_actions;
    private boolean allow_load;
    private boolean allow_save;
    private boolean allow_set_background;
    int board_width;
    int board_height;
    private static String[] app_args;
    static boolean is_applet;
    browser_gui br_gui;
    frame_gui fr_gui;
    groupboard_gui gui;
    bboard_gui bb;
    frame_bboard bbwin;
    frame_games gameswin;
    games_gui games;
    int num_clients;
    server_read server_read_thread;
    boolean multiuser;
    private boolean show_chat_area;
    private boolean show_whiteboard;
    private boolean show_bboard;
    private boolean show_games;
    boolean show_adverts;
    private Socket server;
    DataInputStream is;
    DataOutputStream os;
    static final int GBMANAGER_PORT = 41211;
    static final int CURRENT_VERSION = 2;
    static final int DEFAULT_BOARD_WIDTH = 360;
    static final int MAX_FREE_BOARD_WIDTH = 520;
    static final int DEFAULT_BOARD_HEIGHT = 250;
    static final int SHOW_WHITEBOARD = 1;
    static final int SHOW_CHAT = 2;
    static final int SHOW_BBOARD = 4;
    static final int ALLOW_RESIZE = 8;
    static final int SHOW_ADVERTS = 16;
    static final int SHOW_GAMES = 32;
    static final String HELP_URL = "http://www.groupboard.com/user.html";
    static final String ADVERT_URL = "http://www.groupboard.com/advert.html";
    
    public groupboard() {
        this.need_to_print = false;
        this.tb = null;
        this.use_utf = false;
        this.disable_anti_aliasing = false;
        this.rtl = false;
        this.beep_on_whisper = true;
        this.allow_fills = true;
        this.ignored_stop = false;
        this.bg_red = 255;
        this.is_admin = false;
        this.bg_green = 255;
        this.bg_blue = 255;
        this.display_num_users = true;
        this.br_gui = null;
        this.fr_gui = null;
        this.multiuser = false;
        this.show_chat_area = false;
        this.show_whiteboard = false;
        this.show_bboard = false;
        this.show_games = false;
        this.show_adverts = false;
        this.server = null;
        this.is = null;
        this.os = null;
    }
    
    public String utf_to_string(String utf) {
        if (null == utf) {
            return null;
        }
        final byte[] array = new byte[utf.length() + 2];
        utf.getBytes(0, utf.length(), array, 2);
        array[0] = (byte)(utf.length() >> 8);
        array[1] = (byte)(utf.length() & 0xFF);
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array));
        try {
            utf = dataInputStream.readUTF();
        }
        catch (IOException ex) {}
        return utf;
    }
    
    public String string_to_utf(final String s) {
        if (null == s) {
            return null;
        }
        if (null == this.b) {
            this.b = new ByteArrayOutputStream();
        }
        if (null == this.d) {
            this.d = new DataOutputStream(this.b);
        }
        this.b.reset();
        try {
            this.d.writeUTF(s);
        }
        catch (IOException ex) {}
        final byte[] byteArray = this.b.toByteArray();
        return new String(byteArray, 0, 2, byteArray.length - 2);
    }
    
    public void show_message_board() {
        if (this.show_bboard) {
            if (this.get_boolean_arg("ENHANCED_MSGBOARD", false)) {
                final int get_int_arg = this.get_int_arg("BOARD_ID", 1);
                final String string = "http://www.groupboard.com/forums/" + get_int_arg;
                try {
                    System.out.println("showing url " + string);
                    this.getAppletContext().showDocument(new URL(string), "forums_" + get_int_arg);
                }
                catch (Exception ex) {
                    System.out.println("Error showing url " + string);
                }
            }
            else if (null == this.bbwin) {
                this.bbwin = new frame_bboard(this.is, this.os, this.is_admin, this);
                (this.bb = this.bbwin.gui).get_article_index();
            }
            else {
                this.bbwin.toFront();
            }
        }
    }
    
    public void show_extended_palette() {
        if (null != this.coloursel) {
            this.coloursel.toFront();
        }
        else {
            this.coloursel = new colour_selector(this.gui.draw_data);
        }
    }
    
    public void show_chat() {
        if (this.show_chat_area) {
            if (null == this.gui.fr_chat && null != this.gui.chat) {
                this.gui.fr_chat = new chat_frame(this.gui.chat, this.gui);
            }
            else if (null != this.gui.fr_chat) {
                this.gui.fr_chat.toFront();
            }
        }
    }
    
    public void show_help() {
        final String s = "http://www.groupboard.com/user.html";
        try {
            System.out.println("showing url " + s);
            this.getAppletContext().showDocument(new URL(s), "_blank");
        }
        catch (Exception ex) {
            System.out.println("Error showing url " + s);
        }
    }
    
    public void show_games() {
        if (this.show_games) {
            if (null == this.gameswin) {
                final frame_games gameswin = new frame_games(this.is, this.os, this.is_admin, this, null);
                this.gameswin = gameswin;
                (this.games = gameswin.gui).list_games();
            }
            else {
                this.gameswin.toFront();
            }
        }
    }
    
    public void set_thickness(final int n) {
        if (n < 1 || n > 10) {
            System.out.println("invalid thickness " + n);
        }
        if (null != this.gui && null != this.gui.draw_data) {
            this.gui.draw_data.set_thickness(n);
        }
    }
    
    public void set_background(final String s) {
        if (null == s) {
            this.set_background();
        }
        else if (null != this.gui && null != this.gui.draw_data && !this.gui.draw_data.load_disabled && !this.gui.draw_data.draw_disabled) {
            this.gui.draw_data.set_background(s);
        }
    }
    
    public void show_drawing_log() {
        if (null != this.gui && null != this.gui.draw_data) {
            this.gui.draw_data.show_log();
        }
    }
    
    public void set_allow_drawing(final boolean b) {
        if (null != this.gui && null != this.gui.draw_data) {
            try {
                if (!b) {
                    this.os.writeByte(38);
                }
                else {
                    this.os.writeByte(39);
                }
                if (this.use_tunnel) {
                    this.t.force_post();
                }
            }
            catch (IOException ex) {
                this.dataError();
            }
        }
    }
    
    public void set_allow_loading(final boolean b) {
        if (null != this.gui && null != this.gui.draw_data) {
            try {
                if (!b) {
                    this.os.writeByte(43);
                }
                else {
                    this.os.writeByte(44);
                }
                if (this.use_tunnel) {
                    this.t.force_post();
                }
            }
            catch (IOException ex) {
                this.dataError();
            }
        }
    }
    
    public void set_allow_clearing(final boolean b) {
        if (null != this.gui && null != this.gui.draw_data) {
            try {
                if (!b) {
                    this.os.writeByte(31);
                }
                else {
                    this.os.writeByte(32);
                }
                if (this.use_tunnel) {
                    this.t.force_post();
                }
            }
            catch (IOException ex) {
                this.dataError();
            }
        }
    }
    
    public void set_tool(final int n) {
        if (n < 1 || n > 64) {
            System.out.println("invalid tool " + n);
            return;
        }
        if (null != this.gui && null != this.gui.draw_data) {
            this.gui.draw_data.set_draw_mode(n);
        }
    }
    
    public void clear() {
        if (null != this.gui && null != this.gui.draw_data) {
            if (this.gui.draw_data.clear_disabled || this.gui.draw_data.draw_disabled) {
                new message_box(this, "Error", "Clearing is currently disabled");
            }
            else {
                this.gui.draw_data.clear_array(true, false);
            }
        }
    }
    
    public void set_colour(final int n) {
        if (n < 0 || n > 255) {
            System.out.println("invalid colour " + n);
        }
        if (null != this.gui && null != this.gui.draw_data) {
            this.gui.draw_data.set_current_colour(this.gui.draw_data.col_array[n]);
        }
    }
    
    public void load() {
        if (null != this.gui && null != this.gui.draw_data) {
            if (!this.gui.draw_data.load_disabled && !this.gui.draw_data.draw_disabled) {
                this.load_pic();
            }
            else {
                new message_box(this, "Error", "Loading is currently disabled");
            }
        }
    }
    
    public void show_banlist() {
        if (null == this.banlist_win) {
            this.banlist_win = new banlist_window(this, this.gui.draw_data);
            try {
                this.os.writeByte(21);
            }
            catch (IOException ex) {
                this.dataError();
            }
            if (this.use_tunnel) {
                this.t.force_post();
            }
        }
    }
    
    public void show_board_info() {
        if (null == this.board_info_win) {
            this.board_info_win = new board_info_window(this);
            try {
                this.os.writeByte(35);
            }
            catch (IOException ex) {
                this.dataError();
            }
            if (this.use_tunnel) {
                this.t.force_post();
            }
        }
    }
    
    public void save() {
        if (null != this.gui && null != this.gui.draw_data) {
            if (!this.gui.draw_data.draw_disabled) {
                this.gui.draw_data.save_pic();
            }
            else {
                new message_box(this, "Error", "Drawing is currently disabled");
            }
        }
    }
    
    void log_message(final String s) {
        if (groupboard.is_applet) {
            this.showStatus(s);
        }
        System.out.println(s);
    }
    
    String get_arg(final String s) {
        if (groupboard.is_applet) {
            return this.getParameter(s);
        }
        for (int i = 0; i < groupboard.app_args.length; i += 2) {
            if (groupboard.app_args[i].equalsIgnoreCase(s)) {
                return groupboard.app_args[i + 1];
            }
        }
        return null;
    }
    
    int get_int_arg(final String s, final int n) {
        final String get_arg = this.get_arg(s);
        if (null != get_arg) {
            return Integer.valueOf(get_arg);
        }
        return n;
    }
    
    boolean get_boolean_arg(final String s, final boolean b) {
        final String get_arg = this.get_arg(s);
        if (null != get_arg) {
            return Boolean.valueOf(get_arg);
        }
        return b;
    }
    
    void dataError() {
        this.log_message("Error sending data to server");
        if (null != this.gui && null != this.gui.users_label) {
            this.gui.users_label.setText("Lost connection to server - try reloading page");
        }
        if (this.get_boolean_arg("LOST_CONNECTION_BOX", true) && System.currentTimeMillis() - this.last_error_time > 2000L) {
            new message_box(this, "Error", "Lost connection to server - try reloading page");
        }
    }
    
    public void init() {
        this.asked_for_password = false;
        this.last_event_time = System.currentTimeMillis();
        this.ignored_stop = false;
        this.base = null;
        this.use_tunnel = this.get_boolean_arg("USE_TUNNEL", false);
        this.disable_anti_aliasing = this.get_boolean_arg("DISABLE_ANTI_ALIASING", false);
        this.use_utf = this.get_boolean_arg("USE_UTF", false);
        this.rtl = this.get_boolean_arg("RIGHT_TO_LEFT", false);
        if (!this.get_boolean_arg("DONT_START", false) && !this.get_boolean_arg("HIDE_LOADING_WIN", false) && null == this.loading_win) {
            this.loading_win = new loading_window();
        }
        final String property = System.getProperty("java.version");
        System.out.println("Groupboard Portal v2.16, Copyright 1998-2011 Group Technologies Inc. (info-contact@groupboard.com)");
        System.out.println("jdk version=" + property);
        final String substring = property.substring(0, 3);
        this.jdk_main_version = Float.valueOf(substring);
        final String property2 = System.getProperty("os.name");
        System.out.println("os.name=" + property2);
        this.is_mac_osx = false;
        if (property2.length() >= 8) {
            this.is_mac_osx = property2.substring(0, 8).equals("Mac OS X");
            if (this.is_mac_osx) {
                final String property3 = System.getProperty("os.version");
                System.out.println("os.version=" + property3);
                if (property3.substring(0, 4).equals("10.4")) {
                    final String property4 = System.getProperty("apple.awt.graphics.UseQuartz");
                    System.out.println("UseQuartz=" + property4);
                    if (null != property4 && !property4.equals("false")) {
                        new message_box(this, "Warning", "There is a bug in OSX 10.4 java which causes Groupboard to run very slowly.\nYou should add the following to your java applet runtime parameters\n(go to your java preferences, in the Utilities folder):\n     -Dapple.awt.graphics.UseQuartz=false");
                    }
                }
            }
        }
        this.new_jdk = true;
        if (substring.equals("1.0")) {
            this.new_jdk = false;
        }
        if (substring.equals("1.1") || substring.equals("1.2") || substring.equals("1.3") || substring.equals("1.4") || substring.equals("1.5")) {
            this.new_jdk = true;
        }
        if (property.equals("1.1.2")) {
            this.new_jdk = false;
        }
        else if (property.equals("1.1.5") && System.getProperty("os.name").equals("Mac OS")) {
            this.new_jdk = false;
        }
        this.num_clients = 0;
        this.get_arg("SERVER_ADDRESS");
        this.gallery_pic = this.get_arg("GALLERY_PIC");
        this.multiuser = this.get_boolean_arg("MULTI_USER", true);
        this.display_num_users = this.get_boolean_arg("DISPLAY_NUM_USERS", this.display_num_users);
        this.bg_red = this.get_int_arg("WHITEBOARD_BACKGROUND_RED", this.bg_red);
        this.bg_green = this.get_int_arg("WHITEBOARD_BACKGROUND_GREEN", this.bg_green);
        this.bg_blue = this.get_int_arg("WHITEBOARD_BACKGROUND_BLUE", this.bg_blue);
        this.board_width = this.get_int_arg("BOARD_WIDTH", 360);
        this.board_height = this.get_int_arg("BOARD_HEIGHT", 250);
        this.allow_save = this.get_boolean_arg("ALLOW_SAVE", true);
        this.allow_load = this.get_boolean_arg("ALLOW_LOAD", true);
        this.allow_set_background = this.get_boolean_arg("ALLOW_SET_BACKGROUND", true);
        this.need_password = this.get_boolean_arg("NEED_PASSWORD", false);
        this.need_username = this.get_boolean_arg("NEED_USERNAME", false);
        this.min_actions = this.get_boolean_arg("MIN_ACTIONS", false);
    }
    
    public void setup_gui(final int n) {
        if ((n & 0x8) == 0x0) {
            if (this.board_width > 520) {
                this.board_width = 360;
            }
            this.board_height = 250;
        }
        this.show_whiteboard = ((n & 0x1) != 0x0);
        this.show_bboard = ((n & 0x4) != 0x0);
        this.show_chat_area = ((n & 0x2) != 0x0);
        this.show_adverts = ((n & 0x10) != 0x0);
        this.show_games = ((n & 0x20) != 0x0);
        if (!this.multiuser) {
            this.show_chat_area = false;
        }
        if (null != this.loading_win) {
            this.loading_win.dispose();
            this.loading_win = null;
        }
        final boolean get_boolean_arg = this.get_boolean_arg("ALWAYS_FLOAT", false);
        if (groupboard.is_applet && !get_boolean_arg) {
            final int get_int_arg = this.get_int_arg("BACKGROUND_RED", 0);
            final int get_int_arg2 = this.get_int_arg("BACKGROUND_GREEN", 0);
            final int get_int_arg3 = this.get_int_arg("BACKGROUND_BLUE", 0);
            if (null != this.get_arg("BACKGROUND_RED")) {
                final Color background = new Color(get_int_arg, get_int_arg2, get_int_arg3);
                if (null != background) {
                    this.setBackground(background);
                }
            }
            final int get_int_arg4 = this.get_int_arg("FOREGROUND_RED", 0);
            final int get_int_arg5 = this.get_int_arg("FOREGROUND_GREEN", 0);
            final int get_int_arg6 = this.get_int_arg("FOREGROUND_BLUE", 0);
            if (null != this.get_arg("FOREGROUND_RED")) {
                final Color foreground = new Color(get_int_arg4, get_int_arg5, get_int_arg6);
                if (null != foreground) {
                    this.setForeground(foreground);
                }
            }
            this.br_gui = new browser_gui(this, this.allow_save, this.allow_load, this.allow_set_background, this.is_admin, this.min_actions, this.show_chat_area, this.show_whiteboard, this.show_bboard, this.show_games, this.show_adverts, this.multiuser, this.gallery_pic, this.board_width, this.board_height, this.display_num_users, this.bg_red, this.bg_green, this.bg_blue);
            this.gui = this.br_gui.gui;
            if (this.is_mac_osx) {
                this.setLayout(new BorderLayout(0, 0));
            }
            else {
                this.setLayout(new FlowLayout(1, 0, 0));
            }
            this.add(this.br_gui);
        }
        else {
            this.fr_gui = new frame_gui(this, this.allow_save, this.allow_load, this.allow_set_background, this.is_admin, this.min_actions, this.show_chat_area, this.show_whiteboard, this.show_bboard, this.show_games, this.show_adverts, this.multiuser, this.gallery_pic, this.board_width, this.board_height, this.display_num_users, this.bg_red, this.bg_green, this.bg_blue);
            this.gui = this.fr_gui.gui;
        }
        this.validate();
        if (this.show_whiteboard) {
            final String get_arg = this.get_arg("BACKGROUND_PIC");
            if (null != get_arg) {
                final int get_int_arg7 = this.get_int_arg("BOARD_ID", 1);
                this.gui.draw_data.add_to_array(10, 0, 0, 0, 0, 0, 0, "data/" + get_int_arg7 / 1000 + "/" + get_int_arg7 % 1000 + "/backgrounds/" + get_arg, null, false);
                this.gui.draw_data.update_panel(this.gui.draw_data.off_screen_gc, false);
            }
        }
        this.repaint();
    }
    
    public void start() {
        if (this.ignored_stop) {
            return;
        }
        final String get_arg = this.get_arg("PASSWORD");
        if (null != get_arg) {
            this.password = get_arg;
        }
        final String get_arg2 = this.get_arg("SET_NAME");
        if (null != get_arg2) {
            this.username = get_arg2;
        }
        if (!this.get_boolean_arg("DONT_START", false)) {
            if (this.need_password && this.password == null) {
                new password_window(this, this.need_username);
            }
            else {
                this.start2();
            }
        }
    }
    
    public void startup() {
        final String get_arg = this.get_arg("PASSWORD");
        if (null != get_arg) {
            this.password = get_arg;
        }
        final String get_arg2 = this.get_arg("SET_NAME");
        if (null != get_arg2) {
            this.username = get_arg2;
        }
        if (this.need_password && this.password == null) {
            new password_window(this, this.need_username);
        }
        else {
            this.start2();
        }
    }
    
    public void start2() {
        final String get_arg = this.get_arg("GALLERY_PIC");
        this.log_message("Trying to connect to Groupboard server...");
        if (this.connect_to_server(41211, get_arg, this.multiuser)) {
            (this.server_read_thread = new server_read(this, this.is)).start();
            (this.housekeeping_thread = new housekeeping(this)).start();
        }
        else {
            this.use_tunnel = true;
            if (this.connect_to_server(41211, get_arg, this.multiuser)) {
                (this.server_read_thread = new server_read(this, this.is)).start();
                (this.housekeeping_thread = new housekeeping(this)).start();
            }
            else {
                this.log_message("Error connecting to server");
                new message_box(this, "Error", "Error connecting to server");
                this.really_stop();
            }
        }
    }
    
    public void stop() {
        this.ignored_stop = false;
        if (null == this.fr_gui) {
            if (null != this.base) {
                this.ignored_stop = true;
            }
            else {
                this.really_stop();
                if (null != this.gui && null != this.gui.fr_chat) {
                    this.gui.fr_chat.dispose();
                    this.gui.fr_chat = null;
                }
            }
        }
    }
    
    void really_stop() {
        if (null != this.server_read_thread) {
            this.server_read_thread.stop();
            this.server_read_thread = null;
        }
        if (null != this.tunnel_thread) {
            this.tunnel_thread.stop();
            this.tunnel_thread = null;
        }
        if (null != this.housekeeping_thread) {
            this.housekeeping_thread.stop();
            this.housekeeping_thread = null;
        }
        if (null != this.changer) {
            this.changer.stop();
            this.changer = null;
        }
        if (null != this.base) {
            this.base.dispose();
            this.base = null;
        }
        if (null != this.loading_win) {
            this.loading_win.dispose();
            this.loading_win = null;
        }
        if (null != this.bbwin) {
            this.bbwin.dispose();
            this.bbwin = null;
        }
        if (null != this.gui && null != this.gui.draw_data && null != this.gui.draw_data.log_win) {
            this.gui.draw_data.log_win.dispose();
            this.gui.draw_data.log_win = null;
        }
        this.close_connection();
    }
    
    private boolean connect_to_server(int unsignedShort, String string_to_utf, final boolean b) {
        String get_arg = this.get_arg("SERVER_ADDRESS");
        final InetAddress inetAddress = null;
        if (null == this.get_arg("BOARD_ID")) {
            if (!groupboard.error_given) {
                new message_box(this, "Error", "board_id parameter missing from html code");
                groupboard.error_given = true;
            }
            return false;
        }
        final int get_int_arg = this.get_int_arg("BOARD_ID", 1);
        try {
            if (null == get_arg) {
                get_arg = "www.groupboard.com";
            }
            this.log_message("Getting port number...");
            if (this.use_tunnel) {
                this.t = new tunnel(this);
                if (!this.t.open(get_arg, this.get_int_arg("TUNNEL_PORT", 6666), unsignedShort)) {
                    return false;
                }
                this.os = new DataOutputStream(this.t.getOutputStream());
            }
            else {
                if (null != get_arg) {
                    this.server = new Socket(get_arg, unsignedShort);
                }
                else {
                    this.server = new Socket(inetAddress, unsignedShort);
                }
                if (null == this.server) {
                    return false;
                }
                this.os = new DataOutputStream(this.server.getOutputStream());
                this.is = new DataInputStream(this.server.getInputStream());
            }
            synchronized (this.os) {
                this.os.writeByte(8);
                this.os.writeShort(5);
                this.os.writeInt(get_int_arg);
                if (b) {
                    this.os.writeByte(1);
                }
                else {
                    this.os.writeByte(0);
                }
            }
            if (this.use_tunnel) {
                this.t.post();
                this.t.get(5);
                this.is = new DataInputStream(this.t.getInputStream());
            }
            final int unsignedByte = this.is.readUnsignedByte();
            if (unsignedByte != 39) {
                this.log_message("Error connecting to server: got " + unsignedByte);
                return false;
            }
            final int unsignedShort2 = this.is.readUnsignedShort();
            if (unsignedShort2 != 2) {
                this.log_message("Error connecting to server: size " + unsignedShort2);
                return false;
            }
            unsignedShort = this.is.readUnsignedShort();
            this.log_message("Connecting to port " + unsignedShort + "...");
            this.os.close();
            this.is.close();
            if (this.use_tunnel) {
                this.t.close();
            }
            else {
                this.server.close();
            }
            if (this.use_tunnel) {
                this.t = new tunnel(this);
                if (!this.t.open(get_arg, this.get_int_arg("TUNNEL_PORT", 6666), unsignedShort)) {
                    return false;
                }
                this.os = new DataOutputStream(this.t.getOutputStream());
                if (!this.get_boolean_arg("USE_TUNNEL", false) && !this.get_boolean_arg("DONT_DISPLAY_TUNNEL_WARNING", false)) {
                    final message_box message_box = new message_box(this, "Firewall Tunnelling", "Using firewall tunnelling (port " + this.get_int_arg("TUNNEL_PORT", 6666) + "): performance may be reduced.\n" + "To connect to Groupboard directly, you need to open the following TCP/IP ports\n" + "(outbound) on your firewall: " + 41211 + " and " + unsignedShort, 4, 70);
                }
            }
            else {
                this.server = new Socket(get_arg, unsignedShort);
                this.os = new DataOutputStream(this.server.getOutputStream());
                this.is = new DataInputStream(this.server.getInputStream());
            }
            if (this.use_tunnel) {
                this.t.get(1);
                this.is = new DataInputStream(this.t.getInputStream());
            }
            this.log_message("Logging on...");
            if (this.is.readByte() != 2) {
                this.log_message("Wrong version of applet");
                return false;
            }
            synchronized (this.os) {
                if (this.need_username) {
                    this.os.writeByte(54);
                }
                else {
                    this.os.writeByte(15);
                }
                this.os.writeInt(get_int_arg);
                String s = this.username;
                String s2 = this.password;
                if (this.use_utf) {
                    s = this.string_to_utf(s);
                    s2 = this.string_to_utf(s2);
                }
                if (this.need_username) {
                    if (null == s) {
                        this.os.writeShort(0);
                    }
                    else {
                        this.os.writeShort(s.length());
                    }
                }
                if (null == s2) {
                    this.os.writeShort(0);
                }
                else {
                    this.os.writeShort(s2.length());
                }
                if (this.need_username && s != null && s.length() > 0) {
                    this.os.writeBytes(s);
                }
                if (s2 != null && s2.length() > 0) {
                    this.os.writeBytes(s2);
                }
            }
        }
        catch (UnknownHostException ex) {
            this.log_message("Error connecting to server (unknown host): " + ex.getMessage());
            return false;
        }
        catch (IOException ex2) {
            this.log_message("Error connecting to server (IO exception): " + ex2.getMessage());
            return false;
        }
        this.log_message("Connected to server");
        if (null != string_to_utf) {
            try {
                synchronized (this.os) {
                    if (this.use_utf) {
                        string_to_utf = this.string_to_utf(string_to_utf);
                    }
                    this.os.writeByte(9);
                    this.os.writeShort(string_to_utf.length());
                    if (string_to_utf.length() > 0) {
                        this.os.writeBytes(string_to_utf);
                    }
                }
            }
            catch (IOException ex3) {
                this.dataError();
                return false;
            }
        }
        if (this.use_tunnel) {
            this.t.post();
            (this.tunnel_thread = new tunnel_writer(this.t)).start();
        }
        return true;
    }
    
    void close_connection() {
        if (null != this.os && null != this.is) {
            try {
                this.os.close();
                this.is.close();
                if (this.use_tunnel) {
                    this.t.close();
                }
                else {
                    this.server.close();
                }
                this.log_message("closing connection to server");
                this.os = null;
                this.is = null;
                this.server = null;
                this.t = null;
            }
            catch (IOException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        if (null != this.gui) {
            if (null != this.gui.draw_data) {
                this.gui.draw_data.repaint();
            }
            if (null != this.gui.controls) {
                this.gui.controls.repaint();
            }
        }
    }
    
    public String getAppletInfo() {
        return "Groupboard, Copyright (c) 1998-2006 Group Technologies Inc. (info@groupboard.com)";
    }
    
    public void print_pic() {
        this.need_to_print = true;
    }
    
    void actual_print() {
        try {
            if (null != this.fr_gui) {
                final PrintJob printJob = this.getToolkit().getPrintJob(this.fr_gui, "Print", null);
                if (printJob != null) {
                    final Graphics graphics = printJob.getGraphics();
                    graphics.drawImage(this.gui.draw_data.off_screen_buf, 0, 0, null);
                    graphics.dispose();
                    printJob.end();
                }
            }
            else {
                final Frame frame = new Frame();
                final PrintJob printJob2 = this.getToolkit().getPrintJob(frame, "Print", null);
                if (printJob2 != null) {
                    final Graphics graphics2 = printJob2.getGraphics();
                    graphics2.drawImage(this.gui.draw_data.off_screen_buf, 0, 0, null);
                    graphics2.dispose();
                    printJob2.end();
                }
                frame.dispose();
            }
        }
        catch (Exception ex) {
            if (this.get_boolean_arg("DISPLAY_PRINT_ERROR", true)) {
                new message_box(this, "Error", "Your web browser does not support printing from Java.Please see groupboard.com/support/faq.html for details.");
            }
        }
    }
    
    public void make_float() {
        if (null != this.br_gui && null == this.base) {
            String text = "";
            if (null != this.gui.chat) {
                this.gui.chat.send_name_change("closing connection...");
                text = this.gui.chat.name.getText();
            }
            this.remove(this.br_gui);
            this.validate();
            this.br_gui = null;
            this.fr_gui = new frame_gui(this, this.allow_save, this.allow_load, this.allow_set_background, this.is_admin, this.min_actions, this.show_chat_area, this.show_whiteboard, this.show_bboard, this.show_games, this.show_adverts, this.multiuser, this.gallery_pic, this.board_width, this.board_height, this.display_num_users, this.bg_red, this.bg_green, this.bg_blue);
            this.gui = this.fr_gui.gui;
            this.really_stop();
            final String get_arg = this.get_arg("BACKGROUND_PIC");
            if (null != get_arg) {
                final int get_int_arg = this.get_int_arg("BOARD_ID", 1);
                this.gui.draw_data.add_to_array(10, 0, 0, 0, 0, 0, 0, "data/" + get_int_arg / 1000 + "/" + get_int_arg % 1000 + "/backgrounds/" + get_arg, null, false);
                this.gui.draw_data.update_panel(this.gui.draw_data.off_screen_gc, false);
                this.repaint();
            }
            this.start();
            if (null != this.gui.chat && !this.gui.chat.name.equals("")) {
                this.gui.chat.name.setText(text);
                this.gui.chat.prev_name = "";
                this.gui.chat.send_name_change(text);
            }
            if (null != this.base) {
                this.base.set_os(this.os);
            }
            if (null != this.games) {
                this.games.list_games();
            }
            if (null != this.bb) {
                this.bb.set_os(this.os);
                this.bb.get_article_index();
            }
        }
    }
    
    public void make_unfloat() {
        if (null != this.fr_gui && null == this.base) {
            String text = "";
            if (null != this.gui.chat) {
                this.gui.chat.send_name_change("closing connection...");
                text = this.gui.chat.name.getText();
            }
            this.fr_gui.dispose();
            if (null != this.gui.fr_chat) {
                this.gui.fr_chat.dispose();
                this.gui.fr_chat = null;
            }
            if (null != this.gui.fr_games) {
                this.gui.fr_games.dispose();
                this.gui.fr_games = null;
            }
            this.gui = null;
            this.fr_gui = null;
            this.br_gui = new browser_gui(this, this.allow_save, this.allow_load, this.allow_set_background, this.is_admin, this.min_actions, this.show_chat_area, this.show_whiteboard, this.show_bboard, this.show_games, this.show_adverts, this.multiuser, this.gallery_pic, this.board_width, this.board_height, this.display_num_users, this.bg_red, this.bg_green, this.bg_blue);
            this.gui = this.br_gui.gui;
            this.add(this.br_gui);
            this.validate();
            this.really_stop();
            final String get_arg = this.get_arg("BACKGROUND_PIC");
            if (null != get_arg) {
                final int get_int_arg = this.get_int_arg("BOARD_ID", 1);
                this.gui.draw_data.add_to_array(10, 0, 0, 0, 0, 0, 0, "data/" + get_int_arg / 1000 + "/" + get_int_arg % 1000 + "/backgrounds/" + get_arg, null, false);
                this.gui.draw_data.update_panel(this.gui.draw_data.off_screen_gc, false);
                this.repaint();
            }
            this.start();
            if (null != this.gui.chat && !this.gui.chat.name.equals("")) {
                this.gui.chat.name.setText(text);
                this.gui.chat.prev_name = "";
                this.gui.chat.send_name_change(text);
            }
            if (null != this.base) {
                this.base.set_os(this.os);
            }
            if (null != this.games) {
                this.games.list_games();
            }
            if (null != this.bb) {
                this.bb.set_os(this.os);
                this.bb.get_article_index();
            }
        }
    }
    
    public static void main(final String[] app_args) {
        groupboard.app_args = app_args;
        final groupboard groupboard = new groupboard();
        groupboard.is_applet = false;
        groupboard.init();
        groupboard.start();
    }
    
    void load_pic() {
        if (null != this.gui && null != this.gui.draw_data && !this.gui.draw_data.load_disabled && !this.gui.draw_data.draw_disabled) {
            if (null == this.load_pic_win) {
                this.load_pic_win = new load_pic_window(this, this.gui.draw_data, this.is_admin);
                try {
                    this.os.writeByte(24);
                }
                catch (IOException ex) {
                    this.dataError();
                }
                if (this.use_tunnel) {
                    this.t.force_post();
                }
            }
            else {
                this.load_pic_win.toFront();
            }
        }
    }
    
    public void set_background() {
        if (null != this.gui && null != this.gui.draw_data && !this.gui.draw_data.load_disabled && !this.gui.draw_data.draw_disabled) {
            if (null == this.set_win) {
                final boolean get_boolean_arg = this.get_boolean_arg("ALLOW_NONADMIN_UPLOAD", false);
                this.set_win = new set_background_window(this, this.gui.draw_data, this.is_admin, this.is_admin || get_boolean_arg);
                try {
                    this.os.writeByte(26);
                }
                catch (IOException ex) {
                    this.dataError();
                }
                if (this.use_tunnel) {
                    this.t.force_post();
                }
            }
            else {
                this.set_win.toFront();
            }
        }
    }
    
    public final void get_game_info(final int n, String s) {
        s = s.replace(' ', '_');
        s = s.toLowerCase();
        try {
            synchronized (this.os) {
                this.os.writeByte(129);
                this.os.writeInt(n);
                this.os.writeShort(s.length());
                if (s.length() > 0) {
                    this.os.writeBytes(s);
                }
            }
        }
        catch (IOException ex) {
            this.dataError();
            return;
        }
        if (this.use_tunnel) {
            this.t.force_post();
        }
    }
    
    public final void create_game(String s, String string_to_utf) {
        s = s.replace(' ', '_');
        s = s.toLowerCase();
        try {
            synchronized (this.os) {
                if (this.use_utf) {
                    string_to_utf = this.string_to_utf(string_to_utf);
                }
                this.os.writeByte(131);
                this.os.writeShort(s.length());
                this.os.writeBytes(s);
                this.os.writeShort(string_to_utf.length());
                this.os.writeBytes(string_to_utf);
            }
        }
        catch (IOException ex) {
            this.dataError();
            return;
        }
        if (this.use_tunnel) {
            this.t.force_post();
        }
    }
    
    public final void join_game(final int n, String string_to_utf) {
        try {
            synchronized (this.os) {
                if (this.use_utf) {
                    string_to_utf = this.string_to_utf(string_to_utf);
                }
                this.os.writeByte(130);
                this.os.writeInt(n);
                this.os.writeShort(string_to_utf.length());
                if (string_to_utf.length() > 0) {
                    this.os.writeBytes(string_to_utf);
                }
            }
        }
        catch (IOException ex) {
            this.dataError();
            return;
        }
        if (this.use_tunnel) {
            this.t.force_post();
        }
    }
    
    String replace_text(String string, final String s, final String s2) {
        final int index = string.indexOf(s);
        if (-1 == index) {
            return string;
        }
        string = string.substring(0, index) + s2 + string.substring(index + s.length());
        return string;
    }
    
    String replace_all_text(String string, final String s, final String s2) {
        while (true) {
            final int index = string.indexOf(s);
            if (-1 == index) {
                break;
            }
            string = string.substring(0, index) + s2 + string.substring(index + s.length());
        }
        return string;
    }
    
    Image load_image(final String s) {
        if (groupboard.is_applet) {
            return this.getImage(this.getCodeBase(), s);
        }
        return this.getToolkit().getImage(s);
    }
    
    public void load_pic(String string_to_utf) {
        final draw_panel draw_data = this.gui.draw_data;
        if (null != string_to_utf) {
            if (null != draw_data.last_bg_image) {
                draw_data.last_bg_image.flush();
            }
            draw_data.last_bg = null;
            draw_data.last_bg_image = null;
            draw_data.clear_array(true, false);
            try {
                synchronized (this.os) {
                    this.os.writeByte(9);
                    if (this.use_utf) {
                        string_to_utf = this.string_to_utf(string_to_utf);
                    }
                    this.os.writeShort(string_to_utf.length());
                    if (string_to_utf.length() > 0) {
                        this.os.writeBytes(string_to_utf);
                    }
                }
            }
            catch (IOException ex) {
                this.dataError();
                return;
            }
            if (this.use_tunnel) {
                this.t.force_post();
            }
        }
    }
    
    void ban_client(final int n, String string_to_utf) {
        try {
            synchronized (this.os) {
                this.os.writeByte(74);
                this.os.writeInt(n);
                if (this.use_utf) {
                    string_to_utf = this.string_to_utf(string_to_utf);
                }
                this.os.writeShort(string_to_utf.length());
                if (string_to_utf.length() > 0) {
                    this.os.writeBytes(string_to_utf);
                }
            }
        }
        catch (IOException ex) {
            this.dataError();
        }
        if (this.use_tunnel) {
            this.t.force_post();
        }
    }
    
    static {
        groupboard.error_given = false;
        groupboard.is_applet = true;
    }
}
