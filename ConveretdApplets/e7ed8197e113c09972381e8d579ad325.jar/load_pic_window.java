import java.io.IOException;
import java.awt.Event;
import java.awt.Point;
import java.awt.IllegalComponentStateException;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Hashtable;
import java.awt.List;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class load_pic_window extends Frame implements groupboard_consts
{
    Button ok_but;
    Button cancel_but;
    Button delete_but;
    groupboard parent;
    draw_panel panel;
    List file_list;
    Hashtable file_hash;
    
    load_pic_window(final groupboard parent, final draw_panel panel, final boolean b) {
        this.panel = panel;
        this.parent = parent;
        this.setTitle("Load Picture");
        this.file_hash = new Hashtable();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.add(this.file_list = new List(10, false));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        if (b) {
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.weightx = 3.0;
        }
        else {
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.weightx = 2.0;
        }
        layout.setConstraints(this.file_list, gridBagConstraints);
        this.add(this.ok_but = new Button("Load"));
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.ok_but, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        if (b) {
            this.add(this.delete_but = new Button("Delete"));
            layout.setConstraints(this.delete_but, gridBagConstraints);
            gridBagConstraints.gridx = 2;
        }
        this.add(this.cancel_but = new Button("Close"));
        layout.setConstraints(this.cancel_but, gridBagConstraints);
        try {
            final Point locationOnScreen = parent.gui.draw_data.getLocationOnScreen();
            if (parent.new_jdk) {
                this.setLocation(Math.max(locationOnScreen.x + 10, 10), Math.max(locationOnScreen.y + 10, 10));
            }
        }
        catch (IllegalComponentStateException ex) {}
        this.pack();
        this.show();
        this.requestFocus();
    }
    
    public void add_entry(final String s, final String s2, final String s3, final String s4) {
        String s5;
        if (this.parent.is_admin) {
            s5 = s2 + " (" + s3 + " - " + s4 + ")";
        }
        else {
            s5 = s2 + " (" + s3 + ")";
        }
        final String s6 = s5;
        for (int n = 2; null != this.file_hash.get(s5); s5 = s6 + " " + Integer.toString(n), ++n) {}
        this.file_list.addItem(s5);
        this.file_hash.put(s5, s);
        this.pack();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            this.parent.load_pic_win = null;
        }
        else if (event.target == this.ok_but && !this.panel.draw_disabled) {
            final int selectedIndex = this.file_list.getSelectedIndex();
            if (-1 != selectedIndex) {
                String string_to_utf = this.file_hash.get(this.file_list.getItem(selectedIndex));
                if (null != string_to_utf) {
                    this.panel.last_bg = null;
                    this.panel.last_bg_image = null;
                    this.panel.clear_array(true, false);
                    try {
                        synchronized (this.parent.os) {
                            this.parent.os.writeByte(9);
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
                        return true;
                    }
                    this.dispose();
                    this.parent.load_pic_win = null;
                    if (this.parent.use_tunnel) {
                        this.parent.t.force_post();
                    }
                }
            }
        }
        else if (event.target == this.delete_but) {
            final int selectedIndex2 = this.file_list.getSelectedIndex();
            if (-1 != selectedIndex2) {
                String string_to_utf2 = this.file_hash.get(this.file_list.getItem(selectedIndex2));
                if (null != string_to_utf2) {
                    try {
                        synchronized (this.parent.os) {
                            if (this.parent.use_utf) {
                                string_to_utf2 = this.parent.string_to_utf(string_to_utf2);
                            }
                            this.parent.os.writeByte(28);
                            this.parent.os.writeShort(string_to_utf2.length());
                            if (string_to_utf2.length() > 0) {
                                this.parent.os.writeBytes(string_to_utf2);
                            }
                        }
                    }
                    catch (IOException ex2) {
                        this.parent.dataError();
                        return true;
                    }
                    if (this.parent.use_tunnel) {
                        this.parent.t.force_post();
                    }
                    this.file_list.delItem(selectedIndex2);
                }
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.parent.load_pic_win = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
