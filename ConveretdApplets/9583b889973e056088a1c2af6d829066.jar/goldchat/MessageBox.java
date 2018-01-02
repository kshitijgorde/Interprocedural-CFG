// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Frame;

public class MessageBox extends Frame
{
    BorderLayout borderLayout1;
    Panel panel1;
    Panel panel2;
    Panel panel3;
    Panel panel4;
    Panel panel5;
    FlowLayout flowLayout1;
    FlowLayout flowLayout2;
    FlowLayout flowLayout3;
    FlowLayout flowLayout4;
    Button button1;
    GridLayout gridLayout1;
    Label PromptTxt;
    Label DetailsTxt;
    
    public MessageBox() {
        this.borderLayout1 = new BorderLayout();
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.panel3 = new Panel();
        this.panel4 = new Panel();
        this.panel5 = new Panel();
        this.flowLayout1 = new FlowLayout();
        this.flowLayout2 = new FlowLayout();
        this.flowLayout3 = new FlowLayout();
        this.flowLayout4 = new FlowLayout();
        this.button1 = new Button();
        this.gridLayout1 = new GridLayout();
        this.PromptTxt = new Label();
        this.DetailsTxt = new Label();
        try {
            this._$3231();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$3231() throws Exception {
        this.setLayout(this.borderLayout1);
        this.setFont(new Font("Helvetica", 0, 13));
        this.setBackground(loader.secondaryBgColor);
        this.setResizable(false);
        this.addWindowListener(new 1());
        this.borderLayout1.setVgap(2);
        this.borderLayout1.setHgap(2);
        this.panel5.setLayout(this.gridLayout1);
        this.panel4.setLayout(this.flowLayout4);
        this.panel3.setLayout(this.flowLayout3);
        this.panel2.setLayout(this.flowLayout2);
        this.flowLayout1.setVgap(10);
        this.flowLayout2.setVgap(10);
        this.flowLayout3.setHgap(10);
        this.flowLayout4.setHgap(10);
        this.button1.setFont(new Font("Helvetica", 1, 14));
        this.button1.setLabel("   OK   ");
        this.button1.addActionListener(new 2());
        this.gridLayout1.setRows(2);
        this.gridLayout1.setColumns(1);
        this.gridLayout1.setVgap(5);
        this.PromptTxt.setFont(new Font("Helvetica", 1, 14));
        this.PromptTxt.setText("Message Prompt");
        this.DetailsTxt.setText("Message details go here...");
        this.flowLayout2.setHgap(10);
        this.flowLayout1.setAlignment(2);
        this.flowLayout1.setHgap(20);
        this.panel1.setLayout(this.flowLayout1);
        this.add(this.panel1, "South");
        this.panel1.add(this.button1, null);
        this.add(this.panel2, "North");
        this.add(this.panel3, "West");
        this.add(this.panel4, "East");
        this.add(this.panel5, "Center");
        this.panel5.add(this.PromptTxt, null);
        this.panel5.add(this.DetailsTxt, null);
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    void this_windowDeactivated(final WindowEvent windowEvent) {
        if (this.isVisible()) {
            this.getToolkit().beep();
            this.setVisible(true);
        }
    }
    
    void button1_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            MessageBox.this.this_windowClosing(windowEvent);
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
            MessageBox.this.this_windowDeactivated(windowEvent);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            MessageBox.this.button1_actionPerformed(actionEvent);
        }
    }
}
