import java.awt.Event;
import java.io.IOException;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.List;
import java.util.Hashtable;
import java.awt.TextArea;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class games_gui extends Panel implements groupboard_consts
{
    TextArea console;
    Hashtable id_to_name;
    Hashtable name_to_id;
    private Object mutex;
    private frame_games frame;
    groupboard parent;
    List games;
    List games_started;
    Button create;
    Button join;
    
    games_gui(final boolean b, final groupboard parent, final frame_games frame) {
        this.mutex = new Object();
        this.frame = frame;
        this.parent = parent;
        this.name_to_id = new Hashtable();
        this.id_to_name = new Hashtable();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.setLayout(layout);
        if (parent.new_jdk) {
            this.console = new TextArea("", 20, 45, 1);
        }
        else {
            this.console = new TextArea(20, 45);
        }
        this.add(this.console);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.console, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        final Label label = new Label("Games you can create");
        this.add(label);
        layout.setConstraints(label, gridBagConstraints);
        this.add(this.games = new List(5, false));
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.games, gridBagConstraints);
        gridBagConstraints.fill = 0;
        this.add(this.create = new Button("Create"));
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.create, gridBagConstraints);
        final Label label2 = new Label("Created games which you can join");
        this.add(label2);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        layout.setConstraints(label2, gridBagConstraints);
        this.add(this.games_started = new List(5, false));
        gridBagConstraints.gridy = 4;
        layout.setConstraints(this.games_started, gridBagConstraints);
        gridBagConstraints.fill = 0;
        this.add(this.join = new Button("Join"));
        gridBagConstraints.gridy = 5;
        layout.setConstraints(this.join, gridBagConstraints);
    }
    
    public final void list_games() {
        synchronized (this.mutex) {
            this.games.clear();
            this.games_started.clear();
            this.id_to_name.clear();
            this.name_to_id.clear();
            try {
                this.parent.os.writeByte(128);
            }
            catch (IOException ex) {
                this.parent.dataError();
                return;
            }
            if (this.parent.use_tunnel) {
                this.parent.t.force_post();
            }
        }
    }
    
    void remove_game(final int n) {
        synchronized (this.mutex) {
            final String s = this.id_to_name.get(new Integer(n));
            if (s != null) {
                for (int i = 0; i < this.games_started.countItems(); ++i) {
                    if (s.equals(this.games_started.getItem(i))) {
                        this.games_started.delItem(i);
                        break;
                    }
                }
                this.id_to_name.remove(new Integer(n));
                this.name_to_id.remove(s);
            }
        }
    }
    
    void add_game(final int n, final int n2, String o) {
        o = ((String)o).replace('_', ' ');
        int index = 0;
        final StringBuffer sb = new StringBuffer((String)o);
        while (index < ((String)o).length() && index != -1) {
            sb.setCharAt(index, Character.toUpperCase(((String)o).charAt(index)));
            if (++index < ((String)o).length()) {
                index = ((String)o).indexOf(32, index);
                if (index == -1) {
                    continue;
                }
                ++index;
            }
        }
        o = new String(sb);
        synchronized (this.mutex) {
            if (n == 0) {
                this.games.addItem((String)o);
            }
            else {
                if (null != this.parent.gui.chat) {
                    final String s = this.parent.gui.chat.user_hash.get(new Integer(n2));
                    if (null != s) {
                        o = (String)o + " (" + s + ")";
                    }
                }
                this.name_to_id.put(o, new Integer(n));
                this.id_to_name.put(new Integer(n), o);
                this.games_started.addItem((String)o);
            }
        }
    }
    
    public void add_to_console(final String s) {
        this.console.appendText(s);
    }
    
    public void handle_game_info(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final String s, final String s2) {
        this.console.appendText("Game name: " + s + "\n");
        this.console.appendText("Min players: " + n + "\n");
        this.console.appendText("Max players: " + n2 + "\n");
        final boolean b = (n4 & 0x1) == 0x1;
        if (n6 != 0) {
            if (b) {
                this.console.appendText("Password required\n");
            }
            else {
                this.console.appendText("No password required\n");
            }
            this.console.appendText("Current players: " + n3 + "\n");
            if (null != this.parent.gui.chat) {
                this.console.appendText("Created by: " + this.parent.gui.chat.user_hash.get(new Integer(n6)) + "\n");
            }
        }
        this.console.appendText("Description: " + s2 + "\n\n");
    }
    
    public boolean handleEvent(final Event event) {
        synchronized (this.mutex) {
            switch (event.id) {
                case 1001: {
                    if (event.target == this.create) {
                        if (-1 != this.games.getSelectedIndex() && this.parent.base == null) {
                            new game_start_window(this.parent, this.games.getSelectedItem());
                        }
                        return true;
                    }
                    if (event.target == this.join) {
                        final int selectedIndex = this.games_started.getSelectedIndex();
                        if (-1 != selectedIndex && this.parent.base == null) {
                            this.parent.join_game((int)this.name_to_id.get(this.games_started.getItem(selectedIndex)), "");
                        }
                        return true;
                    }
                    return false;
                }
                case 701: {
                    if (event.target == this.games || event.target == this.games_started) {
                        if (event.target == this.games) {
                            final int selectedIndex2 = this.games_started.getSelectedIndex();
                            if (selectedIndex2 != -1) {
                                this.games_started.deselect(selectedIndex2);
                            }
                            this.parent.get_game_info(0, this.games.getItem(this.games.getSelectedIndex()));
                        }
                        else {
                            final int selectedIndex3 = this.games.getSelectedIndex();
                            if (selectedIndex3 != -1) {
                                this.games.deselect(selectedIndex3);
                            }
                            this.parent.get_game_info(this.name_to_id.get(this.games_started.getItem(this.games_started.getSelectedIndex())), "");
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
}
