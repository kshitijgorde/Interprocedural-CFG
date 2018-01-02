// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.List;
import pclient.bsc.BaseChat;
import java.awt.event.ActionListener;
import pclient.bsc.CommonInter;
import java.awt.Frame;

public class PopAvViewBcast extends Frame implements CommonInter, ActionListener
{
    private String ACT_A;
    private String ACT_REJ;
    private String ACT_IG;
    private BaseChat paraApplet;
    private List userList;
    
    public PopAvViewBcast() {
        this.ACT_A = "A";
        this.ACT_REJ = "R";
        this.ACT_IG = "IG";
    }
    
    public void setPara(final BaseChat paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title() + ": Requests for Viewing Broadcast");
        this.setSize(420, 520);
        this.buildGUI();
        this.enableEvents(64L);
        WindowUtil.center(this);
    }
    
    public void process(final int n, final String[] array) {
        if (array.length > 1) {
            final String s = array[0];
            final String s2 = array[1];
            if (s == null) {
                return;
            }
            if (!this.hasIt(s)) {
                this.userList.add(s);
            }
        }
    }
    
    public boolean hasIt(final String s) {
        for (int i = 0; i < this.userList.getItemCount(); ++i) {
            if (this.userList.getItem(i).equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        final int id = windowEvent.getID();
        if (id != 205) {
            if (id != 206) {
                if (id == 201) {
                    this.setVisible(false);
                }
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String selectedItem = this.userList.getSelectedItem();
        final String actionCommand = actionEvent.getActionCommand();
        if (selectedItem == null) {
            return;
        }
        this.userList.remove(selectedItem);
        if (this.ACT_A.equals(actionCommand)) {
            this.paraApplet.chatModel.cmAvAcceptBc(selectedItem);
            return;
        }
        if (this.ACT_REJ.equals(actionCommand)) {
            this.paraApplet.chatModel.cmAvRejBc(selectedItem);
            return;
        }
        if (this.ACT_IG.equals(actionCommand)) {
            this.paraApplet.chatModel.cmAddIgnore(selectedItem);
        }
    }
    
    private void buildGUI() {
        final Component buildList = this.buildList();
        final Panel buttons = this.getButtons();
        final Label label = new Label(" ");
        this.setLayout(new BorderLayout());
        this.add("North", label);
        this.add("Center", buildList);
        this.add("South", buttons);
    }
    
    private Component buildList() {
        return this.userList = new List(28, false);
    }
    
    private Panel getButtons() {
        final Button button = new Button("Accept");
        button.addActionListener(this);
        button.setActionCommand(this.ACT_A);
        final Button button2 = new Button("Reject");
        button2.addActionListener(this);
        button2.setActionCommand(this.ACT_REJ);
        final Button button3 = new Button("Ignore User");
        button3.addActionListener(this);
        button3.setActionCommand(this.ACT_IG);
        final Panel panel = new Panel(new FlowLayout(1));
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        return panel;
    }
}
