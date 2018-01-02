// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.Component;
import a.a.a.a.b;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import a.a.a.a.a;
import javax.swing.JFrame;

public class bu extends JFrame
{
    a int;
    JLabel else;
    JComboBox do;
    JLabel case;
    JButton goto;
    JButton char;
    aw new;
    JTextField for;
    JLabel byte;
    JTextField if;
    JTextField a;
    JLabel try;
    
    public void if() {
        try {
            this.do.addItem("1: xor");
            this.do.addItem("2: des");
            this.do.addItem("3: xor+base64");
            this.do.addItem("4: base64");
            this.do.setSelectedIndex(2);
        }
        catch (Exception ex) {
            this.new.a(1, "encryptdemo1", ex);
        }
    }
    
    public bu(final aw new1) {
        this.int = new a();
        this.else = new JLabel();
        this.do = new JComboBox();
        this.case = new JLabel();
        this.goto = new JButton();
        this.char = new JButton();
        this.new = null;
        this.for = new JTextField();
        this.byte = new JLabel();
        this.if = new JTextField();
        this.a = new JTextField();
        this.try = new JLabel();
        try {
            this.new = new1;
            this.a();
            this.new.a(this.else);
            this.new.a(this.case);
            this.new.a(this.goto);
            this.new.a(this.char);
        }
        catch (Exception ex) {
            this.new.a(1, "EncryptTestq3342", ex);
        }
        this.if();
    }
    
    private void a() throws Exception {
        this.getContentPane().setLayout(this.int);
        this.else.setText("Method:");
        this.goto.addActionListener(new ap(this));
        this.char.setToolTipText("");
        this.char.setText("Close");
        this.char.addActionListener(new b2(this));
        this.setTitle("Encrypt parameter");
        this.for.setToolTipText("Text to encrypt");
        this.byte.setText("Result:");
        this.if.setFont(new Font("Tahoma", 1, 11));
        this.if.setToolTipText("Encrypted string");
        this.try.setText("Verification:");
        this.goto.setFont(new Font("Tahoma", 1, 11));
        this.a.setToolTipText("Should be the same with the original string");
        this.getContentPane().add(this.else, new b(10, 16, -1, -1));
        this.int.if(329);
        this.int.a(188);
        this.goto.setText("OK");
        this.case.setText("String:");
        this.getContentPane().add(this.for, new b(92, 42, 225, -1));
        this.getContentPane().add(this.case, new b(10, 43, -1, -1));
        this.getContentPane().add(this.if, new b(91, 99, 224, -1));
        this.getContentPane().add(this.byte, new b(10, 99, -1, -1));
        this.getContentPane().add(this.do, new b(91, 13, 226, -1));
        this.getContentPane().add(this.a, new b(91, 128, 224, -1));
        this.getContentPane().add(this.char, new b(237, 158, 78, -1));
        this.getContentPane().add(this.try, new b(9, 132, -1, -1));
        this.getContentPane().add(this.goto, new b(238, 66, 78, -1));
    }
    
    public void if(final ActionEvent actionEvent) {
        try {
            final String text = this.for.getText();
            if (this.do.getSelectedItem() == null) {
                this.new.a(1, "ERROR,select method first!");
                return;
            }
            if (text.length() < 1) {
                this.new.a(1, "ERROR,enter string first!");
                return;
            }
            final String trim = ((String)this.do.getSelectedItem()).trim();
            String text2;
            if (trim.equals("1: xor")) {
                text2 = "encrypted__1__" + this.new.else(text);
            }
            else if (trim.equals("2: des")) {
                text2 = "encrypted__2__" + this.new.if(true, text);
            }
            else if (trim.equals("3: xor+base64")) {
                text2 = "encrypted__3__" + cf.if(this.new.else(text));
            }
            else if (trim.equals("4: base64")) {
                text2 = "encrypted__4__" + cf.if(text);
            }
            else {
                text2 = "Unknown method";
            }
            this.if.setText(text2);
            this.a.setText(this.new.d(text2));
            this.new.a(1, "EVENT, ready");
        }
        catch (Exception ex) {
            this.new.a(1, "encryptdemo", ex);
        }
    }
    
    public void a(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
}
