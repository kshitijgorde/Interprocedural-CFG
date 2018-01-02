import java.net.URL;
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

class set_background_window extends Frame implements groupboard_consts
{
    Button ok_but;
    Button cancel_but;
    Button delete_but;
    Button upload_but;
    Button refresh_but;
    groupboard parent;
    draw_panel panel;
    List file_list;
    Hashtable file_hash;
    
    set_background_window(final groupboard parent, final draw_panel panel, final boolean b, final boolean b2) {
        this.file_hash = new Hashtable();
        this.panel = panel;
        this.parent = parent;
        this.setTitle("Select Background");
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
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        if (b) {
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            gridBagConstraints2.gridwidth += 2;
            final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
            gridBagConstraints3.weightx += 2.0;
        }
        if (b2) {
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            ++gridBagConstraints4.gridwidth;
            final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
            ++gridBagConstraints5.weightx;
        }
        layout.setConstraints(this.file_list, gridBagConstraints);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridy;
        this.add(this.ok_but = new Button("Set"));
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.ok_but, gridBagConstraints);
        if (b) {
            this.add(this.delete_but = new Button("Delete"));
            final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
            ++gridBagConstraints7.gridx;
            layout.setConstraints(this.delete_but, gridBagConstraints);
        }
        if (b2) {
            this.add(this.upload_but = new Button("Upload"));
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
            ++gridBagConstraints8.gridx;
            layout.setConstraints(this.upload_but, gridBagConstraints);
        }
        this.add(this.refresh_but = new Button("Refresh"));
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridx;
        layout.setConstraints(this.refresh_but, gridBagConstraints);
        this.add(this.cancel_but = new Button("Close"));
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
        ++gridBagConstraints10.gridx;
        layout.setConstraints(this.cancel_but, gridBagConstraints);
        this.file_list.addItem("BLANK");
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
    
    public void add_entry(final String s, final String s2) {
        this.file_hash.put(s, s2);
        boolean b = false;
        int i;
        for (i = 1; i < this.file_list.countItems(); ++i) {
            if (s.toLowerCase().compareTo(this.file_list.getItem(i).toLowerCase()) < 0) {
                this.file_list.add(s, i);
                b = true;
                break;
            }
        }
        if (!b) {
            this.file_list.addItem(s, -1);
        }
        final String replace_all_text = this.parent.replace_all_text(s2, " ", "%20");
        if (this.panel.last_bg != null && this.panel.last_bg.equals(replace_all_text)) {
            this.file_list.select(i);
        }
        this.pack();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            this.parent.set_win = null;
        }
        else if (event.target == this.refresh_but) {
            this.file_list.clear();
            this.file_list.addItem("BLANK");
            try {
                this.parent.os.writeByte(26);
            }
            catch (IOException ex) {
                this.parent.dataError();
            }
            if (this.parent.use_tunnel) {
                this.parent.t.force_post();
            }
        }
        else if (event.target == this.ok_but && !this.panel.draw_disabled) {
            final int selectedIndex = this.file_list.getSelectedIndex();
            if (-1 != selectedIndex) {
                if (this.file_list.getItem(selectedIndex).equals("BLANK")) {
                    this.panel.last_bg = null;
                    this.panel.last_bg_image = null;
                    this.panel.clear_array(true, true);
                    this.dispose();
                    this.parent.set_win = null;
                }
                else {
                    this.panel.set_background((String)this.file_hash.get(this.file_list.getItem(selectedIndex)));
                    this.dispose();
                    this.parent.set_win = null;
                }
            }
        }
        else if (event.target == this.delete_but) {
            final int selectedIndex2 = this.file_list.getSelectedIndex();
            if (-1 != selectedIndex2) {
                String string_to_utf = this.file_hash.get(this.file_list.getItem(selectedIndex2));
                if (null != string_to_utf) {
                    try {
                        synchronized (this.parent.os) {
                            if (this.parent.use_utf) {
                                string_to_utf = this.parent.string_to_utf(string_to_utf);
                            }
                            this.parent.os.writeByte(29);
                            this.parent.os.writeShort(string_to_utf.length());
                            if (string_to_utf.length() > 0) {
                                this.parent.os.writeBytes(string_to_utf);
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
        else if (event.target == this.upload_but) {
            final int get_int_arg = this.parent.get_int_arg("BOARD_ID", 1);
            final boolean get_boolean_arg = this.parent.get_boolean_arg("ALLOW_NONADMIN_UPLOAD", false);
            String s = "http://www.groupboard.com/mp/fileupload.cgi?board_id=" + get_int_arg + "&is_popup=true";
            if (get_boolean_arg) {
                s += "&hide_password=true";
            }
            try {
                this.parent.getAppletContext().showDocument(new URL(s), "_blank");
            }
            catch (Exception ex3) {
                System.out.println("Error showing url " + s);
            }
        }
        return true;
    }
    
    private void upload_pic(final String s) {
        try {
            synchronized (this.parent.os) {
                this.parent.os.writeByte(33);
                this.parent.os.writeShort(s.length());
                if (s.length() > 0) {
                    this.parent.os.writeBytes(s);
                }
            }
        }
        catch (IOException ex) {
            this.parent.dataError();
        }
        if (this.parent.use_tunnel) {
            this.parent.t.force_post();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.parent.set_win = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
