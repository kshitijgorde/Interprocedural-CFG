// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.WindowAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.TextArea;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Frame;

public class SetStaff extends Frame
{
    BorderLayout borderLayout1;
    Panel panel1;
    Panel panel2;
    Panel panel3;
    Panel panel4;
    Panel panel5;
    GridLayout gridLayout1;
    Panel panelA;
    Panel panelC;
    Panel panelB;
    BorderLayout borderLayout2;
    TextArea HelpTxt;
    BorderLayout borderLayout3;
    List StaffList;
    FlowLayout flowLayout2;
    Panel panel6;
    GridLayout gridLayout2;
    Label TotalDisp;
    Label label2;
    TextField PasswordInp;
    Label label3;
    TextField UsernameInp;
    Button DelBtn;
    Button AddBtn;
    Panel panel7;
    BorderLayout borderLayout4;
    Button CloseBtn;
    Label StatusTxt;
    BorderLayout borderLayout5;
    Panel panel8;
    Panel panel9;
    Panel panel10;
    Panel panel11;
    
    public SetStaff() {
        this.borderLayout1 = new BorderLayout();
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.panel3 = new Panel();
        this.panel4 = new Panel();
        this.panel5 = new Panel();
        this.gridLayout1 = new GridLayout();
        this.panelA = new Panel();
        this.panelC = new Panel();
        this.panelB = new Panel();
        this.borderLayout2 = new BorderLayout();
        this.HelpTxt = new TextArea("", 10, 10, 3);
        this.borderLayout3 = new BorderLayout();
        this.StaffList = new List();
        this.flowLayout2 = new FlowLayout();
        this.panel6 = new Panel();
        this.gridLayout2 = new GridLayout();
        this.TotalDisp = new Label();
        this.label2 = new Label();
        this.PasswordInp = new TextField();
        this.label3 = new Label();
        this.UsernameInp = new TextField();
        this.DelBtn = new Button();
        this.AddBtn = new Button();
        this.panel7 = new Panel();
        this.borderLayout4 = new BorderLayout();
        this.CloseBtn = new Button();
        this.StatusTxt = new Label();
        this.borderLayout5 = new BorderLayout();
        this.panel8 = new Panel();
        this.panel9 = new Panel();
        this.panel10 = new Panel();
        this.panel11 = new Panel();
        try {
            this._$3231();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$3231() throws Exception {
        this.setSize(new Dimension(541, 324));
        this.setTitle("Add / Remove Staff Users");
        this.setResizable(false);
        this.addWindowListener(new 1());
        this.setBackground(loader.secondaryBgColor);
        this.setForeground(loader.secondaryFgColor);
        this.gridLayout1.setColumns(3);
        this.HelpTxt.setForeground(Color.darkGray);
        this.HelpTxt.setFont(new Font("Helvetica", 0, 11));
        this.HelpTxt.setText("To Add a Staff user, enter a new Username and Password then click the 'Add Staff User' button.\n\nTo Delete a staff user account select the Staff user in the list and click the 'Del Staff User' button.\n\nTo change a users password, remove the account and add it again using the new password.  Passwords are CaSe SeNsItIvE, Usernames are not.");
        this.HelpTxt.setEditable(false);
        this.HelpTxt.setBackground(new Color(255, 255, 225));
        this.panelB.setLayout(this.flowLayout2);
        this.panelC.setLayout(this.borderLayout3);
        this.flowLayout2.setHgap(0);
        this.flowLayout2.setVgap(0);
        this.gridLayout2.setColumns(1);
        this.gridLayout2.setVgap(5);
        this.TotalDisp.setForeground(loader.primaryFgColor);
        this.TotalDisp.setBackground(loader.primaryBgColor);
        this.TotalDisp.setFont(new Font("Helvetica", 1, 12));
        this.TotalDisp.setAlignment(1);
        this.TotalDisp.setText("Total Staff - 0");
        this.CloseBtn.setBackground(loader.buttonBgColor);
        this.CloseBtn.setForeground(loader.buttonFgColor);
        this.CloseBtn.setFont(new Font("Helvetica", 1, 13));
        this.CloseBtn.setLabel("   CLOSE   ");
        this.StatusTxt.setText("Loading Staff List,  Please Wait...     ");
        this.StatusTxt.setAlignment(1);
        this.StatusTxt.setFont(new Font("Helvetica", 1, 12));
        this.StatusTxt.setForeground(loader.buttonFgColor);
        this.StatusTxt.setBackground(loader.buttonBgColor);
        this.CloseBtn.addActionListener(new 2());
        this.panel7.setLayout(this.borderLayout4);
        this.label2.setFont(new Font("Helvetica", 1, 12));
        this.label2.setText("New Username");
        this.PasswordInp.setEchoChar('*');
        this.PasswordInp.setColumns(18);
        this.label3.setFont(new Font("Helvetica", 1, 12));
        this.label3.setText("New Password");
        this.StaffList.setBackground(new Color(235, 235, 255));
        this.StaffList.addItemListener(new 3());
        this.UsernameInp.setColumns(18);
        this.DelBtn.setBackground(loader.buttonBgColor);
        this.DelBtn.setForeground(loader.buttonFgColor);
        this.DelBtn.setFont(new Font("Helvetica", 1, 12));
        this.DelBtn.setLabel("Del Staff User");
        this.DelBtn.addActionListener(new 4());
        this.AddBtn.setBackground(loader.buttonBgColor);
        this.AddBtn.setForeground(loader.buttonFgColor);
        this.AddBtn.setFont(new Font("Helvetica", 1, 12));
        this.AddBtn.setLabel("       Add Staff User       ");
        this.borderLayout4.setHgap(5);
        this.AddBtn.addActionListener(new 5());
        this.gridLayout2.setRows(8);
        this.panel6.setLayout(this.gridLayout2);
        this.panelA.setLayout(this.borderLayout2);
        this.panel5.setLayout(this.gridLayout1);
        this.panel4.setLayout(this.borderLayout5);
        this.setLayout(this.borderLayout1);
        this.add(this.panel1, "North");
        this.add(this.panel2, "West");
        this.add(this.panel3, "East");
        this.add(this.panel4, "South");
        this.panel4.add(this.panel7, "Center");
        this.panel7.add(this.CloseBtn, "East");
        this.panel7.add(this.StatusTxt, "Center");
        this.panel4.add(this.panel8, "South");
        this.panel4.add(this.panel9, "West");
        this.panel4.add(this.panel10, "East");
        this.panel4.add(this.panel11, "North");
        this.add(this.panel5, "Center");
        this.panel5.add(this.panelA, null);
        this.panelA.add(this.HelpTxt, "Center");
        this.panel5.add(this.panelB, null);
        this.panelB.add(this.panel6, null);
        this.panel6.add(this.TotalDisp, null);
        this.panel6.add(this.label2, null);
        this.panel6.add(this.UsernameInp, null);
        this.panel6.add(this.label3, null);
        this.panel6.add(this.PasswordInp, null);
        this.panel6.add(this.AddBtn, null);
        this.panel6.add(this.DelBtn, null);
        this.panel5.add(this.panelC, null);
        this.panelC.add(this.StaffList, "Center");
    }
    
    void UIBusy() {
        this.StatusTxt.setText("Please Wait..");
        this.TotalDisp.setText("Updating..");
        this.UsernameInp.setEnabled(false);
        this.PasswordInp.setEnabled(false);
        this.AddBtn.setEnabled(false);
        this.DelBtn.setEnabled(false);
        this.StaffList.setEnabled(false);
    }
    
    void UIReady() {
        this.StatusTxt.setText("");
        this.UsernameInp.setText("");
        this.PasswordInp.setText("");
        this.UsernameInp.setEnabled(true);
        this.PasswordInp.setEnabled(true);
        this.AddBtn.setEnabled(true);
        this.DelBtn.setEnabled(false);
        this.StaffList.setEnabled(true);
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    void CloseBtn_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    void AddBtn_actionPerformed(final ActionEvent actionEvent) {
        final String trim = this.UsernameInp.getText().trim();
        final String trim2 = this.PasswordInp.getText().trim();
        if (trim.length() < 3) {
            this.StatusTxt.setText("Username Too Short!  3-15 Chars, No Spaces.");
            this.UsernameInp.requestFocus();
            this.getToolkit().beep();
            return;
        }
        if (trim.length() > 15) {
            this.StatusTxt.setText("Username Too Long!  3-15 Chars, No Spaces.");
            this.UsernameInp.requestFocus();
            this.getToolkit().beep();
            return;
        }
        if (trim2.length() < 3) {
            this.StatusTxt.setText("Password Too Short!   3-15 Chars, No Spaces.");
            this.PasswordInp.requestFocus();
            this.getToolkit().beep();
            return;
        }
        if (trim2.length() > 15) {
            this.StatusTxt.setText("Password Too Long!  3-15 Chars, No Spaces.");
            this.PasswordInp.requestFocus();
            this.getToolkit().beep();
            return;
        }
        this.UIBusy();
        final String replace = trim.replace('©', '@').replace(' ', '_').replace('\r', '?').replace('\n', '?').replace('\t', '?').replace('\f', '?');
        final String replace2 = trim2.replace(' ', '_').replace('©', '?');
        this.UsernameInp.setText(replace);
        this.PasswordInp.setText(replace2);
        loader.SendLine(String.valueOf(String.valueOf(String.valueOf("910 610 ").concat(String.valueOf(replace))).concat(String.valueOf(" "))).concat(String.valueOf(replace2)));
    }
    
    void DelBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.StaffList.getSelectedIndex() == -1) {
            return;
        }
        final String item = this.StaffList.getItem(this.StaffList.getSelectedIndex());
        this.UIBusy();
        loader.SendLine(String.valueOf("910 620 ").concat(String.valueOf(item)));
    }
    
    void StaffList_itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            this.DelBtn.setEnabled(true);
        }
        else {
            this.DelBtn.setEnabled(false);
        }
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            SetStaff.this.this_windowClosing(windowEvent);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SetStaff.this.CloseBtn_actionPerformed(actionEvent);
        }
    }
    
    class 3 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            SetStaff.this.StaffList_itemStateChanged(itemEvent);
        }
    }
    
    class 4 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SetStaff.this.DelBtn_actionPerformed(actionEvent);
        }
    }
    
    class 5 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SetStaff.this.AddBtn_actionPerformed(actionEvent);
        }
    }
}
