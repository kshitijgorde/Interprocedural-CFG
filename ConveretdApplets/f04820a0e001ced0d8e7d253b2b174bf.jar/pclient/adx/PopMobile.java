// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.Border;
import pclient.adv.CompUtil;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import pclient.adv.ClientCharacter;
import com.pchat.sc.StringUtil;
import javax.swing.JTextArea;
import pclient.adv.AppletSpice;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopMobile extends JFrame implements ComInter
{
    private AppletSpice paraApplet;
    private JTextArea displayWap;
    private JTextArea displayIphone;
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title());
        this.setSize(614, 360);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        final String value = this.paraApplet.paraConf.get("Url.Wap", "");
        String string;
        if (StringUtil.isTrimmedEmpty(value)) {
            string = "Not Set";
        }
        else {
            string = value + ("?" + "site" + "=" + ClientCharacter.encode(this.paraApplet.paraConf.get("Net.Site")) + "&room" + "=" + ClientCharacter.encode(this.paraApplet.paraConf.get("Net.Room")));
        }
        this.displayWap.setText(string);
        this.setIphoneTab();
        WindowUtil.center(this);
    }
    
    private void setIphoneTab() {
        String string = "";
        if (!this.paraApplet.paraConf.getBool("Op.Iphone", false)) {
            this.displayIphone.setText(this.paraApplet.paraConf.get("Val.Iphone.No", "Connections from iPhone have been disabled by the chat administrator. If iPhone connections were enabled, the information required to connect to this room from your iPhone would display in this location."));
            return;
        }
        final String value = this.paraApplet.paraConf.get("Tt.Ipn.Url", "");
        if (!StringUtil.isTrimmedEmpty(value)) {
            string = "App Download Link: " + value + "\n";
        }
        final String value2 = this.paraApplet.paraConf.get("Tt.Ipn.Serv", "");
        String text;
        if (StringUtil.isTrimmedEmpty(value2)) {
            text = string + "Coming Soon!" + "\n";
        }
        else {
            text = string + "Server Selection: " + value2 + "\n" + "Site Name: " + this.paraApplet.paraConf.get("Net.Site") + "\n" + "Room Name: " + this.paraApplet.paraConf.get("Net.Room") + "\n";
        }
        this.displayIphone.setText(text);
    }
    
    public void process(final int n, final String[] array) {
        if (array.length > 0) {}
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    private void buildGUI() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(tabbedPane, "Center");
        final String value = this.paraApplet.paraConf.get("Tab.Ts.P", "WAP");
        final JComponent wap = this.createWap();
        if (this.paraApplet.paraConf.getBool("Op.M.Tb.W", true)) {
            tabbedPane.addTab(value, null, wap, null);
        }
        final String value2 = this.paraApplet.paraConf.get("Tab.Ts.C", "iPhone");
        final JComponent iphone = this.createIphone();
        if (this.paraApplet.paraConf.getBool("Op.M.Tb.P", true)) {
            tabbedPane.addTab(value2, null, iphone, null);
        }
    }
    
    private JComponent createWap() {
        final JLabel label = new JLabel(this.paraApplet.paraConf.get("Msg.M.Wap", "Access this room from your mobile device web browser using the web address below."));
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(CompUtil.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getWapText());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        return panel;
    }
    
    private JComponent getWapText() {
        final JTextArea displayWap = new JTextArea();
        displayWap.setEditable(false);
        displayWap.setLineWrap(true);
        displayWap.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(displayWap, 20, 31);
        this.displayWap = displayWap;
        return scrollPane;
    }
    
    private JComponent createIphone() {
        final JLabel label = new JLabel(this.paraApplet.paraConf.get("Msg.M.Ipn", "Information about how to access this room using the iPhone App can be found below:"), 2);
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(CompUtil.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getIphoneText());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        return panel;
    }
    
    private JComponent getIphoneText() {
        final JTextArea displayIphone = new JTextArea();
        displayIphone.setEditable(false);
        displayIphone.setLineWrap(true);
        displayIphone.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(displayIphone, 20, 31);
        this.displayIphone = displayIphone;
        return scrollPane;
    }
}
