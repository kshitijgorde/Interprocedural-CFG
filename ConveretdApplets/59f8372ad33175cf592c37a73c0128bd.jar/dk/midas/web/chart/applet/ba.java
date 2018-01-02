// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Dialog;

public class ba extends Dialog implements ActionListener
{
    public static String for;
    public static String int;
    protected Label[] new;
    protected Component[] case;
    protected Button byte;
    protected Button if;
    protected ak[] char;
    public Object[] do;
    private a a;
    private b try;
    
    protected ba(final Frame frame, final String title, final ak[] char1, final String s, final a a) {
        super(frame, title, true);
        this.try = new b();
        this.setFont(a0.if);
        this.setTitle(title);
        this.setResizable(false);
        this.setModal(true);
        final Dimension size = frame.getSize();
        this.setLocation(new Point(frame.getLocationOnScreen().x + size.width / 2, frame.getLocationOnScreen().y + size.height / 2));
        this.char = char1;
        this.a = a;
        this.do = new Object[this.char.length];
        this.new = new Label[this.char.length];
        this.case = new Component[this.char.length];
        final Panel panel = new Panel();
        Container container = null;
        final Panel panel2 = new Panel();
        panel.setLayout(new GridLayout(this.char.length, 2));
        if (s != null) {
            container = new Panel();
            container.add(new Label(s));
        }
        for (int i = 0; i < this.char.length; ++i) {
            this.new[i] = new Label(this.char[i].for);
            this.case[i] = this.a(this.char[i]);
            panel.add(this.new[i]);
            panel.add(this.case[i]);
        }
        this.setLayout(new BorderLayout());
        if (container != null) {
            this.add("North", container);
        }
        this.add("Center", panel);
        panel2.add(this.byte = new Button(ba.for));
        this.byte.addActionListener(this);
        panel2.add(this.if = new Button(ba.int));
        this.if.addActionListener(this);
        this.add("South", panel2);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                ba.this.setVisible(false);
                ba.this.do[0] = null;
            }
        });
    }
    
    private Component a(final ak ak) {
        Choice choice2;
        if (ak.new.compareTo("Choice") == 0) {
            final Choice choice = new Choice();
            final String[] case1 = ((d)ak).case;
            for (int i = 0; i < case1.length; ++i) {
                choice.add(case1[i]);
            }
            choice2 = choice;
        }
        else {
            final TextField textField = new TextField();
            if (ak.new.compareTo("Integer") == 0) {
                textField.setText(new Integer((int)ak.a).toString());
            }
            else if (ak.new.compareTo("Float") == 0) {
                textField.setText(new Float(ak.a).toString());
            }
            textField.addActionListener(this);
            choice2 = (Choice)textField;
        }
        choice2.addKeyListener(this.try);
        return choice2;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.byte || source instanceof TextField) {
            if (this.a()) {
                for (int i = 0; i < this.case.length; ++i) {
                    this.do[i] = this.a(i);
                }
                this.setVisible(false);
                this.transferFocus();
            }
        }
        else if (source == this.if) {
            this.do[0] = null;
            this.setVisible(false);
        }
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.if();
            this.a();
            this.case[0].requestFocus();
        }
        super.setVisible(visible);
    }
    
    protected void if() {
        for (int i = 0; i < this.char.length; ++i) {
            if (this.char[i].new.compareTo("Choice") == 0) {
                ((Choice)this.case[i]).select(0);
            }
            else if (this.char[i].new.compareTo("Integer") == 0) {
                ((TextField)this.case[i]).setText(new Integer((int)this.char[i].a).toString());
            }
            else if (this.char[i].new.compareTo("Float") == 0) {
                ((TextField)this.case[i]).setText(new Float(this.char[i].a).toString());
            }
        }
    }
    
    protected Object a(final int n) {
        if (this.char[n].new.compareTo("Choice") == 0) {
            final int selectedIndex = ((Choice)this.case[n]).getSelectedIndex();
            return (selectedIndex == -1) ? null : ((d)this.char[n]).byte[selectedIndex];
        }
        try {
            if (this.char[n].new.compareTo("Integer") == 0) {
                final Integer n2 = new Integer(((TextField)this.case[n]).getText());
                return this.char[n].a(n2) ? n2 : null;
            }
            if (this.char[n].new.compareTo("Float") == 0) {
                final Float n3 = new Float(((TextField)this.case[n]).getText());
                return this.char[n].a(n3) ? n3 : null;
            }
        }
        catch (NumberFormatException ex) {}
        return null;
    }
    
    protected boolean a() {
        boolean enabled = true;
        for (int n = 0; n < this.case.length && enabled; enabled &= (null != this.do[n]), ++n) {
            this.do[n] = this.a(n);
        }
        if (this.a != null && enabled) {
            enabled &= this.a.a(this.do);
        }
        this.byte.setEnabled(enabled);
        return enabled;
    }
    
    static {
        ba.for = new String("Ok");
        ba.int = new String("Cancel");
    }
    
    public interface a
    {
        boolean a(final Object[] p0);
    }
    
    private class b implements KeyListener
    {
        public void keyPressed(final KeyEvent keyEvent) {
            this.a(keyEvent);
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            this.a(keyEvent);
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
            this.a(keyEvent);
        }
        
        protected void a(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 27) {
                ba.this.actionPerformed(new ActionEvent(ba.this.if, 1001, ba.this.if.getActionCommand()));
            }
            else {
                ba.this.a();
            }
        }
    }
}
