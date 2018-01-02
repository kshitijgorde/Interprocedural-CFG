import java.awt.Event;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class draw_log_window extends Frame implements groupboard_consts
{
    draw_panel draw_data;
    TextArea console;
    Button close_but;
    Button clear_but;
    Button refresh_but;
    Button pause_but;
    boolean add_to_drawing_log;
    String last_msg;
    int last_msg_rpt;
    
    draw_log_window(final draw_panel draw_data) {
        this.add_to_drawing_log = true;
        this.draw_data = draw_data;
        this.last_msg = "";
        this.last_msg_rpt = 0;
        this.setTitle("Drawing Log");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.add(this.console = new TextArea());
        this.console.setEditable(false);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.console, gridBagConstraints);
        this.add(this.clear_but = new Button("Clear"));
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.clear_but, gridBagConstraints);
        this.add(this.pause_but = new Button("  Pause  "));
        gridBagConstraints.gridx = 1;
        layout.setConstraints(this.pause_but, gridBagConstraints);
        this.add(this.refresh_but = new Button("Refresh All"));
        gridBagConstraints.gridx = 2;
        layout.setConstraints(this.refresh_but, gridBagConstraints);
        this.add(this.close_but = new Button("Close"));
        gridBagConstraints.gridx = 3;
        layout.setConstraints(this.close_but, gridBagConstraints);
        this.refresh_all();
        this.pack();
        this.show();
    }
    
    void add_to_console(final String s) {
        if (this.add_to_drawing_log) {
            this.console.appendText(s);
        }
    }
    
    void refresh_all() {
        final StringBuffer sb = new StringBuffer("");
        this.console.setText("Working, please wait...");
        for (int i = 0; i < this.draw_data.items_used; ++i) {
            sb.append(this.get_log_info(this.draw_data.draw_data[i]));
        }
        this.console.setText(sb.toString());
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.clear_but) {
            this.console.setText("");
        }
        else if (event.target == this.refresh_but) {
            this.refresh_all();
        }
        else if (event.target == this.pause_but) {
            if (this.add_to_drawing_log) {
                this.pause_but.setLabel("Unpause");
                this.add_to_drawing_log = false;
            }
            else {
                this.pause_but.setLabel("  Pause  ");
                this.add_to_drawing_log = true;
            }
        }
        else if (event.target == this.close_but) {
            this.draw_data.log_win = null;
            this.dispose();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.draw_data.log_win = null;
            return true;
        }
        return super.handleEvent(event);
    }
    
    void add_item(final draw_item draw_item) {
        this.add_to_console(this.get_log_info(draw_item));
    }
    
    String get_log_info(final draw_item draw_item) {
        String s = "Unknown";
        String s2 = "Unknown";
        if (null != this.draw_data.gui.chat) {
            s = this.draw_data.gui.chat.user_hash.get(new Integer(draw_item.id));
            if (s == null) {
                s = "Unknown";
            }
            s2 = this.draw_data.gui.chat.ip_hash.get(new Integer(draw_item.id));
            if (s2 == null) {
                s2 = "Unknown";
            }
        }
        String string = this.draw_data.col_names.get(draw_item.col);
        if (null == string) {
            string = draw_item.col.toString();
        }
        String last_msg = s + " (" + s2 + "): colour " + string + ", size " + draw_item.thickness;
        switch (draw_item.object_type) {
            case 1:
            case 40: {
                last_msg += ", lines/freehand";
                break;
            }
            case 11: {
                last_msg += ", rectangle";
                break;
            }
            case 13: {
                last_msg += ", filled rectangle";
                break;
            }
            case 2: {
                last_msg += ", oval";
                break;
            }
            case 12: {
                last_msg += ", filled oval";
                break;
            }
            case 64: {
                last_msg += ", flood fill";
                break;
            }
            case 3: {
                last_msg = last_msg + ", text: " + draw_item.text;
                break;
            }
            case 10: {
                last_msg = last_msg + ", set background: " + draw_item.text;
                break;
            }
        }
        String s3 = "";
        if (last_msg.equals(this.last_msg)) {
            ++this.last_msg_rpt;
            if (this.last_msg_rpt >= 20) {
                s3 = "(last message repeated 20 times)\n";
                this.last_msg_rpt = 0;
            }
        }
        else {
            if (this.last_msg_rpt > 0) {
                s3 = "(last message repeated " + this.last_msg_rpt + " times)\n";
                this.last_msg_rpt = 0;
            }
            s3 = s3 + last_msg + "\n";
            this.last_msg = last_msg;
        }
        return s3;
    }
}
