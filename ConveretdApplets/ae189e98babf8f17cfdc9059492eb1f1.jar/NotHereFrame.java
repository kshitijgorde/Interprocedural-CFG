import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import symantec.itools.awt.WrappingLabel;
import symantec.itools.awt.BorderPanel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class NotHereFrame extends Frame
{
    boolean fComponentsAdjusted;
    BorderPanel borderPanel1;
    WrappingLabel errorLbl;
    Panel panel1;
    Button okBtn;
    
    public NotHereFrame() {
        this.fComponentsAdjusted = false;
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 386, this.insets().top + this.insets().bottom + 161);
        this.setBackground(new Color(16776960));
        this.borderPanel1 = new BorderPanel();
        try {
            this.borderPanel1.setBevelStyle(3);
        }
        catch (PropertyVetoException ex) {}
        this.borderPanel1.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPanel1.setBounds(this.insets().left, this.insets().top, 386, 127);
        this.add("Center", (Component)this.borderPanel1);
        ((Component)(this.errorLbl = new WrappingLabel())).setBounds(0, 0, 365, 101);
        ((Container)this.borderPanel1).add("Center", (Component)this.errorLbl);
        (this.panel1 = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.panel1.setBounds(this.insets().left, this.insets().top + 127, 386, 34);
        this.add("South", this.panel1);
        (this.okBtn = new Button()).setActionCommand("button");
        this.okBtn.setLabel("  OK  ");
        this.okBtn.setBounds(169, 5, 47, 24);
        this.okBtn.setBackground(new Color(12632256));
        this.panel1.add(this.okBtn);
        this.setTitle("Untitled");
        this.addWindowListener(new SymWindow());
        this.okBtn.addActionListener(new SymAction());
    }
    
    public NotHereFrame(final String title, final String text) {
        this();
        this.setTitle(title);
        try {
            this.errorLbl.setText(text);
        }
        catch (PropertyVetoException ex) {}
    }
    
    public synchronized void show() {
        this.move(50, 50);
        super.show();
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        this.setSize(this.insets().left + this.insets().right + size.width, this.insets().top + this.insets().bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(this.insets().left, this.insets().top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void Frame1_WindowClosing(final WindowEvent windowEvent) {
        this.hide();
    }
    
    void okBtn_Action(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == NotHereFrame.this) {
                NotHereFrame.this.Frame1_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == NotHereFrame.this.okBtn) {
                NotHereFrame.this.okBtn_Action(actionEvent);
            }
        }
    }
}
