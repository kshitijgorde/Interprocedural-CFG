// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import a.a.a.a.b;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import a.a.a.a.a;
import javax.swing.JFrame;

public class d extends JFrame
{
    a h;
    JLabel e;
    JComboBox i;
    JLabel d;
    JComboBox g;
    JButton try;
    JButton new;
    JSlider do;
    JSlider a;
    aw char;
    webphone byte;
    JButton int;
    a f;
    JLabel c;
    JLabel b;
    JLabel void;
    JTextField long;
    JPasswordField case;
    JCheckBox goto;
    JButton for;
    JButton if;
    JCheckBox else;
    
    public void a() {
        try {
            if (this.byte.color_background.length() > 0) {
                this.setBackground(this.char.a(this.byte.color_background, this.getBackground()));
                this.getContentPane().setBackground(this.char.a(this.byte.color_background, this.getBackground()));
            }
            if (this.byte.color_label_background.length() > 0) {
                this.c.setBackground(this.char.a(this.byte.color_label_background, this.c.getBackground()));
                this.b.setBackground(this.char.a(this.byte.color_label_background, this.b.getBackground()));
                this.void.setBackground(this.char.a(this.byte.color_label_background, this.void.getBackground()));
            }
            if (this.byte.color_label_foreground.length() > 0) {
                this.c.setForeground(this.char.a(this.byte.color_label_foreground, this.c.getForeground()));
                this.b.setForeground(this.char.a(this.byte.color_label_foreground, this.b.getForeground()));
                this.void.setForeground(this.char.a(this.byte.color_label_foreground, this.void.getForeground()));
            }
            if (this.byte.color_edit_background.length() > 0) {
                this.goto.setBackground(this.char.a(this.byte.color_edit_background, this.goto.getBackground()));
            }
            if (this.byte.color_edit_foreground.length() > 0) {
                this.goto.setForeground(this.char.a(this.byte.color_edit_foreground, this.goto.getForeground()));
            }
            if (this.byte.color_buton_background.length() > 0) {
                this.for.setBackground(this.char.a(this.byte.color_buton_background, this.for.getBackground()));
                this.if.setBackground(this.char.a(this.byte.color_buton_background, this.if.getBackground()));
            }
            if (this.byte.color_buton_foreground.length() > 0) {
                this.for.setForeground(this.char.a(this.byte.color_buton_foreground, this.for.getForeground()));
                this.if.setForeground(this.char.a(this.byte.color_buton_foreground, this.if.getForeground()));
            }
            this.repaint();
        }
        catch (Exception ex) {
            this.char.a(3, "proxyauthcolors", ex);
        }
    }
    
    public d(final aw char1, final webphone byte1) {
        this.h = new a();
        this.e = new JLabel();
        this.i = new JComboBox();
        this.d = new JLabel();
        this.g = new JComboBox();
        this.try = new JButton();
        this.new = new JButton();
        this.do = new JSlider();
        this.a = new JSlider();
        this.char = null;
        this.byte = null;
        this.int = new JButton();
        this.f = new a();
        this.c = new JLabel();
        this.b = new JLabel();
        this.void = new JLabel();
        this.long = new JTextField();
        this.case = new JPasswordField();
        this.goto = new JCheckBox();
        this.for = new JButton();
        this.if = new JButton();
        this.else = new JCheckBox();
        try {
            this.char = char1;
            this.byte = byte1;
            this.if();
            this.char.a(this.c);
            this.char.a(this.b);
            this.char.a(this.void);
            this.char.a(this.goto);
            this.char.a(this.for);
            this.char.a(this.if);
            if (this.char.c5) {
                this.long.setText(this.char.aI);
            }
            if (this.char.cs > 2) {
                this.else.setVisible(true);
            }
            else {
                this.else.setVisible(false);
            }
            this.case.setText("");
            this.a();
        }
        catch (Exception ex) {
            this.char.a(2, "proxyauthctor", ex);
        }
    }
    
    private void if() throws Exception {
        this.getContentPane().setLayout(this.h);
        this.getContentPane().setBackground(SystemColor.control);
        this.setTitle("HTTP Proxy Authentication");
        this.getContentPane().setLayout(this.f);
        this.c.setFont(new Font("Tahoma", 1, 12));
        this.c.setText("HTTP Proxy Authentication");
        this.else.setToolTipText("");
        this.else.setText("Don't request again");
        this.goto.setSelected(true);
        this.for.addActionListener(new cc(this));
        this.if.addActionListener(new au(this));
        this.getContentPane().add(this.c, new b(16, 13, -1, -1));
        this.f.if(257);
        this.f.a(192);
        this.if.setText("Cancel");
        this.for.setText("OK");
        this.goto.setToolTipText("");
        this.goto.setText("Remember my password");
        this.void.setToolTipText("");
        this.void.setText("Password:");
        this.b.setText("Username:");
        this.getContentPane().add(this.goto, new b(88, 100, -1, -1));
        this.getContentPane().add(this.case, new b(92, 77, 139, 21));
        this.getContentPane().add(this.void, new b(16, 79, -1, -1));
        this.getContentPane().add(this.b, new b(16, 55, -1, -1));
        this.getContentPane().add(this.long, new b(92, 51, 139, 22));
        this.getContentPane().add(this.if, new b(164, 153, 68, -1));
        this.getContentPane().add(this.for, new b(89, 153, 65, -1));
        this.getContentPane().add(this.else, new b(88, 123, -1, -1));
    }
    
    public void do(final ActionEvent actionEvent) {
    }
    
    public void int(final ActionEvent actionEvent) {
    }
    
    public void a(final ChangeEvent changeEvent) {
    }
    
    public void if(final ChangeEvent changeEvent) {
    }
    
    public void if(final ActionEvent actionEvent) {
        this.byte.jButton18_actionPerformed(null);
    }
    
    public void for(final ActionEvent actionEvent) {
        try {
            if (this.else.isSelected()) {
                this.char.cs = -5;
            }
            this.char.c5 = this.goto.isSelected();
            this.char.aI = this.long.getText();
            this.char.cH = this.case.getText();
            this.byte.SaveSettings();
            this.byte.asyncneedproxyauthui = 3;
            this.byte.asynccfgsave = true;
            this.setVisible(false);
        }
        catch (Exception ex) {
            this.char.a(2, "proxyauthuisave", ex);
        }
    }
    
    public void a(final ActionEvent actionEvent) {
        this.byte.asyncneedproxyauthui = 4;
        this.setVisible(false);
    }
}
