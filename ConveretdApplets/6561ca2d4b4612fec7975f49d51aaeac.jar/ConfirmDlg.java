import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.net.URL;
import symantec.itools.awt.WrappingLabel;
import symantec.itools.awt.BorderPanel;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ConfirmDlg extends Dialog
{
    boolean fComponentsAdjusted;
    Panel panel1;
    Button okButton;
    Button cancelBtn;
    BorderPanel borderPanel1;
    WrappingLabel label1;
    sendFrame parentFrame;
    
    public ConfirmDlg(final sendFrame parentFrame, final String s, final String text, final URL url) {
        super(parentFrame, s, false);
        this.fComponentsAdjusted = false;
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 402, this.insets().top + this.insets().bottom + 132);
        (this.panel1 = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.panel1.setBounds(this.insets().left, this.insets().top + 98, 402, 34);
        this.add("South", this.panel1);
        (this.okButton = new Button()).setActionCommand("OK");
        this.okButton.setLabel("   OK   ");
        this.okButton.setBounds(145, 5, 55, 24);
        this.panel1.add(this.okButton);
        (this.cancelBtn = new Button()).setActionCommand("button");
        this.cancelBtn.setLabel("Cancel");
        this.cancelBtn.setBounds(205, 5, 52, 24);
        this.cancelBtn.setBackground(new Color(12632256));
        this.panel1.add(this.cancelBtn);
        this.borderPanel1 = new BorderPanel();
        try {
            this.borderPanel1.setBevelStyle(3);
        }
        catch (PropertyVetoException ex) {}
        this.borderPanel1.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPanel1.setBounds(this.insets().left, this.insets().top, 20, 40);
        this.add("Center", (Component)this.borderPanel1);
        ((Component)(this.label1 = new WrappingLabel())).setBounds(0, 0, 381, 72);
        ((Container)this.borderPanel1).add("Center", (Component)this.label1);
        try {
            this.label1.setText(text);
        }
        catch (Exception ex2) {}
        final SymAction symAction = new SymAction();
        this.okButton.addActionListener(symAction);
        this.cancelBtn.addActionListener(symAction);
        this.addWindowListener(new SymWindow());
        final SymKey symKey = new SymKey();
        this.addKeyListener(symKey);
        this.okButton.addKeyListener(symKey);
        this.cancelBtn.addKeyListener(symKey);
        this.parentFrame = parentFrame;
    }
    
    public ConfirmDlg(final sendFrame sendFrame) {
        this(sendFrame, "Confirm", "Event", null);
    }
    
    public ConfirmDlg(final sendFrame sendFrame, final boolean b) {
        this(sendFrame);
    }
    
    public ConfirmDlg(final sendFrame sendFrame, final String s, final boolean b) {
        this(sendFrame, "Confirm", s, null);
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
    
    void okButton_Action(final ActionEvent actionEvent) {
        this.hide();
        this.parentFrame.removeFromProps();
    }
    
    void cancelBtn_Action(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    void ConfirmDlg_WindowOpen(final WindowEvent windowEvent) {
        this.cancelBtn.requestFocus();
        this.okButton.transferFocus();
    }
    
    private void global_KeyPress(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equalsIgnoreCase("Escape")) {
            this.setVisible(false);
        }
    }
    
    void ConfirmDlg_KeyPress(final KeyEvent keyEvent) {
        this.global_KeyPress(keyEvent);
    }
    
    void okButton_KeyPress(final KeyEvent keyEvent) {
        this.global_KeyPress(keyEvent);
    }
    
    void cancelBtn_KeyPress(final KeyEvent keyEvent) {
        this.global_KeyPress(keyEvent);
    }
    
    public synchronized void show() {
        final Rectangle bounds = this.getParent().bounds();
        final Rectangle bounds2 = this.bounds();
        this.move(bounds.x + (bounds.width - bounds2.width) / 2, bounds.y + (bounds.height - bounds2.height) / 2);
        super.show();
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == ConfirmDlg.this.okButton) {
                ConfirmDlg.this.okButton_Action(actionEvent);
                return;
            }
            if (source == ConfirmDlg.this.cancelBtn) {
                ConfirmDlg.this.cancelBtn_Action(actionEvent);
            }
        }
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowOpened(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == ConfirmDlg.this) {
                ConfirmDlg.this.ConfirmDlg_WindowOpen(windowEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            final Object source = keyEvent.getSource();
            if (source == ConfirmDlg.this) {
                ConfirmDlg.this.ConfirmDlg_KeyPress(keyEvent);
                return;
            }
            if (source == ConfirmDlg.this.okButton) {
                ConfirmDlg.this.okButton_KeyPress(keyEvent);
                return;
            }
            if (source == ConfirmDlg.this.cancelBtn) {
                ConfirmDlg.this.cancelBtn_KeyPress(keyEvent);
            }
        }
    }
}
