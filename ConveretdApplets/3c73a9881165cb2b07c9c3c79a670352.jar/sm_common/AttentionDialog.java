// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Dialog;

public class AttentionDialog extends Dialog
{
    boolean fComponentsAdjusted;
    Panel panel1;
    Button okButton;
    TextArea textArea1;
    
    public AttentionDialog(final Frame parent, final String title, final String message) {
        super(parent, title, true);
        this.fComponentsAdjusted = false;
        this.panel1 = new Panel();
        this.okButton = new Button();
        this.textArea1 = new TextArea("", 0, 0, 3);
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setSize(270, 100);
        this.setVisible(false);
        this.panel1.setLayout(new FlowLayout(1, 5, 5));
        this.add("South", this.panel1);
        this.panel1.setBackground(Color.lightGray);
        this.panel1.setBounds(0, 67, 270, 33);
        this.okButton.setLabel("OK");
        this.panel1.add(this.okButton);
        this.okButton.setBackground(Color.lightGray);
        this.okButton.setBounds(119, 5, 31, 23);
        this.add("Center", this.textArea1);
        this.textArea1.setBackground(Color.red);
        this.textArea1.setBounds(0, 0, 270, 67);
        this.textArea1.setText(message);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(this.getSize().width / 2, this.getSize().height / 2);
        this.setSize(d.width * 5 / 8, d.height / 3);
        final SymAction lSymAction = new SymAction();
        this.okButton.addActionListener(lSymAction);
    }
    
    public void addNotify() {
        final Dimension d = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + d.width, insets.top + insets.bottom + d.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point p = components[i].getLocation();
            p.translate(insets.left, insets.top);
            components[i].setLocation(p);
        }
        this.fComponentsAdjusted = true;
    }
    
    void okButton_ActionPerformed(final ActionEvent event) {
        this.okButton_ActionPerformed_Interaction1(event);
    }
    
    void okButton_ActionPerformed_Interaction1(final ActionEvent event) {
        try {
            this.dispose();
        }
        catch (Exception ex) {}
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            final Object object = event.getSource();
            if (object == AttentionDialog.this.okButton) {
                AttentionDialog.this.okButton_ActionPerformed(event);
            }
        }
    }
}
