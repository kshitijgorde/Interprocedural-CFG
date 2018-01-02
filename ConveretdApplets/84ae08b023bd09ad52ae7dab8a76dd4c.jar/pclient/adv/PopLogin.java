// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import com.pchat.sc.StringUtil;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class PopLogin extends JPanel implements ActionListener
{
    private JButton connButton;
    protected JTextField inputField;
    private AppletSpice appletChat;
    
    public PopLogin(final AppletSpice appletChat) {
        this.appletChat = appletChat;
        this.buildUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.connButton || source == this.inputField) {
            this.doConnect();
        }
    }
    
    private void buildUI() {
        final JLabel label = new JLabel(this.appletChat.paraConf.get("Lb.Login.User", "User Name:"), 4);
        (this.inputField = new JTextField(10)).addActionListener(this);
        label.setLabelFor(this.inputField);
        (this.connButton = new JButton(this.appletChat.paraConf.get("Bt.Connect", "Connect"))).addActionListener(this);
        this.setLayout(new FlowLayout(0));
        this.add(label);
        this.add(this.inputField);
        this.add(this.connButton);
    }
    
    private void doConnect() {
        String s = this.inputField.getText();
        if (StringUtil.isTrimmedEmpty(s)) {
            LoginPanel.noUser(this, this.appletChat.paraConf);
            return;
        }
        if (!this.appletChat.paraConf.getBool("Op.UserSpace", false)) {
            s = s.replace(' ', '_');
        }
        this.appletChat.connPopLogin(s);
    }
}
