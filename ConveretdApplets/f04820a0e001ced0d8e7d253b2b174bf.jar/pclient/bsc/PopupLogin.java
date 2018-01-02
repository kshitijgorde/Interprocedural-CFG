// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import pclient.shd.ClientUtil;
import com.pchat.sc.StringUtil;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class PopupLogin extends Panel implements ActionListener
{
    private Button connButton;
    private TextField inputField;
    private AppletBase appletChat;
    
    public PopupLogin(final AppletBase appletChat) {
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
        final Label label = new Label("User Name:");
        (this.inputField = new TextField(12)).addActionListener(this);
        (this.connButton = new Button("Connect")).addActionListener(this);
        this.setLayout(new FlowLayout(0));
        this.add(label);
        this.add(this.inputField);
        this.add(this.connButton);
    }
    
    private void doConnect() {
        final String text = this.inputField.getText();
        if (StringUtil.isEmpty(text)) {
            this.inputField.setText("Enter a name");
            return;
        }
        this.appletChat.connPopLogin(ClientUtil.fixUser(text, this.appletChat.paraConf));
    }
}
