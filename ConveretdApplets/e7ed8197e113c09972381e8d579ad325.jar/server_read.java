import java.awt.Graphics;
import java.net.SocketException;
import java.awt.Color;
import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class server_read extends Thread implements groupboard_consts
{
    private boolean got_our_name;
    private static final int MIN_DELAY = 100;
    private long last_update_time;
    private boolean display_drawing_user;
    private int last_packet;
    private int[] compress_dx;
    private int[] compress_dy;
    private draw_panel target;
    private DataInputStream is;
    private groupboard gb;
    private boolean multiuser;
    
    private void handle_bboard_packet(final int n) {
        try {
            switch (n) {
                case 103: {
                    if (this.gb.use_tunnel) {
                        this.gb.t.get(4);
                        this.is = new DataInputStream(this.gb.t.getInputStream());
                        this.gb.is = this.is;
                    }
                    final int int1 = this.is.readInt();
                    final String read_string = this.read_string();
                    final String read_string2 = this.read_string();
                    final String read_string3 = this.read_string();
                    if (null != this.gb.bb) {
                        this.gb.bb.add_article(int1, read_string + " - " + read_string2 + " (" + read_string3 + ")");
                        break;
                    }
                    break;
                }
                case 104: {
                    final String read_string4 = this.read_string();
                    if (null != this.gb.bb) {
                        String text = this.gb.replace_text(this.gb.replace_text(this.gb.replace_text(read_string4, "Date:", "Date:"), "Subject:", "Subject:"), "From:", "From:");
                        if (!this.gb.is_admin) {
                            final int index = text.indexOf("Host:");
                            if (-1 != index) {
                                final int index2 = text.indexOf(10, index);
                                if (index2 != -1) {
                                    text = text.substring(0, index) + text.substring(index2 + 1);
                                }
                            }
                        }
                        else {
                            text = this.gb.replace_text(text, "Host:", "Host:");
                        }
                        this.gb.bb.article_text.setText(text);
                        break;
                    }
                    break;
                }
                case 105:
                case 106: {
                    break;
                }
                default: {
                    this.gb.log_message("Unknown bboard packet " + n + " received from server");
                    break;
                }
            }
        }
        catch (IOException ex) {
            this.gb.dataError();
        }
    }
    
    private void handle_game_packet(final int n) {
        try {
            if (160 == n) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (this.last_update_time != -1L) {
                    final long n2 = currentTimeMillis - this.last_update_time;
                    if (n2 > 0L && n2 < 100L) {
                        try {
                            Thread.sleep(100L - n2);
                        }
                        catch (InterruptedException ex2) {}
                    }
                }
                this.last_update_time = currentTimeMillis;
                if (this.gb.use_tunnel) {
                    this.gb.t.get(2);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final short short1 = this.is.readShort();
                if (this.gb.use_tunnel) {
                    this.gb.t.get(20 * short1);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                if (short1 == 0 && null != this.gb.base) {
                    this.gb.base.send_position_update();
                }
                for (short n3 = 0; n3 < short1; ++n3) {
                    final int int1 = this.is.readInt();
                    final int int2 = this.is.readInt();
                    final int int3 = this.is.readInt();
                    final int int4 = this.is.readInt();
                    final int int5 = this.is.readInt();
                    if (null != this.gb.base) {
                        this.gb.base.handle_position_update(int1, int2, int3, int4, int5, n3 == short1 - 1);
                    }
                }
            }
            else if (150 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(8);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                this.handle_game_list(this.is.readInt(), this.is.readInt(), this.read_string());
            }
            else if (161 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(4);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                this.handle_game_list_remove(this.is.readInt());
            }
            else if (151 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(16);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final short short2 = this.is.readShort();
                final short short3 = this.is.readShort();
                final short short4 = this.is.readShort();
                final short short5 = this.is.readShort();
                final int int6 = this.is.readInt();
                final int int7 = this.is.readInt();
                final String read_string = this.read_string();
                final String read_string2 = this.read_string();
                if (null != this.gb.games) {
                    this.gb.games.handle_game_info(short2, short3, short4, short5, int6, int7, read_string, read_string2);
                }
            }
            else if (152 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(18);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final int int8 = this.is.readInt();
                final int int9 = this.is.readInt();
                final int int10 = this.is.readInt();
                final int int11 = this.is.readInt();
                final int unsignedByte = this.is.readUnsignedByte();
                final int unsignedByte2 = this.is.readUnsignedByte();
                final String read_string3 = this.read_string();
                final String read_string4 = this.read_string();
                Class<?> forName = null;
                try {
                    forName = Class.forName(read_string3);
                }
                catch (ClassNotFoundException ex3) {
                    this.gb.log_message("Error loading game " + read_string3);
                }
                if (null == forName) {
                    this.gb.log_message("Error loading game " + read_string3);
                }
                else if (null != this.gb.base) {
                    this.gb.log_message("Error: game already started");
                }
                else {
                    try {
                        this.gb.base = (game_base)forName.newInstance();
                    }
                    catch (Exception ex) {
                        System.out.println("Error creating instance of game " + read_string3 + ": " + ex.getMessage());
                    }
                    if (null != this.gb.base) {
                        this.gb.base.handle_init(this.gb.os, int8, read_string4, this.gb, int11, int9, int10, unsignedByte, unsignedByte2);
                    }
                }
            }
            else if (153 == n) {
                if (null != this.gb.base) {
                    this.gb.base.handle_start();
                }
                if (null != this.gb.games) {
                    this.gb.games.add_to_console("Game starting\n");
                }
            }
            else if (159 == n) {
                final int selectedIndex = this.gb.games.games_started.getSelectedIndex();
                if (-1 != selectedIndex && this.gb.base == null) {
                    new game_pw_window(this.gb, (int)this.gb.games.name_to_id.get(this.gb.games.games_started.getItem(selectedIndex)));
                }
            }
            else if (154 == n) {
                if (null != this.gb.base) {
                    this.gb.base.handle_restart();
                }
                if (null != this.gb.games) {
                    this.gb.games.add_to_console("Restarting game\n");
                }
            }
            else if (155 == n) {
                if (null != this.gb.base) {
                    this.gb.base.handle_end();
                }
                if (null != this.gb.games) {
                    this.gb.games.add_to_console("Game Over\n");
                }
            }
            else if (156 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(4);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final int int12 = this.is.readInt();
                final String read_string5 = this.read_string();
                if (null != this.gb.base) {
                    this.gb.base.handle_new_player(int12, read_string5);
                }
                if (null != this.gb.games) {
                    this.gb.games.add_to_console("Adding player " + read_string5 + "\n");
                }
            }
            else if (157 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(4);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final int int13 = this.is.readInt();
                if (null != this.gb.base) {
                    this.gb.base.handle_remove_player(int13);
                }
                if (null != this.gb.games) {
                    String s = "(Anon)";
                    if (null != this.gb.gui.chat) {
                        s = this.gb.gui.chat.user_hash.get(new Integer(int13));
                    }
                    this.gb.games.add_to_console("Removing player " + s + "\n");
                }
            }
            else if (158 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(4);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final int int14 = this.is.readInt();
                if (null != this.gb.base) {
                    this.gb.base.handle_resign_player(int14);
                }
                if (null != this.gb.games) {
                    String s2 = "(Anon)";
                    if (null != this.gb.gui.chat) {
                        s2 = this.gb.gui.chat.user_hash.get(new Integer(int14));
                    }
                    this.gb.games.add_to_console(s2 + " " + "has resigned" + "\n");
                }
            }
            else if (170 == n || 171 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(9);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final int int15 = this.is.readInt();
                final byte byte1 = this.is.readByte();
                final int int16 = this.is.readInt();
                final String read_string6 = this.read_string();
                if (null != this.gb.base) {
                    this.gb.base.handle_data_packet(int15, byte1, int16, read_string6);
                }
            }
            else if (172 == n || 173 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(7);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final int int17 = this.is.readInt();
                final byte byte2 = this.is.readByte();
                final short short6 = this.is.readShort();
                if (short6 > 0) {
                    if (this.gb.use_tunnel) {
                        this.gb.t.get(short6);
                        this.is = new DataInputStream(this.gb.t.getInputStream());
                        this.gb.is = this.is;
                    }
                    final byte[] array = new byte[short6];
                    this.is.readFully(array);
                    if (null != this.gb.base) {
                        this.gb.base.handle_bytearray_packet(int17, byte2, array, short6);
                    }
                }
            }
            else {
                this.gb.log_message("Unknown game packet " + n + " received from server");
            }
        }
        catch (IOException ex4) {
            this.gb.dataError();
        }
    }
    
    private void handle_chat_packet(final int n) {
        try {
            if (70 == n) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(8);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final int int1 = this.gb.is.readInt();
                final short short1 = this.gb.is.readShort();
                final short short2 = this.gb.is.readShort();
                if (this.gb.use_tunnel) {
                    this.gb.t.get(short1 + short2);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                String utf_to_string;
                if (short1 <= 0) {
                    utf_to_string = "";
                }
                else {
                    final byte[] array = new byte[short1];
                    for (int n2 = 0; short1 - n2 > 0; n2 += this.gb.is.read(array, n2, short1 - n2)) {}
                    utf_to_string = new String(array, 0);
                    if (this.gb.use_utf) {
                        utf_to_string = this.gb.utf_to_string(utf_to_string);
                    }
                }
                String s = "";
                if (short2 > 0) {
                    final byte[] array2 = new byte[short2];
                    for (int n3 = 0; short2 - n3 > 0; n3 += this.gb.is.read(array2, n3, short2 - n3)) {}
                    s = new String(array2, 0);
                }
                if (null != this.gb.gui && null != this.gb.gui.chat) {
                    this.gb.gui.chat.name_changed(int1, utf_to_string, s);
                    if (null == this.gb.get_arg("SET_NAME") && !this.got_our_name) {
                        this.gb.gui.chat.name.setText(utf_to_string);
                        this.got_our_name = true;
                    }
                }
            }
            else if (71 == n || 72 == n || 74 == n) {
                if (this.gb.use_tunnel) {
                    if (74 == n) {
                        this.gb.t.get(2);
                    }
                    else {
                        this.gb.t.get(6);
                    }
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                int int2 = -1;
                if (74 != n) {
                    int2 = this.gb.is.readInt();
                }
                final short short3 = this.gb.is.readShort();
                if (short3 > 0) {
                    if (this.gb.use_tunnel) {
                        this.gb.t.get(short3);
                        this.is = new DataInputStream(this.gb.t.getInputStream());
                        this.gb.is = this.is;
                    }
                    final byte[] array3 = new byte[short3];
                    for (int n4 = 0; short3 - n4 > 0; n4 += this.gb.is.read(array3, n4, short3 - n4)) {}
                    String utf_to_string2 = new String(array3, 0);
                    if (null != this.gb.gui && null == this.gb.gui.fr_chat && this.gb.get_boolean_arg("HIDE_CHAT", false)) {
                        this.gb.show_chat();
                    }
                    if (null != this.gb.gui && null != this.gb.gui.chat) {
                        if (this.gb.use_utf) {
                            utf_to_string2 = this.gb.utf_to_string(utf_to_string2);
                        }
                        this.gb.gui.chat.output_chat_string(int2, utf_to_string2, 72 == n, false, 74 == n);
                    }
                }
            }
            else {
                this.gb.log_message("Unknown chat packet " + n + " received from server");
            }
        }
        catch (IOException ex) {
            this.gb.dataError();
        }
    }
    
    private String read_string() {
        String utf_to_string = "";
        try {
            if (this.gb.use_tunnel) {
                this.gb.t.get(2);
                this.is = new DataInputStream(this.gb.t.getInputStream());
                this.gb.is = this.is;
            }
            final int unsignedShort = this.is.readUnsignedShort();
            if (unsignedShort > 0) {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(unsignedShort);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                final byte[] array = new byte[unsignedShort];
                for (int n = 0; unsignedShort - n > 0; n += this.is.read(array, n, unsignedShort - n)) {}
                utf_to_string = new String(array, 0);
            }
        }
        catch (IOException ex) {
            this.gb.dataError();
        }
        if (this.gb.use_utf) {
            utf_to_string = this.gb.utf_to_string(utf_to_string);
        }
        return utf_to_string;
    }
    
    server_read(final groupboard gb, final DataInputStream is) {
        this.got_our_name = false;
        this.last_update_time = -1L;
        this.compress_dx = new int[4];
        this.compress_dy = new int[4];
        this.gb = gb;
        this.is = is;
        this.display_drawing_user = gb.get_boolean_arg("DISPLAY_DRAWING_USER", false);
    }
    
    public void run() {
        while (true) {
            int unsignedByte;
            try {
                if (this.gb.use_tunnel) {
                    this.gb.t.get(1);
                    this.is = new DataInputStream(this.gb.t.getInputStream());
                    this.gb.is = this.is;
                }
                unsignedByte = this.is.readUnsignedByte();
                Label_2635: {
                    if (unsignedByte >= 100 && unsignedByte <= 107) {
                        this.handle_bboard_packet(unsignedByte);
                    }
                    else if (unsignedByte >= 70 && unsignedByte <= 74) {
                        this.handle_chat_packet(unsignedByte);
                    }
                    else if (unsignedByte >= 128 && unsignedByte <= 173) {
                        this.handle_game_packet(unsignedByte);
                    }
                    else {
                        if (unsignedByte == 16) {
                            try {
                                if (this.gb.use_tunnel) {
                                    this.gb.t.get(3);
                                    this.is = new DataInputStream(this.gb.t.getInputStream());
                                    this.gb.is = this.is;
                                }
                                this.gb.num_clients = this.is.readShort();
                                final byte byte1 = this.is.readByte();
                                final String read_string = this.read_string();
                                if (null == this.gb.fr_gui && null == this.gb.br_gui) {
                                    this.gb.setup_gui(byte1);
                                }
                                if (null != this.gb.gui) {
                                    if (null != this.gb.gui.chat) {
                                        this.gb.gui.chat.users.clear();
                                        this.gb.gui.chat.users.addItem("ALL");
                                        this.gb.gui.chat.users.select(0);
                                    }
                                    this.target = this.gb.gui.draw_data;
                                    this.gb.gui.set_title(read_string);
                                    if (null != this.gb.gui.users_label) {
                                        this.gb.gui.users_label.setText("There are " + this.gb.num_clients + " " + "other users connected");
                                    }
                                }
                                break Label_2635;
                            }
                            catch (IOException ex2) {
                                this.gb.dataError();
                                return;
                            }
                        }
                        if (5 == unsignedByte) {
                            if (null != this.target) {
                                this.target.clear_array(false, false);
                            }
                        }
                        else if (31 == unsignedByte) {
                            if (null != this.target) {
                                this.target.clear_disabled = true;
                            }
                        }
                        else if (32 == unsignedByte) {
                            if (null != this.target) {
                                this.target.clear_disabled = false;
                            }
                        }
                        else if (43 == unsignedByte) {
                            if (null != this.target) {
                                this.target.load_disabled = true;
                            }
                        }
                        else if (44 == unsignedByte) {
                            if (null != this.target) {
                                this.target.load_disabled = false;
                            }
                        }
                        else if (38 == unsignedByte) {
                            if (null != this.target) {
                                this.target.draw_disabled = true;
                            }
                        }
                        else if (39 == unsignedByte) {
                            if (null != this.target) {
                                this.target.draw_disabled = false;
                            }
                        }
                        else if (34 == unsignedByte) {
                            new message_box(this.gb, "Uploading Picture", "Your picture is being uploaded and should appear in the list soon.");
                        }
                        else if (17 == unsignedByte) {
                            new message_box(this.gb, "Error", this.read_string());
                            this.gb.last_error_time = System.currentTimeMillis();
                        }
                        else if (6 == unsignedByte) {
                            final groupboard gb = this.gb;
                            ++gb.num_clients;
                            if (this.gb.use_tunnel) {
                                this.gb.t.get(4);
                                this.is = new DataInputStream(this.gb.t.getInputStream());
                                this.gb.is = this.is;
                            }
                            this.is.readInt();
                            if (null != this.gb.gui && null != this.gb.gui.users_label) {
                                this.gb.gui.users_label.setText("There are " + this.gb.num_clients + " " + "other users connected");
                            }
                            if (this.gb.get_boolean_arg("BEEP_ON_LOGIN", false)) {
                                this.gb.getToolkit().beep();
                            }
                        }
                        else if (7 == unsignedByte) {
                            if (this.gb.use_tunnel) {
                                this.gb.t.get(4);
                                this.is = new DataInputStream(this.gb.t.getInputStream());
                                this.gb.is = this.is;
                            }
                            final int int1 = this.is.readInt();
                            final groupboard gb2 = this.gb;
                            --gb2.num_clients;
                            if (null != this.gb.gui) {
                                if (null != this.gb.gui.chat) {
                                    this.gb.gui.chat.del_user(int1);
                                }
                                if (null != this.gb.gui && null != this.gb.gui.users_label) {
                                    this.gb.gui.users_label.setText("There are " + this.gb.num_clients + " " + "other users connected");
                                }
                            }
                        }
                        else if (18 == unsignedByte) {
                            if (null != this.gb.loading_win) {
                                this.gb.loading_win.dispose();
                                this.gb.loading_win = null;
                            }
                            new message_box(this.gb, "Too Many Users", "Too many users logged on. Please try again later.");
                            this.gb.log_message("Too Many Users");
                        }
                        else if (19 == unsignedByte) {
                            this.gb.close_connection();
                            if (null != this.gb.loading_win) {
                                this.gb.loading_win.dispose();
                                this.gb.loading_win = null;
                            }
                            final boolean asked_for_password = this.gb.asked_for_password;
                            new password_window(this.gb, this.gb.need_username);
                            if (asked_for_password) {
                                this.gb.log_message("Wrong password");
                                new message_box(this.gb, "Error", "Wrong password");
                            }
                            this.stop();
                        }
                        else if (20 == unsignedByte) {
                            final String read_string2 = this.read_string();
                            final String read_string3 = this.read_string();
                            if (this.gb.show_adverts && null != this.gb.gui.ad_area) {
                                this.gb.gui.ad_area.add_advert(new advert(this.gb.load_image(read_string2), read_string3));
                                this.gb.repaint();
                            }
                        }
                        else if (51 == unsignedByte) {
                            final String read_string4 = this.read_string();
                            final String host = this.gb.getDocumentBase().getHost();
                            if (!host.endsWith(read_string4)) {
                                if (read_string4.equals("groupboard.com")) {
                                    new message_box(this.gb, "Invalid Document Host", "This free board can only be hosted from groupboard.com -\nyou must either pay to upgrade to the full 15-user Groupboard,\nor use an <iframe> to host the free version on your own web page", 4, 60);
                                }
                                else {
                                    final message_box message_box = new message_box(this.gb, "Invalid Document Host", "This board is only authorized from " + read_string4 + " " + "but is being hosted from" + " " + host + "." + "To use the Groupboard, go to: http://www.groupboard.com");
                                }
                                try {
                                    Thread.sleep(4000L);
                                }
                                catch (InterruptedException ex3) {}
                                this.gb.really_stop();
                            }
                        }
                        else if (25 == unsignedByte) {
                            final String read_string5 = this.read_string();
                            final String read_string6 = this.read_string();
                            final String read_string7 = this.read_string();
                            final String read_string8 = this.read_string();
                            if (null != this.gb.load_pic_win) {
                                this.gb.load_pic_win.add_entry(read_string5, read_string6, read_string7, read_string8);
                            }
                        }
                        else if (22 == unsignedByte) {
                            final String read_string9 = this.read_string();
                            if (null != this.gb.banlist_win) {
                                this.gb.banlist_win.set_banlist(read_string9);
                            }
                        }
                        else if (27 == unsignedByte) {
                            final String read_string10 = this.read_string();
                            final String read_string11 = this.read_string();
                            if (null != this.gb.set_win) {
                                this.gb.set_win.add_entry(read_string10, read_string11);
                            }
                        }
                        else if (30 == unsignedByte) {
                            this.gb.is_admin = true;
                        }
                        else if (36 == unsignedByte) {
                            if (this.gb.use_tunnel) {
                                this.gb.t.get(21);
                                this.is = new DataInputStream(this.gb.t.getInputStream());
                                this.gb.is = this.is;
                            }
                            final int int2 = this.is.readInt();
                            final int int3 = this.is.readInt();
                            final int int4 = this.is.readInt();
                            final int int5 = this.is.readInt();
                            final int int6 = this.is.readInt();
                            final byte byte2 = this.is.readByte();
                            final String read_string12 = this.read_string();
                            final String read_string13 = this.read_string();
                            final String read_string14 = this.read_string();
                            final String read_string15 = this.read_string();
                            final String read_string16 = this.read_string();
                            if (null != this.gb.board_info_win) {
                                this.gb.board_info_win.set_info(int2, int3, int4, int5, int6, byte2, read_string12, read_string13, read_string14, read_string15, read_string16);
                            }
                        }
                        else if (unsignedByte >= 1 && unsignedByte <= 64) {
                            final int get_int_arg = this.gb.get_int_arg("draw_speed", 0);
                            final long currentTimeMillis = System.currentTimeMillis();
                            if (this.display_drawing_user && null != this.target && null != this.target.last_name) {
                                this.target.remove_display_user();
                            }
                            if (unsignedByte == 40) {
                                if (this.gb.use_tunnel) {
                                    this.gb.t.get(14);
                                    this.is = new DataInputStream(this.gb.t.getInputStream());
                                    this.gb.is = this.is;
                                }
                                int byte3 = this.is.readByte();
                                if (byte3 > 4) {
                                    byte3 = 4;
                                }
                                final int int7 = this.is.readInt();
                                this.compress_dx[0] = this.is.readByte();
                                this.compress_dy[0] = this.is.readByte();
                                this.compress_dx[1] = this.is.readByte();
                                this.compress_dy[1] = this.is.readByte();
                                this.compress_dx[2] = this.is.readByte();
                                this.compress_dy[2] = this.is.readByte();
                                this.compress_dx[3] = this.is.readByte();
                                this.compress_dy[3] = this.is.readByte();
                                this.is.readUnsignedByte();
                                this.read_string();
                                if (null != this.target) {
                                    final draw_item find_last_drawing_action = this.target.find_last_drawing_action(int7);
                                    if (find_last_drawing_action != null) {
                                        int n = find_last_drawing_action.x + find_last_drawing_action.width;
                                        int n2 = find_last_drawing_action.y + find_last_drawing_action.height;
                                        if (null != this.target) {
                                            final Graphics graphics = this.target.getGraphics();
                                            for (int i = 0; i < byte3; ++i) {
                                                this.target.add_to_array(find_last_drawing_action.object_type, n, n2, this.compress_dx[i], this.compress_dy[i], find_last_drawing_action.thickness, int7, "", find_last_drawing_action.col, false);
                                                n += this.compress_dx[i];
                                                n2 += this.compress_dy[i];
                                                this.target.update_panel(this.target.off_screen_gc, true);
                                                this.target.update_panel(graphics, true);
                                            }
                                            if (this.display_drawing_user && null == this.target.last_name) {
                                                this.target.display_user(graphics, int7, n, n2);
                                            }
                                            graphics.dispose();
                                        }
                                    }
                                }
                            }
                            else {
                                if (this.gb.use_tunnel) {
                                    this.gb.t.get(14);
                                    this.is = new DataInputStream(this.gb.t.getInputStream());
                                    this.gb.is = this.is;
                                }
                                final byte byte4 = this.is.readByte();
                                final int int8 = this.is.readInt();
                                final short short1 = this.is.readShort();
                                final short short2 = this.is.readShort();
                                final short short3 = this.is.readShort();
                                final short short4 = this.is.readShort();
                                final int unsignedByte2 = this.is.readUnsignedByte();
                                final String read_string17 = this.read_string();
                                if (null != this.target) {
                                    Color black;
                                    if (unsignedByte2 >= 0 && unsignedByte2 < this.target.col_array.length) {
                                        black = this.target.col_array[unsignedByte2];
                                    }
                                    else {
                                        black = Color.black;
                                    }
                                    this.target.add_to_array(unsignedByte, short1, short2, short3, short4, byte4, int8, read_string17, black, false);
                                    final Graphics graphics2 = this.target.getGraphics();
                                    this.target.update_panel(this.target.off_screen_gc, true);
                                    this.target.update_panel(graphics2, true);
                                    if (this.display_drawing_user && null == this.target.last_name) {
                                        this.target.display_user(graphics2, int8, short1, short2);
                                    }
                                    graphics2.dispose();
                                    if (10 == unsignedByte) {
                                        this.target.repaint();
                                    }
                                }
                            }
                            if (get_int_arg != 0) {
                                final long n3 = get_int_arg - (System.currentTimeMillis() - currentTimeMillis);
                                if (n3 > 0L) {
                                    try {
                                        Thread.sleep(n3);
                                    }
                                    catch (InterruptedException ex4) {}
                                }
                            }
                        }
                        else {
                            this.gb.log_message("Unknown packet " + unsignedByte + " received from server (last packet=" + this.last_packet + ")");
                        }
                    }
                }
            }
            catch (IOException ex) {
                if (ex instanceof SocketException && ex.getMessage().equals("Socket closed")) {
                    System.out.println("ignoring socket closed error...");
                    continue;
                }
                this.gb.dataError();
                try {
                    if (18 == this.is.readByte()) {
                        if (null != this.gb.loading_win) {
                            this.gb.loading_win.dispose();
                            this.gb.loading_win = null;
                        }
                        new message_box(this.gb, "Too Many Users", "Too many users logged on. Please try again later.");
                        this.gb.log_message("Too Many Users");
                    }
                }
                catch (IOException ex5) {}
                return;
            }
            this.last_packet = unsignedByte;
        }
    }
    
    public void handle_game_list(final int n, final int n2, final String s) {
        if (null != this.gb.games) {
            this.gb.games.add_game(n, n2, s);
        }
    }
    
    public void handle_game_list_remove(final int n) {
        if (null != this.gb.games) {
            this.gb.games.remove_game(n);
        }
    }
}
