// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import a.a.a.a.b;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import a.a.a.a.a;
import javax.swing.JFrame;

public class y extends JFrame
{
    a if;
    JLabel try;
    JEditorPane char;
    JEditorPane byte;
    JButton case;
    JComboBox a;
    public webphone new;
    public t else;
    JScrollPane for;
    JScrollPane do;
    aw int;
    
    public y(final aw int1, final webphone new1) {
        this.if = new a();
        this.try = new JLabel();
        this.char = new JEditorPane();
        this.byte = new JEditorPane();
        this.case = new JButton();
        this.a = new JComboBox();
        this.new = null;
        this.else = null;
        this.for = new JScrollPane();
        this.do = new JScrollPane();
        this.int = null;
        try {
            this.int = int1;
            this.new = new1;
            this.a();
            this.int.a(this.try);
            this.int.a(this.case);
            if (this.new.color_background.length() > 0) {
                this.setBackground(this.int.a(this.new.color_background, this.getBackground()));
                this.getContentPane().setBackground(this.int.a(this.new.color_background, this.getBackground()));
            }
            if (this.new.color_label_background.length() > 0) {
                this.try.setBackground(this.int.a(this.new.color_label_background, this.try.getBackground()));
            }
            if (this.new.color_label_foreground.length() > 0) {
                this.try.setForeground(this.int.a(this.new.color_label_foreground, this.try.getForeground()));
            }
            if (this.new.color_edit_background.length() > 0) {
                this.a.setBackground(this.int.a(this.new.color_edit_background, this.a.getBackground()));
                this.char.setBackground(this.int.a(this.new.color_edit_background, this.char.getBackground()));
                this.byte.setBackground(this.int.a(this.new.color_edit_background, this.byte.getBackground()));
            }
            if (this.new.color_edit_foreground.length() > 0) {
                this.a.setForeground(this.int.a(this.new.color_edit_foreground, this.a.getForeground()));
                this.char.setForeground(this.int.a(this.new.color_edit_foreground, this.char.getForeground()));
                this.char.setForeground(this.int.a(this.new.color_edit_foreground, this.byte.getForeground()));
            }
            if (this.new.color_buton_background.length() > 0) {
                this.case.setBackground(this.int.a(this.new.color_buton_background, this.case.getBackground()));
            }
            if (this.new.color_buton_foreground.length() > 0) {
                this.case.setForeground(this.int.a(this.new.color_buton_foreground, this.case.getForeground()));
            }
        }
        catch (Exception ex) {
            this.int.a(1, "chatjbinit", ex);
        }
    }
    
    private void a() throws Exception {
        this.getContentPane().setLayout(this.if);
        this.try.setForeground(Color.darkGray);
        this.try.setText("Send to: ");
        this.case.setToolTipText("Send message");
        this.case.setSelectedIcon(null);
        this.case.setText("Send");
        this.case.addActionListener(new a9(this));
        this.if.if(369);
        this.if.a(226);
        this.char.setBackground(new Color(249, 249, 255));
        this.char.setEditable(false);
        this.byte.setFont(new Font("Tahoma", 1, 11));
        this.byte.setNextFocusableComponent(this.case);
        this.byte.setToolTipText("");
        this.setResizable(false);
        this.a.setEditable(true);
        this.do.getViewport().add(this.byte);
        this.for.getViewport().add(this.char);
        this.getContentPane().add(this.try, new b(5, 153, 71, -1));
        this.getContentPane().add(this.a, new b(76, 150, 169, -1));
        this.getContentPane().add(this.for, new b(6, 7, 357, 139));
        this.getContentPane().add(this.case, new b(293, 174, 70, 46));
        this.getContentPane().add(this.do, new b(2, 175, 291, 46));
    }
    
    public void a(final ActionEvent actionEvent) {
        try {
            if (this.byte.getText().trim().length() < 1) {
                return;
            }
            String s = "";
            if (this.a.getSelectedItem() != null) {
                s = (String)this.a.getSelectedItem();
            }
            final String trim = s.trim();
            if (trim.length() < 1) {
                this.int.a(1, "No target found");
                return;
            }
            bc bc = null;
            if (this.else != null && this.else.O != null) {
                bc = this.else.O;
            }
            else if (this.new != null && this.new.mainthread != null) {
                bc = this.new.mainthread;
            }
            if (bc == null) {
                this.int.a(1, "No phone instance found");
                return;
            }
            final bc bc2 = bc;
            final aw int1 = this.int;
            bc2.a(1, this.else, -1, trim, this.byte.getText());
            this.char.setText(this.char.getText() + this.new.usr_username + ": " + this.byte.getText() + "\r\n");
            this.byte.setText("");
            this.byte.requestFocus();
        }
        catch (Exception ex) {
            this.int.a(1, "SendChat", ex);
        }
    }
    
    public void a(final String s, final String s2) {
        this.char.setText(this.char.getText() + s + ": " + s2 + "\r\n");
    }
}
