import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Panel;
import symantec.itools.awt.WrappingLabel;
import symantec.itools.awt.BorderPanel;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ErrorDlg extends Dialog
{
    boolean fComponentsAdjusted;
    BorderPanel borderPanel1;
    WrappingLabel errorLbl;
    Panel panel1;
    Button okBtn;
    
    public ErrorDlg(final Frame frame, final boolean b) {
        super(frame, b);
        this.fComponentsAdjusted = false;
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 385, this.insets().top + this.insets().bottom + 150);
        this.borderPanel1 = new BorderPanel();
        try {
            this.borderPanel1.setBevelStyle(3);
        }
        catch (PropertyVetoException ex) {}
        this.borderPanel1.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPanel1.setBounds(this.insets().left, this.insets().top, 300, 116);
        this.add("Center", (Component)this.borderPanel1);
        this.errorLbl = new WrappingLabel();
        try {
            this.errorLbl.setText("Error: ");
        }
        catch (PropertyVetoException ex2) {}
        ((Component)this.errorLbl).setBounds(0, 0, 279, 90);
        ((Container)this.borderPanel1).add("Center", (Component)this.errorLbl);
        (this.panel1 = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.panel1.setBounds(this.insets().left, this.insets().top + 116, 300, 34);
        this.add("South", this.panel1);
        (this.okBtn = new Button()).setActionCommand("button");
        this.okBtn.setLabel("  OK  ");
        this.okBtn.setBounds(126, 5, 47, 24);
        this.panel1.add(this.okBtn);
        this.setTitle("Attention!");
        this.addWindowListener(new SymWindow());
        this.okBtn.addActionListener(new SymAction());
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
    
    public ErrorDlg(final Frame frame, final String title, final String text, final boolean b) {
        this(frame, b);
        this.setTitle(title);
        try {
            this.errorLbl.setText(text);
        }
        catch (PropertyVetoException ex) {}
    }
    
    public synchronized void show() {
        final Rectangle bounds = this.getParent().bounds();
        final Rectangle bounds2 = this.bounds();
        this.move(bounds.x + (bounds.width - bounds2.width) / 2, bounds.y + (bounds.height - bounds2.height) / 2);
        super.show();
    }
    
    void Dialog1_WindowClosing(final WindowEvent windowEvent) {
        this.hide();
    }
    
    void okBtn_Action(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == ErrorDlg.this) {
                ErrorDlg.this.Dialog1_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == ErrorDlg.this.okBtn) {
                ErrorDlg.this.okBtn_Action(actionEvent);
            }
        }
    }
}
