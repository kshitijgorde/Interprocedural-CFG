import java.io.IOException;
import java.awt.Event;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class banlist_window extends Frame implements groupboard_consts
{
    int curpos;
    static final char BANLIST_SEPARATOR = ':';
    boolean changes_made;
    Button ok_but;
    Button cancel_but;
    Button del_but;
    Button add_but;
    Button clear_but;
    Button find_but;
    TextField add_text;
    groupboard parent;
    draw_panel panel;
    List ban_list;
    
    banlist_window(final groupboard parent, final draw_panel panel) {
        this.curpos = 0;
        this.panel = panel;
        this.parent = parent;
        this.changes_made = false;
        this.setTitle("Ban List");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.add(this.ban_list = new List(10, false));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.ban_list, gridBagConstraints);
        this.add(this.add_text = new TextField(10));
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.add_text, gridBagConstraints);
        this.add(this.add_but = new Button("Add"));
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 0;
        layout.setConstraints(this.add_but, gridBagConstraints);
        this.add(this.find_but = new Button("Find"));
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 1;
        layout.setConstraints(this.find_but, gridBagConstraints);
        this.add(this.ok_but = new Button("OK"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.ok_but, gridBagConstraints);
        this.ok_but.disable();
        this.add(this.cancel_but = new Button("Cancel"));
        gridBagConstraints.gridx = 1;
        layout.setConstraints(this.cancel_but, gridBagConstraints);
        this.add(this.del_but = new Button("Delete"));
        gridBagConstraints.gridx = 2;
        layout.setConstraints(this.del_but, gridBagConstraints);
        this.add(this.clear_but = new Button("Clear"));
        gridBagConstraints.gridx = 3;
        layout.setConstraints(this.clear_but, gridBagConstraints);
        this.pack();
        this.show();
        this.requestFocus();
    }
    
    public void set_banlist(final String s) {
        if (this.ban_list.countItems() != 0) {
            this.ban_list.clear();
        }
        final int length = s.length();
        int n = 0;
        while (-1 != n && n < length) {
            final int index = s.indexOf(58, n);
            if (-1 != index) {
                this.ban_list.addItem(s.substring(n, index));
                this.pack();
                n = index + 1;
            }
            else {
                this.ban_list.addItem(s.substring(n));
                n = -1;
            }
        }
        this.ok_but.enable();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            this.parent.banlist_win = null;
        }
        if (event.target == this.find_but) {
            final String text = this.add_text.getText();
            if (!text.equals("")) {
                int i;
                for (i = 0; i < this.ban_list.countItems(); ++i) {
                    final String item = this.ban_list.getItem((i + this.curpos) % this.ban_list.countItems());
                    if (item != null && item.startsWith(text)) {
                        this.ban_list.select((i + this.curpos) % this.ban_list.countItems());
                        this.curpos = (i + this.curpos + 1) % this.ban_list.countItems();
                        break;
                    }
                }
                if (i == this.ban_list.countItems()) {
                    new message_box(this.parent, "Information", "IP address not found");
                }
            }
        }
        else if (event.target == this.ok_but) {
            if (this.changes_made) {
                String s = new String();
                for (int j = 0; j < this.ban_list.countItems(); ++j) {
                    if (j != 0) {
                        s += ':';
                    }
                    s += this.ban_list.getItem(j);
                }
                try {
                    synchronized (this.parent.os) {
                        this.parent.os.writeByte(23);
                        this.parent.os.writeShort(s.length());
                        if (s.length() > 0) {
                            this.parent.os.writeBytes(s);
                        }
                        if (this.parent.use_tunnel) {
                            this.parent.t.force_post();
                        }
                    }
                }
                catch (IOException ex) {
                    this.parent.dataError();
                    return true;
                }
            }
            this.dispose();
            this.parent.banlist_win = null;
        }
        else if (event.target == this.del_but) {
            final int selectedIndex = this.ban_list.getSelectedIndex();
            if (-1 != selectedIndex) {
                this.ban_list.delItem(selectedIndex);
                this.changes_made = true;
            }
        }
        else if (event.target == this.clear_but) {
            this.ban_list.clear();
            this.changes_made = true;
        }
        else if (event.target == this.add_but) {
            if (this.ban_list.countItems() == 0) {
                this.ok_but.enable();
            }
            final String text2 = this.add_text.getText();
            if (!text2.equals("")) {
                this.ban_list.addItem(text2);
                this.pack();
                this.changes_made = true;
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.parent.banlist_win = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
