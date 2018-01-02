// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.WindowAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.event.WindowListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import jclass.bwt.JCLabel;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;

public class Options extends Frame implements Runnable
{
    Color DefaultColor;
    Thread myThread;
    BorderLayout borderLayout1;
    Panel panel1;
    Panel panel2;
    Panel panel3;
    Panel panel4;
    FlowLayout flowLayout1;
    Panel ContentPanel;
    GridLayout gridLayout1;
    FlowLayout flowLayout2;
    FlowLayout flowLayout3;
    Checkbox PrvChatChk;
    Checkbox RoomSoundsChk;
    Button OkBtn;
    Checkbox SystemSoundsChk;
    JCLabel ImgLabel2;
    BorderLayout borderLayout2;
    
    public Options() {
        this.DefaultColor = loader.outputBgColor;
        this.borderLayout1 = new BorderLayout();
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.panel3 = new Panel();
        this.panel4 = new Panel();
        this.flowLayout1 = new FlowLayout();
        this.ContentPanel = new Panel();
        this.gridLayout1 = new GridLayout();
        this.flowLayout2 = new FlowLayout();
        this.flowLayout3 = new FlowLayout();
        this.PrvChatChk = new Checkbox();
        this.RoomSoundsChk = new Checkbox();
        this.OkBtn = new Button();
        this.SystemSoundsChk = new Checkbox();
        this.ImgLabel2 = new JCLabel();
        this.borderLayout2 = new BorderLayout();
        try {
            if (this.myThread == null) {
                (this.myThread = new Thread(this)).start();
            }
            this._$3231();
            this.pack();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$3231() throws Exception {
        this.setLayout(this.borderLayout1);
        this.setFont(new Font("Helvetica", 0, 12));
        this.setSize(new Dimension(330, 200));
        this.setBackground(loader.secondaryBgColor);
        this.setForeground(loader.secondaryFgColor);
        this.setTitle("Chat Room Options");
        this.setResizable(false);
        this.addWindowListener(new 1());
        this.panel1.setLayout(this.borderLayout2);
        this.panel2.setLayout(this.flowLayout3);
        this.panel3.setLayout(this.flowLayout2);
        this.flowLayout1.setVgap(10);
        this.gridLayout1.setRows(3);
        this.gridLayout1.setColumns(1);
        this.gridLayout1.setVgap(3);
        this.flowLayout2.setHgap(20);
        this.flowLayout3.setHgap(20);
        this.PrvChatChk.setState(true);
        this.PrvChatChk.setFont(new Font("Helvetica", 1, 13));
        this.PrvChatChk.setLabel("Accept Incoming Private Chat Requests");
        this.RoomSoundsChk.setFont(new Font("Helvetica", 1, 13));
        this.RoomSoundsChk.setLabel("Extended Room Sounds Enabled");
        this.OkBtn.setForeground(loader.buttonFgColor);
        this.OkBtn.setBackground(loader.buttonBgColor);
        this.OkBtn.setFont(new Font("Helvetica", 1, 14));
        this.OkBtn.setLabel("   DONE   ");
        this.SystemSoundsChk.setState(true);
        this.ImgLabel2.setHighlightThickness(10);
        this.ImgLabel2.setForeground(Color.black);
        this.ImgLabel2.setInsets(new Insets(5, 5, 2, 5));
        this.ImgLabel2.setShadowThickness(2);
        this.ImgLabel2.setFont(new Font("Helvetica", 1, 14));
        this.ImgLabel2.setLabel("Chat Room Options");
        this.ImgLabel2.setAlignment(1);
        this.ImgLabel2.setShadowType(5);
        this.ImgLabel2.setText("Chat Room Options");
        this.SystemSoundsChk.setFont(new Font("Helvetica", 1, 13));
        this.SystemSoundsChk.setLabel("Standard Room Sounds Enabled");
        this.OkBtn.addActionListener(new 2());
        this.ContentPanel.setLayout(this.gridLayout1);
        this.flowLayout1.setAlignment(2);
        this.flowLayout1.setHgap(10);
        this.panel4.setLayout(this.flowLayout1);
        this.add(this.panel1, "North");
        this.panel1.add(this.ImgLabel2, "Center");
        this.add(this.panel2, "East");
        this.add(this.panel3, "West");
        this.add(this.panel4, "South");
        this.panel4.add(this.OkBtn, null);
        this.add(this.ContentPanel, "Center");
        this.ContentPanel.add(this.PrvChatChk, null);
        this.ContentPanel.add(this.SystemSoundsChk, null);
        this.ContentPanel.add(this.RoomSoundsChk, null);
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void run() {
        final boolean b = true;
        while (b) {
            try {
                Thread.sleep(loader.roomDelay);
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
            }
            loader.PermitInput = true;
        }
        if (this.myThread != null) {
            this.myThread.stop();
            this.myThread = null;
        }
    }
    
    void OkBtn_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    void ColorChoice_itemStateChanged(final ItemEvent itemEvent) {
    }
    
    void this_windowDeactivated(final WindowEvent windowEvent) {
        if (this.isVisible()) {
            this.getToolkit().beep();
        }
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            Options.this.this_windowClosing(windowEvent);
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
            Options.this.this_windowDeactivated(windowEvent);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Options.this.OkBtn_actionPerformed(actionEvent);
        }
    }
}
