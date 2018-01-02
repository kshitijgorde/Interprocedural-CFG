// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Frame;

public class SendUrl extends Frame
{
    BorderLayout borderLayout1;
    Panel MainPanel;
    Panel panel2;
    Panel panel3;
    Panel panel4;
    Panel panel5;
    BorderLayout borderLayout2;
    Panel BtnPanel;
    Button CancelBtn;
    Button SendBtn;
    Button PreviewBtn;
    FlowLayout flowLayout1;
    TextArea HelpTxt;
    Panel panel1;
    Panel panel6;
    GridLayout gridLayout1;
    Label label1;
    TextField UrlInp;
    Label label2;
    
    public SendUrl() {
        this.borderLayout1 = new BorderLayout();
        this.MainPanel = new Panel();
        this.panel2 = new Panel();
        this.panel3 = new Panel();
        this.panel4 = new Panel();
        this.panel5 = new Panel();
        this.borderLayout2 = new BorderLayout();
        this.BtnPanel = new Panel();
        this.CancelBtn = new Button();
        this.SendBtn = new Button();
        this.PreviewBtn = new Button();
        this.flowLayout1 = new FlowLayout();
        this.HelpTxt = new TextArea("", 20, 4, 3);
        this.panel1 = new Panel();
        this.panel6 = new Panel();
        this.gridLayout1 = new GridLayout();
        this.label1 = new Label();
        this.UrlInp = new TextField();
        this.label2 = new Label();
        try {
            this._$3231();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$3231() throws Exception {
        this.setLayout(this.borderLayout1);
        this.setSize(new Dimension(583, 181));
        this.setTitle("Send URL To Room");
        this.setResizable(false);
        this.addWindowListener(new 1());
        this.setForeground(loader.secondaryFgColor);
        this.setBackground(loader.secondaryBgColor);
        this.borderLayout2.setHgap(10);
        this.BtnPanel.setLayout(this.flowLayout1);
        this.CancelBtn.setForeground(loader.buttonFgColor);
        this.CancelBtn.setBackground(loader.buttonBgColor);
        this.CancelBtn.setFont(new Font("Helvetica", 1, 13));
        this.CancelBtn.setLabel("  Cancel  ");
        this.CancelBtn.addActionListener(new 2());
        this.SendBtn.setForeground(loader.buttonFgColor);
        this.SendBtn.setBackground(loader.buttonBgColor);
        this.SendBtn.setFont(new Font("Helvetica", 1, 13));
        this.SendBtn.setLabel("  Send  URL To Room  ");
        this.SendBtn.addActionListener(new 3());
        this.PreviewBtn.setForeground(loader.buttonFgColor);
        this.PreviewBtn.setBackground(loader.buttonBgColor);
        this.PreviewBtn.setFont(new Font("Helvetica", 1, 13));
        this.PreviewBtn.setLabel("  Preview URL  ");
        this.PreviewBtn.addActionListener(new 4());
        this.flowLayout1.setAlignment(2);
        this.HelpTxt.setForeground(Color.darkGray);
        this.HelpTxt.setFont(new Font("Helvetica", 0, 11));
        this.HelpTxt.setEditable(false);
        this.HelpTxt.setRows(4);
        this.HelpTxt.setColumns(25);
        this.HelpTxt.setText("Use this Window to send a Web Page URL to ALL current Chat Room users. It will be opened in a new browser window.");
        this.gridLayout1.setColumns(1);
        this.label1.setFont(new Font("Helvetica", 1, 12));
        this.label1.setText("Enter Valid Web Page URL (Inc. http://)");
        this.UrlInp.setForeground(loader.inputFgColor);
        this.UrlInp.setBackground(loader.inputBgColor);
        this.UrlInp.setColumns(50);
        this.label2.setText("Example:  http://www.mydomain.com/mypage.html");
        this.gridLayout1.setRows(3);
        this.panel6.setLayout(this.gridLayout1);
        this.HelpTxt.setBackground(new Color(255, 255, 225));
        this.MainPanel.setLayout(this.borderLayout2);
        this.add(this.MainPanel, "Center");
        this.MainPanel.add(this.BtnPanel, "South");
        this.BtnPanel.add(this.PreviewBtn, null);
        this.BtnPanel.add(this.SendBtn, null);
        this.BtnPanel.add(this.CancelBtn, null);
        this.MainPanel.add(this.HelpTxt, "West");
        this.MainPanel.add(this.panel1, "North");
        this.MainPanel.add(this.panel6, "Center");
        this.panel6.add(this.label1, null);
        this.panel6.add(this.UrlInp, null);
        this.panel6.add(this.label2, null);
        this.add(this.panel2, "South");
        this.add(this.panel3, "West");
        this.add(this.panel4, "East");
        this.add(this.panel5, "North");
        this.pack();
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    void CancelBtn_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    void PreviewBtn_actionPerformed(final ActionEvent actionEvent) {
        final String trim = this.UrlInp.getText().trim();
        if (trim.length() == 0 || trim.length() > 200) {
            this.UrlInp.requestFocus();
            this.getToolkit().beep();
            return;
        }
        loader.ShowNewUrl(trim);
    }
    
    void SendBtn_actionPerformed(final ActionEvent actionEvent) {
        final String trim = this.UrlInp.getText().trim();
        if (trim.length() == 0 || trim.length() > 200) {
            this.UrlInp.requestFocus();
            this.getToolkit().beep();
            return;
        }
        loader.SendLine(String.valueOf("900 300 ").concat(String.valueOf(trim)));
        this.setVisible(false);
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            SendUrl.this.this_windowClosing(windowEvent);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SendUrl.this.CancelBtn_actionPerformed(actionEvent);
        }
    }
    
    class 3 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SendUrl.this.SendBtn_actionPerformed(actionEvent);
        }
    }
    
    class 4 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SendUrl.this.PreviewBtn_actionPerformed(actionEvent);
        }
    }
}
