import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.rmi.Remote;
import java.util.StringTokenizer;
import java.rmi.Naming;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Panel;
import symantec.itools.awt.MultiList;
import symantec.itools.awt.BorderPanel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AddressBookFrame extends Frame
{
    boolean fComponentsAdjusted;
    BorderPanel borderPanel1;
    MultiList addressList;
    Panel eastPnl;
    Panel buttonPnl;
    Button addBtn;
    Button removeBtn;
    Button selectBtn;
    Button closeBtn;
    String[] newAddressData;
    JMailAddress addressToAdd;
    TextField targetTxt;
    String username;
    AddressBookServer book;
    JMProps props;
    AddAddressDlg add;
    String[] addresses;
    
    public AddressBookFrame(final JMProps props) {
        this.fComponentsAdjusted = false;
        this.props = props;
        this.setFont(this.props.font);
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.props.addWindow(this);
        this.setLayout(new BorderLayout(10, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 430, this.insets().top + this.insets().bottom + 270);
        this.borderPanel1 = new BorderPanel();
        try {
            this.borderPanel1.setBevelStyle(3);
        }
        catch (PropertyVetoException ex) {}
        try {
            this.borderPanel1.setIPadBottom(10);
        }
        catch (PropertyVetoException ex2) {}
        try {
            this.borderPanel1.setIPadSides(10);
        }
        catch (PropertyVetoException ex3) {}
        try {
            this.borderPanel1.setPaddingRight(0);
        }
        catch (PropertyVetoException ex4) {}
        try {
            this.borderPanel1.setPaddingBottom(0);
        }
        catch (PropertyVetoException ex5) {}
        try {
            this.borderPanel1.setPaddingTop(0);
        }
        catch (PropertyVetoException ex6) {}
        try {
            this.borderPanel1.setIPadTop(10);
        }
        catch (PropertyVetoException ex7) {}
        try {
            this.borderPanel1.setPaddingLeft(0);
        }
        catch (PropertyVetoException ex8) {}
        this.borderPanel1.setLayout((LayoutManager)new BorderLayout(10, 0));
        this.borderPanel1.setBounds(this.insets().left, this.insets().top, 430, 270);
        this.add("Center", (Component)this.borderPanel1);
        (this.addressList = new MultiList()).setLayout((LayoutManager)null);
        try {
            this.addressList.setColumnSizes(new String[] { new String("85"), new String("85"), new String("150"), new String("1") });
        }
        catch (PropertyVetoException ex9) {}
        try {
            this.addressList.setHeadings(new String[] { this.props.language.getProperty("first_name", "First name"), this.props.language.getProperty("last_name", "Last name"), this.props.language.getProperty("email_address", "E-mail address"), new String(" ") });
        }
        catch (PropertyVetoException ex10) {}
        ((Component)this.addressList).setBounds(0, 0, 350, 249);
        ((Component)this.addressList).setBackground(new Color(16777215));
        ((Container)this.borderPanel1).add("Center", (Component)this.addressList);
        (this.eastPnl = new Panel()).setLayout(new BorderLayout(5, 5));
        this.eastPnl.setBounds(338, 0, 0, 249);
        ((Container)this.borderPanel1).add("East", this.eastPnl);
        (this.buttonPnl = new Panel()).setLayout(new GridLayout(4, 1, 5, 5));
        this.buttonPnl.setBounds(0, 0, 51, 103);
        this.eastPnl.add("North", this.buttonPnl);
        (this.addBtn = new Button()).setActionCommand("button");
        this.addBtn.setLabel(this.props.language.getProperty("button.add", "Add..."));
        this.addBtn.setBounds(0, 0, 51, 22);
        this.buttonPnl.add(this.addBtn);
        (this.removeBtn = new Button()).setActionCommand("button");
        this.removeBtn.setLabel(this.props.language.getProperty("button.remove", "Remove"));
        this.removeBtn.setBounds(0, 27, 71, 22);
        this.buttonPnl.add(this.removeBtn);
        (this.selectBtn = new Button()).setActionCommand("button");
        this.selectBtn.setLabel(this.props.language.getProperty("button.select", "Select"));
        this.selectBtn.setBounds(0, 54, 71, 22);
        this.buttonPnl.add(this.selectBtn);
        (this.closeBtn = new Button()).setActionCommand("button");
        this.closeBtn.setLabel(this.props.language.getProperty("button.close", "Close"));
        this.closeBtn.setBounds(0, 81, 71, 22);
        this.buttonPnl.add(this.closeBtn);
        this.setTitle(this.props.language.getProperty("title.address", "Address Book"));
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        this.addBtn.addActionListener(symAction);
        this.removeBtn.addActionListener(symAction);
        this.selectBtn.addActionListener(symAction);
        this.closeBtn.addActionListener(symAction);
        ((Component)this.addressList).addKeyListener(new SymKey());
        this.addressList.addActionListener((ActionListener)symAction);
        this.addComponentListener(new SymComponent());
        try {
            this.addressList.setCellFont(this.props.font);
            this.addressList.setHeadingBg(this.props.background);
            this.addressList.setHeadingFg(this.props.foreground);
            this.addressList.setHeadingFont(this.props.font);
        }
        catch (Exception ex11) {}
        this.getAddresses();
        this.add = new AddAddressDlg(this, true, this.props);
    }
    
    void getAddresses() {
        this.username = this.props.username;
        try {
            String s = this.props.base.getHost();
            if (this.props.proxyMode) {
                s = String.valueOf(s) + ":1100";
            }
            final Remote lookup = Naming.lookup("//" + s + "/AddressBook1");
            if (lookup instanceof AddressBookServer) {
                this.book = (AddressBookServer)lookup;
                this.addresses = this.book.getAddresses(this.username);
                for (int length = this.addresses.length, i = 0; i < length; ++i) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.addresses[i], "\t");
                    final String nextToken = stringTokenizer.nextToken();
                    final String nextToken2 = stringTokenizer.nextToken();
                    final String nextToken3 = stringTokenizer.nextToken();
                    this.addressList.addTextCell(i, 0, nextToken);
                    this.addressList.addTextCell(i, 1, nextToken2);
                    this.addressList.addTextCell(i, 2, nextToken3);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("ERROR: Could not connect with address book server.");
        }
    }
    
    public synchronized void show() {
        this.move(50, 50);
        super.show();
        this.targetTxt = null;
        this.selectBtn.setEnabled(false);
    }
    
    public void show(final TextField targetTxt) {
        this.move(50, 50);
        super.show();
        this.targetTxt = targetTxt;
        this.selectBtn.setEnabled(true);
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
    
    void addBtn_Action(final ActionEvent actionEvent) {
        this.addNewAddress(null);
    }
    
    void removeBtn_Action(final ActionEvent actionEvent) {
        final int selectedRow = this.addressList.getSelectedRow();
        final String cellText = this.addressList.getCellText(selectedRow, 0);
        final String cellText2 = this.addressList.getCellText(selectedRow, 1);
        final String cellText3 = this.addressList.getCellText(selectedRow, 2);
        this.addressList.removeRow(selectedRow);
        if (this.props.connected) {
            try {
                this.book.remove(this.username, cellText, cellText2, cellText3);
            }
            catch (Exception ex) {
                System.out.println("ERROR: " + ex.toString());
            }
        }
    }
    
    void selectBtn_Action(final ActionEvent actionEvent) {
        final String cellText = this.addressList.getCellText(this.addressList.getSelectedRow(), 2);
        if (this.targetTxt != null) {
            final String text = this.targetTxt.getText();
            if (text == null || text.equals("")) {
                this.targetTxt.setText(cellText);
            }
            else if (text.endsWith(",") || text.endsWith(";")) {
                this.targetTxt.setText(String.valueOf(text) + " " + cellText);
            }
            else if (text.endsWith(", ") || text.endsWith("; ")) {
                this.targetTxt.setText(String.valueOf(text) + cellText);
            }
            else if (text.endsWith(",  ") || text.endsWith(";  ")) {
                this.targetTxt.setText(String.valueOf(text) + cellText);
            }
            else {
                this.targetTxt.setText(String.valueOf(text) + ", " + cellText);
            }
        }
        this.hide();
    }
    
    void closeBtn_Action(final ActionEvent actionEvent) {
        this.hide();
    }
    
    void addressList_keyPressed(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equalsIgnoreCase("Delete")) {
            this.addressList.removeRow(this.addressList.getSelectedRow());
        }
    }
    
    public void addNewAddress(final JMailAddress mailAddress) {
        if (mailAddress != null) {
            this.add.setFirst(mailAddress.getFirst());
            this.add.setLast(mailAddress.getLast());
            this.add.setAddress(mailAddress.getAddress());
        }
        this.add.show();
    }
    
    public void commitAdd(final String[] newAddressData) {
        this.newAddressData = newAddressData;
        final int numberOfRows = this.addressList.getNumberOfRows();
        if (numberOfRows == 0) {}
        this.addressList.addTextCell(numberOfRows, 0, this.newAddressData[0]);
        this.addressList.addTextCell(numberOfRows, 1, this.newAddressData[1]);
        this.addressList.addTextCell(numberOfRows, 2, this.newAddressData[2]);
        if (this.props.connected) {
            try {
                this.book.add(this.username, this.newAddressData[0], this.newAddressData[1], this.newAddressData[2]);
            }
            catch (Exception ex) {
                System.out.println("ERROR: " + ex.toString());
            }
        }
    }
    
    void addressList_actionPerformed(final ActionEvent actionEvent) {
        final String cellText = this.addressList.getCellText(this.addressList.getSelectedRow(), 2);
        if (this.targetTxt != null) {
            final String text = this.targetTxt.getText();
            if (text == null || text.equals("")) {
                this.targetTxt.setText(cellText);
            }
            else if (text.endsWith(";")) {
                this.targetTxt.setText(String.valueOf(text) + " " + cellText);
            }
            else if (text.endsWith("; ")) {
                this.targetTxt.setText(String.valueOf(text) + cellText);
            }
            else if (text.endsWith(";  ")) {
                this.targetTxt.setText(String.valueOf(text) + cellText);
            }
            else {
                this.targetTxt.setText(String.valueOf(text) + "; " + cellText);
            }
            this.hide();
        }
    }
    
    void addressBookFrame_WindowOpen(final WindowEvent windowEvent) {
        if (this.book == null) {
            this.getAddresses();
        }
    }
    
    void AddressBookFrame_ComponentResized(final ComponentEvent componentEvent) {
        try {
            this.addressList.setCellFont(this.props.font);
            this.addressList.setHeadingBg(this.props.background);
            this.addressList.setHeadingFg(this.props.foreground);
            this.addressList.setHeadingFont(this.props.font);
        }
        catch (Exception ex) {}
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowOpened(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == AddressBookFrame.this) {
                AddressBookFrame.this.addressBookFrame_WindowOpen(windowEvent);
            }
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == AddressBookFrame.this) {
                AddressBookFrame.this.Frame1_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == AddressBookFrame.this.addBtn) {
                AddressBookFrame.this.addBtn_Action(actionEvent);
                return;
            }
            if (source == AddressBookFrame.this.removeBtn) {
                AddressBookFrame.this.removeBtn_Action(actionEvent);
                return;
            }
            if (source == AddressBookFrame.this.selectBtn) {
                AddressBookFrame.this.selectBtn_Action(actionEvent);
                return;
            }
            if (source == AddressBookFrame.this.closeBtn) {
                AddressBookFrame.this.closeBtn_Action(actionEvent);
                return;
            }
            if (source == AddressBookFrame.this.addressList) {
                AddressBookFrame.this.addressList_actionPerformed(actionEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getSource() == AddressBookFrame.this.addressList) {
                AddressBookFrame.this.addressList_keyPressed(keyEvent);
            }
        }
    }
    
    class SymComponent extends ComponentAdapter
    {
        public void componentResized(final ComponentEvent componentEvent) {
            if (componentEvent.getSource() == AddressBookFrame.this) {
                AddressBookFrame.this.AddressBookFrame_ComponentResized(componentEvent);
            }
        }
    }
}
