import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class gbFrame extends tFrame
{
    static final String COPYRIGHT = "Guestbook II Copyright (C) 1996 by Bill Giel";
    TextField tf1;
    TextField tf2;
    TextArea ta1;
    TextArea ta2;
    tButton sendButton;
    smtpSend smtp;
    
    gbFrame(final String s, final smtpSend smtp, final Image image, final boolean b) {
        super(s);
        this.smtp = smtp;
        this.setFont(new Font("System", 0, 14));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        if (image != null) {
            gridBagConstraints.insets = new Insets(5, 5, 8, 5);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 0;
            final logoPanel logoPanel = new logoPanel(image, b);
            layout.setConstraints(logoPanel, gridBagConstraints);
            this.add(logoPanel);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(3, 5, 0, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final Label label = new Label(" Your Name (optional): ");
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        gridBagConstraints.insets = new Insets(3, 0, 0, 5);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.tf1 = new TextField("", 32), gridBagConstraints);
        this.add(this.tf1);
        gridBagConstraints.insets = new Insets(3, 5, 0, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final Label label2 = new Label(" Your Email (optional): ");
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        gridBagConstraints.insets = new Insets(3, 0, 0, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 17;
        layout.setConstraints(this.tf2 = new TextField("", 32), gridBagConstraints);
        this.add(this.tf2);
        gridBagConstraints.insets = new Insets(8, 5, 0, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        final Label label3 = new Label(" Any comments or suggestions are welcome! ");
        layout.setConstraints(label3, gridBagConstraints);
        this.add(label3);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.ta1 = new TextArea("", 6, 66), gridBagConstraints);
        this.add(this.ta1);
        gridBagConstraints.insets = new Insets(3, 5, 3, 0);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.sendButton = new tButton("Send"), gridBagConstraints);
        this.add(this.sendButton);
        gridBagConstraints.insets = new Insets(3, 0, 3, 0);
        final tButton tButton = new tButton("Clear");
        layout.setConstraints(tButton, gridBagConstraints);
        this.add(tButton);
        gridBagConstraints.insets = new Insets(3, 0, 3, 5);
        gridBagConstraints.gridwidth = 0;
        final tButton tButton2 = new tButton("Quit");
        layout.setConstraints(tButton2, gridBagConstraints);
        this.add(tButton2);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.ta2 = new TextArea("Ready.", 3, 66), gridBagConstraints);
        this.add(this.ta2, false);
        this.ta2.setEditable(false);
        this.validate();
        smtp.setScroller(this.ta2);
        this.setBackground(Color.lightGray);
        System.out.println("Guestbook II Copyright (C) 1996 by Bill Giel");
    }
    
    private void clearText() {
        this.tf1.setText("");
        this.tf2.setText("");
        this.ta1.setText("");
        this.ta2.setText("Ready.");
    }
    
    public void show() {
        this.pack();
        this.sendButton.enable();
        this.clearText();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.move((screenSize.width - this.size().width) / 2, (screenSize.height - this.size().height) / 2);
        super.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("Quit")) {
            this.hide();
            return true;
        }
        if (o.equals("Clear")) {
            this.clearText();
            return true;
        }
        if (o.equals("Send")) {
            if (this.tf1.getText().length() + this.tf2.getText().length() + this.ta1.getText().length() == 0) {
                this.ta2.appendText("\r\nNothing to send.");
                System.out.println("Nothing to send.");
            }
            else {
                this.sendButton.disable();
                final String string = "Guest: " + this.tf1.getText() + "\n" + "Address: " + this.tf2.getText() + "\n\n" + this.ta1.getText();
                try {
                    this.smtp.mailMessage("Guestbook Entry!", string);
                }
                catch (Exception ex) {
                    final String string2 = ex.toString();
                    this.ta2.appendText("\r\n" + string2.substring(string2.indexOf(":") + 2, string2.length()));
                    System.out.println(string2.substring(string2.indexOf(":") + 2, string2.length()));
                    this.ta2.appendText("\r\nMessage NOT sent.");
                    System.out.println("Message NOT sent.");
                    this.sendButton.enable();
                }
            }
        }
        return false;
    }
}
