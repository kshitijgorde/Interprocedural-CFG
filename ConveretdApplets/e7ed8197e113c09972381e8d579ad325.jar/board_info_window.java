import java.io.IOException;
import java.awt.Event;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class board_info_window extends Frame implements groupboard_consts
{
    int flags;
    TextField visits;
    TextField visits_this_week;
    TextField visits_prev_week;
    TextField title;
    TextField url;
    TextField email;
    TextField password;
    TextField admin_password;
    TextField password2;
    TextField admin_password2;
    Choice category;
    Button ok_but;
    Button cancel_but;
    Checkbox visible;
    Checkbox whiteboard;
    Checkbox chat;
    Checkbox bboard;
    Checkbox games;
    groupboard parent;
    
    board_info_window(final groupboard parent) {
        this.parent = parent;
        this.setTitle("Update Your Board Information");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final Panel panel = new Panel();
        final GridBagLayout layout2 = new GridBagLayout();
        panel.setLayout(layout2);
        final Label label;
        panel.add(label = new Label("Category"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout2.setConstraints(label, gridBagConstraints);
        panel.add(this.category = new Choice());
        this.category.addItem("Art");
        this.category.addItem("Books");
        this.category.addItem("Business");
        this.category.addItem("Children");
        this.category.addItem("Computers");
        this.category.addItem("Education");
        this.category.addItem("Films");
        this.category.addItem("Finance");
        this.category.addItem("Fun");
        this.category.addItem("Games");
        this.category.addItem("General");
        this.category.addItem("Health");
        this.category.addItem("Homepages");
        this.category.addItem("Internet");
        this.category.addItem("Music");
        this.category.addItem("News");
        this.category.addItem("People");
        this.category.addItem("Regional");
        this.category.addItem("Religion");
        this.category.addItem("Science");
        this.category.addItem("Sports");
        this.category.addItem("Teenagers");
        this.category.addItem("Television");
        this.category.addItem("Travel");
        gridBagConstraints.gridx = 1;
        layout2.setConstraints(this.category, gridBagConstraints);
        this.add(panel);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        layout.setConstraints(panel, gridBagConstraints2);
        final Panel panel2 = new Panel();
        final GridBagLayout layout3 = new GridBagLayout();
        panel2.setLayout(layout3);
        panel2.add(this.whiteboard = new Checkbox("Whiteboard"));
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints3.fill = 2;
        gridBagConstraints3.gridwidth = 1;
        gridBagConstraints3.gridheight = 1;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 0.0;
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        layout3.setConstraints(this.whiteboard, gridBagConstraints3);
        panel2.add(this.chat = new Checkbox("Chat"));
        gridBagConstraints3.gridx = 1;
        layout3.setConstraints(this.chat, gridBagConstraints3);
        panel2.add(this.bboard = new Checkbox("Bulletin Board"));
        gridBagConstraints3.gridx = 2;
        layout3.setConstraints(this.bboard, gridBagConstraints3);
        panel2.add(this.games = new Checkbox("Games"));
        gridBagConstraints3.gridx = 3;
        layout3.setConstraints(this.games, gridBagConstraints3);
        this.add(panel2);
        gridBagConstraints2.gridy = 1;
        layout.setConstraints(panel2, gridBagConstraints2);
        final Panel panel3 = new Panel();
        final GridBagLayout layout4 = new GridBagLayout();
        panel3.setLayout(layout4);
        final Label label2;
        panel3.add(label2 = new Label("Visits"));
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints4.fill = 1;
        gridBagConstraints4.gridwidth = 1;
        gridBagConstraints4.gridheight = 1;
        gridBagConstraints4.weightx = 0.0;
        gridBagConstraints4.weighty = 1.0;
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        layout4.setConstraints(label2, gridBagConstraints4);
        final Label label3;
        panel3.add(label3 = new Label("Visits So Far This Week"));
        gridBagConstraints4.gridy = 1;
        layout4.setConstraints(label3, gridBagConstraints4);
        final Label label4;
        panel3.add(label4 = new Label("Visits Previous Week"));
        gridBagConstraints4.gridy = 2;
        layout4.setConstraints(label4, gridBagConstraints4);
        final Label label5;
        panel3.add(label5 = new Label("Title"));
        gridBagConstraints4.gridy = 3;
        layout4.setConstraints(label5, gridBagConstraints4);
        final Label label6;
        panel3.add(label6 = new Label("URL of Board"));
        gridBagConstraints4.gridy = 4;
        layout4.setConstraints(label6, gridBagConstraints4);
        final Label label7;
        panel3.add(label7 = new Label("Your Email Address"));
        gridBagConstraints4.gridy = 5;
        layout4.setConstraints(label7, gridBagConstraints4);
        final Label label8;
        panel3.add(label8 = new Label("Password"));
        gridBagConstraints4.gridy = 6;
        layout4.setConstraints(label8, gridBagConstraints4);
        final Label label9;
        panel3.add(label9 = new Label("Password (confirm)"));
        gridBagConstraints4.gridy = 7;
        layout4.setConstraints(label9, gridBagConstraints4);
        final Label label10;
        panel3.add(label10 = new Label("Admin Password"));
        gridBagConstraints4.gridy = 8;
        layout4.setConstraints(label10, gridBagConstraints4);
        final Label label11;
        panel3.add(label11 = new Label("Admin Password (confirm)"));
        gridBagConstraints4.gridy = 9;
        layout4.setConstraints(label11, gridBagConstraints4);
        panel3.add(this.visits = new TextField(7));
        this.visits.setEditable(false);
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        layout4.setConstraints(this.visits, gridBagConstraints4);
        panel3.add(this.visits_this_week = new TextField(7));
        this.visits_this_week.setEditable(false);
        gridBagConstraints4.gridy = 1;
        layout4.setConstraints(this.visits_this_week, gridBagConstraints4);
        panel3.add(this.visits_prev_week = new TextField(7));
        this.visits_prev_week.setEditable(false);
        gridBagConstraints4.gridy = 2;
        layout4.setConstraints(this.visits_prev_week, gridBagConstraints4);
        panel3.add(this.title = new TextField(20));
        gridBagConstraints4.gridy = 3;
        layout4.setConstraints(this.title, gridBagConstraints4);
        panel3.add(this.url = new TextField(20));
        gridBagConstraints4.gridy = 4;
        layout4.setConstraints(this.url, gridBagConstraints4);
        panel3.add(this.email = new TextField(20));
        gridBagConstraints4.gridy = 5;
        layout4.setConstraints(this.email, gridBagConstraints4);
        panel3.add(this.password = new TextField(20));
        this.password.setEchoCharacter('*');
        gridBagConstraints4.gridy = 6;
        layout4.setConstraints(this.password, gridBagConstraints4);
        panel3.add(this.password2 = new TextField(20));
        this.password2.setEchoCharacter('*');
        gridBagConstraints4.gridy = 7;
        layout4.setConstraints(this.password2, gridBagConstraints4);
        panel3.add(this.admin_password = new TextField(20));
        this.admin_password.setEchoCharacter('*');
        gridBagConstraints4.gridy = 8;
        layout4.setConstraints(this.admin_password, gridBagConstraints4);
        panel3.add(this.admin_password2 = new TextField(20));
        this.admin_password2.setEchoCharacter('*');
        gridBagConstraints4.gridy = 9;
        layout4.setConstraints(this.admin_password2, gridBagConstraints4);
        this.add(panel3);
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.fill = 1;
        layout.setConstraints(panel3, gridBagConstraints2);
        final Panel panel4 = new Panel();
        final GridBagLayout layout5 = new GridBagLayout();
        panel4.setLayout(layout5);
        panel4.add(this.visible = new Checkbox("Make Board Visible in Listings/Search"));
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints5.gridwidth = 1;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.weightx = 0.0;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        layout5.setConstraints(this.visible, gridBagConstraints5);
        this.add(panel4);
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.fill = 2;
        layout.setConstraints(panel4, gridBagConstraints2);
        final Panel panel5 = new Panel();
        final GridBagLayout layout6 = new GridBagLayout();
        panel5.setLayout(layout6);
        panel5.add(this.ok_but = new Button("OK"));
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints6.gridwidth = 1;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.weightx = 0.0;
        gridBagConstraints6.weighty = 0.0;
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        layout6.setConstraints(this.ok_but, gridBagConstraints6);
        panel5.add(this.cancel_but = new Button("Cancel"));
        gridBagConstraints6.gridx = 1;
        layout6.setConstraints(this.cancel_but, gridBagConstraints6);
        this.add(panel5);
        gridBagConstraints2.gridy = 4;
        layout.setConstraints(panel5, gridBagConstraints2);
        this.pack();
    }
    
    void set_info(final int flags, final int n, final int n2, final int n3, final int n4, final int n5, final String text, final String text2, final String text3, final String s, final String s2) {
        this.flags = flags;
        this.whiteboard.setState((this.flags & 0x1) != 0x0);
        this.chat.setState((this.flags & 0x2) != 0x0);
        this.bboard.setState((this.flags & 0x4) != 0x0);
        this.games.setState((this.flags & 0x20) != 0x0);
        this.category.select(n - 1);
        this.visits.setText(Integer.toString(n2));
        this.visits_this_week.setText(Integer.toString(n3));
        this.visits_prev_week.setText(Integer.toString(n4));
        this.title.setText(text);
        this.url.setText(text2);
        this.email.setText(text3);
        this.password.setText(s);
        this.password2.setText(s);
        this.admin_password.setText(s2);
        this.admin_password2.setText(s2);
        this.visible.setState(n5 != 0);
        this.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            this.parent.board_info_win = null;
        }
        else if (event.target == this.ok_but) {
            String s = this.title.getText();
            String s2 = this.url.getText();
            String s3 = this.email.getText();
            String s4 = this.password.getText();
            final String text = this.password2.getText();
            String s5 = this.admin_password.getText();
            final String text2 = this.admin_password2.getText();
            if (!s4.equals(text)) {
                new message_box(this.parent, "Error", "Passwords do not match");
                return true;
            }
            if (!s5.equals(text2)) {
                new message_box(this.parent, "Error", "Admin passwords do not match");
                return true;
            }
            int selectedIndex = this.category.getSelectedIndex();
            if (selectedIndex == -1) {
                selectedIndex = 0;
            }
            try {
                ++selectedIndex;
                this.flags = 0;
                if (this.whiteboard.getState()) {
                    ++this.flags;
                }
                if (this.chat.getState()) {
                    this.flags += 2;
                }
                if (this.bboard.getState()) {
                    this.flags += 4;
                }
                if (this.games.getState()) {
                    this.flags += 32;
                }
                synchronized (this.parent.os) {
                    if (this.parent.use_utf) {
                        s = this.parent.string_to_utf(s);
                        s2 = this.parent.string_to_utf(s2);
                        s3 = this.parent.string_to_utf(s3);
                        s4 = this.parent.string_to_utf(s4);
                        s5 = this.parent.string_to_utf(s5);
                    }
                    if (s.length() > 400) {
                        s = s.substring(0, 400);
                    }
                    if (s2.length() > 400) {
                        s2 = s2.substring(0, 400);
                    }
                    this.parent.os.writeByte(37);
                    this.parent.os.writeInt(this.flags);
                    this.parent.os.writeShort(s.length());
                    this.parent.os.writeShort(s2.length());
                    this.parent.os.writeShort(s3.length());
                    this.parent.os.writeShort(s4.length());
                    this.parent.os.writeShort(s5.length());
                    if (s.length() > 0) {
                        this.parent.os.writeBytes(s);
                    }
                    if (s2.length() > 0) {
                        this.parent.os.writeBytes(s2);
                    }
                    if (s3.length() > 0) {
                        this.parent.os.writeBytes(s3);
                    }
                    if (s4.length() > 0) {
                        this.parent.os.writeBytes(s4);
                    }
                    if (s5.length() > 0) {
                        this.parent.os.writeBytes(s5);
                    }
                    int n;
                    if (this.visible.getState()) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    this.parent.os.writeByte(n);
                    this.parent.os.writeInt(selectedIndex);
                }
            }
            catch (IOException ex) {
                this.parent.dataError();
            }
            this.dispose();
            this.parent.board_info_win = null;
            if (this.parent.use_tunnel) {
                this.parent.t.force_post();
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.parent.board_info_win = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
