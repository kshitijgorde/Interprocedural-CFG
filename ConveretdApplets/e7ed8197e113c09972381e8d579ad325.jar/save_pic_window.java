import java.awt.Event;
import java.awt.Point;
import java.awt.IllegalComponentStateException;
import java.awt.Component;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class save_pic_window extends Frame implements groupboard_consts
{
    private TextField title;
    private TextField name;
    private Button ok_but;
    private Button cancel_but;
    private groupboard gb;
    private draw_panel panel;
    
    save_pic_window(final groupboard gb, final draw_panel panel) {
        this.panel = panel;
        this.gb = gb;
        String s = gb.get_arg("SAVE_FILENAME");
        if (null != s) {
            try {
                synchronized (this.gb.os) {
                    if (this.gb.use_utf) {
                        s = this.gb.string_to_utf(s);
                    }
                    this.gb.os.writeByte(8);
                    this.gb.os.writeShort(s.length());
                    this.gb.os.writeShort(s.length());
                    if (s.length() > 0) {
                        this.gb.os.writeBytes(s);
                    }
                    if (s.length() > 0) {
                        this.gb.os.writeBytes(s);
                    }
                }
            }
            catch (IOException ex) {
                this.gb.dataError();
                return;
            }
            if (this.gb.get_boolean_arg("CLEAR_ON_SAVE", true)) {
                panel.clear_array(true, false);
            }
            this.dispose();
            panel.pic_win = null;
        }
        else {
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            final GridBagConstraints gridBagConstraints3;
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints3 = new GridBagConstraints();
            final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
            final Insets insets = new Insets(10, 10, 10, 10);
            gridBagConstraints4.insets = insets;
            gridBagConstraints3.insets = insets;
            gridBagConstraints2.gridwidth = 0;
            gridBagConstraints2.anchor = 17;
            this.setLayout(layout);
            this.setTitle("Save Picture");
            final Panel panel2 = new Panel();
            panel2.setLayout(new FlowLayout(1));
            final Label label;
            this.add(label = new Label("Title of Picture"));
            layout.setConstraints(label, gridBagConstraints);
            this.add(this.title = new TextField(20));
            layout.setConstraints(this.title, gridBagConstraints2);
            final Label label2;
            this.add(label2 = new Label("Your Name and/or Email Address"));
            layout.setConstraints(label2, gridBagConstraints);
            this.add(this.name = new TextField(20));
            if (null != gb.gui.chat && null != gb.gui.chat.prev_name) {
                this.name.setText(gb.gui.chat.prev_name);
            }
            layout.setConstraints(this.name, gridBagConstraints2);
            panel2.add(this.ok_but = new Button("OK"));
            panel2.add(this.cancel_but = new Button("Cancel"));
            this.add(panel2);
            gridBagConstraints.anchor = 10;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(panel2, gridBagConstraints);
            try {
                final Point locationOnScreen = this.gb.gui.draw_data.getLocationOnScreen();
                if (this.gb.new_jdk) {
                    this.setLocation(Math.max(locationOnScreen.x + 10, 10), Math.max(locationOnScreen.y + 10, 10));
                }
            }
            catch (IllegalComponentStateException ex2) {}
            this.pack();
            this.resize(420, 180);
            this.show();
            this.requestFocus();
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            this.panel.pic_win = null;
        }
        else if (event.target == this.ok_but && !this.panel.draw_disabled) {
            String s = this.title.getText();
            if (s.length() > 80) {
                s = s.substring(0, 79);
            }
            String s2 = this.name.getText();
            if (s2.length() > 60) {
                s2 = s2.substring(0, 59);
            }
            if (s.length() > 0 && s2.length() > 0) {
                try {
                    synchronized (this.gb.os) {
                        if (this.gb.use_utf) {
                            s = this.gb.string_to_utf(s);
                            s2 = this.gb.string_to_utf(s2);
                        }
                        this.gb.os.writeByte(8);
                        this.gb.os.writeShort(s.length());
                        this.gb.os.writeShort(s2.length());
                        if (s.length() > 0) {
                            this.gb.os.writeBytes(s);
                        }
                        if (s2.length() > 0) {
                            this.gb.os.writeBytes(s2);
                        }
                    }
                }
                catch (IOException ex) {
                    this.gb.dataError();
                    return true;
                }
                if (this.gb.get_boolean_arg("CLEAR_ON_SAVE", true)) {
                    this.panel.clear_array(true, false);
                }
                this.dispose();
                this.panel.pic_win = null;
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.panel.pic_win = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
