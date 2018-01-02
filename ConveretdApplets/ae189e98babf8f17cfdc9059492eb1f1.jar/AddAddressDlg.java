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
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;
import symantec.itools.awt.BorderPanel;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class AddAddressDlg extends Dialog
{
    boolean fComponentsAdjusted;
    BorderPanel borderPnl;
    Panel northPnl;
    Panel labelPnl;
    Label firstLbl;
    Label lastLbl;
    Label addressLbl;
    Panel textPnl;
    TextField firstTxt;
    TextField lastTxt;
    TextField addressTxt;
    Panel buttonPnl;
    Button okButton;
    Button cancelBtn;
    String first;
    String last;
    String address;
    AddressBookFrame parent;
    JMProps props;
    
    public AddAddressDlg(final Frame frame, final String s, final JMProps props) {
        super(frame, s, false);
        this.fComponentsAdjusted = false;
        this.props = props;
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 302, this.insets().top + this.insets().bottom + 152);
        this.borderPnl = new BorderPanel();
        try {
            this.borderPnl.setBevelStyle(0);
        }
        catch (PropertyVetoException ex) {}
        try {
            this.borderPnl.setIPadBottom(10);
        }
        catch (PropertyVetoException ex2) {}
        try {
            this.borderPnl.setIPadSides(10);
        }
        catch (PropertyVetoException ex3) {}
        try {
            this.borderPnl.setIPadTop(10);
        }
        catch (PropertyVetoException ex4) {}
        this.borderPnl.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPnl.setBounds(this.insets().left, this.insets().top, 302, 118);
        this.add("Center", (Component)this.borderPnl);
        (this.northPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.northPnl.setBounds(0, 0, 269, 0);
        ((Container)this.borderPnl).add("North", this.northPnl);
        (this.labelPnl = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.labelPnl.setBounds(0, 0, 0, 10);
        this.northPnl.add("West", this.labelPnl);
        (this.firstLbl = new Label()).setText(this.props.language.getProperty("first_name", "First name"));
        this.firstLbl.setBounds(0, 0, 14, 24);
        this.labelPnl.add(this.firstLbl);
        (this.lastLbl = new Label()).setText(this.props.language.getProperty("last_name", "Last name"));
        this.lastLbl.setBounds(0, 24, 78, 24);
        this.labelPnl.add(this.lastLbl);
        (this.addressLbl = new Label()).setText(this.props.language.getProperty("email_address", "E-mail address"));
        this.addressLbl.setBounds(0, 48, 78, 24);
        this.labelPnl.add(this.addressLbl);
        (this.textPnl = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.textPnl.setBounds(107, 0, 162, 72);
        this.northPnl.add("Center", this.textPnl);
        (this.firstTxt = new TextField(1)).setBounds(0, 0, 162, 24);
        this.textPnl.add(this.firstTxt);
        (this.lastTxt = new TextField(1)).setBounds(0, 24, 162, 24);
        this.textPnl.add(this.lastTxt);
        (this.addressTxt = new TextField()).setBounds(0, 48, 162, 24);
        this.textPnl.add(this.addressTxt);
        (this.buttonPnl = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.buttonPnl.setBounds(this.insets().left, this.insets().top + 118, 302, 34);
        this.add("South", this.buttonPnl);
        (this.okButton = new Button()).setActionCommand("OK");
        this.okButton.setLabel(this.props.language.getProperty("button.ok", "  OK  "));
        this.okButton.setBounds(99, 5, 47, 24);
        this.buttonPnl.add(this.okButton);
        (this.cancelBtn = new Button()).setActionCommand("button");
        this.cancelBtn.setLabel(this.props.language.getProperty("button.cancel", "Cancel"));
        this.cancelBtn.setBounds(151, 5, 52, 24);
        this.buttonPnl.add(this.cancelBtn);
        this.firstTxt.setBackground(Color.white);
        this.firstTxt.setForeground(Color.black);
        this.lastTxt.setBackground(Color.white);
        this.lastTxt.setForeground(Color.black);
        this.addressTxt.setBackground(Color.white);
        this.addressTxt.setForeground(Color.black);
        final SymAction symAction = new SymAction();
        this.cancelBtn.addActionListener(symAction);
        this.okButton.addActionListener(symAction);
        this.addWindowListener(new SymWindow());
        final SymKey symKey = new SymKey();
        this.addKeyListener(symKey);
        this.firstTxt.addKeyListener(symKey);
        this.lastTxt.addKeyListener(symKey);
        this.addressTxt.addKeyListener(symKey);
        this.okButton.addKeyListener(symKey);
        this.cancelBtn.addKeyListener(symKey);
        this.setTitle(this.props.language.getProperty("title.add_address", "Add to Address Book"));
        this.setResizable(true);
        this.pack();
    }
    
    public AddAddressDlg(final AddressBookFrame parent, final boolean b, final JMProps jmProps) {
        this(parent, "Add to Address Book", jmProps);
        this.parent = parent;
    }
    
    public String getUserName() {
        return this.firstTxt.getText();
    }
    
    public String getPassword() {
        return this.lastTxt.getText();
    }
    
    public void setUserName(final String text) {
        this.firstTxt.setText(text);
    }
    
    public void setPassword(final String text) {
        this.lastTxt.setText(text);
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
    
    void cancelBtn_Action(final ActionEvent actionEvent) {
        this.firstTxt.setText("");
        this.lastTxt.setText("");
        this.addressTxt.setText("");
        this.setVisible(false);
    }
    
    void okButton_Action(final ActionEvent actionEvent) {
        this.first = this.firstTxt.getText();
        this.last = this.lastTxt.getText();
        this.address = this.addressTxt.getText();
        if (this.address == null || this.address.trim().equals("")) {
            final ErrorDlg errorDlg = new ErrorDlg(this.parent, "ERROR!", this.props.language.getProperty("error.no_address", "You must include an email address with a new address book entry."), true);
            errorDlg.setBackground(this.props.background);
            errorDlg.setForeground(this.props.foreground);
            errorDlg.setFont(this.props.font);
            errorDlg.show();
            this.addressTxt.requestFocus();
            return;
        }
        if (this.first == null || this.first.trim().equals("")) {
            this.first = " ";
        }
        if (this.last == null || this.last.trim().equals("")) {
            this.last = " ";
        }
        this.firstTxt.setText("");
        this.lastTxt.setText("");
        this.addressTxt.setText("");
        this.parent.commitAdd(this.getData());
        this.setVisible(false);
    }
    
    void AddAddressDlg_WindowOpen(final WindowEvent windowEvent) {
        this.firstTxt.requestFocus();
    }
    
    void AddAddressDlg_KeyPress(final KeyEvent keyEvent) {
    }
    
    public String[] getData() {
        return new String[] { this.first, this.last, this.address };
    }
    
    void firstTxt_KeyPress(final KeyEvent keyEvent) {
        this.global_KeyPress(keyEvent);
    }
    
    void lastTxt_KeyPress(final KeyEvent keyEvent) {
        this.global_KeyPress(keyEvent);
    }
    
    void addressTxt_KeyPress(final KeyEvent keyEvent) {
        this.global_KeyPress(keyEvent);
    }
    
    private void global_KeyPress(final KeyEvent keyEvent) {
        final String keyText = KeyEvent.getKeyText(keyEvent.getKeyCode());
        if (!keyText.equalsIgnoreCase("Enter")) {
            if (keyText.equalsIgnoreCase("Escape")) {
                this.firstTxt.setText("");
                this.lastTxt.setText("");
                this.addressTxt.setText("");
                this.setVisible(false);
            }
            return;
        }
        this.first = this.firstTxt.getText();
        this.last = this.lastTxt.getText();
        this.address = this.addressTxt.getText();
        if (this.address == null || this.address.trim().equals("")) {
            final ErrorDlg errorDlg = new ErrorDlg(this.parent, "ERROR!", this.props.language.getProperty("error.no_address", "You must include an email address with a new address book entry."), true);
            errorDlg.setBackground(this.props.background);
            errorDlg.setForeground(this.props.foreground);
            errorDlg.setFont(this.props.font);
            errorDlg.show();
            this.addressTxt.requestFocus();
            return;
        }
        if (this.first == null || this.first.trim().equals("")) {
            this.first = " ";
        }
        if (this.last == null || this.last.trim().equals("")) {
            this.last = " ";
        }
        this.firstTxt.setText("");
        this.lastTxt.setText("");
        this.addressTxt.setText("");
        this.parent.commitAdd(this.getData());
        this.setVisible(false);
    }
    
    public void setFirst(final String text) {
        this.firstTxt.setText(text);
    }
    
    public void setLast(final String text) {
        this.lastTxt.setText(text);
    }
    
    public void setAddress(final String text) {
        this.addressTxt.setText(text);
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
            if (source == AddAddressDlg.this.cancelBtn) {
                AddAddressDlg.this.cancelBtn_Action(actionEvent);
                return;
            }
            if (source == AddAddressDlg.this.okButton) {
                AddAddressDlg.this.okButton_Action(actionEvent);
            }
        }
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowOpened(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == AddAddressDlg.this) {
                AddAddressDlg.this.AddAddressDlg_WindowOpen(windowEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            final Object source = keyEvent.getSource();
            if (source == AddAddressDlg.this) {
                AddAddressDlg.this.AddAddressDlg_KeyPress(keyEvent);
                return;
            }
            if (source == AddAddressDlg.this.firstTxt) {
                AddAddressDlg.this.firstTxt_KeyPress(keyEvent);
                return;
            }
            if (source == AddAddressDlg.this.lastTxt) {
                AddAddressDlg.this.lastTxt_KeyPress(keyEvent);
                return;
            }
            if (source == AddAddressDlg.this.addressTxt) {
                AddAddressDlg.this.addressTxt_KeyPress(keyEvent);
                return;
            }
            if (source == AddAddressDlg.this.okButton) {
                AddAddressDlg.this.okButton_KeyPress(keyEvent);
                return;
            }
            if (source == AddAddressDlg.this.cancelBtn) {
                AddAddressDlg.this.cancelBtn_KeyPress(keyEvent);
            }
        }
    }
}
