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
import java.util.StringTokenizer;
import com.pchat.sc.StringUtil;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import pclient.adv.AppletSpice;
import java.awt.event.ActionListener;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopSound extends JFrame implements ComInter, ActionListener
{
    private String ACT_T;
    private String ACT_S;
    private AppletSpice paraApplet;
    private DefaultListModel soundListModel;
    private JList soundList;
    private String privateParty;
    
    public PopSound() {
        this.ACT_T = "T";
        this.ACT_S = "S";
        this.privateParty = null;
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title());
        this.setSize(200, 420);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        String s = this.paraApplet.paraConf.get("Cf.Sounds", "");
        if (StringUtil.isTrimmedEmpty(s)) {
            s = this.paraApplet.paraConf.serverConf("Cf.Sounds", "");
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ", ");
        while (stringTokenizer.hasMoreTokens()) {
            this.soundListModel.addElement(stringTokenizer.nextToken());
        }
        WindowUtil.center(this);
    }
    
    protected void setPrivate(final String privateParty) {
        this.privateParty = privateParty;
    }
    
    public void process(final int n, final String[] array) {
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String s = this.soundList.getSelectedValue();
        final String actionCommand = actionEvent.getActionCommand();
        if (this.ACT_T.equals(actionCommand)) {
            if (s != null) {
                this.paraApplet.paraConf.play(s);
            }
            return;
        }
        if (this.ACT_S.equals(actionCommand)) {
            if (s != null) {
                this.setVisible(false);
                if (this.privateParty == null) {
                    this.paraApplet.chatModel.cmAudio(s);
                }
                else {
                    this.paraApplet.chatModel.cmAudio(this.privateParty, s);
                }
                this.soundList.clearSelection();
            }
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
        final DefaultListModel<Object> soundListModel = new DefaultListModel<Object>();
        final JList soundList = new JList(soundListModel);
        soundList.setVisibleRowCount(16);
        soundList.setSelectionMode(0);
        final JScrollPane scrollPane = new JScrollPane(soundList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(100, 120));
        scrollPane.setPreferredSize(new Dimension(120, 160));
        this.soundListModel = soundListModel;
        this.soundList = soundList;
        return scrollPane;
    }
    
    private JPanel getButtons() {
        final JButton button = new JButton(this.paraApplet.paraConf.get("Bt.Au.Test", "Test"));
        button.addActionListener(this);
        button.setActionCommand(this.ACT_T);
        final JButton button2 = new JButton(this.paraApplet.paraConf.get("Bt.Au.Send", "Send"));
        button2.addActionListener(this);
        button2.setActionCommand(this.ACT_S);
        final JPanel panel = new JPanel(new FlowLayout(1));
        panel.add(button);
        panel.add(button2);
        return panel;
    }
}
