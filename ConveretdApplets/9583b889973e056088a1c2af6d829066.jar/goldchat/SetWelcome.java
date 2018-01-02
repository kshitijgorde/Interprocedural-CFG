// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.WindowAdapter;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Frame;

public class SetWelcome extends Frame
{
    BorderLayout borderLayout1;
    Panel panel1;
    FlowLayout flowLayout1;
    Button CancelBtn;
    Button SetBtn;
    Panel panel2;
    Panel panel3;
    Panel panel4;
    Panel panel5;
    BorderLayout borderLayout2;
    TextArea MessageTxt;
    Label PromptTxt;
    
    public SetWelcome() {
        this.borderLayout1 = new BorderLayout();
        this.panel1 = new Panel();
        this.flowLayout1 = new FlowLayout();
        this.CancelBtn = new Button();
        this.SetBtn = new Button();
        this.panel2 = new Panel();
        this.panel3 = new Panel();
        this.panel4 = new Panel();
        this.panel5 = new Panel();
        this.borderLayout2 = new BorderLayout();
        this.MessageTxt = new TextArea("", 20, 4, 1);
        this.PromptTxt = new Label();
        try {
            this._$3231();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$3231() throws Exception {
        this.setLayout(this.borderLayout1);
        this.setSize(new Dimension(412, 211));
        this.setTitle("Set Room Welcome Message");
        this.setResizable(false);
        this.addWindowListener(new 1());
        this.setBackground(loader.secondaryBgColor);
        this.setForeground(loader.secondaryFgColor);
        this.flowLayout1.setVgap(10);
        this.flowLayout1.setAlignment(2);
        this.flowLayout1.setHgap(10);
        this.CancelBtn.setBackground(loader.buttonBgColor);
        this.CancelBtn.setForeground(loader.buttonFgColor);
        this.CancelBtn.setFont(new Font("Helvetica", 1, 12));
        this.CancelBtn.setLabel("  Cancel  ");
        this.CancelBtn.addActionListener(new 2());
        this.PromptTxt.setFont(new Font("Helvetica", 1, 12));
        this.PromptTxt.setText("Set new Room Welcome Message below. Max 400 chars.      ");
        this.SetBtn.setBackground(loader.buttonBgColor);
        this.SetBtn.setForeground(loader.buttonFgColor);
        this.SetBtn.setFont(new Font("Helvetica", 1, 12));
        this.SetBtn.setLabel("  Set Message  ");
        this.SetBtn.addActionListener(new 3());
        this.MessageTxt.setBackground(Color.white);
        this.MessageTxt.setForeground(Color.black);
        this.panel5.setLayout(this.borderLayout2);
        this.panel1.setLayout(this.flowLayout1);
        this.add(this.panel1, "South");
        this.panel1.add(this.SetBtn, null);
        this.panel1.add(this.CancelBtn, null);
        this.add(this.panel2, "North");
        this.add(this.panel3, "West");
        this.add(this.panel4, "East");
        this.add(this.panel5, "Center");
        this.panel5.add(this.MessageTxt, "Center");
        this.panel5.add(this.PromptTxt, "North");
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    void CancelBtn_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    void SetBtn_actionPerformed(final ActionEvent actionEvent) {
        final String trim = this.MessageTxt.getText().trim();
        if (trim.length() < 3 || trim.length() > 400) {
            this.PromptTxt.setForeground(Color.red);
            this.getToolkit().beep();
            this.PromptTxt.setText("Welcome message must be 3 - 400 alpha-numeric chars.  ");
            return;
        }
        this.SetBtn.setEnabled(false);
        final StringTokenizer stringTokenizer = new StringTokenizer(trim);
        String concat = "";
        while (stringTokenizer.hasMoreTokens()) {
            concat = String.valueOf(String.valueOf(concat).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken().trim().replace('\r', ' ').replace('\n', ' ').replace('\t', ' ').replace('\f', ' ')));
        }
        this.MessageTxt.setText(concat);
        this.repaint();
        loader.SendLine(String.valueOf("910 200 ").concat(String.valueOf(concat.trim())));
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            SetWelcome.this.this_windowClosing(windowEvent);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SetWelcome.this.CancelBtn_actionPerformed(actionEvent);
        }
    }
    
    class 3 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SetWelcome.this.SetBtn_actionPerformed(actionEvent);
        }
    }
}
