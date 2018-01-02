// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import pclient.adv.AppletSpice;
import java.awt.event.ActionListener;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopAvCastReq extends JFrame implements ComInter, ActionListener
{
    private String ACT_A;
    private String ACT_REJ;
    private String ACT_IG;
    private AppletSpice paraApplet;
    private DefaultListModel userListModel;
    private JList userList;
    
    public PopAvCastReq() {
        this.ACT_A = "A";
        this.ACT_REJ = "R";
        this.ACT_IG = "IG";
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title() + ": Requests for Viewing Broadcast");
        this.setSize(420, 520);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        WindowUtil.center(this);
    }
    
    public void process(final int n, final String[] array) {
        if (array.length > 1) {
            final String s = array[0];
            final String s2 = array[1];
            if (s == null) {
                return;
            }
            if (!this.userListModel.contains(s)) {
                this.userListModel.addElement(s);
            }
        }
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String s = this.userList.getSelectedValue();
        final String actionCommand = actionEvent.getActionCommand();
        if (s == null) {
            return;
        }
        this.userListModel.removeElement(s);
        if (this.ACT_A.equals(actionCommand)) {
            this.paraApplet.chatModel.cmAvAcceptBc(s);
            return;
        }
        if (this.ACT_REJ.equals(actionCommand)) {
            this.paraApplet.chatModel.cmAvRejBc(s);
            return;
        }
        if (this.ACT_IG.equals(actionCommand)) {
            this.paraApplet.chatModel.cmAddIgnore(s);
        }
    }
    
    private void buildGUI() {
        final JComponent buildList = this.buildList();
        final JPanel buttons = this.getButtons();
        final JLabel label = new JLabel(" ");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add("North", label);
        this.getContentPane().add("Center", buildList);
        this.getContentPane().add("South", buttons);
    }
    
    private JComponent buildList() {
        final DefaultListModel<Object> userListModel = new DefaultListModel<Object>();
        final JList userList = new JList(userListModel);
        userList.setVisibleRowCount(16);
        userList.setSelectionMode(0);
        final JScrollPane scrollPane = new JScrollPane(userList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(100, 120));
        scrollPane.setPreferredSize(new Dimension(120, 160));
        this.userListModel = userListModel;
        this.userList = userList;
        return scrollPane;
    }
    
    private JPanel getButtons() {
        final JButton button = new JButton(this.paraApplet.paraConf.get("Lb.Vq.Acc", "Accept"));
        button.addActionListener(this);
        button.setActionCommand(this.ACT_A);
        final JButton button2 = new JButton(this.paraApplet.paraConf.get("Lb.Vq.Rej", "Reject"));
        button2.addActionListener(this);
        button2.setActionCommand(this.ACT_REJ);
        final JButton button3 = new JButton(this.paraApplet.paraConf.get("Lb.Vq.Ign", "Ignore User"));
        button3.addActionListener(this);
        button3.setActionCommand(this.ACT_IG);
        final JPanel panel = new JPanel(new FlowLayout(1));
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        return panel;
    }
}
