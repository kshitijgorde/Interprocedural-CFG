// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Checkbox;
import pclient.bsc.BaseChat;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class CreateRoom extends Panel implements ActionListener
{
    private BaseChat pChat;
    private ControlPanel pControls;
    private Checkbox passCheck;
    private TextField passInput;
    private Button pCreateButton;
    private TextField pInputRoom;
    
    public CreateRoom(final BaseChat pChat, final ControlPanel pControls) {
        this.pChat = pChat;
        this.pControls = pControls;
        this.buildGUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pCreateButton) {
            this.clickedCreate();
        }
        else if (actionEvent.getSource() == this.pInputRoom) {
            this.clickedCreate();
        }
    }
    
    private void clickedCreate() {
        String text = null;
        if (this.passCheck.getState()) {
            text = this.passInput.getText();
        }
        final String text2 = this.pInputRoom.getText();
        this.pChat.paraConf.printer().print("input room:" + text2 + " " + text);
        if (StringUtil.isEmpty(text2)) {
            return;
        }
        this.pControls.setVisible(false);
        this.pChat.parentComp.connRoamCreate(text2, text);
    }
    
    private void buildGUI() {
        final Panel buildCenter = this.buildCenter();
        final Panel buildTop = this.buildTop();
        this.setLayout(new BorderLayout());
        this.add(buildCenter, "North");
        this.add(buildTop, "Center");
    }
    
    private Panel buildTop() {
        this.passCheck = new Checkbox("Add a Password", false);
        this.passInput = new TextField(14);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0));
        panel.add(this.passCheck);
        panel.add(this.passInput);
        return panel;
    }
    
    private Panel buildCenter() {
        final Panel panel = new Panel();
        final Label label = new Label("Enter Room Name");
        final TextField pInputRoom = new TextField(24);
        pInputRoom.addActionListener(this);
        final Button pCreateButton = new Button("Create");
        pCreateButton.addActionListener(this);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(0, 1, 1));
        panel2.add(label);
        panel2.add(pInputRoom);
        panel2.add(pCreateButton);
        this.pCreateButton = pCreateButton;
        this.pInputRoom = pInputRoom;
        return panel2;
    }
}
