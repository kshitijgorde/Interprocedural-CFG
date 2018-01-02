// 
// Decompiled by Procyon v0.5.30
// 

package pclient.ady;

import com.pchat.sc.StringUtil;
import java.awt.event.MouseEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import pclient.adv.RoomNames;
import pclient.adv.LoginNames;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.MouseAdapter;

public class TrCallBack extends MouseAdapter implements TreeSelectionListener, ActionListener
{
    private TrStructView treeObj;
    private JPopupMenu ignorePop;
    private JPopupMenu unignorePop;
    private JPopupMenu roomPop;
    private int lastPopX;
    private int lastPopY;
    
    public TrCallBack(final TrStructView treeObj) {
        this.ignorePop = null;
        this.unignorePop = null;
        this.roomPop = null;
        this.lastPopX = -1;
        this.lastPopY = -1;
        this.treeObj = treeObj;
        this.ignorePop = LoginNames.createIgnore(this, treeObj.appletChat);
        this.unignorePop = LoginNames.createUnIgnore(this, treeObj.appletChat);
        this.roomPop = RoomNames.createPop(this, treeObj.appletChat);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.treeObj.appletChat.paraConf.printer().print("com=" + actionCommand + this.lastPopX + " " + this.lastPopY);
        if (this.lastPopX < 0 || this.lastPopY < 0) {
            return;
        }
        final int rowForLocation = this.treeObj.treeView.getRowForLocation(this.lastPopX, this.lastPopY);
        final TreePath pathForLocation = this.treeObj.treeView.getPathForLocation(this.lastPopX, this.lastPopY);
        if (rowForLocation < 0) {
            return;
        }
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)pathForLocation.getLastPathComponent();
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        this.treeObj.appletChat.paraConf.printer().print("node " + defaultMutableTreeNode + " name " + trCellData.name);
        if (trCellData.type == TrCellData.T_USER) {
            this.actUser(actionCommand, trCellData);
            return;
        }
        if (trCellData.type == TrCellData.T_ROOM) {
            this.actRoom(actionCommand, trCellData);
        }
    }
    
    private void actUser(final String s, final TrCellData trCellData) {
        if ("ST".equals(s)) {
            this.treeObj.appletChat.chatModel.cmQueryProfile(trCellData.name);
        }
        else if ("PV".equals(s)) {
            this.privateChat(trCellData.name);
        }
        else if ("IG".equals(s)) {
            if (this.treeObj.appletChat.chatModel.cmIsIgnored(trCellData.name)) {
                this.treeObj.appletChat.chatModel.cmDeleteIgnore(trCellData.name);
            }
            else {
                this.treeObj.appletChat.chatModel.cmAddIgnore(trCellData.name);
            }
        }
        else if ("VB".equals(s)) {
            this.treeObj.appletChat.chatModel.cmAvViewBc(trCellData.name);
        }
        else {
            System.out.println("warn452=" + trCellData.name);
        }
    }
    
    private void actRoom(final String s, final TrCellData trCellData) {
        if ("jn".equals(s)) {
            this.roamRoom(trCellData.name);
        }
        else if ("Rf".equals(s)) {
            this.treeObj.appletChat.chatModel.cmQueryList();
            this.treeObj.appletChat.chatModel.cmQueryUsers(trCellData.name);
        }
        else {
            System.out.println("warn456=" + trCellData.name);
        }
    }
    
    public void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
        final TreePath newLeadSelectionPath = treeSelectionEvent.getNewLeadSelectionPath();
        if (newLeadSelectionPath == null) {
            return;
        }
        this.treeObj.appletChat.paraConf.printer().print("tree=" + newLeadSelectionPath.getLastPathComponent());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int rowForLocation = this.treeObj.treeView.getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
        final TreePath pathForLocation = this.treeObj.treeView.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
        if (rowForLocation < 0) {
            return;
        }
        if (mouseEvent.getClickCount() == 1) {
            this.treeObj.appletChat.paraConf.printer().print("Single clicked on " + rowForLocation);
            this.singleClick(rowForLocation, pathForLocation);
        }
        else if (mouseEvent.getClickCount() == 2) {
            this.treeObj.appletChat.paraConf.printer().print("Double clicked on " + rowForLocation);
            this.doubleClick(rowForLocation, pathForLocation);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    private void maybeShowPopup(final MouseEvent mouseEvent) {
        if (!mouseEvent.isPopupTrigger()) {
            return;
        }
        final int rowForLocation = this.treeObj.treeView.getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
        final TreePath pathForLocation = this.treeObj.treeView.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
        if (rowForLocation < 0) {
            return;
        }
        this.lastPopX = mouseEvent.getX();
        this.lastPopY = mouseEvent.getY();
        this.rightClick(mouseEvent, pathForLocation);
    }
    
    private void rightClick(final MouseEvent mouseEvent, final TreePath treePath) {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)treePath.getLastPathComponent();
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        this.treeObj.appletChat.paraConf.printer().print("node " + defaultMutableTreeNode + " name " + trCellData.name);
        if (trCellData.type == TrCellData.T_ROOM) {
            this.roomPop.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        if (trCellData.type == TrCellData.T_USER && trCellData.name != null) {
            if (this.treeObj.appletChat.chatModel.cmIsIgnored(trCellData.name)) {
                this.unignorePop.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            }
            else {
                this.ignorePop.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
    
    private void singleClick(final int n, final TreePath treePath) {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)treePath.getLastPathComponent();
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        this.treeObj.appletChat.paraConf.printer().print("node," + defaultMutableTreeNode + " name," + trCellData.name);
        if (trCellData.type == TrCellData.T_ROOM) {
            this.treeObj.appletChat.chatModel.cmQueryUsers(trCellData.name);
            return;
        }
        if (trCellData.type == TrCellData.T_ROOT) {
            this.treeObj.appletChat.chatModel.cmQueryList();
        }
    }
    
    private void doubleClick(final int n, final TreePath treePath) {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)treePath.getLastPathComponent();
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
        this.treeObj.appletChat.paraConf.printer().print("node=" + defaultMutableTreeNode + " name=" + trCellData.name);
        if (trCellData.type == TrCellData.T_ROOM) {
            this.roamRoom(trCellData.name);
            return;
        }
        if (trCellData.type == TrCellData.T_USER) {
            this.privateChat(trCellData.name);
        }
    }
    
    private void roamRoom(final String s) {
        if (StringUtil.isEmpty(s)) {
            return;
        }
        this.treeObj.appletChat.connRoamSwitch(s);
    }
    
    private void privateChat(final String s) {
        if (StringUtil.isEmpty(s)) {
            return;
        }
        this.treeObj.appletChat.startPrivate(s);
    }
}
