// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import com.pchat.sc.StringUtil;
import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import java.awt.Dialog;

public class AvRequestDia extends Dialog
{
    private PrivateWindow privWin;
    private Label infoLabel;
    private Button okButton;
    private Button rejectButton;
    private Button ignoreButton;
    private String thisUser;
    private String theParty;
    private String roomName;
    
    public AvRequestDia(final PrivateWindow privWin, final String thisUser, final String theParty, final String roomName) {
        super(privWin, "Audio/Video - Audio/Video Chat Request", false);
        this.privWin = privWin;
        this.thisUser = thisUser;
        this.theParty = theParty;
        this.roomName = roomName;
        this.buildGUI();
        this.setSize(460, 160);
        try {
            WindowUtil.center(this.privWin, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void shutdown() {
        this.setVisible(false);
        this.dispose();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.okButton) {
                this.privWin.handleAvReqAccept();
            }
            else if (event.target == this.ignoreButton) {
                this.privWin.handleAvIgnore();
            }
            else if (event.target == this.rejectButton) {
                this.privWin.handleAvReqReject();
            }
            return super.handleEvent(event);
        }
        if (event.id == 201) {
            this.privWin.handleAvCancel();
        }
        else if (event.id == 205) {}
        return super.handleEvent(event);
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        this.add("South", this.buildButtons());
        (this.infoLabel = new Label("A user requests video chat. " + this.getInfo(this.theParty, this.thisUser, this.roomName))).setForeground(Color.blue);
        this.add("North", this.infoLabel);
    }
    
    private Panel buildButtons() {
        final Panel panel = new Panel();
        this.okButton = new Button("Accept");
        this.rejectButton = new Button("Reject");
        this.ignoreButton = new Button("Ignore");
        panel.add(this.okButton);
        panel.add(this.rejectButton);
        panel.add(this.ignoreButton);
        return panel;
    }
    
    private String getInfo(final String s, final String s2, final String s3) {
        String s4 = "  " + "To:" + " " + s;
        if (!StringUtil.isTrimmedEmpty(s3)) {
            s4 = s4 + " <" + "Room" + " " + s3 + ">";
        }
        return s4 + "    " + "From:" + " " + this.thisUser;
    }
}
