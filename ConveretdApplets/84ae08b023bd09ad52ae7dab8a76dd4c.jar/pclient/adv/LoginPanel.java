// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JPasswordField;
import java.awt.Container;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.pchat.sc.WindowUtil;
import com.pchat.sc.StringUtil;
import java.util.ArrayList;
import pclient.shd.SmileDef;
import java.net.URL;
import java.util.Vector;
import pclient.shd.RoomItem;
import javax.swing.JOptionPane;
import pclient.shd.Config;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LoginPanel extends JPanel implements ActionListener
{
    private ComInter adBrand;
    protected ComInter adText;
    private JButton connButton;
    protected JTextField userField;
    private JTextField passField;
    private JTextField emailField;
    private JTextField roomPass;
    private JComboBox joinRoom;
    protected AppletSpice appletChat;
    
    public LoginPanel(final AppletSpice appletChat) {
        this.adBrand = null;
        this.adText = null;
        this.appletChat = appletChat;
        this.setOpaque(true);
        this.buildUI();
        this.loadRoomList();
    }
    
    public Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.doConnect();
    }
    
    public static void noUser(final Component component, final Config config) {
        JOptionPane.showMessageDialog(component, config.get("Msg.NoUser", "Please enter a user Name."));
    }
    
    protected void loadRoomList() {
        if (!this.showSelectRoom()) {
            return;
        }
        new Thread(new ClientRmRun(this)).start();
    }
    
    public void setRoomList(final RoomItem[] array, final String s) {
        this.joinRoom.removeAllItems();
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.joinRoom.addItem(array[i].encodeSimple());
            if (array[i].name.equals(s)) {
                this.joinRoom.setSelectedIndex(i);
            }
        }
    }
    
    public static Vector load(final AppletSpice appletSpice) {
        final String value = appletSpice.paraConf.get("Net.PathRm", "/parachat/chat/approoms.jsp");
        int int1 = appletSpice.paraConf.getInt("Net.PortRm", -1);
        if (int1 < 0) {
            int1 = 80;
        }
        final String host = appletSpice.paraConf.getApplet().getCodeBase().getHost();
        final String string = value + getParamForURL(appletSpice.paraConf.get("Net.Site"));
        URL url;
        try {
            url = new URL("http", host, int1, string);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return loadPage(url, appletSpice);
    }
    
    private static Vector loadPage(final URL url, final AppletSpice appletSpice) {
        appletSpice.paraConf.printer().print("rmlist," + url.toExternalForm());
        return SmileDef.getWebData(url);
    }
    
    private static String getParamForURL(final String s) {
        return "?" + "site" + "=" + ClientCharacter.encode(s);
    }
    
    public static RoomItem[] parseRooms(final Vector vector, final AppletSpice appletSpice) {
        appletSpice.paraConf.printer().print("rmlist," + vector);
        if (vector == null) {
            return null;
        }
        boolean b = false;
        final ArrayList list = new ArrayList();
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            if (s.startsWith("Result=")) {
                if (s.startsWith("Result=Success")) {
                    b = true;
                }
                else if (s.startsWith("Result=Error")) {
                    System.out.println(s);
                }
                else {
                    System.out.println("ERR84323:" + s);
                }
            }
            else if (s.startsWith("RM=")) {
                parseParam(s, list);
            }
        }
        if (!b) {
            return null;
        }
        return list.toArray(new RoomItem[0]);
    }
    
    private static void parseParam(String substring, final ArrayList list) {
        if (substring.length() <= 3) {
            return;
        }
        substring = substring.substring(3);
        final String[] splitString = StringUtil.splitString(substring, ",", true);
        if (splitString == null) {
            return;
        }
        if (splitString.length != 4) {
            return;
        }
        final RoomItem roomItem = new RoomItem("", 0);
        roomItem.name = splitString[0];
        roomItem.count = WindowUtil.parseInt(splitString[1], 0);
        roomItem.locked = false;
        roomItem.secondary = false;
        if ("U".equals(splitString[2])) {
            roomItem.locked = false;
        }
        else if ("L".equals(splitString[2])) {
            roomItem.locked = true;
        }
        if ("S".equals(splitString[3])) {
            roomItem.secondary = false;
        }
        else if ("D".equals(splitString[3])) {
            roomItem.secondary = true;
        }
        list.add(roomItem);
    }
    
    private void buildUI() {
        final JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setLayout(new BorderLayout());
        final JPanel panel2 = new JPanel();
        panel2.setOpaque(true);
        panel2.setLayout(new GridBagLayout());
        panel2.add(this.createPanel());
        this.setLayout(new BorderLayout());
        this.add(panel2, "Center");
        this.add(panel, "South");
        if (this.appletChat.paraConf.getBool("Ad.BrandOn", false) && this.appletChat.paraConf.getBool("Ad.BrandLoginOn", false)) {
            final String s = "pclient.adx.AdBrand";
            try {
                (this.adBrand = (ComInter)Class.forName(s).newInstance()).setPara(this.appletChat);
            }
            catch (Exception ex) {
                System.out.println("Error #79382." + s);
                ex.printStackTrace();
                this.adBrand = null;
            }
        }
        if (this.adBrand != null) {
            if (this.appletChat.paraConf.getBool("Ad.BrandTop", true)) {
                this.add((Component)this.adBrand, "North");
            }
            else {
                panel.add((Component)this.adBrand, "Center");
            }
            this.adBrand.restart();
        }
        if (this.appletChat.paraConf.getBool("Ad.TextOnLogin", false)) {
            final String s2 = "pclient.adx.AdText";
            try {
                (this.adText = (ComInter)Class.forName(s2).newInstance()).setPara(this.appletChat);
                this.adText.restart();
            }
            catch (Exception ex2) {
                System.out.println("Error #79382." + s2);
                ex2.printStackTrace();
                this.adText = null;
            }
        }
        if (this.adText != null) {
            panel.add((Component)this.adText, "South");
        }
    }
    
    private JComponent createPanel() {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setOpaque(true);
        verticalPanel.setAlignmentY(0.5f);
        verticalPanel.setAlignmentX(0.5f);
        verticalPanel.setBorder(new CompoundBorder(BorderFactory.createEtchedBorder(1), new EmptyBorder(30, 30, 30, 30)));
        (this.connButton = new JButton(this.appletChat.paraConf.get("Bt.Connect", "Connect"))).addActionListener(this);
        this.connButton.setAlignmentX(0.5f);
        this.connButton.setAlignmentY(0.5f);
        verticalPanel.add(this.boxes());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(this.connButton);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        return verticalPanel;
    }
    
    private JPanel boxes() {
        final JLabel label = new JLabel(this.appletChat.paraConf.get("Lb.Login.User", "User Name:"), 11);
        final JLabel label2 = new JLabel(this.appletChat.paraConf.get("Lb.Login.Pass", "Password:"), 11);
        final JLabel label3 = new JLabel(this.appletChat.paraConf.get("Lb.Login.Info", "User Info:"), 11);
        final JLabel label4 = new JLabel(this.appletChat.paraConf.get("Lb.Login.RoomPass", "Room Password:"), 11);
        final JLabel label5 = new JLabel(this.appletChat.paraConf.get("Lb.Login.Room", "Room:"), 11);
        this.userField = this.genField();
        this.passField = this.genPasswd();
        this.emailField = this.genField();
        this.roomPass = this.genField();
        this.joinRoom = this.genSelect();
        label.setLabelFor(this.userField);
        label2.setLabelFor(this.passField);
        label3.setLabelFor(this.emailField);
        label4.setLabelFor(this.roomPass);
        final JPanel panel = new JPanel(new SpringLayout());
        int n = 1;
        panel.add(label);
        panel.add(this.userField);
        if (this.appletChat.paraConf.getBool("Op.UserPass", false)) {
            panel.add(label2);
            panel.add(this.passField);
            ++n;
        }
        if (this.appletChat.paraConf.getBool("Op.UserInfo", false)) {
            panel.add(label3);
            panel.add(this.emailField);
            ++n;
        }
        if (this.appletChat.paraConf.getBool("Op.RoomPass", false)) {
            panel.add(label4);
            panel.add(this.roomPass);
            ++n;
        }
        if (this.showSelectRoom()) {
            panel.add(label5);
            panel.add(this.joinRoom);
            ++n;
        }
        CompUtil.makeCompactGrid(panel, n, 2, 6, 6, 6, 6);
        return panel;
    }
    
    private boolean showSelectRoom() {
        return this.appletChat.paraConf.isRoam() && this.appletChat.paraConf.getBool("Op.SelRoom", false);
    }
    
    private JTextField genField() {
        final JTextField textField = new JTextField(10);
        this.populate(textField);
        return textField;
    }
    
    private JTextField genPasswd() {
        final JPasswordField passwordField = new JPasswordField(10);
        this.populate(passwordField);
        return passwordField;
    }
    
    private JComboBox genSelect() {
        return new JComboBox();
    }
    
    private void populate(final JTextField textField) {
        textField.addActionListener(this);
        textField.setMinimumSize(new Dimension(16, 10));
        textField.setPreferredSize(new Dimension(180, 20));
        textField.setMaximumSize(new Dimension(200, 24));
    }
    
    private void doConnect() {
        String s = this.userField.getText();
        String text = this.passField.getText();
        String text2 = this.emailField.getText();
        String text3 = this.roomPass.getText();
        if (StringUtil.isTrimmedEmpty(s)) {
            noUser(this, this.appletChat.paraConf);
            return;
        }
        if (StringUtil.isTrimmedEmpty(text)) {
            text = null;
        }
        if (StringUtil.isTrimmedEmpty(text2)) {
            text2 = null;
        }
        if (StringUtil.isTrimmedEmpty(text3)) {
            text3 = null;
        }
        if (!this.appletChat.paraConf.getBool("Op.UserSpace", false)) {
            s = s.replace(' ', '_');
        }
        String decodeSimple = null;
        if (this.showSelectRoom()) {
            decodeSimple = RoomItem.decodeSimple((String)this.joinRoom.getSelectedItem());
        }
        this.appletChat.connPanelLogin(decodeSimple, s, text, text2, text3);
    }
}
