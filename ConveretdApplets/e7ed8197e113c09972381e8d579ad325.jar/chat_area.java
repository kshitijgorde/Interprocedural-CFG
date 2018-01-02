import java.awt.Event;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.Frame;
import java.awt.FileDialog;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.List;
import java.awt.TextField;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class chat_area extends Panel implements groupboard_consts
{
    String prev_name;
    private boolean show_ip_addresses;
    private TextArea console;
    Component input;
    private TextField tf_input;
    private RTLTextField rtl_input;
    TextField name;
    List users;
    private Button ban_button;
    private Button ignore_button;
    private Button hand_button;
    private Hashtable ignore_hash;
    Hashtable user_hash;
    Hashtable ip_hash;
    private Hashtable reverse_user_hash;
    private groupboard gb;
    
    chat_area(final groupboard gb, final boolean b, final boolean b2, final boolean show_ip_addresses) {
        this.show_ip_addresses = show_ip_addresses;
        gb.bb = null;
        this.prev_name = "";
        this.user_hash = new Hashtable();
        this.ip_hash = new Hashtable();
        this.ignore_hash = new Hashtable();
        this.reverse_user_hash = new Hashtable();
        this.gb = gb;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        this.setLayout(layout);
        final int get_int_arg = gb.get_int_arg("CHAT_ROWS", 9);
        final int get_int_arg2 = gb.get_int_arg("CHAT_COLS", 45);
        if (gb.new_jdk) {
            this.console = new TextArea("", get_int_arg, get_int_arg2, 1);
        }
        else {
            this.console = new TextArea(get_int_arg, get_int_arg2);
        }
        this.console.setEditable(false);
        this.add(this.console);
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(10, 1, 1, 5);
        layout.setConstraints(this.console, gridBagConstraints);
        final Label label;
        this.add(label = new Label("Name"));
        this.add(this.name = new TextField(10));
        if (!gb.get_boolean_arg("ALLOW_CHANGE_NAME", true)) {
            this.name.setEditable(false);
        }
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(label, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.name, gridBagConstraints);
        final Label label2;
        this.add(label2 = new Label("Chat To"));
        (this.users = new List()).addItem("ALL");
        this.users.select(0);
        this.add(this.users);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);
        layout.setConstraints(label2, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.users, gridBagConstraints);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        this.add(this.ignore_button = new Button("  Ignore  "));
        if (b || gb.get_boolean_arg("PUT_UP_HAND", false)) {
            gridBagConstraints.gridwidth = 1;
        }
        layout.setConstraints(this.ignore_button, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        if (b) {
            this.add(this.ban_button = new Button("Ban"));
            layout.setConstraints(this.ban_button, gridBagConstraints);
        }
        else if (gb.get_boolean_arg("PUT_UP_HAND", false)) {
            this.add(this.hand_button = new Button("Raise Hand"));
            layout.setConstraints(this.hand_button, gridBagConstraints);
        }
        gridBagConstraints.fill = 2;
        if (gb.rtl) {
            this.rtl_input = new RTLTextField(this, gb.board_width, 25);
            this.input = this.rtl_input;
            this.rtl_input.setText("Click here to chat");
        }
        else {
            this.tf_input = new TextField(50);
            this.input = this.tf_input;
            this.tf_input.setText("Click here to chat");
        }
        this.add(this.input);
        layout.setConstraints(this.input, gridBagConstraints);
        final String get_arg = gb.get_arg("SET_NAME");
        if (null != get_arg) {
            this.send_name_change(get_arg);
            this.name.setText(get_arg);
        }
    }
    
    void clear_text() {
        this.console.setText("");
    }
    
    void save_text() {
        final FileDialog fileDialog = new FileDialog(this.gb.fr_gui, "Save History", 1);
        fileDialog.show();
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null && !file.equals("")) {
            try {
                new DataOutputStream(new FileOutputStream(new File(directory, file))).writeBytes(this.console.getText());
            }
            catch (IOException ex) {
                System.err.println("Error saving file: " + ex.getMessage());
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        this.gb.last_event_time = System.currentTimeMillis();
        switch (event.id) {
            case 1001: {
                if (event.target == this.input) {
                    if (this.gb.rtl) {
                        this.send_chat_string(this.rtl_input.getText());
                        this.rtl_input.setText("");
                    }
                    else {
                        this.send_chat_string(this.tf_input.getText());
                        this.tf_input.setText("");
                    }
                    return true;
                }
                if (event.target == this.name) {
                    this.send_name_change(this.name.getText());
                    return true;
                }
                if (event.target == this.hand_button) {
                    this.send_chat_string("\u0007 *** PUTS UP HAND ***");
                }
                else {
                    if (event.target == this.ban_button) {
                        int selectedIndex = this.users.getSelectedIndex();
                        if (selectedIndex == -1) {
                            selectedIndex = 0;
                        }
                        if (selectedIndex != 0) {
                            final String item = this.users.getItem(selectedIndex);
                            final Integer n = this.reverse_user_hash.get(item);
                            if (null != n) {
                                new ban_reason_window(this.gb, (int)n, item, (String)this.ip_hash.get(n));
                            }
                        }
                        return true;
                    }
                    if (event.target == this.ignore_button) {
                        this.ignore();
                        return true;
                    }
                }
                return false;
            }
            case 1005: {
                if (event.target == this.name) {
                    this.send_name_change(this.name.getText());
                    return true;
                }
                return false;
            }
            case 1004: {
                if (this.gb.rtl && event.target == this.rtl_input) {
                    if (this.rtl_input.getText().equals("Click here to chat")) {
                        this.rtl_input.setText("");
                    }
                }
                else if (!this.gb.rtl && event.target == this.tf_input && this.tf_input.getText().equals("Click here to chat")) {
                    this.tf_input.setText("");
                }
                return false;
            }
            case 701: {
                if (event.target == this.users) {
                    int selectedIndex2 = this.users.getSelectedIndex();
                    if (selectedIndex2 == -1) {
                        selectedIndex2 = 0;
                    }
                    if (selectedIndex2 != 0) {
                        final Integer n2 = this.reverse_user_hash.get(this.users.getItem(selectedIndex2));
                        if (null != n2) {
                            if (null == this.ignore_hash.get(n2)) {
                                this.ignore_button.setLabel("Ignore");
                            }
                            else {
                                this.ignore_button.setLabel("Unignore");
                            }
                        }
                    }
                    if (this.gb.get_boolean_arg("DISABLE_PRIVATE_CHAT", false)) {
                        this.users.select(0);
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
    
    void output_chat_string(final int n, String s, final boolean b, final boolean b2, final boolean b3) {
        if (!b3) {
            if (null != this.ignore_hash.get(new Integer(n))) {
                return;
            }
            if (this.gb.get_boolean_arg("PUT_UP_HAND", false) && s.charAt(0) == '\u0007') {
                s = s.substring(1);
                this.gb.getToolkit().beep();
            }
            String string = this.user_hash.get(new Integer(n));
            final String s2 = this.ip_hash.get(new Integer(n));
            if (null == string) {
                string = "Anon";
            }
            if (this.show_ip_addresses) {
                string = string + " (" + s2 + ")";
            }
            if (b) {
                s = "*" + string + "* " + s;
            }
            else if (b2) {
                s = ">" + string + "< " + s;
            }
            else {
                s = "<" + string + "> " + s;
            }
        }
        else if (s.charAt(0) == '\u0007') {
            s = s.substring(1);
            this.gb.getToolkit().beep();
        }
        this.console.appendText(s + "\n");
        if (b && this.gb.beep_on_whisper && System.currentTimeMillis() - this.gb.last_event_time > this.gb.get_int_arg("WHISPER_BEEP_IDLE_TIME", 30000)) {
            this.gb.last_event_time = System.currentTimeMillis();
            if (this.gb.new_jdk) {
                this.gb.getToolkit().beep();
            }
        }
    }
    
    synchronized void del_user(final int n) {
        final String selectedItem = this.users.getSelectedItem();
        final String s = this.user_hash.get(new Integer(n));
        if (null != s) {
            for (int i = 0; i < this.users.countItems(); ++i) {
                if (s.equals(this.users.getItem(i))) {
                    this.users.delItem(i);
                    break;
                }
            }
            this.reverse_user_hash.remove(s);
            this.user_hash.remove(new Integer(n));
        }
        if (this.gb.is_mac_osx && null != selectedItem && -1 == this.users.getSelectedIndex()) {
            for (int j = 0; j < this.users.countItems(); ++j) {
                if (selectedItem.equals(this.users.getItem(j))) {
                    this.users.select(j);
                    break;
                }
            }
        }
    }
    
    void name_changed(final int n, String replace_text, final String s) {
        final String selectedItem = this.users.getSelectedItem();
        replace_text = this.gb.replace_text(replace_text, "Anon", "Anon");
        this.del_user(n);
        this.user_hash.put(new Integer(n), replace_text);
        this.ip_hash.put(new Integer(n), s);
        this.reverse_user_hash.put(replace_text, new Integer(n));
        this.users.addItem(replace_text);
        if (this.gb.is_mac_osx && null != selectedItem && -1 == this.users.getSelectedIndex()) {
            for (int i = 0; i < this.users.countItems(); ++i) {
                if (selectedItem.equals(this.users.getItem(i))) {
                    this.users.select(i);
                    break;
                }
            }
        }
    }
    
    void ignore() {
        int selectedIndex = this.users.getSelectedIndex();
        if (selectedIndex == -1) {
            selectedIndex = 0;
        }
        if (selectedIndex != 0) {
            final Integer n = this.reverse_user_hash.get(this.users.getItem(selectedIndex));
            if (null != n) {
                final String s = this.user_hash.get(n);
                if (null == this.ignore_hash.get(n)) {
                    this.ignore_hash.put(n, new Integer(0));
                    this.ignore_button.setLabel("Unignore");
                    this.console.appendText("Ignoring " + s + "\n");
                }
                else {
                    this.ignore_hash.remove(n);
                    this.ignore_button.setLabel("Ignore");
                    this.console.appendText("Unignoring " + s + "\n");
                }
            }
        }
    }
    
    void send_chat_string(String s) {
        if (s.length() > 400) {
            s = s.substring(0, 399);
        }
        int selectedIndex = this.users.getSelectedIndex();
        if (selectedIndex == -1) {
            selectedIndex = 0;
        }
        try {
            synchronized (this.gb.os) {
                if (selectedIndex == 0) {
                    this.gb.os.writeByte(71);
                }
                else {
                    Integer n = this.reverse_user_hash.get(this.users.getItem(selectedIndex));
                    if (this.gb.get_boolean_arg("DISABLE_PRIVATE_CHAT", false)) {
                        n = null;
                    }
                    if (null == n) {
                        this.gb.os.writeByte(71);
                    }
                    else {
                        this.gb.os.writeByte(72);
                        this.gb.os.writeInt(n);
                        this.output_chat_string(n, s, false, true, false);
                    }
                }
                if (this.gb.use_utf) {
                    s = this.gb.string_to_utf(s);
                }
                this.gb.os.writeShort(s.length());
                if (s.length() > 0) {
                    this.gb.os.writeBytes(s);
                }
            }
            if (this.gb.use_tunnel) {
                this.gb.t.force_post();
            }
        }
        catch (IOException ex) {
            this.gb.dataError();
        }
    }
    
    void send_name_change(String prev_name) {
        if (this.prev_name.equals(prev_name)) {
            return;
        }
        this.prev_name = prev_name;
        if (prev_name.length() > 80) {
            prev_name = prev_name.substring(0, 79);
        }
        try {
            synchronized (this.gb.os) {
                if (this.gb.use_utf) {
                    prev_name = this.gb.string_to_utf(prev_name);
                }
                this.gb.os.writeByte(70);
                this.gb.os.writeShort(prev_name.length());
                if (prev_name.length() > 0) {
                    this.gb.os.writeBytes(prev_name);
                }
                if (this.gb.use_tunnel) {
                    this.gb.t.force_post();
                }
            }
        }
        catch (IOException ex) {
            this.gb.dataError();
        }
    }
}
