// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.Component;
import a.a.a.a.b;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import a.a.a.a.a;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class bl extends JFrame
{
    JLabel try;
    a do;
    JButton byte;
    JButton new;
    public t case;
    TitledBorder if;
    TitledBorder a;
    aw int;
    webphone for;
    
    public bl(final aw int1) {
        this.try = new JLabel();
        this.do = new a();
        this.byte = new JButton();
        this.new = new JButton();
        this.case = null;
        this.if = new TitledBorder("");
        this.a = new TitledBorder("");
        this.int = null;
        this.for = null;
        try {
            this.int = int1;
            this.for = int1.ct;
            this.a();
            this.int.a(this.try);
            this.int.a(this.byte);
            this.int.a(this.new);
            if (this.for.color_background.length() > 0) {
                this.setBackground(this.int.a(this.for.color_background, this.getBackground()));
            }
            if (this.for.color_label_background.length() > 0) {
                this.try.setBackground(this.int.a(this.for.color_label_background, this.try.getBackground()));
            }
            if (this.for.color_label_foreground.length() > 0) {
                this.try.setForeground(this.int.a(this.for.color_label_foreground, this.try.getForeground()));
            }
            if (this.for.color_buton_background.length() > 0) {
                this.byte.setBackground(this.int.a(this.for.color_buton_background, this.byte.getBackground()));
                this.new.setBackground(this.int.a(this.for.color_buton_background, this.new.getBackground()));
            }
            if (this.for.color_buton_foreground.length() > 0) {
                this.byte.setForeground(this.int.a(this.for.color_buton_foreground, this.byte.getForeground()));
                this.new.setForeground(this.int.a(this.for.color_buton_foreground, this.new.getForeground()));
            }
        }
        catch (Exception ex) {
            this.int.a(1, "incomingjbinit", ex);
        }
    }
    
    private void a() throws Exception {
        this.getContentPane().setLayout(this.do);
        this.try.setToolTipText("");
        this.try.setText("Incoming call ...");
        this.do.if(281);
        this.do.a(38);
        this.new.setForeground(Color.red);
        this.new.setBorder(null);
        this.new.setText("Reject");
        this.new.addActionListener(new bt(this));
        this.byte.setFont(new Font("Tahoma", 1, 11));
        this.byte.setForeground(new Color(0, 161, 0));
        this.byte.setBorder(null);
        this.byte.setText("Accept");
        this.byte.addActionListener(new af(this));
        this.setResizable(false);
        this.getContentPane().add(this.new, new b(148, 19, 133, 20));
        this.getContentPane().add(this.byte, new b(1, 19, 141, 20));
        this.getContentPane().add(this.try, new b(2, 1, 279, -1));
    }
    
    public void if(final ActionEvent actionEvent) {
        if (this.case != null && this.case.O != null) {
            final bc o = this.case.O;
            final aw int1 = this.int;
            o.a(2, this.case, -1, "", "");
        }
        else {
            this.int.a(2, "ERROR, no ep on action1");
        }
        this.int.dI = true;
        this.int.a(1, "Accepted");
        this.setVisible(false);
    }
    
    public void a(final ActionEvent actionEvent) {
        if (this.case != null && this.case.O != null) {
            final bc o = this.case.O;
            final aw int1 = this.int;
            o.a(3, this.case, -1, "", "");
        }
        else {
            this.int.a(2, "ERROR, no ep on action2");
        }
        this.int.dI = true;
        this.int.a(1, "Rejected");
        this.setVisible(false);
    }
}
