// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class NamesMenu extends MouseAdapter implements ActionListener
{
    public static final String ACT_ST = "ST";
    public static final String ACT_PRIV = "PV";
    public static final String ACT_IG = "IG";
    public static final String ACT_VB = "VB";
    private LoginNames userArea;
    
    public NamesMenu(final LoginNames userArea) {
        this.userArea = userArea;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final String selectedUser = this.userArea.selectedUser();
        if (selectedUser == null) {
            return;
        }
        if ("ST".equals(actionCommand)) {
            this.userArea.appletChat.chatModel.cmQueryProfile(selectedUser);
            return;
        }
        if ("PV".equals(actionCommand)) {
            this.userArea.appletChat.startPrivate(selectedUser);
            return;
        }
        if ("IG".equals(actionCommand)) {
            if (this.userArea.appletChat.chatModel.cmIsIgnored(selectedUser)) {
                this.userArea.appletChat.chatModel.cmDeleteIgnore(selectedUser);
            }
            else {
                this.userArea.appletChat.chatModel.cmAddIgnore(selectedUser);
            }
        }
        if ("VB".equals(actionCommand)) {
            this.userArea.appletChat.chatModel.cmAvViewBc(selectedUser);
        }
        if ("Tip.Av".equals(actionCommand)) {
            this.userArea.appletChat.startAv(selectedUser);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int locationToIndex = this.userArea.nameList.locationToIndex(mouseEvent.getPoint());
        if (mouseEvent.getClickCount() == 1) {
            this.userArea.appletChat.paraConf.printer().print("Single clicked on " + locationToIndex);
            this.userArea.singleClick(locationToIndex);
            return;
        }
        if (mouseEvent.getClickCount() == 2) {
            this.userArea.appletChat.paraConf.printer().print("Double clicked on " + locationToIndex);
            this.userArea.doubleClick(locationToIndex);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    private void maybeShowPopup(final MouseEvent mouseEvent) {
        if (!this.userArea.appletChat.paraConf.getBool("Op.Ur.RtCk", true)) {
            this.userArea.appletChat.paraConf.printer().print("right click disabled");
            return;
        }
        String selectedUser = null;
        if (!mouseEvent.isPopupTrigger()) {
            return;
        }
        final int locationToIndex = this.userArea.nameList.locationToIndex(mouseEvent.getPoint());
        if (locationToIndex >= 0) {
            this.userArea.nameList.setSelectedIndex(locationToIndex);
            selectedUser = this.userArea.selectedUser();
        }
        if (selectedUser != null && this.userArea.appletChat.chatModel.cmIsIgnored(selectedUser)) {
            this.userArea.unignorePop.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        this.userArea.ignorePop.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        this.userArea.appletChat.paraConf.printer().print(" show popup on user item " + locationToIndex);
    }
}
