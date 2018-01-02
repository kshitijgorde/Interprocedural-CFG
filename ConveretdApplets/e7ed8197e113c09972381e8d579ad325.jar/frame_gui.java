import java.io.IOException;
import java.awt.Event;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class frame_gui extends Frame implements groupboard_consts
{
    groupboard_gui gui;
    
    frame_gui(final groupboard groupboard, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final boolean b8, final boolean b9, final boolean b10, final boolean b11, final String s, final int n, final int n2, final boolean b12, final int n3, final int n4, final int n5) {
        this.setTitle("Groupboard");
        this.gui = new groupboard_gui(groupboard, b, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, s, n, n2, true, b12, n3, n4, n5);
        this.create_menus(b7, b4, b2, b, b3, b6 && (!groupboard.get_boolean_arg("SEPARATE_CHAT_WINDOW", true) || !b7), b8);
        this.add("Center", this.gui);
        this.pack();
        this.show();
        if (null != this.gui.fr_chat) {
            this.gui.fr_chat.show();
        }
        if (null != this.gui.fr_games) {
            this.gui.fr_games.show();
        }
    }
    
    void create_menus(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7) {
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("Applet");
        menu.add(new MenuItem("Print..."));
        menu.addSeparator();
        menu.add(new MenuItem("Unfloat"));
        menu.add(new MenuItem("Exit"));
        final Menu menu2 = new Menu("Whiteboard");
        if (b3) {
            menu2.add(new MenuItem("Load Picture..."));
        }
        if (b4) {
            menu2.add(new MenuItem("Save Picture..."));
        }
        if (b5) {
            menu2.add(new MenuItem("Set Background..."));
        }
        final CheckboxMenuItem checkboxMenuItem;
        menu2.add(checkboxMenuItem = new CheckboxMenuItem("Allow flood fills"));
        checkboxMenuItem.setState(this.gui.parent.allow_fills);
        final Menu menu3 = new Menu("Chat");
        if (b6) {
            menu3.add(new MenuItem("Ignore User"));
            menu3.add(new MenuItem("Chat To All"));
            menu3.add(new MenuItem("Save Text..."));
            menu3.add(new MenuItem("Clear Text"));
            final CheckboxMenuItem checkboxMenuItem2;
            menu3.add(checkboxMenuItem2 = new CheckboxMenuItem("Beep on Whisper if Idle"));
            checkboxMenuItem2.setState(this.gui.parent.beep_on_whisper);
        }
        final Menu menu4 = new Menu("Message Board");
        if (b7) {
            menu4.add(new MenuItem("Display Messages..."));
            menu4.add(new MenuItem("Post New Message..."));
        }
        final Menu menu5 = new Menu("Admin");
        if (b2) {
            menu5.add(new MenuItem("Ban Users..."));
            if (b) {
                menu5.add(new MenuItem("Show Drawing Log..."));
                menu5.add(new CheckboxMenuItem("Disable Clear"));
                menu5.add(new CheckboxMenuItem("Disable Drawing"));
                menu5.add(new CheckboxMenuItem("Disable Loading"));
            }
            menu5.add(new MenuItem("Board Info..."));
        }
        final Menu helpMenu = new Menu("Help");
        helpMenu.add(new MenuItem("Help"));
        helpMenu.addSeparator();
        helpMenu.add(new MenuItem("About"));
        menuBar.add(menu);
        if (b) {
            menuBar.add(menu2);
        }
        if (b6) {
            menuBar.add(menu3);
        }
        if (b7) {
            menuBar.add(menu4);
        }
        if (b2) {
            menuBar.add(menu5);
        }
        menuBar.setHelpMenu(helpMenu);
        this.setMenuBar(menuBar);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof CheckboxMenuItem) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)event.target;
            final String label = checkboxMenuItem.getLabel();
            if (label.equals("Disable Clear")) {
                try {
                    if (checkboxMenuItem.getState()) {
                        this.gui.parent.os.writeByte(31);
                    }
                    else {
                        this.gui.parent.os.writeByte(32);
                    }
                    if (this.gui.parent.use_tunnel) {
                        this.gui.parent.t.force_post();
                    }
                }
                catch (IOException ex) {
                    this.gui.parent.dataError();
                }
            }
            else if (label.equals("Disable Drawing")) {
                try {
                    if (checkboxMenuItem.getState()) {
                        this.gui.parent.os.writeByte(38);
                    }
                    else {
                        this.gui.parent.os.writeByte(39);
                    }
                    if (this.gui.parent.use_tunnel) {
                        this.gui.parent.t.force_post();
                    }
                }
                catch (IOException ex2) {
                    this.gui.parent.dataError();
                }
            }
            else if (label.equals("Disable Loading")) {
                try {
                    if (checkboxMenuItem.getState()) {
                        this.gui.parent.os.writeByte(43);
                    }
                    else {
                        this.gui.parent.os.writeByte(44);
                    }
                    if (this.gui.parent.use_tunnel) {
                        this.gui.parent.t.force_post();
                    }
                }
                catch (IOException ex3) {
                    this.gui.parent.dataError();
                }
            }
            else if (label.equals("Beep on Whisper if Idle")) {
                this.gui.parent.beep_on_whisper = checkboxMenuItem.getState();
            }
            else if (label.equals("Allow flood fills")) {
                this.gui.parent.allow_fills = checkboxMenuItem.getState();
            }
        }
        else if (event.target instanceof MenuItem) {
            final String s = (String)o;
            if (s.equals("Unfloat")) {
                this.gui.parent.make_unfloat();
            }
            else if (s.equals("Help")) {
                this.gui.parent.show_help();
            }
            else if (s.equals("About")) {
                new message_box(this.gui.parent, "About", "Groupboard Portal v2.16, Copyright 1998-2011 Group Technologies Inc. (info-contact@groupboard.com)");
            }
            else if (s.equals("Print...")) {
                this.gui.parent.print_pic();
            }
            else if (s.equals("Exit")) {
                this.dispose();
                if (null != this.gui.fr_chat) {
                    this.gui.fr_chat.dispose();
                    this.gui.fr_chat = null;
                }
                if (null != this.gui.fr_games) {
                    this.gui.fr_games.dispose();
                    this.gui.fr_games = null;
                }
                this.gui.parent.really_stop();
                this.gui.parent.gui = null;
                this.gui.parent.fr_gui = null;
                this.gui = null;
            }
            else if (s.equals("Load Picture...")) {
                this.gui.parent.load_pic();
            }
            else if (s.equals("Save Picture...")) {
                this.gui.draw_data.save_pic();
            }
            else if (s.equals("Set Background...")) {
                this.gui.parent.set_background();
            }
            else if (s.equals("Ignore User")) {
                this.gui.chat.ignore();
            }
            else if (s.equals("Chat To All")) {
                this.gui.chat.users.select(0);
            }
            else if (s.equals("Save Text...")) {
                this.gui.chat.save_text();
            }
            else if (s.equals("Clear Text")) {
                this.gui.chat.clear_text();
            }
            else if (s.equals("Display Messages...")) {
                this.gui.parent.show_message_board();
            }
            else if (s.equals("Post New Message...")) {
                String prev_name = "";
                if (null != this.gui.chat && null != this.gui.chat.prev_name) {
                    prev_name = this.gui.chat.prev_name;
                }
                new msg_window(this.gui.parent, prev_name, this.gui.parent.os, "", "");
            }
            else if (s.equals("Show Drawing Log...")) {
                this.gui.draw_data.show_log();
            }
            else if (s.equals("Ban Users...")) {
                this.gui.parent.show_banlist();
            }
            else if (s.equals("Board Info...")) {
                this.gui.parent.show_board_info();
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            if (null != this.gui.fr_chat) {
                this.gui.fr_chat.dispose();
                this.gui.fr_chat = null;
            }
            if (null != this.gui.fr_games) {
                this.gui.fr_games.dispose();
                this.gui.fr_games = null;
            }
            this.gui.parent.really_stop();
            this.gui.parent.gui = null;
            this.gui.parent.fr_gui = null;
            this.gui = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
