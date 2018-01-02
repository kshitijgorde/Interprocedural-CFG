import java.io.IOException;
import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.DataOutputStream;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class msg_window extends Frame implements groupboard_consts
{
    private TextField subject;
    private TextField name;
    private TextField email;
    private TextArea article_text;
    private Button post_button;
    private Button cancel_button;
    private DataOutputStream os;
    private groupboard gb;
    
    msg_window(final groupboard gb, final String text, final DataOutputStream os, final String text2, final String text3) {
        this.gb = gb;
        this.setTitle("Post Article");
        this.os = os;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.anchor = 13;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = 2;
        this.setLayout(layout);
        final Label label;
        this.add(label = new Label("Subject"));
        layout.setConstraints(label, gridBagConstraints);
        this.add(this.subject = new TextField());
        this.subject.setText(text2);
        layout.setConstraints(this.subject, gridBagConstraints2);
        final Label label2;
        this.add(label2 = new Label("Your Name (Optional)"));
        layout.setConstraints(label2, gridBagConstraints);
        this.add(this.name = new TextField());
        layout.setConstraints(this.name, gridBagConstraints2);
        this.name.setText(text);
        final Label label3;
        this.add(label3 = new Label("Your Email Address (Optional)"));
        layout.setConstraints(label3, gridBagConstraints);
        this.add(this.email = new TextField());
        layout.setConstraints(this.email, gridBagConstraints2);
        this.add(this.article_text = new TextArea());
        this.article_text.setText(text3);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.weighty = 10.0;
        layout.setConstraints(this.article_text, gridBagConstraints2);
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.fill = 0;
        this.add(this.post_button = new Button("Post"));
        layout.setConstraints(this.post_button, gridBagConstraints2);
        gridBagConstraints2.gridx = -1;
        this.add(this.cancel_button = new Button("Cancel"));
        layout.setConstraints(this.cancel_button, gridBagConstraints2);
        this.pack();
        this.resize(420, 500);
        this.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_button) {
            this.dispose();
        }
        else if (event.target == this.post_button) {
            String s = this.subject.getText();
            String s2 = this.name.getText();
            String s3 = this.email.getText();
            String s4 = this.article_text.getText();
            if (s4.length() <= 0) {
                new message_box(this.gb, "Error", "You cannot post an empty message");
            }
            else if (s.length() <= 0) {
                new message_box(this.gb, "Error", "You must enter a subject");
            }
            else if (s4.length() > 0 && s4.length() < 65500) {
                try {
                    synchronized (this.os) {
                        if (this.gb.use_utf) {
                            s = this.gb.string_to_utf(s);
                            s2 = this.gb.string_to_utf(s2);
                            s3 = this.gb.string_to_utf(s3);
                            s4 = this.gb.string_to_utf(s4);
                        }
                        this.os.writeByte(102);
                        this.os.writeShort(s.length());
                        this.os.writeShort(s2.length());
                        this.os.writeShort(s3.length());
                        this.os.writeShort(s4.length());
                        if (s.length() > 0) {
                            this.os.writeBytes(s);
                        }
                        if (s2.length() > 0) {
                            this.os.writeBytes(s2);
                        }
                        if (s3.length() > 0) {
                            this.os.writeBytes(s3);
                        }
                        if (s4.length() > 0) {
                            this.os.writeBytes(s4);
                        }
                    }
                }
                catch (IOException ex) {
                    System.err.println("Error sending data to server: " + ex.getMessage());
                }
                if (this.gb.use_tunnel) {
                    this.gb.t.force_post();
                }
                this.dispose();
            }
            else {
                new message_box(this.gb, "Error", "Message too long");
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
}
