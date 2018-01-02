import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class f implements ActionListener
{
    private int byte;
    private int for;
    private Applet try;
    Font int;
    Color case;
    Color else;
    Color if;
    Panel a;
    Button char;
    Button do;
    TextField new;
    
    public f(final Applet try1, final int byte1, final int for1) {
        this.int = new Font("Helvetica", 1, 11);
        this.case = new Color(90, 75, 20);
        this.else = new Color(125, 45, 0);
        this.if = new Color(250, 250, 164);
        this.a = new Panel();
        this.char = new Button();
        this.do = new Button();
        this.new = new TextField();
        this.for = for1;
        this.byte = byte1;
        this.try = try1;
        ((under)this.try).int();
        this.a();
    }
    
    private void a() {
        final Label label = new Label();
        this.a.setLayout(null);
        this.try.add(this.a);
        this.a.setBackground(this.case);
        this.a.setBounds((this.try.getBounds().width - 200) / 2, (this.try.getBounds().height - 100) / 2, 200, 100);
        label.setText("YOU MADE A HIGHSCORE!");
        label.setForeground(this.if);
        label.setFont(this.int);
        this.a.add(label);
        label.setBounds(30, 10, 160, 24);
        this.a.add(this.new);
        this.new.setBounds(20, 40, 160, 20);
        this.new.setFont(this.int);
        this.new.setForeground(this.if);
        this.new.addActionListener(this);
        this.new.requestFocus();
        this.char.setLabel("Done");
        this.a.add(this.char);
        this.char.setBackground(this.else);
        this.char.setForeground(this.if);
        this.char.setFont(this.int);
        this.char.setBounds(105, 70, 80, 24);
        this.char.addActionListener(this);
        this.do.setLabel("Cancel");
        this.do.setForeground(this.if);
        this.do.setFont(this.int);
        this.a.add(this.do);
        this.do.setBackground(this.else);
        this.do.setBounds(15, 70, 80, 24);
        this.do.addActionListener(this);
        this.a.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.char || actionEvent.getSource() == this.new) {
            String s = this.new.getText();
            if (s.length() < 1) {
                s = "NoName";
            }
            else if (s.length() > 16) {
                s = s.substring(0, 15);
            }
            new c(this.byte, this.try, this.for, s);
            this.a.setVisible(false);
        }
        else {
            this.a.setVisible(false);
        }
        ((under)this.try).byte();
    }
}
